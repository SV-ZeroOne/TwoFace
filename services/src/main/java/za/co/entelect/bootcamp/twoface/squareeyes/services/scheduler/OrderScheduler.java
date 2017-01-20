package za.co.entelect.bootcamp.twoface.squareeyes.services.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by sean.vienings on 2017/01/20.
 */
public class OrderScheduler {

  public static void main(String[] args) throws Exception
  {

          JobDetail scheduleJob = JobBuilder.newJob(ScheduleJob.class)
                  .withIdentity("Order Schedule", "group1").build();


          Trigger trigger = TriggerBuilder
                  .newTrigger()
                  .withIdentity("Trigger", "trigger")
                  .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?"))
                  .build();

         // Thread.sleep(90L * 1000L);

          Scheduler scheduler = new StdSchedulerFactory().getScheduler();
          System.out.println("schedule starting");
          scheduler.start();

          scheduler.scheduleJob(scheduleJob, trigger);

  }

}
