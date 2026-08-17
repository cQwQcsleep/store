package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0001¨\u0006\u0006"}, d2 = {"join", "", "collection", "", "", "separator", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class StringsKt {
    public static final String join(Iterable<? extends Object> iterable, String str) {
        iterable.getClass();
        str.getClass();
        return kotlin.collections.CollectionsKt.joinToString$default(iterable, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
