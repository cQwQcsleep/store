package com.reandroid.apk.framework;

import com.reandroid.apk.AndroidFrameworks;
import com.reandroid.apk.FrameworkApk;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class InternalFrameworks extends FrameworkManager {
    private static final String ANDROID_PACKAGE = "android";
    private static final String ANDROID_RESOURCE_DIRECTORY = "/frameworks/android/";
    private static final String FRAMEWORK_EXTENSION = ".apk";
    private static final int HIGHEST_AVAILABLE_VERSION = 36;
    public static final InternalFrameworks INSTANCE = new InternalFrameworks();
    private static final int LOWEST_AVAILABLE_VERSION = 23;
    private Map<Integer, String> resourcePaths;

    private InternalFrameworks() {
    }

    private static void closeQuietly(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        } catch (IOException unused) {
        }
    }

    private Map<Integer, String> getResourcePaths() {
        Map<Integer, String> mapScanAvailableResourcePaths;
        Map<Integer, String> map = this.resourcePaths;
        if (map != null) {
            return map;
        }
        synchronized (this) {
            mapScanAvailableResourcePaths = scanAvailableResourcePaths();
            this.resourcePaths = mapScanAvailableResourcePaths;
        }
        return mapScanAvailableResourcePaths;
    }

    private static boolean isAvailable(String str) {
        InputStream resourceAsStream = InternalFrameworks.class.getResourceAsStream(str);
        if (resourceAsStream == null) {
            return false;
        }
        closeQuietly(resourceAsStream);
        return true;
    }

    private FrameworkApk loadResource(int i) throws IOException {
        String str = getResourcePaths().get(Integer.valueOf(i));
        if (str != null) {
            return FrameworkApk.loadApkBuffer(toSimpleName(str), AndroidFrameworks.class.getResourceAsStream(str));
        }
        t8g.a("No resource found for version: ", i);
        return null;
    }

    private Map<Integer, String> scanAvailableResourcePaths() {
        HashMap map = new HashMap();
        int i = 36;
        for (int i2 = 23; i2 < i; i2++) {
            String resourcePath = toResourcePath(i2);
            if (isAvailable(resourcePath)) {
                map.put(Integer.valueOf(i2), resourcePath);
                if (i2 + 1 == i) {
                    i++;
                }
            }
        }
        return map;
    }

    private static String toResourcePath(int i) {
        return "/frameworks/android/android-" + i + FRAMEWORK_EXTENSION;
    }

    private static String toSimpleName(String str) {
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf < 0) {
            iLastIndexOf = str.lastIndexOf(File.separatorChar);
        }
        if (iLastIndexOf > 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        int iLastIndexOf2 = str.lastIndexOf(46);
        return iLastIndexOf2 >= 0 ? str.substring(0, iLastIndexOf2) : str;
    }

    @Override // com.reandroid.apk.framework.FrameworkManager
    public FrameworkApk get(int i) {
        return null;
    }

    @Override // com.reandroid.apk.framework.FrameworkManager
    public FrameworkApk getBestMatch(int i) {
        Integer nearestVersion = getNearestVersion(i);
        if (nearestVersion == null) {
            return null;
        }
        synchronized (AndroidFrameworks.class) {
            int iIntValue = nearestVersion.intValue();
            FrameworkApk current = getCurrent();
            if (current != null && iIntValue == current.getVersionCode()) {
                return current;
            }
            try {
                return loadResource(iIntValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override // com.reandroid.apk.framework.FrameworkManager
    public FrameworkApk getLatest() {
        synchronized (AndroidFrameworks.class) {
            int iIntValue = getLatestVersion().intValue();
            FrameworkApk current = getCurrent();
            if (current != null && iIntValue == current.getVersionCode()) {
                return current;
            }
            try {
                FrameworkApk frameworkApkLoadResource = loadResource(iIntValue);
                if (current == null) {
                    setCurrent(frameworkApkLoadResource);
                }
                return frameworkApkLoadResource;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override // com.reandroid.apk.framework.FrameworkManager
    public Integer getLatestVersion() {
        Iterator<Integer> it = getResourcePaths().keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (i == 0 || iIntValue > i) {
                i = iIntValue;
            }
        }
        return Integer.valueOf(i);
    }

    @Override // com.reandroid.apk.framework.FrameworkManager
    public Integer getNearestVersion(int i) {
        Map<Integer, String> resourcePaths = getResourcePaths();
        if (resourcePaths.containsKey(Integer.valueOf(i))) {
            return Integer.valueOf(i);
        }
        Iterator<Integer> it = resourcePaths.keySet().iterator();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (i3 == 0) {
                i4 = (i * 2) + 1000;
                i2 = iIntValue;
                i3 = i2;
            } else {
                if (iIntValue > i3) {
                    i3 = iIntValue;
                }
                int i5 = iIntValue - i;
                if (i5 < 0) {
                    i5 = -i5;
                }
                if (i5 < i4 || (i5 == i4 && iIntValue > i2)) {
                    i2 = iIntValue;
                    i4 = i5;
                }
            }
        }
        return Integer.valueOf(i2);
    }
}
