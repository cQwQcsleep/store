package androidx.compose.foundation.text;

import androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$BasicTextFieldKt {
    public static final ComposableSingletons$BasicTextFieldKt INSTANCE = new ComposableSingletons$BasicTextFieldKt();
    private static Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> lambda$759698998 = ComposableLambdaKt.composableLambdaInstance(759698998, false, new Function3() { // from class: ye2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$BasicTextFieldKt.c((Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> lambda$486633673 = ComposableLambdaKt.composableLambdaInstance(486633673, false, new Function3() { // from class: ze2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$BasicTextFieldKt.d((Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> lambda$444370233 = ComposableLambdaKt.composableLambdaInstance(444370233, false, new Function3() { // from class: af2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$BasicTextFieldKt.a((Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-665310900, reason: not valid java name */
    private static Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> f57lambda$665310900 = ComposableLambdaKt.composableLambdaInstance(-665310900, false, new Function3() { // from class: bf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$BasicTextFieldKt.b((Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public static Unit a(Function2 function2, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= composer.changedInstance(function2) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(444370233, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda$444370233.<anonymous> (BasicTextField.kt:949)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(Function2 function2, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= composer.changedInstance(function2) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-665310900, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda$-665310900.<anonymous> (BasicTextField.kt:989)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(Function2 function2, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= composer.changedInstance(function2) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(759698998, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda$759698998.<anonymous> (BasicTextField.kt:748)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit d(Function2 function2, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= composer.changedInstance(function2) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(486633673, i, -1, "androidx.compose.foundation.text.ComposableSingletons$BasicTextFieldKt.lambda$486633673.<anonymous> (BasicTextField.kt:905)");
            }
            function2.invoke(composer, Integer.valueOf(i & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: getLambda$-665310900$foundation, reason: not valid java name */
    public final Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> m1322getLambda$665310900$foundation() {
        return f57lambda$665310900;
    }

    public final Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> getLambda$444370233$foundation() {
        return lambda$444370233;
    }

    public final Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> getLambda$486633673$foundation() {
        return lambda$486633673;
    }

    public final Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> getLambda$759698998$foundation() {
        return lambda$759698998;
    }
}
