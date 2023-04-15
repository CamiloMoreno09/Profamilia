package controller;

import profamilia.Sede;
import profamilia.Cliente;
import profamilia.Departamento;
import java.util.Collection;
import facade.SedeFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "sedeController")
@ViewScoped
public class SedeController extends AbstractController<Sede> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isClienteCollectionEmpty;
    private boolean isDepartamentoCollectionEmpty;

    public SedeController() {
        // Inform the Abstract parent controller of the concrete Sede Entity
        super(Sede.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsClienteCollectionEmpty();
        this.setIsDepartamentoCollectionEmpty();
    }

    public boolean getIsClienteCollectionEmpty() {
        return this.isClienteCollectionEmpty;
    }

    private void setIsClienteCollectionEmpty() {
        Sede selected = this.getSelected();
        if (selected != null) {
            SedeFacade ejbFacade = (SedeFacade) this.getFacade();
            this.isClienteCollectionEmpty = ejbFacade.isClienteCollectionEmpty(selected);
        } else {
            this.isClienteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Cliente entities that are
     * retrieved from Sede and returns the navigation outcome.
     *
     * @return navigation outcome for Cliente page
     */
    public String navigateClienteCollection() {
        Sede selected = this.getSelected();
        if (selected != null) {
            SedeFacade ejbFacade = (SedeFacade) this.getFacade();
            Collection<Cliente> selectedClienteCollection = ejbFacade.findClienteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cliente_items", selectedClienteCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/profamilia/cliente/index";
    }

    public boolean getIsDepartamentoCollectionEmpty() {
        return this.isDepartamentoCollectionEmpty;
    }

    private void setIsDepartamentoCollectionEmpty() {
        Sede selected = this.getSelected();
        if (selected != null) {
            SedeFacade ejbFacade = (SedeFacade) this.getFacade();
            this.isDepartamentoCollectionEmpty = ejbFacade.isDepartamentoCollectionEmpty(selected);
        } else {
            this.isDepartamentoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Departamento entities
     * that are retrieved from Sede and returns the navigation outcome.
     *
     * @return navigation outcome for Departamento page
     */
    public String navigateDepartamentoCollection() {
        Sede selected = this.getSelected();
        if (selected != null) {
            SedeFacade ejbFacade = (SedeFacade) this.getFacade();
            Collection<Departamento> selectedDepartamentoCollection = ejbFacade.findDepartamentoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Departamento_items", selectedDepartamentoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/profamilia/departamento/index";
    }

}
