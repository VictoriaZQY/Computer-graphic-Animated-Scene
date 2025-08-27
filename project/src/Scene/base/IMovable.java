package Scene.base;

import base.GraphicsObjects.Point4f;
import base.GraphicsObjects.Vector4f;


public interface IMovable {
    void setPosition(Point4f point);

    void move(Vector4f vector);
}
