package com.github.pashockerr.webtelemetry.components;

import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pashockerr.webtelemetry.models.Values;

@Component
public class ValuesComponent {
	private Values cachedValues;
	
	public Values getCachedValues() {
		return cachedValues;
	}
	
	@Scheduled(fixedDelay = 1000)
	private void updateCachedValues(){
		Random rand = new Random();
		cachedValues = new Values(rand.nextFloat(0, 12), rand.nextFloat(-10, 10), rand.nextFloat(0, 12), rand.nextFloat(-5, 5), rand.nextFloat(0, 12), rand.nextFloat(-5, 5));
	}
}
