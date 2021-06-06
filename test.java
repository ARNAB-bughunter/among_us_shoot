import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class test extends JFrame{
	private static Container c;
	public static void main(String[] args) {
	JFrame.setDefaultLookAndFeelDecorated(true);	
	test frame=new test();	
	screen panel=new screen();
	c=frame.getContentPane();
    c.setLayout(null);
    frame.setResizable(false);
    frame.setBackground(Color.red);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,400);
    frame.setOpacity(0.9f);
    frame.setLocationRelativeTo(null);
    c.add(panel);
    frame.setVisible(true);   
	}
}

class screen extends JPanel  implements MouseListener,Runnable{
    private  Color c1=new Color(17,57,42);
	private  Color c2=new Color(54,117,77);
	private  Color c3=new Color(27,82,54);
	private  int mouseX=200;
	private  int mouseY=200;
	private  Rectangle point,astroid1,astroid2,astroid3;
	private  int  score=0;
	private  int astroid_x1=35,astroid_y1=30;
	private  int astroid_x2=150,astroid_y2=230;
	private  int astroid_x3=250,astroid_y3=100;
	private  Image astriod_pic;
	private  int speed=100;
	private  int dx1=-3,dy1=+7;
	private  int dx2=-3,dy2=+7;
	private  int dx3=-3,dy3=+7;
	private  Random rand=new Random();



	screen(){
		this.setBounds(0,0,400,400);
		this.setBackground(c2);
		this.addMouseListener(this);
		Toolkit tool=Toolkit.getDefaultToolkit();
		astriod_pic=tool.getImage("astroid.png");
		Thread tt=new Thread(this);
		tt.start();
	}
	public void paint(Graphics g){

		super.paint(g);
		Graphics2D g2=(Graphics2D)g;

		g2.drawImage(astriod_pic,astroid_x1,astroid_y1,this);
		g2.drawImage(astriod_pic,astroid_x2,astroid_y2,this);
		g2.drawImage(astriod_pic,astroid_x3,astroid_y3,this);        		
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(3));

		g2.setColor(new Color(157,93,0));


        g2.setColor(c1);
   		g2.drawRect(mouseX-25,mouseY-25,50,50);

   		


		g2.drawLine(mouseX+10,mouseY,mouseX-10,mouseY);//middle '-'
		g2.drawLine(mouseX,mouseY+10,mouseX,mouseY-10);//middle '|'
		g2.drawLine(mouseX+20,mouseY,mouseX+30,mouseY);
		g2.drawLine(mouseX-20,mouseY,mouseX-30,mouseY);
		g2.drawLine(mouseX,mouseY-20,mouseX,mouseY-30);
		g2.drawLine(mouseX,mouseY+20,mouseX,mouseY+30);


        g2.setColor(c3);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(0,365,mouseX,mouseY);
		g2.drawLine(390,365,mouseX,mouseY);

        g2.setColor(Color.white);
		g2.setFont(new Font("Arial",Font.BOLD,25));
		g2.drawString("Destroyed:"+score,130,350);
	}

	public void run(){
		while(true){

		if(astroid_x1<=-100)
			dx1=3;
		if(astroid_x1>=360)
			dx1=-3;
		if(astroid_y1<=-100)
			dy1=7;
		if(astroid_y1>=360)
			dy1=-7;

		if(astroid_x2<=-100)
			dx2=3;
		if(astroid_x2>=360)
			dx2=-3;
		if(astroid_y2<=-100)
			dy2=7;
		if(astroid_y2>=360)
			dy2=-7;

		if(astroid_x3<=-100)
			dx3=3;
		if(astroid_x3>=360)
			dx3=-3;
		if(astroid_y3<=-100)
			dy3=7;
		if(astroid_y3>=360)
			dy3=-7;



		astroid_x1+=dx1;
		astroid_y1+=dy1;
		astroid_x2+=dx2;
		astroid_y2+=dy2;
		astroid_x3+=dx3;
		astroid_y3+=dy3;

		astroid1=new Rectangle(astroid_x1,astroid_y1,70,70);
		astroid2=new Rectangle(astroid_x2,astroid_y2,70,70);
		astroid3=new Rectangle(astroid_x3,astroid_y3,70,70);
		point=new Rectangle(mouseX-25,mouseY-25,50,50);


		if(astroid1.intersects(point)){
			score++;
			astroid_x1=rand.nextInt(320);
			astroid_y1=rand.nextInt(320);
			
		}

		if(astroid2.intersects(point)){
			score++;
			astroid_x2=rand.nextInt(320);
			astroid_y2=rand.nextInt(320);
		}

		if(astroid3.intersects(point)){
			score++;
			astroid_x3=rand.nextInt(320);
			astroid_y3=rand.nextInt(320);
		}



		repaint();
		try{Thread.sleep(speed);}catch(Exception e){}
		}
	}

  	public void mouseExited(MouseEvent mouse){}
	public void mouseEntered(MouseEvent mouse){}
	public void mouseReleased(MouseEvent mouse){}
	public void mouseClicked(MouseEvent mouse){}
		public void mousePressed(MouseEvent mouse){
		mouseX=mouse.getX();
		mouseY=mouse.getY();
		repaint();
	}

}
