package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.spatial.RelativeLayoutBounds;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u001a\u001a\u00020\u0017J\b\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/layout/LayoutBoundsNode;", "Landroidx/compose/ui/Modifier$Node;", "holder", "Landroidx/compose/ui/layout/LayoutBoundsHolder;", "<init>", "(Landroidx/compose/ui/layout/LayoutBoundsHolder;)V", "getHolder", "()Landroidx/compose/ui/layout/LayoutBoundsHolder;", "setHolder", "handle", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "getHandle", "()Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "setHandle", "(Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;)V", "lastBounds", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "getLastBounds", "()Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "setLastBounds", "(Landroidx/compose/ui/spatial/RelativeLayoutBounds;)V", "rectChanged", "Lkotlin/Function1;", "", "getRectChanged", "()Lkotlin/jvm/functions/Function1;", "forceUpdate", "onAttach", "onDetach", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LayoutBoundsNode extends Modifier.Node {
    public static final int $stable = 8;
    private DelegatableNode.RegistrationHandle handle;
    private LayoutBoundsHolder holder;
    private RelativeLayoutBounds lastBounds;
    private final Function1<RelativeLayoutBounds, Unit> rectChanged = new Function1<RelativeLayoutBounds, Unit>() { // from class: androidx.compose.ui.layout.LayoutBoundsNode$rectChanged$1
        {
            super(1);
        }

        public final void invoke(RelativeLayoutBounds relativeLayoutBounds) {
            this.this$0.getHolder().setBounds$ui(relativeLayoutBounds);
            this.this$0.setLastBounds(relativeLayoutBounds);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((RelativeLayoutBounds) obj);
            return Unit.INSTANCE;
        }
    };

    public LayoutBoundsNode(LayoutBoundsHolder layoutBoundsHolder) {
        this.holder = layoutBoundsHolder;
    }

    public final void forceUpdate() {
        this.holder.setBounds$ui(this.lastBounds);
    }

    public final DelegatableNode.RegistrationHandle getHandle() {
        return this.handle;
    }

    public final LayoutBoundsHolder getHolder() {
        return this.holder;
    }

    public final RelativeLayoutBounds getLastBounds() {
        return this.lastBounds;
    }

    public final Function1<RelativeLayoutBounds, Unit> getRectChanged() {
        return this.rectChanged;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        DelegatableNode.RegistrationHandle registrationHandle = this.handle;
        if (registrationHandle != null) {
            registrationHandle.unregister();
        }
        this.handle = OnLayoutRectChangedModifierKt.registerOnLayoutRectChanged(this, 0L, 0L, this.rectChanged);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        DelegatableNode.RegistrationHandle registrationHandle = this.handle;
        if (registrationHandle != null) {
            registrationHandle.unregister();
        }
        this.holder.setBounds$ui(null);
    }

    public final void setHandle(DelegatableNode.RegistrationHandle registrationHandle) {
        this.handle = registrationHandle;
    }

    public final void setHolder(LayoutBoundsHolder layoutBoundsHolder) {
        this.holder = layoutBoundsHolder;
    }

    public final void setLastBounds(RelativeLayoutBounds relativeLayoutBounds) {
        this.lastBounds = relativeLayoutBounds;
    }
}
