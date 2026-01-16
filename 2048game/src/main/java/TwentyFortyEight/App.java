/* Gia Chopra, 540700310, gcho0798

USYD CODE CITATION ACKNOWLEDGEMENT 
This file contains acknowldgements for ideas and/or code 
AI ACKNOWLEDGEMENT
Timer implementation was aided by the below reference:

StackOverflow. (2013, Jan 14). How to make a simple timer in Java. StackOverflow
https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java


I acknowledge the use of ChatGPT [https://chat.openai.com/] to aid with debugging of code 
and the best ways to implement/utilise the PApplet extension .
I entered the following prompts: 
 * "Please give me some hints on how to use PApplet extension"
 * "How to utilise the PApplet extension to display text"
 * "How to best display a timer in Java?"
 
I then used the outputs to modify the code and ensure it meets functionality requirements 
 */

package TwentyFortyEight;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class App extends PApplet {

    public static int GRID_SIZE = 4; // 4x4 grid
    public static final int CELLSIZE = 100; // Cell size in pixels
    public static final int CELL_BUFFER = 8; // Space between cells
    public static final int WIDTH = GRID_SIZE * CELLSIZE;
    public static final int HEIGHT = GRID_SIZE * CELLSIZE;
    public static final int FPS = 30;

    private Cell[][] board;

    private long startTime;
    private long finishTime;
    private boolean gameOver;

    public boolean firstLine;

    public static Random random = new Random();

    private PFont font;
    public PImage eight;

    public App(){

        this.board = new Cell[GRID_SIZE][GRID_SIZE];
    }

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH + (GRID_SIZE-1)*CELL_BUFFER, HEIGHT + 27 + (GRID_SIZE-1)*CELL_BUFFER);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player
     * and map elements.
     */
    @Override
    public void setup() {
        frameRate(FPS);
        // See PApplet javadoc:
        // loadJSONObject(configPath)
        //this.eight = loadImage(this.getClass().getResource("8.png").getPath().toLowerCase(Locale.ROOT).replace("%20", ""));
        // " "));

        // create attributes for data storage, eg board

        background(156, 139, 124);

        startTime = System.currentTimeMillis();
        gameOver = false;
        
        for (int i = 0; i < board.length; i++) {
            for (int i2 = 0; i2 < board[i].length; i2++) {
                board[i][i2] = new Cell(i2, i);
            }
        }
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if (isGameOver()){
            gameOver = true;
            finishTime = System.currentTimeMillis() - startTime;
            //if (key == 'r'){
              //restartGame();
            //}
        }
        else if (key == 'r'){
            restartGame();
        }
        else if (keyCode == LEFT){
            moveLeft();
            spawnTile();
        }
        else if (keyCode == RIGHT){
            moveRight();
            spawnTile();
        }
        else if (keyCode == UP){
            moveUp();
            spawnTile();
        }
        else if (keyCode == DOWN){
            moveDown();
            spawnTile();
        }
    }

    /**
     * Receive key released signal from the keyboard.
     */
    @Override
    public void keyReleased(KeyEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == PConstants.LEFT) {
            Cell current = board[e.getY()/App.CELLSIZE][e.getX()/App.CELLSIZE];
            current.place();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Draw all elements in the game by current frame.
     */
    @Override
    public void draw() {
        // draw game board
        
        fill(220); 
        rect(width - 140, 0, WIDTH + 15, 35);

        fill(0);
        textSize(20);
        textAlign(RIGHT, TOP);
        text(elapsedTime(), WIDTH, 1);

        this.textSize(40);
        this.strokeWeight(15);
        this.textAlign(CENTER);
       
        if (gameOver) {
            textSize(40);
            textAlign(CENTER, CENTER);
            text("GAME OVER", width / 2, height / 2);

            if (key == 'r'){
              restartGame();
            }

            return;
        }

   for (int i = 0; i < board.length; i++) {
        for (int i2 = 0; i2 < board[i].length; i2++) {
            board[i][i2].draw(this);
            }
        }
    }

    public String elapsedTime(){
        /*USYD CODE CITATION ACKNOWLEDGEMENT 
         * I declare that the following lines of code have been taken from the website titled and is not my own work:
         * How to make a simple timer in Java. StackOverflow
         * 
         * original URL: https://stackoverflow.com/questions/10820033/make-a-simple-timer-in-java
            last accessed March 31st 
         */
        long elapsedTime = gameOver ? finishTime : (System.currentTimeMillis() - startTime);
        long seconds = (elapsedTime / 1000) % 60;
        long minutes = (elapsedTime / 60000);
        return String.format("Timer: %02d:%02d", minutes, seconds);
    }

  public void moveLeft(){

        for (int i = 0; i < board.length; i++) {
           for (int i2 = 0; i2 < board.length; i2++) {
               
            if (board[i][i2].value != 0){

                    int targetCol = i2;
                
                    while (targetCol - 1 >= 0){
                        if (board[i][targetCol - 1].value == 0){
                            if (targetCol - 1 >= 0){
                                board[i][targetCol - 1].value = board[i][targetCol].value;
    
                                board[i][targetCol].value = 0;
                            } 
                        } else if (board[i][targetCol - 1].value == board[i][targetCol].value){
                            board[i][targetCol - 1].value = board[i][targetCol].value * 2;

                            board[i][targetCol].value = 0;
                        }
                        targetCol --;
                    }
                }
            }
        }
    }  

   /* public void moveLeft(){
        boolean moved; 

        do { 
            moved = false;
        for (int i = 0; i < board.length; i++) {
            for (int i2 = 1; i2 < board.length; i2++) {
                
             if (board[i][i2].value != 0){ 
              
                    if (i2 - 1 >= 0){
                            if (board[i][i2 -1].value == 0){
                                board[i][i2 -1].value = board[i][i2].value;
     
                                board[i][i2].value = 0;
                                moved = true;
                             } else if (board[i][i2 -1].value == board[i][i2].value){
                            board[i][i2 -1].value = board[i][i2].value * 2;
 
                             board[i][i2].value = 0;
                             moved = true;
                         }
                        }
                     }
                 }
        }
        } while (moved);

    } */

    public void moveRight(){
        boolean moved;

        do { 
            moved = false;
            for (int i = 0; i < board.length; i++) {
                for (int i2 = board.length - 2; i2 >= 0; i2--) {
     
                 if (board[i][i2].value != 0){
                    while (i2 + 1 <= GRID_SIZE - 1){
                             if (board[i][i2 + 1].value == 0){
                                    board[i][i2 + 1].value = board[i][i2].value;
                                    board[i][i2].value = 0;
                                    moved = true;

                             } else if (board[i][i2 + 1].value == board[i][i2].value){
                                 board[i][i2 + 1].value = board[i][i2].value * 2;
     
                                 board[i][i2].value = 0;
                                 moved = true;
                                 //break;
                             } else{
                                 break;
                             }
                         }
                    }
                }
             }
        } while (moved);
        
    }

    public void moveUp(){
        for (int i2 = 0; i2 <= board.length - 1; i2 ++ ) {
            for (int i = 1; i < board.length; i++) {
             if (board[i][i2].value != 0){
                     int targetRow = i;
                 
                     while (targetRow - 1 >= 0){
                         if (board[targetRow - 1][i2].value == 0){
                                board[targetRow - 1][i2].value = board[targetRow][i2].value;
                                board[targetRow][i2].value = 0;
                                targetRow --;
                         } else if (board[targetRow - 1][i2].value == board[targetRow][i2].value){
                             board[targetRow - 1][i2].value = board[targetRow][i2].value * 2;
 
                             board[targetRow][i2].value = 0;
                             break;
                         } else{
                             break;
                         }
                     }
                 }
             }
         }
    }

    public void moveDown(){
        for (int i2 = 0; i2 <= board.length - 1; i2 ++ ) {
            for (int i = board.length-2; i >= 0; i--) {
             if (board[i][i2].value != 0){
                     int targetRow = i;
                 
                     while (targetRow + 1 < board.length){
                         if (board[targetRow + 1][i2].value == 0){
                            // if (targetRow - 1 <= GRID_SIZE){
                                board[targetRow + 1][i2].value = board[targetRow][i2].value;
                                board[targetRow][i2].value = 0;
                                targetRow ++;
                            // } 
                         } else if (board[targetRow + 1][i2].value == board[targetRow][i2].value){
                             board[targetRow + 1][i2].value = board[targetRow][i2].value * 2;
 
                             board[targetRow][i2].value = 0;
                             break;
                         } else{
                            break;
                         }
                     }
                 }
             }
         }
    }

    public void spawnTile(){
        int row, col;

        do { 
            row = random.nextInt(GRID_SIZE);
            col = random.nextInt(GRID_SIZE);
        } while (board[row][col].value != 0);
        board[row][col].value = random.nextBoolean() ? 2:4;
    }

    public boolean isGameOver(){
        for (int i = 0; i < board.length; i++) {
            for (int i2 = 0; i2 < board[i].length; i2++) {
                if (board[i][i2].value == 0){
                    return false;
                }
                else if ((i2 < board.length - 1) && (board[i][i2].value == board[i][i2+1].value)) {
                    return false;
                }
                else if ((i < board.length - 1) && (board[i][i2].value == board[i+1][i2].value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void restartGame(){
        for (int i = 0; i < board.length; i++) {
            for (int i2 = 0; i2 < board[i].length; i2++) {
                board[i][i2].value = 0;
            }
        }
        startTime = System.currentTimeMillis();
        gameOver = false;
    }

    public static void main(String[] args) {
       if (args.length != 0){
            try {
                int n = Integer.parseInt(args[0]);

               GRID_SIZE = n;

            } catch (NumberFormatException e) {
                System.out.println("Using default - 4 x 4");
            }
        }

        PApplet.main("TwentyFortyEight.App");
    }

}
