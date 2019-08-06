import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        tf.setVisible(true);

        for(;;){
            TimeUnit.MILLISECONDS.sleep(25);
            tf.repaint();
        }
    }
}
