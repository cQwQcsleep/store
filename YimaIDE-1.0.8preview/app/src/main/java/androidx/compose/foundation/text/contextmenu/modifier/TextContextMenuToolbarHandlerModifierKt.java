package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0082\u0001\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052 \b\u0002\u0010\u0006\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00072 \b\u0002\u0010\u000b\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00072#\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0007H\u0000¢\u0006\u0002\u0010\u0012\u001a \u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"ToolbarRequesterNotInitialized", "", "textContextMenuToolbarHandler", "Landroidx/compose/ui/Modifier;", "requester", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "onShow", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "onHide", "computeContentBounds", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Lkotlin/ParameterName;", "name", "destinationCoordinates", "Landroidx/compose/ui/geometry/Rect;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Landroidx/compose/ui/Modifier;", "translateRootToDestination", "rootContentBounds", "localCoordinates", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextContextMenuToolbarHandlerModifierKt {
    private static final String ToolbarRequesterNotInitialized = "ToolbarRequester is not initialized.";

    public static final Modifier textContextMenuToolbarHandler(Modifier modifier, ToolbarRequester toolbarRequester, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Function1<? super Continuation<? super Unit>, ? extends Object> function2, Function1<? super LayoutCoordinates, Rect> function3) {
        return modifier.then(new TextContextMenuToolbarHandlerElement(toolbarRequester, function1, function2, function3));
    }

    public static /* synthetic */ Modifier textContextMenuToolbarHandler$default(Modifier modifier, ToolbarRequester toolbarRequester, Function1 function1, Function1 function2, Function1 function3, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        if ((i & 4) != 0) {
            function2 = null;
        }
        return textContextMenuToolbarHandler(modifier, toolbarRequester, function1, function2, function3);
    }

    public static final Rect translateRootToDestination(Rect rect, LayoutCoordinates layoutCoordinates, LayoutCoordinates layoutCoordinates2) {
        if (!layoutCoordinates.isAttached() || !layoutCoordinates2.isAttached()) {
            return Rect.Companion.getZero();
        }
        return RectKt.Rect-tz77jQw(layoutCoordinates2.localPositionOf-R5De75A(LayoutCoordinatesKt.findRootCoordinates(layoutCoordinates), rect.getTopLeft-F1C5BW0()), rect.getSize-NH-jbRc());
    }
}
