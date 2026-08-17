package androidx.compose.material3;

import androidx.compose.material3.TooltipScopeImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.window.PopupPositionProvider;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ-\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u001f\u0010\u000f\u001a\u001b\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013H\u0017J\u000e\u0010\u0014\u001a\u0004\u0018\u00010\u0004*\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0006H\u0016R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/TooltipScopeImpl;", "Landroidx/compose/material3/TooltipScope;", "getAnchorBounds", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "positionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "<init>", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupPositionProvider;)V", "getGetAnchorBounds", "()Lkotlin/jvm/functions/Function0;", "getPositionProvider", "()Landroidx/compose/ui/window/PopupPositionProvider;", "drawCaret", "Landroidx/compose/ui/Modifier;", "draw", "Lkotlin/Function2;", "Landroidx/compose/ui/draw/CacheDrawScope;", "Landroidx/compose/ui/draw/DrawResult;", "Lkotlin/ExtensionFunctionType;", "obtainAnchorBounds", "Landroidx/compose/ui/layout/MeasureScope;", "obtainPositionProvider", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TooltipScopeImpl implements TooltipScope {
    public static final int $stable = 0;
    private final Function0<LayoutCoordinates> getAnchorBounds;
    private final PopupPositionProvider positionProvider;

    /* JADX WARN: Multi-variable type inference failed */
    public TooltipScopeImpl(Function0<? extends LayoutCoordinates> function0, PopupPositionProvider popupPositionProvider) {
        this.getAnchorBounds = function0;
        this.positionProvider = popupPositionProvider;
    }

    public static DrawResult a(Function2 function2, TooltipScopeImpl tooltipScopeImpl, CacheDrawScope cacheDrawScope) {
        return (DrawResult) function2.invoke(cacheDrawScope, tooltipScopeImpl.getAnchorBounds.invoke());
    }

    @Override // androidx.compose.material3.TooltipScope
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ Modifier drawCaret(Modifier modifier, final Function2 function2) {
        return DrawModifierKt.drawWithCache(modifier, new Function1() { // from class: fhe
            public final Object invoke(Object obj) {
                return TooltipScopeImpl.a(function2, this, (CacheDrawScope) obj);
            }
        });
    }

    public final Function0<LayoutCoordinates> getGetAnchorBounds() {
        return this.getAnchorBounds;
    }

    public final PopupPositionProvider getPositionProvider() {
        return this.positionProvider;
    }

    @Override // androidx.compose.material3.TooltipScope
    public LayoutCoordinates obtainAnchorBounds(MeasureScope measureScope) {
        return (LayoutCoordinates) this.getAnchorBounds.invoke();
    }

    @Override // androidx.compose.material3.TooltipScope
    public PopupPositionProvider obtainPositionProvider() {
        return this.positionProvider;
    }
}
