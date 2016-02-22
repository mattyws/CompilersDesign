package program3;

public class Main {

	public void generateTokens(String text){
		Token t = null;
		int i = 0;
		switch (text.charAt(0)) {
		case 'i':
			t = new TokenIf(text, 1);
			break;
		default:
			break;
		}
		while(t.pos < text.length()){
			//t = t.getToken();
		}
	}
}
