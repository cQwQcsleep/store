package org.jetbrains.kotlin.synthetic;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"canBePropertyAccessor", "", "identifier", "", "org.jetbrains.kotlin:frontend.java"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class JavaSyntheticPropertiesScopeKt {
    public static final boolean canBePropertyAccessor(String str) {
        str.getClass();
        return StringsKt.startsWith$default(str, "get", false, 2, (Object) null) || StringsKt.startsWith$default(str, "is", false, 2, (Object) null) || StringsKt.startsWith$default(str, "set", false, 2, (Object) null);
    }
}
