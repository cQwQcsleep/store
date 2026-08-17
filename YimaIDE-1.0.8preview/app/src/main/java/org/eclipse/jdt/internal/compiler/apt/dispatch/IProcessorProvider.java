package org.eclipse.jdt.internal.compiler.apt.dispatch;

import java.util.List;
import javax.annotation.processing.Processor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IProcessorProvider {
    ProcessorInfo discoverNextProcessor();

    List<ProcessorInfo> getDiscoveredProcessors();

    void reportProcessorException(Processor processor, Exception exc);
}
