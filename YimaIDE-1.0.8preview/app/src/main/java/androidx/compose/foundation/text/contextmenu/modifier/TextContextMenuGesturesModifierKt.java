package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001aH\u0010\u0000\u001a\u00020\u0001*\u00020\u000125\b\u0002\u0010\u0002\u001a/\b\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0003H\u0000¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"showTextContextMenuOnSecondaryClick", "Landroidx/compose/ui/Modifier;", "onPreShowContextMenu", "Lkotlin/Function2;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", "name", "clickLocation", "Lkotlin/coroutines/Continuation;", "", "", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/Modifier;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextContextMenuGesturesModifierKt {
    public static final Modifier showTextContextMenuOnSecondaryClick(Modifier modifier, Function2<? super Offset, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return modifier.then(new TextContextMenuGestureElement(function2));
    }

    public static /* synthetic */ Modifier showTextContextMenuOnSecondaryClick$default(Modifier modifier, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = null;
        }
        return showTextContextMenuOnSecondaryClick(modifier, function2);
    }
}
