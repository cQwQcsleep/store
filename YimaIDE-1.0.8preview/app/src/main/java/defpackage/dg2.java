package defpackage;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
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
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class dg2 {
    public static final dg2 a = new dg2();
    public static Function3 b = ComposableLambdaKt.composableLambdaInstance(389293765, false, new Function3() { // from class: yf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return dg2.a((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 c = ComposableLambdaKt.composableLambdaInstance(206852054, false, new Function3() { // from class: zf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return dg2.c((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 d = ComposableLambdaKt.composableLambdaInstance(1521196593, false, new Function2() { // from class: ag2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return dg2.b((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 e = ComposableLambdaKt.composableLambdaInstance(-420981872, false, new Function2() { // from class: bg2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return dg2.e((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function3 f = ComposableLambdaKt.composableLambdaInstance(1909634625, false, new Function3() { // from class: cg2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return dg2.d((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public static Unit a(RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(389293765, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$DepsWarmupUiKt.lambda$389293765.<anonymous> (DepsWarmupUi.kt:147)");
            }
            TextKt.Text-Nvy7gAk("下载", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(1521196593, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$DepsWarmupUiKt.lambda$1521196593.<anonymous> (DepsWarmupUi.kt:133)");
            }
            TextKt.Text-Nvy7gAk("需要下载依赖", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 1572870, 0, 262078);
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
                ComposerKt.traceEventStart(206852054, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$DepsWarmupUiKt.lambda$206852054.<anonymous> (DepsWarmupUi.kt:156)");
            }
            TextKt.Text-Nvy7gAk("稍后", (Modifier) null, ColorKt.Color(4284773515L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit d(RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1909634625, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$DepsWarmupUiKt.lambda$1909634625.<anonymous> (DepsWarmupUi.kt:252)");
            }
            TextKt.Text-Nvy7gAk("取消", (Modifier) null, ColorKt.Color(4287931320L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
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
                ComposerKt.traceEventStart(-420981872, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$DepsWarmupUiKt.lambda$-420981872.<anonymous> (DepsWarmupUi.kt:135)");
            }
            TextKt.Text-Nvy7gAk("检测到依赖包尚未就绪。首次下载可能需要几分钟，视网络而定。", SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(280.0f)), ColorKt.Color(4282865001L), (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 25014, 48, 260072);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public final Function2 f() {
        return e;
    }

    public final Function2 g() {
        return d;
    }

    public final Function3 h() {
        return f;
    }

    public final Function3 i() {
        return c;
    }

    public final Function3 j() {
        return b;
    }
}
