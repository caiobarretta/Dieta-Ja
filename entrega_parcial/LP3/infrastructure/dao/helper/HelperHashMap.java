package infrastructure.dao.helper;
import java.util.HashMap;
import java.util.Map;

public class HelperHashMap {
	
	public static Map<Integer, Object> criarHashMapComNInteirosSequenciais(Integer... params){
		var  map = preencheMapSequencialmente(params);
		return map;
	}
	
	public static Map<Integer, Object> criarHashMapComNStringsSequenciais(String... params){
		var  map = preencheMapSequencialmente(params);
		return map;
	}

	private static Map<Integer, Object> preencheMapSequencialmente(Object... params) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		int index = 1;
		for (var param : params) {
			map.put(index, param);
			index++;
		}
		return map;
	}
}
