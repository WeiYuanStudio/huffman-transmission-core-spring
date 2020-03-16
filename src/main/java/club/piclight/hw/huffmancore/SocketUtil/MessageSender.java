package club.piclight.hw.huffmancore.SocketUtil;

import club.piclight.hw.huffmancore.HuffmanUtil.DictBuilder;
import club.piclight.hw.huffmancore.HuffmanUtil.HuffmanBinaryMsgBuilder;
import club.piclight.hw.huffmancore.SocketUtil.Json.DictFormatter;
import club.piclight.hw.huffmancore.SocketUtil.Pack.MessagePacker;
import club.piclight.hw.huffmancore.SocketUtil.Pack.Type;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

public class MessageSender {
    private static Logger logger = Logger.getLogger(MessageSender.class.getName());

    private static final int REMOTE_PORT = 9999;
    private static final int DICT_HEAD = 17;

    private String ip;
    private String data; //待传送的原文
    private String hash;

    public MessageSender(String ip, String data) {
        this.ip = ip;
        this.data = data;
        this.hash = DigestUtils.sha1Hex(data);
    }

    public void send() throws IOException {
        /* Build dict */
        Map<Character, String> map = new DictBuilder().loadContent(data).build();

        map.forEach((k, v) -> { //Todo: Delete, for show dict
            logger.info(k + "->" + v);
        });


        /* Send dict */
        MessagePacker dictPacker = new MessagePacker(Type.DICT, hash, new DictFormatter(map).build());
        byte[] dictByte = dictPacker.pack();

        Socket socket = new Socket(ip, REMOTE_PORT);
        OutputStream out = socket.getOutputStream();
        out.write(dictByte);
        out.close();


        /* Build huffman code */
        String huffmanData = new HuffmanBinaryMsgBuilder(data, map).build(); //Message huffman 01 code

        logger.info("Huffman Message Code:" + huffmanData);


    }
}
