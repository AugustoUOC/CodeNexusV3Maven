package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Excursion;
import com.codenexus.codenexusp4.modelo.Inscripcion;
import com.codenexus.codenexusp4.modelo.Socio;
import com.codenexus.codenexusp4.utilidad.Generador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarError;
import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarInformacion;

public class AInscripcionFXController {


    @FXML
    public TableView<Excursion> tablaExcursiones;
    @FXML
    public TableColumn<Excursion, Integer> idExcursionColumn;
    @FXML
    public TableColumn<Excursion, String> descripcionExcursionColumn;
    @FXML
    public TableColumn<Excursion, Double> precioInscripcionColumn;
    @FXML
    public TextField idSocio;
    @FXML
    public TextField idExcursion;
    @FXML
    public Button btnCrearInscripcion;

    private Controladora control = new Controladora();



    private ObservableList<Excursion> excursiones;

    @FXML
    public void initialize() {
        idExcursionColumn.setCellValueFactory(new PropertyValueFactory<>("idExcursion"));
        descripcionExcursionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioInscripcionColumn.setCellValueFactory(new PropertyValueFactory<>("precioInscripcion"));
        excursiones = FXCollections.observableArrayList(control.obtenerListaExcursiones());
        tablaExcursiones.setItems(excursiones);

    }

    public void agregarInscripcion(ActionEvent actionEvent) {

        int idSocio = Integer.parseInt(this.idSocio.getText());
        int idExcursion = Integer.parseInt(this.idExcursion.getText());

        Socio socioEncontrado = control.buscarSocioPorId(idSocio);
        Excursion excursionAInscribir = control.buscarExcursionPorId(idExcursion);

        if (socioEncontrado != null && excursionAInscribir != null) {
            Inscripcion i = new Inscripcion();
            i.setIdSocio(socioEncontrado.getIdSocio());
            i.setIdExcursion(excursionAInscribir.getIdExcursion());
            i.setFechaInscripcion(Generador.generarFechaActual());
            control.crearInscripcion(i);
            mostrarInformacion("Informacion", "Inscripcion agregada correctamente");

        } else {
            mostrarError("Error", "No se ha podido agregar la Inscripcion");
        }

        Stage stage = (Stage) btnCrearInscripcion.getScene().getWindow();
        stage.close();

    }
}
