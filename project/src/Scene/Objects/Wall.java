package Scene.Objects;

import Scene.base.SceneObject;
import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Vector4f;
import base.objects3D.DisplayListTexCube;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;

/**
 * Wall to construct the maze
 */
public class Wall extends SceneObject {
    DisplayListTexCube cube = new DisplayListTexCube();

    public Wall(Point4f origin, Point4f position, Vector4f scale) {
        super(origin, position, scale);
    }

    public Wall(Point4f origin, Point4f position, Vector4f scale, HashMap<String, Texture> textures) {
        super(origin, position, scale, textures);
    }

    public Wall(Point4f origin, Point4f position, Vector4f scale, Vector4f rotation, HashMap<String, Texture> textures) {
        super(origin, position, scale, rotation, textures);
    }

    @Override
    public void draw(Integer frame_delta) {
        Color.white.bind();
        getTextures().get("bookshelf").bind();
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        GL11.glPushMatrix();
        {
            // Draw a wall with 4 columns and 6 rows
            for (int col = 0; col < 4; col++) {
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(col * 2, 0, 0); // Each column is spaced by 2 units
                    for (int row = 0; row < 4; row++) {
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0, row * 2, 0); // Each row is spaced by 2 units
                            cube.DrawTexCube();
                        }
                        GL11.glPopMatrix();
                    }
                }
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public void drawShadow() {

    }
}
