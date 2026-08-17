package androidx.compose.foundation.text.input.internal;

import android.R;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.PreviewableHandwritingGesture;
import androidx.collection.ScatterMapKt;
import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.TextFieldCharSequenceKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000³\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0015\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u001bH\u0002J\b\u0010\u001f\u001a\u00020\u0012H\u0016J\u001a\u0010 \u001a\u00020\u001b2\b\u0010\n\u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\tH\u0016J\u0018\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\tH\u0016J\u001a\u0010&\u001a\u00020\u001b2\b\u0010\n\u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\tH\u0016J\u0018\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\tH\u0016J\u0018\u0010*\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020\tH\u0016J\u0018\u0010+\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\tH\u0016J\b\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020!2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\tH\u0016J\u0018\u00103\u001a\u00020!2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u00020\tH\u0016J\u0012\u00104\u001a\u0004\u0018\u00010!2\u0006\u00102\u001a\u00020\tH\u0016J\u0010\u00105\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\tH\u0016J$\u00107\u001a\u00020\u00122\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u001a\u0010>\u001a\u00020\u001b2\u0006\u00108\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u001a\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010E2\u0006\u00102\u001a\u00020\tH\u0016J\u0010\u0010F\u001a\u00020\t2\u0006\u0010G\u001a\u00020\tH\u0016J\u0010\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020\tH\u0016J\u0010\u0010J\u001a\u00020\u00122\u0006\u0010K\u001a\u00020\tH\u0002J\u0010\u0010L\u001a\u00020\u001b2\u0006\u0010M\u001a\u00020\tH\u0016J\u0012\u0010N\u001a\u00020\u001b2\b\u0010\n\u001a\u0004\u0018\u00010OH\u0016J\u0012\u0010P\u001a\u00020\u001b2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0016J\n\u0010S\u001a\u0004\u0018\u00010TH\u0016J\u0010\u0010U\u001a\u00020\u001b2\u0006\u0010V\u001a\u00020\tH\u0016J\u0010\u0010W\u001a\u00020\u001b2\u0006\u0010X\u001a\u00020\u001bH\u0016J\u001c\u0010Y\u001a\u00020\u001b2\b\u0010Z\u001a\u0004\u0018\u00010[2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\"\u0010^\u001a\u00020\u001b2\u0006\u0010_\u001a\u00020`2\u0006\u00102\u001a\u00020\t2\b\u0010a\u001a\u0004\u0018\u00010]H\u0016J\u0010\u0010b\u001a\u00020\u00122\u0006\u0010c\u001a\u00020[H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR%\u0010\u000e\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u00130\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0019¨\u0006d"}, d2 = {"Landroidx/compose/foundation/text/input/internal/StatelessInputConnection;", "Landroid/view/inputmethod/InputConnection;", "session", "Landroidx/compose/foundation/text/input/internal/TextInputSession;", "editorInfo", "Landroid/view/inputmethod/EditorInfo;", "<init>", "(Landroidx/compose/foundation/text/input/internal/TextInputSession;Landroid/view/inputmethod/EditorInfo;)V", "batchDepth", "", "text", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "getText", "()Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "editCommands", "Landroidx/compose/runtime/collection/MutableVector;", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/input/internal/ImeEditCommandScope;", "", "Lkotlin/ExtensionFunctionType;", "terminalInputConnection", "androidx/compose/foundation/text/input/internal/StatelessInputConnection$terminalInputConnection$1", "Landroidx/compose/foundation/text/input/internal/StatelessInputConnection$terminalInputConnection$1;", "commitContentDelegateInputConnection", "getCommitContentDelegateInputConnection$annotations", "()V", "beginBatchEdit", "", "beginBatchEditInternal", "endBatchEdit", "endBatchEditInternal", "closeConnection", "commitText", "", "newCursorPosition", "setComposingRegion", "start", "end", "setComposingText", "deleteSurroundingTextInCodePoints", "beforeLength", "afterLength", "deleteSurroundingText", "setSelection", "finishComposingText", "sendKeyEvent", "event", "Landroid/view/KeyEvent;", "getTextBeforeCursor", "maxChars", "flags", "getTextAfterCursor", "getSelectedText", "requestCursorUpdates", "cursorUpdateMode", "performHandwritingGesture", "gesture", "Landroid/view/inputmethod/HandwritingGesture;", "executor", "Ljava/util/concurrent/Executor;", "consumer", "Ljava/util/function/IntConsumer;", "previewHandwritingGesture", "Landroid/view/inputmethod/PreviewableHandwritingGesture;", "cancellationSignal", "Landroid/os/CancellationSignal;", "getExtractedText", "Landroid/view/inputmethod/ExtractedText;", "request", "Landroid/view/inputmethod/ExtractedTextRequest;", "getCursorCapsMode", "reqModes", "performContextMenuAction", "id", "sendSynthesizedKeyEvent", "code", "performEditorAction", "editorAction", "commitCompletion", "Landroid/view/inputmethod/CompletionInfo;", "commitCorrection", "correctionInfo", "Landroid/view/inputmethod/CorrectionInfo;", "getHandler", "Landroid/os/Handler;", "clearMetaKeyStates", "states", "reportFullscreenMode", "enabled", "performPrivateCommand", "action", "", "data", "Landroid/os/Bundle;", "commitContent", "inputContentInfo", "Landroid/view/inputmethod/InputContentInfo;", "opts", "logDebug", "message", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class StatelessInputConnection implements InputConnection {
    public static final int $stable = 8;
    private int batchDepth;
    private final InputConnection commitContentDelegateInputConnection;
    private final MutableVector<Function1<ImeEditCommandScope, Unit>> editCommands = new MutableVector<>(new Function1[16], 0);
    private final TextInputSession session;
    private final StatelessInputConnection$terminalInputConnection$1 terminalInputConnection;

    /* JADX WARN: Type inference failed for: r3v2, types: [android.view.inputmethod.InputConnection, androidx.compose.foundation.text.input.internal.StatelessInputConnection$terminalInputConnection$1] */
    public StatelessInputConnection(TextInputSession textInputSession, EditorInfo editorInfo) {
        this.session = textInputSession;
        ?? r3 = new InputConnectionWrapper(this) { // from class: androidx.compose.foundation.text.input.internal.StatelessInputConnection$terminalInputConnection$1
            {
                super(this, false);
            }

            @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
            public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
                return false;
            }

            @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
            public boolean performPrivateCommand(String action, Bundle data) {
                return true;
            }
        };
        this.terminalInputConnection = r3;
        this.commitContentDelegateInputConnection = InputConnectionCompat.createWrapper((InputConnection) r3, editorInfo, new InputConnectionCompat.OnCommitContentListener() { // from class: androidx.compose.foundation.text.input.internal.StatelessInputConnection$commitContentDelegateInputConnection$1
            public boolean onCommitContent(InputContentInfoCompat inputContentInfo, int flags, Bundle opts) {
                if ((flags & 1) != 0) {
                    try {
                        inputContentInfo.requestPermission();
                        Object objUnwrap = inputContentInfo.unwrap();
                        objUnwrap.getClass();
                        Parcelable parcelable = (Parcelable) objUnwrap;
                        opts = opts == null ? new Bundle() : new Bundle(opts);
                        opts.putParcelable("EXTRA_INPUT_CONTENT_INFO", parcelable);
                    } catch (Exception e) {
                        this.this$0.logDebug("Can't insert content from IME; requestPermission() failed, " + e);
                        return false;
                    }
                }
                return this.this$0.session.onCommitContent(StatelessInputConnection_androidKt.toTransferableContent(inputContentInfo, opts));
            }
        });
    }

    private final boolean beginBatchEditInternal() {
        return this.session.beginBatchEdit();
    }

    private final boolean endBatchEditInternal() {
        return this.session.endBatchEdit();
    }

    private static /* synthetic */ void getCommitContentDelegateInputConnection$annotations() {
    }

    private final TextFieldCharSequence getText() {
        return this.session.getText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logDebug(String message) {
    }

    private final void sendSynthesizedKeyEvent(int code) {
        sendKeyEvent(new KeyEvent(0, code));
        sendKeyEvent(new KeyEvent(1, code));
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        logDebug("beginBatchEdit()");
        return beginBatchEditInternal();
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int states) {
        logDebug("clearMetaKeyStates(" + states + ')');
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public void closeConnection() {
        logDebug("closeConnection()");
        this.editCommands.clear();
        this.batchDepth = 0;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCompletion(CompletionInfo text) {
        StringBuilder sb = new StringBuilder("commitCompletion(");
        sb.append((Object) (text != null ? text.getText() : null));
        sb.append(')');
        logDebug(sb.toString());
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
        logDebug("commitContent(" + inputContentInfo + ", " + flags + ", " + opts + ')');
        return Api25CommitContentImpl.INSTANCE.commitContent(this.commitContentDelegateInputConnection, inputContentInfo, flags, opts);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence text, int newCursorPosition) {
        logDebug("commitText(\"" + ((Object) text) + "\", " + newCursorPosition + ')');
        if (text == null) {
            return true;
        }
        ImeEditCommand_androidKt.commitText(this.session, text.toString(), newCursorPosition);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        logDebug("deleteSurroundingText(" + beforeLength + ", " + afterLength + ')');
        ImeEditCommand_androidKt.deleteSurroundingText(this.session, beforeLength, afterLength);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        logDebug("deleteSurroundingTextInCodePoints(" + beforeLength + ", " + afterLength + ')');
        ImeEditCommand_androidKt.deleteSurroundingTextInCodePoints(this.session, beforeLength, afterLength);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        logDebug("endBatchEdit()");
        return endBatchEditInternal();
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        logDebug("finishComposingText()");
        ImeEditCommand_androidKt.finishComposingText(this.session);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public int getCursorCapsMode(int reqModes) {
        logDebug("getCursorCapsMode(" + reqModes + ')');
        return TextUtils.getCapsMode(getText(), TextRange.getMin-impl(getText().getSelection()), reqModes);
    }

    @Override // android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        logDebug("getExtractedText(" + request + ", " + flags + ')');
        return StatelessInputConnection_androidKt.access$toExtractedText(getText());
    }

    @Override // android.view.inputmethod.InputConnection
    public Handler getHandler() {
        logDebug("getHandler()");
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getSelectedText(int flags) {
        String string = TextRange.getCollapsed-impl(getText().getSelection()) ? null : TextFieldCharSequenceKt.getSelectedText(getText()).toString();
        logDebug("getSelectedText(" + flags + "): " + ((Object) string));
        return string;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextAfterCursor(int maxChars, int flags) {
        String string = TextFieldCharSequenceKt.getTextAfterSelection(getText(), maxChars).toString();
        logDebug("getTextAfterCursor(" + maxChars + ", " + flags + "): " + string);
        return string;
    }

    @Override // android.view.inputmethod.InputConnection
    public CharSequence getTextBeforeCursor(int maxChars, int flags) {
        String string = TextFieldCharSequenceKt.getTextBeforeSelection(getText(), maxChars).toString();
        logDebug("getTextBeforeCursor(" + maxChars + ", " + flags + "): " + string);
        return string;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int id) {
        logDebug("performContextMenuAction(" + id + ')');
        switch (id) {
            case R.id.selectAll:
                ImeEditCommand_androidKt.setSelection(this.session, 0, getText().length());
                return false;
            case R.id.cut:
                sendSynthesizedKeyEvent(277);
                return false;
            case R.id.copy:
                sendSynthesizedKeyEvent(278);
                return false;
            case R.id.paste:
                sendSynthesizedKeyEvent(279);
                return false;
            default:
                return false;
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performEditorAction(int editorAction) {
        int i;
        logDebug("performEditorAction(" + editorAction + ')');
        if (editorAction != 0) {
            switch (editorAction) {
                case 2:
                    i = ImeAction.Companion.getGo-eUduSuo();
                    break;
                case 3:
                    i = ImeAction.Companion.getSearch-eUduSuo();
                    break;
                case 4:
                    i = ImeAction.Companion.getSend-eUduSuo();
                    break;
                case 5:
                    i = ImeAction.Companion.getNext-eUduSuo();
                    break;
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                    i = ImeAction.Companion.getDone-eUduSuo();
                    break;
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                    i = ImeAction.Companion.getPrevious-eUduSuo();
                    break;
                default:
                    logDebug("IME sent an unrecognized editor action: " + editorAction);
                    i = ImeAction.Companion.getDefault-eUduSuo();
                    break;
            }
        } else {
            i = ImeAction.Companion.getDefault-eUduSuo();
        }
        this.session.mo1521onImeActionKlQnJC8(i);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public void performHandwritingGesture(HandwritingGesture gesture, Executor executor, IntConsumer consumer) {
        logDebug("performHandwritingGesture(" + gesture + ", " + executor + ", " + consumer + ')');
        if (Build.VERSION.SDK_INT < 34) {
            return;
        }
        Api34PerformHandwritingGestureImpl.INSTANCE.performHandwritingGesture(this.session, gesture, executor, consumer);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String action, Bundle data) {
        logDebug("performPrivateCommand(" + action + ", " + data + ')');
        return this.commitContentDelegateInputConnection.performPrivateCommand(action, data);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean previewHandwritingGesture(PreviewableHandwritingGesture gesture, CancellationSignal cancellationSignal) {
        logDebug("previewHandwritingGesture(" + gesture + ", " + cancellationSignal + ')');
        if (Build.VERSION.SDK_INT < 34) {
            return false;
        }
        return Api34PerformHandwritingGestureImpl.INSTANCE.previewHandwritingGesture(this.session, gesture, cancellationSignal);
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean reportFullscreenMode(boolean enabled) {
        logDebug("reportFullscreenMode(" + enabled + ')');
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        logDebug("requestCursorUpdates(" + cursorUpdateMode + ')');
        this.session.requestCursorUpdates(cursorUpdateMode);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent event) {
        logDebug("sendKeyEvent(" + event + ')');
        this.session.sendKeyEvent(event);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int start, int end) {
        logDebug("setComposingRegion(" + start + ", " + end + ')');
        ImeEditCommand_androidKt.setComposingRegion(this.session, start, end);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        logDebug("setComposingText(\"" + ((Object) text) + "\", " + newCursorPosition + ')');
        if (text == null) {
            return true;
        }
        TextInputSession textInputSession = this.session;
        String string = text.toString();
        Spanned spanned = text instanceof Spanned ? (Spanned) text : null;
        ImeEditCommand_androidKt.setComposingText(textInputSession, string, newCursorPosition, spanned != null ? StatelessInputConnection_androidKt.toAnnotationList(spanned) : null);
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public boolean setSelection(int start, int end) {
        logDebug("setSelection(" + start + ", " + end + ')');
        ImeEditCommand_androidKt.setSelection(this.session, start, end);
        this.session.updateTouchMode(false);
        return true;
    }
}
