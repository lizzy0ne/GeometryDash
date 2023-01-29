package gameScreen;

import gameScreen.Panel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL extends KeyAdapter implements KeyListener {
    private Panel panel;
    public KL(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key is pressed");
    }


    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key is released");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if (Panel.state == Panel.GameState.MENU) {
                    Panel.state = Panel.GameState.GAME;
                } else if (Panel.state == Panel.GameState.GAME) {
                    panel.getPlayer().jump();
                } else if (Panel.state == Panel.GameState.END) {
                    Panel.state = Panel.GameState.MENU;
                }
                break;
        }
    }

}