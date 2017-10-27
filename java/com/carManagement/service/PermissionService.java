package com.carManagement.service;

public interface PermissionService {

    String getCurrentUserId();

    boolean checkApiAccess();

}
