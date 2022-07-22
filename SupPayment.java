import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTable;
import java.util.*;
import java.text.*;

/*
import net.sf.dynamicreports.jasper.builder.*;
import net.sf.dynamicreports.report.builder.*;
import net.sf.dynamicreports.report.builder.column.*;
import net.sf.dynamicreports.report.builder.component.*;
import net.sf.dynamicreports.report.builder.datatype.*;
import net.sf.dynamicreports.report.constant.*;
import net.sf.dynamicreports.report.exception.*;
*/

public class SupPayment extends JFrame implements ActionListener,KeyListener
{
   JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,toplabel,background1,background2;
   JTextField txtsup_id,txtresult,txtcheque_no,txtcheque_date;
   JButton btnprint ,btnpayment,btnexit,btngen,btnline;
   JScrollPane s1,s2,s3,sp;
   JSpinner  mspin,yspin;
   JTable j;
   String supname[];
   String milk_type[];
    DefaultTableModel model ;
   JPanel p1;
   ArrayList <Integer> supid;
       ArrayList <Double> mtypeid;
    JComboBox <String> c1;
   JComboBox <String> c2;
   Dashboard db;
  
   Connection con=null;
   ResultSet rs=null,rs1=null,rs2=null,rs3=null;
   Statement st=null,st1=null; 
   PreparedStatement pst=null,pst2=null;
   int flag=0;
  
      
  SupPayment(Dashboard db) throws Exception
  {	
  	//super();
  	this.db=db;
      setTitle("Supplier Payment");
      setLayout(new GridLayout(1,2,50,50));
      
      ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/grass.png");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(0,800,1660,435);
		
	ImageIcon img2 = new ImageIcon("/home/ubuntu/Project2/Images/payment.png");
		background2 = new JLabel("",img2,JLabel.CENTER);
		background2.setBounds(480,150,250,250);	

    
      
      txtsup_id=new JTextField();
      txtcheque_no=new JTextField();
       txtcheque_date=new JTextField();
       
        j = new JTable();
		model = new DefaultTableModel();
                    j.setModel(model);
                    model.addColumn("Date");
                    model.addColumn("Milk Type");
                    model.addColumn("Fat%");
                    model.addColumn("Qty");
                    model.addColumn("Total Amount");
            sp=new JScrollPane(j);
                  
                    
                ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());
      Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
      if(con==null)
          System.exit(0);
      st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
      rs=st.executeQuery("select * from sup_manage order by sup_name");
      if(rs!=null)
      {
         rs.last();
           supname=new String[rs.getRow()];
          supid=new ArrayList<Integer>();
          int i=0;
          rs.first();
          while(true)
         {
          supname[i]=new String(rs.getString(2));
          supid.add(rs.getInt(1)); 
          i++;  
          if(!rs.next())
             break; 
        }
        c1=new JComboBox<String>(supname);
        txtsup_id.setText(""+supid.get(0));
        rs.close();
       }    
	
	JScrollPane s1=new JScrollPane(c1);
                  
	 SpinnerModel value=new SpinnerNumberModel(1,1,12,1);
      mspin = new JSpinner(value);
      
      SpinnerModel value1=new SpinnerNumberModel(2018,2018,2030,1);
      yspin = new JSpinner(value1);
                              
      l1=new JLabel("Supplier Name");
      l2=new JLabel("Supplier ID");
      l3=new JLabel("Months");
      l4=new JLabel("Year");
      l5=new JLabel("Total For Month & Year");
      l6=new JLabel("Cheque no");
      l7=new JLabel("Cheque Date(dd-mm-yyyy)");
      
      
      toplabel=new JLabel("Supplier Payment");
     
      
       txtresult=new JTextField();
       
            
      btnpayment=new JButton("PAYMENT");
      btnprint=new JButton("PRINT");
      btnexit=new JButton("CANCEL");
      btngen=new JButton("GENERATE BILL");
      btnline=new JButton("");
      
      toplabel.setBounds(200,30,500,50);
      
     
      l1.setBounds(50,120,200,30); 
      l2.setBounds(50,190,200,30);
      l3.setBounds(50,260,200,30);
      l4.setBounds(50,330,200,30);
      l5.setBounds(50,450,350,30);
      l6.setBounds(20,700,130,30);
      l7.setBounds(20,750,320,30);
      
      
      s1.setBounds	     (250,120,200,40);//sup name
      txtsup_id.setBounds    (250,190,120,40);//sup id
      mspin.setBounds	     (250,260,200,40);//month
      yspin.setBounds	     (250,330,200,40);//year
      txtresult.setBounds     (450,450,150,40);//tot for m & y 
      txtcheque_no.setBounds (330,700,200,40);//ch no     
      txtcheque_date.setBounds(330,750,200,40);//ch Date
           
      btnline.setBounds(50,550,1200,5); 
      btnprint.setBounds(100,600,150,50);
      btnpayment.setBounds(330,600,150,50);
      btnexit.setBounds(350,800,150,50);
      btngen.setBounds(550,600,150,50);
      
      btnpayment.setEnabled(false);
      
      p1=new JPanel();
      p1.setLayout(null);
      p1.setSize(400,1000);      
      p1.add(s1);
      p1.add(txtsup_id);
      p1.add(txtresult);
      p1.add(txtcheque_no);
      p1.add(txtcheque_date);
     
      p1.add(mspin);
      p1.add(yspin);
      
      p1.add(toplabel);
      p1.add(l1);
      p1.add(l2);
      p1.add(l3);
      p1.add(l4);
      p1.add(l5);
      p1.add(l6);
      p1.add(l7);
      
      p1.add(background1);
      p1.add(background2);
     
      p1.add(btnline); 
      p1.add(btnpayment);
      p1.add(btnprint);
      p1.add(btnexit);
      p1.add(btngen);
      
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
   
      toplabel.setFont(f2);

      txtsup_id.setFont(f3);
      txtresult.setFont(f3);
      txtcheque_no.setFont(f3);
      txtcheque_date.setFont(f3);
      
     
      
      txtsup_id.setEnabled(false);
      txtresult.setEnabled(false);
      
      
      
      btnprint.addActionListener(this);
      btnexit.addActionListener(this);
      btnpayment.addActionListener(this);
      c1.addActionListener(this);
      btngen.addActionListener(this);
      txtcheque_no.addKeyListener(this);
      
      
       Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
      if(con!=null)
      {
         System.out.println("Connection successful");              st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
     
      setSize(1650,1000); 
      setVisible(true);
    }
	
   }
   
   
    public void keyTyped(KeyEvent ke)
   {
      char ch=ke.getKeyChar(); 
      if(ke.getSource()==txtcheque_no)
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
   
   
   
   
   
   
   
   boolean validate_chequedate(String cdate)
   {
      try{
        SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
        java.util.Date d1=sdf.parse(cdate);
        String str=sdf.format(new java.util.Date());
        java.util.Date d2=sdf.parse(str);
        long diff=((d2.getTime()-d1.getTime())/(1000*60*60*24))%365;
        if(diff>0)
           return false;
       return true;
       }
      catch(Exception e)
      {
          return false;
      } 
   }
   
   public void actionPerformed(ActionEvent ae)
   {
   	if(ae.getSource()==btnexit)
   	{
		         
         this.dispose();
	db.setVisible(true);  	
   	
   	}
   
       if(ae.getSource()==c1)
       {
            int index=c1.getSelectedIndex();
            txtsup_id.setText(""+supid.get(index));
       }
       else if(ae.getSource()==btngen)
       {
       try{
           pst2=con.prepareStatement("select * from payment where sup_id=? and month=? and year=?");
           pst2.setInt(1,Integer.parseInt(txtsup_id.getText()));
           pst2.setInt(2,(Integer)mspin.getValue());
           pst2.setInt(3,(Integer)yspin.getValue());
           rs3=pst2.executeQuery();
           if(rs3.next())
           {
               JOptionPane.showMessageDialog(this,"Payment for selected supplier for the selected month and year is already done.");
           }
           else
           {
          pst=con.prepareStatement("select cdate,type_name,fat_per, quantity, ((fat_per/100.0)*milk_rate)*quantity as totamt from daily_collection_details dcd,milktype mt where  mt.type_id=dcd.type_id and sup_id=? and extract(month from cdate)=? and extract(year from cdate)=?");
          pst.setInt(1,Integer.parseInt(txtsup_id.getText()));
          pst.setInt(2,(Integer)mspin.getValue());
          pst.setInt(3,(Integer)yspin.getValue());
          rs=pst.executeQuery();
          double sum=0.0;
          boolean flag=false;
          while(rs.next())
          {
            flag=true;
       model.addRow(new Object[]{rs.getDate(1),rs.getString(2),rs.getInt(3),rs.getInt(4),String.format("%.2f",rs.getDouble(5))}); 
       sum=sum+rs.getDouble(5);
          }
          if(flag==false)
             JOptionPane.showMessageDialog(this,"No collection records for the selected supplier for selected month and year.");
             else{
          txtresult.setText(String.format("%.2f",sum));
          btngen.setEnabled(false);
          btnpayment.setEnabled(true);
          }
          }
         }catch(Exception e)
         {
            System.out.println(e);
         }
       }
       else if(ae.getSource()==btnpayment)
       { 
          if(txtcheque_no.getText().equals("") || txtcheque_date.getText().equals(""))
              JOptionPane.showMessageDialog(this,"Please enter cheque number and/or cheque date.");
          else if(!validate_chequedate(txtcheque_date.getText()))
              JOptionPane.showMessageDialog(this,"Please enter valid cheque date in given format.");
          else
          {
          try{
              st1=con.createStatement();
          rs2=st1.executeQuery("select max(pay_recid) from payment");
          int no;
          if(rs2.next())
            no=rs2.getInt(1)+1;
          else
           no=1;         
          if(st1!=null)
            st1.close();
          if(rs2!=null)
             rs2.close();
             
             pst=con.prepareStatement("insert into payment values(?,?,?,?,?,?,?,?)");
             pst.setInt(1,no);
             SimpleDateFormat sdf=new SimpleDateFormat();
             String str=sdf.format(new java.util.Date());
            java.util.Date d=sdf.parse(str);
            java.sql.Date d1=new java.sql.Date(d.getTime());
             pst.setDate(2,d1);
             pst.setInt(3,Integer.parseInt(txtsup_id.getText()));
             
             pst.setInt(4,((Integer)mspin.getValue()));
             pst.setInt(5,((Integer)yspin.getValue()));
             pst.setDouble(6,Double.parseDouble(txtresult.getText()));
             pst.setDouble(7,Double.parseDouble(txtcheque_no.getText()));
             pst.setString(8,txtcheque_date.getText());
             pst.executeUpdate();
             JOptionPane.showMessageDialog(this,"Payment made successfully.");
             this.dispose();
             db.setVisible(true);
            }
            catch(Exception e)
            {
               System.out.println(e);
            } 
          }
         /* JasperReportBuilder report=DynamicReports.report();
          report
          .columns(
          Columns.column("Date",cdate,DataTypes.dateType()),
          Columns.column("Milk Type",type_name,DataTypes.stringType()),
          Columns.column("Fat Percent",fat_per,DataTypes.integerType()),
          Columns.column("Quantity",quantity,DataTypes.integerType()),                 	
          Columns.column("Amount",totamt,DataTypes.doubleType()))
          .title(
          Components.text("Monthly Payment Of "+c1.getSelectedItem()+" For The Month "+(String)mspin.getValue()+" - "+(String)yspin.getValue())
          .setHorizontalAlignment(HorizontalAlignment.CENTER))
          .pageFooter(Components.pageXofY())
          .setDataSource("select cdate,type_name,fat_per, quantity, ((fat_per/100.0)*milk_rate)*quantity as totamt from daily_collection_details dcd,milktype mt where  mt.type_id=dcd.type_id and sup_id=? and extract(month from cdate)=? and extract(year from cdate)=?",con);
          try{
          	report.show();
          	report.toPdf(new FileOutputStream("report.pdf"));
          		
          	}
          catch (Exception e)
          {
          System.out.println(e);
          	}*/
       }        

   }
   
   public static void main(String args[]) throws Exception
   {
      new SupPayment(null);
   }  
}








            
