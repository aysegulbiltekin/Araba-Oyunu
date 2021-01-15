import java.awt.EventQueue;
import javax.swing.JFrame;

public class ArabaYarýsýOyunuTest extends JFrame {

    public ArabaYarýsýOyunuTest() {
        initUI();
    }

    private void initUI() {
        Surface surface = new Surface();
        add(surface);
        setTitle("Araba Yarýþý Oyunu"); // Acýlan pencere basligi
        setSize(900, 830);
      
        setLocationRelativeTo(null);  //Acýlan pencerenin ortada acýlmasýný saglýyor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Programin sonlandirilmasini saglýyor.
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	ArabaYarýsýOyunuTest ex = new ArabaYarýsýOyunuTest();
                ex.setVisible(true);
            }
        });
    }
}
