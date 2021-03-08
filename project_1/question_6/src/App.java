import java.nio.ByteBuffer;
import java.util.Scanner;

public class App {
    private static ByteBuffer encryptionKey;
    private static String wordToEncrypt;

    public static void main(String[] args) throws Exception {
        getEncryptionKey();

        getWordToEncrypt();

        Encryptor encryptor = new Encryptor(encryptionKey, wordToEncrypt);
        encryptor.encryptAndPrint();
    }

    private static void getWordToEncrypt() {
        Scanner scanner = new Scanner(System.in);
        wordToEncrypt = scanner.nextLine();
    }

    private static void getEncryptionKey() {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        char[] inputCharArray = inputString.toCharArray();
        byte[] inputBytes = new byte[inputCharArray.length];

        for (int index = 0; index < inputCharArray.length; index++) {
            inputBytes[index] = (byte) (inputCharArray[index] - 48);
        }

        encryptionKey = ByteBuffer.wrap(inputBytes);
    }
}
