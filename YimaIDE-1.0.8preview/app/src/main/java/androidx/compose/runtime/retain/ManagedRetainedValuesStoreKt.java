package androidx.compose.runtime.retain;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.retain.ManagedRetainedValuesStoreKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"retainManagedRetainedValuesStore", "Landroidx/compose/runtime/retain/ManagedRetainedValuesStore;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/retain/ManagedRetainedValuesStore;", "runtime-retain"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ManagedRetainedValuesStoreKt {
    public static final ManagedRetainedValuesStore retainManagedRetainedValuesStore(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(847199873, i, -1, "androidx.compose.runtime.retain.retainManagedRetainedValuesStore (ManagedRetainedValuesStore.kt:172)");
        }
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: et9
                public final Object invoke() {
                    return ManagedRetainedValuesStoreKt.retainManagedRetainedValuesStore$lambda$0$0();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ManagedRetainedValuesStore retainedValuesStore = ((RetainManagedRetainedValuesStoreWrapper) RetainKt.retain(RetainManagedRetainedValuesStoreWrapper.class.getName().hashCode(), (Function0) objRememberedValue, composer, 48)).getRetainedValuesStore();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return retainedValuesStore;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainManagedRetainedValuesStoreWrapper retainManagedRetainedValuesStore$lambda$0$0() {
        return new RetainManagedRetainedValuesStoreWrapper();
    }
}
