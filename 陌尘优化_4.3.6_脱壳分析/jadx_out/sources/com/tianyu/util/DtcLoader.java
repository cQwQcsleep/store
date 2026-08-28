package com.tianyu.util;

import android.content.pm.ApplicationInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class DtcLoader {
    public static native void cbde(ClassLoader classLoader);

    public static native void clinit();

    public static native void craoc(String str);

    public static native ClassLoader dcl(ClassLoader classLoader, String str);

    public static native String gap();

    public static native String gdp();

    public static String getAbiDirName() throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName(Configuration.VM_RUNTIME_CLASS);
            return (String) cls.getDeclaredMethod(Configuration.METHOD_VM_INSTRUCTION_SET, null).invoke(cls.getDeclaredMethod(Configuration.METHOD_GET_RUNTIME, null).invoke(null, null), null);
        } catch (Exception unused) {
            return Configuration.DEFAULT_ABI;
        }
    }

    public static ApplicationInfo getApplicationInfo() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, SecurityException, IllegalArgumentException {
        try {
            Class<?> cls = Class.forName(Configuration.ACTIVITY_THREAD_CLASS);
            Field declaredField = cls.getDeclaredField(Configuration.FIELD_CURRENT_ACTIVITY_THREAD);
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            Field declaredField2 = cls.getDeclaredField(Configuration.FIELD_BOUND_APPLICATION);
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            Field declaredField3 = obj2.getClass().getDeclaredField(Configuration.FIELD_APPLICATION_INFO);
            declaredField3.setAccessible(true);
            return (ApplicationInfo) declaredField3.get(obj2);
        } catch (Exception unused) {
            return null;
        }
    }

    private static long getCrc32(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream, new CRC32());
                try {
                    while (checkedInputStream.read(new byte[4096]) != -1) {
                    }
                    long value = checkedInputStream.getChecksum().getValue();
                    checkedInputStream.close();
                    fileInputStream.close();
                    return value;
                } finally {
                }
            } finally {
            }
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static native void ia();

    public static void loadShellLibs(String str) {
        String[] strArr = {Configuration.SHELL_SO_NAME};
        try {
            String abiDirName = getAbiDirName();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = File.separator;
            sb.append(str2);
            sb.append(Configuration.LIB_DIR);
            sb.append(str2);
            sb.append(abiDirName);
            File[] fileArrListFiles = new File(sb.toString()).listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    String absolutePath = file.getAbsolutePath();
                    if (absolutePath.endsWith(File.separator + strArr[0])) {
                        System.load(absolutePath);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static native Object ra(String str);

    public static native String rapn();

    public static native String rcf();

    public static native void rde(ClassLoader classLoader, String str);

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
    
        if (r0 != r3.getCrc()) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:
    
        r5 = new java.io.FileOutputStream(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
    
        r6 = new java.io.BufferedOutputStream(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005a, code lost:
    
        r7 = new java.io.BufferedInputStream(r2.getInputStream(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0065, code lost:
    
        r0 = new byte[4096];
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0067, code lost:
    
        r1 = r7.read(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006c, code lost:
    
        if (r1 == (-1)) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
    
        r6.write(r0, 0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0075, code lost:
    
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0078, code lost:
    
        r6.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007b, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0081, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0083, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x008d, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0096, code lost:
    
        throw r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x009f, code lost:
    
        throw r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void unzipInNeeded(String str, String str2, String str3) throws IOException {
        File file = new File(str3);
        if (file.exists() || file.mkdirs()) {
            File file2 = new File(file, Configuration.SHELL_SO_NAME);
            long crc32 = file2.exists() ? getCrc32(file2) : 0L;
            try {
                ZipFile zipFile = new ZipFile(str);
                try {
                    Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                    while (true) {
                        if (!enumerationEntries.hasMoreElements()) {
                            break;
                        }
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        if (str2.equals(zipEntryNextElement.getName())) {
                            break;
                        }
                    }
                    zipFile.close();
                } finally {
                }
            } catch (IOException unused) {
            }
        }
    }

    public static void unzipLibs(String str, String str2) throws IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException, InvocationTargetException {
        String abiDirName = getAbiDirName();
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        String str3 = File.separator;
        sb.append(str3);
        sb.append(Configuration.LIB_DIR);
        sb.append(str3);
        sb.append(abiDirName);
        unzipInNeeded(str, Configuration.ASSETS_DIR + Configuration.getShellAssetName(abiDirName), new File(sb.toString()).getAbsolutePath());
    }
}
