
package main.java.models.renderers;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import main.java.models.camera.CameraInterface;
import main.java.models.newmodels.RenderableObject;
import main.java.models.threedee.Polygon;
import main.java.models.threedee.Vector4f;

/**
 * A simple ray tracer. It does barely any optimising and is made to be easily understandable.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class SimpleRayTracer extends RayTracer
{
    @Override
    public void render(List<RenderableObject> objects, CameraInterface myCamera, PixelWriter writer, Point2D shift)
    {
        for(float x = 0; x < myCamera.getSceenSpaceMatrix().get(0, 0) * 2; x++)
        {
            for(float y = 0; y < myCamera.getSceenSpaceMatrix().get(1, 1) * -2; y++)
            {
                Vector4f ray = null;
                //make the ray
                writer.setColor((int) (x + shift.getX()), (int) (y + shift.getY()), calculateRay(objects, myCamera, ray));
            }
        }
    }

    @Override
    public Image render(List<RenderableObject> objects, CameraInterface myCamera)
    {
        WritableImage render = null;
        render(objects, myCamera, render.getPixelWriter(), new Point2D(0,0));
        return render;
    }

    @Override
    public void prepareForNewFrame()
    {
        //nothing
    }
    
    private Color calculateRay(List<RenderableObject> objects, CameraInterface myCamera, Vector4f rayDirection)
    {
        float distance = Float.MAX_VALUE;
        Polygon currentMinimum = null;
        for(RenderableObject object : myCamera.getScene().getObjects())
        {
            for(Polygon p : object.getFaces())
            {
                float temp = rayToPolygonDistance(myCamera.getTransform().getPos(), rayDirection, p.getVertex(0).getPosition(), p.getVertex(1).getPosition(), p.getVertex(2).getPosition());
                if(temp < distance)
                {
                    distance = temp;
                    currentMinimum = p;
                }
            }
        }
        
        //find first collision with polygon
        //IF or IFNOT transparent....
        //recusively trace all rays afterwards.
        return Color.ALICEBLUE;//TODO stub
    }/**
     * 
     * @param origin of the ray.
     * @param direction a normalized vector specifying the direction of the ray.
     * @param v1
     * @param v2
     * @param v3
     * @return 
     */
    private float rayToPolygonDistance(Vector4f origin, Vector4f direction, Vector4f v1,Vector4f v2,Vector4f v3)
    {
        final boolean cull = true;
        final float eps = 0.000001f;
        Vector4f result = new Vector4f(0,0,0);
        float resultX;
        float resultY;
        float resultZ;
        Vector4f edge1;
        Vector4f edge2;
        Vector4f tvec;
        Vector4f pvec;
        Vector4f qvec;
        float determinant, inv_det;

        edge1 = v2.subtract(v1);
        edge2 = v3.subtract(v1);
        pvec = direction.crossProduct(edge2);
        determinant = edge1.dotProduct(pvec);

        if(cull)
        {
            if( determinant < eps )
            {
                //cout << "cull test failed" << endl;
                return Float.MAX_VALUE;//no intersect
            }

            tvec = origin.subtract(v1);

            resultX = tvec.dotProduct(pvec);

            if( resultX < 0.0f || resultX > determinant )
            {
                //cout << "U test failed" << endl;
                return Float.MAX_VALUE;
            }

            qvec = tvec.crossProduct(edge1);
            resultY = direction.dotProduct(qvec);

            if(resultY < 0.0f || ( resultX + resultY ) > determinant)
            {
                //cout << "V test failed" << endl;
                return Float.MAX_VALUE;
            }

            resultZ = edge2.dotProduct(qvec);
            inv_det = 1.0f / determinant;
            resultX *= inv_det;
            resultY *= inv_det;
            resultZ *= inv_det;
        }
        else
        {
            if( determinant > - eps && determinant < eps )
            {
                return Float.MAX_VALUE;
            }

            inv_det = 1.0f / determinant;
            tvec = origin.subtract(v1);
            resultX = tvec.dotProduct(pvec) * inv_det;

            if( resultY < 0.0f || resultX > 1.0f )
            {
                return Float.MAX_VALUE;
            }

            qvec = tvec.crossProduct(edge1);
            resultY = direction.dotProduct(qvec) * inv_det;

            if( resultY < 0.0f || resultX + resultY > 1.0f )
            {
                return Float.MAX_VALUE;
            }

            resultZ = edge2.dotProduct(qvec) * inv_det;
        }

        return resultZ;//distance
        }
}
