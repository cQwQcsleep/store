package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ga, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0505Ga extends AbstractC0686Na {
    public static final /* synthetic */ boolean e = true;
    public final AbstractC0660Ma[] c;
    public final AbstractC0660Ma[] d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0505Ga(com.android.tools.r8.graph.I2[] i2Arr, com.android.tools.r8.graph.I2[] i2Arr2, AbstractC2004lX abstractC2004lX) {
        super(0, null);
        if (!e && abstractC2004lX == null) {
            x1f.a();
            throw null;
        }
        this.c = new AbstractC0660Ma[i2Arr.length];
        this.d = new AbstractC0660Ma[i2Arr2.length];
        for (int i = 0; i < i2Arr.length; i++) {
            AbstractC0660Ma[] abstractC0660MaArr = this.c;
            com.android.tools.r8.graph.I2 i2 = i2Arr[i];
            abstractC0660MaArr[i] = i2 == null ? null : i2.Z0().equals("NULL") ? new C0609Ka(El0.b) : new C0635La(i2);
        }
        for (int i3 = 0; i3 < i2Arr2.length; i3++) {
            if (!e && i2Arr2[i3] == null) {
                x1f.a();
                throw null;
            }
            AbstractC0660Ma[] abstractC0660MaArr2 = this.d;
            com.android.tools.r8.graph.I2 i4 = i2Arr2[i3];
            abstractC0660MaArr2[i3] = i4.Z0().equals("NULL") ? new C0609Ka(El0.b) : new C0635La(i4);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final void a(C0505Ga c0505Ga) {
        int i = 0;
        int i2 = 0;
        while (true) {
            AbstractC0660Ma[] abstractC0660MaArr = this.c;
            if (i2 >= abstractC0660MaArr.length) {
                break;
            }
            AbstractC0660Ma[] abstractC0660MaArr2 = c0505Ga.c;
            if (i2 >= abstractC0660MaArr2.length) {
                break;
            }
            abstractC0660MaArr2[i2] = abstractC0660MaArr[i2];
            i2++;
        }
        while (true) {
            AbstractC0660Ma[] abstractC0660MaArr3 = this.d;
            if (i >= abstractC0660MaArr3.length) {
                return;
            }
            AbstractC0660Ma[] abstractC0660MaArr4 = c0505Ga.d;
            if (i >= abstractC0660MaArr4.length) {
                return;
            }
            abstractC0660MaArr4[i] = abstractC0660MaArr3[i];
            i++;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja b(int i) {
        return new C0583Ja(i + 100000, this.d[i]);
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja c() {
        if (e || this.d.length > 0) {
            return b(this.d.length - 1);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final int d() {
        return this.d.length;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("stack: [");
        AbstractC0660Ma[] abstractC0660MaArr = this.d;
        int length = abstractC0660MaArr.length;
        String str = XmlPullParser.NO_NAMESPACE;
        int i = 0;
        String str2 = XmlPullParser.NO_NAMESPACE;
        int i2 = 0;
        while (i2 < length) {
            AbstractC0660Ma abstractC0660Ma = abstractC0660MaArr[i2];
            sb.append(str2);
            sb.append(abstractC0660Ma);
            i2++;
            str2 = ", ";
        }
        sb.append("] locals: [");
        while (true) {
            AbstractC0660Ma[] abstractC0660MaArr2 = this.c;
            if (i >= abstractC0660MaArr2.length) {
                sb.append(']');
                return sb.toString();
            }
            if (abstractC0660MaArr2[i] != null) {
                sb.append(str);
                sb.append(i);
                sb.append(':');
                sb.append(this.c[i]);
                str = ", ";
            }
            i++;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final int b() {
        return this.c.length - 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0583Ja a(int i) {
        AbstractC0660Ma abstractC0660Ma;
        AbstractC0660Ma[] abstractC0660MaArr = this.c;
        if (i < abstractC0660MaArr.length && (abstractC0660Ma = abstractC0660MaArr[i]) != null) {
            return new C0583Ja(i, abstractC0660Ma);
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0686Na
    public final C0505Ga a() {
        return this;
    }

    public C0505Ga(int i, int i2) {
        super(0, null);
        this.c = new AbstractC0660Ma[i];
        this.d = new AbstractC0660Ma[i2];
    }
}
