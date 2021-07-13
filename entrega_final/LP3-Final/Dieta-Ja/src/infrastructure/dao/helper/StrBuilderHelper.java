package infrastructure.dao.helper;

public class StrBuilderHelper {

	
	public static String criarString(String...params) {
		StringBuilder sb = new StringBuilder();
		for (String s : params) {
			sb.append(s);
		}
		return sb.toString();
	}
}
