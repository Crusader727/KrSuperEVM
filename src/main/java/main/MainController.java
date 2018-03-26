package main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import main.dao.MainDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import main.service.PersonGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class MainController {
    private PersonGenerator personGenerator;
    private MainDAO dao;
    private Random rand;

    public MainController(MainDAO md) {
        dao = md;
        if (false) {
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

    @RequestMapping(path = "/create", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> createDiagram() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage primaryStage = Application.primaryStage;
                PieChart pieChart = new PieChart();
                pieChart.setData(getAllWorkers());

                pieChart.setTitle("Employees");
                primaryStage.setTitle("PieChart");
                pieChart.setLegendVisible(false);
                pieChart.setClockwise(false);
                pieChart.setLabelsVisible(true);

                StackPane root = new StackPane();
                root.getChildren().add(pieChart);
                primaryStage.setScene(new Scene(root, 500, 400));
                primaryStage.show();
            }
        });

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    private ObservableList<PieChart.Data> getAllWorkers() {
        ObservableList<PieChart.Data> answer = FXCollections.observableArrayList();

        ArrayList<Pair<String, Integer>> employees = dao.getWorkers();
        for(Pair key : employees) {
            answer.add(new PieChart.Data(key.getKey().toString(), (Integer)key.getValue()));
        }
        return answer;
    }
}
