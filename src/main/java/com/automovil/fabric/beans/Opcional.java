package com.automovil.fabric.beans;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="OPTIONAL", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID") })

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Opcional {
	

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 3)
	private String id;
	
	@Column(name = "NAME", nullable = false, length = 80)
	private String nombre;
	
	@Column(name = "PRICE", nullable = false)
	private BigDecimal precio;
	
	
	//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "opcionales")
	//private Set<Automovil> automoviles = new HashSet<Automovil>(0);
	
	
	public Opcional(String id, String nombre, BigDecimal precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opcional opcional = (Opcional) o;
        return Objects.equals(nombre, opcional.getNombre());
    }
	
	 @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
	
	public Opcional() {
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

	/*public Set<Automovil> getAutomoviles() {
		return automoviles;
	}

	public void setAutomoviles(Set<Automovil> automoviles) {
		this.automoviles = automoviles;
	}*/


}
