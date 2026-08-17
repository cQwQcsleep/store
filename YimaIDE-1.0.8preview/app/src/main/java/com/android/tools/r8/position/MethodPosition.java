package com.android.tools.r8.position;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.references.MethodReference;
import defpackage.u1a;
import java.util.List;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class MethodPosition implements Position {
    private final MethodReference a;
    private final Position b;

    public MethodPosition(MethodReference methodReference) {
        Position position = Position.UNKNOWN;
        this.a = methodReference;
        this.b = position;
    }

    public static MethodPosition create(C0231j1 c0231j1) {
        Position position = Position.UNKNOWN;
        if (c0231j1.i1() && c0231j1.U0().w0()) {
            position = c0231j1.U0().H().l;
        }
        return create(c0231j1.getReference().z0(), position);
    }

    public boolean equals(Object obj) {
        if (obj instanceof MethodPosition) {
            return this.a.equals(((MethodPosition) obj).a);
        }
        return false;
    }

    @Override // com.android.tools.r8.position.Position
    public String getDescription() {
        return toString();
    }

    public String getHolder() {
        return this.a.getHolderClass().getDescriptor();
    }

    public MethodReference getMethod() {
        return this.a;
    }

    public String getName() {
        return this.a.getMethodName();
    }

    public List<String> getParameterTypes() {
        return (List) this.a.getFormalTypes().stream().map(new u1a()).collect(Collectors.toList());
    }

    public String getReturnType() {
        return this.a.getReturnType().getDescriptor();
    }

    public Position getTextPosition() {
        return this.b;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public MethodPosition(C0322w2 c0322w2) {
        this(c0322w2.z0());
    }

    private MethodPosition(MethodReference methodReference, Position position) {
        this.a = methodReference;
        this.b = position;
    }

    public static MethodPosition create(B5 b5) {
        return create(b5.e());
    }

    public static MethodPosition create(MethodReference methodReference) {
        return new MethodPosition(methodReference, Position.UNKNOWN);
    }

    public static MethodPosition create(MethodReference methodReference, Position position) {
        return new MethodPosition(methodReference, position);
    }
}
