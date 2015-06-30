package gui;

import grupo.*;

import java.awt.event.ActionListener;

import javax.swing.*;

import mailUam.*;
import mensaje.*;

public class GUIVerGrupo extends GUIMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton botonPreguntas;
	private JTextField campoEscribir;
	private JButton botonEnviar;
	private JLabel labelMensajes;
	private Grupo grupo;
	
	public GUIVerGrupo(MailUam modelo) {
		super(modelo);
		setLabelTituloText("Ver Grupo");
		botonPreguntas = new JButton("Preguntas");
		botonPreguntas.setEnabled(false);
		campoEscribir = new JTextField("Escribe tu mensaje...");
		botonEnviar = new JButton("Enviar");
		labelMensajes = new JLabel();
		JPanel p1 = new JPanel();
		JScrollPane scroll = new JScrollPane(labelMensajes);
		p1.add(botonPreguntas);
		p1.add(scroll);
		p1.add(campoEscribir);
		p1.add(botonEnviar);
		add(p1);
	}
	
	
	
	/**
	 * @return the botonPreguntas
	 */
	public JButton getBotonPreguntas() {
		return botonPreguntas;
	}



	/**
	 * @param botonPreguntas the botonPreguntas to set
	 */
	public void setBotonPreguntas(JButton botonPreguntas) {
		this.botonPreguntas = botonPreguntas;
	}



	/**
	 * @return the campoEscribir
	 */
	public JTextField getCampoEscribir() {
		return campoEscribir;
	}



	/**
	 * @param campoEscribir the campoEscribir to set
	 */
	public void setCampoEscribir(JTextField campoEscribir) {
		this.campoEscribir = campoEscribir;
	}



	/**
	 * @return the botonEnviar
	 */
	public JButton getBotonEnviar() {
		return botonEnviar;
	}



	/**
	 * @param botonEnviar the botonEnviar to set
	 */
	public void setBotonEnviar(JButton botonEnviar) {
		this.botonEnviar = botonEnviar;
	}



	/**
	 * @return the grupo
	 */
	public Grupo getGrupo() {
		return grupo;
	}



	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}



	/**
	 * @return the labelMensajes
	 */
	public JLabel getLabelMensajes() {
		return labelMensajes;
	}



	/**
	 * @param labelMensajes the labelMensajes to set
	 */
	public void setLabelMensajes(JLabel labelMensajes) {
		this.labelMensajes = labelMensajes;
	}



	public void setValores(Grupo g){
		this.grupo=g;
		String text="<html><body>---------------------------<br/>-"+g.getNombre()+"-<br/>---------------------------<br/><hr/>";
		if(g.isGrupoColaborativo()){
			text+="Falta Implementar";
			for(MensajeGrupo mensaje:g.getListaMensajes()){
				MensajeColaborativo m = (MensajeColaborativo)mensaje;
				text+="Pregunta: "+m.getRemitente().getCorreo()+"<br/>"+m.getCuerpo()+"<br/><hr width=2/>";
				for(MensajeColaborativo respuesta:m.getRespuestas()){
					text+="Respuesta: "+m.getRemitente().getCorreo()+"<br/>"+m.getCuerpo()+"<br/><hr width=1/>";
				}
			}
			labelMensajes.setText(text);
		}else if(g.isGrupoEstudio()){
			botonPreguntas.setEnabled(true);
		}
		for(MensajeGrupo m:g.getListaMensajes()){
			text+=m.getRemitente().getCorreo()+"<br/>"+m.getCuerpo()+"<br/>";
			text+="<hr/>";
		}
		text+="</body></html>";
		labelMensajes.setText(text);
	}

	@Override
	public void setControlador(ActionListener c) {
		super.setControlador(c);
		botonEnviar.addActionListener(c);
		botonPreguntas.addActionListener(c);
	}
	
}