package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FractalExplorer{
    //поля состояния программы
    private int size; //размер экрана - квадрат
    private JImageDisplay jImageDisplay; //для обновления отображения
    private FractalGenerator fractalGenerator; //отображение других видов фракталов
    private Rectangle2D.Double complexRange; //указывает диапазон плоскости, выводящейся на экран
    public FractalExplorer(int size) { //конструктор, который принимает размер окна
        this.size=size;// сохраняет значение
        jImageDisplay = new JImageDisplay(size,size); //инициализация поля для фрактала
        fractalGenerator = new Mandelbrot(); //инициализация генератора фрактала
        complexRange = new Rectangle2D.Double(); //инициализация диапазона
        fractalGenerator.getInitialRange(complexRange); //установка первоначальных значений диапазона для плоскости фрактала
    }
    public void createAndShowGUI() {
        jImageDisplay.setLayout(new BorderLayout());//инициализация менеджера расположения
        JFrame jFrame = new JFrame("Fractal Explorer");//инициализация окна с рамкой
        jFrame.add(jImageDisplay,BorderLayout.CENTER);//изображение по центру
        JButton buttonReset = new JButton("Reset"); //инициализация кнопки сброса
        buttonReset.addActionListener(new buttonResetHandler());// слушатель - обработчик кнопки сброса
        buttonReset.setVisible(true);//видимость
        jFrame.add(buttonReset,BorderLayout.SOUTH);//кнопка вниз
        jImageDisplay.addMouseListener(new MouseHandler());// слушатель нажатия на мышь
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//закрытие окна по умолчанию
        jFrame.pack();//правильное размещение содержимого окна
        jFrame.setVisible(true);//видимость
        jFrame.setResizable(false);//нельзя менять размер окна
    }
    public void drawFractal()//вспомогательный метод для вывода фрактала на экран
    {
        for (int x=0; x<size; ++x){//проходимся по всем пикселям в отображении
            for (int y=0; y<size; ++y){
                //находим координаты проскости фрактала
                double xCoord = FractalGenerator.getCoord(complexRange.x, complexRange.x + complexRange.width, size, x);
                double yCoord = FractalGenerator.getCoord(complexRange.y, complexRange.y + complexRange.height, size, y);
                //вычисляем количество иттераций для соответствующих координат
                int iter = fractalGenerator.numIterations(xCoord, yCoord);
                if (iter < 0) { //красим в черный, если точка не выходит за границы
                    jImageDisplay.drawPixel(x, y, 0);
                    continue;
                }//иначе красим в цвет
                float hue = 0.7f + (float) iter / 200f;
                int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                jImageDisplay.drawPixel(x, y, rgbColor);
            }
        }
        //обновили JimageDisplay в соответствии с текущим изображением
        jImageDisplay.repaint();
    }
    class buttonResetHandler implements ActionListener {// кнопка сброса = возврат к первоначальным значениям
        public void actionPerformed(ActionEvent e) {
            fractalGenerator.getInitialRange(complexRange);
            drawFractal();//перерисовка фрактала
        }
    }
    class MouseHandler extends MouseAdapter{// обработчик события нажатия на мышь. создание прямоугольника на плоскости фракталов
        @Override
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(complexRange.x, complexRange.x+complexRange.width, size, e.getX());
            double y = FractalGenerator.getCoord(complexRange.y, complexRange.y+complexRange.height, size, e.getY());
            fractalGenerator.recenterAndZoomRange(complexRange, x, y, 0.5);
            drawFractal();//перерисовка фрактала
        }
    }

}