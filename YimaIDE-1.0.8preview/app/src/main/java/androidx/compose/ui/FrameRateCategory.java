package androidx.compose.ui;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/FrameRateCategory;", "", "value", "", "constructor-impl", "(F)F", "toString", "", "toString-impl", "(F)Ljava/lang/String;", "equals", "", "other", "hashCode", "", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class FrameRateCategory {
    private final float value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float Default = m2599constructorimpl(Float.NaN);
    private static final float Normal = m2599constructorimpl(-3.0f);
    private static final float High = m2599constructorimpl(-4.0f);

    private /* synthetic */ FrameRateCategory(float f) {
        this.value = f;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FrameRateCategory m2598boximpl(float f) {
        return new FrameRateCategory(f);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static float m2599constructorimpl(float f) {
        return f;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2600equalsimpl(float f, Object obj) {
        return (obj instanceof FrameRateCategory) && Float.compare(f, ((FrameRateCategory) obj).getValue()) == 0;
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2601equalsimpl0(float f, float f2) {
        return Float.compare(f, f2) == 0;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2602hashCodeimpl(float f) {
        return Float.hashCode(f);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2603toStringimpl(float f) {
        if (f == -3.0f) {
            return "Normal";
        }
        return f == -4.0f ? "High" : "Default";
    }

    public boolean equals(Object other) {
        return m2600equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m2602hashCodeimpl(this.value);
    }

    public String toString() {
        return m2603toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ float getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/ui/FrameRateCategory$Companion;", "", "<init>", "()V", "Default", "Landroidx/compose/ui/FrameRateCategory;", "getDefault-NSsRyOo", "()F", "F", "Normal", "getNormal-NSsRyOo", "High", "getHigh-NSsRyOo", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getDefault-NSsRyOo, reason: not valid java name */
        public final float m2605getDefaultNSsRyOo() {
            return FrameRateCategory.Default;
        }

        /* JADX INFO: renamed from: getHigh-NSsRyOo, reason: not valid java name */
        public final float m2606getHighNSsRyOo() {
            return FrameRateCategory.High;
        }

        /* JADX INFO: renamed from: getNormal-NSsRyOo, reason: not valid java name */
        public final float m2607getNormalNSsRyOo() {
            return FrameRateCategory.Normal;
        }

        private Companion() {
        }
    }
}
