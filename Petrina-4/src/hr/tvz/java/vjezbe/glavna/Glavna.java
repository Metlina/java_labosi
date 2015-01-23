package hr.tvz.java.vjezbe.glavna;

import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.entitet.Publikacija;
import hr.tvz.java.vjezbe.enumeracija.Jezik;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.DuplikatPublikacijeException;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.net.SyslogOutputStream;

public class Glavna {
	
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static Knjiga upisiKnjigu() throws NeisplativoObjavljivanjeException
	{
		Scanner scan = new Scanner(System.in);
		
		String nazivKnjige, nazivIzdavaca, drzavaIzdavaca;
		Integer odabir, odabirJezika;
		Integer godinaKnjige, brojStranicaKnjige;
		Double cijenaPoStranici = null;
		Jezik jezik = Jezik.HRVATSKI;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		
		System.out.print("Unesite naziv knjige : \n");
		nazivKnjige = scan.nextLine();
		System.out.print("Unesite jezik knjige : \n");
		System.out.println("1) HRVATSKI");
		System.out.println("2) ENGLESKI");
		System.out.println("3) NJEMACKI");
		System.out.println("4) FRANCUSKI");
		System.out.println("5) TALIJANKSI");
		System.out.println("6) RUSKI");
		System.out.println("7) KINESKI");
		odabirJezika = scan.nextInt();
		
		do{
			if (odabirJezika < 1 || odabirJezika > 7)
			{
				System.out.println("Odabrali ste krivi jezik, molimo odaberite ponovno!");
				odabirJezika = scan.nextInt();
			}
		}while(odabirJezika < 1 || odabirJezika > 7);
			
		switch(odabirJezika) {
			case (1) :
				jezik = Jezik.HRVATSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_HR;
				break;
			case (2) :
				jezik = Jezik.ENGLESKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (3) :
				jezik = Jezik.NJEMACKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (4) :
				jezik = Jezik.FRANCUSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (5) :
				jezik = Jezik.TALIJANSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (6) :
				jezik = Jezik.RUSKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
			case (7) :
				jezik = Jezik.KINESKI;
				cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
				break;
		}
		
		System.out.print("Unesite godinu knjige : \n");
		godinaKnjige = scan.nextInt();
		System.out.print("Unesite broj stranica knjige : \n");
		brojStranicaKnjige = scan.nextInt();
		System.out.print("Unesite vrstu : \n");
		System.out.print("1. Elektronicka publikacija \n");
		System.out.print("2. Papirnata publikacija \n");
		odabir = scan.nextInt();
		scan.nextLine();
		
		do
		{
			if (odabir != 1 && odabir != 2)
			{
				System.out.print("Unijeli ste krivi odabir, molim unesite ponovno! \n");
				odabir = scan.nextInt();
			}
		}while (odabir != 1 && odabir != 2);
		
		if (odabir == 1)
		{
			vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		}
		else if (odabir == 2)
		{
			vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
		}
		
		System.out.print("Unesite izdavaca : \n");
		nazivIzdavaca = scan.nextLine();
		System.out.print("Unesite drzavu izdavaca : \n");
		drzavaIzdavaca = scan.nextLine();
		
		Izdavac izdavac = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		Knjiga knjiga = new Knjiga(nazivKnjige, jezik, izdavac, godinaKnjige, vrstaPublikacije, cijenaPoStranici, brojStranicaKnjige);
		
		//scan.close();
		
		return knjiga;
	}
	
	public static Casopis upisCasopisa() throws NeisplativoObjavljivanjeException
	{
		Scanner scan = new Scanner(System.in);
		
		String nazivCasopisa;
		Integer godinaCasopisa, brojstranicaCasopisa, mjesecCasopisa;
		Integer odabir;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		
		System.out.print("Unesite naziv casopisa : " + "\n");
		nazivCasopisa = scan.nextLine();
		System.out.print("Unesite godinu casopisa : " + "\n");
		godinaCasopisa = scan.nextInt();
		System.out.print("Unesite broj stranica casopisa : " + "\n");
		brojstranicaCasopisa = scan.nextInt();
		System.out.print("Unesite mjesec casopisa" + "\n");
		mjesecCasopisa = scan.nextInt();
		System.out.print("Unesite vrstu : \n");
		System.out.print("1. Elektronicka publikacija \n");
		System.out.print("2. Papirnata publikacija \n");
		odabir = scan.nextInt();
		
		do
		{
			if (odabir != 1 && odabir != 2)
			{
				System.out.print("Unijeli ste krivi odabir, molim unesite ponovno! \n");
				odabir = scan.nextInt();
			}
		}while (odabir != 1 && odabir != 2);
		
		if (odabir == 1)
		{
			vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		}
		else if (odabir == 2)
		{
			vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
		}
		
		Casopis casopis = new Casopis(mjesecCasopisa, godinaCasopisa, brojstranicaCasopisa, vrstaPublikacije, nazivCasopisa);
		
		return casopis;
		
	}
	
	public static Clan upisiClana()
	{
		Scanner scan = new Scanner(System.in);
		
		String ime, prezime, OIB;
		
		System.out.print("Unesite OIB clana : \n");
		OIB = scan.nextLine();
		System.out.print("Unesite ime clana : \n");
		ime = scan.nextLine();
		System.out.print("Unesite prezime clana : \n");
		prezime = scan.nextLine();
		System.out.print("\n");
		
		Clan clan = new Clan(ime, prezime, OIB);
		
		//scan.close();
		
		return clan;
	}
	
	public static void ispisiKnjiga(Publikacija publikacija)
	{
		System.out.print("Naziv publikacije : " + publikacija.getNaziv() + "\n");
		System.out.print("Vrsta : " + publikacija.getVrstaPublikacije() + "\n");
		System.out.print("Broj stranica : " + publikacija.getBrojStranicaPublikacije() + "\n");
		System.out.print("Cijena : " + publikacija.getCijenaPublikacije().setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
		System.out.print("Jezik knjige : " + ((Knjiga) publikacija).getJezikKnjige() +"\n");
		System.out.print("Izdavac : " + ((Knjiga) publikacija).getIzdavac().getNazivIzdavaca() +"\n");
		System.out.print("Drzava izdavaca: " + ((Knjiga) publikacija).getIzdavac().getDrzavaIzdavaca() +"\n");
		System.out.print("Raspolozivo za posudbu : " + ((Knjiga) publikacija).provjeriRaspolozivost() + "\n");
		
	}
	
	public static void ispisiCasopis(Publikacija publikacija)
	{
		System.out.print("Naziv publikacije : " + publikacija.getNaziv() + "\n");
		System.out.print("Vrsta : " + publikacija.getVrstaPublikacije() + "\n");
		System.out.print("Broj stranica : " + publikacija.getBrojStranicaPublikacije() + "\n");
		System.out.print("Cijena : " + publikacija.getCijenaPublikacije() + "\n");
		System.out.print("Mjesec : " + ((Casopis) publikacija).getMjesecIzdavanjaCasopisa() + "\n");
	}
	
	public static void provjeraDuplikataKnjige(Knjiga knjiga1, Knjiga knjiga2) throws DuplikatPublikacijeException
	{
		if (knjiga2.equals(knjiga1))
		{
			throw new DuplikatPublikacijeException("Pogreska! Unijeli ste duplikat knjige, molimo ponovite upis!");
		}
	}
	
	public static void provjeraDuplikataCasopis(Casopis casopis1, Casopis casopis2) throws DuplikatPublikacijeException
	{
		if (casopis2.equals(casopis1))
		{
			throw new DuplikatPublikacijeException("Pogreska! Unijeli ste duplikat casopisa, molimo ponovite upis!");
		}
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
	
		String odabir, pretraziPublikacije;
		Knjiga knjiga1 = null, knjiga2 = null;
		Casopis casopis1 = null, casopis2 = null;
		Clan clan;
		Publikacija pub;
		Boolean pogodio = true;
		List<Publikacija> listaPublikacija = new ArrayList<Publikacija>();
		
		do {
			try {
				System.out.print("Unos 1. knjige : \n");
				logger.info("Unos 1. knjige!");
				knjiga1 = upisiKnjigu();
				pogodio = false;
			} catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		} while (pogodio);
		
		listaPublikacija.add(knjiga1);
		
		do{
			try {
				System.out.print("Unos 2. knjige : \n");
				logger.info("Unos 2. knjige!");
				knjiga2 = upisiKnjigu();
				provjeraDuplikataKnjige(knjiga1, knjiga2);
				pogodio = false;
			}catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}catch (DuplikatPublikacijeException e)
			{
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		}while(pogodio);
		
		listaPublikacija.add(knjiga2);
		
		do{
			try {
				System.out.print("Unos 1. casopisa : \n");
				logger.info("Unos 1. casopisa!");
				casopis1 = upisCasopisa();
				pogodio = false;
			}catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		}while(pogodio);
		
		listaPublikacija.add(casopis1);
		
		do{
			try {
				System.out.print("Unos 2. casopisa : \n");
				logger.info("Unos 2. casopisa!");
				casopis2 = upisCasopisa();
				provjeraDuplikataCasopis(casopis1, casopis2);
				pogodio = false;
			}catch (NeisplativoObjavljivanjeException e) {
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}catch (DuplikatPublikacijeException e)
			{
				pogodio = true;
				logger.info(e.getMessage(), e);
				System.out.println(e);
			}
		}while(pogodio);
		
		listaPublikacija.add(casopis2);
		
		List<Publikacija> sortiranaLista = new ArrayList<>();
		
		//radi i ovo ali u zadatku pise da mora biti lambda, ostavljam cisto radi toga jer sam se najvise na ovom koraku mucia
		//trebalo mi je 6 h da skuzim a zapravo je trebalo samo u novu listu stavit i onda sve radi :D :D :D :D :D
		//Function<Publikacija, BigDecimal> poCijeniFunkction = Publikacija::getCijenaPublikacije;
		//Comparator<Publikacija> poCijeniComparator = Comparator.comparing(poCijeniFunkction);
		//sortiranaLista = listaPublikacija.stream().sorted(poCijeniComparator).collect(Collectors.toList());
		
		sortiranaLista = listaPublikacija.stream().sorted((Publikacija p1, Publikacija p2) -> (p1.getCijenaPublikacije().compareTo(p2.getCijenaPublikacije()))).collect(Collectors.toList());

		
		Publikacija najmanja = sortiranaLista.stream().filter(p -> (p.getCijenaPublikacije().doubleValue() > 0)).findFirst().get();
		Publikacija najveca = sortiranaLista.get(listaPublikacija.size() - 1);
		
		System.out.println("Najskuplja publikacija : ");
		if (najveca instanceof Knjiga)
		{
			ispisiKnjiga(najveca);
		}
		else if (najveca instanceof Casopis)
		{
			ispisiCasopis(najveca);
		}
		
		System.out.println("Najjeftinija publikacija : ");
		
		if (najmanja instanceof Knjiga)
		{
			ispisiKnjiga(najmanja);
		}
		else if (najmanja instanceof Casopis)
		{
			ispisiCasopis(najmanja);
		}
		
		System.out.print("Unos clana : \n");
		clan = upisiClana();
		System.out.print("Odaberite publikaciju : \n");
		System.out.print("1)" + knjiga1.getNaziv() + "\n");
		System.out.print("2)" + knjiga2.getNaziv() + "\n");
		System.out.print("3)" + casopis1.getNaziv() + "\n");
		System.out.print("4)" + casopis2.getNaziv() + "\n");
		odabir = scan.nextLine();
		
		do
		{
			if (!odabir.equals(knjiga1.getNaziv()) && !odabir.equals(knjiga2.getNaziv()) && !odabir.equals(casopis1.getNaziv()) && !odabir.equals(casopis2.getNaziv()))
			{
				System.out.print("Unesen je krivi naslov, molimo unesite ponovno! \n");
				odabir = scan.nextLine();
			}
			
		}while(!odabir.equals(knjiga1.getNaziv()) && !odabir.equals(knjiga2.getNaziv()) && !odabir.equals(casopis1.getNaziv()) && !odabir.equals(casopis2.getNaziv()));
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		System.out.print("Stanje posudbe : \n");
		
		final String odabirFinal = odabir;
				
		if (listaPublikacija.stream().filter(p -> p.getNaziv().equals(odabirFinal)).findFirst().get() != null)
		{
			Posudba posudba = new Posudba(clan, knjiga1, localDateTime);
			ispisiKnjiga(knjiga1);
		}
		else if (listaPublikacija.stream().filter(p -> p.getNaziv().equals(odabirFinal)).findFirst().get() != null)
		{
			Posudba posudba = new Posudba(clan, knjiga2, localDateTime);
			ispisiKnjiga(knjiga2);
		}
		else if (listaPublikacija.stream().filter(p -> p.getNaziv().equals(odabirFinal)).findFirst().get() != null)
		{
			Posudba posudba = new Posudba(clan, casopis1, localDateTime);
			ispisiCasopis(casopis1);
		}
		else if (listaPublikacija.stream().filter(p -> p.getNaziv().equals(odabirFinal)).findFirst().get() != null)
		{
			Posudba posudba = new Posudba(clan, casopis2, localDateTime);
			ispisiCasopis(casopis2);
		}
		
		System.out.print("Podaci korisnika : \n");
		System.out.print("Prezime : " + clan.getPrezime() +"\n");
		System.out.print("Ime : " + clan.getIme() +"\n");
		System.out.print("OIB : " + clan.getOIB() +"\n");
		System.out.print("Datum posudbe : " + localDateTime.format(DateTimeFormatter.ofPattern("dd:MM:yyyy. HH:mm:ss")) +"\n");
		
		System.out.println("Pretrazi publikacije : ");
		pretraziPublikacije = scan.nextLine();
		
		List<Publikacija> novaLista = new ArrayList<>();
		novaLista = listaPublikacija.stream().filter(p -> p.getNaziv().contains(pretraziPublikacije)).collect(Collectors.toList());
		
		System.out.println("Pronadene publikacije : ");
		for (Publikacija publikacija : novaLista)
		{
			if (publikacija instanceof Knjiga)
				ispisiKnjiga(publikacija);
			else if (publikacija instanceof Casopis)
				ispisiCasopis(publikacija);
			System.out.println();
		}
		
		scan.close();

	}

}