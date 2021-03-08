public class EditDistance {
    public static int calculateDistance(String string1, String string2) {
        int string1Length = string1.length();
        int string2Length = string2.length();

        if (string1Length == 0) {
            return string2Length;
        }

        if (string2Length == 0) {
            return string1Length;
        }

        if (string1.charAt(string1Length - 1) == string2.charAt(string2Length - 1)) {
            return calculateDistance(removeLastCharacter(string1), removeLastCharacter(string2));
        }

        return 1 + minimum(calculateDistance(string1, removeLastCharacter(string2)),
                calculateDistance(removeLastCharacter(string1), string2),
                calculateDistance(removeLastCharacter(string1), removeLastCharacter(string2)));
    }

    private static int minimum(int num1, int num2, int num3) {
        return Math.min(Math.min(num1, num2), num3);
    }

    private static String removeLastCharacter(String string) {
        return string.substring(0, string.length() - 1);
    }

}
