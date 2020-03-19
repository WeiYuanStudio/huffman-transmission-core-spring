package club.piclight.hw.huffmancore.ViewModel.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Format text/plain for axios
 */
@AllArgsConstructor
@Data
public class Plain {
    private int code;
    private String text;
}
