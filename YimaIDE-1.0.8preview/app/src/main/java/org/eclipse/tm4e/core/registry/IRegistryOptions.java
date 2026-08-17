package org.eclipse.tm4e.core.registry;

import java.util.Collection;
import java.util.List;
import org.eclipse.tm4e.core.internal.theme.raw.IRawTheme;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IRegistryOptions {
    default List<String> getColorMap() {
        return null;
    }

    default IGrammarSource getGrammarSource(String str) {
        return null;
    }

    default Collection<String> getInjections(String str) {
        return null;
    }

    default IRawTheme getTheme() {
        return null;
    }
}
