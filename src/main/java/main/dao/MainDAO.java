package main.dao;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class MainDAO {


    private final JdbcTemplate template;

    @Autowired
    public MainDAO(JdbcTemplate template) {
        this.template = template;
    }

    public void createMuseum(String name, String address) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst = con.prepareStatement(
                    "insert into Museums(name, address)"
                            + " values(?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setString(2, address);
            return pst;
        }, keyHolder);

    }

    public void createPavilion(String name, Integer floor, Integer museumID) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst = con.prepareStatement(
                    "insert into Pavilion(name, floor, museum_id)"
                            + " values(?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setInt(2, floor);
            pst.setInt(3, museumID);
            return pst;
        }, keyHolder);

    }

    public void createExhibition(String name, Integer pavilionID, Timestamp beginDate, Timestamp endDate) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst = con.prepareStatement(
                    "insert into Exhibition(name, pavilion_id, begindate, enddate)"
                            + " values(?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setInt(2, pavilionID);
            pst.setTimestamp(3, beginDate);
            pst.setTimestamp(4, endDate);
            return pst;
        }, keyHolder);

    }

    public void createExhibit(String name, Integer exhibitonID, Timestamp date,
                              String author, Integer copyCount, Boolean original) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst = con.prepareStatement(
                    "insert into Exhibit(name, exhibiton_id, date, author, copycount, original)"
                            + " values(?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setInt(2, exhibitonID);
            pst.setTimestamp(3, date);
            pst.setString(4, author);
            pst.setInt(5, copyCount);
            pst.setBoolean(6, original);
            return pst;
        }, keyHolder);
    }

    public void createEmployee(String name, String passportID, Integer museumID,
                               String position) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst = con.prepareStatement(
                    "insert into Employee(name, passport_id, museum_id, position)"
                            + " values(?,?,?,CAST(? as post))",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setString(2, passportID);
            pst.setInt(3, museumID);
            pst.setString(4, position);
            return pst;
        }, keyHolder);
    }

    public void createTicket(Integer price, Integer exhibitionID) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst = con.prepareStatement(
                    "insert into Ticket(price, exhibiton_id)"
                            + " values(?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, price);
            pst.setInt(2, exhibitionID);

            return pst;
        }, keyHolder);
    }

    public void createVisitor(Integer ticketID, String name) {
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst = con.prepareStatement(
                    "insert into Visitor(name, ticket_id)"
                            + " values(?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, name);
            pst.setInt(2, ticketID);

            return pst;
        }, keyHolder);
    }

    public ArrayList<Pair<String, Integer>> getWorkers() {
        ArrayList<Pair<String, Integer>> result = new ArrayList<>();
        Integer viceCount = getCount("vice");
        result.add(new Pair<String, Integer>("Vice: " + viceCount, viceCount));
        Integer headmasterCount = getCount("headmaster");
        result.add(new Pair<String, Integer>("Headmaster: " + headmasterCount, headmasterCount));
        Integer guideCount = getCount("guide");
        result.add(new Pair<String, Integer>("Guide: " + guideCount, guideCount));
        Integer guardCount = getCount("guard");
        result.add(new Pair<String, Integer>("Guard: " + guardCount, guardCount));
        Integer managerCount = getCount("manager");
        result.add(new Pair<String, Integer>("Manager: " + managerCount, managerCount));
        return result;
    }

    public Integer getCount(String position) {
        return template.queryForObject(
                "select count(*) from employee where position = CAST(? as post);",
                new Object[]{position}, Integer.class);
    }
}

