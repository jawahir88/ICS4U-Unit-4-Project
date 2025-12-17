import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    protected int x, y;//pos of object
    protected int width, height;//size of object

    public GameObject(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }
    public abstract void update();//subclas must implement
    public abstract void draw(Graphics g); //subclas must implement
    
//check if this object collides with another object
    public boolean collision(GameObject other) {
        Rectangle r1 = new Rectangle(x, y, width, height);
        Rectangle r2 = new Rectangle(other.x, other.y, other.width, other.height);
        return r1.intersects(r2);
    }
}
//crearing "rectangles" around the 2 objects that are checked for collision to make it easier and see if the rectangles overlap
