package org.eclipse.tm4e.core.internal.registry;

import java.util.Collection;
import org.eclipse.tm4e.core.internal.grammar.raw.IRawGrammar;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IGrammarRepository {
    Collection<String> injections(String str);

    IRawGrammar lookup(String str);
}
