/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import profamilia.Departamento;
import profamilia.Departamento_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import profamilia.Empleado;
import profamilia.Sede;

/**
 *
 * @author 57313
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {

    @PersistenceContext(unitName = "ProfamiliaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentoFacade() {
        super(Departamento.class);
    }

    public boolean isEmpleadoCollectionEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotEmpty(departamento.get(Departamento_.empleadoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Empleado> findEmpleadoCollection(Departamento entity) {
        Departamento mergedEntity = this.getMergedEntity(entity);
        Collection<Empleado> empleadoCollection = mergedEntity.getEmpleadoCollection();
        empleadoCollection.size();
        return empleadoCollection;
    }

    public boolean isCodigosedeEmpty(Departamento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Departamento> departamento = cq.from(Departamento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(departamento, entity), cb.isNotNull(departamento.get(Departamento_.codigosede)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Sede findCodigosede(Departamento entity) {
        return this.getMergedEntity(entity).getCodigosede();
    }
    
}
