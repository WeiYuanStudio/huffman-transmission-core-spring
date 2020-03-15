package club.piclight.hw.huffmancore.API;

import club.piclight.hw.huffmancore.DB.MessageRepository;
import club.piclight.hw.huffmancore.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前端API
 */
@RestController
@RequestMapping(value = "/huffman_api")
public class FrontAPI {
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
}
