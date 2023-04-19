package com.assessment.item;


import com.assessment.exception.NotFoundException;
import com.assessment.exception.UnauthorizedException;
import com.assessment.file.FileService;
import com.assessment.permission.PermissionGroup;
import com.assessment.permission.PermissionGroupService;
import com.assessment.permission.PermissionLevel;
import com.assessment.permission.PermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private PermissionGroupService permissionGroupService;
    @Mock
    private PermissionService permissionService;
    @Mock
    private FileService fileService;

    @InjectMocks
    private ItemService itemService;

    private final Long parentId = 1L;
    private final String folderName = "testFolder";
    private final String userEmail = "test@test.com";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository, permissionGroupService, permissionService, fileService);
    }

    @Test
    void createSpace_shouldSaveSpaceItem() {

        String spaceName = "testSpace";
        int permissionGroupId = 1;
        PermissionGroup permissionGroup = PermissionGroup.builder().id(permissionGroupId).build();
        Item spaceItem = Item.builder().name(spaceName).permissionGroup(permissionGroup).type(ItemType.SPACE).build();

        when(permissionGroupService.getPermissionById(permissionGroupId)).thenReturn(permissionGroup);
        when(itemRepository.save(spaceItem)).thenReturn(spaceItem);


        itemService.createSpace(spaceName, permissionGroupId);


        verify(permissionGroupService).getPermissionById(permissionGroupId);
        verify(itemRepository).save(spaceItem);
    }

    @Test
    void createFolder_shouldSaveFolderItem() {

        PermissionGroup permissionGroup = PermissionGroup.builder().id(1).build();
        Item space = Item.builder().id(parentId).type(ItemType.SPACE).permissionGroup(permissionGroup).build();

        when(itemRepository.findByIdAndType(parentId, ItemType.SPACE)).thenReturn(Optional.of(space));
        when(permissionService.isUserAuthorized(userEmail, PermissionLevel.EDIT, permissionGroup)).thenReturn(true);

        Item folder = Item.builder().name(folderName).type(ItemType.FOLDER).parentItem(space).permissionGroup(permissionGroup).build();
        when(itemRepository.save(folder)).thenReturn(folder);


        itemService.createFolder(parentId, folderName, userEmail);


        verify(itemRepository).findByIdAndType(parentId, ItemType.SPACE);
        verify(permissionService).isUserAuthorized(userEmail, PermissionLevel.EDIT, permissionGroup);
        verify(itemRepository).save(folder);
    }

    @Test
    void testCreateFolderWithAuthorizedUser() {
        String folderName = "MyFolder";
        Item space = Item.builder()
                .id(parentId)
                .name("MySpace")
                .type(ItemType.SPACE)
                .permissionGroup(PermissionGroup.builder().build())
                .build();
        when(itemRepository.findByIdAndType(parentId, ItemType.SPACE)).thenReturn(java.util.Optional.of(space));
        when(permissionService.isUserAuthorized(userEmail, PermissionLevel.EDIT, space.getPermissionGroup())).thenReturn(true);

        itemService.createFolder(parentId, folderName, userEmail);

        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void testCreateFolderWithUnauthorizedUser() {

        Item space = Item.builder()
                .id(parentId)
                .name("MySpace")
                .type(ItemType.SPACE)
                .permissionGroup(PermissionGroup.builder().build())
                .build();
        when(itemRepository.findByIdAndType(parentId, ItemType.SPACE)).thenReturn(java.util.Optional.of(space));
        when(permissionService.isUserAuthorized(userEmail, PermissionLevel.EDIT, space.getPermissionGroup())).thenReturn(false);


        assertThrows(UnauthorizedException.class, () -> itemService.createFolder(parentId, folderName, userEmail));
    }


    @Test
    public void testCreateFile() throws IOException {

        byte[] bytes = "Hello World!".getBytes();
        MultipartFile file = mock(MultipartFile.class);
        ItemType parentType = ItemType.SPACE;
        Item parent = new Item();
        Item item = new Item();
        parent.setType(ItemType.SPACE);
        parent.setPermissionGroup(new PermissionGroup());
        when(itemRepository.findByIdAndType(eq(parentId), eq(parentType))).thenReturn(Optional.of(parent));
        when(permissionService.isUserAuthorized(eq(userEmail), eq(PermissionLevel.EDIT), any(PermissionGroup.class))).thenReturn(true);
        when(itemRepository.saveAndFlush(any(Item.class))).thenReturn(item);
        when(file.getBytes()).thenReturn(bytes);


        itemService.createFile(parentId, file, parentType, userEmail);


        verify(itemRepository, times(1)).saveAndFlush(any(Item.class));
        verify(fileService, times(1)).saveFile(eq(bytes), eq(item));
    }

    @Test
    public void testCreateFileWithNotFoundParent(){

        MultipartFile file = mock(MultipartFile.class);
        ItemType parentType = ItemType.SPACE;
        when(itemRepository.findByIdAndType(eq(parentId), eq(parentType))).thenReturn(Optional.empty());

        NotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                NotFoundException.class, () -> itemService.createFile(parentId, file, parentType, userEmail));
        org.junit.jupiter.api.Assertions.assertEquals(
                "Item [" + parentId + "] with type [" + parentType + "] not found", exception.getMessage());
        verify(itemRepository, never()).saveAndFlush(any(Item.class));
        verify(fileService, never()).saveFile(any(byte[].class), any(Item.class));
    }

    @Test
    public void testCreateFileWithUnauthorizedUser() {

        MultipartFile file = mock(MultipartFile.class);
        ItemType parentType = ItemType.SPACE;
        Item parent = new Item();
        parent.setType(ItemType.SPACE);
        parent.setPermissionGroup(new PermissionGroup());
        when(itemRepository.findByIdAndType(eq(parentId), eq(parentType))).thenReturn(Optional.of(parent));
        when(permissionService.isUserAuthorized(eq(userEmail), eq(PermissionLevel.EDIT), any(PermissionGroup.class))).thenReturn(false);

        UnauthorizedException exception = org.junit.jupiter.api.Assertions.assertThrows(
                UnauthorizedException.class, () -> itemService.createFile(parentId, file, parentType, userEmail));
        org.junit.jupiter.api.Assertions.assertEquals(
                "User [" + userEmail + "] don't have permission to do this action", exception.getMessage());
        verify(itemRepository, never()).saveAndFlush(any(Item.class));
        verify(fileService, never()).saveFile(any(byte[].class), any(Item.class));
    }

}