import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/** 
 * This program Sketch.java outputs an interactive space-shooter style game 
 * by taking in key inputs from the user (WASD) to move around the character and avoid 
 * as well as destroy oncoming threats (by utilizing "e" to shoot a laser)
 * @author: Maximilian Schwarzenberg and Garv Choudhry 
 * 
 */

public class Sketch extends PApplet {

  PImage imgBackground;
  PImage imgBackground2;
  PImage imgBackground3;
  PImage imgShip;
  PImage imgMothership;
  PImage imgMothershipDead;
  PImage imgLives;
  PImage imgStar;

	int intPlayerX = 200;
  int intPlayerY = 400;

  boolean upPressed;
  boolean downPressed;
  boolean leftPressed;
  boolean rightPressed;

  Random  myRandom = new Random();

  int intLives = 5;
  int intPhase = 0; // 0
  int intAchievment = 0;
  boolean fullHealth = true;
  boolean secretEnding = false;
  
  /*
  1 - Easy
  2 - Normal
  3 - Hard 
  */
  int difficulty = 0;

  float[] bulletX = new float[25];
  boolean[] boolBulletVis = new boolean[25];

  boolean laserCannon = false;

  int intAvoidAreaNum = 1; // 1-3

  boolean lifeLost = false;

  int intTimer = 0;
  int intTimeSave;
  int intTimeSave2;

  int intSecondPhaseNumber = 0;

  int intMotherX = 800;
  int intMotherY = 200;
  int intMotherSpeedY = 2;
  int intMotherHealth = 1000; // 1000

  /*
  Screen 1 - Main menu (default)
  Screen 2 - Difficulty chooser 
  Screen 3 - Main game
  Screen 4 - Game over
  Screen 5 - Help menu
  Screen 6 - Game won
  */
  int screen = 1;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	   // screen size
    size(800, 800);

    for (int i = 0; i < bulletX.length; i++) { 
      bulletX[i] = random(width);
      boolBulletVis[i] = true;
      bulletX[i] += width;
    }

  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    imgBackground = loadImage("background.png");
    imgBackground2 = loadImage("background2.jpg");
    imgBackground3 = loadImage("background3.gif");
    imgShip = loadImage("ship.png");
    imgMothership = loadImage("mothership.png");
    imgMothershipDead = loadImage("mothershipdead.png");
    imgLives = loadImage("lives.png");
    imgStar = loadImage("star.png");

    background(0); // Black
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  if (screen == 1) {
      mainMenu();
    }
    if (screen == 2) {
      difficultyChooser();
    }
    if (screen == 3) {
      mainGame();
      intTimer++;
      //System.out.println("TIME: " + timer);
      //if (timeSave != 0) { System.out.println("SAVE1: " + timeSave);}
      //if (timeSave2 != 0) { System.out.println("SAVE2: " + timeSave2);}

      if (intTimer <= 2000) {
        bulletRain();
        intPhase = 1;
        fill(255); // White
        textSize(35);
        text("Phase 1/3", 10, 30);
        if (intTimer > 1750 && intTimer < 2000) {
          fill(0, 255, 0); // Green
          textSize(35);
          text("Phase 1/3", 10, 30);
        }
      }
      else if (intTimer > 2000 && intTimer <= 4000) {
        areaAvoid();
        intPhase = 2;
        fill(255); // White
        textSize(35);
        text("Phase 2/3", 10, 30);
        if (intTimer > 3750 && intTimer < 4000) {
          fill(0, 255, 0); // Green
          textSize(35);
          text("Phase 2/3", 10, 30);
        }
      }
      else if (intTimer > 4000) {
        motherShip();
        intPhase = 3;
        fill(255, 0, 0); // Red
        textSize(35);
        text("Phase 3/3", 10, 30);
        if (intMotherHealth == 0) {
          fill(0, 255, 0); // Green
          textSize(35);
          text("Phase 3/3", 10, 30);
        }
      }

    }
    if (screen == 4) { // Game over screen
      gameOver();
    }
    if (screen == 5) { // Help screen
      helpMenu();
    }
    if (screen == 6) { // Game won screen
      gameWon(); 
    }

    if (screen < 1 || screen > 6) {
      screen = 1; // Makes sure screen is within the 1-6 range
    }
    
  }
  
// Defining other methods 

/**
* Detects if a key on the keyboard is clicked
*/
public void keyPressed() {
  if (screen == 3) {
    // Shift the player
    if (key == 'w') {
      upPressed = true; // Up
    }
    if (key == 's') {
      downPressed = true; // Down
    }
    if (key == 'a') {
      leftPressed = true; // Left
     }
    if (key == 'd') {
      rightPressed = true; // Right
    }
    if (key == 'e') { // Laser cannon
      laserCannon = true;
    }
    if (keyCode == SHIFT) { // Dash
      if (upPressed == true) {
        intPlayerY-=100;
        upPressed = false;
      }
      if (downPressed == true) {
        intPlayerY+=100;
        downPressed = false;
      }
      if (leftPressed == true) {
       intPlayerX-=100;
        leftPressed = false;
      }
      if (rightPressed == true) {
        intPlayerX+=100;
        rightPressed = false;
      }
    }
   } 
 }

/**
* Detects if a key on the keyboard is released
*/
  public void keyReleased() {
  if (screen == 3) {
       // Shift the player
    if (key == 'w') {
      upPressed = false; // Up
    }
    if (key == 's') {
      downPressed = false; // Down
    }
    if (key == 'a') {
      leftPressed = false; // Left
    }
    if (key == 'd') {
      rightPressed = false; // Right
    }
    if (key == 'e') {
      laserCannon = false;
    }
  }
    
}

/**
* This method creates the title screen 
*/
 public void mainMenu() {
  background(imgBackground2);

  fill(255); // White
  textSize(75);
  text("Revenge Earth-4056", 25, 150);

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 300 && mouseY <= 350)) {
    fill(0, 255, 0); // Green
    rect(50, 300, 700, 50);

    fill(255); // Black
    textSize(50);
    text("PLAY", 55, 345);

    if (mousePressed == true) { 
      screen = 2; // Changes the screen from main menu to difficulty chooser
    }
  }
  else {
    fill(255); // Black
    rect(50, 300, 700, 50);

    fill(0, 255, 0); // Green
    textSize(50);
    text("PLAY", 55, 345);
  }


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 400 && mouseY <= 450)) {
    fill(0, 0, 255); // Blue
    rect(50, 400, 700, 50);

    fill(255); // White
    textSize(50);
    text("HELP", 55, 445);

    if (mousePressed == true) {
      screen = 5; // Changes the screen from main menu to help menu
    }
  }
  else {
    fill(255); // white
    rect(50, 400, 700, 50);

    fill(0, 0, 255); // Blue
    textSize(50);
    text("HELP", 55, 445);
  }
  


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 500 && mouseY <= 550)) {
    fill(255, 0, 0); // Red
    rect(50, 500, 700, 50);

    fill(255); // White
    textSize(50);
    text("EXIT", 55, 545);

    if (mousePressed == true) {
      exit(); // Quits the program
    }
  }
  else {
    fill(255); // White
    rect(50, 500, 700, 50);

    fill(255, 0, 0); // Red
    textSize(50);
    text("EXIT", 55, 545);
  }
  

 }

/**
* This method creates the difficulty chooser screen where the player can select the difficulty to play the game at
*/
 public void difficultyChooser() {
  background(255);

  if (difficulty >= 1 && difficulty <= 3) {
    screen = 3; // If the difficulty is already determined from the code it goes right to the main game
  }

  fill(0); // Black
  textSize(75);
  text("CHOOSE DIFFICULTY", 25, 150);

  if ((mouseX >= 5 && mouseX <= 200) && (mouseY >= 5 && mouseY <= 55)) {
    fill(0); // black
    rect(5, 5, 150, 50);

    fill(255); // White
    textSize(50);
    text("<--", 10, 50);

    if (mousePressed == true) {
      screen = 1; // Changes the screen from difficulty chooser to main menu
    }
  }
  else {
    fill(255, 165, 0); // orange
    rect(5, 5, 150, 50);

    fill(0); // Black
    textSize(50);
    text("<--", 10, 50);
  }
  

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 250 && mouseY <= 300)) {
    fill(0); // black
    rect(50, 250, 700, 50);

    fill(0, 255, 0); // Green
    textSize(50);
    text("EASY (5 Lives)", 55, 295);

    if (mousePressed == true) {
      intLives = 5;
      difficulty = 1;
      screen = 3; // Changes the screen from difficulty chooser to main game on easy
    }
  }
  else {
    fill(0, 255, 0); // Green
    rect(50, 250, 700, 50);

    fill(0); // Black
    textSize(50);
    text("EASY (5 Lives)", 55, 295);
  }
  


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 400 && mouseY <= 450)) {
    fill(0); // black
    rect(50, 400, 700, 50);

    fill(255, 255, 0); // yellow
    textSize(50);
    text("NORMAL (3 Lives)", 55, 445);
    
    if (mousePressed == true) {
      intLives = 3;
      difficulty = 2;
      screen = 3; // Changes the screen from difficulty chooser to main game on normal
    }
  }
  else {
    fill(255, 255, 0); // yellow
    rect(50, 400, 700, 50);

    fill(0); // Black
    textSize(50);
    text("NORMAL (3 Lives)", 55, 445);
  }
  


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(0); // black
    rect(50, 550, 700, 50);

    fill(255, 0, 0); // Red
    textSize(50);
    text("HARD (1 Life)", 55, 595);

    if (mousePressed == true) {
      intLives = 1;
      difficulty = 3;
      screen = 3; // Changes the screen from difficulty chooser to main game on hard
    }
  }
  else {
    fill(255, 0, 0); // Red
    rect(50, 550, 700, 50);

    fill(0); // Black
    textSize(50);
    text("HARD (1 Life)", 55, 595);
  }
  

 }

/**
* This method creates all the essiential gameplay functions for the game such as the movement of the ship, the laser cannon, edge detection and lives
*/
 public void mainGame() {
  background(imgBackground);
  
  if (upPressed == true) { // If up pressed is true shifts the player up
    intPlayerY-=5;
  }
  if (downPressed == true) { // If down pressed is true shifts the player down
    intPlayerY+=5;
  }
  if (leftPressed == true) { // If left pressed is true shifts the player left
    intPlayerX-=5;
  }
  if (rightPressed == true) { // If right pressed is true shifts the player right
    intPlayerX+=5;
  }

  image(imgShip, intPlayerX, intPlayerY); // Renders the player

  if (laserCannon == true && intPhase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false)) { // Laser cannon weapon
    fill(0, 255, 0); // Green
    noStroke();
    rect(intPlayerX+100, intPlayerY+45, (width/2), 5);
    stroke(0); // Black
  }

   // Player boundries - Keeps the player on the screen
  if (intPlayerX < 0) {
    intPlayerX = 0;
  }
  if (intPlayerY < 0) {
    intPlayerY = 0;
  }
  if (intPlayerX > width-100) {
    intPlayerX = width-100;
  }
  if (intPlayerY > height-100) {
    intPlayerY = height-100;
  }


  if (intLives == 5) { // Renders 5 hearts
    image(imgLives, 500, 10);
    image(imgLives, 560, 10);
    image(imgLives, 620, 10);
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (intLives == 4) { // Renders 4 hearts
    image(imgLives, 560, 10);
    image(imgLives, 620, 10);
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (intLives == 3) { // Renders 3 hearts
    image(imgLives, 620, 10);
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (intLives == 2) { // Renders 2 hearts
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (intLives == 1) { // Renders 1 heart
    image(imgLives, 740, 10);
  }
  else if (intLives == 0) { // Dead player
    screen = 4;
  }

 }

/**
* This method creates an end screen once the player loses (runs out of lives) 
*/
 public void gameOver() {
  background(255, 0, 0);

  fill(0); // Black
  textSize(75);
  text("GAME OVER!", 25, 150);
  

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(255, 0, 0); // Red
    rect(50, 550, 700, 50);

    fill(0); // Black
    textSize(50);
    text("EXIT", 55, 595);

    if (mousePressed == true) {
      exit(); // Exits the game from the game over screen
    }
  }
  else {
    fill(0); // Black
    rect(50, 550, 700, 50);

    fill(255, 0, 0); // Red
    textSize(50);
    text("EXIT", 55, 595);
  }
  
 }

/**
* This method creates an end screen once the game is won and outputs a certain amount of stars depending on the ending(s) you achieve 
*/
 public void gameWon() {
  background(0, 255, 0);

  fill(0); // Black
  textSize(75);
  text("GAME WON!", 25, 150);

  image(imgStar, 25, 250); // One star
  if (fullHealth == true && secretEnding == true) { // If all 3 achievments are done
    intAchievment = 3;
  }
  if (fullHealth == false && secretEnding == false) { // If only one achievment is done
    intAchievment = 1;
  }
  if (fullHealth == true && secretEnding == false) { // If two achievments are done (Keep full health)
    intAchievment = 2;
  }
  if (fullHealth == false && secretEnding == true) { // If two achievments are done (Secret ending)
    intAchievment = 2;
  }

  if (intAchievment == 3) { // If all 3 achievments are done
    image(imgStar, 185, 250); // Two star
    image(imgStar, 345, 250); // Three star
  }
  if (intAchievment == 2) { // If 2 achievments are done
    image(imgStar, 185, 250); // Two star
  }
  

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(255, 0, 0); // Red
    rect(50, 550, 700, 50);

    fill(0); // Black
    textSize(50);
    text("EXIT", 55, 595);

    if (mousePressed == true) {
      exit(); // If exit is pressed from game won screen
    }
  }
  else {
    fill(0); // Black
    rect(50, 550, 700, 50);

    fill(255, 0, 0); // Red
    textSize(50);
    text("EXIT", 55, 595);
  }
  
 }

/**
* This method creates a help menu which displays gameplay controls and gameplay objective onto the screen
*/
 public void helpMenu() {
  background(255);

  fill(0); // Black
  textSize(75);
  text("HELP", 25, 150);

  if ((mouseX >= 5 && mouseX <= 200) && (mouseY >= 5 && mouseY <= 55)) {
    fill(0); // black
    rect(5, 5, 150, 50);

    fill(255); // White
    textSize(50);
    text("<--", 10, 50);

    if (mousePressed == true) {
      screen = 1; // Goes back to main menu from help menu
    }
  }
  else {
    fill(255, 165, 0); // orange
    rect(5, 5, 150, 50);

    fill(0); // Black
    textSize(50);
    text("<--", 10, 50);
  }

  // Display gameplay controls on the screen 
  fill(0); // Black
  textSize(45);
  text("W - Up", 25, 225);
  text("S - Down", 325, 225);
  text("A - Left", 25, 275);
  text("D - Right", 325, 275);
  text("E - Laser cannon (after phase 1)", 25, 325);
  text("Shift - Dash (temporarily stuns)", 25, 375);

  text("Navigate around obstacles.", 25, 475);
  text("Survive each phase.", 25, 525);
  text("Take your Revenge", 25, 575);
  

}

/**
* This method creates the earth debris rock that you must avoid in phase 1 and creates the bullets shot out by the mothership in phase 3
*/
public void bulletRain() {
  for (int i = 0; i < bulletX.length; i++) { 
    if (intLives > 0) {
      float bulletY = width * i / bulletX.length;

      if (boolBulletVis[i]) { // Draws the bullets if it is supposed to be visible
        if (intPhase == 1) {
          fill(220); // Grey
        }
        if (intPhase == 3) {
          fill(255, 0, 0); // Red
        }
        ellipse(bulletX[i], bulletY, 25, 25); // Renders the rocks/bullets
      }

      bulletX[i] -= 4; // Moves the bullet to the left

      if (bulletX[i] < -12.5) {
        if (intTimer < 1750 && intPhase == 1) {
          bulletX[i] = width;
        }
        if (intMotherHealth > 0 && intPhase == 3) {
          bulletX[i] = random(width);
          bulletY = width * i / bulletX.length;
        }
      }

      if (intPlayerX+50 >= bulletX[i]-12.5 && intPlayerX+50 <= bulletX[i]+12.5 && intPlayerY+50 >= bulletY-12.5 && intPlayerY+50 <= bulletY+12.5 && boolBulletVis[i] == true) {
        intLives -= 1;
        boolBulletVis[i] = false;
      }

      if (laserCannon == true && intPhase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false) && intPlayerY+47.5 >= bulletY-12.5 && intPlayerY+47.5 <= bulletY+12.5 && intPlayerX+100+(width/2) >= bulletX[i]) {
        boolBulletVis[i] = false;
      }
      
    }

  }

}

/**
* This method creates the shockwave attack section of phase 2
*/
public void areaAvoid() {
  if (intAvoidAreaNum == 1 && intSecondPhaseNumber <= 5 && intMotherHealth > 0) {
    if (intTimer > intTimeSave + 150 && intTimer < intTimeSave + 200) {
      fill(255); // White
      textSize(75);
      text("!", 750, (800/3)-((800/3)/2)); // Warning !
    }
    else if (intTimer >= intTimeSave + 200 && intTimer < intTimeSave + 250) {
      fill(255, 0, 0); // Red
      rect(0, 0, width, (800/3));
      if (lifeLost == false && intPlayerY >= 0 && intPlayerY <= (800/3)) { // If in shockwave
        intLives--; // Subtracts one life
        lifeLost = true;
        fullHealth = false;
      }
      else if (intPlayerY >= (800/3) && intPlayerY <= height) { // Moves out of shockwave
        lifeLost = false;
      }
    }
    else if (intTimer >= intTimeSave + 400) {
      intTimeSave = intTimer; // Saves time
      intAvoidAreaNum = myRandom.nextInt((3 - 1) + 1) + 1; // Randomizes next attack
      if (intPhase == 2 && intMotherHealth > 0) {
        intSecondPhaseNumber++; // Limits the number of attacks in second phase
      }
    }
  }
  if (intAvoidAreaNum == 2 && intSecondPhaseNumber <= 5 && intMotherHealth > 0) {
    if (intTimer > intTimeSave + 150 && intTimer < intTimeSave + 200) {
      fill(255); // White
      textSize(75);
      text("!", 750, 395); // Warning !
    }
    else if (intTimer >= intTimeSave + 200 && intTimer < intTimeSave + 250) {
      fill(255, 0, 0); // Red
      rect(0, (800/3), width, (800/3));
      if (lifeLost == false && intPlayerY >= (800/3) && intPlayerY <= ((800/3)*2)) { // If in shockwave
        intLives--; // Subtracts one life
        lifeLost = true;
        fullHealth = false;
      }
      else if (intPlayerY <= (800/3) && intPlayerY >= ((800/3)*2)) { // Moves out of shockwave
        lifeLost = false; // Limits the number of attacks in second pha
      }
    }
    else if (intTimer >= intTimeSave + 400) {
      intTimeSave = intTimer; // Saves time
      intAvoidAreaNum = myRandom.nextInt((3 - 1) + 1) + 1; // Randomizes next attack
      if (intPhase == 2 && intMotherHealth > 0) {
        intSecondPhaseNumber++;
      }
    }
  }
  if (intAvoidAreaNum == 3 && intSecondPhaseNumber <= 5 && intMotherHealth > 0) {
    if (intTimer > intTimeSave + 150 && intTimer < intTimeSave + 200) {
      fill(255); // White
      textSize(75);
      text("!", 750, ((800/3)-((800/3)/2)+(800/3)*2)); // Warning !
    }
    else if (intTimer >= intTimeSave + 200 && intTimer < intTimeSave + 250) {
      fill(255, 0, 0); // Red
      rect(0, (800/3)*2, width, (800/3));
      if (lifeLost == false && intPlayerY >= ((800/3)*2) && intPlayerY <= height) { // If in shockwave
        intLives--; // Subtracts one life
        lifeLost = true;
        fullHealth = false;
      }
      else if (intPlayerY <= ((800/3)*2) && intPlayerY >= height) { // Moves out of shockwave
        lifeLost = false;
      }
    }
    else if (intTimer >= intTimeSave + 400) {
      intTimeSave = intTimer; // Saves time
      intAvoidAreaNum = myRandom.nextInt((3 - 1) + 1) + 1; // Randomizes next attack
      if (intPhase == 2 && intMotherHealth > 0) {
        intSecondPhaseNumber++; // Limits the number of attacks in second pha
      }
    }
  }

}

/**
* This method creates the mothership portion of phase 3, it displays the mothership, creates boundaries, mothership movement and the secret ending
*/
public void motherShip() {
  if (intMotherHealth == 0) {
    image(imgMothershipDead, intMotherX, intMotherY); // Renders the dead mothership
  }
  else {
    image(imgMothership, intMotherX, intMotherY); // Renders the mothership
  }
  fill(255); // White
  textSize(50);
  text(intMotherHealth + "/1000", 0, 105);
  if (intMotherX > 550) { // Moves mothership out from the right of the screen
    intMotherX--; // Shifts the mothership
    intTimeSave2 = intTimer; // Saves the time
  }
  else if (intMotherX == 550 && intMotherHealth > 0) {
    intMotherY += intMotherSpeedY;
    if (intMotherY <= 45) { // Keeps the mothership within the top bound
      intMotherSpeedY *= -1;
    }
    if (intMotherY >= height-400) { // Keeps the mothership within the bottom bound
      intMotherSpeedY *= -1;
    }

  }

  if (laserCannon == true && intPhase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false) && intPlayerY+47.5 >= intMotherY && intPlayerY+47.5 <= intMotherY+400 && intPlayerX+100+(width/2) >= intMotherX) {
    intMotherHealth--; // If being shot, mother health go down
  }

  if (intPlayerX+50 >= intMotherX && intPlayerX+50 <= intMotherX+100 && intPlayerY+50 >= intMotherY && intPlayerY+50 <= intMotherY+100 && intMotherHealth > 0 && lifeLost == false) {
    intLives -= 1; // Takes away a life
    lifeLost = true;
  }
  else if (intPlayerX+50 <= intMotherX && intPlayerX+50 >= intMotherX+100 && intPlayerY+50 <= intMotherY && intPlayerY+50 >= intMotherY+100) {
    lifeLost = false;
  }

  if ((intMotherHealth == 1000 && intMotherHealth <= 750) || intTimer >= intTimeSave2+1000) {
    bulletRain(); // After the mothership is below 750 health or after a certain time, bullets start shooting
  }
  if ((intMotherHealth == 1000 && intMotherHealth <= 250) || intTimer >= intTimeSave2+1500) {
    areaAvoid(); // After the mothership is below 250 health or after a certain time, shockwaves attack
  }
  if (intTimer >= intTimeSave2+2500 && intMotherHealth > 0) {
    fill(255); // White
    textSize(50);
    text("ULTIMATE READY", 10, 700); // Indicates to the player that the ultimate is ready

    if (laserCannon == true && intPhase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false)) {
      fill(0, 255, 0); // Green
      noStroke();
      triangle(intPlayerX+100, intPlayerY+45, intPlayerX+100+width, intPlayerY+45+height, intPlayerX+100+width, intPlayerY+45-height); // Ultimate ability
      stroke(0); // Black
      intMotherHealth = 0;
    }
    secretEnding = true; // Sets the secret ending to true
  }

  if (intMotherHealth < 0) {
    intMotherHealth = 0; // Makes sure the mothership health stays at 0 and doesnt go to negatives
  }

  if (intMotherHealth == 2) {
    intTimeSave = intTimer; // Saves the time
  }
  if (intMotherHealth <= 0 && intTimer > intTimeSave + 500) {
    screen = 6; // Sets the screen to the game won screen after the ending
  }
  
}
  
}