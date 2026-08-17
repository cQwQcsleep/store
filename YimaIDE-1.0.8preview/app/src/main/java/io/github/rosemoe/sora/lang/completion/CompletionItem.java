package io.github.rosemoe.sora.lang.completion;

import android.graphics.drawable.Drawable;
import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.widget.CodeEditor;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class CompletionItem {
    public CharSequence desc;
    protected Object extra;
    public String filterText;
    public Drawable icon;
    public CompletionItemKind kind;
    public CharSequence label;
    public int prefixLength;
    public String sortText;

    public CompletionItem(CharSequence charSequence, CharSequence charSequence2, Drawable drawable) {
        this.prefixLength = 0;
        this.label = charSequence;
        this.desc = charSequence2;
        this.icon = drawable;
    }

    public CompletionItem desc(CharSequence charSequence) {
        this.desc = charSequence;
        return this;
    }

    public CompletionItem icon(Drawable drawable) {
        this.icon = drawable;
        return this;
    }

    public CompletionItem kind(CompletionItemKind completionItemKind) {
        this.kind = completionItemKind;
        return this;
    }

    public CompletionItem label(CharSequence charSequence) {
        this.label = charSequence;
        return this;
    }

    public abstract void performCompletion(CodeEditor codeEditor, Content content, int i, int i2);

    public void performCompletion(CodeEditor codeEditor, Content content, CharPosition charPosition) {
        performCompletion(codeEditor, content, charPosition.line, charPosition.column);
    }

    public CompletionItem(CharSequence charSequence, CharSequence charSequence2) {
        this(charSequence, charSequence2, null);
    }

    public CompletionItem(CharSequence charSequence) {
        this(charSequence, null);
    }
}
