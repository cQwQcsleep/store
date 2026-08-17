package com.fasterxml.aalto.in;

import com.fasterxml.aalto.impl.ErrorConsts;
import com.fasterxml.aalto.util.DataUtil;
import java.text.MessageFormat;
import javax.xml.namespace.QName;
import org.codehaus.stax2.typed.TypedValueDecoder;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AttributeCollector {
    final ReaderConfig _config;
    protected int _hashAreaSize;
    protected int _spillAreaEnd;
    private PName[] _names = null;
    private char[] _valueBuffer = null;
    protected int[] _attrMap = null;
    private int[] _valueOffsets = null;
    private boolean _needToResetValues = true;
    private String _errorMsg = null;
    private String _allAttrValues = null;
    private int _attrCount = 0;

    public AttributeCollector(ReaderConfig readerConfig) {
        this._config = readerConfig;
    }

    private static final boolean isSpace(char c) {
        return c <= ' ';
    }

    private void noteDupAttr(int i, int i2) {
        this._errorMsg = MessageFormat.format(ErrorConsts.ERR_WF_DUP_ATTRS, this._names[i].toString(), Integer.valueOf(i), this._names[i2].toString(), Integer.valueOf(i2));
    }

    public final void decodeValue(int i, TypedValueDecoder typedValueDecoder) throws IllegalArgumentException {
        int i2;
        int i3;
        if (i < 0 || i >= this._attrCount) {
            pb0.a("Invalid index ", i, "; current element has only ", this._attrCount, " attributes");
            return;
        }
        int[] iArr = this._valueOffsets;
        if (i == 0) {
            i3 = 0;
            i2 = iArr[0];
        } else {
            int i4 = iArr[i - 1];
            i2 = iArr[i];
            i3 = i4;
        }
        char[] cArr = this._valueBuffer;
        while (i3 < i2) {
            if (!isSpace(cArr[i3])) {
                while (true) {
                    int i5 = i2 - 1;
                    if (i5 <= i3 || !isSpace(cArr[i5])) {
                        break;
                    } else {
                        i2 = i5;
                    }
                }
                typedValueDecoder.decode(cArr, i3, i2);
                return;
            }
            i3++;
        }
        typedValueDecoder.handleEmptyValue();
    }

    public int findIndex(String str, String str2) {
        int i = this._hashAreaSize;
        if (i < 1) {
            int i2 = this._attrCount;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this._names[i3].boundEquals(str, str2)) {
                    return i3;
                }
            }
            return -1;
        }
        int iBoundHashCode = PName.boundHashCode(str, str2);
        int i4 = this._attrMap[(i - 1) & iBoundHashCode];
        if (i4 > 0) {
            int i5 = i4 - 1;
            if (this._names[i5].boundEquals(str, str2)) {
                return i5;
            }
            int i6 = this._spillAreaEnd;
            while (i < i6) {
                int[] iArr = this._attrMap;
                if (iArr[i] == iBoundHashCode) {
                    int i7 = iArr[i + 1];
                    if (this._names[i7].boundEquals(str, str2)) {
                        return i7;
                    }
                }
                i += 2;
            }
        }
        return -1;
    }

    public final int finishLastValue(int i) {
        if (this._needToResetValues) {
            return 0;
        }
        this._needToResetValues = true;
        int i2 = this._attrCount;
        this._valueOffsets[i2 - 1] = i;
        if (i2 >= 3) {
            return finishLastValue2();
        }
        this._hashAreaSize = 0;
        if (i2 == 2) {
            PName[] pNameArr = this._names;
            if (pNameArr[0].boundEquals(pNameArr[1])) {
                noteDupAttr(0, 1);
                return -1;
            }
        }
        return i2;
    }

    public final int finishLastValue2() {
        int i = this._attrCount;
        PName[] pNameArr = this._names;
        int[] iArrGrowArrayBy = this._attrMap;
        int i2 = 8;
        while (i2 < (i >> 2) + i) {
            i2 += i2;
        }
        this._hashAreaSize = i2;
        int i3 = (i2 >> 4) + i2;
        if (iArrGrowArrayBy == null || iArrGrowArrayBy.length < i3) {
            iArrGrowArrayBy = new int[i3];
        } else {
            iArrGrowArrayBy[7] = 0;
            iArrGrowArrayBy[6] = 0;
            iArrGrowArrayBy[5] = 0;
            iArrGrowArrayBy[4] = 0;
            iArrGrowArrayBy[3] = 0;
            iArrGrowArrayBy[2] = 0;
            iArrGrowArrayBy[1] = 0;
            iArrGrowArrayBy[0] = 0;
            for (int i4 = 8; i4 < i2; i4++) {
                iArrGrowArrayBy[i4] = 0;
            }
        }
        int i5 = i2 - 1;
        int i6 = i2;
        for (int i7 = 0; i7 < i; i7++) {
            PName pName = pNameArr[i7];
            int iBoundHashCode = pName.boundHashCode();
            int i8 = iBoundHashCode & i5;
            int i9 = iArrGrowArrayBy[i8];
            if (i9 == 0) {
                iArrGrowArrayBy[i8] = i7 + 1;
            } else {
                int i10 = i9 - 1;
                if (pNameArr[i10].boundEquals(pName) && this._errorMsg == null) {
                    noteDupAttr(i10, i7);
                }
                int i11 = i6 + 1;
                if (i11 >= iArrGrowArrayBy.length) {
                    iArrGrowArrayBy = DataUtil.growArrayBy(iArrGrowArrayBy, 8);
                }
                for (int i12 = i2; i12 < i6; i12 += 2) {
                    if (iArrGrowArrayBy[i12] == iBoundHashCode) {
                        int i13 = iArrGrowArrayBy[i12 + 1];
                        if (pNameArr[i13].boundEquals(pName)) {
                            if (this._errorMsg != null) {
                                break;
                            }
                            noteDupAttr(i13, i7);
                            break;
                        }
                    }
                }
                iArrGrowArrayBy[i6] = iBoundHashCode;
                i6 += 2;
                iArrGrowArrayBy[i11] = i7;
            }
        }
        this._spillAreaEnd = i6;
        this._attrMap = iArrGrowArrayBy;
        if (this._errorMsg == null) {
            return i;
        }
        return -1;
    }

    public final int getCount() {
        return this._attrCount;
    }

    public String getErrorMsg() {
        return this._errorMsg;
    }

    public final PName getName(int i) {
        return this._names[i];
    }

    public final QName getQName(int i) {
        return this._names[i].constructQName();
    }

    public String getValue(int i) {
        int i2 = this._attrCount;
        if (this._allAttrValues == null) {
            int i3 = this._valueOffsets[i2 - 1];
            this._allAttrValues = i3 == 0 ? XmlPullParser.NO_NAMESPACE : new String(this._valueBuffer, 0, i3);
        }
        if (i == 0) {
            if (i2 == 1) {
                return this._allAttrValues;
            }
            int i4 = this._valueOffsets[0];
            return i4 == 0 ? XmlPullParser.NO_NAMESPACE : this._allAttrValues.substring(0, i4);
        }
        int[] iArr = this._valueOffsets;
        int i5 = iArr[i - 1];
        int i6 = iArr[i];
        return i5 == i6 ? XmlPullParser.NO_NAMESPACE : this._allAttrValues.substring(i5, i6);
    }

    public char[] startNewValue(PName pName, int i) {
        int i2 = 0;
        if (this._needToResetValues) {
            this._needToResetValues = false;
            this._attrCount = 0;
            this._allAttrValues = null;
            if (this._valueBuffer == null) {
                this._names = new PName[12];
                this._valueBuffer = new char[120];
                this._valueOffsets = new int[12];
            }
        } else {
            int i3 = this._attrCount;
            int[] iArr = this._valueOffsets;
            if (i3 >= iArr.length) {
                PName[] pNameArr = this._names;
                int length = iArr.length;
                int i4 = length + length;
                this._valueOffsets = new int[i4];
                this._names = new PName[i4];
                while (i2 < length) {
                    this._valueOffsets[i2] = iArr[i2];
                    this._names[i2] = pNameArr[i2];
                    i2++;
                }
            }
            if (i3 > 0) {
                this._valueOffsets[i3 - 1] = i;
            }
            i2 = i3;
        }
        this._names[i2] = pName;
        this._attrCount++;
        return this._valueBuffer;
    }

    public char[] valueBufferFull() {
        char[] cArr = this._valueBuffer;
        char[] cArrGrowArrayBy = DataUtil.growArrayBy(cArr, cArr.length);
        this._valueBuffer = cArrGrowArrayBy;
        return cArrGrowArrayBy;
    }

    public String getValue(String str, String str2) {
        int iFindIndex = findIndex(str, str2);
        if (iFindIndex >= 0) {
            return getValue(iFindIndex);
        }
        return null;
    }
}
