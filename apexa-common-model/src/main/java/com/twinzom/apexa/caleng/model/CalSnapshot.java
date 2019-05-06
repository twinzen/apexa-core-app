package com.twinzom.apexa.caleng.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalSnapshot {

	private String snapshotKey;
	
	private CalPosition position;

	public String getSnapshotKey() {
		return snapshotKey;
	}

	public void setSnapshotKey(String snapshotKey) {
		this.snapshotKey = snapshotKey;
	}

	public CalPosition getPosition() {
		return position;
	}

	public void setPosition(CalPosition position) {
		this.position = position;
	}

	
}
