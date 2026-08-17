package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1581ga0;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.retrace.RetraceClassElement;
import com.android.tools.r8.retrace.RetraceFieldElement;
import com.android.tools.r8.retrace.RetraceFieldResult;
import com.android.tools.r8.retrace.RetraceMethodElement;
import com.android.tools.r8.retrace.RetraceMethodResult;
import com.android.tools.r8.retrace.RetracedFieldReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.Retracer;
import defpackage.eia;
import defpackage.oyg;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ga0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1581ga0 {
    public static final C1581ga0 b = new C1581ga0(null);
    public final Retracer a;

    public C1581ga0(C1667ha0 c1667ha0) {
        this.a = c1667ha0;
    }

    public final String a(C0245l1 c0245l1, Function function, final Function function2, final Function function3) {
        if (this.a == null) {
            return (String) function.apply(c0245l1);
        }
        FieldReference fieldReferenceZ0 = c0245l1.z0();
        RetraceFieldResult retraceFieldResultRetraceField = this.a.retraceField(fieldReferenceZ0);
        if (retraceFieldResultRetraceField.isEmpty()) {
            retraceFieldResultRetraceField = this.a.retraceClass(fieldReferenceZ0.getHolderClass()).lookupField(fieldReferenceZ0.getFieldName());
        }
        return Wf0.a(retraceFieldResultRetraceField.stream(), new Function() { // from class: qyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1581ga0.a(function3, function2, (RetraceFieldElement) obj);
            }
        });
    }

    public final String b(com.android.tools.r8.graph.I2 i2) {
        eia eiaVar = new eia();
        Function function = new Function() { // from class: fyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetraceClassElement) obj).getRetracedClass().getTypeName();
            }
        };
        Retracer retracer = this.a;
        return retracer == null ? (String) eiaVar.apply(i2) : Wf0.a(retracer.retraceClass(i2.w0()).stream(), function);
    }

    public final String c(C0322w2 c0322w2) {
        return a(c0322w2, new Function() { // from class: cyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0322w2) obj).m0();
            }
        }, new Function() { // from class: dyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetracedMethodReference.KnownRetracedMethodReference) obj).getMethodReference().toSourceString();
            }
        }, new Function() { // from class: eyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1581ga0.b((RetracedMethodReference) obj);
            }
        });
    }

    public final String d(C0245l1 c0245l1) {
        return a(c0245l1, new Function() { // from class: ryg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0245l1) obj).z0().toSourceString();
            }
        }, new Function() { // from class: syg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetracedFieldReference.KnownRetracedFieldReference) obj).getFieldReference().toSourceString();
            }
        }, new Function() { // from class: tyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1581ga0.b((RetracedFieldReference) obj);
            }
        });
    }

    public final String c(C0245l1 c0245l1) {
        return a(c0245l1, new Function() { // from class: zxg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0245l1) obj).z0().toString();
            }
        }, new Function() { // from class: ayg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetracedFieldReference.KnownRetracedFieldReference) obj).getFieldReference().toString();
            }
        }, new Function() { // from class: byg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1581ga0.a((RetracedFieldReference) obj);
            }
        });
    }

    public static /* synthetic */ String b(RetracedMethodReference retracedMethodReference) {
        return retracedMethodReference.getHolderClass().getTypeName() + " " + retracedMethodReference.getMethodName();
    }

    public final String b(C0322w2 c0322w2) {
        return a(c0322w2, new Function() { // from class: kyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0322w2) obj).z0().toString();
            }
        }, new Function() { // from class: lyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetracedMethodReference.KnownRetracedMethodReference) obj).getMethodReference().toString();
            }
        }, new Function() { // from class: myg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1581ga0.a((RetracedMethodReference) obj);
            }
        });
    }

    public static /* synthetic */ String b(RetracedFieldReference retracedFieldReference) {
        return retracedFieldReference.getHolderClass().getDescriptor() + " " + retracedFieldReference.getFieldName();
    }

    public final String b(com.android.tools.r8.graph.X3 x3) {
        if (!(x3 instanceof com.android.tools.r8.graph.F2)) {
            return x3.m0();
        }
        return (String) ((com.android.tools.r8.graph.F2) x3).a(new Function() { // from class: yxg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((I2) obj);
            }
        }, new Function() { // from class: jyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.d((C0245l1) obj);
            }
        }, new Function() { // from class: nyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c((C0322w2) obj);
            }
        });
    }

    public final String a(C0322w2 c0322w2, Function function, final Function function2, final Function function3) {
        Retracer retracer = this.a;
        if (retracer == null) {
            return (String) function.apply(c0322w2);
        }
        RetraceMethodResult retraceMethodResultRetraceMethod = retracer.retraceMethod(c0322w2.z0());
        return Wf0.a(retraceMethodResultRetraceMethod.stream(), new Function() { // from class: uyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C1581ga0.a(function3, function2, (RetraceMethodElement) obj);
            }
        });
    }

    public static /* synthetic */ String a(Function function, Function function2, RetraceMethodElement retraceMethodElement) {
        if (retraceMethodElement.isUnknown()) {
            return (String) function.apply(retraceMethodElement.getRetracedMethod());
        }
        return (String) function2.apply(retraceMethodElement.getRetracedMethod().asKnown());
    }

    public static /* synthetic */ String a(RetracedMethodReference retracedMethodReference) {
        return retracedMethodReference.getHolderClass().getDescriptor() + retracedMethodReference.getMethodName();
    }

    public final String a(com.android.tools.r8.graph.I2 i2) {
        oyg oygVar = new oyg();
        Function function = new Function() { // from class: pyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetraceClassElement) obj).getRetracedClass().getDescriptor();
            }
        };
        Retracer retracer = this.a;
        if (retracer == null) {
            return (String) oygVar.apply(i2);
        }
        return Wf0.a(retracer.retraceClass(i2.w0()).stream(), function);
    }

    public static /* synthetic */ String a(Function function, Function function2, RetraceFieldElement retraceFieldElement) {
        if (retraceFieldElement.isUnknown()) {
            return (String) function.apply(retraceFieldElement.getField());
        }
        return (String) function2.apply(retraceFieldElement.getField().asKnown());
    }

    public static /* synthetic */ String a(RetracedFieldReference retracedFieldReference) {
        return retracedFieldReference.getHolderClass().getDescriptor() + retracedFieldReference.getFieldName();
    }

    public final String a(com.android.tools.r8.graph.X3 x3) {
        if (!(x3 instanceof com.android.tools.r8.graph.F2)) {
            return x3.toString();
        }
        return (String) ((com.android.tools.r8.graph.F2) x3).a(new Function() { // from class: gyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((I2) obj);
            }
        }, new Function() { // from class: hyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.c((C0245l1) obj);
            }
        }, new Function() { // from class: iyg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b((C0322w2) obj);
            }
        });
    }

    public final boolean a() {
        return this == b;
    }
}
