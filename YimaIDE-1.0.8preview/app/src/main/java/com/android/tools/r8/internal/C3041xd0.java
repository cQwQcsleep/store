package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C3041xd0;
import com.android.tools.r8.internal.C3210zd0;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetraceStackTraceElementProxy;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedFieldReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedTypeReference;
import defpackage.f63;
import java.util.ArrayList;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3041xd0 {
    public static final /* synthetic */ boolean k = true;
    public final String a;
    public final ArrayList b = new ArrayList();
    public C2955wd0 c;
    public C3124yd0 d;
    public C3124yd0 e;
    public C3124yd0 f;
    public C3124yd0 g;
    public C3124yd0 h;
    public C3124yd0 i;
    public int j;

    public C3041xd0(String str) {
        C2955wd0 c2955wd0 = C2955wd0.e;
        this.c = c2955wd0;
        this.d = c2955wd0;
        this.e = c2955wd0;
        this.f = c2955wd0;
        this.g = c2955wd0;
        this.h = c2955wd0;
        this.i = c2955wd0;
        this.j = -1;
        this.a = str;
    }

    public static /* synthetic */ String a(RetraceStackTraceElementProxy retraceStackTraceElementProxy, C3210zd0 c3210zd0, Boolean bool) {
        if (!retraceStackTraceElementProxy.hasRetracedField()) {
            return c3210zd0.getFieldName();
        }
        RetracedFieldReference retracedField = retraceStackTraceElementProxy.getRetracedField();
        if (!bool.booleanValue() || retracedField.isUnknown()) {
            return retracedField.getFieldName();
        }
        return retracedField.asKnown().getFieldType().getTypeName() + " " + retracedField.getFieldName();
    }

    public static /* synthetic */ String b(RetraceStackTraceElementProxy retraceStackTraceElementProxy, C3210zd0 c3210zd0, Boolean bool) {
        if (retraceStackTraceElementProxy.hasRetracedFieldOrReturnType()) {
            return retraceStackTraceElementProxy.getRetracedFieldOrReturnType().isVoid() ? "void" : retraceStackTraceElementProxy.getRetracedFieldOrReturnType().getTypeName();
        }
        return c3210zd0.getFieldOrReturnType();
    }

    public static String c(RetraceStackTraceElementProxy retraceStackTraceElementProxy, C3210zd0 c3210zd0, Boolean bool) {
        if (!retraceStackTraceElementProxy.hasRetracedMethodArguments()) {
            return c3210zd0.getMethodArguments();
        }
        if (retraceStackTraceElementProxy.getRetracedMethodArguments().isEmpty()) {
            return XmlPullParser.NO_NAMESPACE;
        }
        StringBuilder sb = new StringBuilder();
        String methodArguments = c3210zd0.getMethodArguments();
        boolean z = false;
        int iIndexOf = 0;
        for (RetracedTypeReference retracedTypeReference : retraceStackTraceElementProxy.getRetracedMethodArguments()) {
            if (z) {
                sb.append(",");
            }
            char[] cArr = Wf0.a;
            int length = iIndexOf;
            while (true) {
                if (length >= methodArguments.length()) {
                    length = methodArguments.length();
                    break;
                }
                if (!Wf0.b(methodArguments.charAt(length))) {
                    break;
                }
                length++;
            }
            int i = length - iIndexOf;
            sb.append(" ".repeat(i));
            sb.append(retracedTypeReference.getTypeName());
            iIndexOf = methodArguments.indexOf(44, iIndexOf + i) + 1;
            z = true;
        }
        return sb.toString();
    }

    public static String d(RetraceStackTraceElementProxy retraceStackTraceElementProxy, C3210zd0 c3210zd0, Boolean bool) {
        if (!retraceStackTraceElementProxy.hasRetracedMethod()) {
            return c3210zd0.getMethodName();
        }
        RetracedMethodReference retracedMethod = retraceStackTraceElementProxy.getRetracedMethod();
        boolean zBooleanValue = bool.booleanValue();
        HashSet hashSet = V90.a;
        StringBuilder sb = new StringBuilder();
        if (!zBooleanValue || retracedMethod.isUnknown()) {
            sb.append(retracedMethod.getMethodName());
            return sb.toString();
        }
        if (!V90.b && !retracedMethod.isKnown()) {
            x1f.a();
            return null;
        }
        RetracedMethodReference.KnownRetracedMethodReference knownRetracedMethodReferenceAsKnown = retracedMethod.asKnown();
        sb.append(knownRetracedMethodReferenceAsKnown.isVoid() ? "void" : knownRetracedMethodReferenceAsKnown.getReturnType().getTypeName());
        sb.append(" ");
        sb.append(retracedMethod.getMethodName());
        sb.append("(");
        boolean z = false;
        for (TypeReference typeReference : knownRetracedMethodReferenceAsKnown.getFormalTypes()) {
            if (z) {
                sb.append(",");
            }
            sb.append(typeReference.getTypeName());
            z = true;
        }
        sb.append(")");
        return sb.toString();
    }

    public final void e(int i, int i2) {
        C3124yd0 c3124yd0 = new C3124yd0(i, i2, new InterfaceC1938ki0() { // from class: lsi
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C3041xd0.e((RetraceStackTraceElementProxy) obj, (C3210zd0) obj2, (Boolean) obj3);
            }
        });
        this.e = c3124yd0;
        this.b.add(c3124yd0);
    }

    public static /* synthetic */ String e(RetraceStackTraceElementProxy retraceStackTraceElementProxy, C3210zd0 c3210zd0, Boolean bool) {
        return retraceStackTraceElementProxy.hasSourceFile() ? retraceStackTraceElementProxy.getSourceFile() : c3210zd0.getSourceFile();
    }

    public final void b(int i, int i2) {
        C3124yd0 c3124yd0 = new C3124yd0(i, i2, new InterfaceC1938ki0() { // from class: fsi
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C3041xd0.b((RetraceStackTraceElementProxy) obj, (C3210zd0) obj2, (Boolean) obj3);
            }
        });
        this.h = c3124yd0;
        this.b.add(c3124yd0);
    }

    public final void a(int i, int i2, final boolean z) {
        C3124yd0 c3124yd0 = new C3124yd0(i, i2, new InterfaceC1938ki0() { // from class: hsi
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C3041xd0.a(z, (RetraceStackTraceElementProxy) obj, (C3210zd0) obj2, (Boolean) obj3);
            }
        });
        this.f = c3124yd0;
        this.b.add(c3124yd0);
    }

    public static /* synthetic */ String a(boolean z, RetraceStackTraceElementProxy retraceStackTraceElementProxy, C3210zd0 c3210zd0, Boolean bool) {
        if (retraceStackTraceElementProxy.hasLineNumber() && ((c3210zd0.hasLineNumber() && c3210zd0.getLineNumber() > -1) || !retraceStackTraceElementProxy.isAmbiguous() || bool.booleanValue())) {
            int lineNumber = retraceStackTraceElementProxy.getLineNumber();
            String str = XmlPullParser.NO_NAMESPACE;
            if (lineNumber <= 0) {
                return XmlPullParser.NO_NAMESPACE;
            }
            if (z) {
                str = ":";
            }
            return str + retraceStackTraceElementProxy.getLineNumber();
        }
        return c3210zd0.a();
    }

    public final void a(int i, int i2) {
        C3124yd0 c3124yd0 = new C3124yd0(i, i2, new InterfaceC1938ki0() { // from class: ksi
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C3041xd0.a((RetraceStackTraceElementProxy) obj, (C3210zd0) obj2, (Boolean) obj3);
            }
        });
        this.g = c3124yd0;
        this.b.add(c3124yd0);
    }

    public static /* synthetic */ String a(EnumC2869vd0 enumC2869vd0, RetraceStackTraceElementProxy retraceStackTraceElementProxy, C3210zd0 c3210zd0, Boolean bool) {
        if (!k && !retraceStackTraceElementProxy.hasRetracedClass()) {
            x1f.a();
            return null;
        }
        RetracedClassReference retracedClass = retraceStackTraceElementProxy.getRetracedClass();
        if (enumC2869vd0 == EnumC2869vd0.b) {
            return retracedClass.getBinaryName();
        }
        return retracedClass.getTypeName();
    }

    public final void a(int i, int i2, final EnumC2869vd0 enumC2869vd0) {
        if (this.j < i) {
            this.j = i;
            C2955wd0 c2955wd0 = new C2955wd0(i, i2, new InterfaceC1938ki0() { // from class: isi
                @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                public final Object a(Object obj, Object obj2, Object obj3) {
                    return C3041xd0.a(enumC2869vd0, (RetraceStackTraceElementProxy) obj, (C3210zd0) obj2, (Boolean) obj3);
                }
            }, enumC2869vd0);
            this.c = c2955wd0;
            this.b.add(c2955wd0);
            return;
        }
        f63.a("Parsing has to be incremental in the order of characters.");
    }

    public final void c(int i, int i2) {
        C3124yd0 c3124yd0 = new C3124yd0(i, i2, new InterfaceC1938ki0() { // from class: gsi
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C3041xd0.c((RetraceStackTraceElementProxy) obj, (C3210zd0) obj2, (Boolean) obj3);
            }
        });
        this.i = c3124yd0;
        this.b.add(c3124yd0);
    }

    public final void d(int i, int i2) {
        C3124yd0 c3124yd0 = new C3124yd0(i, i2, new InterfaceC1938ki0() { // from class: jsi
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return C3041xd0.d((RetraceStackTraceElementProxy) obj, (C3210zd0) obj2, (Boolean) obj3);
            }
        });
        this.d = c3124yd0;
        this.b.add(c3124yd0);
    }
}
