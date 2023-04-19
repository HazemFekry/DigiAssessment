package com.assessment.item;

import com.assessment.exception.NotFoundException;
import com.assessment.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;


    @Test
    public void givenValidSpaceNameAndPermissionGroupId_whenCreateSpace_thenReturnOK() throws Exception {
        String spaceName = "Test Space";
        int permissionGroupId = 1;

        doNothing().when(itemService).createSpace(anyString(), anyInt());

        mockMvc.perform(post("/api/v1/space")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", spaceName)
                        .param("permission_group_id", String.valueOf(permissionGroupId)))
                .andExpect(status().isOk());

        
        verify(itemService, times(1)).createSpace(eq(spaceName), eq(permissionGroupId));
        
    }

    @Test
    public void givenValidSpaceNameAndInvalidPermissionGroupId_whenCreateSpace_thenReturnNotFound() throws Exception {
        
        String spaceName = "Test Space";
        int permissionGroupId = 1;

        
        doThrow(NotFoundException.class).when(itemService).createSpace(anyString(), anyInt());

        mockMvc.perform(post("/api/v1/space")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", spaceName)
                        .param("permission_group_id", String.valueOf(permissionGroupId)))
                .andExpect(status().isNotFound());

        
        verify(itemService, times(1)).createSpace(eq(spaceName), eq(permissionGroupId));
        
    }

    @Test
    public void givenValidFolderNameAndSpaceId_whenCreateFolder_thenReturnOK() throws Exception {
        
        String folderName = "Test Folder";
        Long spaceId = 1L;
        String userEmail = "test@test.com";

        
        doNothing().when(itemService).createFolder(anyLong(), anyString(), anyString());

        mockMvc.perform(post("/api/v1/space/{spaceId}/folder", spaceId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("User-Email", userEmail)
                        .param("name", folderName))
                .andExpect(status().isOk());

        
        verify(itemService, times(1)).createFolder(eq(spaceId), eq(folderName), eq(userEmail));
        
    }

    @Test
    public void givenValidFolderNameAndInvalidSpaceId_whenCreateFolder_thenReturnNotFound() throws Exception {
        
        String folderName = "Test Folder";
        Long spaceId = 1L;
        String userEmail = "test@test.com";

        
        doThrow(NotFoundException.class).when(itemService).createFolder(anyLong(), anyString(), anyString());

        mockMvc.perform(post("/api/v1/space/{spaceId}/folder", spaceId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("User-Email", userEmail)
                        .param("name", folderName))
                .andExpect(status().isNotFound());

        
        verify(itemService, times(1)).createFolder(eq(spaceId), eq(folderName), eq(userEmail));
        
    }

    @Test
    public void givenValidFolderNameAndSpaceIdAndUnauthorizedUser_whenCreateFolder_thenReturnUnauthorized() throws Exception {
        
        String folderName = "Test Folder";
        Long spaceId = 1L;
        String userEmail = "test@test.com";

        
        doThrow(UnauthorizedException.class).when(itemService).createFolder(anyLong(), anyString(), anyString());

        mockMvc.perform(post("/api/v1/space/{spaceId}/folder", spaceId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("User-Email", userEmail)
                        .param("name", folderName))
                .andExpect(status().isUnauthorized());

        
        verify(itemService, times(1)).createFolder(eq(spaceId), eq(folderName), eq(userEmail));
        
    }

    @Test
    public void givenValidFileAndSpaceId_whenCreateFileWithSpaceParent_thenReturnOK() throws Exception {
        
        Long spaceId = 1L;
        String userEmail = "test@test.com";
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test File".getBytes());

        
        doNothing().when(itemService).createFile(anyLong(), any(), any(), anyString());

        mockMvc.perform(multipart("/api/v1/space/{spaceId}/file", spaceId)
                        .file(file)
                        .header("User-Email", userEmail))
                .andExpect(status().isOk());

        
        verify(itemService, times(1)).createFile(eq(spaceId), eq(file), eq(ItemType.SPACE), eq(userEmail));
        
    }

    @Test
    public void givenValidFileAndSpaceIdAndUnauthorizedUser_whenCreateFileWithSpaceParent_thenReturnUnauthorized() throws Exception {
        
        Long spaceId = 1L;
        String userEmail = "test@test.com";
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test File".getBytes());

        
        doThrow(UnauthorizedException.class).when(itemService).createFile(anyLong(), any(), any(), anyString());

        mockMvc.perform(multipart("/api/v1/space/{spaceId}/file", spaceId)
                        .file(file)
                        .header("User-Email", userEmail))
                .andExpect(status().isUnauthorized());

        
        verify(itemService, times(1)).createFile(eq(spaceId), eq(file), eq(ItemType.SPACE), eq(userEmail));

    }

    @Test
    public void givenValidFileAndFolderId_whenCreateFileWithFolderParent_thenReturnOK() throws Exception {
        
        Long folderId = 1L;
        String userEmail = "test@test.com";
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test File".getBytes());

        
        doNothing().when(itemService).createFile(anyLong(), any(), any(), anyString());

        mockMvc.perform(multipart("/api/v1/folder/{folderId}/file", folderId)
                        .file(file)
                        .header("User-Email", userEmail))
                .andExpect(status().isOk());

        
        verify(itemService, times(1)).createFile(eq(folderId), eq(file), eq(ItemType.FOLDER), eq(userEmail));
        
    }
}
