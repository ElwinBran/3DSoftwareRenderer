
package main.java.models.camera.builderinterfaces;

import main.java.models.scene.RenderableScene;

/**
 *
 * @author Elwin Slokker
 * @version 0.2
 * @param <L>
 */
public interface SceneSetter<L>
{
    public TypeSetter<L> setScene(RenderableScene scene);
}
