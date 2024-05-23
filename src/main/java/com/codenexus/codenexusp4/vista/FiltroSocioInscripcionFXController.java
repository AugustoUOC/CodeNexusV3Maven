package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarError;

public class FiltroSocioInscripcionFXController {


    @FXML
    public TextField idSocio;
    @FXML
    public Button btnAplicarFiltro;

    private Controladora control = new Controladora();

    private MenuInscripcionesFXController menuInscripcionesFXController;

    private ObservableList<Inscripcion> inscripciones;

    public void setMenuInscripcionesFXController(MenuInscripcionesFXController menuInscripcionesFXController) {
        this.menuInscripcionesFXController = menuInscripcionesFXController;
    }

    public void aplicarFiltroPorSocio(ActionEvent actionEvent) {
        try {
            int idSocio = Integer.parseInt(this.idSocio.getText());
            List<Inscripcion>  listaInscripciones = control.obtenerInscripcionesPorSocio(idSocio);
            if (listaInscripciones == null || listaInscripciones.isEmpty()) {
                mostrarError("Error", "El socio no tiene inscripciones asociadas");
            } else {
                inscripciones = FXCollections.observableArrayList(listaInscripciones);
                menuInscripcionesFXController.actualizarTablaInscripcionFiltrada(inscripciones);
                Stage stage = (Stage) btnAplicarFiltro.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            mostrarError("Error", "Ocurrió un error al aplicar el filtro");
            e.printStackTrace();
        }
    }
}
