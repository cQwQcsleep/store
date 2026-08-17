package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettingsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\b\u001a\u00020\u0005*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\t"}, d2 = {"toDeprecationWarningMessage", Argument.Delimiters.none, "deprecatingFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "appendDeprecationWarningSuffix", Argument.Delimiters.none, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "appendVersion", "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RenderingUtilsKt {
    public static final void appendDeprecationWarningSuffix(StringBuilder sb, LanguageFeature languageFeature) {
        sb.getClass();
        languageFeature.getClass();
        sb.append("This will become an error ");
        appendVersion(sb, languageFeature);
        sb.append(".");
        String issue = languageFeature.getIssue();
        if (Intrinsics.areEqual(issue, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED)) {
            issue = null;
        }
        if (issue != null) {
            sb.append(" See https://youtrack.jetbrains.com/issue/");
            sb.append(issue);
            sb.append(".");
        }
    }

    public static final void appendVersion(StringBuilder sb, LanguageFeature languageFeature) {
        sb.getClass();
        languageFeature.getClass();
        LanguageVersion sinceVersion = languageFeature.getSinceVersion();
        if (sinceVersion == null) {
            sb.append("in a future release");
        } else {
            sb.append("in language version ");
            sb.append(sinceVersion.getVersionString());
        }
    }

    public static final String toDeprecationWarningMessage(String str, LanguageFeature languageFeature) {
        str.getClass();
        languageFeature.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (StringsKt.endsWith$default(sb, ".", false, 2, (Object) null)) {
            sb.append(Argument.Delimiters.space);
        } else {
            Character chLastOrNull = StringsKt.lastOrNull(sb);
            if (chLastOrNull == null || !CharsKt.isWhitespace(chLastOrNull.charValue())) {
                sb.append(". ");
            } else {
                Unit unit = Unit.INSTANCE;
            }
        }
        appendDeprecationWarningSuffix(sb, languageFeature);
        return sb.toString();
    }
}
