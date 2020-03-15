package club.piclight.hw.huffmancore.DB;

import club.piclight.hw.huffmancore.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Message Table
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAll();
}
