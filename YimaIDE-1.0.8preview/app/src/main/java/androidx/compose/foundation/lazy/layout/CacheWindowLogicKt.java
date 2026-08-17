package androidx.compose.foundation.lazy.layout;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001aZ\u0010\u0000\u001a\u00020\u0001*\u00020\u00022K\u0010\u0003\u001aG\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00010\u0004H\u0080\b\u001a\u0017\u0010\u0011\u001a\u00020\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082\b\"\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"forEachVisibleItem", "", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "action", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "itemIndex", "", "itemKey", "mainAxisSize", "InvalidItemSize", "InvalidIndex", "UnsetItemCount", "DebugEnabled", "", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CacheWindowLogicKt {
    private static final boolean DebugEnabled = false;
    public static final int InvalidIndex = -1;
    private static final int InvalidItemSize = -1;
    private static final int UnsetItemCount = -1;

    private static final void debugLog(Function0<String> function0) {
    }

    public static final void forEachVisibleItem(CacheWindowScope cacheWindowScope, Function3<? super Integer, Object, ? super Integer, Unit> function3) {
        int visibleLineCount = cacheWindowScope.getVisibleLineCount();
        for (int i = 0; i < visibleLineCount; i++) {
            function3.invoke(Integer.valueOf(cacheWindowScope.getVisibleItemLine(i)), cacheWindowScope.getVisibleLineKey(i), Integer.valueOf(cacheWindowScope.getVisibleItemSize(i)));
        }
    }
}
