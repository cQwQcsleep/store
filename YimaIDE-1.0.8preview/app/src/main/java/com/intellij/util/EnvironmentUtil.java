package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.util.containers.CollectionFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.future.FutureKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class EnvironmentUtil {
    private static final Logger LOG = Logger.getInstance(EnvironmentUtil.class);
    private static final AtomicReference<CompletableDeferred<Map<String, String>>> ourEnvGetter = new AtomicReference<>();
    private static final Pattern pattern = Pattern.compile("\\$(.*?)\\$");

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 1:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 16:
            default:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 1:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                i2 = 3;
                break;
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 16:
            default:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
                objArr[0] = "parentJob";
                break;
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 16:
            default:
                objArr[0] = "com/intellij/util/EnvironmentUtil";
                break;
            case 5:
                objArr[0] = "name";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "shell";
                break;
            case 8:
            case 21:
                objArr[0] = "lines";
                break;
            case 10:
                objArr[0] = "text";
                break;
            case 11:
            case 12:
                objArr[0] = "process";
                break;
            case 13:
            case 14:
            case 17:
                objArr[0] = "env";
                break;
            case 15:
                objArr[0] = "charset";
                break;
            case 18:
            case 19:
                objArr[0] = "envs";
                break;
            case 20:
                objArr[0] = "parentEnv";
                break;
        }
        switch (i) {
            case 1:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                objArr[1] = "com/intellij/util/EnvironmentUtil";
                break;
            case 2:
            case 3:
            case 4:
                objArr[1] = "getSystemEnv";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "buildShellProcessCommand";
                break;
            case 9:
                objArr[1] = "parseEnv";
                break;
            case 16:
                objArr[1] = "setLocaleEnv";
                break;
            default:
                objArr[1] = "getEnvironmentMap";
                break;
        }
        switch (i) {
            case 1:
                objArr[2] = "loadEnvironment";
                break;
            case 5:
                objArr[2] = "getValue";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "buildShellProcessCommand";
                break;
            case 8:
            case 10:
                objArr[2] = "parseEnv";
                break;
            case 11:
                objArr[2] = "waitAndTerminateAfter";
                break;
            case 12:
                objArr[2] = "waitFor";
                break;
            case 13:
                objArr[2] = "setCharsetVar";
                break;
            case 14:
            case 15:
                objArr[2] = "setLocaleEnv";
                break;
            case 17:
                objArr[2] = "isCharsetVarDefined";
                break;
            case 18:
            case 19:
            case 20:
                objArr[2] = "inlineParentOccurrences";
                break;
            case 21:
                objArr[2] = "testParser";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 1:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                throw new IllegalArgumentException(str2);
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 16:
            default:
                throw new IllegalStateException(str2);
        }
    }

    private EnvironmentUtil() {
    }

    public static Map<String, String> getEnvironmentMap() {
        AtomicReference<CompletableDeferred<Map<String, String>>> atomicReference = ourEnvGetter;
        CompletableDeferred<Map<String, String>> CompletableDeferred = atomicReference.get();
        if (CompletableDeferred == null) {
            CompletableDeferred = CompletableDeferredKt.CompletableDeferred(getSystemEnv());
            if (!atomicReference.compareAndSet(null, CompletableDeferred)) {
                CompletableDeferred = atomicReference.get();
            }
        }
        try {
            Map<String, String> map = (Map) FutureKt.asCompletableFuture(CompletableDeferred).join();
            if (map == null) {
                $$$reportNull$$$0(0);
            }
            return map;
        } catch (Throwable th) {
            x01.a(th);
            return null;
        }
    }

    private static Map<String, String> getSystemEnv() {
        if (SystemInfoRt.isWindows) {
            Map<String, String> mapUnmodifiableMap = Collections.unmodifiableMap(CollectionFactory.createCaseInsensitiveStringMap(System.getenv()));
            if (mapUnmodifiableMap == null) {
                $$$reportNull$$$0(2);
            }
            return mapUnmodifiableMap;
        }
        if (!SystemInfoRt.isUnix || SystemInfoRt.isMac) {
            Map<String, String> map = System.getenv();
            if (map == null) {
                $$$reportNull$$$0(4);
            }
            return map;
        }
        Map<String, String> mapUnmodifiableMap2 = System.getenv();
        if (mapUnmodifiableMap2.containsKey("DESKTOP_STARTUP_ID")) {
            HashMap map2 = new HashMap(mapUnmodifiableMap2);
            map2.remove("DESKTOP_STARTUP_ID");
            mapUnmodifiableMap2 = Collections.unmodifiableMap(map2);
        }
        if (mapUnmodifiableMap2 == null) {
            $$$reportNull$$$0(3);
        }
        return mapUnmodifiableMap2;
    }

    public static String getValue(String str) {
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        return getEnvironmentMap().get(str);
    }
}
