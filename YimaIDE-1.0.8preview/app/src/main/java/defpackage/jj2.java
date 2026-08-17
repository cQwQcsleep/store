package defpackage;

import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class jj2 {
    public static final jj2 a = new jj2();
    public static Function2 b = ComposableLambdaKt.composableLambdaInstance(1962547670, false, new Function2() { // from class: ej2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return jj2.c((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 c = ComposableLambdaKt.composableLambdaInstance(-1201381738, false, new Function2() { // from class: fj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return jj2.a((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 d = ComposableLambdaKt.composableLambdaInstance(-368451177, false, new Function2() { // from class: gj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return jj2.e((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 e = ComposableLambdaKt.composableLambdaInstance(464479384, false, new Function2() { // from class: hj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return jj2.b((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 f = ComposableLambdaKt.composableLambdaInstance(876612493, false, new Function2() { // from class: ij2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return jj2.d((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public static Unit a(Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1201381738, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SearchReplacePanelKt.lambda$-1201381738.<anonymous> (SearchReplacePanel.kt:104)");
            }
            TextKt.Text-Nvy7gAk("↑", (Modifier) null, ColorKt.Color(4293060848L), (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24966, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(464479384, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SearchReplacePanelKt.lambda$464479384.<anonymous> (SearchReplacePanel.kt:110)");
            }
            TextKt.Text-Nvy7gAk("X", (Modifier) null, ColorKt.Color(4287931320L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24966, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1962547670, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SearchReplacePanelKt.lambda$1962547670.<anonymous> (SearchReplacePanel.kt:70)");
            }
            TextKt.Text-Nvy7gAk("搜索", (Modifier) null, ColorKt.Color(4284773515L), (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24966, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit d(Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(876612493, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SearchReplacePanelKt.lambda$876612493.<anonymous> (SearchReplacePanel.kt:123)");
            }
            TextKt.Text-Nvy7gAk("替换", (Modifier) null, ColorKt.Color(4284773515L), (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24966, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit e(Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-368451177, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SearchReplacePanelKt.lambda$-368451177.<anonymous> (SearchReplacePanel.kt:107)");
            }
            TextKt.Text-Nvy7gAk("↓", (Modifier) null, ColorKt.Color(4293060848L), (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24966, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public final Function2 f() {
        return c;
    }

    public final Function2 g() {
        return d;
    }

    public final Function2 h() {
        return b;
    }

    public final Function2 i() {
        return e;
    }

    public final Function2 j() {
        return f;
    }
}
