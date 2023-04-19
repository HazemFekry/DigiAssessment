package com.assessment.permission;

import com.assessment.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PermissionGroupServiceTest {

    @Mock
    private PermissionGroupRepository permissionGroupRepository;

    @InjectMocks
    private PermissionGroupService permissionGroupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        permissionGroupService = new PermissionGroupService(permissionGroupRepository);
    }
    @Test
    public void testGetPermissionById_Success() {

        int permissionGroupId = 1;
        PermissionGroup expectedPermissionGroup = new PermissionGroup(permissionGroupId, "Admin");

        when(permissionGroupRepository.findById(permissionGroupId))
                .thenReturn(Optional.of(expectedPermissionGroup));


        PermissionGroup result = permissionGroupService.getPermissionById(permissionGroupId);

        assertEquals(expectedPermissionGroup, result);
    }

    @Test
    public void testGetPermissionById_NotFound() {

        int permissionGroupId = 1;

        when(permissionGroupRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> permissionGroupService.getPermissionById(permissionGroupId));
    }
}
