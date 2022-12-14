package price.fuel.pf2.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import price.fuel.pf2.common.domain.EntityMap;


public interface ServiceIF {

	public List<HashMap> select(HashMap entity);
	public HashMap  detail(HashMap entity);
}
