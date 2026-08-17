package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C90;
import com.android.tools.r8.internal.D90;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.retrace.RetraceClassElement;
import com.android.tools.r8.retrace.RetraceFrameElement;
import com.android.tools.r8.retrace.RetraceInvalidRewriteFrameDiagnostics;
import com.android.tools.r8.retrace.RetraceResult;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetracedClassMemberReference;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedSourceFile;
import defpackage.a71;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C90 implements RetraceFrameElement {
    public final AbstractC1241ca0 a;
    public final E90 b;
    public final C3007x90 c;
    public final List d;
    public final Optional e;
    public final OptionalInt f;
    public final C1667ha0 g;

    public C90(E90 e90, C3007x90 c3007x90, AbstractC1241ca0 abstractC1241ca0, List list, Optional optional, OptionalInt optionalInt, C1667ha0 c1667ha0) {
        this.a = abstractC1241ca0;
        this.b = e90;
        this.c = c3007x90;
        this.d = list;
        this.e = optional;
        this.f = optionalInt;
        this.g = c1667ha0;
    }

    public static C3331k.b a(Object obj) {
        return ((D90) obj).a;
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final void forEach(Consumer consumer) {
        List list = this.d;
        if (list == null || list.isEmpty()) {
            consumer.accept(new C1325da0(this, this.a, 0));
            return;
        }
        consumer.accept(new C1325da0(this, this.a, 0));
        Iterator it = getOuterFrames().iterator();
        int i = 1;
        while (it.hasNext()) {
            consumer.accept(new C1325da0(this, (AbstractC1241ca0) it.next(), i));
            i++;
        }
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final void forEachRewritten(Consumer consumer) {
        List listA;
        L90 l90;
        K90 k90 = this.b.d;
        boolean zG = false;
        if (k90 == null || (listA = C2847vL.a((Collection) this.d, new Function() { // from class: b71
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C90.a((D90) obj);
            }
        })) == null || listA.isEmpty()) {
            l90 = L90.b;
        } else {
            Iterator it = listA.iterator();
            int i = 0;
            while (it.hasNext()) {
                for (C2351pa0 c2351pa0 : ((C3331k.b) it.next()).k()) {
                    Iterator it2 = c2351pa0.a.iterator();
                    do {
                        if (!it2.hasNext()) {
                            Iterator it3 = c2351pa0.b.iterator();
                            while (it3.hasNext()) {
                                i += ((C2179na0) it3.next()).a;
                            }
                            break;
                        }
                    } while (((C2265oa0) it2.next()).a.equals(k90.a));
                }
            }
            l90 = new L90(i);
        }
        int i2 = l90.a;
        List list = this.d;
        if (i2 > ((list == null || list.isEmpty()) ? 1 : this.d.size())) {
            this.g.b.warning(RetraceInvalidRewriteFrameDiagnostics.create(i2, this.a.a().toString()));
            i2 = 0;
        }
        AbstractC1241ca0 abstractC1241ca0 = this.a;
        int i3 = 0;
        for (AbstractC1241ca0 abstractC1241ca1 : getOuterFrames()) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                consumer.accept(new C1325da0(this, abstractC1241ca0, i3));
                i3++;
            }
            abstractC1241ca0 = abstractC1241ca1;
            i2 = i4;
        }
        if (i2 <= 0) {
            if (!this.e.isPresent()) {
                List list2 = this.d;
                if (list2 != null && !list2.isEmpty()) {
                    for (com.android.tools.r8.naming.mappinginformation.e eVar : ((D90) C2847vL.b(this.d)).a.i) {
                        if (eVar.l() || (eVar instanceof GV)) {
                            zG = true;
                            break;
                        }
                    }
                }
            } else {
                zG = ((com.android.tools.r8.naming.V) this.e.get()).g();
            }
            if (zG) {
                return;
            }
            consumer.accept(new C1325da0(this, abstractC1241ca0, i3));
        }
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final RetraceClassElement getClassElement() {
        return this.c;
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final List getOuterFrames() {
        if (this.d == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < this.d.size(); i++) {
            D90 d90 = (D90) this.d.get(i);
            C3331k.b bVar = d90.a;
            ClassReference classReference = this.c.b.a;
            HashSet hashSet = V90.a;
            arrayList.add(this.b.a(V90.a(bVar.c, classReference), d90, this.f));
        }
        return arrayList;
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement, com.android.tools.r8.retrace.RetraceElement
    public final RetraceResult getParentResult() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final RetraceStackTraceContext getRetraceStackTraceContext() {
        boolean zM;
        List list = this.d;
        if (list != null && !list.isEmpty() && this.f.isPresent()) {
            if (this.e.isPresent()) {
                Iterator it = ((com.android.tools.r8.naming.V) this.e.get()).e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        zM = false;
                        break;
                    }
                    com.android.tools.r8.naming.mappinginformation.e eVar = (com.android.tools.r8.naming.mappinginformation.e) it.next();
                    eVar.getClass();
                    if (eVar instanceof GV) {
                        zM = true;
                        break;
                    }
                }
            } else {
                List list2 = this.d;
                if (list2 == null || list2.isEmpty()) {
                    zM = false;
                    break;
                }
                zM = ((D90) C2847vL.b(this.d)).a.m();
            }
            if (zM) {
                OptionalInt.empty();
                return new K90(null, this.f);
            }
        }
        return RetraceStackTraceContext.empty();
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final RetracedSourceFile getSourceFile(RetracedClassMemberReference retracedClassMemberReference) {
        RetracedClassReference holderClass = retracedClassMemberReference.getHolderClass();
        C1667ha0 c1667ha0 = this.b.c;
        HashSet hashSet = V90.a;
        return new C1410ea0(holderClass, c1667ha0.a.b(holderClass.getClassReference().getTypeName()));
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final RetracedMethodReference getTopFrame() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceElement
    public final boolean isCompilerSynthesized() {
        boolean zG;
        if (getOuterFrames().isEmpty()) {
            if (this.e.isPresent()) {
                zG = ((com.android.tools.r8.naming.V) this.e.get()).g();
            } else {
                List list = this.d;
                if (list == null || list.isEmpty()) {
                    zG = false;
                } else {
                    for (com.android.tools.r8.naming.mappinginformation.e eVar : ((D90) C2847vL.b(this.d)).a.i) {
                        if (eVar.l() || (eVar instanceof GV)) {
                            zG = true;
                        }
                    }
                    zG = false;
                }
            }
            if (zG) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final boolean isUnknown() {
        AbstractC1241ca0 abstractC1241ca0 = this.a;
        abstractC1241ca0.getClass();
        return !(abstractC1241ca0 instanceof C1072aa0);
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final Stream stream() {
        Stream.Builder builder = Stream.builder();
        Objects.requireNonNull(builder);
        forEach(new a71(builder));
        return builder.build();
    }

    @Override // com.android.tools.r8.retrace.RetraceFrameElement
    public final Stream streamRewritten(RetraceStackTraceContext retraceStackTraceContext) {
        Stream.Builder builder = Stream.builder();
        Objects.requireNonNull(builder);
        forEachRewritten(new a71(builder));
        return builder.build();
    }
}
