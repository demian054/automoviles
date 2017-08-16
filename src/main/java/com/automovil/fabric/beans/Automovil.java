package com.automovil.fabric.beans;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CAR", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "NAME") })

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Automovil {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "NAME", unique = true, nullable = false, length = 80)
	private String nombre;
	
	private BigDecimal precioTotal = new BigDecimal(0);	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinTable(name = "CAR_OPTIONAL", joinColumns = {
			@JoinColumn(name = "CAR_ID", nullable = false, updatable = true) },
			inverseJoinColumns = { @JoinColumn(name = "OPTIONAL_ID",
					nullable = false, updatable = true) })
	private Set<Opcional> opcionales;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade={CascadeType.ALL})
	private AutomovilTipo automovilTipo;
	
	
	public Automovil(long id, String nombre, BigDecimal precioTotal) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioTotal = precioTotal;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automovil automovil = (Automovil) o;
        return Objects.equals(nombre, automovil.getNombre());
    }
	
	 @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
 
	
	public Automovil() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public BigDecimal getPrecioTotal() {
		precioTotal = new BigDecimal(0).add(automovilTipo.getPrecio());
		for (Opcional opcional: getOpcionales()) {
			precioTotal = precioTotal.add(opcional.getPrecio());
		}
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Set<Opcional> getOpcionales() {
		return opcionales;
	}

	public void setOpcionales(Set<Opcional> opcionales) {
		this.opcionales = opcionales;
	}

	public AutomovilTipo getAutomovilTipo() {
		return automovilTipo;
	}

	public void setAutomovilTipo(AutomovilTipo automovilTipo) {
		this.automovilTipo = automovilTipo;
	}

	
	

}
