package com.company;

import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {

    private static final int SIZE = 256;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private int n;
    static Chord chord;
    /** @param n  the desired number of circles. */
    public Draw(int n, Chord chord) {
        super(true);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.n = n;
        this.chord = chord;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        a = getWidth() / 2;
        b = getHeight() / 2;
        int m = Math.min(a, b);
        r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 2;
        g2d.drawOval(a - r, b - r, 2 * r, 2 * r);
        g2d.setColor(Color.blue);

        chord = new Chord();

        Chord.Node head = chord.getHead();
        Chord.Node iter = head;
        int i = 0;
        int size = chord.getSize();
        while (true) {
            if (i > 0 && iter == head) {
                break;
            } else {
                double t = 2 * Math.PI * i / size;
                int x = (int) Math.round(a + r * Math.cos(t));
                int y = (int) Math.round(b + r * Math.sin(t));
                g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawString(iter.getDataStr(), x - r2-10, y - r2-10);
                iter = iter.next;
                i++;
            }
        }
    }

    private static void create() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Draw(9, chord));
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        Chord chord = new Chord();
        chord.addNode();
        chord.addNode();
        chord.addNode();
        chord.addNode();
        chord.addData(60,60);
        chord.addData(32,32);
        chord.addData(51,51);
        chord.printNode();

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                create();
            }
        });
    }
}