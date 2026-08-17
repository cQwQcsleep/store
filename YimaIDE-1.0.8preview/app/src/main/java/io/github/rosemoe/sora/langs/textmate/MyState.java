package io.github.rosemoe.sora.langs.textmate;

import java.util.List;
import org.eclipse.tm4e.core.grammar.IStateStack;
import org.eclipse.tm4e.core.internal.oniguruma.OnigResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class MyState {
    public OnigResult foldingCache;
    public List<String> identifiers;
    public int indent;
    public IStateStack tokenizeState;

    public MyState(IStateStack iStateStack, OnigResult onigResult, int i, List<String> list) {
        this.tokenizeState = iStateStack;
        this.foldingCache = onigResult;
        this.indent = i;
        this.identifiers = list;
    }
}
