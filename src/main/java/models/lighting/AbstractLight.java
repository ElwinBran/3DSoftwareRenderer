
package main.java.models.lighting;

import javafx.scene.paint.Color;
import main.java.models.threedee.Vector4f;
import main.java.models.newmodels.Positionable;

/**
 *
 * @author Elwin Slokker
 */
public class AbstractLight implements LightInterface, Positionable
{
    private Vector4f position;
    private Color color;
    
    @Override
    public Vector4f getPosition()
    {
        return this.position;
    }

    @Override
    public void setPosition(Vector4f position)
    {
        this.position = position;
    }

    @Override
    public Color getColor()
    {
        return this.color;
    }
    @Override
    public void setColor(Color color)
    {
        this.color = color;
    }
    @Override
    public float getIntensity(float distance)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector4f getDirection()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
