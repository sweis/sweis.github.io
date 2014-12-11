/**
* Clinkle's AESObfuscator
*
* This class is derived from the Clinkle Android app Java classes. It was originally under
* package com.clinkle.lvl. It is *not* original source code and the interface has been 
* slightly modified for readability and understandability. Notably, callers would pass in a
* salt, package name, and Android ID. These values were fixed in the source as far as I can tell.
*
* The Java crypto usage has been left intact. They are using CBC with a fixed IV value and no
* authentication. Keys appear to be derived from the Android ID, which is visible to all apps.
*
* Note, this has not been compiled, may contain bugs I've introduced, and will require more refactoring to
* actually build.
*
* Here are the tools used to generate the source code:
* 1. Downloaded APK from Google Play on 12/10/14 via http://apps.evozi.com/apk-downloader/?id=com.clinkle
* 2. Unziped APK and converted classes.dex to a JAR with: https://code.google.com/p/dex2jar/
* 3. Decompiled JAR with jd-gui: http://jd.benow.ca/
* 4. Manually refactored code and renamed jd-gui's generated variables.
*/

import android.content.Context;
import android.provider.Settings.Secure;

import com.clinkle.lvl.util.Base64;
import com.clinkle.lvl.util.Base64DecoderException;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESObfuscator implements Obfuscator {
  private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
  private static final byte[] IV =
	  { 16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74 };
  private static final byte[] SALT =
	  { 100, 34, 43, -5, -41, 20, 9, 0, -48, -52, -67, 15, 118, 107, -74, -80, 119, -5, 11, 10 };
  private static final String KEYGEN_ALGORITHM ="PBEWITHSHAAND256BITAES-CBC-BC";
  private static final String UTF8 = "UTF-8";
  private static final String HEADER = "com.clinkle.lvl.AESObfuscator-1|";
  private static final int ITERATION_COUNT = 1024;
  private static final int KEY_LENGTH = 256;
  private Cipher decryptor;
  private Cipher encryptor;
  
    public AESObfuscator(Context paramContext) {
    try {
    	String packageName = paramContext.getPackageName();
    	String androidId = Settings.Secure.getString(
    			paramContext.getContentResolver(), "android_id");
    	String password = packageName + androidId;
    	PBEKeySpec pbeKeySpec = new PBEKeySpec(
    			password.toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
    	SecretKeySpec secretKeySpec = new SecretKeySpec(
    		  SecretKeyFactory.getInstance(KEYGEN_ALGORITHM)
    				  .generateSecret(pbeKeySpec).getEncoded(), "AES");
      this.encryptor = Cipher.getInstance(CIPHER_ALGORITHM);
      this.encryptor.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));
      this.decryptor = Cipher.getInstance(CIPHER_ALGORITHM);
      this.decryptor.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));
      return;
    } catch (GeneralSecurityException gse) {
      throw new RuntimeException("Invalid environment", gse);
    }
  }

  public String obfuscate(String propertyValue, String propertyKey) {
    if (propertyValue == null)
      return null;
    
    try {
      String str = Base64.encode(
    		  this.encryptor.doFinal((HEADER + propertyKey + propertyValue).getBytes(UTF8)));
      return str;
    } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
      throw new RuntimeException("Invalid environment", localUnsupportedEncodingException);
    } catch (GeneralSecurityException localGeneralSecurityException) {
      throw new RuntimeException("Invalid environment", localGeneralSecurityException);
    }
  }

  public String unobfuscate(String base64encoded, String propertyKey) throws ValidationException {
    if (base64encoded == null)
      return null;
    
    try {
      String decoded = new String(this.decryptor.doFinal(Base64.decode(base64encoded)), UTF8);
      if (decoded.indexOf(HEADER + propertyKey) != 0)
        throw new ValidationException("Header not found (invalid data or key):" + base64encoded);
      String propertyValue = decoded.substring(
    		  HEADER.length() + propertyKey.length(), decoded.length());
      return propertyValue;
    }
    catch (Base64DecoderException localBase64DecoderException) {
      throw new ValidationException(localBase64DecoderException.getMessage() + ":" + base64encoded);
    } catch (IllegalBlockSizeException localIllegalBlockSizeException) {
      throw new ValidationException(localIllegalBlockSizeException.getMessage() + ":" + base64encoded);
    } catch (BadPaddingException localBadPaddingException) {
      throw new ValidationException(localBadPaddingException.getMessage() + ":" + base64encoded);
    } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
      throw new RuntimeException("Invalid environment", localUnsupportedEncodingException);
    }
  }
}
