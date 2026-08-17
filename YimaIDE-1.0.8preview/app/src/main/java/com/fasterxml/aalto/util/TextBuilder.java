package com.fasterxml.aalto.util;

import com.fasterxml.aalto.in.ReaderConfig;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TextBuilder {
    private static final char[] sIndSpacesArray;
    private static final String[] sIndSpacesStrings;
    private static final char[] sIndTabsArray;
    private static final String[] sIndTabsStrings;
    static final char[] sNoChars = new char[0];
    private final ReaderConfig _config;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _isIndentation = false;
    private char[] _resultArray;
    private int _resultLen;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    static {
        char[] cArr = {'\n', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        sIndSpacesArray = cArr;
        sIndSpacesStrings = new String[cArr.length];
        char[] cArr2 = {'\n', '\t', '\t', '\t', '\t', '\t', '\t', '\t', '\t', '\t'};
        sIndTabsArray = cArr2;
        sIndTabsStrings = new String[cArr2.length];
    }

    private TextBuilder(ReaderConfig readerConfig) {
        this._config = readerConfig;
    }

    private final char[] allocBuffer(int i) {
        char[] cArrAllocMediumCBuffer;
        int iMax = Math.max(500, i);
        ReaderConfig readerConfig = this._config;
        return (readerConfig == null || (cArrAllocMediumCBuffer = readerConfig.allocMediumCBuffer(iMax)) == null) ? new char[iMax] : cArrAllocMediumCBuffer;
    }

    private char[] buildResultArray() {
        int i;
        String str = this._resultString;
        if (str != null) {
            return str.toCharArray();
        }
        int size = size();
        if (size < 1) {
            return sNoChars;
        }
        char[] cArr = new char[size];
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size2 = arrayList.size();
            i = 0;
            for (int i2 = 0; i2 < size2; i2++) {
                char[] cArr2 = this._segments.get(i2);
                int length = cArr2.length;
                System.arraycopy(cArr2, 0, cArr, i, length);
                i += length;
            }
        } else {
            i = 0;
        }
        System.arraycopy(this._currentSegment, 0, cArr, i, this._currentSize);
        return cArr;
    }

    private int calcNewSize(int i) {
        return Math.min(i + (i < 8000 ? i : i >> 1), 262144);
    }

    public static TextBuilder createRecyclableBuffer(ReaderConfig readerConfig) {
        return new TextBuilder(readerConfig);
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        char[] cArrBuildResultArray = buildResultArray();
        this._resultArray = cArrBuildResultArray;
        return cArrBuildResultArray;
    }

    public String contentsAsString() {
        if (this._resultString == null) {
            char[] cArr = this._resultArray;
            if (cArr != null) {
                this._resultString = new String(cArr);
            } else {
                int i = this._segmentSize;
                int i2 = this._currentSize;
                if (i == 0) {
                    String str = i2 == 0 ? XmlPullParser.NO_NAMESPACE : new String(this._currentSegment, 0, i2);
                    this._resultString = str;
                    return str;
                }
                StringBuilder sb = new StringBuilder(i + i2);
                ArrayList<char[]> arrayList = this._segments;
                if (arrayList != null) {
                    int size = arrayList.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        char[] cArr2 = this._segments.get(i3);
                        sb.append(cArr2, 0, cArr2.length);
                    }
                }
                sb.append(this._currentSegment, 0, i2);
                this._resultString = sb.toString();
            }
        }
        return this._resultString;
    }

    public int contentsToArray(int i, char[] cArr, int i2, int i3) {
        ArrayList<char[]> arrayList = this._segments;
        int i4 = 0;
        if (arrayList != null) {
            int size = arrayList.size();
            int i5 = 0;
            for (int i6 = 0; i6 < size; i6++) {
                char[] cArr2 = this._segments.get(i6);
                int length = cArr2.length;
                int i7 = length - i;
                if (i7 < 1) {
                    i -= length;
                } else {
                    if (i7 >= i3) {
                        System.arraycopy(cArr2, i, cArr, i2, i3);
                        return i5 + i3;
                    }
                    System.arraycopy(cArr2, i, cArr, i2, i7);
                    i5 += i7;
                    i2 += i7;
                    i3 -= i7;
                    i = 0;
                }
            }
            i4 = i5;
        }
        if (i3 <= 0) {
            return i4;
        }
        int i8 = this._currentSize - i;
        if (i3 > i8) {
            i3 = i8;
        }
        if (i3 <= 0) {
            return i4;
        }
        System.arraycopy(this._currentSegment, i, cArr, i2, i3);
        return i4 + i3;
    }

    public char[] finishCurrentSegment() {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        this._segments.add(this._currentSegment);
        int length = this._currentSegment.length;
        this._segmentSize += length;
        char[] cArr = new char[calcNewSize(length)];
        this._currentSize = 0;
        this._currentSegment = cArr;
        return cArr;
    }

    public char[] getBufferWithoutReset() {
        return this._currentSegment;
    }

    public int getCurrentLength() {
        return this._currentSize;
    }

    public char[] getTextBuffer() {
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null && arrayList.size() != 0) {
            return contentsAsArray();
        }
        char[] cArr = this._resultArray;
        return cArr != null ? cArr : this._currentSegment;
    }

    public boolean isAllWhitespace() {
        if (this._isIndentation) {
            return true;
        }
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                for (char c : this._segments.get(i)) {
                    if (c > ' ') {
                        return false;
                    }
                }
            }
        }
        char[] cArr = this._currentSegment;
        int i2 = this._currentSize;
        for (int i3 = 0; i3 < i2; i3++) {
            if (cArr[i3] > ' ') {
                return false;
            }
        }
        return true;
    }

    public void recycle(boolean z) {
        if (this._config == null || this._currentSegment == null) {
            return;
        }
        if (z) {
            this._resultString = null;
            this._resultArray = null;
        } else if (this._segmentSize + this._currentSize > 0) {
            return;
        }
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null && arrayList.size() > 0) {
            this._segments.clear();
            this._segmentSize = 0;
        }
        char[] cArr = this._currentSegment;
        this._currentSegment = null;
        this._config.freeMediumCBuffer(cArr);
    }

    public char[] resetWithEmpty() {
        this._resultString = null;
        this._resultArray = null;
        this._isIndentation = false;
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null && arrayList.size() > 0) {
            this._segments.clear();
            this._segmentSize = 0;
        }
        this._currentSize = 0;
        if (this._currentSegment == null) {
            this._currentSegment = allocBuffer(0);
        }
        return this._currentSegment;
    }

    public void resetWithIndentation(int i, char c) {
        String strSubstring;
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null && arrayList.size() > 0) {
            this._segments.clear();
            this._segmentSize = 0;
        }
        this._currentSize = -1;
        this._isIndentation = true;
        int i2 = i + 1;
        this._resultLen = i2;
        if (c == '\t') {
            this._resultArray = sIndTabsArray;
            String[] strArr = sIndTabsStrings;
            strSubstring = strArr[i];
            if (strSubstring == null) {
                strSubstring = "\n\t\t\t\t\t\t\t\t\t".substring(0, i2);
                strArr[i] = strSubstring;
            }
        } else {
            this._resultArray = sIndSpacesArray;
            String[] strArr2 = sIndSpacesStrings;
            strSubstring = strArr2[i];
            if (strSubstring == null) {
                strSubstring = "\n                                 ".substring(0, i2);
                strArr2[i] = strSubstring;
            }
        }
        this._resultString = strSubstring;
    }

    public void setCurrentLength(int i) {
        this._currentSize = i;
    }

    public int size() {
        int i = this._currentSize;
        return i < 0 ? this._resultLen : i + this._segmentSize;
    }

    public String toString() {
        this._resultString = null;
        this._resultArray = null;
        return contentsAsString();
    }
}
