
package main.java.models.lighting;

/**
 * The different kinds of light sources. 
 * They all have a different shape and some specify something about intensity as well.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public enum LightingType
{
    /**
     * a light that shines everywhere without direction.
     * Should have the same intensity everywhere.
     */
    AMBIENT,
    /**
     * a sphere that emit light, like a light bulb.
     */
    POINT,
    /**
     * an area that emits light at the front like a {@code POINT} light.
     * @see main.java.models.lighting.AreaTypes
     */
    POINT_AREA,
    /**
     * a light that shines everywhere with a single direction, like a sun.
     * Should have the same intensity everywhere.
     */
    DIRECTIONAL,
    /**
     * an area that emits light at the front like a {@code DIRECTIONAL} light.
     * @see main.java.models.lighting.AreaTypes
     */
    DIRECTIONAL_AREA,
    /**
     * a light from a single point that lights a rectangle area.
     */
    PYRAMID,
    /**
     * a light from a single point that lights an elliptic area.
     */
    CONE, 
}
