package androidx.compose.ui.hapticfeedback;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/hapticfeedback/HapticFeedbackType;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class HapticFeedbackType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    private /* synthetic */ HapticFeedbackType(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ HapticFeedbackType m3909boximpl(int i) {
        return new HapticFeedbackType(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m3910constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3911equalsimpl(int i, Object obj) {
        return (obj instanceof HapticFeedbackType) && i == ((HapticFeedbackType) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3912equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3913hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3914toStringimpl(int i) {
        Companion companion = INSTANCE;
        if (m3912equalsimpl0(i, companion.m3916getConfirm5zf0vsI())) {
            return "Confirm";
        }
        if (m3912equalsimpl0(i, companion.m3917getContextClick5zf0vsI())) {
            return "ContextClick";
        }
        if (m3912equalsimpl0(i, companion.m3918getGestureEnd5zf0vsI())) {
            return "GestureEnd";
        }
        if (m3912equalsimpl0(i, companion.m3919getGestureThresholdActivate5zf0vsI())) {
            return "GestureThresholdActivate";
        }
        if (m3912equalsimpl0(i, companion.m3920getKeyboardTap5zf0vsI())) {
            return "KeyboardTap";
        }
        if (m3912equalsimpl0(i, companion.m3921getLongPress5zf0vsI())) {
            return "LongPress";
        }
        if (m3912equalsimpl0(i, companion.m3922getReject5zf0vsI())) {
            return "Reject";
        }
        if (m3912equalsimpl0(i, companion.m3923getSegmentFrequentTick5zf0vsI())) {
            return "SegmentFrequentTick";
        }
        if (m3912equalsimpl0(i, companion.m3924getSegmentTick5zf0vsI())) {
            return "SegmentTick";
        }
        if (m3912equalsimpl0(i, companion.m3925getTextHandleMove5zf0vsI())) {
            return "TextHandleMove";
        }
        if (m3912equalsimpl0(i, companion.m3926getToggleOff5zf0vsI())) {
            return "ToggleOff";
        }
        if (m3912equalsimpl0(i, companion.m3927getToggleOn5zf0vsI())) {
            return "ToggleOn";
        }
        return m3912equalsimpl0(i, companion.m3928getVirtualKey5zf0vsI()) ? "VirtualKey" : "Invalid";
    }

    public boolean equals(Object other) {
        return m3911equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m3913hashCodeimpl(this.value);
    }

    public String toString() {
        return m3914toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050!R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0007¨\u0006\""}, d2 = {"Landroidx/compose/ui/hapticfeedback/HapticFeedbackType$Companion;", "", "<init>", "()V", "Confirm", "Landroidx/compose/ui/hapticfeedback/HapticFeedbackType;", "getConfirm-5zf0vsI", "()I", "ContextClick", "getContextClick-5zf0vsI", "GestureEnd", "getGestureEnd-5zf0vsI", "GestureThresholdActivate", "getGestureThresholdActivate-5zf0vsI", "KeyboardTap", "getKeyboardTap-5zf0vsI", "LongPress", "getLongPress-5zf0vsI", "Reject", "getReject-5zf0vsI", "SegmentFrequentTick", "getSegmentFrequentTick-5zf0vsI", "SegmentTick", "getSegmentTick-5zf0vsI", "TextHandleMove", "getTextHandleMove-5zf0vsI", "ToggleOff", "getToggleOff-5zf0vsI", "ToggleOn", "getToggleOn-5zf0vsI", "VirtualKey", "getVirtualKey-5zf0vsI", "values", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getConfirm-5zf0vsI, reason: not valid java name */
        public final int m3916getConfirm5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3929getConfirm5zf0vsI();
        }

        /* JADX INFO: renamed from: getContextClick-5zf0vsI, reason: not valid java name */
        public final int m3917getContextClick5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3930getContextClick5zf0vsI();
        }

        /* JADX INFO: renamed from: getGestureEnd-5zf0vsI, reason: not valid java name */
        public final int m3918getGestureEnd5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3931getGestureEnd5zf0vsI();
        }

        /* JADX INFO: renamed from: getGestureThresholdActivate-5zf0vsI, reason: not valid java name */
        public final int m3919getGestureThresholdActivate5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3932getGestureThresholdActivate5zf0vsI();
        }

        /* JADX INFO: renamed from: getKeyboardTap-5zf0vsI, reason: not valid java name */
        public final int m3920getKeyboardTap5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3933getKeyboardTap5zf0vsI();
        }

        /* JADX INFO: renamed from: getLongPress-5zf0vsI, reason: not valid java name */
        public final int m3921getLongPress5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3934getLongPress5zf0vsI();
        }

        /* JADX INFO: renamed from: getReject-5zf0vsI, reason: not valid java name */
        public final int m3922getReject5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3935getReject5zf0vsI();
        }

        /* JADX INFO: renamed from: getSegmentFrequentTick-5zf0vsI, reason: not valid java name */
        public final int m3923getSegmentFrequentTick5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3936getSegmentFrequentTick5zf0vsI();
        }

        /* JADX INFO: renamed from: getSegmentTick-5zf0vsI, reason: not valid java name */
        public final int m3924getSegmentTick5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3937getSegmentTick5zf0vsI();
        }

        /* JADX INFO: renamed from: getTextHandleMove-5zf0vsI, reason: not valid java name */
        public final int m3925getTextHandleMove5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3938getTextHandleMove5zf0vsI();
        }

        /* JADX INFO: renamed from: getToggleOff-5zf0vsI, reason: not valid java name */
        public final int m3926getToggleOff5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3939getToggleOff5zf0vsI();
        }

        /* JADX INFO: renamed from: getToggleOn-5zf0vsI, reason: not valid java name */
        public final int m3927getToggleOn5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3940getToggleOn5zf0vsI();
        }

        /* JADX INFO: renamed from: getVirtualKey-5zf0vsI, reason: not valid java name */
        public final int m3928getVirtualKey5zf0vsI() {
            return PlatformHapticFeedbackType.INSTANCE.m3941getVirtualKey5zf0vsI();
        }

        public final List<HapticFeedbackType> values() {
            return CollectionsKt.listOf(new HapticFeedbackType[]{HapticFeedbackType.m3909boximpl(m3916getConfirm5zf0vsI()), HapticFeedbackType.m3909boximpl(m3917getContextClick5zf0vsI()), HapticFeedbackType.m3909boximpl(m3918getGestureEnd5zf0vsI()), HapticFeedbackType.m3909boximpl(m3919getGestureThresholdActivate5zf0vsI()), HapticFeedbackType.m3909boximpl(m3920getKeyboardTap5zf0vsI()), HapticFeedbackType.m3909boximpl(m3921getLongPress5zf0vsI()), HapticFeedbackType.m3909boximpl(m3922getReject5zf0vsI()), HapticFeedbackType.m3909boximpl(m3923getSegmentFrequentTick5zf0vsI()), HapticFeedbackType.m3909boximpl(m3924getSegmentTick5zf0vsI()), HapticFeedbackType.m3909boximpl(m3925getTextHandleMove5zf0vsI()), HapticFeedbackType.m3909boximpl(m3926getToggleOff5zf0vsI()), HapticFeedbackType.m3909boximpl(m3927getToggleOn5zf0vsI()), HapticFeedbackType.m3909boximpl(m3928getVirtualKey5zf0vsI())});
        }

        private Companion() {
        }
    }
}
