package Base;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import Base.GameWorld;
 
public class Fenetre extends JFrame{
	private JTextField textField;
	private Boolean Commencer=false;
	 GameWorld world;
	 private JTextArea textArea;
	 
	
	public Boolean getCommencer() {
		return Commencer;
	}
	public void setCommencer(Boolean commencer) {
		Commencer = commencer;
	}
	public Fenetre(GameWorld world) {
		super();
		this.build();
		this.world=world;
	}
	private void build(){
		setTitle("Paramétrage du Simulateur"); //On donne un titre ï¿½ l'application
		setSize(500,200); //On donne une taille ï¿½ notre fenï¿½tre
		setLocationRelativeTo(null); //On centre la fenï¿½tre sur l'ï¿½cran
		setResizable(true); //On permet le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit ï¿½ l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
		this.setVisible(true);
	}
		
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
 
		textField = new JTextField();
		textField.setColumns(15);
 
		
 
		JLabel label = new JLabel("Nombre de Personne :");
 
		panel.add(label);
		panel.add(textField);
		
		
		JButton bouton = new JButton(new GetAction(this, "Commencer !"));
		panel.add(bouton);
		//JButton bouton1 = new JButton(new getAction1(this," Nettoyer"));
		//panel.add(bouton1);
		
		
		JLabel label2 =new JLabel("Temps de la simulation (en s)");
		panel.add(label2);
		
		textArea= new JTextArea();
		textArea.setColumns(15);
		textArea.setEditable(false);
		panel.add(textArea);
		return panel;
	}
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public int TextFieldtoInt() {
		if(textField.getText()!=null && textField.getText() != "") {
			int a = Integer.valueOf(textField.getText());
		return a;
		}
		return -1;
	}
	
}

class GetAction extends AbstractAction {
	private Fenetre fenetre;
 
	public GetAction(Fenetre fenetre, String texte){
		super(texte);
 
		this.fenetre = fenetre;
	}
 
	public void actionPerformed(ActionEvent e) {

		int b = fenetre.TextFieldtoInt();
		if(b!=-1)
		this.fenetre.world.spawnPersonne(b);
	} 	
}
class getAction1 extends AbstractAction{
	private Fenetre fenetre;
	 
	public getAction1(Fenetre fenetre, String texte){
		super(texte);
 
		this.fenetre = fenetre;
	}
 
	public void actionPerformed(ActionEvent e) {
		fenetre.world.setPausePlay(false);
		fenetre.world.clearAllPersonne();
		
	} 	
}