package com.housee.app.job.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CreateNewGameJob extends QuartzJobBean {
	
	@Autowired
	private CreateNewGameTask createNewGameTask;
	
	public void setCreateNewGameTask(CreateNewGameTask createNewGameTask) {
		this.createNewGameTask = createNewGameTask;
	}
 
	protected void executeInternal(JobExecutionContext context)
		throws JobExecutionException {
		System.out.println("Check New Game Status To Create ---> ");
		createNewGameTask.checkNewGameToCreate();
 
	}
	

}
