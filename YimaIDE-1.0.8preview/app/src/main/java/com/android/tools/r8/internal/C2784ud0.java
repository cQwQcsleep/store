package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2612sd0;
import com.android.tools.r8.internal.C2698td0;
import com.android.tools.r8.internal.C2784ud0;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetraceClassElement;
import com.android.tools.r8.retrace.RetraceClassResult;
import com.android.tools.r8.retrace.RetraceFieldElement;
import com.android.tools.r8.retrace.RetraceFieldResult;
import com.android.tools.r8.retrace.RetraceFrameElement;
import com.android.tools.r8.retrace.RetraceFrameResult;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceStackTraceElementProxyResult;
import com.android.tools.r8.retrace.RetraceThrownExceptionElement;
import com.android.tools.r8.retrace.RetraceTypeElement;
import com.android.tools.r8.retrace.RetraceTypeResult;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedSingleFrame;
import com.android.tools.r8.retrace.RetracedSourceFile;
import com.android.tools.r8.retrace.RetracedTypeReference;
import com.android.tools.r8.retrace.Retracer;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import com.android.tools.r8.retrace.StackTraceElementProxyRetracer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ud0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2784ud0 implements StackTraceElementProxyRetracer {
    public final Retracer a;

    public C2784ud0(Retracer retracer) {
        this.a = retracer;
    }

    public final N90 a(N90 n90, StackTraceElementProxy stackTraceElementProxy) {
        if (!stackTraceElementProxy.hasFieldOrReturnType()) {
            return n90;
        }
        M90 m90A = n90.a();
        String fieldOrReturnType = stackTraceElementProxy.getFieldOrReturnType();
        if (fieldOrReturnType.equals("void")) {
            return new N90(n90.a.map(new Function() { // from class: bhi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a((C2698td0) obj);
                }
            }), m90A.a);
        }
        final RetraceTypeResult retraceTypeResultRetraceType = this.a.retraceType(Reference.typeFromTypeName(fieldOrReturnType));
        final List list = (List) retraceTypeResultRetraceType.stream().collect(Collectors.toList());
        return new N90(n90.a.flatMap(new Function() { // from class: chi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(list, retraceTypeResultRetraceType, (C2698td0) obj);
            }
        }), m90A.a);
    }

    public final N90 b(N90 n90, StackTraceElementProxy stackTraceElementProxy) {
        if (!stackTraceElementProxy.hasMethodArguments()) {
            return n90;
        }
        Stream<TypeReference> stream = stackTraceElementProxy.getMethodArgumentTypeReferences().stream();
        final Retracer retracer = this.a;
        Objects.requireNonNull(retracer);
        List list = (List) stream.map(new Function() { // from class: tgi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return retracer.retraceType((TypeReference) obj);
            }
        }).collect(Collectors.toList());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ArrayList());
        final List list2 = (List) C2847vL.a(list, arrayList, new BiFunction() { // from class: ugi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return C2784ud0.a((List) obj, (RetraceTypeResult) obj2);
            }
        });
        final boolean z = list2.size() > 1;
        return new N90(n90.a.flatMap(new Function() { // from class: vgi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return list2.stream().map(new Function() { // from class: zgi
                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        return C2784ud0.a(c2698td0, z, (List) obj2);
                    }
                });
            }
        }), n90.a().a);
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxyRetracer
    public final RetraceStackTraceElementProxyResult retrace(StackTraceElementProxy stackTraceElementProxy, RetraceStackTraceContext retraceStackTraceContext) {
        N90 n90 = new N90(Stream.of(new C2698td0(stackTraceElementProxy, null, null, null, null, null, null, -1, false, false, retraceStackTraceContext)), new Supplier() { // from class: ohi
            @Override // java.util.function.Supplier
            public final Object get() {
                return RetraceStackTraceContext.empty();
            }
        });
        if (!stackTraceElementProxy.hasClassName() && !stackTraceElementProxy.hasFieldOrReturnType() && !stackTraceElementProxy.hasMethodArguments()) {
            return n90;
        }
        N90 n90B = b(a(n90, stackTraceElementProxy), stackTraceElementProxy);
        if (!stackTraceElementProxy.hasClassName()) {
            return n90B;
        }
        RetraceClassResult retraceClassResultRetraceClass = this.a.retraceClass(stackTraceElementProxy.getClassReference());
        if (stackTraceElementProxy.hasMethodName()) {
            return a(n90B, stackTraceElementProxy, retraceClassResultRetraceClass, retraceStackTraceContext);
        }
        return stackTraceElementProxy.hasFieldName() ? a(n90B, stackTraceElementProxy, retraceClassResultRetraceClass) : a(n90B, retraceClassResultRetraceClass);
    }

    public final N90 a(N90 n90, final RetraceClassResult retraceClassResult) {
        return new N90(n90.a.flatMap(new Function() { // from class: hhi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(retraceClassResult, (C2698td0) obj);
            }
        }), n90.a().a);
    }

    public static C2698td0 a(RetraceClassResult retraceClassResult, C2698td0 c2698td0, final RetraceThrownExceptionElement retraceThrownExceptionElement) {
        C2612sd0 c2612sd0A = c2698td0.a();
        c2612sd0A.b = retraceThrownExceptionElement.getRetracedClass();
        C2612sd0 c2612sd0A2 = c2612sd0A.a(retraceClassResult.isAmbiguous());
        c2612sd0A2.j = true;
        c2612sd0A2.k = retraceThrownExceptionElement.getContext();
        return c2612sd0A2.a(a(new Supplier() { // from class: xgi
            @Override // java.util.function.Supplier
            public final Object get() {
                return retraceThrownExceptionElement.getSourceFile();
            }
        })).a();
    }

    public final /* synthetic */ C2698td0 b(StackTraceElementProxy stackTraceElementProxy, C2698td0 c2698td0, RetraceFrameResult retraceFrameResult, RetraceFrameElement retraceFrameElement, RetracedSingleFrame retracedSingleFrame) {
        return a(stackTraceElementProxy, c2698td0, retraceFrameResult, retraceFrameElement, retracedSingleFrame);
    }

    public final /* synthetic */ C2698td0 b(C2698td0 c2698td0, RetraceFieldResult retraceFieldResult, RetraceFieldElement retraceFieldElement) {
        return a(c2698td0, retraceFieldResult, retraceFieldElement);
    }

    public final /* synthetic */ C2698td0 b(RetraceClassResult retraceClassResult, C2698td0 c2698td0, RetraceThrownExceptionElement retraceThrownExceptionElement) {
        return a(retraceClassResult, c2698td0, retraceThrownExceptionElement);
    }

    public final N90 a(N90 n90, final StackTraceElementProxy stackTraceElementProxy, final RetraceClassResult retraceClassResult, final RetraceStackTraceContext retraceStackTraceContext) {
        final C1975l7 c1975l7 = new C1975l7(RetraceStackTraceContext.empty());
        M90 m90A = n90.a();
        m90A.a = new Supplier() { // from class: rgi
            @Override // java.util.function.Supplier
            public final Object get() {
                return (RetraceStackTraceContext) c1975l7.a();
            }
        };
        return new N90(n90.a.flatMap(new Function() { // from class: sgi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(retraceClassResult, retraceStackTraceContext, stackTraceElementProxy, c1975l7, (C2698td0) obj);
            }
        }), m90A.a);
    }

    public final /* synthetic */ Stream a(final RetraceClassResult retraceClassResult, final RetraceStackTraceContext retraceStackTraceContext, final StackTraceElementProxy stackTraceElementProxy, final C1975l7 c1975l7, final C2698td0 c2698td0) {
        OptionalInt optionalIntEmpty;
        if (stackTraceElementProxy.hasLineNumber()) {
            optionalIntEmpty = OptionalInt.of(stackTraceElementProxy.getLineNumber());
        } else {
            optionalIntEmpty = OptionalInt.empty();
        }
        final RetraceFrameResult retraceFrameResultLookupFrame = retraceClassResult.lookupFrame(retraceStackTraceContext, optionalIntEmpty, stackTraceElementProxy.getMethodName());
        if (retraceFrameResultLookupFrame.isEmpty()) {
            return retraceClassResult.stream().map(new Function() { // from class: jhi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(c2698td0, retraceClassResult, stackTraceElementProxy, (RetraceClassElement) obj);
                }
            });
        }
        return retraceFrameResultLookupFrame.stream().flatMap(new Function() { // from class: khi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c1975l7, retraceStackTraceContext, stackTraceElementProxy, c2698td0, retraceFrameResultLookupFrame, (RetraceFrameElement) obj);
            }
        });
    }

    public final C2698td0 a(C2698td0 c2698td0, RetraceClassResult retraceClassResult, final StackTraceElementProxy stackTraceElementProxy, final RetraceClassElement retraceClassElement) {
        C2612sd0 c2612sd0A = c2698td0.a();
        c2612sd0A.j = true;
        C2612sd0 c2612sd0A2 = c2612sd0A.a(retraceClassResult.isAmbiguous());
        c2612sd0A2.b = retraceClassElement.getRetracedClass();
        return c2612sd0A2.a(stackTraceElementProxy.hasLineNumber(), new Consumer() { // from class: dhi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2784ud0.a(stackTraceElementProxy, (C2612sd0) obj);
            }
        }).a(a(new Supplier() { // from class: ehi
            @Override // java.util.function.Supplier
            public final Object get() {
                return retraceClassElement.getSourceFile();
            }
        })).a();
    }

    public static void a(StackTraceElementProxy stackTraceElementProxy, C2612sd0 c2612sd0) {
        c2612sd0.h = stackTraceElementProxy.getLineNumber();
    }

    public final /* synthetic */ Stream a(C1975l7 c1975l7, RetraceStackTraceContext retraceStackTraceContext, final StackTraceElementProxy stackTraceElementProxy, final C2698td0 c2698td0, final RetraceFrameResult retraceFrameResult, final RetraceFrameElement retraceFrameElement) {
        c1975l7.a(retraceFrameElement.getRetraceStackTraceContext());
        return retraceFrameElement.streamRewritten(retraceStackTraceContext).map(new Function() { // from class: mhi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b(stackTraceElementProxy, c2698td0, retraceFrameResult, retraceFrameElement, (RetracedSingleFrame) obj);
            }
        });
    }

    public static C2698td0 a(final StackTraceElementProxy stackTraceElementProxy, C2698td0 c2698td0, RetraceFrameResult retraceFrameResult, final RetraceFrameElement retraceFrameElement, RetracedSingleFrame retracedSingleFrame) {
        boolean z = retracedSingleFrame.getIndex() == 0;
        final RetracedMethodReference methodReference = retracedSingleFrame.getMethodReference();
        C2612sd0 c2612sd0A = c2698td0.a();
        c2612sd0A.b = methodReference.getHolderClass();
        c2612sd0A.c = methodReference;
        C2612sd0 c2612sd0A2 = c2612sd0A.a(retraceFrameResult.isAmbiguous());
        c2612sd0A2.j = z;
        c2612sd0A2.k = retraceFrameElement.getRetraceStackTraceContext();
        return c2612sd0A2.a(stackTraceElementProxy.hasLineNumber(), new Consumer() { // from class: pgi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2784ud0.a(methodReference, stackTraceElementProxy, (C2612sd0) obj);
            }
        }).a(a(new Supplier() { // from class: ahi
            @Override // java.util.function.Supplier
            public final Object get() {
                return retraceFrameElement.getSourceFile(methodReference);
            }
        })).a();
    }

    public static void a(RetracedMethodReference retracedMethodReference, StackTraceElementProxy stackTraceElementProxy, C2612sd0 c2612sd0) {
        c2612sd0.h = retracedMethodReference.getOriginalPositionOrDefault(stackTraceElementProxy.getLineNumber());
    }

    public final N90 a(N90 n90, final StackTraceElementProxy stackTraceElementProxy, final RetraceClassResult retraceClassResult) {
        return new N90(n90.a.flatMap(new Function() { // from class: ihi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(retraceClassResult, stackTraceElementProxy, (C2698td0) obj);
            }
        }), n90.a().a);
    }

    public final /* synthetic */ Stream a(RetraceClassResult retraceClassResult, StackTraceElementProxy stackTraceElementProxy, final C2698td0 c2698td0) {
        final RetraceFieldResult retraceFieldResultLookupField = retraceClassResult.lookupField(stackTraceElementProxy.getFieldName());
        return retraceFieldResultLookupField.stream().map(new Function() { // from class: fhi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b(c2698td0, retraceFieldResultLookupField, (RetraceFieldElement) obj);
            }
        });
    }

    public static C2698td0 a(C2698td0 c2698td0, RetraceFieldResult retraceFieldResult, final RetraceFieldElement retraceFieldElement) {
        C2612sd0 c2612sd0A = c2698td0.a();
        c2612sd0A.b = retraceFieldElement.getField().getHolderClass();
        c2612sd0A.d = retraceFieldElement.getField();
        C2612sd0 c2612sd0A2 = c2612sd0A.a(retraceFieldResult.isAmbiguous());
        c2612sd0A2.j = true;
        return c2612sd0A2.a(a(new Supplier() { // from class: ygi
            @Override // java.util.function.Supplier
            public final Object get() {
                return retraceFieldElement.getSourceFile();
            }
        })).a();
    }

    public static Consumer a(final Supplier supplier) {
        return new Consumer() { // from class: qgi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2784ud0.a(supplier, (C2612sd0) obj);
            }
        };
    }

    public static void a(Supplier supplier, C2612sd0 c2612sd0) {
        if (c2612sd0.a.hasSourceFile()) {
            RetracedSourceFile retracedSourceFile = (RetracedSourceFile) supplier.get();
            if (C2612sd0.l || retracedSourceFile != null) {
                c2612sd0.g = retracedSourceFile;
            } else {
                x1f.a();
            }
        }
    }

    public final C2698td0 a(C2698td0 c2698td0) {
        C1496fa0 c1496fa0 = new C1496fa0(null);
        boolean z = c2698td0.j;
        C2612sd0 c2612sd0A = c2698td0.a();
        c2612sd0A.e = c1496fa0;
        return c2612sd0A.a(z).a();
    }

    public final /* synthetic */ Stream a(List list, final RetraceTypeResult retraceTypeResult, final C2698td0 c2698td0) {
        return list.stream().map(new Function() { // from class: ghi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c2698td0, retraceTypeResult, (RetraceTypeElement) obj);
            }
        });
    }

    public final C2698td0 a(C2698td0 c2698td0, RetraceTypeResult retraceTypeResult, RetraceTypeElement retraceTypeElement) {
        RetracedTypeReference type = retraceTypeElement.getType();
        boolean zIsAmbiguous = retraceTypeResult.isAmbiguous();
        C2612sd0 c2612sd0A = c2698td0.a();
        c2612sd0A.e = type;
        return c2612sd0A.a(zIsAmbiguous).a();
    }

    public static /* synthetic */ List a(final List list, RetraceTypeResult retraceTypeResult) {
        final ArrayList arrayList = new ArrayList();
        retraceTypeResult.forEach(new Consumer() { // from class: wgi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.forEach(new Consumer() { // from class: lhi
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        C2784ud0.a(retraceTypeElement, list, (List) obj2);
                    }
                });
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void a(RetraceTypeElement retraceTypeElement, List list, List list2) {
        ArrayList arrayList = new ArrayList(list2);
        arrayList.add(retraceTypeElement.getType());
        list.add(arrayList);
    }

    public static C2698td0 a(C2698td0 c2698td0, boolean z, List list) {
        C2612sd0 c2612sd0A = c2698td0.a();
        c2612sd0A.f = list;
        return c2612sd0A.a(z).a();
    }

    public final Stream a(final RetraceClassResult retraceClassResult, final C2698td0 c2698td0) {
        return retraceClassResult.lookupThrownException(c2698td0.l).stream().map(new Function() { // from class: nhi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.b(retraceClassResult, c2698td0, (RetraceThrownExceptionElement) obj);
            }
        });
    }
}
