package gameObjects;

import util.Constants;
import util.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacles {

    private List<Spike> spikes;
    private Player player;

    public List<Spike> getSpikes() {
        return spikes;
    }

    public Obstacles(Player player) throws IOException {
        spikes = new ArrayList<Spike>();
        spikes.add(getRandomSpike());
        this.player = player;
    }

    public Spike getRandomSpike() throws IOException {
        Spike spike = new Spike();
        BufferedImage spikePic1 = Picture.getPicture("pictures/spike.png");
        BufferedImage spikePic2 = Picture.getPicture("pictures/spikes.png");
        Random rand = new Random();
        spike.setX((int) Constants.SPIKE_POSITION_X);
        if(rand.nextBoolean()) {
            spike.setImage(spikePic1);
        } else {
            spike.setImage(spikePic2);
        }
        return spike;
    }

    public void update() throws IOException {
        for (Spike s : spikes) {
            s.update();
            if (s.getRectangle().intersects(player.getRectangle())) {
                System.out.println("Collision!");
                player.setAlive(false);}

            if (s.isOvercome()) {
                player.incrementScore();
            }
        }
        Spike first = spikes.get(0);
        if (first.isOvercome()) {  //CHECK
            spikes.remove(first);
            spikes.add(getRandomSpike());
        }
    }
    public void draw(Graphics g) {
        for (Spike s : spikes) {
            s.draw(g);
        }
    }


}
