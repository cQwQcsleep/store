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
public final class tj2 {
    public static final tj2 a = new tj2();
    public static Function3 b = ComposableLambdaKt.composableLambdaInstance(463787983, false, new Function3() { // from class: kj2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return tj2.f((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 c = ComposableLambdaKt.composableLambdaInstance(793508884, false, new Function2() { // from class: lj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return tj2.d((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function3 d = ComposableLambdaKt.composableLambdaInstance(-1479279965, false, new Function3() { // from class: mj2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return tj2.i((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 e = ComposableLambdaKt.composableLambdaInstance(-177122459, false, new Function2() { // from class: nj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return tj2.e((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 f = ComposableLambdaKt.composableLambdaInstance(39232412, false, new Function2() { // from class: oj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return tj2.h((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 g = ComposableLambdaKt.composableLambdaInstance(1549254237, false, new Function2() { // from class: pj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return tj2.a((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 h = ComposableLambdaKt.composableLambdaInstance(2039688503, false, new Function2() { // from class: qj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return tj2.g((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 i = ComposableLambdaKt.composableLambdaInstance(-2027494674, false, new Function2() { // from class: rj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return tj2.b((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 j = ComposableLambdaKt.composableLambdaInstance(119071791, false, new Function2() { // from class: sj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return tj2.c((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public static Unit a(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1549254237, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$1549254237.<anonymous> (SigningDialog.kt:160)");
            }
            TextKt.Text-Nvy7gAk("密钥密码", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(-2027494674, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$-2027494674.<anonymous> (SigningDialog.kt:176)");
            }
            TextKt.Text-Nvy7gAk("组织 O（可选）", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(119071791, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$119071791.<anonymous> (SigningDialog.kt:184)");
            }
            TextKt.Text-Nvy7gAk("有效期（年）", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit d(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(793508884, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$793508884.<anonymous> (SigningDialog.kt:85)");
            }
            TextKt.Text-Nvy7gAk("签名设置", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit e(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-177122459, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$-177122459.<anonymous> (SigningDialog.kt:144)");
            }
            TextKt.Text-Nvy7gAk("别名 (alias)", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(463787983, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$463787983.<anonymous> (SigningDialog.kt:251)");
            }
            TextKt.Text-Nvy7gAk("完成", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(2039688503, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$2039688503.<anonymous> (SigningDialog.kt:169)");
            }
            TextKt.Text-Nvy7gAk("名称 CN（可选）", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit h(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(39232412, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$39232412.<anonymous> (SigningDialog.kt:152)");
            }
            TextKt.Text-Nvy7gAk("密钥库密码", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(-1479279965, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$SigningDialogKt.lambda$-1479279965.<anonymous> (SigningDialog.kt:128)");
            }
            TextKt.Text-Nvy7gAk("清除密钥库", (Modifier) null, ColorKt.Color(4292617766L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public final Function3 j() {
        return d;
    }

    public final Function2 k() {
        return e;
    }

    public final Function2 l() {
        return i;
    }

    public final Function2 m() {
        return j;
    }

    public final Function2 n() {
        return g;
    }

    public final Function2 o() {
        return h;
    }

    public final Function2 p() {
        return f;
    }

    public final Function3 q() {
        return b;
    }

    public final Function2 r() {
        return c;
    }
}
