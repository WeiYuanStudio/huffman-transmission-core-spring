package club.piclight.hw.huffmancore.SocketUtil.Binary;

/**
 * 将字节数组转换成二进制String
 */
public class ByteToBinary {
    private byte[] huffmanByte; //哈夫曼数据
    private int binaryCodeLength; //二进制数据长度

    public ByteToBinary(byte[] huffmanByte) {
        this.huffmanByte = huffmanByte;
        this.binaryCodeLength = huffmanByte.length * 8;
    }

    /**
     * 构造
     *
     * @param huffmanByte      哈夫曼字节数据
     * @param binaryCodeLength 二进制编码长度
     */
    public ByteToBinary(byte[] huffmanByte, int binaryCodeLength) {
        this.huffmanByte = huffmanByte;
        this.binaryCodeLength = binaryCodeLength;
    }

    /**
     * 获取String格式二进制数据
     *
     * @return String格式二进制数据
     */
    public String getBinary() {
        StringBuilder stringBuilder = new StringBuilder();
        int length = binaryCodeLength; //复制二进制数据长度
        for (byte _byte : huffmanByte) {
            String tempBinaryCode = String.format("%8s", Integer.toBinaryString(_byte & 0xFF)).replace(' ', '0'); //读取一个字节并转换成二进制
            if (length >= 8) { //在适当的地方截取掉补位二进制
                length -= 8; //每读取一个字节长度减8bit
                stringBuilder.append(tempBinaryCode); //加入8bit
            } else {
                stringBuilder.append(tempBinaryCode.substring(0, length)); //截取补位
            }
        }
        return stringBuilder.toString();
    }
}
