package org.eclipse.tm4e.core.registry;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IGrammarConfiguration {
    default List<String> getBalancedBracketSelectors() {
        return null;
    }

    default Map<String, Integer> getEmbeddedLanguages() {
        return null;
    }

    default Map<String, Integer> getTokenTypes() {
        return null;
    }

    default List<String> getUnbalancedBracketSelectors() {
        return null;
    }
}
