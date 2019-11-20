package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Game extends Application{
    GameStage gameStage = new GameStage();
    public void start(Stage primaryStage ) throws Exception {
        Group root = new Group();
        MenuStart menuStart = new MenuStart(root);
        Scene startScene = new Scene(root,900,660);

        Button button_start = new Button("START");
        button_start.setPrefWidth(200);
        button_start.setPrefHeight(100);
        button_start.relocate(350, 300);
        root.getChildren().addAll(button_start);
        primaryStage.setScene(startScene);

        gameStage = new GameStage();

        button_start.setOnAction(event -> {
            try {
                gameStage.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        if (!gameStage.stillAlive)
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
