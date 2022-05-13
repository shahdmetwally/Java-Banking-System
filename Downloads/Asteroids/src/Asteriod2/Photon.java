package src.Asteriod2;

public class Photon extends Sprite {

    public Photon() {
        // Create shape for the photon sprite.
        super();
        shape.addPoint(1, 1);
        shape.addPoint(1, -1);
        shape.addPoint(-1, 1);
        shape.addPoint(-1, -1);
    }
}
