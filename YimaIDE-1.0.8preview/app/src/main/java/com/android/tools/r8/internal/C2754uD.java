package com.android.tools.r8.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2754uD implements Closeable, Flushable {
    public static final Pattern i = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    public static final String[] j = new String[128];
    public static final String[] k;
    public final Writer a;
    public int[] b = new int[32];
    public int c = 0;
    public final String d;
    public boolean e;
    public boolean f;
    public String g;
    public boolean h;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            j[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = j;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        k = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public C2754uD(Writer writer) {
        a(6);
        this.d = ":";
        this.h = true;
        Objects.requireNonNull(writer, "out == null");
        this.a = writer;
    }

    public void a(Number number) throws IOException {
        if (number == null) {
            i();
            return;
        }
        j();
        String string = number.toString();
        if (!string.equals("-Infinity") && !string.equals("Infinity") && !string.equals("NaN")) {
            Class<?> cls = number.getClass();
            if (cls != Integer.class && cls != Long.class && cls != Double.class && cls != Float.class && cls != Byte.class && cls != Short.class && cls != BigDecimal.class && cls != BigInteger.class && cls != AtomicInteger.class && cls != AtomicLong.class && !i.matcher(string).matches()) {
                h0f.a("String created by ", cls, " is not a valid JSON number: ", string);
                return;
            }
        } else if (!this.e) {
            w01.a("Numeric values must be finite, but was ".concat(string));
            return;
        }
        c();
        this.a.append((CharSequence) string);
    }

    public void b(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.g != null) {
            g33.a();
        } else if (this.c != 0) {
            this.g = str;
        } else {
            k2d.a("JsonWriter is closed.");
        }
    }

    public final void c() throws IOException {
        int i2 = this.c;
        if (i2 == 0) {
            k2d.a("JsonWriter is closed.");
            return;
        }
        int[] iArr = this.b;
        int i3 = i2 - 1;
        int i4 = iArr[i3];
        if (i4 == 1) {
            iArr[i3] = 2;
            h();
            return;
        }
        if (i4 == 2) {
            this.a.append(',');
            h();
            return;
        }
        if (i4 == 4) {
            this.a.append((CharSequence) this.d);
            this.b[this.c - 1] = 5;
            return;
        }
        if (i4 != 6) {
            if (i4 != 7) {
                k2d.a("Nesting problem.");
                return;
            } else if (!this.e) {
                k2d.a("JSON must have only one top-level value.");
                return;
            }
        }
        iArr[i3] = 7;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
        int i2 = this.c;
        if (i2 > 1 || (i2 == 1 && this.b[i2 - 1] != 7)) {
            a16.a("Incomplete document");
        } else {
            this.c = 0;
        }
    }

    public void d() throws IOException {
        j();
        c();
        a(1);
        this.a.write(91);
    }

    public void e() throws IOException {
        j();
        c();
        a(3);
        this.a.write(123);
    }

    public void f() throws IOException {
        a(1, 2, ']');
    }

    public void flush() throws IOException {
        if (this.c != 0) {
            this.a.flush();
        } else {
            k2d.a("JsonWriter is closed.");
        }
    }

    public void g() throws IOException {
        a(3, 5, '}');
    }

    public final void h() {
    }

    public C2754uD i() throws IOException {
        if (this.g != null) {
            if (!this.h) {
                this.g = null;
                return this;
            }
            j();
        }
        c();
        this.a.write("null");
        return this;
    }

    public final void j() throws IOException {
        if (this.g != null) {
            int i2 = this.c;
            if (i2 == 0) {
                k2d.a("JsonWriter is closed.");
                return;
            }
            int i3 = this.b[i2 - 1];
            if (i3 == 5) {
                this.a.write(44);
            } else if (i3 != 3) {
                k2d.a("Nesting problem.");
                return;
            }
            h();
            this.b[this.c - 1] = 4;
            c(this.g);
            this.g = null;
        }
    }

    public void d(String str) throws IOException {
        if (str == null) {
            i();
            return;
        }
        j();
        c();
        c(str);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0034  */
    public final void c(String str) throws IOException {
        String str2;
        String[] strArr = this.f ? k : j;
        this.a.write(34);
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt < 128) {
                str2 = strArr[cCharAt];
                if (str2 != null) {
                    if (i2 < i3) {
                        this.a.write(str, i2, i3 - i2);
                    }
                    this.a.write(str2);
                    i2 = i3 + 1;
                }
            } else {
                if (cCharAt == 8232) {
                    str2 = "\\u2028";
                } else if (cCharAt == 8233) {
                    str2 = "\\u2029";
                }
                if (i2 < i3) {
                    this.a.write(str, i2, i3 - i2);
                }
                this.a.write(str2);
                i2 = i3 + 1;
            }
        }
        if (i2 < length) {
            this.a.write(str, i2, length - i2);
        }
        this.a.write(34);
    }

    public final C2754uD a(int i2, int i3, char c) throws IOException {
        int i4 = this.c;
        if (i4 != 0) {
            int i5 = i4 - 1;
            int i6 = this.b[i5];
            if (i6 != i3 && i6 != i2) {
                k2d.a("Nesting problem.");
                return null;
            }
            if (this.g == null) {
                this.c = i5;
                if (i6 == i3) {
                    h();
                }
                this.a.write(c);
                return this;
            }
            sle.a("Dangling name: ", this.g);
            return null;
        }
        k2d.a("JsonWriter is closed.");
        return null;
    }

    public void a(boolean z) throws IOException {
        j();
        c();
        this.a.write(z ? "true" : "false");
    }

    public void a(Boolean bool) throws IOException {
        if (bool == null) {
            i();
            return;
        }
        j();
        c();
        this.a.write(bool.booleanValue() ? "true" : "false");
    }

    public void a(double d) throws IOException {
        j();
        if (!this.e && (Double.isNaN(d) || Double.isInfinite(d))) {
            c72.a("Numeric values must be finite, but was ", d);
        } else {
            c();
            this.a.append((CharSequence) Double.toString(d));
        }
    }

    public void a(long j2) throws IOException {
        j();
        c();
        this.a.write(Long.toString(j2));
    }

    public final void a(int i2) {
        int i3 = this.c;
        int[] iArr = this.b;
        if (i3 == iArr.length) {
            this.b = Arrays.copyOf(iArr, i3 * 2);
        }
        int[] iArr2 = this.b;
        int i4 = this.c;
        this.c = i4 + 1;
        iArr2[i4] = i2;
    }
}
