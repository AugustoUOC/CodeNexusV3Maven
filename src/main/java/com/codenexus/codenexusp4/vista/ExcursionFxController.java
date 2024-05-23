package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
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
import com.codenexus.codenexusp4.modelo.Excursion;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ExcursionFxController implements Initializable {

    @FXML
    public Button btnAgregar;
    @FXML
    public Button filtrarExcursiones;
    @FXML
    public Button botonSalir;

    @FXML
    private TableView<Excursion> excursionTable;
    @FXML
    private TableColumn<Excursion, Integer> idExcursionColumn;
    @FXML
    private TableColumn<Excursion, String> descripcionColumn;
    @FXML
    private TableColumn<Excursion, Date> fechaExcursionColumn;
    @FXML
    private TableColumn<Excursion, Integer> duracionDiasColumn;
    @FXML
    private TableColumn<Excursion, Double> precioInscripcionColumn;

    private Controladora control = new Controladora();

    private ObservableList<Excursion> excursiones;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idExcursionColumn.setCellValueFactory(new PropertyValueFactory<>("idExcursion"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        fechaExcursionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaExcursion"));
        duracionDiasColumn.setCellValueFactory(new PropertyValueFactory<>("duracionDias"));
        precioInscripcionColumn.setCellValueFactory(new PropertyValueFactory<>("precioInscripcion"));

        excursiones = FXCollections.observableArrayList(control.obtenerListaExcursiones());
        excursionTable.setItems(excursiones);
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void agregarExcursion(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/AgregarExcursion.fxml"));
            Parent root = fxmlLoader.load();
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

            // Después de cerrar la ventana de agregar, actualizar la lista de excursiones
            actualizarListaExcursiones();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void actualizarListaExcursiones() {
        excursiones.setAll(control.obtenerListaExcursiones());
    }

    public void filtroFecha(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/FiltroFecha.fxml"));
            Parent root = fxmlLoader.load();
            FFechaFXController controlador = fxmlLoader.getController();
            controlador.setExcursionFxController(this);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<Excursion> getExcursiones() {
        return excursiones;
    }


}
