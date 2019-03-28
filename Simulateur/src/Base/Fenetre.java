package Base;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		setTitle("PAram�trage du Simulateur"); //On donne un titre � l'application
		setSize(500,200); //On donne une taille � notre fen�tre
		setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		setResizable(true); //On permet le redimensionnement
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l'application de se fermer lors du clic sur la croix
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
		JButton bouton1 = new JButton(new getAction1(this," Nettoyer"));
		panel.add(bouton1);
		return panel;
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
		System.out.println("la joie de vivre");
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
		fenetre.world.clearAllPersonne();
	} 	
}