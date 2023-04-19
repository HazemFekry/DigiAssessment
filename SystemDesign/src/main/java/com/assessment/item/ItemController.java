package com.assessment.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequestMapping("api/v1")
@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService spaceService) {
        this.itemService = spaceService;
    }

    @PostMapping("space")
    public ResponseEntity createSpace(@RequestParam("name") String spaceName, @RequestParam("permission_group_id") int permissionGroupId) {
        itemService.createSpace(spaceName, permissionGroupId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("space/{spaceId}/folder")
    public void createFolder(@PathVariable("spaceId") Long parentId, @RequestParam("name") String folderName
            , @RequestHeader("User-Email") String userEmail) {
        itemService.createFolder(parentId, folderName, userEmail);
    }

    @PostMapping("space/{spaceId}/file")
    public void createFileWithSpaceParent(@PathVariable("spaceId") Long spaceId, @RequestParam("file") MultipartFile file
            , @RequestHeader("User-Email") String userEmail) throws IOException {
        itemService.createFile(spaceId, file, ItemType.SPACE, userEmail);
    }

    @PostMapping("folder/{folderId}/file")
    public void createFileWithFolderParent(@PathVariable("folderId") Long folderId, @RequestParam("file") MultipartFile file
            , @RequestHeader("User-Email") String userEmail) throws IOException {
        itemService.createFile(folderId, file, ItemType.FOLDER, userEmail);
    }

}
