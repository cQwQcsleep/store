package androidx.compose.ui.platform;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0001\u0018\u0000  2\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00128V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001c¨\u0006!"}, d2 = {"Landroidx/compose/ui/platform/WindowInfoImpl;", "Landroidx/compose/ui/platform/WindowInfo;", "<init>", "()V", "_containerSize", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/unit/IntSize;", "_containerDpSize", "Landroidx/compose/ui/unit/DpSize;", "<set-?>", "", "isWindowFocused", "()Z", "setWindowFocused", "(Z)V", "isWindowFocused$delegate", "Landroidx/compose/runtime/MutableState;", "value", "Landroidx/compose/ui/input/pointer/PointerKeyboardModifiers;", "keyboardModifiers", "getKeyboardModifiers-k7X9c1A", "()I", "setKeyboardModifiers-5xRPYO0", "(I)V", "containerSize", "getContainerSize-YbymL2g", "()J", "setContainerSize-ozmzZPI", "(J)V", "containerDpSize", "getContainerDpSize-MYxV2XQ", "setContainerDpSize-EaSLcWc", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowInfoImpl implements WindowInfo {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MutableState<PointerKeyboardModifiers> GlobalKeyboardModifiers = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(PointerKeyboardModifiers.m4519boximpl(PointerEvent_androidKt.EmptyPointerKeyboardModifiers()), null, 2, null);
    private final MutableState<IntSize> _containerSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m6185boximpl(IntSize.INSTANCE.m6198getZeroYbymL2g()), null, 2, null);
    private final MutableState<DpSize> _containerDpSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(DpSize.m6108boximpl(DpSize.INSTANCE.m6130getZeroMYxV2XQ()), null, 2, null);

    /* JADX INFO: renamed from: isWindowFocused$delegate, reason: from kotlin metadata */
    private final MutableState isWindowFocused = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

    @Override // androidx.compose.ui.platform.WindowInfo
    /* JADX INFO: renamed from: getContainerDpSize-MYxV2XQ */
    public long mo5180getContainerDpSizeMYxV2XQ() {
        return this._containerDpSize.getValue().getPackedValue();
    }

    @Override // androidx.compose.ui.platform.WindowInfo
    /* JADX INFO: renamed from: getContainerSize-YbymL2g */
    public long mo5181getContainerSizeYbymL2g() {
        return this._containerSize.getValue().m6197unboximpl();
    }

    @Override // androidx.compose.ui.platform.WindowInfo
    /* JADX INFO: renamed from: getKeyboardModifiers-k7X9c1A */
    public int mo5182getKeyboardModifiersk7X9c1A() {
        return GlobalKeyboardModifiers.getValue().getPackedValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.platform.WindowInfo
    public boolean isWindowFocused() {
        return ((Boolean) this.isWindowFocused.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: setContainerDpSize-EaSLcWc, reason: not valid java name */
    public void m5222setContainerDpSizeEaSLcWc(long j) {
        this._containerDpSize.setValue(DpSize.m6108boximpl(j));
    }

    /* JADX INFO: renamed from: setContainerSize-ozmzZPI, reason: not valid java name */
    public void m5223setContainerSizeozmzZPI(long j) {
        this._containerSize.setValue(IntSize.m6185boximpl(j));
    }

    /* JADX INFO: renamed from: setKeyboardModifiers-5xRPYO0, reason: not valid java name */
    public void m5224setKeyboardModifiers5xRPYO0(int i) {
        GlobalKeyboardModifiers.setValue(PointerKeyboardModifiers.m4519boximpl(i));
    }

    public void setWindowFocused(boolean z) {
        this.isWindowFocused.setValue(Boolean.valueOf(z));
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/WindowInfoImpl$Companion;", "", "<init>", "()V", "GlobalKeyboardModifiers", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/input/pointer/PointerKeyboardModifiers;", "getGlobalKeyboardModifiers$ui", "()Landroidx/compose/runtime/MutableState;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MutableState<PointerKeyboardModifiers> getGlobalKeyboardModifiers$ui() {
            return WindowInfoImpl.GlobalKeyboardModifiers;
        }

        private Companion() {
        }
    }
}
