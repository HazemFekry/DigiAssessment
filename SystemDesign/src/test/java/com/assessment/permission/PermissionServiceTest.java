package com.assessment.permission;

import com.assessment.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PermissionServiceTest {

    @Mock
    PermissionRepository permissionRepository;

    @InjectMocks
    PermissionService permissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        permissionService = new PermissionService(permissionRepository);
    }

    @Test
    void testIsUserAuthorizedReturnsTrueWhenPermissionIsFound() {
        PermissionGroup permissionGroup = new PermissionGroup(1, "Group1");
        PermissionLevel permissionLevel = PermissionLevel.EDIT;
        Permission permission = Permission.builder()
                .id(1L).
                userEmail("user@example.com")
                .permissionGroup(permissionGroup)
                .permissionLevel(permissionLevel)
                .build();

        when(permissionRepository.findByUserEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(permission));

        assertTrue(permissionService.isUserAuthorized("user@example.com", permissionLevel, permissionGroup));

        verify(permissionRepository).findByUserEmail(ArgumentMatchers.anyString());
    }

    @Test
    void testIsUserAuthorizedThrowsExceptionWhenPermissionIsNotFound() {
        when(permissionRepository.findByUserEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                permissionService.isUserAuthorized("user@example.com", PermissionLevel.EDIT, new PermissionGroup(1, "Group1")));

        verify(permissionRepository).findByUserEmail(ArgumentMatchers.anyString());
    }
}
