package com.android.tools.r8.tracereferences;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.diagnostic.DefinitionClassContext;
import com.android.tools.r8.diagnostic.DefinitionFieldContext;
import com.android.tools.r8.diagnostic.DefinitionMethodContext;
import com.android.tools.r8.diagnostic.internal.l;
import com.android.tools.r8.diagnostic.internal.m;
import com.android.tools.r8.diagnostic.internal.n;
import com.android.tools.r8.diagnostic.internal.o;
import com.android.tools.r8.diagnostic.internal.p;
import com.android.tools.r8.diagnostic.internal.q;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.PackageReference;
import com.android.tools.r8.tracereferences.TraceReferencesCheckConsumer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TraceReferencesCheckConsumer extends TraceReferencesConsumer.ForwardingConsumer {
    private final ConcurrentHashMap c;
    private final ConcurrentHashMap d;
    private final ConcurrentHashMap e;

    public TraceReferencesCheckConsumer(TraceReferencesConsumer traceReferencesConsumer) {
        super(traceReferencesConsumer);
        this.c = new ConcurrentHashMap();
        this.d = new ConcurrentHashMap();
        this.e = new ConcurrentHashMap();
    }

    private m a() {
        boolean z = m.c;
        final l lVar = new l();
        this.c.forEach(new BiConsumer() { // from class: qie
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TraceReferencesCheckConsumer.a(lVar, (ClassReference) obj, (Map) obj2);
            }
        });
        this.d.forEach(new BiConsumer() { // from class: rie
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TraceReferencesCheckConsumer.a(lVar, (FieldReference) obj, (Map) obj2);
            }
        });
        this.e.forEach(new BiConsumer() { // from class: eie
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TraceReferencesCheckConsumer.a(lVar, (MethodReference) obj, (Map) obj2);
            }
        });
        return new m(lVar.a.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Map map, DefinitionClassContext definitionClassContext) {
        map.put(definitionClassContext.getClassReference(), definitionClassContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Map map, DefinitionClassContext definitionClassContext) {
        map.put(definitionClassContext.getClassReference(), definitionClassContext);
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ForwardingConsumer, com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public void acceptField(TraceReferencesConsumer.TracedField tracedField, DiagnosticsHandler diagnosticsHandler) {
        super.acceptField(tracedField, diagnosticsHandler);
        if (tracedField.isMissingDefinition()) {
            final Map map = (Map) this.d.computeIfAbsent(tracedField.getReference(), new Function() { // from class: fie
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TraceReferencesCheckConsumer.a((FieldReference) obj);
                }
            });
            com.android.tools.r8.diagnostic.internal.d.a(tracedField.getReferencedFromContext(), new Consumer() { // from class: gie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.a(map, (DefinitionClassContext) obj);
                }
            }, new Consumer() { // from class: hie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.a(map, (DefinitionFieldContext) obj);
                }
            }, new Consumer() { // from class: iie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.a(map, (DefinitionMethodContext) obj);
                }
            });
        }
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ForwardingConsumer, com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public void acceptMethod(TraceReferencesConsumer.TracedMethod tracedMethod, DiagnosticsHandler diagnosticsHandler) {
        super.acceptMethod(tracedMethod, diagnosticsHandler);
        if (tracedMethod.isMissingDefinition()) {
            final Map map = (Map) this.e.computeIfAbsent(tracedMethod.getReference(), new Function() { // from class: die
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TraceReferencesCheckConsumer.a((MethodReference) obj);
                }
            });
            com.android.tools.r8.diagnostic.internal.d.a(tracedMethod.getReferencedFromContext(), new Consumer() { // from class: jie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.b(map, (DefinitionClassContext) obj);
                }
            }, new Consumer() { // from class: kie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.b(map, (DefinitionFieldContext) obj);
                }
            }, new Consumer() { // from class: lie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.b(map, (DefinitionMethodContext) obj);
                }
            });
        }
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ForwardingConsumer, com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public void acceptPackage(PackageReference packageReference, DiagnosticsHandler diagnosticsHandler) {
        super.acceptPackage(packageReference, diagnosticsHandler);
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ForwardingConsumer, com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public void acceptType(TraceReferencesConsumer.TracedClass tracedClass, DiagnosticsHandler diagnosticsHandler) {
        super.acceptType(tracedClass, diagnosticsHandler);
        if (tracedClass.isMissingDefinition()) {
            final Map map = (Map) this.c.computeIfAbsent(tracedClass.getReference(), new Function() { // from class: mie
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TraceReferencesCheckConsumer.a((ClassReference) obj);
                }
            });
            com.android.tools.r8.diagnostic.internal.d.a(tracedClass.getReferencedFromContext(), new Consumer() { // from class: nie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.c(map, (DefinitionClassContext) obj);
                }
            }, new Consumer() { // from class: oie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.c(map, (DefinitionFieldContext) obj);
                }
            }, new Consumer() { // from class: pie
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TraceReferencesCheckConsumer.c(map, (DefinitionMethodContext) obj);
                }
            });
        }
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ForwardingConsumer, com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public void finished(DiagnosticsHandler diagnosticsHandler) {
        super.finished(diagnosticsHandler);
        if (this.c.isEmpty() && this.d.isEmpty() && this.e.isEmpty()) {
            return;
        }
        diagnosticsHandler.error(a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Map map, DefinitionFieldContext definitionFieldContext) {
        map.put(definitionFieldContext.getFieldReference(), definitionFieldContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Map map, DefinitionFieldContext definitionFieldContext) {
        map.put(definitionFieldContext.getFieldReference(), definitionFieldContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Map map, DefinitionMethodContext definitionMethodContext) {
        map.put(definitionMethodContext.getMethodReference(), definitionMethodContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(Map map, DefinitionMethodContext definitionMethodContext) {
        map.put(definitionMethodContext.getMethodReference(), definitionMethodContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(l lVar, FieldReference fieldReference, Map map) {
        n nVar = new n();
        nVar.b = fieldReference;
        n nVar2 = (n) nVar.a(map.values());
        lVar.a.a(new o(nVar2.b, nVar2.a.a()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(l lVar, MethodReference methodReference, Map map) {
        p pVar = new p();
        pVar.b = methodReference;
        p pVar2 = (p) pVar.a(map.values());
        lVar.a.a(new q(pVar2.b, pVar2.a.a()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Map a(ClassReference classReference) {
        return new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Map a(FieldReference fieldReference) {
        return new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Map map, DefinitionClassContext definitionClassContext) {
        map.put(definitionClassContext.getClassReference(), definitionClassContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Map map, DefinitionFieldContext definitionFieldContext) {
        map.put(definitionFieldContext.getFieldReference(), definitionFieldContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Map map, DefinitionMethodContext definitionMethodContext) {
        map.put(definitionMethodContext.getMethodReference(), definitionMethodContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Map a(MethodReference methodReference) {
        return new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(l lVar, ClassReference classReference, Map map) {
        com.android.tools.r8.diagnostic.internal.g gVar = new com.android.tools.r8.diagnostic.internal.g();
        gVar.b = classReference;
        com.android.tools.r8.diagnostic.internal.g gVar2 = (com.android.tools.r8.diagnostic.internal.g) gVar.a(map.values());
        lVar.a.a(new com.android.tools.r8.diagnostic.internal.h(gVar2.b, gVar2.a.a()));
    }
}
