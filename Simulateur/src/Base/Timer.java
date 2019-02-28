package Base;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
/**
 * A class Timer
 * @author hugom
 */
public class Timer {
	/**
	 * The start of the Timer
	 */
	private Instant debut;
	/**
	 * The lasting of the timer in millisecondes
	 */
	private long duree; // duree du timer en millisecondes
	/**
	 * Constructor of a Timer
	 * @param duree
	 * @return a timer that will last duree millisecondes
	 */
	public Timer(long duree) {
		this.duree = duree;
		restart();
	}
	/**
	 * Restart the timer
	 */
	public void restart() {
		debut = Instant.now();
	}
	/**
	 * @return if the timer has finished
	 */
	public boolean hasFinished() {
		return (debut.compareTo((Instant.now().minus(duree, ChronoUnit.MILLIS))) < 0);
	}
	
}
