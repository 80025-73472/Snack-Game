package snackGame;

import javax.swing.*;
import java.awt.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel  implements ActionListener {

    private Image apple;
    private Image dot;
    private  Image head;

    private final int All_DOTS =900;
    private final int DOT_SIZE=10;
    private final int RANDOM_POSITION=29;

    private int apple_x;
    private  int apple_y;



    private final int[] x=new int[All_DOTS];
    private final int[] y=new int[All_DOTS];

    private boolean leftDirection=false;
    private boolean rightDirection=true;
    private boolean upDirection=false;
    private boolean downDirection=false;
private boolean inGame=true;


    private int dots;
    private Timer timer;
  //  private final int[] x=new int[];
    Board(){

        addKeyListener(new TAdapter());

    setBackground(Color.WHITE);
    setFocusable(true);

    loadImages();
    initGame();
    }
    public void loadImages(){
    ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("snackGame/icons/apple.png"));
          apple =i1.getImage();

    ImageIcon i2= new ImageIcon(ClassLoader.getSystemResource("snackGame/icons/dot.png"));
          dot=i2.getImage();

    ImageIcon i3= new ImageIcon(ClassLoader.getSystemResource("snackGame/icons/head.png"));
        head=i3.getImage();


    }

    public void initGame() {
        dots=2;
        for(int i=0; i<dots; i++){
             y[i]=200;
             x[i]=200- i * DOT_SIZE;
        }
        locateApple();

        Timer timer=new Timer(140,this);
        timer.start();
    }
    public void locateApple(){
         int r= (int) (Math.random() * RANDOM_POSITION);
         apple_x =r*DOT_SIZE;
         r= (int)(Math.random() * RANDOM_POSITION);
         apple_y = r * DOT_SIZE;

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        draw(g);
    }
    public void draw(Graphics g){
        g.drawImage(apple,apple_x,apple_y,this);
              for(int i=0;i<dots;i++){
                 if(i==0){
                      g.drawImage(head,x[i],y[i],this);
                 }
                 else {
                      g.drawImage(dot,x[i],y[i],this);
                 }
               }
          Toolkit.getDefaultToolkit().sync();
    }
    public void move(){
         for(int i=dots;i>0;i--){
              x[i]=x[i- 1];
             y[i]=y[i-1];
        }
      if(leftDirection){
             x[0]=x[0] -DOT_SIZE;
        }
              if(rightDirection){
                     x[0]=x[0] + DOT_SIZE;
              }
                    if(upDirection){
                          y[0]=y[0] -DOT_SIZE;
                    }
                         if(downDirection){
                               y[0]=y[0] + DOT_SIZE;
                          }
    }
    public void checkApple(){
         if((x[0] == apple_x) && y[0] ==apple_y){
               dots++;
              locateApple();
        }
    }
    public void checkCollision(){
        for(int i=dots;i>0;i--){
            if ((i>4) && (x[0]==x[i]) && (y[0]==y[i])){
                inGame=false;
            }
        }

        if(y[0]>=300){
            inGame=false;
        }
        if(x[0]>=300){
            inGame=false;
        }
        if(y[0]<0){
            inGame=false;
        }
        if(x[0]<0){
            inGame=false;
        }
        if(!inGame){
            timer.stop();
        }

    }
    public void actionPerformed(ActionEvent ae){
         checkApple();
            move();
            repaint();
    }
    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
             int key =e.getKeyCode();
             if(key== KeyEvent.VK_LEFT  && (!rightDirection)){
                 leftDirection =true;
                 upDirection=false;
                 downDirection=false;
            }
                if(key== KeyEvent.VK_RIGHT  && (!leftDirection)){
                  upDirection=false;
                  rightDirection=true;
                  downDirection=false;
               }
                    if(key== KeyEvent.VK_UP  && (!downDirection)){
                            leftDirection =false;
                            upDirection=true;
                             rightDirection=false;

                    }
             if(key== KeyEvent.VK_DOWN  && (!upDirection)){
                       leftDirection =false;
                       rightDirection=false;
                        downDirection=true;
             }
        }
    }
}
