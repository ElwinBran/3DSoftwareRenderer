package main.java.models.threedee;

/**
 * A very crude class representing a 3-point polygon (triangle).
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class Polygon
{
    private Vertex vertexOne;
    private Vertex vertexTwo;
    private Vertex vertexThree;

    //get points
    //get drawing method?
    //calulate normal?
    public Polygon(Vertex one, Vertex two, Vertex three)
    {
        this.vertexOne = one;
        this.vertexTwo = two;
        this.vertexThree = three;
    }

    public Vertex getVertex(int index)
    {
        switch (index)
        {
        case 0:
            return vertexOne;
        case 1:
            return vertexTwo;
        case 2:
            return vertexThree;
        }
        return null;
    }
}
