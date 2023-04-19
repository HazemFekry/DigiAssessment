package com.assessment.permission;

import com.assessment.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionGroupService {

    PermissionGroupRepository permissionGroupRepository;
    public PermissionGroup getPermissionById(int permissionGroupId) {
        Optional<PermissionGroup> permissionGroup = permissionGroupRepository.findById(permissionGroupId);
        if (permissionGroup.isEmpty())
            throw new NotFoundException("Permission Group Id ["+permissionGroupId+ "] not found");
        return permissionGroup.get();
    }
}
