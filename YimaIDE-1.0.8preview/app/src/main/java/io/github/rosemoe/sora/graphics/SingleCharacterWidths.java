package io.github.rosemoe.sora.graphics;

import android.util.SparseArray;
import io.github.rosemoe.sora.text.CharArrayWrapper;
import io.github.rosemoe.sora.text.FunctionCharacters;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SingleCharacterWidths {
    private static final long PRECISION = 1000;
    private boolean handleFunctionCharacters;
    private final int tabWidth;
    private final float[] cache = new float[65536];
    public final char[] buffer = new char[10];
    public final float[] widths = new float[10];
    public final SparseArray<Float> codePointWidths = new SparseArray<>();

    public SingleCharacterWidths(int i) {
        this.tabWidth = i;
    }

    public static boolean isEmoji(char c) {
        return c == 55356 || c == 55357 || c == 55358;
    }

    public void clearCache() {
        Arrays.fill(this.cache, 0.0f);
        this.codePointWidths.clear();
    }

    public boolean isHandleFunctionCharacters() {
        return this.handleFunctionCharacters;
    }

    public float measureChar(char c, Paint paint) {
        int i;
        if (c == '\t') {
            i = this.tabWidth;
            c = ' ';
        } else {
            i = 1;
        }
        float fMeasureText = this.cache[c];
        if (fMeasureText == 0.0f) {
            char[] cArr = this.buffer;
            cArr[0] = c;
            fMeasureText = paint.measureText(cArr, 0, 1);
            this.cache[c] = fMeasureText;
        }
        return fMeasureText * i;
    }

    public float measureCodePoint(int i, Paint paint) {
        if (i <= 65535) {
            return measureChar((char) i, paint);
        }
        Float fValueOf = this.codePointWidths.get(i);
        if (fValueOf == null) {
            fValueOf = Float.valueOf(paint.measureText(this.buffer, 0, Character.toChars(i, this.buffer, 0)));
            this.codePointWidths.put(i, fValueOf);
        }
        return fValueOf.floatValue();
    }

    public float measureText(CharSequence charSequence, int i, int i2, Paint paint) {
        double dCeil;
        char[] cArr;
        long jCeil = 0;
        while (i < i2) {
            char cCharAt = charSequence.charAt(i);
            if (isEmoji(cCharAt)) {
                int i3 = i + 4;
                if (i3 <= i2) {
                    paint.getTextWidths(charSequence, i, i3, this.widths);
                    float[] fArr = this.widths;
                    float f = fArr[0];
                    if (f > 0.0f && fArr[1] == 0.0f && fArr[2] == 0.0f && fArr[3] == 0.0f) {
                        i += 3;
                        dCeil = Math.ceil(f * 1000.0f);
                        jCeil += (long) dCeil;
                    }
                }
                int iMin = Math.min(i2, i + 2) - i;
                int i4 = 0;
                while (true) {
                    cArr = this.buffer;
                    if (i4 >= iMin) {
                        break;
                    }
                    cArr[i4] = charSequence.charAt(i + i4);
                    i4++;
                }
                jCeil += (long) Math.ceil(paint.measureText(cArr, 0, iMin) * 1000.0f);
                i += iMin - 1;
            } else if (isHandleFunctionCharacters() && FunctionCharacters.isEditorFunctionChar(cCharAt)) {
                String nameForFunctionCharacter = FunctionCharacters.getNameForFunctionCharacter(cCharAt);
                for (int i5 = 0; i5 < nameForFunctionCharacter.length(); i5++) {
                    jCeil += (long) Math.ceil(measureChar(nameForFunctionCharacter.charAt(i5), paint) * 1000.0f);
                }
            } else {
                dCeil = Math.ceil(measureChar(cCharAt, paint) * 1000.0f);
                jCeil += (long) dCeil;
            }
            i++;
        }
        return jCeil / 1000.0f;
    }

    public void setHandleFunctionCharacters(boolean z) {
        this.handleFunctionCharacters = z;
    }

    public float measureText(CharSequence charSequence, Paint paint) {
        return measureText(charSequence, 0, charSequence.length(), paint);
    }

    public float measureText(char[] cArr, int i, int i2, Paint paint) {
        return measureText(new CharArrayWrapper(cArr, cArr.length), i, i2, paint);
    }
}
