package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Enemy.*;
import sample.GameTile.Path.Path;
import sample.GameTile.Tower.Tower;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStage {
    public Pane layer = new Pane();
    Random rnd = new Random();
    public List<Enemy> enemies = new ArrayList<>();
    public List<Tower> towers = new ArrayList<>();
    public List<Bullet> bullets = new ArrayList<>();
    public Menu menuTower = new Menu(layer);
    public Tower newTower = new Tower();
    public Enemy enemy = new Enemy();
    public int[][] Map = Setting.map1;
    public double blood = 50;
    public boolean stillAlive = true;

    Text pointText = new Text();
    Text castText = new Text();
    Text highPointText = new Text();
    int point = 0;
    int cast = 500;
    private int highPoint ;

    public static final int TIME = 50;
    int timer = 0;
    boolean isOk ;
    int countClick = 0;
    boolean isDragging ;
    boolean isPressed;
    int towerType;
    boolean isReleasing ;
    Point2D mousePos;


    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Group secondRoot = new Group();
        primaryStage.setTitle("Defense Tower");
        primaryStage.setScene(new Scene(root, 900, 660));
        Path path = new Path(layer);
        path.setPath(Map);
        root.getChildren().addAll(path.draw());
        root.getChildren().add(layer);

        MenuFinish menuFinish = new MenuFinish(secondRoot,point);
        Scene gameOver = new Scene(secondRoot,900,660);

        Button other_exit = new Button("EXIT");
        other_exit.setPrefWidth(100);
        other_exit.setPrefHeight(50);
        other_exit.setLayoutX(75 + 660);
        other_exit.setLayoutY(550);
        layer.getChildren().add(other_exit);
        other_exit.setOnAction(event -> {
            System.exit(0);
        });
        other_exit.setOnAction(event -> {
            try{
                DataOutputStream dos1 = new DataOutputStream(new FileOutputStream("C:/Users/admin/Downloads/ver5/ProjectDT/src/saveHighScore/myScore.bin"));
                dos1.writeInt(highPoint);
                dos1.close();
            } catch (IOException ex) { ex.printStackTrace(); }
            System.exit(0);
        });



        primaryStage.show();
        AnimationTimer Timer = (new AnimationTimer() {


            @Override
            public void handle(long l) {

                if(!enemies.isEmpty())
                    enemies.forEach(e->{
                        e.move();
                        e.update();
                    });

                menuTower.towerBtnArrayList.forEach(btn ->{
                    btn.btn.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            isPressed = true;
                            mousePos = new Point2D(event.getSceneX(),event.getSceneY());
                            towerType = btn.index;
                            menuTower.addNewTower(towerType,mousePos,new Pane());

                            layer.getChildren().add(menuTower.animationLayer);


                        }
                    });

                    btn.btn.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            isDragging = true;
                            mousePos = new Point2D(event.getSceneX(),event.getSceneY() );
                        }
                    });

                    btn.btn.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            isDragging = false;
                            isReleasing = true;
                        }
                    });

                });

                if(isPressed) {

                    menuTower.animationLayer.relocate(mousePos.getX(),mousePos.getY());
                    if(isDragging) {
                        menuTower.animationLayer.relocate(mousePos.getX(),mousePos.getY());
                    }

                    if(isReleasing) {

                        layer.getChildren().remove(menuTower.animationLayer);
                        menuTower.animationLayer.relocate(menuTower.towerBtnArrayList.get(towerType).pos.getX(),menuTower.towerBtnArrayList.get(towerType).pos.getY());
                        newTower =menuTower.addNewTower(towerType,mousePos,layer);
                        if (cast < newTower.price){
                            isOk = false;
                        }
                        else{
                            isOk = true;
                            cast -= newTower.price;
                        }

                        towers.forEach(t->{
                            if(newTower.hasCollision(t)){
                                isOk = false;
                            }
                        });

                        path.map.forEach(c->{
                            if(newTower.pos.getX() + newTower.width >= c.pos.getX() && newTower.pos.getX() <= c.pos.getX() + c.width && newTower.pos.getY() + newTower.height >= c.pos.getY() && newTower.pos.getY() <= c.pos.getY() + c.height) {
                                isOk = false;
                            }
                        });

                        if(newTower.pos.getX() <0  || newTower.pos.getX() > 660 - newTower.width ||newTower.pos.getY() < 0 || newTower.pos.getY() > 660 - newTower.height ) {
                            isOk = false;
                        }
                        if(isOk)
                            towers.add(newTower);
                        else layer.getChildren().remove(newTower.animationLayer);
                        isReleasing = false;

                    }
                }

                enemies.forEach(e -> {
                    if(e.isAtDestination()) {
                        blood-=10;
                        System.out.println(blood);
                        menuTower.healthBar.setScale(blood,50);
                        menuTower.healthBar.setBloodBarWidth();
                        menuTower.healthBar.update(720,50);
                        if (blood == 0){
                            primaryStage.setScene(gameOver);
                        }
                    }

                    e.move();
                    e.update();
                });

                bullets.forEach(b->{
                    b.move();
                    b.update();
                });


                if(timer % TIME == 0 ){
                    createEnemy(Map);
                }

                timer = (timer + 1) % TIME;

                if(blood <= 0) {
                    stillAlive = false;
                }

                towers.forEach(t-> {
                    if(enemies.isEmpty()){
                    }
                    double minDistance =t.shootingRange;
                    for(Enemy e: enemies){
                        if(e.getCenter().distance(t.getCenter()) < minDistance){
                            enemy = e;
                            minDistance = e.getCenter().distance(t.getCenter());
                        }
                    };
                    try{
                        t.addBulletAndShoot(bullets,enemy);}
                    catch (Exception e) {}
                    t.update();
                });
                earnMoney();
                updateScore();
                updateHighScore();
                updateCast();
                handleCollision();

                try {
                    remove();
                } catch (Exception e){

                }
            }

        });
        Timer.start();

        menuTower.pauseBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                countClick +=event.getClickCount() ;
                if(countClick % 2 ==1){
                    Timer.stop();
                    menuTower.pauseBtn.setText("CONTINUE");
                }
                else {
                    Timer.start();
                    menuTower.pauseBtn.setText("PAUSE");
                }
            }
        });


        createPoint();
        saveHighPoint();
        createCast();
    }



    public void handleCollision() {
        enemies.forEach(e->{
            if(e.blood <= 0)
                e.bloodBar.layer.getChildren().removeAll(e.bloodBar.imageViewBloodBar,e.bloodBar.imageViewMaxBloodBar);
            bullets.forEach(b->{
                if(e.hasCollision(b)) {
                    b.removable = true;
                    if(e.blood > 0)
                        e.getDamagedBy(b);
                }
            });
        });
    }

    public void remove() {
        enemies.forEach(e->{
            if(e.removable) {
                enemies.remove(e);
                e.removeImageView();

            }
        });

        bullets.forEach(b-> {
            if(b.removable) {
                bullets.remove(b);
                b.removeImageView();
            }
        });
    }
    public void earnMoney() {

        enemies.forEach(e -> {
            if (e.blood == 0) {
                cast += 1;
                if (e.armor == 50){ point += 1; } // small
                else if (e.armor == 60){ point += 2;} // normal
                else if (e.armor == 70){point +=3;} // tank
                else point += 4; // boss
                if (highPoint < point){highPoint = point;}
            }
        });
    }
    public void updateScore() {
        pointText.setText( String.valueOf(point));
    }
    public void updateHighScore(){
        highPointText.setText(String.valueOf(highPoint));
    }
    public void updateCast() {
        castText.setText( String.valueOf(cast));
    }
    Text scoreText = new Texts().ScoreText(layer);
    Text highScoreText = new Texts().HighScoreText(layer);

    public void createPoint(){
        pointText.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(pointText);
        pointText.setText( String.valueOf(point));
        pointText.relocate(745, 174);
        pointText.setFill(Color.RED);
    }
    public void createCast(){
        castText.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(castText);
        castText.setText( String.valueOf(cast));
        castText.relocate(765, 145);
        castText.setFill(Color.BLUEVIOLET);
    }
    public void saveHighPoint(){
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("C:/Users/admin/Downloads/ver5/ProjectDT/src/saveHighScore/myScore.bin"));
            highPoint = dis.readInt();
            dis.close();
            System.out.println(highPoint);
        } catch (IOException ex) { ex.printStackTrace(); }
        highPointText.setText( String.valueOf(highPoint));
        highPointText.setStyle("-fx-font-size: 20px;");
        layer.getChildren().add(highPointText);
        highPointText.relocate(795,84);
        highPointText.setFill(Color.YELLOW);
    }


    private void createEnemy( int[][]  Map)  {
        int type = rnd.nextInt(3) + 1;
        if (type == 1) {
            NormalEnemy normalEnemy = new NormalEnemy(layer,Map);
            enemies.add(normalEnemy);
        } else if (type == 2) {
            TankEnemy tankerEnemy = new TankEnemy(layer,Map);
            enemies.add(tankerEnemy);
        }
        else if (type == 3){
            SmallEnemy smallEnemy = new SmallEnemy(layer,Map);
            enemies.add(smallEnemy);
        }
        else{
            BossEnemy bossEnemy = new BossEnemy(layer,Map);
            enemies.add(bossEnemy);
        }

    }



}
