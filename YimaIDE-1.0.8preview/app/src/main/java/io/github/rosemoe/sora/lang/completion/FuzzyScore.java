package io.github.rosemoe.sora.lang.completion;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u000b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/FuzzyScore;", "", "score", "", "wordStart", "matches", "", "<init>", "(IILjava/util/List;)V", "getScore", "()I", "setScore", "(I)V", "getWordStart", "getMatches", "()Ljava/util/List;", "Companion", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class FuzzyScore {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: default, reason: not valid java name */
    private static final FuzzyScore f0default = new FuzzyScore(-100, 0, null, 4, null);
    private final List<Integer> matches;
    private int score;
    private final int wordStart;

    public FuzzyScore(int i, int i2, List<Integer> list) {
        list.getClass();
        this.score = i;
        this.wordStart = i2;
        this.matches = list;
    }

    public static final FuzzyScore getDefault() {
        return INSTANCE.getDefault();
    }

    @JvmStatic
    public static final boolean isDefault(FuzzyScore fuzzyScore) {
        return INSTANCE.isDefault(fuzzyScore);
    }

    public final List<Integer> getMatches() {
        return this.matches;
    }

    public final int getScore() {
        return this.score;
    }

    public final int getWordStart() {
        return this.wordStart;
    }

    public final void setScore(int i) {
        this.score = i;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/FuzzyScore$Companion;", "", "<init>", "()V", "default", "Lio/github/rosemoe/sora/lang/completion/FuzzyScore;", "getDefault$annotations", "getDefault", "()Lio/github/rosemoe/sora/lang/completion/FuzzyScore;", "isDefault", "", "score", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getDefault$annotations() {
        }

        public final FuzzyScore getDefault() {
            return FuzzyScore.f0default;
        }

        @JvmStatic
        public final boolean isDefault(FuzzyScore score) {
            return score != null && score.getScore() == -100 && score.getWordStart() == 0;
        }

        private Companion() {
        }
    }

    public /* synthetic */ FuzzyScore(int i, int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? new ArrayList() : list);
    }
}
