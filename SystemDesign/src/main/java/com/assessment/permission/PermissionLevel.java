package com.assessment.permission;

public enum PermissionLevel {
    EDIT, VIEW;

    public boolean hasPermission(PermissionLevel permissionLevel){
        if(this.equals(EDIT)){
            return true;
        }else if(permissionLevel.equals(VIEW)){
            return true;
        }
        else
            return false;
    }


}
