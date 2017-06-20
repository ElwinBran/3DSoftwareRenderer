
package main.java.models.loop;

import javafx.beans.property.SimpleIntegerProperty;
import main.java.view.DisplayInterface;

/**
 * A simple render loop that only renders one scene at the time with the first 
 * camera of the scene.
 * The only control one has on this loop is being able to change the scene.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public class SimpleRenderLoop extends AbstractLoop
{
    
    private final SimpleIntegerProperty selectedSceneIndex = new SimpleIntegerProperty();
    public SimpleRenderLoop(DisplayInterface display)
    {
        super(display);
    }
    /**
     * 
     * @param now 
     */
    @Override
    public void handle(long now)
    {
        //TODO this is not complete
        super.getScenes().get(selectedSceneIndex.get()).get(0).drawView(super.getDisplay().getPixelWriter());
        //give the display the camera to render with or take pixel writer give it to camera...
    }
    public SimpleIntegerProperty getSelectedSceneIndex()
    {
        return this.selectedSceneIndex;
    }
}
