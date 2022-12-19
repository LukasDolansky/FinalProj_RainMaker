import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import static java.lang.Math.*;
import static javafx.scene.paint.Color.*;


public class RainMaker extends Application {


    private static final int GAME_HEIGHT = 600;
    private static final int GAME_WIDTH = 800;
    private static final Color color = Color.TRANSPARENT;

    public static int getGameHeight(){return GAME_HEIGHT;}
    public static int getGameWidth(){return GAME_WIDTH;}
    public static double getWindSpeed(){
        double WIND_SPEED = .5;
        return WIND_SPEED;}
    public static int getWindDirection(){
        int WIND_DIRECTION = 270;
        return WIND_DIRECTION;}


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



        Blimp Blimp = new Blimp(100,100);
        Blimp Blimpo = new Blimp(100,500);

        Pond Pond1 = new Pond(XnYvalues[0][0], XnYvalues[1][0], XnYvalues[2][0] , XnYvalues[2][0], CORNFLOWERBLUE);
        Pond Pond2 = new Pond(XnYvalues[0][2], XnYvalues[1][2], XnYvalues[2][2] , XnYvalues[2][2], CORNFLOWERBLUE);
        Pond Pond3 = new Pond(XnYvalues[0][3], XnYvalues[1][3], XnYvalues[2][3] , XnYvalues[2][3], CORNFLOWERBLUE);
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

        for (Line item : lines) {
            item.setStroke(color);

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
        Helo.setState('O');
        //BezierTest BezierTest = new BezierTest(100,100);


        Game Game = new Game(Ponds, Clouds, lines, HeliPad, Helo, Blimp, Blimpo);


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
                for (Cloud cloud : Clouds) {
                    cloud.restart(0);
                }
                for (Pond pond : Ponds) {
                    pond.restart(1);
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
                for (Cloud cloud : Clouds) {
                    cloud.restart(0);
                }
                for (Pond pond : Ponds) {
                    pond.restart(1);
                }

                Losing.close();
            } else {
                System.out.println("CLosing");
                Losing.close();
                primaryStage.close();
                System.exit(0);
            }
        });

        Pane canvas = new Pane();
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
                    for (Cloud value : Clouds) {
                        if (abs(value.getCenterX() - Helo.getLayoutX()) < 100 &&
                                abs(value.getCenterY() - Helo.getLayoutY()) < 100 &&
                                Helo.getState() == 'R') {
                            value.Increase(1);
                        }
                    }
                    //Helo.clip();
                    break;
                //case R: loop.start();
                case R:
                    Helo.changeStateO();
                    Helo.restart();
                    Blimp.restart(2);
                    Blimpo.restart(2);
                    for (Cloud cloud : Clouds) {
                        cloud.restart(0);
                    }
                    for (Pond pond : Ponds) {
                        pond.restart(1);
                    }
                    break;
                case I:

                    if(Helo.getState() == 'O') {
                        Helo.setBladeSpeed(.1);
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
                        for (Line line : lines) {
                            line.setStroke(WHITE);
                        }
                    }else{
                        for (Line line : lines) {
                            line.setStroke(TRANSPARENT);
                        }

                    }
                    break;

            }
        });

        primaryStage.setTitle("RainMaker");
        primaryStage.setScene(scene);
        primaryStage.show();

        canvas.getChildren().addAll(Pond1,Pond2,Pond3,HeliPad, Cloud1,
                Cloud2,Cloud3,Cloud4,Cloud5, Blimp, Blimpo, Helo,
                line11,line12,line13,
                line21,line22,line23,
                line31,line32,line33,
                line41,line42,line43,
                line51,line52,line53);


        AnimationTimer loop = new AnimationTimer() {


            @Override
            public void handle(long now) {
                //Updates the lines to the center of the clouds. Not needed for ponds since they dont move.
                for(int i = 0; i<Clouds.size();i++){
                    for (int j = 0; j<3; j++){
                        lines.get(j+(i*3)).setStartX(Clouds.get(i).getCenterX());
                        lines.get(j+(i*3)).setStartY(Clouds.get(i).getCenterY());

                    }
                }
                for (Cloud cloud : Clouds) {
                    cloud.updateLocation();
                }

                Blimp.updateLocation();
                Blimpo.updateLocation();

                Helo.updateLocation();
                Helo.lessGas();
                if (Blimp.refuel(Helo)|| Blimpo.refuel(Helo)){

                    Helo.moreGas();
                }
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
        private final ArrayList<Pond> Ponds;
        private final ArrayList<Cloud> Clouds;
        private final ArrayList<Line> Lines;

        private final Blimp Blimp;
        private final Blimp Blimpo;
        private final HeliPad HeliPad;
        private final Helo Helo;
        private int i = 0;



    public Game(ArrayList<Pond> ponds, ArrayList<Cloud> clouds,ArrayList<Line> line,
                HeliPad HeliPad, Helo Helo,Blimp Blimp ,Blimp Blimpo) {
        this.Ponds = ponds;
        this.Clouds = clouds;
        this.Lines = line;
        this.HeliPad = HeliPad;
        this.Helo = Helo;
        this.Blimp = Blimp;
        this.Blimpo = Blimpo;
    }

    public void run() {
//        if(Helo.bladeSpeed == 2 && Helo.stateChar == 'A'){
//            Helo.changeStateR();
//            Helo.setState('R');
//        }

        if(Helo.getState() == 'A'  &&Helo.getBladeSpeed() >= 5){
            Helo.changeStateR();
            Helo.setState('R');
            System.out.println("--------------------------------");

        }
        if(Helo.getState() == 'P'  && Helo.getBladeSpeed() < 0.1){
            System.out.println(Helo.getBladeSpeed());
            Helo.changeStateO();
            Helo.setState('O');
            System.out.println("****************************");
        }


        i++;



        if (i % 60 == 0) {

            for (Cloud value : Clouds) {
                value.Increase(-1);
            }


            //System.out.println("This is called " + i / 60 + " time");
            for(int k = 0; k<Clouds.size();k++){
                for (int j = 0; j<3; j++){
                    Lines.get(j+(k*3)).setStartX(Clouds.get(k).getCenterX());
                    Lines.get(j+(k*3)).setStartY(Clouds.get(k).getCenterY());

                }
            }
            for (Cloud cloud : Clouds) {
                for (Pond pond : Ponds) {
                    if (cloud.getReclimationTotal() > 29 && cloud.proximity(pond)) {
                        pond.Growth();
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

class Pond extends TransientGameObject {

    private int Xcord;
    private int Ycord;

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

class Cloud extends TransientGameObject {



    public Cloud(int Xcord, int Ycord, double size, double percent
            , Color Color, Pond Pond1,Pond Pond2,Pond Pond3) {
        super(Xcord, Ycord, size, percent, Color);
        setReclimationTotal(percent);


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

class TransientGameObject extends GameObject {
    private double ReclimationTotal;
    private double deltaY;
    private double deltaX;
    private double variation;
    private final Circle WaterBody;
    private int fuel;
    private final Circle edge;

    private final Label total;
    private final double size;


    public TransientGameObject(int Xcord, int Ycord, double size, Double percent
            , Color Color) {
        super((Xcord/ 2) + 20,( Ycord/ 2) + 20);
        Random random = new Random();

        this.size = size;
        WaterBody = new Circle(Xcord, Ycord, size);
        edge = new Circle(Xcord, Ycord, size+2);
        total = new Label(Double.toString(percent) + "%");
        ReclimationTotal = percent;

        WaterBody.setFill(Color);
        getChildren().addAll(edge,WaterBody, total);

        variation = random.nextDouble(.5,1.5);



    }
    public void setReclimationTotal(double x){
        ReclimationTotal = x;
    }
    public double getReclimationTotal(){
        return ReclimationTotal;
    }
    public void Growth() {
        ReclimationTotal = ReclimationTotal + 1;
        if (ReclimationTotal > 99) {
            ReclimationTotal = 100;
        }
        total.setText(Double.toString(ReclimationTotal) + "%");
        WaterBody.setRadius((ReclimationTotal / 2) + 20);
        edge.setRadius((ReclimationTotal / 2) + 22);

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
    public int getFuel(){return fuel;}
    public void setFuel(int x){fuel = x;}
    public void restart(int x){
        Random random = new Random();
        super.setLayoutX(random.nextInt(5,  RainMaker.getGameWidth()-30));
        super.setLayoutY(random.nextInt(0, 2* RainMaker.getGameHeight() / 3));
        ReclimationTotal = x;
        if(x == 1){
            ReclimationTotal = random.nextInt(20, 50);

        }
    }
    public void updateLocation() {
        deltaX = (RainMaker.getWindSpeed() * sin(toRadians(-RainMaker.getWindDirection()))) + variation;
        deltaY = (RainMaker.getWindSpeed() * cos(toRadians(-RainMaker.getWindDirection())));
        super.setLayoutY(super.getLayoutY() + deltaY);
        super.setLayoutX(super.getLayoutX() + deltaX);

        if (super.getLayoutX() >= RainMaker.getGameWidth() + 50) {
            Random random = new Random();
            ReclimationTotal = 0;
            super.setLayoutX(-1 * random.nextInt(50, 450));
            super.setLayoutY(random.nextInt(0, (int) (RainMaker.getGameHeight() * .9)));
            variation = random.nextDouble(.5, 1.5);
            fuel = 1000* random.nextInt(5,10);

        }
    }
}


abstract class Heli extends GameObject {
    private final Circle body;
    private final Line Nose1;
    private final Line Nose2;
    private double velocity = 0;
    private double deltaX = 0;
    private double deltaY = 0;
    private int heliFacing = 0;
    private double bladeRotation = 1;
    private double bladeSpeed = 1 ;
    private AudioClip clip;
    private double gas = 25000;
    private final Label gasLabel;
    private double depletionRate;
    public HeliState state;
    private char stateChar = 'O';
    private final int originX;
    private final int originY;

    public Heli(int Xcord, int Ycord) {

        super(Xcord, Ycord);
        originX = Xcord;
        originY = Ycord;

        body = new Circle(Xcord, Ycord, RainMaker.getGameHeight() / 48);
        Nose1 = new Line(0, 0, 0, RainMaker.getGameHeight() / 12);
        Nose2 = new Line(0, 0, 0, RainMaker.getGameHeight() / 12);

        body.setFill(Color.YELLOWGREEN);
        Nose1.setStroke(Color.CRIMSON);
        Nose2.setStroke(Color.CRIMSON);

        gasLabel = new Label("F:" + Double.toString(gas));
        getChildren().addAll(body, Nose1, Nose2, gasLabel);
        //getChildren().addAll(gasLabel);
        super.setRotate(heliFacing);
        Nose2.setRotate(90);
    }

    public void setState(char c) {
        stateChar = c;
    }
    public char getState() {
        return stateChar;
    }
    public double getBladeSpeed(){return bladeSpeed;}
    public void setBladeSpeed(double x){bladeSpeed = x;}
    public int getHeliFacing(){
        return heliFacing;
    }
    public double getDeltaX(){
        return deltaX;
    }
    public double getDeltaY(){
        return deltaY;
    }
    public double getGas(){return gas;}

    public void restart(){

        super.setLayoutX(originX);
        super.setLayoutY(originY);
        heliFacing = 0;

        stateChar = 'O';
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
        void moreGas() {
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
        public void moreGas() {
            depletionRate = 6;
            gas = gas + depletionRate;
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


    public void steer(int val) {
        state.steer(val);
    }

    public void lessGas() {
        state.lessGas();
    }
    public void moreGas() {
        state.moreGas();
    }

    public void goBackward() {
        state.goBackward();
    }

    public void goForward() {
        state.goForward();
    }

    public void updateLocation() {
        state.updateLocalTransforms();
        state.spin();
        super.setRotate(getHeliFacing());
        super.setLayoutY(super.getLayoutY() + getDeltaY());
        super.setLayoutX(super.getLayoutX() + getDeltaX());
        super.setStyle("-fx-background-image: url('/resources/helocopterBeauty.png'); -fx-background-size: " + super.getWidth() + " " + super.getHeight() + ";");
    }

}


class HeliPad extends GameObject {
    private final Circle bubble;
    private final Rectangle Square;
    private final Circle bubbleEdge;
    private final Rectangle SquareEdge;
    private final int size = RainMaker.getGameHeight() / 12;


    HeliPad(int x, int y) {
        super(x, y);
        bubble = new Circle(x, y, size);
        Square = new Rectangle(x, y, 2.5 * size, 2.5 * size);
        bubbleEdge = new Circle(x, y, size+2);
        SquareEdge = new Rectangle(x, y, (2.5 * size)+2, (2.5 * size)+2);

        bubble.setFill(Color.BLUEVIOLET);
        Square.setFill(Color.TURQUOISE);


        getChildren().addAll(SquareEdge,Square,bubbleEdge, bubble);
        super.setLayoutX(x - (Square.getWidth() / 2));
        super.setLayoutY(y - size);

    }
}
class Blimp extends TransientGameObject{
    private final Ellipse edge;
    private Label fuelCounter;

    private final Ellipse ellipse;
    Blimp(int x,int y){

        super(x, y,4,0.0,BLACK);

        ellipse = new Ellipse();
        edge = new Ellipse();
        fuelCounter = new Label();

        ellipse.setCenterX(100.0f);
        ellipse.setCenterY(100.0f);
        edge.setRadiusX(82);
        edge.setRadiusY(32);
        ellipse.setRadiusX(80);
        ellipse.setRadiusY(30);
        ellipse.setFill(GRAY);

        Random random = new Random();
        setFuel(1000* random.nextInt(5,10));
        fuelCounter.setText("F:"+Integer.toString(getFuel()));
        //setTotal(Integer.toString(fuel));
        getChildren().addAll(edge,ellipse,fuelCounter);


    }
    public boolean refuel(Helo Helo){

        Helo.getLayoutY();

        super.getCenterY();
        if(abs(Helo.getLayoutX()-super.getCenterX())<15&&
                abs(Helo.getLayoutY()-super.getCenterY())<15&&
        Helo.getState() == 'R'){
            setFuel(getFuel() - 9);
            return true;
        }
        fuelCounter.setText("F:"+Integer.toString(getFuel()));

        return false;
    }
    public void restart(int x){
        Random random = new Random();
        super.setLayoutX(random.nextInt(5,  RainMaker.getGameWidth()-30));
        super.setLayoutY(random.nextInt(0, 2* RainMaker.getGameHeight() / 3));
        if(x == 2){
            setFuel(1000* random.nextInt(5,10));
            fuelCounter.setText("F:"+(getFuel()));

        }
    }


    }
