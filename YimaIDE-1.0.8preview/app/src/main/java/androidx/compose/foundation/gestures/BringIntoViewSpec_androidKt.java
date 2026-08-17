package androidx.compose.foundation.gestures;

import android.content.Context;
import androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\"\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u00020\u0002X\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"LocalBringIntoViewSpec", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "getLocalBringIntoViewSpec$annotations", "()V", "getLocalBringIntoViewSpec", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "PivotBringIntoViewSpec", "getPivotBringIntoViewSpec$annotations", "getPivotBringIntoViewSpec", "()Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BringIntoViewSpec_androidKt {
    private static final ProvidableCompositionLocal<BringIntoViewSpec> LocalBringIntoViewSpec = CompositionLocalKt.compositionLocalWithComputedDefaultOf(new Function1() { // from class: o01
        public final Object invoke(Object obj) {
            return BringIntoViewSpec_androidKt.a((CompositionLocalAccessorScope) obj);
        }
    });
    private static final BringIntoViewSpec PivotBringIntoViewSpec = new BringIntoViewSpec() { // from class: androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt$PivotBringIntoViewSpec$1
        private final float childFraction;
        private final float parentFraction = 0.3f;

        @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
        public float calculateScrollDistance(float offset, float size, float containerSize) {
            float fAbs = Math.abs((size + offset) - offset);
            boolean z = fAbs <= containerSize;
            float f = (this.parentFraction * containerSize) - (this.childFraction * fAbs);
            float f2 = containerSize - f;
            if (z && f2 < fAbs) {
                f = containerSize - fAbs;
            }
            return offset - f;
        }

        public final float getChildFraction() {
            return this.childFraction;
        }

        public final float getParentFraction() {
            return this.parentFraction;
        }
    };

    public static BringIntoViewSpec a(CompositionLocalAccessorScope compositionLocalAccessorScope) {
        return !((Context) compositionLocalAccessorScope.getCurrentValue(AndroidCompositionLocals_androidKt.getLocalContext())).getPackageManager().hasSystemFeature("android.software.leanback") ? BringIntoViewSpec.INSTANCE.getDefaultBringIntoViewSpec$foundation() : PivotBringIntoViewSpec;
    }

    public static final ProvidableCompositionLocal<BringIntoViewSpec> getLocalBringIntoViewSpec() {
        return LocalBringIntoViewSpec;
    }

    public static /* synthetic */ void getLocalBringIntoViewSpec$annotations() {
    }

    public static final BringIntoViewSpec getPivotBringIntoViewSpec() {
        return PivotBringIntoViewSpec;
    }

    public static /* synthetic */ void getPivotBringIntoViewSpec$annotations() {
    }
}
