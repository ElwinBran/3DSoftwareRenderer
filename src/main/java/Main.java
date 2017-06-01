package main.java;


/**
 * @file
 * @author Benny Bobaganoosh <thebennybox@gmail.com>
 * @section LICENSE
 *
 * Copyright (c) 2014, Benny Bobaganoosh All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer. 2. Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
import main.java.models.Vector4f;
import main.java.models.Camera;
import main.java.models.Display;
import main.java.models.Bitmap;
import java.io.IOException;
import main.java.models.Matrix4f;
import main.java.models.Mesh;
import main.java.models.Quaternion;
import main.java.models.RenderContext;
import main.java.models.Transform;

/**
 * The sole purpose of this class is to hold the main method.
 *
 * Any other use should be placed in a separate class
 */
public class Main
{
    // Lazy exception handling here. You can do something more interesting 
    // depending on what you're doing
    public static void main(String[] args) throws IOException
    {
        final float zNear = 0.1f;
        final float zFar = 1000.0f;
        
        Display display = new Display(800, 600, "Software Rendering");
        RenderContext target = display.getFrameBuffer();
        
        //Plane plena = new Plane(new Vector4f(1.0f, 0.0f, 0.0f), new Vector4f(0.0f, 1.0f, 0.0f),new Vector4f(0.0f, 0.0f, 1.0f));
        //BoundingSphere bs = new BoundingSphere(new Vector4f(1000f,0f,0f,0f),50f);
        //BoundingSphere bss = new BoundingSphere(new Vector4f(0f,0f,0f,0f), 70f);
        //Transform bssTransform = new Transform(new Vector4f(0, 0.0f, 0.0f));
        
        Bitmap texture = new Bitmap("./res/bricks.jpg");
        Bitmap texture2 = new Bitmap("./res/bricks2.jpg");
        
        Mesh monkeyMesh = new Mesh("./res/smoothMonkey0.obj");
        Transform monkeyTransform = new Transform(new Vector4f(0, 0.0f, 3.0f));

        Mesh terrainMesh = new Mesh("./res/terrain2.obj");
        Transform terrainTransform = new Transform(new Vector4f(0, -1.0f, 0.0f));
        
        //Transform sofaTransform = new Transform(new Vector4f(0, 0.0f, 0.0f));
        //Mesh sofaMesh = new Mesh("./res/sofa.obj");
        Camera camera = new Camera(new Matrix4f().initPerspective((float) Math.toRadians(70.0f),
                (float) target.getWidth() / (float) target.getHeight(), zNear, zFar));
        //Camera camera = new Camera(new Matrix4f().InitOrthographic(-400, 400, -300, 300, zNear, zFar));

        //float rotCounter = 0.0f;
        long previousTime = System.nanoTime();
        while (true)
        {
            long currentTime = System.nanoTime();
            float delta = (float) ((currentTime - previousTime) / 1000000000.0);
            previousTime = currentTime;

            camera.update(display.getInput(), delta);
            Matrix4f vp = camera.getViewProjection();
            //monkeyTransform = monkeyTransform.SetPos(new Vector4f(monkeyTransform.GetPos().GetX()-0.01f,0.0f,0.0f));
            monkeyTransform = monkeyTransform.rotate(new Quaternion(new Vector4f(0, 1, 0), delta));
            //bssTransform = bssTransform.SetPos(new Vector4f(bssTransform.GetPos().GetX()+1.0f,0.0f,0.0f));
            //bss.updateWorldTransform(bssTransform);
            //if (GJK.convexShapesIntersect(bs, bss))
            //{
            //    System.out.println("hooray");
            //}
            target.clear((byte) 0x00);
            target.clearDepthBuffer();
            monkeyMesh.draw(target, vp, monkeyTransform.getTransformation(), texture2);
            terrainMesh.draw(target, vp, terrainTransform.getTransformation(), texture);
            //sofaMesh.Draw(target, vp, sofaTransform.GetTransformation(), texture);
            
            display.swapBuffers();
        }
    }
}
