package com.example.etlap;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.List;

public class HozzaadasController extends Controller{

    @FXML
    public Spinner<Integer> spinnerAr;
    @FXML
    public ChoiceBox<Kategoria> choiceBoxKategoria;
    @FXML
    public TextArea textAreaLeiras;
    @FXML
    public TextField textFieldNev;

    private EtlapDB db;
    private List<Kategoria> kategoriak;

    public void initialize(){
        db = new EtlapDB();

        try {
            kategoriak = db.getKategoriak();
            for(Kategoria kategoria : kategoriak){
                choiceBoxKategoria.getItems().add(kategoria);
            }
            choiceBoxKategoria.setValue(kategoriak.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hozzaadasClick(MouseEvent mouseEvent) {
        String nev = textFieldNev.getText();
        String leiras = textAreaLeiras.getText();
        int kategoria = choiceBoxKategoria.getSelectionModel().getSelectedItem().getId();
        int ar = spinnerAr.getValue();

        if (nev.isEmpty() || leiras.isEmpty() || kategoria == 0) {
            this.alert("Az összes adat megadása kötelező!");
        }
        else{
            try {
                if (db.ujEtel(nev, leiras, kategoria, ar)) {
                    this.alert("Étel hozzáadása sikeres!");
                } else {
                    this.alert("Étel hozzáadása sikertelen!");
                }
            } catch (Exception e) {
                this.alert(e.getMessage());
            }

            textFieldNev.setText("");
            textAreaLeiras.setText("");
            choiceBoxKategoria.setValue(kategoriak.get(0));
            spinnerAr.getValueFactory().setValue(1);
        }
    }
}
