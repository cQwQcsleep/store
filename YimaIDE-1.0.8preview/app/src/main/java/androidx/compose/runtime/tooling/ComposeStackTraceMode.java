package androidx.compose.runtime.tooling;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\t\u001a\u00020\u0003HÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceMode;", "", "value", "", "constructor-impl", "(I)I", "equals", "", "other", "hashCode", "toString", "", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class ComposeStackTraceMode {
    private static boolean isMinified;
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int None = m2587constructorimpl(0);
    private static final int GroupKeys = m2587constructorimpl(1);
    private static final int SourceInformation = m2587constructorimpl(2);

    private /* synthetic */ ComposeStackTraceMode(int i) {
        this.value = i;
    }

    public static final /* synthetic */ boolean access$isMinified$cp() {
        return true;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ComposeStackTraceMode m2586boximpl(int i) {
        return new ComposeStackTraceMode(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m2587constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2588equalsimpl(int i, Object obj) {
        return (obj instanceof ComposeStackTraceMode) && i == ((ComposeStackTraceMode) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2589equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2590hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2591toStringimpl(int i) {
        return "ComposeStackTraceMode(value=" + i + ')';
    }

    public boolean equals(Object other) {
        return m2588equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m2590hashCodeimpl(this.value);
    }

    public String toString() {
        return m2591toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0011\u0010\r\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0007R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceMode$Companion;", "", "<init>", "()V", "None", "Landroidx/compose/runtime/tooling/ComposeStackTraceMode;", "getNone-MD5MrJc", "()I", "I", "GroupKeys", "getGroupKeys-MD5MrJc", "SourceInformation", "getSourceInformation-MD5MrJc", "Auto", "getAuto-MD5MrJc", "isMinified", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getAuto-MD5MrJc, reason: not valid java name */
        public final int m2593getAutoMD5MrJc() {
            return ComposeStackTraceMode.access$isMinified$cp() ? m2594getGroupKeysMD5MrJc() : m2595getNoneMD5MrJc();
        }

        /* JADX INFO: renamed from: getGroupKeys-MD5MrJc, reason: not valid java name */
        public final int m2594getGroupKeysMD5MrJc() {
            return ComposeStackTraceMode.GroupKeys;
        }

        /* JADX INFO: renamed from: getNone-MD5MrJc, reason: not valid java name */
        public final int m2595getNoneMD5MrJc() {
            return ComposeStackTraceMode.None;
        }

        /* JADX INFO: renamed from: getSourceInformation-MD5MrJc, reason: not valid java name */
        public final int m2596getSourceInformationMD5MrJc() {
            return ComposeStackTraceMode.SourceInformation;
        }

        private Companion() {
        }
    }
}
