import gameObjects.Obstacles;
import gameObjects.Player;
import gameObjects.Spike;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesTest {
    Player p = new Player();
    Obstacles o = new Obstacles(p);

    ObstaclesTest() throws IOException {

    }

    @Test
    void getRandomSpike() {
        int expX = 1000;
        for (Spike s : o.getSpikes()) {
            expX = s.getX();
        }
        assertEquals(expX, 1000);
    }

    @Test
    void updateCollision() throws IOException {
        p.setRectangle(new Rectangle(100, 100, 100, 100));
        Spike spike = new Spike();
        spike.setRectangle(new Rectangle(100, 100, 100, 100));  //spike has the same position as a player
        spike.setX(100);
        spike.setY(100);
        o.getSpikes().clear();
        o.getSpikes().add(spike);
        System.out.println("Player: " + p.getRectangle().x + " " + p.getRectangle().y + " " + p.getRectangle().width + " " + p.getRectangle().height);
        System.out.println("Spike: " + spike.getRectangle().x + " " + spike.getRectangle().y + " " + spike.getRectangle().width + " " + spike.getRectangle().height);
        o.update();
        assertEquals(false, p.isAlive());
    }

    @Test
    void updateScoreIncrement() throws IOException {
        int previousScore = 0;
        p.setScore(previousScore);
        Spike spike = new Spike();
        spike.setX(-100);  //spike has position that is out of screen
        o.getSpikes().clear();  //since in constructor adds random spike with X=1000
        o.getSpikes().add(spike);
        o.update();
        assertEquals(previousScore + 1, p.getScore());
    }
}