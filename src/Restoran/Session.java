/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restoran;

/**
 *
 * @author uhsis
 */
public class Session {
    private static int s_iduser;
    private static String s_namauser;
    private static String s_password;
    
    public static int getS_iduser(){
        return s_iduser;
    }
    
    public static void setS_iduser(int s_iduser){
        Session.s_iduser = s_iduser;
    }
    
    public static String getS_namauser(){
        return s_namauser;
    }
    
    public static void setS_namauser(String s_namauser){
        Session.s_namauser = s_namauser;
    }
    
    public static String getS_password(){
        return s_password;
    }
    
    public static void setS_pasword(String s_password){
        Session.s_password = s_password;
    }
}
