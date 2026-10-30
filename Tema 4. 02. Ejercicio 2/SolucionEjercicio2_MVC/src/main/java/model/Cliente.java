package model;

import java.util.ArrayList;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



public class Cliente {
	private int id;
	@NotNull(message = "El nombre es obligatorio")
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	@NotNull(message = "Los apellidos son obligatorios")
	@NotBlank(message = "Los apellidos son obligatorios")
	private String apellidos;
	@NotNull(message = "La fecha de nacimiento es obligatoria")
	@NotBlank(message = "La fecha de nacimiento es obligatoria")
	@Pattern(regexp = "^(\\d{4})-(0?[1-9]|1[1-2])-(?:3[01]|[12][0-9]|0?[1-9])$", message = "El formato de fecha es aaaa-mm-dd") 
	private String strFechaNacimiento;
	private Date fechaNacimiento;
	
	private ArrayList<Pedido> pedidos;
	
	
	public Cliente(){
		pedidos = new ArrayList<Pedido>();
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
		return pedidos.size();
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
	public String getStrFechaNacimiento() {
		return strFechaNacimiento;
	}
	public void setStrFechaNacimiento(String strFechaNacimiento) {
		this.strFechaNacimiento = strFechaNacimiento;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
	