package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.MapIdEnvironment;
import com.android.tools.r8.MapIdProvider;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.Iterator;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FM implements MapIdProvider {
    public static final AbstractC0706Nu c = AbstractC0706Nu.e().a(a(), new MapIdProvider() { // from class: nl4
        @Override // com.android.tools.r8.MapIdProvider
        public final String get(MapIdEnvironment mapIdEnvironment) {
            return mapIdEnvironment.getMapHash();
        }
    }).a();
    public static final /* synthetic */ boolean d = true;
    public final String a;
    public String b = null;

    public FM(String str) {
        this.a = str;
    }

    public static MapIdProvider a(String str, DiagnosticsHandler diagnosticsHandler) {
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
            return new FM(str);
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

    @Override // com.android.tools.r8.MapIdProvider
    public final String get(final MapIdEnvironment mapIdEnvironment) {
        if (this.b == null) {
            this.b = this.a;
            c.forEach(new BiConsumer() { // from class: ml4
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.a(mapIdEnvironment, (String) obj, (MapIdProvider) obj2);
                }
            });
        }
        return this.b;
    }

    public static String a() {
        return "%MAP_HASH";
    }

    public final /* synthetic */ void a(MapIdEnvironment mapIdEnvironment, String str, MapIdProvider mapIdProvider) {
        this.b = this.b.replace(str, mapIdProvider.get(mapIdEnvironment));
    }
}
