package org.jetbrains.kotlin.resolve.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.ExplicitApiMode;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"explicitApiEnabled", "", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getExplicitApiEnabled", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)Z", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ExplicitApiDeclarationCheckerKt {
    public static final boolean getExplicitApiEnabled(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return languageVersionSettings.getFlag(AnalysisFlags.getExplicitApiMode()) != ExplicitApiMode.DISABLED;
    }
}
