package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.SourceFileEnvironment;
import com.android.tools.r8.SourceFileProvider;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.Iterator;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Yc0 implements SourceFileProvider {
    public static final AbstractC0706Nu c = AbstractC0706Nu.e().a(a("MAP_ID"), new SourceFileProvider() { // from class: p2g
        @Override // com.android.tools.r8.SourceFileProvider
        public final String get(SourceFileEnvironment sourceFileEnvironment) {
            return sourceFileEnvironment.getMapId();
        }
    }).a(a("MAP_HASH"), new SourceFileProvider() { // from class: q2g
        @Override // com.android.tools.r8.SourceFileProvider
        public final String get(SourceFileEnvironment sourceFileEnvironment) {
            return sourceFileEnvironment.getMapHash();
        }
    }).a();
    public static final /* synthetic */ boolean d = true;
    public final String a;
    public String b = null;

    public Yc0(String str) {
        this.a = str;
    }

    public static SourceFileProvider a(String str, DiagnosticsHandler diagnosticsHandler) {
        String strReplace = str;
        for (String str2 : c.keySet()) {
            strReplace = strReplace.replace(str2, " ".concat(str2.substring(1)));
        }
        if (!d && str.length() != strReplace.length()) {
            x1f.a();
            return null;
        }
        int iIndexOf = strReplace.indexOf(37);
        if (iIndexOf < 0) {
            return new Yc0(str);
        }
        while (iIndexOf >= 0) {
            Iterator it = c.keySet().iterator();
            int iMax = 0;
            while (it.hasNext()) {
                iMax = Math.max(iMax, ((String) it.next()).length());
            }
            diagnosticsHandler.error(new StringDiagnostic("Invalid template variable starting with ".concat(str.substring(iIndexOf, Math.min(iMax + iIndexOf, str.length())))));
            iIndexOf = strReplace.indexOf(37, iIndexOf + 1);
        }
        return null;
    }

    @Override // com.android.tools.r8.SourceFileProvider
    public final String get(final SourceFileEnvironment sourceFileEnvironment) {
        if (this.b == null) {
            this.b = this.a;
            c.forEach(new BiConsumer() { // from class: o2g
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a(sourceFileEnvironment, (String) obj, (SourceFileProvider) obj2);
                }
            });
        }
        return this.b;
    }

    public static String a(String str) {
        return "%" + str;
    }

    public final /* synthetic */ void a(SourceFileEnvironment sourceFileEnvironment, String str, SourceFileProvider sourceFileProvider) {
        this.b = this.b.replace(str, sourceFileProvider.get(sourceFileEnvironment));
    }
}
