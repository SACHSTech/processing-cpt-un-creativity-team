import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  PImage imgBackground;
  PImage imgBackground2;
  PImage imgBackground3;
  PImage imgShip;
  PImage imgLives;

	int playerX = 200;
  int playerY = 400;

  int lives = 5;

  /*
  Screen 1 - Main menu (default)
  Screen 2 - Difficulty chooser 
  Screen 3 - Main game
  Screen 4 - Game over
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
    imgBackground = loadImage("background.png");
    imgBackground2 = loadImage("background2.jpg");
    imgBackground3 = loadImage("background3.gif");
    imgShip = loadImage("ship.png");
    imgLives = loadImage("lives.png");

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
    }
    if (screen == 4) {
      gameOver();
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

  fill(255); // White
  textSize(100);
  text("TITLE", 50, 150);

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 300 && mouseY <= 350)) {
    fill(0, 255, 0); // Green
    rect(50, 300, 700, 50);

    fill(255); // Black
    textSize(50);
    text("PLAY", 55, 345);

    if (mousePressed == true) {
      screen = 2;
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
    text("SETTINGS", 55, 445);

    if (mousePressed == true) {
      screen = 5;
    }
  }
  else {
    fill(255); // white
    rect(50, 400, 700, 50);

    fill(0, 0, 255); // Blue
    textSize(50);
    text("SETTINGS", 55, 445);
  }
  


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 500 && mouseY <= 550)) {
    fill(255, 0, 0); // Red
    rect(50, 500, 700, 50);

    fill(255); // White
    textSize(50);
    text("EXIT", 55, 545);

    if (mousePressed == true) {
      exit();
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

 public void difficultyChooser() {
  background(255);

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
      screen = 1;
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
      lives = 5;
      screen = 3;
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
      lives = 3;
      screen = 3;
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
      lives = 1;
      screen = 3;
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

  fill(0); // Black
  textSize(75);
  text("GAME OVER!", 25, 150);

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 300 && mouseY <= 350)) {
    fill(0, 255, 0); // Green
    rect(50, 300, 700, 50);

    fill(0); // Black
    textSize(50);
    text("RETRY", 55, 345);

    if (mousePressed == true) {
      screen = 4;
    }
  }
  else {
    fill(0); // Black
    rect(50, 300, 700, 50);

    fill(0, 255, 0); // Green
    textSize(50);
    text("RETRY", 55, 345);
  }
  


  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(0, 0, 255); // Blue
    rect(50, 550, 700, 50);

    fill(0); // Black
    textSize(50);
    text("MENU", 55, 595);

    if (mousePressed == true) {
      screen = 1;
    }
  }
  else {
    fill(0); // Black
    rect(50, 550, 700, 50);

    fill(0, 0, 255); // Blue
    textSize(50);
    text("MENU", 55, 595);
  }
  

 }

 public void settingsMenu() {
  background(255);

  fill(0); // Black
  textSize(75);
  text("SETTINGS", 25, 150);

  if ((mouseX >= 5 && mouseX <= 200) && (mouseY >= 5 && mouseY <= 55)) {
    fill(0); // black
    rect(5, 5, 150, 50);

    fill(255); // White
    textSize(50);
    text("<--", 10, 50);

    if (mousePressed == true) {
      screen = 1;
    }
  }
  else {
    fill(255, 165, 0); // orange
    rect(5, 5, 150, 50);

    fill(0); // Black
    textSize(50);
    text("<--", 10, 50);
  }
  

}
  
}