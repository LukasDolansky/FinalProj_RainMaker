
        import java.util.Random;

        import javafx.animation.AnimationTimer;
        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.StackPane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Rectangle;
        import javafx.scene.shape.Circle;
        import javafx.stage.Stage;
        import javafx.scene.control.Label;


public class RainMaker extends Application{


    static Heli Helicopter;
    static Pond Pond;
    static Cloud Cloud;
    static HeliPad HeliPad;
    private static Pane canvas;

    static boolean dragging = false;
    private static int total;

    static int Height = 600;
    static int Width = 400;
    //scene.setOnKeyPressed(e -> {
      //      if (e.getCode() == KeyCode.A) {
        //        System.out.println("A key was pressed");
          //  }
        //});



    @Override
    public void start(final Stage primaryStage) {
// setting the scene, random numbers, rotate
        Random random = new Random();
        int rNum = random.nextInt(30,60);
        int rNum2 = random.nextInt(30,60);
        int rNum3 = random.nextInt(30,60);
        int rNum4 = random.nextInt(30,60);


        Heli Helicopter = new Heli(rNum, 100,25,0, Color.BLUE);

        //creating the Pond
        Pond Pond = new Pond(4*rNum, 2*rNum, rNum3,0,  Color.BLUE);
        Cloud Cloud = new Cloud(4*rNum2, 4*rNum2, rNum4,0,  Color.WHITE);
        HeliPad HeliPad = new HeliPad((Width/2),Height*5/6);


        PongApp PongApp = new PongApp(Helicopter, Pond, Cloud,HeliPad);





        canvas = new Pane();
        canvas.setStyle("-fx-background-color: black");
        //holder.getChildren().add(canvas);
        final Scene scene = new Scene(canvas, Width, Height);

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
        canvas.getChildren().addAll(Helicopter, Pond,Cloud,Wet,Dry,HeliPad);

        //canvas.setOnKeyPressed(e -> keyPressed(e));


        AnimationTimer loop = new AnimationTimer() {


            @Override
            public void handle(long now) {


                PongApp.run();
                Helicopter.updateLocation();


            }
        };

        loop.start();


    }

    public static void main(final String[] args) {

        launch(args);
    }

}

class PongApp {
    private Heli Helicopter;
    private Pond Pond;
    private Cloud Cloud;
    private HeliPad HeliPad;


    public PongApp(Heli Helicopter, Pond Pond, Cloud Cloud,HeliPad HeliPad) {
        this.Helicopter = Helicopter;
        this.Pond = Pond;
        this.Cloud = Cloud;
        this.HeliPad = HeliPad;
    }


    public void run() {
/*
        //boolean atRightBorder = Helicopter.getLayoutX() >= (RainMaker.Width - Helicopter.getWidth());
        //boolean atLeftBorder = Helicopter.getLayoutX() <= (0 + Helicopter.getWidth());
        //boolean atBottomBorder = Helicopter.getLayoutY() >= (RainMaker.Height - Helicopter.getHeight());
        //boolean atTopBorder = Helicopter.getLayoutY() <= (0 + Helicopter.getHeight());
        //boolean Contact = !Shape.intersect(Pond, Helicopter).;



        //if (atRightBorder || atLeftBorder) {
            //Sound.play(500);
            Helicopter.changeX();
        }

        if (atTopBorder) {
            //Sound.play(500);
            Helicopter.changeY();

        }
        //if (Contact) {
        //Sound.play(500);
        //    Helicopter.Hit();
        //}
        if (atBottomBorder) {
            Helicopter.changeY();
        }
    }

*/

    }

}
    abstract class PhysicsObject extends Circle {

        public PhysicsObject(int Xcord, int Ycord, int width, int rotate, Color Color) {
            super.setRadius(width);
            super.setLayoutX(Xcord);
            super.setLayoutY(Ycord);
            super.setRotate(rotate);
            super.setFill(Color);
        }


    }

    class Pond extends PhysicsObject {

        int ReclimationTotal = 28;
        int Xcord;
        int Ycord;


        public Pond(int Xcord, int Ycord, int radius, int rotate, Color Color) {
            super(Xcord, Ycord, radius, rotate, Color);
        }

        public int getXCoord() {
            return Xcord;
        }
        public int getYCoord() {
            return Ycord;
        }

    }
        class Cloud extends PhysicsObject {

            int ReclimationTotal = 28;

            public Cloud(int Xcord, int Ycord, int radius, int rotate, Color Color) {
                super(Xcord, Ycord, radius, rotate, Color);
            }

            public void changeX(int r) {
                System.out.println("r");
                super.setLayoutX(r);
            }

        }

        //class HeliPad extends

    class Heli extends PhysicsObject {

        int deltaX = 3;
        int deltaY = 3;
        int rotate;

        public Heli(int Xcord, int Ycord, int width, int rotate, Color Color) {
            super(Xcord, Ycord, width, rotate, Color);
        }

        public void updateLocation() {
            super.setLayoutY(super.getLayoutY() + deltaY);
            super.setLayoutX(super.getLayoutX() + deltaX);
            super.setRotate(super.getRotate() + rotate);
        }

        public void changeX() {
            deltaX *= -1;
        }

        public void changeY() {
            deltaY *= -1;
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
        class HeliPad extends StackPane{
            private final Circle bubble;
            private final Rectangle Square;
            private final int size = RainMaker.Height/12;


            HeliPad(double x, double y)
            {
                bubble = new Circle (x, y, size);
                Square = new Rectangle (x, y, 2.5*size, 2.5*size);
                bubble.setFill(Color.YELLOW);
                Square.setFill(Color.BLUEVIOLET);
                getChildren().addAll(Square,bubble);
                super.setLayoutX(x-(Square.getWidth()/2));
                super.setLayoutY(y-size);
            }
        }



