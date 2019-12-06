package montp.services;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

//@Singleton
public class DeclarativeScheduler {
    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void atSchedule() throws InterruptedException {
        System.out.println("DeclarativeScheduler:: In atSchedule()");
    }
}
