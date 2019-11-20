package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.Enemy.Enemy;

public class NormalEnemy extends Enemy {
    public NormalEnemy(Pane layer,int[][] Map){
        super(layer,40, 40, 0,0.75,10,30,Map,"file:src/sample/AssetsKit_1/PNG/1/1_enemies_1_run_00");
    }
}
