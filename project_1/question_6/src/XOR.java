import java.nio.ByteBuffer;

public class XOR {
    public static ByteBuffer check(ByteBuffer bytes1, ByteBuffer bytes2) {
        byte[] bytes1Array = bytes1.array();
        byte[] bytes2Array = bytes2.array();
        byte[] result = new byte[bytes1Array.length];

        for (int index = 0; index < bytes1Array.length; index++) {
            if (bytes1Array[index] != bytes2Array[index]) {
                result[index] = (byte) 1;
            } else {
                result[index] = (byte) 0;
            }
        }

        return ByteBuffer.wrap(result);
    }
}
