package base.objects3D;

import static org.lwjgl.opengl.GL11.*;

import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Vector4f;

/***
 * Draw a tall tetrahedron with texture
 */
public class TexTetrahedron {

    public TexTetrahedron() {
    }

    /**
     * Define the vertices and faces of the tetrahedron, calculate the normal of each
     * face, set texture coordinates, and draw the tetrahedron.
     */
    public void drawTexTetrahedron() {
        // Vertices of the tall tetrahedron
        Point4f vertices[] = { 
            new Point4f(0.0f, 1.5f, 0.0f, 1.0f),  // Top vertex (higher than base)
            new Point4f(-1.0f, 0.0f, -1.0f, 1.0f), // Base vertex 1
            new Point4f(1.0f, 0.0f, -1.0f, 1.0f),  // Base vertex 2
            new Point4f(0.0f, 0.0f, 1.0f, 1.0f)    // Base vertex 3
        };

        // Faces of the tetrahedron (each defined by 3 vertices)
        int faces[][] = { 
            { 0, 1, 2 }, // Face 1
            { 0, 2, 3 }, // Face 2
            { 0, 3, 1 }, // Face 3
            { 1, 3, 2 }  // Base face
        };

        // Draw the tetrahedron
        glBegin(GL_TRIANGLES);

        for (int face = 0; face < faces.length; face++) {
            // Calculate the normal for each face
            Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            Vector4f w = vertices[faces[face][2]].MinusPoint(vertices[faces[face][0]]);
            Vector4f normal = v.cross(w).Normal();
            glNormal3f(normal.x, normal.y, normal.z);

            // Set texture coordinates and draw each vertex of the face
            glTexCoord2f(0.0f, 0.0f);
            glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);

            glTexCoord2f(1.0f, 0.0f);
            glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);

            glTexCoord2f(0.5f, 1.0f);
            glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
        }

        glEnd();
    }
}
