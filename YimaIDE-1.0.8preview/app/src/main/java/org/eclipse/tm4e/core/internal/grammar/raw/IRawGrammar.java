package org.eclipse.tm4e.core.internal.grammar.raw;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IRawGrammar {
    Collection<String> getFileTypes();

    String getFirstLineMatch();

    String getInjectionSelector();

    Map<String, IRawRule> getInjections();

    String getName();

    Collection<IRawRule> getPatterns();

    IRawRepository getRepository();

    String getScopeName();

    void setRepository(IRawRepository iRawRepository);

    IRawRule toRawRule();
}
