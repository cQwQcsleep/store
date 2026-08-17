package androidx.compose.material3;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/TooltipAnchorPosition;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class TooltipAnchorPosition {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Above = m1257constructorimpl(1);
    private static final int Below = m1257constructorimpl(2);
    private static final int Left = m1257constructorimpl(3);
    private static final int Right = m1257constructorimpl(4);
    private static final int Start = m1257constructorimpl(5);
    private static final int End = m1257constructorimpl(6);

    private /* synthetic */ TooltipAnchorPosition(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TooltipAnchorPosition m1256boximpl(int i) {
        return new TooltipAnchorPosition(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m1257constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m1258equalsimpl(int i, Object obj) {
        return (obj instanceof TooltipAnchorPosition) && i == ((TooltipAnchorPosition) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1259equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m1260hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m1261toStringimpl(int i) {
        if (m1259equalsimpl0(i, Above)) {
            return "Above";
        }
        if (m1259equalsimpl0(i, Below)) {
            return "Below";
        }
        if (m1259equalsimpl0(i, Left)) {
            return "Left";
        }
        if (m1259equalsimpl0(i, Right)) {
            return "Right";
        }
        if (m1259equalsimpl0(i, Start)) {
            return "Start";
        }
        return m1259equalsimpl0(i, End) ? "End" : "Invalid";
    }

    public boolean equals(Object other) {
        return m1258equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m1260hashCodeimpl(this.value);
    }

    public String toString() {
        return m1261toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/TooltipAnchorPosition$Companion;", "", "<init>", "()V", "Above", "Landroidx/compose/material3/TooltipAnchorPosition;", "getAbove-lOKsHw4", "()I", "I", "Below", "getBelow-lOKsHw4", "Left", "getLeft-lOKsHw4", "Right", "getRight-lOKsHw4", "Start", "getStart-lOKsHw4", "End", "getEnd-lOKsHw4", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getAbove-lOKsHw4, reason: not valid java name */
        public final int m1263getAbovelOKsHw4() {
            return TooltipAnchorPosition.Above;
        }

        /* JADX INFO: renamed from: getBelow-lOKsHw4, reason: not valid java name */
        public final int m1264getBelowlOKsHw4() {
            return TooltipAnchorPosition.Below;
        }

        /* JADX INFO: renamed from: getEnd-lOKsHw4, reason: not valid java name */
        public final int m1265getEndlOKsHw4() {
            return TooltipAnchorPosition.End;
        }

        /* JADX INFO: renamed from: getLeft-lOKsHw4, reason: not valid java name */
        public final int m1266getLeftlOKsHw4() {
            return TooltipAnchorPosition.Left;
        }

        /* JADX INFO: renamed from: getRight-lOKsHw4, reason: not valid java name */
        public final int m1267getRightlOKsHw4() {
            return TooltipAnchorPosition.Right;
        }

        /* JADX INFO: renamed from: getStart-lOKsHw4, reason: not valid java name */
        public final int m1268getStartlOKsHw4() {
            return TooltipAnchorPosition.Start;
        }

        private Companion() {
        }
    }
}
