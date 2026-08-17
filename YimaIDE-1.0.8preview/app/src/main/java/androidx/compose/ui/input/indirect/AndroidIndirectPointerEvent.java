package androidx.compose.ui.input.indirect;

import android.view.MotionEvent;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0001\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\t\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/input/indirect/AndroidIndirectPointerEvent;", "Landroidx/compose/ui/input/indirect/PlatformIndirectPointerEvent;", "changes", "", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/input/indirect/IndirectPointerEventType;", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "nativeEvent", "Landroid/view/MotionEvent;", "<init>", "(Ljava/util/List;IILandroid/view/MotionEvent;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getChanges", "()Ljava/util/List;", "getType-4ZHQPSE", "()I", "I", "getPrimaryDirectionalMotionAxis-nZO2Niw", "getNativeEvent$ui", "()Landroid/view/MotionEvent;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidIndirectPointerEvent implements PlatformIndirectPointerEvent {
    public static final int $stable = 8;
    private final List<IndirectPointerInputChange> changes;
    private final MotionEvent nativeEvent;
    private final int primaryDirectionalMotionAxis;
    private final int type;

    private AndroidIndirectPointerEvent(List<IndirectPointerInputChange> list, int i, int i2, MotionEvent motionEvent) {
        this.changes = list;
        this.type = i;
        this.primaryDirectionalMotionAxis = i2;
        this.nativeEvent = motionEvent;
        if (getChanges().isEmpty()) {
            w01.a("changes cannot be empty");
            throw null;
        }
    }

    @Override // androidx.compose.ui.input.indirect.IndirectPointerEvent
    public List<IndirectPointerInputChange> getChanges() {
        return this.changes;
    }

    /* JADX INFO: renamed from: getNativeEvent$ui, reason: from getter */
    public final MotionEvent getNativeEvent() {
        return this.nativeEvent;
    }

    @Override // androidx.compose.ui.input.indirect.IndirectPointerEvent
    /* JADX INFO: renamed from: getPrimaryDirectionalMotionAxis-nZO2Niw, reason: not valid java name and from getter */
    public int getPrimaryDirectionalMotionAxis() {
        return this.primaryDirectionalMotionAxis;
    }

    @Override // androidx.compose.ui.input.indirect.IndirectPointerEvent
    /* JADX INFO: renamed from: getType-4ZHQPSE, reason: not valid java name and from getter */
    public int getType() {
        return this.type;
    }

    public /* synthetic */ AndroidIndirectPointerEvent(List list, int i, int i2, MotionEvent motionEvent, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, i, i2, motionEvent);
    }
}
