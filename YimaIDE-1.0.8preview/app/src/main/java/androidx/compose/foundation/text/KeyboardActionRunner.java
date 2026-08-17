package androidx.compose.foundation.text;

import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.input.ImeAction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0019\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/text/KeyboardActionRunner;", "Landroidx/compose/foundation/text/KeyboardActionScope;", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "<init>", "(Landroidx/compose/ui/platform/SoftwareKeyboardController;)V", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "getKeyboardActions", "()Landroidx/compose/foundation/text/KeyboardActions;", "setKeyboardActions", "(Landroidx/compose/foundation/text/KeyboardActions;)V", "focusManager", "Landroidx/compose/ui/focus/FocusManager;", "getFocusManager", "()Landroidx/compose/ui/focus/FocusManager;", "setFocusManager", "(Landroidx/compose/ui/focus/FocusManager;)V", "runAction", "", "imeAction", "Landroidx/compose/ui/text/input/ImeAction;", "runAction-KlQnJC8", "(I)Z", "defaultKeyboardActionWithResult", "defaultKeyboardActionWithResult-KlQnJC8", "defaultKeyboardAction", "", "defaultKeyboardAction-KlQnJC8", "(I)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class KeyboardActionRunner implements KeyboardActionScope {
    public static final int $stable = 8;
    public FocusManager focusManager;
    public KeyboardActions keyboardActions;
    private final SoftwareKeyboardController keyboardController;

    public KeyboardActionRunner(SoftwareKeyboardController softwareKeyboardController) {
        this.keyboardController = softwareKeyboardController;
    }

    /* JADX INFO: renamed from: defaultKeyboardActionWithResult-KlQnJC8, reason: not valid java name */
    private final boolean m1355defaultKeyboardActionWithResultKlQnJC8(int imeAction) {
        SoftwareKeyboardController softwareKeyboardController;
        ImeAction.Companion companion = ImeAction.Companion;
        if (ImeAction.equals-impl0(imeAction, companion.getNext-eUduSuo())) {
            getFocusManager().moveFocus-3ESFkO8(FocusDirection.Companion.getNext-dhqQ-8s());
            return true;
        }
        if (ImeAction.equals-impl0(imeAction, companion.getPrevious-eUduSuo())) {
            getFocusManager().moveFocus-3ESFkO8(FocusDirection.Companion.getPrevious-dhqQ-8s());
            return true;
        }
        if (!ImeAction.equals-impl0(imeAction, companion.getDone-eUduSuo()) || (softwareKeyboardController = this.keyboardController) == null) {
            return false;
        }
        softwareKeyboardController.hide();
        return true;
    }

    @Override // androidx.compose.foundation.text.KeyboardActionScope
    /* JADX INFO: renamed from: defaultKeyboardAction-KlQnJC8, reason: not valid java name */
    public void mo1356defaultKeyboardActionKlQnJC8(int imeAction) {
        m1355defaultKeyboardActionWithResultKlQnJC8(imeAction);
    }

    public final FocusManager getFocusManager() {
        FocusManager focusManager = this.focusManager;
        if (focusManager != null) {
            return focusManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("focusManager");
        return null;
    }

    public final KeyboardActions getKeyboardActions() {
        KeyboardActions keyboardActions = this.keyboardActions;
        if (keyboardActions != null) {
            return keyboardActions;
        }
        Intrinsics.throwUninitializedPropertyAccessException("keyboardActions");
        return null;
    }

    /* JADX INFO: renamed from: runAction-KlQnJC8, reason: not valid java name */
    public final boolean m1357runActionKlQnJC8(int imeAction) {
        Function1<KeyboardActionScope, Unit> onSend;
        ImeAction.Companion companion = ImeAction.Companion;
        if (ImeAction.equals-impl0(imeAction, companion.getDone-eUduSuo())) {
            onSend = getKeyboardActions().getOnDone();
        } else if (ImeAction.equals-impl0(imeAction, companion.getGo-eUduSuo())) {
            onSend = getKeyboardActions().getOnGo();
        } else if (ImeAction.equals-impl0(imeAction, companion.getNext-eUduSuo())) {
            onSend = getKeyboardActions().getOnNext();
        } else if (ImeAction.equals-impl0(imeAction, companion.getPrevious-eUduSuo())) {
            onSend = getKeyboardActions().getOnPrevious();
        } else if (ImeAction.equals-impl0(imeAction, companion.getSearch-eUduSuo())) {
            onSend = getKeyboardActions().getOnSearch();
        } else if (ImeAction.equals-impl0(imeAction, companion.getSend-eUduSuo())) {
            onSend = getKeyboardActions().getOnSend();
        } else {
            if (!ImeAction.equals-impl0(imeAction, companion.getDefault-eUduSuo()) && !ImeAction.equals-impl0(imeAction, companion.getNone-eUduSuo())) {
                k2d.a("invalid ImeAction");
                return false;
            }
            onSend = null;
        }
        if (onSend == null) {
            return m1355defaultKeyboardActionWithResultKlQnJC8(imeAction);
        }
        onSend.invoke(this);
        return true;
    }

    public final void setFocusManager(FocusManager focusManager) {
        this.focusManager = focusManager;
    }

    public final void setKeyboardActions(KeyboardActions keyboardActions) {
        this.keyboardActions = keyboardActions;
    }
}
