package defpackage;

import androidx.compose.foundation.layout.RowScope;
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
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class hg2 {
    public static final hg2 a = new hg2();
    public static Function3 b = ComposableLambdaKt.composableLambdaInstance(902869126, false, new Function3() { // from class: eg2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return hg2.c((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 c = ComposableLambdaKt.composableLambdaInstance(1247697288, false, new Function3() { // from class: fg2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return hg2.a((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 d = ComposableLambdaKt.composableLambdaInstance(-408408947, false, new Function2() { // from class: gg2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return hg2.b((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public static Unit a(RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1247697288, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$EditorTabBarKt.lambda$1247697288.<anonymous> (EditorTabBar.kt:160)");
            }
            TextKt.Text-Nvy7gAk("取消", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(-408408947, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$EditorTabBarKt.lambda$-408408947.<anonymous> (EditorTabBar.kt:144)");
            }
            TextKt.Text-Nvy7gAk("关闭全部标签", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(902869126, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$EditorTabBarKt.lambda$902869126.<anonymous> (EditorTabBar.kt:155)");
            }
            TextKt.Text-Nvy7gAk("关闭全部", (Modifier) null, ColorKt.Color(4292943176L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public final Function2 d() {
        return d;
    }

    public final Function3 e() {
        return c;
    }

    public final Function3 f() {
        return b;
    }
}
