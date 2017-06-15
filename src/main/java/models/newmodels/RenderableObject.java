
package main.java.models.newmodels;

import java.util.List;
import main.java.models.matrix.Matrix4f;
import main.java.models.threedee.Polygon;
import main.java.models.threedee.Transform;

/**
 * A 3D renderable object's basic behaviour.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public interface RenderableObject
{
    public void setTransform(Transform transform);
    public Transform getTransform();
    //get all polygons
    //get face in screenspace (projection_matrix, screenspace_matrix)
    public List<Polygon> getFacesInScreenSpace(Matrix4f projectionMatrix, Matrix4f screenSpace);
}
