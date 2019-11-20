package sample;


import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Texts {
    public Text ScoreText(Pane layer) {
        Text pointLetter = new Text(670, 190, "SCORE : ");
        pointLetter.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(pointLetter);
        return pointLetter;
    }

    public Text HighScoreText(Pane layer) {
        Text highPointLetter = new Text(670, 100, "HIGH SCORE : ");
        highPointLetter.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(highPointLetter);
        return highPointLetter;
    }

    public Text getWelcome(Group root) {
        Text welcome = new Text(240,100,"      WELCOME \n TOWER DEFENSE ");
        welcome.setFont(Font.font(null, FontWeight.MEDIUM, 50));
        welcome.setFill(Color.RED);
        root.getChildren().add(welcome);
        return welcome;
    }
    public Text NormalPrice(Pane layer){
        Text normalPrice = new Text(710, 350, " $100 ");
        normalPrice.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(normalPrice);
        return normalPrice;
    }
    public Text TankPrice(Pane layer){
        Text tankPrice = new Text(810, 450, " $400 ");
        tankPrice.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(tankPrice);
        return tankPrice;
    }
    public Text GunPrice(Pane layer){
        Text gunPrice = new Text(810, 355, " $300 ");
        gunPrice.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(gunPrice);
        return gunPrice;
    }
    public Text SniperPrice(Pane layer){
        Text sniperPrice = new Text(710, 450, " $200 ");
        sniperPrice.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(sniperPrice);
        return sniperPrice;
    }


}
