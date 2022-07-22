import java.util.*;
import java.io.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class SupManage extends JFrame implements ActionListener,KeyListener
{
   JLabel l1,l2,l3,l4,l5;
   JTextField txtsup_id,txtsup_name,txtsup_addr,txtsup_cont_no;
   JButton btnprev,btnnext,btndelete,btnupdate,btnexit,btnadd,btnsave;
   Connection con=null;
   ResultSet rs=null,rs1=null,rs2=null;
   Statement st=null,st1=null; 
   PreparedStatement pst=null;
   int flag=0;
   Dashboard db;
   JLabel background,background1;
   SupManage(Dashboard db) throws Exception
   {
      this.db=db;
      setTitle("Supplier Management");
      setLayout(null);
     		ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());		
		ImageIcon img = new ImageIcon("/home/ubuntu/Project2/Images/supmanage.png");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(1150,250,400,400);
		add(background);
		
		ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/grass.png");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(0,800,1660,435);
		add(background1);
      
      l1=new JLabel("Supplier ID");
      l2=new JLabel("Name");
      l3=new JLabel("Address");
      l4=new JLabel("Contact No");
      l5=new JLabel("Supplier Management");
      
      txtsup_id=new JTextField();
      txtsup_addr=new JTextField();
      txtsup_name=new JTextField();
      txtsup_cont_no=new JTextField();
      
      btnprev=new JButton("<<");
      btnnext=new JButton(">>");
      btnupdate=new JButton("UPDATE");
      btndelete=new JButton("DELETE");      
      btnexit=new JButton("EXIT");
      btnadd=new JButton("ADD NEW");
      btnsave=new JButton("SAVE");
      
      l1.setBounds(280,250,200,50); //x,y,width,height
      l2.setBounds(280,350,200,50);
      l3.setBounds(280,450,150,50);
      l4.setBounds(280,550,150,50);      
      l5.setBounds(500,50,800,200);
      
      txtsup_id.setBounds  (450,250,100,40);
      txtsup_name.setBounds(450,350,300,40);
      txtsup_addr.setBounds    (450,450,300,40);
      txtsup_cont_no.setBounds(450,550,175,40);


      btnprev.setBounds(310,650,70,50);
      btnnext.setBounds(570,650,70,50);
      
      btnadd.setBounds(850,250,150,50);
      btnupdate.setBounds(850,350,150,50);
      btndelete.setBounds(850,450,150,50); 
      btnsave.setBounds(850,550,150,50);
      btnexit.setBounds(850,650,150,50);
      
      
      add(l5);
      add(l1);
      add(txtsup_id);
      add(l2);
      add(txtsup_addr);
      add(l3);
      add(txtsup_name);
      add(l4);
      add(txtsup_cont_no);
      add(btnprev);
      add(btnnext);
      add(btndelete);
      add(btnupdate);
      add(btnadd);
      add(btnsave);
      add(btnexit);
      
      Font f1=new Font("Arial",Font.BOLD,16);
      Font f2=new Font("Arial",Font.BOLD,40);
      
      l1.setFont(f1);
      l2.setFont(f1);
      l3.setFont(f1);
      l4.setFont(f1);
      l5.setFont(f2);
      
      txtsup_id.setFont(f1);
      txtsup_addr.setFont(f1);
      txtsup_name.setFont(f1);
      txtsup_cont_no.setFont(f1);
      
      txtsup_id.setEnabled(false);
      txtsup_addr.setEnabled(false);
      txtsup_name.setEnabled(false);
      txtsup_cont_no.setEnabled(false);
      btnsave.setEnabled(false);
      
      btnprev.addActionListener(this);
      btnnext.addActionListener(this);
      btndelete.addActionListener(this);
      btnupdate.addActionListener(this);
      btnsave.addActionListener(this);
      btnexit.addActionListener(this);
      btnadd.addActionListener(this);
      txtsup_cont_no.addKeyListener(this);
      //txtsup_addr.setEditable(false);
     // txtsup_id.setBackground(Color.red);
     // txtsup_id.setForeground(Color.blue);
      
      
      setSize(1650,1000); 
      setVisible(true);
      //setResizable(false);  
      setDefaultCloseOperation(2);
      
      Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
      if(con!=null)
      {
         System.out.println("Connection successful");              st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
              
	rs=st.executeQuery("select * from sup_manage order by sup_id");
	if(rs.first())
	{
	   txtsup_id.setText(rs.getString(1));
	   txtsup_name.setText(rs.getString(2));
	   txtsup_addr.setText(rs.getString(3));
	   txtsup_cont_no.setText(rs.getString(4));
	}
	else
	{
	   JOptionPane.showMessageDialog(this,"No records in Suplier Management.");
	   System.exit(0);
	}   
      }      		  
   }
   
      boolean validatesup_cont_no(String sup_cont_no)
   {
   	
     if(sup_cont_no.length()!=10)
     {
       JOptionPane.showMessageDialog(null,"Phone must be numberic with 10 digits");
       txtsup_cont_no.setText("");
       return false;
      }  
      else
        return true;
   }
  
   
   
   
   
   
    public void keyTyped(KeyEvent ke)
   {
      char ch=ke.getKeyChar(); 
      if(ke.getSource()==txtsup_cont_no)
      {
        if(!((ch>='0' && ch <='9')||(ch==32) ||(ch==8) ||(ch==(char)127)))
           ke.setKeyChar((char)127);       
       }
   }
   
   public void keyPressed(KeyEvent ke)
   {
   }
   
   public void keyReleased(KeyEvent ke)
   {
   }
   
   
   
   
   
   
   
   
   
   
   public void actionPerformed(ActionEvent ae)
   {
    try{
      if(ae.getSource()==btnprev)
      {
         if(!rs.isFirst())
         {
           rs.previous();
           txtsup_id.setText(rs.getString(1));
	   txtsup_name.setText(rs.getString(2));
	   txtsup_addr.setText(rs.getString(3));
	   txtsup_cont_no.setText(rs.getString(4));
	   }
      }
      else if(ae.getSource()==btnnext)
      {
         if(!rs.isLast())
         {
           rs.next();
           txtsup_id.setText(rs.getString(1));
	   txtsup_name.setText(rs.getString(2));
	   txtsup_addr.setText(rs.getString(3));
	   txtsup_cont_no.setText(rs.getString(4));
         }
      }
      else if(ae.getSource()==btndelete)
      {
         int ans=JOptionPane.showConfirmDialog(null,"Are u sure to delete?","Confirmation",JOptionPane.YES_NO_OPTION);
         if(ans==0)
         {
            rs.deleteRow();
            if(rs.next())
            {
               txtsup_id.setText(rs.getString(1));
	        txtsup_name.setText(rs.getString(2));
	       txtsup_addr.setText(rs.getString(3));
	       txtsup_cont_no.setText(rs.getString(4));
            }
            else if(rs.first())
            {              
                txtsup_id.setText(rs.getString(1));
	        txtsup_name.setText(rs.getString(2));
	        txtsup_addr.setText(rs.getString(3));
	        txtsup_cont_no.setText(rs.getString(4));
            }  
            else
            {
               JOptionPane.showMessageDialog(null,"U have deleted last record, no more records");
               if(rs!=null)
                 rs.close();
               if(st!=null)
                 st.close();
               if(con!=null)
                 con.close();    
               System.exit(0);
            }                   
         }
      }
      else if(ae.getSource()==btnupdate)
      {
       
         txtsup_addr.setEnabled(true);
         txtsup_name.setEnabled(true);
         txtsup_cont_no.setEnabled(true);
         btnprev.setEnabled(false);
         btnnext.setEnabled(false);
         btndelete.setEnabled(false);
         btnupdate.setEnabled(false);
         btnsave.setEnabled(true);
         btnadd.setEnabled(false);
           flag=1;
         JOptionPane.showMessageDialog(this,"please press save button to save the changes.");
      
        
       }
       else if(ae.getSource()==btnadd)
       {
         txtsup_id.setEnabled(false);
         txtsup_addr.setEnabled(true);
         txtsup_name.setEnabled(true);
         txtsup_cont_no.setEnabled(true);
         //txtsup_id.setText("");
         txtsup_name.setText("");
         txtsup_addr.setText("");
         txtsup_cont_no.setText("");
         btnprev.setEnabled(false);
         btnnext.setEnabled(false);
         btndelete.setEnabled(false);
         btnupdate.setEnabled(false);
         btnsave.setEnabled(true);
         btnadd.setEnabled(false);
         flag=2;
         
         st1=con.createStatement();
          rs2=st1.executeQuery("select max(sup_id) from sup_manage");
          int no;
          if(rs2.next())
            no=rs2.getInt(1)+1;
          else
           no=1;
          txtsup_id.setText(""+no);
          if(st1!=null)
            st1.close();
          if(rs2!=null)
             rs2.close();
           
         JOptionPane.showMessageDialog(this,"please press save button to add the record.");
       }
       
       
       
        else if(ae.getSource()==btnsave)
        { System.out.println("flag="+flag);
           if(txtsup_id.getText().equals("") || txtsup_name.getText().equals("") || txtsup_addr.getText().equals("") || txtsup_cont_no.getText().equals(""))
               JOptionPane.showMessageDialog(this,"All fields are compulsory.");
               
           else if(flag==1)
           {
              if(validatesup_cont_no(txtsup_cont_no.getText()))
              {
                 
		 rs.updateString("sup_name",txtsup_name.getText().toUpperCase());
		 rs.updateString("sup_addr",txtsup_addr.getText().toUpperCase());
		 rs.updateString("sup_cont_no",txtsup_cont_no.getText());
                 rs.updateRow();
                 txtsup_addr.setEnabled(false);
                 txtsup_name.setEnabled(false);
                 txtsup_cont_no.setEnabled(false);
                 btnprev.setEnabled(true);
                 btnnext.setEnabled(true);
                 btndelete.setEnabled(true);
                 btnsave.setEnabled(false);
                 btnadd.setEnabled(true);
                 btnupdate.setEnabled(true);
                 JOptionPane.showMessageDialog(this,"Record updated successfully.");
                 flag=0;
              }
           } 
           else if(flag==2)
           {
             if(validatesup_cont_no(txtsup_cont_no.getText()))
              {
                 pst=con.prepareStatement("select * from sup_manage where sup_id=?");
                 pst.setInt(1,Integer.parseInt(txtsup_id.getText()));
                 rs1=pst.executeQuery();
                 if(rs1.next())
                 {
                   JOptionPane.showMessageDialog(this,"Supplier ID already exists.");
                   txtsup_id.setText("");
                 }
                 else
                 {
                 if(rs1!=null)
                   rs1.close(); 
                 if(pst!=null)
                   pst.close();
                  rs.moveToInsertRow();
                  
                 rs.updateInt("sup_id",Integer.parseInt(txtsup_id.getText()));
		  rs.updateString("sup_name",txtsup_name.getText().toUpperCase());
		  rs.updateString("sup_addr",txtsup_addr.getText().toUpperCase());
		  rs.updateString("sup_cont_no",txtsup_cont_no.getText());
                 rs.insertRow();
                 rs.first();
                 
                 txtsup_id.setText(rs.getString(1));
	         txtsup_name.setText(rs.getString(2));
	         txtsup_addr.setText(rs.getString(3));
	         txtsup_cont_no.setText(rs.getString(4));
	         
                 txtsup_id.setEnabled(false);
                 txtsup_addr.setEnabled(false);
                 txtsup_name.setEnabled(false);
                 txtsup_cont_no.setEnabled(false);
                 
                 btnprev.setEnabled(true);
                 btnnext.setEnabled(true);
                 btndelete.setEnabled(true);
                 btnsave.setEnabled(false);
                 btnupdate.setEnabled(true);
                 btnadd.setEnabled(true);
                 JOptionPane.showMessageDialog(this,"Record added successfully.");
                 flag=0;
               }  
              }
           }     
        
      }
      else if(ae.getSource()==btnexit)
      {
         if(rs!=null)
            rs.close();
         if(st!=null)
           st.close();
         if(con!=null)
           con.close();            
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
      new SupManage(null);
   }  
}


/*

postgres=# Create table sup_manage (sup_id int primary key,sup_name varchar(40),sup_addr varchar(40),sup_cont_no char(10));
CREATE TABLE
postgres=# insert into sup_manage values(101,'SHARAD SHINDE','64/55 HADAPSAR',8651472569);
INSERT 0 1
postgres=# insert into sup_manage values(102,'VIVEK JAGTAP','PALAKHITAL SASWAD',7536985412);
INSERT 0 1
postgres=# insert into sup_manage values(103,'MAHESH PATIL','PATIL NAGAR PURANDAR',8574965214);

 sup_id |   sup_name    |       sup_addr       | sup_cont_no 
--------+---------------+----------------------+-------------
    101 | SHARAD SHINDE | 64/55 HADAPSAR       | 8651472569
    102 | VIVEK JAGTAP  | PALAKHITAL SASWAD    | 7536985412
    103 | MAHESH PATIL  | PATIL NAGAR PURANDAR | 8574965214





*/














