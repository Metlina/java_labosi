package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.glavna.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class GlavnaController {

	public void prikaziKnjige()
	{
		try {
			BorderPane knjigePane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/knjige.fxml"));
			//BorderPane knjigePane = (BorderPane)FXMLLoader.load(new File("src/hr/tvz/java/vjezbe/fxml/knjige.fxml").toURI().toURL());
			Main.setCenterPane(knjigePane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void prikaziCasopise()
	{
		try {
			BorderPane casopisiPane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/casopisi.fxml"));
			//BorderPane casopisiPane = (BorderPane)FXMLLoader.load(new File("src/hr/tvz/java/vjezbe/fxml/casopisi.fxml").toURI().toURL());
			Main.setCenterPane(casopisiPane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void prikaziClanove()
	{
		try {
			BorderPane clanoviPane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/clanovi.fxml"));
			//BorderPane clanoviPane = (BorderPane)FXMLLoader.load(new File("src/hr/tvz/java/vjezbe/fxml/clanovi.fxml").toURI().toURL());
			Main.setCenterPane(clanoviPane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
