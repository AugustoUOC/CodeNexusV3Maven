package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.controlador.ExcursionJPAController;
import com.codenexus.codenexusp4.modelo.Excursion;
import com.codenexus.codenexusp4.utilidad.Teclado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class FFechaFXController {

    @FXML
    public Button aplicarFiltro;

    @FXML
    public TextField fechaInicio;
    @FXML
    public TextField fechaFin;

    private Controladora control = new Controladora();

    private ExcursionFxController ExcursionFxController;


    @FXML
    public void initialize() {

    }

    public void setExcursionFxController(ExcursionFxController excursionFxController) {
        this.ExcursionFxController = excursionFxController;
    }

    public void filtroFechas(ActionEvent actionEvent) {

        String fechaInicio = this.fechaInicio.getText();
        String fechaFin = this.fechaFin.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Para asegurar que la fecha es estrictamente validada

        try {
            // Parsear las fechas desde el texto a java.util.Date
            java.util.Date parsedDateInicio = dateFormat.parse(fechaInicio);
            java.util.Date parsedDateFin = dateFormat.parse(fechaFin);
            if (parsedDateInicio.after(parsedDateFin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            // Convertir java.util.Date a java.sql.Date
            Date sqlDateInicio = new Date(parsedDateInicio.getTime());
            Date sqlDateFin = new Date(parsedDateFin.getTime());

            // Obtener la lista de excursiones filtrada por las fechas
            List<Excursion> excursionesFiltradas = control.obtenerExcursionPorFecha(sqlDateInicio, sqlDateFin);

            // Imprimir para debug
            excursionesFiltradas.forEach(excursion -> System.out.println(excursion.toString()));

            // Actualizar la lista de excursiones en la tabla
            ExcursionFxController.getExcursiones().setAll(excursionesFiltradas);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Filtro aplicado correctamente");
            alert.showAndWait();

            Stage stage = (Stage) aplicarFiltro.getScene().getWindow();
            stage.close();

        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, use el formato dd/MM/yyyy.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
