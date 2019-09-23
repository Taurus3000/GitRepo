package ru.gb.jtwo.online.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameCanvas extends JPanel {

    private MainCircles gameWindow;
    private long lastFrameTime;
    private final int n_balls = 15;	// Количество шариков
    private int countBalls = 0;
    private long currentTime;
    float deltaTime;
    private Ball balls[] = new Ball[n_balls]; // Массив шариков

    GameCanvas(MainCircles gameWindow) {
        this.gameWindow = gameWindow;
        lastFrameTime = System.nanoTime();
    mouseCliks();
}
    private void mouseCliks() {					// обработка нажатий кнопок мыши
        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {

                    if (countBalls < n_balls) {
                        initBalls(countBalls);						// Инициализация шариков. Ниже сам метод
                        countBalls++;								// Увеличиваем количество шариков
                    }

                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (countBalls > 0) {
                        countBalls--;								// Уменьшаем количество шариков
                    }
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

    }

    // Геттеры определяющие размер GameCanvas. Используются в классе Ball
    // Крайнее левое значенине. Всегда ноль
    int getLeft() {
        return 0;
    }
    // Крайнее правое значение, ширина GameCanvas минус один пиксель
    int getRight() {
        return getWidth() - 1;
    }
    // Крайнее верхнее значение, всегда ноль
    int getTop() {
        return 0;
    }
    // Крайнее нижнее значение, высота GameCanvas минус один пиксель
    int getBottom() {
        return getHeight() - 1;
    }


    @Override									// Переопределение библиотечного метода paintComponent
    public void paintComponent(Graphics g) {
        super.paintComponent(g);				// Сброс внешнего вида GameCanvas перед перерисовкой?

        currentTime = System.nanoTime();		// Текущее системное время
        // Определение разницы между текущим временем и прошедшим, с переводом в секунды(?)
        deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        // Присвоение стартовой метке предыдущего текущего значения, для следующего цикла обновления
        lastFrameTime = currentTime;

        if (countBalls >= 0) {
            setPosition(this, deltaTime); 		// Реализация ниже
            drawBalls(this, g); 				// Реализация ниже
        }

        repaint();								// По всей видимости перерисовка всего поля GameCanvas

        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }

    }

     //Инициализация массива шариков.

    void initBalls(int i) {
        balls[i] = new Ball();
    }

    void setPosition(GameCanvas gameCanvas, float deltaTime) {		// Изменение шариков. Смена местоположения
        for (int i = 0; i < countBalls; i++) {
            balls[i].update(gameCanvas, deltaTime);					// Определение новых координат для шарика
        }
    }

    void drawBalls(GameCanvas gameCanvas, Graphics g) {				// Отрисовка шариков
        for (int i = 0; i < countBalls; i++) {
            balls[i].render(gameCanvas, g);							// Установка цвета и рисование шарика
        }
    }
}
