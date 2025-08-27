package base.objects3D;

import base.GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;


public class Grid {

    static float[] black = {0.0f, 0.0f, 0.0f, 1.0f};
    static float[] white = {1.0f, 1.0f, 1.0f, 1.0f};


    public Grid() {

    }

    // Do not touch this class, I have implmented to help you in your Assignment 3 and project
    public void DrawGrid() {
        int nGridlines = 50;

        int x, z;
        // edges don't reflect
        GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
        GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_EMISSION, Utils.ConvertForGL(black)); // but they do emit
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glPushMatrix();
        for (x = -nGridlines; x <= nGridlines; x++) { // for each x
            if ((x % 50 > 0)) GL11.glLineWidth((float) 0.1);

            else GL11.glLineWidth((float) 0.1);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3i(x, 0, -nGridlines);
            GL11.glVertex3i(x, 0, +nGridlines);
            GL11.glEnd();
        } // for each x


        for (z = -nGridlines; z <= nGridlines; z++) { // for each y
            if ((z % 50 > 0)) GL11.glLineWidth((float) 0.1);
            else GL11.glLineWidth((float) 0.1);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3i(-nGridlines, 0, z);
            GL11.glVertex3i(+nGridlines, 0, z);
            GL11.glEnd();
        } // for each y
        GL11.glLineWidth((float) 1.0);
        GL11.glPopMatrix();
        // stop emitting, otherwise other objects will emit the same colour
        GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_EMISSION, Utils.ConvertForGL(black)); // but they do emit


    }
}
