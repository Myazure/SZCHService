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
		LOG.debug("secScanner"+System.currentTimeMillis());
	}

	@Scheduled(cron = "0/30 * *  * * ? ")
	protected void thirtySecScanner() {
		LOG.debug("thirtySecScanner"+System.currentTimeMillis());
	}

	@Scheduled(cron = "0 0/1 *  * * ? ")
	protected void minScanner() {
		LOG.debug("minScanner"+System.currentTimeMillis());
	}

	@Scheduled(cron = "0 0/10 *  * * ? ")
	protected void tenMinScanner() {
		LOG.debug("tenMinScanner"+System.currentTimeMillis());
	}

	@Scheduled(cron = "0 0/30 *  * * ? ")
	protected void halfHourScanner() {
		LOG.debug("halfHourScanner"+System.currentTimeMillis());
	}

	@Scheduled(cron = "0 0 0/1  * * ? ")
	protected void hourScanner() {
		LOG.debug("hourScanner"+System.currentTimeMillis());
	}

	@Scheduled(cron = "0 0 0 0/1 * ? ")
	protected void daylyScanner() {
		LOG.debug("daylyScanner"+System.currentTimeMillis());
	}

}
