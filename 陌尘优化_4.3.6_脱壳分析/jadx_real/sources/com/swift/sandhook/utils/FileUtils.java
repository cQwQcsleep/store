package com.swift.sandhook.utils;

import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import com.swift.sandhook.HookLog;
import com.swift.sandhook.SandHookConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class FileUtils {
    public static final boolean IS_USING_PROTECTED_STORAGE;

    public interface FileMode {
        public static final int MODE_755 = 493;
        public static final int MODE_IRGRP = 32;
        public static final int MODE_IROTH = 4;
        public static final int MODE_IRUSR = 256;
        public static final int MODE_ISGID = 1024;
        public static final int MODE_ISUID = 2048;
        public static final int MODE_ISVTX = 512;
        public static final int MODE_IWGRP = 16;
        public static final int MODE_IWOTH = 2;
        public static final int MODE_IWUSR = 128;
        public static final int MODE_IXGRP = 8;
        public static final int MODE_IXOTH = 1;
        public static final int MODE_IXUSR = 64;
    }

    static {
        IS_USING_PROTECTED_STORAGE = Build.VERSION.SDK_INT >= 24;
    }

    public static void chmod(String str, int i) throws InterruptedException, ErrnoException {
        if (SandHookConfig.SDK_INT >= 21) {
            try {
                Os.chmod(str, i);
                return;
            } catch (Exception unused) {
            }
        }
        Runtime.getRuntime().exec((new File(str).isDirectory() ? NPStringFog.decode("0D18000E0A414748204E") : NPStringFog.decode("0D18000E0A41")) + String.format(NPStringFog.decode("4B1F"), Integer.valueOf(i)) + NPStringFog.decode("4E") + str).waitFor();
    }

    public static void delete(File file) throws IOException {
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                delete(file2);
            } else if (!file2.delete()) {
                throw new IOException();
            }
        }
        if (!file.delete()) {
            throw new IOException();
        }
    }

    public static String getDataPathPrefix() {
        return IS_USING_PROTECTED_STORAGE ? NPStringFog.decode("41140C150F4E1216171C2F0904415148") : NPStringFog.decode("41140C150F4E0304060F5F");
    }

    public static String getPackageName(String str) {
        if (TextUtils.isEmpty(str)) {
            HookLog.e(NPStringFog.decode("091519310F020C04150B3E0C0C0B4112161B00174D040311131C520A1119002A0815"));
            return NPStringFog.decode("");
        }
        int iLastIndexOf = str.lastIndexOf(NPStringFog.decode("41"));
        return iLastIndexOf < 0 ? str : str.substring(iLastIndexOf + 1);
    }

    public static String readLine(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            try {
                String line = bufferedReader.readLine();
                bufferedReader.close();
                return line;
            } finally {
            }
        } catch (Throwable unused) {
            return NPStringFog.decode("");
        }
    }

    public static void writeLine(File file, String str) throws IOException {
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            try {
                bufferedWriter.write(str);
                bufferedWriter.flush();
                bufferedWriter.close();
            } finally {
            }
        } catch (Throwable th) {
            HookLog.e(NPStringFog.decode("0B021F0E1C4110171B1A1903064E0D0E0B174E04024108080B0052") + file + NPStringFog.decode("5450") + th.getMessage());
        }
    }
}
