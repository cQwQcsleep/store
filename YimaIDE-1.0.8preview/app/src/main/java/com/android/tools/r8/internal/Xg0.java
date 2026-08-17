package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Xg0 extends mY {
    public static final List l = Collections.unmodifiableList(Arrays.asList("T", "I", "F", "D", "J", "N", "U"));
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public HashMap j;
    public int k;

    public Xg0(int i) {
        super(i);
        this.f = "  ";
        this.g = "    ";
        this.h = "      ";
        this.i = "   ";
    }

    public final void a(int i) {
        int i2 = i >>> 24;
        if (i2 == 0) {
            StringBuilder sb = ((mY) this).b;
            sb.append("CLASS_TYPE_PARAMETER ");
            sb.append((i & 16711680) >> 16);
            return;
        }
        if (i2 == 1) {
            StringBuilder sb2 = ((mY) this).b;
            sb2.append("METHOD_TYPE_PARAMETER ");
            sb2.append((i & 16711680) >> 16);
            return;
        }
        switch (i2) {
            case Fcntl.S_IWGRP /* 16 */:
                StringBuilder sb3 = ((mY) this).b;
                sb3.append("CLASS_EXTENDS ");
                sb3.append((int) ((short) ((i & 16776960) >> 8)));
                break;
            case 17:
                StringBuilder sb4 = ((mY) this).b;
                sb4.append("CLASS_TYPE_PARAMETER_BOUND ");
                sb4.append((i & 16711680) >> 16);
                sb4.append(", ");
                sb4.append((i & 65280) >> 8);
                break;
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                StringBuilder sb5 = ((mY) this).b;
                sb5.append("METHOD_TYPE_PARAMETER_BOUND ");
                sb5.append((i & 16711680) >> 16);
                sb5.append(", ");
                sb5.append((i & 65280) >> 8);
                break;
            case AndroidSdkVersion.KITKAT /* 19 */:
                ((mY) this).b.append("FIELD");
                break;
            case 20:
                ((mY) this).b.append("METHOD_RETURN");
                break;
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
                ((mY) this).b.append("METHOD_RECEIVER");
                break;
            case 22:
                StringBuilder sb6 = ((mY) this).b;
                sb6.append("METHOD_FORMAL_PARAMETER ");
                sb6.append((i & 16711680) >> 16);
                break;
            case AndroidSdkVersion.M /* 23 */:
                StringBuilder sb7 = ((mY) this).b;
                sb7.append("THROWS ");
                sb7.append((i & 16776960) >> 8);
                break;
            default:
                switch (i2) {
                    case 64:
                        ((mY) this).b.append("LOCAL_VARIABLE");
                        break;
                    case 65:
                        ((mY) this).b.append("RESOURCE_VARIABLE");
                        break;
                    case 66:
                        StringBuilder sb8 = ((mY) this).b;
                        sb8.append("EXCEPTION_PARAMETER ");
                        sb8.append((i & 16776960) >> 8);
                        break;
                    case 67:
                        ((mY) this).b.append("INSTANCEOF");
                        break;
                    case 68:
                        ((mY) this).b.append("NEW");
                        break;
                    case 69:
                        ((mY) this).b.append("CONSTRUCTOR_REFERENCE");
                        break;
                    case 70:
                        ((mY) this).b.append("METHOD_REFERENCE");
                        break;
                    case 71:
                        StringBuilder sb9 = ((mY) this).b;
                        sb9.append("CAST ");
                        sb9.append(i & 255);
                        break;
                    case 72:
                        StringBuilder sb10 = ((mY) this).b;
                        sb10.append("CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT ");
                        sb10.append(i & 255);
                        break;
                    case 73:
                        StringBuilder sb11 = ((mY) this).b;
                        sb11.append("METHOD_INVOCATION_TYPE_ARGUMENT ");
                        sb11.append(i & 255);
                        break;
                    case 74:
                        StringBuilder sb12 = ((mY) this).b;
                        sb12.append("CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT ");
                        sb12.append(i & 255);
                        break;
                    case 75:
                        StringBuilder sb13 = ((mY) this).b;
                        sb13.append("METHOD_REFERENCE_TYPE_ARGUMENT ");
                        sb13.append(i & 255);
                        break;
                    default:
                        j2d.a();
                        break;
                }
                break;
        }
    }

    public final void b(String str) {
        ((mY) this).b.setLength(0);
        int i = this.k;
        this.k = i + 1;
        b(i);
        if (str != null) {
            StringBuilder sb = ((mY) this).b;
            sb.append(str);
            sb.append('=');
        }
    }

    public final void b(int i) {
        if (i > 0) {
            ((mY) this).b.append(", ");
        }
    }

    public final void a(int i, String str, String str2, String str3, boolean z) {
        ((mY) this).b.setLength(0);
        StringBuilder sb = ((mY) this).b;
        sb.append(this.g);
        sb.append(mY.d[i]);
        sb.append(' ');
        a(0, str);
        StringBuilder sb2 = ((mY) this).b;
        sb2.append('.');
        sb2.append(str2);
        sb2.append(' ');
        a(3, str3);
        if (z) {
            ((mY) this).b.append(" (itf)");
        }
        ((mY) this).b.append('\n');
        ((mY) this).c.add(((mY) this).b.toString());
    }

    public final Xg0 a(int i, C3052xj0 c3052xj0, String str, boolean z) {
        ((mY) this).b.setLength(0);
        StringBuilder sb = ((mY) this).b;
        sb.append(this.f);
        sb.append('@');
        a(1, str);
        ((mY) this).b.append('(');
        ((mY) this).c.add(((mY) this).b.toString());
        ((mY) this).b.setLength(0);
        ((mY) this).b.append(") : ");
        a(i);
        StringBuilder sb2 = ((mY) this).b;
        sb2.append(", ");
        sb2.append(c3052xj0);
        ((mY) this).b.append(z ? "\n" : " // invisible\n");
        return a(((mY) this).b.toString());
    }

    public final void a(int i, String str) {
        if (i != 5 && i != 2 && i != 4) {
            ((mY) this).b.append(str);
        } else if (str != null) {
            StringBuilder sb = ((mY) this).b;
            sb.append("// signature ");
            sb.append(str);
            sb.append('\n');
        }
    }

    public final void a(WI wi) {
        if (this.j == null) {
            this.j = new HashMap();
        }
        String strA = (String) this.j.get(wi);
        if (strA == null) {
            strA = CX.a(this.j.size(), "L");
            this.j.put(wi, strA);
        }
        ((mY) this).b.append(strA);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0089  */
    /* JADX WARN: Code duplicated, block: B:21:0x0099  */
    /* JADX WARN: Code duplicated, block: B:24:0x00a4  */
    public final void a(C0497Fs c0497Fs) {
        int i = c0497Fs.a;
        StringBuilder sb = ((mY) this).b;
        sb.append("// handle kind 0x");
        sb.append(Integer.toHexString(i));
        sb.append(" : ");
        boolean z = true;
        switch (i) {
            case 1:
                ((mY) this).b.append("GETFIELD");
                z = false;
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case 2:
                ((mY) this).b.append("GETSTATIC");
                z = false;
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case XmlPullParser.END_TAG /* 3 */:
                ((mY) this).b.append("PUTFIELD");
                z = false;
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case 4:
                ((mY) this).b.append("PUTSTATIC");
                z = false;
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case XmlPullParser.CDSECT /* 5 */:
                ((mY) this).b.append("INVOKEVIRTUAL");
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case XmlPullParser.ENTITY_REF /* 6 */:
                ((mY) this).b.append("INVOKESTATIC");
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case 7:
                ((mY) this).b.append("INVOKESPECIAL");
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case 8:
                ((mY) this).b.append("NEWINVOKESPECIAL");
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            case 9:
                ((mY) this).b.append("INVOKEINTERFACE");
                ((mY) this).b.append('\n');
                ((mY) this).b.append(this.h);
                a(0, c0497Fs.b);
                ((mY) this).b.append('.');
                ((mY) this).b.append(c0497Fs.c);
                if (!z) {
                    ((mY) this).b.append('(');
                }
                a(9, c0497Fs.d);
                if (!z) {
                    ((mY) this).b.append(')');
                }
                if (c0497Fs.e) {
                    ((mY) this).b.append(" itf");
                }
                break;
            default:
                j2d.a();
                break;
        }
    }

    public final void a(int i, Object[] objArr) {
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                ((mY) this).b.append(' ');
            }
            Object obj = objArr[i2];
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.charAt(0) == '[') {
                    a(1, str);
                } else {
                    a(0, str);
                }
            } else if (obj instanceof Integer) {
                ((mY) this).b.append((String) l.get(((Integer) obj).intValue()));
            } else {
                a((WI) obj);
            }
        }
    }

    public final Xg0 a(String str) {
        Xg0 xg0 = new Xg0(((mY) this).a);
        ((mY) this).c.add(((mY) xg0).c);
        if (str != null) {
            ((mY) this).c.add(str);
        }
        return xg0;
    }
}
