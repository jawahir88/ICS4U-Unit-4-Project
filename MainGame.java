import java.util.ArrayList;

public class MainGame {
    Player flint;//player controled by user
    ArrayList<FallingObj> foods;//list of falling objs onscreen
   objRandomizer randomizer;//randomly generates objs
    int score;//current player score
    boolean gameOver;//checks if games over t or f

    public MainGame() {
        flint = new Player(225, 500);//start player near bottom
       foods = new ArrayList<>();//starts woth no meatballs falling
    randomizer = new objRandomizer();//create obj spawner object
        score = 0;//score begins at zero
       gameOver = false;//game initalluy begins in progress
    }

    public void update() {//called every frame
        if (gameOver) return;//stop updating if the games over

       flint.update();

        FallingObj food = randomizer.randomizeFood();
        if (food != null)
            foods.add(food);//add obj possibly

        for (int i = 0; i < foods.size(); i++) {
            FallingObj f = foods.get(i);
            f.update(); //move object down the screen
            
            if (flint.collision(f)) {
           score += f.getPointValue(); //add or remove points  based on good/bad obj
            foods.remove(i);//remove obj after collision

            if (score < 0)
                    gameOver = true;// if score reaches 0 end game
            }

           if (f.y > 700)//if obj reaches bottom without collision get rid of it
                foods.remove(i);
        }
    }
}
