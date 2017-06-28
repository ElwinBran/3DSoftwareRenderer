
package main.java.models.threedee.objects;

/**
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public enum RenderableType
{
    /**
     * A 'normal' 3d object that consists of vertices.
     */
    SOLID,
    /**
     * A 'normal' 3d object that consists of vertices, but some are transparent 
     * (this is important for the renderer to know).
     */
    HAS_TRANSPARENT_VERTICES,
    /**
     * An object that is mathematically defined. The renderer will have to 'ask'
     * this object its details and try to approximate it.
     * For example, a sphere; the renderer will want to know what the radius is and other properties.
     * Except for cheaply rendering shapes, it may be used to render otherwise impossible splines and curves(?).
     */
    MATHEMETICAL_SHAPE,
    /**
     * An object that is mathematically defined and is also transparent. The renderer will have to 'ask'
     * this object its details and try to approximate it.
     * For example, a sphere; the renderer will want to know what the radius is and other properties.
     * This type might be very nice in a ray tracer; a transparent sphere. 
     */
    TRANSPARENT_MATHEMETICAL_SHAPE
}
