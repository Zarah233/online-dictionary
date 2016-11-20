package Interface;

import java.awt.*;
import javax.swing.*;


public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel panel1 = new JPanel();
	JLabel Label1 = new JLabel("��½");
	JLabel Label2 = new JLabel("ע��");
	
	JPanel panel2 = new JPanel();
	JLabel Label3 = new JLabel("Input");
	JTextField input = new JTextField(35);
	JButton Search = new JButton("Search");

	JPanel panel3 = new JPanel();
	JCheckBox box1 = new JCheckBox("�ٶ�",true);
	JCheckBox box2 = new JCheckBox("�е�",true);
	JCheckBox box3 = new JCheckBox("��ɽ",true);
	
	JPanel Pan1 = new JPanel();
	
	
	JPanel panel4 = new JPanel();
	JLabel Label4 = new JLabel("�ٶ�");
	JTextArea Out1 = new JTextArea("",5,30);
	JCheckBox like1 = new JCheckBox("����",false);
	
	JPanel panel5 = new JPanel();
	JLabel Label5 = new JLabel("�е�");
	JTextArea Out2 = new JTextArea("",5,30);
	JCheckBox like2 = new JCheckBox("����",false);
	
	JPanel panel6 = new JPanel();
	JLabel Label6 = new JLabel("��ɽ");
	JTextArea Out3 = new JTextArea("",5,30);
	JCheckBox like3 = new JCheckBox("����",false);
	
	JPanel Pan2 = new JPanel();
	
	
	
	public Window(){
        
        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.RIGHT , 30 , 5);  
        panel1.setLayout(flowLayout1);  
        panel1.add(Label1);  
        panel1.add(Label2);  
        
        FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER , 20 , 5);  
        panel2.setLayout(flowLayout2);  
        panel2.add(Label3);  
        panel2.add(input);
        panel2.add(Search);
        
        FlowLayout flowLayout3 = new FlowLayout(FlowLayout.CENTER , 30 , 5);  
        panel3.setLayout(flowLayout3);  
        panel3.add(box1);  
        panel3.add(box2);
        panel3.add(box3); 
        
        GridLayout gridLayout = new GridLayout(3, 1);  
        Pan1.setLayout(gridLayout); 
        Pan1.add(panel1);
        Pan1.add(panel2);
        Pan1.add(panel3);
		
        FlowLayout flowLayout4 = new FlowLayout(FlowLayout.CENTER , 30 , 5);  
        panel4.setLayout(flowLayout4);  
        //Out1.setMargin(new Insets(5, 5, 5, 5));
        Out1.setEditable(false);   //ֻ��
		Out1.setLineWrap(true);  //�Զ�����
		Out1.setFont(new Font("Courier",Font.BOLD,20));//������ʾЧ��
        panel4.add(Label4);  
        panel4.add(Out1);
        panel4.add(like1);
        
        FlowLayout flowLayout5 = new FlowLayout(FlowLayout.CENTER , 30 , 5);  
        panel5.setLayout(flowLayout5);  
        //Out2.setMargin(new Insets(5, 5, 5, 5));
		Out2.setEditable(false);   //ֻ��
		Out2.setLineWrap(true);  //�Զ�����
		Out2.setFont(new Font("Courier",Font.BOLD,20));//������ʾЧ��
        panel5.add(Label5);  
        panel5.add(Out2);
        panel5.add(like2);
        
        FlowLayout flowLayout6 = new FlowLayout(FlowLayout.CENTER , 30 , 5);  
        panel6.setLayout(flowLayout6);  
        //Out3.setMargin(new Insets(5, 5, 5, 5));
		Out3.setEditable(false);   //ֻ��
		Out3.setLineWrap(true);  //�Զ�����
		Out3.setFont(new Font("Courier",Font.BOLD,20));//������ʾЧ��
        panel6.add(Label6);  
        panel6.add(Out3);
        panel6.add(like3);
        
        GridLayout gridLayout2 = new GridLayout(3,1,10,5);  
        Pan2.setLayout(gridLayout2);
		Pan2.add(panel4);  
        Pan2.add(panel5);  
        Pan2.add(panel6);     
        
        setLayout (new BorderLayout(0,10));
        add(Pan1,BorderLayout.NORTH);
		add(Pan2,BorderLayout.CENTER);
	}
	

	public static void main(String[] args){
		Window frame = new Window();
		frame.setTitle("Dictionary");
		frame.setLocation(200,100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setVisible(true);
	} 
	
}
