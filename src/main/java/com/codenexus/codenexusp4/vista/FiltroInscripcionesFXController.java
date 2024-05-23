package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Inscripcion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class FiltroInscripcionesFXController {

    @FXML
    public Button btnMostrarInscripciones;
    @FXML
    public Button btnFiltrarPorSocio;
    @FXML
    public Button btnFiltrarPorFecha;
    @FXML
    public Button btnFiltroPorSocioYFecha;
    @FXML
    public Button btnSalir;

    private Controladora control = new Controladora();

    private MenuInscripcionesFXController menuInscripcionesFXController;

    public void setMenuInscripcionesFXController(MenuInscripcionesFXController menuInscripcionesFXController) {
        this.menuInscripcionesFXController = menuInscripcionesFXController;
    }


    public void mostrarInscripciones(ActionEvent actionEvent) {
        List<Inscripcion> listaInscripciones = control.obtenerListaInscripciones();
        ObservableList<Inscripcion> todasLasInscripciones = FXCollections.observableArrayList(listaInscripciones);
        menuInscripcionesFXController.actualizarTablaInscripcionFiltrada(todasLasInscripciones);
        Stage stage = (Stage) btnMostrarInscripciones.getScene().getWindow();
        stage.close();
    }

    public void abrirFiltroPorSocio(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/FiltroSocioInscripcion.fxml"));
            Parent root = fxmlLoader.load();
            FiltroSocioInscripcionFXController controller = fxmlLoader.getController();
            controller.setMenuInscripcionesFXController(menuInscripcionesFXController);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }



    public void abrirFiltroFecha(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/FiltroFechaInscripciones.fxml"));
            Parent root = fxmlLoader.load();
            FiltroFechaInscripcionesFXController controller = fxmlLoader.getController();
            controller.setMenuInscripcionesFXController(menuInscripcionesFXController);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void abrirFiltroPorSocioYFecha(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/FiltroSocioYFechaInscripcion.fxml"));
            Parent root = fxmlLoader.load();
            FiltroSocioYFechaInscripcionFXController controller = fxmlLoader.getController();
            controller.setMenuInscripcionesFXController(menuInscripcionesFXController);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void salir(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
