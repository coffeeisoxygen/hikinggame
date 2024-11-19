package com.coffeeisoxigen.ui.drawingpage.animation;

import javax.swing.JComponent;

public interface AnimatedBorder {
    void startAnimation(JComponent component); // Mulai animasi
    void stopAnimation(JComponent component);  // Stop animasi
}
