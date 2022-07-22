import java.io.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTable;

public class ProdBill extends JFrame implements ActionListener
{
   JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,toplabel,background1,background2;
   JTextField txtprod_name,txtprod_id,txtprod_price,txtsub_total,txttotal,txtpay,txtbalance;
   JButton btnline ,btnadd,btnprint,btnexit;
   JScrollPane s1,s2,s3,sp;
   JTable j;
   JPanel p1;
   JSpinner spin;
   JTextArea bill;
   Connection con=null;
   ResultSet rs=null,rs1=null;
   Statement st=null; 
   PreparedStatement pst=null;
   int flag=0;
   Dashboard db;
      
 ProdBill(Dashboard db) throws Exception
  {	
  	this.db=db;
      setTitle("Billing");
      setLayout(new GridLayout(1,2,50,50));
      ImageIcon img2 = new ImageIcon("/home/ubuntu/Project2/Images/prodbill.png");
		background2 = new JLabel("",img2,JLabel.CENTER);
		background2.setBounds(500,150,300,300); 
		
		 ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/bill1.jpg");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(230,505,430,530);
		
       ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());
      
      
        String[][] data = {
        { "210","BUTTER 700G","BUTTER","1","353","353"},
        { "215","CHEESE 500G","CHEESE","2","285","570"},
        { "218","VANILLA MAGIC","ICE CREAM","3","160","480"},
        { "220","RAJBHOG","ICE CREAM","2","250","500"},
        { "202","BUFFALO MILK 500ML","MILK","1","29","58"}};

	String[] columnNames = { "Product ID","Product Name","Type","Qty","Price","Total" };

	j = new JTable(data, columnNames);
	JScrollPane sp = new JScrollPane(j);
	
        SpinnerModel value=new SpinnerNumberModel(1,1,1200,1);
      	spin = new JSpinner(value);
      
	                              
      l1=new JLabel("Product ID");
      l2=new JLabel("Product Name");
      l3=new JLabel("Qty");
      l4=new JLabel("Price");
      l5=new JLabel("Sub Total");
      l6=new JLabel("Total");
      l7=new JLabel("Pay");
      l8=new JLabel("Balance");
      
      
      toplabel=new JLabel("Product Billing");
            
      txtprod_id=new JTextField();
      txtprod_name=new JTextField();
      txtprod_price=new JTextField();
      txtsub_total=new JTextField();
      txttotal=new JTextField();
      txtpay=new JTextField();
      txtbalance=new JTextField();
      
      bill=new JTextArea();
      
      btnadd=new JButton("ADD");
      btnprint=new JButton("PRINT");
      btnexit=new JButton("EXIT");
      btnline=new JButton(" ");
      
      toplabel.setBounds(200,30,500,50);
      
      bill.setBounds(230,520,530,430);
      
      l1.setBounds(50,120,200,30); 
      l2.setBounds(50,190,200,30);
      l3.setBounds(50,260,200,30);
      l4.setBounds(50,330,200,30);
      l5.setBounds(50,400,200,30);
      
            
      txtprod_id.setBounds    (250,120,120,40);
      txtprod_name.setBounds  (250,190,300,40);
      spin.setBounds          (250,260,70,40);
      txtprod_price.setBounds (250,330,100,40);
      txtsub_total.setBounds  (250,400,100,40);
     
     
      l6.setBounds	  (60,520,150,30);
      txttotal.setBounds  (60,570,150,30);
      l7.setBounds	  (60,630,150,30);
      txtpay.setBounds    (60,690,150,30);
      l8.setBounds	  (60,750,150,30);
      txtbalance.setBounds(60,810,150,30);
      btnprint.setBounds(60,870,150,30);
      btnexit.setBounds(60,925,150,30);
      
      btnline.setBounds(50,480,1200,5); 
      btnadd.setBounds(400,400,150,50);
      
      
      txtprod_id.setText("202");
      txtprod_name.setText("BUFFALO MILK 500ML");
      txtprod_price.setText("29");
      txtbalance.setText("68");
      txttotal.setText("1932");
      txtpay.setText("2000");
      txtsub_total.setText("29");
      
      
      p1=new JPanel();
      p1.setLayout(null);
      p1.setSize(400,1000);
      
      p1.add(toplabel);
      
      p1.add(spin);
      
      p1.add(txtprod_id);
      p1.add(txtprod_name);
      p1.add(txtprod_price);
      p1.add(txtsub_total);
      p1.add(txttotal);
      p1.add(txtpay);
      p1.add(txtbalance);
      
      p1.add(l1);
      p1.add(l2);
      p1.add(l3);
      p1.add(l4);
      p1.add(l5);
      p1.add(l6);
      p1.add(l7);
      p1.add(l8);
      //p1.add(bill);
      p1.add(background2);
      p1.add(background1);
      p1.add(btnadd);
      p1.add(btnprint);
      p1.add(btnexit);
      p1.add(btnline);
      
      
      add(p1);
      add(sp);
      Font f1=new Font("Arial",Font.BOLD,18);
      Font f3=new Font("Arial",Font.PLAIN,18);
      Font f2=new Font("Arial",Font.BOLD,40);
      
      l1.setFont(f1);
      l2.setFont(f1);
      l3.setFont(f1);
      l4.setFont(f1);
      l5.setFont(f1);
      l6.setFont(f1);
      l7.setFont(f1);
      l8.setFont(f1);
      
      
      toplabel.setFont(f2);
      txtprod_name.setFont(f3);
      txtprod_id.setFont(f3);
      txtprod_price.setFont(f3);
      txtsub_total.setFont(f3);
      txttotal.setFont(f3);
      txtpay.setFont(f3);
      txtbalance.setFont(f3);
      spin.setFont(f3);
      bill.setFont(f3);
      sp.setFont(f3);
      
      
      
      txtprod_name.setEnabled(false);
      txtprod_price.setEnabled(false);
      txtsub_total.setEnabled(false);
      txttotal.setEnabled(false);
      txtpay.setEnabled(false);
      txtbalance.setEnabled(false);
      bill.setEnabled(false);
      
      
      btnprint.addActionListener(this);
      btnexit.addActionListener(this);
      btnadd.addActionListener(this);
      btnline.addActionListener(this);
      
       
     
      setSize(1650,1000); 
      setVisible(true);
   
   }
  
   public void actionPerformed(ActionEvent ae)
   {
   try{
   	 if(ae.getSource()==btnexit)
      	{
         	this.dispose();
		db.setVisible(true);
     	 }
     }
      catch(Exception e)
     	{
        	System.out.println(e);
     	} 	   	
   
   }
   
   
   
   public static void main(String args[]) throws Exception
   {
      new ProdBill(null);
   }  


}






