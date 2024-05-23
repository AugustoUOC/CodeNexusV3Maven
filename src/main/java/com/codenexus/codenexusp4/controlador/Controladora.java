package com.codenexus.codenexusp4.controlador;



import com.codenexus.codenexusp4.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class Controladora {

    ControladorPersistencia controlPersis = new ControladorPersistencia();
    //Controladora para mandar los Socios al JPA
    public void crearSocio(Socio socio){
        controlPersis.crearSocio(socio);
    }

    public Socio buscarSocioPorId(int idSocio){
        return controlPersis.buscarSocioPorId(idSocio);
    }

    public List<Socio> obtenerListaSocios(){
        return controlPersis.obtenerSocios();
    }

    public ObservableList<Socio> mostrarSocioPorTipo(String tipoSocio){
        return controlPersis.obtenerSociosPorTipo(tipoSocio);
    }

    public ObservableList<Estandar> obtenerListaEstandar() {
        List<Socio> socios = controlPersis.obtenerSociosPorTipo("ESTANDAR");
        List<Estandar> estandarList = socios.stream()
                .filter(socio -> socio instanceof Estandar)
                .map(socio -> (Estandar) socio)
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(estandarList);
    }

    public List<Federado> obtenerListaFederado(){
        return controlPersis.obtenerSociosPorTipo("FEDERADO").stream()
                .filter(socio -> socio instanceof Federado)
                .map(socio -> (Federado) socio)
                .collect(Collectors.toList());
    }

    public List<Infantil> obtenerListaInfantil(){
        return controlPersis.obtenerSociosPorTipo("INFANTIL").stream()
                .filter(socio -> socio instanceof Infantil)
                .map(socio -> (Infantil) socio)
                .collect(Collectors.toList());
    }

    public boolean eliminarSocioPorId(int idSocio){
        return controlPersis.eliminarSocioPorId(idSocio);
    }

    //Controladora para mandar los seguros al JPA
    public Seguro obtenerSeguro(int idSeguro) {
        return controlPersis.obtenerSeguroJPA(idSeguro);
    }

    public Seguro buscarSeguroPorTipo(String tipo) {
        return controlPersis.buscarSeguroPorTipo(tipo);
    }

    public void actualizarSeguro(Estandar estandar){
        controlPersis.actualizarSeguroJPA(estandar);
    }



    //Controladora para mandar las Federaciones al JPA
    public Federacion crearFederacion(String nombreFederacion){
        return controlPersis.crearFederacionJPA(nombreFederacion);
    }

    public Federacion obtenerFederacionPorNombre(String nombreFederacion){
        return controlPersis.obtenerFederacionPorNombre(nombreFederacion);
    }

    public Federacion obtenerFederacion(int idFederacion){
        return controlPersis.obtenerFederacion(idFederacion);
    }

    //Controladora para mandar las Excursiones al JPA

    public void crearExcursion(Excursion excursion){
        controlPersis.crearExcursion(excursion);
    }

    public  boolean eliminarExcursion(int idExcursion){
        return controlPersis.eliminarExcursion(idExcursion);
    }

    public ObservableList<Excursion> obtenerExcursionPorFecha(java.sql.Date fechaInicio, java.sql.Date fechaFin){
        return controlPersis.obtenerExcursionesPorFecha(fechaInicio, fechaFin);
    }

    public ObservableList<Excursion> obtenerListaExcursiones(){
       return controlPersis.obtenerListaExcursiones();
    }

    public Excursion buscarExcursionPorId(int idExcursion){
        return controlPersis.buscarExcursionPorId(idExcursion);
    }

    //Controladora para mandar las Inscripciones al JPA

    public void crearInscripcion(Inscripcion inscripcion){
        controlPersis.crearInscripcion(inscripcion);
    }

    public Inscripcion buscarInscripcionPorId(int idInscripcion){
        return controlPersis.buscarInscripcionPorId(idInscripcion);
    }

    public void eliminarInscripcion(int idInscripcion){
        controlPersis.eliminarInscripcion(idInscripcion);
    }

    public List<Inscripcion> obtenerListaInscripciones(){
        return controlPersis.obtenerListaInscripciones();
    }

    public List<Inscripcion> obtenerInscripcionesPorSocio(int idSocio){
        return controlPersis.obtenerInscripcionesPorSocio(idSocio);
    }

    public List<Inscripcion> obtenerInscripcionesPorFechas(java.sql.Date fechaInicio, java.sql.Date fechaFin){
        return controlPersis.obtenerInscripcionesPorFechas(fechaInicio, fechaFin);
    }

    public List<Inscripcion> obtenerInscripcionesPorSocioYFechas(int idSocio, java.sql.Date fechaInicio, java.sql.Date fechaFin) {
        return controlPersis.obtenerInscripcionesPorSocioYFechas(idSocio, fechaInicio, fechaFin);
    }

}
