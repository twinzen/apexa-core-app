package com.twinzom.apexa.api.remoteclient;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twinzom.apexa.caleng.model.CalGroup;
import com.twinzom.apexa.caleng.model.CalPosition;
import com.twinzom.apexa.caleng.model.CalResponse;
import com.twinzom.apexa.caleng.model.CalSnapshot;

@Service
public class CalEngClient {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${caleng.endpoint.base}")
	private String endpointBase;
	
	@Value("${caleng.calculateHoldingSnapshots.uri}")
	private String calculateHoldingSnapshotsUri;
	
	@Async ("taskExecutor1")
	public CompletableFuture<CalResponse> callCalculateHoldingSnapshots (List<CalGroup> calGroups, String datesStr) {
		
		String calculateHoldingSnapshotsEndpoint = endpointBase + calculateHoldingSnapshotsUri + "?dates="+datesStr;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<List> entity = new HttpEntity<List>(calGroups, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(calculateHoldingSnapshotsEndpoint, entity, String.class);

		Gson gson = new GsonBuilder().create();
		CalResponse reponse = gson.fromJson(response.getBody() , CalResponse.class);
		
		return CompletableFuture.completedFuture(reponse);
	}
	
}
