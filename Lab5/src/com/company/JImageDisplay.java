package com.company;
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public class JImageDisplay extends JComponent {//JComponent - окрашивание для всех компонентов
    public BufferedImage image; // управляет изображением, содержимое которого можно записать.
    public JImageDisplay (int width, int height){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);// инициализируем новый объект BufferedImage
        Dimension dim = new Dimension(width,height); // просто хранит два числа
        super.setPreferredSize(dim); //метод setPreferredSize (устанавливает размеры), унаследованный от родительского класса передает значения в dim
    }
    @Override
    public void paintComponent (Graphics g){//метод, который движок Java Swing вызывает для JComponent, чтобы нарисовать его с использованием переданной Graphics (класс, который имееет набор методов для работы с графикой: шрифты, размеры, цвет)
        super.paintComponent (g);
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null); //ImageObserver - получение сообщения о создании изображения
    }
    public void ClearImage (){ //закрашивает все в черный
        image.setRGB(0, 0, getWidth(), getHeight(), new int[getWidth()*getHeight()], 0, 1);
        // x,y,ширина, высота, область, смещение,
    }
    public void drawPixel(int x, int y, int rgbColor){ //закрашивает x и y в цвет
        image.setRGB(x,y,rgbColor);
    }

}
