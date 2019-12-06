package montp.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;


//@Singleton
//@Startup
public class TimerSessionBean {
    @Resource
    TimerService timerService;

    @PostConstruct
    public void initialize() {
        timerService.createTimer(0, 4000, "Every four second timer with no delay");
    }

    @Timeout
    public void programmaticTimeout(Timer timer) {
        System.out.println("ProgrammaticScheduler:: in programmaticTimeout");
    }
}
