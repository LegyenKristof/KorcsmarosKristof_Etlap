package com.example.etlap;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class KategoriaHozzaadasController extends Controller {

    @FXML
    public TextField textFieldKatNev;

    private EtlapDB db;

    public void initialize(){
        db = new EtlapDB();
    }

    @FXML
    public void hozzaadasClick(MouseEvent mouseEvent) {
        String nev = textFieldKatNev.getText();

        if (nev.isEmpty()) {
            this.alert("A név megadása kötelező!");
        }
        else{
            try {
                if (db.ujKategoria(nev) == 1) {
                    this.alert("Kategória hozzáadása sikeres!");
                } else {
                    this.alert("Kategória hozzáadása sikertelen!");
                }
            } catch (SQLException e) {
                if (e.getSQLState().startsWith("23")){
                    this.alert("Az adott névvel már létezik kategória!");
                }
                else{
                    this.alert(e.getMessage());
                }
            }

            textFieldKatNev.setText("");
        }
    }
}
