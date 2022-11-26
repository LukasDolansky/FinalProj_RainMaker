import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import static java.lang.Math.*;


public class RainMaker extends Application{


    static Pond Pond;
    static Cloud Cloud;
    static HeliPad HeliPad;
    static Helo Helo;
    private static Pane canvas;
    static int Height = 600;
    static int Width = 400;
    public static boolean ignition = false;





            @Override
    public void start(final Stage primaryStage) throws FileNotFoundException {

        Random random = new Random();
        int x1 = random.nextInt(5,Width-50);
        int y1 = random.nextInt(0,Height/3);
        int x2 = random.nextInt(5,Width-50);
        int y2 = random.nextInt(0,Height/3);
        double PondNum = random.nextInt(20,50);
        double CloudNum = random.nextInt(20,50);



        //creating the Pond
        Pond Pond = new Pond(x1, y1,(PondNum / 2) +20,PondNum, Color.BLUE);
        Cloud Cloud = new Cloud(x2, y2,(CloudNum / 2) +20,0, Color.WHITE);
        HeliPad HeliPad = new HeliPad((Width/2),Height*5/6);
        Helo Helo = new Helo(Width/2, Height*5/6);





        InputStream stream = new FileInputStream("C:\\Users\\Lukas\\Pictures\\Stats week 4.PNG");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

                imageView.setX(10);
                imageView.setY(10);
                imageView.setFitWidth(575);
                imageView.setPreserveRatio(true);

                Group root = new Group(imageView);


        Game Game = new Game(Pond, Cloud, HeliPad,Helo);



                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Win");
                alert.setContentText("You have Won! Play Again?");
                alert.setHeaderText("Confirmation");
                ButtonType buttonPlayAgain = new ButtonType("Yes");
                ButtonType NotPlayAgain = new ButtonType("No");

                alert.getButtonTypes().set(0, buttonPlayAgain);
                alert.getButtonTypes().set(1, NotPlayAgain);
                Stage a = (Stage) alert.getDialogPane().getScene().getWindow();
                a.toFront();
                alert.setOnCloseRequest(e -> {
                    ButtonType result = alert.getResult();
                    if (result != null && result == buttonPlayAgain) {
                        System.out.println("Play Again!");

                    } else {
                        a.close();
                        alert.close();
                        //stage.close();
                        System.out.println("Quit!");
                    }
                });

        canvas = new Pane();
        canvas.setStyle("-fx-background-color: black");
        final Scene scene = new Scene(canvas,Width, Height);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    Helo.steer(-1);
                    break;
                case RIGHT:
                    Helo.steer(1);
                    break;
                case UP:
                    Helo.goForward();
                    break;
                case DOWN:
                    Helo.goBackward();
                    break;
                case SPACE:
                    Cloud.Increase(1);
                    Helo.clip();
                    break;
                case I:
                    Helo.ignition = !Helo.ignition;
                    break;
            }
        });

        primaryStage.setTitle("RainMaker");
        primaryStage.setScene(scene);
        primaryStage.show();

        canvas.getChildren().addAll(Pond,Cloud,HeliPad,Helo);


        AnimationTimer loop = new AnimationTimer() {


            @Override
            public void handle(long now) {
                Helo.updateLocation();
                Helo.lessGas();
                Game.run();
                if (Pond.getReclimationTotal() >= 100){
                    a.show();

                }

            }
        };

        loop.start();


    }
    public static void main(final String[] args) {

        launch(args);
    }
}

class Game extends Pane{

    private Pond Pond;
    private Cloud Cloud;
    private HeliPad HeliPad;
    private Helo Helo;


    public Game(Pond Pond, Cloud Cloud,HeliPad HeliPad,Helo Helo) {
        this.Pond = Pond;
        this.Cloud = Cloud;
        this.HeliPad = HeliPad;
        this.Helo = Helo;


    }
    int  i = 0;
    double rotationSpeed = 0;
    public void run() {


        i++;
        if (Helo.getIgnition()) {
            if (i % 10 == 0 && rotationSpeed < 10) {
                rotationSpeed = rotationSpeed + .5;
            } else {
                Helo.setMobility();
            }

            Helo.spin();
            Helo.lessGas();

        }
        if (!Helo.getIgnition()) {
            if (i % 10 == 0 && rotationSpeed > 0) {
                rotationSpeed = rotationSpeed - .5;
            }

            Helo.spin();

        }
        if (i % 60 == 0) {

            Cloud.Increase(-1);
            //System.out.println("This is called " + i / 60 + " time");
            if (Cloud.ReclimationTotal > 29) {
                Pond.Growth();
            }
        }


    }
    }

    abstract class GameObject extends StackPane {

        public GameObject(int Xcord, int Ycord) {
            super.setLayoutX(Xcord);
            super.setLayoutY(Ycord);
        }


    }
    class Pond extends Cloud_Pond {

        int Xcord;
        int Ycord;
        public Pond(int Xcord, int Ycord, double size,double percent,
                    Color Color) {
            super(Xcord, Ycord,size,percent,Color);

        }
        public int getXCoord() {
            return Xcord;
        }
        public int getYCoord() {
            return Ycord;
        }

    }
    class Cloud extends Cloud_Pond {


        public Cloud(int Xcord, int Ycord, double size,double percent
                ,Color Color) {
                super(Xcord, Ycord,size,percent,Color);
                ReclimationTotal = percent;

            }
            public void Seeded(int r) {
                System.out.println("r");

            }


        }
    class Cloud_Pond extends GameObject{
        double ReclimationTotal;

        private final Circle WaterBody;
        private final Label total;


            public Cloud_Pond(int Xcord, int Ycord, double size,Double percent
                    ,Color Color) {
                super(Xcord, Ycord);
                WaterBody = new Circle (Xcord, Ycord, size);
                total = new Label(Double.toString(percent)+ "%");
                ReclimationTotal = percent;

                WaterBody.setFill(Color);
                getChildren().addAll(WaterBody,total);


            }

            public void Growth(){
                ReclimationTotal = ReclimationTotal+1;
                if (ReclimationTotal>99){
                    ReclimationTotal = 100;
                }
                total.setText(Double.toString(ReclimationTotal)+"%");
                WaterBody.setRadius((ReclimationTotal/2)+20);
            }public void Increase(int x){
            ReclimationTotal = ReclimationTotal + x;
            if (ReclimationTotal>99){
                ReclimationTotal = 100;
            }
            if (ReclimationTotal<1){
                ReclimationTotal = 0;
            }
            total.setText(Double.toString(ReclimationTotal)+"%");
            int ColorVal = 256- ((int)(ReclimationTotal));
            Color c = Color.rgb(ColorVal,ColorVal,ColorVal);
            WaterBody.setFill(c);
        }
        public double getReclimationTotal(){
                return ReclimationTotal;
        }

        }



    abstract class Heli extends GameObject {
    private final Circle body;
    private final Line Nose1;
    private final Line Nose2;
    private boolean heliMobile;
    double velocity = 0;
    double deltaX = 0;
    double deltaY = 0;
    int rotate = 10;
    double rotated = 1;
    private AudioClip clip;
    double gas = 25000;
    private final Label gasLabel;
    double DepletionRate;
    boolean ignition = false;
    public HeliState state;
    public Heli(int Xcord, int Ycord) {

            super(Xcord, Ycord);
            body = new Circle(Xcord, Ycord, RainMaker.Height / 48);
            Nose1 = new Line(0, 0, 0, RainMaker.Height / 12);
            Nose2 = new Line(0, 0, 0, RainMaker.Height / 12);

            body.setFill(Color.YELLOWGREEN);
            Nose1.setStroke(Color.CRIMSON);
            Nose2.setStroke(Color.CRIMSON);

            gasLabel = new Label("F:" + Double.toString(gas));
            getChildren().addAll(body, Nose1, Nose2, gasLabel);
            super.setRotate(rotate);
            Nose2.setRotate(90);
        }








        abstract static class HeliState {
            //abstract void startOrStopEngine();
            void spin(){}
            void lessGas(){}
            void steer(int val){}
            void goForward(){}
            void goBackward(){}
            public boolean checkLand(int x, int y){
                return false;
            }
            public abstract void updateLocalTransforms();
        }



        class Off extends HeliState {


            public void updateLocalTransforms() {
                deltaX = velocity * sin(toRadians(-rotate));
                deltaY = velocity * cos(toRadians(-rotate));

            }

        }
        class Ready extends HeliState {

            void steer(int val) {
                rotate += val * 15;
                System.out.println(rotate);
            }
            public void spin(){
                rotated = rotated + 5;
                Nose1.setRotate(rotated);
                Nose2.setRotate(90+rotated);
            }
            public void lessGas(){
                DepletionRate= 1;
                gas = gas - DepletionRate;
                gasLabel.setText("F:"+Integer.toString((int)gas));
            }
            public void goForward() {
                velocity += -.1;
                if (velocity < -10) {
                    velocity = -10;
                }
                System.out.println(velocity);
                //HeliManipulator.alert();
            }
            public void goBackward() {
                velocity += .1;
                if (velocity > 2) {
                    velocity = 2;
                }
                System.out.println(velocity);
            }
            public void updateLocalTransforms() {
                deltaX = velocity * sin(toRadians(-rotate));
                deltaY = velocity * cos(toRadians(-rotate));
                Nose1.setRotate(rotated);
                Nose2.setRotate(90+rotated);
            }

        }
        class Starting extends HeliState {
            public void spins(){
                rotated = rotated + 5;
                Nose1.setRotate(rotated);
                Nose2.setRotate(90+rotated);
            }
            public void spin(){

                if (rotated < 5000){
                    rotated = rotated + rotated*.02;
                } else {
                    System.out.println("Entry");
                    state = new Ready();
                    System.out.println("Success?");

                }
                Nose1.setRotate(rotated);
                Nose2.setRotate(90+rotated);
            }
            public void lessGas(){
                DepletionRate= .5;
                gas = gas - DepletionRate;
                gasLabel.setText("F:"+Integer.toString((int)gas));
            }
            public void updateLocalTransforms() {
                deltaX = velocity * sin(toRadians(-rotate));
                deltaY = velocity * cos(toRadians(-rotate));
            }

        }
        class Stopping extends HeliState {
            void spinBlades(){
                if (rotated < 0){
                    rotated -= .25;
                } else {
                    state = new Off();
                }
            }
            public void updateLocalTransforms() {}

        }





        public void setMobility() {
            heliMobile = !heliMobile;
        }
        public AudioClip clip() {
            if(clip == null){

                AudioClip src = new AudioClip(new File("C:/Users/Lukas/IdeaProjects/CSC133/HW1/FinalProj_RainMaker/src/resources/lets-take-off.mp3").toURI().toString());
                src.play();
                System.out.println("src: "+src);
                clip = src;

            }
            return clip;
        }
        }







        class Helo extends Heli{
            HeliState state = new Ready();

            public Helo(int Xcord, int Ycord) {

        super(Xcord, Ycord);

        }

            public void steer(int val){
        state.steer(val);
            }
            public void lessGas(){
                state.lessGas();
            }
            public void spin(){
                state.spin();
            }
            public void goBackward() {
                state.goBackward();
            }
            public void goForward() {
                state.goForward();
            }
            public boolean getIgnition() {
                return ignition;
            }
            public void updateLocation() {

                state.updateLocalTransforms();
                state.spin();
                super.setRotate(rotate);
                super.setLayoutY(super.getLayoutY() + deltaY);
                super.setLayoutX(super.getLayoutX() + deltaX);
            }

        }















    class HeliPad extends GameObject{
            private final Circle bubble;
            private final Rectangle Square;
            private final int size = RainMaker.Height/12;


            HeliPad(int x, int y)
            {
                super(x,y);
                bubble = new Circle (x, y, size);
                Square = new Rectangle (x, y, 2.5*size, 2.5*size);
                bubble.setFill(Color.BLUEVIOLET);
                Square.setFill(Color.TURQUOISE);
                getChildren().addAll(Square,bubble);
                super.setLayoutX(x-(Square.getWidth()/2));
                super.setLayoutY(y-size);
            }
        }



