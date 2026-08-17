package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.EditorInfo;
import androidx.compose.foundation.text.handwriting.StylusHandwriting_androidKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¨\u0006\u0013"}, d2 = {"update", "", "Landroid/view/inputmethod/EditorInfo;", "text", "", "selection", "Landroidx/compose/ui/text/TextRange;", "imeOptions", "Landroidx/compose/ui/text/input/ImeOptions;", "contentMimeTypes", "", "", "update-pLxbY9I", "(Landroid/view/inputmethod/EditorInfo;Ljava/lang/CharSequence;JLandroidx/compose/ui/text/input/ImeOptions;[Ljava/lang/String;)V", "hasFlag", "", "bits", "", "flag", "foundation"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EditorInfo_androidKt {
    private static final boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    /* JADX INFO: renamed from: update-pLxbY9I, reason: not valid java name */
    public static final void m52updatepLxbY9I(EditorInfo editorInfo, CharSequence charSequence, long j, ImeOptions imeOptions, String[] strArr) {
        String privateImeOptions;
        int i = imeOptions.getImeAction-eUduSuo();
        ImeAction.Companion companion = ImeAction.INSTANCE;
        int i2 = 3;
        int i3 = 6;
        if (ImeAction.m5647equalsimpl0(i, companion.m5660getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i3 = 0;
            }
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5664getNoneeUduSuo())) {
            i3 = 1;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5662getGoeUduSuo())) {
            i3 = 2;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5663getNexteUduSuo())) {
            i3 = 5;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5665getPreviouseUduSuo())) {
            i3 = 7;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5666getSearcheUduSuo())) {
            i3 = 3;
        } else if (ImeAction.m5647equalsimpl0(i, companion.m5667getSendeUduSuo())) {
            i3 = 4;
        } else if (!ImeAction.m5647equalsimpl0(i, companion.m5661getDoneeUduSuo())) {
            k2d.a("invalid ImeAction");
            return;
        }
        editorInfo.imeOptions = i3;
        PlatformImeOptions platformImeOptions = imeOptions.getPlatformImeOptions();
        if (platformImeOptions != null && (privateImeOptions = platformImeOptions.getPrivateImeOptions()) != null) {
            editorInfo.privateImeOptions = privateImeOptions;
        }
        LocaleListHelper.INSTANCE.setHintLocales(editorInfo, imeOptions.getHintLocales());
        int i4 = imeOptions.getKeyboardType-PjHm6EE();
        KeyboardType.Companion companion2 = KeyboardType.INSTANCE;
        if (KeyboardType.m5690equalsimpl0(i4, companion2.m5711getTextPjHm6EE())) {
            i2 = 1;
        } else if (KeyboardType.m5690equalsimpl0(i4, companion2.m5704getAsciiPjHm6EE())) {
            editorInfo.imeOptions |= Integer.MIN_VALUE;
            i2 = 1;
        } else if (KeyboardType.m5690equalsimpl0(i4, companion2.m5707getNumberPjHm6EE())) {
            i2 = 2;
        } else if (!KeyboardType.m5690equalsimpl0(i4, companion2.m5710getPhonePjHm6EE())) {
            if (KeyboardType.m5690equalsimpl0(i4, companion2.m5713getUriPjHm6EE())) {
                i2 = 17;
            } else if (KeyboardType.m5690equalsimpl0(i4, companion2.m5706getEmailPjHm6EE())) {
                i2 = 33;
            } else if (KeyboardType.m5690equalsimpl0(i4, companion2.m5709getPasswordPjHm6EE())) {
                i2 = 129;
            } else if (KeyboardType.m5690equalsimpl0(i4, companion2.m5708getNumberPasswordPjHm6EE())) {
                i2 = 18;
            } else {
                if (!KeyboardType.m5690equalsimpl0(i4, companion2.m5705getDecimalPjHm6EE())) {
                    k2d.a("Invalid Keyboard Type");
                    return;
                }
                i2 = InputDeviceCompat.SOURCE_MOUSE;
            }
        }
        editorInfo.inputType = i2;
        if (!imeOptions.getSingleLine() && hasFlag(editorInfo.inputType, 1)) {
            editorInfo.inputType |= 131072;
            if (ImeAction.m5647equalsimpl0(imeOptions.getImeAction-eUduSuo(), companion.m5660getDefaulteUduSuo())) {
                editorInfo.imeOptions |= 1073741824;
            }
        }
        if (hasFlag(editorInfo.inputType, 1)) {
            int i5 = imeOptions.getCapitalization-IUNYP9k();
            KeyboardCapitalization.Companion companion3 = KeyboardCapitalization.INSTANCE;
            if (KeyboardCapitalization.m5673equalsimpl0(i5, companion3.m5682getCharactersIUNYP9k())) {
                editorInfo.inputType |= 4096;
            } else if (KeyboardCapitalization.m5673equalsimpl0(i5, companion3.m5686getWordsIUNYP9k())) {
                editorInfo.inputType |= 8192;
            } else if (KeyboardCapitalization.m5673equalsimpl0(i5, companion3.m5684getSentencesIUNYP9k())) {
                editorInfo.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                editorInfo.inputType |= 32768;
            }
        }
        editorInfo.initialSelStart = TextRange.m5479getStartimpl(j);
        editorInfo.initialSelEnd = TextRange.m5474getEndimpl(j);
        EditorInfoCompat.setInitialSurroundingText(editorInfo, charSequence);
        if (strArr != null) {
            EditorInfoCompat.setContentMimeTypes(editorInfo, strArr);
        }
        editorInfo.imeOptions |= 33554432;
        if (!StylusHandwriting_androidKt.isStylusHandwritingSupported() || KeyboardType.m5690equalsimpl0(imeOptions.getKeyboardType-PjHm6EE(), companion2.m5709getPasswordPjHm6EE()) || KeyboardType.m5690equalsimpl0(imeOptions.getKeyboardType-PjHm6EE(), companion2.m5708getNumberPasswordPjHm6EE())) {
            EditorInfoCompat.setStylusHandwritingEnabled(editorInfo, false);
        } else {
            EditorInfoCompat.setStylusHandwritingEnabled(editorInfo, true);
            EditorInfoApi34.INSTANCE.setHandwritingGestures(editorInfo);
        }
    }

    /* JADX INFO: renamed from: update-pLxbY9I$default, reason: not valid java name */
    public static /* synthetic */ void m53updatepLxbY9I$default(EditorInfo editorInfo, CharSequence charSequence, long j, ImeOptions imeOptions, String[] strArr, int i, Object obj) {
        if ((i & 8) != 0) {
            strArr = null;
        }
        m52updatepLxbY9I(editorInfo, charSequence, j, imeOptions, strArr);
    }
}
