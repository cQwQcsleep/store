package androidx.compose.foundation.selection;

import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0007¨\u0006\u0002"}, d2 = {"selectableGroup", "Landroidx/compose/ui/Modifier;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectableGroupKt {
    public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.selectableGroup(semanticsPropertyReceiver);
        return Unit.INSTANCE;
    }

    public static final Modifier selectableGroup(Modifier modifier) {
        return SemanticsModifierKt.semantics$default(modifier, false, new Function1() { // from class: b3d
            public final Object invoke(Object obj) {
                return SelectableGroupKt.a((SemanticsPropertyReceiver) obj);
            }
        }, 1, (Object) null);
    }
}
