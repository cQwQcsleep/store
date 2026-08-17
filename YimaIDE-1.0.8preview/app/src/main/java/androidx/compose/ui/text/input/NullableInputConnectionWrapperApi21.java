package androidx.compose.ui.text.input;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\f\u001a\u00020\u0006J\u0006\u0010\r\u001a\u00020\u0006J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u001a\u0010\u001c\u001a\u00020\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0018\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016J\u001a\u0010\u001f\u001a\u00020\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0012\u0010 \u001a\u00020\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010#H\u0016J\u0018\u0010$\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010&\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010'\u001a\u00020\u001aH\u0016J\b\u0010(\u001a\u00020\u001aH\u0016J\u0012\u0010)\u001a\u00020\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010*H\u0016J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010,\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u001aH\u0016J\u001c\u0010-\u001a\u00020\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010.2\b\u0010\u0012\u001a\u0004\u0018\u00010/H\u0016J\u0010\u00100\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\n\u00101\u001a\u0004\u0018\u000102H\u0016J\"\u00103\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u0002042\u0006\u0010\u0012\u001a\u00020\u00112\b\u00105\u001a\u0004\u0018\u00010/H\u0016J\u0010\u00106\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0014R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0003@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u00067"}, d2 = {"Landroidx/compose/ui/text/input/NullableInputConnectionWrapperApi21;", "Landroidx/compose/ui/text/input/NullableInputConnectionWrapper;", "delegate", "Landroid/view/inputmethod/InputConnection;", "onConnectionClosed", "Lkotlin/Function1;", "", "<init>", "(Landroid/view/inputmethod/InputConnection;Lkotlin/jvm/functions/Function1;)V", "value", "getDelegate", "()Landroid/view/inputmethod/InputConnection;", "disposeDelegate", "closeConnection", "getTextBeforeCursor", "", "p0", "", "p1", "getTextAfterCursor", "getSelectedText", "getCursorCapsMode", "getExtractedText", "Landroid/view/inputmethod/ExtractedText;", "Landroid/view/inputmethod/ExtractedTextRequest;", "deleteSurroundingText", "", "deleteSurroundingTextInCodePoints", "setComposingText", "setComposingRegion", "finishComposingText", "commitText", "commitCompletion", "Landroid/view/inputmethod/CompletionInfo;", "commitCorrection", "Landroid/view/inputmethod/CorrectionInfo;", "setSelection", "performEditorAction", "performContextMenuAction", "beginBatchEdit", "endBatchEdit", "sendKeyEvent", "Landroid/view/KeyEvent;", "clearMetaKeyStates", "reportFullscreenMode", "performPrivateCommand", "", "Landroid/os/Bundle;", "requestCursorUpdates", "getHandler", "Landroid/os/Handler;", "commitContent", "Landroid/view/inputmethod/InputContentInfo;", "p2", "closeDelegate", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
class NullableInputConnectionWrapperApi21 implements NullableInputConnectionWrapper {
    private InputConnection delegate;
    private final Function1<NullableInputConnectionWrapper, Unit> onConnectionClosed;

    public NullableInputConnectionWrapperApi21(InputConnection inputConnection, Function1<? super NullableInputConnectionWrapper, Unit> function1) {
        this.onConnectionClosed = function1;
        this.delegate = inputConnection;
    }

    public boolean beginBatchEdit() {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.beginBatchEdit();
        }
        return false;
    }

    public boolean clearMetaKeyStates(int p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.clearMetaKeyStates(p0);
        }
        return false;
    }

    public final void closeConnection() {
        if (this.delegate != null) {
            disposeDelegate();
            this.onConnectionClosed.invoke(this);
        }
    }

    public void closeDelegate(InputConnection delegate) {
    }

    public boolean commitCompletion(CompletionInfo p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.commitCompletion(p0);
        }
        return false;
    }

    public boolean commitContent(InputContentInfo p0, int p1, Bundle p2) {
        return false;
    }

    public boolean commitCorrection(CorrectionInfo p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.commitCorrection(p0);
        }
        return false;
    }

    public boolean commitText(CharSequence p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.commitText(p0, p1);
        }
        return false;
    }

    public boolean deleteSurroundingText(int p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.deleteSurroundingText(p0, p1);
        }
        return false;
    }

    public boolean deleteSurroundingTextInCodePoints(int p0, int p1) {
        return false;
    }

    public final void disposeDelegate() {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            closeDelegate(inputConnection);
            this.delegate = null;
        }
    }

    public boolean endBatchEdit() {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.endBatchEdit();
        }
        return false;
    }

    public boolean finishComposingText() {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.finishComposingText();
        }
        return false;
    }

    public int getCursorCapsMode(int p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.getCursorCapsMode(p0);
        }
        return 0;
    }

    public final InputConnection getDelegate() {
        return this.delegate;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.getExtractedText(p0, p1);
        }
        return null;
    }

    public Handler getHandler() {
        return null;
    }

    public CharSequence getSelectedText(int p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.getSelectedText(p0);
        }
        return null;
    }

    public CharSequence getTextAfterCursor(int p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.getTextAfterCursor(p0, p1);
        }
        return null;
    }

    public CharSequence getTextBeforeCursor(int p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.getTextBeforeCursor(p0, p1);
        }
        return null;
    }

    public boolean performContextMenuAction(int p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.performContextMenuAction(p0);
        }
        return false;
    }

    public boolean performEditorAction(int p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.performEditorAction(p0);
        }
        return false;
    }

    public boolean performPrivateCommand(String p0, Bundle p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.performPrivateCommand(p0, p1);
        }
        return false;
    }

    public boolean reportFullscreenMode(boolean p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.reportFullscreenMode(p0);
        }
        return false;
    }

    public boolean requestCursorUpdates(int p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.requestCursorUpdates(p0);
        }
        return false;
    }

    public boolean sendKeyEvent(KeyEvent p0) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.sendKeyEvent(p0);
        }
        return false;
    }

    public boolean setComposingRegion(int p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.setComposingRegion(p0, p1);
        }
        return false;
    }

    public boolean setComposingText(CharSequence p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.setComposingText(p0, p1);
        }
        return false;
    }

    public boolean setSelection(int p0, int p1) {
        InputConnection inputConnection = this.delegate;
        if (inputConnection != null) {
            return inputConnection.setSelection(p0, p1);
        }
        return false;
    }
}
