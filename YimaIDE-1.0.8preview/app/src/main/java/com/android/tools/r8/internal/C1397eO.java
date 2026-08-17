package com.android.tools.r8.internal;

import com.android.tools.r8.dex.C0155s;
import com.android.tools.r8.retrace.RetracePartitionException;
import defpackage.x0g;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1397eO {
    public final List a;
    public final Set b;

    public C1397eO(List list, HashSet hashSet) {
        this.a = list;
        this.b = hashSet;
    }

    public static C1397eO a(byte[] bArr, Predicate predicate) {
        C0155s c0155sB = C0155s.b(bArr);
        short sG = c0155sB.g();
        List<String> listF = null;
        HashSet hashSetG = null;
        for (int i = 0; i < sG; i++) {
            short sG2 = c0155sB.g();
            EnumC1229cO enumC1229cOB = EnumC1229cO.b(sG2);
            if (enumC1229cOB == EnumC1229cO.c) {
                throw new RetracePartitionException("Could not additional info from key: " + ((int) sG2));
            }
            if (predicate.test(enumC1229cOB)) {
                int iOrdinal = enumC1229cOB.ordinal();
                if (iOrdinal == 1) {
                    listF = Wf0.f(c0155sB.i());
                } else {
                    if (iOrdinal != 2) {
                        x0g.a("Unreachable since we already checked for UNKNOWN");
                        return null;
                    }
                    hashSetG = Wf0.g(c0155sB.i());
                }
            } else {
                c0155sB.c(c0155sB.m() + c0155sB.f());
            }
        }
        return new C1397eO(listF, hashSetG);
    }

    public Collection b() {
        return this.a;
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean d() {
        return this.a != null;
    }

    public final void a(DataOutputStream dataOutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream2.writeShort(2);
        dataOutputStream2.writeShort(0);
        AbstractC1583gb0.a(dataOutputStream2, Wf0.a("\n", this.a));
        dataOutputStream2.writeShort(1);
        ArrayList arrayList = new ArrayList(this.b);
        Collections.sort(arrayList);
        AbstractC1583gb0.a(dataOutputStream2, Wf0.a("\n", (List) arrayList));
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        dataOutputStream.writeInt(byteArray.length);
        dataOutputStream.write(byteArray);
    }

    public Set a() {
        return this.b;
    }
}
