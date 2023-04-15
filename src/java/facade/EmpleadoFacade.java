/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import profamilia.Empleado;
import profamilia.Empleado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import profamilia.Departamento;

/**
 *
 * @author 57313
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "ProfamiliaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public boolean isCodigodepartamentoEmpty(Empleado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Empleado> empleado = cq.from(Empleado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(empleado, entity), cb.isNotNull(empleado.get(Empleado_.codigodepartamento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Departamento findCodigodepartamento(Empleado entity) {
        return this.getMergedEntity(entity).getCodigodepartamento();
    }
    
}
