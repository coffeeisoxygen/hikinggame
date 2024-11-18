package com.coffeeisoxigen.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.coffeeisoxigen.model.board.Board;
import com.coffeeisoxigen.model.board.Tile;

public class PreviewUI extends JFrame {
    private Board board;
    private JPanel boardPanel;

    public PreviewUI(Board board) {
        this.board = board;
        setTitle("Map Preview");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridBagLayout());
        add(boardPanel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayingPageUI playingPageUI = new PlayingPageUI(board);
                playingPageUI.setVisible(true);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(PreviewUI.this,
                        "Default map cannot be changed. Redirecting to landing page.");
                LandingPageUI landingPageUI = new LandingPageUI();
                landingPageUI.setVisible(true);
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        initializeBoardPanel();
    }

    private void initializeBoardPanel() {
        boardPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Tile tile = board.getTile(x, y);
                gbc.gridx = x;
                gbc.gridy = y;
                boardPanel.add(new TilePanel(tile, x, y), gbc);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private class TilePanel extends JPanel {
        private final Tile tile;

        public TilePanel(Tile tile, int x, int y) {
            this.tile = tile;
            setBackground(Color.decode(tile.getColor()));
            setBorder(new LineBorder(Color.BLACK, 1));
            setPreferredSize(new Dimension(50, 50));
            JLabel label = new JLabel("<html>" + (x * board.getWidth() + y) + "<br>(" + x + "," + y + ")</html>");
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            add(label);
        }
    }
}