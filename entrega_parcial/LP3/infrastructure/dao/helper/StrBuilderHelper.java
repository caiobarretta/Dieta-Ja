package infrastructure.dao.helper;

public class StrBuilderHelper {

	
	public static String criarString(String...params) {
		var sb = new StringBuilder();
		for (String s : params) {
			sb.append(s);
		}
		return sb.toString();
	}
}
