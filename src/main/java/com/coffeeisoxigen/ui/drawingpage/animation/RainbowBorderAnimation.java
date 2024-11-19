package com.coffeeisoxigen.ui.drawingpage.animation;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.Timer;

public class RainbowBorderAnimation implements AnimatedBorder {
    private Timer timer;
    private Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA };
    private int colorIndex = 0;

    @Override
    public void startAnimation(JComponent component) {
        timer = new Timer(100, e -> {
            component.setBorder(BorderFactory.createLineBorder(colors[colorIndex], 3));
            colorIndex = (colorIndex + 1) % colors.length; // Loop warna
            component.repaint();
        });
        timer.start();
    }

    @Override
    public void stopAnimation(JComponent component) {
        if (timer != null) {
            timer.stop();
        }
        component.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Balikin ke border default
    }
}
