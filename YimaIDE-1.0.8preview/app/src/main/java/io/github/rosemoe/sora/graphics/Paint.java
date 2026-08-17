package io.github.rosemoe.sora.graphics;

import android.graphics.Typeface;
import io.github.rosemoe.sora.text.ContentLine;
import io.github.rosemoe.sora.text.FunctionCharacters;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Paint extends android.graphics.Paint {
    private boolean renderFunctionCharacters;
    private float spaceWidth;

    public Paint(boolean z) {
        this.renderFunctionCharacters = z;
        this.spaceWidth = measureText(" ");
    }

    private int breakTextImpl(ContentLine contentLine, int i, int i2, int i3, int i4, boolean z, float f) {
        return getOffsetForAdvance(contentLine.getBackingCharArray(), i, i2, i3, i4, z, f);
    }

    public int findOffsetByRunAdvance(ContentLine contentLine, int i, int i2, int i3, int i4, boolean z, float f) {
        char[] cArr;
        if (!this.renderFunctionCharacters) {
            return breakTextImpl(contentLine, i, i2, i3, i4, z, f);
        }
        char[] backingCharArray = contentLine.getBackingCharArray();
        int i5 = i;
        int i6 = i5;
        float fMeasureTextRunAdvance = 0.0f;
        while (i6 < i2) {
            char c = backingCharArray[i6];
            if (FunctionCharacters.isEditorFunctionChar(c)) {
                int iBreakTextImpl = i5 == i6 ? i6 : breakTextImpl(contentLine, i5, i6, i3, i4, z, f - fMeasureTextRunAdvance);
                if (iBreakTextImpl < i6) {
                    return iBreakTextImpl;
                }
                cArr = backingCharArray;
                fMeasureTextRunAdvance = fMeasureTextRunAdvance + measureTextRunAdvance(cArr, i5, i6, i3, i4, z) + measureText(FunctionCharacters.getNameForFunctionCharacter(c));
                if (fMeasureTextRunAdvance >= f) {
                    return i6;
                }
                i5 = i6 + 1;
            } else {
                cArr = backingCharArray;
            }
            i6++;
            backingCharArray = cArr;
        }
        return i5 < i2 ? breakTextImpl(contentLine, i5, i2, i3, i4, z, f - fMeasureTextRunAdvance) : i2;
    }

    public float getSpaceWidth() {
        return this.spaceWidth;
    }

    public boolean isRenderFunctionCharacters() {
        return this.renderFunctionCharacters;
    }

    public float measureTextRunAdvance(char[] cArr, int i, int i2, int i3, int i4, boolean z) {
        return myGetTextRunAdvances(cArr, i, i2 - i, i3, i4 - i3, z, null, 0);
    }

    public float myGetTextRunAdvances(char[] cArr, int i, int i2, int i3, int i4, boolean z, float[] fArr, int i5) {
        float fMeasureText;
        float textRunAdvances = getTextRunAdvances(cArr, i, i2, i3, i4, z, fArr, i5);
        if (this.renderFunctionCharacters) {
            for (int i6 = 0; i6 < i2; i6++) {
                char c = cArr[i + i6];
                if (FunctionCharacters.isEditorFunctionChar(c)) {
                    float fMeasureText2 = measureText(FunctionCharacters.getNameForFunctionCharacter(c));
                    if (fArr != null) {
                        int i7 = i5 + i6;
                        fMeasureText = textRunAdvances - fArr[i7];
                        fArr[i7] = fMeasureText2;
                    } else {
                        fMeasureText = textRunAdvances - measureText(Character.toString(c));
                    }
                    textRunAdvances = fMeasureText + fMeasureText2;
                }
            }
        }
        return textRunAdvances;
    }

    public void onAttributeUpdate() {
        this.spaceWidth = measureText(" ");
    }

    public void setFontFeatureSettingsWrapped(String str) {
        super.setFontFeatureSettings(str);
        onAttributeUpdate();
    }

    @Override // android.graphics.Paint
    public void setLetterSpacing(float f) {
        super.setLetterSpacing(f);
        onAttributeUpdate();
    }

    public void setRenderFunctionCharacters(boolean z) {
        this.renderFunctionCharacters = z;
    }

    public void setTextSizeWrapped(float f) {
        super.setTextSize(f);
        onAttributeUpdate();
    }

    public void setTypefaceWrapped(Typeface typeface) {
        super.setTypeface(typeface);
        onAttributeUpdate();
    }

    public Paint() {
        this(false);
    }
}
