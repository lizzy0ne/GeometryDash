package gameObjects;

import util.Constants;
import util.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Ground {
    private BufferedImage groundImage;
    private String path;
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private float speed;
    private ArrayList<PictureGround> listPictures;

    public Ground(String path, float startX, float startY, float endX, float endY, float speed) throws IOException {
        this.path = path;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.speed = speed;

        groundImage = Picture.getPicture(path);
        listPictures = new ArrayList<PictureGround>();
        int numImages = Constants.SCREEN_WIDTH / groundImage.getWidth() + 2;  // +2 to avoid empty spaces
        for (int i = 0; i < numImages; i++) {
            PictureGround pictureGround = new PictureGround(   //constructor
                    groundImage,
                    startX + groundImage.getWidth() * i
            );

            listPictures.add(pictureGround);
        }
    }

    public void draw(Graphics g) {
        for (PictureGround pictureGround : listPictures) {
            g.drawImage(pictureGround.bufImage, (int) pictureGround.x, (int)startY, groundImage.getWidth(), (int)endY,null);
        }
    }

    public void update() {
        for (PictureGround pictureGround : listPictures)
            pictureGround.x -= speed;
        PictureGround first = listPictures.get(0);
        if (first.x + first.bufImage.getWidth() < 0) {
            first.x = listPictures.get(listPictures.size() - 1).x + listPictures.get(listPictures.size()-1).bufImage.getWidth();
            listPictures.add(first);
            listPictures.remove(0);
        }
    }

    public class PictureGround { // to store images in the list
        public BufferedImage bufImage;
        public float x;

        public PictureGround(BufferedImage bufImage, float x) {
            this.bufImage = bufImage;
            this.x = x;
        }
    }
}
