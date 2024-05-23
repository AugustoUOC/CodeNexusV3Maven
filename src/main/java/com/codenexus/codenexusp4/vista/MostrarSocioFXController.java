package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarError;
import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarInformacion;

public class MostrarSocioFXController {


    @FXML
    public Button btnMostrarSocios;
    @FXML
    public Button btnAplicarFiltro;
    @FXML
    public ChoiceBox<String> aplicarFiltroDesplegable;

    private Controladora control = new Controladora();
    private SocioFxController parentController;


    private ObservableList<String> tipoSocios;



    public void setParentController(SocioFxController parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() {
        tipoSocios = FXCollections.observableArrayList("Estandar", "Federado", "Infantil");
        aplicarFiltroDesplegable.setItems(tipoSocios);
    }


    public void mostrarSocios(ActionEvent actionEvent) {
        parentController.actualizarListaSocio();
        Stage stage = (Stage) btnMostrarSocios.getScene().getWindow();
        stage.close();
    }

    public void mostrarSociosPorTipo(ActionEvent actionEvent) {
        try {
            String tipoSocio = aplicarFiltroDesplegable.getValue();
            ObservableList<Socio> sociosFiltrados = control.mostrarSocioPorTipo(tipoSocio);

            if (sociosFiltrados != null && !sociosFiltrados.isEmpty()) {
                parentController.actualizarListaSocioConFiltro(sociosFiltrados);
            } else {
                mostrarInformacion("Sin resultados", "No se encontraron socios del tipo: " + tipoSocio);
            }

            Stage stage = (Stage) btnAplicarFiltro.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error", "Ocurrió un error al filtrar los socios.");
        }
    }



}
