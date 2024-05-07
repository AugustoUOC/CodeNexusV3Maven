package org.CodeNexus.controlador;


import org.CodeNexus.modelo.*;

import java.util.List;

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

    public List<Socio> mostrarSocioPorTipo(String tipoSocio){
        return controlPersis.obtenerSociosPorTipo(tipoSocio);
    }

    public boolean eliminarSocioPorId(int idSocio){
        return controlPersis.eliminarSocioPorId(idSocio);
    }

    //Controladora para mandar los seguros al JPA
    public Seguro obtenerSeguro(int idSeguro){
        return controlPersis.obtenerSeguroJPA(idSeguro);
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

    public List<Excursion> obtenerExcursionPorFecha(java.sql.Date fechaInicio, java.sql.Date fechaFin){
        return controlPersis.obtenerExcursionesPorFecha(fechaInicio, fechaFin);
    }

    public List<Excursion> obtenerListaExcursiones(){
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

}
