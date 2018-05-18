package com.example.helloservice.serverportconfig;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        applicationEnvironmentPreparedEvent.getEnvironment().getPropertySources().addLast(new RandomServerPortPropertySource());
    }
}
