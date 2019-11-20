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
import javafx.stage.Stage;

import java.awt.*;

public class MenuFinish {
    public MenuFinish(Group root, int point) {
        ImageView gameOverImage = new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/1.png"));
        gameOverImage.setFitWidth(900);
        gameOverImage.setFitHeight(660);
        root.getChildren().add(gameOverImage);

        Text byeText = new Text(400, 550, "YOUR SCORE : ");
        byeText.setStyle("-fx-font-size: 20px;");
        byeText.setFont(Font.font(null, FontWeight.MEDIUM, 60));
        root.getChildren().add(byeText);

        Text gainPoint = new Text();
        gainPoint.setText( String.valueOf(point));
        gainPoint.setStyle("-fx-font-size: 20px;");
        byeText.setFont(Font.font(null, FontWeight.MEDIUM, 60));
        root.getChildren().add(gainPoint);
        gainPoint.relocate(540,535);
        gainPoint.setFill(Color.RED);


    }
}
