package com.android.tools.r8.references;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Wf0;
import defpackage.u1a;
import defpackage.ykg;
import java.util.List;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MethodReference {
    static final /* synthetic */ boolean e = true;
    private final ClassReference a;
    private final String b;
    private final List c;
    private final TypeReference d;

    public MethodReference(ClassReference classReference, String str, AbstractC0551Hu abstractC0551Hu, TypeReference typeReference) {
        boolean z = e;
        if (!z && classReference == null) {
            x1f.a();
            throw null;
        }
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        this.a = classReference;
        this.b = str;
        this.c = abstractC0551Hu;
        this.d = typeReference;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MethodReference)) {
            return false;
        }
        MethodReference methodReference = (MethodReference) obj;
        return this.a.equals(methodReference.a) && this.b.equals(methodReference.b) && this.c.equals(methodReference.c) && Objects.equals(this.d, methodReference.d);
    }

    public List<TypeReference> getFormalTypes() {
        return this.c;
    }

    public ClassReference getHolderClass() {
        return this.a;
    }

    public String getMethodDescriptor() {
        return Wf0.a(XmlPullParser.NO_NAMESPACE, getFormalTypes(), new u1a(), Wf0.a.b) + (getReturnType() == null ? "V" : getReturnType().getDescriptor());
    }

    public String getMethodName() {
        return this.b;
    }

    public TypeReference getReturnType() {
        return this.d;
    }

    public int hashCode() {
        return Objects.hash(this.a, this.b, this.c, this.d);
    }

    public String toSourceString() {
        StringBuilder sb = new StringBuilder();
        TypeReference typeReference = this.d;
        sb.append(typeReference == null ? "void" : typeReference.getTypeName());
        sb.append(" ");
        sb.append(this.a.getTypeName());
        sb.append(".");
        sb.append(this.b);
        sb.append(Wf0.a(", ", getFormalTypes(), new ykg(), Wf0.a.b));
        return sb.toString();
    }

    public String toString() {
        return getHolderClass() + getMethodName() + getMethodDescriptor();
    }
}
