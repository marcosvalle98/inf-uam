package control;

import gui.*;

import java.awt.event.*;

import mailUam.*;

/**
 * @author Antonio Gomez lucas, Mario Valdemaro Garcia Roque
 * 
 *         Clase ControlMenu
 */

public class ControlMenu implements ActionListener {

	private MailUam modelo;
	private Ventana v;

	/**
	 * Constructor de ControlMenu
	 * @param v
	 * 		El modelo de la vistas
	 * @param modelo
	 * 		La aplicaion donde estan todos los datos
	 */
	public ControlMenu(Ventana v, MailUam modelo) {
		this.modelo = modelo;
		this.v = v;
	}

	/**
	 * @return the modelo
	 */
	public MailUam getModelo() {
		return modelo;
	}

	/**
	 * @param modelo
	 *            the modelo to set
	 */
	public void setModelo(MailUam modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the v
	 */
	public Ventana getV() {
		return v;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setV(Ventana v) {
		this.v = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		System.out.println("Activando controlador: " + getClass());
		GUIMenu menu = v.getMenu();
		if (source.equals(menu.getBotonMensajes())) {
			GUIMensaje menuMensaje = getV().getMensajes();
			menuMensaje.setValores();
			System.out.println("Cambiando a Mensajes");
			v.cambiarPanelMenuMensajes();
		} else if (source.equals(menu.getBotonGrupos())) {
			System.out.println("Cambiando a Grupos");
			GUIMenuGrupo menuGrupo = v.getMenuGrupos();
			menuGrupo.setValores();
			v.cambiarPanelMenuGrupos();
		} else if (source.equals(menu.getBotonVerPerfil())) {
			System.out.println("Cambiando a Perfil");
			GUIVerPerfil verPerfil = v.getPerfil();
			verPerfil.setValores(modelo.getLogged());
			v.cambiarPanelPerfil();
		} else if (source.equals(menu.getBotonSalir())) {
			System.out.println("Cambiando a Salir");
			modelo.logout();
			v.cambiarPanelLogin();
		}

	}

}
