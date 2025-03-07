package breakout;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Represents the ball in the Breakout game.
 */

public class Ball {
    private Ellipse ballEllipse;
    private double dx;
    private double dy;
    private boolean isOver;
    private int numOfLives;
    private double ballRadius;
    private double ballSpeed;
    private ArrayList<Brick> bricks;

    /**
     * Constructs a new Ball object.
     *
     * @param x      The Xcoordinate of the ball initial position.
     * @param y      The Ycoordinate of the ball initial position.
     * @param radius The radius of the ball.
     * @param speed  The speed of the ball.
     * @param lives  The number of lives the user will have
     */
    public Ball(double x, double y, double radius, double speed, int lives){
       
        ballRadius = radius;
        ballSpeed = speed;
        numOfLives = lives;
        isOver = false;

        ballEllipse = new Ellipse(x, y, ballRadius * 2, ballRadius * 2);
        ballEllipse.setFillColor(Color.RED);

        Random rand = new Random();
        double angle = rand.nextDouble() * Math.PI / 3 + Math.PI / 6;
        dx = ballSpeed * Math.cos(angle);
        dy = -ballSpeed * Math.sin(angle);
    }

    /**
     * Moves the ball according to its current velocity.
     */

    public void move(){
        ballEllipse.moveBy(dx, dy);
    }

    /**
     * Handles collisions with the boundaries of the canvas and the paddle.
     *
     * @param CANVAS_WIDTH  The width of the canvas
     * @param CANVAS_HEIGHT The height of the canvas
     * @param paddle        The paddle object
     */
    public void handleCollisions(double CANVAS_WIDTH, double CANVAS_HEIGHT, Paddle paddle){
        double ballX = ballEllipse.getCenter().getX();
        double ballY = ballEllipse.getCenter().getY();
    
        if (ballX - ballRadius <= 0 || ballX + ballRadius >= CANVAS_WIDTH) {
            dx = -dx; 
        }
    
        if (ballY - ballRadius <= 0) {
            dy = -dy;
        }
    
        if (checkCollisionWithPaddle(paddle)){
            double hitOffset = paddle.getBallOffset(ballEllipse);
            double angle = Math.toRadians(hitOffset * 45); 
            dx = ballSpeed * Math.sin(angle); 
            dy = -ballSpeed * Math.cos(angle);
        } 
    
        if (ballY + ballRadius >= CANVAS_HEIGHT) {
            ballEllipse.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
            numOfLives=numOfLives-1;
            if (numOfLives <= 0) {
                isOver = true;
            }
        }
    }
    
    /**
     * Checks for collision with the paddle.
     *
     * @param paddle The paddle object
     * @return True if the ball collides with the paddle, false if no collision happens.
     */
    public boolean checkCollisionWithPaddle(Paddle paddle){
        double ballX = ballEllipse.getCenter().getX();
        double ballY = ballEllipse.getCenter().getY();
        double ballRadius = ballEllipse.getWidth() / 2;
    
        double paddleLeft = paddle.getX();
        double paddleRight = paddle.getX() + paddle.getWidth();
        double paddleTop = paddle.getY();
        double paddleBottom = paddle.getY() + paddle.getHeight();
    
        return ballX + ballRadius >= paddleLeft && ballX - ballRadius <= paddleRight &&
                ballY + ballRadius >= paddleTop && ballY - ballRadius <= paddleBottom;
    }

    /**
     * Handles collisions with the bricks.
     *
     * @param bricks The arraylist of bricks
     * @param canvas The canvas window
     */
    public void handleBrickCollisions(ArrayList<Brick> bricks, CanvasWindow canvas) {        
      
        double ballX = ballEllipse.getCenter().getX();
        double ballY = ballEllipse.getCenter().getY();
        
        

        for (Brick brick : bricks) {
            if (!brick.isRemovedFromCanvas()) {
                if (brick.intersects(ballX, ballY)) {
                    brick.removeFromCanvas(canvas);
                    bricks.remove(brick);
                    dy = -dy;       
                    break;             
                }
            }
            }
        
    }

    /**
     * Returns the ellipse representing the ball.
     *
     * @return The ellipse representing the ball.
     */
    public Ellipse getEllipse(){
        return ballEllipse;
    }

    /**
     * Checks if the game has ended.
     *
     * @return True if the game has ended, false if it has not
     */

    public boolean isGameEnded(){
        return isOver;
    }

    /**
     * Gets the number of lives left
     *
     * @return The number of lives left
     */
    public int getNumOfLives(){
        return numOfLives;
    }

}
