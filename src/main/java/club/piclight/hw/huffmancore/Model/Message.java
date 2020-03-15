package club.piclight.hw.huffmancore.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 消息 DBModel，用于存放消息
 */
@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String hash;

    private String title;

    private String author;

    private Date date;
}
