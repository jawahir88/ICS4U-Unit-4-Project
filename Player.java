import java.awt.*;
import javax.swing.*;

public class Player extends GameObject {
    private int speed;
    private Image flintImage;

    // Constructor
    public Player(int x, int y) {
      super(x, y, 50, 60); // width=50, height=60
         speed = 0;

        // Load image
  ImageIcon icon = new ImageIcon("b1b22c43b205b4cd7162e87634c781f9.png");
flintImage = icon.getImage();
    }
    // Movement methods
       public void moveLeft() {
        speed = -6;
    }
    public void moveRight() {
        speed = 6;
    }
     public void stop() {
        speed = 0;
    }

    // Update player position
    @Override
    public void update() {
        x += speed;

        // Keep player within bounds (assuming game width = 500)
        if (x < 0) x = 0;
        if (x > 500 - width) x = 500 - width;
    }

    // Draw player
    @Override
    public void draw(Graphics g) {
        if (flintImage != null) {
            g.drawImage(flintImage, x, y, width, height, null);
         } else {
              g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }
}
