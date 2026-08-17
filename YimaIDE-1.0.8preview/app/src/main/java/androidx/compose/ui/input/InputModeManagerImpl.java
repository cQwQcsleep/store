package androidx.compose.ui.input;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0011\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00038V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/input/InputModeManagerImpl;", "Landroidx/compose/ui/input/InputModeManager;", "initialInputMode", "Landroidx/compose/ui/input/InputMode;", "onRequestInputModeChange", "Lkotlin/Function1;", "", "<init>", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "<set-?>", "inputMode", "getInputMode-aOaMEAU", "()I", "setInputMode-iuPiT84", "(I)V", "inputMode$delegate", "Landroidx/compose/runtime/MutableState;", "requestInputMode", "requestInputMode-iuPiT84", "(I)Z", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class InputModeManagerImpl implements InputModeManager {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: inputMode$delegate, reason: from kotlin metadata */
    private final MutableState inputMode;
    private final Function1<InputMode, Boolean> onRequestInputModeChange;

    /* JADX WARN: Multi-variable type inference failed */
    private InputModeManagerImpl(int i, Function1<? super InputMode, Boolean> function1) {
        this.onRequestInputModeChange = function1;
        this.inputMode = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(InputMode.m3942boximpl(i), null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.input.InputModeManager
    /* JADX INFO: renamed from: getInputMode-aOaMEAU */
    public int mo3951getInputModeaOaMEAU() {
        return ((InputMode) this.inputMode.getValue()).getValue();
    }

    @Override // androidx.compose.ui.input.InputModeManager
    /* JADX INFO: renamed from: requestInputMode-iuPiT84 */
    public boolean mo3952requestInputModeiuPiT84(int inputMode) {
        return ((Boolean) this.onRequestInputModeChange.invoke(InputMode.m3942boximpl(inputMode))).booleanValue();
    }

    /* JADX INFO: renamed from: setInputMode-iuPiT84, reason: not valid java name */
    public void m3953setInputModeiuPiT84(int i) {
        this.inputMode.setValue(InputMode.m3942boximpl(i));
    }

    public /* synthetic */ InputModeManagerImpl(int i, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, function1);
    }
}
