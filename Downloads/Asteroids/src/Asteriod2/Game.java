package src.Asteriod2;

/******************************************************************************
 Asteroids, Version 1.3

 Copyright 1998-2001 by Mike Hall.
 Please see http://www.brainjar.com for terms of use.

 Revision History:

 1.01, 12/18/1999: Increased number of active photons allowed.
 Improved explosions for more realism.
 Added progress bar for loading of sound clips.
 1.2,  12/23/1999: Increased frame rate for smoother animation.
 Modified code to calculate game object speeds and timer
 counters based on the frame rate so they will remain
 constant.
 Improved speed limit checking for ship.
 Removed wrapping of photons around screen and set a fixed
 firing rate.
 Added sprites for ship's thrusters.
 1.3,  01/25/2001: Updated to JDK 1.1.8.

 Usage:

 <applet code="Game.class" width=w height=h></applet>

 Keyboard Controls:

 S            - Start Game    P           - Pause Game
 Cursor Left  - Rotate Left   Cursor Up   - Fire Thrusters
 Cursor Right - Rotate Right  Cursor Down - Fire Retro Thrusters
 Spacebar     - Fire Cannon   H           - Hyperspace
 M            - Toggle Sound  D           - Toggle Graphics Detail

 ******************************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.applet.Applet;
import java.applet.AudioClip;

/******************************************************************************
 The Sprite class defines a game object, including it's shape,
 position, movement and rotation. It also can detemine if two objects collide.
 ******************************************************************************/

public class Game extends Applet implements Runnable, KeyListener {

    // Copyright information.

    String copyName = "Asteroids";
    String copyVers = "Version 1.3";
    String copyInfo = "Copyright 1998-2001 by Mike Hall";
    String copyLink = "http://www.brainjar.com";
    String copyText = copyName + '\n' + copyVers + '\n'
            + copyInfo + '\n' + copyLink;


    // Thread control variables.

    Thread loadThread;
    Thread loopThread;

    // Constants

    static final int DELAY = 20;             // Milliseconds between screen and
    static final int FPS   =                 // the resulting frame rate.
            Math.round(1000 / DELAY);

    static final int MAX_SHOTS =  8;          // Maximum number of sprites
    static final int MAX_ROCKS =  8;          // for photons, asteroids and
    static final int MAX_SCRAP = 40;          // explosions.

    static final int SCRAP_COUNT  = 2 * FPS;  // Timer counter starting values
    static final int HYPER_COUNT  = 3 * FPS;  // calculated using number of
    static final int MISSLE_COUNT = 4 * FPS;  // seconds x frames per second.
    static final int STORM_PAUSE  = 2 * FPS;

    static final int    MIN_ROCK_SIDES =   6; // Ranges for asteroid shape, size
    static final int    MAX_ROCK_SIDES =  16; // speed and rotation.
    static final int    MIN_ROCK_SIZE  =  20;
    static final int    MAX_ROCK_SIZE  =  40;
    static final double MIN_ROCK_SPEED =  40.0 / FPS;
    static final double MAX_ROCK_SPEED = 240.0 / FPS;
    static final double MAX_ROCK_SPIN  = Math.PI / FPS;

    static final int MAX_SHIPS = 3;           // Starting number of ships for
    // each game.
    static final int UFO_PASSES = 3;          // Number of passes for flying
    // saucer per appearance.

    // Ship's rotation and acceleration rates and maximum speed.

    static final double SHIP_ANGLE_STEP = Math.PI / FPS;
    static final double SHIP_SPEED_STEP = 15.0 / FPS;
    static final double MAX_SHIP_SPEED  = 1.25 * MAX_ROCK_SPEED;

    static final int FIRE_DELAY = 50;         // Minimum number of milliseconds
    // required between photon shots.

    // Probablility of flying saucer firing a missle during any given frame
    // (other conditions must be met).

    static final double MISSLE_PROBABILITY = 0.45 / FPS;

    static final int BIG_POINTS    =  25;     // Points scored for shooting
    static final int SMALL_POINTS  =  50;     // various objects.
    static final int UFO_POINTS    = 250;
    static final int MISSLE_POINTS = 500;

    // Number of points the must be scored to earn a new ship or to cause the
    // flying saucer to appear.

    static final int NEW_SHIP_POINTS = 5000;
    static final int NEW_UFO_POINTS  = 2750;

    // Background stars.

    int     numStars;
    Point[] stars;

    // Game data.

    int score;
    int highScore;
    int newShipScore;
    int newUfoScore;

    // Flags for game state and options.

    boolean loaded = false;
    boolean paused;
    boolean playing;
    boolean sound;
    boolean detail;

    // Key flags.

    boolean left  = false;
    boolean right = false;
    boolean up    = false;
    boolean down  = false;

    // Sprite objects.

    Ship ship;
    Ufo    ufo;
    Missile missile;
    Photon[] photons    = new Photon[MAX_SHOTS];
    Asteroid[] asteroids  = new Asteroid[MAX_ROCKS];
    Explosion[] explosions = new Explosion[MAX_SCRAP];

    // Ship data.

    int shipsLeft;       // Number of ships left in game, including current one.
    int shipCounter;     // Timer counter for ship explosion.
    int hyperCounter;    // Timer counter for hyperspace.

    // Photon data.

    int   photonIndex;    // Index to next available photon sprite.
    long  photonTime;     // Time value used to keep firing rate constant.

    // Flying saucer data.

    int ufoPassesLeft;    // Counter for number of flying saucer passes.
    int ufoCounter;       // Timer counter used to track each flying saucer pass.

    // Missile data.

    int missileCounter;    // Counter for life of missle.

    // Asteroid data.

    boolean[] asteroidIsSmall = new boolean[MAX_ROCKS];    // Asteroid size flag.
    int       asteroidsCounter;                            // Break-time counter.
    double    asteroidsSpeed;                              // Asteroid speed.
    int       asteroidsLeft;                               // Number of active asteroids.

    // Explosion data.

    int[] explosionCounter = new int[MAX_SCRAP];  // Time counters for explosions.
    int   explosionIndex;                         // Next available explosion sprite.

    // Sound clips.

    AudioClip crashSound;
    AudioClip explosionSound;
    AudioClip fireSound;
    AudioClip missileSound;
    AudioClip saucerSound;
    AudioClip thrustersSound;
    AudioClip warpSound;

    // Flags for looping sound clips.

    boolean thrustersPlaying;
    boolean saucerPlaying;
    boolean missilePlaying;

    // Counter and total used to track the loading of the sound clips.

    int clipTotal   = 0;
    int clipsLoaded = 0;

    // Off screen image.

    Dimension offDimension;
    Image     offImage;
    Graphics  offGraphics;

    // Data for the screen font.

    Font font      = new Font("Helvetica", Font.BOLD, 12);
    FontMetrics fm = getFontMetrics(font);
    int fontWidth  = fm.getMaxAdvance();
    int fontHeight = fm.getHeight();

    public Game(){

    }

    public String getAppletInfo() {

        // Return copyright information.

        return(copyText);
    }

    public void init() {

        Dimension d = getSize();
        int i;

        // Display copyright information.

        System.out.println(copyText);

        // Set up key event handling and set focus to applet window.

        addKeyListener(this);
        requestFocus();

        // Save the screen size.

        Sprite.width = d.width;
        Sprite.height = d.height;

        // Generate the starry background.

        numStars = Sprite.width * Sprite.height / 5000;
        stars = new Point[numStars];
        for (i = 0; i < numStars; i++)
            stars[i] = new Point((int) (Math.random() * Sprite.width), (int) (Math.random() * Sprite.height));

        // Create shape for the ship sprite.

        ship = new Ship();

        // Create shape for each photon sprites.

        for (i = 0; i < MAX_SHOTS; i++) {
            photons[i] = new Photon();
        }

        // Create UFO object

        ufo = new Ufo();

        // Create shape for the guided missle.

        missile = new Missile();

        // Create asteroid sprites.

        for (i = 0; i < MAX_ROCKS; i++)
            asteroids[i] = new Asteroid();

        // Create explosion sprites.

        for (i = 0; i < MAX_SCRAP; i++)
            explosions[i] = new Explosion();

        // Initialize game data and put us in 'game over' mode.

        highScore = 0;
        sound = true;
        detail = true;
        initGame();
        endGame();
    }

    public void initGame() {

        // Initialize game data and sprites.

        score = 0;
        shipsLeft = MAX_SHIPS;
        asteroidsSpeed = MIN_ROCK_SPEED;
        newShipScore = NEW_SHIP_POINTS;
        newUfoScore = NEW_UFO_POINTS;
        initShip();
        initPhotons();
        stopUfo();
        stopMissle();
        initAsteroids();
        initExplosions();
        playing = true;
        paused = false;
        photonTime = System.currentTimeMillis();
    }

    public void endGame() {

        // Stop ship, flying saucer, guided missle and associated sounds.

        playing = false;
        stopShip();
        stopUfo();
        stopMissle();
    }

    public void start() {

        if (loopThread == null) {
            loopThread = new Thread(this);
            loopThread.start();
        }
        if (!loaded && loadThread == null) {
            loadThread = new Thread(this);
            loadThread.start();
        }
    }

    public void stop() {

        if (loopThread != null) {
            loopThread.stop();
            loopThread = null;
        }
        if (loadThread != null) {
            loadThread.stop();
            loadThread = null;
        }
    }

    public void run() {
        long startTime;

        // Lower this thread's priority and get the current time.

        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        startTime = System.currentTimeMillis();

        // Run thread for loading sounds.

        if (!loaded && Thread.currentThread() == loadThread) {
            loadSounds();
            loaded = true;
            loadThread.stop();
        }

        // This is the main loop.

        while (Thread.currentThread() == loopThread) {

            if (!paused) {

                // Move and process all sprites.

                updateShip();
                updatePhotons();
                updateUfo();
                updateMissle();
                updateAsteroids();
                updateExplosions();

                // Check the score and advance high score, add a new ship or start the
                // flying saucer as necessary.

                if (score > highScore)
                    highScore = score;
                if (score > newShipScore) {
                    newShipScore += NEW_SHIP_POINTS;
                    shipsLeft++;
                }
                if (playing && score > newUfoScore && !ufo.active) {
                    newUfoScore += NEW_UFO_POINTS;
                    ufoPassesLeft = UFO_PASSES;
                    initUfo();
                }

                // If all asteroids have been destroyed create a new batch.

                if (asteroidsLeft <= 0)
                    if (--asteroidsCounter <= 0)
                        initAsteroids();
            }

            // Update the screen and set the timer for the next loop.

            repaint();
            try {
                startTime += DELAY;
                Thread.sleep(Math.max(0, startTime - System.currentTimeMillis()));
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }

    public void loadSounds() {

        // Load all sound clips by playing and immediately stopping them. Update
        // counter and total for display.

        try {
            crashSound     = getAudioClip(new URL(getCodeBase(), Sound.CRASH));
            clipTotal++;
            explosionSound = getAudioClip(new URL(getCodeBase(), Sound.EXPLOSION));
            clipTotal++;
            fireSound      = getAudioClip(new URL(getCodeBase(), Sound.FIRE));
            clipTotal++;
            missileSound    = getAudioClip(new URL(getCodeBase(), Sound.MISSILE));
            clipTotal++;
            saucerSound    = getAudioClip(new URL(getCodeBase(), Sound.SAUCER));
            clipTotal++;
            thrustersSound = getAudioClip(new URL(getCodeBase(), Sound.THRUSTERS));
            clipTotal++;
            warpSound      = getAudioClip(new URL(getCodeBase(), Sound.WARP));
            clipTotal++;
        }
        catch (MalformedURLException e) {}

        try {
            crashSound.play();     crashSound.stop();     clipsLoaded++;
            repaint(); Thread.currentThread().sleep(DELAY);
            explosionSound.play(); explosionSound.stop(); clipsLoaded++;
            repaint(); Thread.currentThread().sleep(DELAY);
            fireSound.play();      fireSound.stop();      clipsLoaded++;
            repaint(); Thread.currentThread().sleep(DELAY);
            missileSound.play();    missileSound.stop();    clipsLoaded++;
            repaint(); Thread.currentThread().sleep(DELAY);
            saucerSound.play();    saucerSound.stop();    clipsLoaded++;
            repaint(); Thread.currentThread().sleep(DELAY);
            thrustersSound.play(); thrustersSound.stop(); clipsLoaded++;
            repaint(); Thread.currentThread().sleep(DELAY);
            warpSound.play();      warpSound.stop();      clipsLoaded++;
            repaint(); Thread.currentThread().sleep(DELAY);
        }
        catch (InterruptedException e) {}
    }

    public void initShip() {

        // Reset the ship sprite at the center of the screen and
        // initialize thruster sprites.
        ship.initShip();

        if (loaded)
            thrustersSound.stop();
        thrustersPlaying = false;
        hyperCounter = 0;
    }

    public void updateShip() {

        double dx, dy, speed;

        if (!playing)
            return;

        // Rotate the ship if left or right cursor key is down.

        if (left) {
            ship.angle += SHIP_ANGLE_STEP;
            if (ship.angle > 2 * Math.PI)
                ship.angle -= 2 * Math.PI;
        }
        if (right) {
            ship.angle -= SHIP_ANGLE_STEP;
            if (ship.angle < 0)
                ship.angle += 2 * Math.PI;
        }

        // Fire thrusters if up or down cursor key is down.

        dx = SHIP_SPEED_STEP * -Math.sin(ship.angle);
        dy = SHIP_SPEED_STEP *  Math.cos(ship.angle);
        if (up) {
            ship.deltaX += dx;
            ship.deltaY += dy;
        }
        if (down) {
            ship.deltaX -= dx;
            ship.deltaY -= dy;
        }

        // Don't let ship go past the speed limit.

        if (up || down) {
            speed = Math.sqrt(ship.deltaX * ship.deltaX + ship.deltaY * ship.deltaY);
            if (speed > MAX_SHIP_SPEED) {
                dx = MAX_SHIP_SPEED * -Math.sin(ship.angle);
                dy = MAX_SHIP_SPEED *  Math.cos(ship.angle);
                if (up)
                    ship.deltaX = dx;
                else
                    ship.deltaX = -dx;
                if (up)
                    ship.deltaY = dy;
                else
                    ship.deltaY = -dy;
            }
        }

        // Move the ship. If it is currently in hyperspace, advance the countdown.

        if (ship.active) {
            ship.advance();
            ship.render();
            if (hyperCounter > 0)
                hyperCounter--;

            // Update the thruster sprites to match the ship sprite.

            ship.updateThrusters();

        }

        // Ship is exploding, advance the countdown or create a new ship if it is
        // done exploding. The new ship is added as though it were in hyperspace.
        // (This gives the player time to move the ship if it is in imminent
        // danger.) If that was the last ship, end the game.

        else
        if (--shipCounter <= 0)
            if (shipsLeft > 0) {
                initShip();
                hyperCounter = HYPER_COUNT;
            }
            else
                endGame();
    }

    public void stopShip() {

        ship.active = false;
        shipCounter = SCRAP_COUNT;
        if (shipsLeft > 0)
            shipsLeft--;
        if (loaded)
            thrustersSound.stop();
        thrustersPlaying = false;
    }

    public void initPhotons() {

        int i;

        for (i = 0; i < MAX_SHOTS; i++)
            photons[i].active = false;
        photonIndex = 0;
    }

    public void updatePhotons() {

        int i;

        // Move any active photons. Stop it when its counter has expired.

        for (i = 0; i < MAX_SHOTS; i++)
            if (photons[i].active) {
                if (!photons[i].advance())
                    photons[i].render();
                else
                    photons[i].active = false;
            }
    }

    public void initUfo() {

        double angle, speed;

        // Randomly set flying saucer at left or right edge of the screen.

        ufo.active = true;
        ufo.x = -Sprite.width / 2;
        ufo.y = Math.random() * 2 * Sprite.height - Sprite.height;
        angle = Math.random() * Math.PI / 4 - Math.PI / 2;
        speed = MAX_ROCK_SPEED / 2 + Math.random() * (MAX_ROCK_SPEED / 2);
        ufo.deltaX = speed * -Math.sin(angle);
        ufo.deltaY = speed *  Math.cos(angle);
        if (Math.random() < 0.5) {
            ufo.x = Sprite.width / 2;
            ufo.deltaX = -ufo.deltaX;
        }
        if (ufo.y > 0)
            ufo.deltaY = ufo.deltaY;
        ufo.render();
        saucerPlaying = true;
        if (sound)
            saucerSound.loop();
        ufoCounter = (int) Math.abs(Sprite.width / ufo.deltaX);
    }

    public void updateUfo() {

        int i, d;
        boolean wrapped;

        // Move the flying saucer and check for collision with a photon. Stop it
        // when its counter has expired.

        if (ufo.active) {
            if (--ufoCounter <= 0) {
                if (--ufoPassesLeft > 0)
                    initUfo();
                else
                    stopUfo();
            }
            if (ufo.active) {
                ufo.advance();
                ufo.render();
                for (i = 0; i < MAX_SHOTS; i++)
                    if (photons[i].active && ufo.isColliding(photons[i])) {
                        if (sound)
                            crashSound.play();
                        explode(ufo);
                        stopUfo();
                        score += UFO_POINTS;
                    }

                // On occassion, fire a missle at the ship if the saucer is not too
                // close to it.

                d = (int) Math.max(Math.abs(ufo.x - ship.x), Math.abs(ufo.y - ship.y));
                if (ship.active && hyperCounter <= 0 &&
                        ufo.active && !missile.active &&
                        d > MAX_ROCK_SPEED * FPS / 2 &&
                        Math.random() < MISSLE_PROBABILITY)
                    initMissle();
            }
        }
    }

    public void stopUfo() {

        ufo.active = false;
        ufoCounter = 0;
        ufoPassesLeft = 0;
        if (loaded)
            saucerSound.stop();
        saucerPlaying = false;
    }

    public void initMissle() {

        missile.active = true;
        missile.angle = 0.0;
        missile.deltaAngle = 0.0;
        missile.x = ufo.x;
        missile.y = ufo.y;
        missile.deltaX = 0.0;
        missile.deltaY = 0.0;
        missile.render();
        missileCounter = MISSLE_COUNT;
        if (sound)
            missileSound.loop();
        missilePlaying = true;
    }

    public void updateMissle() {

        int i;

        // Move the guided missle and check for collision with ship or photon. Stop
        // it when its counter has expired.

        if (missile.active) {
            if (--missileCounter <= 0)
                stopMissle();
            else {
                guideMissle();
                missile.advance();
                missile.render();
                for (i = 0; i < MAX_SHOTS; i++)
                    if (photons[i].active && missile.isColliding(photons[i])) {
                        if (sound)
                            crashSound.play();
                        explode(missile);
                        stopMissle();
                        score += MISSLE_POINTS;
                    }
                if (missile.active && ship.active &&
                        hyperCounter <= 0 && ship.isColliding(missile)) {
                    if (sound)
                        crashSound.play();
                    explode(ship);
                    stopShip();
                    stopUfo();
                    stopMissle();
                }
            }
        }
    }

    public void guideMissle() {

        double dx, dy, angle;

        if (!ship.active || hyperCounter > 0)
            return;

        // Find the angle needed to hit the ship.

        dx = ship.x - missile.x;
        dy = ship.y - missile.y;
        if (dx == 0 && dy == 0)
            angle = 0;
        if (dx == 0) {
            if (dy < 0)
                angle = -Math.PI / 2;
            else
                angle = Math.PI / 2;
        }
        else {
            angle = Math.atan(Math.abs(dy / dx));
            if (dy > 0)
                angle = -angle;
            if (dx < 0)
                angle = Math.PI - angle;
        }

        // Adjust angle for screen coordinates.

        missile.angle = angle - Math.PI / 2;

        // Change the missle's angle so that it points toward the ship.

        missile.deltaX = 0.75 * MAX_ROCK_SPEED * -Math.sin(missile.angle);
        missile.deltaY = 0.75 * MAX_ROCK_SPEED *  Math.cos(missile.angle);
    }

    public void stopMissle() {

        missile.active = false;
        missileCounter = 0;
        if (loaded)
            missileSound.stop();
        missilePlaying = false;
    }

    public void initAsteroids() {

        int i, j;
        int s;
        double theta, r;
        int x, y;

        // Create random shapes, positions and movements for each asteroid.

        for (i = 0; i < MAX_ROCKS; i++) {

            // Create a jagged shape for the asteroid and give it a random rotation.

            asteroids[i].shape = new Polygon();
            s = MIN_ROCK_SIDES + (int) (Math.random() * (MAX_ROCK_SIDES - MIN_ROCK_SIDES));
            for (j = 0; j < s; j ++) {
                theta = 2 * Math.PI / s * j;
                r = MIN_ROCK_SIZE + (int) (Math.random() * (MAX_ROCK_SIZE - MIN_ROCK_SIZE));
                x = (int) -Math.round(r * Math.sin(theta));
                y = (int)  Math.round(r * Math.cos(theta));
                asteroids[i].shape.addPoint(x, y);
            }
            asteroids[i].active = true;
            asteroids[i].angle = 0.0;
            asteroids[i].deltaAngle = Math.random() * 2 * MAX_ROCK_SPIN - MAX_ROCK_SPIN;

            // Place the asteroid at one edge of the screen.

            if (Math.random() < 0.5) {
                asteroids[i].x = -Sprite.width / 2;
                if (Math.random() < 0.5)
                    asteroids[i].x = Sprite.width / 2;
                asteroids[i].y = Math.random() * Sprite.height;
            }
            else {
                asteroids[i].x = Math.random() * Sprite.width;
                asteroids[i].y = -Sprite.height / 2;
                if (Math.random() < 0.5)
                    asteroids[i].y = Sprite.height / 2;
            }

            // Set a random motion for the asteroid.

            asteroids[i].deltaX = Math.random() * asteroidsSpeed;
            if (Math.random() < 0.5)
                asteroids[i].deltaX = -asteroids[i].deltaX;
            asteroids[i].deltaY = Math.random() * asteroidsSpeed;
            if (Math.random() < 0.5)
                asteroids[i].deltaY = -asteroids[i].deltaY;

            asteroids[i].render();
            asteroidIsSmall[i] = false;
        }

        asteroidsCounter = STORM_PAUSE;
        asteroidsLeft = MAX_ROCKS;
        if (asteroidsSpeed < MAX_ROCK_SPEED)
            asteroidsSpeed += 0.5;
    }

    public void initSmallAsteroids(int n) {

        int count;
        int i, j;
        int s;
        double tempX, tempY;
        double theta, r;
        int x, y;

        // Create one or two smaller asteroids from a larger one using inactive
        // asteroids. The new asteroids will be placed in the same position as the
        // old one but will have a new, smaller shape and new, randomly generated
        // movements.

        count = 0;
        i = 0;
        tempX = asteroids[n].x;
        tempY = asteroids[n].y;
        do {
            if (!asteroids[i].active) {
                asteroids[i].shape = new Polygon();
                s = MIN_ROCK_SIDES + (int) (Math.random() * (MAX_ROCK_SIDES - MIN_ROCK_SIDES));
                for (j = 0; j < s; j ++) {
                    theta = 2 * Math.PI / s * j;
                    r = (MIN_ROCK_SIZE + (int) (Math.random() * (MAX_ROCK_SIZE - MIN_ROCK_SIZE))) / 2;
                    x = (int) -Math.round(r * Math.sin(theta));
                    y = (int)  Math.round(r * Math.cos(theta));
                    asteroids[i].shape.addPoint(x, y);
                }
                asteroids[i].active = true;
                asteroids[i].angle = 0.0;
                asteroids[i].deltaAngle = Math.random() * 2 * MAX_ROCK_SPIN - MAX_ROCK_SPIN;
                asteroids[i].x = tempX;
                asteroids[i].y = tempY;
                asteroids[i].deltaX = Math.random() * 2 * asteroidsSpeed - asteroidsSpeed;
                asteroids[i].deltaY = Math.random() * 2 * asteroidsSpeed - asteroidsSpeed;
                asteroids[i].render();
                asteroidIsSmall[i] = true;
                count++;
                asteroidsLeft++;
            }
            i++;
        } while (i < MAX_ROCKS && count < 2);
    }

    public void updateAsteroids() {

        int i, j;

        // Move any active asteroids and check for collisions.

        for (i = 0; i < MAX_ROCKS; i++)
            if (asteroids[i].active) {
                asteroids[i].advance();
                asteroids[i].render();

                // If hit by photon, kill asteroid and advance score. If asteroid is
                // large, make some smaller ones to replace it.

                for (j = 0; j < MAX_SHOTS; j++)
                    if (photons[j].active && asteroids[i].active && asteroids[i].isColliding(photons[j])) {
                        asteroidsLeft--;
                        asteroids[i].active = false;
                        photons[j].active = false;
                        if (sound)
                            explosionSound.play();
                        explode(asteroids[i]);
                        if (!asteroidIsSmall[i]) {
                            score += BIG_POINTS;
                            initSmallAsteroids(i);
                        }
                        else
                            score += SMALL_POINTS;
                    }

                // If the ship is not in hyperspace, see if it is hit.

                if (ship.active && hyperCounter <= 0 &&
                        asteroids[i].active && asteroids[i].isColliding(ship)) {
                    if (sound)
                        crashSound.play();
                    explode(ship);
                    stopShip();
                    stopUfo();
                    stopMissle();
                }
            }
    }

    public void initExplosions() {

        int i;

        for (i = 0; i < MAX_SCRAP; i++) {
            explosions[i].shape = new Polygon();
            explosions[i].active = false;
            explosionCounter[i] = 0;
        }
        explosionIndex = 0;
    }

    public void explode(Sprite s) {

        int c, i, j;
        int cx, cy;

        // Create sprites for explosion animation. The each individual line segment
        // of the given sprite is used to create a new sprite that will move
        // outward  from the sprite's original position with a random rotation.

        s.render();
        c = 2;
        if (detail || s.sprite.npoints < 6)
            c = 1;
        for (i = 0; i < s.sprite.npoints; i += c) {
            explosionIndex++;
            if (explosionIndex >= MAX_SCRAP)
                explosionIndex = 0;
            explosions[explosionIndex].active = true;
            explosions[explosionIndex].shape = new Polygon();
            j = i + 1;
            if (j >= s.sprite.npoints)
                j -= s.sprite.npoints;
            cx = (int) ((s.shape.xpoints[i] + s.shape.xpoints[j]) / 2);
            cy = (int) ((s.shape.ypoints[i] + s.shape.ypoints[j]) / 2);
            explosions[explosionIndex].shape.addPoint(
                    s.shape.xpoints[i] - cx,
                    s.shape.ypoints[i] - cy);
            explosions[explosionIndex].shape.addPoint(
                    s.shape.xpoints[j] - cx,
                    s.shape.ypoints[j] - cy);
            explosions[explosionIndex].x = s.x + cx;
            explosions[explosionIndex].y = s.y + cy;
            explosions[explosionIndex].angle = s.angle;
            explosions[explosionIndex].deltaAngle = 4 * (Math.random() * 2 * MAX_ROCK_SPIN - MAX_ROCK_SPIN);
            explosions[explosionIndex].deltaX = (Math.random() * 2 * MAX_ROCK_SPEED - MAX_ROCK_SPEED + s.deltaX) / 2;
            explosions[explosionIndex].deltaY = (Math.random() * 2 * MAX_ROCK_SPEED - MAX_ROCK_SPEED + s.deltaY) / 2;
            explosionCounter[explosionIndex] = SCRAP_COUNT;
        }
    }

    public void updateExplosions() {

        int i;

        // Move any active explosion debris. Stop explosion when its counter has
        // expired.

        for (i = 0; i < MAX_SCRAP; i++)
            if (explosions[i].active) {
                explosions[i].advance();
                explosions[i].render();
                if (--explosionCounter[i] < 0)
                    explosions[i].active = false;
            }
    }

    public void keyPressed(KeyEvent e) {

        char c;

        // Check if any cursor keys have been pressed and set flags.

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            left = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            right = true;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            up = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            down = true;

        if ((up || down) && ship.active && !thrustersPlaying) {
            if (sound && !paused)
                thrustersSound.loop();
            thrustersPlaying = true;
        }

        // Spacebar: fire a photon and start its counter.

        if (e.getKeyChar() == ' ' && ship.active) {
            if (sound & !paused)
                fireSound.play();
            photonTime = System.currentTimeMillis();
            photonIndex++;
            if (photonIndex >= MAX_SHOTS)
                photonIndex = 0;
            photons[photonIndex].active = true;
            photons[photonIndex].x = ship.x;
            photons[photonIndex].y = ship.y;
            photons[photonIndex].deltaX = 2 * MAX_ROCK_SPEED * -Math.sin(ship.angle);
            photons[photonIndex].deltaY = 2 * MAX_ROCK_SPEED *  Math.cos(ship.angle);
        }

        // Allow upper or lower case characters for remaining keys.

        c = Character.toLowerCase(e.getKeyChar());

        // 'H' key: warp ship into hyperspace by moving to a random location and
        // starting counter.

        if (c == 'h' && ship.active && hyperCounter <= 0) {
            ship.x = Math.random() * Sprite.width;
            ship.y = Math.random() * Sprite.height;
            hyperCounter = HYPER_COUNT;
            if (sound & !paused)
                warpSound.play();
        }

        // 'P' key: toggle pause mode and start or stop any active looping sound
        // clips.

        if (c == 'p') {
            if (paused) {
                if (sound && missilePlaying)
                    missileSound.loop();
                if (sound && saucerPlaying)
                    saucerSound.loop();
                if (sound && thrustersPlaying)
                    thrustersSound.loop();
            }
            else {
                if (missilePlaying)
                    missileSound.stop();
                if (saucerPlaying)
                    saucerSound.stop();
                if (thrustersPlaying)
                    thrustersSound.stop();
            }
            paused = !paused;
        }

        // 'M' key: toggle sound on or off and stop any looping sound clips.

        if (c == 'm' && loaded) {
            if (sound) {
                crashSound.stop();
                explosionSound.stop();
                fireSound.stop();
                missileSound.stop();
                saucerSound.stop();
                thrustersSound.stop();
                warpSound.stop();
            }
            else {
                if (missilePlaying && !paused)
                    missileSound.loop();
                if (saucerPlaying && !paused)
                    saucerSound.loop();
                if (thrustersPlaying && !paused)
                    thrustersSound.loop();
            }
            sound = !sound;
        }

        // 'D' key: toggle graphics detail on or off.

        if (c == 'd')
            detail = !detail;

        // 'S' key: start the game, if not already in progress.

        if (c == 's' && loaded && !playing)
            initGame();

        // 'HOME' key: jump to web site (undocumented).

        if (e.getKeyCode() == KeyEvent.VK_HOME)
            try {
                getAppletContext().showDocument(new URL(copyLink));
            }
            catch (Exception excp) {}
    }

    public void keyReleased(KeyEvent e) {

        // Check if any cursor keys where released and set flags.

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            left = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            right = false;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            up = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            down = false;

        if (!up && !down && thrustersPlaying) {
            thrustersSound.stop();
            thrustersPlaying = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void update(Graphics g) {

        paint(g);
    }

    public void paint(Graphics g) {

        Dimension d = getSize();
        int i;
        int c;
        String s;
        int w, h;
        int x, y;

        // Create the off screen graphics context, if no good one exists.

        if (offGraphics == null || d.width != offDimension.width || d.height != offDimension.height) {
            offDimension = d;
            offImage = createImage(d.width, d.height);
            offGraphics = offImage.getGraphics();
        }

        // Fill in background and stars.

        offGraphics.setColor(Color.black);
        offGraphics.fillRect(0, 0, d.width, d.height);
        if (detail) {
            offGraphics.setColor(Color.white);
            for (i = 0; i < numStars; i++)
                offGraphics.drawLine(stars[i].x, stars[i].y, stars[i].x, stars[i].y);
        }

        // Draw photon bullets.

        offGraphics.setColor(Color.white);
        for (i = 0; i < MAX_SHOTS; i++)
            if (photons[i].active)
                offGraphics.drawPolygon(photons[i].sprite);

        // Draw the guided missle, counter is used to quickly fade color to black
        // when near expiration.

        c = Math.min(missileCounter * 24, 255);
        offGraphics.setColor(new Color(c, c, c));
        if (missile.active) {
            offGraphics.drawPolygon(missile.sprite);
            offGraphics.drawLine(missile.sprite.xpoints[missile.sprite.npoints - 1], missile.sprite.ypoints[missile.sprite.npoints - 1],
                    missile.sprite.xpoints[0], missile.sprite.ypoints[0]);
        }

        // Draw the asteroids.

        for (i = 0; i < MAX_ROCKS; i++)
            if (asteroids[i].active) {
                if (detail) {
                    offGraphics.setColor(Color.black);
                    offGraphics.fillPolygon(asteroids[i].sprite);
                }
                offGraphics.setColor(Color.white);
                offGraphics.drawPolygon(asteroids[i].sprite);
                offGraphics.drawLine(asteroids[i].sprite.xpoints[asteroids[i].sprite.npoints - 1], asteroids[i].sprite.ypoints[asteroids[i].sprite.npoints - 1],
                        asteroids[i].sprite.xpoints[0], asteroids[i].sprite.ypoints[0]);
            }

        // Draw the flying saucer.

        if (ufo.active) {
            if (detail) {
                offGraphics.setColor(Color.black);
                offGraphics.fillPolygon(ufo.sprite);
            }
            offGraphics.setColor(Color.white);
            offGraphics.drawPolygon(ufo.sprite);
            offGraphics.drawLine(ufo.sprite.xpoints[ufo.sprite.npoints - 1], ufo.sprite.ypoints[ufo.sprite.npoints - 1],
                    ufo.sprite.xpoints[0], ufo.sprite.ypoints[0]);
        }

        // Draw the ship, counter is used to fade color to white on hyperspace.

        c = 255 - (255 / HYPER_COUNT) * hyperCounter;
        if (ship.active) {
            if (detail && hyperCounter == 0) {
                offGraphics.setColor(Color.black);
                offGraphics.fillPolygon(ship.sprite);
            }
            offGraphics.setColor(new Color(c, c, c));
            offGraphics.drawPolygon(ship.sprite);
            offGraphics.drawLine(ship.sprite.xpoints[ship.sprite.npoints - 1], ship.sprite.ypoints[ship.sprite.npoints - 1],
                    ship.sprite.xpoints[0], ship.sprite.ypoints[0]);

            // Draw thruster exhaust if thrusters are on. Do it randomly to get a
            // flicker effect.

            if (!paused && detail && Math.random() < 0.5) {
                if (up) {

                    offGraphics.drawPolygon(ship.getForwardThruster().sprite);
                    offGraphics.drawLine(ship.getForwardThruster().sprite.xpoints[ship.getForwardThruster().sprite.npoints - 1], ship.getForwardThruster().sprite.ypoints[ship.getForwardThruster().sprite.npoints - 1],
                            ship.getForwardThruster().sprite.xpoints[0], ship.getForwardThruster().sprite.ypoints[0]);
                }
                if (down) {
                    offGraphics.drawPolygon(ship.getReverseThruster().sprite);
                    offGraphics.drawLine(ship.getReverseThruster().sprite.xpoints[ship.getReverseThruster().sprite.npoints - 1], ship.getReverseThruster().sprite.ypoints[ship.getReverseThruster().sprite.npoints - 1],
                            ship.getReverseThruster().sprite.xpoints[0], ship.getReverseThruster().sprite.ypoints[0]);
                }
            }
        }

        // Draw any explosion debris, counters are used to fade color to black.

        for (i = 0; i < MAX_SCRAP; i++)
            if (explosions[i].active) {
                c = (255 / SCRAP_COUNT) * explosionCounter [i];
                offGraphics.setColor(new Color(c, c, c));
                offGraphics.drawPolygon(explosions[i].sprite);
            }

        // Display status and messages.

        offGraphics.setFont(font);
        offGraphics.setColor(Color.white);

        offGraphics.drawString("Score: " + score, fontWidth, fontHeight);
        offGraphics.drawString("Ships: " + shipsLeft, fontWidth, d.height - fontHeight);
        s = "High: " + highScore;
        offGraphics.drawString(s, d.width - (fontWidth + fm.stringWidth(s)), fontHeight);
        if (!sound) {
            s = "Mute";
            offGraphics.drawString(s, d.width - (fontWidth + fm.stringWidth(s)), d.height - fontHeight);
        }

        if (!playing) {
            s = copyName;
            offGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 2 - 2 * fontHeight);
            s = copyVers;
            offGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 2 - fontHeight);
            s = copyInfo;
            offGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 2 + fontHeight);
            s = copyLink;
            offGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 2 + 2 * fontHeight);
            if (!loaded) {
                s = "Loading sounds...";
                w = 4 * fontWidth + fm.stringWidth(s);
                h = fontHeight;
                x = (d.width - w) / 2;
                y = 3 * d.height / 4 - fm.getMaxAscent();
                offGraphics.setColor(Color.black);
                offGraphics.fillRect(x, y, w, h);
                offGraphics.setColor(Color.gray);
                if (clipTotal > 0)
                    offGraphics.fillRect(x, y, (int) (w * clipsLoaded / clipTotal), h);
                offGraphics.setColor(Color.white);
                offGraphics.drawRect(x, y, w, h);
                offGraphics.drawString(s, x + 2 * fontWidth, y + fm.getMaxAscent());
            }
            else {
                s = "Game Over";
                offGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 4);
                s = "'S' to Start";
                offGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 4 + fontHeight);
            }
        }
        else if (paused) {
            s = "Game Paused";
            offGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 4);
        }

        // Copy the off screen buffer to the screen.

        g.drawImage(offImage, 0, 0, this);
    }
}