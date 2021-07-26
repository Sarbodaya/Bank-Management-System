package aSimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener{
	
	JTextField tf1,tf2;
	JButton b1,b2,b3;
	JLabel l1,l2,l3;
	
	Deposit()
	{
		setFont(new Font("System", Font.BOLD, 22));
		Font f = getFont();
		FontMetrics fm = getFontMetrics(f);
		int x = fm.stringWidth("DEPOSIT");
		int y = fm.stringWidth(" ");
		int z = getWidth()- (5*x);
		int w = z/y;
		String pad = "";
		pad = String.format("%"+w+"s",pad);
		setTitle(pad +"DEPOSIT");
		
		l1 = new JLabel("Enter the Amount You Want");
        l1.setFont(new Font("System", Font.BOLD, 35));
        
        l2 = new JLabel("TO DEPOSIT");
        l2.setFont(new Font("System", Font.BOLD, 35));
        
        l3 = new JLabel("Enter Pin");
        l3.setFont(new Font("System", Font.BOLD, 14));
        
        
        tf1 = new JTextField();
        tf1.setFont(new Font("Raleway", Font.BOLD,22));
        
        tf2 = new JTextField();
        tf2.setFont(new Font("Raleway", Font.BOLD,14));
        
        
        b1 = new JButton("Deposit");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("Back");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("Exit");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        l3.setBounds(620,10,80,30);
        add(l3);
        
        tf2.setBounds(700,10,40,30);
        add(tf2);
        
        l1.setBounds(150,150,800,60);
        add(l1);
        
        l2.setBounds(290,210,800,60);
        add(l2);
        
        tf1.setBounds(250,300,300,50);
        add(tf1);
        

        b1.setBounds(260,380,125,50);
        add(b1);
        
        b2.setBounds(415,380,125,50);
        add(b2);
        
        b3.setBounds(300,550,200,50);
        add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.white);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
        
	}
	
	public void actionPerformed(ActionEvent ae){
        try
        {        
        	
        	String a = tf1.getText();
            String b = tf2.getText();
            
            
            if(ae.getSource() == b1)
            {
                if(tf2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }
                else
                {
                    Conn c1 = new Conn();
                    ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+b+"' ");
                    double balance = 0;
                    if(rs.next())
                    {
                    	String pin = rs.getString("pin");
                    	balance = rs.getDouble("balance");
                    	
                    	double d = Double.parseDouble(a);
                        balance += d;
                        String q1 = "insert into bank values('"+pin+",'"+a+"',null,'"+balance+"')";
                        c1.s.executeUpdate(q1);
            
                    }        
                    JOptionPane.showMessageDialog(null, "Rs. "+a+" Deposited Successfully");
                    new Transactions().setVisible(true);
                    setVisible(false);
                }
            }
            else if(ae.getSource()==b2)
            {
                
                new Transactions().setVisible(true);
                setVisible(false);
            }
            else if(ae.getSource() == b3)
            {
            	System.exit(0);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
	
	public static void main(String[] args)
	{
		new Deposit().setVisible(true);
	}

}
