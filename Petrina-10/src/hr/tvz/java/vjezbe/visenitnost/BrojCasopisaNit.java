package hr.tvz.java.vjezbe.visenitnost;

import hr.tvz.java.vjezbe.baza.BazaPodataka;
import hr.tvz.java.vjezbe.glavna.Main;

import java.util.concurrent.ExecutorService;

import javafx.scene.control.Label;

public class BrojCasopisaNit implements Runnable {

	private Label label;
	private ExecutorService executorService;
	//private Integer broj = 0;
	
	public BrojCasopisaNit(Label label, ExecutorService executorService)
	{
		this.label = label;
		this.executorService = executorService;
	}

	@Override
	public void run() {
		while(Main.getStanjeAplikacije())
		{
			//VisenitnostHelper.nitPauza(1000);
			try {
				VisenitnostHelper.setLabelText(label, BazaPodataka.ukupniBrojPosudenihCasopisa().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			VisenitnostHelper.nitPauza(1000);
		}
		executorService.shutdown();
	}
}