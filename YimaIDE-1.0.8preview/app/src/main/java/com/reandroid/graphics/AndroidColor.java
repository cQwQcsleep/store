package com.reandroid.graphics;

import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.sun.org.apache.xpath.internal.XPath;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AndroidColor {
    public static final double LENGTH = ObjectsUtil.of(441.67d);
    private int alpha;
    private int blue;
    private int green;
    private int red;
    private Type type;
    private boolean upperCase;

    public static class ColorIterator implements Iterator<AndroidColor> {
        private AndroidColor current;
        private int index;
        private String lastColor;
        private int lastIndex = -1;
        private String text;

        public ColorIterator(String str) {
            this.text = str;
        }

        private AndroidColor getNext() {
            while (this.current == null && nextIndex() != -1) {
                AndroidColor androidColorDecode = AndroidColor.decode(this.text.substring(this.index));
                this.current = androidColorDecode;
                if (androidColorDecode != null) {
                    this.lastIndex = this.index;
                }
                this.index++;
            }
            return this.current;
        }

        private int nextIndex() {
            String str = this.text;
            if (str == null) {
                return -1;
            }
            int length = str.length();
            while (true) {
                int i = this.index;
                if (i >= length) {
                    return -1;
                }
                char cCharAt = this.text.charAt(i);
                int i2 = this.index;
                if (cCharAt == '#') {
                    return i2;
                }
                this.index = i2 + 1;
            }
        }

        public String getText() {
            return this.text;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return getNext() != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public AndroidColor next() {
            AndroidColor next = getNext();
            if (next == null) {
                z0e.a();
                return null;
            }
            this.lastColor = next.toHexString();
            this.current = null;
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            String str = this.lastColor;
            if (str == null) {
                return;
            }
            String text = getText();
            this.lastColor = null;
            this.text = text.substring(0, this.lastIndex).concat(text.substring(this.lastIndex + str.length()));
        }

        public String replace(AndroidColor androidColor) {
            String text = getText();
            if (androidColor == null || this.lastColor == null) {
                return text;
            }
            String strSubstring = text.substring(0, this.lastIndex);
            String strSubstring2 = text.substring(this.lastIndex + this.lastColor.length());
            this.lastColor = androidColor.toHexString();
            String str = strSubstring + this.lastColor + strSubstring2;
            this.text = str;
            return str;
        }
    }

    public static class Type {
        private final boolean eightBit;
        private final boolean hasAlpha;
        private final String name;
        public static final Type RGB4 = new Type("RGB4", false, false);
        public static final Type ARGB4 = new Type("ARGB4", true, false);
        public static final Type RGB8 = new Type("RGB8", false, true);
        public static final Type ARGB8 = new Type("ARGB8", true, true);

        private Type(String str, boolean z, boolean z2) {
            this.name = str;
            this.hasAlpha = z;
            this.eightBit = z2;
        }

        public static Type getFor(int i, int i2, int i3, int i4) {
            Type type = RGB4;
            if (type.fits(i, i2, i3, i4)) {
                return type;
            }
            Type type2 = ARGB4;
            if (type2.fits(i, i2, i3, i4)) {
                return type2;
            }
            Type type3 = RGB8;
            return type3.fits(i, i2, i3, i4) ? type3 : ARGB8;
        }

        public static Type valueOf(int i) {
            if (i == 4) {
                return RGB4;
            }
            if (i == 5) {
                return ARGB4;
            }
            if (i == 7) {
                return RGB8;
            }
            if (i == 9) {
                return ARGB8;
            }
            return null;
        }

        public boolean equals(Object obj) {
            return obj == this;
        }

        public int fit(int i) {
            int iMask = mask();
            if (i > iMask) {
                return iMask;
            }
            if (i < 0) {
                return 0;
            }
            return i;
        }

        public boolean fits(int i, int i2, int i3, int i4) {
            int i5;
            if (i != 0 && !this.hasAlpha) {
                return false;
            }
            int iMask = mask();
            return (i & iMask) == i && (i5 = iMask & i2) == i2 && i5 == i3 && i5 == i4;
        }

        public boolean hasAlpha() {
            return this.hasAlpha;
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public boolean isEightBit() {
            return this.eightBit;
        }

        public int mask() {
            return this.eightBit ? 255 : 15;
        }

        public Type toEightBit() {
            if (isEightBit()) {
                return this;
            }
            return this == RGB4 ? RGB8 : ARGB8;
        }

        public Type toFourBit() {
            if (isEightBit()) {
                return this == RGB8 ? RGB4 : ARGB4;
            }
            return this;
        }

        public String toString() {
            return this.name;
        }
    }

    public AndroidColor(int i, int i2, int i3, int i4) {
        this.alpha = i;
        this.red = i2;
        this.green = i3;
        this.blue = i4;
    }

    public static AndroidColor decode(String str) {
        int length;
        if (str == null || (length = str.length()) < 4 || str.charAt(0) != '#') {
            return null;
        }
        StringBuilder sb = new StringBuilder(9);
        sb.append('#');
        for (int i = 1; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (!HexUtil.isHexChar(cCharAt)) {
                break;
            }
            if (i > 8) {
                return null;
            }
            sb.append(cCharAt);
        }
        return parseHex(sb.toString(), false);
    }

    public static ColorIterator decodeAll(String str) {
        return new ColorIterator(str);
    }

    private static int deltaSquare(int i, int i2) {
        int i3 = i - i2;
        return i3 * i3;
    }

    private static AndroidColor parseHex(String str, boolean z) {
        if (str == null || str.length() == 0 || str.charAt(0) != '#') {
            if (!z) {
                return null;
            }
            throw new NumberFormatException("Invalid hex color: " + str);
        }
        Type typeValueOf = Type.valueOf(str.length());
        if (typeValueOf == null) {
            if (z) {
                throw new NumberFormatException("Invalid hex color string length: ".concat(str));
            }
            return null;
        }
        AndroidColor androidColor = new AndroidColor();
        androidColor.type = typeValueOf;
        String strSubstring = str.substring(1);
        androidColor.setUpperCase(StringsUtil.containsUpperAZ(strSubstring));
        int i = typeValueOf.isEightBit() ? 2 : 1;
        if (typeValueOf.hasAlpha()) {
            int iDecodeHex = HexUtil.decodeHex(strSubstring.substring(0, i), -1);
            if (iDecodeHex == -1) {
                if (z) {
                    throw new NumberFormatException("Invalid hex: ".concat(str));
                }
                return null;
            }
            androidColor.alpha = iDecodeHex;
            strSubstring = strSubstring.substring(i);
        }
        int iDecodeHex2 = HexUtil.decodeHex(strSubstring.substring(0, i), -1);
        if (iDecodeHex2 == -1) {
            if (z) {
                throw new NumberFormatException("Invalid hex: ".concat(str));
            }
            return null;
        }
        androidColor.red = iDecodeHex2;
        String strSubstring2 = strSubstring.substring(i);
        int iDecodeHex3 = HexUtil.decodeHex(strSubstring2.substring(0, i), -1);
        if (iDecodeHex3 == -1) {
            if (z) {
                throw new NumberFormatException("Invalid hex: ".concat(str));
            }
            return null;
        }
        androidColor.green = iDecodeHex3;
        String strSubstring3 = strSubstring2.substring(i);
        int iDecodeHex4 = HexUtil.decodeHex(strSubstring3.substring(0, i), -1);
        if (iDecodeHex4 != -1) {
            androidColor.blue = iDecodeHex4;
            return androidColor;
        }
        if (z) {
            throw new NumberFormatException("Invalid hex: ".concat(strSubstring3));
        }
        return null;
    }

    public static double toDistance(float f) {
        return (((double) f) * LENGTH) / 100.0d;
    }

    public static float toPercent(double d) {
        return (float) ((d * 100.0d) / LENGTH);
    }

    public AndroidColor addRgb(int i) {
        if (i != 0) {
            AndroidColor androidColorCopy = copy();
            int i2 = androidColorCopy.isEightBit() ? 255 : 15;
            int i3 = androidColorCopy.red + i;
            if (i3 >= 0 && i3 <= i2) {
                androidColorCopy.red = i3;
                int i4 = androidColorCopy.green + i;
                if (i4 >= 0 && i4 <= i2) {
                    androidColorCopy.green = i4;
                    int i5 = androidColorCopy.blue + i;
                    if (i5 >= 0 && i5 <= i2) {
                        androidColorCopy.blue = i5;
                        return androidColorCopy;
                    }
                }
            }
        }
        return this;
    }

    public void alpha(int i) {
        if (this.alpha != i || i == 0) {
            this.alpha = getType().fit(i);
        }
    }

    public AndroidColor argb() {
        AndroidColor androidColor = new AndroidColor(0, this.red, this.green, this.blue);
        androidColor.setType(getType().isEightBit() ? Type.ARGB8 : Type.ARGB4);
        androidColor.setUpperCase(this.upperCase);
        androidColor.alpha(alpha());
        return androidColor;
    }

    public void blue(int i) {
        this.blue = getType().fit(i);
    }

    public int compareTo(AndroidColor androidColor, AndroidColor androidColor2) {
        return Double.compare(androidColor.distance(this), androidColor.distance(androidColor2));
    }

    public AndroidColor copy() {
        AndroidColor androidColor = new AndroidColor(this.alpha, this.red, this.green, this.blue);
        androidColor.setType(getType());
        androidColor.setUpperCase(this.upperCase);
        return androidColor;
    }

    public double distance(AndroidColor androidColor) {
        if (androidColor == this) {
            return XPath.MATCH_SCORE_QNAME;
        }
        if (isEightBit() != androidColor.isEightBit()) {
            if (isEightBit()) {
                androidColor = androidColor.toEightBit();
            } else {
                this = toEightBit();
            }
        }
        return Math.round(Math.sqrt((((double) deltaSquare(androidColor.red(), this.red())) + ((double) deltaSquare(androidColor.green(), this.green()))) + ((double) deltaSquare(androidColor.blue(), this.blue()))) * 100.0d) / 100.0d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AndroidColor)) {
            return false;
        }
        AndroidColor eightBit = (AndroidColor) obj;
        if (isEightBit() != eightBit.isEightBit()) {
            if (isEightBit()) {
                eightBit = eightBit.toEightBit();
            } else {
                this = toEightBit();
            }
        }
        return this.alpha == eightBit.alpha && this.red == eightBit.red && this.green == eightBit.green && this.blue == eightBit.blue;
    }

    public boolean equalsRgb(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AndroidColor)) {
            return false;
        }
        AndroidColor eightBit = (AndroidColor) obj;
        if (isEightBit() != eightBit.isEightBit()) {
            if (isEightBit()) {
                eightBit = eightBit.toEightBit();
            } else {
                this = toEightBit();
            }
        }
        return this.red == eightBit.red && this.green == eightBit.green && this.blue == eightBit.blue;
    }

    public Type getType() {
        Type type = this.type;
        return type == null ? Type.getFor(alpha(), red(), green(), blue()) : type;
    }

    public void green(int i) {
        this.green = getType().fit(i);
    }

    public boolean hasAlpha() {
        return getType().hasAlpha();
    }

    public int hashCode() {
        return intValue();
    }

    public int intValue() {
        return this.blue | (this.alpha << 24) | (this.red << 16) | (this.green << 8);
    }

    public AndroidColor inverse() {
        AndroidColor androidColorCopy = copy();
        int iMask = getType().mask();
        androidColorCopy.red((~androidColorCopy.red()) & iMask);
        androidColorCopy.green((~androidColorCopy.green()) & iMask);
        androidColorCopy.blue(iMask & (~androidColorCopy.blue()));
        return androidColorCopy;
    }

    public boolean isEightBit() {
        return getType().isEightBit();
    }

    public void red(int i) {
        this.red = getType().fit(i);
    }

    public AndroidColor rgb() {
        AndroidColor androidColor = new AndroidColor(0, this.red, this.green, this.blue);
        androidColor.setType(getType().isEightBit() ? Type.RGB8 : Type.RGB4);
        androidColor.setUpperCase(this.upperCase);
        return androidColor;
    }

    public void setEightBit(boolean z) {
        Type fourBit;
        Type type = getType();
        if (z == type.isEightBit()) {
            return;
        }
        int i = 15;
        int i2 = 255;
        if (z) {
            fourBit = type.toEightBit();
            i2 = 15;
            i = 255;
        } else {
            fourBit = type.toFourBit();
        }
        this.alpha = (this.alpha * i) / i2;
        this.red = (this.red * i) / i2;
        this.green = (this.green * i) / i2;
        this.blue = (this.blue * i) / i2;
        this.type = fourBit;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0015 A[PHI: r1
      0x0015: PHI (r1v3 com.reandroid.graphics.AndroidColor$Type) = (r1v1 com.reandroid.graphics.AndroidColor$Type), (r1v2 com.reandroid.graphics.AndroidColor$Type) binds: [B:8:0x0013, B:14:0x0020] A[DONT_GENERATE, DONT_INLINE]] */
    public void setHasAlpha(boolean z) {
        Type type = getType();
        if (z != type.hasAlpha()) {
            Type type2 = Type.ARGB4;
            if (type == type2) {
                type = Type.RGB4;
            } else if (type != Type.RGB4) {
                type2 = Type.ARGB8;
                if (type == type2) {
                    type = Type.RGB8;
                } else if (type == Type.RGB8) {
                    type = type2;
                }
            } else {
                type = type2;
            }
            int iFit = z ? type.fit(alpha()) : 0;
            setType(type);
            alpha(iFit);
        }
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setUpperCase(boolean z) {
        this.upperCase = z;
    }

    public AndroidColor toEightBit() {
        if (isEightBit()) {
            return this;
        }
        AndroidColor androidColorCopy = copy();
        androidColorCopy.setEightBit(true);
        return androidColorCopy;
    }

    public AndroidColor toFourBit() {
        if (!isEightBit()) {
            return this;
        }
        AndroidColor androidColorCopy = copy();
        androidColorCopy.setEightBit(false);
        return androidColorCopy;
    }

    public String toHexString() {
        Type type = getType();
        StringBuilder sb = new StringBuilder("#");
        int i = type.isEightBit() ? 2 : 1;
        if (type.hasAlpha()) {
            sb.append(HexUtil.toHex((String) null, this.alpha, i));
        }
        sb.append(HexUtil.toHex((String) null, this.red, i));
        sb.append(HexUtil.toHex((String) null, this.green, i));
        sb.append(HexUtil.toHex((String) null, this.blue, i));
        String string = sb.toString();
        return this.upperCase ? StringsUtil.toUpperCase(string) : string;
    }

    public String toString() {
        return toHexString();
    }

    public int blue() {
        return this.blue;
    }

    public int green() {
        return this.green;
    }

    public int red() {
        return this.red;
    }

    public AndroidColor() {
        this(0, 0, 0, 0);
    }

    public int alpha() {
        return this.alpha;
    }

    public boolean equals(AndroidColor androidColor, double d) {
        if (androidColor == null) {
            return false;
        }
        return androidColor == this || distance(androidColor) <= d;
    }

    public boolean equals(AndroidColor androidColor, float f) {
        if (androidColor == null) {
            return false;
        }
        return androidColor == this || distance(androidColor) <= toDistance(f);
    }

    public static AndroidColor parseHex(String str) {
        return parseHex(str, true);
    }
}
