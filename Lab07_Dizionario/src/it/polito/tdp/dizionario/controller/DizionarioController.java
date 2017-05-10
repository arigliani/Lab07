package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {
	Model model= new Model();

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;

	@FXML
	void doReset(ActionEvent event) {
		txtResult.setText("Reset!");
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		try {
		txtResult.clear();
		int i= Integer.parseInt(inputNumeroLettere.getText());
		List<String> grafo=  model.createGraph(i);
		String elenco=this.trasformaStringa(grafo);
		txtResult.setText(elenco);
		
		} catch(IllegalArgumentException e){
			throw new IllegalArgumentException();
		}
	}

	private String trasformaStringa(List<String> grafo) {
		String elenco="";
		for(String i: grafo){
			elenco+=i+"\n";
		}
		return elenco.trim();
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		try {
			txtResult.clear();
			int i= Integer.parseInt(inputNumeroLettere.getText());
			String parola= model.findMaxDegree(i);
			txtResult.setText("la parola con grado max e': "+parola);
			

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		try {
			txtResult.clear();
			String parola= inputParola.getText();
			List<String> grafo=model.displayNeighbours(parola);
			String elenco=this.trasformaStringa(grafo);
			txtResult.setText(elenco);

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}