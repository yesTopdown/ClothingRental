package com.example.clothingrental.Clothing.common;

/**
 *保存管理员的用户Id
 */
public class AdminContext {
    private static Long adminId;
    private static Long userId;
//    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();
    public static void setAdminId(Long id){
        adminId=id;
    }
    public static Long getAdminId(){
        return adminId;
    }
    public static void setUserId(Long id){
        userId=id;
    }
    public static Long getUserId(){
        return userId;
    }
}
