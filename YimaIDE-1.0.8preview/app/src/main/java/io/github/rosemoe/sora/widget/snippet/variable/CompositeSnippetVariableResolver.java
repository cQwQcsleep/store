package io.github.rosemoe.sora.widget.snippet.variable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class CompositeSnippetVariableResolver implements ISnippetVariableResolver {
    private final Map<String, ISnippetVariableResolver> resolverMap = new HashMap();

    public void addResolver(ISnippetVariableResolver iSnippetVariableResolver) {
        if (iSnippetVariableResolver instanceof CompositeSnippetVariableResolver) {
            j2d.a();
            return;
        }
        Objects.requireNonNull(iSnippetVariableResolver, "resolver must not be null");
        for (String str : iSnippetVariableResolver.getResolvableNames()) {
            this.resolverMap.put(str, iSnippetVariableResolver);
        }
    }

    public boolean canResolve(String str) {
        return this.resolverMap.containsKey(str);
    }

    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String[] getResolvableNames() {
        return new String[0];
    }

    public void removeResolver(ISnippetVariableResolver iSnippetVariableResolver) {
        for (String str : iSnippetVariableResolver.getResolvableNames()) {
            if (this.resolverMap.get(str) == iSnippetVariableResolver) {
                this.resolverMap.remove(str);
            }
        }
    }

    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String resolve(String str) {
        ISnippetVariableResolver iSnippetVariableResolver = this.resolverMap.get(str);
        return iSnippetVariableResolver != null ? iSnippetVariableResolver.resolve(str) : "";
    }
}
