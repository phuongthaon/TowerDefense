package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuStart {

    ImageView menu = new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/2.png"));
    public MenuStart(Group root) {
        menu.setFitWidth(900);
        menu.setFitHeight(660);

        Button button_exit = new Button("EXIT");

        button_exit.relocate(350, 450);
        button_exit.setPrefWidth(200);
        button_exit.setPrefHeight(100);
        button_exit.setOnAction(event -> {
            System.exit(0);
        });
        root.getChildren().addAll(menu,button_exit);
        Text welcomeText = new Texts().getWelcome(root);
    }
}
