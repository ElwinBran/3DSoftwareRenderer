package main.java.models.camera;

import main.java.models.Matrix4f;
import main.java.models.Quaternion;
import main.java.models.Transform;
import main.java.models.Vector4f;

public class SimpleCamera extends AbstractCamera
{
    private static final Vector4f Y_AXIS = new Vector4f(0, 1, 0);
    private static final float SENSITIVITY_X = 2.66f;
    private static final float SENSITIVITY_Y = 2.0f;
    private static final float MOVE_AMOUNT_BASE = 5.0f;
    
    private Transform m_transform;
    private Matrix4f m_projection;
    

    private Transform getTransform()
    {
        return m_transform;
    }

    public SimpleCamera(Matrix4f projection)
    {
        this.m_projection = projection;
        this.m_transform = new Transform();
    }

    public Matrix4f getViewProjection()
    {
        Matrix4f cameraRotation = getTransform().getTransformedRot().Conjugate().toRotationMatrix();
        Vector4f cameraPos = getTransform().getTransformedPos().mul(-1);

        Matrix4f cameraTranslation = new Matrix4f().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());

        return m_projection.mul(cameraRotation.mul(cameraTranslation));
    }

    private void move(Vector4f dir, float amt)
    {
        m_transform = getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
    }

    private void rotate(Vector4f axis, float angle)
    {
        m_transform = getTransform().rotate(new Quaternion(axis, angle));
    }
}
