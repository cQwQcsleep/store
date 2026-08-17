package androidx.compose.animation;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\rR+\u0010\u0016\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u0017\u0010\u000b\"\u0004\b\u0018\u0010\r¨\u0006\u001a"}, d2 = {"Landroidx/compose/animation/TargetData;", "", "size", "Landroidx/compose/ui/geometry/Size;", "initialMfrOffset", "Landroidx/compose/ui/geometry/Offset;", "targetStructuralOffset", "<init>", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "<set-?>", "getSize-NH-jbRc", "()J", "setSize-uvyYCjk", "(J)V", "size$delegate", "Landroidx/compose/runtime/MutableState;", "getInitialMfrOffset-F1C5BW0", "setInitialMfrOffset-k-4lQ0M", "initialMfrOffset$delegate", "getTargetStructuralOffset-F1C5BW0", "setTargetStructuralOffset-k-4lQ0M", "targetStructuralOffset$delegate", "currentMfrOffset", "getCurrentMfrOffset-F1C5BW0", "setCurrentMfrOffset-k-4lQ0M", "currentMfrOffset$delegate", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TargetData {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: currentMfrOffset$delegate, reason: from kotlin metadata */
    private final MutableState currentMfrOffset;

    /* JADX INFO: renamed from: initialMfrOffset$delegate, reason: from kotlin metadata */
    private final MutableState initialMfrOffset;

    /* JADX INFO: renamed from: size$delegate, reason: from kotlin metadata */
    private final MutableState size;

    /* JADX INFO: renamed from: targetStructuralOffset$delegate, reason: from kotlin metadata */
    private final MutableState targetStructuralOffset;

    private TargetData(long j, long j2, long j3) {
        this.size = SnapshotStateKt.mutableStateOf$default(Size.box-impl(j), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.initialMfrOffset = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(j2), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.targetStructuralOffset = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(j3), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.currentMfrOffset = SnapshotStateKt.mutableStateOf$default(Offset.box-impl(j2), (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    /* JADX INFO: renamed from: getCurrentMfrOffset-F1C5BW0, reason: not valid java name */
    public final long m181getCurrentMfrOffsetF1C5BW0() {
        return ((Offset) this.currentMfrOffset.getValue()).unbox-impl();
    }

    /* JADX INFO: renamed from: getInitialMfrOffset-F1C5BW0, reason: not valid java name */
    public final long m182getInitialMfrOffsetF1C5BW0() {
        return ((Offset) this.initialMfrOffset.getValue()).unbox-impl();
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final long m183getSizeNHjbRc() {
        return ((Size) this.size.getValue()).unbox-impl();
    }

    /* JADX INFO: renamed from: getTargetStructuralOffset-F1C5BW0, reason: not valid java name */
    public final long m184getTargetStructuralOffsetF1C5BW0() {
        return ((Offset) this.targetStructuralOffset.getValue()).unbox-impl();
    }

    /* JADX INFO: renamed from: setCurrentMfrOffset-k-4lQ0M, reason: not valid java name */
    public final void m185setCurrentMfrOffsetk4lQ0M(long j) {
        this.currentMfrOffset.setValue(Offset.box-impl(j));
    }

    /* JADX INFO: renamed from: setInitialMfrOffset-k-4lQ0M, reason: not valid java name */
    public final void m186setInitialMfrOffsetk4lQ0M(long j) {
        this.initialMfrOffset.setValue(Offset.box-impl(j));
    }

    /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
    public final void m187setSizeuvyYCjk(long j) {
        this.size.setValue(Size.box-impl(j));
    }

    /* JADX INFO: renamed from: setTargetStructuralOffset-k-4lQ0M, reason: not valid java name */
    public final void m188setTargetStructuralOffsetk4lQ0M(long j) {
        this.targetStructuralOffset.setValue(Offset.box-impl(j));
    }

    public /* synthetic */ TargetData(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }
}
