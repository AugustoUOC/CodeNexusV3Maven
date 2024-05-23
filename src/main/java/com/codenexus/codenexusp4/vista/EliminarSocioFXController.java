package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Socio;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EliminarSocioFXController {

    @FXML
    public TextField idSocio;
    @FXML
    public Button btnEliminarSocio;

    private Controladora control = new Controladora();
    private SocioFxController parentController;
    private ObservableList<Socio> sociosObsList;

    public void setParentController(SocioFxController parentController) {
        this.parentController = parentController;
        this.sociosObsList = parentController.getSociosObsList();
    }

    @FXML
    public void EliminarSocio(ActionEvent actionEvent) {
        String idSocioText = this.idSocio.getText();
        if (idSocioText == null || idSocioText.isEmpty()) {
            mostrarError("El ID del socio no puede estar vacío.");
            return;
        }

        int idSocio;
        try {
            idSocio = Integer.parseInt(idSocioText);
        } catch (NumberFormatException e) {
            mostrarError("El ID del socio debe ser un número válido.");
            return;
        }

        try {
            Socio socio = control.buscarSocioPorId(idSocio);
            if (socio == null) {
                mostrarError("ID del Socio no válido.");
                return;
            }

            if (sociosObsList.contains(socio)) {
                sociosObsList.remove(socio);
                control.eliminarSocioPorId(idSocio);
                mostrarInformacion("Se ha eliminado el socio correctamente.");
                parentController.actualizarListaSocio();
                parentController.actualizarListaEstandar();
                parentController.actualizarListaFederado();
                parentController.actualizarListaInfantil();
            } else {
                mostrarInformacion("No se ha encontrado al socio en la lista.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Ocurrió un error al eliminar el Socio.");
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
