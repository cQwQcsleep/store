package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0014\u0010\u000fJ\u000f\u0010\u0015\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0016\u0010\u0012R+\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"androidx/compose/material3/ScaffoldKt$ScaffoldLayout$contentPadding$1$1", "Landroidx/compose/foundation/layout/PaddingValues;", "<set-?>", "paddingHolder", "getPaddingHolder", "()Landroidx/compose/foundation/layout/PaddingValues;", "setPaddingHolder", "(Landroidx/compose/foundation/layout/PaddingValues;)V", "paddingHolder$delegate", "Landroidx/compose/runtime/MutableState;", "calculateLeftPadding", "Landroidx/compose/ui/unit/Dp;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "calculateLeftPadding-u2uoSUM", "(Landroidx/compose/ui/unit/LayoutDirection;)F", "calculateTopPadding", "calculateTopPadding-D9Ej5fM", "()F", "calculateRightPadding", "calculateRightPadding-u2uoSUM", "calculateBottomPadding", "calculateBottomPadding-D9Ej5fM", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ScaffoldKt$ScaffoldLayout$contentPadding$1$1 implements PaddingValues {

    /* JADX INFO: renamed from: paddingHolder$delegate, reason: from kotlin metadata */
    private final MutableState paddingHolder = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(PaddingKt.PaddingValues-0680j_4(Dp.m6022constructorimpl(0.0f)), null, 2, null);

    /* JADX INFO: renamed from: calculateBottomPadding-D9Ej5fM, reason: not valid java name */
    public float m788calculateBottomPaddingD9Ej5fM() {
        return getPaddingHolder().calculateBottomPadding-D9Ej5fM();
    }

    /* JADX INFO: renamed from: calculateLeftPadding-u2uoSUM, reason: not valid java name */
    public float m789calculateLeftPaddingu2uoSUM(LayoutDirection layoutDirection) {
        return getPaddingHolder().calculateLeftPadding-u2uoSUM(layoutDirection);
    }

    /* JADX INFO: renamed from: calculateRightPadding-u2uoSUM, reason: not valid java name */
    public float m790calculateRightPaddingu2uoSUM(LayoutDirection layoutDirection) {
        return getPaddingHolder().calculateRightPadding-u2uoSUM(layoutDirection);
    }

    /* JADX INFO: renamed from: calculateTopPadding-D9Ej5fM, reason: not valid java name */
    public float m791calculateTopPaddingD9Ej5fM() {
        return getPaddingHolder().calculateTopPadding-D9Ej5fM();
    }

    public final PaddingValues getPaddingHolder() {
        return (PaddingValues) this.paddingHolder.getValue();
    }

    public final void setPaddingHolder(PaddingValues paddingValues) {
        this.paddingHolder.setValue(paddingValues);
    }
}
