package base.objects3D;

import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Vector4f;

import static org.lwjgl.opengl.GL11.*;
/**
 * This part is initialized first cuz due to the update of movement,
 * OR the picture will get very awkward.
 */
public class DisplayListTetrahedron {
    int displayListHandle = glGenLists(1);

    public DisplayListTetrahedron(float baseLength, float height) {
        glNewList(displayListHandle, GL_COMPILE);

        // Define the vertices based on base length and height
        Point4f apex = new Point4f(0.0f, height, 0.0f, 0.0f);  // Apex pointing in the positive Y direction
        Point4f base1 = new Point4f(-baseLength / 2.0f, 0.0f, -baseLength / 2.0f, 0.0f);
        Point4f base2 = new Point4f(-baseLength / 2.0f, 0.0f, baseLength / 2.0f, 0.0f);
        Point4f base3 = new Point4f(baseLength / 2.0f, 0.0f, 0.0f, 0.0f); // Adjusted to maintain symmetry

        // Define the faces of the tetrahedron (three vertices per face)
        Point4f[][] faces = {
                {apex, base1, base2}, // Front face
                {apex, base2, base3}, // Right face
                {apex, base3, base1}, // Left face
                {base1, base3, base2} // Base face
        };

        glBegin(GL_TRIANGLES);

        for (Point4f[] face : faces) {
            // Compute the normal for the face
            Vector4f edge1 = face[1].MinusPoint(face[0]);
            Vector4f edge2 = face[2].MinusPoint(face[0]);
            Vector4f normal = edge1.cross(edge2).Normal();

            // Set the normal for the current face
            glNormal3f(normal.x, normal.y, normal.z);

            // Draw the vertices of the face
            for (Point4f vertex : face) {
                glVertex3f(vertex.x, vertex.y, vertex.z);
            }
        }

        glEnd();
        glEndList();
    }

    // Method to draw the tetrahedron
    public void DrawTetrahedron() {
        glCallList(displayListHandle);
    }
}