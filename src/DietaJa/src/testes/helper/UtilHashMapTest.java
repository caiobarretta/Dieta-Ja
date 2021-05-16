package testes.helper;

import static org.junit.Assert.fail;
import java.util.Map;
import junit.framework.TestCase;

public class UtilHashMapTest extends TestCase{

	
	public static void testMapCount(int length, Map<Integer, Object> map) {
		int counter = 0;
		for(var m: map.entrySet()) {
			counter++;
			System.out.println(m.getKey() + ":" + m.getValue());
		}
		if(counter < length)
			fail("Tamanho do map menor que deveria");
		
		if(!map.containsKey(1))
			fail("Deveria conter chave informada");
	}
	
	public static void testContentMap(Map<Integer, Object> map, Object...values) {
		int index = 0;
		for(var m: map.entrySet()) {
			if(!values[index].equals(m.getValue()) )
				fail(String.format("na posição index:%d o valor é: %s e deveria ser: %s",index, m.getValue().toString(), values[index].toString()));
			System.out.println(m.getKey() + ":" + m.getValue());
			index++;
		}
	}
}
