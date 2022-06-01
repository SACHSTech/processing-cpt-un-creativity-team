import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage imgBackground;
  PImage imgShip;
  PImage imgLives;
	
	int playerX = 200;
  int playerY = 400;

  int lives = 3;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	   // screen size
    size(800, 800);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    imgBackground = loadImage("background.png");
    imgShip = loadImage("ship.png");
    imgLives = loadImage("lives.png");

    background(imgBackground);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  background(imgBackground);
    
    image(imgShip, playerX, playerY);

     // player boundries
    if (playerX < 0) {
      playerX = 0;
    }
    if (playerY < 0) {
      playerY = 0;
    }
    if (playerX > width-100) {
      playerX = width-100;
    }
    if (playerY > height-100) {
      playerY = height-100;
    }

    /*
    if (lives == 3) {
      
    }

    image(imgLives, 755, 795);
    image(imgLives, 770, 795);
    image(imgLives, 785, 795);
    */
    
  }
  
  // other methods down here

  /**
  * Detects if a key on the keyboard is clicked
  * @return if further keys are clicked it executes a condition
  */
  public void keyPressed() {
     // Shift the player
    if (key == 'w') {
      playerY-=20; // Up
    }
    if (key == 's') {
      playerY+=20; // Down
    }
    if (key == 'a') {
      playerX-=20; // Left
    }
    if (key == 'd') {
      playerX+=20; // Right
    }
   
 }
  
}