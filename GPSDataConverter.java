package no.hvl.dat100.prosjekt;
import static java.lang.Integer.*;
import java.sql.Timestamp;
import java.text.ParseException;
//import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;

public class GPSDataConverter {

	// arrays for GPS data in the original representation as array of strings
	private String[] timesstr, latitudesstr, longitudesstr, elevationsstr;
	private int arrayLength;
	
	// arrays for GPS data that we would like to transform to
	// public to simplify later code and since get/set methods have not yet been covered in the course
	public static int[] times;
	public double[] latitudes, longitudes, elevations;
	
	public GPSDataConverter(GPSData gpsdata) {
		
		// initialize the arrays for the original representation
		timesstr = gpsdata.getTimes();
		latitudesstr = gpsdata.getLattitudes();
		longitudesstr = gpsdata.getLongitudes();
		elevationsstr = gpsdata.getElevations();
		
		arrayLength = timesstr.length; // denne representerer antall rader i arrayene over.
	}

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	public static int TIME_STARTINDEX = 11; // startindex for tidspunkt

	public static int toSeconds(String timestr) {
		
		int sekunderSidenMidnatt = 0;
		int t, min, sekunder;
		int startIndex = 11;
		t = Integer.parseInt(timestr.substring(startIndex, 13));
		min = Integer.parseInt(timestr.substring(14, 16));
		sekunder = Integer.parseInt(timestr.substring(17,19));
		sekunderSidenMidnatt = t*3600 + min*60 + sekunder;
		return sekunderSidenMidnatt;
	}
	
		// TODO
		// OPPGAVE - START
		
	
			
			
		
	
		// OPPGAVE - SLUTT

	// konverter representation av data fra strenger (String) til tall
	public void convert() {

		// antall GPS datapunkter
		int n = arrayLength;

		// tabeller for konvertert gps data
		times = new int[n];			// ny tabell for tidsinformasjon i sekunder (int)
		latitudes = new double[n];  // ny tabell for breddegrad (double)
		longitudes = new double[n]; // ny tabell for lengdegrad (double)
		elevations = new double[n]; // ny tabell for høyde (double)

		// tabeller som skal konverteres fra er
		// timesstr, latitudesstr, longitudesstr, elevationsstr erklært først i klassen
		
		// TODO
		// OPPGAVE - START
		for (int i=0; i<n; i++) {
			times[i] = toSeconds(timesstr[i]);
			latitudes[i] = Double.parseDouble(latitudesstr[i]);
			longitudes[i] = Double.parseDouble(longitudesstr[i]);
			elevations[i] = Double.parseDouble(elevationsstr[i]);
			
		}
		
		// Hint:
		// iterer igjennom alle gps punkter (hint: bruk en for-løkke)
		// konverter hver inngang gps datapunkt 
		// - tidsinformasjon til sekunder (int) (hint: bruk toSeconds)
		// - breddegrad til double (hint: bruk Double.parseDouble)
		// - lengdegrad til double 
		// - høyde til double
        // sett konvertert data inn på rett plass i den rette tabellen
		
		// END
		// OPPGAVE - SLUTT ;
	}
	
	// skriv ut konvertert GPS data op formatet:
	// sekunder (breddegrad,lengdegrad) høyde
	public void print() {
		
		System.out.println();
		System.out.println("Konvertert GPS Data");
		// TODO
		// OPPGAVE - START
		
		int i = 0;
		for(i=0; i<arrayLength; i++ ) {
			System.out.println(times[i] + " (" + latitudes[i] + "," + longitudes[i] + ") " + elevations[i]);
		}
		// OPPGAVE - SLUTT
	}
}
