package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0001X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0004\b\b\u0010\u0005\"\u0014\u0010\t\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0003¨\u0006\u000b"}, d2 = {"useK1Implementation", HttpUrl.FRAGMENT_ENCODE_SET, "getUseK1Implementation", "()Z", "setUseK1Implementation", "(Z)V", "newFakeOverridesImplementation", "getNewFakeOverridesImplementation", "setNewFakeOverridesImplementation", "loadMetadataDirectly", "getLoadMetadataDirectly", "kotlin-reflection"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SystemPropertiesKt {
    private static final boolean loadMetadataDirectly;
    private static boolean newFakeOverridesImplementation;
    private static boolean useK1Implementation;

    static {
        Object obj;
        Object obj2;
        Object obj3;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(System.getProperty("kotlin.reflect.jvm.useK1Implementation"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = null;
        }
        String str = (String) obj;
        boolean z = false;
        useK1Implementation = str != null && Boolean.parseBoolean(str);
        try {
            obj2 = Result.constructor-impl(System.getProperty("kotlin.reflect.jvm.newFakeOverridesImplementation"));
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.Companion;
            obj2 = Result.constructor-impl(ResultKt.createFailure(th2));
        }
        if (Result.isFailure-impl(obj2)) {
            obj2 = null;
        }
        String str2 = (String) obj2;
        newFakeOverridesImplementation = str2 != null && Boolean.parseBoolean(str2);
        try {
            obj3 = Result.constructor-impl(System.getProperty("kotlin.reflect.jvm.loadMetadataDirectly"));
        } catch (Throwable th3) {
            Result.Companion companion4 = Result.Companion;
            obj3 = Result.constructor-impl(ResultKt.createFailure(th3));
        }
        String str3 = (String) (Result.isFailure-impl(obj3) ? null : obj3);
        if (str3 != null && Boolean.parseBoolean(str3)) {
            z = true;
        }
        loadMetadataDirectly = z;
    }

    public static final boolean getLoadMetadataDirectly() {
        return loadMetadataDirectly;
    }

    public static final boolean getNewFakeOverridesImplementation() {
        return newFakeOverridesImplementation;
    }

    public static final boolean getUseK1Implementation() {
        return useK1Implementation;
    }
}
