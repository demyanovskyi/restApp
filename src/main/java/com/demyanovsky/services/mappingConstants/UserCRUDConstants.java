package com.demyanovsky.services.mappingConstants;

public class UserCRUDConstants {
    public static final String GET_ALL_USERS = "/user/";
    public static final String GET_USER = "/user/{id}";
    public static final String CREATE_USER = "/user";
    public static final String CREATE_ADMIN = "/admin";
    public static final String DELETE_USER = "/user/{id}";
    public static final String UPDATE_USER = "/user/{id}";
    public static final String PASSWORD_RESTORE = "/user/password/restore";
    public static final String CONFIRMATION_PASSWORD_RESTORE = "/user/password/restore/{hash}";
}
