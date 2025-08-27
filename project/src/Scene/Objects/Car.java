package Scene.Objects;

import Scene.base.SceneObject;
import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Vector4f;
import base.objects3D.DisplayListTexCube;
import base.objects3D.TexSphere;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class Car extends SceneObject {
    DisplayListTexCube cube = new DisplayListTexCube();
    TexSphere sphere = new TexSphere();

    public Car(Point4f origin, Point4f position, Vector4f scale, HashMap<String, org.newdawn.slick.opengl.Texture> textures) {
        super(origin, position, scale, textures);
    }

    @Override
    public void draw(Integer frame_delta) {
        // Draw car body
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(0, 1.5f, 0);
            GL11.glScalef(4.0f, 1.0f, 2.0f); // Scale to create a rectangular body

            getTextures().get("blood").bind();
            cube.DrawTexCube();
        }
        GL11.glPopMatrix();

        // Draw wheels
        float wheelRadius = 0.8f;
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(-1.8f, wheelRadius, -1.2f); // Position front-left wheel
            GL11.glScalef(wheelRadius, wheelRadius, wheelRadius);
            sphere.DrawTexSphere(1.0f, 32, 32);
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        {
            GL11.glTranslatef(1.8f, wheelRadius, -1.2f); // Position front-right wheel
            GL11.glScalef(wheelRadius, wheelRadius, wheelRadius);
            sphere.DrawTexSphere(1.0f, 32, 32);
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        {
            GL11.glTranslatef(-1.8f, wheelRadius, 1.2f); // Position back-left wheel
            GL11.glScalef(wheelRadius, wheelRadius, wheelRadius);
            sphere.DrawTexSphere(1.0f, 32, 32);
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        {
            GL11.glTranslatef(1.8f, wheelRadius, 1.2f); // Position back-right wheel
            GL11.glScalef(wheelRadius, wheelRadius, wheelRadius);
            sphere.DrawTexSphere(1.0f, 32, 32);
        }
        GL11.glPopMatrix();

        // Draw roof
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(0, 2.5f, 0); // Raised roof above the body
            GL11.glScalef(2.0f, 1.2f, 1.5f); // Adjusted scale to maintain proportional roof
            cube.DrawTexCube();
        }
        GL11.glPopMatrix();
    }

    @Override
    public void drawShadow() { }

}
