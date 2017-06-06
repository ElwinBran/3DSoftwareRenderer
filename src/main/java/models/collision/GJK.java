package main.java.models.collision;


import java.util.ArrayList;
import main.java.models.Vector4f;

/**
 * Gilbert–Johnson–Keerthi algorithm.
 *
 * @author Elwin Slokker
 */
public class GJK
{
    private static Vector4f a;
    private static Vector4f b;
    private static Vector4f c;
    private static Vector4f d;
    private static Vector4f vdir;
    /**
     *
     */
    private static int n;

    public static boolean convexShapesIntersect(Shape3D convexShape1, Shape3D convexShape2)
    {
        Vector4f dir = new Vector4f(1f, 1f, 1f);

        c = support(convexShape1, convexShape2, dir);

        dir = c.mul(-1f);//negative direction

        b = support(convexShape1, convexShape2, dir);

        if (b.dot(dir) < 0)
        {
            return false;
        }
        dir = doubleCross(c.sub(b), b.mul(-1f));

        n = 2; //begin with 2 points in simplex

        int steps = 0;//avoid infinite loop
        while (steps < 50)
        {
            a = support(convexShape1, convexShape2, dir);
            if (a.dot(dir) < 0)
            {
                return false;
            } else if (containsOrigin(dir))
            {
                return true;
            }
            steps++;

        }

        return false;
    }

    private static boolean containsOrigin(Vector4f dir)
    {
        /*if (n == 1)
	 {
		return line(a, dir);
	 }*/

        if (n == 2)
        {
            return triangle(dir);
        } else if (n == 3)
        {
            return tetrahedron(dir);
        }

        return false;
    }

    private static boolean line(Vector4f dir)
    {
        Vector4f ab = b.sub(a);
        Vector4f ao = a.mul(-1f);//vector3::zero() - a

        //can t be behind b;
        //new direction towards a0
        dir = doubleCross(ab, ao);

        c = b;
        b = a;
        n = 2;

        return false;
    }

    private static boolean triangle(Vector4f dir)
    {
        Vector4f ao = new Vector4f(-a.getX(), -a.getY(), -a.getZ());
        Vector4f ab = b.sub(a);
        Vector4f ac = c.sub(a);
        Vector4f abc = ab.cross(ac);

        //point is can't be behind/in the direction of B,C or BC
        Vector4f ab_abc = ab.cross(abc);
        // is the origin away from ab edge? in the same plane
        //if a0 is in that direction than
        if (ab_abc.dot(ao) > 0)
        {
            //change points
            c = b;
            b = a;

            //dir is not ab_abc because it's not point towards the origin
            dir = doubleCross(ab, ao);

            //direction change; can't build tetrahedron
            return false;
        }

        Vector4f abc_ac = abc.cross(ac);

        // is the origin away from ac edge? or it is in abc?
        //if a0 is in that direction than
        if (abc_ac.dot(ao) > 0)
        {
            //keep c the same
            b = a;

            //dir is not abc_ac because it's not point towards the origin
            dir = doubleCross(ac, ao);

            //direction change; can't build tetrahedron
            return false;
        }

        //now can build tetrahedron; check if it's above or below
        if (abc.dot(ao) > 0)
        {
            //base of tetrahedron
            d = c;
            c = b;
            b = a;

            //new direction
            dir = abc;
        } else
        {
            //upside down tetrahedron
            d = b;
            b = a;
            dir = abc.mul(-1f);
        }

        n = 3;

        return false;
    }

    private static boolean tetrahedron(Vector4f dir)
    {
        Vector4f ao = a.mul(-1f);//0-a
        Vector4f ab = b.sub(a);
        Vector4f ac = c.sub(a);

        //build abc triangle
        Vector4f abc = ab.cross(ac);

        //CASE 1
        if (abc.dot(ao) > 0)
        {
            //in front of triangle ABC
            //we don't have to change the ao,ab,ac,abc meanings
            checkTetrahedron(ao, ab, ac, abc, dir);
        }

        //CASE 2:
        Vector4f ad = d.sub(a);

        //build acd triangle
        Vector4f acd = ac.cross(ad);

	 //same direaction with ao
	 if (acd.dot(ao) > 0)
        {

            //in front of triangle ACD
            b = c;
            c = d;
            ab = ac;
            ac = ad;
            abc = acd;

            return checkTetrahedron(ao, ab, ac, abc, dir);
        }

        //build adb triangle
        Vector4f adb = ad.cross(ab);

        //case 3:
        //same direaction with ao
        if (adb.dot(ao) > 0)
        {

            //in front of triangle ADB
            c = b;
            b = d;

            ac = ab;
            ab = ad;

            abc = adb;
            return checkTetrahedron(ao, ab, ac, abc, dir);
        }

        //origin in tetrahedron
        return true;

    }

    private static boolean checkTetrahedron(Vector4f ao, Vector4f ab, Vector4f ac, Vector4f abc, Vector4f dir)
    {

        //almost the same like triangle checks
        Vector4f ab_abc = ab.cross(abc);

        if (ab_abc.dot(ao) > 0)
        {
            c = b;
            b = a;

            //dir is not ab_abc because it's not point towards the origin;
            //ABxA0xAB direction we are looking for
            dir = doubleCross(ab, ao);

            //build new triangle
            // d will be lost
            n = 2;

            return false;
        }

        Vector4f acp = abc.cross(ac);

        if (acp.dot(ao) > 0)
        {
            b = a;

            //dir is not abc_ac because it's not point towards the origin;
            //ACxA0xAC direction we are looking for
            dir = doubleCross(ac, ao);
            //ac x (ac x ao)

            //build new triangle
            // d will be lost
            n = 2;

            return false;
        }

        //build new tetrahedron with new base
        d = c;
        c = b;
        b = a;

        dir = abc;

        n = 3;

        return false;
    }

    /**
     *
     * @param convexShape1
     * @param convexShape2
     * @param searchDirection
     * @return
     */
    public static Vector4f support(Shape3D convexShape1, Shape3D convexShape2, Vector4f searchDirection)
    {
        /*
	 Vector4f p1 = convexShape1.furthestPointFromDirection(searchDirection);
	 Vector4f p2 = convexShape2.furthestPointFromDirection(new Vector4f(-searchDirection.GetX(), -searchDirection.GetY(), -searchDirection.GetZ()));

	 //p2 -p1
	 vector4f p3 = p1 - p2;
         */
        return convexShape1.furthestPointFromDirection(searchDirection)
                .sub(convexShape2.furthestPointFromDirection(
                        searchDirection.mul(-1f)));
    }

    /**
     *
     * @param vectorA
     * @param vectorB
     * @return
     */
    public static Vector4f doubleCross(Vector4f vectorA, Vector4f vectorB)
    {
        return vectorA.cross(vectorA.cross(vectorB));
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
