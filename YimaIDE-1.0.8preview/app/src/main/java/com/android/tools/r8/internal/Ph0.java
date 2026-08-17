package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ph0 extends Eb0 {
    public static final Map l;
    public final boolean a;
    public final StringBuilder b;
    public StringBuilder c;
    public StringBuilder d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public int j;
    public String k;

    static {
        HashMap map = new HashMap();
        map.put('Z', "boolean");
        map.put('B', "byte");
        map.put('C', "char");
        map.put('S', "short");
        map.put('I', "int");
        map.put('J', "long");
        map.put('F', "float");
        map.put('D', "double");
        map.put('V', "void");
        l = Collections.unmodifiableMap(map);
    }

    public Ph0() {
        this.k = XmlPullParser.NO_NAMESPACE;
        this.a = false;
        this.b = new StringBuilder();
    }

    public final void a(String str) {
        if (!"java/lang/Object".equals(str)) {
            StringBuilder sb = this.b;
            sb.append(this.k);
            sb.append(str.replace(DataResource.SEPARATOR, '.'));
        } else if (this.i % 2 != 0 || this.g) {
            StringBuilder sb2 = this.b;
            sb2.append(this.k);
            sb2.append(str.replace(DataResource.SEPARATOR, '.'));
        }
        this.k = XmlPullParser.NO_NAMESPACE;
        this.i *= 2;
    }

    public final void b(String str) {
        if (this.i % 2 != 0) {
            this.b.append('>');
        }
        this.i /= 2;
        this.b.append('.');
        StringBuilder sb = this.b;
        sb.append(this.k);
        sb.append(str.replace(DataResource.SEPARATOR, '.'));
        this.k = XmlPullParser.NO_NAMESPACE;
        this.i *= 2;
    }

    public Ph0(StringBuilder sb) {
        this.k = XmlPullParser.NO_NAMESPACE;
        this.a = false;
        this.b = sb;
    }

    public final Ph0 a(char c) {
        int i = this.i;
        if (i % 2 == 0) {
            this.i = i + 1;
            this.b.append('<');
        } else {
            this.b.append(", ");
        }
        if (c == '+') {
            this.b.append("? extends ");
        } else if (c == '-') {
            this.b.append("? super ");
        }
        this.j *= 2;
        return this;
    }

    public final void a() {
        int i = this.j;
        if (i % 2 == 0) {
            this.j = i / 2;
            return;
        }
        while (true) {
            int i2 = this.j;
            if (i2 % 2 == 0) {
                return;
            }
            this.j = i2 / 2;
            this.b.append("[]");
        }
    }
}
