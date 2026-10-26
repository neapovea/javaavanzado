package model; 

import java.io.Serializable; 
import java.math.BigDecimal; 

public class Pedido implements Serializable { 
	private static final long serialVersionUID = 1L; 
	private String articulo; 
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