
package main.java.models.camera;

/**
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public enum ViewType
{
    NORMAL,//lighting, textures etc.
    //NORMAL_WITH_TRANSPARANT_FRAMES,//new frames are transparent and will cause a blur effect.
    WIREFRAME,//only the edges of polygons.
    WIREFRAME_OPAQUE,//only the edges, but polygons cannot be seen through each other.
    FLAT_WITH_LIGHTING,
    NO_LIGHTING
}
