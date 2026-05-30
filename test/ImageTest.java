import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class ImageTest {
    @Test
    public void loadImageTest(){
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/GoodTank1.png"))).getImage();
        assertNotNull(image);
    }
}
