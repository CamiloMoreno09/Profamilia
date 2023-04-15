package controller;

import profamilia.Cliente;
import facade.ClienteFacade;
import controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "clienteController")
@ViewScoped
public class ClienteController extends AbstractController<Cliente> {

    @Inject
    private SedeController codigosedeController;
    @Inject
    private MobilePageController mobilePageController;

    public ClienteController() {
        // Inform the Abstract parent controller of the concrete Cliente Entity
        super(Cliente.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigosedeController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Sede controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigosede(ActionEvent event) {
        Cliente selected = this.getSelected();
        if (selected != null && codigosedeController.getSelected() == null) {
            codigosedeController.setSelected(selected.getCodigosede());
        }
    }

}
