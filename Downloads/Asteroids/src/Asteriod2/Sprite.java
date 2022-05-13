package src.Asteriod2;
import java.awt.Polygon;
/******************************************************************************
 The Sprite class defines a game object, including it's shape,
 position, movement and rotation. It also can detemine if two objects collide.
 ******************************************************************************/
abstract class Sprite {
    // Fields:

    static int width;          // Dimensions of the graphics area.
    static int height;

    Polygon shape;             // Base sprite shape, centered at the origin (0,0).
    boolean active;            // Active flag.
    double  angle;             // Current angle of rotation.
    double  deltaAngle;        // Amount to change the rotation angle.
    double  x, y;              // Current position on screen.
    double  deltaX, deltaY;    // Amount to change the screen position.
    Polygon sprite;            // Final location and shape of sprite after
    // applying rotation and translation to get screen
    // position. Used for drawing on the screen and in
    // detecting collisions.

    // Constructors:

    public Sprite() {

        this.shape = new Polygon();
        this.active = false;
        this.angle = 0.0;
        this.deltaAngle = 0.0;
        this.x = 0.0;
        this.y = 0.0;
        this.deltaX = 0.0;
        this.deltaY = 0.0;
        this.sprite = new Polygon();
    }

    // Methods:

    public boolean advance() {

        boolean wrapped;

        // Update the rotation and position of the sprite based on the delta
        // values. If the sprite moves off the edge of the screen, it is wrapped
        // around to the other side and TRUE is returnd.

        this.angle += this.deltaAngle;
        if (this.angle < 0)
            this.angle += 2 * Math.PI;
        if (this.angle > 2 * Math.PI)
            this.angle -= 2 * Math.PI;
        wrapped = false;
        this.x += this.deltaX;
        if (this.x < -width / 2) {
            this.x += width;
            wrapped = true;
        }
        if (this.x > width / 2) {
            this.x -= width;
            wrapped = true;
        }
        this.y -= this.deltaY;
        if (this.y < -height / 2) {
            this.y += height;
            wrapped = true;
        }
        if (this.y > height / 2) {
            this.y -= height;
            wrapped = true;
        }

        return wrapped;
    }

    public void render() {

        int i;

        // Render the sprite's shape and location by rotating it's base shape and
        // moving it to it's proper screen position.

        this.sprite = new Polygon();
        for (i = 0; i < this.shape.npoints; i++)
            this.sprite.addPoint((int) Math.round(this.shape.xpoints[i] * Math.cos(this.angle) + this.shape.ypoints[i] * Math.sin(this.angle)) + (int) Math.round(this.x) + width / 2,
                    (int) Math.round(this.shape.ypoints[i] * Math.cos(this.angle) - this.shape.xpoints[i] * Math.sin(this.angle)) + (int) Math.round(this.y) + height / 2);
    }

    public boolean isColliding(Sprite s) {

        int i;

        // Determine if one sprite overlaps with another, i.e., if any vertice
        // of one sprite lands inside the other.

        for (i = 0; i < s.sprite.npoints; i++)
            if (this.sprite.contains(s.sprite.xpoints[i], s.sprite.ypoints[i]))
                return true;
        for (i = 0; i < this.sprite.npoints; i++)
            if (s.sprite.contains(this.sprite.xpoints[i], this.sprite.ypoints[i]))
                return true;
        return false;
    }
}
