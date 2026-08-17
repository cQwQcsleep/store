package androidx.compose.foundation.text.input.internal.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0081\b\u0018\u0000 &2\u00020\u0001:\u0001&B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0010J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JB\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006'"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "", "visible", "", "position", "Landroidx/compose/ui/geometry/Offset;", "lineHeight", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "handlesCrossed", "<init>", "(ZJFLandroidx/compose/ui/text/style/ResolvedTextDirection;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getVisible", "()Z", "getPosition-F1C5BW0", "()J", "J", "getLineHeight", "()F", "getDirection", "()Landroidx/compose/ui/text/style/ResolvedTextDirection;", "getHandlesCrossed", "component1", "component2", "component2-F1C5BW0", "component3", "component4", "component5", "copy", "copy-YqVAtuI", "(ZJFLandroidx/compose/ui/text/style/ResolvedTextDirection;Z)Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "equals", "other", "hashCode", "", "toString", "", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class TextFieldHandleState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TextFieldHandleState Hidden = new TextFieldHandleState(false, Offset.Companion.getUnspecified-F1C5BW0(), 0.0f, ResolvedTextDirection.Ltr, false, null);
    private final ResolvedTextDirection direction;
    private final boolean handlesCrossed;
    private final float lineHeight;
    private final long position;
    private final boolean visible;

    private TextFieldHandleState(boolean z, long j, float f, ResolvedTextDirection resolvedTextDirection, boolean z2) {
        this.visible = z;
        this.position = j;
        this.lineHeight = f;
        this.direction = resolvedTextDirection;
        this.handlesCrossed = z2;
    }

    /* JADX INFO: renamed from: copy-YqVAtuI$default, reason: not valid java name */
    public static /* synthetic */ TextFieldHandleState m1639copyYqVAtuI$default(TextFieldHandleState textFieldHandleState, boolean z, long j, float f, ResolvedTextDirection resolvedTextDirection, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = textFieldHandleState.visible;
        }
        if ((i & 2) != 0) {
            j = textFieldHandleState.position;
        }
        if ((i & 4) != 0) {
            f = textFieldHandleState.lineHeight;
        }
        if ((i & 8) != 0) {
            resolvedTextDirection = textFieldHandleState.direction;
        }
        if ((i & 16) != 0) {
            z2 = textFieldHandleState.handlesCrossed;
        }
        boolean z3 = z2;
        float f2 = f;
        return textFieldHandleState.m1641copyYqVAtuI(z, j, f2, resolvedTextDirection, z3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getVisible() {
        return this.visible;
    }

    /* JADX INFO: renamed from: component2-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getLineHeight() {
        return this.lineHeight;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ResolvedTextDirection getDirection() {
        return this.direction;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getHandlesCrossed() {
        return this.handlesCrossed;
    }

    /* JADX INFO: renamed from: copy-YqVAtuI, reason: not valid java name */
    public final TextFieldHandleState m1641copyYqVAtuI(boolean visible, long position, float lineHeight, ResolvedTextDirection direction, boolean handlesCrossed) {
        return new TextFieldHandleState(visible, position, lineHeight, direction, handlesCrossed, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextFieldHandleState)) {
            return false;
        }
        TextFieldHandleState textFieldHandleState = (TextFieldHandleState) other;
        return this.visible == textFieldHandleState.visible && Offset.equals-impl0(this.position, textFieldHandleState.position) && Float.compare(this.lineHeight, textFieldHandleState.lineHeight) == 0 && this.direction == textFieldHandleState.direction && this.handlesCrossed == textFieldHandleState.handlesCrossed;
    }

    public final ResolvedTextDirection getDirection() {
        return this.direction;
    }

    public final boolean getHandlesCrossed() {
        return this.handlesCrossed;
    }

    public final float getLineHeight() {
        return this.lineHeight;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name */
    public final long m1642getPositionF1C5BW0() {
        return this.position;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.visible) * 31) + Offset.hashCode-impl(this.position)) * 31) + Float.hashCode(this.lineHeight)) * 31) + this.direction.hashCode()) * 31) + Boolean.hashCode(this.handlesCrossed);
    }

    public String toString() {
        return "TextFieldHandleState(visible=" + this.visible + ", position=" + ((Object) Offset.toString-impl(this.position)) + ", lineHeight=" + this.lineHeight + ", direction=" + this.direction + ", handlesCrossed=" + this.handlesCrossed + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState$Companion;", "", "<init>", "()V", "Hidden", "Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "getHidden", "()Landroidx/compose/foundation/text/input/internal/selection/TextFieldHandleState;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TextFieldHandleState getHidden() {
            return TextFieldHandleState.Hidden;
        }

        private Companion() {
        }
    }

    public /* synthetic */ TextFieldHandleState(boolean z, long j, float f, ResolvedTextDirection resolvedTextDirection, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, j, f, resolvedTextDirection, z2);
    }
}
