package main.java.fx;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import main.java.controllers.NewInput;


/**
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public class ControlledScene extends Scene
{
    public ControlledScene(Parent root, NewInput input)
    {
        super(root);
        this.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                //input.execute(event.getCode());
            }
        });
    }
    
}
