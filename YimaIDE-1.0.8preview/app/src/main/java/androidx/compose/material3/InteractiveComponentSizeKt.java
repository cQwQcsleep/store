package androidx.compose.material3;

import androidx.compose.material3.InteractiveComponentSizeKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, d2 = {"minimumInteractiveComponentSize", "Landroidx/compose/ui/Modifier;", "MinimumInteractiveTopAlignmentLine", "Landroidx/compose/ui/layout/HorizontalAlignmentLine;", "getMinimumInteractiveTopAlignmentLine", "()Landroidx/compose/ui/layout/HorizontalAlignmentLine;", "MinimumInteractiveLeftAlignmentLine", "Landroidx/compose/ui/layout/VerticalAlignmentLine;", "getMinimumInteractiveLeftAlignmentLine", "()Landroidx/compose/ui/layout/VerticalAlignmentLine;", "LocalMinimumInteractiveComponentEnforcement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalMinimumInteractiveComponentEnforcement$annotations", "()V", "getLocalMinimumInteractiveComponentEnforcement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalMinimumInteractiveComponentSize", "Landroidx/compose/ui/unit/Dp;", "getLocalMinimumInteractiveComponentSize", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class InteractiveComponentSizeKt {
    private static final HorizontalAlignmentLine MinimumInteractiveTopAlignmentLine = new HorizontalAlignmentLine(InteractiveComponentSizeKt$MinimumInteractiveTopAlignmentLine$1.INSTANCE);
    private static final VerticalAlignmentLine MinimumInteractiveLeftAlignmentLine = new VerticalAlignmentLine(InteractiveComponentSizeKt$MinimumInteractiveLeftAlignmentLine$1.INSTANCE);
    private static final ProvidableCompositionLocal<Boolean> LocalMinimumInteractiveComponentEnforcement = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: gu6
        public final Object invoke() {
            return Boolean.valueOf(InteractiveComponentSizeKt.b());
        }
    });
    private static final ProvidableCompositionLocal<Dp> LocalMinimumInteractiveComponentSize = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: hu6
        public final Object invoke() {
            return InteractiveComponentSizeKt.a();
        }
    });

    public static Dp a() {
        return Dp.m6020boximpl(Dp.m6022constructorimpl(48.0f));
    }

    public static boolean b() {
        return true;
    }

    public static final ProvidableCompositionLocal<Boolean> getLocalMinimumInteractiveComponentEnforcement() {
        return LocalMinimumInteractiveComponentEnforcement;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use LocalMinimumInteractiveComponentSize with 0.dp to turn off enforcement instead.", replaceWith = @ReplaceWith(expression = "LocalMinimumInteractiveComponentSize", imports = {}))
    public static /* synthetic */ void getLocalMinimumInteractiveComponentEnforcement$annotations() {
    }

    public static final ProvidableCompositionLocal<Dp> getLocalMinimumInteractiveComponentSize() {
        return LocalMinimumInteractiveComponentSize;
    }

    public static final VerticalAlignmentLine getMinimumInteractiveLeftAlignmentLine() {
        return MinimumInteractiveLeftAlignmentLine;
    }

    public static final HorizontalAlignmentLine getMinimumInteractiveTopAlignmentLine() {
        return MinimumInteractiveTopAlignmentLine;
    }

    public static final Modifier minimumInteractiveComponentSize(Modifier modifier) {
        return modifier.then(MinimumInteractiveModifier.INSTANCE);
    }
}
