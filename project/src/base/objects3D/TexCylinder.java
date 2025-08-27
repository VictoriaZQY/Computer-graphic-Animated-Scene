package base.objects3D;

import org.lwjgl.opengl.GL11;

/***
 * draw a cylinder with texture
 */
public class TexCylinder {
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    public TexCylinder() {

    }

    /***
     * determine the vertices and faces of the cylinder, then calculate the normal of each face, the texture coordinates and draw the cylinder
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     * @param nSegments the number of segments of the cylinder
     */
    public void drawTexCylinder(float radius, float height, int nSegments) {
        float s,t;
        // the vertices of the cylinder
        for (float i = 0.0f; i < nSegments; i += 1.0f) {
            // calculate the angle of each segment
            float angle = (float) (Math.PI * i * 2.0 / nSegments);
            float nextAngle = (float) (Math.PI * (i + 1.0) * 2.0 / nSegments);

            // calculate the x and y of each segment
            float x1 = (float) Math.sin(angle), y1 = (float) Math.cos(angle);
            float x2 = (float) Math.sin(nextAngle), y2 = (float) Math.cos(nextAngle);

            // draw the cylinder, each segment has two triangles
            GL11.glBegin(GL11.GL_TRIANGLES);
            //  draw top triangle
            GL11.glNormal3f(x1, y1, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f((float) i / nSegments, 0.0f);
            GL11.glVertex3f(x1 * radius, y1 * radius, 0.0f);

            GL11.glNormal3f(x2, y2, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f((float) (i + 1.0) / nSegments, 1.0f);
            GL11.glVertex3f(x2 * radius, y2 * radius, height);

            GL11.glNormal3f(x1, y1, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f((float) i / nSegments, 1.0f);
            GL11.glVertex3f(x1*radius, y1*radius, height);

            // draw bottom triangle
            GL11.glNormal3f(x1, y1, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f((float) i / nSegments, 0.0f);
            GL11.glVertex3f(x1*radius, y1*radius, 0.0f);
            GL11.glNormal3f(x2, y2, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f((float) (i + 1.0) / nSegments, 0.0f);
            GL11.glVertex3f(x2*radius, y2*radius, 0.0f);
            GL11.glNormal3f(x2, y2, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f((float) (i + 1.0) / nSegments, 1.0f);
            GL11.glVertex3f(x2*radius, y2*radius, height);

            // draw bottom circle
            GL11.glNormal3f(0.0f, 0.0f, -1.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f(0.5f + x1 * 0.5f, 0.5f + y1 * 0.5f);
            GL11.glVertex3f(x1 * radius, y1 * radius, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f(0.5f + x2 * 0.5f, 0.5f + y2 * 0.5f);
            GL11.glVertex3f(x2 * radius, y2 * radius, 0.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f(0.5f, 0.5f);
            GL11.glVertex3f(0.0f, 0.0f, 0.0f);
            // draw top circle
            GL11.glNormal3f(0.0f, 0.0f, 1.0f);
            // calculate the texture coordinates
            GL11.glTexCoord2f(0.5f + x1 * 0.5f, 0.5f + y1 * 0.5f);
            GL11.glVertex3f(x1 * radius, y1 * radius, height);
            // calculate the texture coordinates
            GL11.glTexCoord2f(0.5f + x2 * 0.5f, 0.5f + y2 * 0.5f);
            GL11.glVertex3f(x2 * radius, y2 * radius, height);
            // calculate the texture coordinates
            GL11.glTexCoord2f(0.5f, 0.5f);
            GL11.glVertex3f(0.0f, 0.0f, height);

            GL11.glEnd();
        }
    }
}
