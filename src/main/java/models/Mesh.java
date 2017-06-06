package main.java.models;


import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class Mesh
{
    private List<Vertex> m_vertices;
    private List<Integer> m_indices;

    public Mesh(String fileName) throws IOException
    {
        final IndexedModel model = new OBJModel(fileName).toIndexedModel();

        m_vertices = new ArrayList<Vertex>();
        for (int i = 0; i < model.getPositions().size(); i++)
        {
            m_vertices.add(new Vertex(
                    model.getPositions().get(i),
                    model.getTexCoords().get(i),
                    model.getNormals().get(i)));
        }

        m_indices = model.getIndices();
    }

    public void draw(RenderContext context, Matrix4f viewProjection, Matrix4f transform, Bitmap texture)
    {
        Matrix4f mvp = viewProjection.mul(transform);
        for (int i = 0; i < m_indices.size(); i += 3)
        {
            context.drawTriangle(
                    m_vertices.get(m_indices.get(i)).transform(mvp, transform),
                    m_vertices.get(m_indices.get(i + 1)).transform(mvp, transform),
                    m_vertices.get(m_indices.get(i + 2)).transform(mvp, transform),
                    texture);
        }
    }
}
