package androidx.compose.ui.text.input;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/input/ImeAction;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class ImeAction {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Unspecified = m5645constructorimpl(-1);
    private static final int Default = m5645constructorimpl(1);
    private static final int None = m5645constructorimpl(0);
    private static final int Go = m5645constructorimpl(2);
    private static final int Search = m5645constructorimpl(3);
    private static final int Send = m5645constructorimpl(4);
    private static final int Previous = m5645constructorimpl(5);
    private static final int Next = m5645constructorimpl(6);
    private static final int Done = m5645constructorimpl(7);

    private /* synthetic */ ImeAction(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ImeAction m5644boximpl(int i) {
        return new ImeAction(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m5645constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5646equalsimpl(int i, Object obj) {
        return (obj instanceof ImeAction) && i == ((ImeAction) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5647equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5648hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5649toStringimpl(int i) {
        if (m5647equalsimpl0(i, Unspecified)) {
            return "Unspecified";
        }
        if (m5647equalsimpl0(i, None)) {
            return "None";
        }
        if (m5647equalsimpl0(i, Default)) {
            return "Default";
        }
        if (m5647equalsimpl0(i, Go)) {
            return "Go";
        }
        if (m5647equalsimpl0(i, Search)) {
            return "Search";
        }
        if (m5647equalsimpl0(i, Send)) {
            return "Send";
        }
        if (m5647equalsimpl0(i, Previous)) {
            return "Previous";
        }
        if (m5647equalsimpl0(i, Next)) {
            return "Next";
        }
        return m5647equalsimpl0(i, Done) ? "Done" : "Invalid";
    }

    public boolean equals(Object other) {
        return m5646equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m5648hashCodeimpl(this.value);
    }

    public String toString() {
        return m5649toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001e\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\bR\u001e\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\bR\u001e\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\bR\u001e\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\bR\u001e\u0010\u0016\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0017\u0010\u0003\u001a\u0004\b\u0018\u0010\bR\u001e\u0010\u0019\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u001a\u0010\u0003\u001a\u0004\b\u001b\u0010\bR\u001e\u0010\u001c\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u001d\u0010\u0003\u001a\u0004\b\u001e\u0010\bR\u001e\u0010\u001f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b \u0010\u0003\u001a\u0004\b!\u0010\b¨\u0006\""}, d2 = {"Landroidx/compose/ui/text/input/ImeAction$Companion;", "", "<init>", "()V", "Unspecified", "Landroidx/compose/ui/text/input/ImeAction;", "getUnspecified-eUduSuo$annotations", "getUnspecified-eUduSuo", "()I", "I", "Default", "getDefault-eUduSuo$annotations", "getDefault-eUduSuo", "None", "getNone-eUduSuo$annotations", "getNone-eUduSuo", "Go", "getGo-eUduSuo$annotations", "getGo-eUduSuo", "Search", "getSearch-eUduSuo$annotations", "getSearch-eUduSuo", "Send", "getSend-eUduSuo$annotations", "getSend-eUduSuo", "Previous", "getPrevious-eUduSuo$annotations", "getPrevious-eUduSuo", "Next", "getNext-eUduSuo$annotations", "getNext-eUduSuo", "Done", "getDone-eUduSuo$annotations", "getDone-eUduSuo", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getDefault-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5651getDefaulteUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getDone-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5652getDoneeUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getGo-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5653getGoeUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getNext-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5654getNexteUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getNone-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5655getNoneeUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getPrevious-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5656getPreviouseUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getSearch-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5657getSearcheUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getSend-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5658getSendeUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getUnspecified-eUduSuo$annotations, reason: not valid java name */
        public static /* synthetic */ void m5659getUnspecifiedeUduSuo$annotations() {
        }

        /* JADX INFO: renamed from: getDefault-eUduSuo, reason: not valid java name */
        public final int m5660getDefaulteUduSuo() {
            return ImeAction.Default;
        }

        /* JADX INFO: renamed from: getDone-eUduSuo, reason: not valid java name */
        public final int m5661getDoneeUduSuo() {
            return ImeAction.Done;
        }

        /* JADX INFO: renamed from: getGo-eUduSuo, reason: not valid java name */
        public final int m5662getGoeUduSuo() {
            return ImeAction.Go;
        }

        /* JADX INFO: renamed from: getNext-eUduSuo, reason: not valid java name */
        public final int m5663getNexteUduSuo() {
            return ImeAction.Next;
        }

        /* JADX INFO: renamed from: getNone-eUduSuo, reason: not valid java name */
        public final int m5664getNoneeUduSuo() {
            return ImeAction.None;
        }

        /* JADX INFO: renamed from: getPrevious-eUduSuo, reason: not valid java name */
        public final int m5665getPreviouseUduSuo() {
            return ImeAction.Previous;
        }

        /* JADX INFO: renamed from: getSearch-eUduSuo, reason: not valid java name */
        public final int m5666getSearcheUduSuo() {
            return ImeAction.Search;
        }

        /* JADX INFO: renamed from: getSend-eUduSuo, reason: not valid java name */
        public final int m5667getSendeUduSuo() {
            return ImeAction.Send;
        }

        /* JADX INFO: renamed from: getUnspecified-eUduSuo, reason: not valid java name */
        public final int m5668getUnspecifiedeUduSuo() {
            return ImeAction.Unspecified;
        }

        private Companion() {
        }
    }
}
