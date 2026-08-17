package io.github.rosemoe.sora.lang.completion;

import io.github.rosemoe.sora.lang.completion.snippet.CodeSnippet;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/SnippetDescription;", "", "selectedLength", "", "snippet", "Lio/github/rosemoe/sora/lang/completion/snippet/CodeSnippet;", "deleteSelected", "", "<init>", "(ILio/github/rosemoe/sora/lang/completion/snippet/CodeSnippet;Z)V", "getSelectedLength", "()I", "getSnippet", "()Lio/github/rosemoe/sora/lang/completion/snippet/CodeSnippet;", "getDeleteSelected", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class SnippetDescription {
    private final boolean deleteSelected;
    private final int selectedLength;
    private final CodeSnippet snippet;

    public SnippetDescription(int i, CodeSnippet codeSnippet, boolean z) {
        codeSnippet.getClass();
        this.selectedLength = i;
        this.snippet = codeSnippet;
        this.deleteSelected = z;
    }

    public static /* synthetic */ SnippetDescription copy$default(SnippetDescription snippetDescription, int i, CodeSnippet codeSnippet, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = snippetDescription.selectedLength;
        }
        if ((i2 & 2) != 0) {
            codeSnippet = snippetDescription.snippet;
        }
        if ((i2 & 4) != 0) {
            z = snippetDescription.deleteSelected;
        }
        return snippetDescription.copy(i, codeSnippet, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSelectedLength() {
        return this.selectedLength;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CodeSnippet getSnippet() {
        return this.snippet;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getDeleteSelected() {
        return this.deleteSelected;
    }

    public final SnippetDescription copy(int selectedLength, CodeSnippet snippet, boolean deleteSelected) {
        snippet.getClass();
        return new SnippetDescription(selectedLength, snippet, deleteSelected);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SnippetDescription)) {
            return false;
        }
        SnippetDescription snippetDescription = (SnippetDescription) other;
        return this.selectedLength == snippetDescription.selectedLength && Intrinsics.areEqual(this.snippet, snippetDescription.snippet) && this.deleteSelected == snippetDescription.deleteSelected;
    }

    public final boolean getDeleteSelected() {
        return this.deleteSelected;
    }

    public final int getSelectedLength() {
        return this.selectedLength;
    }

    public final CodeSnippet getSnippet() {
        return this.snippet;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.selectedLength) * 31) + this.snippet.hashCode()) * 31) + Boolean.hashCode(this.deleteSelected);
    }

    public String toString() {
        return "SnippetDescription(selectedLength=" + this.selectedLength + ", snippet=" + this.snippet + ", deleteSelected=" + this.deleteSelected + ")";
    }

    public /* synthetic */ SnippetDescription(int i, CodeSnippet codeSnippet, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, codeSnippet, (i2 & 4) != 0 ? true : z);
    }
}
