package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Estandar;
import com.codenexus.codenexusp4.modelo.Federado;
import com.codenexus.codenexusp4.modelo.Infantil;
import com.codenexus.codenexusp4.modelo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ASocioFXController implements Initializable {


    @FXML
    public Button agregarSE;

    @FXML
    public Button agregarSF;

    @FXML
    public Button agregarSI;


    private Controladora control = new Controladora();

    private ObservableList<Socio> socios;
    private ObservableList<Estandar> estandar;
    private ObservableList<Federado> federado;
    private ObservableList<Infantil> infantil;

    public ASocioFXController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        socios = FXCollections.observableArrayList();
        estandar = FXCollections.observableArrayList();
        federado = FXCollections.observableArrayList();
        infantil = FXCollections.observableArrayList();
    }

    public void agregarSE(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/AgregarEstandar.fxml"));
            Parent root = fxmlLoader.load();
            AEstandarFXController controlador = fxmlLoader.getController();
            controlador.setParentController(this);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

            // Después de cerrar la ventana de agregar, actualizar la lista
            actualizarListaSocio();
            actualizarListaEstandar();

            Stage stage = (Stage) agregarSE.getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void agregarSF(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/AgregarFederado.fxml"));
            Parent root = fxmlLoader.load();
            AFederadoFXController controlador = fxmlLoader.getController();
            controlador.setParentController(this);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

            // Después de cerrar la ventana de agregar, actualizar la lista
            actualizarListaSocio();
            actualizarListaFederado();

            Stage stage = (Stage) agregarSF.getScene().getWindow();
            stage.close();


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void agregarSI(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/AgregarInfantil.fxml"));
            Parent root = fxmlLoader.load();
            AInfantilFXController controlador = fxmlLoader.getController();
            controlador.setParentController(this);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

            // Después de cerrar la ventana de agregar, actualizar la lista
            actualizarListaSocio();
            actualizarListaInfantil();

            Stage stage = (Stage) agregarSI.getScene().getWindow();
            stage.close();


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void actualizarListaSocio() {
        socios.setAll(control.obtenerListaSocios());
    }

    public void actualizarListaEstandar() {
        socios.setAll(control.obtenerListaEstandar());
    }

    public void actualizarListaFederado() {
        socios.setAll(control.obtenerListaFederado());
    }

    public void actualizarListaInfantil() {
        socios.setAll(control.obtenerListaInfantil());
    }


    public ObservableList<Socio> getSociosObsList() {
        return socios;
    }

    public ObservableList<Estandar> getEstandarObsList() {
        return estandar;
    }

    public ObservableList<Federado> getFederadoObsList() {
        return federado;
    }

    public ObservableList<Infantil> getInfantilObsList() {
        return infantil;
    }

}
