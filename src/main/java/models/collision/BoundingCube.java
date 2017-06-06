package main.java.models.collision;


import java.util.ArrayList;
import main.java.models.Transform;
import main.java.models.Vector4f;


/**
 *
 * @author Elwin Slokker
 */
public class BoundingCube extends BoundingShape
{
    private ArrayList<Vector4f> baseVertices = new ArrayList<>();
    private ArrayList<Vector4f> currentVertices = new ArrayList<>();
    
    public BoundingCube()
    {
        
    }
    
    @Override
    public void updateWorldTransform(Transform modelTransformation)
    {
       
    }
    @Override
    public Vector4f furthestPointFromDirection(Vector4f direction)
    {
        /*
        int best = 0;
        float best_dot = points.get(0).Dot(v);

        for (int i = 1; i < points.size(); i++)
        {
            float d = points.get(i).Dot(v);
            if (d > best_dot)
            {
                best = i;
                best_dot = d;
            }
        }

        return points.get(best);
        */
        return new Vector4f(1,1,1);
    }
    @Override
    public boolean isInside(Vector4f point)
    {
        /*
        check against all faces
        */
        return false;
    }
        
    @Override
    public boolean isInside(Vector4f point, Vector4f translation)
    {
        /*
        check against all faces
        */
        return false;
    }
}
