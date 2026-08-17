package androidx.compose.ui.layout;

import androidx.collection.IntList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeLayoutPausableCompositionException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "operations", "Landroidx/collection/IntList;", "slotId", "", "cause", "", "<init>", "(Landroidx/collection/IntList;Ljava/lang/Object;Ljava/lang/Throwable;)V", "operationsList", "", "", "message", "getMessage$annotations", "()V", "getMessage", "()Ljava/lang/String;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class SubcomposeLayoutPausableCompositionException extends IllegalStateException {
    private final IntList operations;
    private final Object slotId;

    public SubcomposeLayoutPausableCompositionException(IntList intList, Object obj, Throwable th) {
        super(th);
        this.operations = intList;
        this.slotId = obj;
    }

    public static /* synthetic */ void getMessage$annotations() {
    }

    private final List<String> operationsList() {
        String str;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        for (int i = this.operations._size - 1; i >= 0; i += -1) {
            int i2 = this.operations.get(i);
            int iM4693constructorimpl = SLOperation.m4693constructorimpl(i2);
            SLOperation.Companion companion = SLOperation.INSTANCE;
            if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4700getCancelPausedPrecompositionNjRlDlw())) {
                str = "CancelPausedPrecomposition";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4706getReuseForceSyncDeactivationNjRlDlw())) {
                str = "ReuseForceSyncDeactivation";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4707getReuseScheduleOutOfFrameDeactivationNjRlDlw())) {
                str = "ReuseScheduleOutOfFrameDeactivation";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4708getReuseSyncDeactivationNjRlDlw())) {
                str = "ReuseSyncDeactivation";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4705getReuseDeactivationViaHostNjRlDlw())) {
                str = "ReuseDeactivationViaHost";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4716getTookFromPrecomposeMapNjRlDlw())) {
                str = "TookFromPrecomposeMap";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4712getSubcomposeNjRlDlw())) {
                str = "Subcompose";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4714getSubcomposeNewNjRlDlw())) {
                str = "SubcomposeNew";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4715getSubcomposePausableNjRlDlw())) {
                str = "SubcomposePausable";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4713getSubcomposeForceReuseNjRlDlw())) {
                str = "SubcomposeForceReuse";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4701getDeactivateOutOfFrameNjRlDlw())) {
                str = "DeactivateOutOfFrame";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4702getDeactivateOutOfFrameCancelledNjRlDlw())) {
                str = "DeactivateOutOfFrameCancelled";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4710getSlotToReusedFromOnDeactivateNjRlDlw())) {
                str = "SlotToReusedFromOnDeactivate";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4711getSlotToReusedFromOnReuseNjRlDlw())) {
                str = "SlotToReusedFromOnReuse";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4709getReusedNjRlDlw())) {
                str = "Reused";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4704getResumePausedNjRlDlw())) {
                str = "ResumePaused";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4703getPausePausedNjRlDlw())) {
                str = "PausePaused";
            } else if (SLOperation.m4695equalsimpl0(iM4693constructorimpl, companion.m4699getApplyPausedNjRlDlw())) {
                str = "ApplyPaused";
            } else {
                str = "Unexpected " + i2;
            }
            listCreateListBuilder.add(i + ": " + str);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return StringsKt.trimMargin$default("\n            |slotid=" + this.slotId + ". Last operations:\n            |" + CollectionsKt.joinToString$default(operationsList(), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + "\n            ", (String) null, 1, (Object) null);
    }
}
