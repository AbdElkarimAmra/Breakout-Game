package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;

import java.awt.Color;

import java.util.ArrayList;

/**
 * Represents the Breakout game. it manages the game elements and interactions
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private ArrayList<Brick> listOfBlocks;
    private Paddle paddle;
    private Ball ball;
    private CanvasWindow canvas;
    private boolean gameOver;
    private GraphicsText label;
    private Button restartButton;

    /**
     * This is the constructor for the BreakoutGame class. It initializes the game
     * canvas, creates the game elements (bricks, paddle, and ball), and sets up the
     * game loop to animate the movement of the ball and handle collision
     */
    public BreakoutGame(){
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        listOfBlocks = new ArrayList<>();
        createBricks();

        paddle = new Paddle(300, 700, 80, 10);
        paddle.addToCanvas(canvas);

        ball = new Ball(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, 7, 10, 3);
        canvas.add(ball.getEllipse());
                
        canvas.animate(dt ->{
            if (!gameOver){
                ball.move();
                ball.handleCollisions(CANVAS_WIDTH, CANVAS_HEIGHT, paddle);
                ball.handleBrickCollisions(listOfBlocks, canvas);
                processEndingGame();
            }
        });

        canvas.onMouseMove(event ->{
            double mouseX = event.getPosition().getX();
            double paddleX = mouseX - paddle.getWidth() / 2;
            paddle.setX(paddleX);
        });
    }

        
        
    /**
     * Creates the bricks that make up the top section of the game
     */
    public void createBricks(){
        double brickWidth = 57;
        double brickHeight = 20;
        double bricksSpaceX = 3.35;
        double brickSpaceY = 3.35; // Space between rows
        
        double startY = 100;
        
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                double brickX = j * (brickWidth + bricksSpaceX);
                double brickY = startY + i * (brickHeight + brickSpaceY);
                
                Brick brick = new Brick(brickX, brickY, brickWidth, brickHeight);
                brick.addToCanvas(canvas);
                listOfBlocks.add(brick);
            }
        }
    }
    
    
    



    
    /**
     * This method checks the game state (win or lose) and displays the
     * appropriate message and restart button on the canvas
     */
    private void processEndingGame() {
        if (ball.isGameEnded()) {
            generateGameOverGraphics("Game Over! press the restart button to play again");
        } else if (listOfBlocks.isEmpty()) {
            generateGameOverGraphics("Congrats You Won! press the restart button to start again");
        }
    }
    /**
     * This method generates the visual elements (text and button) to
     * indicate the game is over and allows the user to restart.
     * @param message The message to display on the canvas
     */
    private void generateGameOverGraphics(String message) {
        label = new GraphicsText(message);
        label.setFillColor(Color.RED);
        label.setFontSize(24);
        canvas.add(label, 5, 30);
        
        restartButton = new Button("Restart");
        restartButton.setCenter(CANVAS_WIDTH / 2, 350);
        restartButton.onClick(this::restartGame);
        canvas.add(restartButton);
        gameOver = true;
    }
    
    
    /**
     * resets the game by removing all elements from the canvas, recreating
     * the bricks, repositioning the paddle and ball, and resetting the game
     * state flag
     */
    private void restartGame() {
        canvas.removeAll();
        listOfBlocks.clear();
        createBricks();
        paddle.setX(300);
        paddle.addToCanvas(canvas);
        ball = new Ball(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, 7, 10, 3);
        canvas.add(ball.getEllipse());
        gameOver = false;
    }

    /**
     * creates a new instance of BreakoutGame to start the game.
     * @param args
     */
    public static void main(String[] args){
        new BreakoutGame();
    }
}


