package com.android.tools.r8.tracereferences;

import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.PackageReference;
import com.android.tools.r8.tracereferences.TraceReferencesConsumer;
import defpackage.ykg;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class a {
    public static final /* synthetic */ boolean b = true;
    public final StringBuilder a = new StringBuilder();

    public abstract void a(List list);

    public final void a(Set set, Set set2, Map map, Map map2) {
        ArrayList<TraceReferencesConsumer.TracedClass> arrayList = new ArrayList(set);
        arrayList.sort(Comparator.comparing(new Function() { // from class: t9g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((TraceReferencesConsumer.TracedClass) obj).getReference().getTypeName();
            }
        }));
        for (TraceReferencesConsumer.TracedClass tracedClass : arrayList) {
            ClassReference reference = tracedClass.getReference();
            Set set3 = Collections.EMPTY_SET;
            Set<TraceReferencesConsumer.TracedMethod> set4 = (Set) map2.getOrDefault(reference, set3);
            Set set5 = (Set) map.getOrDefault(tracedClass.getReference(), set3);
            if (!tracedClass.isMissingDefinition()) {
                b(tracedClass);
                ArrayList<TraceReferencesConsumer.TracedMethod> arrayList2 = new ArrayList(set4.size());
                for (TraceReferencesConsumer.TracedMethod tracedMethod : set4) {
                    if (!tracedMethod.isMissingDefinition()) {
                        if (!b && tracedMethod.getAccessFlags() == null) {
                            x1f.a();
                            return;
                        }
                        arrayList2.add(tracedMethod);
                    }
                }
                arrayList2.sort(Comparator.comparing(new Function() { // from class: z9g
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((TraceReferencesConsumer.TracedMethod) obj).getReference().toString();
                    }
                }));
                for (TraceReferencesConsumer.TracedMethod tracedMethod2 : arrayList2) {
                    b bVar = (b) this;
                    if (!tracedMethod2.getReference().getMethodName().equals("<clinit>")) {
                        bVar.a.append("  ");
                        if (((TraceReferencesConsumer.MethodAccessFlags) tracedMethod2.getAccessFlags()).isPublic()) {
                            bVar.a.append("public ");
                        } else if (((TraceReferencesConsumer.MethodAccessFlags) tracedMethod2.getAccessFlags()).isPrivate()) {
                            bVar.a.append("private ");
                        } else if (((TraceReferencesConsumer.MethodAccessFlags) tracedMethod2.getAccessFlags()).isProtected()) {
                            bVar.a.append("protected ");
                        }
                        if (((TraceReferencesConsumer.MethodAccessFlags) tracedMethod2.getAccessFlags()).isStatic()) {
                            bVar.a.append("static ");
                        }
                        MethodReference reference2 = tracedMethod2.getReference();
                        if (reference2.getMethodName().equals("<init>")) {
                            bVar.a.append("<init>");
                        } else {
                            bVar.a.append(reference2.getReturnType() != null ? reference2.getReturnType().getTypeName() : "void");
                            bVar.a.append(" ");
                            bVar.a.append(reference2.getMethodName());
                        }
                        bVar.a(tracedMethod2.getReference());
                        bVar.a.append(Wf0.b(";"));
                    }
                }
                ArrayList<TraceReferencesConsumer.TracedField> arrayList3 = new ArrayList(set5);
                arrayList3.sort(Comparator.comparing(new Function() { // from class: dag
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((TraceReferencesConsumer.TracedField) obj).getReference().toString();
                    }
                }));
                for (TraceReferencesConsumer.TracedField tracedField : arrayList3) {
                    if (!tracedField.isMissingDefinition()) {
                        b(tracedField);
                    }
                }
                ((b) this).a.append(Wf0.b("}"));
            }
        }
        a((List) set2.stream().map(new Function() { // from class: hag
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((PackageReference) obj).getPackageName();
            }
        }).sorted().collect(Collectors.toList()));
    }

    public abstract void b(TraceReferencesConsumer.TracedClass tracedClass);

    public abstract void b(TraceReferencesConsumer.TracedField tracedField);

    public final void b(String str) {
        this.a.append(Wf0.b(str));
    }

    public final void a(String str) {
        this.a.append(str);
    }

    public final void a(MethodReference methodReference) {
        Wf0.a(this.a, C2847vL.a((Collection) methodReference.getFormalTypes(), (Function) new ykg()), ",", Wf0.a.b);
    }
}
