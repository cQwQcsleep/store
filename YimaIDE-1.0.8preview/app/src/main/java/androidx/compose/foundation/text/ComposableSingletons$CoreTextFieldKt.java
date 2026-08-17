package androidx.compose.foundation.text;

import androidx.compose.foundation.text.ComposableSingletons$CoreTextFieldKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$CoreTextFieldKt {
    public static final ComposableSingletons$CoreTextFieldKt INSTANCE = new ComposableSingletons$CoreTextFieldKt();
    private static Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> lambda$559628295 = ComposableLambdaKt.composableLambdaInstance(559628295, false, new Function3() { // from class: uf2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$CoreTextFieldKt.a((Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public static Unit a(Function2 function2, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= composer.changedInstance(function2) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(559628295, i, -1, "androidx.compose.foundation.text.ComposableSingletons$CoreTextFieldKt.lambda$559628295.<anonymous> (CoreTextField.kt:210)");
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

    public final Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> getLambda$559628295$foundation() {
        return lambda$559628295;
    }
}
