package com.twinzom.apexa.caleng.model;

import java.util.List;
import java.util.Map;

public class CalResponse {

	private Map<String , List<CalSnapshot>> snapshotsByGroup;

	public Map<String, List<CalSnapshot>> getSnapshotsByGroup() {
		return snapshotsByGroup;
	}

	public void setSnapshotsByGroup(Map<String, List<CalSnapshot>> snapshotsByGroup) {
		this.snapshotsByGroup = snapshotsByGroup;
	}
	
}
