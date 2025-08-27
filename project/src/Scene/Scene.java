package Scene;

import Scene.BackgroundBox.BackgroundBox;
import Scene.BackgroundBox.BackgroundBoxDarkMode;
import Scene.Objects.*;
import Scene.base.*;
import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Vector4f;
import main.Engine;
import main.Main;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class Scene {
    public static boolean isDarkMode = false; // current mode, default dark mode
    public static Player player;
    public static NPC center;


    public static void initScene(SceneManager sceneManager, HashMap textures) {
        // SET THE PLAYER FIRST
        player = new Player(
                new Point4f(100, 130, 0, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        );
        sceneManager.addSceneObject(player);

        // the door to escape
        sceneManager.addSceneObject(new Door(
                new Point4f(-2100, 50f, 8000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(500f, 500f, 500f, 0),
                textures
        ));

        // a group of Pyramid Head (less blood on their body) guard
        sceneManager.addSceneObject(new NPC(
                new Point4f(0, 130, 1800, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC(
                new Point4f(-200, 130, 1800, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC(
                new Point4f(200, 130, 1800, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        center = new NPC(
                new Point4f(0, 130, 1600, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        );
        sceneManager.addSceneObject(center);

        sceneManager.addSceneObject(new NPC(
                new Point4f(-200, 130, 1600, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC(
                new Point4f(200, 130, 1600, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));

        sceneManager.addSceneObject(new NPC(
                new Point4f(0, 130, 2000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC(
                new Point4f(-200, 130, 2000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC(
                new Point4f(200, 130, 2000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));

        sceneManager.addSceneObject(new Car(
                new Point4f(100, 0, 8000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90f, 90f, 90f, 0),
                textures
        ));


        // add a welcome board
        sceneManager.addSceneObject(new Welcome(
                new Point4f(0, 5500, -10000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(10, 4000, 6000, 0),
                new Vector4f(0, 1, 0, 90),
                textures
        ));

        // add a free NPC
        sceneManager.addSceneObject(new NPC(
                new Point4f(0, 130, 8000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));

        // Nurses waving
        sceneManager.addSceneObject(new NPC3(
                new Point4f(1200, 130, 6000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                new Vector4f(0, 1, 0, 180),
                textures
        ));
        sceneManager.addSceneObject(new NPC3(
                new Point4f(1600, 130, 6000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                new Vector4f(0, 1, 0, 180),
                textures
        ));
        sceneManager.addSceneObject(new NPC3(
                new Point4f(1200, 130, 6200, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC3(
                new Point4f(1600, 130, 6200, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC3(
                new Point4f(-1200, 130, 6400, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC3(
                new Point4f(-1600, 130, 6400, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC3(
                new Point4f(-1200, 130, 6200, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                new Vector4f(0, 1, 0, 180),
                textures
        ));
        sceneManager.addSceneObject(new NPC3(
                new Point4f(-1600, 130, 6200, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                new Vector4f(0, 1, 0, 180),
                textures
        ));

        RotatingSphere rotatingSphere = new RotatingSphere(
                new Point4f(0, 2000, 12000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(100, 100, 100, 0),
                textures
        );
        sceneManager.addSceneObject(rotatingSphere);


        // Add maze walls using Wall
        addMaze(sceneManager, textures);
    }


    public static void addMaze(SceneManager sceneManager, HashMap textures) {
        float wallHeight = 4 * 2f * 90; // 4 cubes tall, each 2 units
        float wallWidth = 4 * 2f * 90; // 4 cubes wide, each 2 units

        // Define maze structure: 1 represents a wall, 0 represents a path
        int[][] mazeLayout = {
                {1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1}
        };

        float startX = -10 * wallWidth / 2f + 2100; // Center the maze
        float startZ = -10 * wallWidth / 2f + 3000;

        for (int z = 0; z < mazeLayout.length; z++) {
            for (int x = 0; x < mazeLayout[z].length; x++) {
                if (mazeLayout[z][x] == 1) {
                    float posX = startX + x * wallWidth;
                    float posZ = startZ + z * wallWidth;
                    sceneManager.addSceneObject(new Wall(
                            new Point4f(posX, 90, posZ, 0),
                            new Point4f(0, 0, 0, 0),
                            new Vector4f(150f, 150f, 150f, 0),
                            textures
                    ));
                }
            }
        }
    }


    public static void updateScene(SceneManager sceneManager, HashMap textures) {
        // add 3 jumping Pyramid Head holding weapons if it's dark now
        sceneManager.addSceneObject(new NPC2(
                new Point4f(-800, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(-400, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(0, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(400, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(800, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(1200, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(1600, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(2000, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
        sceneManager.addSceneObject(new NPC2(
                new Point4f(2400, 130, -3000, 0),
                new Point4f(0, 0, 0, 0),
                new Vector4f(90, 90, 90, 0),
                textures
        ));
    }


    public static void initBackground(SceneManager backgroundManager, HashMap textures) {
        backgroundManager.addSceneObject(new Ground(
                new Point4f(0, 0, 0, 0),
                new Point4f(0, -1, 0, 0),
                new Vector4f(5000f, 1f, 30000f, 0),
                textures
        ));

        if (isDarkMode) {
            backgroundManager.remove(backgroundManager.getSceneObjects().get(1));
            backgroundManager.addSceneObject(new BackgroundBoxDarkMode(
                    new Point4f(0, 0, 0, 0),
                    new Point4f(0, 0, 0, 0),
                    new Vector4f(30000f, 30000f, 30000f, 0),
                    textures
            ));
        } else {
            backgroundManager.addSceneObject(new BackgroundBox(
                    new Point4f(0, 0, 0, 0),
                    new Point4f(0, 0, 0, 0),
                    new Vector4f(30000f, 30000f, 30000f, 0),
                    textures
            ));
        }
    }


    public static void drawBackground(SceneManager backgroundManager, Integer delta) {
        backgroundManager.drawAll(new IDrawListener() {
            @Override
            public void beforeEachDraw(SceneObject object) {
                GL11.glPushMatrix();
            }

            @Override
            public void afterEachDraw(SceneObject object) {
                GL11.glPopMatrix();
            }
        }, delta);
    }


    public static void drawScene(SceneManager sceneManager, Integer delta) {
        sceneManager.drawAll(new IDrawListener() {
            @Override
            public void beforeEachDraw(SceneObject object) {
                GL11.glPushMatrix();
            }

            @Override
            public void afterEachDraw(SceneObject object) {
                GL11.glPopMatrix();
            }
        }, delta);
    }

    public static void handleKeyPress(int key) {
        // if user press B, switch mode
        if (key == Keyboard.KEY_B) {
            isDarkMode = !isDarkMode;
            initBackground(Main.backgroundManager, Engine.getTextures()); // refresh the background
            if (isDarkMode) {
                updateScene(Main.sceneManager, Engine.getTextures());
            } else {
                for (int i=0; i <9; i++) {
                    Main.sceneManager.remove(Main.sceneManager.getSceneObjects().getLast());
                }
            }
        }
    }
}
