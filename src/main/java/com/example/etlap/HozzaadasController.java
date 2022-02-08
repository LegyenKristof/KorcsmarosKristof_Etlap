package com.example.etlap;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HozzaadasController extends Controller{

    @FXML
    public Spinner<Integer> spinnerAr;
    @FXML
    public ChoiceBox<String> choiceBoxKategoria;
    @FXML
    public TextArea textAreaLeiras;
    @FXML
    public TextField textFieldNev;

    private EtlapDB db;

    public void initialize(){
        db = new EtlapDB();
        choiceBoxKategoria.setValue("előétel");
        choiceBoxKategoria.getItems().add("előétel");
        choiceBoxKategoria.getItems().add("főétel");
        choiceBoxKategoria.getItems().add("desszert");
    }

    @FXML
    public void hozzaadasClick(MouseEvent mouseEvent) {
        String nev = textFieldNev.getText();
        String leiras = textAreaLeiras.getText();
        String kategoria = choiceBoxKategoria.getSelectionModel().getSelectedItem();
        int ar = spinnerAr.getValue();

        if (nev.isEmpty() || leiras.isEmpty() || kategoria.isEmpty()) {
            this.alert("Az összes adat megadása kötelező!");
        }
        else{
            try {
                if (db.ujEtel(nev, leiras, kategoria, ar) == 1) {
                    this.alert("Étel hozzáadása sikeres!");
                } else {
                    this.alert("Étel hozzáadása sikertelen!");
                }
            } catch (Exception e) {
                this.alert(e.getMessage());
            }

            textFieldNev.setText("");
            textAreaLeiras.setText("");
            choiceBoxKategoria.setValue("előétel");
            spinnerAr.getValueFactory().setValue(1);
        }
    }
}
