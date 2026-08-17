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
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class rf2 {
    public static final rf2 a = new rf2();
    public static Function3 b = ComposableLambdaKt.composableLambdaInstance(1428482637, false, new Function3() { // from class: ef2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.a((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 c = ComposableLambdaKt.composableLambdaInstance(1687678323, false, new Function3() { // from class: lf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.m((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 d = ComposableLambdaKt.composableLambdaInstance(-1384938710, false, new Function3() { // from class: mf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.c((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 e = ComposableLambdaKt.composableLambdaInstance(12256532, false, new Function2() { // from class: nf2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return rf2.g((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function3 f = ComposableLambdaKt.composableLambdaInstance(1646681206, false, new Function3() { // from class: of2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.e((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 g = ComposableLambdaKt.composableLambdaInstance(1625632284, false, new Function3() { // from class: pf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.l((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 h = ComposableLambdaKt.composableLambdaInstance(-516804077, false, new Function3() { // from class: qf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.f((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 i = ComposableLambdaKt.composableLambdaInstance(-2136940163, false, new Function2() { // from class: ff2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return rf2.j((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 j = ComposableLambdaKt.composableLambdaInstance(1196401918, false, new Function2() { // from class: gf2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return rf2.b((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function3 k = ComposableLambdaKt.composableLambdaInstance(930830590, false, new Function3() { // from class: hf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.k((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 l = ComposableLambdaKt.composableLambdaInstance(1116411193, false, new Function3() { // from class: if2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.h((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 m = ComposableLambdaKt.composableLambdaInstance(-2110082521, false, new Function3() { // from class: jf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.d((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 n = ComposableLambdaKt.composableLambdaInstance(139217826, false, new Function3() { // from class: kf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return rf2.i((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public static Unit a(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1428482637, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$1428482637.<anonymous> (ConsoleScreen.kt:133)");
            }
            TextKt.Text-Nvy7gAk("确认关闭", (Modifier) null, ColorKt.Color(4294151080L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1196401918, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$1196401918.<anonymous> (ConsoleScreen.kt:158)");
            }
            TextKt.Text-Nvy7gAk("上次调试基座已打包完成。若刚才误取消了系统安装提示，可直接重装；需要改代码后再编则选重新构建。", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1384938710, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$-1384938710.<anonymous> (ConsoleScreen.kt:144)");
            }
            TextKt.Text-Nvy7gAk("挂载后台", (Modifier) null, ColorKt.Color(4289127329L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit d(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2110082521, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$-2110082521.<anonymous> (ConsoleScreen.kt:259)");
            }
            TextKt.Text-Nvy7gAk("重新构建", (Modifier) null, 0L, (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24582, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit e(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1646681206, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$1646681206.<anonymous> (ConsoleScreen.kt:164)");
            }
            TextKt.Text-Nvy7gAk("重新构建", (Modifier) null, ColorKt.Color(4287214842L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit f(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-516804077, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$-516804077.<anonymous> (ConsoleScreen.kt:175)");
            }
            TextKt.Text-Nvy7gAk("重装基座", (Modifier) null, ColorKt.Color(4289127329L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit g(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(12256532, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$12256532.<anonymous> (ConsoleScreen.kt:119)");
            }
            TextKt.Text-Nvy7gAk("关闭控制台", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit h(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1116411193, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$1116411193.<anonymous> (ConsoleScreen.kt:244)");
            }
            TextKt.Text-Nvy7gAk("复制", (Modifier) null, 0L, (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24582, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit i(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(139217826, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$139217826.<anonymous> (ConsoleScreen.kt:272)");
            }
            TextKt.Text-Nvy7gAk("关闭", (Modifier) null, 0L, (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24582, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit j(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2136940163, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$-2136940163.<anonymous> (ConsoleScreen.kt:156)");
            }
            TextKt.Text-Nvy7gAk("选择操作", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit k(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(930830590, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$930830590.<anonymous> (ConsoleScreen.kt:218)");
            }
            TextKt.Text-Nvy7gAk("停止", (Modifier) null, 0L, (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24582, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit l(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1625632284, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$1625632284.<anonymous> (ConsoleScreen.kt:169)");
            }
            TextKt.Text-Nvy7gAk("取消", (Modifier) null, ColorKt.Color(4287863218L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit m(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1687678323, i2, -1, "com.yimaide.app.ui.workbench.console.ComposableSingletons$ConsoleScreenKt.lambda$1687678323.<anonymous> (ConsoleScreen.kt:138)");
            }
            TextKt.Text-Nvy7gAk("取消", (Modifier) null, ColorKt.Color(4287863218L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public final Function3 n() {
        return d;
    }

    public final Function3 o() {
        return m;
    }

    public final Function2 p() {
        return i;
    }

    public final Function3 q() {
        return h;
    }

    public final Function3 r() {
        return l;
    }

    public final Function2 s() {
        return j;
    }

    public final Function2 t() {
        return e;
    }

    public final Function3 u() {
        return n;
    }

    public final Function3 v() {
        return b;
    }

    public final Function3 w() {
        return g;
    }

    public final Function3 x() {
        return f;
    }

    public final Function3 y() {
        return c;
    }

    public final Function3 z() {
        return k;
    }
}
