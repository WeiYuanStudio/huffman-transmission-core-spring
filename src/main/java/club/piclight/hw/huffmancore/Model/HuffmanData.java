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
    private String binaryCode;
}
