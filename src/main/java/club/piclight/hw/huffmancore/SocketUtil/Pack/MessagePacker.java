package club.piclight.hw.huffmancore.SocketUtil.Pack;

import club.piclight.hw.huffmancore.SocketUtil.Binary.BinaryToByte;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据打包类
 * <p>
 * 将数据打包成byte数组，协议1.0版本
 */
public class MessagePacker {
    private Type type;
    private String hash;
    private String data; //如果是字典消息，不进行二进制01代码，直接放json字典，如果是消息，则是01代码，等待编码

    /**
     * 将待发送的消息打包
     *
     * @param type 消息类型
     * @param hash 消息哈希
     * @param data 消息数据 json字典未编码格式，或者已经编码的二进制哈夫曼数据
     */
    public MessagePacker(Type type, String hash, String data) {
        this.type = type;

        /* Cut hash string */
        if (hash.length() < 6)
            throw new IllegalArgumentException("Hash length can not less than 6");
        this.hash = hash.substring(0,6); //Todo check hash string length

        this.data = data; //Todo if binary data, check code max length ‭65535‬
    }

    /**
     * 打包消息
     *
     * @return 返回打包后的字节数组数据
     */
    public byte[] pack() {
        List<Byte> dataByte = new ArrayList<>();
        /*数据头，一字节*/
        dataByte.add(type.getHead());

        /*消息哈希，三个字节*/
        String hashBinary = String.format("%24s", Integer.toBinaryString(Integer.parseInt(hash, 16))).replace(' ', '0');
        System.out.println(hashBinary);
        for (byte _byte : new BinaryToByte(hashBinary).getByteArray()) {
            dataByte.add(_byte);
        }

        /*处理数据体*/
        if (type == Type.CONTENT) {
            /*处理哈夫曼编码*/

            /*写入消息体长度*/
            String huffmanLength = String.format("%16s", Integer.toBinaryString(data.length())).replace(' ', '0');
            for (byte _byte : new BinaryToByte(huffmanLength).getByteArray()) {
                dataByte.add(_byte);
            }
            assert dataByte.size() == 6 : "Assert CONTENT HEAD length is 6, check error";

            /*写入消息体*/
            System.out.println(data);
            for (byte _byte : new BinaryToByte(data).getByteArray()) {
                System.out.println("Mark");
                System.out.println(_byte);
                dataByte.add(_byte);
            }
        } else if (type == Type.DICT) {
            /*处理哈夫曼编码json字典*/
            for (byte _byte : data.getBytes()) {
                dataByte.add(_byte);
            }
        }

        byte[] unboxByte = new byte[dataByte.size()];
        for (int i = 0; i < dataByte.size(); ++i) {
            unboxByte[i] = dataByte.get(i);
        }

        return unboxByte;
    }
}
