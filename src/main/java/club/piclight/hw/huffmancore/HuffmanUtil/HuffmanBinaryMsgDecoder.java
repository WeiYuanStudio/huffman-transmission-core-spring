package club.piclight.hw.huffmancore.HuffmanUtil;

import java.util.HashMap;
import java.util.Map;

public class HuffmanBinaryMsgDecoder {
    private Map<String, Character> reverseCharDict; //K:01哈夫曼，V:字符 反转字典
    private String binaryContent; //String格式哈夫曼01代码
    private StringBuilder messageContent = new StringBuilder(); //存放解码完毕的消息

    public HuffmanBinaryMsgDecoder(Map<Character, String> charDict, String binaryContent) {
        this.reverseCharDict = new HashMap<>();
        charDict.forEach((key, value) -> this.reverseCharDict.put(value, key)); //哈夫曼字典键值反转
        this.binaryContent = binaryContent;
    }

    /**
     * 通过反转后的字典将String格式哈夫曼01代码解码
     *
     * @return 返回解码后字符串
     */
    public String decode() {
        StringBuilder huffmanBuffer = new StringBuilder(); //用于暂时存放哈夫曼01代码
        binaryContent.chars().forEach(_char -> {
            huffmanBuffer.append((char) _char); //逐位添加哈夫曼01代码
            if (reverseCharDict.get(huffmanBuffer.toString()) != null) { //是否找到了哈夫曼01代码对应的字符
                messageContent.append(reverseCharDict.get(huffmanBuffer.toString())); //将解码出的字符存放
                huffmanBuffer.delete(0, huffmanBuffer.length()); //清空二进制编码暂存
            }
        });
        return messageContent.toString();
    }
}
