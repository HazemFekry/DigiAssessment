package com.assessment.file;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.assessment.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileServiceTest {


    private FileRepository fileRepositoryMock;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileRepositoryMock = mock(FileRepository.class);
        fileService = new FileService(fileRepositoryMock);
    }

    @Test
    void saveFile_shouldSaveFile() {
        byte[] bytes = "Hello World!".getBytes();
        Item item = new Item();

        fileService.saveFile(bytes, item);

        verify(fileRepositoryMock).save(argThat(file->file.getBinary().equals(bytes)));
    }

}
