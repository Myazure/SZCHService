package org.myazure.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleMananger {
	private static final Logger LOG = LoggerFactory
			.getLogger(ScheduleMananger.class);

	public ScheduleMananger() {
	}

	@Scheduled(cron = "0/1 * *  * * ? ")
	protected void secScanner() {
		LOG.debug("secScanner");
	}

	@Scheduled(cron = "0/30 * *  * * ? ")
	protected void thirtySecScanner() {
		LOG.debug("thirtySecScanner");
	}

	@Scheduled(cron = "0 0/1 *  * * ? ")
	protected void minScanner() {
		LOG.debug("minScanner");
	}

	@Scheduled(cron = "0 0/10 *  * * ? ")
	protected void tenMinScanner() {
		LOG.debug("tenMinScanner");
	}

	@Scheduled(cron = "0 0/30 *  * * ? ")
	protected void halfHourScanner() {
		LOG.debug("halfHourScanner");
	}

	@Scheduled(cron = "0 0 0/1  * * ? ")
	protected void hourScanner() {
		LOG.debug("hourScanner");
	}

	@Scheduled(cron = "0 0 0 0/1 * ? ")
	protected void daylyScanner() {
		LOG.debug("daylyScanner");
	}

}
