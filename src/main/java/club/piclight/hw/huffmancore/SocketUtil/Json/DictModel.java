package club.piclight.hw.huffmancore.SocketUtil.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictModel {
    private List<CodeSet> codeList;

    public void addDict(Map<Character, String> charDict) {
        codeList = new ArrayList<>();
        charDict.forEach((key, value) -> codeList.add(new CodeSet(key, value)));
    }

    public Map<Character, String> getMapDict() {
        Map<Character, String> map = new HashMap<>();
        codeList.forEach(codeSet -> {
            map.put(codeSet.getCharacter(), codeSet.getHuffmanCode());
        });
        return map;
    }

    /**
     * 单个词典对
     */
    class CodeSet {
        private char character;
        private String huffmanCode;

        public CodeSet(char character, String huffmanCode) {
            this.character = character;
            this.huffmanCode = huffmanCode;
        }

        public void setCharacter(char character) {
            this.character = character;
        }

        public void setHuffmanCode(String huffmanCode) {
            this.huffmanCode = huffmanCode;
        }

        public char getCharacter() {
            return character;
        }

        public String getHuffmanCode() {
            return huffmanCode;
        }
    }
}
