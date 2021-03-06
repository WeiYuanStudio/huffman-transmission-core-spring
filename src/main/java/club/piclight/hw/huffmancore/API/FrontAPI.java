package club.piclight.hw.huffmancore.API;

import club.piclight.hw.huffmancore.DB.HuffmanDataRepository;
import club.piclight.hw.huffmancore.DB.HuffmanDictRepository;
import club.piclight.hw.huffmancore.DB.MessageRepository;
import club.piclight.hw.huffmancore.HuffmanUtil.HuffmanBinaryMsgDecoder;
import club.piclight.hw.huffmancore.Model.HuffmanData;
import club.piclight.hw.huffmancore.Model.HuffmanDict;
import club.piclight.hw.huffmancore.Model.Message;
import club.piclight.hw.huffmancore.SocketUtil.Json.DictDecoder;
import club.piclight.hw.huffmancore.SocketUtil.MessageSender;
import club.piclight.hw.huffmancore.ViewModel.Request.SendMessageRequestModel;
import club.piclight.hw.huffmancore.ViewModel.Response.Plain;
import club.piclight.hw.huffmancore.ViewModel.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 前端API
 */
@RestController
@RequestMapping(value = "/huffman_api")
public class FrontAPI {
    Logger logger = Logger.getLogger(FrontAPI.class.getName());

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private HuffmanDictRepository huffmanDictRepository;

    @Autowired
    private HuffmanDataRepository huffmanDataRepository;

    /**
     * 列出所有表内消息
     *
     * @return 表内所有消息
     */
    @GetMapping("/message_list")
    public List<Message> ListMessage() {
        return messageRepository.findAll();
    }

    /**
     * 获取消息字典
     *
     * @return 消息字典json
     */
    @GetMapping("/dict/{hash}")
    public String GetDict(@PathVariable("hash") String hash) {
        HuffmanDict dict = huffmanDictRepository.getAllByHash(hash);
        return dict.getDict();
    }

    /**
     * 获取消息哈夫曼二进制码
     *
     * @param hash 消息哈希
     * @return 消息二进制哈夫曼编码
     */
    @GetMapping("/code/{hash}")
    public Plain GetHuffmanCode(@PathVariable("hash") String hash) {
        HuffmanData data = huffmanDataRepository.getAllByHash(hash);
        return new Plain(200, data.getBinaryCode());
    }

    /**
     * 获取Message消息
     *
     * @param hash 消息哈希
     * @return 消息原码
     */
    @GetMapping("/message/{hash}")
    public Plain GetMessage(@PathVariable("hash") String hash) {
        String jsonDict = huffmanDictRepository.getAllByHash(hash).getDict(); //String json dict from database
        String binaryCode = huffmanDataRepository.getAllByHash(hash).getBinaryCode(); //String binary code from database
        DictDecoder dictDecoder = new DictDecoder(jsonDict); //gson load json dict
        Map<Character, String> dictMap =  dictDecoder.decode().getMapDict(); //get map dict
        HuffmanBinaryMsgDecoder msgDecoder = new HuffmanBinaryMsgDecoder(dictMap, binaryCode);
        return new Plain(200, msgDecoder.decode());
    }

    /**
     * 发送消息接口，POST JSON
     */
    @PostMapping("/send")
    public Status SendMessage(@RequestBody SendMessageRequestModel requestModel) {
        logger.info("" + requestModel.getIp());
        logger.info("Message" + requestModel.getData());
        MessageSender sender = new MessageSender(requestModel.getIp(), requestModel.getData());
        try {
            sender.send();
        } catch (IOException e) {
            e.printStackTrace();
            return new Status(500, "Message send failed");
        }
        return new Status(200, "Message send success");
    }
}
