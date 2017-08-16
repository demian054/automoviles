package com.automovil.fabric.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CAR_TYPE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID")
		})

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class AutomovilTipo {
	

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 3)
	private String id;
	
	@Column(name = "NAME", nullable = false, length = 80)
	private String nombre;
	
	@Column(name = "PRICE", nullable = false)
	private BigDecimal precio;	
	
	public AutomovilTipo(String id, String nombre, BigDecimal precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutomovilTipo automovilTipo = (AutomovilTipo) o;
        return Objects.equals(nombre, automovilTipo.getNombre());
    }
	
	 @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
	
	public AutomovilTipo() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}	

}
