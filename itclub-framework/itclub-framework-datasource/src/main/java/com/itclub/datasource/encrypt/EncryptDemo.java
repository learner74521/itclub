package com.itclub.datasource.encrypt;

import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;

/**
 * 数据库加密
 *
 * @author: onePiece
 * @date: 2023-02-24
 */
public class EncryptDemo {

    /**
     * 加密
     * @param args
     * @throws Exception
     */
    //自定义publicKey
    public static void main(String[] args) throws Exception {
        String[] arr = CryptoUtils.genKeyPair(512);
        System.out.println("privateKey:  " + arr[0]);
        System.out.println("publicKey:  " + arr[1]);
        System.out.println("url:  " + CryptoUtils.encrypt(arr[0], "jdbc:mysql://127.0.0.1:3306/itclub2"));
        System.out.println("username:  " + CryptoUtils.encrypt(arr[0], "root"));
        System.out.println("password:  " + CryptoUtils.encrypt(arr[0], "123456"));

    }
}
