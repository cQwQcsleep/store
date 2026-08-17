package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.config.FirLanguageVersionSettingsChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/CommonLanguageVersionSettingsCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/LanguageVersionSettingsCheckers;", "<init>", "()V", "languageVersionSettingsCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirLanguageVersionSettingsChecker;", "getLanguageVersionSettingsCheckers", "()Ljava/util/Set;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonLanguageVersionSettingsCheckers extends LanguageVersionSettingsCheckers {
    public static final CommonLanguageVersionSettingsCheckers INSTANCE = new CommonLanguageVersionSettingsCheckers();
    private static final Set<FirLanguageVersionSettingsChecker> languageVersionSettingsCheckers = SetsKt.emptySet();

    private CommonLanguageVersionSettingsCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.LanguageVersionSettingsCheckers
    public Set<FirLanguageVersionSettingsChecker> getLanguageVersionSettingsCheckers() {
        return languageVersionSettingsCheckers;
    }
}
