package club.piclight.hw.huffmancore.API;

import club.piclight.hw.huffmancore.DB.MessageRepository;
import club.piclight.hw.huffmancore.Model.Message;
import club.piclight.hw.huffmancore.ViewModel.Request.SendMessageRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    /**
     * 列出所有表内消息
     * @return 表内所有消息
     */
    @GetMapping("/message_list")
    public List<Message> ListMessage() {
        return messageRepository.findAll();
    }

    @PostMapping("/send")
    public void SendMessage(@RequestBody SendMessageRequestModel requestModel) {
        logger.info("IP" + requestModel.getIp());
        logger.info("Message" + requestModel.getData());
    }
}
