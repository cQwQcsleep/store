package androidx.compose.material3;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Landroidx/compose/material3/TextFieldLabelPosition;", "", "<init>", "()V", "Attached", "Above", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class TextFieldLabelPosition {
    public static final int $stable = 0;

    public /* synthetic */ TextFieldLabelPosition(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private TextFieldLabelPosition() {
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/material3/TextFieldLabelPosition$Above;", "Landroidx/compose/material3/TextFieldLabelPosition;", "alignment", "Landroidx/compose/ui/Alignment$Horizontal;", "<init>", "(Landroidx/compose/ui/Alignment$Horizontal;)V", "getAlignment", "()Landroidx/compose/ui/Alignment$Horizontal;", "equals", "", "other", "", "hashCode", "", "toString", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Above extends TextFieldLabelPosition {
        public static final int $stable = 0;
        private final Alignment.Horizontal alignment;

        public /* synthetic */ Above(Alignment.Horizontal horizontal, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? Alignment.INSTANCE.getStart() : horizontal);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Above) {
                return Intrinsics.areEqual(this.alignment, ((Above) other).alignment);
            }
            return false;
        }

        public final Alignment.Horizontal getAlignment() {
            return this.alignment;
        }

        public int hashCode() {
            return this.alignment.hashCode();
        }

        public String toString() {
            return "Above(alignment=" + this.alignment + ')';
        }

        public Above(Alignment.Horizontal horizontal) {
            super(null);
            this.alignment = horizontal;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Above() {
            Alignment.Horizontal horizontal = null;
            this(horizontal, 1, horizontal);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/TextFieldLabelPosition$Attached;", "Landroidx/compose/material3/TextFieldLabelPosition;", "alwaysMinimize", "", "minimizedAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "expandedAlignment", "<init>", "(ZLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Horizontal;)V", "getAlwaysMinimize", "()Z", "getMinimizedAlignment", "()Landroidx/compose/ui/Alignment$Horizontal;", "getExpandedAlignment", "equals", "other", "", "hashCode", "", "toString", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Attached extends TextFieldLabelPosition {
        public static final int $stable = 0;
        private final boolean alwaysMinimize;
        private final Alignment.Horizontal expandedAlignment;
        private final Alignment.Horizontal minimizedAlignment;

        public /* synthetic */ Attached(boolean z, Alignment.Horizontal horizontal, Alignment.Horizontal horizontal2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? Alignment.INSTANCE.getStart() : horizontal, (i & 4) != 0 ? Alignment.INSTANCE.getStart() : horizontal2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Attached)) {
                return false;
            }
            Attached attached = (Attached) other;
            return this.alwaysMinimize == attached.alwaysMinimize && Intrinsics.areEqual(this.minimizedAlignment, attached.minimizedAlignment) && Intrinsics.areEqual(this.expandedAlignment, attached.expandedAlignment);
        }

        public final boolean getAlwaysMinimize() {
            return this.alwaysMinimize;
        }

        public final Alignment.Horizontal getExpandedAlignment() {
            return this.expandedAlignment;
        }

        public final Alignment.Horizontal getMinimizedAlignment() {
            return this.minimizedAlignment;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.alwaysMinimize) * 31) + this.minimizedAlignment.hashCode()) * 31) + this.expandedAlignment.hashCode();
        }

        public String toString() {
            return "Attached(alwaysMinimize=" + this.alwaysMinimize + ", minimizedAlignment=" + this.minimizedAlignment + ", expandedAlignment=" + this.expandedAlignment + ')';
        }

        public Attached() {
            this(false, null, null, 7, null);
        }

        public Attached(boolean z, Alignment.Horizontal horizontal, Alignment.Horizontal horizontal2) {
            super(null);
            this.alwaysMinimize = z;
            this.minimizedAlignment = horizontal;
            this.expandedAlignment = horizontal2;
        }
    }
}
