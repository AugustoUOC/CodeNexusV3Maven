package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Federacion;
import com.codenexus.codenexusp4.modelo.Federado;
import com.codenexus.codenexusp4.modelo.Infantil;
import com.codenexus.codenexusp4.modelo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AInfantilFXController {

    @FXML
    public TextField nombre;
    @FXML
    public TextField idTutor;
    @FXML
    public Button btnGuardar;


    private Controladora control = new Controladora();

    private ObservableList<Infantil> infantils;
    private ASocioFXController parentController;

    public void setParentController(ASocioFXController parentController) {
        this.parentController = parentController;
        this.infantils = parentController.getInfantilObsList();  // Obtén la lista observable de federados
    }
    @FXML
    public void initialize() {
        infantils = FXCollections.observableArrayList();
    }

    public void agregarSocio(ActionEvent actionEvent) {

        String nombre = this.nombre.getText();
        int idTutor = Integer.parseInt(this.idTutor.getText());

        try {
            // Busca el tutor en la base de datos
            Socio tutor = control.buscarSocioPorId(idTutor);
            if (tutor == null) {
                throw new IllegalArgumentException("ID de tutor no válido");
            }
            Infantil inf = new Infantil();
            inf.setIdTutor(idTutor);
            inf.setNombre(nombre);
            inf.setTipoSocio("Infantil");

            control.crearSocio(inf);
            parentController.actualizarListaSocio();
            parentController.actualizarListaInfantil();

            if (!infantils.contains(inf)){
                infantils.add(inf);

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
