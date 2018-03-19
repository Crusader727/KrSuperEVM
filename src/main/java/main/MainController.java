package main;

import main.dao.MainDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import main.service.PersonGenerator;

import java.sql.Timestamp;
import java.util.Random;

@RestController
@RequestMapping("/api/create")
public class MainController {
    private PersonGenerator personGenerator;
    private MainDAO dao;
    private Random rand;

    public MainController(MainDAO md) {

        long offset = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long offset2 = Timestamp.valueOf("1912-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        long diff2 = end - offset2 + 1;

        rand = new Random();
        personGenerator = new PersonGenerator();
        dao = md;

        for (int i = 0; i < 25; i++) {
            dao.createMuseum("Museum #" + (i + 1), "City #" + (i + 1));
        }
        Integer pavCount = rand.nextInt(100) + 100;
        for (int j = 0; j < pavCount; j++) {
            dao.createPavilion("Pavilion #" + j + 1, rand.nextInt(5) + 1,
                    rand.nextInt(25) + 1);
        }
        Integer exhibitionCount = rand.nextInt(200) + 100;
        for (int k = 0; k < exhibitionCount; k++) {
            Timestamp begin = new Timestamp(offset + (long) (Math.random() * diff));
            dao.createExhibition("Exhibition #" + k + 1, rand.nextInt(pavCount) + 1,
                    begin,
                    new Timestamp(begin.getTime() + (long) (Math.random() * diff)));
        }
        for (int x = 0; x < rand.nextInt(500) + 1000; x++) {
            dao.createExhibit("Exhibit #" + x + 1, rand.nextInt(exhibitionCount) + 1,
                    new Timestamp(offset2 + (long) (Math.random() * diff2)),
                    personGenerator.getRandomPerson(), rand.nextInt(3) + 1,
                    rand.nextBoolean()
            );
        }
        for (int y = 0; y < rand.nextInt(150) + 150; y++) {
            dao.createEmployee(personGenerator.getRandomPerson(),
                    "#" + rand.nextInt(9000) + 1000,
                    rand.nextInt(25) + 1, personGenerator.getRandomPosition());
        }
        for (int z = 0; z < rand.nextInt(exhibitionCount * 2) + exhibitionCount * 2; z++) {
            dao.createTicket(rand.nextInt(1000) + 400, rand.nextInt(exhibitionCount) + 1);
            dao.createVisitor(z + 1, personGenerator.getRandomPerson());
        }


    }
}
