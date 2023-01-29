package gameObjects;

import util.Constants;
import util.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Spike {
    private BufferedImage image;
    private int x, y;
    private Rectangle rectangle;
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Spike() throws IOException {
        image = Picture.getPicture("pictures/spike.png");
        this.x = 1000;
        this.y = (int) Constants.GROUND_Y - image.getHeight();
        rectangle = new Rectangle();
    }

    public void update() {  //assign to spike a rectangle in order to check for collision
        x -= Constants.GROUND_SPEED;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = image.getWidth();
        rectangle.height = image.getHeight();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean isOvercome() {  //CHECK, MB OUT OF SCREEN
        return (x + image.getWidth() < 0);
    }

    public void setY(int y) {
        this.y = y;
    }
}