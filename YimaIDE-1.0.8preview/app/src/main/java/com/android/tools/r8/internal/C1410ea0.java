package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedSourceFile;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ea0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1410ea0 implements RetracedSourceFile {
    public static final /* synthetic */ boolean c = true;
    public final RetracedClassReference a;
    public final String b;

    public C1410ea0(RetracedClassReference retracedClassReference, String str) {
        if (!c && retracedClassReference == null) {
            x1f.a();
            throw null;
        }
        this.a = retracedClassReference;
        this.b = str;
    }

    @Override // com.android.tools.r8.retrace.RetracedSourceFile
    public final String getOrInferSourceFile(String str) {
        String str2 = this.b;
        if (str2 != null) {
            return str2;
        }
        String typeName = this.a.getTypeName();
        if (str == null) {
            str = XmlPullParser.NO_NAMESPACE;
        }
        return V90.a(typeName, str, this.a.isKnown());
    }

    @Override // com.android.tools.r8.retrace.RetracedSourceFile
    public final String getSourceFile() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetracedSourceFile
    public final boolean hasRetraceResult() {
        return this.b != null;
    }

    @Override // com.android.tools.r8.retrace.RetracedSourceFile
    public final String getOrInferSourceFile() {
        return getOrInferSourceFile(null);
    }
}
