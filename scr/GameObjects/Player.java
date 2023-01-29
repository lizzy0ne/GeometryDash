package gameObjects;

import util.Constants;
import util.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Player implements Comparable<gameObjects.Player>, Serializable{  //
    private float x = Constants.PLAYER_POSITION_X;
    private float y = Constants.PLAYER_POSITION_Y;
    private float y_velocity = Constants.PLAYER_JUMP_VELOCITY;
    private Rectangle rectangle;
    private boolean isAlive = true;
    private int score = 0;
    private String playerName = "";

    public Player() {
        rectangle = new Rectangle();
    }

    @Override
    public int compareTo(gameObjects.Player other) {
        return other.score - this.score;
    }

    public void update() {   //all these for player jumping
        if (y + 75 >= Constants.GROUND_Y  ) {
            y_velocity = 0;
        } else {
            y_velocity += Constants.GRAVITY;
            y += y_velocity;
        }

        rectangle.x = (int) this.x;   //assigning a rectangle to the player object
        rectangle.y = (int) this.y;
        rectangle.width = 75;  //playerIcon.getWidth;
        rectangle.height = 75;
    }

    public void draw(Graphics g) {
        BufferedImage playerIcon = null;
        try {
            playerIcon = Picture.getPicture("pictures/character1.png ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g.drawImage(playerIcon, (int)x, (int)y, 75, 75,null);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void jump() {
        y_velocity = -10;
        y += y_velocity;
    }

    public void incrementScore() {
        score++;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score = s;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY_velocity() {
        return y_velocity;
    }

    public void setY_velocity(float y_velocity) {
        this.y_velocity = y_velocity;
    }
}
