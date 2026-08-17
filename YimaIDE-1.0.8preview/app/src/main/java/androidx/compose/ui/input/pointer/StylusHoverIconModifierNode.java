package androidx.compose.ui.input.pointer;

import androidx.compose.ui.node.DpTouchBoundsExpansion;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/input/pointer/StylusHoverIconModifierNode;", "Landroidx/compose/ui/input/pointer/HoverIconModifierNode;", "icon", "Landroidx/compose/ui/input/pointer/PointerIcon;", "overrideDescendants", "", "touchBoundsExpansion", "Landroidx/compose/ui/node/DpTouchBoundsExpansion;", "<init>", "(Landroidx/compose/ui/input/pointer/PointerIcon;ZLandroidx/compose/ui/node/DpTouchBoundsExpansion;)V", "traverseKey", "", "getTraverseKey", "()Ljava/lang/String;", "isRelevantPointerType", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "isRelevantPointerType-uerMTgs", "(I)Z", "displayIcon", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class StylusHoverIconModifierNode extends HoverIconModifierNode {
    public static final int $stable = 8;
    private final String traverseKey;

    public /* synthetic */ StylusHoverIconModifierNode(PointerIcon pointerIcon, boolean z, DpTouchBoundsExpansion dpTouchBoundsExpansion, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(pointerIcon, (i & 2) != 0 ? false : z, (i & 4) != 0 ? null : dpTouchBoundsExpansion);
    }

    @Override // androidx.compose.ui.input.pointer.HoverIconModifierNode
    public void displayIcon(PointerIcon icon) {
        PointerIconService pointerIconService = getPointerIconService();
        if (pointerIconService != null) {
            pointerIconService.setStylusHoverIcon(icon);
        }
    }

    @Override // androidx.compose.ui.input.pointer.HoverIconModifierNode
    /* JADX INFO: renamed from: isRelevantPointerType-uerMTgs */
    public boolean mo4372isRelevantPointerTypeuerMTgs(int pointerType) {
        PointerType.Companion companion = PointerType.INSTANCE;
        return PointerType.m4529equalsimpl0(pointerType, companion.m4535getStylusT8wyACA()) || PointerType.m4529equalsimpl0(pointerType, companion.m4533getEraserT8wyACA());
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public String getTraverseKey() {
        return this.traverseKey;
    }

    public StylusHoverIconModifierNode(PointerIcon pointerIcon, boolean z, DpTouchBoundsExpansion dpTouchBoundsExpansion) {
        super(pointerIcon, z, dpTouchBoundsExpansion);
        this.traverseKey = "androidx.compose.ui.input.pointer.StylusHoverIcon";
    }
}
