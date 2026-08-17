package androidx.compose.foundation.text.input.internal;

import android.os.Build;
import android.view.View;
import androidx.compose.foundation.text.input.internal.ComposeInputMethodManager_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a(\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0001\"\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"ComposeInputMethodManager", "Landroidx/compose/foundation/text/input/internal/ComposeInputMethodManager;", "view", "Landroid/view/View;", "ComposeInputMethodManagerFactory", "Lkotlin/Function1;", "overrideComposeInputMethodManagerFactoryForTests", "factory", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ComposeInputMethodManager_androidKt {
    private static Function1<? super View, ? extends ComposeInputMethodManager> ComposeInputMethodManagerFactory = new Function1() { // from class: lm2
        public final Object invoke(Object obj) {
            return ComposeInputMethodManager_androidKt.a((View) obj);
        }
    };

    public static final ComposeInputMethodManager ComposeInputMethodManager(View view) {
        return (ComposeInputMethodManager) ComposeInputMethodManagerFactory.invoke(view);
    }

    public static ComposeInputMethodManager a(View view) {
        return Build.VERSION.SDK_INT >= 34 ? new ComposeInputMethodManagerImplApi34(view) : new ComposeInputMethodManagerImplApi24(view);
    }

    public static final Function1<View, ComposeInputMethodManager> overrideComposeInputMethodManagerFactoryForTests(Function1<? super View, ? extends ComposeInputMethodManager> function1) {
        Function1 function2 = ComposeInputMethodManagerFactory;
        ComposeInputMethodManagerFactory = function1;
        return function2;
    }
}
