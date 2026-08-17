package io.github.rosemoe.sora.widget.snippet.variable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class WorkspaceBasedSnippetVariableResolver implements ISnippetVariableResolver {
    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String[] getResolvableNames() {
        return new String[]{"WORKSPACE_NAME", "WORKSPACE_FOLDER"};
    }
}
