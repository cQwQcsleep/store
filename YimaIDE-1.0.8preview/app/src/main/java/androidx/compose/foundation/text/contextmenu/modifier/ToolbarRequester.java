package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\b\u0011J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0013H&R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarRequester;", "", "<init>", "()V", "toolbarHandlerNode", "Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuToolbarHandlerNode;", "getToolbarHandlerNode$foundation", "()Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuToolbarHandlerNode;", "setToolbarHandlerNode$foundation", "(Landroidx/compose/foundation/text/contextmenu/modifier/TextContextMenuToolbarHandlerNode;)V", "toolbarHandlerState", "Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarHandlerState;", "getToolbarHandlerState$foundation", "()Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarHandlerState;", "setToolbarHandlerState$foundation", "(Landroidx/compose/foundation/text/contextmenu/modifier/ToolbarHandlerState;)V", "requireInitialized", "requireInitialized$foundation", "show", "", "hide", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class ToolbarRequester {
    public static final int $stable = 8;
    private TextContextMenuToolbarHandlerNode toolbarHandlerNode;
    private ToolbarHandlerState toolbarHandlerState = ToolbarHandlerState.Uninitialized;

    /* JADX INFO: renamed from: getToolbarHandlerNode$foundation, reason: from getter */
    public final TextContextMenuToolbarHandlerNode getToolbarHandlerNode() {
        return this.toolbarHandlerNode;
    }

    /* JADX INFO: renamed from: getToolbarHandlerState$foundation, reason: from getter */
    public final ToolbarHandlerState getToolbarHandlerState() {
        return this.toolbarHandlerState;
    }

    public abstract void hide();

    public final TextContextMenuToolbarHandlerNode requireInitialized$foundation() {
        if (!(this.toolbarHandlerState != ToolbarHandlerState.Uninitialized)) {
            InlineClassHelperKt.throwIllegalStateException("ToolbarRequester is not initialized.");
        }
        return this.toolbarHandlerNode;
    }

    public final void setToolbarHandlerNode$foundation(TextContextMenuToolbarHandlerNode textContextMenuToolbarHandlerNode) {
        this.toolbarHandlerNode = textContextMenuToolbarHandlerNode;
    }

    public final void setToolbarHandlerState$foundation(ToolbarHandlerState toolbarHandlerState) {
        this.toolbarHandlerState = toolbarHandlerState;
    }

    public abstract void show();
}
