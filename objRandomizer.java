import java.util.Random;
//determines when good and bad objs appear
public class objRandomizer {
    private Random random = new Random();
   private int timer = 0;//counts frames until next obj comes up
private int spawnRate = 30;//30 frames between obj

    public FallingObj randomizeFood() {
         timer++;//timer increments each frame
        //checks if enough frames have passed to spawn new obj
    if (timer >= spawnRate) {
           timer = 0;//reset timer
     int x = random.nextInt(470);//width-obj size to ensure objs dont fall off screem

           if (random.nextInt(100) < 70)// chance for good obj
       return new goodObj(x);
            else
                return new badObj(x);//rest chance for bad obj
        }   return null;
    }
//decreasing spawn rate time to increase dificulty
    public void increaseDifficulty() {
        if (spawnRate > 10)
            spawnRate--;
    }
}
