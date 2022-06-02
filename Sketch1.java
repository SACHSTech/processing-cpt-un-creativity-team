import processing.core.PApplet;
import processing.core.PImage;

public class Sketch1 extends PApplet {

  PImage imgBackground2;
  PImage imgBackground;
  PImage imgShip;
  PImage imgLives;

	int playerX = 200;
  int playerY = 400;

  int lives = 5;

  /*
  Screen 1 - Main menu
  Screen 2 - Main game
  Screen 3 - Game over
  Screen 4- Difficulty chooser 
  Screen 5 - Settings menu
  */
  int screen = 1;

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
    imgBackground2 = loadImage("background2.jpg");
    imgBackground = loadImage("background.png");
    imgShip = loadImage("ship.png");
    imgLives = loadImage("lives.png");

    background(imgBackground);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  if (screen == 1) {
      mainMenu();
    }
    if (screen == 2) {
      mainGame();
    }
    if (screen == 3) {
      gameOver();
    }
    if (screen == 4) {
      difficultyChooser();
    }
    if (screen == 5) {
      settingsMenu();
    }
    
  }
  
  // other methods down here

  /**
  * Detects if a key on the keyboard is clicked
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

 public void mainMenu() {
  background(imgBackground2);

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 300 && mouseY <= 350)) {
    fill(0, 255, 0); // green
    if (mousePressed == true) {
      screen = 4;
    }
  }
  else {
    fill(255); // white
  }
  rect(50, 300, 700, 50);


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 400 && mouseY <= 450)) {
    fill(0, 0, 255); // blue
  }
  else {
    fill(255); // white
  }
  rect(50, 400, 700, 50);


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 500 && mouseY <= 550)) {
    fill(255, 0, 0); // red
  }
  else {
    fill(255); // white
  }
  rect(50, 500, 700, 50);


 }

 public void mainGame() {
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


  if (lives == 5) {
    image(imgLives, 500, 10);
    image(imgLives, 560, 10);
    image(imgLives, 620, 10);
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (lives == 4) {
    image(imgLives, 560, 10);
    image(imgLives, 620, 10);
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (lives == 3) {
    image(imgLives, 620, 10);
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (lives == 2) {
    image(imgLives, 680, 10);
    image(imgLives, 740, 10);
  }
  else if (lives == 1) {
    image(imgLives, 740, 10);
  }
  else if (lives == 0) {
    screen = 3;
  }

 }

 public void gameOver() {
  background(255, 0, 0);

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 300 && mouseY <= 350)) {
    fill(0, 255, 0); // green
    if (mousePressed == true) {
      screen = 4;
    }
  }
  else {
    fill(0); // black
  }
  rect(50, 300, 700, 50);


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(0, 0, 255); // blue
    if (mousePressed == true) {
      screen = 1;
    }
  }
  else {
    fill(0); // black
  }
  rect(50, 550, 700, 50);

 }

 public void difficultyChooser() {
  background(255);

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 250 && mouseY <= 300)) {
    fill(0); // black
    if (mousePressed == true) {
      lives = 5;
      screen = 2;
    }
  }
  else {
    fill(0, 255, 0); // green
  }
  rect(50, 250, 700, 50);


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 400 && mouseY <= 450)) {
    fill(0); // black
    if (mousePressed == true) {
      lives = 3;
      screen = 2;
    }
  }
  else {
    fill(255, 255, 0); // yellow
  }
  rect(50, 400, 700, 50);


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(0); // black
    if (mousePressed == true) {
      lives = 1;
      screen = 2;
    }
  }
  else {
    fill(255, 0, 0); // red
  }
  rect(50, 550, 700, 50);


 }

 public void settingsMenu() {
   //here
}
  
}