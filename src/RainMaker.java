
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


    static Heli Heli;
    static Pond Pond;
    static Cloud Cloud;
    static HeliPad HeliPad;
    private static Pane canvas;


    static int Height = 600;
    static int Width = 400;




    @Override
    public void start(final Stage primaryStage) {
// setting the scene, random numbers, rotate


        Random random = new Random();
        int rNum = random.nextInt(30,60);
        int rNum2 = random.nextInt(30,60);
        int rNum3 = random.nextInt(30,60);
        int rNum4 = random.nextInt(30,60);


        Heli Heli = new Heli(rNum, 100);

        //creating the Pond
        Pond Pond = new Pond(4*rNum, 2*rNum);
        Cloud Cloud = new Cloud(4*rNum2, 4*rNum2);
        HeliPad HeliPad = new HeliPad((Width/2),Height*5/6);


        PongApp PongApp = new PongApp(Heli, Pond, Cloud,HeliPad);





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

        String k = String.valueOf(rNum2);
        String m = String.valueOf(rNum3);


        Label Wet=new Label(k+"%");
        Label Dry=new Label(m+"%");
        Wet.setLayoutX(4*rNum-5);
        Wet.setLayoutY(2*rNum-5);
        Dry.setLayoutX(4*rNum2-5);
        Dry.setLayoutY(4*rNum2-5);

        //Water.getChildren().addAll(Pond,Wet);
        //Air.getChildren().addAll(Cloud,Dry);
        canvas.getChildren().addAll(Heli, Pond,Cloud,Wet,Dry,HeliPad);

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
    private Heli Heli;
    private Pond Pond;
    private Cloud Cloud;
    private HeliPad HeliPad;


    public PongApp(Heli Heli, Pond Pond, Cloud Cloud,HeliPad HeliPad) {
        this.Heli = Heli;
        this.Pond = Pond;
        this.Cloud = Cloud;
        this.HeliPad = HeliPad;
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

    class Pond extends GameObject {

        int ReclimationTotal = 28;
        int Xcord;
        int Ycord;


        public Pond(int Xcord, int Ycord) {
            super(Xcord, Ycord);
        }

        public int getXCoord() {
            return Xcord;
        }
        public int getYCoord() {
            return Ycord;
        }

    }
        class Cloud extends GameObject {

            int ReclimationTotal = 28;

            public Cloud(int Xcord, int Ycord) {
                super(Xcord, Ycord);
            }

            public void changeX(int r) {
                System.out.println("r");
                super.setLayoutX(r);
            }

        }

        //class HeliPad extends

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
            System.out.println(velocity);
        }
        public void goBackward() {
            velocity += .1;
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



