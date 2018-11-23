package isa.qa.project.timers;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.Instant;
import java.util.Date;

/**
 *  The cron timer demo
 *
 *  @author    May
 *  @date      2018/11/23 15:04
 *  @version   1.0
 */
public class CronDemoTimer extends QuartzJobBean {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CronDemoTimer.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String taskName = jobDetail.getJobDataMap().get("taskName").toString();

        LOGGER.info("The task = {}, Execute At {}", taskName, Date.from(Instant.now()));
    }
}
