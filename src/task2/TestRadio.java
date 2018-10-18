package task2;

public class TestRadio {

	public static void main(String[] args) {
		Radio r1 = new Radio(true, "Audio sistem Sony MHC", 96.2);
		
		System.out.println(r1.getFrequency());
		
		System.out.println(r1.toString());
	}
}
