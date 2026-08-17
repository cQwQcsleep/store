package org.jetbrains.kotlin.konan.file;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"isBitcode", "", "", "(Ljava/lang/String;)Z", "isUnixStaticLib", "isWindowsStaticLib", "kotlin-native-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NativeFileTypeKt {
    public static final boolean isBitcode(String str) {
        str.getClass();
        return StringsKt.endsWith$default(str, ".bc", false, 2, (Object) null);
    }

    public static final boolean isUnixStaticLib(String str) {
        str.getClass();
        return StringsKt.endsWith$default(str, ".a", false, 2, (Object) null);
    }

    public static final boolean isWindowsStaticLib(String str) {
        str.getClass();
        return StringsKt.endsWith$default(str, ".lib", false, 2, (Object) null);
    }
}
