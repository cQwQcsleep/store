package androidx.window.layout.adapter.sidecar;

import android.graphics.Rect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.Bounds;
import androidx.window.core.SpecificationComputer;
import androidx.window.core.VerificationMode;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.sidecar.SidecarAdapter;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0006\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\fJ\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u0015\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000fJ(\u0010\u0016\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007H\u0002J\u001c\u0010\u0017\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\nH\u0002J\u001f\u0010\u0006\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarAdapter;", "", "verificationMode", "Landroidx/window/core/VerificationMode;", "<init>", "(Landroidx/window/core/VerificationMode;)V", "translate", "", "Landroidx/window/layout/DisplayFeature;", "sidecarDisplayFeatures", "Landroidx/window/sidecar/SidecarDisplayFeature;", "deviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "Landroidx/window/layout/WindowLayoutInfo;", "extensionInfo", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "state", "isEqualSidecarDeviceState", "", "first", "second", "isEqualSidecarWindowLayoutInfo", "isEqualSidecarDisplayFeatures", "isEqualSidecarDisplayFeature", "feature", "translate$window_release", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SidecarAdapter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = SidecarAdapter.class.getSimpleName();
    private final VerificationMode verificationMode;

    public /* synthetic */ SidecarAdapter(VerificationMode verificationMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? VerificationMode.QUIET : verificationMode);
    }

    private final boolean isEqualSidecarDisplayFeature(SidecarDisplayFeature first, SidecarDisplayFeature second) {
        if (Intrinsics.areEqual(first, second)) {
            return true;
        }
        if (first == null || second == null || first.getType() != second.getType()) {
            return false;
        }
        return Intrinsics.areEqual(first.getRect(), second.getRect());
    }

    private final boolean isEqualSidecarDisplayFeatures(List<SidecarDisplayFeature> first, List<SidecarDisplayFeature> second) {
        if (first == second) {
            return true;
        }
        if (first == null || second == null || first.size() != second.size()) {
            return false;
        }
        int size = first.size();
        for (int i = 0; i < size; i++) {
            if (!isEqualSidecarDisplayFeature(first.get(i), second.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translate$lambda$1(SidecarDisplayFeature sidecarDisplayFeature) {
        sidecarDisplayFeature.getClass();
        return sidecarDisplayFeature.getType() == 1 || sidecarDisplayFeature.getType() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translate$lambda$2(SidecarDisplayFeature sidecarDisplayFeature) {
        sidecarDisplayFeature.getClass();
        return (sidecarDisplayFeature.getRect().width() == 0 && sidecarDisplayFeature.getRect().height() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translate$lambda$3(SidecarDisplayFeature sidecarDisplayFeature) {
        sidecarDisplayFeature.getClass();
        return sidecarDisplayFeature.getType() != 1 || sidecarDisplayFeature.getRect().width() == 0 || sidecarDisplayFeature.getRect().height() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean translate$lambda$4(SidecarDisplayFeature sidecarDisplayFeature) {
        sidecarDisplayFeature.getClass();
        return sidecarDisplayFeature.getRect().left == 0 || sidecarDisplayFeature.getRect().top == 0;
    }

    public final boolean isEqualSidecarDeviceState(SidecarDeviceState first, SidecarDeviceState second) {
        if (Intrinsics.areEqual(first, second)) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        Companion companion = INSTANCE;
        return companion.getSidecarDevicePosture$window_release(first) == companion.getSidecarDevicePosture$window_release(second);
    }

    public final boolean isEqualSidecarWindowLayoutInfo(SidecarWindowLayoutInfo first, SidecarWindowLayoutInfo second) {
        if (Intrinsics.areEqual(first, second)) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        Companion companion = INSTANCE;
        return isEqualSidecarDisplayFeatures(companion.getSidecarDisplayFeatures(first), companion.getSidecarDisplayFeatures(second));
    }

    public final WindowLayoutInfo translate(SidecarWindowLayoutInfo extensionInfo, SidecarDeviceState state) {
        state.getClass();
        if (extensionInfo == null) {
            return new WindowLayoutInfo(CollectionsKt.emptyList());
        }
        SidecarDeviceState sidecarDeviceState = new SidecarDeviceState();
        Companion companion = INSTANCE;
        companion.setSidecarDevicePosture(sidecarDeviceState, companion.getSidecarDevicePosture$window_release(state));
        return new WindowLayoutInfo(translate(companion.getSidecarDisplayFeatures(extensionInfo), sidecarDeviceState));
    }

    public final DisplayFeature translate$window_release(SidecarDisplayFeature feature, SidecarDeviceState deviceState) {
        HardwareFoldingFeature.Type fold;
        FoldingFeature.State state;
        feature.getClass();
        deviceState.getClass();
        SpecificationComputer.Companion companion = SpecificationComputer.INSTANCE;
        String str = TAG;
        str.getClass();
        SidecarDisplayFeature sidecarDisplayFeature = (SidecarDisplayFeature) SpecificationComputer.Companion.startSpecification$default(companion, feature, str, this.verificationMode, null, 4, null).require("Type must be either TYPE_FOLD or TYPE_HINGE", new Function1() { // from class: sad
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$1((SidecarDisplayFeature) obj));
            }
        }).require("Feature bounds must not be 0", new Function1() { // from class: tad
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$2((SidecarDisplayFeature) obj));
            }
        }).require("TYPE_FOLD must have 0 area", new Function1() { // from class: uad
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$3((SidecarDisplayFeature) obj));
            }
        }).require("Feature be pinned to either left or top", new Function1() { // from class: vad
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SidecarAdapter.translate$lambda$4((SidecarDisplayFeature) obj));
            }
        }).compute();
        if (sidecarDisplayFeature == null) {
            return null;
        }
        int type = sidecarDisplayFeature.getType();
        if (type == 1) {
            fold = HardwareFoldingFeature.Type.INSTANCE.getFOLD();
        } else {
            if (type != 2) {
                return null;
            }
            fold = HardwareFoldingFeature.Type.INSTANCE.getHINGE();
        }
        int sidecarDevicePosture$window_release = INSTANCE.getSidecarDevicePosture$window_release(deviceState);
        if (sidecarDevicePosture$window_release != 0 && sidecarDevicePosture$window_release != 1) {
            if (sidecarDevicePosture$window_release == 2) {
                state = FoldingFeature.State.HALF_OPENED;
            } else if (sidecarDevicePosture$window_release == 3 || sidecarDevicePosture$window_release != 4) {
                state = FoldingFeature.State.FLAT;
            }
            Rect rect = feature.getRect();
            rect.getClass();
            return new HardwareFoldingFeature(new Bounds(rect), fold, state);
        }
        return null;
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0007J\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0010H\u0007R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/window/layout/adapter/sidecar/SidecarAdapter$Companion;", "", "<init>", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getSidecarDisplayFeatures", "", "Landroidx/window/sidecar/SidecarDisplayFeature;", "info", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "setSidecarDisplayFeatures", "", "displayFeatures", "getSidecarDevicePosture", "", "sidecarDeviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "getSidecarDevicePosture$window_release", "getRawSidecarDevicePosture", "setSidecarDevicePosture", "posture", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getRawSidecarDevicePosture(SidecarDeviceState sidecarDeviceState) {
            sidecarDeviceState.getClass();
            try {
                try {
                    return sidecarDeviceState.posture;
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                    return 0;
                }
            } catch (NoSuchFieldError unused2) {
                Object objInvoke = SidecarDeviceState.class.getMethod("getPosture", null).invoke(sidecarDeviceState, null);
                objInvoke.getClass();
                return ((Integer) objInvoke).intValue();
            }
        }

        public final int getSidecarDevicePosture$window_release(SidecarDeviceState sidecarDeviceState) {
            sidecarDeviceState.getClass();
            int rawSidecarDevicePosture = getRawSidecarDevicePosture(sidecarDeviceState);
            if (rawSidecarDevicePosture < 0 || rawSidecarDevicePosture > 4) {
                return 0;
            }
            return rawSidecarDevicePosture;
        }

        public final List<SidecarDisplayFeature> getSidecarDisplayFeatures(SidecarWindowLayoutInfo info) {
            info.getClass();
            try {
                try {
                    List<SidecarDisplayFeature> list = info.displayFeatures;
                    return list == null ? CollectionsKt.emptyList() : list;
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                    return CollectionsKt.emptyList();
                }
            } catch (NoSuchFieldError unused2) {
                Object objInvoke = SidecarWindowLayoutInfo.class.getMethod("getDisplayFeatures", null).invoke(info, null);
                objInvoke.getClass();
                return (List) objInvoke;
            }
            return CollectionsKt.emptyList();
        }

        public final void setSidecarDevicePosture(SidecarDeviceState sidecarDeviceState, int posture) {
            sidecarDeviceState.getClass();
            try {
                try {
                    sidecarDeviceState.posture = posture;
                } catch (NoSuchFieldError unused) {
                    SidecarDeviceState.class.getMethod("setPosture", Integer.TYPE).invoke(sidecarDeviceState, Integer.valueOf(posture));
                }
            } catch (IllegalAccessException unused2) {
                Unit unit = Unit.INSTANCE;
            } catch (NoSuchMethodException unused3) {
                Unit unit2 = Unit.INSTANCE;
            } catch (InvocationTargetException unused4) {
                Unit unit3 = Unit.INSTANCE;
            }
        }

        public final void setSidecarDisplayFeatures(SidecarWindowLayoutInfo info, List<SidecarDisplayFeature> displayFeatures) {
            info.getClass();
            displayFeatures.getClass();
            try {
                try {
                    info.displayFeatures = displayFeatures;
                } catch (NoSuchFieldError unused) {
                    SidecarWindowLayoutInfo.class.getMethod("setDisplayFeatures", List.class).invoke(info, displayFeatures);
                }
            } catch (IllegalAccessException unused2) {
                Unit unit = Unit.INSTANCE;
            } catch (NoSuchMethodException unused3) {
                Unit unit2 = Unit.INSTANCE;
            } catch (InvocationTargetException unused4) {
                Unit unit3 = Unit.INSTANCE;
            }
        }

        private Companion() {
        }
    }

    public SidecarAdapter(VerificationMode verificationMode) {
        verificationMode.getClass();
        this.verificationMode = verificationMode;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SidecarAdapter() {
        VerificationMode verificationMode = null;
        this(verificationMode, 1, verificationMode);
    }

    public final List<DisplayFeature> translate(List<SidecarDisplayFeature> sidecarDisplayFeatures, SidecarDeviceState deviceState) {
        sidecarDisplayFeatures.getClass();
        deviceState.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = sidecarDisplayFeatures.iterator();
        while (it.hasNext()) {
            DisplayFeature displayFeatureTranslate$window_release = translate$window_release((SidecarDisplayFeature) it.next(), deviceState);
            if (displayFeatureTranslate$window_release != null) {
                arrayList.add(displayFeatureTranslate$window_release);
            }
        }
        return arrayList;
    }
}
