package io.github.rosemoe.sora.widget.snippet.variable;

import android.content.ClipData;
import android.content.ClipboardManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ClipboardBasedSnippetVariableResolver implements ISnippetVariableResolver {
    private final ClipboardManager clipboardManager;

    public ClipboardBasedSnippetVariableResolver(ClipboardManager clipboardManager) {
        this.clipboardManager = clipboardManager;
    }

    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String[] getResolvableNames() {
        return new String[]{"CLIPBOARD"};
    }

    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String resolve(String str) {
        ClipData primaryClip;
        if ("CLIPBOARD".equals(str)) {
            ClipboardManager clipboardManager = this.clipboardManager;
            return (clipboardManager == null || !clipboardManager.hasPrimaryClip() || (primaryClip = this.clipboardManager.getPrimaryClip()) == null || primaryClip.getItemCount() == 0) ? "" : primaryClip.getItemAt(0).getText().toString();
        }
        aca.a("Unsupported variable name:", str);
        return null;
    }
}
