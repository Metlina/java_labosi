package hr.tvz.java.vjezbe.baza;

import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.enumeracija.Jezik;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Datoteke {
	
	public static List<Knjiga> ucitajKnjige()
	{
		List<Knjiga> listaKnjiga = new ArrayList<Knjiga>();
		
		String fileName = "knjiga.txt";
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Knjiga knjiga = null;
		
		while(true) 
		{
			try {
				knjiga = ucitajKnjigaIzDatoteka(reader);
			} catch (IOException e){
				e.printStackTrace();
			} catch (NeisplativoObjavljivanjeException e) {
				e.printStackTrace();
			}
			
			if(knjiga == null)
			{
				break;
			}
			
			listaKnjiga.add(knjiga);
		}
		
		return listaKnjiga;
	}
	
	public static final Knjiga ucitajKnjigaIzDatoteka(BufferedReader reader) throws IOException, NeisplativoObjavljivanjeException
	{		
		String nazivKnjige = null, odabirString = null, odabirJezikaString = null, godinaKnjigeString = null, brojStranicaKnjigeString = null;
		Integer odabir = null, odabirJezika = null, godinaKnjige = null, brojStranicaKnjige = null;
		Double cijenaPoStranici = null;
		Jezik jezik = Jezik.HRVATSKI;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		nazivKnjige = reader.readLine();
		
		if (nazivKnjige == null)
			return null;
		
		odabirString = reader.readLine();
		odabir = Integer.parseInt(odabirString);

		if (odabir == 1) {
			vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		} else if (odabir == 2) {
			vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
		}
		
		Izdavac izdavac = unesiIzdavacaIzDatoteka(reader);
		
		godinaKnjigeString = reader.readLine();
		godinaKnjige = Integer.parseInt(godinaKnjigeString);
		
		odabirJezikaString = reader.readLine();
		odabirJezika = Integer.parseInt(odabirJezikaString);

		switch (odabirJezika) {
		case (1):
			jezik = Jezik.HRVATSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_HR;
			break;
		case (2):
			jezik = Jezik.ENGLESKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (3):
			jezik = Jezik.NJEMACKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (4):
			jezik = Jezik.FRANCUSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (5):
			jezik = Jezik.TALIJANSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (6):
			jezik = Jezik.RUSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (7):
			jezik = Jezik.KINESKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		}
		
		brojStranicaKnjigeString = reader.readLine();
		brojStranicaKnjige = Integer.parseInt(brojStranicaKnjigeString);
		
		//Izdavac izdavac = new Izdavac( naziv, drzavaIzdavaca);
		Knjiga knjiga = new Knjiga(nazivKnjige, jezik, izdavac, godinaKnjige, vrstaPublikacije, cijenaPoStranici, brojStranicaKnjige);
		
		return knjiga;
	}
	
	public static final Izdavac unesiIzdavacaIzDatoteka(BufferedReader reader) throws IOException 
	{
		String nazivIzdavaca = null, drzavaIzdavaca = null;
		nazivIzdavaca = reader.readLine();
		drzavaIzdavaca = reader.readLine();
		Izdavac noviIzdavac = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		return noviIzdavac;
		}

	public static List<Casopis> ucitajCasopise()
	{
		List<Casopis> listaCasopisa = new ArrayList<Casopis>();
		
		String fileName = "casopis.txt";
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File (fileName)));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		Casopis casopis = null;
		
		while(true)
		{
			try {
				casopis = ucitajCasopiseIzDatoteke(reader);
			} catch (IOException e){
				e.printStackTrace();
			} catch (NeisplativoObjavljivanjeException e) {
				e.printStackTrace();
			}
			
			if(casopis == null)
			{
				break;
			}
			
			listaCasopisa.add(casopis);
		}
		
		return listaCasopisa;
	}
	
	public static final Casopis ucitajCasopiseIzDatoteke(BufferedReader reader) throws IOException, NeisplativoObjavljivanjeException
	{
		String nazivCasopisa = null, godinaCasopisaString = null, brojStranicaCasopisaString = null, odabirString = null, mjesecCasopisaString = null;
		Integer godinaCasopisa = null, brojstranicaCasopisa = null, mjesecCasopisa = null, odabir = null;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;		
		
		nazivCasopisa = reader.readLine();
		
		if (nazivCasopisa == null)
			return null;
		
		godinaCasopisaString = reader.readLine();
		godinaCasopisa = Integer.parseInt(godinaCasopisaString);
		mjesecCasopisaString = reader.readLine();
		mjesecCasopisa = Integer.parseInt(mjesecCasopisaString);
		odabirString = reader.readLine();
		odabir = Integer.parseInt(odabirString);

		if (odabir == 1) {
			vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		} else if (odabir == 2) {
			vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
		}
		
		brojStranicaCasopisaString = reader.readLine();
		brojstranicaCasopisa = Integer.parseInt(brojStranicaCasopisaString);
		
		Casopis casopis = new Casopis(mjesecCasopisa, godinaCasopisa, brojstranicaCasopisa, vrstaPublikacije, nazivCasopisa);
		return casopis;
	}

	public static List<Clan> ucitajClana()
	{
		List<Clan> listaClanova = new ArrayList<Clan>();
		
		String fileName = "clan.txt";
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		Clan clan = null;
		
		while (true)
		{
			try {
				clan = ucitajClanaIzDatoteke(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (clan == null)
			{
				break;
			}
			
			listaClanova.add(clan);
		}
		
		return listaClanova;
	}
	
	public static final Clan ucitajClanaIzDatoteke (BufferedReader reader) throws IOException
	{
		String ime = null, prezime = null, OIB = null;
		
		OIB = reader.readLine();
		if (OIB == null)
		{
			return null;
		}
		prezime = reader.readLine();
		ime = reader.readLine();
		
		Clan clan = new Clan(ime, prezime, OIB);
		return clan;
	}

	public static void unesiKnjige(List<Knjiga> knjige) 
	{
		String fileName = "knjiga.txt";
		
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.print("");
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		for (Knjiga knjiga : knjige){		
			String naziv = knjiga.getNaziv();
			VrstaPublikacije odabir = knjiga.getVrstaPublikacije();
			Integer odabirInteger = null;

			if (odabir == VrstaPublikacije.ELEKTRONICKA) {
				odabirInteger = 1;
			} else if (odabir == VrstaPublikacije.PAPIRNATA) {
				odabirInteger = 2;
			}

			String godina = knjiga.getGodinaIzdanjaPublikacije().toString();
			String brojStranica = knjiga.getBrojStranicaPublikacije()
					.toString();
			Jezik jezik = knjiga.getJezikKnjige();
			Integer jezikOdabir = null;

			switch (jezik) {
			case ENGLESKI:
				jezikOdabir = 2;
				break;
			case FRANCUSKI:
				jezikOdabir = 4;
				break;
			case HRVATSKI:
				jezikOdabir = 1;
				break;
			case KINESKI:
				jezikOdabir = 7;
				break;
			case NJEMACKI:
				jezikOdabir = 3;
				break;
			case RUSKI:
				jezikOdabir = 6;
				break;
			case TALIJANSKI:
				jezikOdabir = 5;
				break;
			}

			String nazivIzdavaca = knjiga.getIzdavac().getNazivIzdavaca();
			String drzavaIzdavac = knjiga.getIzdavac().getDrzavaIzdavaca();

			try (FileWriter fw = new FileWriter(fileName, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println(naziv);
				out.println(odabirInteger.toString());
				out.println(nazivIzdavaca);
				out.println(drzavaIzdavac);
				out.println(godina);
				out.println(jezikOdabir.toString());
				out.println(brojStranica);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void unesiCasopise(List<Casopis> casopise)
	{
		String fileName = "casopis.txt";
		
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.print("");
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		for (Casopis casopis : casopise) {
			String naziv = casopis.getNaziv();
			VrstaPublikacije odabir = casopis.getVrstaPublikacije();
			Integer odabirInteger = null;

			if (odabir == VrstaPublikacije.ELEKTRONICKA) {
				odabirInteger = 1;
			} else if (odabir == VrstaPublikacije.PAPIRNATA) {
				odabirInteger = 2;
			}

			String godina = casopis.getGodinaIzdanjaPublikacije().toString();
			String brojStranica = casopis.getBrojStranicaPublikacije()
					.toString();
			String mjesecCasopisa = casopis.getMjesecIzdavanjaCasopisa()
					.toString();

			try (FileWriter fw = new FileWriter(fileName, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println(naziv);
				out.println(godina);
				out.println(mjesecCasopisa);
				out.println(odabirInteger.toString());
				out.println(brojStranica);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void unesiClanove(List<Clan> clanove)
	{
		String fileName = "clan.txt";
		
		try {
			PrintWriter writer = new PrintWriter(fileName);
			writer.print("");
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		for (Clan clan : clanove){
			String ime = clan.getIme();
			String prezime = clan.getPrezime();
			String oib = clan.getOIB();

			try (FileWriter fw = new FileWriter(fileName, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println(oib);
				out.println(prezime);
				out.println(ime);

				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
