package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SocioFxController implements Initializable {

    @FXML
    public Button salir;
    @FXML
    public Button mostrarFactura;
    @FXML
    public Button mostrarFiltroSocio;
    @FXML
    public Button eliminarSocio;
    @FXML
    public Button modificarSeguro;
    @FXML
    public Button agregarSocio;

    @FXML
    public TableView<Socio> tablaSocios;
    @FXML
    public TableColumn<Socio, Integer> idSocioColumn;
    @FXML
    public TableColumn<Socio, String> nombreSocioColumn;
    @FXML
    public TableColumn<Socio, String> tipoSocioColumn;

    @FXML
    public TableView<Estandar> tablaEstandar;
    @FXML
    public TableColumn<Estandar, Integer> idSEstandarColumn;
    @FXML
    public TableColumn<Estandar, String> nifEstandarColumn;
    @FXML
    public TableColumn<Estandar, Integer> seguroContradadoColumn;

    @FXML
    public TableView<Federado> tablaFederado;
    @FXML
    public TableColumn<Federado, Integer> idSFederadoColumn;
    @FXML
    public TableColumn<Federado, String> nifFederadoColumn;
    @FXML
    public TableColumn<Federado, Integer> idFederacionColumn;

    @FXML
    public TableView<Infantil> tablaInfantil;
    @FXML
    public TableColumn<Infantil, Integer> idSInfantilColumn;
    @FXML
    public TableColumn<Infantil, Socio> idTutorColumn;

    private Controladora control = new Controladora();

    private ObservableList<Socio> socios;
    private ObservableList<Estandar> estandar;
    private ObservableList<Federado> federado;
    private ObservableList<Infantil> infantil;
    private ObservableList<Seguro> seguros;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración de las columnas de la tabla de Socios
        idSocioColumn.setCellValueFactory(new PropertyValueFactory<>("idSocio"));
        nombreSocioColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tipoSocioColumn.setCellValueFactory(new PropertyValueFactory<>("tipoSocio"));
        socios = FXCollections.observableArrayList(control.obtenerListaSocios());
        tablaSocios.setItems(socios);

        // Configuración de las columnas de la tabla de Estandar
        idSEstandarColumn.setCellValueFactory(cellData -> {
            Estandar estandar1 = cellData.getValue();
            return new ReadOnlyObjectWrapper<>(estandar1 != null ? estandar1.getIdSocio() : null);
        });
        nifEstandarColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        seguroContradadoColumn.setCellValueFactory(cellData -> {
            Seguro seguro = cellData.getValue().getSeguroContratado();
            return new ReadOnlyObjectWrapper<>(seguro != null ? seguro.getIdSeguro() : null);
        });
        estandar = FXCollections.observableArrayList(control.obtenerListaEstandar());
        tablaEstandar.setItems(estandar);

        // Configuración de las columnas de la tabla de Federado
        idSFederadoColumn.setCellValueFactory(new PropertyValueFactory<>("idSocio"));
        nifFederadoColumn.setCellValueFactory(new PropertyValueFactory<>("nif"));
        idFederacionColumn.setCellValueFactory(cellData -> {
            Federacion federacion = cellData.getValue().getFederacion();
            return new ReadOnlyObjectWrapper<>(federacion != null ? federacion.getIdFederacion() : null);
        });
        federado = FXCollections.observableArrayList(control.obtenerListaFederado());
        tablaFederado.setItems(federado);

        // Configuración de las columnas de la tabla de Infantil
        idSInfantilColumn.setCellValueFactory(new PropertyValueFactory<>("idSocio"));
        idTutorColumn.setCellValueFactory(new PropertyValueFactory<>("idTutor"));
        infantil = FXCollections.observableArrayList(control.obtenerListaInfantil());
        tablaInfantil.setItems(infantil);
    }

    public void agregarSocio(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/AgregarSocio.fxml"));
            Parent root = fxmlLoader.load();

            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();
            actualizarListaSocio();
            actualizarListaEstandar();
            actualizarListaFederado();
            actualizarListaInfantil();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void eliminarSocio(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/EliminarSocio.fxml"));
            Parent root = fxmlLoader.load();
            EliminarSocioFXController controlador = fxmlLoader.getController();
            controlador.setParentController(this);

            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();
            actualizarListaSocio();
            actualizarListaEstandar();
            actualizarListaFederado();
            actualizarListaInfantil();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void salir(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void mostrarFactura(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/FacturaSocio.fxml"));
            Parent root = fxmlLoader.load();

            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    public void mostrarFiltroSocio(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/MostrarSocios.fxml"));
            Parent root = fxmlLoader.load();
            MostrarSocioFXController controlador = fxmlLoader.getController();
            controlador.setParentController(this);
            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    public void modificarSeguro(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/codenexus/codenexusp4/ModificarSeguro.fxml"));
            Parent root = fxmlLoader.load();
            ModifSeguroFXController controlador = fxmlLoader.getController();
            controlador.setParentController(this);

            Scene escena = new Scene(root);
            Stage escenaStage = new Stage();
            escenaStage.initModality(Modality.APPLICATION_MODAL);
            escenaStage.setScene(escena);
            escenaStage.showAndWait();
            actualizarListaSocio();
            actualizarListaEstandar();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void actualizarListaSocio() {
        socios.setAll(control.obtenerListaSocios());
    }

    public void actualizarListaEstandar() {
        estandar.setAll(control.obtenerListaEstandar());
    }

    public void actualizarListaFederado() {
        federado.setAll(control.obtenerListaFederado());
    }

    public void actualizarListaInfantil() {
        infantil.setAll(control.obtenerListaInfantil());
    }

    public ObservableList<Socio> getSociosObsList() {
        return socios;
    }

    public ObservableList<Seguro> getSegurosObsList() {
        return seguros;
    }

    public void actualizarListaSocioConFiltro(ObservableList<Socio> sociosFiltrados) {
        socios.setAll(sociosFiltrados);
    }
}
