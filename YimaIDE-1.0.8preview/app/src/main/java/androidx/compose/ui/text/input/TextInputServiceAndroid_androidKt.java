package androidx.compose.ui.text.input;

import android.view.Choreographer;
import android.view.inputmethod.EditorInfo;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextInputServiceAndroid_androidKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.emoji2.text.EmojiCompat;
import java.util.concurrent.Executor;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\u001c\u0010\u0005\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\fH\u0000\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"DEBUG_CLASS", "", "updateWithEmojiCompat", "", "Landroid/view/inputmethod/EditorInfo;", "update", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "asExecutor", "Ljava/util/concurrent/Executor;", "Landroid/view/Choreographer;", "hasFlag", "", "bits", "", "flag", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextInputServiceAndroid_androidKt {
    private static final String DEBUG_CLASS = "TextInputServiceAndroid";

    public static void a(Choreographer choreographer, final Runnable runnable) {
        choreographer.postFrameCallback(new Choreographer.FrameCallback() { // from class: pae
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                runnable.run();
            }
        });
    }

    public static final Executor asExecutor(final Choreographer choreographer) {
        return new Executor() { // from class: oae
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                TextInputServiceAndroid_androidKt.a(choreographer, runnable);
            }
        };
    }

    private static final boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    public static final void update(EditorInfo editorInfo, ImeOptions imeOptions, TextFieldValue textFieldValue) {
        String privateImeOptions;
        int i = imeOptions.getImeAction-eUduSuo();
        ImeAction.Companion companion = ImeAction.INSTANCE;
        int i2 = 6;
        if (ImeAction.m5647equalsimpl0(i, companion.m5660getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i2 = 0;
            }
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5664getNoneeUduSuo())) {
            i2 = 1;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5662getGoeUduSuo())) {
            i2 = 2;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5663getNexteUduSuo())) {
            i2 = 5;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5665getPreviouseUduSuo())) {
            i2 = 7;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5666getSearcheUduSuo())) {
            i2 = 3;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5667getSendeUduSuo())) {
            i2 = 4;
        } else if (!ImeAction.m5647equalsimpl0(i, companion.m5661getDoneeUduSuo())) {
            k2d.a("invalid ImeAction");
            return;
        }
        editorInfo.imeOptions = i2;
        PlatformImeOptions platformImeOptions = imeOptions.getPlatformImeOptions();
        if (platformImeOptions != null && (privateImeOptions = platformImeOptions.getPrivateImeOptions()) != null) {
            editorInfo.privateImeOptions = privateImeOptions;
        }
        int i3 = imeOptions.getKeyboardType-PjHm6EE();
        KeyboardType.Companion companion2 = KeyboardType.INSTANCE;
        if (KeyboardType.m5690equalsimpl0(i3, companion2.m5711getTextPjHm6EE())) {
            editorInfo.inputType = 1;
        } else if (KeyboardType.m5690equalsimpl0(i3, companion2.m5704getAsciiPjHm6EE())) {
            editorInfo.inputType = 1;
            editorInfo.imeOptions |= Integer.MIN_VALUE;
        } else if (KeyboardType.m5690equalsimpl0(i3, companion2.m5707getNumberPjHm6EE())) {
            editorInfo.inputType = 2;
        } else if (KeyboardType.m5690equalsimpl0(i3, companion2.m5710getPhonePjHm6EE())) {
            editorInfo.inputType = 3;
        } else if (KeyboardType.m5690equalsimpl0(i3, companion2.m5713getUriPjHm6EE())) {
            editorInfo.inputType = 17;
        } else if (KeyboardType.m5690equalsimpl0(i3, companion2.m5706getEmailPjHm6EE())) {
            editorInfo.inputType = 33;
        } else if (KeyboardType.m5690equalsimpl0(i3, companion2.m5709getPasswordPjHm6EE())) {
            editorInfo.inputType = 129;
        } else if (KeyboardType.m5690equalsimpl0(i3, companion2.m5708getNumberPasswordPjHm6EE())) {
            editorInfo.inputType = 18;
        } else {
            if (!KeyboardType.m5690equalsimpl0(i3, companion2.m5705getDecimalPjHm6EE())) {
                k2d.a("Invalid Keyboard Type");
                return;
            }
            editorInfo.inputType = InputDeviceCompat.SOURCE_MOUSE;
        }
        if (!imeOptions.getSingleLine() && hasFlag(editorInfo.inputType, 1)) {
            editorInfo.inputType |= 131072;
            if (ImeAction.m5647equalsimpl0(imeOptions.getImeAction-eUduSuo(), companion.m5660getDefaulteUduSuo())) {
                editorInfo.imeOptions |= 1073741824;
            }
        }
        if (hasFlag(editorInfo.inputType, 1)) {
            int i4 = imeOptions.getCapitalization-IUNYP9k();
            KeyboardCapitalization.Companion companion3 = KeyboardCapitalization.INSTANCE;
            if (KeyboardCapitalization.m5673equalsimpl0(i4, companion3.m5682getCharactersIUNYP9k())) {
                editorInfo.inputType |= 4096;
            } else if (KeyboardCapitalization.m5673equalsimpl0(i4, companion3.m5686getWordsIUNYP9k())) {
                editorInfo.inputType |= 8192;
            } else if (KeyboardCapitalization.m5673equalsimpl0(i4, companion3.m5684getSentencesIUNYP9k())) {
                editorInfo.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                editorInfo.inputType |= 32768;
            }
        }
        editorInfo.initialSelStart = TextRange.m5479getStartimpl(textFieldValue.getSelection());
        editorInfo.initialSelEnd = TextRange.m5474getEndimpl(textFieldValue.getSelection());
        EditorInfoCompat.setInitialSurroundingText(editorInfo, textFieldValue.getText());
        editorInfo.imeOptions |= 33554432;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWithEmojiCompat(EditorInfo editorInfo) {
        if (EmojiCompat.isConfigured()) {
            EmojiCompat.get().updateEditorInfo(editorInfo);
        }
    }
}
