package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class UnsupportedFeatureDiagnostic implements Diagnostic {
    private final String b;
    private final EnumC3077y2 c;
    private final Origin d;
    private final Position e;

    public UnsupportedFeatureDiagnostic(String str, EnumC3077y2 enumC3077y2, Origin origin, Position position) {
        this.b = str;
        this.c = enumC3077y2;
        this.d = origin;
        this.e = position;
    }

    public static String makeMessage(EnumC3077y2 enumC3077y2, String str, String str2) {
        String str3;
        if (enumC3077y2 == null) {
            str3 = str + " are not supported at any API level known by the compiler";
        } else {
            str3 = str + " are only supported starting with " + enumC3077y2.e() + " (--min-api " + enumC3077y2.d() + ")";
        }
        if (str2 == null) {
            return str3;
        }
        return str3 + ": " + str2;
    }

    public String getFeatureDescriptor() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.e;
    }

    public int getSupportedApiLevel() {
        EnumC3077y2 enumC3077y2 = this.c;
        if (enumC3077y2 == null) {
            return -1;
        }
        return enumC3077y2.d();
    }
}
