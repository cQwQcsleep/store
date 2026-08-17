package com.android.tools.r8.position;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TextPosition implements Position {
    public static final int UNKNOWN_COLUMN = -1;
    static final /* synthetic */ boolean d = true;
    private final long a;
    private final int b;
    private final int c;

    public TextPosition(long j, int i, int i2) {
        if (!d && (j < 0 || i < 0 || (i2 < 1 && i2 != -1))) {
            x1f.a();
            throw null;
        }
        this.a = j;
        this.b = i;
        this.c = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass().equals(TextPosition.class)) {
            TextPosition textPosition = (TextPosition) obj;
            if (this.a == textPosition.a && this.b == textPosition.b && this.c == textPosition.c) {
                return true;
            }
        }
        return false;
    }

    public int getColumn() {
        return this.c;
    }

    @Override // com.android.tools.r8.position.Position
    public String getDescription() {
        String str;
        int i = this.b;
        int i2 = this.c;
        if (i2 != -1) {
            str = ", column " + i2;
        } else {
            str = XmlPullParser.NO_NAMESPACE;
        }
        return "line " + i + str;
    }

    public int getLine() {
        return this.b;
    }

    public long getOffset() {
        return this.a;
    }

    public int getOffsetAsInt() {
        long j = this.a;
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new RuntimeException("Expected offset to be an int, but was " + j);
    }

    public int hashCode() {
        return (this.c << 16) ^ (Long.hashCode(this.a) ^ this.b);
    }

    public String toString() {
        return "offset: " + this.a + ", line: " + this.b + ", column: " + this.c;
    }
}
