import java.time.Duration;
import java.time.Instant;
import java.util.Timer;

public class Test {
    private Instant startTime;
    private Timer timer;

    public void startTimer() {
        startTime = Instant.now();
        timer = new Timer();
    }

    public void stopTimer() {
        if (timer != null) {
            Duration duration = Duration.between(startTime, Instant.now());
            System.out.println("Time elapsed " + duration.getNano());
            timer.cancel();
            timer.purge();
        }
    }
}
