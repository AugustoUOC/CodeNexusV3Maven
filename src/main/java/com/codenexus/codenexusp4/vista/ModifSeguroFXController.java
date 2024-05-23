package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.Estandar;
import com.codenexus.codenexusp4.modelo.Seguro;
import com.codenexus.codenexusp4.modelo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarError;
import static com.codenexus.codenexusp4.utilidad.InformacionSistema.mostrarInformacion;

public class ModifSeguroFXController {

    @FXML
    public TextField idSocio;
    @FXML
    public ChoiceBox<String> cambioSeguroDesplegable;
    @FXML
    public Button buscarSocio;
    @FXML
    public Button cambiarSeguro;

    private Controladora control = new Controladora();
    private SocioFxController parentController;
    private ObservableList<Seguro> seguros;
    private ObservableList<String> opcionesSeguro;

    public ModifSeguroFXController() {

    }

    @FXML
    public void initialize() {
        opcionesSeguro = FXCollections.observableArrayList("Básico", "Completo");
        cambioSeguroDesplegable.setItems(opcionesSeguro);
    }

    public void setParentController(SocioFxController parentController) {
        this.parentController = parentController;
    }

    public void comprobarSocio(ActionEvent actionEvent) {
        try {
            int idSocio = Integer.parseInt(this.idSocio.getText());
            Socio socioEncontrado = control.buscarSocioPorId(idSocio);

            if (socioEncontrado != null) {
                if (socioEncontrado instanceof Estandar) {
                    Estandar estandar = (Estandar) socioEncontrado;
                    String tipoSeguro = estandar.getSeguroContratado().getTipo();
                    cambioSeguroDesplegable.setValue(tipoSeguro);

                    mostrarInformacion("Socio Encontrado", "El socio es de tipo Estandar y su seguro es: " + tipoSeguro);
                } else {
                    cambioSeguroDesplegable.setValue(null);
                    mostrarInformacion("Socio Encontrado", "El socio no es de tipo Estandar.");
                }
            } else {
                cambioSeguroDesplegable.setValue(null);
                mostrarInformacion("Socio No Encontrado", "El socio no existe.");
            }
        } catch (NumberFormatException e) {
            cambioSeguroDesplegable.setValue(null);
            mostrarError("Error de Formato", "El ID del socio debe ser un número válido.");
        }
    }

    public void cambiarSeguro(ActionEvent actionEvent) {
        try {
            int idSocio = Integer.parseInt(this.idSocio.getText());
            String nuevoTipoSeguro = cambioSeguroDesplegable.getValue();

            Socio socioEncontrado = control.buscarSocioPorId(idSocio);

            if (socioEncontrado == null) {
                mostrarInformacion("Socio No Encontrado", "El socio no existe.");
                return;
            }

            if (!(socioEncontrado instanceof Estandar)) {
                mostrarInformacion("Socio Incorrecto", "El socio no es de tipo Estandar.");
                return;
            }

            Estandar estandar = (Estandar) socioEncontrado;
            Seguro seguroActual = estandar.getSeguroContratado();

            if (seguroActual.getTipo().equals(nuevoTipoSeguro)) {
                mostrarInformacion("Sin Cambios", "El seguro seleccionado es el mismo que el actual.");
                return;
            }

            Seguro nuevoSeguro = control.buscarSeguroPorTipo(nuevoTipoSeguro);
            if (nuevoSeguro == null) {
                throw new IllegalArgumentException("Tipo de seguro no válido");
            }

            estandar.setSeguroContratado(nuevoSeguro);
            control.actualizarSeguro(estandar);

            mostrarInformacion("Actualización Exitosa", "El seguro del socio ha sido actualizado a: " + nuevoTipoSeguro);
            actualizarSeguro(nuevoTipoSeguro);

            // Cerrar la ventana solo si no hay excepciones
            Stage stage = (Stage) cambiarSeguro.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            mostrarError("Error de Formato", "El ID del socio debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            mostrarError("Argumento Inválido", e.getMessage());
        }
    }



    public void actualizarSeguro(String tipoSeguro) {
        seguros.setAll(control.buscarSeguroPorTipo(tipoSeguro));
    }
}
