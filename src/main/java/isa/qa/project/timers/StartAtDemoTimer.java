package isa.qa.project.timers;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.Instant;
import java.util.Date;

/**
 *  The timer demo which is start at the set time
 *  
 *  @author    May
 *  @date      2018/11/23 15:01
 *  @version   1.0
 */
public class StartAtDemoTimer extends QuartzJobBean {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StartAtDemoTimer.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("Start At类型Timer开始执行，At {}", Date.from(Instant.now()));
    }
}
