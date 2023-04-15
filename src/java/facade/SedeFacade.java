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
import profamilia.Sede;
import profamilia.Sede_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import profamilia.Cliente;
import profamilia.Departamento;

/**
 *
 * @author 57313
 */
@Stateless
public class SedeFacade extends AbstractFacade<Sede> {

    @PersistenceContext(unitName = "ProfamiliaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SedeFacade() {
        super(Sede.class);
    }

    public boolean isClienteCollectionEmpty(Sede entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Sede> sede = cq.from(Sede.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(sede, entity), cb.isNotEmpty(sede.get(Sede_.clienteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Cliente> findClienteCollection(Sede entity) {
        Sede mergedEntity = this.getMergedEntity(entity);
        Collection<Cliente> clienteCollection = mergedEntity.getClienteCollection();
        clienteCollection.size();
        return clienteCollection;
    }

    public boolean isDepartamentoCollectionEmpty(Sede entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Sede> sede = cq.from(Sede.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(sede, entity), cb.isNotEmpty(sede.get(Sede_.departamentoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Departamento> findDepartamentoCollection(Sede entity) {
        Sede mergedEntity = this.getMergedEntity(entity);
        Collection<Departamento> departamentoCollection = mergedEntity.getDepartamentoCollection();
        departamentoCollection.size();
        return departamentoCollection;
    }
    
}
