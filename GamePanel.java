import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//main canvas of game and handles timer, input, draws everything
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    MainGame game;//holds all the objects
    Timer timer;//redraws the game and updates
    Main frame;//refers to main wdinow and shows gameover when needed
    UserInfo user;// current player
    private Image backgroundImage; // new: background image

    public GamePanel(MainGame g, Main f, UserInfo u) {
 game = g;//store game logic
 frame = f;//store frame
user = u;//store info of user
setFocusable(true);//allows key input
addKeyListener(this);//listens to keys to move player
        // Load background image (can be PNG or JPEG)
ImageIcon icon = new ImageIcon("2bde0eede62b9d8b015ac4d450c37dae.jpg"); // change to your file name
 backgroundImage = icon.getImage();
timer = new Timer(16, this); // ~60 frames per sec (16millisecs)
 timer.start();
    }

   @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);//clears panel before drawing

    // draw background image 
     if (backgroundImage != null) {
          g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }//draw bg picture across the full panel

    // draw player
   game.flint.draw(g);

    // draw falling objects and uses polymorphism-***
    for (FallingObj f : game.foods)
        f.draw(g);

    // Draw score
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 20));
    g.drawString("Score: " + game.score, 20, 30);
}


    @Override
    public void actionPerformed(ActionEvent e) {
           game.update();//move objs, check collosion,update score
       if (game.gameOver) {
           timer.stop();
            SwingUtilities.invokeLater(() -> frame.showGameOver(game.score));
        }// if games over stop timer and switch frame to gameover 
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // move left if left is clikced
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
              game.flint.moveLeft();
     // move right if left is clikced

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            game.flint.moveRight();
    }
   @Override
    public void keyReleased(KeyEvent e) {
        game.flint.stop();
    }// when the user lets go of key then stop player as it is

    @Override
    public void keyTyped(KeyEvent e) {}
}
