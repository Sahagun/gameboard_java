
public class SimpleHash {
	public static int hash(String string){
		int hash = 5;
		for(int i = 0; i < string.length(); i++){
			hash = hash*31+string.charAt(i);
		}
		return hash;
	}
	public static void main( String[] args ) {
		String  s = "dogdsdads";
		System.out.println("Java Hash code: " + s.hashCode());
		System.out.println("Saha Hash code: " + SimpleHash.hash("Do"));
		System.out.println("Saha Hash code: " + SimpleHash.hash("oD"));
	}
}
