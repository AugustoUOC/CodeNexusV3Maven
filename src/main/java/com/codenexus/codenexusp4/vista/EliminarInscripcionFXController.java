package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Inscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarError;
import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarInformacion;

public class EliminarInscripcionFXController {

    @FXML
    public TextField idInscripcion;
    @FXML
    public Button btnEliminarInscripcion;

    private Controladora control = new Controladora();


    public void eliminarInscripcion(ActionEvent actionEvent) {

        try {
            int idInscripcion = Integer.parseInt(this.idInscripcion.getText());
            Inscripcion i = control.buscarInscripcionPorId(idInscripcion);
            if (i != null) {
                control.eliminarInscripcion(idInscripcion);
                mostrarInformacion("Informacion", "Inscripcion Eliminada con exito");
            }

            Stage stage = (Stage) btnEliminarInscripcion.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            mostrarError("Error", "Inserta una ID de la inscripcion");
        }

    }
}
