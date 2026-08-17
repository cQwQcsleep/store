package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.C3210zd0;
import com.android.tools.r8.internal.I90;
import com.android.tools.r8.internal.Wf0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StringRetrace extends Retrace<String, C3210zd0> {
    public StringRetrace(StackTraceLineParser stackTraceLineParser, MappingSupplier mappingSupplier, DiagnosticsHandler diagnosticsHandler, boolean z) {
        super(stackTraceLineParser, mappingSupplier, diagnosticsHandler, z);
    }

    private static ArrayList a(List list) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RetraceStackFrameAmbiguousResult retraceStackFrameAmbiguousResult = (RetraceStackFrameAmbiguousResult) it.next();
            int i = 0;
            for (boolean z2 = true; z2; z2 = z) {
                HashSet hashSet = new HashSet();
                RetraceStackFrameResult retraceStackFrameResult = null;
                z = false;
                for (RetraceStackFrameResult retraceStackFrameResult2 : retraceStackFrameAmbiguousResult.getAmbiguousResult()) {
                    if (retraceStackFrameResult == null) {
                        retraceStackFrameResult = retraceStackFrameResult2;
                    }
                    if (i < retraceStackFrameResult2.size()) {
                        String str = (String) retraceStackFrameResult2.get(i);
                        if (hashSet.add(str)) {
                            if (retraceStackFrameResult2 != retraceStackFrameResult) {
                                arrayList.add(a(str));
                            } else {
                                arrayList.add(str);
                            }
                        }
                        z = true;
                    }
                }
                i++;
            }
        }
        return arrayList;
    }

    public static StringRetrace create(RetraceOptions retraceOptions) {
        return create(retraceOptions.getMappingSupplier(), retraceOptions.getDiagnosticsHandler(), retraceOptions.getRegularExpression(), retraceOptions.isVerbose());
    }

    public RetraceStackFrameResultWithContext<String> retrace(List<String> list, RetraceStackTraceContext retraceStackTraceContext) {
        RetraceStackTraceResult<String> retraceStackTraceResultRetraceStackTrace = retraceStackTrace(list, retraceStackTraceContext);
        return new I90(a(retraceStackTraceResultRetraceStackTrace.getResult()), retraceStackTraceResultRetraceStackTrace.getContext());
    }

    public RetraceStackFrameResultWithContext<String> retraceParsed(List<C3210zd0> list, RetraceStackTraceContext retraceStackTraceContext) {
        RetraceStackTraceResult<String> retraceStackTraceResultRetraceStackTraceParsed = retraceStackTraceParsed(list, retraceStackTraceContext);
        return new I90(a(retraceStackTraceResultRetraceStackTraceParsed.getResult()), retraceStackTraceResultRetraceStackTraceParsed.getContext());
    }

    public <E extends Throwable> void retraceSupplier(StreamSupplier<E> streamSupplier, Consumer<String> consumer) throws Throwable {
        RetraceStackTraceContext retraceStackTraceContextEmpty = RetraceStackTraceContext.empty();
        while (true) {
            String next = streamSupplier.getNext();
            if (next == null) {
                return;
            }
            RetraceStackFrameResultWithContext<String> retraceStackFrameResultWithContextRetrace = retrace(next, retraceStackTraceContextEmpty);
            RetraceStackTraceContext context = retraceStackFrameResultWithContextRetrace.getContext();
            retraceStackFrameResultWithContextRetrace.forEach(consumer);
            retraceStackTraceContextEmpty = context;
        }
    }

    public static StringRetrace create(MappingSupplier<?> mappingSupplier, DiagnosticsHandler diagnosticsHandler, String str, boolean z) {
        return new StringRetrace(StackTraceLineParser.createRegularExpressionParser(str), mappingSupplier, diagnosticsHandler, z);
    }

    public RetraceStackFrameResultWithContext<String> retrace(String str, RetraceStackTraceContext retraceStackTraceContext) {
        RetraceStackFrameAmbiguousResultWithContext<String> retraceStackFrameAmbiguousResultWithContextRetraceFrame = retraceFrame(str, retraceStackTraceContext);
        return new I90(a(Collections.singletonList(retraceStackFrameAmbiguousResultWithContextRetraceFrame)), retraceStackFrameAmbiguousResultWithContextRetraceFrame.getContext());
    }

    private static String a(String str) {
        int iIndexOf = str.indexOf("at ");
        if (iIndexOf < 0) {
            iIndexOf = Math.max(Wf0.b(str), 0);
        }
        return str.substring(0, iIndexOf) + "<OR> " + str.substring(iIndexOf);
    }
}
