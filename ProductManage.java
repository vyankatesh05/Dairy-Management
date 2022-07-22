import java.io.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class ProductManage extends JFrame implements ActionListener
{
   JLabel l1,l2,l3,l4,l5,toplabel;
   JTextField txtprod_id,txtprod_name,txtprod_type,txtprod_rate,txtprod_exp_date;
   JButton btnprev,btnnext,btndelete,btnupdate,btnexit,btnadd,btnsave;
   Connection con=null;
   ResultSet rs=null,rs1=null,rs2=null;
   Statement st=null,st1=null; 
   PreparedStatement pst=null;
   int flag=0;
   Dashboard db;
   JLabel background,background1;
   
   
   
   ProductManage(Dashboard db) throws Exception
   {
      this.db=db;
      setTitle("Product Management");
      setLayout(null);
      
      
		ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/grass.png");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(0,800,1660,435);
		add(background1);      
      
     		 ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());
		ImageIcon img = new ImageIcon("/home/ubuntu/Project2/Images/product.png");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(800,450,700,454);
		add(background);
      
      l1=new JLabel("Product ID");
      l2=new JLabel("Product Name");
      l3=new JLabel("Product Type");
      l4=new JLabel("Product Rate");
      l5=new JLabel("Expiry Date");
      
      toplabel=new JLabel("Product Management");
      
      txtprod_id=new JTextField();
      txtprod_type=new JTextField();
      txtprod_name=new JTextField();
      txtprod_rate=new JTextField();
      txtprod_exp_date=new JTextField();
      
      
      btnprev=new JButton("<<");
      btnnext=new JButton(">>");
      btnupdate=new JButton("UPDATE");
      btndelete=new JButton("DELETE");      
      btnexit=new JButton("CANCEL");
      btnadd=new JButton("ADD NEW");
      btnsave=new JButton("SAVE");
      
      l1.setBounds(280,250,200,50); //x,y,width,height
      l2.setBounds(280,350,200,50);
      l3.setBounds(280,450,150,50);
      l4.setBounds(280,550,150,50); 
      l5.setBounds(280,650,200,50);     
      
      toplabel.setBounds(500,50,800,200);
      
      
      txtprod_id.setBounds  (450,250,100,40);
      txtprod_name.setBounds(450,350,300,40);
      txtprod_type.setBounds    (450,450,300,40);
      txtprod_rate.setBounds(450,550,100,40);
      txtprod_exp_date.setBounds(450,650,130,40);

      btnprev.setBounds(310,750,70,50);
      btnnext.setBounds(570,750,70,50);
      
      btnadd.setBounds(950,250,150,50);
      btnupdate.setBounds(1150,250,150,50);
      btndelete.setBounds(950,350,150,50); 
      btnsave.setBounds(1150,350,150,50);
      btnexit.setBounds(400,750,150,50);
      
      
      add(toplabel);
      add(l1);
      add(txtprod_id);
      add(l2);
      add(txtprod_type);
      add(l3);
      add(txtprod_name);
      add(l4);
      add(txtprod_rate);
      add(l5);
      add(txtprod_exp_date);
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
      l5.setFont(f1);
      
      toplabel.setFont(f2);
      
      txtprod_id.setFont(f1);
      txtprod_type.setFont(f1);
      txtprod_name.setFont(f1);
      txtprod_rate.setFont(f1);
      txtprod_exp_date.setFont(f1);
      
      txtprod_id.setEnabled(false);
      txtprod_type.setEnabled(false);
      txtprod_name.setEnabled(false);
      txtprod_rate.setEnabled(false);
      txtprod_exp_date.setEnabled(false);
      btnsave.setEnabled(false);
      
      btnprev.addActionListener(this);
      btnnext.addActionListener(this);
      btndelete.addActionListener(this);
      btnupdate.addActionListener(this);
      btnsave.addActionListener(this);
      btnexit.addActionListener(this);
      btnadd.addActionListener(this);
      
     
     // txtprod_id.setBackground(Color.red);
     // txtprod_id.setForeground(Color.blue);
      
      
      setSize(1650,1000); 
      setVisible(true);
      //setResizable(false);  
      setDefaultCloseOperation(2);
      
      Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
      if(con!=null)
      {
         System.out.println("Connection successful");              st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
              
	rs=st.executeQuery("select * from prod_manage order by prod_id");
	if(rs.first())
	{
	   txtprod_id.setText(rs.getString(1));
	   txtprod_name.setText(rs.getString(2));
	   txtprod_type.setText(rs.getString(3));
	   txtprod_rate.setText(rs.getString(4));
	   txtprod_exp_date.setText(rs.getString(5));
	   
	}
	else
	{
	   JOptionPane.showMessageDialog(this,"No records in Product Management.");
	   System.exit(0);
	}   
      }      		  
   }
   
      boolean validateprod_rate(String prod_rate)
   {
    /* if(phone.length!=10 ||  !phone.isNumeric())
     {
       JOptionPane.showMessageDialog(null,"Phone must be numberic with 10 digits");
       txtprod_name.setText("");
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
           txtprod_id.setText(rs.getString(1));
	   txtprod_name.setText(rs.getString(2));
	   txtprod_type.setText(rs.getString(3));
	   txtprod_rate.setText(rs.getString(4));
	   txtprod_exp_date.setText(rs.getString(5));
	   }
      }
      else if(ae.getSource()==btnnext)
      {
         if(!rs.isLast())
         {
           rs.next();
           txtprod_id.setText(rs.getString(1));
	   txtprod_name.setText(rs.getString(2));
	   txtprod_type.setText(rs.getString(3));
	   txtprod_rate.setText(rs.getString(4));
	   txtprod_exp_date.setText(rs.getString(5));
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
               txtprod_id.setText(rs.getString(1));
	        txtprod_name.setText(rs.getString(2));
	       txtprod_type.setText(rs.getString(3));
	       txtprod_rate.setText(rs.getString(4));
	       txtprod_exp_date.setText(rs.getString(5));
            }
            else if(rs.first())
            {              
                txtprod_id.setText(rs.getString(1));
	        txtprod_name.setText(rs.getString(2));
	        txtprod_type.setText(rs.getString(3));
	        txtprod_rate.setText(rs.getString(4));
	        txtprod_exp_date.setText(rs.getString(5));
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
       
         txtprod_type.setEnabled(true);
         txtprod_name.setEnabled(true);
         txtprod_rate.setEnabled(true);
         txtprod_exp_date.setEnabled(true);
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
         txtprod_id.setEnabled(false);
         txtprod_type.setEnabled(true);
         txtprod_name.setEnabled(true);
         txtprod_rate.setEnabled(true);
         txtprod_exp_date.setEnabled(true);
         
        // txtprod_id.setText("");
         txtprod_name.setText("");
         txtprod_type.setText("");
         txtprod_rate.setText("");
         txtprod_exp_date.setText("");
         btnprev.setEnabled(false);
         btnnext.setEnabled(false);
         btndelete.setEnabled(false);
         btnupdate.setEnabled(false);
         btnsave.setEnabled(true);
         btnadd.setEnabled(false);
         flag=2;
         
         st1=con.createStatement();
          rs2=st1.executeQuery("select max(prod_id) from prod_manage");
          int no;
          if(rs2.next())
            no=rs2.getInt(1)+1;
          else
           no=1;
          txtprod_id.setText(""+no);
          if(st1!=null)
            st1.close();
          if(rs2!=null)
             rs2.close();
         
         JOptionPane.showMessageDialog(this,"please press save button to add the record.");
       }
       
       
        else if(ae.getSource()==btnsave)
        { System.out.println("flag="+flag);
           if(txtprod_id.getText().equals("") || txtprod_name.getText().equals("") || txtprod_type.getText().equals("") || txtprod_rate.getText().equals("")|| txtprod_exp_date.getText().equals(""))
               JOptionPane.showMessageDialog(this,"All fields are compulsory.");
               
           else if(flag==1)
           {
              if(validateprod_rate(txtprod_rate.getText()))
              {
                 
		 rs.updateString("prod_name",txtprod_name.getText().toUpperCase());
		 rs.updateString("prod_type",txtprod_type.getText().toUpperCase());
		 rs.updateFloat("prod_rate",Float.parseFloat(txtprod_rate.getText()));
		 rs.updateString("prod_exp_date",txtprod_exp_date.getText());
		 
                 rs.updateRow();
                 txtprod_type.setEnabled(false);
                 txtprod_name.setEnabled(false);
                 txtprod_rate.setEnabled(false);
                 txtprod_exp_date.setEnabled(false);
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
             if(validateprod_rate(txtprod_rate.getText()))
              {
                 pst=con.prepareStatement("select * from prod_manage where prod_id=?");
                 pst.setInt(1,Integer.parseInt(txtprod_id.getText()));
                 rs1=pst.executeQuery();
                 if(rs1.next())
                 {
                   JOptionPane.showMessageDialog(this,"Product ID already exists.");
                   txtprod_id.setText("");
                 }
                 else
                 {
                 if(rs1!=null)
                   rs1.close(); 
                 if(pst!=null)
                   pst.close();
                  rs.moveToInsertRow();
                  
                 rs.updateInt("prod_id",Integer.parseInt(txtprod_id.getText()));
		  rs.updateString("prod_name",txtprod_name.getText().toUpperCase());
		  rs.updateString("prod_type",txtprod_type.getText().toUpperCase());
		  rs.updateFloat("prod_rate",Float.parseFloat(txtprod_rate.getText()));
		  rs.updateString("prod_exp_date",txtprod_exp_date.getText());
                 rs.insertRow();
                 rs.first();
                 
                 txtprod_id.setText(rs.getString(1));
	         txtprod_name.setText(rs.getString(2));
	         txtprod_type.setText(rs.getString(3));
	         txtprod_rate.setText(rs.getString(4));
	         txtprod_exp_date.setText(rs.getString(5));
	         
                 txtprod_id.setEnabled(false);
                 txtprod_type.setEnabled(false);
                 txtprod_name.setEnabled(false);
                 txtprod_rate.setEnabled(false);
                 txtprod_exp_date.setEnabled(false);
                 
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
      new ProductManage(null);
   }  
}









