package main.java.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class RenderContext extends Bitmap
{
    private float[] m_zBuffer;

    public RenderContext(int width, int height)
    {
        super(width, height);
        m_zBuffer = new float[width * height];
    }

    public void clearDepthBuffer()
    {
        for (int i = 0; i < m_zBuffer.length; i++)
        {
            m_zBuffer[i] = Float.MAX_VALUE;
        }
    }

    public void drawTriangle(Vertex v1, Vertex v2, Vertex v3, Bitmap texture)
    {
        if (v1.isInsideViewFrustum() && v2.isInsideViewFrustum() && v3.isInsideViewFrustum())
        {
            fillTriangle(v1, v2, v3, texture);
            return;
        }

        List<Vertex> vertices = new ArrayList<>();
        List<Vertex> auxillaryList = new ArrayList<>();

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        if (clipPolygonAxis(vertices, auxillaryList, 0)
                && clipPolygonAxis(vertices, auxillaryList, 1)
                && clipPolygonAxis(vertices, auxillaryList, 2))
        {
            Vertex initialVertex = vertices.get(0);

            for (int i = 1; i < vertices.size() - 1; i++)
            {
                fillTriangle(initialVertex, vertices.get(i), vertices.get(i + 1), texture);
            }
        }
    }

    private boolean clipPolygonAxis(List<Vertex> vertices, List<Vertex> auxillaryList,
            int componentIndex)
    {
        clipPolygonComponent(vertices, componentIndex, 1.0f, auxillaryList);
        vertices.clear();

        if (auxillaryList.isEmpty())
        {
            return false;
        }

        clipPolygonComponent(auxillaryList, componentIndex, -1.0f, vertices);
        auxillaryList.clear();

        return !vertices.isEmpty();
    }

    private void clipPolygonComponent(List<Vertex> vertices, int componentIndex,
            float componentFactor, List<Vertex> result)
    {
        Vertex previousVertex = vertices.get(vertices.size() - 1);
        float previousComponent = previousVertex.get(componentIndex) * componentFactor;
        boolean previousInside = previousComponent <= previousVertex.getPosition().getW();

        Iterator<Vertex> it = vertices.iterator();
        while (it.hasNext())
        {
            Vertex currentVertex = it.next();
            float currentComponent = currentVertex.get(componentIndex) * componentFactor;
            boolean currentInside = currentComponent <= currentVertex.getPosition().getW();

            if (currentInside ^ previousInside)
            {
                float lerpAmt = (previousVertex.getPosition().getW() - previousComponent)
                        / ((previousVertex.getPosition().getW() - previousComponent)
                        - (currentVertex.getPosition().getW() - currentComponent));

                result.add(previousVertex.lerp(currentVertex, lerpAmt));
            }

            if (currentInside)
            {
                result.add(currentVertex);
            }

            previousVertex = currentVertex;
            previousComponent = currentComponent;
            previousInside = currentInside;
        }
    }

    private void fillTriangle(Vertex v1, Vertex v2, Vertex v3, Bitmap texture)
    {
        Matrix4f screenSpaceTransform
                = new Matrix4f().initScreenSpaceTransform(getWidth() / 2, getHeight() / 2);
        Matrix4f identity = new Matrix4f().initIdentity();
        Vertex minYVert = v1.transform(screenSpaceTransform, identity).perspectiveDivide();
        Vertex midYVert = v2.transform(screenSpaceTransform, identity).perspectiveDivide();
        Vertex maxYVert = v3.transform(screenSpaceTransform, identity).perspectiveDivide();

        if (minYVert.triangleAreaTimesTwo(maxYVert, midYVert) >= 0)
        {
            return;
        }

        if (maxYVert.getY() < midYVert.getY())
        {
            Vertex temp = maxYVert;
            maxYVert = midYVert;
            midYVert = temp;
        }

        if (midYVert.getY() < minYVert.getY())
        {
            Vertex temp = midYVert;
            midYVert = minYVert;
            minYVert = temp;
        }

        if (maxYVert.getY() < midYVert.getY())
        {
            Vertex temp = maxYVert;
            maxYVert = midYVert;
            midYVert = temp;
        }

        scanTriangle(minYVert, midYVert, maxYVert,
                minYVert.triangleAreaTimesTwo(maxYVert, midYVert) >= 0,
                texture);
    }

    private void scanTriangle(Vertex minYVert, Vertex midYVert,
            Vertex maxYVert, boolean handedness, Bitmap texture)
    {
        Gradients gradients = new Gradients(minYVert, midYVert, maxYVert);
        Edge topToBottom = new Edge(gradients, minYVert, maxYVert, 0);
        Edge topToMiddle = new Edge(gradients, minYVert, midYVert, 0);
        Edge middleToBottom = new Edge(gradients, midYVert, maxYVert, 1);

        scanEdges(gradients, topToBottom, topToMiddle, handedness, texture);
        scanEdges(gradients, topToBottom, middleToBottom, handedness, texture);
    }

    private void scanEdges(Gradients gradients, Edge a, Edge b, boolean handedness, Bitmap texture)
    {
        Edge left = a;
        Edge right = b;
        if (handedness)
        {
            Edge temp = left;
            left = right;
            right = temp;
        }

        int yStart = b.getYStart();
        int yEnd = b.getYEnd();
        for (int j = yStart; j < yEnd; j++)
        {
            drawScanLine(gradients, left, right, j, texture);
            left.step();
            right.step();
        }
    }

    private void drawScanLine(Gradients gradients, Edge left, Edge right, int j, Bitmap texture)
    {
        int xMin = (int) Math.ceil(left.getX());
        int xMax = (int) Math.ceil(right.getX());
        float xPrestep = xMin - left.getX();

//		float xDist = right.GetX() - left.GetX();
//		float texCoordXXStep = (right.GetTexCoordX() - left.GetTexCoordX())/xDist;
//		float texCoordYXStep = (right.GetTexCoordY() - left.GetTexCoordY())/xDist;
//		float oneOverZXStep = (right.GetOneOverZ() - left.GetOneOverZ())/xDist;
//		float depthXStep = (right.GetDepth() - left.GetDepth())/xDist;
        // Apparently, now that stepping is actually on pixel centers, gradients are
        // precise enough again.
        float texCoordXXStep = gradients.getTexCoordXXStep();
        float texCoordYXStep = gradients.getTexCoordYXStep();
        float oneOverZXStep = gradients.getOneOverZXStep();
        float depthXStep = gradients.getDepthXStep();
        float lightAmtXStep = gradients.getLightAmtXStep();

        float texCoordX = left.getTexCoordX() + texCoordXXStep * xPrestep;
        float texCoordY = left.getTexCoordY() + texCoordYXStep * xPrestep;
        float oneOverZ = left.getOneOverZ() + oneOverZXStep * xPrestep;
        float depth = left.getDepth() + depthXStep * xPrestep;
        float lightAmt = left.getLightAmt() + lightAmtXStep * xPrestep;

        for (int i = xMin; i < xMax; i++)
        {
            int index = i + j * getWidth();
            if (depth < m_zBuffer[index])
            {
                m_zBuffer[index] = depth;
                float z = 1.0f / oneOverZ;
                int srcX = (int) ((texCoordX * z) * (float) (texture.getWidth() - 1) + 0.5f);
                int srcY = (int) ((texCoordY * z) * (float) (texture.getHeight() - 1) + 0.5f);

                copyPixel(i, j, srcX, srcY, texture, lightAmt);
            }

            oneOverZ += oneOverZXStep;
            texCoordX += texCoordXXStep;
            texCoordY += texCoordYXStep;
            depth += depthXStep;
            lightAmt += lightAmtXStep;
        }
    }
}
