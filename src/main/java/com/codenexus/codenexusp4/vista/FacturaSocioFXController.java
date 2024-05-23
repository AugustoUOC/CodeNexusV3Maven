package com.codenexus.codenexusp4.vista;

import com.codenexus.codenexusp4.controlador.Controladora;
import com.codenexus.codenexusp4.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FacturaSocioFXController {

    @FXML
    public TextField idSocioField;
    @FXML
    public Button mostrarFacturaBtn;
    @FXML
    public TextArea facturaTextArea;

    private Controladora control = new Controladora();





    public void mostrarFactura(ActionEvent actionEvent) {

        try {
            int idSocio = Integer.parseInt(idSocioField.getText());
            Socio socioFactura = control.buscarSocioPorId(idSocio);
            if (socioFactura != null) {
                StringBuilder facturaInfo = new StringBuilder();
                facturaInfo.append("Id del Socio: ").append(socioFactura.getIdSocio()).append("\n");
                facturaInfo.append("Nombre del Socio: ").append(socioFactura.getNombre()).append("\n");
                facturaInfo.append("Factura mensual del socio número: ").append(socioFactura.getIdSocio()).append("\n");
                facturaInfo.append(Datos.mostrarFactura(socioFactura)).append("\n");

                facturaTextArea.setText(facturaInfo.toString());
            } else {
                facturaTextArea.setText("Socio no encontrado.");
            }
        } catch (NumberFormatException e) {
            facturaTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            facturaTextArea.setText("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
