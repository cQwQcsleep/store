package io.github.rosemoe.sora.langs.textmate.registry.model;

import java.util.Collections;
import java.util.Map;
import org.eclipse.tm4e.core.registry.IGrammarSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface GrammarDefinition {
    default Map<String, String> getEmbeddedLanguages() {
        return Collections.EMPTY_MAP;
    }

    IGrammarSource getGrammar();

    String getLanguageConfiguration();

    String getName();

    String getScopeName();
}
