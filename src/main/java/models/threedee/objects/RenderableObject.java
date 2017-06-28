
package main.java.models.threedee.objects;

import java.util.List;
import main.java.models.threedee.matrix.Matrix4f;
import main.java.models.threedee.Polygon;
import main.java.models.threedee.Transformable;

/**
 * A 3D renderable object's basic behaviour.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public interface RenderableObject extends BoundedObject, Transformable
{
    //get all polygons
    //get face in screenspace (projection_matrix, screenspace_matrix)
    public List<Polygon> getFacesInScreenSpace(Matrix4f projectionMatrix, Matrix4f screenSpace);
    public Polygon getFaceInScreenSpace(int index, Matrix4f projectionMatrix, Matrix4f screenSpace);
    //public Vector4f something that will return a point based on transforms and screen x, y?
    @Deprecated
    public List<Polygon> getFaces();
}
