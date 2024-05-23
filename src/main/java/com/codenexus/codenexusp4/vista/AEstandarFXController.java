package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Estandar;
import com.codenexus.codenexusp4.modelo.Seguro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;

public class AEstandarFXController {

    @FXML
    public TextField nif;
    @FXML
    public TextField nombre;
    @FXML
    public TextField idSeguro;
    @FXML
    public Button btnGuardar;

    private Controladora control = new Controladora();
    private ASocioFXController parentController;

    private ObservableList<Estandar> estandars;


    public void setParentController(ASocioFXController parentController) {
        this.parentController = parentController;
        this.estandars = parentController.getEstandarObsList();
    }


    @FXML
    public void initialize() {
        estandars = FXCollections.observableArrayList();
    }

    public void agregarSocio(ActionEvent actionEvent) {

        String nif = this.nif.getText();
        String nombre = this.nombre.getText();
        int idSeguro = Integer.parseInt(this.idSeguro.getText());

        try {

            Seguro seguro = control.obtenerSeguro(idSeguro);
            if (seguro == null) {
                throw new IllegalArgumentException("ID de seguro no válido");
            }
            Estandar estandar = new Estandar();
            estandar.setNif(nif);
            estandar.setNombre(nombre);
            estandar.setTipoSocio("Estandar");
            estandar.setSeguroContratado(seguro);

            control.crearSocio(estandar);
            parentController.actualizarListaSocio();
            parentController.actualizarListaEstandar();

            if (!estandars.contains(estandar)) {
                estandars.add(estandar); // Agregar a la lista de excursiones si no existe

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Socio agregado correctamente");
                alert.showAndWait();

                // Cerrar la ventana
                Stage stage = (Stage) btnGuardar.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("El Socio ya existe");
                alert.showAndWait();
            }


        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ocurrió un error al guardar el socio.");
            alert.showAndWait();
        }
    }

}

