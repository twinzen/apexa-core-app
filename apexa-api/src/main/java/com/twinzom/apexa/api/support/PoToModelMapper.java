package com.twinzom.apexa.api.support;

import java.util.List;

public interface PoToModelMapper <P, M> {

	public M mapObject (P poObject);
	
	public List<M> mapObjects (List<P> poObjects);
	
}
