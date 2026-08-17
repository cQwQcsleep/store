package com.android.tools.r8.kotlin;

import com.android.tools.r8.internal.InterfaceC1936kh0;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class l0 implements Closeable {
    public static final /* synthetic */ boolean c = true;
    public final BufferedReader a;
    public String b;

    public l0(String str) {
        this.a = new BufferedReader(new StringReader(str));
    }

    public final String a(Predicate predicate, int i, InterfaceC1936kh0 interfaceC1936kh0) throws Throwable {
        if (predicate.test(this.b)) {
            return this.b;
        }
        ArrayList arrayList = new ArrayList();
        String line = this.a.readLine();
        this.b = line;
        arrayList.add(line);
        int i2 = i;
        while (!predicate.test(this.b) && !c()) {
            if (i2 != 1) {
                i2--;
            } else {
                if (!c && arrayList.size() != i) {
                    x1f.a();
                    return null;
                }
                interfaceC1936kh0.accept(arrayList);
                arrayList = new ArrayList();
                i2 = i;
            }
            String line2 = this.a.readLine();
            this.b = line2;
            arrayList.add(line2);
        }
        if (arrayList.isEmpty() || predicate.test((String) arrayList.get(0))) {
            if (arrayList.isEmpty()) {
                return null;
            }
            return (String) arrayList.get(0);
        }
        throw new m0("Block size does not match linesInBlock = " + i);
    }

    public final boolean c() {
        return this.b == null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
    }

    public final void d() throws m0, IOException {
        String line = this.a.readLine();
        this.b = line;
        if (line.equals("*F")) {
            return;
        }
        throw new m0("The string " + this.b + " does not match the expected string *F");
    }

    public final void a(InterfaceC1936kh0 interfaceC1936kh0) throws Throwable {
        final String str = "*L";
        a(new Predicate() { // from class: zih
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return str.equals((String) obj);
            }
        }, 2, interfaceC1936kh0);
    }
}
