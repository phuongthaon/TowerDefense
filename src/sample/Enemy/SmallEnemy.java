package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.Enemy.Enemy;

public class SmallEnemy extends Enemy {
    public SmallEnemy(Pane layer,int Map[][]){
        super(layer,40, 40, 0,1,20,30,Map,"file:src/sample/AssetsKit_1/PNG/2/2_enemies_1_run_00");
    }
}
