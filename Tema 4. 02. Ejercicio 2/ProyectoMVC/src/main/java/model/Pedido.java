package model; 

import java.io.Serializable; 
import java.math.BigDecimal;

import jakarta.validation.constraints.*;


public class Pedido implements Serializable { 
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "El nombre del artículo es obligatorio")  // Verifica que el campo no está en blanco
	private String articulo; 
	
	@NotNull(message = "El precio es obligatorio")  // Verifica que el campo no es nulo
	@DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")	 // Verifica que el campo es un número mayor que 0	
	private BigDecimal precio;
	
	public Pedido() { } 
	
	public Pedido(String articulo, BigDecimal precio) { 
		this.articulo = articulo; this.precio = precio; 
		} 
	
	public String getArticulo() { 
		return articulo; 
		} 
	
	public void setArticulo(String articulo) { 
		this.articulo = articulo; 
		} 
	
	public BigDecimal getPrecio() { 
		return precio; 
		} 
	
	public void setPrecio(BigDecimal precio) { 
		this.precio = precio; 
		} 
}