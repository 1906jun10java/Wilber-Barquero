
public class Driver {
	public static void main(String[] args) {
		MinimumMutations method = new MinimumMutations();
		String start = "AACCGGTT";
		String end ="AAACGGTA";
		String bank [] = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
		System.out.println("Number of mutations: "+method.minMutation(start, end, bank));
	}
}
