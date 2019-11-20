package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.Enemy.Enemy;

public class BossEnemy extends Enemy {
    public BossEnemy(Pane layer,int[][] Map){
        super(layer,40, 40, 0,0.5,40,50,Map,"file:src/sample/AssetsKit_1/PNG/5/5_enemies_1_run_00");
    }
}
