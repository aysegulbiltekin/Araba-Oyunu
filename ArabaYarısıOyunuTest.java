import java.awt.EventQueue;
import javax.swing.JFrame;

public class ArabaYar�s�OyunuTest extends JFrame {

    public ArabaYar�s�OyunuTest() {
        initUI();
    }

    private void initUI() {
        Surface surface = new Surface();
        add(surface);
        setTitle("Araba Yar��� Oyunu"); // Ac�lan pencere basligi
        setSize(900, 830);
      
        setLocationRelativeTo(null);  //Ac�lan pencerenin ortada ac�lmas�n� sagl�yor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Programin sonlandirilmasini sagl�yor.
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	ArabaYar�s�OyunuTest ex = new ArabaYar�s�OyunuTest();
                ex.setVisible(true);
            }
        });
    }
}
