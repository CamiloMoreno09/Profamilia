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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByCodigodepartamento", query = "SELECT d FROM Departamento d WHERE d.codigodepartamento = :codigodepartamento")
    , @NamedQuery(name = "Departamento.findByNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Departamento.findByModalidad", query = "SELECT d FROM Departamento d WHERE d.modalidad = :modalidad")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer codigodepartamento;
    @Size(max = 50)
    private String nombre;
    @Size(max = 50)
    private String modalidad;
    @OneToMany(mappedBy = "codigodepartamento")
    private Collection<Empleado> empleadoCollection;
    @JoinColumn(name = "codigosede", referencedColumnName = "codigosede")
    @ManyToOne
    private Sede codigosede;

    public Departamento() {
    }

    public Departamento(Integer codigodepartamento) {
        this.codigodepartamento = codigodepartamento;
    }

    public Integer getCodigodepartamento() {
        return codigodepartamento;
    }

    public void setCodigodepartamento(Integer codigodepartamento) {
        this.codigodepartamento = codigodepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    public Sede getCodigosede() {
        return codigosede;
    }

    public void setCodigosede(Sede codigosede) {
        this.codigosede = codigosede;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigodepartamento != null ? codigodepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.codigodepartamento == null && other.codigodepartamento != null) || (this.codigodepartamento != null && !this.codigodepartamento.equals(other.codigodepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "profamilia.Departamento[ codigodepartamento=" + codigodepartamento + " ]";
    }
    
}
