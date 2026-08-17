package org.jetbrains.kotlin.konan.util;

import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0010\n\u0002\b\u0003\"%\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"visibleName", "", "T", "", "getVisibleName", "(Ljava/lang/Enum;)Ljava/lang/String;", "kotlin-native-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VisibleNamedKt {
    public static final <T extends Enum<T>> String getVisibleName(T t) {
        t.getClass();
        String lowerCase = t.name().toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return lowerCase;
    }
}
