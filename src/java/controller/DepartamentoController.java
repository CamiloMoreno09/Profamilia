package controller;

import profamilia.Departamento;
import profamilia.Empleado;
import java.util.Collection;
import facade.DepartamentoFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "departamentoController")
@ViewScoped
public class DepartamentoController extends AbstractController<Departamento> {

    @Inject
    private SedeController codigosedeController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEmpleadoCollectionEmpty;

    public DepartamentoController() {
        // Inform the Abstract parent controller of the concrete Departamento Entity
        super(Departamento.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigosedeController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEmpleadoCollectionEmpty();
    }

    public boolean getIsEmpleadoCollectionEmpty() {
        return this.isEmpleadoCollectionEmpty;
    }

    private void setIsEmpleadoCollectionEmpty() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            this.isEmpleadoCollectionEmpty = ejbFacade.isEmpleadoCollectionEmpty(selected);
        } else {
            this.isEmpleadoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Empleado entities that
     * are retrieved from Departamento and returns the navigation outcome.
     *
     * @return navigation outcome for Empleado page
     */
    public String navigateEmpleadoCollection() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            Collection<Empleado> selectedEmpleadoCollection = ejbFacade.findEmpleadoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Empleado_items", selectedEmpleadoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/profamilia/empleado/index";
    }

    /**
     * Sets the "selected" attribute of the Sede controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigosede(ActionEvent event) {
        Departamento selected = this.getSelected();
        if (selected != null && codigosedeController.getSelected() == null) {
            codigosedeController.setSelected(selected.getCodigosede());
        }
    }

}
