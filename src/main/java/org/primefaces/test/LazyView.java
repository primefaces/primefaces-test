/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.primefaces.test;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Davide Mainardi <ingmainardi at live.com>
 */
@Named(value = "dtLazyView")
@ViewScoped
public class LazyView implements Serializable {
     
    private LazyDataModel<Car> lazyModel;
     
    private Car selectedCar;
     
    @Inject
    private CarService service;
     
    @PostConstruct
    public void init() {
        lazyModel = new LazyCarDataModel(service.createCars(200));
    }
 
    public LazyDataModel<Car> getLazyModel() {
        return lazyModel;
    }
 
    public Car getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
     
    public void setService(CarService service) {
        this.service = service;
    }
     
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}