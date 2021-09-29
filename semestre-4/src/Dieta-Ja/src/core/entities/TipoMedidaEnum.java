package core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TipoMedidaEnum {
	g(1, "g"),
	Kg(2, "kg"),
	Ml(3, "Ml");

	private final Integer id;
	private final String nome;

    private TipoMedidaEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
	
    private static final Map<Integer, TipoMedidaEnum> mapId = new HashMap<>();
	private static final Map<String, TipoMedidaEnum> mapNome = new HashMap<>();
	private static final Map<TipoMedidaEnum, Integer> mapEnum = new HashMap<>();
	private static final Map<Integer, String> mapIdNome = new HashMap<>();
	
    static {
        for (TipoMedidaEnum e: values()) {
        	mapId.put(e.id, e);
        	mapNome.put(e.nome, e);
        	mapEnum.put(e, e.id);
        	mapIdNome.put(e.id, e.nome);
        }
    }
    public static TipoMedidaEnum retornaEnumPeloId(Integer id) {
    	return mapId.get(id);
    }
    
    public static TipoMedidaEnum retornaEnumPeloNome(String nome) {
        return mapNome.get(nome);
    }
    
    public static Integer retornaIdPeloEnum(TipoMedidaEnum enumeration) {
    	return mapEnum.get(enumeration);
    }
    
    public static List<String> asList(){
    	List<String> lst = new ArrayList<String>();
    	for (Map.Entry<String, TipoMedidaEnum> entry : mapNome.entrySet()) {
			lst.add(entry.getKey());
		}
    	return lst;
    }
    
    public static String retornaNomeEnumPeloId(Integer id){
    	return mapIdNome.get(id);
    }
    
    public static List<String> asListOrderedById(){
    	List<Integer> lstOrdered = new ArrayList<Integer>();
    	List<String> lst = new ArrayList<String>();
    	for (Map.Entry<Integer, TipoMedidaEnum> entry : mapId.entrySet()) {
    		lstOrdered.add(entry.getKey());
		}
    	
    	Collections.sort(lstOrdered);
    	for (Integer id : lstOrdered) {
    		lst.add(retornaNomeEnumPeloId(id));
		}
    	return lst;
    }
}
