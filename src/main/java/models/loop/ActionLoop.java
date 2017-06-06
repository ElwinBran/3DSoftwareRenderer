
package main.java.models.loop;

import java.util.List;
import javafx.animation.AnimationTimer;
import main.java.models.scene.RenderableScene;

/**
 * Using this loop class one can achieve 60FPS real-time rendering.
 * It extends the {@code AnimationTimer} to ensure the right loop speed.
 * What this object will do every cycle can be defined using the public methods [example].
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class ActionLoop extends AnimationTimer implements LoopInterface
{
    /**
     * 
     */
    private long startNanoTime;
    /**
     * 
     */
    private List<RenderableScene> scenes;
    /**
     * 
     * @param now 
     */
    @Override
    public void handle(long now)
    {
        
    }
    /**
     * 
     */
    @Override
    public void start()
    {
        this.startNanoTime = System.nanoTime();
        super.start();
    }

    @Override
    public void addScene(RenderableScene scene)
    {
        this.scenes.add(scene);
    }
}
