package androidx.compose.ui.text;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/PlaceholderVerticalAlign;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class PlaceholderVerticalAlign {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int AboveBaseline = m5394constructorimpl(1);
    private static final int Top = m5394constructorimpl(2);
    private static final int Bottom = m5394constructorimpl(3);
    private static final int Center = m5394constructorimpl(4);
    private static final int TextTop = m5394constructorimpl(5);
    private static final int TextBottom = m5394constructorimpl(6);
    private static final int TextCenter = m5394constructorimpl(7);

    private /* synthetic */ PlaceholderVerticalAlign(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PlaceholderVerticalAlign m5393boximpl(int i) {
        return new PlaceholderVerticalAlign(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m5394constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5395equalsimpl(int i, Object obj) {
        return (obj instanceof PlaceholderVerticalAlign) && i == ((PlaceholderVerticalAlign) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5396equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5397hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5398toStringimpl(int i) {
        if (m5396equalsimpl0(i, AboveBaseline)) {
            return "AboveBaseline";
        }
        if (m5396equalsimpl0(i, Top)) {
            return "Top";
        }
        if (m5396equalsimpl0(i, Bottom)) {
            return "Bottom";
        }
        if (m5396equalsimpl0(i, Center)) {
            return "Center";
        }
        if (m5396equalsimpl0(i, TextTop)) {
            return "TextTop";
        }
        if (m5396equalsimpl0(i, TextBottom)) {
            return "TextBottom";
        }
        return m5396equalsimpl0(i, TextCenter) ? "TextCenter" : "Invalid";
    }

    public boolean equals(Object other) {
        return m5395equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m5397hashCodeimpl(this.value);
    }

    public String toString() {
        return m5398toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/text/PlaceholderVerticalAlign$Companion;", "", "<init>", "()V", "AboveBaseline", "Landroidx/compose/ui/text/PlaceholderVerticalAlign;", "getAboveBaseline-J6kI3mc", "()I", "I", "Top", "getTop-J6kI3mc", "Bottom", "getBottom-J6kI3mc", "Center", "getCenter-J6kI3mc", "TextTop", "getTextTop-J6kI3mc", "TextBottom", "getTextBottom-J6kI3mc", "TextCenter", "getTextCenter-J6kI3mc", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getAboveBaseline-J6kI3mc, reason: not valid java name */
        public final int m5400getAboveBaselineJ6kI3mc() {
            return PlaceholderVerticalAlign.AboveBaseline;
        }

        /* JADX INFO: renamed from: getBottom-J6kI3mc, reason: not valid java name */
        public final int m5401getBottomJ6kI3mc() {
            return PlaceholderVerticalAlign.Bottom;
        }

        /* JADX INFO: renamed from: getCenter-J6kI3mc, reason: not valid java name */
        public final int m5402getCenterJ6kI3mc() {
            return PlaceholderVerticalAlign.Center;
        }

        /* JADX INFO: renamed from: getTextBottom-J6kI3mc, reason: not valid java name */
        public final int m5403getTextBottomJ6kI3mc() {
            return PlaceholderVerticalAlign.TextBottom;
        }

        /* JADX INFO: renamed from: getTextCenter-J6kI3mc, reason: not valid java name */
        public final int m5404getTextCenterJ6kI3mc() {
            return PlaceholderVerticalAlign.TextCenter;
        }

        /* JADX INFO: renamed from: getTextTop-J6kI3mc, reason: not valid java name */
        public final int m5405getTextTopJ6kI3mc() {
            return PlaceholderVerticalAlign.TextTop;
        }

        /* JADX INFO: renamed from: getTop-J6kI3mc, reason: not valid java name */
        public final int m5406getTopJ6kI3mc() {
            return PlaceholderVerticalAlign.Top;
        }

        private Companion() {
        }
    }
}
