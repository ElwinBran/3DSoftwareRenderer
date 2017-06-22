package main.java.models.collision;

import java.util.HashMap;
import java.util.Map;
import main.java.models.threedee.Vector4f;

/**
 * Gilbert–Johnson–Keerthi algorithm.
 * 
 * TODO it is still unfinished.
 * @author Elwin Slokker
 * @version 0.4
 */
public class GJKAlgorithm
{
    public static boolean convexShapesIntersect(final GJKAble convexShapeOne, final GJKAble convexShapeTwo)
    {
        final Map<PointMapping,Vector4f> simplex = new HashMap<>();//makeshift simplex object for performance.
        Vector4f dir = new Vector4f(1f, 1f, 1f);
        
        simplex.put(PointMapping.C, support(convexShapeOne, convexShapeTwo, dir));
        dir = simplex.get(PointMapping.C).multiply(-1f);//negative direction
        simplex.put(PointMapping.B, support(convexShapeOne, convexShapeTwo, dir));

        if (simplex.get(PointMapping.B).dotProduct(dir) < 0)
        {
            return false;
        }
        dir = doubleCross(simplex.get(PointMapping.C).subtract(simplex.get(PointMapping.B)), simplex.get(PointMapping.B).multiply(-1f));
        return loop(dir, simplex, convexShapeOne, convexShapeTwo);
    }

    public static boolean loop(Vector4f dir, Map<PointMapping, Vector4f> simplex, final GJKAble convexShapeOne, final GJKAble convexShapeTwo)
    {
        boolean condition = false;

        final Vector4f[] direction = new Vector4f[1];
        direction[0] = dir;
        for(int i = 0; i < 50; i++)//prevent infinite loop.
        {
            simplex.put(PointMapping.A, support(convexShapeOne, convexShapeTwo, direction[0]));
            if (simplex.get(PointMapping.A).dotProduct(direction[0]) < 0)
            {
                break;
            } else if (containsOrigin(direction, simplex))
            {
                condition = true;
                break;
            }
        }
        return condition;
    }
    
    private static boolean containsOrigin(Vector4f[] direction, final Map<PointMapping,Vector4f> simplex)
    {
        switch(simplex.size() - 1) //the amount of keys -1 is equal to the dimesions of the simplex.
        {
            case(1): 
                return line(direction, simplex);
            case(2): 
                return triangle(direction, simplex);
            case(3): 
                return tetrahedron(direction, simplex);
            default: 
                return false;
        }
    }

    private static boolean line(Vector4f[] direction, final Map<PointMapping,Vector4f> simplex)
    {
        Vector4f ab = simplex.get(PointMapping.B).subtract(simplex.get(PointMapping.A));
        Vector4f ao = simplex.get(PointMapping.A).multiply(-1f);//vector3::zero() - a

        //can t be behind b;
        //new direction towards a0
        direction[0] = doubleCross(ab, ao);//impure stuff

        simplex.put(PointMapping.C,simplex.get(PointMapping.B));
        simplex.put(PointMapping.B,simplex.get(PointMapping.A));
        simplex.remove(PointMapping.D);//ensures a 2D simplex.
        return false;
    }

    private static boolean triangle(Vector4f[] direction, final Map<PointMapping,Vector4f> simplex)
    {
        Vector4f ao = new Vector4f(-simplex.get(PointMapping.A).getX(), -simplex.get(PointMapping.A).getY(), -simplex.get(PointMapping.A).getZ());//??
        Vector4f ab = simplex.get(PointMapping.B).subtract(simplex.get(PointMapping.A));
        Vector4f ac = simplex.get(PointMapping.C).subtract(simplex.get(PointMapping.A));
        Vector4f abc = ab.crossProduct(ac);

        //point is can't be behind/in the direction of B,C or BC
        // is the origin away from ab edge? in the same plane
        //if a0 is in that direction than
        if ((ab.crossProduct(abc)).dotProduct(ao) > 0)
        {
            //change points
            simplex.put(PointMapping.C,simplex.get(PointMapping.B));
            simplex.put(PointMapping.B,simplex.get(PointMapping.A));
            //dir is not ab_abc because it's not point towards the origin
            direction[0] = doubleCross(ab, ao);
            //direction change; can't build tetrahedron
            return false;
        }

        // is the origin away from ac edge? or it is in abc?
        //if a0 is in that direction than
        if ((abc.crossProduct(ac)).dotProduct(ao) > 0)
        {
            //keep c the same
            simplex.put(PointMapping.B,simplex.get(PointMapping.A));
            //dir is not abc_ac because it's not point towards the origin
            direction[0] = doubleCross(ac, ao);
            //direction change; can't build tetrahedron
            return false;
        }

        //now can build tetrahedron; check if it's above or below
        if (abc.dotProduct(ao) > 0)
        {
            //base of tetrahedron
            simplex.put(PointMapping.D,simplex.get(PointMapping.C));
            simplex.put(PointMapping.C,simplex.get(PointMapping.B));
            simplex.put(PointMapping.B,simplex.get(PointMapping.A));

            //new direction
            direction[0] = abc;
        } else
        {
            //upside down tetrahedron
            simplex.put(PointMapping.D,simplex.get(PointMapping.B));
            simplex.put(PointMapping.B,simplex.get(PointMapping.A));
            direction[0] = abc.multiply(-1f);
        }
        return false;
    }

    private static boolean tetrahedron(Vector4f[] direction, final Map<PointMapping,Vector4f> simplex)
    {
        Vector4f ao = simplex.get(PointMapping.A).multiply(-1f);//0-a
        Vector4f ab = simplex.get(PointMapping.B).subtract(simplex.get(PointMapping.A));
        Vector4f ac = simplex.get(PointMapping.B).subtract(simplex.get(PointMapping.A));
        //build abc triangle
        Vector4f abc = ab.crossProduct(ac);

        //CASE 1
        if (abc.dotProduct(ao) > 0)
        {
            //in front of triangle ABC
            //we don't have to change the ao,ab,ac,abc meanings
            checkTetrahedron(ao, ab, ac, abc, direction, simplex);
        }

        //CASE 2:
        Vector4f ad = simplex.get(PointMapping.D).subtract(simplex.get(PointMapping.A));

        //build acd triangle
        Vector4f acd = ac.crossProduct(ad);

	 //same direaction with ao
	 if (acd.dotProduct(ao) > 0)
        {
            //in front of triangle ACD
            simplex.put(PointMapping.B,simplex.get(PointMapping.C));
            simplex.put(PointMapping.C,simplex.get(PointMapping.D));
            ab = ac;
            ac = ad;
            abc = acd;
            return checkTetrahedron(ao, ab, ac, abc, direction, simplex);
        }

        //build adb triangle
        Vector4f adb = ad.crossProduct(ab);

        //case 3:
        //same direaction with ao
        if (adb.dotProduct(ao) > 0)
        {
            //in front of triangle ADB
            simplex.put(PointMapping.C,simplex.get(PointMapping.B));
            simplex.put(PointMapping.B,simplex.get(PointMapping.D));
            ac = ab;
            ab = ad;
            abc = adb;
            return checkTetrahedron(ao, ab, ac, abc, direction, simplex);
        }
        //origin in tetrahedron
        return true;

    }

    private static boolean checkTetrahedron(Vector4f ao, Vector4f ab, Vector4f ac, Vector4f abc, Vector4f[] direction, final Map<PointMapping,Vector4f> simplex)
    {
        //almost the same like triangle checks
        Vector4f ab_abc = ab.crossProduct(abc);

        if (ab_abc.dotProduct(ao) > 0)
        {
            simplex.put(PointMapping.C,simplex.get(PointMapping.B));
            simplex.put(PointMapping.B,simplex.get(PointMapping.A));
            //dir is not ab_abc because it's not point towards the origin;
            //ABxA0xAB direction we are looking for
            direction[0] = doubleCross(ab, ao);
            //build new triangle
            // d will be lost
            simplex.remove(PointMapping.D);
            return false;
        }

        Vector4f acp = abc.crossProduct(ac);

        if (acp.dotProduct(ao) > 0)
        {
            simplex.put(PointMapping.B,simplex.get(PointMapping.A));
            //dir is not abc_ac because it's not point towards the origin;
            //ACxA0xAC direction we are looking for
            direction[0] = doubleCross(ac, ao);
            //ac x (ac x ao)

            //build new triangle
            // d will be lost
            simplex.remove(PointMapping.D);
            return false;
        }

        //build new tetrahedron with new base
        simplex.put(PointMapping.D,simplex.get(PointMapping.C));
        simplex.put(PointMapping.C,simplex.get(PointMapping.B));
        simplex.put(PointMapping.B,simplex.get(PointMapping.A));
        direction[0] = abc;
        return false;
    }

    /**
     *
     * @param convexShape1
     * @param convexShape2
     * @param searchDirection
     * @return
     */
    public static Vector4f support(GJKAble convexShape1, GJKAble convexShape2, Vector4f searchDirection)
    {
        /*
        ORIGINAL CODE (?)
	 Vector4f p1 = convexShape1.furthestPointFromDirection(searchDirection);
	 Vector4f p2 = convexShape2.furthestPointFromDirection(new Vector4f(-searchDirection.GetX(), -searchDirection.GetY(), -searchDirection.GetZ()));

	 //p2 -p1
	 vector4f p3 = p1 - p2;
         */
        return convexShape1.furthestPointFromDirection(searchDirection)
                .subtract(convexShape2.furthestPointFromDirection(
                        searchDirection.multiply(-1f)));
    }

    /**
     *
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static Vector4f doubleCross(Vector4f vectorA, Vector4f vectorB)
    {
        return vectorA.crossProduct(vectorA.crossProduct(vectorB));
    }
    private static enum PointMapping
    {
        A,
        B,
        C,
        D
    }
}

/*
public class GJK
{
    private static Vector4f v;
    private static Vector4f b;
    private static Vector4f c;
    private static Vector4f d;
    private static int n;

    public static boolean convexShapesIntersect(Shape3D convexShape1, Shape3D convexShape2)
    {
        //.Mul(-1.0f)
        GJK.v = new Vector4f(1f, 0f, 0f,0f); //some arbitrary starting vector

        GJK.c = support(convexShape1, convexShape2, v); //the shape support
        if (GJK.c.Dot(v) < 0)
        {
            return false;
        }

        GJK.v = GJK.c.Mul(-1.0f);
        GJK.b = support(convexShape1, convexShape2, v);

        if (GJK.b.Dot(GJK.v) < 0)
        {
            return false;
        }

        GJK.v = crossAba(GJK.c.Sub(GJK.b), new Vector4f(0f,0f,0f,0f).Sub(GJK.b));
        GJK.n = 2;

        for (int iterations = 0; iterations < 32; iterations++)
        {
            Vector4f a = support(convexShape1, convexShape2, GJK.v);

            if (a.Dot(GJK.v) < 0)
            {
                return false;
            }

            if (update(a))
            {
                return true;
            }
        }

        //out of iterations, be conservative
        return true;
    }

    private static Vector4f support(Shape3D convexShape1, Shape3D convexShape2, Vector4f searchDirection)
    {
        return convexShape1.furthestPointFromDirection(searchDirection).Sub(convexShape2.furthestPointFromDirection(searchDirection));
    }

    private static boolean update(Vector4f a)
    {
        switch (GJK.n)
        {
        case 0:
            GJK.b = a;
            GJK.v = a.Mul(-1.0f);
            GJK.n = 1;
            return false;
        case 1:
            GJK.v = crossAba(b.Sub(a), a.Mul(-1.0f));
            GJK.c = GJK.b;
            GJK.b = a; //silly, yes, we'll come back to this
            GJK.n = 2;
            //can't possibly contain the origin unless we've
            //built a tetrahedron, so just return false
            return false;
        case 2:
            Vector4f ao = a.Mul(-1.0f); //silly, yes, clarity is important

            //compute the vectors parallel to the edges we'll test
            Vector4f ab = b.Sub(a);
            Vector4f ac = c.Sub(a);

            //compute the triangle's normal
            Vector4f abc = ab.Cross(ac);

            //compute a vector within the plane of the triangle,
            //pointing away from the edge ab
            Vector4f abp = ab.Cross(abc);

            if (abp.Dot(ao) > 0)
            {
                //the origin lies outside the triangle,
                //near the edge ab
                GJK.c = GJK.b;
                GJK.b = a;
                GJK.v = crossAba(ab, ao);

                return false;
            }

            //perform a similar test for the edge ac
            Vector4f acp = abc.Cross(ac);

            if (acp.Dot(ao) > 0)
            {
                b = a;
                v = crossAba(ac, ao);
                return false;
            }

            //if we get here, then the origin must be
            //within the triangle, but we care whether
            //it is above or below it, so test
            if (abc.Dot(ao) > 0)
            {
                d = c;
                c = b;
                b = a;

                v = abc;
            } else
            {
                d = b;
                b = a;

                v = abc.Mul(-1.0f);
            }

            n = 3;

            //again, need a tetrahedron to enclose the origin
            return false;
        //return a vector perpendicular to a and
        //parallel to (and in the direction of) b
        case 3:
            return Case3D(a);

        default:
            return false;
        }
    }

    private static Vector4f crossAba(Vector4f a, Vector4f b)
    {
        return (a.Cross(b)).Cross(a);
    }

    private static boolean Case3D(Vector4f a)
    {
        Vector4f ao = a.Mul(-1.0f);

        Vector4f ab = b.Sub(a);
        Vector4f ac = c.Sub(a);
        Vector4f ad = d.Sub(a);

        Vector4f abc = ab.Cross(ac);
        Vector4f acd = ac.Cross(ad);
        Vector4f adb = ad.Cross(ab);

        Vector4f tmp;

        final int over_abc = 0b001;
        final int over_acd = 0b010;
        final int over_adb = 0b100;

        int plane_tests
                = (abc.Dot(ao) > 0 ? over_abc : 0)
                | (acd.Dot(ao) > 0 ? over_acd : 0)
                | (adb.Dot(ao) > 0 ? over_adb : 0);

        switch (plane_tests)
        {
        case 0:
            //behind all three faces, thus inside the tetrahedron - we're done
            return true;

        case over_abc:
            return checkOneFacePartOne(a, ab, ac, ao, abc);

        case over_acd:
            //rotate ACD into ABC

            b = c;
            c = d;

            ab = ac;
            ac = ad;

            abc = acd;

            return checkOneFacePartOne(a, ab, ac, ao, abc);

        case over_adb:
            //rotate ADB into ABC

            c = b;
            b = d;

            ac = ab;
            ab = ad;

            abc = adb;

            return checkOneFacePartOne(a, ab, ac, ao, abc);

        case over_abc | over_acd:
            return checkTwoFaces(a, ab, ac, ad, ao, abc, acd);

        case over_acd | over_adb:
            //rotate ACD, ADB into ABC, ACD

            tmp = b;
            b = c;
            c = d;
            d = tmp;

            tmp = ab;
            ab = ac;
            ac = ad;
            ad = tmp;

            abc = acd;
            acd = adb;

            return checkTwoFaces(a, ab, ac, ad, ao, abc, acd);

        case over_adb | over_abc:
            //rotate ADB, ABC into ABC, ACD

            tmp = c;
            c = b;
            b = d;
            d = tmp;

            tmp = ac;
            ac = ab;
            ab = ad;
            ad = tmp;

            acd = abc;
            abc = adb;

            return checkTwoFaces(a, ab, ac, ad, ao, abc, acd);

        default:
            //we've managed to build a horribly degenerate simplex
            //this could just as easily be an assert, but since this
            //code was originally used to reject definite non-intersections
            //as an optimization it conservatively returns true
            return true;
        }
    }

    private static boolean checkOneFacePartOne(Vector4f a, Vector4f ab, Vector4f ac, Vector4f ao, Vector4f abc)
    {
        if (abc.Cross(ac).Dot(ao) > 0)
        {
            //in the region of AC

            b = a;

            v = crossAba(ac, ao);

            n = 2;

            return false;
        }
        return checkOneFacePartTwo(a, ab, ac, ao, abc);
    }

    private static boolean checkOneFacePartTwo(Vector4f a, Vector4f ab, Vector4f ac, Vector4f ao, Vector4f abc)
    {
        if (ab.Cross(abc).Dot(ao) > 0)
        {
            //in the region of edge AB

            c = b;
            b = a;

            v = crossAba(ab, ao);

            n = 2;

            return false;
        }

//in the region of ABC
        d = c;
        c = b;
        b = a;

        v = abc;

        n = 3;

        return false;
    }

    private static boolean checkTwoFaces(Vector4f a, Vector4f ab, Vector4f ac, Vector4f ad, Vector4f ao, Vector4f abc, Vector4f acd)
    {

        if (abc.Cross(ac).Dot(ao) > 0)
        {
            //the origin is beyond AC from ABC's
            //perspective, effectively excluding
            //ACD from consideration

            //we thus need test only ACD
            b = c;
            c = d;

            ab = ac;
            ac = ad;

            abc = acd;
            return checkOneFacePartOne(a, ab, ac, ao, abc);
        }
        //at this point we know we're either over
        //ABC or over AB - all that's left is the
        //second half of the one-fase test
        return checkOneFacePartTwo(a, ab, ac, ao, abc);
    }
    /*
    Vector4f sphere_support(sphere s, Vector4f v)
    {
        //remove the division if v is known to be normalized elsewhere
        return s.center + v * (s.radius / v.Length());
    }

    //aabb = axis-aligned bounding box
    Vector4f aabb_support(
            
    
        const aabb &bb, const Vector4f &v )
{
	Vector4f ret;

        ret.x = v.x >= 0 ? bb.max.x : bb.min.x;
        ret.y = v.y >= 0 ? bb.max.y : bb.min.y;
        ret.z = v.z >= 0 ? bb.max.z : bb.min.z;

        return ret;
    }

    Vector4f point_cloud_support(ArrayList<Vector4f> points, Vector4f v)
    {
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
    }
}
 */
