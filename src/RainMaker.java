import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
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
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import static java.lang.Math.*;
import static javafx.scene.paint.Color.*;


public class RainMaker extends Application {


    static Pond Pond1;
    static Pond Pond2;
    static Pond Pond3;
    static Cloud Cloud1;
    static Cloud Cloud2;
    static Cloud Cloud3;
    static Cloud Cloud4;
    static Cloud Cloud5;
    static BezierTest BezierTest;

    static HeliPad HeliPad;
    static Helo Helo;
    private static Pane canvas;
    static int GAME_HEIGHT = 600;
    static int GAME_WIDTH = 800;
    static double WIND_SPEED = .5;
    static int WIND_DIRECTION = 270;
    public static Color color = Color.TRANSPARENT;


    @Override
    public void start(final Stage primaryStage) throws FileNotFoundException {

        ArrayList<Cloud> Clouds = new ArrayList<Cloud>();
        ArrayList<Pond> Ponds = new ArrayList<Pond>();
        ArrayList<Line> lines = new ArrayList<Line>();

        int[][] XnYvalues = new int [3][8];
        for(int j = 0; j<8; j++){
            Random random = new Random();
            XnYvalues [0][j] = random.nextInt(5, 2*GAME_WIDTH);
            XnYvalues [1][j] = random.nextInt(0, 5*GAME_HEIGHT / 6);
            XnYvalues [2][j] = random.nextInt(20, 50);
        }



        //creating the Pond
        Pond Pond1 = new Pond(XnYvalues[0][0], XnYvalues[1][0], XnYvalues[2][0] , XnYvalues[2][0], Color.BLUE);
        Pond Pond2 = new Pond(XnYvalues[0][2], XnYvalues[1][2], XnYvalues[2][2] , XnYvalues[2][2], Color.BLUE);
        Pond Pond3 = new Pond(XnYvalues[0][3], XnYvalues[1][3], XnYvalues[2][3] , XnYvalues[2][3], Color.BLUE);
        Cloud Cloud1 = new Cloud(XnYvalues[0][1], XnYvalues[1][1], XnYvalues[2][1], 0, Color.WHITE,Pond1,Pond2,Pond3);
        Cloud Cloud2 = new Cloud(XnYvalues[0][4], XnYvalues[1][4], XnYvalues[2][4], 0, Color.WHITE,Pond1,Pond2,Pond3);
        Cloud Cloud3 = new Cloud(XnYvalues[0][5], XnYvalues[1][5], XnYvalues[2][5], 0, Color.WHITE,Pond1,Pond2,Pond3);
        Cloud Cloud4 = new Cloud(XnYvalues[0][6], XnYvalues[1][6], XnYvalues[2][6], 0, Color.WHITE,Pond1,Pond2,Pond3);
        Cloud Cloud5 = new Cloud(XnYvalues[0][7], XnYvalues[1][7], XnYvalues[2][7], 0, Color.WHITE,Pond1,Pond2,Pond3);

        Line line11 = new Line(Cloud1.getCenterX(), Cloud1.getCenterY(), Pond1.getCenterX(), Pond1.getCenterY());
        Line line12 = new Line(Cloud1.getCenterX(), Cloud1.getCenterY(), Pond2.getCenterX(), Pond2.getCenterY());
        Line line13 = new Line(Cloud1.getCenterX(), Cloud1.getCenterY(), Pond3.getCenterX(), Pond3.getCenterY());

        Line line21 = new Line(Cloud2.getCenterX(), Cloud2.getCenterY(), Pond1.getCenterX(), Pond1.getCenterY());
        Line line22 = new Line(Cloud2.getCenterX(), Cloud2.getCenterY(), Pond2.getCenterX(), Pond2.getCenterY());
        Line line23 = new Line(Cloud2.getCenterX(), Cloud2.getCenterY(), Pond3.getCenterX(), Pond3.getCenterY());

        Line line31 = new Line(Cloud3.getCenterX(), Cloud3.getCenterY(), Pond1.getCenterX(), Pond1.getCenterY());
        Line line32 = new Line(Cloud3.getCenterX(), Cloud3.getCenterY(), Pond2.getCenterX(), Pond2.getCenterY());
        Line line33 = new Line(Cloud3.getCenterX(), Cloud3.getCenterY(), Pond3.getCenterX(), Pond3.getCenterY());

        Line line41 = new Line(Cloud4.getCenterX(), Cloud4.getCenterY(), Pond1.getCenterX(), Pond1.getCenterY());
        Line line42 = new Line(Cloud4.getCenterX(), Cloud4.getCenterY(), Pond2.getCenterX(), Pond2.getCenterY());
        Line line43 = new Line(Cloud4.getCenterX(), Cloud4.getCenterY(), Pond3.getCenterX(), Pond3.getCenterY());

        Line line51 = new Line(Cloud5.getCenterX(), Cloud5.getCenterY(), Pond1.getCenterX(), Pond1.getCenterY());
        Line line52 = new Line(Cloud5.getCenterX(), Cloud5.getCenterY(), Pond2.getCenterX(), Pond2.getCenterY());
        Line line53 = new Line(Cloud5.getCenterX(), Cloud5.getCenterY(), Pond3.getCenterX(), Pond3.getCenterY());
        lines.add(line11);
        lines.add(line12);
        lines.add(line13);

        lines.add(line21);
        lines.add(line22);
        lines.add(line23);

        lines.add(line31);
        lines.add(line32);
        lines.add(line33);

        lines.add(line41);
        lines.add(line42);
        lines.add(line43);

        lines.add(line51);
        lines.add(line52);
        lines.add(line53);

        for (int i = 0; i < lines.size(); i++){
            lines.get(i).setStroke(color);

        }

        Clouds.add(Cloud1);
        Clouds.add(Cloud2);
        Clouds.add(Cloud3);
        Clouds.add(Cloud4);
        Clouds.add(Cloud5);

        Ponds.add(Pond1);
        Ponds.add(Pond2);
        Ponds.add(Pond3);



        HeliPad HeliPad = new HeliPad(GAME_WIDTH / 2, GAME_HEIGHT * 5 / 6);
        Helo Helo = new Helo(GAME_WIDTH / 2, GAME_HEIGHT * 5 / 6);
        Helo.state_char = 'O';
        //BezierTest BezierTest = new BezierTest(100,100);


        Game Game = new Game(Ponds, Clouds, lines, HeliPad, Helo);


        Alert Winning = new Alert(Alert.AlertType.CONFIRMATION);
        Winning.setTitle("Victory");
        Winning.setContentText("You have Won! Play Again?");
        Winning.setHeaderText("Confirmation");
        ButtonType Yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        Winning.getButtonTypes().set(0,Yes);
        Winning.getButtonTypes().set(1,no);
        Winning.setOnCloseRequest(e->{
            ButtonType result = Winning.getResult();
            if (result != null && result == Yes) {
                System.out.println("Play Again!");

                Helo.restart();
                for(int i = 0; i<Clouds.size();i++){
                    Clouds.get(i).restart(0);
                }
                for(int i = 0; i<Ponds.size();i++){
                    Ponds.get(i).restart(1);
                }

                Winning.close();
            } else {
                System.out.println("CLosing");
                Winning.close();
                primaryStage.close();
                System.exit(0);
            }
        });

        Alert Losing = new Alert(Alert.AlertType.CONFIRMATION);
        Losing.setTitle("Failure!");
        Losing.setContentText("You have Lost! Play Again?");
        Losing.setHeaderText("Confirmation");
        ButtonType Si = new ButtonType("Yes");
        ButtonType No = new ButtonType("No");
        Losing.getButtonTypes().set(0,Si);
        Losing.getButtonTypes().set(1,No);
        Losing.setOnCloseRequest(e->{
            ButtonType result = Losing.getResult();
            if (result != null && result == Si) {
                System.out.println("Play Again!");

                Helo.restart();
                for(int i = 0; i<Clouds.size();i++){
                    Clouds.get(i).restart(0);
                }
                for(int i = 0; i<Ponds.size();i++){
                    Ponds.get(i).restart(1);
                }

                Losing.close();
            } else {
                System.out.println("CLosing");
                Losing.close();
                primaryStage.close();
                System.exit(0);
            }
        });

        canvas = new Pane();
        canvas.setStyle("-fx-background-image: url('/resources/tile.jpg'); -fx-background-position: center center; -fx-background-repeat: repeat;");
        final Scene scene = new Scene(canvas, GAME_WIDTH, GAME_HEIGHT);



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
                    for(int i = 0; i<Clouds.size();i++) {
                        if (abs(Clouds.get(i).getCenterX() - Helo.getLayoutX()) < 100 &&
                                abs(Clouds.get(i).getCenterY() - Helo.getLayoutY()) < 100 &&
                        Helo.getState() == 'R') {
                            Clouds.get(i).Increase(1);
                        }
                    }
                    //Helo.clip();
                    break;
                //case R: loop.start();
                case R:
                    Helo.changeStateO();
                    Helo.restart();
                    for(int i = 0; i<Clouds.size();i++){
                        Clouds.get(i).restart(0);
                    }
                    for(int i = 0; i<Ponds.size();i++){
                        Ponds.get(i).restart(1);
                    }
                    break;
                case I:

                    if(Helo.getState() == 'O') {
                        Helo.bladeSpeed=.1;
                        Helo.changeStateA();
                        Helo.setState('A');
                    }if(Helo.getState() == 'P') {
                        Helo.changeStateA();
                        Helo.setState('A');
                    }else{
                        Helo.changeStateP();
                        Helo.setState('P');
                }


                    break;
                case D:
                    if(line11.getStroke() == TRANSPARENT){
                        for(int i = 0; i<lines.size();i++){
                            lines.get(i).setStroke(WHITE);
                        }
                    }else{
                        for(int i = 0; i<lines.size();i++){
                            lines.get(i).setStroke(TRANSPARENT);
                        }

                    }
                    break;
            }
        });

        primaryStage.setTitle("RainMaker");
        primaryStage.setScene(scene);
        primaryStage.show();

        canvas.getChildren().addAll(Pond1,Pond2,Pond3, Cloud1,
                Cloud2,Cloud3,Cloud4,Cloud5,HeliPad, Helo,
                line11,line12,line13,
                line21,line22,line23,
                line31,line32,line33,
                line41,line42,line43,
                line51,line52,line53);


        AnimationTimer loop = new AnimationTimer() {


            @Override
            public void handle(long now) {
                for(int i = 0; i<Clouds.size();i++){
                    for (int j = 0; j<3; j++){
                        lines.get(j+(i*3)).setStartX(Clouds.get(i).getCenterX());
                        lines.get(j+(i*3)).setStartY(Clouds.get(i).getCenterY());

                    }
                }
                for(int i = 0; i<Clouds.size();i++){
                Clouds.get(i).updateLocation();
                }

                Helo.updateLocation();
                Helo.lessGas();
                Game.run();
                if (Pond1.getReclimationTotal() >= 80&&
                        Pond2.getReclimationTotal() >= 80&&
                        Pond3.getReclimationTotal() >= 80&&
                Helo.getState() == 'O') {
                    Winning.show();

                }
                if(Helo.getGas()<=0){
                    Losing.show();
                }

            }
        };

        loop.start();
    }




    //////////////////end of start

    public static void main(final String[] args) {

        launch(args);
    }
}

class Game extends Pane {
        ArrayList<Pond> Ponds;
        ArrayList<Cloud> Clouds;
        ArrayList<Line> Lines;

        private BezierTest BezierTest;
        private HeliPad HeliPad;
        private Helo Helo;
        int i = 0;
        double rotationSpeed = 0;



    public Game(ArrayList<Pond> ponds, ArrayList<Cloud> clouds,ArrayList<Line> line,
                HeliPad HeliPad, Helo Helo) {
        this.Ponds = ponds;
        this.Clouds = clouds;
        this.Lines = line;
        this.HeliPad = HeliPad;
        this.Helo = Helo;
    }

    public void run() {
//        if(Helo.bladeSpeed == 2 && Helo.state_char == 'A'){
//            Helo.changeStateR();
//            Helo.setState('R');
//        }

        if(Helo.state_char == 'A'  &&Helo.getBladeSpeed() >= 5){
            Helo.changeStateR();
            Helo.state_char = 'R';
            System.out.println("--------------------------------");

        }
        if(Helo.state_char == 'P'  && Helo.getBladeSpeed() < 0.1){
            System.out.println(Helo.getBladeSpeed());
            Helo.changeStateO();
            Helo.state_char = 'O';
            System.out.println("****************************");
        }


        i++;




//        if (!Helo.getIgnition()) {
//            if (i % 10 == 0 && rotationSpeed > 0) {
//                rotationSpeed = rotationSpeed - .5;
//            }
//
//            Helo.spin();
//
//        }
        if (i % 60 == 0) {

            for(int j = 0; j < Clouds.size();j++){
                Clouds.get(j).Increase(-1);
            }


            //System.out.println("This is called " + i / 60 + " time");
            for(int k = 0; k<Clouds.size();k++){
                for (int j = 0; j<3; j++){
                    Lines.get(j+(k*3)).setStartX(Clouds.get(k).getCenterX());
                    Lines.get(j+(k*3)).setStartY(Clouds.get(k).getCenterY());

                }
            }
            for(int k = 0; k <Clouds.size();k++){
                for(int j = 0; j <Ponds.size();j++){
                    if(Clouds.get(k).ReclimationTotal>29 && Clouds.get(k).proximity(Ponds.get(j))){
                        Ponds.get(j).Growth();
                    }
                }
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

    public Pond(int Xcord, int Ycord, double size, double percent,
                Color Color) {
        super(Xcord, Ycord, size, percent, Color);

    }

    public int getXCoord() {
        return Xcord;
    }

    public int getYCoord() {
        return Ycord;
    }


}

class Cloud extends Cloud_Pond {


    double deltaY;
    double deltaX;
    double variation;
    public Cloud(int Xcord, int Ycord, double size, double percent
            , Color Color, Pond Pond1,Pond Pond2,Pond Pond3) {
        super(Xcord, Ycord, size, percent, Color);
        Random random = new Random();
        ReclimationTotal = percent;
        variation = random.nextDouble(.5,1.5);


    }
    public boolean proximity(Pond pond){
        double diameter = pond.getHeight();
        double acceptance = 4*diameter;
        double Xdif = super.getCenterX()-pond.getCenterX();
        double Ydif = super.getCenterY()-pond.getCenterY();
        Xdif = Xdif*Xdif;
        Ydif = Ydif*Ydif;
        double distance = sqrt(Xdif + Ydif);

        if (distance < acceptance){
            return true;
        }else{

        return false;
    }
    }





    public void updateLocation() {
        deltaX = (RainMaker.WIND_SPEED * sin(toRadians(-RainMaker.WIND_DIRECTION)))+variation;
        deltaY = (RainMaker.WIND_SPEED * cos(toRadians(-RainMaker.WIND_DIRECTION)));
        super.setLayoutY(super.getLayoutY() + deltaY);
        super.setLayoutX(super.getLayoutX() + deltaX);

        if(super.getLayoutX()>=RainMaker.GAME_WIDTH+50){
            Random random = new Random();

            super.setLayoutX(-1*random.nextInt(50,150));
            super.setLayoutY(random.nextInt(0,(int)(RainMaker.GAME_HEIGHT *.9)));
            variation = random.nextDouble(.5,1.5);
            ReclimationTotal = 0;
        }
//        if(super.getLayoutX()<-50|| super.getLayoutX()> RainMaker.Width+50||super.getLayoutY()<-50|| super.getLayoutY()> RainMaker.GAME_HEIGHT+50){
//            System.out.println(super.getLayoutX()+super.getLayoutY());
//            super.setLayoutX(super.getLayoutX()+ -deltaX* (RainMaker.Width+150));
//            super.setLayoutY(super.getLayoutY()+ -deltaY* (RainMaker.GAME_HEIGHT+50));
//            if(Math.abs(super.getLayoutX()+super.getLayoutY())>1200){
//                super.setLayoutX(RainMaker.Width/2 + -deltaX*RainMaker.Width/2);
//                super.setLayoutY(RainMaker.GAME_HEIGHT/2+ -deltaY*RainMaker.GAME_HEIGHT/2);
//            }
//        }
    }
}

class Cloud_Pond extends GameObject {
    double ReclimationTotal;

    private final Circle WaterBody;
    private final Label total;
    private double size;


    public Cloud_Pond(int Xcord, int Ycord, double size, Double percent
            , Color Color) {
        super((Xcord/ 2) + 20,( Ycord/ 2) + 20);

        this.size = size;
        WaterBody = new Circle(Xcord, Ycord, size);
        total = new Label(Double.toString(percent) + "%");
        ReclimationTotal = percent;

        WaterBody.setFill(Color);
        getChildren().addAll(WaterBody, total);


    }

    public void Growth() {
        ReclimationTotal = ReclimationTotal + 1;
        if (ReclimationTotal > 99) {
            ReclimationTotal = 100;
        }
        total.setText(Double.toString(ReclimationTotal) + "%");
        WaterBody.setRadius((ReclimationTotal / 2) + 20);
    }

    public void Increase(int x) {

        ReclimationTotal = ReclimationTotal + x;
        if (ReclimationTotal > 99) {
            ReclimationTotal = 100;
        }
        if (ReclimationTotal < 1) {
            ReclimationTotal = 0;
        }
        total.setText(Double.toString(ReclimationTotal) + "%");
        Color c = Color.rgb((int) (255-(ReclimationTotal)),
                (int) (255-(ReclimationTotal)),
                (int) (255-(ReclimationTotal)));

        WaterBody.setFill(c);
    }

    public int getCenterX() {

        double getCenterX = getLayoutX() + size;
        return (int) getCenterX;
    }

    public int getCenterY() {
        double getCenterY = getLayoutY() + size;
        return (int) getCenterY;
    }

    public double getReclimationTotal() {
        return ReclimationTotal;
    }

    public void restart(int x){
        Random random = new Random();
        super.setLayoutX(random.nextInt(5,  RainMaker.GAME_WIDTH-30));
        super.setLayoutY(random.nextInt(0, 2* RainMaker.GAME_HEIGHT / 3));
        ReclimationTotal = x;
        if(x !=0){
            ReclimationTotal = random.nextInt(20, 50);

        }
    }
}


abstract class Heli extends GameObject {
    private final Circle body;
    private final Line Nose1;
    private final Line Nose2;
    private double velocity = 0;
    double deltaX = 0;
    double deltaY = 0;
    int heliFacing = 0;
    double bladeRotation = 1;
    double bladeSpeed = 1 ;
    private AudioClip clip;
    double gas = 25000;
    private final Label gasLabel;
    double depletionRate;
    //boolean ignition = false;
    public HeliState state;
    char state_char = 'O';
    int originX;
    int originY;

    public Heli(int Xcord, int Ycord) {

        super(Xcord, Ycord);
        originX = Xcord;
        originY = Ycord;

        body = new Circle(Xcord, Ycord, RainMaker.GAME_HEIGHT / 48);
        Nose1 = new Line(0, 0, 0, RainMaker.GAME_HEIGHT / 12);
        Nose2 = new Line(0, 0, 0, RainMaker.GAME_HEIGHT / 12);

        body.setFill(Color.YELLOWGREEN);
        Nose1.setStroke(Color.CRIMSON);
        Nose2.setStroke(Color.CRIMSON);

        gasLabel = new Label("F:" + Double.toString(gas));
        getChildren().addAll(body, Nose1, Nose2, gasLabel);
        //getChildren().addAll(gasLabel);
        super.setRotate(heliFacing);
        Nose2.setRotate(90);
    }
    public void restart(){

        super.setLayoutX(originX);
        super.setLayoutY(originY);
        heliFacing = 0;

        state_char = 'O';
        velocity = 0;
        deltaX = 0;
        deltaY = 0;
        bladeRotation=0;
        bladeSpeed = 0;
        Nose1.setRotate(0);
        Nose2.setRotate(90);
        gas = 25000;
    }


    abstract static class HeliState {
        //abstract void startOrStopEngine();
        void spin() {
        }

        void lessGas() {
        }

        void steer(int val) {
        }

        void goForward() {
        }

        void goBackward() {
        }

        public boolean checkLand(int x, int y) {
            return false;
        }

        public abstract void updateLocalTransforms();
    }


    class Off extends HeliState {


        public void updateLocalTransforms() {
            //deltaX = velocity * sin(toRadians(-heliFacing));
            //deltaY = velocity * cos(toRadians(-heliFacing));

        }

    }

    class Ready extends HeliState {

        void steer(int val) {
            heliFacing += val * 15;
            System.out.println(heliFacing);
        }

        public void spin() {
            bladeRotation = bladeRotation + 5;
            Nose1.setRotate(bladeRotation);
            Nose2.setRotate(90 + bladeRotation);
        }

        public void lessGas() {
            depletionRate = 1;
            gas = gas - depletionRate;
            gasLabel.setText("F:" + Integer.toString((int) gas));
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
            deltaX = velocity * sin(toRadians(-heliFacing));
            deltaY = velocity * cos(toRadians(-heliFacing));
            Nose1.setRotate(bladeRotation);
            Nose2.setRotate(90 + bladeRotation);

        }

    }

    class Starting extends HeliState {

        public void spin() {


            if (bladeSpeed < 5) {
                bladeSpeed = bladeSpeed + .01;
                bladeRotation = bladeRotation + bladeSpeed;
            }
            Nose1.setRotate(bladeRotation);
            Nose2.setRotate(90 + bladeRotation);
        }

        public void lessGas() {
            depletionRate = .5;
            gas = gas - depletionRate;
            gasLabel.setText("F:" + Integer.toString((int) gas));
        }

        public void updateLocalTransforms() {
            //deltaX = velocity * sin(toRadians(-heliFacing));
            //deltaY = velocity * cos(toRadians(-heliFacing));
        }

    }

    class Stopping extends HeliState {

        public void spin() {

            if (bladeSpeed > .1) {
                bladeSpeed = bladeSpeed - .01;
                bladeRotation = bladeRotation + bladeSpeed;
            }
            Nose1.setRotate(bladeRotation);
            Nose2.setRotate(90 + bladeRotation);
        }

        public void updateLocalTransforms() {
            //deltaX = velocity * sin(toRadians(-heliFacing));
            //deltaY = velocity * cos(toRadians(-heliFacing));
        }

    }


    public AudioClip clip() {
        if (clip == null) {

            AudioClip src = new AudioClip(new File("C:/Users/Lukas/IdeaProjects/CSC133/HW1/FinalProj_RainMaker/src/resources/lets-take-off.mp3").toURI().toString());
            src.play();
            System.out.println("src: " + src);
            clip = src;

        }
        return clip;
    }
}


class Helo extends Heli {
    HeliState state = new Off();

    public Helo(int Xcord, int Ycord) {

        super(Xcord, Ycord);

    }

    public void changeStateR() {
        state = new Ready(); System.out.println("Ready");
    }

    public void changeStateA() {
        state = new Starting(); System.out.println("Starting");
    }

    public void changeStateP() {
        state = new Stopping(); System.out.println("Stopping");
    }

    public void changeStateO() {
        state = new Off();        System.out.println("Off");

    }

    public void setState(char c) {
        state_char = c;
    }

    public char getState() {
        return state_char;
    }
    public double getBladeSpeed(){return bladeSpeed;}
    public double getGas(){return gas;}

    public void steer(int val) {
        state.steer(val);
    }

    public void lessGas() {
        state.lessGas();
    }

    public void spin() {
        state.spin();
    }

    public void goBackward() {
        state.goBackward();
    }

    public void goForward() {
        state.goForward();
    }

    //public boolean getIgnition() {
    //    return ignition;
    //}

    public void updateLocation() {
        state.updateLocalTransforms();
        state.spin();
        super.setRotate(heliFacing);
        super.setLayoutY(super.getLayoutY() + deltaY);
        super.setLayoutX(super.getLayoutX() + deltaX);
        super.setStyle("-fx-background-image: url('/resources/helocopterBeauty.png'); -fx-background-size: " + super.getWidth() + " " + super.getHeight() + ";");
    }

}


class HeliPad extends GameObject {
    private final Circle bubble;
    private final Rectangle Square;
    private final int size = RainMaker.GAME_HEIGHT / 12;
    private final QuadCurve quadCurve;


    HeliPad(int x, int y) {
        super(x, y);
        bubble = new Circle(x, y, size);
        Square = new Rectangle(x, y, 2.5 * size, 2.5 * size);
        quadCurve = new QuadCurve(x+10,y,x+20,y+50,x+10,y);

        bubble.setFill(Color.BLUEVIOLET);
        Square.setFill(Color.TURQUOISE);


        getChildren().addAll(Square, bubble,quadCurve);
        super.setLayoutX(x - (Square.getWidth() / 2));
        super.setLayoutY(y - size);

    }
}
class BezierTest extends GameObject{
    private final Circle circle;
    private final QuadCurve quadCurve;

    BezierTest(int x,int y){
        super(0, 0);
        quadCurve = new QuadCurve(10.0f,500.0f,50.0f,150.0f,10.0f,100.0f);
        //super.setLayoutX(100);
        //super.setLayoutY(100);
        circle = new Circle();
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(50.0f);
        circle.setFill(WHITE);
        System.out.print("yo");
        getChildren().addAll(circle,quadCurve);

    }
    }
