package main;

import Scene.Scene;
import Scene.base.SceneManager;
import base.GraphicsObjects.Vector4f;
import base.RenderProgramStatement;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;

import static main.TextureLoader.loadTexture;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPointSize;


public class Main {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 800;
    private static final ArrayList<Vector4f> key_positions = new ArrayList<>();
    private static final ArrayList<Vector4f> key_rotations = new ArrayList<>();
    public static Engine engine;
    public static SceneManager sceneManager = new SceneManager();
    public static SceneManager backgroundManager = new SceneManager();
    public static Camera camera = new Camera();

    public static void main(String[] args) {
        engine = new Engine(WIDTH, HEIGHT);
        glPointSize(8);
        engine.init();
        engine.setOrtho(Camera.OrthoNumber);
        engine.enterModelView();
        engine.initTimer();


        camera.setCamera(new Vector4f(
                0, 2800, 0, 0
        ));
        Display.setTitle("Loading....... Scene: Init");
        Scene.initScene(sceneManager, Engine.getTextures());
        Scene.initBackground(backgroundManager, Engine.getTextures());
        Display.setTitle("Loading....... Scene: Ok.");

        // the welcome board side
        key_positions.add(new Vector4f(0, 7000, 0, 2000));
        key_rotations.add(new Vector4f(0, 0, 0, 2000));

        // turn left
        key_positions.add(new Vector4f(-1000, 4000, 0, 1000));
        key_rotations.add(new Vector4f(-10, -90, 0, 1000));

        // turn right
        key_positions.add(new Vector4f(-1000, 4000, 0, 1000));
        key_rotations.add(new Vector4f(-10, 90, 0, 1000));

        // the door side
        key_positions.add(new Vector4f(0, 5000, 0, 1000));
        key_rotations.add(new Vector4f(-20, 180, 0, 1000));

        // go back a little
        key_positions.add(new Vector4f(0, 5000, 8000, 1000));
        key_rotations.add(new Vector4f(-20, 180, 0, 1000));

        // the ground overview
        key_positions.add(new Vector4f(0, 5000, 6000, 1000));
        key_rotations.add(new Vector4f(30, 180, 0, 1000));

        // turn back
        key_positions.add(new Vector4f(0, 5000, 6000, 1000));
        key_rotations.add(new Vector4f(30, 0, 0, 1000));

        // a corner
        key_positions.add(new Vector4f(-3000, 1000, 3000, 2000));
        key_rotations.add(new Vector4f(40, -180, 0, 2000));

        // the player view
        key_positions.add(new Vector4f(0, 0, 0, 5000));
        key_rotations.add(new Vector4f(10, 180, 0, 5000));

        // set the camera positions
        Runnable cameraRunnable = new Runnable() {
            @Override
            public void run() {
                Camera.position = new Vector4f(
                        0, 12000, 0, 0
                );
                Camera.rotation = new Vector3f(10, 0, 0);
                for (int i = 0; i < key_positions.size(); i++) {
                    Vector4f key_position = key_positions.get(i);
                    Vector4f key_rotation = key_rotations.get(i);
                    Vector4f origin_position = new Vector4f(Camera.position.x, Camera.position.y, Camera.position.z, Camera.position.a);
                    Vector3f origin_rotation = new Vector3f(Camera.rotation.x, Camera.rotation.y, Camera.rotation.z);
                    Vector4f Current_position = Camera.position;
                    Vector3f Current_rotation = Camera.rotation;
                    float position_x_distance = key_position.x - Current_position.x;
                    float position_y_distance = key_position.y - Current_position.y;
                    float position_z_distance = key_position.z - Current_position.z;
                    float rotation_x_distance = key_rotation.x - Current_rotation.x;
                    float rotation_y_distance = key_rotation.y - Current_rotation.y;
                    float rotation_z_distance = key_rotation.z - Current_rotation.z;
                    float position_x_distance_step = position_x_distance / key_position.a;
                    float position_y_distance_step = position_y_distance / key_position.a;
                    float position_z_distance_step = position_z_distance / key_position.a;
                    float rotation_x_distance_step = rotation_x_distance / key_position.a;
                    float rotation_y_distance_step = rotation_y_distance / key_position.a;
                    float rotation_z_distance_step = rotation_z_distance / key_position.a;
                    float count = 0f;
                    while (count < key_position.a) {

                        Current_position.x = (float) (origin_position.x + position_x_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
                        Current_position.y = (float) (origin_position.y + position_y_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
                        Current_position.z = (float) (origin_position.z + position_z_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));

                        Current_rotation.x = (float) (origin_rotation.x + rotation_x_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
                        Current_rotation.y = (float) (origin_rotation.y + rotation_y_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
                        Current_rotation.z = (float) (origin_rotation.z + rotation_z_distance_step * count * Math.sin(count / key_position.a * Math.PI / 2));
                        count += 1;
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Camera.loading_finished = true;
            }
        };
        new Thread(cameraRunnable).start();

        while (!Display.isCloseRequested()) {
            glLoadIdentity();
            camera.updatePosition();
            camera.setCamera(new Vector4f(0, -300, -600, 0));
            engine.setLight();
            engine.render(new RenderProgramStatement() {
                @Override
                public void renderScene(int delta) {
                    Scene.drawScene(sceneManager, Integer.valueOf(delta));
                }

                @Override
                public void renderBackground(int delta) {
                    // if user press key B, re-draw the background
                    if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
                        Scene.handleKeyPress(Keyboard.KEY_B);
                    }
                    Scene.drawBackground(backgroundManager, delta);
                }
            });

            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                break;
            }
        }
        engine.close();
    }
}
