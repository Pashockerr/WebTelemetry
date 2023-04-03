package com.github.pashockerr.webtelemetry.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.pashockerr.webtelemetry.models.Values;

@Component
public class ValuesComponent {
	private Values cachedValues;
	private final OkHttpClient okHttpClient;
	@Value("${request.url}")
	private String url;

	public ValuesComponent(OkHttpClient okHttpClient){
		this.okHttpClient = okHttpClient;
	}
	
	public Values getCachedValues() {
		return cachedValues;
	}
	
	@Scheduled(fixedDelay = 2000)
	private void updateCachedValues() throws IOException {
		Request request = new Request.Builder().url(url).build();
		Call call = okHttpClient.newCall(request);
		Response response = call.execute();
		var stream = response.body().charStream();
		StringBuilder stringBuilder = new StringBuilder();
		while(stream.ready()){
			stringBuilder.append(stream.read());
		}
		String body = stringBuilder.toString();
		String[] data = body.split(",");
		ArrayList<Float> numValues = new ArrayList<>();
		for (var value:
			 data) {
			numValues.add(Float.parseFloat(value.split(":")[1]));
		}
		//Random rand = new Random();
		cachedValues = new Values(numValues.get(0), numValues.get(1), numValues.get(2), numValues.get(3), numValues.get(4), numValues.get(5));
	}
}
