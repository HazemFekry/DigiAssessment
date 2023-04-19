package com.assessment.permission;

import com.assessment.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionService {
    PermissionRepository permissionRepository;

    public boolean isUserAuthorized(String userEmail, PermissionLevel permissionLevel, PermissionGroup permissionGroup) {

        Permission permission = getPermissionByUserEmail(userEmail);

        if (permission.getPermissionGroup().getId() == permissionGroup.getId()
                && permission.getPermissionLevel().hasPermission(permissionLevel))
            return true;

        return false;
    }

    private Permission getPermissionByUserEmail(String userEmail){
        Optional<Permission> permission = permissionRepository.findByUserEmail(userEmail);
        if (permission.isEmpty()) {
            throw new NotFoundException("User Email ["+userEmail+"] not found");
        }
        return permission.get();
    }
}
