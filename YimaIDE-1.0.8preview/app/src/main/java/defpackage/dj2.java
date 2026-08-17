package defpackage;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
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
public final class dj2 {
    public static final dj2 a = new dj2();
    public static Function3 b = ComposableLambdaKt.composableLambdaInstance(-1460467717, false, new Function3() { // from class: zi2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return dj2.a((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 c = ComposableLambdaKt.composableLambdaInstance(1091549258, false, new Function3() { // from class: aj2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return dj2.d((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 d = ComposableLambdaKt.composableLambdaInstance(-932078321, false, new Function2() { // from class: bj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return dj2.c((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 e = ComposableLambdaKt.composableLambdaInstance(2109833952, false, new Function2() { // from class: cj2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return dj2.b((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public static Unit a(RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1460467717, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$RenamePackageDialogKt.lambda$-1460467717.<anonymous> (RenamePackageDialog.kt:61)");
            }
            TextKt.Text-Nvy7gAk("确定", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(2109833952, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$RenamePackageDialogKt.lambda$2109833952.<anonymous> (RenamePackageDialog.kt:47)");
            }
            TextKt.Text-Nvy7gAk("包名 (applicationId)", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(-932078321, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$RenamePackageDialogKt.lambda$-932078321.<anonymous> (RenamePackageDialog.kt:38)");
            }
            TextKt.Text-Nvy7gAk("修改包名", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
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
                ComposerKt.traceEventStart(1091549258, i, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$RenamePackageDialogKt.lambda$1091549258.<anonymous> (RenamePackageDialog.kt:64)");
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

    public final Function3 e() {
        return b;
    }

    public final Function2 f() {
        return d;
    }

    public final Function3 g() {
        return c;
    }

    public final Function2 h() {
        return e;
    }
}
