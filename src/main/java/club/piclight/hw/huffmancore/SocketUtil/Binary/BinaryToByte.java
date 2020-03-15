package club.piclight.hw.huffmancore.SocketUtil.Binary;

import java.util.ArrayList;
import java.util.List;

/**
 * String格式二进制序列转至Byte
 */
public class BinaryToByte {
    private String binarySequence;
    private List<Byte> byteBuffer = new ArrayList<>();

    public BinaryToByte(String binarySequence) {
        this.binarySequence = binarySequence;
    }

    public byte[] getByteArray() {
        StringBuilder binaryCodeBuffer = new StringBuilder(); //存放等待编码的String二进制数据
        binarySequence.chars().forEach(_char -> {
            binaryCodeBuffer.append((char) _char);

            //当集齐8个字符，进行编码
            if (binaryCodeBuffer.length() == 8) {
                byte tempByte = (byte) Integer.parseInt(binaryCodeBuffer.toString(), 2); //编码
                binaryCodeBuffer.delete(0, binaryCodeBuffer.length()); //清空二进制String缓存区
                byteBuffer.add(tempByte); //将编码后的字节数据存放
            }
        });
        //将剩余不足8bit的数据补充到8bit
        if (binaryCodeBuffer.length() != 0) {
            do {
                binaryCodeBuffer.append('0');
            } while (binaryCodeBuffer.length() != 8);
            byte tempByte = (byte) Integer.parseInt(binaryCodeBuffer.toString(), 2); //编码
            binaryCodeBuffer.delete(0, binaryCodeBuffer.length()); //清空二进制String缓存区
            byteBuffer.add(tempByte); //将编码后的字节数据存放
        }

        byte[] byteBufferArray = new byte[byteBuffer.size()];
        for (int i = 0; i < byteBuffer.size(); ++i) {
            byteBufferArray[i] = byteBuffer.get(i);
        }
        return byteBufferArray; //返回byte数组
    }
}
