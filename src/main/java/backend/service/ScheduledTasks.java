package backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import backend.service.impl.OglasiServiceImpl;

@Configuration
@EnableScheduling
public class ScheduledTasks {

	@Autowired
	private OglasiServiceImpl oglasiService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void deleteExpiredAdsTask() {
		oglasiService.deleteExpiredAdvertisements();
	}
	
}
