package core.ioc;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Container {
	private Map<Serializable, Object> mapInterfaceImplementation;
	public Container() {
		mapInterfaceImplementation = new HashMap<Serializable, Object>();
	}
	
	public void register(Serializable interfaceObj, Object implementationObj) {
		
		if(implementationObj == null)
			throw new NullPointerException("O Objeto de implementação não pode ser nulo.");
		
		mapInterfaceImplementation.put(interfaceObj, implementationObj);
	}
	
	public <T extends Serializable>  Object resolve(T interfaceObj){
		return mapInterfaceImplementation.get(interfaceObj);
	}
}
