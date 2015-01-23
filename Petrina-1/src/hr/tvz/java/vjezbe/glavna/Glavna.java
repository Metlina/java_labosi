package hr.tvz.java.vjezbe.glavna;

import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Glavna {

	public static Knjiga upisiKnjigu()
	{
		Scanner scan = new Scanner(System.in);
		
		String nazivKnjige, jezikKnjige, nazivIzdavaca, drzavaIzdavaca;
		
		System.out.print("Unesite naziv knjige : \n");
		nazivKnjige = scan.nextLine();
		System.out.print("Unesite jezik knjige : \n");
		jezikKnjige = scan.nextLine();
		System.out.print("Unesite izdavaca : \n");
		nazivIzdavaca = scan.nextLine();
		System.out.print("Unesite drzavu izdavaca : \n");
		drzavaIzdavaca = scan.nextLine();
		//System.out.print("\n");
		
		Izdavac izdavac = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		Knjiga knjiga = new Knjiga(nazivKnjige, jezikKnjige, izdavac);
		
		//scan.close();
		
		return knjiga;
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
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String odabir;
		Knjiga knjiga1, knjiga2;
		Clan clan;
		
		System.out.print("Unos 1. knjige : \n");
		knjiga1 = upisiKnjigu();
		System.out.print("Unos 2. knjige : \n");
		knjiga2 = upisiKnjigu();
		System.out.print("Unos clana : \n");
		clan = upisiClana();
		System.out.print("Odaberite knjigu : \n");
		System.out.print("1)" + knjiga1.getNazivKnjige() + "\n");
		System.out.print("2)" + knjiga2.getNazivKnjige() + "\n");
		odabir = scan.nextLine();
		
		do
		{
			if (!odabir.equals(knjiga1.getNazivKnjige()) && !odabir.equals(knjiga2.getNazivKnjige()))
			{
				System.out.print("Unesen je krivi naslov, molimo unesite ponovno! \n");
				odabir = scan.nextLine();
			}
			
		}while(!odabir.equals(knjiga1.getNazivKnjige()) && !odabir.equals(knjiga2.getNazivKnjige()));
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		if (odabir.equals(knjiga1.getNazivKnjige()))
		{
			Posudba posudba = new Posudba(clan, knjiga1, localDateTime);
			System.out.print("Stanje posudbe : \n");
			System.out.print("Naziv knjige :" + knjiga1.getNazivKnjige() + " \n");
			System.out.print("Jezik knjige : " + knjiga1.getJezikKnjige() +"\n");
			System.out.print("Izdava : " + knjiga1.getIzdavac().getNazivIzdavaca() +"\n");
			System.out.print("Drzava izdavaca: " + knjiga1.getIzdavac().getDrzavaIzdavaca() +"\n");
		}
		else if (odabir.equals(knjiga2.getNazivKnjige()))
		{
			Posudba posudba = new Posudba(clan, knjiga2, localDateTime);
			System.out.print("Stanje posudbe : \n");
			System.out.print("Naziv knjige :" + knjiga2.getNazivKnjige() + " \n");
			System.out.print("Jezik knjige : " + knjiga2.getJezikKnjige() +"\n");
			System.out.print("Izdava : " + knjiga2.getIzdavac().getNazivIzdavaca() +"\n");
			System.out.print("Drzava izdavaca: " + knjiga2.getIzdavac().getDrzavaIzdavaca() +"\n");
		}
		
		System.out.print("Podaci korisnika : \n");
		System.out.print("Prezime : " + clan.getPrezime() +"\n");
		System.out.print("Ime : " + clan.getIme() +"\n");
		System.out.print("OIB : " + clan.getOIB() +"\n");
		System.out.print("Datum posudbe : " + localDateTime.format(DateTimeFormatter.ofPattern("dd:MM:yyyy. HH:mm:ss")) +"\n");
		
		scan.close();

	}

}
