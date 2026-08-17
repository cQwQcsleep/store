package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.AssertionsConfiguration;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.H2;
import defpackage.hkh;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3250e {
    public static final /* synthetic */ boolean c = true;
    public final AssertionsConfiguration a;
    public final H2 b;

    public C3250e(AssertionsConfiguration assertionsConfiguration, B1 b1) {
        this.a = assertionsConfiguration;
        int i = AbstractC3248d.a[assertionsConfiguration.getScope().ordinal()];
        if (i == 1) {
            if (assertionsConfiguration.getValue().length() == 0) {
                this.b = b1.c(XmlPullParser.NO_NAMESPACE);
                return;
            }
            this.b = b1.c("L" + assertionsConfiguration.getValue().replace('.', DataResource.SEPARATOR) + "/");
            return;
        }
        if (i != 2) {
            if (i == 3) {
                this.b = null;
                return;
            } else {
                hkh.a();
                throw null;
            }
        }
        this.b = b1.c("L" + assertionsConfiguration.getValue().replace('.', DataResource.SEPARATOR) + ";");
    }
}
