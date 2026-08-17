package org.jetbrains.kotlin.cfg;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"hasUnknown", "", "", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "getHasUnknown", "(Ljava/util/List;)Z", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class WhenCheckerKt {
    public static final boolean getHasUnknown(List<? extends WhenMissingCase> list) {
        list.getClass();
        return Intrinsics.areEqual(CollectionsKt.firstOrNull(list), WhenMissingCase.Unknown.INSTANCE);
    }
}
