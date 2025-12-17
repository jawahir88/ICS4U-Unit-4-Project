public abstract class FallingObj extends GameObject {//cant directly make objects
    protected int fallSpeed;//speed at what obj falls
   protected int pointValue;// points from catching objectzs

    public FallingObj(int x, int size, int speed, int points) {
        super(x, -size, size, size);//to start above the screen
        fallSpeed = speed;
        pointValue = points;
    }
    @Override
    public void update() {
        y += fallSpeed;//y cooridnate of the object is same as falling speed(acts like gravity's pull)
    }
            public int getPointValue() {
          return pointValue;//get point value for scoring
    }
}
