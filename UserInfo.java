import java.util.ArrayList;
import java.io.*;

public class UserInfo {
private String username;
 private String password;
  private int highScore;
   private int totalGamesPlayed;

   public static ArrayList<UserInfo> users = new ArrayList<>();

    public UserInfo(String user, String pass) {
       username = user;
    password = pass;
       highScore = 0;
    totalGamesPlayed = 0;
    }

    public String getUsername() {
    return username;
    }

    public int getHighScore() {
       return highScore;
    }

    public void updateHighScore(int score) {
    if (score > highScore) {//only update if new scores higher
           highScore = score;
        }
    }

    public void incrementGamesPlayed() {
       totalGamesPlayed++;
    }

    public static void register(String user, String pass) {
        users.add(new UserInfo(user, pass));//create new user objecg and add to list
      saveUsers();//save info to file
    }

    public static UserInfo login(String user, String pass) {
        for (UserInfo u : users) {
         if (u.username.equals(user) && u.password.equals(pass)) {
          return u;//return maytching user based on input info
            }
        }
        return null;
    }

    public static void saveUsers() {
        try (PrintWriter out = new PrintWriter(new FileWriter("users.txt"))) {
            for (UserInfo u : users) {
      out.println(u.username + "," + u.password + "," +
                           u.highScore + "," + u.totalGamesPlayed);
            }//save data with user first, then pass, highscore, and fames played
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static void loadUsers() {
        users.clear();//clears ecisitng list before loading
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
               String[] data = line.split(",");
              UserInfo u = new UserInfo(data[0], data[1]);
             u.highScore = Integer.parseInt(data[2]);
    u.totalGamesPlayed = Integer.parseInt(data[3]);
             users.add(u);
            }
        } catch (IOException e) {
            // file may not exist first run
        }
    }
}
