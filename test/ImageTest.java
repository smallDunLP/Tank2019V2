import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ImageTest {

    @Test
    public void testImage() throws IOException {
        BufferedImage image = ImageIO.read(new File("C:\\Users\\25182\\IdeaProjects\\Tank2019V2\\src\\images\\bulletL.gif"));
        assertNotNull(image);


        

    }


}
