package no.hvl.dat100.prosjekt;
import static java.lang.Math.*;
import java.text.SimpleDateFormat ;
import java.util.Date ;
import java.util.TimeZone;


public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {
		
		String timestr = "";
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		// TODO
		// OPPGAVE - START
		Date d = new Date(secs * 1000L); // HH for 0-23
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		timestr = df.format(d);
		// OPPGAVE - SLUTT
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		// fjern = "0.0" når metoden implementeres for ikke få forkert minimum
		double min = da[0];
		
		// TODO
		// OPPGAVE - START
		for(double d : da) {
			if(d < min) {
				min = d;
			}
		}
		// OPPGAVE - SLUT
		return min;
	}

	
	private static int R = 6371000; // jordens radius
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		double a,c,d; // fjern = 1.0
		
		// TODO:
		// OPPGAVE - START
		double phi1 = toRadians(latitude1);
		double phi2 = toRadians(latitude2);
		double dphi1 = toRadians(latitude2 - latitude1);
		double dLamd1 = toRadians(longitude2 - longitude1);
		
		a = Math.pow((sin(dphi1/2)),2)+ cos(phi1)*cos(phi2)*Math.pow(sin(dLamd1/2),2);
		c = 2*atan2(sqrt(a), sqrt((1-a)));
		d = R*c;
		// OPPGAVE - SLUTT

		return d;
	}
	
	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		double speed;

		// TODO:
		// OPPGAVE - START
	   double d = distance(latitude1, longitude1, latitude2, longitude2);
	   speed = (d/secs)*3.6;
		// OPPGAVE - SLUTT
	   return speed;

	
	}
	
	private static int TEXTWIDTH = 10;
	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til "      1.35" og enhet til slutt
	// Hint: se på String.format metoden
	
	public static String printDouble(double d) {
		String str = "";
		
		
		// TODO
		// OPPGAVE - START
		String numberAsString = String.format ("%10.2f", d);
		str = numberAsString;
		/*String tall = String.format("%.2f", d );
		int len = tall.length();
		for(int i = len + 1; i<=TEXTWIDTH; i++) {
			str = " " + str;
		}*/
		// OPPGAVE - SLUTT
		return str;
	}
}
