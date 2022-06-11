import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

public class Sketch extends PApplet {

  PImage imgBackground;
  PImage imgBackground2;
  PImage imgBackground3;
  PImage imgShip;
  PImage imgMothership;
  PImage imgMothershipDead;
  PImage imgLives;

	int playerX = 200;
  int playerY = 400;

  boolean upPressed;
  boolean downPressed;
  boolean leftPressed;
  boolean rightPressed;

  Random  myRandom = new Random();

  int lives = 5;
  int phase = 0; // 0

  float[] bulletX = new float[25];
  boolean[] boolBulletVis = new boolean[25];

  boolean laserCannon = false;

  int avoidAreaNum = 1; // 1-3

  boolean lifeLost = false;

  int timer = 0;
  int timeSave;
  int timeSave2;

  int SecondPhaseNumber = 0;

  int motherX = 800;
  int motherY = 200;
  int motherSpeedY = 2;
  int motherHealth = 1000; // 1000

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
      timer++;
      //System.out.println("TIME: " + timer);
      //if (timeSave != 0) { System.out.println("SAVE1: " + timeSave);}
      //if (timeSave2 != 0) { System.out.println("SAVE2: " + timeSave2);}

      if (timer <= 2000) {
        bulletRain();
        phase = 1;
        fill(255); // White
        textSize(35);
        text("Phase 1/3", 10, 30);
        if (timer > 1750 && timer < 2000) {
          fill(0, 255, 0); // Green
          textSize(35);
          text("Phase 1/3", 10, 30);
        }
      }
      else if (timer > 2000 && timer <= 4000) {
        areaAvoid();
        phase = 2;
        fill(255); // White
        textSize(35);
        text("Phase 2/3", 10, 30);
        if (timer > 3750 && timer < 4000) {
          fill(0, 255, 0); // Green
          textSize(35);
          text("Phase 2/3", 10, 30);
        }
      }
      else if (timer > 4000) {
        motherShip();
        phase = 3;
        fill(255, 0, 0); // Red
        textSize(35);
        text("Phase 3/3", 10, 30);
        if (motherHealth == 0) {
          fill(0, 255, 0); // Green
          textSize(35);
          text("Phase 3/3", 10, 30);
        }
      }

      //bulletRain();
      //areaAvoid();
      //motherShip();
    }
    if (screen == 4) {
      gameOver();
    }
    if (screen == 5) {
      helpMenu();
    }
    if (screen == 6) {
      gameWon(); 
    }
    
  }
  
  // other methods down here

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
          playerY-=50;
          upPressed = false;
        }
        if (downPressed == true) {
          playerY+=50;
          downPressed = false;
        }
        if (leftPressed == true) {
          playerX-=50;
          leftPressed = false;
        }
        if (rightPressed == true) {
          playerX+=50;
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
    text("HELP", 55, 445);

    if (mousePressed == true) {
      screen = 5;
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

  if (laserCannon == true && phase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false)) {
    fill(0, 255, 0); // Green
    noStroke();
    rect(playerX+100, playerY+45, (width/2), 5);
    stroke(0); // Black
  }

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
  

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(255, 0, 0); // Red
    rect(50, 550, 700, 50);

    fill(0); // Black
    textSize(50);
    text("EXIT", 55, 595);

    if (mousePressed == true) {
      exit();
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

 public void gameWon() {
  background(0, 255, 0);

  fill(0); // Black
  textSize(75);
  text("GAME WON!", 25, 150);
  

  if ((mouseX >= 50 && mouseX <= 750) && (mouseY >= 550 && mouseY <= 600)) {
    fill(255, 0, 0); // Red
    rect(50, 550, 700, 50);

    fill(0); // Black
    textSize(50);
    text("EXIT", 55, 595);

    if (mousePressed == true) {
      exit();
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

public void bulletRain() {
  for (int i = 0; i < bulletX.length; i++) { 
    if (lives > 0) {
      float bulletY = width * i / bulletX.length;

      if (boolBulletVis[i]) { // Draws the bullets if it is supposed to be visible
        if (phase == 1) {
          fill(220); // Grey
        }
        if (phase == 3) {
          fill(255, 0, 0); // Red
        }
        ellipse(bulletX[i], bulletY, 25, 25);
      }

      bulletX[i] -= 4;

      if (bulletX[i] < -12.5) {
        if (timer < 1750 && phase == 1) {
          bulletX[i] = width;
        }
        if (motherHealth > 0 && phase == 3) {
          bulletX[i] = random(width);
          bulletY = width * i / bulletX.length;
        }
      }

      if (playerX+50 >= bulletX[i]-12.5 && playerX+50 <= bulletX[i]+12.5 && playerY+50 >= bulletY-12.5 && playerY+50 <= bulletY+12.5 && boolBulletVis[i] == true) {
        lives -= 1;
        boolBulletVis[i] = false;
      }

      if (laserCannon == true && phase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false) && playerY+47.5 >= bulletY-12.5 && playerY+47.5 <= bulletY+12.5 && playerX+100+(width/2) >= bulletX[i]) {
        boolBulletVis[i] = false;
      }
      
    }

  }

}

public void areaAvoid() {
  if (avoidAreaNum == 1 && SecondPhaseNumber <= 5 && motherHealth > 0) {
    if (timer > timeSave + 150 && timer < timeSave + 200) {
      fill(255); // White
      textSize(75);
      text("!", 750, (800/3)-((800/3)/2));
    }
    else if (timer >= timeSave + 200 && timer < timeSave + 250) {
      fill(255, 0, 0); // Red
      rect(0, 0, width, (800/3));
      if (lifeLost == false && playerY >= 0 && playerY <= (800/3)) {
        lives--;
        lifeLost = true;
      }
      else if (playerY >= (800/3) && playerY <= height) {
        lifeLost = false;
      }
    }
    else if (timer >= timeSave + 400) {
      timeSave = timer;
      avoidAreaNum = myRandom.nextInt((3 - 1) + 1) + 1;
      if (phase == 2 && motherHealth > 0) {
        SecondPhaseNumber++;
      }
    }
  }
  if (avoidAreaNum == 2 && SecondPhaseNumber <= 5 && motherHealth > 0) {
    if (timer > timeSave + 150 && timer < timeSave + 200) {
      fill(255); // White
      textSize(75);
      text("!", 750, 395);
    }
    else if (timer >= timeSave + 200 && timer < timeSave + 250) {
      fill(255, 0, 0); // Red
      rect(0, (800/3), width, (800/3));
      if (lifeLost == false && playerY >= (800/3) && playerY <= ((800/3)*2)) {
        lives--;
        lifeLost = true;
      }
      else if (playerY <= (800/3) && playerY >= ((800/3)*2)) {
        lifeLost = false;
      }
    }
    else if (timer >= timeSave + 400) {
      timeSave = timer;
      avoidAreaNum = myRandom.nextInt((3 - 1) + 1) + 1;
      if (phase == 2 && motherHealth > 0) {
        SecondPhaseNumber++;
      }
    }
  }
  if (avoidAreaNum == 3 && SecondPhaseNumber <= 5 && motherHealth > 0) {
    if (timer > timeSave + 150 && timer < timeSave + 200) {
      fill(255); // White
      textSize(75);
      text("!", 750, ((800/3)-((800/3)/2)+(800/3)*2));
    }
    else if (timer >= timeSave + 200 && timer < timeSave + 250) {
      fill(255, 0, 0); // Red
      rect(0, (800/3)*2, width, (800/3));
      if (lifeLost == false && playerY >= ((800/3)*2) && playerY <= height) {
        lives--;
        lifeLost = true;
      }
      else if (playerY <= ((800/3)*2) && playerY >= height) {
        lifeLost = false;
      }
    }
    else if (timer >= timeSave + 400) {
      timeSave = timer;
      avoidAreaNum = myRandom.nextInt((3 - 1) + 1) + 1;
      if (phase == 2 && motherHealth > 0) {
        SecondPhaseNumber++;
      }
    }
  }

}

public void motherShip() {
  if (motherHealth == 0) {
    image(imgMothershipDead, motherX, motherY);
  }
  else {
    image(imgMothership, motherX, motherY);
  }
  fill(255); // White
  textSize(50);
  text(motherHealth + "/1000", 0, 105);
  if (motherX > 550) {
    motherX--;
    timeSave2 = timer;
  }
  else if (motherX == 550 && motherHealth > 0) {
    motherY += motherSpeedY;
    if (motherY <= 45) {
      motherSpeedY *= -1;
    }
    if (motherY >= height-400) {
      motherSpeedY *= -1;
    }

  }

  if (laserCannon == true && phase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false) && playerY+47.5 >= motherY && playerY+47.5 <= motherY+400 && playerX+100+(width/2) >= motherX) {
    motherHealth--;
  }

  if (playerX+50 >= motherX && playerX+50 <= motherX+100 && playerY+50 >= motherY && playerY+50 <= motherY+100 && motherHealth > 0 && lifeLost == false) {
    lives -= 1;
    lifeLost = true;
  }
  else if (playerX+50 <= motherX && playerX+50 >= motherX+100 && playerY+50 <= motherY && playerY+50 >= motherY+100) {
    lifeLost = false;
  }

  if (motherHealth <= 750 || timer >= timeSave2+1000) {
    bulletRain();
  }
  if (motherHealth <= 250 || timer >= timeSave2+1500) {
    areaAvoid();
  }
  if (timer >= timeSave2+2500) {
    fill(255); // White
    textSize(50);
    text("ULTIMATE READY", 10, 700);

    if (laserCannon == true && phase > 1 && (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false)) {
      fill(0, 255, 0); // Green
      noStroke();
      triangle(playerX+100, playerY+45, playerX+100+width, playerY+45+height, playerX+100+width, playerY+45-height);
      stroke(0); // Black
      motherHealth = 0;
    }
  }

  if (motherHealth < 0) {
    motherHealth = 0;
  }

  if (motherHealth == 2) {
    timeSave = timer;
  }
  if (motherHealth <= 0 && timer > timeSave + 500) {
    screen = 6;
  }
  
}
  
}