package hr.tvz.java.vjezbe.baza;

import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.enumeracija.Jezik;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BazaPodataka {

	private static final String DATABASE_FILE = "properties.txt";
	
	private static Connection connectToDatabase() throws SQLException, IOException 
	{
		Properties svojstva = new Properties();
		
		svojstva.load(new FileReader(DATABASE_FILE));
		
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme,lozinka);
		
		return veza;
	}
	
	private static void closeConnectionToDatabase(Connection connection) 
	{	
		/*Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM RAZVOJ");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}*/
	}
	
	public static List<Knjiga> dohvatiKnjige() throws Exception 
	{
		Connection connection = connectToDatabase();
		
		List<Knjiga> listaKnjiga = new ArrayList<>();
		
		String queryString = "SELECT * FROM RAZVOJ.KNJIGA";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String naziv = resultSet.getString("naziv");
			Integer godinaIzdanja = resultSet.getInt("godinaIzdanja");
			Integer vrstaPublikacijeId = resultSet.getInt("vrstaPublikacije");
			VrstaPublikacije vrstaPublikacije = null;
			for (VrstaPublikacije temp : VrstaPublikacije.values()) {
				if (vrstaPublikacijeId == temp.getKod()) {
					vrstaPublikacije = temp;
				}
			}
			Integer brojStranica = resultSet.getInt("brojStranica");
			Integer jezikId = resultSet.getInt("jezik");
			Jezik jezik = null;
			for (Jezik temp : Jezik.values()) {
				if (jezikId == temp.getKod()) {
					jezik = temp;
				}
			}
			Integer izdavacId = resultSet.getInt("izdavac");
			Izdavac izdavac = dohvatiIzdavaca(izdavacId);
			double cijenaStranice = (jezik == Jezik.HRVATSKI) ? 0.45f : 0.75f;
			Knjiga k = new Knjiga(
					id, 
					naziv, 
					jezik, 
					izdavac, 
					godinaIzdanja,
					vrstaPublikacije,
					cijenaStranice, 
					brojStranica);
			listaKnjiga.add(k);
		}
		
		closeConnectionToDatabase(connection);
		
		return listaKnjiga;
	}

	private static Izdavac dohvatiIzdavaca(Integer izdavacId) throws Exception 
	{
		Connection connection = connectToDatabase();
		
		String queryString = "SELECT * FROM RAZVOJ.IZDAVAC WHERE ID = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		preparedStatement.setInt(1, izdavacId);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		Izdavac izdavac = null;
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String naziv = rs.getString("naziv");
			String drzava = rs.getString("drzava");
			izdavac = new Izdavac(id, naziv, drzava);
		}
		
		closeConnectionToDatabase(connection);
		
		return izdavac;
	}
	
	public static List<Casopis> dohvatiCasopise() throws Exception
	{
		Connection connection = connectToDatabase();
		
		List<Casopis> listaCasopisa = new ArrayList<>();
		
		String queryString = "SELECT * FROM RAZVOJ.CASOPIS";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String naziv = resultSet.getString("naziv");
			Integer godinaIzdanja = resultSet.getInt("godinaIzdanja");
			Integer vrstaPublikacijeId = resultSet.getInt("vrstaPublikacije");
			VrstaPublikacije vrstaPublikacije = null;
			for (VrstaPublikacije temp : VrstaPublikacije.values()) {
				if (vrstaPublikacijeId == temp.getKod()) {
					vrstaPublikacije = temp;
				}
			}
			Integer brojStranica = resultSet.getInt("brojStranica");
			Integer mjesecIzdanja = resultSet.getInt("mjesecIzdanja");
			Casopis c = new Casopis(
					id, 
					mjesecIzdanja,
					godinaIzdanja,
					brojStranica,
					vrstaPublikacije,
					naziv);
			listaCasopisa.add(c);
		}
		
		closeConnectionToDatabase(connection);
		
		return listaCasopisa;
	}
	
	public static List<Clan> dohvatiClanove() throws Exception
	{
		Connection connection = connectToDatabase();
		
		List<Clan> listaClanova = new ArrayList<>();
		
		String queryString = "SELECT * FROM RAZVOJ.CLAN";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			String oib = resultSet.getString("oib");
			Clan c = new Clan(
					id, 
					ime,
					prezime,
					oib);
			listaClanova.add(c);
		}
		
		closeConnectionToDatabase(connection);
		
		return listaClanova;
	}

	public static void spremiKnjigu(Knjiga knjiga) throws Exception 
	{
		Connection connection = connectToDatabase();
		connection.setAutoCommit(false);
		
		PreparedStatement insertIzdavac = connection.prepareStatement("INSERT INTO RAZVOJ.IZDAVAC (naziv, drzava) VALUES (?, ?)");
		
		insertIzdavac.setString(1, knjiga.getIzdavac().getNazivIzdavaca());
		insertIzdavac.setString(2, knjiga.getIzdavac().getDrzavaIzdavaca());
		
		insertIzdavac.executeUpdate();
		
		ResultSet generatedKeys = insertIzdavac.getGeneratedKeys();
		if (generatedKeys.next())
		{
			knjiga.getIzdavac().setId(generatedKeys.getInt(1));
		}
		
		PreparedStatement insertKnjiga = connection.prepareStatement("INSERT  INTO  RAZVOJ.KNJIGA  (naziv,  godinaizdanja, 	vrstapublikacije, brojstranica, jezik, izdavac) VALUES (?, ?, ?, ?, ?, ?)");
		
		insertKnjiga.setString(1, knjiga.getNaziv());
		insertKnjiga.setInt(2, knjiga.getGodinaIzdanjaPublikacije());
		insertKnjiga.setInt(3, knjiga.getVrstaPublikacije().getKod());
		insertKnjiga.setInt(4, knjiga.getBrojStranicaPublikacije());
		insertKnjiga.setInt(5, knjiga.getJezikKnjige().getKod());
		insertKnjiga.setInt(6, knjiga.getIzdavac().getId());
		insertKnjiga.executeUpdate();
		
		connection.commit();
		connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
		}

	public static void spremiCasopis(Casopis casopis) throws Exception
	{
		Connection connection = connectToDatabase();
		//connection.setAutoCommit(false);
		
		PreparedStatement insertCasopis = connection.prepareStatement("INSERT INTO RAZVOJ.CASOPIS (naziv, godinaizdanja, vrstapublikacije, brojstranica, mjesecizdanja) VALUES (?, ?, ?, ?, ?)");
		
		insertCasopis.setString(1, casopis.getNaziv());
		insertCasopis.setInt(2, casopis.getGodinaIzdanjaPublikacije());
		insertCasopis.setInt(3, casopis.getVrstaPublikacije().getKod());
		insertCasopis.setInt(4, casopis.getBrojStranicaPublikacije());
		insertCasopis.setInt(5, casopis.getMjesecIzdavanjaCasopisa());
		insertCasopis.executeUpdate();
		
		//connection.commit();
		//connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
	}
	
	public static void spremiClana(Clan clan) throws Exception
	{
		Connection connection = connectToDatabase();
		//connection.setAutoCommit(false);
		
		PreparedStatement insertClan = connection.prepareStatement("INSERT INTO RAZVOJ.CLAN (ime, prezime, oib) VALUES (?, ?, ?))");
		
		insertClan.setString(1, clan.getIme());
		insertClan.setString(2, clan.getPrezime());
		insertClan.setString(3, clan.getOIB());
		insertClan.executeUpdate();
		
		//connection.commit();
		//connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
	}

	public static void promijeniKnjigu(Knjiga knjiga) throws Exception 
	{
		Connection connection = connectToDatabase();
		connection.setAutoCommit(false);
		
		PreparedStatement insertIzdavac = connection.prepareStatement("UPDATE  RAZVOJ.IZDAVAC  SET  naziv  =  ?,  drzava  =  ? WHERE ID = ?");
		
		insertIzdavac.setString(1, knjiga.getIzdavac().getNazivIzdavaca());
		insertIzdavac.setString(2, knjiga.getIzdavac().getDrzavaIzdavaca());
		insertIzdavac.setInt(3, knjiga.getIzdavac().getId());
		insertIzdavac.executeUpdate();
		
		PreparedStatement insertKnjiga = connection.prepareStatement("UPDATE RAZVOJ.KNJIGA SET naziv = ?, godinaizdanja = ?, vrstapublikacije = ?, brojstranica = ?, jezik = ?, izdavac = ? WHERE ID = ?");
		
		insertKnjiga.setString(1, knjiga.getNaziv());
		insertKnjiga.setInt(2, knjiga.getGodinaIzdanjaPublikacije());
		insertKnjiga.setInt(3, knjiga.getVrstaPublikacije().getKod());
		insertKnjiga.setInt(4, knjiga.getBrojStranicaPublikacije());
		insertKnjiga.setInt(5, knjiga.getJezikKnjige().getKod());
		insertKnjiga.setInt(6, knjiga.getIzdavac().getId());
		insertKnjiga.setInt(7, knjiga.getId());
		insertKnjiga.executeUpdate();
		
		connection.commit();
		connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
	}

	public static void promijeniCasopis(Casopis casopis) throws Exception
	{
		Connection connection = connectToDatabase();
		//connection.setAutoCommit(false);
		
		PreparedStatement insertCasopis = connection.prepareStatement("UPDATE RAZVOJ.CASOPIS SET naziv = ?, godinaizdanja = ?, vrstapublikacije = ?, brojstranica = ?, mjesecizdanja = ? WHERE ID = ?");
		
		insertCasopis.setString(1, casopis.getNaziv());
		insertCasopis.setInt(2, casopis.getGodinaIzdanjaPublikacije());
		insertCasopis.setInt(3, casopis.getVrstaPublikacije().getKod());
		insertCasopis.setInt(4, casopis.getBrojStranicaPublikacije());
		insertCasopis.setInt(5, casopis.getMjesecIzdavanjaCasopisa());
		insertCasopis.setInt(6, casopis.getId());
		insertCasopis.executeUpdate();
		
		//connection.commit();
		//connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
	}
	
	public static void promijeniClan(Clan clan) throws Exception
	{
		Connection connection = connectToDatabase();
		//connection.setAutoCommit(false);
		
		PreparedStatement insertClan = connection.prepareStatement("UPDATE RAZVOJ.CLAN SET ime = ?, prezime = ?, oib = ? WHERE ID = ?");
		
		insertClan.setString(1, clan.getIme());
		insertClan.setString(2, clan.getPrezime());
		insertClan.setString(3, clan.getOIB());
		insertClan.setInt(4, clan.getId());
		insertClan.executeUpdate();
		
		//connection.commit();
		//connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
	}

	public static void spremiPosudbuKnjige(Posudba<Knjiga> posudba) throws Exception 
	{
		Connection connection = connectToDatabase();
		String queryString = null;
			
		queryString  =  "INSERT INTO RAZVOJ.POSUDBA_KNJIGA (clan, knjiga, datumPosudbe) VALUES 	(?, ?, ?)";
			
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
		preparedStatement.setInt(1, posudba.getClan().getId());
		preparedStatement.setInt(2, ((Knjiga) posudba.getPublikacija()).getId());
		preparedStatement.setDate(3, Date.valueOf(posudba.getLocalDateTIme().toLocalDate()));
		preparedStatement.executeUpdate();
			
		closeConnectionToDatabase(connection);
	}
	
	public static void spremiPosudbuCasopisa(Posudba<Casopis> posudba) throws Exception 
	{
		Connection connection = connectToDatabase();
		String queryString = null;
			
		queryString  =  "INSERT INTO RAZVOJ.POSUDBA_CASOPISA (clan, casopis, datumPosudbe) VALUES (?, ?, ?)";
			
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
		preparedStatement.setInt(1, posudba.getClan().getId());
		preparedStatement.setInt(2, ((Casopis) posudba.getPublikacija()).getId());
		preparedStatement.setDate(3, Date.valueOf(posudba.getLocalDateTIme().toLocalDate()));
		preparedStatement.executeUpdate();
			
		closeConnectionToDatabase(connection);
	}

	public static List<Posudba<Knjiga>> dohvatiPosudbaKnjiga() throws Exception
	{
		Connection connection = connectToDatabase();
		
		List<Posudba<Knjiga>> listaPosudbiKnjiga = new ArrayList<>();
		
		String queryString = "SELECT * FROM RAZVOJ.POSUDBA_KNJIGA";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			Integer id = resultSet.getInt("id");
			Integer clanInt = resultSet.getInt("clan");
			Clan clan = dohvatiJedanClan(clanInt);
			Integer knjigaInt = resultSet.getInt("knjiga");
			Knjiga knjiga = dohvatiJednuKnjigu(knjigaInt);
			Date datumPosudbe = resultSet.getDate("datumPosudbe");
			Instant instant = Instant.ofEpochMilli(datumPosudbe.getTime());
			LocalDateTime datum = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			Posudba<Knjiga> k = new Posudba<Knjiga>(
					id,
					clan,
					knjiga,
					datum);
			listaPosudbiKnjiga.add(k);
		}
		
		closeConnectionToDatabase(connection);
		
		return listaPosudbiKnjiga;
	}
	
	public static List<Posudba<Casopis>> dohvatiPosudbaCasopis() throws Exception
	{
		Connection connection = connectToDatabase();
		
		List<Posudba<Casopis>> listaPosudbiCasopisa = new ArrayList<>();
		
		String queryString = "SELECT * FROM RAZVOJ.POSUDBA_CASOPISA";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			Integer id = resultSet.getInt("id");
			Integer clanInt = resultSet.getInt("clan");
			Clan clan = dohvatiJedanClan(clanInt);
			Integer casopisInt = resultSet.getInt("casopis");
			Casopis casopis = dohvatiJedanCasopis(casopisInt);
			Date datumPosudbe = resultSet.getDate("datumPosudbe");
			Instant instant = Instant.ofEpochMilli(datumPosudbe.getTime());
			LocalDateTime datum = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			Posudba<Casopis> k = new Posudba<Casopis>(
					id,
					clan,
					casopis,
					datum);
			listaPosudbiCasopisa.add(k);
		}
		
		closeConnectionToDatabase(connection);
		
		return listaPosudbiCasopisa;
	}

	public static void promijeniPosudbuKnjige(Posudba<Knjiga> posudbaKnjiga) throws Exception
	{
		Connection connection = connectToDatabase();
		connection.setAutoCommit(false);
		
		LocalDateTime ldt = posudbaKnjiga.getLocalDateTIme();
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		//Date res = Date.from(instant);
		
		PreparedStatement insertClan = connection.prepareStatement("UPDATE RAZVOJ.CLAN SET ime = ?, prezime = ?, oib = ? WHERE ID = ?");
		
		insertClan.setString(1, posudbaKnjiga.getClan().getIme());
		insertClan.setString(2, posudbaKnjiga.getClan().getPrezime());
		insertClan.setString(3, posudbaKnjiga.getClan().getOIB());
		insertClan.setInt(4, posudbaKnjiga.getClan().getId());
		insertClan.executeUpdate();
		
		PreparedStatement insertIzdavac = connection.prepareStatement("UPDATE  RAZVOJ.IZDAVAC  SET  naziv  =  ?,  drzava  =  ? WHERE ID = ?");
		
		insertIzdavac.setString(1, posudbaKnjiga.getPublikacija().getIzdavac().getNazivIzdavaca());
		insertIzdavac.setString(2, posudbaKnjiga.getPublikacija().getIzdavac().getDrzavaIzdavaca());
		insertIzdavac.setInt(3, posudbaKnjiga.getPublikacija().getIzdavac().getId());
		insertIzdavac.executeUpdate();
		
		PreparedStatement insertKnjiga = connection.prepareStatement("UPDATE RAZVOJ.KNJIGA SET naziv = ?, godinaizdanja = ?, vrstapublikacije = ?, brojstranica = ?, jezik = ?, izdavac = ? WHERE ID = ?");
		
		insertKnjiga.setString(1, posudbaKnjiga.getPublikacija().getNaziv());
		insertKnjiga.setInt(2, posudbaKnjiga.getPublikacija().getGodinaIzdanjaPublikacije());
		insertKnjiga.setInt(3, posudbaKnjiga.getPublikacija().getVrstaPublikacije().getKod());
		insertKnjiga.setInt(4, posudbaKnjiga.getPublikacija().getBrojStranicaPublikacije());
		insertKnjiga.setInt(5, posudbaKnjiga.getPublikacija().getJezikKnjige().getKod());
		insertKnjiga.setInt(6, posudbaKnjiga.getPublikacija().getIzdavac().getId());
		insertKnjiga.setInt(7, posudbaKnjiga.getPublikacija().getId());
		insertKnjiga.executeUpdate();
		
		PreparedStatement insertPosudbaKnjiga = connection.prepareStatement("UPDATE RAZVOJ.POSUDBA_KNJIGA SET clan =  ?, knjiga = ?, datumPosudbe = ? WHERE ID = ?");
		
		insertPosudbaKnjiga.setObject(1, posudbaKnjiga.getClass());
		insertPosudbaKnjiga.setObject(2, posudbaKnjiga.getPublikacija());
		insertPosudbaKnjiga.setDate(3, (Date) Date.from(instant));
		insertPosudbaKnjiga.setInt(4, posudbaKnjiga.getId());
		insertPosudbaKnjiga.executeUpdate();
		
		connection.commit();
		connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
	}
		
	public static void promijeniPosudbuCasopisa(Posudba<Casopis> posudbaCasopis) throws Exception
	{
		Connection connection = connectToDatabase();
		connection.setAutoCommit(false);
		
		LocalDateTime ldt = posudbaCasopis.getLocalDateTIme();
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		//Date res = Date.from(instant);
		
		PreparedStatement insertClan = connection.prepareStatement("UPDATE RAZVOJ.CLAN SET ime = ?, prezime = ?, oib = ? WHERE ID = ?");
		
		insertClan.setString(1, posudbaCasopis.getClan().getIme());
		insertClan.setString(2, posudbaCasopis.getClan().getPrezime());
		insertClan.setString(3, posudbaCasopis.getClan().getOIB());
		insertClan.setInt(4, posudbaCasopis.getClan().getId());
		insertClan.executeUpdate();
		
		PreparedStatement insertCasopis = connection.prepareStatement("UPDATE RAZVOJ.CASOPIS SET naziv = ?, godinaizdanja = ?, vrstapublikacije = ?, brojstranica = ?, mjesecizdanja = ? WHERE ID = ?");
		
		insertCasopis.setString(1, posudbaCasopis.getPublikacija().getNaziv());
		insertCasopis.setInt(2, posudbaCasopis.getPublikacija().getGodinaIzdanjaPublikacije());
		insertCasopis.setInt(3, posudbaCasopis.getPublikacija().getVrstaPublikacije().getKod());
		insertCasopis.setInt(4, posudbaCasopis.getPublikacija().getBrojStranicaPublikacije());
		insertCasopis.setInt(5, posudbaCasopis.getPublikacija().getMjesecIzdavanjaCasopisa());
		insertCasopis.setInt(6, posudbaCasopis.getPublikacija().getId());
		insertCasopis.executeUpdate();
		
		PreparedStatement insertPosudbaKnjiga = connection.prepareStatement("UPDATE RAZVOJ.POSUDBA_KNJIGA SET clan =  ?, knjiga = ?, datumPosudbe = ? WHERE ID = ?");
		
		insertPosudbaKnjiga.setObject(1, posudbaCasopis.getClass());
		insertPosudbaKnjiga.setObject(2, posudbaCasopis.getPublikacija());
		insertPosudbaKnjiga.setDate(3, (Date) Date.from(instant));
		insertPosudbaKnjiga.setInt(4, posudbaCasopis.getId());
		insertPosudbaKnjiga.executeUpdate();
		
		connection.commit();
		connection.setAutoCommit(true);
		closeConnectionToDatabase(connection);
	}
	
	private static Knjiga dohvatiJednuKnjigu(Integer knjigaId) throws Exception
	{
		Connection connection = connectToDatabase();
		
		String queryString = "SELECT * FROM RAZVOJ.KNJIGA WHERE ID = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		preparedStatement.setInt(1, knjigaId);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		Knjiga knjiga = null;
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String naziv = rs.getString("naziv");
			Integer godinaIzdanja = rs.getInt("godinaIzdanja");
			Integer vrstaPublikacijeId = rs.getInt("vrstaPublikacije");
			VrstaPublikacije vrstaPublikacije = null;
			for (VrstaPublikacije temp : VrstaPublikacije.values()) {
				if (vrstaPublikacijeId == temp.getKod()) {
					vrstaPublikacije = temp;
				}
			}
			Integer brojStranica = rs.getInt("brojStranica");
			Integer jezikId = rs.getInt("jezik");
			Jezik jezik = null;
			for (Jezik temp : Jezik.values()) {
				if (jezikId == temp.getKod()) {
					jezik = temp;
				}
			}
			Integer izdavacId = rs.getInt("izdavac");
			Izdavac izdavac = dohvatiIzdavaca(izdavacId);
			double cijenaStranice = (jezik == Jezik.HRVATSKI) ? 0.45f : 0.75f;
			knjiga = new Knjiga(
					id, 
					naziv, 
					jezik, 
					izdavac, 
					godinaIzdanja,
					vrstaPublikacije,
					cijenaStranice, 
					brojStranica);
		}
		
		closeConnectionToDatabase(connection);
		
		return knjiga;
	}
	
	private static Casopis dohvatiJedanCasopis(Integer casopisId) throws Exception
	{
		Connection connection = connectToDatabase();
		
		String queryString = "SELECT * FROM RAZVOJ.CASOPIS WHERE ID = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		preparedStatement.setInt(1, casopisId);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		Casopis casopis = null;
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String naziv = rs.getString("naziv");
			Integer godinaIzdanja = rs.getInt("godinaIzdanja");
			Integer vrstaPublikacijeId = rs.getInt("vrstaPublikacije");
			VrstaPublikacije vrstaPublikacije = null;
			for (VrstaPublikacije temp : VrstaPublikacije.values()) {
				if (vrstaPublikacijeId == temp.getKod()) {
					vrstaPublikacije = temp;
				}
			}
			Integer brojStranica = rs.getInt("brojStranica");
			Integer mjesecIzdanja = rs.getInt("mjesecIzdanja");
			casopis = new Casopis(
					id, 
					mjesecIzdanja,
					godinaIzdanja,
					brojStranica,
					vrstaPublikacije,
					naziv);
		}
		
		closeConnectionToDatabase(connection);
		
		return casopis;
	}

	private static Clan dohvatiJedanClan(Integer clanId) throws Exception
	{
		Connection connection = connectToDatabase();
		
		String queryString = "SELECT * FROM RAZVOJ.CLAN WHERE ID = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		preparedStatement.setInt(1, clanId);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		Clan clan = null;
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String ime = rs.getString("ime");
			String prezime = rs.getString("prezime");
			String oib = rs.getString("oib");
			clan = new Clan(
					id, 
					ime,
					prezime,
					oib);
		}
		
		closeConnectionToDatabase(connection);
		
		return clan;
	}

	public static Integer ukupniBrojPosudenihKnjiga() throws Exception
	{
		Integer brojPosudenihKnjiga = 0;
		
		Connection connection = connectToDatabase();
		
		String queryString = "SELECT COUNT(*) as broj FROM RAZVOJ.POSUDBA_KNJIGA";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			brojPosudenihKnjiga++;
		}
		
		closeConnectionToDatabase(connection);
		
		return brojPosudenihKnjiga;
	}
	
	public static Integer ukupniBrojPosudenihCasopisa() throws Exception
	{
		Integer brojPosudenihCasopisa = 0;
		
		Connection connection = connectToDatabase();
		
		String queryString = "SELECT COUNT(*) as broj FROM RAZVOJ.POSUDBA_CASOPISA";
		
		PreparedStatement preparedStatement = connection.prepareStatement(queryString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			brojPosudenihCasopisa++;
		}
		
		closeConnectionToDatabase(connection);
		
		return brojPosudenihCasopisa;
	}

}