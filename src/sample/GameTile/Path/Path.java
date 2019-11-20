package sample.GameTile.Path;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.Calculate;
import sample.GameEntity;
import sample.GameTile.GameTile;
import sample.Setting;

import java.util.ArrayList;
import java.util.List;

public class Path extends GameTile {
    public Path(Pane layer) {
        super(layer,new Image("file:src/sample/AssetsKit_1/shapes/0.png"),0,0,0,660,660);
        this.movable = false;
        this.canRotate = false;
    }

    public  List<CellRoad> map =  new ArrayList<>();
    public CellRoad spawner = new CellRoad(5*110, 0);
    public CellRoad dest = new CellRoad(5*110, 4*110);
    public boolean checkedMap[][] = new boolean[6][6];

    public Path() {}

    public  List setPath(int[][] Map) {

            int cellX = (int)(spawner.getPos().getX() / 110);
            int cellY = (int)(spawner.getPos().getY()/110);

                while (!(cellX == dest.getPos().getX()/110 && cellY == dest.getPos().getY()/110)) {
                    checkedMap[cellY][cellX] = true;
                    map.add(new CellRoad(cellX *110 , cellY *110));
                    if (cellX +1 <6 && !checkedMap[cellY][cellX + 1] && Map[cellY][cellX + 1] == 1) {
                        cellX++;
                    } else if (cellX - 1 >=0 && !checkedMap[cellY][cellX - 1] && Map[cellY][cellX - 1] == 1) {
                        cellX--;
                    } else if (cellY - 1 >= 0 && !checkedMap[cellY - 1][cellX] && Map[cellY - 1][cellX] == 1) {
                        cellY--;
                    } else if (cellY + 1 < 6 && !checkedMap[cellY + 1][cellX] && Map[cellY + 1][cellX] == 1) {
                        cellY++;
                    }

                }

                map.add(dest);
                Calculate cal = new Calculate();

        map.get(map.size()-1).radDirection = Math.PI / 2;
                for(int i = 0; i < map.size() - 1;i++) {
                   double angle = cal.angle(map.get(i).getCenter(),map.get(i+1).getCenter());
                   map.get(i).radDirection = angle;
                }

                return map;

    }

    public Pane draw() {
        Pane pane = new Pane();
        ImageView backgroundImageView = new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/53.png"));
        backgroundImageView.setFitWidth(660);
        backgroundImageView.setFitHeight(660);
        pane.getChildren().add(backgroundImageView);


        map.forEach(c->{
            ImageView cellRoadImageView = new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/road.png"));
            cellRoadImageView.setFitWidth(110);
            cellRoadImageView.setFitHeight(110);
            cellRoadImageView.setX(c.getPos().getX());
            cellRoadImageView.setY(c.getPos().getY());
            this.layer.getChildren().add(cellRoadImageView );
        });
        return pane;
    }
    public static void main(String[] a) {
//        EnemyDirection e = EnemyDirection.AHEAD;
//        e.setEnemyDirection(EnemyDirection.LEFT);
//        System.out.println(e.getEnemyDirection());

    }
}
