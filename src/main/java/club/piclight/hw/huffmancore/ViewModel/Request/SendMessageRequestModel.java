package club.piclight.hw.huffmancore.ViewModel.Request;

import lombok.Data;

/**
 * 发送消息请求接口的VM
 */
@Data
public class SendMessageRequestModel {
    private String ip;
    private String author;
    private String title;
    private String data;
}
