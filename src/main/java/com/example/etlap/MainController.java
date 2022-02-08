package com.example.etlap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller{

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

        etlapFeltolt();
    }

    @FXML
    public void etelClick(MouseEvent mouseEvent) {
        labelLeiras.setText(tableViewEtlap.getSelectionModel().getSelectedItem().getLeiras());
    }

    @FXML
    public void ujClick(MouseEvent mouseEvent) {
        try {
            Controller felvetel = this.ujAblak("hozzaadas-view.fxml", "Új étel", 400, 400);
            felvetel.getStage().setOnCloseRequest(event -> etlapFeltolt());
            felvetel.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void etlapFeltolt(){
        try {
            tableViewEtlap.getItems().clear();
            List<Etel> etlap = db.getEtlap();
            for (Etel etel : etlap){
                tableViewEtlap.getItems().add(etel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void torlesClick(MouseEvent mouseEvent) {
        if (tableViewEtlap.getSelectionModel().getSelectedIndex() == -1) {
            this.alert("Nincs elem megjelölve");
        }
        else if (this.felugro("Biztosan törölni szeretné az ételt?")) {
            Etel etel = tableViewEtlap.getSelectionModel().getSelectedItem();
            try {
                if(db.deleteEtel(etel.getId())){
                    this.alert("A törlés sikeres");
                    etlapFeltolt();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void buttonFt(MouseEvent mouseEvent) {
        if(tableViewEtlap.getSelectionModel().getSelectedIndex() == -1){
            if(this.felugro("Biztos szeretné emelni az összes étel árát?")){
                try {
                    db.aremelesFt(spinnerFt.getValue());
                    etlapFeltolt();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            String etelNev = tableViewEtlap.getSelectionModel().getSelectedItem().getNev();
            if(this.felugro("Biztos szeretné emelni a(z) " + etelNev + " étel árát?")){
                try {
                    db.aremelesFt(spinnerFt.getValue(), tableViewEtlap.getSelectionModel().getSelectedItem().getId());
                    etlapFeltolt();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void buttonSzazalek(MouseEvent mouseEvent) {
        if(tableViewEtlap.getSelectionModel().getSelectedIndex() == -1){
            if(this.felugro("Biztos szeretné emelni az összes étel árát?")){
                try {
                    db.aremelesSzazalek(spinnerSzazalek.getValue());
                    etlapFeltolt();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            String etelNev = tableViewEtlap.getSelectionModel().getSelectedItem().getNev();
            if(this.felugro("Biztos szeretné emelni a(z) " + etelNev + " étel árát?")){
                try {
                    db.aremelesSzazalek(spinnerSzazalek.getValue(), tableViewEtlap.getSelectionModel().getSelectedItem().getId());
                    etlapFeltolt();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}