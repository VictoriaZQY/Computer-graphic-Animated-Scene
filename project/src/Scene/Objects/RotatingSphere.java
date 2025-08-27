package Scene.Objects;


import Scene.base.SceneObject;
import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Vector4f;
import base.objects3D.DisplayListTexCube;
import base.objects3D.TexSphere;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

public class RotatingSphere extends SceneObject {
    TexSphere sphere = new TexSphere();
    private float currentRotationAngle = 0.0f; // current rotation angle
    private float rotationSpeed = 50.0f;       // self-rotation 50 degree every second

    public RotatingSphere(Point4f origin, Point4f position, Vector4f scale, HashMap<String, org.newdawn.slick.opengl.Texture> textures) {
        super(origin, position, scale, textures);
    }

    @Override
    public void draw(Integer frame_delta) {
        // calculate the rotation angle
        currentRotationAngle += (rotationSpeed * frame_delta / 1000.0f); // refresh the angle
        if (currentRotationAngle >= 360.0f) {
            currentRotationAngle -= 360.0f; // keep the angle within 360 degree
        }
        // Draw wheels
        float r = 8f;
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(-1.8f, r, -1.2f); // Position front-left wheel
            GL11.glScalef(r, r, r);
            GL11.glRotatef(currentRotationAngle, 0, 1, 0);

            getTextures().get("earthspace").bind();
            sphere.DrawTexSphere(1.0f, 32, 32);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void drawShadow() { }

}
