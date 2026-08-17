package androidx.compose.foundation.text.input.internal;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.Key_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000\u001a\u001b\u0010\u0007\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000b\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"createTextFieldKeyEventHandler", "Landroidx/compose/foundation/text/input/internal/TextFieldKeyEventHandler;", "isFromSoftKeyboard", "", "Landroidx/compose/ui/input/key/KeyEvent;", "isFromSoftKeyboard-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isKeyCode", "keyCode", "", "isKeyCode-YhN2O0w", "(Landroid/view/KeyEvent;I)Z", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextFieldKeyEventHandler_androidKt {
    public static final TextFieldKeyEventHandler createTextFieldKeyEventHandler() {
        return new AndroidTextFieldKeyEventHandler();
    }

    /* JADX INFO: renamed from: isFromSoftKeyboard-ZmokQxo, reason: not valid java name */
    public static final boolean m1585isFromSoftKeyboardZmokQxo(KeyEvent keyEvent) {
        return (keyEvent.getFlags() & 2) == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isKeyCode-YhN2O0w, reason: not valid java name */
    public static final boolean m1586isKeyCodeYhN2O0w(KeyEvent keyEvent, int i) {
        return Key_androidKt.getNativeKeyCode-YVgTNJs(KeyEvent_androidKt.getKey-ZmokQxo(keyEvent)) == i;
    }
}
