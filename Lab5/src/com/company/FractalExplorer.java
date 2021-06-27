package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;//выбор файла для сохранения. предоставляет метод showSaveDialog()
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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



    //+ 5 лаба
    public String[] items = {"Mandelbrot","Burning Ship", "Tricorn"};   //+объекты управления Сombo-box

    public void createAndShowGUI() {
        jImageDisplay.setLayout(new BorderLayout());

        JFrame jFrame = new JFrame("Fractals");
        JButton buttonReset = new JButton("Reset");
        JButton buttonSave = new JButton("Save"); //+ кнопка сохранения
        JComboBox comboBox = new JComboBox(items); //+ combobox
        JPanel panelBottom = new JPanel(); //+ панель вниз
        JPanel panelTop= new JPanel();  //+панель вверх
        JLabel label = new JLabel("Fractals:"); //+ label

        // + добавление объектов на панель
        panelBottom.add(buttonSave);
        panelBottom.add(buttonReset);
        panelTop.add(label);
        panelTop.add(comboBox);

        // + расположение панелей
        jFrame.add(jImageDisplay,BorderLayout.CENTER);
        jFrame.add(panelTop,BorderLayout.NORTH);
        jFrame.add(panelBottom,BorderLayout.SOUTH);

        // + слушатели
        jImageDisplay.addMouseListener(new MouseHandler());
        buttonReset.addActionListener(new buttonResetHandler());
        buttonSave.addActionListener(new buttonSaveHandler());
        comboBox.addActionListener(new comboBoxHandler());

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setResizable(false);

    }
    public void drawFractal()//вспомогательный метод для вывода фрактала на экран
    {
        for (int x=0; x<size; ++x){//проходимся по всем пикселям в отображении
            for (int y=0; y<size; ++y){
                //находим координаты плоскости фрактала
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


    //+5
    class comboBoxHandler implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox)e.getSource();
            String nameFractal = (String)combo.getSelectedItem();
            if(nameFractal=="Mandelbrot") {
                fractalGenerator=new Mandelbrot();
            }
            if(nameFractal=="Burning Ship") {
                fractalGenerator= new BurningShip();
            }
            if(nameFractal=="Tricorn") {
                fractalGenerator= new Tricorn();
            }
            fractalGenerator.getInitialRange(complexRange);
            drawFractal();
        }

    }


    class buttonSaveHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(); //сохранение в png
            FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);

            int result = chooser.showSaveDialog(jImageDisplay);
            if(result==JFileChooser.APPROVE_OPTION) { //проверка результата операции
                try {
                    ImageIO.write(jImageDisplay.image, "png",chooser.getSelectedFile()); //сохранение изображения на диск
                }catch(Exception exception) {
                    JOptionPane.showMessageDialog(chooser, exception.getMessage(),"Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                //родитель, сообщение,
                }
            }
        }
    }


}