import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {

  PImage imgBackground;
  PImage imgBackground2;
  PImage imgBackground3;
  PImage imgShip;
  PImage imgLives;

	int playerX = 200;
  int playerY = 400;

  boolean upPressed;
  boolean downPressed;
  boolean leftPressed;
  boolean rightPressed;

  int lives = 5;

  float[] bulletX = new float[25];
  boolean[] boolBulletVis = new boolean[25];

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

      bulletRain();

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
   
 }

  /**
    * Detects if a key on the keyboard is released
    */
    public void keyReleased() {
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
  
  if (upPressed == true) {
    playerY-=5;
  }
  if (downPressed == true) {
    playerY+=5;
  }
  if (leftPressed == true) {
    playerX-=5;
  }
  if (rightPressed == true) {
    playerX+=5;
  }

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
    screen = 4;
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
      screen = 2;
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

public void bulletRain() {
  for (int i = 0; i < bulletX.length; i++) { 
    if (lives > 0) {
      float bulletY = width * i / bulletX.length;

      if (boolBulletVis[i]) { // Draws the snowballs if it is supposed to be visible
        fill(220); // White
        ellipse(bulletX[i], bulletY, 25, 25);
      }

      bulletX[i] -= 2;

      if (bulletX[i] < -12.5) {
        bulletX[i] = width;
      }

      if (playerX+50 >= bulletX[i]-12.5 && playerX+50 <= bulletX[i]+12.5 && playerY+50 >= bulletY-12.5 && playerY+50 <= bulletY+12.5 && boolBulletVis[i] == true) {
        lives -= 1;
        boolBulletVis[i] = false;
      }

      if (mouseX >= bulletY-12.5 && mouseX <= bulletY+12.5 && mouseY >= bulletX[i]-12.5 && mouseY <= bulletX[i]+12.5 && mousePressed == true) {
        boolBulletVis[i] = false;
      }
      
    }

  }

}
  
}