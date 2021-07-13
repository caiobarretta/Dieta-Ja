package core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Classe que representa um Tipo de usuário
public enum TipoUsuarioEnum {
	Administrador(1, "Administrador"),
	Nutricionista(2, "Nutricionista"),
	Paciente(3, "Paciente");

	private final Integer id;
	private final String nome;

    private TipoUsuarioEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
	
    private static final Map<Integer, TipoUsuarioEnum> mapId = new HashMap<>();
	private static final Map<String, TipoUsuarioEnum> mapNome = new HashMap<>();
	private static final Map<TipoUsuarioEnum, Integer> mapEnum = new HashMap<>();
	private static final Map<Integer, String> mapIdNome = new HashMap<>();
	
    static {
        for (TipoUsuarioEnum e: values()) {
        	mapId.put(e.id, e);
        	mapNome.put(e.nome, e);
        	mapEnum.put(e, e.id);
        	mapIdNome.put(e.id, e.nome);
        }
    }
    public static TipoUsuarioEnum retornaEnumPeloId(Integer id) {
    	return mapId.get(id);
    }
    
    public static TipoUsuarioEnum retornaEnumPeloNome(String nome) {
        return mapNome.get(nome);
    }
    
    public static Integer retornaIdPeloEnum(TipoUsuarioEnum enumeration) {
    	return mapEnum.get(enumeration);
    }
    
    public static List<String> asList(){
    	List<String> lst = new ArrayList<String>();
    	for (Map.Entry<String, TipoUsuarioEnum> entry : mapNome.entrySet()) {
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
    	for (Map.Entry<Integer, TipoUsuarioEnum> entry : mapId.entrySet()) {
    		lstOrdered.add(entry.getKey());
		}
    	
    	Collections.sort(lstOrdered);
    	for (Integer id : lstOrdered) {
    		lst.add(retornaNomeEnumPeloId(id));
		}
    	return lst;
    }
    
    
}
