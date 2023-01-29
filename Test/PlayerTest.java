import gameObjects.Player;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player p1 = new Player();

    @Test
    void compareTo() {
        p1.setScore(10);
        Player p2 = new Player();
        p2.setScore(15);
        assertTrue(p1.compareTo(p2) > 0);
    }

    @Test
    void compareTo1() {
        p1.setScore(10);
        Player p3 = new Player();
        p3.setScore(5);
        assertTrue(p1.compareTo(p3) < 0);
    }

    @Test
    void compareTo2() {
        p1.setScore(10);
        Player p4 = new Player();
        p4.setScore(10);
        assertEquals(0, p1.compareTo(p4));
    }

    @Test
    void updateFirstCondition() {
        p1.setY(600);
        p1.update();
        assertEquals(0, p1.getY_velocity());
    }

    @Test
    void updateSecondCondition() {
        p1.setY(0);
        float previousY = p1.getY();
        p1.update();
        assertEquals(previousY + p1.getY_velocity(), p1.getY());
    }

    @Test
    void update() {
        p1.setX(0);
        p1.setY(0);
        p1.update();
        Rectangle rectangle = new Rectangle((int)p1.getX(), (int)p1.getY(), 75, 75);
        assertEquals(rectangle, p1.getRectangle());
    }

    @Test
    void getRectangle() {
        Rectangle expResult = new Rectangle();
        p1.setRectangle(expResult);
        Rectangle result = p1.getRectangle();
        assertEquals(expResult, result);
    }

    @Test
    void setRectangle() {
        Rectangle rectangle = new Rectangle();
        p1.setRectangle(rectangle);
        assertEquals(rectangle, p1.getRectangle());
    }

    @Test
    void jump() {
        float previousY = p1.getY();
        p1.jump();
        float afterJump = previousY + p1.getY_velocity();
        assertEquals(afterJump, p1.getY());
    }

    @Test
    void incrementScore() {
        int previous = p1.getScore();
        p1.incrementScore();
        assertEquals(previous + 1, p1.getScore());
    }

    @Test
    void isAlive() {
        System.out.println("isAlive");
        boolean expResult = true;
        p1.setAlive(true);
        boolean result = p1.isAlive();
        assertEquals(expResult, result);
    }

    @Test
    void setAlive() {
        boolean b = false;
        p1.setAlive(b);
        assertEquals(b, p1.isAlive());
    }

    @Test
    void getScore() {
        int expResult = 10;
        p1.setScore(10);
        int result = p1.getScore();
        assertEquals(expResult, result);
    }

    @Test
    void setScore() {
        int score = 10;
        p1.setScore(score);
        assertEquals(score, p1.getScore());
    }

    @Test
    void getPlayerName() {
        String expResult = "Anna";
        p1.setPlayerName("Anna");
        String result = p1.getPlayerName();
        assertEquals(expResult, result);
    }

    @Test
    void setPlayerName() {
        String name = "Anna";
        p1.setPlayerName(name);
        assertEquals(name, p1.getPlayerName());
    }
}