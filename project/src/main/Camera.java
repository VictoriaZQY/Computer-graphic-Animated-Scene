package main;

import Scene.Objects.Player;
import base.GraphicsObjects.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Camera {
    private static final int mouseSpeed = 1;
    private static final FloatBuffer noAmbient = BufferUtils.createFloatBuffer(4);
    private static final FloatBuffer diffuse = BufferUtils.createFloatBuffer(4);
    private static final FloatBuffer spec = BufferUtils.createFloatBuffer(4);
    private static final FloatBuffer direction = BufferUtils.createFloatBuffer(4);
    public static int maxLookUp = 30;
    public static int maxLookDown = -20;
    public static int OrthoNumber = 0; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
    public static Vector3f rotation = new Vector3f(10, 180, 0);
    public static Vector4f position = new Vector4f(0, 0, 0, 0);
    public static float rotationX = -20;
    public static Boolean loading_finished = false;
    public static FloatBuffer lightPos;
    private static Vector4f inital_camera_position = new Vector4f();
    private final float rotationY = 0;
    private final float rotationZ = 0;
    private final boolean MouseOnepressed = true;
    private final boolean dragMode = false;


    public Camera() {
    }

    public static void setPosition(Vector4f position) {
        Camera.position = position;
    }

    public void setCamera(Vector4f v) {
        inital_camera_position = v;
        noAmbient.put(new float[]{0.2f, 0.2f, 0.2f, 1.0f});
        noAmbient.rewind();
        diffuse.put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        diffuse.rewind();
        spec.put(new float[]{1.0f, 1.0f, 1.0f, 1.0f});
        spec.rewind();
        direction.put(new float[]{0f, 0f, -1f, 0});
        direction.rewind();
        lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(0).put(3000f).put(0).put(1).flip();
        GL11.glLight(GL11.GL_LIGHT7, GL_POSITION, lightPos);
        GL11.glLight(GL11.GL_LIGHT7, GL_DIFFUSE, diffuse);
        GL11.glLight(GL11.GL_LIGHT7, GL_SPECULAR, spec);
        GL11.glLight(GL11.GL_LIGHT7, GL_SPOT_DIRECTION, direction);
        GL11.glLightf(GL11.GL_LIGHT7, GL_SPOT_CUTOFF, 45);

    }


    public void updatePosition() {
        glTranslatef(inital_camera_position.x, inital_camera_position.y, inital_camera_position.z);
        glRotatef(rotation.x, 1, 0, 0);
        glRotatef(rotation.y, 0, 1, 0);
        glRotatef(-rotation.z, 0, 0, 1);
        glTranslatef(position.x, -position.y, position.z);
        Main.engine.setOrtho(Camera.OrthoNumber);
    }

    public void update() {
        float speed = Player.frame_delta / 1.6f;
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            Vector4f move = new Vector4f();
            move = move.PlusVector(new Vector4f(0, 0, -speed, 0));
            float angle = rotation.y - 180;
            float new_x = (float) (move.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (move.length() * Math.cos(Math.toRadians(angle)));
            move.x = new_x;
            move.z = -new_z;
            position = position.PlusVector(move);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            Vector4f move = new Vector4f();
            move = move.PlusVector(new Vector4f(speed, 0, 0, 0));
            float angle = 180 - rotation.y + 90;
            float new_x = (float) (move.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (move.length() * Math.cos(Math.toRadians(angle)));
            move.x = new_x;
            move.z = new_z;
            position = position.PlusVector(move);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            Vector4f move = new Vector4f();
            move = move.PlusVector(new Vector4f(0, 0, speed, 0));
            float angle = rotation.y - 180;
            float new_x = (float) (move.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (move.length() * Math.cos(Math.toRadians(angle)));
            move.x = -new_x;
            move.z = new_z;
            position = position.PlusVector(move);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            Vector4f move = new Vector4f();
            move = move.PlusVector(new Vector4f(-speed, 0, 0, 0));
            float angle = 180 - rotation.y - 90;
            float new_x = (float) (move.length() * Math.sin(Math.toRadians(angle)));
            float new_z = (float) (move.length() * Math.cos(Math.toRadians(angle)));
            move.x = new_x;
            move.z = new_z;
            position = position.PlusVector(move);
        }


        if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
            if (position.y < 0) {

            } else {
                position.y -= speed;
            }
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            position.y += speed;
        }

    }

    public void update_mouse() {

        int WheelPosition = Mouse.getDWheel();
        int MouseDX = Mouse.getDX();
        int MouseDY = Mouse.getDY();


        if (WheelPosition > 0) {
            OrthoNumber += 10;
        }
        if (WheelPosition < 0) {
            OrthoNumber -= 10;
        }

        while (Mouse.next()) {
            if (Mouse.isButtonDown(0)) {
                Mouse.setGrabbed(true);
            }
            if (Mouse.isButtonDown(1)) {
                Mouse.setGrabbed(false);
            }
        }

        if (Mouse.isGrabbed()) {
            float mouseDX = MouseDX * mouseSpeed * 0.16f;
            float mouseDY = MouseDY * mouseSpeed * 0.16f;
            if (rotation.y + mouseDX >= 360) {
                rotation.y = rotation.y + mouseDX - 360;
            } else if (rotation.y + mouseDX < 0) {
                rotation.y = 360 - rotation.y + mouseDX;
            } else {
                rotation.y += mouseDX;
            }
            if (rotation.x - mouseDY >= maxLookDown && rotation.x - mouseDY <= maxLookUp) {
                rotation.x += -mouseDY;
            } else if (rotation.x - mouseDY < maxLookDown) {
                rotation.x = maxLookDown;
            } else if (rotation.x - mouseDY > maxLookUp) {
                rotation.x = maxLookUp;
            }
        }
        Player.angle_target = 180 - (int) rotation.y;
    }
}
