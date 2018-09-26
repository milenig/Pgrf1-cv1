package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class pixeltest {

    private JFrame window; //objekt pro zapis pixelu
    private BufferedImage img; //panel pro vykresleni Buffered Image
    private JPanel panel;

    public pixeltest() {
        window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //zastaveni programu po zavreni okna
        window.setSize( 800, 600);
        window.setTitle("PGRF cviceni");

        //vytvoreni img, nastavni rozmeru (a nastaveni typu, ale ten nas nezajima)
        img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);

        //inicializace panelu, do ktereho budeme kreslit img
        panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paintComponents(g);
                //pri prekleseni panelu zajisti vykresleni img
                g.drawImage(img,0,0,null);
            }
        };
        //pridat panel do okna a zobrazit okno
        window.add(panel);
        window.setVisible(true);

        drawPixel(100,50,Color.GREEN.getRGB()); //0x00ff00 == Color.GREEN.getRGB()

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawPixel(e.getX(),e.getY(),Color.YELLOW.getRGB());
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawPixel(e.getX(),e.getY(),Color.RED.getRGB());
            }
        });
    }

    private void drawPixel (int x, int y, int color){
        //nastavit pixel do img
        img.setRGB(x, y, color);
        panel.getGraphics().drawImage(img,0,0,null); //zobrazi akualni img
    }

    public static void main(String[] args) {
        new pixeltest();
    }
}
