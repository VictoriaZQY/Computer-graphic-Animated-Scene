package Scene.base;

import Scene.Objects.Player;
import base.GraphicsObjects.Point4f;

import java.util.ArrayList;

public class SceneManager {

    private ArrayList<SceneObject> sceneObjects = new ArrayList<>();

    // draw everything in the sceneObjects list
    public void drawAll(IDrawListener listener, Integer delta) {
        for (int i = 0; i < sceneObjects.size(); i++) {
            sceneObjects.get(i).draw(listener, delta);
        }
    }

    // draw the first thing in the sceneObjects list
    public void drawFirst(IDrawListener listener, Integer delta) {
        sceneObjects.get(0).draw(listener, delta);
    }

    // draw the last thing in the sceneObjects list
    public void drawLast(IDrawListener listener, Integer delta) {
        sceneObjects.get(sceneObjects.size() - 1).draw(listener, delta);
    }


    public void remove(SceneObject sceneObject) {
        sceneObjects.remove(sceneObject);
    }

    public void addSceneObject(SceneObject sceneObject) {
        sceneObjects.add(sceneObject);
    }

    public ArrayList<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    public void setSceneObjects(ArrayList<SceneObject> sceneObjects) {
        this.sceneObjects = sceneObjects;
    }

    public void clear() {
        sceneObjects.clear();
    }
}
