import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private UserInfo currentUser;//stores player thats logged in
    private MainGame game;//stores current instance of game
    private GamePanel panel;//panel draws game

    public Main() {
     setTitle("Flint's Meatball Game");
      setSize(500, 700);//wdinow size
      setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);//center window on screen

        UserInfo.loadUsers(); // load saved users
        showLoginScreen();

        setVisible(true);//make window visible
    }

   
    private void showLoginScreen() {
        JPanel loginPanel = new JPanel();//new panel to hold login elements
        loginPanel.setLayout(null); 

        // Username
       JLabel userLabel = new JLabel("Username:");
      userLabel.setBounds(50, 50, 100, 25);
        loginPanel.add(userLabel);//add label to panel

        JTextField usernameField = new JTextField();//textbox for userinput
      usernameField.setBounds(150, 50, 150, 25);//place next to label
        loginPanel.add(usernameField);

        // Password
  JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 25);
       loginPanel.add(passLabel);

        JPasswordField passwordField = new JPasswordField();//hides typed text like a real pass
      passwordField.setBounds(150, 100, 150, 25);
        loginPanel.add(passwordField);

        // Buttons
        JButton loginButton = new JButton("Login");
   loginButton.setBounds(50, 150, 100, 30);
      loginPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
      registerButton.setBounds(200, 150, 100, 30);
      loginPanel.add(registerButton);

        // Message
       JLabel message = new JLabel("", SwingConstants.CENTER);
      message.setBounds(50, 200, 250, 25);
      loginPanel.add(message);

      getContentPane().removeAll();
      setContentPane(loginPanel);
       revalidate();
        repaint();

        loginButton.addActionListener(e -> {
      String user = usernameField.getText();//read user
          String pass = new String(passwordField.getPassword());//read pass
           UserInfo u = UserInfo.login(user, pass);//try login
          if (u != null) {
              currentUser = u;//if logins success then save  user
                showStartScreen();
            } else {
                message.setText("Login failed!");//if logins failed
            }
        });

        registerButton.addActionListener(e -> {
     String user = usernameField.getText();
         String pass = new String(passwordField.getPassword());
          UserInfo.register(user, pass);//add user to list and save
            message.setText("Registered! Now login.");
        });
    }

    private void showStartScreen() {  JPanel startPanel = new JPanel();
      startPanel.setLayout(null); // absolute positioning
        
        // Welcome label
       JLabel title = new JLabel("Welcome " + currentUser.getUsername());
        title.setBounds(50, 50, 300, 30);
       startPanel.add(title);

        // Start Game button
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(100, 150, 150, 30);
        startPanel.add(startButton);

      getContentPane().removeAll();
  setContentPane(startPanel);
      revalidate();
      repaint();

        startButton.addActionListener(e -> startGame());
    }

    private void startGame() {
   game = new MainGame();//create new game instance
     panel = new GamePanel(game, this, currentUser);//panel handles drawinf & input

  getContentPane().removeAll();//clear prev panel
      setContentPane(panel);//show game panel
      revalidate();
       repaint();
      panel.requestFocusInWindow();
    }

    public void showGameOver(int score) {
  currentUser.updateHighScore(score);//save new highscore if applciable
     currentUser.incrementGamesPlayed();//count game played
      UserInfo.saveUsers();

      JPanel gameOverPanel = new JPanel();
      gameOverPanel.setLayout(null);

        // Game over label and score
     JLabel gameOverLabel = new JLabel("Game Over! Score: " + score);
      gameOverLabel.setBounds(50, 50, 300, 30);
      gameOverPanel.add(gameOverLabel);

        // Restart button
  JButton restart = new JButton("Back to Start");
     restart.setBounds(100, 150, 150, 30);
        gameOverPanel.add(restart);

       getContentPane().removeAll();//remove game panel
      setContentPane(gameOverPanel);//show gameoverpanel
       revalidate();
       repaint();

       restart.addActionListener(e -> showStartScreen());//retrusn to the start of game
    }

    public static void main(String[] args) {
        new Main();
    }//start program by creating a main instacne
}
