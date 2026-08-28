package com.tianyu.util;

/* loaded from: classes.dex */
public class Configuration {
    public static final String ACTIVITY_THREAD_CLASS = "android.app.ActivityThread";
    public static final String ASSETS_DIR = "assets/";
    public static final String DEFAULT_ABI = "arm64";
    public static final String EMPTY = "";
    public static final String FIELD_APPLICATION_INFO = "appInfo";
    public static final String FIELD_BOUND_APPLICATION = "mBoundApplication";
    public static final String FIELD_CURRENT_ACTIVITY_THREAD = "sCurrentActivityThread";
    public static final String LIB_DIR = "dpt-libs";
    public static final String METHOD_GET_RUNTIME = "getRuntime";
    public static final String METHOD_INSTANTIATE_ACTIVITY = "instantiateActivity";
    public static final String METHOD_INSTANTIATE_APPLICATION = "instantiateApplication";
    public static final String METHOD_INSTANTIATE_CLASS_LOADER = "instantiateClassLoader";
    public static final String METHOD_INSTANTIATE_PROVIDER = "instantiateProvider";
    public static final String METHOD_INSTANTIATE_RECEIVER = "instantiateReceiver";
    public static final String METHOD_INSTANTIATE_SERVICE = "instantiateService";
    public static final String METHOD_VM_INSTRUCTION_SET = "vmInstructionSet";
    public static final String SHELL_SO_NAME = "libjiagu.so";
    public static final String VM_RUNTIME_CLASS = "dalvik.system.VMRuntime";
    public static volatile boolean sIsReplacedClassLoader = false;
    public static volatile boolean sNeedCalledApplication = true;

    public static String getShellAssetName(String str) {
        str.getClass();
        switch (str) {
            case "x86_64":
                return "libjiagu_x64.so";
            case "arm":
                return "libjiagu_a.so";
            case "x86":
                return "libjiagu_x86.so";
            case "arm64":
                return "libjiagu_a64.so";
            default:
                throw new IllegalArgumentException("Unsupported shell ABI: ".concat(str));
        }
    }
}
