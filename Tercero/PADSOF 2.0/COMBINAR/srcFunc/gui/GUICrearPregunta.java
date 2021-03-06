package gui;

import java.awt.event.ActionListener;
import grupo.*;

import javax.swing.*;

import mailUam.*;

public class GUICrearPregunta extends GUIMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelPregunta;
	private JTextArea textPregunta;
	private GrupoEstudio grupo;
	private JButton buttonCrear;
	public GUICrearPregunta(MailUam modelo) {
		super(modelo);
		labelPregunta = new JLabel("Pregunta:");
		textPregunta = new JTextArea(20,20);
		buttonCrear = new JButton("Crear");
		JPanel p1= new JPanel();
		p1.add(labelPregunta);
		p1.add(textPregunta);
		p1.add(buttonCrear);
		add(p1);
	}
	
	/**
	 * @return the labelPregunta
	 */
	public JLabel getLabelPregunta() {
		return labelPregunta;
	}

	/**
	 * @param labelPregunta the labelPregunta to set
	 */
	public void setLabelPregunta(JLabel labelPregunta) {
		this.labelPregunta = labelPregunta;
	}

	/**
	 * @return the textPregunta
	 */
	public JTextArea getTextPregunta() {
		return textPregunta;
	}
	
	/**
	 * @return the textPregunta
	 */
	public String getTextPreguntaText() {
		return textPregunta.getText();
	}
	
	/**
	 * @param textPregunta the textPregunta to set
	 */
	public void setTextPregunta(JTextArea textPregunta) {
		this.textPregunta = textPregunta;
	}

	/**
	 * @return the grupo
	 */
	public GrupoEstudio getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(GrupoEstudio grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the buttonCrear
	 */
	public JButton getButtonCrear() {
		return buttonCrear;
	}

	/**
	 * @param buttonCrear the buttonCrear to set
	 */
	public void setButtonCrear(JButton buttonCrear) {
		this.buttonCrear = buttonCrear;
	}

	@Override
	public void setControlador(ActionListener c) {
		//TODO Auto-generated method stub
		super.setControlador(c);
		buttonCrear.addActionListener(c);
	}
	public void setValores(GrupoEstudio grupo){
		this.grupo= grupo;
	}

}
