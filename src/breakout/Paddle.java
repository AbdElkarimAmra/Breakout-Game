package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Rectangle;

/**
 * represents the paddle in the game, which is a rectangle the player
 * can  move horizontally to bounce the ball
 */
public class Paddle {
    private double width;
    private double height;
    private Rectangle paddleShape;
    
    /**
     * construcotr that create a new paddle obejct with specified dimensions
     * and position
     * @param x Xcoordinate of the upper left corner
     * @param y Ycoordinate of the upper left corner
     * @param width width of the paddle
     * @param height height of the paddle
     */
    public Paddle(double x, double y, double width, double height){
        this.width = width;
        this.height = height;
        paddleShape = new Rectangle(x, y, width, height);
    }

    
    /**
     * returns the Xcoordinate of the upper left corner of the paddle
     * @return the Xcoordinate of the upper left corner of the paddle
     */
    public double getX(){
        return paddleShape.getX();
    }
    /**
     * returns the Ycoordinate of the upper left corner of the paddle
     * @return the Ycoordinate of the upper left corner of the paddle
     */
    public double getY(){
        return paddleShape.getY();
    }

    /**
     * returns the width of the paddle
     * @return the width of the paddle
     */

    public double getWidth(){
        return width;
    }

    /**
     * returns the height of the paddle
     * @return the height of the paddle
     */
    public double getHeight(){
        return height;
    }

    /**
     * adds the paddle to the canvas 
     * @param canvas a CanvasWindow object
     */
    public void addToCanvas(CanvasWindow canvas){
        canvas.add(paddleShape);
    }

    /**
     * calculates the relatvie position of the ball on the paddle(from left to right)
     * @param ballEllipse an Ellipse object representing the ball
     * @return a value between -1 and 1 representing the ball's position on
     * the paddle
     */
    public double getBallOffset(Ellipse ballEllipse){
        double paddleRadius = width / 2;
        double paddleX = paddleShape.getX() + paddleRadius;
        double ballX = ballEllipse.getCenter().getX();
    
        double offsetNum = (ballX - paddleX) / paddleRadius;
        return offsetNum;
    }
    
    /**
     * sets the Xcoordinate of the upperleft conrner of the paddle to x
     */
    public void setX(double x){
        paddleShape.setX(x);
    }
}
