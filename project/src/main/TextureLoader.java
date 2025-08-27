package main;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;

public class TextureLoader {
    private static HashMap<String, Texture> textures_map;

    public static void loadTexture(HashMap<String, Texture> textures) {
        //LOAD textures
        textures_map = textures;
        try {
            loadTexture("earthspace.jpg");
            loadTexture("wool_pink.png");
            loadTexture("default_stone.png");

            loadTexture("bookshelf.png");


            loadTexture("zombie_skin_texture.jpg");
            loadTexture("abstract-green.jpg");

            loadTexture("wood.png");
            loadTexture("blood.png");
            loadTexture("hand-drawn-blood-handprint.jpg");
            loadTexture("pyramid-head-with-welcome-sign.jpg");
            loadTexture("metal.jpg");
            loadTexture("blood_on_metal.jpg");
            // jointTexture
            loadTexture("blood_on_skin.jpg");

            //sky box
            loadTexture("bgbox/Texture2024-top.jpg");
            loadTexture("bgbox/Texture2024-middle.jpg");
            loadTexture("bgbox/Texture2024-down.jpg");

            loadTexture("bgbox/silent_hill_top.jpg");
            loadTexture("bgbox/silent_hill_side1.jpg");
            loadTexture("bgbox/silent_hill_side2.jpg");
            loadTexture("bgbox/silent_hill_down.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadTexture(String filename) throws IOException {
        String key_name = filename.split("\\.")[0];
        Display.setTitle("Loading....... Texture:" + filename);
        textures_map.put(
                key_name,
                org.newdawn.slick.opengl.TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/" + filename))
        );
    }

    public static Texture getTexture(String filename, String fileType) throws IOException {
        String key_name = filename.split("\\.")[0];
        return org.newdawn.slick.opengl.TextureLoader.getTexture(fileType, ResourceLoader.getResourceAsStream("res/" + filename));
    }
}
