package breakout;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * represents a Brick object in the BreakOut game
 */
public class Brick{
    private Rectangle brickShape;
    private boolean removedFromCanvas;

    /**
     * creates a new Brick object with a random color and add it
     * to the given canvas window
     * @param x the Xcoordinate of the upper left corner of the brick
     * @param y the Ycoordinate of the upper left corner of the brick
     * @param width width of the brick
     * @param height height of the brick
     */
    public Brick(double x, double y, double width, double height){
        Random random = new Random();
        ArrayList<Color> listColors = new ArrayList<>();
        listColors.add(Color.red);
        listColors.add(Color.yellow);
        listColors.add(Color.orange);
        listColors.add(Color.green);

        int randomIndex = random.nextInt(listColors.size());
        Color randomColor = listColors.get(randomIndex);

        brickShape = new Rectangle(x, y, width, height);
        brickShape.setFillColor(randomColor);
    }

    /**
     * adds the brick's rectanglular shape to the canvas
     * @param canvas the CanavasWindonw object to add the brick to
     */
    public void addToCanvas(CanvasWindow canvas){
        canvas.add(brickShape);
    }

    /**
     * returns the Xcoordinate of the upper left corner of the brick
     * @return the Xcoordinate of the upper left corner of the brick
     */
    public double getX(){
        return brickShape.getX();
    }

    /**
     * returns the Ycoordinate of the upper left corner of the brick
     * @return the Ycoordinate of the upper left corner of the brick
     */
    public double getY(){
        return brickShape.getY();
    }
    /**
     *  sets the Xcoordinate to newX
     * @param newX the value to which the Xcoordinate will be set
     */

    public void setX(double newX){
        brickShape.setX(newX);
    }

    /**
     *  sets the Ycoordinate to newY
     * @param newX the value to which the Ycoordinate will be set
     */

    public void setY(double newY){
        brickShape.setY(newY);
    }

    /**
     * returns the width of the brick
     * @return the width of the brick
     */
    public double getWidth(){
        return brickShape.getWidth();
    }

      /**
     * returns the height of the brick
     * @return the height of the brick
     */
    public double getHeight(){
        return brickShape.getHeight();
    }

     /**
      * returns the Rectangle object representing the shape of the brick
      * @return the Rectangle object representing the shape of the brick 
      */
    public Rectangle getRectangle(){
        return brickShape;
    }

    /**
     * checks if a given point (x,y) collides with the brick
     * @param x Xcoordinate of the brick
     * @param y Ycoordinate of the brick
     * @return true if the point collides with the brick, false if not
     */
    public boolean intersects(double x, double y){
        return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
    }

    /**
     * removes the brick from the canvas
     * @param canvas
     */
    public void removeFromCanvas(CanvasWindow canvas){
        if (!removedFromCanvas){
            canvas.remove(brickShape);
            removedFromCanvas = true;
        }
    }

    /**
     * checks if the brick has been removed from the game canvas
     * @return true if the brick has been removed from the game canvas, false if not
     */
    public boolean isRemovedFromCanvas(){
        return removedFromCanvas;
    }
}
