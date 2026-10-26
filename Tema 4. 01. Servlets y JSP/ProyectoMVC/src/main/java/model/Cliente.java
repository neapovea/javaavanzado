package model;

import java.util.Date;

public class Cliente{
	private int id;
	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private int numPedidos;
	
	public Cliente(){
		
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public int getNumPedidos() {
		return numPedidos;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setNumPedidos(int numPedidos) {
		this.numPedidos = numPedidos;
	}
}	
