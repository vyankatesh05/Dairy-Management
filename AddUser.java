import java.io.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AddUser extends JFrame implements ActionListener
{
   JLabel l1,l2,toplabel;
   JLabel background,background1;
   JTextField txtusername;
   JPasswordField txtpaasword;
   JButton btnprev,btnnext,btndelete,btnupdate,btnexit,btnadd,btnsave;
   Connection con=null;
   ResultSet rs=null,rs1=null;
   Statement st=null; 
   PreparedStatement pst=null;
   int flag=0;
   Dashboard db;
   
   AddUser(Dashboard db) throws Exception
   {
      		this.db=db;
      		setLayout(null);
      
     	        ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());		
		ImageIcon img = new ImageIcon("/home/ubuntu/Project2/Images/adduser.png");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(400,160,400,400);
		add(background);
      		
      		
      		ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/grass.png");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(0,800,1660,435);
		add(background1);
      
      
      //setTitle("Product Management");
      l1=new JLabel("Username");
      l2=new JLabel("Password");
            
      toplabel=new JLabel("Add New User");
      
      txtusername=new JTextField();
      txtpaasword=new JPasswordField();
      
      
      
      btnprev=new JButton("<<");
      btnnext=new JButton(">>");
      btnupdate=new JButton("UPDATE");
      btndelete=new JButton("DELETE");      
      btnexit=new JButton("CANCEL");
      btnadd=new JButton("ADD NEW");
      btnsave=new JButton("SAVE");
      
    
      l1.setBounds(280,550,150,50); 
      l2.setBounds(280,650,200,50);     
      
      toplabel.setBounds(650,40,800,200);
      
      
      txtusername.setBounds  (450,550,300,40);
      txtpaasword.setBounds(450,650,300,40);
     

      btnprev.setBounds(310,750,70,50);
      btnnext.setBounds(570,750,70,50);
      
      btnadd.setBounds(1100,250,150,50);
      btnupdate.setBounds(1100,350,150,50);
      btndelete.setBounds(1100,450,150,50); 
      btnsave.setBounds(1100,550,150,50);
      btnexit.setBounds(1100,650,150,50);
      
      
      add(toplabel);
      add(l1);
      add(txtusername);
      add(l2);
      add(txtpaasword);
      
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
      toplabel.setFont(f2);
      
      txtusername.setFont(f1);
      txtpaasword.setFont(f1);
            
      txtusername.setEnabled(false);
      txtpaasword.setEnabled(false);
      btnsave.setEnabled(false);
      
      btnprev.addActionListener(this);
      btnnext.addActionListener(this);
      btndelete.addActionListener(this);
      btnupdate.addActionListener(this);
      btnsave.addActionListener(this);
      btnexit.addActionListener(this);
      btnadd.addActionListener(this);
      
     
     // txtusername.setBackground(Color.red);
     // txtusername.setForeground(Color.blue);
      
      
      setSize(1650,1000); 
      setVisible(true);
      //setResizable(false);  
      setDefaultCloseOperation(2);
      
      Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
      if(con!=null)
      {
         System.out.println("Connection successful");              st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
              
	rs=st.executeQuery("select * from add_user order by username");
	if(rs.first())
	{
	   txtusername.setText(rs.getString(1));
	   txtpaasword.setText(rs.getString(2));
	   	   
	}
	else
	{
	   JOptionPane.showMessageDialog(this,"No record found");
	   System.exit(0);
	}   
      }      		  
   }
   
      boolean validateuser_name(String username)
   {
    /* if(phone.length!=10 ||  !phone.isNumeric())
     {
       JOptionPane.showMessageDialog(null,"Phone must be numberic with 10 digits");
       txtpaasword.setText("");
       return false;
      }  
      else*/
        return true;
   }
  
   
   public void actionPerformed(ActionEvent ae)
   {
    try{
      if(ae.getSource()==btnprev)
      {
         if(!rs.isFirst())
         {
           rs.previous();
           txtusername.setText(rs.getString(1));
	   txtpaasword.setText(rs.getString(2));
	   
	   }
      }
      else if(ae.getSource()==btnnext)
      {
         if(!rs.isLast())
         {
           rs.next();
           txtusername.setText(rs.getString(1));
	   txtpaasword.setText(rs.getString(2));
         }
      }
      else if(ae.getSource()==btndelete)
      {
         int ans=JOptionPane.showConfirmDialog(null,"Are you sure to delete?","Confirmation",JOptionPane.YES_NO_OPTION);
         if(ans==0)
         {
            rs.deleteRow();
            if(rs.next())
            {
               txtusername.setText(rs.getString(1));
	       txtpaasword.setText(rs.getString(2));
	     
            }
            else if(rs.first())
            {              
                txtusername.setText(rs.getString(1));
	        txtpaasword.setText(rs.getString(2));
	    }  
            else
            {
               JOptionPane.showMessageDialog(null,"You have deleted last record, no more records");
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
       

         txtpaasword.setEnabled(true);
         
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
         txtusername.setEnabled(true);
         txtpaasword.setEnabled(true);

         txtusername.setText("");
         txtpaasword.setText("");
       
         btnprev.setEnabled(false);
         btnnext.setEnabled(false);
         btndelete.setEnabled(false);
         btnupdate.setEnabled(false);
         btnsave.setEnabled(true);
         btnadd.setEnabled(false);
         flag=2;
         JOptionPane.showMessageDialog(this,"please press save button to add the record.");
       }
       
       
        else if(ae.getSource()==btnsave)
        { System.out.println("flag="+flag);
           if(txtusername.getText().equals("") || txtpaasword.getText().equals(""))
               JOptionPane.showMessageDialog(this,"All fields are compulsory.");
               
           else if(flag==1)
           {
              if(validateuser_name(txtusername.getText()))
              {
                 
		 rs.updateString("password",txtpaasword.getText());
		 rs.updateRow();

                 txtpaasword.setEnabled(false);
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
             if(validateuser_name(txtusername.getText()))
              {
                 pst=con.prepareStatement("select * from add_user where username=?");
                 pst.setString(1,txtusername.getText());
                 rs1=pst.executeQuery();
                 if(rs1.next())
                 {
                   JOptionPane.showMessageDialog(this,"User Name already exists.");
                   txtusername.setText("");
                 }
                 else
                 {
                 if(rs1!=null)
                   rs1.close(); 
                 if(pst!=null)
                   pst.close();
                  rs.moveToInsertRow();
                  
                 rs.updateString("username",txtusername.getText());
		  rs.updateString("password",txtpaasword.getText());
		  
                 rs.insertRow();
                 rs.first();
                 
                 txtusername.setText(rs.getString(1));
	         txtpaasword.setText(rs.getString(2));
	         
	         
                 txtusername.setEnabled(false);
                 txtpaasword.setEnabled(false);
                 
                 
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
      new AddUser(null);
   }  
}









