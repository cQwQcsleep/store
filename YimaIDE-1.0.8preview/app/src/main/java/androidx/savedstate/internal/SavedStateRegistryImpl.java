package androidx.savedstate.internal;

import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.SavedStateWriter;
import androidx.savedstate.internal.SavedStateRegistryImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0000\u0018\u0000 -2\u00020\u0001:\u0001-B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u001e\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u00152\u0006\u0010\u001f\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010 J\u0018\u0010!\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0010H\u0007J\u0010\u0010#\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001f\u001a\u00020\u000fJ\u0010\u0010$\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u000fH\u0007J\b\u0010%\u001a\u00020\u0006H\u0007J\u001f\u0010&\u001a\u00020\u00062\u000e\u0010'\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015H\u0001¢\u0006\u0004\b(\u0010)J\u001b\u0010*\u001a\u00020\u00062\n\u0010+\u001a\u00060\u0014j\u0002`\u0015H\u0001¢\u0006\u0004\b,\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R \u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00128G@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0019\"\u0004\b\u001c\u0010\u001d¨\u0006."}, d2 = {"Landroidx/savedstate/internal/SavedStateRegistryImpl;", "", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "onAttach", "Lkotlin/Function0;", "", "<init>", "(Landroidx/savedstate/SavedStateRegistryOwner;Lkotlin/jvm/functions/Function0;)V", "getOnAttach$savedstate_release", "()Lkotlin/jvm/functions/Function0;", "lock", "Landroidx/savedstate/internal/SynchronizedObject;", "keyToProviders", "", "", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "attached", "", "restoredState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "Landroid/os/Bundle;", "value", "isRestored", "()Z", "isAllowingSavingState", "isAllowingSavingState$savedstate_release", "setAllowingSavingState$savedstate_release", "(Z)V", "consumeRestoredStateForKey", "key", "(Ljava/lang/String;)Landroid/os/Bundle;", "registerSavedStateProvider", "provider", "getSavedStateProvider", "unregisterSavedStateProvider", "performAttach", "performRestore", "savedState", "performRestore$savedstate_release", "(Landroid/os/Bundle;)V", "performSave", "outBundle", "performSave$savedstate_release", "Companion", "savedstate_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SavedStateRegistryImpl {
    private static final Companion Companion = new Companion(null);
    private static final String SAVED_COMPONENTS_KEY = "androidx.lifecycle.BundlableSavedStateRegistry.key";
    private boolean attached;
    private boolean isAllowingSavingState;
    private boolean isRestored;
    private final Map<String, SavedStateRegistry.SavedStateProvider> keyToProviders;
    private final SynchronizedObject lock;
    private final Function0<Unit> onAttach;
    private final SavedStateRegistryOwner owner;
    private Bundle restoredState;

    public SavedStateRegistryImpl(SavedStateRegistryOwner savedStateRegistryOwner, Function0<Unit> function0) {
        savedStateRegistryOwner.getClass();
        function0.getClass();
        this.owner = savedStateRegistryOwner;
        this.onAttach = function0;
        this.lock = new SynchronizedObject();
        this.keyToProviders = new LinkedHashMap();
        this.isAllowingSavingState = true;
    }

    public static void a(SavedStateRegistryImpl savedStateRegistryImpl, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        lifecycleOwner.getClass();
        event.getClass();
        if (event == Lifecycle.Event.ON_START) {
            savedStateRegistryImpl.isAllowingSavingState = true;
        } else if (event == Lifecycle.Event.ON_STOP) {
            savedStateRegistryImpl.isAllowingSavingState = false;
        }
    }

    public static Unit b() {
        return Unit.INSTANCE;
    }

    public final Bundle consumeRestoredStateForKey(String key) {
        key.getClass();
        if (!this.isRestored) {
            k2d.a("You can 'consumeRestoredStateForKey' only after the corresponding component has moved to the 'CREATED' state");
            return null;
        }
        Bundle bundle = this.restoredState;
        if (bundle == null) {
            return null;
        }
        Bundle bundleM6310constructorimpl = SavedStateReader.m6310constructorimpl(bundle);
        Bundle bundleM6367getSavedStateimpl = SavedStateReader.m6311containsimpl(bundleM6310constructorimpl, key) ? SavedStateReader.m6367getSavedStateimpl(bundleM6310constructorimpl, key) : null;
        SavedStateWriter.m6432removeimpl(SavedStateWriter.m6396constructorimpl(bundle), key);
        if (SavedStateReader.m6388isEmptyimpl(SavedStateReader.m6310constructorimpl(bundle))) {
            this.restoredState = null;
        }
        return bundleM6367getSavedStateimpl;
    }

    public final Function0<Unit> getOnAttach$savedstate_release() {
        return this.onAttach;
    }

    public final SavedStateRegistry.SavedStateProvider getSavedStateProvider(String key) {
        SavedStateRegistry.SavedStateProvider savedStateProvider;
        key.getClass();
        synchronized (this.lock) {
            Iterator it = this.keyToProviders.entrySet().iterator();
            do {
                savedStateProvider = null;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                SavedStateRegistry.SavedStateProvider savedStateProvider2 = (SavedStateRegistry.SavedStateProvider) entry.getValue();
                if (Intrinsics.areEqual(str, key)) {
                    savedStateProvider = savedStateProvider2;
                }
            } while (savedStateProvider == null);
        }
        return savedStateProvider;
    }

    /* JADX INFO: renamed from: isAllowingSavingState$savedstate_release, reason: from getter */
    public final boolean getIsAllowingSavingState() {
        return this.isAllowingSavingState;
    }

    /* JADX INFO: renamed from: isRestored, reason: from getter */
    public final boolean getIsRestored() {
        return this.isRestored;
    }

    public final void performAttach() {
        if (this.owner.getLifecycle().getState() != Lifecycle.State.INITIALIZED) {
            k2d.a("Restarter must be created only during owner's initialization stage");
        } else {
            if (this.attached) {
                k2d.a("SavedStateRegistry was already attached.");
                return;
            }
            this.onAttach.invoke();
            this.owner.getLifecycle().addObserver(new LifecycleEventObserver() { // from class: yrc
                @Override // androidx.lifecycle.LifecycleEventObserver
                public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    SavedStateRegistryImpl.a(this.b, lifecycleOwner, event);
                }
            });
            this.attached = true;
        }
    }

    public final void performRestore$savedstate_release(Bundle savedState) {
        if (!this.attached) {
            performAttach();
        }
        if (this.owner.getLifecycle().getState().isAtLeast(Lifecycle.State.STARTED)) {
            cpa.a("performRestore cannot be called when owner is ", this.owner.getLifecycle().getState());
            return;
        }
        if (this.isRestored) {
            k2d.a("SavedStateRegistry was already restored.");
            return;
        }
        Bundle bundleM6367getSavedStateimpl = null;
        if (savedState != null) {
            Bundle bundleM6310constructorimpl = SavedStateReader.m6310constructorimpl(savedState);
            if (SavedStateReader.m6311containsimpl(bundleM6310constructorimpl, SAVED_COMPONENTS_KEY)) {
                bundleM6367getSavedStateimpl = SavedStateReader.m6367getSavedStateimpl(bundleM6310constructorimpl, SAVED_COMPONENTS_KEY);
            }
        }
        this.restoredState = bundleM6367getSavedStateimpl;
        this.isRestored = true;
    }

    public final void performSave$savedstate_release(Bundle outBundle) {
        Pair[] pairArr;
        outBundle.getClass();
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
        Bundle bundleM6396constructorimpl = SavedStateWriter.m6396constructorimpl(bundleBundleOf);
        Bundle bundle = this.restoredState;
        if (bundle != null) {
            SavedStateWriter.m6400putAllimpl(bundleM6396constructorimpl, bundle);
        }
        synchronized (this.lock) {
            try {
                for (Map.Entry entry2 : this.keyToProviders.entrySet()) {
                    SavedStateWriter.m6423putSavedStateimpl(bundleM6396constructorimpl, (String) entry2.getKey(), ((SavedStateRegistry.SavedStateProvider) entry2.getValue()).saveState());
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (SavedStateReader.m6388isEmptyimpl(SavedStateReader.m6310constructorimpl(bundleBundleOf))) {
            return;
        }
        SavedStateWriter.m6423putSavedStateimpl(SavedStateWriter.m6396constructorimpl(outBundle), SAVED_COMPONENTS_KEY, bundleBundleOf);
    }

    public final void registerSavedStateProvider(String key, SavedStateRegistry.SavedStateProvider provider) {
        key.getClass();
        provider.getClass();
        synchronized (this.lock) {
            if (this.keyToProviders.containsKey(key)) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
            this.keyToProviders.put(key, provider);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void setAllowingSavingState$savedstate_release(boolean z) {
        this.isAllowingSavingState = z;
    }

    public final void unregisterSavedStateProvider(String key) {
        key.getClass();
        synchronized (this.lock) {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/savedstate/internal/SavedStateRegistryImpl$Companion;", "", "<init>", "()V", "SAVED_COMPONENTS_KEY", "", "savedstate_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public /* synthetic */ SavedStateRegistryImpl(SavedStateRegistryOwner savedStateRegistryOwner, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(savedStateRegistryOwner, (i & 2) != 0 ? new Function0() { // from class: zrc
            public final Object invoke() {
                return SavedStateRegistryImpl.b();
            }
        } : function0);
    }
}
