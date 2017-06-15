package main.java.models.camera.builderinterfaces;

import main.java.models.viewvolumeculling.ViewVolumeCullInterface;

/**
 * Meant to be implemented by {@see main.java.models.camera.BaseCameraBuilder}
 * @author Elwin Slokker
 */
public interface ViewVolumeCullerSetter<L>
{
    public L setViewVolumeCull(ViewVolumeCullInterface culler);
}
