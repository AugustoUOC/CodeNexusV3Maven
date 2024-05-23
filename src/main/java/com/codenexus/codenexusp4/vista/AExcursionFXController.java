package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Excursion;
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
import java.text.SimpleDateFormat;

public class AExcursionFXController {

    @FXML
    public TextField descripcion;
    @FXML
    public TextField fecha;
    @FXML
    public TextField duracion;
    @FXML
    public TextField precio;

    @FXML
    public Button btnGuardar;

    private Controladora control = new Controladora();

    private ObservableList<Excursion> excursions;

    @FXML
    public void initialize() {
        excursions = FXCollections.observableArrayList();
    }

    public void guardarExcursion(ActionEvent actionEvent) {

        String descripcion = this.descripcion.getText();
        String fechaExcursion = this.fecha.getText();
        int duracionExcursion = Integer.parseInt(this.duracion.getText());
        double precioExcursion = Integer.parseInt(this.precio.getText());


        // Definir el formato de fecha esperado
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Para asegurar que la fecha es estrictamente validada

        try {
            // Parsear la fecha desde el texto a java.util.Date
            java.util.Date parsedDate = dateFormat.parse(fechaExcursion);

            // Convertir java.util.Date a java.sql.Date
            Date sqlDate = new Date(parsedDate.getTime());

            // Crear una nueva Excursion (entidad JPA)
            Excursion e = new Excursion();
            e.setDescripcion(descripcion);
            e.setFechaExcursion(sqlDate);
            e.setDuracionDias(duracionExcursion);
            e.setPrecioInscripcion(precioExcursion);

            // Guardar la nueva excursion en la base de datos
            Controladora control = new Controladora();
            control.crearExcursion(e);

            // Actualizar la lista de excursiones
            actualizarListaExcursiones();

            if (!excursions.contains(e)) {
                excursions.add(e); // Agregar a la lista de excursiones si no existe

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Excursión agregada correctamente");
                alert.showAndWait();

                // Cerrar la ventana
                Stage stage = (Stage) btnGuardar.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("La excursión ya existe");
                alert.showAndWait();
            }
        } catch (ParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Formato");
            alert.setHeaderText(null);
            alert.setContentText("Formato de fecha inválido. Por favor, use el formato dd/MM/yyyy.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ocurrió un error al guardar la excursión.");
            alert.showAndWait();
        }
    }

    public void actualizarListaExcursiones() {
        excursions.setAll(control.obtenerListaExcursiones());
    }


}

