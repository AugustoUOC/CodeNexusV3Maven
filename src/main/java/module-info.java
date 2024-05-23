module com.codenexus.codenexusp4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jakarta.persistence;
    requires java.desktop;
    requires org.hibernate.orm.core;

    opens com.codenexus.codenexusp4.modelo to org.hibernate.orm.core, javafx.base;

    exports com.codenexus.codenexusp4;
    exports com.codenexus.codenexusp4.controlador;
    exports com.codenexus.codenexusp4.vista;
    exports com.codenexus.codenexusp4.modelo;


    opens com.codenexus.codenexusp4 to javafx.fxml;
    opens com.codenexus.codenexusp4.vista to javafx.fxml;

}