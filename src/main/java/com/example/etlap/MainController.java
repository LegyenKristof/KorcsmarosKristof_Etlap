package com.example.etlap;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    public TableColumn<Etel, String>  colNev;
    @FXML
    public TableColumn<Etel, String>  colKategoria;
    @FXML
    public TableColumn<Etel, Integer> colAr;
    @FXML
    public Spinner<Integer> spinnerFt;
    @FXML
    public Spinner<Integer> spinnerSzazalek;
    @FXML
    public Label labelLeiras;
    @FXML
    public TableView<Etel> tableViewEtlap;

    public EtlapDB db;

    public void initialize(){
        db = new EtlapDB();

        colNev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        colKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        colAr.setCellValueFactory(new PropertyValueFactory<>("ar"));

        try {
            List<Etel> etlap = db.getEtlap();
            for (Etel etel : etlap){
                tableViewEtlap.getItems().add(etel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}