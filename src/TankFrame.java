import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankFrame extends Frame {
    private Tank mytank;
    private Tank enamy;

    public TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(800, 600);

        mytank = new Tank(100, 100, Dir.R);
        enamy = new Tank(200,200,Dir.D);
        //设计模式--->Observer模式:观察者模式
        this.addKeyListener(new TankKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        //需要重新绘制的时候 就会自动调用这个方法
        mytank.paint(g);
        enamy.paint(g);
    }


    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            mytank.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
