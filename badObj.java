import java.awt.*;
public class badObj extends FallingObj {

    public badObj(int x) {
        super(x, 30, 5, -30); // -30 points and inherits from falling obj
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
       g.fillOval(x, y, width, height);
        g.setColor(Color.GREEN);
        g.drawString("X", x + 10, y + 20);
    }
}
