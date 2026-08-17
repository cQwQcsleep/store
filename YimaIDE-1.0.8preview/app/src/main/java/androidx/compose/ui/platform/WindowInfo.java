package androidx.compose.ui.platform;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/WindowInfo;", "", "isWindowFocused", "", "()Z", "keyboardModifiers", "Landroidx/compose/ui/input/pointer/PointerKeyboardModifiers;", "getKeyboardModifiers-k7X9c1A", "()I", "containerSize", "Landroidx/compose/ui/unit/IntSize;", "getContainerSize-YbymL2g", "()J", "containerDpSize", "Landroidx/compose/ui/unit/DpSize;", "getContainerDpSize-MYxV2XQ", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface WindowInfo {
    /* JADX INFO: renamed from: getContainerDpSize-MYxV2XQ */
    default long mo5180getContainerDpSizeMYxV2XQ() {
        return DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
    }

    /* JADX INFO: renamed from: getContainerSize-YbymL2g */
    default long mo5181getContainerSizeYbymL2g() {
        return IntSize.m6188constructorimpl(-9223372034707292160L);
    }

    /* JADX INFO: renamed from: getKeyboardModifiers-k7X9c1A */
    default int mo5182getKeyboardModifiersk7X9c1A() {
        return WindowInfoImpl.INSTANCE.getGlobalKeyboardModifiers$ui().getValue().getPackedValue();
    }

    boolean isWindowFocused();
}
