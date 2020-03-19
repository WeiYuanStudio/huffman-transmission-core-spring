package club.piclight.hw.huffmancore.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class HuffmanData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String hash;

    @Column(nullable = false)
    private String binaryCode; //数据前要加下划线防止JPA框架将01序列转成浮点型
}
