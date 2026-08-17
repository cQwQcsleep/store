package com.intellij.util.text;

import com.intellij.openapi.util.text.Strings;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001c\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\b*\u00020\b\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0001\u001a\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001\u001a\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001¨\u0006\u000f"}, d2 = {"nullize", "", "nullizeSpaces", "", "trimMiddle", "maxLength", "", "useEllipsisSymbol", "", "escLBr", "findTextRange", "Lcom/intellij/openapi/util/TextRange;", "substring", "allOccurrencesOf", "Lkotlin/sequences/Sequence;", "intellij.platform.util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class StringKt {
    public static final String nullize(String str, boolean z) {
        return Strings.nullize(str, z);
    }

    public static /* synthetic */ String nullize$default(String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return nullize(str, z);
    }
}
