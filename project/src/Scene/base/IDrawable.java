package Scene.base;

public interface IDrawable {
    void draw(IDrawListener listener, Integer delta);

    void draw(Integer delta);

    void drawShadow();
}
