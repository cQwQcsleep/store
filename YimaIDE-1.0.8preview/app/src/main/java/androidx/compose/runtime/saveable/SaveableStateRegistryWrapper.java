package androidx.compose.runtime.saveable;

import android.os.Bundle;
import androidx.compose.runtime.saveable.SaveableStateRegistryWrapper;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.os.BundleKt;
import androidx.lifecycle.LifecycleRegistry;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.SavedStateWriter;
import com.reandroid.apk.ApkUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000b\u001a\u00020\u0007H\u0002J\u0018\u0010\u0011\u001a\u00020\r2\u000e\u0010\u0012\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014H\u0002J\u0011\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0096\u0001J\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH\u0096\u0001J\u001d\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u001f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\"0!H\u0096\u0001J!\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u001f2\u000e\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0&H\u0096\u0001R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006'"}, d2 = {"Landroidx/compose/runtime/saveable/SaveableStateRegistryWrapper;", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "Landroidx/savedstate/SavedStateRegistryOwner;", ApkUtil.DEF_MODULE_NAME, "<init>", "(Landroidx/compose/runtime/saveable/SaveableStateRegistry;)V", "lifecycle", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycle", "()Landroidx/lifecycle/LifecycleRegistry;", "_lifecycle", "getOrInitLifecycle", "controller", "Landroidx/savedstate/SavedStateRegistryController;", "getController", "()Landroidx/savedstate/SavedStateRegistryController;", "_controller", "getOrInitController", "savedState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "getSavedStateRegistry", "()Landroidx/savedstate/SavedStateRegistry;", "canBeSaved", "", "value", "", "consumeRestored", "key", "", "performSave", "", "", "registerProvider", "Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "valueProvider", "Lkotlin/Function0;", "runtime-saveable"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SaveableStateRegistryWrapper implements SaveableStateRegistry, SavedStateRegistryOwner {
    public static final int $stable = 8;
    private final /* synthetic */ SaveableStateRegistry $$delegate_0;
    private SavedStateRegistryController _controller;
    private LifecycleRegistry _lifecycle;

    public SaveableStateRegistryWrapper(SaveableStateRegistry saveableStateRegistry) {
        this.$$delegate_0 = saveableStateRegistry;
        Object objConsumeRestored = consumeRestored("androidx.savedstate.SavedStateRegistry");
        Bundle bundle = objConsumeRestored instanceof Bundle ? (Bundle) objConsumeRestored : null;
        if (bundle != null) {
            getOrInitController(bundle);
        }
        registerProvider("androidx.savedstate.SavedStateRegistry", new Function0() { // from class: trc
            public final Object invoke() {
                return SaveableStateRegistryWrapper.a(this.b);
            }
        });
    }

    public static Object a(SaveableStateRegistryWrapper saveableStateRegistryWrapper) {
        Pair[] pairArr;
        SavedStateRegistryController savedStateRegistryController = saveableStateRegistryWrapper._controller;
        if (savedStateRegistryController == null) {
            return null;
        }
        Map mapEmptyMap = MapsKt.emptyMap();
        if (mapEmptyMap.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList = new ArrayList(mapEmptyMap.size());
            for (Map.Entry entry : mapEmptyMap.entrySet()) {
                arrayList.add(TuplesKt.to((String) entry.getKey(), entry.getValue()));
            }
            pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m6396constructorimpl(bundleBundleOf);
        savedStateRegistryController.performSave(bundleBundleOf);
        if (SavedStateReader.m6388isEmptyimpl(SavedStateReader.m6310constructorimpl(bundleBundleOf))) {
            return null;
        }
        return bundleBundleOf;
    }

    private final SavedStateRegistryController getController() {
        return getOrInitController(null);
    }

    private final SavedStateRegistryController getOrInitController(Bundle savedState) {
        SavedStateRegistryController savedStateRegistryController = this._controller;
        if (savedStateRegistryController != null) {
            return savedStateRegistryController;
        }
        SavedStateRegistryController savedStateRegistryControllerCreate = SavedStateRegistryController.INSTANCE.create(this);
        this._controller = savedStateRegistryControllerCreate;
        savedStateRegistryControllerCreate.performRestore(savedState);
        return savedStateRegistryControllerCreate;
    }

    private final LifecycleRegistry getOrInitLifecycle() {
        LifecycleRegistry lifecycleRegistry = this._lifecycle;
        if (lifecycleRegistry != null) {
            return lifecycleRegistry;
        }
        LifecycleRegistry lifecycleRegistryCreateUnsafe = LifecycleRegistry.INSTANCE.createUnsafe(this);
        this._lifecycle = lifecycleRegistryCreateUnsafe;
        return lifecycleRegistryCreateUnsafe;
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object value) {
        return this.$$delegate_0.canBeSaved(value);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String key) {
        return this.$$delegate_0.consumeRestored(key);
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        return getController().getSavedStateRegistry();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map<String, List<Object>> performSave() {
        return this.$$delegate_0.performSave();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(String key, Function0<? extends Object> valueProvider) {
        return this.$$delegate_0.registerProvider(key, valueProvider);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public LifecycleRegistry getLifecycle() {
        return getOrInitLifecycle();
    }
}
