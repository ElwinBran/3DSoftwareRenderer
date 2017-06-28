package main.java.models.threedee.collision;


import java.util.ArrayList;
import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;


/**
 * TODO unfinished.
 * @author Elwin Slokker
 * @version 0.1
 */
public class BoundingCube extends BoundingVolume
{
    private ArrayList<Vector4f> baseVertices = new ArrayList<>();
    private ArrayList<Vector4f> currentVertices = new ArrayList<>();

    public BoundingCube(Transform transform)
    {
        super(transform);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Vector4f getFurthestPointFromDirection(Vector4f direction)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
