package model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Pedido {

	@NotNull(message = "El artículo es obligatorio")
	@NotBlank(message = "El artículo es obligatorio")
	private String articulo;
	
	@NotNull(message = "El precio es obligatorio")
	@NotBlank(message = "El precio es obligatorio")
	@Digits(integer=6, fraction=2,message="Precio máximo 99.9999,99.")
	private String strPrecio;
	private BigDecimal precio;

	public Pedido() {
		
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getStrPrecio() {
		return strPrecio;
	}
	public void setStrPrecio(String strPrecio) {
		this.strPrecio = strPrecio;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
}
