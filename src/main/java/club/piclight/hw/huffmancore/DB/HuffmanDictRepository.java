package club.piclight.hw.huffmancore.DB;

import club.piclight.hw.huffmancore.Model.HuffmanDict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuffmanDictRepository extends JpaRepository<HuffmanDict, Long> {
    HuffmanDict getAllByHash(String hash);
}
