CREATE TABLE Museums (
  id         SERIAL PRIMARY KEY,
  name       TEXT NOT NULL UNIQUE,
  address    TEXT NOT NULL,
  staffCount INTEGER DEFAULT 0
);
DROP TYPE public.post;
CREATE TYPE POST AS ENUM ('headmaster', 'vice', 'guide', 'guard', 'manager');

CREATE TABLE Pavilion (
  id        SERIAL PRIMARY KEY,
  museum_id INTEGER REFERENCES Museums (id),
  name      TEXT              NOT NULL,
  floor     INTEGER DEFAULT 1 NOT NULL
);

CREATE TABLE Exhibition (
  id           SERIAL PRIMARY KEY,
  pavilion_id  INTEGER REFERENCES Pavilion (id),
  exhibitCount INTEGER DEFAULT 0,
  name         TEXT      NOT NULL,
  beginDate    TIMESTAMP NOT NULL,
  endDate      TIMESTAMP NOT NULL
);

CREATE TABLE Exhibit (
  id           SERIAL PRIMARY KEY,
  exhibiton_id INTEGER REFERENCES Exhibition (id),
  date         TIMESTAMP NOT NULL,
  name         TEXT      NOT NULL,
  author       TEXT      NOT NULL,
  copyCount    INTEGER   NOT NULL,
  original     BOOLEAN DEFAULT TRUE
);

CREATE TABLE Employee (
  id          SERIAL PRIMARY KEY,
  name        TEXT NOT NULL,
  passport_id TEXT NOT NULL,
  museum_id   INTEGER REFERENCES Museums (id),
  position    POST DEFAULT 'guide'
);

CREATE TABLE Ticket (
  id           SERIAL PRIMARY KEY,
  exhibiton_id INTEGER REFERENCES Exhibition (id),
  price        INTEGER NOT NULL
);

CREATE TABLE Visitor (
  id        SERIAL PRIMARY KEY,
  name      TEXT NOT NULL,
  ticket_id INTEGER REFERENCES Ticket (id)
);

CREATE OR REPLACE FUNCTION staff_count()
  RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
  UPDATE Museums
  SET staffCount = staffCount + 1
  WHERE Museums.id = new.museum_id;
  RETURN new;
END;
$$;

DROP TRIGGER IF EXISTS staff_count_incr
ON Employee;

CREATE TRIGGER staff_count_incr
BEFORE INSERT
  ON Employee
FOR EACH ROW
EXECUTE PROCEDURE staff_count();

---------------------

CREATE OR REPLACE FUNCTION exhibit_count()
  RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
  UPDATE Exhibition
  SET exhibitCount = Exhibition.exhibitCount + 1
  WHERE Exhibition.id = new.exhibiton_id;
  RETURN new;
END;
$$;

DROP TRIGGER IF EXISTS exhibit_count_incr
ON Exhibit;

CREATE TRIGGER exhibit_count_incr
BEFORE INSERT
  ON Exhibit
FOR EACH ROW
EXECUTE PROCEDURE exhibit_count();


------------------------
