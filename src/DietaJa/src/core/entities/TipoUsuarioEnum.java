package core.entities;

import java.util.HashMap;
import java.util.Map;

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
	
    static {
        for (TipoUsuarioEnum e: values()) {
        	mapId.put(e.id, e);
        	mapNome.put(e.nome, e);
        	mapEnum.put(e, e.id);
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
    
    
}
