package io.github.rosemoe.sora.lang.completion;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/SortedCompletionItem;", "", "completionItem", "Lio/github/rosemoe/sora/lang/completion/CompletionItem;", "score", "Lio/github/rosemoe/sora/lang/completion/FuzzyScore;", "<init>", "(Lio/github/rosemoe/sora/lang/completion/CompletionItem;Lio/github/rosemoe/sora/lang/completion/FuzzyScore;)V", "getCompletionItem", "()Lio/github/rosemoe/sora/lang/completion/CompletionItem;", "getScore", "()Lio/github/rosemoe/sora/lang/completion/FuzzyScore;", "setScore", "(Lio/github/rosemoe/sora/lang/completion/FuzzyScore;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class SortedCompletionItem {
    private final CompletionItem completionItem;
    private FuzzyScore score;

    public SortedCompletionItem(CompletionItem completionItem, FuzzyScore fuzzyScore) {
        completionItem.getClass();
        fuzzyScore.getClass();
        this.completionItem = completionItem;
        this.score = fuzzyScore;
    }

    public static /* synthetic */ SortedCompletionItem copy$default(SortedCompletionItem sortedCompletionItem, CompletionItem completionItem, FuzzyScore fuzzyScore, int i, Object obj) {
        if ((i & 1) != 0) {
            completionItem = sortedCompletionItem.completionItem;
        }
        if ((i & 2) != 0) {
            fuzzyScore = sortedCompletionItem.score;
        }
        return sortedCompletionItem.copy(completionItem, fuzzyScore);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CompletionItem getCompletionItem() {
        return this.completionItem;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FuzzyScore getScore() {
        return this.score;
    }

    public final SortedCompletionItem copy(CompletionItem completionItem, FuzzyScore score) {
        completionItem.getClass();
        score.getClass();
        return new SortedCompletionItem(completionItem, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SortedCompletionItem)) {
            return false;
        }
        SortedCompletionItem sortedCompletionItem = (SortedCompletionItem) other;
        return Intrinsics.areEqual(this.completionItem, sortedCompletionItem.completionItem) && Intrinsics.areEqual(this.score, sortedCompletionItem.score);
    }

    public final CompletionItem getCompletionItem() {
        return this.completionItem;
    }

    public final FuzzyScore getScore() {
        return this.score;
    }

    public int hashCode() {
        return (this.completionItem.hashCode() * 31) + this.score.hashCode();
    }

    public final void setScore(FuzzyScore fuzzyScore) {
        fuzzyScore.getClass();
        this.score = fuzzyScore;
    }

    public String toString() {
        return "SortedCompletionItem(completionItem=" + this.completionItem + ", score=" + this.score + ")";
    }
}
