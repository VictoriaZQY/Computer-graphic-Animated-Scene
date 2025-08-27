package Scene.Objects;

import Scene.base.SceneObject;
import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Utils;
import base.GraphicsObjects.Vector4f;
import base.objects3D.DisplayListCylinder;
import base.objects3D.DisplayListShadow;
import base.objects3D.DisplayListTetrahedron;
import base.objects3D.DisplayListTexSphere;
import main.Engine;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glRotatef;

public class NPC extends SceneObject {
    public static int angle_target = 0;
    public static int angle = 0;
    // basic colours
    static float[] black = {0.0f, 0.0f, 0.0f, 1.0f};
    static float[] white = {1.0f, 1.0f, 1.0f, 1.0f};
    static float[] grey = {0.5f, 0.5f, 0.5f, 1.0f};
    static float[] spot = {0.1f, 0.1f, 0.1f, 0.5f};
    // primary colours
    static float[] red = {1.0f, 0.0f, 0.0f, 1.0f};
    static float[] green = {0.0f, 1.0f, 0.0f, 1.0f};
    static float[] blue = {0.0f, 0.0f, 1.0f, 1.0f};
    // secondary colours
    static float[] yellow = {1.0f, 1.0f, 0.0f, 1.0f};
    static float[] magenta = {1.0f, 0.0f, 1.0f, 1.0f};
    static float[] cyan = {0.0f, 1.0f, 1.0f, 1.0f};
    // other colours
    static float[] orange = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float[] brown = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float[] dkgreen = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float[] pink = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};
    DisplayListTexSphere s1 = new DisplayListTexSphere(0.5f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s2 = new DisplayListTexSphere(0.5f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s3 = new DisplayListTexSphere(0.25f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s4 = new DisplayListTexSphere(0.2f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s5 = new DisplayListTexSphere(0.2f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s6 = new DisplayListTexSphere(0.25f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s7 = new DisplayListTexSphere(0.2f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s8 = new DisplayListTexSphere(0.2f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s9 = new DisplayListTexSphere(0.25f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s10 = new DisplayListTexSphere(0.25f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s11 = new DisplayListTexSphere(0.3f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s12 = new DisplayListTexSphere(0.25f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s13 = new DisplayListTexSphere(0.25f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTexSphere s14 = new DisplayListTexSphere(0.3f, 16, 16, getTextures().get("blood_on_metal"));
    DisplayListTetrahedron t1 = new DisplayListTetrahedron(1.3f, 2f);
    DisplayListCylinder c1 = new DisplayListCylinder(0.15f, 0.7f, 16);
    DisplayListCylinder c2 = new DisplayListCylinder(0.15f, 0.7f, 16);
    DisplayListCylinder c3 = new DisplayListCylinder(0.1f, 0.7f, 16);
    DisplayListCylinder c4 = new DisplayListCylinder(0.15f, 0.7f, 16);
    DisplayListCylinder c5 = new DisplayListCylinder(0.1f, 0.7f, 16);
    DisplayListCylinder c6 = new DisplayListCylinder(0.15f, 0.7f, 16);
    DisplayListCylinder c7 = new DisplayListCylinder(0.15f, 0.7f, 16);
    DisplayListCylinder c8 = new DisplayListCylinder(0.15f, 0.7f, 16);
    DisplayListCylinder c9 = new DisplayListCylinder(0.15f, 0.7f, 16);

    DisplayListShadow shadow = new DisplayListShadow(3f, 32);

    private float delta;
    private Boolean isWalking = false;
    private long walkStartTime;
    private int stopCount = 0;
    private Boolean isJumping = false;
    private float jump_height = 0f;
    private float timePassed = Engine.getTimePassed();
    public NPC(Point4f origin, Point4f position, Vector4f scale) {
        super(origin, position, scale);
    }

    public NPC(Point4f origin, Point4f position, Vector4f scale, HashMap<String, Texture> textures) {
        super(origin, position, scale, textures);
    }

    public void walk() {
        stopCount = 0;
        if (isWalking == false) {
            isWalking = true;
            walkStartTime = Engine.getTimePassed();
        }
        if (isWalking) {
            this.delta = (Engine.getTimePassed() - walkStartTime) / 10000f;
        }
    }

    public void setAngle(float angle) {
        angle_target = (int) angle;
    }

    @Override
    public void draw(Integer frame_delta) {
        timePassed = Engine.getTimePassed() / 10000.0f;
        float speed = frame_delta / 160f;
        walk();

        // code to aid in animation
        float theta = (float) (timePassed * 2 * Math.PI);
        float posn_x = (float) Math.cos(theta);
        float posn_y = (float) Math.sin(theta);

        // Translate to circular position

        move(new Vector4f(0.0f, 0.0f, 0.0f, 0));
        glTranslatef(posn_x * 40f, 0.0f, posn_y * 40f);

        // Rotate to face the center of the circle
        float thetaDeg = (float) Math.toDegrees(theta); // Convert to degrees for rotation
        glRotatef(180-thetaDeg, 0.0f, 5.0f, 0.0f);

        Boolean GoodAnimation = true;

        float d = (float) (delta * 2 * Math.PI) * 8;
        //a variable for anim sync
        float LimbRotation = 0;
        //two animation
        if (GoodAnimation) {
            LimbRotation = (float) Math.sin(d) * 60;
        } else {
            LimbRotation = 0;
        }

        //Start to draw
        GL11.glPushMatrix();
        {
            // move to pelvis
            GL11.glTranslatef(0.0f, 0.5f, 0.0f);
            //set up TEXTURE
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                    GL11.GL_REPEAT);
            //bind a color texture
            Color.white.bind();

            getTextures().get("hand-drawn-blood-handprint").bind();
            //Enable TEXTURE

            //set texture Parameters
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

            s1.DrawTexSphere();
            //Draw finish Disable TEXTURE

            //  chest
            GL11.glColor3f(green[0], green[1], green[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.0f, 0.5f, 0.0f);           // move to chest
                GL11.glTexParameteri(
                        GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                        GL11.GL_REPEAT);
                Color.white.bind(); //bind color
                //bind texture
                getTextures().get("hand-drawn-blood-handprint").bind(); //set texture
                //Enable TEXTURE
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

                s2.DrawTexSphere();

                GL11.glRotatef((float) (LimbRotation * 0.2), 0.0f, 0.0f, 1.0f);

                // neck
                GL11.glColor3f(orange[0], orange[1], orange[2]); //set color
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange)); //set material render mode
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                    GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);

                    c1.DrawCylinder();


                    // head
                    GL11.glColor3f(red[0], red[1], red[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f, 0.0f, 1.0f);

                        GL11.glTexParameteri(
                                GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                GL11.GL_REPEAT);
                        Color.white.bind();
                        getTextures().get("hand-drawn-blood-handprint").bind(); //set texture
                        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                        t1.DrawTetrahedron();

                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();


                    // left shoulder
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    GL11.glPushMatrix();
                    {
                        GL11.glRotatef((float) (LimbRotation * 0.5), 0f, 1f, 0f);

                        GL11.glTranslatef(0.5f, 0.4f, 0.0f);
                        //set texture Parameter
                        GL11.glTexParameteri(
                                GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                GL11.GL_REPEAT);
                        Color.white.bind();
                        getTextures().get("blood_on_metal").bind();  //set texture
                        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                        s3.DrawTexSphere();

                        // left arm
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(20, 0f, 0f, 1f);
                            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);


                            GL11.glRotatef(LimbRotation, 1.0f, 0.0f, 0.0f);

                            c2.DrawCylinder();


                            // left elbow
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            GL11.glPushMatrix();
                            {
                                GL11.glRotatef((float) (LimbRotation * -0.5), 0f, 0f, 1f);
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                GL11.glTexParameteri(
                                        GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                        GL11.GL_REPEAT);
                                Color.white.bind();
                                getTextures().get("blood_on_metal").bind();
                                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                                s4.DrawTexSphere();

                                //left forearm
                                GL11.glColor3f(orange[0], orange[1], orange[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                                    c3.DrawCylinder();

                                    // left hand
                                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        GL11.glTexParameteri(
                                                GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                                GL11.GL_REPEAT);
                                        Color.white.bind();
                                        getTextures().get("blood_on_metal").bind();
                                        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                                        s5.DrawTexSphere();
                                    }
                                    GL11.glPopMatrix();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();


                    // to chest
                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                    GL11.glPushMatrix();
                    {
                        GL11.glRotatef((float) (LimbRotation * 0.5), 0f, 1f, 0f);

                        GL11.glTranslatef(-0.5f, 0.4f, 0.0f); // move to right arm
                        GL11.glTexParameteri(
                                GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                GL11.GL_REPEAT);
                        Color.white.bind();
                        getTextures().get("blood_on_metal").bind();
                        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                        s6.DrawTexSphere();

                        // right arm
                        GL11.glColor3f(orange[0], orange[1], orange[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                        GL11.glPushMatrix();
                        {
                            GL11.glRotatef(-20, 0f, 0f, 1f);
                            GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);


                            GL11.glRotatef(-LimbRotation, 1.0f, 0.0f, 0.0f);

                            c4.DrawCylinder();


                            // right elbow
                            GL11.glColor3f(blue[0], blue[1], blue[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                            GL11.glPushMatrix();
                            {
                                GL11.glRotatef((float) (LimbRotation * -0.5), 0f, 0f, 1f);
                                GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                GL11.glTexParameteri(
                                        GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                        GL11.GL_REPEAT);
                                Color.white.bind();
                                getTextures().get("blood_on_metal").bind();
                                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                                s7.DrawTexSphere();

                                //right forearm
                                GL11.glColor3f(orange[0], orange[1], orange[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                    GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);

                                    c5.DrawCylinder();

                                    // right hand
                                    GL11.glColor3f(blue[0], blue[1], blue[2]);
                                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                    GL11.glPushMatrix();
                                    {
                                        GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                        GL11.glTexParameteri(
                                                GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                                GL11.GL_REPEAT);
                                        Color.white.bind();
                                        getTextures().get("blood_on_metal").bind();
                                        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                                        s8.DrawTexSphere();
                                    }
                                    GL11.glPopMatrix();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();


                // pelvis
                // left hip
                GL11.glColor3f(blue[0], blue[1], blue[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.5f, -0.2f, 0.0f);
                    GL11.glTexParameteri(
                            GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                            GL11.GL_REPEAT);
                    Color.white.bind();
                    getTextures().get("default_stone").bind();
                    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                    s9.DrawTexSphere();

                    // left high leg
                    GL11.glColor3f(orange[0], orange[1], orange[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef((float) -LimbRotation + 90, 1.0f, 0.0f, 0.0f);

                        c6.DrawCylinder();


                        // left knee
                        GL11.glColor3f(blue[0], blue[1], blue[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                            // make the calf move differently when the upper thigh moves in different directions
                            // if the thigh moves to the front, the calf can only rotate in a little angle or the leg will break;
                            // if the thigh moves to the rear, the calf will rotate more to show the running posture
                            float calfRotation = (LimbRotation < 0) ? (float) (LimbRotation / 1.2) : (LimbRotation / 10);
                            glRotatef(calfRotation, 1.0f, 0.0f, 0.0f);
                            GL11.glTexParameteri(
                                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                    GL11.GL_REPEAT);
                            Color.white.bind();
                            getTextures().get("default_stone").bind();
                            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                            s10.DrawTexSphere();

                            //left low leg
                            GL11.glColor3f(orange[0], orange[1], orange[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.0f);

                                c7.DrawCylinder();

                                // left foot
                                GL11.glColor3f(blue[0], blue[1], blue[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                    GL11.glTexParameteri(
                                            GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                            GL11.GL_REPEAT);
                                    Color.white.bind();
                                    getTextures().get("default_stone").bind();
                                    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                                    s11.DrawTexSphere();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();

                // pelvis
                // right hip
                GL11.glColor3f(blue[0], blue[1], blue[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-0.5f, -0.2f, 0.0f);
                    GL11.glTexParameteri(
                            GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                            GL11.GL_REPEAT);
                    Color.white.bind();
                    getTextures().get("default_stone").bind();
                    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                    s12.DrawTexSphere();

                    // right high leg
                    GL11.glColor3f(orange[0], orange[1], orange[2]);
                    GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                    GL11.glPushMatrix();
                    {
                        GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                        glRotatef((float) LimbRotation + 90, 1.0f, 0.0f, 0.0f);

                        c8.DrawCylinder();


                        // right knee
                        GL11.glColor3f(blue[0], blue[1], blue[2]);
                        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                        GL11.glPushMatrix();
                        {
                            GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                            // make the calf move differently when the upper thigh moves in different directions
                            // if the thigh moves to the front, the calf can only rotate in a little angle or the leg will break;
                            // if the thigh moves to the rear, the calf will rotate more to show the running posture
                            float calfRotation = (LimbRotation > 0) ? (float) (LimbRotation / 1.2) : (LimbRotation / 10) ;
                            glRotatef(-calfRotation, 1.0f, 0.0f, 0.0f);
                            GL11.glTexParameteri(
                                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                    GL11.GL_REPEAT);
                            Color.white.bind();
                            getTextures().get("default_stone").bind();
                            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                            s13.DrawTexSphere();

                            //right low leg
                            GL11.glColor3f(orange[0], orange[1], orange[2]);
                            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
                            GL11.glPushMatrix();
                            {
                                GL11.glTranslatef(0.0f, 0.0f, 0.0f);
                                c9.DrawCylinder();

                                // right foot
                                GL11.glColor3f(blue[0], blue[1], blue[2]);
                                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
                                GL11.glPushMatrix();
                                {
                                    GL11.glTranslatef(0.0f, 0.0f, 0.75f);
                                    GL11.glTexParameteri(
                                            GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                                            GL11.GL_REPEAT);
                                    Color.white.bind();
                                    getTextures().get("default_stone").bind();
                                    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

                                    s14.DrawTexSphere();
                                }
                                GL11.glPopMatrix();
                            }
                            GL11.glPopMatrix();
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();

        }
    }
}