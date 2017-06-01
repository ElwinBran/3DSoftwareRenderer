package main.java.models;




public class Transform
{
    private Vector4f m_pos;
    private Quaternion m_rot;
    private Vector4f m_scale;

    public Transform()
    {
        this(new Vector4f(0, 0, 0, 0));
    }

    public Transform(Vector4f pos)
    {
        this(pos, new Quaternion(0, 0, 0, 1), new Vector4f(1, 1, 1, 1));
    }

    public Transform(Vector4f pos, Quaternion rot, Vector4f scale)
    {
        m_pos = pos;
        m_rot = rot;
        m_scale = scale;
    }

    public Transform setPos(Vector4f pos)
    {
        return new Transform(pos, m_rot, m_scale);
    }

    public Transform rotate(Quaternion rotation)
    {
        return new Transform(m_pos, rotation.mul(m_rot).normalized(), m_scale);
    }

    public Transform lookAt(Vector4f point, Vector4f up)
    {
        return rotate(getLookAtRotation(point, up));
    }

    public Quaternion getLookAtRotation(Vector4f point, Vector4f up)
    {
        return new Quaternion(new Matrix4f().initRotation(point.sub(m_pos).normalized(), up));
    }

    public Matrix4f getTransformation()
    {
        Matrix4f translationMatrix = new Matrix4f().initTranslation(m_pos.getX(), m_pos.getY(), m_pos.getZ());
        Matrix4f rotationMatrix = m_rot.toRotationMatrix();
        Matrix4f scaleMatrix = new Matrix4f().initScale(m_scale.getX(), m_scale.getY(), m_scale.getZ());

        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    public Vector4f getTransformedPos()
    {
        return m_pos;
    }

    public Quaternion getTransformedRot()
    {
        return m_rot;
    }

    public Vector4f getPos()
    {
        return m_pos;
    }

    public Quaternion getRot()
    {
        return m_rot;
    }

    public Vector4f getScale()
    {
        return m_scale;
    }
}
