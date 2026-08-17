package org.fusesource.jansi.internal;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class OSInfo {
    public static final String ARM64 = "arm64";
    public static final String IA64 = "ia64";
    public static final String IA64_32 = "ia64_32";
    public static final String PPC = "ppc";
    public static final String PPC64 = "ppc64";
    public static final String X86 = "x86";
    public static final String X86_64 = "x86_64";
    private static final HashMap<String, String> archMapping;

    static {
        HashMap<String, String> map = new HashMap<>();
        archMapping = map;
        map.put(X86, X86);
        map.put("i386", X86);
        map.put("i486", X86);
        map.put("i586", X86);
        map.put("i686", X86);
        map.put("pentium", X86);
        map.put(X86_64, X86_64);
        map.put("amd64", X86_64);
        map.put("em64t", X86_64);
        map.put("universal", X86_64);
        map.put(IA64, IA64);
        map.put("ia64w", IA64);
        map.put(IA64_32, IA64_32);
        map.put("ia64n", IA64_32);
        map.put(PPC, PPC);
        map.put("power", PPC);
        map.put("powerpc", PPC);
        map.put("power_pc", PPC);
        map.put("power_rs", PPC);
        map.put(PPC64, PPC64);
        map.put("power64", PPC64);
        map.put("powerpc64", PPC64);
        map.put("power_pc64", PPC64);
        map.put("power_rs64", PPC64);
        map.put("aarch64", ARM64);
    }

    public static String getArchName() {
        String property = System.getProperty("os.arch");
        if (isAndroid()) {
            return "android-arm";
        }
        if (property.startsWith("arm")) {
            property = resolveArmArchType();
        } else {
            String lowerCase = property.toLowerCase(Locale.US);
            HashMap<String, String> map = archMapping;
            if (map.containsKey(lowerCase)) {
                return map.get(lowerCase);
            }
        }
        return translateArchNameToFolderName(property);
    }

    public static String getHardwareName() {
        try {
            Process processExec = Runtime.getRuntime().exec("uname -m");
            processExec.waitFor();
            InputStream inputStream = processExec.getInputStream();
            try {
                return readFully(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (Throwable th) {
            System.err.println("Error while running uname -m: " + th.getMessage());
            return "unknown";
        }
    }

    public static String getNativeLibFolderPathForCurrentOS() {
        return getOSName() + PsuedoNames.PSEUDONAME_ROOT + getArchName();
    }

    public static String getOSName() {
        return translateOSNameToFolderName(System.getProperty("os.name"));
    }

    public static boolean isAlpine() {
        try {
            Process processExec = Runtime.getRuntime().exec("cat /etc/os-release | grep ^ID");
            processExec.waitFor();
            InputStream inputStream = processExec.getInputStream();
            try {
                return readFully(inputStream).toLowerCase().contains("alpine");
            } finally {
                inputStream.close();
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isAndroid() {
        return System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
    }

    public static void main(String[] strArr) {
        if (strArr.length >= 1) {
            if ("--os".equals(strArr[0])) {
                System.out.print(getOSName());
                return;
            } else if ("--arch".equals(strArr[0])) {
                System.out.print(getArchName());
                return;
            }
        }
        System.out.print(getNativeLibFolderPathForCurrentOS());
    }

    private static String readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[32];
        while (true) {
            int i = inputStream.read(bArr, 0, 32);
            if (i < 0) {
                return byteArrayOutputStream.toString();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static String resolveArmArchType() {
        if (System.getProperty("os.name").contains("Linux")) {
            String hardwareName = getHardwareName();
            if (hardwareName.startsWith("armv6")) {
                return "armv6";
            }
            if (hardwareName.startsWith("armv7")) {
                return "armv7";
            }
            if (hardwareName.startsWith("armv5")) {
                return "arm";
            }
            if (hardwareName.equals("aarch64")) {
                return ARM64;
            }
            String property = System.getProperty("sun.arch.abi");
            if (property != null && property.startsWith("gnueabihf")) {
                return "armv7";
            }
        }
        return "arm";
    }

    public static String translateArchNameToFolderName(String str) {
        return str.replaceAll("\\W", "");
    }

    public static String translateOSNameToFolderName(String str) {
        if (str.contains("Windows")) {
            return "Windows";
        }
        if (str.contains("Mac") || str.contains("Darwin")) {
            return "Mac";
        }
        if (str.contains("Linux")) {
            return "Linux";
        }
        return str.contains("AIX") ? "AIX" : str.replaceAll("\\W", "");
    }
}
