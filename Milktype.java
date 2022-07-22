//table milktype(type_id int primary key,type_name varchar(30),milk_rate float);


import java.io.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Milktype extends JFrame implements ActionListener
{
   JLabel l1,l2,l3,toplabel;
   JTextField txttype_id,txttype_name,txtmilk_rate;
   JButton btnprev,btnnext,btndelete,btnupdate,btnexit,btnadd,btnsave;
   Connection con=null;
   ResultSet rs=null,rs1=null,rs2=null;
   Statement st=null,st1=null; 
   PreparedStatement pst=null;
   int flag=0;
   Dashboard db;
      JLabel background,background1;
   
   
   
   Milktype(Dashboard db) throws Exception
   {
      this.db=db;
      setTitle("Milk Type");
      setLayout(null);
      
     		 ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/grass.png");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(0,800,1660,435);
		add(background1);
     		 
     		 ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());
		ImageIcon img = new ImageIcon("/home/ubuntu/Project2/Images/type.jpg");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(280,650,390,285);
		add(background);
		
		
		
      
      l1=new JLabel("Type ID");
      l2=new JLabel("Type Name");
      l3=new JLabel("Milk Rate");
      
      
      toplabel=new JLabel("Milk Rate And Type");
      
      txttype_id=new JTextField();
      txtmilk_rate=new JTextField();
      txttype_name=new JTextField();
      
      
      
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
           
      
      toplabel.setBounds(500,50,800,200);
      
      
      txttype_id.setBounds  (450,250,100,40);
      txttype_name.setBounds(450,350,300,40);
      txtmilk_rate.setBounds    (450,450,300,40);
      

      btnprev.setBounds(310,550,70,50);
      btnnext.setBounds(570,550,70,50);
      
      btnadd.setBounds(1100,250,150,50);
      btnupdate.setBounds(1100,350,150,50);
      btndelete.setBounds(1100,450,150,50); 
      btnsave.setBounds(1100,550,150,50);
      btnexit.setBounds(1100,650,150,50);
      
      
      add(toplabel);
      add(l1);
      add(txttype_id);
      add(l2);
      add(txtmilk_rate);
      add(l3);
      add(txttype_name);
      
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
      
      
      toplabel.setFont(f2);
      
      txttype_id.setFont(f1);
      txtmilk_rate.setFont(f1);
      txttype_name.setFont(f1);
      
      
      txttype_id.setEnabled(false);
      txtmilk_rate.setEnabled(false);
      txttype_name.setEnabled(false);
      
      btnsave.setEnabled(false);
      
      btnprev.addActionListener(this);
      btnnext.addActionListener(this);
      btndelete.addActionListener(this);
      btnupdate.addActionListener(this);
      btnsave.addActionListener(this);
      btnexit.addActionListener(this);
      btnadd.addActionListener(this);
      
           
      setSize(1650,1000); 
      setVisible(true);
      //setResizable(false);  
      setDefaultCloseOperation(2);
      
      Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
      if(con!=null)
      {
         System.out.println("Connection successful");              st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
              
	rs=st.executeQuery("select * from milktype order by type_id");
	if(rs.first())
	{
	   txttype_id.setText(rs.getString(1));
	   txttype_name.setText(rs.getString(2));
	   txtmilk_rate.setText(rs.getString(3));
	   
	}
	else
	{
	   JOptionPane.showMessageDialog(this,"No records in  Milk Type.");
	   System.exit(0);
	}   
      }      		  
   }
   
      boolean validatemilk_rate(String milk_rate)
   {
    /* if(phone.length!=10 ||  !phone.isNumeric())
     {
       JOptionPane.showMessageDialog(null,"Phone must be numberic with 10 digits");
       txttype_name.setText("");
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
           txttype_id.setText(rs.getString(1));
	   txttype_name.setText(rs.getString(2));
	   txtmilk_rate.setText(rs.getString(3));
	   }
      }
      else if(ae.getSource()==btnnext)
      {
         if(!rs.isLast())
         {
           rs.next();
           txttype_id.setText(rs.getString(1));
	   txttype_name.setText(rs.getString(2));
	   txtmilk_rate.setText(rs.getString(3));
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
               txttype_id.setText(rs.getString(1));
	        txttype_name.setText(rs.getString(2));
	       txtmilk_rate.setText(rs.getString(3));
	       
            }
            else if(rs.first())
            {              
                txttype_id.setText(rs.getString(1));
	        txttype_name.setText(rs.getString(2));
	        txtmilk_rate.setText(rs.getString(3));
	        
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
       
         txtmilk_rate.setEnabled(true);
         txttype_name.setEnabled(true);
         
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
         txttype_id.setEnabled(true);
         txtmilk_rate.setEnabled(true);
         txttype_name.setEnabled(true);
         
         txttype_id.setText("");
         txttype_name.setText("");
         txtmilk_rate.setText("");
         
         btnprev.setEnabled(false);
         btnnext.setEnabled(false);
         btndelete.setEnabled(false);
         btnupdate.setEnabled(false);
         btnsave.setEnabled(true);
         btnadd.setEnabled(false);
         flag=2;
         /*
          st1=con.createStatement();
          rs2=st.executeQuery("select max(type_id) from milktype");
          int no;
          if(rs2.next())
            no=rs2.getInt(1)+1;
          else
           no=1;
          txttype_id.setText(""+no);
          if(st1!=null)
            st1.close();
          if(rs2!=null)
             rs2.close(); 
             */
          
         JOptionPane.showMessageDialog(this,"please press save button to add the record.");
       }
       
       
        else if(ae.getSource()==btnsave)
        { System.out.println("flag="+flag);
           if(txttype_id.getText().equals("") || txttype_name.getText().equals("") || txtmilk_rate.getText().equals(""))
               JOptionPane.showMessageDialog(this,"All fields are compulsory.");
               
           else if(flag==1)
           {
              if(validatemilk_rate(txtmilk_rate.getText()))
              {
                 
		 rs.updateString("type_name",txttype_name.getText().toUpperCase());
		 rs.updateFloat("milk_rate",Float.parseFloat(txtmilk_rate.getText()));
		 
		 
                 rs.updateRow();
                 txtmilk_rate.setEnabled(false);
                 txttype_name.setEnabled(false);
                 
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
             if(validatemilk_rate(txtmilk_rate.getText()))
              {
                 pst=con.prepareStatement("select * from milktype where type_id=?");
                 pst.setInt(1,Integer.parseInt(txttype_id.getText()));
                 rs1=pst.executeQuery();
                 if(rs1.next())
                 {
                   JOptionPane.showMessageDialog(this,"Type ID already exists.");
                   txttype_id.setText("");
                 }
                 else
                 {
                 if(rs1!=null)
                   rs1.close(); 
                 if(pst!=null)
                   pst.close();
                  rs.moveToInsertRow();
                  
                 rs.updateInt("type_id",Integer.parseInt(txttype_id.getText()));
		  rs.updateString("type_name",txttype_name.getText().toUpperCase());
		  rs.updateFloat("milk_rate",Float.parseFloat(txtmilk_rate.getText()));
		  
                 rs.insertRow();
                 rs.first();
                 
                 txttype_id.setText(rs.getString(1));
	         txttype_name.setText(rs.getString(2));
	         txtmilk_rate.setText(rs.getString(3));
	         
	         
                 txttype_id.setEnabled(false);
                 txtmilk_rate.setEnabled(false);
                 txttype_name.setEnabled(false);
                
                 
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
      new Milktype(null);
   }  
}









