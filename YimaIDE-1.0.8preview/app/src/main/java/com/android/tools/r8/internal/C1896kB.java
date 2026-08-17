package com.android.tools.r8.internal;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.Version;
import com.android.tools.r8.internal.C1405eW;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.zip.ZipOutputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1896kB {
    public static final /* synthetic */ boolean c = true;
    public final ProgramResource.Kind a;
    public final ArrayList b = new ArrayList();

    public C1896kB(ProgramResource.Kind kind) {
        this.a = kind;
    }

    public final byte[] a() throws IOException {
        String versionString = Version.getVersionString();
        Charset charset = StandardCharsets.UTF_8;
        this.b.add(new C1405eW("compilerinfo", versionString.getBytes(charset)));
        this.b.add(new C1405eW("kind", this.a.toString().getBytes(charset)));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((Integer) C2847vL.a((Collection) this.b, (Object) 0, new BiFunction() { // from class: vhh
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                C1405eW c1405eW = (C1405eW) obj2;
                return Integer.valueOf(((String) c1405eW.a()).length() + ((Integer) obj).intValue() + ((byte[]) c1405eW.b()).length + 200);
            }
        })).intValue() + 500);
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        try {
            for (C1405eW c1405eW : this.b) {
                com.android.tools.r8.utils.v.a(zipOutputStream, (String) c1405eW.a(), (byte[]) c1405eW.b(), 0);
                c1405eW.b = null;
            }
            zipOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                zipOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void a(String str, byte[] bArr) {
        this.b.add(new C1405eW(str, bArr));
    }

    public static String a(String str) {
        if (!c && (str == null || !C0929Wj.z(str))) {
            x1f.a();
            return null;
        }
        return C0929Wj.i(str) + ".global";
    }
}
