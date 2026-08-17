package androidx.compose.material3.internal;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0002\u0014\u0017\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001(B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0003H\u0016J\u000e\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001aJ\u000e\u0010'\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR+\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u0003*\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u00020\u0003*\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006)"}, d2 = {"Landroidx/compose/material3/internal/Listener;", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "Landroidx/compose/runtime/State;", "", "listenToTouchExplorationState", "listenToSwitchAccessState", "listenToVoiceAccessState", "<init>", "(ZZZ)V", "getListenToSwitchAccessState", "()Z", "getListenToVoiceAccessState", "<set-?>", "accessibilityEnabled", "getAccessibilityEnabled", "setAccessibilityEnabled", "(Z)V", "accessibilityEnabled$delegate", "Landroidx/compose/runtime/MutableState;", "touchExplorationListener", "androidx/compose/material3/internal/Listener$touchExplorationListener$1", "Landroidx/compose/material3/internal/Listener$touchExplorationListener$1;", "otherA11yServicesListener", "androidx/compose/material3/internal/Listener$otherA11yServicesListener$1", "Landroidx/compose/material3/internal/Listener$otherA11yServicesListener$1;", "switchAccessEnabled", "Landroid/view/accessibility/AccessibilityManager;", "getSwitchAccessEnabled", "(Landroid/view/accessibility/AccessibilityManager;)Z", "voiceAccessEnabled", "getVoiceAccessEnabled", "value", "getValue", "()Ljava/lang/Boolean;", "onAccessibilityStateChanged", "", "enabled", "register", "am", "unregister", "Api33Impl", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class Listener implements AccessibilityManager.AccessibilityStateChangeListener, State<Boolean> {

    /* JADX INFO: renamed from: accessibilityEnabled$delegate, reason: from kotlin metadata */
    private final MutableState accessibilityEnabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    private final boolean listenToSwitchAccessState;
    private final boolean listenToVoiceAccessState;
    private final Listener$otherA11yServicesListener$1 otherA11yServicesListener;
    private final Listener$touchExplorationListener$1 touchExplorationListener;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/material3/internal/Listener$Api33Impl;", "", "<init>", "()V", "addAccessibilityServicesStateChangeListener", "", "am", "Landroid/view/accessibility/AccessibilityManager;", "listener", "Landroid/view/accessibility/AccessibilityManager$AccessibilityServicesStateChangeListener;", "removeAccessibilityServicesStateChangeListener", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        @JvmStatic
        public static final void addAccessibilityServicesStateChangeListener(AccessibilityManager am, AccessibilityManager.AccessibilityServicesStateChangeListener listener) {
            am.addAccessibilityServicesStateChangeListener(listener);
        }

        @JvmStatic
        public static final void removeAccessibilityServicesStateChangeListener(AccessibilityManager am, AccessibilityManager.AccessibilityServicesStateChangeListener listener) {
            am.removeAccessibilityServicesStateChangeListener(listener);
        }
    }

    public Listener(boolean z, boolean z2, boolean z3) {
        this.listenToSwitchAccessState = z2;
        this.listenToVoiceAccessState = z3;
        this.touchExplorationListener = z ? new Listener$touchExplorationListener$1() : null;
        this.otherA11yServicesListener = (z2 || z3) ? new Listener$otherA11yServicesListener$1(this) : null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getAccessibilityEnabled() {
        return ((Boolean) this.accessibilityEnabled.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSwitchAccessEnabled(AccessibilityManager accessibilityManager) {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(16);
        int size = enabledAccessibilityServiceList.size();
        for (int i = 0; i < size; i++) {
            String settingsActivityName = enabledAccessibilityServiceList.get(i).getSettingsActivityName();
            if (settingsActivityName != null && StringsKt.contains(settingsActivityName, "SwitchAccess", true)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getVoiceAccessEnabled(AccessibilityManager accessibilityManager) {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(16);
        int size = enabledAccessibilityServiceList.size();
        for (int i = 0; i < size; i++) {
            String settingsActivityName = enabledAccessibilityServiceList.get(i).getSettingsActivityName();
            if (settingsActivityName != null && StringsKt.contains(settingsActivityName, "VoiceAccess", true)) {
                return true;
            }
        }
        return false;
    }

    private final void setAccessibilityEnabled(boolean z) {
        this.accessibilityEnabled.setValue(Boolean.valueOf(z));
    }

    public final boolean getListenToSwitchAccessState() {
        return this.listenToSwitchAccessState;
    }

    public final boolean getListenToVoiceAccessState() {
        return this.listenToVoiceAccessState;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Code duplicated, block: B:23:0x0030  */
    @Override // androidx.compose.runtime.State
    public Boolean getValue() {
        boolean z;
        Listener$otherA11yServicesListener$1 listener$otherA11yServicesListener$1;
        Listener$otherA11yServicesListener$1 listener$otherA11yServicesListener$2;
        if (getAccessibilityEnabled()) {
            Listener$touchExplorationListener$1 listener$touchExplorationListener$1 = this.touchExplorationListener;
            z = true;
            if ((listener$touchExplorationListener$1 == null || !listener$touchExplorationListener$1.getEnabled()) && ((!this.listenToSwitchAccessState || (listener$otherA11yServicesListener$2 = this.otherA11yServicesListener) == null || !listener$otherA11yServicesListener$2.getSwitchAccessEnabled()) && (!this.listenToVoiceAccessState || (listener$otherA11yServicesListener$1 = this.otherA11yServicesListener) == null || !listener$otherA11yServicesListener$1.getVoiceAccessEnabled()))) {
                z = false;
            }
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean enabled) {
        setAccessibilityEnabled(enabled);
    }

    public final void register(AccessibilityManager am) {
        setAccessibilityEnabled(am.isEnabled());
        am.addAccessibilityStateChangeListener(this);
        Listener$touchExplorationListener$1 listener$touchExplorationListener$1 = this.touchExplorationListener;
        if (listener$touchExplorationListener$1 != null) {
            listener$touchExplorationListener$1.setEnabled(am.isTouchExplorationEnabled());
            am.addTouchExplorationStateChangeListener(listener$touchExplorationListener$1);
        }
        Listener$otherA11yServicesListener$1 listener$otherA11yServicesListener$1 = this.otherA11yServicesListener;
        if (listener$otherA11yServicesListener$1 != null) {
            listener$otherA11yServicesListener$1.setSwitchAccessEnabled(getSwitchAccessEnabled(am));
            listener$otherA11yServicesListener$1.setVoiceAccessEnabled(getVoiceAccessEnabled(am));
            Api33Impl.addAccessibilityServicesStateChangeListener(am, listener$otherA11yServicesListener$1);
        }
    }

    public final void unregister(AccessibilityManager am) {
        am.removeAccessibilityStateChangeListener(this);
        Listener$touchExplorationListener$1 listener$touchExplorationListener$1 = this.touchExplorationListener;
        if (listener$touchExplorationListener$1 != null) {
            am.removeTouchExplorationStateChangeListener(listener$touchExplorationListener$1);
        }
        Listener$otherA11yServicesListener$1 listener$otherA11yServicesListener$1 = this.otherA11yServicesListener;
        if (listener$otherA11yServicesListener$1 != null) {
            Api33Impl.removeAccessibilityServicesStateChangeListener(am, listener$otherA11yServicesListener$1);
        }
    }
}
