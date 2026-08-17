package org.jetbrains.kotlin.fir.analysis.checkers.config;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentInternal;
import org.jetbrains.kotlin.fir.analysis.checkers.LanguageVersionSettingsCheckers;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0007b\u0002\b\fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/config/ComposedLanguageVersionSettingsCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/LanguageVersionSettingsCheckers;", "<init>", "()V", "languageVersionSettingsCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirLanguageVersionSettingsChecker;", "getLanguageVersionSettingsCheckers", "()Ljava/util/Set;", "register", Argument.Delimiters.none, "checkers", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponentInternal;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposedLanguageVersionSettingsCheckers extends LanguageVersionSettingsCheckers {
    private final Set<FirLanguageVersionSettingsChecker> languageVersionSettingsCheckers = new LinkedHashSet();

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LanguageVersionSettingsCheckers
    public Set<FirLanguageVersionSettingsChecker> getLanguageVersionSettingsCheckers() {
        return this.languageVersionSettingsCheckers;
    }

    @CheckersComponentInternal
    public final void register(LanguageVersionSettingsCheckers checkers) {
        checkers.getClass();
        CollectionsKt.addAll(getLanguageVersionSettingsCheckers(), checkers.getLanguageVersionSettingsCheckers());
    }
}
