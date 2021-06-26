package DietaJa;


public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object teste = 1;
		Class cls = Integer.class;
		boolean retval = cls.isInstance(teste);
		System.out.println(retval);
	}
}
