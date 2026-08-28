package com.swift.sandhook;

import android.os.Build;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class SandHookConfig {
    public static volatile boolean DEBUG = true;
    public static volatile int SDK_INT = Build.VERSION.SDK_INT;
    public static volatile boolean compiler;
    public static volatile int curUser;
    public static volatile boolean delayHook;
    public static volatile ClassLoader initClassLoader;
    public static volatile LibLoader libLoader;
    public static volatile String libSandHookPath;

    public interface LibLoader {
        void loadLib();
    }

    static {
        compiler = SDK_INT < 29;
        curUser = 0;
        delayHook = true;
        libLoader = new LibLoader() { // from class: com.swift.sandhook.SandHookConfig.1
            @Override // com.swift.sandhook.SandHookConfig.LibLoader
            public void loadLib() {
                if (SandHookConfig.libSandHookPath == null) {
                    System.loadLibrary(NPStringFog.decode("1D110305060E080E"));
                } else {
                    System.load(SandHookConfig.libSandHookPath);
                }
            }
        };
    }
}
