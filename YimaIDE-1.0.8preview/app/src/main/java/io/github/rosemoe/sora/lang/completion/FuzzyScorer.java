package io.github.rosemoe.sora.lang.completion;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/FuzzyScorer;", "", "calculateScore", "Lio/github/rosemoe/sora/lang/completion/FuzzyScore;", "pattern", "", "lowPattern", "patternPos", "", "word", "lowWord", "wordPos", "options", "Lio/github/rosemoe/sora/lang/completion/FuzzyScoreOptions;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface FuzzyScorer {
    FuzzyScore calculateScore(String pattern, String lowPattern, int patternPos, String word, String lowWord, int wordPos, FuzzyScoreOptions options);
}
