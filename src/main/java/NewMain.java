
package main.java;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.models.ControlledScene;
import main.java.models.NewInput;
import main.java.view.Display;

/**
 * The new main of 3DSoftwareRenderer. It starts an Javafx window.
 * This is a multi-theaded application able to render 3D-models using different 
 * renderers (although all software).
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
        Scene scene = new ControlledScene(new Display(600, 400), new NewInput());
        //TODO make sure the view (display) listens to the canvas contents.
        //bind the scene eventlisteners to the controller
        final long startNanoTime = System.nanoTime();
        //Start loop
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                double deltaTime = (currentNanoTime - startNanoTime) / 1000000000.0;
                //cam.viewAndDisplay(world);
                //cam.clearView();
                //MainScreenController.canvasDrawSpace.getGraphicsContext2D().getPixelWriter().setColor(0, 0, Color.CYAN);
                //MainScreenController.canvasDrawSpace.getGraphicsContext2D().getPixelWriter().setColor(5, 5, Color.RED);
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
}
