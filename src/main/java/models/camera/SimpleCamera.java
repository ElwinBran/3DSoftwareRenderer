package main.java.models.camera;

import main.java.models.matrix.Matrix4f;
import main.java.models.matrix.Matrix4fBuilder;
import main.java.models.scene.RenderableScene;
import main.java.models.threedee.Quaternion;
import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;

public class SimpleCamera extends AbstractCamera
{
    private static final Vector4f Y_AXIS = new Vector4f(0, 1, 0);
    private static final float SENSITIVITY_X = 2.66f;
    private static final float SENSITIVITY_Y = 2.0f;
    private static final float MOVE_AMOUNT_BASE = 5.0f;
    
    private Transform m_transform;
    private Matrix4f m_projection;
    

    public Transform getTransform()
    {
        return m_transform;
    }
    protected SimpleCamera(SimpleCameraBuilder builder)
    {
        super(builder);
        //set some more based on the builder.
    }
    SimpleCamera(RenderableScene scene)
    {
        super(scene);
    }
    /*
    public SimpleCamera(Matrix4f projection)
    {
        this.m_projection = projection;
        this.m_transform = new Transform();
    }
    */
    public Matrix4f getViewProjection()
    {
        Matrix4f cameraRotation = getTransform().getTransformedRot().conjugate().toRotationMatrix();
        Vector4f cameraPos = getTransform().getTransformedPos().multiply(-1);

        Matrix4f cameraTranslation = Matrix4fBuilder.getInstance().initTranslation(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());
        return m_projection.mulitply(cameraRotation.mulitply(cameraTranslation));
    }

    private void move(Vector4f dir, float amt)
    {
        m_transform = getTransform().setPos(getTransform().getPos().add(dir.multiply(amt)));
    }

    private void rotate(Vector4f axis, float angle)
    {
        m_transform = getTransform().rotate(new Quaternion(axis, angle));
    }
}
