package controller;

import profamilia.Empleado;
import facade.EmpleadoFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "empleadoController")
@ViewScoped
public class EmpleadoController extends AbstractController<Empleado> {

    @Inject
    private DepartamentoController codigodepartamentoController;
    @Inject
    private MobilePageController mobilePageController;

    public EmpleadoController() {
        // Inform the Abstract parent controller of the concrete Empleado Entity
        super(Empleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigodepartamentoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigodepartamento(ActionEvent event) {
        Empleado selected = this.getSelected();
        if (selected != null && codigodepartamentoController.getSelected() == null) {
            codigodepartamentoController.setSelected(selected.getCodigodepartamento());
        }
    }

}
