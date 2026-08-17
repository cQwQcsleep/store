package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.AbstractC2835v90;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.retrace.MappingSupplierBase;
import com.android.tools.r8.retrace.RetraceInvalidStackTraceLineDiagnostics;
import com.android.tools.r8.retrace.RetraceStackFrameAmbiguousResultWithContext;
import com.android.tools.r8.retrace.RetraceStackFrameResult;
import com.android.tools.r8.retrace.RetraceStackFrameResultWithContext;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceStackTraceElementProxy;
import com.android.tools.r8.retrace.RetraceStackTraceElementProxyResult;
import com.android.tools.r8.retrace.RetraceStackTraceResult;
import com.android.tools.r8.retrace.Retracer;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import com.android.tools.r8.retrace.StackTraceElementProxyRetracer;
import com.android.tools.r8.retrace.StackTraceLineParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2835v90 {
    private final StackTraceLineParser a;
    private final MappingSupplierBase b;
    private final DiagnosticsHandler c;
    protected final boolean isVerbose;

    public AbstractC2835v90(StackTraceLineParser stackTraceLineParser, MappingSupplierBase mappingSupplierBase, DiagnosticsHandler diagnosticsHandler, boolean z) {
        this.a = stackTraceLineParser;
        this.b = mappingSupplierBase;
        this.c = diagnosticsHandler;
        this.isVerbose = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RetraceStackTraceContext a(StackTraceElementProxyRetracer stackTraceElementProxyRetracer, final C2750u90 c2750u90, List list, RetraceStackTraceContext retraceStackTraceContext, final StackTraceElementProxy stackTraceElementProxy) {
        final ArrayList arrayList = new ArrayList();
        final C1975l7 c1975l7 = new C1975l7();
        final HashSet hashSet = new HashSet();
        final ArrayList arrayList2 = new ArrayList();
        RetraceStackTraceElementProxyResult retraceStackTraceElementProxyResultRetrace = stackTraceElementProxyRetracer.retrace(stackTraceElementProxy, retraceStackTraceContext);
        retraceStackTraceElementProxyResultRetrace.stream().forEach(new Consumer() { // from class: bli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(hashSet, c2750u90, c1975l7, arrayList, arrayList2, stackTraceElementProxy, (RetraceStackTraceElementProxy) obj);
            }
        });
        arrayList.sort(Comparator.comparing(new Function() { // from class: cli
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (RetraceStackTraceElementProxy) ((C1405eW) obj).a();
            }
        }));
        list.add(new H90(C2847vL.a((Collection) arrayList, new Function() { // from class: dli
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (RetraceStackFrameResult) ((C1405eW) obj).b();
            }
        }), RetraceStackTraceContext.empty()));
        if (arrayList2.isEmpty()) {
            return retraceStackTraceElementProxyResultRetrace.getResultContext();
        }
        return arrayList2.size() == 1 ? (RetraceStackTraceContext) arrayList2.get(0) : RetraceStackTraceContext.empty();
    }

    public List<StackTraceElementProxy<Object, Object>> parse(List<Object> list) {
        InterfaceC2762uL interfaceC2762uL = new InterfaceC2762uL() { // from class: eli
            @Override // com.android.tools.r8.internal.InterfaceC2762uL
            public final void accept(Object obj, int i) {
                this.a.a(obj, i);
            }
        };
        boolean z = C2847vL.a;
        for (int i = 0; i < list.size(); i++) {
            interfaceC2762uL.accept(list.get(i), i);
        }
        final StackTraceLineParser stackTraceLineParser = this.a;
        Objects.requireNonNull(stackTraceLineParser);
        return C2847vL.a((Collection) list, new Function() { // from class: fli
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return stackTraceLineParser.parse(obj);
            }
        });
    }

    public void registerUses(List<StackTraceElementProxy<Object, Object>> list) {
        list.forEach(new Consumer() { // from class: gli
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.registerUses((StackTraceElementProxy<Object, Object>) obj);
            }
        });
    }

    public RetraceStackFrameAmbiguousResultWithContext<Object> retraceFrameWithRetracer(Retracer retracer, final StackTraceElementProxy<Object, Object> stackTraceElementProxy, RetraceStackTraceContext retraceStackTraceContext) {
        final HashMap map = new HashMap();
        final ArrayList arrayList = new ArrayList();
        StackTraceElementProxyRetracer stackTraceElementProxyRetracerCreateDefault = StackTraceElementProxyRetracer.createDefault(retracer);
        final C1975l7 c1975l7 = new C1975l7(retraceStackTraceContext);
        stackTraceElementProxyRetracerCreateDefault.retrace(stackTraceElementProxy, retraceStackTraceContext).stream().forEach(new Consumer() { // from class: yki
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a(arrayList, map, stackTraceElementProxy, c1975l7, (RetraceStackTraceElementProxy) obj);
            }
        });
        Collections.sort(arrayList);
        final ArrayList arrayList2 = new ArrayList();
        arrayList.forEach(new Consumer() { // from class: zki
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC2835v90.a(arrayList2, map, (RetraceStackTraceElementProxy) obj);
            }
        });
        return new H90(arrayList2, (RetraceStackTraceContext) c1975l7.a());
    }

    public RetraceStackFrameResultWithContext<Object> retraceLineWithRetracer(Retracer retracer, final StackTraceElementProxy<Object, Object> stackTraceElementProxy, RetraceStackTraceContext retraceStackTraceContext) {
        StackTraceElementProxyRetracer stackTraceElementProxyRetracerCreateDefault = StackTraceElementProxyRetracer.createDefault(retracer);
        final C1975l7 c1975l7 = new C1975l7(retraceStackTraceContext);
        return new I90((List) stackTraceElementProxyRetracerCreateDefault.retrace(stackTraceElementProxy, retraceStackTraceContext).stream().map(new Function() { // from class: ali
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c1975l7, stackTraceElementProxy, (RetraceStackTraceElementProxy) obj);
            }
        }).collect(Collectors.toList()), (RetraceStackTraceContext) c1975l7.a());
    }

    public RetraceStackTraceResult<Object> retraceStackTraceParsedWithRetracer(Retracer retracer, List<StackTraceElementProxy<Object, Object>> list, RetraceStackTraceContext retraceStackTraceContext) {
        final C2750u90 c2750u90 = new C2750u90(this.isVerbose);
        final StackTraceElementProxyRetracer stackTraceElementProxyRetracerCreateDefault = StackTraceElementProxyRetracer.createDefault(retracer);
        final ArrayList arrayList = new ArrayList();
        return new O90(arrayList, (RetraceStackTraceContext) C2847vL.a(list, retraceStackTraceContext, new BiFunction() { // from class: hli
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return this.b.a(stackTraceElementProxyRetracerCreateDefault, c2750u90, arrayList, (RetraceStackTraceContext) obj, (StackTraceElementProxy) obj2);
            }
        }));
    }

    public void registerUses(StackTraceElementProxy<Object, Object> stackTraceElementProxy) {
        stackTraceElementProxy.registerUses(this.b, this.c);
    }

    public StackTraceElementProxy<Object, Object> parse(Object obj) {
        return this.a.parse(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(Object obj, int i) {
        if (obj != null) {
            return;
        }
        this.c.error(RetraceInvalidStackTraceLineDiagnostics.createNull(i));
        throw new C2664t90();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Set set, C2750u90 c2750u90, C1975l7 c1975l7, List list, List list2, StackTraceElementProxy stackTraceElementProxy, RetraceStackTraceElementProxy retraceStackTraceElementProxy) {
        if (retraceStackTraceElementProxy.isTopFrame() || !retraceStackTraceElementProxy.hasRetracedClass()) {
            c2750u90.getClass();
            if (set.add(new C2119mo(c2750u90, retraceStackTraceElementProxy))) {
                c1975l7.a(new ArrayList());
                list.add(C1405eW.a(retraceStackTraceElementProxy, new I90((List) c1975l7.a(), RetraceStackTraceContext.empty())));
                list2.add(retraceStackTraceElementProxy.getContext());
            } else {
                c1975l7.a((Object) null);
            }
        }
        if (c1975l7.b()) {
            ((List) c1975l7.a()).add(stackTraceElementProxy.toRetracedItem(retraceStackTraceElementProxy, this.isVerbose));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(List list, Map map, StackTraceElementProxy stackTraceElementProxy, C1975l7 c1975l7, RetraceStackTraceElementProxy retraceStackTraceElementProxy) {
        if (retraceStackTraceElementProxy.isTopFrame() || !retraceStackTraceElementProxy.hasRetracedClass()) {
            list.add(retraceStackTraceElementProxy);
            map.put(retraceStackTraceElementProxy, new ArrayList());
        }
        ((List) map.get(C2847vL.b(list))).add(stackTraceElementProxy.toRetracedItem(retraceStackTraceElementProxy, this.isVerbose));
        c1975l7.a(retraceStackTraceElementProxy.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List list, Map map, RetraceStackTraceElementProxy retraceStackTraceElementProxy) {
        list.add(new I90((List) map.get(retraceStackTraceElementProxy), RetraceStackTraceContext.empty()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object a(C1975l7 c1975l7, StackTraceElementProxy stackTraceElementProxy, RetraceStackTraceElementProxy retraceStackTraceElementProxy) {
        c1975l7.a(retraceStackTraceElementProxy.getContext());
        return stackTraceElementProxy.toRetracedItem(retraceStackTraceElementProxy, this.isVerbose);
    }
}
