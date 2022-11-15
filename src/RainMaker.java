
        import java.util.Random;
        import javafx.animation.AnimationTimer;
        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.StackPane;
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
    static Heli Heli;

    private static Pane canvas;


    static int Height = 600;
    static int Width = 400;
    public static boolean ignition = false;





            @Override
    public void start(final Stage primaryStage) {
// setting the scene, random numbers, rotate


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
        Heli Heli = new Heli(Width/2, Height*5/6);


        Game Game = new Game(Pond, Cloud, HeliPad, Heli);





        canvas = new Pane();
        canvas.setStyle("-fx-background-color: black");
        final Scene scene = new Scene(canvas, Width, Height);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    Heli.turnRight();
                    break;
                case D:
                    Heli.turnLeft();
                    break;
                case W:
                    Heli.goForward();
                    break;
                case S:
                    Heli.goBackward();
                    break;
                case SPACE:
                    Cloud.Increase(1);
                    break;
                case I:
                    ignition = !ignition;
                    break;
            }
        });

        primaryStage.setTitle("RainMaker");
        primaryStage.setScene(scene);
        primaryStage.show();

        canvas.getChildren().addAll( Pond,Cloud,HeliPad,Heli);

        AnimationTimer loop = new AnimationTimer() {


            @Override
            public void handle(long now) {

                Heli.updateLocation();
                Game.run();



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
    private Heli Heli;


    public Game(Pond Pond, Cloud Cloud,HeliPad HeliPad,Heli Heli) {
        this.Pond = Pond;
        this.Cloud = Cloud;
        this.HeliPad = HeliPad;
        this.Heli = Heli;


    }

    int  i = 0;
    double rotationSpeed = 0;
    public void run() {



        i++;
        if(RainMaker.ignition) {
            if(i%10 == 0 && rotationSpeed <10) {
                rotationSpeed = rotationSpeed + .5;
            }else{

            }

            Heli.spin(rotationSpeed);
            Heli.lessGas();

        }
        if(!RainMaker.ignition) {
            if(i%10 == 0 && rotationSpeed >0) {
                rotationSpeed = rotationSpeed - .5;
            }

            Heli.spin(rotationSpeed);
            //Heli.lessGas();

        }
        if(i%60 == 0) {
        //rotationSpeed--;

            Cloud.Increase(-1);
            System.out.println("This is called " + i/60 + " time");
            if(Cloud.ReclimationTotal>29){
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

        }
    class Heli extends GameObject {
        private final Circle body;
        private final Line Nose1;
        private final Line Nose2;
        private boolean heliMobile;
        double velocity = 0;
        double deltaX = 0;
        double deltaY = 0;
        int rotate = 0;
        double rotated = 0;


        double gas = 25000;
        private final Label gasLabel;

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


        public void updateLocation() {
            deltaX = velocity * sin(toRadians(-rotate));
            deltaY = velocity * cos(toRadians(-rotate));
            super.setRotate(rotate);
            super.setLayoutY(super.getLayoutY() + deltaY);
            super.setLayoutX(super.getLayoutX() + deltaX);
        }
        public void turnLeft() {
            rotate += 15;
            System.out.println(rotate);
        }
        public void turnRight() {
            rotate += -15;
            System.out.println(rotate);
        }
        public void goForward() {
            velocity += -.1;
            if (velocity < -10) {
                velocity = -10;
            }
            System.out.println(velocity);
        }
        public void goBackward() {
            velocity += .1;
            if (velocity > 2) {
                velocity = 2;
            }
            System.out.println(velocity);
        }
        public void lessGas(){
            gas--;
            gasLabel.setText("F:"+Integer.toString((int)gas));
        }
        public void spin(double up){
            rotated = rotated + up;
            Nose1.setRotate(rotated);
            Nose2.setRotate(90+rotated);
        }
        public void setMobility() {
            heliMobile = !heliMobile;
        }
        public void falls() {
            Random random = new Random();
            int rNum = random.nextInt((RainMaker.Width - 25) + 25);
            super.setLayoutX(rNum);
            super.setLayoutY(RainMaker.Height / 6);
            deltaX = 3;
            deltaY = 3;
        }

        public void Hit() {
            deltaY *= -1;

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



