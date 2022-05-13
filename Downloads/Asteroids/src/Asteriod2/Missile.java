package src.Asteriod2;

public class Missile extends Sprite {

    public Missile() {
        super();
        shape.addPoint(0, -4);
        shape.addPoint(1, -3);
        shape.addPoint(1, 3);
        shape.addPoint(2, 4);
        shape.addPoint(-2, 4);
        shape.addPoint(-1, 3);
        shape.addPoint(-1, -3);
    }

}
