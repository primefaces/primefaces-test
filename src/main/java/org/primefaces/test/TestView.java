package org.primefaces.test;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TestView implements Serializable {

    private static final int CHUNK_SIZE = 10;
    private static final int DATA_SET_SIZE = 10;
    private static final int DATA_ROWS = 100;

    private String testString;

    private List<Car> cars;

    private LazyDataModel carsLDM;

    @Inject
    private CarService carService;

    @PostConstruct
    public void init() {
        testString = "PrimeFaces Lazy DataScroller Test";
        cars = carService.createCars(DATA_ROWS);
        carsLDM = new LazyDataModel<Car>() {
            @Override
            public List<Car> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                this.setRowCount(DATA_ROWS);
                return carService.getDataSet(first, DATA_SET_SIZE);
            }
        };
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("The Cars table fully loaded",
                "100 rows were populated.  The chunk size is " + CHUNK_SIZE +
                        ". Bring the bottom of the viewport up to the bottom of the data scroll output to trigger " +
                        "scrolling.  Clicking on the link returns valid items only for the last 10 records added to" +
                        "the scrolling array."));

    }

    public String reportIdOfCar(Car car) {
        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Test",
//                "Test"));
        context.addMessage(null, new FacesMessage("This car has an id of " + car.getId(),
               "For " + car.getYear() + " " + car.getBrand()));
        return null;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public LazyDataModel getCarsLDM() {
        return carsLDM;
    }

    public void setCarsLDM(LazyDataModel carsLDM) {
        this.carsLDM = carsLDM;
    }

    public int getChunkSize() {
        return CHUNK_SIZE;
    }
}
