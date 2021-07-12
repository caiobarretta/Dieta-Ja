package core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Enum que representa os dias da semana
public enum DiaDaSemanaEnum {
	Segunda(1, "Segunda-feira"),
	Terca(2, "Terça-feira"),
	Quarta(3, "Quarta-feira"),
	Quinta(4, "Quinta-feira"),
	Sexta(5, "Sexta-feira"),
	Sabado(6, "Sábado"),
	Domingo(7, "Domingo");
	
	private final Integer id;
	private final String nome;

    private DiaDaSemanaEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
	
    private static final Map<Integer, DiaDaSemanaEnum> mapId = new HashMap<>();
	private static final Map<String, DiaDaSemanaEnum> mapNome = new HashMap<>();
	private static final Map<DiaDaSemanaEnum, Integer> mapEnum = new HashMap<>();
	private static final Map<Integer, String> mapIdNome = new HashMap<>();
	private static final Map<String, Integer> mapNomeId = new HashMap<>();
	
    static {
        for (DiaDaSemanaEnum e: values()) {
        	mapId.put(e.id, e);
        	mapNome.put(e.nome, e);
        	mapEnum.put(e, e.id);
        	mapIdNome.put(e.id, e.nome);
        	mapNomeId.put( e.nome, e.id);
        }
    }
    public static DiaDaSemanaEnum retornaEnumPeloId(Integer id) {
    	return mapId.get(id);
    }
    
    public static DiaDaSemanaEnum retornaEnumPeloNome(String nome) {
        return mapNome.get(nome);
    }
    
    public static Integer retornaIdPeloEnum(TipoUsuarioEnum enumeration) {
    	return mapEnum.get(enumeration);
    }
    
    public static String retornaNomeEnumPeloId(Integer id){
    	return mapIdNome.get(id);
    }
    
    public static Integer retornaIdPeloNome(String nome) {
    	return mapNomeId.get(nome);
    }
    
    public static List<String> asListOrderedById(){
    	List<Integer> lstOrdered = new ArrayList<Integer>();
    	List<String> lst = new ArrayList<String>();
    	for (Map.Entry<Integer, DiaDaSemanaEnum> entry : mapId.entrySet()) {
    		lstOrdered.add(entry.getKey());
		}
    	
    	Collections.sort(lstOrdered);
    	for (Integer id : lstOrdered) {
    		lst.add(retornaNomeEnumPeloId(id));
		}
    	return lst;
    }

    public static List<Integer> convertListStringToListInt(List<String> lst){
    	List<Integer> listId = new ArrayList<Integer>();
		for (String item : lst) {
			Integer itemEnum = retornaIdPeloNome(item);
			listId.add(itemEnum);
		}
		return listId;
    }
}
