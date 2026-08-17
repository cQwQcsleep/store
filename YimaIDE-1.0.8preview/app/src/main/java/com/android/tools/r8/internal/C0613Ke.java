package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.MethodPosition;
import com.android.tools.r8.position.Position;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ke, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0613Ke extends RuntimeException {
    public final Origin b;
    public final Position c;

    public C0613Ke(Origin origin, MethodPosition methodPosition) {
        super("Absent Code attribute in method that is not native or abstract", null);
        this.b = origin;
        this.c = methodPosition;
    }

    public C0613Ke(String str, Throwable th) {
        this(Origin.unknown(), str, th);
    }

    public C0613Ke(String str, Origin origin) {
        this(origin, str, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0613Ke(Origin origin, String str, Throwable th) {
        super(str, th);
        Position position = Position.UNKNOWN;
        this.b = origin;
        this.c = position;
    }

    public C0613Ke(String str) {
        this(Origin.unknown(), str, null);
    }

    public C0613Ke(String str, C0613Ke c0613Ke, Origin origin, Position position) {
        super(str, c0613Ke);
        this.b = origin;
        this.c = position;
    }
}
