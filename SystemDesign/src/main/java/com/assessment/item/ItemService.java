package com.assessment.item;

import com.assessment.exception.NotFoundException;
import com.assessment.exception.UnauthorizedException;
import com.assessment.file.FileService;
import com.assessment.permission.PermissionGroup;
import com.assessment.permission.PermissionGroupService;
import com.assessment.permission.PermissionLevel;
import com.assessment.permission.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemService {

    ItemRepository itemRepository;
    PermissionGroupService permissionGroupService;
    PermissionService permissionService;

    FileService fileService;

    public void createSpace(String spaceName, int permissionGroupId) {
        PermissionGroup permissionGroup = permissionGroupService.getPermissionById(permissionGroupId);

        Item spaceitem = Item.builder()
                .name(spaceName)
                .permissionGroup(permissionGroup)
                .type(ItemType.SPACE)
                .build();

        itemRepository.save(spaceitem);
    }

    public void createFolder(Long parentId, String folderName, String userEmail) {
        Item space = findItemByIdAndItemType(parentId, ItemType.SPACE);

        checkUserAuthorization(userEmail, PermissionLevel.EDIT, space);

        Item folder = Item.builder()
                .name(folderName)
                .type(ItemType.FOLDER)
                .parentItem(space)
                .permissionGroup(space.getPermissionGroup())
                .build();

        itemRepository.save(folder);
    }


    public void createFile(Long parentId, MultipartFile file, ItemType parentType, String userEmail) throws IOException {
        Item parent = findItemByIdAndItemType(parentId, parentType);

        checkUserAuthorization(userEmail, PermissionLevel.EDIT, parent);
        Item folder = Item.builder()
                .name(file.getOriginalFilename())
                .type(ItemType.FILE)
                .parentItem(parent)
                .permissionGroup(parent.getPermissionGroup())
                .build();

        Item item = itemRepository.saveAndFlush(folder);

        fileService.saveFile(file.getBytes(), item);

    }


    private Item findItemByIdAndItemType(Long itemId, ItemType itemType) {
        Optional<Item> item = itemRepository.findByIdAndType(itemId, itemType);
        if (item.isEmpty())
            throw new NotFoundException("Item [" + itemId + "] with type [" + itemType + "] not found");
        return item.get();
    }

    private void checkUserAuthorization(String userEmail, PermissionLevel permissionLevel, Item parent)  {
        boolean authorized = permissionService.isUserAuthorized(userEmail, permissionLevel, parent.getPermissionGroup());
        if (!authorized) {
            throw new UnauthorizedException("User [" + userEmail + "] don't have permission to do this action");
        }
    }
}
