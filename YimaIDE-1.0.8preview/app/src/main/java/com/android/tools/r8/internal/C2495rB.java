package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.GlobalSyntheticsResourceProvider;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.Version;
import com.android.tools.r8.internal.C2495rB;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2495rB implements ProgramResourceProvider {
    public final List a;
    public ArrayList b = null;

    public C2495rB(List list) {
        this.a = list;
    }

    public final synchronized void a() {
        try {
            if (this.b != null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            HashSet hashSet = new HashSet();
            for (GlobalSyntheticsResourceProvider globalSyntheticsResourceProvider : this.a) {
                ArrayList arrayList2 = new ArrayList();
                try {
                    ZipInputStream zipInputStream = new ZipInputStream(globalSyntheticsResourceProvider.getByteStream());
                    ProgramResource.Kind kindValueOf = null;
                    while (true) {
                        try {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry != null) {
                                String name = nextEntry.getName();
                                if (name.equals("kind")) {
                                    kindValueOf = ProgramResource.Kind.valueOf(new String(K7.a(zipInputStream), StandardCharsets.UTF_8));
                                } else if (name.equals("compilerinfo")) {
                                    String str = new String(K7.a(zipInputStream), StandardCharsets.UTF_8);
                                    if (!Version.getVersionString().equals(str)) {
                                        throw new ResourceException(globalSyntheticsResourceProvider.getOrigin(), "Outdated or inconsistent global synthetics information.\nGlobal synthetics information version: " + str + "\nCompiler version: " + Version.getVersionString());
                                    }
                                } else if (name.endsWith(".global") && hashSet.add(name)) {
                                    final C2411qB c2411qB = new C2411qB(name, globalSyntheticsResourceProvider.getOrigin());
                                    String strA = a(name);
                                    final byte[] bArrA = K7.a(zipInputStream);
                                    final Set setSingleton = Collections.singleton(strA);
                                    arrayList2.add(new Function() { // from class: p7i
                                        @Override // java.util.function.Function
                                        public final Object apply(Object obj) {
                                            return C2495rB.a(c2411qB, bArrA, setSingleton, (ProgramResource.Kind) obj);
                                        }
                                    });
                                }
                            }
                        } catch (Throwable th) {
                            try {
                                zipInputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    }
                    zipInputStream.close();
                    if (kindValueOf == null) {
                        throw new ResourceException(globalSyntheticsResourceProvider.getOrigin(), "Invalid global synthetics provider does not specify its content kind.");
                    }
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        arrayList.add((ProgramResource) ((Function) it.next()).apply(kindValueOf));
                    }
                } catch (IOException e) {
                    throw new ResourceException(globalSyntheticsResourceProvider.getOrigin(), e);
                }
            }
            this.b = arrayList;
        } catch (Throwable th3) {
            throw th3;
        }
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() {
        if (this.b == null) {
            a();
        }
        return this.b;
    }

    public static ProgramResource a(C2411qB c2411qB, byte[] bArr, Set set, ProgramResource.Kind kind) {
        return new C1152bV(c2411qB, kind, bArr, set);
    }

    public static String a(String str) {
        return C0929Wj.y(AbstractC0005a.a(7, 0, str) + ".class");
    }
}
