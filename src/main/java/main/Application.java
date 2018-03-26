package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class Application {
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//}
@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    public static Stage primaryStage;
    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }



    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
