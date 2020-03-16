package club.piclight.hw.huffmancore.SocketUtil.Json;

import com.google.gson.Gson;

import java.util.Map;

public class DictFormatter {
    private Map<Character, String> charDict;

    public DictFormatter(Map<Character, String> charDict) {
        this.charDict = charDict;
    }

    /**
     * 词典格式化
     * @return
     */
    public String build() {
        DictModel dataModel = new DictModel();
        dataModel.addDict(charDict);
        Gson gson = new Gson();
        return gson.toJson(dataModel, DictModel.class);
    }
}
