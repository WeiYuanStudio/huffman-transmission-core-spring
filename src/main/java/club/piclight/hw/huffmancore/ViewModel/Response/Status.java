package club.piclight.hw.huffmancore.ViewModel.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Status Response View Model
 */
@AllArgsConstructor
@Data
public class Status {
    private int code;
    private String info;
}
