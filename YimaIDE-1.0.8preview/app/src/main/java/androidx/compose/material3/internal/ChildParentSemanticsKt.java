package androidx.compose.material3.internal;

import androidx.compose.material3.internal.ChildParentSemanticsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0000\u001a%\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0000¨\u0006\b"}, d2 = {"childSemantics", "Landroidx/compose/ui/Modifier;", "properties", "Lkotlin/Function1;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "", "Lkotlin/ExtensionFunctionType;", "parentSemantics", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ChildParentSemanticsKt {
    public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return Unit.INSTANCE;
    }

    public static final Modifier childSemantics(Modifier modifier, Function1<? super SemanticsPropertyReceiver, Unit> function1) {
        return modifier.then(new ChildSemanticsNodeElement(function1));
    }

    public static /* synthetic */ Modifier childSemantics$default(Modifier modifier, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: js1
                public final Object invoke(Object obj2) {
                    return ChildParentSemanticsKt.a((SemanticsPropertyReceiver) obj2);
                }
            };
        }
        return childSemantics(modifier, function1);
    }

    public static final Modifier parentSemantics(Modifier modifier, Function1<? super SemanticsPropertyReceiver, Unit> function1) {
        return modifier.then(new ParentSemanticsNodeElement(function1));
    }
}
