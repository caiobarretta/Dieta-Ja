package core.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SentimentoEnum {
	MuitoSatisfeito(1, "Muito Satisfeito"),
	Satisfeito(2, "Satisfeito"),
	RazoalmenteSatisfeito(3, "Razoavelmente Satisfeito"),
	PoucoSatisfeito(4, "Pouco Satisfeito"),
	insatisfeito(5, "insatisfeito"),
	TotalmenteInsatisfeito(3, "Totalmente Insatisfeito");

	private final Integer id;
	private final String nome;

    private SentimentoEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
	
    private static final Map<Integer, SentimentoEnum> mapId = new HashMap<>();
	private static final Map<String, SentimentoEnum> mapNome = new HashMap<>();
	private static final Map<SentimentoEnum, Integer> mapEnum = new HashMap<>();
	private static final Map<Integer, String> mapIdNome = new HashMap<>();
	
    static {
        for (SentimentoEnum e: values()) {
        	mapId.put(e.id, e);
        	mapNome.put(e.nome, e);
        	mapEnum.put(e, e.id);
        	mapIdNome.put(e.id, e.nome);
        }
    }
    public static SentimentoEnum retornaEnumPeloId(Integer id) {
    	return mapId.get(id);
    }
    
    public static SentimentoEnum retornaEnumPeloNome(String nome) {
        return mapNome.get(nome);
    }
    
    public static Integer retornaIdPeloEnum(SentimentoEnum enumeration) {
    	return mapEnum.get(enumeration);
    }
    
    public static List<String> asList(){
    	List<String> lst = new ArrayList<String>();
    	for (Map.Entry<String, SentimentoEnum> entry : mapNome.entrySet()) {
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
    	for (Map.Entry<Integer, SentimentoEnum> entry : mapId.entrySet()) {
    		lstOrdered.add(entry.getKey());
		}
    	
    	Collections.sort(lstOrdered);
    	for (Integer id : lstOrdered) {
    		lst.add(retornaNomeEnumPeloId(id));
		}
    	return lst;
    }
}
