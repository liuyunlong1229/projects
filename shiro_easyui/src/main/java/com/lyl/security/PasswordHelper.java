package com.lyl.security;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import com.lyl.system.entity.SysUser;


@Service
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void  encryptPassword(SysUser user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName,user.getPassword(),
        						ByteSource.Util.bytes(user.getSalt()),
        						hashIterations).toHex();
        user.setPassword(newPassword);
    }
    
    public static void main(String[] args) {
    	byte [] b=org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
    	for (int i = 0; i < b.length; i++) {
    		System.out.println((char)b[i]);
		}
    	
    }
}
