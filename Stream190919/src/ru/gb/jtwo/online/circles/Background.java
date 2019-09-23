package ru.gb.jtwo.online.circles;

import java.awt.*;

public class Background extends GameCanvas{

    private int startTime = 70;
    private int updateTime = startTime;

    Background(MainCircles gameWindow) {
        super(gameWindow);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateTime--;

        if (updateTime == 0) {
            setBG(this);
            updateTime = startTime;
        }
    }

    void setBG(GameCanvas gameCanvas) {
        int red = (int)(Math.random() * 255);
        int green = (int)(Math.random() * 255);
        int blue = (int)(Math.random() * 255);

        gameCanvas.setBackground(new Color(red, green, blue));

    }
}
