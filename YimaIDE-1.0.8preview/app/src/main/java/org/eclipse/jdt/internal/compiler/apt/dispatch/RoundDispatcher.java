package org.eclipse.jdt.internal.compiler.apt.dispatch;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class RoundDispatcher {
    private final List<ProcessorInfo> _processors;
    private final IProcessorProvider _provider;
    private final RoundEnvironment _roundEnv;
    private boolean _searchForStar = false;
    private final PrintWriter _traceProcessorInfo;
    private final PrintWriter _traceRounds;
    private final Set<TypeElement> _unclaimedAnnotations;

    public RoundDispatcher(IProcessorProvider iProcessorProvider, RoundEnvironment roundEnvironment, Set<TypeElement> set, PrintWriter printWriter, PrintWriter printWriter2) {
        this._provider = iProcessorProvider;
        this._processors = iProcessorProvider.getDiscoveredProcessors();
        this._roundEnv = roundEnvironment;
        this._unclaimedAnnotations = new HashSet(set);
        this._traceProcessorInfo = printWriter;
        this._traceRounds = printWriter2;
    }

    private void handleProcessor(ProcessorInfo processorInfo) {
        try {
            HashSet hashSet = new HashSet();
            if (processorInfo.computeSupportedAnnotations(this._unclaimedAnnotations, hashSet)) {
                boolean zProcess = processorInfo._processor.process(hashSet, this._roundEnv);
                if (this._traceProcessorInfo != null && !this._roundEnv.processingOver()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Processor ");
                    sb.append(processorInfo._processor.getClass().getName());
                    sb.append(" matches [");
                    Iterator<TypeElement> it = hashSet.iterator();
                    boolean zHasNext = it.hasNext();
                    while (zHasNext) {
                        sb.append(it.next());
                        zHasNext = it.hasNext();
                        if (zHasNext) {
                            sb.append(' ');
                        }
                    }
                    sb.append("] and returns ");
                    sb.append(zProcess);
                    this._traceProcessorInfo.println(sb.toString());
                }
                if (zProcess) {
                    this._unclaimedAnnotations.removeAll(hashSet);
                    if (processorInfo.supportsStar()) {
                        this._searchForStar = false;
                    }
                }
            }
        } catch (Throwable th) {
            this._provider.reportProcessorException(processorInfo._processor, new Exception(th));
        }
    }

    public void round() {
        ProcessorInfo processorInfoDiscoverNextProcessor;
        if (this._traceRounds != null) {
            StringBuilder sb = new StringBuilder("\tinput files: {");
            Iterator it = this._roundEnv.getRootElements().iterator();
            boolean zHasNext = it.hasNext();
            while (zHasNext) {
                sb.append(it.next());
                zHasNext = it.hasNext();
                if (zHasNext) {
                    sb.append(',');
                }
            }
            sb.append('}');
            this._traceRounds.println(sb.toString());
            StringBuilder sb2 = new StringBuilder("\tannotations: [");
            Iterator<TypeElement> it2 = this._unclaimedAnnotations.iterator();
            boolean zHasNext2 = it2.hasNext();
            while (zHasNext2) {
                sb2.append(it2.next());
                zHasNext2 = it2.hasNext();
                if (zHasNext2) {
                    sb2.append(',');
                }
            }
            sb2.append(']');
            this._traceRounds.println(sb2.toString());
            this._traceRounds.println("\tlast round: " + this._roundEnv.processingOver());
        }
        this._searchForStar = this._unclaimedAnnotations.isEmpty();
        Iterator<ProcessorInfo> it3 = this._processors.iterator();
        while (it3.hasNext()) {
            handleProcessor(it3.next());
        }
        while (true) {
            if ((!this._searchForStar && this._unclaimedAnnotations.isEmpty()) || (processorInfoDiscoverNextProcessor = this._provider.discoverNextProcessor()) == null) {
                return;
            } else {
                handleProcessor(processorInfoDiscoverNextProcessor);
            }
        }
    }
}
