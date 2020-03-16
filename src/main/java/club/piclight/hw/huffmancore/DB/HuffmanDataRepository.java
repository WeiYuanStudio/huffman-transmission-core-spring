package club.piclight.hw.huffmancore.DB;

import club.piclight.hw.huffmancore.Model.HuffmanData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Huffman Binary Code Database
 */
public interface HuffmanDataRepository extends JpaRepository<HuffmanData, Long> {
    HuffmanData getAllByHash(String hash);
}
