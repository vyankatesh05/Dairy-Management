import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
class Dashboard extends JFrame  implements ActionListener
{	
	
	JTextField t1,t2,t3,t4;	
	JLabel l1,l2,l3,l4,ltag;
	JButton b1,b2,b3,b4,b5,b6,b7,btnlogout;
	JLabel background,background1,background2,background3;
	Connection con=null;
	ResultSet rs=null,rs1=null;
	PreparedStatement pst=null;
	int flag=0;
	Statement st=null;
	LoginFrame l;
	HashMap<String,String> hm=new HashMap<String,String>();
	
	
	Dashboard(LoginFrame l) throws Exception
	{	
		this.l=l;	
		setTitle("DashBoard");
		setLayout(null);
		setSize(1650,1000); //setSize(width,height)
		
		Font f1=new Font("Arial",Font.BOLD,16);
		Font f2=new Font("Serif",Font.BOLD,40);
		
		ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());		
		
		ImageIcon img = new ImageIcon("/home/ubuntu/Project2/Images/image.png");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(600,30,400,400);
		add(background);
		
		
		
		 ImageIcon img2 = new ImageIcon("/home/ubuntu/Project2/Images/newleft.png");
		background2 = new JLabel("",img2,JLabel.CENTER);
		background2.setBounds(100,700,277,300);
		add(background2);
		
		 ImageIcon img3 = new ImageIcon("/home/ubuntu/Project2/Images/newright.png");
		background3 = new JLabel("",img3,JLabel.CENTER);
		background3.setBounds(1300,700,277,300);
		add(background3);
		
		 ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/grass.png");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(0,800,1660,435);
		add(background1);
			
		
		t1=new JTextField(); //buff rate
		t2=new JTextField(); //cow rate
		t3=new JTextField(); //Tank buff
		t4=new JTextField(); //tank cow
		
				
		t1.setFont(f1);
		t2.setFont(f1);
		t3.setFont(f1);
		t4.setFont(f1);
		
		
		
		
		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		
		
		
		
		
		
		l1=new JLabel("Buffalo Milk Per ltr");
		l2=new JLabel(" Cow Milk Per ltr");
		l3=new JLabel("A2 Milk Per ltr");
		l4=new JLabel("SHEAP Milk Per ltr");
		
		ltag=new JLabel("ROYAL LOYALITY");
		
		l1.setFont(f1);	
		l2.setFont(f1);
		l3.setFont(f1);
		l4.setFont(f1);
		
		ltag.setFont(f2);
			
				
		b1= new JButton("Update Rate");
		b2= new JButton("Supplier Payment");
		b3= new JButton("Add New User");
		b4= new JButton("Add Milk Record");
		b5= new JButton("Manage Suppliers");
		b6= new JButton("Manage Products");
		b7= new JButton("Products Billing");
		btnlogout= new JButton("LOG OUT");
		
		
		
		
		
		b1.setFont(f1);
		b2.setFont(f1);
		b3.setFont(f1);
		b4.setFont(f1);
		b5.setFont(f1);
		b6.setFont(f1);
		b7.setFont(f1);
		btnlogout.setFont(f1);
		
		l1.setBounds(100,50,200,50);
		l2.setBounds(100,150,200,50);
		l3.setBounds(1400,50,200,50);
		l4.setBounds(1400,150,200,50);
		
		ltag.setBounds(600,350,500,250);
		
		
		t1.setBounds(100,100,150,50); //setBounds(x,y,w,h) rate text 
		t2.setBounds(100,200,150,50);//
		t3.setBounds(1400,100,150,50);
		t4.setBounds(1400,200,150,50);
		
		t1.setText("   55");
		t2.setText("   48");
		t3.setText("   80");
		t4.setText("   310");
		
		t1.setFont(f1);
		t2.setFont(f1);
		t3.setFont(f1);
		t4.setFont(f1);
		
		
		
		//t1.setFont (new Font("Verdana",Font.BOLD,14));
		//t1.setBorder (new LineBorder(Color.BLACK,3)); 
		//t1.setBackground (Color. LIGHT_GRAY);
				
		b1.setBounds(100,280,150,50); //update
		b2.setBounds(451,560,200,125);//Sup payment
		b3.setBounds(721,560,200,125);//new user
		b4.setBounds(1001,560,200,125);//add milk
		b5.setBounds(451,760,200,125);//suplier management
		b6.setBounds(721,760,200,125);//man pro 
		b7.setBounds(1001,760,200,125);//product
		btnlogout.setBounds(1400,280,150,50);//logout
		
		
		b1.setToolTipText("Click to update rate of milk");
		b2.setToolTipText("Suppliers Payment");
		b3.setToolTipText("Set New Login_id And Password");
		b4.setToolTipText("Record of milk");
		b5.setToolTipText("Manage Suppliers");
		b6.setToolTipText("Manage Products");
		b7.setToolTipText("Billing of Products");
		btnlogout.setToolTipText("Log Out From Here");
		
		
		
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(ltag);
				
		add(t1);
		add(t2);
		add(t3);
		add(t4);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		add(b7);
		add(btnlogout);
				
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		btnlogout.addActionListener(this);
		
		setDefaultCloseOperation(2);
		setVisible(true);
		
	
	}

	public void actionPerformed( ActionEvent ae)
	{
	try{
		  if(ae.getSource()==b1)
	 	{
	   		this.setVisible(false);
	   		new Milktype(this);
	  	} 
	 	else if(ae.getSource()==b2)
	   	{
	       		this.setVisible(false);
	      		 new SupPayment(this);
	   	}
	   
	 	 else if(ae.getSource()==b3)
	   	{
	       		this.setVisible(false);
	      		 new AddUser(this);
	   	} 
	 	 
	 	 else if(ae.getSource()==b4)
	   	{
	       		this.setVisible(false);
	      		 new AddMilk(this);
	   	} 
	 	 
	 	  else if(ae.getSource()==b5)
	   	{
	       		this.setVisible(false);
	      		 new SupManage(this);
	   	} 
	   	
	   	else if(ae.getSource()==b6)
	   	{
	       		this.setVisible(false);
	      		 new ProductManage(this);
	   	} 
	   	else if(ae.getSource()==b7)
	   	{
	       		this.setVisible(false);
	      		 new ProdBill(this);
	   	} 
	   	
	   	else if(ae.getSource()==btnlogout)
	   	{
	       		 this.dispose();
			l.setVisible(true);
			l.userTextField.setText("");
			l.passwordField.setText("");
	   	}
	   
	   	
	   }
	   catch(Exception e)
     	{
        	System.out.println(e);
     	} 
	      
	}
 

public static void main(String args[]) throws Exception
	{
		 Dashboard db=new Dashboard(null);
	}
}



