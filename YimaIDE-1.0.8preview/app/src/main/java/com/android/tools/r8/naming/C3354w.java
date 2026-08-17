package com.android.tools.r8.naming;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.position.TextPosition;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

/* JADX INFO: renamed from: com.android.tools.r8.naming.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3354w implements AutoCloseable {
    public static final /* synthetic */ boolean c = true;
    public final BufferedReader a;
    public final Path b;

    public C3354w(Path path) {
        this.b = path;
        this.a = Files.newBufferedReader(path);
    }

    public final String a(C2742u50 c2742u50) throws IOException {
        if (!c && this.a == null) {
            x1f.a();
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (true) {
            int i2 = this.a.read();
            if (i2 == -1) {
                return sb.toString();
            }
            char c2 = (char) i2;
            if ((sb.length() == 0 || !Character.isJavaIdentifierPart(c2)) && !(sb.length() == 0 && Character.isJavaIdentifierStart(c2))) {
                boolean z = c2 == '#';
                boolean z2 = c2 == '\n' || c2 == '\r';
                if (z || z2) {
                    if (z) {
                        this.a.readLine();
                    }
                    i++;
                }
                if (z2 && sb.length() != 0) {
                    return sb.toString();
                }
                sb = new StringBuilder();
                if (!z2) {
                    c2742u50.info(new StringDiagnostic("Invalid character in dictionary '" + c2 + "'", new PathOrigin(this.b), new TextPosition(0L, i, -1)));
                    this.a.readLine();
                    i++;
                }
            } else {
                sb.append(c2);
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws IOException {
        BufferedReader bufferedReader = this.a;
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

    public static AbstractC0551Hu a(C2742u50 c2742u50, Path path) {
        if (path != null) {
            HashSet hashSet = new HashSet();
            C0473Eu c0473Eu = new C0473Eu();
            try {
                C3354w c3354w = new C3354w(path);
                try {
                    String strA = c3354w.a(c2742u50);
                    while (!strA.isEmpty()) {
                        if (!hashSet.add(strA)) {
                            c2742u50.error(new StringDiagnostic("Duplicate entry for '" + strA + "' in dictionary", new PathOrigin(path)));
                        }
                        c0473Eu.a(strA);
                        strA = c3354w.a(c2742u50);
                    }
                    c3354w.close();
                } catch (Throwable th) {
                    try {
                        c3354w.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e) {
                c2742u50.error(new ExceptionDiagnostic(e, new PathOrigin(path)));
            }
            return c0473Eu.a();
        }
        return AbstractC0551Hu.i();
    }
}
