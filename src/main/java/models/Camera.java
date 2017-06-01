package main.java.models;


import java.awt.event.KeyEvent;

public class Camera
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

    public Camera(Matrix4f projection)
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

    public void update(Input input, float delta)
    {
        // Speed and rotation amounts are hardcoded here.
        // In a more general system, you might want to have them as variables.
        final float sensitivityX = SENSITIVITY_X * delta;
        final float sensitivityY = SENSITIVITY_Y * delta;
        final float movAmt = MOVE_AMOUNT_BASE * delta;

        // Similarly, input keys are hardcoded here.
        // As before, in a more general system, you might want to have these as variables.
        if (input.getKey(KeyEvent.VK_W))
        {
            move(getTransform().getRot().getForward(), movAmt);
        }
        if (input.getKey(KeyEvent.VK_S))
        {
            move(getTransform().getRot().getForward(), -movAmt);
        }
        if (input.getKey(KeyEvent.VK_A))
        {
            move(getTransform().getRot().getLeft(), movAmt);
        }
        if (input.getKey(KeyEvent.VK_D))
        {
            move(getTransform().getRot().getRight(), movAmt);
        }

        if (input.getKey(KeyEvent.VK_RIGHT))
        {
            rotate(Y_AXIS, sensitivityX);
        }
        if (input.getKey(KeyEvent.VK_LEFT))
        {
            rotate(Y_AXIS, -sensitivityX);
        }
        if (input.getKey(KeyEvent.VK_DOWN))
        {
            rotate(getTransform().getRot().getRight(), sensitivityY);
        }
        if (input.getKey(KeyEvent.VK_UP))
        {
            rotate(getTransform().getRot().getRight(), -sensitivityY);
        }
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
