package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.TargetedFlingBehaviorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u0002\n\u0000\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"NoOnReport", "Lkotlin/Function1;", "", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TargetedFlingBehaviorKt {
    private static final Function1<Float, Unit> NoOnReport = new Function1() { // from class: t2e
        public final Object invoke(Object obj) {
            return TargetedFlingBehaviorKt.a(((Float) obj).floatValue());
        }
    };

    public static Unit a(float f) {
        return Unit.INSTANCE;
    }
}
