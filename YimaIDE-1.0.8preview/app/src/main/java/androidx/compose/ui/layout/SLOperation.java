package androidx.compose.ui.layout;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0083@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/layout/SLOperation;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "equals", "", "other", "hashCode", "toString", "", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
final class SLOperation {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int CancelPausedPrecomposition = m4693constructorimpl(0);
    private static final int ReuseForceSyncDeactivation = m4693constructorimpl(1);
    private static final int ReuseScheduleOutOfFrameDeactivation = m4693constructorimpl(2);
    private static final int ReuseSyncDeactivation = m4693constructorimpl(3);
    private static final int ReuseDeactivationViaHost = m4693constructorimpl(4);
    private static final int TookFromPrecomposeMap = m4693constructorimpl(5);
    private static final int Subcompose = m4693constructorimpl(6);
    private static final int SubcomposeNew = m4693constructorimpl(7);
    private static final int SubcomposePausable = m4693constructorimpl(8);
    private static final int SubcomposeForceReuse = m4693constructorimpl(9);
    private static final int DeactivateOutOfFrame = m4693constructorimpl(10);
    private static final int DeactivateOutOfFrameCancelled = m4693constructorimpl(11);
    private static final int SlotToReusedFromOnDeactivate = m4693constructorimpl(12);
    private static final int SlotToReusedFromOnReuse = m4693constructorimpl(13);
    private static final int Reused = m4693constructorimpl(14);
    private static final int ResumePaused = m4693constructorimpl(15);
    private static final int PausePaused = m4693constructorimpl(16);
    private static final int ApplyPaused = m4693constructorimpl(17);

    private /* synthetic */ SLOperation(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SLOperation m4692boximpl(int i) {
        return new SLOperation(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m4693constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4694equalsimpl(int i, Object obj) {
        return (obj instanceof SLOperation) && i == ((SLOperation) obj).m4698unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4695equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m4696hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m4697toStringimpl(int i) {
        return "SLOperation(value=" + i + ')';
    }

    public boolean equals(Object other) {
        return m4694equalsimpl(this.value, other);
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        return m4696hashCodeimpl(this.value);
    }

    public String toString() {
        return m4697toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m4698unboximpl() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b&\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\"\u0010\u0007R\u0013\u0010#\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b$\u0010\u0007R\u0013\u0010%\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b&\u0010\u0007R\u0013\u0010'\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b(\u0010\u0007R\u0013\u0010)\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b*\u0010\u0007¨\u0006+"}, d2 = {"Landroidx/compose/ui/layout/SLOperation$Companion;", "", "<init>", "()V", "CancelPausedPrecomposition", "Landroidx/compose/ui/layout/SLOperation;", "getCancelPausedPrecomposition-NjRlDlw", "()I", "I", "ReuseForceSyncDeactivation", "getReuseForceSyncDeactivation-NjRlDlw", "ReuseScheduleOutOfFrameDeactivation", "getReuseScheduleOutOfFrameDeactivation-NjRlDlw", "ReuseSyncDeactivation", "getReuseSyncDeactivation-NjRlDlw", "ReuseDeactivationViaHost", "getReuseDeactivationViaHost-NjRlDlw", "TookFromPrecomposeMap", "getTookFromPrecomposeMap-NjRlDlw", "Subcompose", "getSubcompose-NjRlDlw", "SubcomposeNew", "getSubcomposeNew-NjRlDlw", "SubcomposePausable", "getSubcomposePausable-NjRlDlw", "SubcomposeForceReuse", "getSubcomposeForceReuse-NjRlDlw", "DeactivateOutOfFrame", "getDeactivateOutOfFrame-NjRlDlw", "DeactivateOutOfFrameCancelled", "getDeactivateOutOfFrameCancelled-NjRlDlw", "SlotToReusedFromOnDeactivate", "getSlotToReusedFromOnDeactivate-NjRlDlw", "SlotToReusedFromOnReuse", "getSlotToReusedFromOnReuse-NjRlDlw", "Reused", "getReused-NjRlDlw", "ResumePaused", "getResumePaused-NjRlDlw", "PausePaused", "getPausePaused-NjRlDlw", "ApplyPaused", "getApplyPaused-NjRlDlw", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getApplyPaused-NjRlDlw, reason: not valid java name */
        public final int m4699getApplyPausedNjRlDlw() {
            return SLOperation.ApplyPaused;
        }

        /* JADX INFO: renamed from: getCancelPausedPrecomposition-NjRlDlw, reason: not valid java name */
        public final int m4700getCancelPausedPrecompositionNjRlDlw() {
            return SLOperation.CancelPausedPrecomposition;
        }

        /* JADX INFO: renamed from: getDeactivateOutOfFrame-NjRlDlw, reason: not valid java name */
        public final int m4701getDeactivateOutOfFrameNjRlDlw() {
            return SLOperation.DeactivateOutOfFrame;
        }

        /* JADX INFO: renamed from: getDeactivateOutOfFrameCancelled-NjRlDlw, reason: not valid java name */
        public final int m4702getDeactivateOutOfFrameCancelledNjRlDlw() {
            return SLOperation.DeactivateOutOfFrameCancelled;
        }

        /* JADX INFO: renamed from: getPausePaused-NjRlDlw, reason: not valid java name */
        public final int m4703getPausePausedNjRlDlw() {
            return SLOperation.PausePaused;
        }

        /* JADX INFO: renamed from: getResumePaused-NjRlDlw, reason: not valid java name */
        public final int m4704getResumePausedNjRlDlw() {
            return SLOperation.ResumePaused;
        }

        /* JADX INFO: renamed from: getReuseDeactivationViaHost-NjRlDlw, reason: not valid java name */
        public final int m4705getReuseDeactivationViaHostNjRlDlw() {
            return SLOperation.ReuseDeactivationViaHost;
        }

        /* JADX INFO: renamed from: getReuseForceSyncDeactivation-NjRlDlw, reason: not valid java name */
        public final int m4706getReuseForceSyncDeactivationNjRlDlw() {
            return SLOperation.ReuseForceSyncDeactivation;
        }

        /* JADX INFO: renamed from: getReuseScheduleOutOfFrameDeactivation-NjRlDlw, reason: not valid java name */
        public final int m4707getReuseScheduleOutOfFrameDeactivationNjRlDlw() {
            return SLOperation.ReuseScheduleOutOfFrameDeactivation;
        }

        /* JADX INFO: renamed from: getReuseSyncDeactivation-NjRlDlw, reason: not valid java name */
        public final int m4708getReuseSyncDeactivationNjRlDlw() {
            return SLOperation.ReuseSyncDeactivation;
        }

        /* JADX INFO: renamed from: getReused-NjRlDlw, reason: not valid java name */
        public final int m4709getReusedNjRlDlw() {
            return SLOperation.Reused;
        }

        /* JADX INFO: renamed from: getSlotToReusedFromOnDeactivate-NjRlDlw, reason: not valid java name */
        public final int m4710getSlotToReusedFromOnDeactivateNjRlDlw() {
            return SLOperation.SlotToReusedFromOnDeactivate;
        }

        /* JADX INFO: renamed from: getSlotToReusedFromOnReuse-NjRlDlw, reason: not valid java name */
        public final int m4711getSlotToReusedFromOnReuseNjRlDlw() {
            return SLOperation.SlotToReusedFromOnReuse;
        }

        /* JADX INFO: renamed from: getSubcompose-NjRlDlw, reason: not valid java name */
        public final int m4712getSubcomposeNjRlDlw() {
            return SLOperation.Subcompose;
        }

        /* JADX INFO: renamed from: getSubcomposeForceReuse-NjRlDlw, reason: not valid java name */
        public final int m4713getSubcomposeForceReuseNjRlDlw() {
            return SLOperation.SubcomposeForceReuse;
        }

        /* JADX INFO: renamed from: getSubcomposeNew-NjRlDlw, reason: not valid java name */
        public final int m4714getSubcomposeNewNjRlDlw() {
            return SLOperation.SubcomposeNew;
        }

        /* JADX INFO: renamed from: getSubcomposePausable-NjRlDlw, reason: not valid java name */
        public final int m4715getSubcomposePausableNjRlDlw() {
            return SLOperation.SubcomposePausable;
        }

        /* JADX INFO: renamed from: getTookFromPrecomposeMap-NjRlDlw, reason: not valid java name */
        public final int m4716getTookFromPrecomposeMapNjRlDlw() {
            return SLOperation.TookFromPrecomposeMap;
        }

        private Companion() {
        }
    }
}
