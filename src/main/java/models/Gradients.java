package main.java.models;


public class Gradients
{
    private float[] m_texCoordX;
    private float[] m_texCoordY;
    private float[] m_oneOverZ;
    private float[] m_depth;
    private float[] m_lightAmt;

    private float m_texCoordXXStep;
    private float m_texCoordXYStep;
    private float m_texCoordYXStep;
    private float m_texCoordYYStep;
    private float m_oneOverZXStep;
    private float m_oneOverZYStep;
    private float m_depthXStep;
    private float m_depthYStep;
    private float m_lightAmtXStep;
    private float m_lightAmtYStep;

    public float getTexCoordX(int loc)
    {
        return m_texCoordX[loc];
    }

    public float getTexCoordY(int loc)
    {
        return m_texCoordY[loc];
    }

    public float getOneOverZ(int loc)
    {
        return m_oneOverZ[loc];
    }

    public float getDepth(int loc)
    {
        return m_depth[loc];
    }

    public float getLightAmt(int loc)
    {
        return m_lightAmt[loc];
    }

    public float getTexCoordXXStep()
    {
        return m_texCoordXXStep;
    }

    public float getTexCoordXYStep()
    {
        return m_texCoordXYStep;
    }

    public float getTexCoordYXStep()
    {
        return m_texCoordYXStep;
    }

    public float getTexCoordYYStep()
    {
        return m_texCoordYYStep;
    }

    public float getOneOverZXStep()
    {
        return m_oneOverZXStep;
    }

    public float getOneOverZYStep()
    {
        return m_oneOverZYStep;
    }

    public float getDepthXStep()
    {
        return m_depthXStep;
    }

    public float getDepthYStep()
    {
        return m_depthYStep;
    }

    public float getLightAmtXStep()
    {
        return m_lightAmtXStep;
    }

    public float getLightAmtYStep()
    {
        return m_lightAmtYStep;
    }

    private float calcXStep(float[] values, Vertex minYVert, Vertex midYVert,
            Vertex maxYVert, float oneOverdX)
    {
        return (((values[1] - values[2])
                * (minYVert.getY() - maxYVert.getY()))
                - ((values[0] - values[2])
                * (midYVert.getY() - maxYVert.getY()))) * oneOverdX;
    }

    private float calcYStep(float[] values, Vertex minYVert, Vertex midYVert,
            Vertex maxYVert, float oneOverdY)
    {
        return (((values[1] - values[2])
                * (minYVert.getX() - maxYVert.getX()))
                - ((values[0] - values[2])
                * (midYVert.getX() - maxYVert.getX()))) * oneOverdY;
    }

    private float saturate(float val)
    {
        if (val > 1.0f)
        {
            return 1.0f;
        }
        if (val < 0.0f)
        {
            return 0.0f;
        }
        return val;
    }

    public Gradients(Vertex minYVert, Vertex midYVert, Vertex maxYVert)
    {
        float oneOverdX = 1.0f
                / (((midYVert.getX() - maxYVert.getX())
                * (minYVert.getY() - maxYVert.getY()))
                - ((minYVert.getX() - maxYVert.getX())
                * (midYVert.getY() - maxYVert.getY())));

        float oneOverdY = -oneOverdX;

        m_oneOverZ = new float[3];
        m_texCoordX = new float[3];
        m_texCoordY = new float[3];
        m_depth = new float[3];
        m_lightAmt = new float[3];

        m_depth[0] = minYVert.getPosition().getZ();
        m_depth[1] = midYVert.getPosition().getZ();
        m_depth[2] = maxYVert.getPosition().getZ();

        Vector4f lightDir = new Vector4f(0, 0, 1);
        m_lightAmt[0] = saturate(minYVert.getNormal().dot(lightDir)) * 0.9f + 0.1f;
        m_lightAmt[1] = saturate(midYVert.getNormal().dot(lightDir)) * 0.9f + 0.1f;
        m_lightAmt[2] = saturate(maxYVert.getNormal().dot(lightDir)) * 0.9f + 0.1f;

        // Note that the W component is the perspective Z value;
        // The Z component is the occlusion Z value
        m_oneOverZ[0] = 1.0f / minYVert.getPosition().getW();
        m_oneOverZ[1] = 1.0f / midYVert.getPosition().getW();
        m_oneOverZ[2] = 1.0f / maxYVert.getPosition().getW();

        m_texCoordX[0] = minYVert.GetTexCoords().getX() * m_oneOverZ[0];
        m_texCoordX[1] = midYVert.GetTexCoords().getX() * m_oneOverZ[1];
        m_texCoordX[2] = maxYVert.GetTexCoords().getX() * m_oneOverZ[2];

        m_texCoordY[0] = minYVert.GetTexCoords().getY() * m_oneOverZ[0];
        m_texCoordY[1] = midYVert.GetTexCoords().getY() * m_oneOverZ[1];
        m_texCoordY[2] = maxYVert.GetTexCoords().getY() * m_oneOverZ[2];

        m_texCoordXXStep = calcXStep(m_texCoordX, minYVert, midYVert, maxYVert, oneOverdX);
        m_texCoordXYStep = calcYStep(m_texCoordX, minYVert, midYVert, maxYVert, oneOverdY);
        m_texCoordYXStep = calcXStep(m_texCoordY, minYVert, midYVert, maxYVert, oneOverdX);
        m_texCoordYYStep = calcYStep(m_texCoordY, minYVert, midYVert, maxYVert, oneOverdY);
        m_oneOverZXStep = calcXStep(m_oneOverZ, minYVert, midYVert, maxYVert, oneOverdX);
        m_oneOverZYStep = calcYStep(m_oneOverZ, minYVert, midYVert, maxYVert, oneOverdY);
        m_depthXStep = calcXStep(m_depth, minYVert, midYVert, maxYVert, oneOverdX);
        m_depthYStep = calcYStep(m_depth, minYVert, midYVert, maxYVert, oneOverdY);
        m_lightAmtXStep = calcXStep(m_lightAmt, minYVert, midYVert, maxYVert, oneOverdX);
        m_lightAmtYStep = calcYStep(m_lightAmt, minYVert, midYVert, maxYVert, oneOverdY);
    }
}
