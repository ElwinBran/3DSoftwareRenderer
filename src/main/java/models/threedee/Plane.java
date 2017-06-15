package main.java.models.threedee;


/**
 * A representation of the mathematical 3d plane.
 * Plane equation: ax + by + cz + d = 0
 *
 * @author Elwin Slokker
 */
public class Plane
{
    /**
     * The Unit Normal of the plane (a normalized vector perpendicular to the
     * plane).
     */
    private final Vector4f planeNormal;
    /**
     * The d factor from the plane equation.
     */
    private final float d;

    /**
     * Constructs a Plane with three points (that are not on a shared line).
     * 
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     */
    public Plane(Vector4f firstPoint, Vector4f secondPoint, final Vector4f thirdPoint)
    {
        firstPoint = firstPoint.subtract(thirdPoint);
        secondPoint = secondPoint.subtract(thirdPoint);
        this.planeNormal = firstPoint.crossProduct(secondPoint).normalized();
        /*
        float x1, x2, y1, y2, z1, z2;
        x1 = firstPoint.GetX() - thirdPoint.GetX();//1
        y1 = firstPoint.GetY() - thirdPoint.GetY();//0
        z1 = firstPoint.GetZ() - thirdPoint.GetZ();//-1
        x2 = secondPoint.GetX() - thirdPoint.GetX();//0
        y2 = secondPoint.GetY() - thirdPoint.GetY();//1
        z2 = secondPoint.GetZ() - thirdPoint.GetZ();//-1
        float a = y1 * z2 - z1 * y2;//1
        float b = z1 * x2 - x1 * z2;//1
        float c = x1 * y2 - y1 * x2;//1
        this.planeNormal = new Vector4f(a,b,c,0.0f).Normalized();
         */
        this.d = -(this.planeNormal.dotProduct(thirdPoint));
    }

    /**
     * Constructs a Plane with the four factors and normalizes it.
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Plane(final float a, final float b, final float c, final float d)
    {
        this.d = d / (new Vector4f(a, b, c, 0.0f)).length();
        this.planeNormal = new Vector4f(a, b, c, 0.0f).normalized();
    }

    /**
     * Constructs a Plane using a point and a normal.
     *
     * @param normal
     * @param point
     */
    public Plane(Vector4f normal, Vector4f point)
    {
        this.planeNormal = new Vector4f(0.0f, 0.0f, 0.0f, 0.0f);
        this.d = 0f;
    }
    /**
     * The shortest distance from this plane to the point.
     * 
     * @param point The point you want to have the distance from the plane.
     * @return The exact distance and direction of the point in respect to the plane.
     */
    public float distanceTo(Vector4f point)
    {
        //(-D = (<a,b,c>.P<x,y,z>) -d => D = -((<a,b,c>.P<x,y,z>) -d))
        return -((this.planeNormal.dotProduct(point) - this.d));
    }
    /**
     * 
     * @param point
     * @return null
     * @deprecated Because using vectors makes the normal distanceTo equal to this method.
     */
    @Deprecated
    public float relativeLocation(Vector4f point)
    {
        return 0.0f;
    }
    /*
                              d-D  =   a   b   c
    we can use the fact that (2-d) = <1/3,2/3,2/3> . p where . is the dot product and p is a point.
    (-D = (<a,b,c>.P<x,y,z>) -d => D = -((<a,b,c>.P<x,y,z>) -d))
    In this form it becomes very easy to find the distance between any point let alone the origin to the plane,
    and by using this normalized form we plug in p = (0,0,0) to give:
    2-d = 0*1/3 + 0*2/3 + 0*2/3 which gives 2-d = 0 which gives d = 2.

    Reference https://www.physicsforums.com/threads/distance-from-a-plane-to-the-origin-question.636540/
     */
}
