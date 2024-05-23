package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Estandar;
import com.codenexus.codenexusp4.modelo.Federacion;
import com.codenexus.codenexusp4.modelo.Federado;
import com.codenexus.codenexusp4.modelo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AFederadoFXController {

    @FXML
    public TextField nif;
    @FXML
    public TextField nombre;
    @FXML
    public TextField nombreFederacion;
    @FXML
    public Button btnGuardar;

    private Controladora control = new Controladora();
    private ASocioFXController parentController;

    private ObservableList<Federado> federados;



    public void setParentController(ASocioFXController parentController) {
        this.parentController = parentController;
        this.federados = parentController.getFederadoObsList();
    }

    @FXML
    public void initialize() {
        federados = FXCollections.observableArrayList();
    }


    public void agregarSocio(ActionEvent actionEvent) {

        String nif = this.nif.getText();
        String nombre = this.nombre.getText();
        String nombreFederacion = this.nombreFederacion.getText();

        try {
            Federacion federacion = control.obtenerFederacionPorNombre(nombreFederacion);
            Federado f = new Federado();
            f.setFederacion(federacion);
            f.setNif(nif);
            f.setNombre(nombre);
            f.setTipoSocio("Federado");

            control.crearSocio(f);
            parentController.actualizarListaSocio();
            parentController.actualizarListaFederado();

            if (!federados.contains(f)){
                federados.add(f);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Socio agregado correctamente");
                alert.showAndWait();

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
