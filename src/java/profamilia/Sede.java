/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profamilia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 57313
 */
@Entity
@Table(catalog = "profamilia", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
    , @NamedQuery(name = "Sede.findByCodigosede", query = "SELECT s FROM Sede s WHERE s.codigosede = :codigosede")
    , @NamedQuery(name = "Sede.findByNombre", query = "SELECT s FROM Sede s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Sede.findByDireccion", query = "SELECT s FROM Sede s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "Sede.findByCapacidad", query = "SELECT s FROM Sede s WHERE s.capacidad = :capacidad")})
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer codigosede;
    @Size(max = 50)
    private String nombre;
    @Size(max = 100)
    private String direccion;
    private Integer capacidad;
    @OneToMany(mappedBy = "codigosede")
    private Collection<Cliente> clienteCollection;
    @OneToMany(mappedBy = "codigosede")
    private Collection<Departamento> departamentoCollection;

    public Sede() {
    }

    public Sede(Integer codigosede) {
        this.codigosede = codigosede;
    }

    public Integer getCodigosede() {
        return codigosede;
    }

    public void setCodigosede(Integer codigosede) {
        this.codigosede = codigosede;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Departamento> getDepartamentoCollection() {
        return departamentoCollection;
    }

    public void setDepartamentoCollection(Collection<Departamento> departamentoCollection) {
        this.departamentoCollection = departamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigosede != null ? codigosede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.codigosede == null && other.codigosede != null) || (this.codigosede != null && !this.codigosede.equals(other.codigosede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "profamilia.Sede[ codigosede=" + codigosede + " ]";
    }
    
}
