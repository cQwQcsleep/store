package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2327pD implements Closeable {
    public final Reader a;
    public long i;
    public int j;
    public String k;
    public int[] l;
    public String[] n;
    public int[] o;
    public boolean b = false;
    public final char[] c = new char[Fcntl.S_ISGID];
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int m = 1;

    public C2327pD(StringReader stringReader) {
        int[] iArr = new int[32];
        this.l = iArr;
        iArr[0] = 6;
        this.n = new String[32];
        this.o = new int[32];
        this.a = stringReader;
    }

    public final String a(char c) throws BM {
        char[] cArr = this.c;
        StringBuilder sb = null;
        do {
            int i = this.d;
            int i2 = this.e;
            int i3 = i;
            while (i < i2) {
                int i4 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.d = i4;
                    int i5 = (i4 - i3) - 1;
                    if (sb == null) {
                        return new String(cArr, i3, i5);
                    }
                    sb.append(cArr, i3, i5);
                    return sb.toString();
                }
                if (c2 == '\\') {
                    this.d = i4;
                    int i6 = i4 - i3;
                    int i7 = i6 - 1;
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i6 * 2, 16));
                    }
                    sb.append(cArr, i3, i7);
                    sb.append(q());
                    i3 = this.d;
                    i2 = this.e;
                    i = i3;
                } else {
                    if (c2 == '\n') {
                        this.f++;
                        this.g = i4;
                    }
                    i = i4;
                }
            }
            if (sb == null) {
                sb = new StringBuilder(Math.max((i - i3) * 2, 16));
            }
            sb.append(cArr, i3, i - i3);
            this.d = i;
        } while (a(1));
        b("Unterminated string");
        throw null;
    }

    public final int b(boolean z) throws IOException {
        char[] cArr = this.c;
        int i = this.d;
        int i2 = this.e;
        while (true) {
            if (i == i2) {
                this.d = i;
                if (!a(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + j());
                }
                i = this.d;
                i2 = this.e;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.f++;
                this.g = i3;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.d = i3;
                    if (i3 == i2) {
                        this.d = i;
                        boolean zA = a(2);
                        this.d++;
                        if (!zA) {
                        }
                        return c;
                    }
                    e();
                    int i4 = this.d;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.d = i4 + 1;
                        while (true) {
                            if (this.d + 2 > this.e && !a(2)) {
                                b("Unterminated comment");
                                throw null;
                            }
                            char[] cArr2 = this.c;
                            int i5 = this.d;
                            if (cArr2[i5] != '\n') {
                                int i6 = 0;
                                while (true) {
                                    if (i6 >= 2) {
                                        i = this.d + 2;
                                        i2 = this.e;
                                        break;
                                    }
                                    if (this.c[this.d + i6] != "*/".charAt(i6)) {
                                        break;
                                    }
                                    i6++;
                                }
                            } else {
                                this.f++;
                                this.g = i5 + 1;
                            }
                            this.d++;
                        }
                    } else {
                        if (c2 != '/') {
                            return c;
                        }
                        this.d = i4 + 1;
                        r();
                        i = this.d;
                        i2 = this.e;
                    }
                } else {
                    if (c != '#') {
                        this.d = i3;
                        return c;
                    }
                    this.d = i3;
                    e();
                    r();
                    i = this.d;
                    i2 = this.e;
                }
            }
            i = i3;
        }
    }

    public final void c() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 3) {
            zia.a("Expected BEGIN_ARRAY but was ", AbstractC2497rD.a(p()), j());
            return;
        }
        b(1);
        this.o[this.m - 1] = 0;
        this.h = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.h = 0;
        this.l[0] = 8;
        this.m = 1;
        this.a.close();
    }

    public final void d() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 1) {
            zia.a("Expected BEGIN_OBJECT but was ", AbstractC2497rD.a(p()), j());
        } else {
            b(3);
            this.h = 0;
        }
    }

    public final void e() throws BM {
        if (this.b) {
            return;
        }
        b("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:130:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:133:0x01b0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:134:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:137:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:140:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:143:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:144:0x01dc A[PHI: r8 r13
      0x01dc: PHI (r8v4 int) = (r8v3 int), (r8v8 int) binds: [B:136:0x01c4, B:143:0x01d7] A[DONT_GENERATE, DONT_INLINE]
      0x01dc: PHI (r13v9 int) = (r13v8 int), (r13v10 int) binds: [B:136:0x01c4, B:143:0x01d7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:146:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:148:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:212:0x028b  */
    /* JADX WARN: Code duplicated, block: B:213:0x028e A[DONT_INVERT, PHI: r20
      0x028e: PHI (r20v10 boolean) = 
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v6 boolean)
      (r20v11 boolean)
     binds: [B:187:0x0257, B:189:0x025b, B:191:0x025f, B:193:0x0263, B:195:0x0267, B:212:0x028b, B:198:0x026d, B:203:0x0277, B:205:0x027b, B:207:0x0280, B:210:0x0286, B:142:0x01d3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:214:0x0290 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:225:0x02b0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:226:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:242:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:245:0x02de  */
    /* JADX WARN: Code duplicated, block: B:251:0x02f0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:252:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:254:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:264:0x030f  */
    /* JADX WARN: Code duplicated, block: B:265:0x0311  */
    /* JADX WARN: Code duplicated, block: B:267:0x0315 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:279:0x0332  */
    /* JADX WARN: Code duplicated, block: B:350:0x03ea  */
    /* JADX WARN: Code duplicated, block: B:363:0x02ed A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:364:0x01d3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:371:0x02ed A[SYNTHETIC] */
    public final int f() throws IOException {
        int iB;
        int i;
        String str;
        String str2;
        int i2;
        char c;
        int i3;
        char c2;
        char[] cArr;
        int i4;
        int i5;
        int i6;
        boolean z;
        char c3;
        char c4;
        boolean z2;
        boolean z3;
        int i7;
        char c5;
        int[] iArr = this.l;
        boolean z4 = true;
        int i8 = this.m - 1;
        int i9 = iArr[i8];
        if (i9 == 1) {
            iArr[i8] = 2;
        } else if (i9 == 2) {
            int iB2 = b(true);
            if (iB2 != 44) {
                if (iB2 != 59) {
                    if (iB2 == 93) {
                        this.h = 4;
                        return 4;
                    }
                    b("Unterminated array");
                    throw null;
                }
                e();
            }
        } else {
            if (i9 == 3 || i9 == 5) {
                iArr[i8] = 4;
                if (i9 == 5 && (iB = b(true)) != 44) {
                    if (iB != 59) {
                        if (iB == 125) {
                            this.h = 2;
                            return 2;
                        }
                        b("Unterminated object");
                        throw null;
                    }
                    e();
                }
                int iB3 = b(true);
                if (iB3 == 34) {
                    this.h = 13;
                    return 13;
                }
                if (iB3 == 39) {
                    e();
                    this.h = 12;
                    return 12;
                }
                if (iB3 == 125) {
                    if (i9 != 5) {
                        this.h = 2;
                        return 2;
                    }
                    b("Expected name");
                    throw null;
                }
                e();
                this.d--;
                char c6 = (char) iB3;
                if (c6 != '\t' && c6 != '\n' && c6 != '\f' && c6 != '\r' && c6 != ' ') {
                    if (c6 == '#') {
                        e();
                    } else if (c6 != ',') {
                        if (c6 == '/' || c6 == '=') {
                            e();
                        } else if (c6 != '{' && c6 != '}' && c6 != ':') {
                            if (c6 != ';') {
                                switch (c6) {
                                    case '[':
                                    case ']':
                                        break;
                                    case '\\':
                                        e();
                                        break;
                                    default:
                                        this.h = 14;
                                        return 14;
                                }
                            } else {
                                e();
                            }
                        }
                    }
                }
                b("Expected name");
                throw null;
            }
            if (i9 == 4) {
                iArr[i8] = 5;
                int iB4 = b(true);
                if (iB4 != 58) {
                    if (iB4 != 61) {
                        b("Expected ':'");
                        throw null;
                    }
                    e();
                    if (this.d < this.e || a(1)) {
                        char[] cArr2 = this.c;
                        int i10 = this.d;
                        if (cArr2[i10] == '>') {
                            this.d = i10 + 1;
                        }
                    }
                }
            } else if (i9 == 6) {
                if (this.b) {
                    b(true);
                    int i11 = this.d;
                    this.d = i11 - 1;
                    if (i11 + 4 <= this.e || a(5)) {
                        int i12 = this.d;
                        char[] cArr3 = this.c;
                        if (cArr3[i12] == ')' && cArr3[i12 + 1] == ']' && cArr3[i12 + 2] == '}' && cArr3[i12 + 3] == '\'' && cArr3[i12 + 4] == '\n') {
                            this.d = i12 + 5;
                        }
                    }
                }
                this.l[this.m - 1] = 7;
            } else if (i9 == 7) {
                if (b(false) == -1) {
                    this.h = 17;
                    return 17;
                }
                e();
                this.d--;
            } else if (i9 == 8) {
                k2d.a("JsonReader is closed");
                return 0;
            }
        }
        int iB5 = b(true);
        if (iB5 == 34) {
            this.h = 9;
            return 9;
        }
        if (iB5 == 39) {
            e();
            this.h = 8;
            return 8;
        }
        if (iB5 == 44 || iB5 == 59) {
            i = 1;
        } else {
            if (iB5 == 91) {
                this.h = 3;
                return 3;
            }
            if (iB5 != 93) {
                if (iB5 == 123) {
                    this.h = 1;
                    return 1;
                }
                int i13 = this.d - 1;
                this.d = i13;
                char c7 = this.c[i13];
                if (c7 == 't' || c7 == 'T') {
                    str = "true";
                    str2 = "TRUE";
                    i2 = 5;
                } else {
                    if (c7 != 'f' && c7 != 'F') {
                        if (c7 == 'n' || c7 == 'N') {
                            str = "null";
                            str2 = "NULL";
                            i2 = 7;
                        } else {
                            i3 = 0;
                        }
                        if (i3 != 0) {
                            return i3;
                        }
                        cArr = this.c;
                        i4 = this.d;
                        boolean z5 = true;
                        i5 = this.e;
                        i6 = 0;
                        z = false;
                        long j = 0;
                        c3 = 0;
                        while (true) {
                            if (i4 + i6 != i5) {
                                c4 = cArr[i4 + i6];
                                if (c4 == '+') {
                                    if (c3 != 5) {
                                    }
                                    z = z;
                                    c3 = 6;
                                    i6++;
                                    z4 = true;
                                } else {
                                    if (c4 == 'E' && c4 != 'e') {
                                        if (c4 == '-') {
                                            if (c3 == 0) {
                                                z = true;
                                                c3 = 1;
                                            } else {
                                                if (c3 != 5) {
                                                }
                                                z = z;
                                                c3 = 6;
                                            }
                                            i6++;
                                            z4 = true;
                                        } else if (c4 == '.') {
                                            z2 = z;
                                            if (c3 == 2) {
                                                c3 = 3;
                                                z = z2;
                                                i6++;
                                                z4 = true;
                                            }
                                        } else if (c4 < '0' || c4 > '9') {
                                            z3 = z;
                                            if (c4 == '\t' || c4 == '\n' || c4 == '\f' || c4 == '\r' || c4 == ' ') {
                                                if (c3 != 2 && z5 && ((j != Long.MIN_VALUE || z3) && !(j == 0 && z3))) {
                                                    if (!z3) {
                                                        j = -j;
                                                    }
                                                    this.i = j;
                                                    this.d += i6;
                                                    i7 = 15;
                                                    this.h = 15;
                                                } else if (c3 != 2 || c3 == 4 || c3 == 7) {
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                }
                                            } else if (c4 == '#') {
                                                e();
                                                if (c3 != 2) {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                } else {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                }
                                            } else if (c4 == ',') {
                                                if (c3 != 2) {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                } else {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                }
                                            } else if (c4 == '/' || c4 == '=') {
                                                e();
                                                if (c3 != 2) {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                } else {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                }
                                            } else if (c4 == '{' || c4 == '}' || c4 == ':') {
                                                if (c3 != 2) {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                } else {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                }
                                            } else if (c4 != ';') {
                                                switch (c4) {
                                                    case '\\':
                                                        e();
                                                    case '[':
                                                    case ']':
                                                        if (c3 != 2) {
                                                            if (c3 != 2) {
                                                            }
                                                            this.j = i6;
                                                            i7 = 16;
                                                            this.h = 16;
                                                            break;
                                                        } else {
                                                            if (c3 != 2) {
                                                            }
                                                            this.j = i6;
                                                            i7 = 16;
                                                            this.h = 16;
                                                            break;
                                                        }
                                                    default:
                                                        i7 = 0;
                                                        break;
                                                }
                                            } else {
                                                e();
                                                if (c3 != 2) {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                } else {
                                                    if (c3 != 2) {
                                                    }
                                                    this.j = i6;
                                                    i7 = 16;
                                                    this.h = 16;
                                                }
                                            }
                                        } else {
                                            if (c3 == z4 || c3 == 0) {
                                                z2 = z;
                                                j = -(c4 - '0');
                                                c3 = 2;
                                            } else {
                                                if (c3 != 2) {
                                                    z2 = z;
                                                    if (c3 == 3) {
                                                        z = z2;
                                                        c3 = 4;
                                                    } else if (c3 == 5 || c3 == 6) {
                                                        z = z2;
                                                        c3 = 7;
                                                    }
                                                } else if (j != 0) {
                                                    boolean z6 = z;
                                                    long j2 = (10 * j) - ((long) (c4 - '0'));
                                                    z5 &= j > -922337203685477580L || (j == -922337203685477580L && j2 < j);
                                                    z = z6;
                                                    j = j2;
                                                }
                                                i6++;
                                                z4 = true;
                                            }
                                            z = z2;
                                            i6++;
                                            z4 = true;
                                        }
                                        if (i7 != 0) {
                                            return i7;
                                        }
                                        c5 = this.c[this.d];
                                        if (c5 != '\t' && c5 != '\n' && c5 != '\f' && c5 != '\r' && c5 != ' ') {
                                            if (c5 == '#') {
                                                e();
                                            } else if (c5 != ',') {
                                                if (c5 != '/' || c5 == '=') {
                                                    e();
                                                } else if (c5 != '{' && c5 != '}' && c5 != ':') {
                                                    if (c5 != ';') {
                                                        switch (c5) {
                                                            case '[':
                                                            case ']':
                                                                break;
                                                            case '\\':
                                                                e();
                                                                break;
                                                            default:
                                                                e();
                                                                this.h = 10;
                                                                return 10;
                                                        }
                                                    } else {
                                                        e();
                                                    }
                                                }
                                            }
                                        }
                                        b("Expected value");
                                        throw null;
                                    }
                                    z2 = z;
                                    if (c3 != 2 || c3 == 4) {
                                        c3 = 5;
                                        z = z2;
                                        i6++;
                                        z4 = true;
                                    }
                                }
                            } else if (i6 != cArr.length) {
                                if (a(i6 + 1)) {
                                    i4 = this.d;
                                    i5 = this.e;
                                    c4 = cArr[i4 + i6];
                                    if (c4 == '+') {
                                        if (c4 == 'E') {
                                        }
                                        z2 = z;
                                        if (c3 != 2) {
                                        }
                                        c3 = 5;
                                        z = z2;
                                        i6++;
                                        z4 = true;
                                    } else {
                                        if (c3 != 5) {
                                        }
                                        z = z;
                                        c3 = 6;
                                        i6++;
                                        z4 = true;
                                    }
                                } else {
                                    z3 = z;
                                }
                                if (c3 != 2) {
                                    if (c3 != 2) {
                                    }
                                    this.j = i6;
                                    i7 = 16;
                                    this.h = 16;
                                } else {
                                    if (c3 != 2) {
                                    }
                                    this.j = i6;
                                    i7 = 16;
                                    this.h = 16;
                                }
                                if (i7 != 0) {
                                    return i7;
                                }
                                c5 = this.c[this.d];
                                if (c5 != '\t') {
                                    if (c5 == '#') {
                                        e();
                                    } else if (c5 != ',') {
                                        if (c5 != '/') {
                                            e();
                                        } else {
                                            e();
                                        }
                                    }
                                }
                                b("Expected value");
                                throw null;
                            }
                            i7 = 0;
                            if (i7 != 0) {
                                return i7;
                            }
                            c5 = this.c[this.d];
                            if (c5 != '\t') {
                                if (c5 == '#') {
                                    e();
                                } else if (c5 != ',') {
                                    if (c5 != '/') {
                                        e();
                                    } else {
                                        e();
                                    }
                                }
                            }
                            b("Expected value");
                            throw null;
                        }
                    }
                    str = "false";
                    str2 = "FALSE";
                    i2 = 6;
                }
                int length = str.length();
                int i14 = 1;
                while (true) {
                    int i15 = this.d;
                    if (i14 >= length) {
                        if ((i15 + length < this.e || a(length + 1)) && (c = this.c[this.d + length]) != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                            if (c == '#') {
                                e();
                            } else if (c != ',') {
                                if (c == '/' || c == '=') {
                                    e();
                                } else if (c != '{' && c != '}' && c != ':') {
                                    if (c != ';') {
                                        switch (c) {
                                            case '\\':
                                                e();
                                            case '[':
                                            case ']':
                                                this.d += length;
                                                this.h = i2;
                                                i3 = i2;
                                                break;
                                        }
                                    } else {
                                        e();
                                    }
                                }
                            }
                        }
                        this.d += length;
                        this.h = i2;
                        i3 = i2;
                    } else if ((i15 + i14 < this.e || a(i14 + 1)) && ((c2 = this.c[this.d + i14]) == str.charAt(i14) || c2 == str2.charAt(i14))) {
                        i14++;
                    }
                    i3 = 0;
                }
                if (i3 != 0) {
                    return i3;
                }
                cArr = this.c;
                i4 = this.d;
                boolean z7 = true;
                i5 = this.e;
                i6 = 0;
                z = false;
                long j3 = 0;
                c3 = 0;
                while (true) {
                    if (i4 + i6 != i5) {
                        c4 = cArr[i4 + i6];
                        if (c4 == '+') {
                            if (c4 == 'E') {
                            }
                            z2 = z;
                            if (c3 != 2) {
                            }
                            c3 = 5;
                            z = z2;
                            i6++;
                            z4 = true;
                        } else {
                            if (c3 != 5) {
                            }
                            z = z;
                            c3 = 6;
                            i6++;
                            z4 = true;
                        }
                    } else if (i6 != cArr.length) {
                        if (a(i6 + 1)) {
                            z3 = z;
                        } else {
                            i4 = this.d;
                            i5 = this.e;
                            c4 = cArr[i4 + i6];
                            if (c4 == '+') {
                                if (c4 == 'E') {
                                }
                                z2 = z;
                                if (c3 != 2) {
                                }
                                c3 = 5;
                                z = z2;
                                i6++;
                                z4 = true;
                            } else {
                                if (c3 != 5) {
                                }
                                z = z;
                                c3 = 6;
                                i6++;
                                z4 = true;
                            }
                        }
                        if (c3 != 2) {
                            if (c3 != 2) {
                            }
                            this.j = i6;
                            i7 = 16;
                            this.h = 16;
                        } else {
                            if (c3 != 2) {
                            }
                            this.j = i6;
                            i7 = 16;
                            this.h = 16;
                        }
                        if (i7 != 0) {
                            return i7;
                        }
                        c5 = this.c[this.d];
                        if (c5 != '\t') {
                            if (c5 == '#') {
                                e();
                            } else if (c5 != ',') {
                                if (c5 != '/') {
                                    e();
                                } else {
                                    e();
                                }
                            }
                        }
                        b("Expected value");
                        throw null;
                    }
                    i7 = 0;
                    if (i7 != 0) {
                        return i7;
                    }
                    c5 = this.c[this.d];
                    if (c5 != '\t') {
                        if (c5 == '#') {
                            e();
                        } else if (c5 != ',') {
                            if (c5 != '/') {
                                e();
                            } else {
                                e();
                            }
                        }
                    }
                    b("Expected value");
                    throw null;
                }
            }
            i = 1;
            if (i9 == 1) {
                this.h = 4;
                return 4;
            }
        }
        if (i9 != i && i9 != 2) {
            b("Unexpected value");
            throw null;
        }
        e();
        this.d -= i;
        this.h = 7;
        return 7;
    }

    public final void g() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 4) {
            zia.a("Expected END_ARRAY but was ", AbstractC2497rD.a(p()), j());
            return;
        }
        int i = this.m;
        this.m = i - 1;
        int[] iArr = this.o;
        int i2 = i - 2;
        iArr[i2] = iArr[i2] + 1;
        this.h = 0;
    }

    public final void h() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 2) {
            zia.a("Expected END_OBJECT but was ", AbstractC2497rD.a(p()), j());
            return;
        }
        int i = this.m;
        int i2 = i - 1;
        this.m = i2;
        this.n[i2] = null;
        int[] iArr = this.o;
        int i3 = i - 2;
        iArr[i3] = iArr[i3] + 1;
        this.h = 0;
    }

    public final boolean i() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        return (iF == 2 || iF == 4 || iF == 17) ? false : true;
    }

    public final String j() {
        return " at line " + (this.f + 1) + " column " + ((this.d - this.g) + 1) + " path " + a(false);
    }

    public final boolean k() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 5) {
            this.h = 0;
            int[] iArr = this.o;
            int i = this.m - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iF != 6) {
            zia.a("Expected a boolean but was ", AbstractC2497rD.a(p()), j());
            return false;
        }
        this.h = 0;
        int[] iArr2 = this.o;
        int i2 = this.m - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return false;
    }

    public final String l() throws IOException {
        String strA;
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 14) {
            strA = o();
        } else if (iF == 12) {
            strA = a('\'');
        } else {
            if (iF != 13) {
                zia.a("Expected a name but was ", AbstractC2497rD.a(p()), j());
                return null;
            }
            strA = a('\"');
        }
        this.h = 0;
        this.n[this.m - 1] = strA;
        return strA;
    }

    public final void m() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 7) {
            zia.a("Expected null but was ", AbstractC2497rD.a(p()), j());
            return;
        }
        this.h = 0;
        int[] iArr = this.o;
        int i = this.m - 1;
        iArr[i] = iArr[i] + 1;
    }

    public final String n() throws IOException {
        String str;
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 10) {
            str = o();
        } else if (iF == 8) {
            str = a('\'');
        } else if (iF == 9) {
            str = a('\"');
        } else if (iF == 11) {
            str = this.k;
            this.k = null;
        } else if (iF == 15) {
            str = Long.toString(this.i);
        } else {
            if (iF != 16) {
                zia.a("Expected a string but was ", AbstractC2497rD.a(p()), j());
                return null;
            }
            str = new String(this.c, this.d, this.j);
            this.d += this.j;
        }
        this.h = 0;
        int[] iArr = this.o;
        int i = this.m - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0042. Please report as an issue. */
    public final String o() throws BM {
        String string;
        StringBuilder sb = null;
        int i = 0;
        while (true) {
            int i2 = 0;
            while (true) {
                int i3 = this.d + i2;
                int i4 = this.e;
                char[] cArr = this.c;
                if (i3 < i4) {
                    char c = cArr[i3];
                    if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                        if (c != '#') {
                            if (c != ',') {
                                if (c != '/' && c != '=') {
                                    if (c != '{' && c != '}' && c != ':') {
                                        if (c != ';') {
                                            switch (c) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i2++;
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        e();
                    }
                    i = i2;
                } else if (i2 >= cArr.length) {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i2, 16));
                    }
                    sb.append(this.c, this.d, i2);
                    this.d += i2;
                    if (!a(1)) {
                    }
                } else if (!a(i2 + 1)) {
                    i = i2;
                }
                char[] cArr2 = this.c;
                if (sb == null) {
                    string = new String(cArr2, this.d, i);
                } else {
                    sb.append(cArr2, this.d, i);
                    string = sb.toString();
                }
                this.d += i;
                return string;
            }
        }
    }

    public final int p() throws IOException {
        int iF = this.h;
        if (iF == 0) {
            iF = f();
        }
        switch (iF) {
            case 1:
                return 3;
            case 2:
                return 4;
            case XmlPullParser.END_TAG /* 3 */:
                return 1;
            case 4:
                return 2;
            case XmlPullParser.CDSECT /* 5 */:
            case XmlPullParser.ENTITY_REF /* 6 */:
                return 8;
            case 7:
                return 9;
            case 8:
            case 9:
            case XmlPullParser.DOCDECL /* 10 */:
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return 6;
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case Fcntl.S_IWGRP /* 16 */:
                return 7;
            case 17:
                return 10;
            default:
                x1f.a();
                return 0;
        }
    }

    public final char q() throws BM {
        int i;
        if (this.d == this.e && !a(1)) {
            b("Unterminated escape sequence");
            throw null;
        }
        char[] cArr = this.c;
        int i2 = this.d;
        int i3 = i2 + 1;
        this.d = i3;
        char c = cArr[i2];
        if (c == '\n') {
            this.f++;
            this.g = i3;
            return c;
        }
        if (c == '\"' || c == '\'' || c == '/' || c == '\\') {
            return c;
        }
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return '\r';
        }
        if (c == 't') {
            return '\t';
        }
        if (c != 'u') {
            b("Invalid escape sequence");
            throw null;
        }
        if (i2 + 5 > this.e && !a(4)) {
            b("Unterminated escape sequence");
            throw null;
        }
        int i4 = this.d;
        int i5 = i4 + 4;
        char c2 = 0;
        while (i4 < i5) {
            char[] cArr2 = this.c;
            char c3 = cArr2[i4];
            char c4 = (char) (c2 << 4);
            if (c3 >= '0' && c3 <= '9') {
                i = c3 - '0';
            } else if (c3 >= 'a' && c3 <= 'f') {
                i = c3 - 'W';
            } else {
                if (c3 < 'A' || c3 > 'F') {
                    throw new NumberFormatException("\\u".concat(new String(cArr2, this.d, 4)));
                }
                i = c3 - '7';
            }
            c2 = (char) (i + c4);
            i4++;
        }
        this.d += 4;
        return c2;
    }

    public final void r() {
        char c;
        do {
            if (this.d >= this.e && !a(1)) {
                return;
            }
            char[] cArr = this.c;
            int i = this.d;
            int i2 = i + 1;
            this.d = i2;
            c = cArr[i];
            if (c == '\n') {
                this.f++;
                this.g = i2;
                return;
            }
        } while (c != '\r');
    }

    public final String toString() {
        return C2327pD.class.getSimpleName() + j();
    }

    public final boolean a(int i) throws IOException {
        int i2;
        int i3;
        char[] cArr = this.c;
        int i4 = this.g;
        int i5 = this.d;
        this.g = i4 - i5;
        int i6 = this.e;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.e = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.e = 0;
        }
        this.d = 0;
        do {
            Reader reader = this.a;
            int i8 = this.e;
            int i9 = reader.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = this.e + i9;
            this.e = i2;
            if (this.f == 0 && (i3 = this.g) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.d++;
                this.g = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    public final String a(boolean z) {
        StringBuilder sb = new StringBuilder("$");
        int i = 0;
        while (true) {
            int i2 = this.m;
            if (i < i2) {
                int i3 = this.l[i];
                if (i3 == 1 || i3 == 2) {
                    int i4 = this.o[i];
                    if (z && i4 > 0 && i == i2 - 1) {
                        i4--;
                    }
                    sb.append('[');
                    sb.append(i4);
                    sb.append(']');
                } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                    sb.append('.');
                    String str = this.n[i];
                    if (str != null) {
                        sb.append(str);
                    }
                }
                i++;
            } else {
                return sb.toString();
            }
        }
    }

    public final void b(int i) {
        int i2 = this.m;
        int[] iArr = this.l;
        if (i2 == iArr.length) {
            int i3 = i2 * 2;
            this.l = Arrays.copyOf(iArr, i3);
            this.o = Arrays.copyOf(this.o, i3);
            this.n = (String[]) Arrays.copyOf(this.n, i3);
        }
        int[] iArr2 = this.l;
        int i4 = this.m;
        this.m = i4 + 1;
        iArr2[i4] = i;
    }

    public final void b(String str) throws BM {
        throw new BM(str + j());
    }
}
