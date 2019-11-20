package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.Enemy.Enemy;

public class TankEnemy extends Enemy {
    public TankEnemy(Pane layer,int Map[][]){
        super(layer,40, 40, 0,0.75,20,30,Map,"file:src/sample/AssetsKit_1/PNG/4/4_enemies_1_run_00");
    }
}
