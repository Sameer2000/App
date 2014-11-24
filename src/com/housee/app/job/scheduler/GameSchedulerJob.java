package com.housee.app.job.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class GameSchedulerJob extends QuartzJobBean {
	
	@Autowired
	private GameSchedulerTask gameSchedulerTask;
 
	public void setGameSchedulerTask(GameSchedulerTask gameSchedulerTask) {
		this.gameSchedulerTask = gameSchedulerTask;
	}
 
	protected void executeInternal(JobExecutionContext context)
		throws JobExecutionException {
		System.out.println("Check New Game Status ---> ");
		gameSchedulerTask.checkScheduleGame();
 
	}
}