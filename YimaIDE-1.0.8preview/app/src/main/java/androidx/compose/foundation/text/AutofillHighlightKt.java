package androidx.compose.foundation.text;

import androidx.compose.foundation.text.AutofillHighlightKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u001a'\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u000e\u0010\u000f\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\u0004¨\u0006\u0010"}, d2 = {"LocalAutofillHighlightBrush", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/ui/graphics/Brush;", "getLocalAutofillHighlightBrush", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalAutofillHighlightColor", "Landroidx/compose/ui/graphics/Color;", "getLocalAutofillHighlightColor$annotations", "()V", "getLocalAutofillHighlightColor", "resolveAutofillHighlight", "brush", "color", "defaultColor", "resolveAutofillHighlight-WkMS-hQ", "(Landroidx/compose/ui/graphics/Brush;JJ)Landroidx/compose/ui/graphics/Brush;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AutofillHighlightKt {
    private static final ProvidableCompositionLocal<Brush> LocalAutofillHighlightBrush = CompositionLocalKt.compositionLocalOf$default((SnapshotMutationPolicy) null, new Function0() { // from class: qj0
        public final Object invoke() {
            return AutofillHighlightKt.a();
        }
    }, 1, (Object) null);
    private static final ProvidableCompositionLocal<Color> LocalAutofillHighlightColor = CompositionLocalKt.compositionLocalOf$default((SnapshotMutationPolicy) null, new Function0<Color>() { // from class: androidx.compose.foundation.text.AutofillHighlightKt$LocalAutofillHighlightColor$1
        public /* bridge */ /* synthetic */ Object invoke() {
            return Color.box-impl(m1296invoke0d7_KjU());
        }

        /* JADX INFO: renamed from: invoke-0d7_KjU, reason: not valid java name */
        public final long m1296invoke0d7_KjU() {
            return AutofillHighlight_androidKt.autofillHighlightColor();
        }
    }, 1, (Object) null);

    public static Brush a() {
        return new SolidColor(AutofillHighlight_androidKt.autofillHighlightColor(), (DefaultConstructorMarker) null);
    }

    public static final ProvidableCompositionLocal<Brush> getLocalAutofillHighlightBrush() {
        return LocalAutofillHighlightBrush;
    }

    public static final ProvidableCompositionLocal<Color> getLocalAutofillHighlightColor() {
        return LocalAutofillHighlightColor;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use LocalAutofillHighlightBrush instead. To provide a solid color, use SolidColor(yourColor).", replaceWith = @ReplaceWith(expression = "LocalAutofillHighlightBrush", imports = {"androidx.compose.foundation.text.LocalAutofillHighlightBrush"}))
    public static /* synthetic */ void getLocalAutofillHighlightColor$annotations() {
    }

    /* JADX INFO: renamed from: resolveAutofillHighlight-WkMS-hQ, reason: not valid java name */
    public static final Brush m1295resolveAutofillHighlightWkMShQ(Brush brush, long j, long j2) {
        return !Color.equals-impl0(j, j2) ? new SolidColor(j, (DefaultConstructorMarker) null) : brush;
    }
}
