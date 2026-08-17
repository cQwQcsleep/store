package androidx.lifecycle.internal;

import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.os.BundleKt;
import androidx.lifecycle.internal.SavedStateHandleImpl;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0011\u001a\u00020\fH\u0007J\u0011\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004H\u0087\u0002J)\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0018\"\u0004\b\u0000\u0010\u00192\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u0002H\u0019H\u0007¢\u0006\u0002\u0010\u001bJ)\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00190\u000e\"\u0004\b\u0000\u0010\u00192\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u0002H\u0019H\u0007¢\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u001fH\u0007J\u001e\u0010 \u001a\u0004\u0018\u0001H\u0019\"\u0004\b\u0000\u0010\u00192\u0006\u0010\u0016\u001a\u00020\u0004H\u0087\u0002¢\u0006\u0002\u0010!J&\u0010\"\u001a\u00020#\"\u0004\b\u0000\u0010\u00192\u0006\u0010\u0016\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u0001H\u0019H\u0087\u0002¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u0004\u0018\u0001H\u0019\"\u0004\b\u0000\u0010\u00192\u0006\u0010\u0016\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010!J\u0018\u0010'\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\fH\u0007J\u0010\u0010)\u001a\u00020#2\u0006\u0010\u0016\u001a\u00020\u0004H\u0007R\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0011\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006*"}, d2 = {"Landroidx/lifecycle/internal/SavedStateHandleImpl;", "", "initialState", "", "", "<init>", "(Ljava/util/Map;)V", "regular", "", "getRegular", "()Ljava/util/Map;", "providers", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "flows", "Lkotlinx/coroutines/flow/MutableStateFlow;", "mutableFlows", "getMutableFlows", "savedStateProvider", "getSavedStateProvider", "()Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "contains", "", "key", "getStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "T", "initialValue", "(Ljava/lang/String;Ljava/lang/Object;)Lkotlinx/coroutines/flow/StateFlow;", "getMutableStateFlow", "(Ljava/lang/String;Ljava/lang/Object;)Lkotlinx/coroutines/flow/MutableStateFlow;", "keys", "", "get", "(Ljava/lang/String;)Ljava/lang/Object;", "set", "", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "remove", "setSavedStateProvider", "provider", "clearSavedStateProvider", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SavedStateHandleImpl {
    private final Map<String, MutableStateFlow<Object>> flows;
    private final Map<String, MutableStateFlow<Object>> mutableFlows;
    private final Map<String, SavedStateRegistry.SavedStateProvider> providers;
    private final Map<String, Object> regular;
    private final SavedStateRegistry.SavedStateProvider savedStateProvider;

    public SavedStateHandleImpl(Map<String, ? extends Object> map) {
        map.getClass();
        this.regular = MapsKt.toMutableMap(map);
        this.providers = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.mutableFlows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateRegistry.SavedStateProvider() { // from class: vrc
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                return SavedStateHandleImpl.a(this.a);
            }
        };
    }

    public static Bundle a(SavedStateHandleImpl savedStateHandleImpl) {
        Pair[] pairArr;
        for (Map.Entry entry : MapsKt.toMap(savedStateHandleImpl.mutableFlows).entrySet()) {
            savedStateHandleImpl.set((String) entry.getKey(), ((MutableStateFlow) entry.getValue()).getValue());
        }
        for (Map.Entry entry2 : MapsKt.toMap(savedStateHandleImpl.providers).entrySet()) {
            savedStateHandleImpl.set((String) entry2.getKey(), ((SavedStateRegistry.SavedStateProvider) entry2.getValue()).saveState());
        }
        Map<String, Object> map = savedStateHandleImpl.regular;
        if (map.isEmpty()) {
            pairArr = new Pair[0];
        } else {
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry<String, Object> entry3 : map.entrySet()) {
                arrayList.add(TuplesKt.to(entry3.getKey(), entry3.getValue()));
            }
            pairArr = (Pair[]) arrayList.toArray(new Pair[0]);
        }
        Bundle bundleBundleOf = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
        SavedStateWriter.m6396constructorimpl(bundleBundleOf);
        return bundleBundleOf;
    }

    public final void clearSavedStateProvider(String key) {
        key.getClass();
        this.providers.remove(key);
    }

    public final boolean contains(String key) {
        key.getClass();
        return this.regular.containsKey(key);
    }

    public final <T> T get(String key) {
        T t;
        key.getClass();
        try {
            MutableStateFlow<Object> mutableStateFlow = this.mutableFlows.get(key);
            if (mutableStateFlow != null && (t = (T) mutableStateFlow.getValue()) != null) {
                return t;
            }
            return (T) this.regular.get(key);
        } catch (ClassCastException unused) {
            remove(key);
            return null;
        }
    }

    public final Map<String, MutableStateFlow<Object>> getMutableFlows() {
        return this.mutableFlows;
    }

    public final <T> MutableStateFlow<T> getMutableStateFlow(String key, T initialValue) {
        key.getClass();
        Map<String, MutableStateFlow<Object>> map = this.mutableFlows;
        Object objMutableStateFlow = map.get(key);
        if (objMutableStateFlow == null) {
            if (!this.regular.containsKey(key)) {
                this.regular.put(key, initialValue);
            }
            objMutableStateFlow = StateFlowKt.MutableStateFlow(this.regular.get(key));
            map.put(key, (MutableStateFlow<Object>) objMutableStateFlow);
        }
        MutableStateFlow<T> mutableStateFlow = (MutableStateFlow) objMutableStateFlow;
        mutableStateFlow.getClass();
        return mutableStateFlow;
    }

    public final Map<String, Object> getRegular() {
        return this.regular;
    }

    public final SavedStateRegistry.SavedStateProvider getSavedStateProvider() {
        return this.savedStateProvider;
    }

    public final <T> StateFlow<T> getStateFlow(String key, T initialValue) {
        key.getClass();
        Map<String, MutableStateFlow<Object>> map = this.flows;
        MutableStateFlow<Object> MutableStateFlow = map.get(key);
        if (MutableStateFlow == null) {
            if (!this.regular.containsKey(key)) {
                this.regular.put(key, initialValue);
            }
            MutableStateFlow = StateFlowKt.MutableStateFlow(this.regular.get(key));
            map.put(key, MutableStateFlow);
        }
        StateFlow<T> stateFlowAsStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        stateFlowAsStateFlow.getClass();
        return stateFlowAsStateFlow;
    }

    public final Set<String> keys() {
        return SetsKt.plus(this.regular.keySet(), this.providers.keySet());
    }

    public final <T> T remove(String key) {
        key.getClass();
        T t = (T) this.regular.remove(key);
        this.flows.remove(key);
        this.mutableFlows.remove(key);
        return t;
    }

    public final SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.savedStateProvider;
    }

    public final <T> void set(String key, T value) {
        key.getClass();
        this.regular.put(key, value);
        MutableStateFlow<Object> mutableStateFlow = this.flows.get(key);
        if (mutableStateFlow != null) {
            mutableStateFlow.setValue(value);
        }
        MutableStateFlow<Object> mutableStateFlow2 = this.mutableFlows.get(key);
        if (mutableStateFlow2 != null) {
            mutableStateFlow2.setValue(value);
        }
    }

    public final void setSavedStateProvider(String key, SavedStateRegistry.SavedStateProvider provider) {
        key.getClass();
        provider.getClass();
        this.providers.put(key, provider);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SavedStateHandleImpl() {
        Map map = null;
        this(map, 1, map);
    }

    public /* synthetic */ SavedStateHandleImpl(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MapsKt.emptyMap() : map);
    }
}
