package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class NodeCounter {
    public static final int END = -1;
    public final DOM _document;
    protected String _format;
    protected String _groupSep;
    protected int _groupSize;
    protected boolean _hasFrom;
    public final DTMAxisIterator _iterator;
    protected String _lang;
    protected String _letterValue;
    public final Translet _translet;
    private static final String[] Thousands = {"", "m", "mm", "mmm"};
    private static final String[] Hundreds = {"", "c", "cc", "ccc", "cd", "d", "dc", "dcc", "dccc", "cm"};
    private static final String[] Tens = {"", "x", "xx", "xxx", "xl", "l", "lx", "lxx", "lxxx", "xc"};
    private static final String[] Ones = {"", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix"};
    protected int _node = -1;
    protected int _nodeType = -1;
    protected double _value = -2.147483648E9d;
    private boolean _separFirst = true;
    private boolean _separLast = false;
    private List<String> _separToks = new ArrayList();
    private List<String> _formatToks = new ArrayList();
    private int _nSepars = 0;
    private int _nFormats = 0;
    private StringBuilder _tempBuffer = new StringBuilder();

    public NodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator, boolean z) {
        this._translet = translet;
        this._document = dom;
        this._iterator = dTMAxisIterator;
        this._hasFrom = z;
    }

    private String alphaValue(int i, int i2, int i3) {
        if (i <= 0) {
            return "" + i;
        }
        int i4 = (i3 - i2) + 1;
        int i5 = i - 1;
        char c = (char) ((i5 % i4) + i2);
        if (i <= i4) {
            return "" + c;
        }
        return alphaValue(i5 / i4, i2, i3) + c;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:45:0x00b2 A[LOOP:3: B:43:0x00a9->B:45:0x00b2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:54:0x00b4 A[EDGE_INSN: B:54:0x00b4->B:46:0x00b4 BREAK  A[LOOP:3: B:43:0x00a9->B:45:0x00b2], SYNTHETIC] */
    private void formatValue(int i, String str, StringBuilder sb) {
        int i2;
        int i3;
        char cCharAt = str.charAt(0);
        if (Character.isDigit(cCharAt)) {
            char numericValue = (char) (cCharAt - Character.getNumericValue(cCharAt));
            StringBuilder sb2 = this._groupSize > 0 ? new StringBuilder() : sb;
            String str2 = "";
            while (i > 0) {
                str2 = ((char) ((i % 10) + numericValue)) + str2;
                i /= 10;
            }
            for (int i4 = 0; i4 < str.length() - str2.length(); i4++) {
                sb2.append(numericValue);
            }
            sb2.append(str2);
            if (this._groupSize > 0) {
                for (int i5 = 0; i5 < sb2.length(); i5++) {
                    if (i5 != 0 && (sb2.length() - i5) % this._groupSize == 0) {
                        sb.append(this._groupSep);
                    }
                    sb.append(sb2.charAt(i5));
                }
                return;
            }
            return;
        }
        if (cCharAt == 'i' && !this._letterValue.equals(Constants.ATTRVAL_ALPHABETIC)) {
            sb.append(romanValue(i));
            return;
        }
        if (cCharAt == 'I' && !this._letterValue.equals(Constants.ATTRVAL_ALPHABETIC)) {
            sb.append(romanValue(i).toUpperCase());
            return;
        }
        if (cCharAt < 945) {
            i2 = cCharAt;
            while (true) {
                i3 = i2 + 1;
                if (Character.isLetterOrDigit((char) i3)) {
                    break;
                    break;
                }
                i2 = i3;
            }
        } else {
            i2 = 969;
            if (cCharAt > 969) {
                i2 = cCharAt;
                while (true) {
                    i3 = i2 + 1;
                    if (Character.isLetterOrDigit((char) i3)) {
                        break;
                    } else {
                        i2 = i3;
                    }
                }
            }
        }
        sb.append(alphaValue(i, cCharAt, i2));
    }

    private int parseStringToAnInt(String str) {
        int length;
        int i;
        int i2;
        int i3;
        int i4;
        if (str == null || (length = str.length()) <= 0) {
            return 0;
        }
        if (str.charAt(0) == '-') {
            i = Integer.MIN_VALUE;
            i2 = 1;
        } else {
            i = -2147483647;
            i2 = 0;
        }
        int i5 = i2;
        int i6 = i / 10;
        if (i2 < length) {
            int i7 = i2 + 1;
            int iDigit = Character.digit(str.charAt(i2), 10);
            if (iDigit < 0) {
                return 0;
            }
            i3 = -iDigit;
            i2 = i7;
        } else {
            i3 = 0;
        }
        while (i2 < length) {
            int i8 = i2 + 1;
            int iDigit2 = Character.digit(str.charAt(i2), 10);
            if (iDigit2 < 0 || i3 < i6 || (i4 = i3 * 10) < i + iDigit2) {
                return 0;
            }
            i3 = i4 - iDigit2;
            i2 = i8;
        }
        if (i5 == 0) {
            return -i3;
        }
        if (i2 > 1) {
            return i3;
        }
        return 0;
    }

    private String romanValue(int i) {
        if (i <= 0 || i > 4000) {
            return "" + i;
        }
        return Thousands[i / 1000] + Hundreds[(i / 100) % 10] + Tens[(i / 10) % 10] + Ones[i % 10];
    }

    private final void setTokens(String str) {
        String str2 = this._format;
        if (str2 == null || !str.equals(str2)) {
            this._format = str;
            int length = str.length();
            this._separFirst = true;
            this._separLast = false;
            this._nSepars = 0;
            this._nFormats = 0;
            this._separToks.clear();
            this._formatToks.clear();
            boolean z = true;
            int i = 0;
            while (i < length) {
                char cCharAt = str.charAt(i);
                int i2 = i;
                while (Character.isLetterOrDigit(cCharAt) && (i2 = i2 + 1) != length) {
                    cCharAt = str.charAt(i2);
                }
                if (i2 > i) {
                    if (z) {
                        this._separToks.add(Constants.ATTRVAL_THIS);
                        this._separFirst = false;
                        z = false;
                    }
                    this._formatToks.add(str.substring(i, i2));
                }
                if (i2 == length) {
                    break;
                }
                char cCharAt2 = str.charAt(i2);
                int i3 = i2;
                while (!Character.isLetterOrDigit(cCharAt2) && (i3 = i3 + 1) != length) {
                    cCharAt2 = str.charAt(i3);
                    z = false;
                }
                i = i3;
                if (i > i2) {
                    this._separToks.add(str.substring(i2, i));
                }
            }
            this._nSepars = this._separToks.size();
            int size = this._formatToks.size();
            this._nFormats = size;
            int i4 = this._nSepars;
            if (i4 > size) {
                this._separLast = true;
            }
            if (this._separFirst) {
                this._nSepars = i4 - 1;
            }
            if (this._separLast) {
                this._nSepars--;
            }
            if (this._nSepars == 0) {
                this._separToks.add(1, Constants.ATTRVAL_THIS);
                this._nSepars++;
            }
            if (this._separFirst) {
                this._nSepars++;
            }
        }
    }

    public String formatNumbers(int[] iArr) {
        boolean z = true;
        for (int i : iArr) {
            if (i != Integer.MIN_VALUE) {
                z = false;
            }
        }
        if (z) {
            return "";
        }
        this._tempBuffer.setLength(0);
        StringBuilder sb = this._tempBuffer;
        if (this._separFirst) {
            sb.append(this._separToks.get(0));
        }
        boolean z2 = true;
        int i2 = 1;
        int i3 = 0;
        for (int i4 : iArr) {
            if (i4 != Integer.MIN_VALUE) {
                if (!z2) {
                    sb.append(this._separToks.get(i2));
                    i2++;
                }
                int i5 = i3 + 1;
                formatValue(i4, this._formatToks.get(i3), sb);
                if (i5 != this._nFormats) {
                    i3 = i5;
                }
                if (i2 >= this._nSepars) {
                    i2--;
                }
                z2 = false;
            }
        }
        if (this._separLast) {
            List<String> list = this._separToks;
            sb.append(list.get(list.size() - 1));
        }
        return sb.toString();
    }

    public abstract String getCounter();

    public String getCounter(String str, String str2, String str3, String str4, String str5) {
        setFormatting(str, str2, str3, str4, str5);
        return getCounter();
    }

    public boolean matchesCount(int i) {
        return this._nodeType == this._document.getExpandedTypeID(i);
    }

    public boolean matchesFrom(int i) {
        return false;
    }

    public NodeCounter setDefaultFormatting() {
        setFormatting("1", "en", Constants.ATTRVAL_ALPHABETIC, null, null);
        return this;
    }

    public void setFormatting(String str, String str2, String str3, String str4, String str5) {
        this._lang = str2;
        this._groupSep = str4;
        this._letterValue = str3;
        this._groupSize = parseStringToAnInt(str5);
        setTokens(str);
    }

    public abstract NodeCounter setStartNode(int i);

    public NodeCounter setValue(double d) {
        this._value = d;
        return this;
    }

    public NodeCounter(Translet translet, DOM dom, DTMAxisIterator dTMAxisIterator) {
        this._translet = translet;
        this._document = dom;
        this._iterator = dTMAxisIterator;
    }

    public String formatNumbers(int i) {
        return formatNumbers(new int[]{i});
    }
}
