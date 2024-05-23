package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Inscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarInformacion;

public class FiltroFechaInscripcionesFXController {


    @FXML
    public TextField fechaInicio;
    @FXML
    public TextField fechaFin;
    @FXML
    public Button aplicarFiltro;

    private Controladora control = new Controladora();

    private MenuInscripcionesFXController menuInscripcionesFXController;

    public void setMenuInscripcionesFXController(MenuInscripcionesFXController menuInscripcionesFXController) {
        this.menuInscripcionesFXController = menuInscripcionesFXController;
    }


    public void aplicarFiltroFechas(ActionEvent actionEvent) {

        try{

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

            Date sqlDateInicio = new Date(parsedDateInicio.getTime());
            Date sqlDateFin = new Date(parsedDateFin.getTime());

            List<Inscripcion> inscripcionesFiltradas = control.obtenerInscripcionesPorFechas(sqlDateInicio, sqlDateFin);

            menuInscripcionesFXController.getInscripciones().setAll(inscripcionesFiltradas);

            mostrarInformacion("Informacion", "Filtro aplicado correctamente");

            Stage stage = (Stage) aplicarFiltro.getScene().getWindow();
            stage.close();

        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, use el formato dd/MM/yyyy.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
