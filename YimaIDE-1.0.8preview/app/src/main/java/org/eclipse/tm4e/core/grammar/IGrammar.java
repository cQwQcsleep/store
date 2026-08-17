package org.eclipse.tm4e.core.grammar;

import java.time.Duration;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IGrammar {
    Collection<String> getFileTypes();

    String getName();

    String getScopeName();

    ITokenizeLineResult<IToken[]> tokenizeLine(String str);

    ITokenizeLineResult<IToken[]> tokenizeLine(String str, IStateStack iStateStack, Duration duration);

    ITokenizeLineResult<int[]> tokenizeLine2(String str);

    ITokenizeLineResult<int[]> tokenizeLine2(String str, IStateStack iStateStack, Duration duration);
}
