package main.java.models;


public class Edge
{
    private float m_x;
    private float m_xStep;
    private int m_yStart;
    private int m_yEnd;
    private float m_texCoordX;
    private float m_texCoordXStep;
    private float m_texCoordY;
    private float m_texCoordYStep;
    private float m_oneOverZ;
    private float m_oneOverZStep;
    private float m_depth;
    private float m_depthStep;
    private float m_lightAmt;
    private float m_lightAmtStep;

    public float getX()
    {
        return m_x;
    }

    public int getYStart()
    {
        return m_yStart;
    }

    public int getYEnd()
    {
        return m_yEnd;
    }

    public float getTexCoordX()
    {
        return m_texCoordX;
    }

    public float getTexCoordY()
    {
        return m_texCoordY;
    }

    public float getOneOverZ()
    {
        return m_oneOverZ;
    }

    public float getDepth()
    {
        return m_depth;
    }

    public float getLightAmt()
    {
        return m_lightAmt;
    }

    public Edge(Gradients gradients, Vertex minYVert, Vertex maxYVert, int minYVertIndex)
    {
        m_yStart = (int) Math.ceil(minYVert.getY());
        m_yEnd = (int) Math.ceil(maxYVert.getY());

        float yDist = maxYVert.getY() - minYVert.getY();
        float xDist = maxYVert.getX() - minYVert.getX();

        float yPrestep = m_yStart - minYVert.getY();
        m_xStep = (float) xDist / (float) yDist;
        m_x = minYVert.getX() + yPrestep * m_xStep;
        float xPrestep = m_x - minYVert.getX();

        m_texCoordX = gradients.getTexCoordX(minYVertIndex)
                + gradients.getTexCoordXXStep() * xPrestep
                + gradients.getTexCoordXYStep() * yPrestep;
        m_texCoordXStep = gradients.getTexCoordXYStep() + gradients.getTexCoordXXStep() * m_xStep;

        m_texCoordY = gradients.getTexCoordY(minYVertIndex)
                + gradients.getTexCoordYXStep() * xPrestep
                + gradients.getTexCoordYYStep() * yPrestep;
        m_texCoordYStep = gradients.getTexCoordYYStep() + gradients.getTexCoordYXStep() * m_xStep;

        m_oneOverZ = gradients.getOneOverZ(minYVertIndex)
                + gradients.getOneOverZXStep() * xPrestep
                + gradients.getOneOverZYStep() * yPrestep;
        m_oneOverZStep = gradients.getOneOverZYStep() + gradients.getOneOverZXStep() * m_xStep;

        m_depth = gradients.getDepth(minYVertIndex)
                + gradients.getDepthXStep() * xPrestep
                + gradients.getDepthYStep() * yPrestep;
        m_depthStep = gradients.getDepthYStep() + gradients.getDepthXStep() * m_xStep;

        m_lightAmt = gradients.getLightAmt(minYVertIndex)
                + gradients.getLightAmtXStep() * xPrestep
                + gradients.getLightAmtYStep() * yPrestep;
        m_lightAmtStep = gradients.getLightAmtYStep() + gradients.getLightAmtXStep() * m_xStep;
    }

    public void step()
    {
        m_x += m_xStep;
        m_texCoordX += m_texCoordXStep;
        m_texCoordY += m_texCoordYStep;
        m_oneOverZ += m_oneOverZStep;
        m_depth += m_depthStep;
        m_lightAmt += m_lightAmtStep;
    }
}
