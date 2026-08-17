package androidx.compose.material3;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/FabPosition;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class FabPosition {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Start = m465constructorimpl(0);
    private static final int Center = m465constructorimpl(1);
    private static final int End = m465constructorimpl(2);
    private static final int EndOverlay = m465constructorimpl(3);

    private /* synthetic */ FabPosition(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FabPosition m464boximpl(int i) {
        return new FabPosition(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m465constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m466equalsimpl(int i, Object obj) {
        return (obj instanceof FabPosition) && i == ((FabPosition) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m467equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m468hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m469toStringimpl(int i) {
        if (m467equalsimpl0(i, Start)) {
            return "FabPosition.Start";
        }
        if (m467equalsimpl0(i, Center)) {
            return "FabPosition.Center";
        }
        return m467equalsimpl0(i, End) ? "FabPosition.End" : "FabPosition.EndOverlay";
    }

    public boolean equals(Object other) {
        return m466equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m468hashCodeimpl(this.value);
    }

    public String toString() {
        return m469toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/material3/FabPosition$Companion;", "", "<init>", "()V", "Start", "Landroidx/compose/material3/FabPosition;", "getStart-ERTFSPs", "()I", "I", "Center", "getCenter-ERTFSPs", "End", "getEnd-ERTFSPs", "EndOverlay", "getEndOverlay-ERTFSPs", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getCenter-ERTFSPs, reason: not valid java name */
        public final int m471getCenterERTFSPs() {
            return FabPosition.Center;
        }

        /* JADX INFO: renamed from: getEnd-ERTFSPs, reason: not valid java name */
        public final int m472getEndERTFSPs() {
            return FabPosition.End;
        }

        /* JADX INFO: renamed from: getEndOverlay-ERTFSPs, reason: not valid java name */
        public final int m473getEndOverlayERTFSPs() {
            return FabPosition.EndOverlay;
        }

        /* JADX INFO: renamed from: getStart-ERTFSPs, reason: not valid java name */
        public final int m474getStartERTFSPs() {
            return FabPosition.Start;
        }

        private Companion() {
        }
    }
}
