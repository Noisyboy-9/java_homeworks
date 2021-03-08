import java.nio.ByteBuffer;

public class Encryptor {
    private ByteBuffer key;
    private String word;

    public Encryptor(ByteBuffer key, String word) {
        this.key = key;
        this.word = word;
    }

    public void encryptAndPrint() {
        for (char character : word.toCharArray()) {
            ByteBuffer wordAsciiByte = convertLetterToAsciiValue(character);

            ByteBuffer result = XOR.check(wordAsciiByte, key);
            this.print(result.array());
        }
    }

    private ByteBuffer convertLetterToAsciiValue(char character) {
        String binaryString = String.format("%8s", Integer.toBinaryString(character)).replace(" ", "0");
        char[] binaryCharArray = binaryString.toCharArray();
        byte[] byteArray = new byte[binaryCharArray.length];

        for (int index = 0; index < binaryCharArray.length; index++) {
            byteArray[index] = (byte) (binaryCharArray[index] - 48);
        }

        return ByteBuffer.wrap(byteArray);
    }

    private void print(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.print(" ");
    }

}
