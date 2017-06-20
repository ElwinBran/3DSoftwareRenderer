
package main.java.models.lighting;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import main.java.models.threedee.Vector4f;

/**
 * Implements the basic attributes of a 3D light.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public abstract class AbstractLight implements LightInterface
{
    /**
     * 
     */
    private Vector4f position;
    /**
     * 
     */
    private final Property<Color> colorProperty;
    
    /**
     * Default constructor for an {@AbstractLight}.
     * 
     * @param position the position of the light.
     * @param color the color of the light.
     */
    public AbstractLight(Vector4f position, Color color)
    {
        this.position = position;
        this.colorProperty = new SimpleObjectProperty<>(color);
    }
    /**
     * Constructor with control over what property implementation should be used.
     * 
     * @param position the position of the light.
     * @param colorProperty a property.
     */
    public AbstractLight(Vector4f position, Property<Color> colorProperty)
    {
        this.position = position;
        this.colorProperty = colorProperty;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector4f getPosition()
    {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Vector4f position)
    {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Color> getColorProperty()
    {
        return this.colorProperty;
    }
}
