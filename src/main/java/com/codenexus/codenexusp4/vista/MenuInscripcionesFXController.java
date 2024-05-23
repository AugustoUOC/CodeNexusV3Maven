package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Inscripcion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuInscripcionesFXController implements Initializable {

    @FXML
    public TableView<Inscripcion> tablaInscripciones;
    @FXML
    public TableColumn<Inscripcion, Integer> idInscripcion;
    @FXML
    public TableColumn<Inscripcion, Integer> idSocio;
    @FXML
    public TableColumn<Inscripcion, Integer> idExcursion;
    @FXML
    public TableColumn<Inscripcion, Date> fechaInscripcion;
    @FXML
    public Button btnAgregarInscripcion;
    @FXML
    public Button btnEliminarInscripcion;
    @FXML
    public Button btnFiltrarInscripciones;
    @FXML
    public Button btnSalir;

    private Controladora control = new Controladora();

    private ObservableList<Inscripcion> inscripciones;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idInscripcion.setCellValueFactory(new PropertyValueFactory<>("idInscripcion"));
        idSocio.setCellValueFactory(new PropertyValueFactory<>("idSocio"));
        idExcursion.setCellValueFactory(new PropertyValueFactory<>("idExcursion"));
        fechaInscripcion.setCellValueFactory(new PropertyValueFactory<>("fechaInscripcion"));
        inscripciones = FXCollections.observableArrayList(control.obtenerListaInscripciones());
        tablaInscripciones.setItems(inscripciones);
    }

    public void abrirMenuAgregarInscripcion(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/AgregarInscripcion.fxml"));
            Parent root = fxmlLoader.load();

            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

            // Después de cerrar la ventana de agregar, actualizar la lista de excursiones
            actualizarTablaInscripcion();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    public void eliminarInscripcion(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/EliminarInscripcion.fxml"));
            Parent root = fxmlLoader.load();

            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

            // Después de cerrar la ventana de agregar, actualizar la lista de excursiones
            actualizarTablaInscripcion();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    public void mostrarFiltrosInscripciones(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/FiltroInscripciones.fxml"));
            Parent root = fxmlLoader.load();
            FiltroInscripcionesFXController controller = fxmlLoader.getController();
            controller.setMenuInscripcionesFXController(this);
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

    public ObservableList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void actualizarTablaInscripcion() {
        inscripciones.setAll(control.obtenerListaInscripciones());
    }

    public void actualizarTablaInscripcionFiltrada(ObservableList<Inscripcion> inscripcionesFiltradas) {
        if (inscripcionesFiltradas == null) {
            inscripcionesFiltradas = FXCollections.observableArrayList();
        }
        inscripciones.setAll(inscripcionesFiltradas);
    }

}
