import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JTable;
import java.util.*;
import java.text.*;

public class AddMilk extends JFrame implements ActionListener,KeyListener
{
   JLabel l1,l2,l3,l4,l5,l6,l7,toplabel,background1;
   JTextField txtdate,txtsup_id,txtquantity,txtrate;
   JButton btndelete,btnupdate,btnexit,btnadd,btnsave;
   JScrollPane s1,s2,s3,sp;
   JSpinner spin;
    SimpleDateFormat sdf;
   JComboBox <String> c1;
   JComboBox <String> c2;
   JTable j;
   JPanel p1;
   String supname[];
   String milk_type[];
   ArrayList <Integer> supid;
    ArrayList <Double> mtypeid;
   Connection con=null;
   ResultSet rs=null,rs1=null;
   Statement st=null; 
   PreparedStatement pst=null,pst1=null;
   int flag=0;
	Dashboard db;

 DefaultTableModel model ;
    
    
  AddMilk(Dashboard db) throws Exception
  {	
  	this.db=db;
      setTitle("Manage Milk Records");
      
       Class.forName("org.postgresql.Driver");
				con=DriverManager.getConnection("jdbc:postgresql://localhost/postgres","postgres","postgres");
      if(con!=null)
      {
         System.out.println("Connection successful");                
    } 
      setLayout(new GridLayout(1,2,50,50));
      ImageIcon img1 = new ImageIcon("/home/ubuntu/Project2/Images/grass.png");
		background1 = new JLabel("",img1,JLabel.CENTER);
		background1.setBounds(0,800,1660,435);
            
   //   String[][] data = {{ "101", "xyz", "cow milk","80","500" }};

		// Column Names
	//	String[] columnNames = { "Supplier ID", "Name", "Milk Type","Fat%","Qty" };

		// Initializing the JTable
	//	j = new JTable(data, columnNames);
		//j.setBounds(700,150,500,300);

		// adding it to 
		j = new JTable();
		model = new DefaultTableModel();
                    j.setModel(model);
                    model.addColumn("Supplier Id");
                    model.addColumn("Supplier Name");
                    model.addColumn("Milk Type");
                    model.addColumn("Fat%");
                    model.addColumn("Qty");
                    
                    
                    
		JScrollPane sp = new JScrollPane(j);
	        j.setEnabled(false);
		
      
     		 ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
		setIconImage(icon.getImage());
		
		
      
      st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
      
      txtsup_id=new JTextField();
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
      
        txtrate=new JTextField();
      txtrate.setEnabled(false);
      
      rs=st.executeQuery("select * from milktype order by type_name");
      if(rs!=null)
      {
         rs.last();
           milk_type=new String[rs.getRow()];
          mtypeid=new ArrayList<Double>();
          int i=0;
          rs.first();
          while(true)
         {
          milk_type[i]=new String(rs.getString(2));
          mtypeid.add(rs.getDouble(3)); 
          i++;   
          if(!rs.next())
             break;
        }
        c2=new JComboBox<String>(milk_type);
        txtrate.setText(""+mtypeid.get(0));
        rs.close();
       }
     
      JScrollPane s2=new JScrollPane(c2);
      
     
      SpinnerModel value=new SpinnerNumberModel(50,50,100,1);
      spin = new JSpinner(value);
      
      
      l1=new JLabel("Date");
      l2=new JLabel("Supplier Name");
      l3=new JLabel("Supplier ID");
      l4=new JLabel("Milk Type");
      l5=new JLabel("Quantity ltrs");
      l6=new JLabel("Fat In%");
      l7=new JLabel("Rate");
      
      toplabel=new JLabel("Add Milk Record");
      
      txtdate=new JTextField();
      txtdate.setEnabled(false);
      sdf=new SimpleDateFormat("dd/MM/yyyy");
      java.util.Date d=java.util.Calendar.getInstance().getTime();
      txtdate.setText(sdf.format(d));
     
      txtquantity=new JTextField();
    
      
      btnupdate=new JButton("UPDATE");
      btndelete=new JButton("DELETE");      
      btnexit=new JButton("CANCEL");
      btnadd=new JButton("ADD RECORD");
      btnsave=new JButton("SAVE");
      
      s1.setBounds(350,150,200,40);//sup name
      s2.setBounds(350,320,200,40);//milk type
      txtrate.setBounds(350,390,200,40);
      spin.setBounds(350,550,200,40);//fat%
      
      l1.setBounds(20,50,100,50); //date
      l2.setBounds(150,150,200,50);//sup name
      l3.setBounds(150,235,150,50);//sup id
      l4.setBounds(150,320,150,50); //milk type
      l5.setBounds(150,450,200,50);   //  qty
      l7.setBounds(150,390,200,30);
      l6.setBounds(150,550,150,50); //fat
      
      toplabel.setBounds(200,30,500,50);
      
      
      txtdate.setBounds  (20,100,150,40);
      txtsup_id.setBounds(350,235,80,40);
      txtquantity.setBounds(350,450,300,40);
      

            
      btnadd.setBounds(150,620,150,50);
      btnupdate.setBounds(350,720,150,50);
      btndelete.setBounds(150,720,150,50); 
      btnsave.setBounds(350,720,150,50);
      btnexit.setBounds(350,620,150,50);
      
      p1=new JPanel();
      p1.setLayout(null);
      p1.setSize(400,1000);
      
      p1.add(s1);
      p1.add(s2);
      p1.add(spin);
      p1.add(toplabel);
      p1.add(l1);
      p1.add(txtdate);
      p1.add(l2);
      p1.add(txtquantity);
      p1.add(l3);
      p1.add(txtsup_id);
      p1.add(l4);
      p1.add(l5);
      p1.add(l6);
      p1.add(txtrate);
      p1.add(l7);
      p1.add(btndelete);
      p1.add(background1);
      p1.add(btnadd);
      p1.add(btnsave);
      p1.add(btnexit);
      
      
      add(p1);
      add(sp);
      Font f1=new Font("Arial",Font.BOLD,18);
      Font f3=new Font("Arial",Font.PLAIN,18);
      Font f2=new Font("Arial",Font.BOLD,40);
      Font f4=new Font("serif",Font.PLAIN,16);
      
      l1.setFont(f1);
      l2.setFont(f1);
      l3.setFont(f1);
      l4.setFont(f1);
      l5.setFont(f1);
      l6.setFont(f1);
      l7.setFont(f1);      
      
      toplabel.setFont(f2);
      txtdate.setFont(f1);
      txtquantity.setFont(f1);
      txtsup_id.setFont(f1);
      txtrate.setFont(f1);
      c1.setFont(f3);
      c2.setFont(f3);
      sp.setFont(f4);
      j.setFont(f4);
      
      spin.setFont(f3);
      
	txtsup_id.setEnabled(false);
        
      
      btndelete.addActionListener(this);
      btnupdate.addActionListener(this);
      btnsave.addActionListener(this);
      btnexit.addActionListener(this);
      btnadd.addActionListener(this);
      c1.addActionListener(this);
       c2.addActionListener(this);
      txtquantity.addKeyListener(this);
        setSize(1650,1000); 
      setVisible(true);
    
	
   }
   
   public void keyTyped(KeyEvent ke)
   {
      char ch=ke.getKeyChar(); 
      if(ke.getSource()==txtquantity)
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
       
       else if(ae.getSource()==c2)
       {
            int index=c2.getSelectedIndex();
            txtrate.setText(""+mtypeid.get(index));
       }
       else if(ae.getSource()==btnadd)
       {
         if(txtquantity.getText().equals(""))
              JOptionPane.showMessageDialog(this,"Please Enter Quantity.");
         else
         {    
         boolean flag=true;
       	for(int i=0;i<j.getRowCount();i++)
       	{
       		if(((String)j.getValueAt(i,0)).equals(txtsup_id.getText()) && ((String)j.getValueAt(i,2)).equals(c2.getSelectedItem()))
       		 {
       		      JOptionPane.showMessageDialog(this,"For the selected supplier, with selected milk type, record is already exists.");
       		      flag=false;
       		      break;
       		 }       	
       	} 
       	if(flag==true)
         	model.addRow(new Object[]{txtsup_id.getText(), c1.getSelectedItem(),c2.getSelectedItem(),spin.getValue(),txtquantity.getText()}); 
         	
       }
      }
      else if(ae.getSource()==btnsave)
      {
      	try{
        if(j.getRowCount()!=0)
        {
         int recid;
         st=con.createStatement();
         rs=st.executeQuery("select max(recid) from daily_collection_details");
         if(rs!=null)
         {
             rs.next();
             recid=rs.getInt(1)+1;
             rs.close();
         }
         else
            recid=1;
            System.out.println(recid);
         
        for(int i=0;i<j.getRowCount();i++)
       	{
       	 String milktype=(String)j.getValueAt(i,2);
       	 System.out.println(milktype);
       	 pst1=con.prepareStatement("select type_id from milktype where type_name=?");
       	 pst1.setString(1,milktype);
         rs1=pst1.executeQuery();
         int milktypeid=0;
         if(rs1!=null)
         {
         	rs1.next();
             milktypeid=rs1.getInt(1);
             rs1.close();
         }
         java.util.Date d1=sdf.parse(txtdate.getText());
         java.sql.Date d2=new java.sql.Date(d1.getTime());
          String supid=(String)j.getValueAt(i,0);
          Integer fatper=(Integer)j.getValueAt(i,3);
          String qty=(String)j.getValueAt(i,4);
          pst1=con.prepareStatement("select * from daily_collection_details where cdate=?and sup_id=? and type_id=?");

          pst1.setDate(1,d2);
          pst1.setInt(2,Integer.parseInt(supid));
          pst1.setInt(3,milktypeid);
          rs1=pst1.executeQuery();
          if(rs1.next())
             JOptionPane.showMessageDialog(this,"Today's entry for supplier "+supid+" for the milk type "+milktype+" exists.");
          else
          {
         pst=con.prepareStatement("insert into daily_collection_details values(?,?,?,?,?,?)");
         pst.setInt(1,recid);
         pst.setDate(2,d2);   
         pst.setInt(3,Integer.parseInt(supid));
         pst.setInt(4,milktypeid);
         pst.setInt(5,fatper); 
         pst.setInt(6,Integer.parseInt(qty));
         pst.executeUpdate();
         }
        } 
        JOptionPane.showMessageDialog(this,"all Records are saved successfully.");
       // this.dispose();
        
      }
      else
         JOptionPane.showMessageDialog(this,"No Records In table.");
         }
         catch(Exception e)
         {System.out.println(e); }
      }
      
   }
   
   public static void main(String args[]) throws Exception
   {
      new AddMilk(null);
   }  
}









