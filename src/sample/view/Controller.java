package sample.view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controller.SystemController;

import java.util.Arrays;
import java.util.LinkedList;

public class Controller extends SystemController {

    public void setView() throws Exception{
        Stage primaryStage = new Stage();
        Pane root = new StackPane();
        VBox controllersPane = new VBox();
        LinkedList<Button> buttonsList = new LinkedList<>();
        LinkedList<TextField> indicatorsList = new LinkedList<>();
        Text alertMessage = new Text();
        alertMessage.setStroke(Color.RED);
        Timeline blinker = createBlinker(alertMessage);
        blinker.setOnFinished(e -> alertMessage.opacityProperty().set(0));
        Transition blink = new SequentialTransition(blinker);


        HBox tempControllerPane = new HBox();
        Label tempL = new Label("TEMPERATURE:");
        Button tempUp = new Button(">");
        TextField temperature = new TextField(""+getTemperature());
        temperature.setFont(new Font(21));
        Button tempDown = new Button("<");

        tempUp.setOnAction(ae ->{
            try {
                temperature.setText("" + incrementTemperature());
            } catch (RuntimeException e) {
                alertMessage.setText(e.getMessage());
                blink.play();
            }
        });
        temperature.textProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal.matches("\\d{0,2}"))
                temperature.setText(oldVal);
            else
                try {
                    setTemperature(Integer.parseInt(temperature.getText()));
                } catch (RuntimeException e) {
                    alertMessage.setText(e.getMessage());
                    blink.play();
                    setTemperature(Integer.parseInt(oldVal));
                }
        });
        tempDown.setOnAction(ae -> {
            try {
                temperature.setText("" + decrementTemperature());
            } catch (RuntimeException e) {
                alertMessage.setText(e.getMessage());
                blink.play();
            }
        });
        tempControllerPane.getChildren().addAll(tempDown, temperature,tempUp);

        HBox humControllerPane = new HBox();

        Label humL = new Label("HUMIDITY:");
        Button humUp = new Button(">");
        TextField humidity = new TextField(""+ getHumidity());
        humidity.setFont(new Font(21));

        Button humDown = new Button("<");

        buttonsList.addAll(Arrays.asList(tempUp, tempDown, humUp, humDown));
        buttonsList.stream().forEach(b -> {
            b.setPrefHeight(50);
            b.setPrefWidth(50);
        });

        indicatorsList.addAll(Arrays.asList(temperature, humidity));
        indicatorsList.stream().forEach(i ->{
            i.setMaxWidth(50);
            HBox.setMargin(i, new Insets(5, 10, 0, 10));
        });


        humUp.setOnAction(ae ->{
            try {
                humidity.setText("" + incrementHumidity());
            } catch (RuntimeException e) {
                alertMessage.setText(e.getMessage());
                blink.play();
            }
        });


        humidity.textProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal.matches("\\d{0,3}"))
                humidity.setText(oldVal);
            else
            try {
                setHumidity(Integer.parseInt(humidity.getText()));
            } catch (RuntimeException e) {
                alertMessage.setText(e.getMessage());
                blink.play();
            }
        });
        humDown.setOnAction(ae -> {
            try {
                humidity.setText("" + decrementHumidity());
            } catch (RuntimeException e) {
                alertMessage.setText(e.getMessage());
                blink.play();
            }
        });
        humControllerPane.getChildren().addAll(humDown, humidity,humUp);

        controllersPane.getChildren().addAll(tempL,tempControllerPane,humL, humControllerPane);
        root.getChildren().addAll(controllersPane, alertMessage);
        primaryStage.setTitle("Climate control system");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private Timeline createBlinker(Node node) {
        Timeline blink = new Timeline(
                new KeyFrame(
                        Duration.seconds(1.5),
                        new KeyValue(
                                node.opacityProperty(),
                                1,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(
                                node.opacityProperty(),
                                0,
                                Interpolator.DISCRETE
                        )
                ),
                new KeyFrame(
                        Duration.seconds(1.5),
                        new KeyValue(
                                node.opacityProperty(),
                                1,
                                Interpolator.DISCRETE
                        )
                )
        );
        blink.setCycleCount(1);

        return blink;
    }

    public static void main(String[] args) {
        Application.launch(ClimateControlSystemApp.class,args);
    }
    public static class ClimateControlSystemApp extends Application {


        //        --module-path ${JavaFXLibPath} --add-modules javafx.controls,javafx.fxml
        @Override
        public void start(Stage stage) throws Exception {
            Controller c = new Controller();
            c.setView();
        }
    }
}
