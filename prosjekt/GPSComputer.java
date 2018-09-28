package no.hvl.dat100.prosjekt;
import static java.lang.Math.*;

public class GPSComputer {
	
	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
		this.arrayLength = times.length;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;
	private int arrayLength;//Brukes for å gjøre det mer oversiktlig i koden
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;
		double midlAvstand;
		double latitude1;
		double longitude1;
		double latitude2;
		double longitude2;
		
		// TODO
		// OPPGAVE - START
		int i;
for(i=0; i<arrayLength-1; i++) { //index går fra 0-5 og linjenummer går fra 1-5.
	latitude1 = latitudes[i];
	longitude1 = longitudes[i];
	latitude2 = latitudes[i+1];
	longitude2 = longitudes[i+1];
	distance = distance + GPSUtils.distance(latitude1, longitude1, latitude2, longitude2);
	

		}
		// Hint: bruk distance-metoden fra GPSUtils.
		
		// OPPGAVE - SLUTT

		return distance;
	}

	// beregn totale hÃ¸ydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;
		
		// TODO
		// OPPGAVE - START
		double h1, h2;
		int i = 0;
		for( i=0; i< arrayLength-1; i++) {
			h1 = elevations[i];
			h2 = elevations[i+1];
			
			if(h1<h2){
				elevation = elevation + (h2-h1);
			}
			
			
		}
		// OPPGAVE - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int totaltime = 0;
		
		// TODO 
		// OPPGAVE START
		int i = 0;
		int t1, t2;
		for(i = 0; i<arrayLength-1; i++) {
			t1 = times[i];
			t2 = times[i+1];
			totaltime = totaltime + (t2 - t1);
		}
		
		
		// OPPGAVE SLUTT
		
		return totaltime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double[times.length-1];
		double latitude1;
		double longitude1;
		double latitude2;
		double longitude2;
		int tid;
		int t1,t2;
		double fart;
		
		
		// TODO
		// OPPGAVE - START
		int i;
		for (i=0; i<arrayLength-1; i++) {
			t1 = times[i];
			t2 = times[i+1];
			tid = (t2-t1);
			latitude1 = latitudes[i];
			longitude1 = longitudes[i];
			latitude2 = latitudes[i+1];
			longitude2 = longitudes[i+1];
			fart = GPSUtils.speed(tid, latitude1, longitude1, latitude2, longitude2);
			fart = (Math.round(fart*10))/10.0;
			
			speeds[i] = fart;
		}
		
		// OPPGAVE - SLUTT
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		
		// TODO
		// OPPGAVE - START
		double speeds[]=speeds();
		for(int i =0; i<speeds.length; i++ ) {
			maxspeed=GPSUtils.findMax(speeds);
			
		}
		// OPPGAVE - SLUTT
		
		return maxspeed;
	}
	
	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {

		double average = 0;
		
		// TODO
		// OPPGAVE - START
				average = (totalDistance() / totalTime()*3600/1000);
		// OPPGAVE - SLUTT
		
		return average;
	}


	// conversion factor kph (km/t) to miles per hour (mph)
	public static double MS = 0.62;

	// beregn kcal gitt weight og tid der kjÃ¸res med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;
	
		

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		double fart[] = {0, 10, 12, 14, 16, 20};
		double MET[] = {4, 6, 8, 10, 12, 16};

		// TODO
		// OPPGAVE START
		for(int i=0; i<fart.length-1; i++) {
			if(fart[i]<speedmph && fart[i+1]>speedmph) {
				met = MET[i];	
			}
		}
		kcal = met*weight*((secs)/3600.0);
		// Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)

		// OPPGAVE SLUTT
		
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		double speeds[] = speeds();

		// TODO
		// OPPGAVE - START 
		for(int i=0; i<speeds.length-1; i++ ) {
			totalkcal = totalkcal + kcal(weight, times[i+1]-times[i], speeds[i]);
			
		}
		
		// Hint: hent hastigheter i speeds tabellen og tider i timestabellen
		// disse er definer i toppen av klassen og lese automatisk inn
		
		// OPPGAVE - SLUTT
		
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	// skriv ut statistikk for turen
	public void print() {
		
		// TODO
		// OPPGAVE - START
		String tid = GPSUtils.printTime(totalTime());
		String avstand = GPSUtils.printDouble(totalDistance()/1000);
		String hoyde = GPSUtils.printDouble(totalElevation());
		String mFart = GPSUtils.printDouble(maxSpeed());
		String gjFart = GPSUtils.printDouble(averageSpeed());
		String energi = GPSUtils.printDouble(totalKcal(WEIGHT));
		String tabell[] = new String[6];
		tabell[0] = ("Total time     :      "+tid);
		tabell[1] = ("Total distance : "+avstand+"km");
		tabell[2] = ("Total elevation:  "+hoyde+"m");
		tabell[3] = ("Max speed      : "+mFart+"km/t");
		tabell[4] = ("Average speed  : "+gjFart+"km/t");
		tabell[5] = ("Energy         :  "+energi+"kcal");
		for(int i=0; i<tabell.length; i++) {
			System.out.println(tabell[i]);
		}
		
		// OPPGAVE - SLUT
	}
	
	// ekstraoppgaver
	public void climbs() {
		
	}
	
	public void maxClimb() {
		
	}
}
