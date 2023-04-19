package com.assessment.file;

import com.assessment.item.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileService {
    FileRepository fileRepository;

    public void saveFile(byte[] bytes, Item item) {
        //Todo: check if there is a file with the same name under the same parent
        File file = File.builder().binary(bytes).item(item).build();
        fileRepository.save(file);
    }
}
