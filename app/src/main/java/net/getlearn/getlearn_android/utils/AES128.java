package net.getlearn.getlearn_android.utils;

import android.annotation.SuppressLint;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// AES加密解密工具类
public class AES128
{
    private static final String UTF_8 = "UTF-8";

    @SuppressLint("TrulyRandom")
    public static String encrypt(String key, String src)
    {
        try
        {
            if(key == null || key.length() <= 0 || src == null || src.length() <= 0) return null;

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = src.getBytes(UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, skey);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return byte2hex(result).toLowerCase(); // 加密
        }
        catch (Exception ext)
        {
            ext.printStackTrace();
        }
        return null;
    }

    //解密
    public static String Decrypt(String src, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
                return null;
            }

            // 密钥补位
            int plus= 16-key.length();
            byte[] data = key.getBytes("utf-8");
            byte[] raw = new byte[16];
            byte[] plusbyte={ 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for(int i=0;i<16;i++)
            {
                if (data.length > i)
                    raw[i] = data[i];
                else
                    raw[i] = plusbyte[plus];
            }

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            //byte[] encrypted1 = new Base64().decode(src);//base64
            byte[] encrypted1 = toByteArray(src);//十六进制

            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (hexString.isEmpty())
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }


    //解密 二进制
    public static byte[] DecryptByte(byte[] src, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
            }

            // 密钥补位
            int plus= 16-key.length();
            byte[] data = key.getBytes("utf-8");
            byte[] raw = new byte[16];
            byte[] plusbyte={ 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for(int i=0;i<16;i++)
            {
                if (data.length > i)
                    raw[i] = data[i];
                else
                    raw[i] = plusbyte[plus];
            }

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            //byte[] encrypted1 = new Base64().decode(src);//base64
            byte[] encrypted1 = src;//十六进制

            try {
                byte[] original = cipher.doFinal(encrypted1);
                return original;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b
     *            byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static final String byte2hex(byte b[]) {
        if (b == null) {
            throw new IllegalArgumentException(
                    "Argument b ( byte array ) is null! ");
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xff);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }


    private static  String byte2Base64(byte[] encode)
    {
        try
        {
            return Base64.encodeToString(encode, Base64.NO_WRAP);

        }
        catch (Exception ext)
        {
            ext.printStackTrace();
        }

        return null;
    }


}
