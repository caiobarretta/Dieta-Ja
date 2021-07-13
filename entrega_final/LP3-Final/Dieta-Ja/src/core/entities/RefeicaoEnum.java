package core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Enum que representa uma RefeiÃ§Ã£o
public enum RefeicaoEnum {
	CafeDaManha(1, "Café da Manhã"),
	Brunch(2, "Bruch"),
	Almoço(3, "Almoço"),
	Lanche(4, "Lanche"),
	Jantar(5, "Jantar"),
	Ceia(6, "Ceia");
	
	private final Integer id;
	private final String nome;

    private RefeicaoEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
	
    private static final Map<Integer, RefeicaoEnum> mapId = new HashMap<>();
	private static final Map<String, RefeicaoEnum> mapNome = new HashMap<>();
	private static final Map<RefeicaoEnum, Integer> mapEnum = new HashMap<>();
	private static final Map<Integer, String> mapIdNome = new HashMap<>();
	private static final Map<String, Integer> mapNomeId = new HashMap<>();
	
    static {
        for (RefeicaoEnum e: values()) {
        	mapId.put(e.id, e);
        	mapNome.put(e.nome, e);
        	mapEnum.put(e, e.id);
        	mapIdNome.put(e.id, e.nome);
        	mapNomeId.put( e.nome, e.id);
        }
    }
    public static RefeicaoEnum retornaEnumPeloId(Integer id) {
    	return mapId.get(id);
    }
    
    public static RefeicaoEnum retornaEnumPeloNome(String nome) {
        return mapNome.get(nome);
    }
    
    public static Integer retornaIdPeloEnum(RefeicaoEnum enumeration) {
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
    	for (Map.Entry<Integer, RefeicaoEnum> entry : mapId.entrySet()) {
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
