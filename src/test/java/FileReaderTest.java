import org.example.entity.Status;
import org.example.service.impl.FileReaderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FileReaderTest {
    @InjectMocks
    private FileReaderImpl fileReader;

    @Test
    public void testReadStatuses() {
        // given
        // when
        HashMap<Integer, Status> result = fileReader.readStatuses();
        // then
        assertNotNull(result);
        assertEquals(4, result.size());
        assertTrue(result.containsKey(1));
        assertTrue(result.containsKey(2));
        assertTrue(result.containsKey(3));
        assertTrue(result.containsKey(4));
    }
}
