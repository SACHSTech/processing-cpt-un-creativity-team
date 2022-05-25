import processing.core.PApplet;

public class Sketch1 extends PApplet {
	
	int playerX = 200;
  int playerY = 400;

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
    background(255);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  background(255);

    fill(0, 0, 139); // Blue
    rect(playerX, playerY, 25, 25); // Draws the player
    
  }
  
  // other methods down here

  /**
  * Detects if a key on the keyboard is clicked
  * @return if further keys are clicked it executes a condition
  */
  public void keyPressed() {
     // Shift the player
    if (key == 'w') {
      playerY-=20;
    }
    if (key == 's') {
      playerY+=20;
    }
    if (key == 'a') {
      playerX-=20;
    }
    if (key == 'd') {
      playerX+=20;
    }
   
 }
  
}