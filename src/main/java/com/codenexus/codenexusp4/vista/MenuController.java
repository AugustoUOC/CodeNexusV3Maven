package com.codenexus.codenexusp4.vista;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuController {
    //Botones de para la Escena
    @FXML
    public Button botonExcursiones;
    @FXML
    public Button botonSocios;
    @FXML
    public Button botonInscripciones;
    @FXML
    public Button botonSalir;

    //Eventos para cada Boton
    @FXML
    void showExcursiones(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/MenuExcursiones.fxml"));
            Parent root = fxmlLoader.load();
            ExcursionFxController controlador = fxmlLoader.getController();
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    @FXML
    void showSocios(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/MenuSocios.fxml"));
            Parent root = fxmlLoader.load();
            SocioFxController controlador = fxmlLoader.getController();
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @FXML
    void showInscripciones(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/MenuInscripciones.fxml"));
            Parent root = fxmlLoader.load();
            MenuInscripcionesFXController controlador = fxmlLoader.getController();

            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void click(ActionEvent actionEvent) {

    }
}

