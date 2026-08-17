package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.AbstractC0007c;
import defpackage.x0g;
import java.nio.charset.StandardCharsets;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.dZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1323dZ implements com.android.tools.r8.naming.P {
    public static final byte[] h = "sourceFile".getBytes();
    public final Predicate c;
    public final boolean d;
    public int a = 0;
    public int b = 0;
    public boolean e = false;
    public boolean f = false;
    public int g = 9;

    public AbstractC1323dZ(Predicate predicate, boolean z) {
        this.c = predicate;
        this.d = z;
    }

    /* JADX WARN: Code duplicated, block: B:121:0x0155 A[PHI: r2
      0x0155: PHI (r2v17 int) = (r2v16 int), (r2v19 int) binds: [B:112:0x013a, B:116:0x014a] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.android.tools.r8.naming.P
    public final String a() {
        String str;
        int i;
        int i2;
        int i3;
        boolean z;
        int i4;
        int i5;
        while (true) {
            this.a = 0;
            this.b = 0;
            String str2 = null;
            byte[] bArr = null;
            do {
                byte[] bArrE = e();
                if (bArrE == null) {
                    break;
                }
                if (b() || bArr != null) {
                    int iC = c() - d();
                    int length = bArr == null ? 0 : bArr.length;
                    int i6 = iC + length;
                    byte[] bArr2 = new byte[i6];
                    if (bArr != null) {
                        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    }
                    System.arraycopy(bArrE, d(), bArr2, length, iC);
                    this.b = i6;
                    bArr = bArr2;
                } else {
                    this.a = d();
                    this.b = c();
                    bArr = bArrE;
                }
            } while (b());
            if (bArr == null) {
                return null;
            }
            Predicate predicate = this.c;
            int i7 = this.a;
            if (predicate == null) {
                return new String(bArr, i7, this.b - i7, StandardCharsets.UTF_8);
            }
            int i8 = this.b;
            int i9 = 1;
            while (i9 != 9 && i9 != 6 && i9 != 8) {
                boolean z2 = AbstractC1070aZ.a;
                if (!z2 && i9 == 9) {
                    x1f.a();
                    return str2;
                }
                int iB = AbstractC0007c.b(i9);
                if (iB == 0) {
                    str = str2;
                    while (true) {
                        if (i7 >= i8) {
                            i9 = 9;
                            break;
                            break;
                        }
                        if (!Character.isWhitespace(bArr[i7])) {
                            i9 = 2;
                            break;
                        }
                        i7++;
                    }
                } else {
                    str = str2;
                    if (iB == 1) {
                        if (bArr[i7] != 35) {
                            int i10 = i7;
                            while (true) {
                                if (i10 < i8) {
                                    byte b = bArr[i10];
                                    if (b == 32) {
                                        i = i10 - i7;
                                        break;
                                    }
                                    if (!Character.isWhitespace(b)) {
                                        i10++;
                                    }
                                }
                                i = -1;
                                break;
                            }
                            if (i <= 0) {
                                i2 = i10;
                                i7 = i2;
                                i9 = 9;
                                break;
                                break;
                            }
                            i7 = i10;
                            i9 = 3;
                        } else {
                            i9 = 7;
                        }
                    } else if (iB == 2) {
                        int i11 = i7 + 1;
                        if (bArr[i7] == 32) {
                            i3 = i7 + 2;
                            if (bArr[i11] == 45) {
                                i11 = i7 + 3;
                                if (bArr[i3] == 62) {
                                    i3 = i7 + 4;
                                    z = bArr[i11] == 32;
                                } else {
                                    i3 = i11;
                                }
                            }
                        } else {
                            i3 = i11;
                        }
                        i7 = i3;
                        if (!z) {
                            i9 = 9;
                            break;
                            break;
                        }
                        i9 = 4;
                    } else if (iB == 3) {
                        i2 = i7;
                        while (true) {
                            if (i2 < i8) {
                                byte b2 = bArr[i2];
                                if (b2 == 58) {
                                    i4 = i2 - i7;
                                    break;
                                }
                                if (!Character.isWhitespace(b2)) {
                                    i2++;
                                }
                            }
                            i4 = -1;
                            break;
                        }
                        if (i4 <= 0) {
                            i7 = i2;
                            i9 = 9;
                            break;
                            break;
                        }
                        i9 = 5;
                        i7 = i2;
                    } else {
                        if (iB == 4) {
                            i5 = i7 + 1;
                            if (bArr[i7] == 58) {
                                i7 = i5;
                                while (true) {
                                    if (i7 < i8) {
                                        if (Character.isWhitespace(bArr[i7])) {
                                            i7++;
                                        } else if (bArr[i7] == 35) {
                                        }
                                    }
                                    i9 = 6;
                                }
                            } else {
                                i7 = i5;
                            }
                            i9 = 9;
                            break;
                            break;
                        }
                        if (iB == 6) {
                            while (true) {
                                if (i7 >= i8) {
                                    i9 = 9;
                                    break;
                                    break;
                                }
                                i5 = i7 + 1;
                                if (bArr[i7] == 123) {
                                    while (true) {
                                        if (i5 < i8) {
                                            int i12 = i5 + 1;
                                            if (bArr[i5] == 58) {
                                                i5 += 2;
                                                byte b3 = bArr[i12];
                                                if (b3 == 39 || b3 == 34) {
                                                    int i13 = i8 - i5;
                                                    byte[] bArr3 = h;
                                                    if (i13 >= bArr3.length) {
                                                        int length2 = bArr3.length + i5;
                                                        int i14 = 0;
                                                        while (true) {
                                                            if (i5 < length2) {
                                                                int i15 = i14 + 1;
                                                                if (h[i14] == bArr[i5]) {
                                                                    i5++;
                                                                    i14 = i15;
                                                                }
                                                            } else {
                                                                int i16 = i5 + 1;
                                                                byte b4 = bArr[i5];
                                                                if (b4 == 39 || b4 == 34) {
                                                                    i7 = i16;
                                                                    i9 = 8;
                                                                } else {
                                                                    i5 = i16;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                i5 = i12;
                                            }
                                        }
                                        i7 = i5;
                                        i9 = 9;
                                        break;
                                    }
                                }
                                i7 = i5;
                            }
                        } else {
                            if (z2 || i9 == 9 || i9 == 6 || i9 == 8) {
                                x0g.a("Should not compute next state on terminal state");
                                return str;
                            }
                            x1f.a();
                            return str;
                        }
                    }
                }
                str2 = str;
            }
            this.g = i9;
            if (i9 == 6) {
                this.f = true;
                int i17 = this.a;
                String str3 = new String(bArr, i17, this.b - i17, StandardCharsets.UTF_8);
                boolean zTest = this.c.test(AbstractC0005a.a(1, str3.indexOf(">") + 2, str3));
                this.e = zTest;
                if (zTest || this.d) {
                    return str3;
                }
            } else {
                if (i9 == 8 && this.d) {
                    int i18 = this.a;
                    return new String(bArr, i18, this.b - i18, StandardCharsets.UTF_8);
                }
                if (this.e || (!this.f && this.d)) {
                    int i19 = this.a;
                    return new String(bArr, i19, this.b - i19, StandardCharsets.UTF_8);
                }
            }
        }
    }

    public abstract boolean b();

    public abstract int c();

    public abstract int d();

    public abstract byte[] e();
}
