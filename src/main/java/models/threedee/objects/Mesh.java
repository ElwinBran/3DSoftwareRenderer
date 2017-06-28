package main.java.models.threedee.objects;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import main.java.models.threedee.matrix.Matrix4f;
import main.java.models.threedee.matrix.Matrix4fUtilities;
import main.java.models.threedee.Polygon;
import main.java.models.threedee.Transform;
import main.java.models.threedee.Vertex;
/**
 * Mockup for testing RenderableObject.
 * 
 * @author Elwin Slokker
 * @author Benny Bobaganoosh
 * @version 0.1
 */
public class Mesh
{
    private List<Vertex> vertices;
    private List<Integer> indices;
    private Transform trans;

    public Mesh(String fileName) throws IOException
    {
        final IndexedModel model = new OBJModel(fileName).toIndexedModel();

        vertices = new ArrayList<Vertex>();
        for (int i = 0; i < model.getPositions().size(); i++)
        {
            vertices.add(new Vertex(
                    model.getPositions().get(i),
                    model.getTexCoords().get(i),
                    model.getNormals().get(i)));
        }

        indices = model.getIndices();
    }

    public Polygon getFaceInScreenSpace(int index, Matrix4f projectionMatrix, Matrix4f screenSpace)
    {
        Matrix4f identity = Matrix4fUtilities.initIdentity();
        return new Polygon(
                vertices.get(indices.get(index)).transform(projectionMatrix, trans.getTransformation()).transform(screenSpace, identity).perspectiveDivide()
                , vertices.get(indices.get(index + 1)).transform(projectionMatrix, trans.getTransformation()).transform(screenSpace, identity).perspectiveDivide()
                , vertices.get(indices.get(index + 2)).transform(projectionMatrix, trans.getTransformation()).transform(screenSpace, identity).perspectiveDivide()
        );
    }
    public Polygon getPolygon(int index)
    {
        return new Polygon(vertices.get(indices.get(index)), vertices.get(indices.get(index + 1)), vertices.get(indices.get(index + 2)));
    }
    public int getPolygonAmount()
    {
        return indices.size()/3;
    }
}
