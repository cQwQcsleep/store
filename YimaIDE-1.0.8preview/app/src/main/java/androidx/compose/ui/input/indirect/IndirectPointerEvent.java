package androidx.compose.ui.input.indirect;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\n\u0082\u0001\u0001\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "", "changes", "", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "getChanges", "()Ljava/util/List;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/input/indirect/IndirectPointerEventType;", "getType-4ZHQPSE", "()I", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "getPrimaryDirectionalMotionAxis-nZO2Niw", "Landroidx/compose/ui/input/indirect/PlatformIndirectPointerEvent;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface IndirectPointerEvent {
    List<IndirectPointerInputChange> getChanges();

    /* JADX INFO: renamed from: getPrimaryDirectionalMotionAxis-nZO2Niw */
    int mo3954getPrimaryDirectionalMotionAxisnZO2Niw();

    /* JADX INFO: renamed from: getType-4ZHQPSE */
    int mo3955getType4ZHQPSE();
}
