package gameScreen;

import util.Constants;

import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame {
    private Panel panel;
    private KL keyListener;
    private ML mouseListener;
    private static Window window = null;

    public Window() throws IOException {
        panel = new Panel();
        keyListener = new KL(panel);
        mouseListener = new ML(panel);

        setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        setTitle(Constants.SCREEN_TITLE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        setLocationRelativeTo(null);
    }

    public void start() {
        panel.start();
    }

    public static Window getWindow() throws IOException {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }
}
