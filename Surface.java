import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Biltekin 
 */
public class Surface extends JPanel implements ActionListener, KeyListener {

    private int space;
    private int artiPuan;
    private int puan;
    private int zaman;
    private int speed;
    private int width = 80;
    private int height = 110 ;
    private int WIDTH = 900;
    private int HEIGHT = 830;
    private int move = 20;
    private int count = 1;

    private Rectangle araba;

    private ArrayList<Rectangle> gelenArabalar;
    
    private ArrayList<Rectangle> puanYildizi;

    private ArrayList<Rectangle> serit;

    private Random rand;
    
    BufferedImage arac;
    BufferedImage arkaplan;
	BufferedImage arac2;
	BufferedImage yildiz;
    
	 Timer t;

    public Surface() {
        t = new Timer(20, this);
        rand = new Random();
        gelenArabalar = new ArrayList<Rectangle>();
       
        puanYildizi = new ArrayList<Rectangle>();

        serit = new ArrayList<Rectangle>();

        araba = new Rectangle(this.WIDTH / 2 - 150, this.HEIGHT - 230, this.width, this.height);

        space = 300;
        speed = 5;
        addKeyListener(this);
        setFocusable(true);

        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        
        addpuanYildizi(true);
        addpuanYildizi(true);
        addpuanYildizi(true);
        addpuanYildizi(true);

        addgelenArabalar(true);
        addgelenArabalar(true);
        addgelenArabalar(true);
        addgelenArabalar(true);
        
        
        
        

        try {
        	arac = ImageIO.read(new File ("C:\\Depo\\ArabaOyunu\\araba.png"));
      		 arkaplan = ImageIO.read(new File ("C:\\Depo\\ArabaOyunu\\yeniyol.png"));
      		 arac2 = ImageIO.read(new File ("C:\\Depo\\ArabaOyunu\\arac2.png"));
      		 yildiz = ImageIO.read(new File ("C:\\Depo\\ArabaOyunu\\yildiz.png"));


   		 } catch (IOException ex) {
   			 Logger.getLogger(Surface.class.getName()).log(Level.SEVERE,null,ex);;
   		 }

        t.start();
    }
  
	

    public void addLine(boolean first) {
        int x = WIDTH / 2 - 8;
        int y = serit.size() * 200;
        if (first) {
        	serit.add(new Rectangle(x, y - gelenArabalar.size(), 15, 70));
        } else {
        	serit.add(new Rectangle(x, serit.get(serit.size() - 1).y - 200, 15, 70));
         
            System.out.println(gelenArabalar.size());
           
            if (first) {
            	serit.add(new Rectangle(x, y - puanYildizi.size(), 15, 70));
            } else {
            	serit.add(new Rectangle(x, serit.get(serit.size() - 1).y - 200, 15, 70));
            System.out.println(puanYildizi.size());

            }}

    }

    public void addgelenArabalar(boolean first) {
        int positionx = rand.nextInt() % 2;
        int x = 0;
        int y = 0;
        int Width = this.width;
        int Height = this.height;
        if (positionx == 0) {
            x = WIDTH / 2 - 120;
        } else {
            x = WIDTH / 2 + 40;
        }
        if (first) {
        	gelenArabalar.add(new Rectangle(x, y - 100 - (gelenArabalar.size() * space), Width, Height));
        } else {
        	gelenArabalar.add(new Rectangle(x, gelenArabalar.get(gelenArabalar.size() - 1).y - 300, Width, Height));
        }
    }
    
    public void addpuanYildizi(boolean first) {
        int positionx = rand.nextInt() % 2;
        int x = 0;
        int y = 0;
        int Width = this.width;
        int Height = this.height;
        if (positionx == 0) {
            x = WIDTH / 2 + 40 ;
        } else {
            x = WIDTH / 2 - 120 ;
        }
        if (first) {
        	puanYildizi.add(new Rectangle(x, y +50 - (puanYildizi.size() * space), Width, Height));
        } else {
        	puanYildizi.add(new Rectangle(x, puanYildizi.get(puanYildizi.size() - 1).y +300, Width, Height));
        }
    }
    
    private void doDrawing(Graphics g) {
        System.out.println(araba.x);
        zaman += 30;
        puan += zaman;
        Graphics2D g2d = (Graphics2D) g;

        //Dýþ Alan
		g2d.drawImage(arkaplan,0,0,arkaplan.getWidth(),arkaplan.getHeight(),this);  // Arkaplaný çiziyor.

        //Yol Seriti
        g2d.setColor(Color.white);
        for (Rectangle cizgi : serit) {
            g2d.fillRect(cizgi.x, cizgi.y, cizgi.width, cizgi.height);  
        }
        
        ImageObserver paintingChild = null;

       
		//Yýldýz
        for (Rectangle ydz : puanYildizi) {
        	g2d.drawImage(yildiz,ydz.x, ydz.y, ydz.width/2, ydz.height/2,paintingChild);
        
        }
        
        //Gelen Arabalar
        for (Rectangle rect : gelenArabalar) {
        	g2d.drawImage(arac2,rect.x, rect.y, rect.width, rect.height,paintingChild); 
        	
        }
      //Arabam
		g2d.drawImage(arac,araba.x, araba.y, araba.width, araba.height, paintingChild);
      
    }
        
       

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle rect;
       
        for (int i = 0; i < gelenArabalar.size(); i++) {
            rect = gelenArabalar.get(i);
            rect.y += speed + 1;

        }

        for (int i = 0; i < gelenArabalar.size(); i++) {
            rect = gelenArabalar.get(i);
            if (rect.y + rect.height > this.HEIGHT) {
            	gelenArabalar.remove(rect);
             addgelenArabalar(false);
            }
        }
        
        for (int i = 0; i < puanYildizi.size(); i++) {
            rect = puanYildizi.get(i);
            rect.y += speed + 1;

        }

        for (int i = 0; i < puanYildizi.size(); i++) {
            rect = puanYildizi.get(i);
            if (rect.y + rect.height > this.HEIGHT) {
            	puanYildizi.remove(rect);
             addpuanYildizi(false);
             
            }
        }

        for (int i = 0; i < serit.size(); i++) {
            rect = serit.get(i);
            rect.y += speed;

        }

        for (int i = 0; i < serit.size(); i++) {
            rect = serit.get(i);
            if (rect.y + rect.height >= this.HEIGHT) {
            	serit.remove(rect);
                addLine(false);
            }
        }
        
     
        
        if (KontrolEt()) {
            t.stop();
            String message = 
            		
            	     "YILDIZ PUANI : " + artiPuan+"\n"
            	    +"\n"
            	    + "TOPLAM PUAN : " +( puan+ artiPuan) /1000 + "\n"
                    +"\n"
                    + "GECEN SURE : " + zaman / 1000.0+ " saniye"
                    ;
         
            JOptionPane.showMessageDialog(this, message);
		
         

            System.exit(0);
        }
    
        repaint();
        if (YildizaCarpti()) {
            

    	
        }

        repaint();
    }
    



    public boolean KontrolEt() {
        for (Rectangle sariAraba : gelenArabalar) {
            if (sariAraba.intersects(araba)) {
                return true;
            }
          }
        
       return false;
    }
    public boolean YildizaCarpti() {

        for (Rectangle ydz :puanYildizi) {
            if (ydz.intersects(araba)) {
            	artiPuan++;

                return true;


            }

          }
   
    return false;
}
        
    public void moveup() {

        if (araba.y - move < 0) {
        	araba.y -= move;
        } else {
        	araba.y -= move;

        }
    }

    public void moveleft() {
        if (araba.x - move < WIDTH / 2 - 200) {
            System.out.println("\b");
        } else {
        	araba.x -= move;
        }
    }

    public void moveright() {
        if (araba.x + move > WIDTH / 2 + 100) {
            System.out.println("\b");
        } else {
        	araba.x += move;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    //Klavye Islemleri

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            moveleft();
        } else {
            System.out.println("sol");
        }
        if (key == KeyEvent.VK_RIGHT) {
            moveright();
        } else {
            System.out.println("sag");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
