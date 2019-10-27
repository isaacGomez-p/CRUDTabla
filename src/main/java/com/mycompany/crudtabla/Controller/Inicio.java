/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudtabla.Controller;

import Utilitarios.Carro;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author ISAACELEAZAR
 */
@Named(value = "inicio")
@SessionScoped
public class Inicio implements Serializable {
    
    @NotNull(message ="este campo es obligatorio")
    private String nombre;
    
    @NotNull(message ="este campo es obligatorio")
    private String marca;
    
    @NotNull(message ="este campo es obligatorio")
    private Date anio;
    
    private List<String> listaMarcas;
    
    private List<Carro> listaCarros;
    
    private List<Carro> filtrado;
    
    private static final Logger LOG = Logger.getLogger(Inicio.class.getName());

    public List<Carro> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<Carro> listaCarros) {
        this.listaCarros = listaCarros;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public List<String> getListaMarcas() {
        return listaMarcas;
    }

    public void setListaMarcas(List<String> listaMarcas) {
        this.listaMarcas = listaMarcas;
    }

    public List<Carro> getFiltrado() {
        return filtrado;
    }

    public void setFiltrado(List<Carro> filtrado) {
        this.filtrado = filtrado;
    }
    
    public void actionButton() throws IOException{
        Carro add = new Carro();
        add.setNombre(nombre);
        add.setMarca(marca);
        add.setAnio(anio);
        listaCarros.add(add);
        FileHandler fileTxt = new FileHandler("Logging.txt");
        SimpleFormatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        LOG.addHandler(fileTxt);
    }
    
    public void edicion(CellEditEvent event) throws IOException {
        Object valorAnt = event.getOldValue();
        Object valorNue = event.getNewValue();
         
        if(valorNue != null && !valorNue.equals(valorAnt)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda actualizada.", "Anterior: " + valorAnt + ", Nuevo:" + valorNue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        FileHandler fileTxt = new FileHandler("Logging.txt");
        SimpleFormatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        LOG.addHandler(fileTxt);
    }
    
    
    public void eliminar(Carro carro) throws IOException{
        listaCarros.remove(carro);
        FileHandler fileTxt = new FileHandler("Logging.txt");
        SimpleFormatter formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        LOG.addHandler(fileTxt);
    }
    
    @PostConstruct
    public void inicializar(){
        listaCarros = new ArrayList<>();
        
        listaMarcas = new ArrayList<>();
        listaMarcas.add("BMW");
        listaMarcas.add("Mercedes");
        listaMarcas.add("Volvo");
        listaMarcas.add("Audi");
        listaMarcas.add("Renault");
        listaMarcas.add("Fiat");
        listaMarcas.add("Volkswagen");
        listaMarcas.add("Honda");
        listaMarcas.add("Jaguar");
        listaMarcas.add("Ford");
    }   
}
