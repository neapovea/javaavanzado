package model;

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public class Cliente{
	private int id;
	@NotNull(message = "El nombre es obligatorio") // Verifica que el campo no es nulo
	@NotBlank(message = "El nombre es obligatorio") // Verifica que el campo no está en blanco
	private String nombre;
	
	@NotNull(message = "Los apellidos son obligatorios") // Verifica que el campo no es nulo 
	@NotBlank(message = "Los apellidos no pueden estar en blanco") // Verifica que el campo no está en blanco
	private String apellidos;
	
	@NotNull(message = "La fecha de nacimiento es obligatoria") // Verifica que el campo no es nulo 
	@Past(message = "La fecha de nacimiento debe ser anterior a la actual")// Verifica que el campo no está en blanco
	private Date fechaNacimiento;
	//eliminamos el int para crear usar lista // private int numPedidos;
	private List<Pedido> pedidos; // Lista de pedidos del cliente
	//private ArrayList<Pedido> pedidos;
	
	public Cliente(){
		this.pedidos = new ArrayList<>();
		
	}

	public Cliente(int id, String nombre, String apellidos, Date fechaNacimiento) { 
		this.id = id; 
		this.nombre = nombre; 
		this.apellidos = apellidos; 
		this.fechaNacimiento = fechaNacimiento; 
		this.pedidos = new ArrayList<>();
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
	public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // Campo calculado que devuelve el número de pedidos en base al tamaño de la lista
    public int getNumPedidos() {
        return (pedidos != null) ? pedidos.size() : 0;
    }

    public void addPedido(Pedido pedido) {
        if (this.pedidos == null) {
            this.pedidos = new ArrayList<>();
        }
        this.pedidos.add(pedido);
    }

}	
