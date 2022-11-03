
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
        Pond Pond = new Pond(x1, y1,(PondNum / 2) +20,Double.toString(PondNum)+"%", Color.BLUE);
        Cloud Cloud = new Cloud(x2, y2,(CloudNum / 2) +20,Double.toString(CloudNum)+"%", Color.GRAY);
        HeliPad HeliPad = new HeliPad((Width/2),Height*5/6);
        Heli Heli = new Heli(Height/2, 500);


        PongApp PongApp = new PongApp(Pond, Cloud,HeliPad,Heli);





        canvas = new Pane();
        canvas.setStyle("-fx-background-color: black");
        //holder.getChildren().add(canvas);
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
                    Heli.seed();
                    break;
            }
        });

        primaryStage.setTitle("RainMaker");
        primaryStage.setScene(scene);
        primaryStage.show();


        //Water.getChildren().addAll(Pond,Wet);
        //Air.getChildren().addAll(Cloud,Dry);
        canvas.getChildren().addAll(Heli, Pond,Cloud,HeliPad);

        //canvas.setOnKeyPressed(e -> keyPressed(e));


        AnimationTimer loop = new AnimationTimer() {


            @Override
            public void handle(long now) {


                PongApp.run();
                Heli.updateLocation();


            }
        };

        loop.start();


    }

    public static void main(final String[] args) {

        launch(args);
    }

}

class PongApp {

    private Pond Pond;
    private Cloud Cloud;
    private HeliPad HeliPad;

    private Heli Heli;


    public PongApp(Pond Pond, Cloud Cloud,HeliPad HeliPad,Heli Heli) {
        this.Pond = Pond;
        this.Cloud = Cloud;
        this.HeliPad = HeliPad;
        this.Heli = Heli;

    }


    public void run() {


        //System.out.println("yoyo");
/*
        //boolean atRightBorder = Heli.getLayoutX() >= (RainMaker.Width - Heli.getWidth());
        //boolean atLeftBorder = Heli.getLayoutX() <= (0 + Heli.getWidth());
        //boolean atBottomBorder = Heli.getLayoutY() >= (RainMaker.Height - Heli.getHeight());
        //boolean atTopBorder = Heli.getLayoutY() <= (0 + Heli.getHeight());
        //boolean Contact = !Shape.intersect(Pond, Heli).;



        //if (atRightBorder || atLeftBorder) {
            //Sound.play(500);
            Heli.changeX();
        }

        if (atTopBorder) {
            //Sound.play(500);
            Heli.changeY();

        }
        //if (Contact) {
        //Sound.play(500);
        //    Heli.Hit();
        //}
        if (atBottomBorder) {
            Heli.changeY();
        }
    }

*/

    }

}
    abstract class GameObject extends StackPane {

        public GameObject(int Xcord, int Ycord) {
            super.setLayoutX(Xcord);
            super.setLayoutY(Ycord);
        }


    }

    class Pond extends Cloud_Pond {

        int ReclimationTotal = 28;
        int Xcord;
        int Ycord;


        public Pond(int Xcord, int Ycord, double size,String  percent,Color Color) {
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

            int ReclimationTotal = 28;

            public Cloud(int Xcord, int Ycord, double size,String  percent,Color Color) {
                super(Xcord, Ycord,size,percent,Color);

            }
            public void Seeded(int r) {
                System.out.println("r");

                super.setLayoutX(r);
            }
        public void Decay(int r) {
            System.out.println("r");
            super.setLayoutX(r);
        }

        }

        //class HeliPad extends
        class Cloud_Pond extends GameObject{
            private final Circle WaterBody;
            private final Label total;


            public Cloud_Pond(int Xcord, int Ycord, double size,String percent,Color Color) {
                super(Xcord, Ycord);
                WaterBody = new Circle (Xcord, Ycord, size);
                total = new Label(percent);

                WaterBody.setFill(Color);
                getChildren().addAll(WaterBody,total);


            }

        }
    class Heli extends GameObject {
        private final Circle body;
        private final Line Nose;

        double velocity = 0;
        double deltaX = 0;
        double deltaY = 0;
        int rotate = 0;



        public Heli(int Xcord, int Ycord) {
            super(Xcord, Ycord);
            body = new Circle (Xcord, Ycord, RainMaker.Height/48);
            Nose = new Line (0,0,0, RainMaker.Height/12);
            body.setFill(Color.YELLOWGREEN);
            Nose.setStroke(Color.YELLOWGREEN);
            getChildren().addAll(body,Nose);
            super.setRotate(rotate);
        }

        public void updateLocation() {
            deltaX = velocity*sin(toRadians(-rotate));
            deltaY = velocity*cos(toRadians(-rotate));
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
            if (velocity<-10){
                velocity = -10;
            }
            System.out.println(velocity);

        }
        public void goBackward() {
            velocity += .1;
            if (velocity>2){
                velocity = 2;
            }
            System.out.println(velocity);
        }


        public void seed() { deltaY += -.1;
            System.out.println("S");
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



