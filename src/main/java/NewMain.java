
package main.java;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.models.ControlledScene;
import main.java.models.NewInput;
import main.java.models.camera.SimpleCameraBuilder;
import main.java.view.SimpleDisplay;

/**
 * The new main of 3DSoftwareRenderer. It starts an Javafx window.
 * This is a multi-theaded application able to render 3D-models using different 
 * renderers (although all software).
 * 
 * TODO remove musings:
 * 
 * So bottomline there should be a view class where we can draw to.
 * The loop should handle the when everything is drawn and using what settings.
 * So the loop is kind of the main object. The loop could be in this class, but
 * eventually we would like a user to change settings without diving in the code 
 * and that he can make his own scene and define every action (mostly the inputs like mouse clicks and
 * keyboard. (throw in gamepad support as well?)).
 * 
 * But anyway a display should have one access point.
 * Although it is possible to draw each frame on an image and pass it to the display
 * (which passes it to the canvas), it might be very slow and costs some memory in any case.
 * So the loop at least needs a reference to the canvas, which is very awkward.
 * 
 * It is clear that the loop should at least call a clearRect to the gc of the canvas
 * (or not if the user wishes)
 * 
 * Another thing is that just putting every object in the drawing loop is no good.
 * Having a list of object is obvious and then reducing it to teh visible list is also 
 * clear. But... in order to pass control upwards we need to have a 3DScene object.
 * that can simply add or remove objects and maybe reduce memory usage.
 * It heavily simplifies the whole thing. The scene should have a method for
 * retrieving the cameras, so the loop can decide what cameras are going to be used 
 * (basic CRUD actually).
 * 
 * So a loop has a scene, gc and controls about everything else as well.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class NewMain extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/resource/view/Main.fxml"));
        Scene scene = new Scene(root);
        */
        //TODO this is total stub code with arbitrary numbers.
        final int width = 600;
        final int height = 400;
        SimpleDisplay display = new SimpleDisplay(width, height);
        Scene scene = new ControlledScene(display, new NewInput());
        //TODO make sure the view (display) listens to the canvas contents.
        //bind the scene eventlisteners to the controller
        WritableImage test = new WritableImage(width, height);
        final long startNanoTime = System.nanoTime();
        //Start loop
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                emptyImage(test);
                
                for(int c = 0; c < 100; c++)
                {
                for(int x = 0; x < 300 - c; x++)
                {
                    for(int y = 0; y < 200 - c; y++)
                    {
                        test.getPixelWriter().setColor(x + 50, y + 50, Color.rgb(c, c, c));
                        //display.getCanvas().getGraphicsContext2D().getPixelWriter().setColor(x + 50, y + 50, Color.rgb(c, c, c));
                    }
                }
                }
                display.getCanvas().getGraphicsContext2D().drawImage(test, 0.0, 0.0);
                double deltaTime = (currentNanoTime - startNanoTime) / 1000000000.0;
                //cam.viewAndDisplay(world);
                //cam.clearView();
            }
        }.start();
        primaryStage.setScene(scene);
        //primaryStage.setTitle();
        primaryStage.show();
    }
    
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    @Deprecated
    private void emptyImage(WritableImage image)
    {
        for(int x = 0; x < (int)image.getWidth(); x++)
        {
            for(int y = 0; y < (int)image.getHeight(); y++)
            {
                image.getPixelWriter().setColor(x, y, Color.WHITE);
            }
        }
    }
}
