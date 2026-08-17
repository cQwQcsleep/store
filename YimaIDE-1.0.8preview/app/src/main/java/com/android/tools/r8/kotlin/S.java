package com.android.tools.r8.kotlin;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S implements Diagnostic {
    public final Origin b;
    public final Position c;
    public final String d;

    public S(Origin origin, Position position, String str) {
        this.b = origin;
        this.c = position;
        this.d = str;
    }

    public static S a(I2 i2, Throwable th) {
        return new S(Origin.unknown(), Position.UNKNOWN, "Unexpected error during rewriting of Kotlin metadata for class '" + i2.m0() + "':" + Wf0.c + Wf0.a(th));
    }

    @Override // com.android.tools.r8.Diagnostic
    public final String getDiagnosticMessage() {
        return this.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Origin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Position getPosition() {
        return this.c;
    }

    public static S a(String str) {
        return new S(Origin.unknown(), Position.UNKNOWN, "The classifier " + str + " is unknown and cannot be parsed");
    }

    public static S a(E0 e0, String str) {
        return new S(e0.getOrigin(), Position.UNKNOWN, "The companion object " + str + " could not be found in class " + e0.e1());
    }

    public static S a() {
        return new S(Origin.unknown(), Position.UNKNOWN, "An error occurred when parsing kotlin metadata. This normally happens when using a newer version of kotlin than the kotlin version released when this version of R8 was created. To find compatible kotlin versions, please see: https://developer.android.com/studio/build/kotlin-d8-r8-versions");
    }
}
