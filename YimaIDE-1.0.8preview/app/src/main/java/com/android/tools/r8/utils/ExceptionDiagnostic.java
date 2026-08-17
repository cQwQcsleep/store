package com.android.tools.r8.utils;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ExceptionDiagnostic implements Diagnostic {
    static final /* synthetic */ boolean e = true;
    private final Throwable b;
    private final Origin c;
    private final Position d;

    public ExceptionDiagnostic(Throwable th, Origin origin, Position position) {
        boolean z = e;
        if (!z && th == null) {
            x1f.a();
            throw null;
        }
        if (!z && origin == null) {
            x1f.a();
            throw null;
        }
        if (!z && position == null) {
            x1f.a();
            throw null;
        }
        this.b = th;
        this.c = origin;
        this.d = position;
    }

    public Throwable getCause() {
        return this.b;
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        return this.b.toString();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return this.d;
    }

    public ExceptionDiagnostic(Throwable th) {
        this(th, Origin.unknown(), Position.UNKNOWN);
    }

    public ExceptionDiagnostic(Throwable th, Origin origin) {
        this(th, origin, Position.UNKNOWN);
    }

    public ExceptionDiagnostic(ResourceException resourceException) {
        this(resourceException, resourceException.getOrigin());
    }
}
