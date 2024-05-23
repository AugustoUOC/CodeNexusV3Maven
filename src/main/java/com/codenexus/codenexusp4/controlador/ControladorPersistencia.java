package com.codenexus.codenexusp4.controlador;
import com.codenexus.codenexusp4.modelo.*;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

public class ControladorPersistencia {
    //Controlador JPA para la clase Socios
    SocioJPAController SocJpa = new SocioJPAController();
    //Enviamos el socio desde el Controlador de persistencia al controlador JPA especifico de la clase
    public void crearSocio(Socio Socio){
        SocJpa.agregarSocio(Socio);
    }

    public Socio buscarSocioPorId(int idSocio){
        return SocJpa.buscarSocioPorId(idSocio);
    }

    public List<Socio> obtenerSocios(){
        return SocJpa.obtenerListaSocios();
    }

    public ObservableList<Socio> obtenerSociosPorTipo(String tipoSocio){
        return SocJpa.obtenerListaSociosPorTipo(tipoSocio);
    }

    public boolean eliminarSocioPorId(int idSocio){
        return SocJpa.eliminarSocioPorId(idSocio);
    }

    //Controladora JPA para la clase Seguros

    SeguroJPAController SegJPA = new SeguroJPAController();

    public Seguro obtenerSeguroJPA(int idSeguro){
        return SegJPA.obtenerSeguro(idSeguro);
    }

    public Seguro buscarSeguroPorTipo(String tipoSeguro){
        return SegJPA.obtenerSeguroPorTipo(tipoSeguro);
    }

    public void actualizarSeguroJPA(Estandar estandar){
        SegJPA.actualizarSeguroDeSocio(estandar);
    }

    //Controladora JPA para la clase Federaciones

    FederacionJPAController FedJPA = new FederacionJPAController();

    public Federacion crearFederacionJPA(String nombreFederacion){
        return FedJPA.crearFederacion(nombreFederacion);
    }

    public Federacion obtenerFederacion(int idFederacion){
        return FedJPA.obtenerFederacion(idFederacion);

    }

    public Federacion obtenerFederacionPorNombre(String nombreFederacion){
        return FedJPA.obtenerFederacionPorNombre(nombreFederacion);

    }

    //Controladora JPA para la clase Excursiones

    ExcursionJPAController ExcJPA = new ExcursionJPAController();

    public void crearExcursion(Excursion excursion){
        ExcJPA.agregarExcursion(excursion);
    }

    public boolean eliminarExcursion(int idExcursion){
        return ExcJPA.eliminarExcursion(idExcursion);
    }

    public ObservableList<Excursion> obtenerExcursionesPorFecha(java.sql.Date fechaInicio, java.sql.Date fechaFin){
        return ExcJPA.obtenerListaExcursionesFiltroFecha(fechaInicio, fechaFin);
    }

    public ObservableList<Excursion> obtenerListaExcursiones(){
       return ExcJPA.obtenerListaExcursiones();
    }

    public Excursion buscarExcursionPorId(int idExcursion){
        return ExcJPA.buscarExcursionPorId(idExcursion);
    }

    //Controladora JPA para la clase Excursiones

    InscripcionJPAController InsJPA = new InscripcionJPAController();

    public void crearInscripcion(Inscripcion inscripcion){
        InsJPA.agregarInscripcion(inscripcion);
    }

    public Inscripcion buscarInscripcionPorId(int idInscripcion){
        return InsJPA.buscarInscripcionPorID(idInscripcion);
    }

    public void eliminarInscripcion(int idInscripcion){
        InsJPA.eliminarInscripcion(idInscripcion);
    }

    public List<Inscripcion> obtenerListaInscripciones(){
        return InsJPA.obtenerListaInscripciones();
    }

    public List<Inscripcion> obtenerInscripcionesPorSocio(int idSocio){
        return InsJPA.obtenerInscripcionesporSocio(idSocio);
    }

    public List<Inscripcion> obtenerInscripcionesPorFechas(java.sql.Date fechaInicio, java.sql.Date fechaFin){
        return InsJPA.obtenerInscripcionesFiltroFechas(fechaInicio, fechaFin);
    }


    public List<Inscripcion> obtenerInscripcionesPorSocioYFechas(int idSocio, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
        return InsJPA.obtenerInscripcionesPorSocioYFechas(idSocio, fechaInicio, fechaFin);
    }
}

