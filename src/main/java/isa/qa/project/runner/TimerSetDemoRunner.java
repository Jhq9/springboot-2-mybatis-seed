package isa.qa.project.runner;

import isa.qa.project.timers.CronDemoTimer;
import isa.qa.project.timers.StartAtDemoTimer;
import lombok.AllArgsConstructor;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 *  The demo of set the timer runner
 *
 *  @author    May
 *  @date      2018/11/23 15:08
 *  @version   1.0
 */
@Component
@AllArgsConstructor
public class TimerSetDemoRunner implements CommandLineRunner {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerSetDemoRunner.class);

    private final Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Set the startAt timer and the cron timer");

        setStartAtTimer();
        setCronTimer();
    }

    /**
     * Set the start At timer
     *
     * @throws SchedulerException
     */
    private void setStartAtTimer() throws SchedulerException {
        //Task name
        String taskName = UUID.randomUUID().toString();

        //Task group name
        String taskGroup = StartAtDemoTimer.class.getName();
        //Task start time
        Long startTime = System.currentTimeMillis() + 1000 * 2 * 60;

        //Job Detail
        JobDetail jobDetail = JobBuilder
                .newJob(StartAtDemoTimer.class)
                .withIdentity(taskName, taskGroup)
                .build();
        //Create task trigger(创建任务触发器)
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(taskName, taskGroup)
                .startAt(new Date(startTime))
                .build();
        //Bind the job task with trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * Set the cron timer
     *
     * @throws SchedulerException
     */
    private void setCronTimer() throws SchedulerException {
        //Task name
        String taskName = UUID.randomUUID().toString();
        //Task group name
        String groupName = CronDemoTimer.class.getName();
        //Cron
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");

        JobDetail jobDetail = JobBuilder
                .newJob(CronDemoTimer.class)
                .withIdentity(taskName, groupName)
                .build();

        jobDetail.getJobDataMap().put("taskName", taskName);
        //Create the trigger
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(taskName, groupName)
                .withSchedule(scheduleBuilder)
                .build();
        //Bind the job task with trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
