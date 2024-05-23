package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Inscripcion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class FiltroSocioYFechaInscripcionFXController {

    @FXML
    public TextField idSocio;
    @FXML
    public TextField fechaInicio;
    @FXML
    public TextField fechaFin;
    @FXML
    public Button btnAplicarFiltro;

    private Controladora control = new Controladora();

    private MenuInscripcionesFXController menuInscripcionesFXController;

    public void setMenuInscripcionesFXController(MenuInscripcionesFXController menuInscripcionesFXController) {
        this.menuInscripcionesFXController = menuInscripcionesFXController;
    }

    public void aplicarFiltroSocioFechas(ActionEvent actionEvent) {
        try {
            int idSocio = Integer.parseInt(this.idSocio.getText());
            String fechaInicio = this.fechaInicio.getText();
            String fechaFin = this.fechaFin.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);

            java.util.Date parsedDateInicio = dateFormat.parse(fechaInicio);
            java.util.Date parsedDateFin = dateFormat.parse(fechaFin);

            if (parsedDateInicio.after(parsedDateFin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            java.sql.Date sqlDateInicio = new java.sql.Date(parsedDateInicio.getTime());
            java.sql.Date sqlDateFin = new java.sql.Date(parsedDateFin.getTime());

            List<Inscripcion> inscripciones = control.obtenerInscripcionesPorSocioYFechas(idSocio, sqlDateInicio, sqlDateFin);

            if (inscripciones == null || inscripciones.isEmpty()) {
                System.out.println("No hay inscripciones para el socio y las fechas especificadas.");
            } else {
                ObservableList<Inscripcion> inscripcionesObservable = FXCollections.observableArrayList(inscripciones);
                menuInscripcionesFXController.actualizarTablaInscripcionFiltrada(inscripcionesObservable);
                Stage stage = (Stage) btnAplicarFiltro.getScene().getWindow();
                stage.close();
            }
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, use el formato dd/MM/yyyy.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
