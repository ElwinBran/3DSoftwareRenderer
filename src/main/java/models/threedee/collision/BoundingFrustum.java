package main.java.models.threedee.collision;

import java.util.List;
import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;

/**
 * TODO unfinished.
 * @author Elwin Slokker
 * @version 0.1
 */
public class BoundingFrustum extends BoundingVolume
{
    List<Vector4f> points;
    public BoundingFrustum(Transform transform)
    {
        super(transform);
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public Vector4f getFurthestPointFromDirection(Vector4f direction)
    {
        int best = 0;
        float best_dot = points.get(0).dotProduct(direction);

        for (int i = 1; i < points.size(); i++)
        {
            float d = points.get(i).dotProduct(direction);
            if (d > best_dot)
            {
                best = i;
                best_dot = d;
            }
        }
        return points.get(best);
    }

}
