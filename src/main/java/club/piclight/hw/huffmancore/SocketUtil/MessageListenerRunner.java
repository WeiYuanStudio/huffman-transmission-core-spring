package club.piclight.hw.huffmancore.SocketUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Socket监听线程启动类
 */
@Component
public class MessageListenerRunner implements CommandLineRunner {
    @Autowired
    MessageListener listener;

    @Override
    public void run(String... args) throws Exception {
        listener.start();
    }
}
