package club.piclight.hw.huffmancore.HuffmanUtil;

import java.util.Map;

/**
 * 通过原文以及哈希编码字典，编码出String格式的01哈夫曼编码
 */
public class HuffmanBinaryMsgBuilder {
    private String content; //未编码原文
    private Map<Character, String> charDict; //哈夫曼编码字典

    public HuffmanBinaryMsgBuilder(String content, Map<Character, String> charDict) {
        this.content = content;
        this.charDict = charDict;
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        content.chars().forEach(_char -> stringBuilder.append(charDict.get((char) _char)));

        return stringBuilder.toString();
    }
}
