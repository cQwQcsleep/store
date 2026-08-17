package androidx.compose.runtime.retain;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.retain.RetainedValuesStoreRegistryKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"retainRetainedValuesStoreRegistry", "Landroidx/compose/runtime/retain/RetainedValuesStoreRegistry;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/retain/RetainedValuesStoreRegistry;", "runtime-retain"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RetainedValuesStoreRegistryKt {
    public static final RetainedValuesStoreRegistry retainRetainedValuesStoreRegistry(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1800021473, i, -1, "androidx.compose.runtime.retain.retainRetainedValuesStoreRegistry (RetainedValuesStoreRegistry.kt:145)");
        }
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: ajc
                public final Object invoke() {
                    return RetainedValuesStoreRegistryKt.retainRetainedValuesStoreRegistry$lambda$0$0();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        RetainedValuesStoreRegistry retainedValuesStoreRegistry = ((RetainedValuesStoreRegistryWrapper) RetainKt.retain(RetainedValuesStoreRegistryWrapper.class.getName().hashCode(), (Function0) objRememberedValue, composer, 48)).getRetainedValuesStoreRegistry();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return retainedValuesStoreRegistry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RetainedValuesStoreRegistryWrapper retainRetainedValuesStoreRegistry$lambda$0$0() {
        return new RetainedValuesStoreRegistryWrapper();
    }
}
