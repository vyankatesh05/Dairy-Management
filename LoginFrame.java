import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener 
{
	HashMap<String,String> hm=new HashMap<String,String>();
	Container container = getContentPane();
	JLabel userLabel = new JLabel("USERNAME");
	JLabel passwordLabel = new JLabel("PASSWORD");
	JTextField userTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JButton loginButton = new JButton("LOGIN");
	JButton resetButton = new JButton("RESET");
	JCheckBox showPassword = new JCheckBox("Show Password");
	JLabel background,background1; 
	//Dashboard db;   

    public LoginFrame() throws Exception
    {

//	frame.setTitle("Login Form");        
        setSize(1650,1000);
		setLayout(null);
	ImageIcon icon = new ImageIcon("/home/ubuntu/Project2/Images/logo.png");
	setIconImage(icon.getImage());		
	ImageIcon img = new ImageIcon("/home/ubuntu/Project2/Images/login.png");
	background = new JLabel(img);
	background.setBounds(700,100,150,150);
	add(background); 
	
	ImageIcon img2 = new ImageIcon("/home/ubuntu/Project2/Images/log1.png");
	background1 = new JLabel(img2);
	background1.setBounds(0,285,1650,709);
	add(background1);
        setVisible(true);
	
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	
         
    }

    public void setLayoutManager() 
    {
        container.setLayout(null);
        //container.setIconImage(icon.getImage());
                setLocationAndSize();

    }

    public void setLocationAndSize() 
    {
    	//background.setBounds(600,30,400,400);
    	
        userLabel.setBounds(600, 320, 200, 30);
        passwordLabel.setBounds(600, 390, 200, 30);
        userTextField.setBounds(800, 320, 250, 30);
        passwordField.setBounds(800, 390, 250, 30);
        showPassword.setBounds(800, 450, 250, 30);
        loginButton.setBounds(600, 490, 200, 40);
        resetButton.setBounds(850, 490, 200, 40);
        
        Font f1=new Font("Arial",Font.BOLD,20);
        Font f2=new Font("Arial",Font.PLAIN,18);
        
        userLabel.setFont(f1);
        passwordLabel.setFont(f1);
        userTextField.setFont(f2);
        passwordField.setFont(f2);
        showPassword.setFont(f1);
        loginButton.setFont(f2);
        resetButton.setFont(f2);
        
        addComponentsToContainer();


    }

    public void addComponentsToContainer() 
    {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        //container.add(background);
        addActionEvent();

    }

    public void addActionEvent() 
    {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
    
    try{
        if (e.getSource() == loginButton) 
        {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());
            if (userText.equalsIgnoreCase("prathamesh") && pwdText.equalsIgnoreCase("12345") || userText.equalsIgnoreCase("abcd") && pwdText.equalsIgnoreCase("1234")) 
            {
                JOptionPane.showMessageDialog(this, "Login Successful");
                this.setVisible(false);
	   	new Dashboard(this);
                
                
            } else 
            {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
        if (e.getSource() == resetButton) 
        {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) 
        {
            if (showPassword.isSelected()) 
            {
                passwordField.setEchoChar((char) 0);
            } else 
            {
                passwordField.setEchoChar('*');
            }

	}
        }
        catch(Exception ex)
     	{
        	System.out.println(ex);
     	}
    }

    public static void main(String[] a) throws Exception
    {

	LoginFrame l=new LoginFrame();
        l.setLayoutManager();

        

    
  }
}





