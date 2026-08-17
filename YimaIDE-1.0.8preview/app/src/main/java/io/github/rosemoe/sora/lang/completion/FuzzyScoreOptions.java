package io.github.rosemoe.sora.lang.completion;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/FuzzyScoreOptions;", "", "firstMatchCanBeWeak", "", "boostFullMatch", "<init>", "(ZZ)V", "getFirstMatchCanBeWeak", "()Z", "getBoostFullMatch", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class FuzzyScoreOptions {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: default, reason: not valid java name */
    private static final FuzzyScoreOptions f1default = new FuzzyScoreOptions(true, true);
    private final boolean boostFullMatch;
    private final boolean firstMatchCanBeWeak;

    public FuzzyScoreOptions(boolean z, boolean z2) {
        this.firstMatchCanBeWeak = z;
        this.boostFullMatch = z2;
    }

    public static /* synthetic */ FuzzyScoreOptions copy$default(FuzzyScoreOptions fuzzyScoreOptions, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = fuzzyScoreOptions.firstMatchCanBeWeak;
        }
        if ((i & 2) != 0) {
            z2 = fuzzyScoreOptions.boostFullMatch;
        }
        return fuzzyScoreOptions.copy(z, z2);
    }

    public static final FuzzyScoreOptions getDefault() {
        return INSTANCE.getDefault();
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getFirstMatchCanBeWeak() {
        return this.firstMatchCanBeWeak;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getBoostFullMatch() {
        return this.boostFullMatch;
    }

    public final FuzzyScoreOptions copy(boolean firstMatchCanBeWeak, boolean boostFullMatch) {
        return new FuzzyScoreOptions(firstMatchCanBeWeak, boostFullMatch);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FuzzyScoreOptions)) {
            return false;
        }
        FuzzyScoreOptions fuzzyScoreOptions = (FuzzyScoreOptions) other;
        return this.firstMatchCanBeWeak == fuzzyScoreOptions.firstMatchCanBeWeak && this.boostFullMatch == fuzzyScoreOptions.boostFullMatch;
    }

    public final boolean getBoostFullMatch() {
        return this.boostFullMatch;
    }

    public final boolean getFirstMatchCanBeWeak() {
        return this.firstMatchCanBeWeak;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.firstMatchCanBeWeak) * 31) + Boolean.hashCode(this.boostFullMatch);
    }

    public String toString() {
        return "FuzzyScoreOptions(firstMatchCanBeWeak=" + this.firstMatchCanBeWeak + ", boostFullMatch=" + this.boostFullMatch + ")";
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/FuzzyScoreOptions$Companion;", "", "<init>", "()V", "default", "Lio/github/rosemoe/sora/lang/completion/FuzzyScoreOptions;", "getDefault$annotations", "getDefault", "()Lio/github/rosemoe/sora/lang/completion/FuzzyScoreOptions;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getDefault$annotations() {
        }

        public final FuzzyScoreOptions getDefault() {
            return FuzzyScoreOptions.f1default;
        }

        private Companion() {
        }
    }
}
