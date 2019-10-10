import javax.swing.*;
import javax.swing.JFileChooser;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame{

	JButton loadFileB, validateStringB;
	JLabel titleLabel, authorA, authorB, loadLabel, fileLoadConfirm, inStringLabel, resultLabel;
	JTextField tf;

	String fileName = "";
	FileRead stringValidator;

	public Main(){
		super();
		titleLabel = new JLabel("NDFA-lambda simulator");
		titleLabel.setBounds(80,20,220,70);
		authorA = new JLabel("Rafael Rojas O. A01339605");
		authorA.setBounds(100,65,220,70);
		authorB = new JLabel("Nicolas Gazzolo V. A01339945");
		authorB.setBounds(90,95,220,70);
		titleLabel.setFont(new Font("Arial", Font.ITALIC, 18));
		loadLabel = new JLabel("Select a .txt file:");
		loadLabel.setBounds(60,150,100,70);
		loadFileB = new JButton("Load File");
		loadFileB.setBounds(180,165,150,35);
		fileLoadConfirm = new JLabel("Placeholder text");
		fileLoadConfirm.setBounds(20,195,300,60);
		validateStringB = new JButton("TEST String");
		validateStringB.setBounds(110,325,150,40);
		inStringLabel = new JLabel("String:");
		inStringLabel.setBounds(40,235,100,70);
		resultLabel = new JLabel("SALU2 PELU2 wapos");
		resultLabel.setBounds(20,345,320,100);
		tf = new JTextField();
		tf.setBounds(180,250,150,45);
		add(loadFileB);add(resultLabel);add(tf);add(loadLabel);add(titleLabel);add(inStringLabel);add(authorA);add(authorB);add(fileLoadConfirm);add(validateStringB);
		loadFileB.addActionListener(new LFileListener());
		validateStringB.addActionListener(new VStringListener());
		getContentPane().setBackground(new Color(111,188,240));
		setSize(400,500);
		setLayout(null);
		setVisible(true);
	}

	private class LFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				try{
					File selectedFile = fileChooser.getSelectedFile();
					fileName = selectedFile.getName();
					fileLoadConfirm.setText("\"" + fileName + "\" has been loaded succesfully.");
				}
				catch(Exception ex){
					System.out.println("Efe");
				}
			}

		}
	}

	private class VStringListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(tf.getText().equals("")){
				resultLabel.setText("Error. String is empty.");
			}
			else if(fileName.equals("")){
				resultLabel.setText("Error. No file loaded.");
			}
			else{
				stringValidator = new FileRead(fileName);
				if(stringValidator.validateString(tf.getText())){
					resultLabel.setText("String \"" + tf.getText() + "\" is ACCEPTED by the automaton.");
				}
				else{
					resultLabel.setText("String \"" + tf.getText() + "\" is REJECTED by the automaton.");
				}
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}