package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"extractRadix", "Lorg/jetbrains/kotlin/utils/NumberWithRadix;", "value", "", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class NumbersKt {
    public static final NumberWithRadix extractRadix(String str) {
        str.getClass();
        if (StringsKt.startsWith$default(str, "0x", false, 2, (Object) null) || StringsKt.startsWith$default(str, "0X", false, 2, (Object) null)) {
            return new NumberWithRadix(str.substring(2), 16);
        }
        return (StringsKt.startsWith$default(str, "0b", false, 2, (Object) null) || StringsKt.startsWith$default(str, "0B", false, 2, (Object) null)) ? new NumberWithRadix(str.substring(2), 2) : new NumberWithRadix(str, 10);
    }
}
