import java.awt.*;

public class goodObj extends FallingObj {
public goodObj(int x) {
        super(x, 30, 4, 5); //5  points increment, 30 size, 4 speed
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(139, 69, 19)); // brown color
       g.fillOval(x, y, width, height);//draw as a circle
    }
}
