package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ \u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ \u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/LanguageSettingsParser;", Argument.Delimiters.none, "<init>", "()V", "wholePrefix", Argument.Delimiters.none, "parseInternalArgument", "Lorg/jetbrains/kotlin/cli/common/arguments/ManualLanguageFeatureSetting;", "arg", AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS, "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentParseErrors;", "parseLanguageFeature", "tail", "wholeArgument", "reportAndReturnNull", Argument.Delimiters.none, "message", "severity", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageSettingsParser {
    public static final LanguageSettingsParser INSTANCE = new LanguageSettingsParser();
    private static final String wholePrefix = "-XXLanguage";

    private LanguageSettingsParser() {
    }

    private final Void reportAndReturnNull(ArgumentParseErrors argumentParseErrors, String str, CompilerMessageSeverity compilerMessageSeverity) {
        argumentParseErrors.getInternalArgumentsParsingProblems().add(TuplesKt.to(compilerMessageSeverity, str));
        return null;
    }

    public static /* synthetic */ Void reportAndReturnNull$default(LanguageSettingsParser languageSettingsParser, ArgumentParseErrors argumentParseErrors, String str, CompilerMessageSeverity compilerMessageSeverity, int i, Object obj) {
        if ((i & 2) != 0) {
            compilerMessageSeverity = CompilerMessageSeverity.STRONG_WARNING;
        }
        return languageSettingsParser.reportAndReturnNull(argumentParseErrors, str, compilerMessageSeverity);
    }

    public final ManualLanguageFeatureSetting parseInternalArgument(String arg, ArgumentParseErrors errors) {
        arg.getClass();
        errors.getClass();
        String str = wholePrefix;
        if (!StringsKt.startsWith$default(arg, str, false, 2, (Object) null)) {
            return null;
        }
        String strRemovePrefix = StringsKt.removePrefix(arg, str);
        Character orNull = StringsKt.getOrNull(strRemovePrefix, 0);
        if (orNull != null && orNull.charValue() == ':') {
            return parseLanguageFeature(strRemovePrefix.substring(1), arg, errors);
        }
        return (ManualLanguageFeatureSetting) reportAndReturnNull$default(this, errors, "Incorrect internal argument syntax, missing colon: " + arg, null, 2, null);
    }

    public final ManualLanguageFeatureSetting parseLanguageFeature(String tail, String wholeArgument, ArgumentParseErrors errors) {
        LanguageFeature.State state;
        tail.getClass();
        wholeArgument.getClass();
        errors.getClass();
        Character orNull = StringsKt.getOrNull(tail, 0);
        if (orNull != null && orNull.charValue() == '+') {
            state = LanguageFeature.State.ENABLED;
        } else {
            if (orNull == null || orNull.charValue() != '-') {
                return (ManualLanguageFeatureSetting) reportAndReturnNull$default(this, errors, "Incorrect internal argument syntax, missing modificator: " + wholeArgument, null, 2, null);
            }
            state = LanguageFeature.State.DISABLED;
        }
        String strSubstring = tail.substring(1);
        if (strSubstring.length() == 0) {
            return (ManualLanguageFeatureSetting) reportAndReturnNull$default(this, errors, "Empty language feature name for internal argument '" + wholeArgument + '\'', null, 2, null);
        }
        LanguageFeature languageFeatureFromString = LanguageFeature.INSTANCE.fromString(strSubstring);
        if (languageFeatureFromString == null) {
            return (ManualLanguageFeatureSetting) reportAndReturnNull$default(this, errors, "Unknown language feature '" + strSubstring + "' in passed internal argument '" + wholeArgument + '\'', null, 2, null);
        }
        if (languageFeatureFromString.getTestOnly() && !LanguageSettingsParserKt.getAreTestOnlyLanguageFeaturesAllowed()) {
            reportAndReturnNull(errors, "Language feature '" + strSubstring + "' is test-only and cannot be enabled from command line", CompilerMessageSeverity.ERROR);
        }
        return new ManualLanguageFeatureSetting(languageFeatureFromString, state, wholeArgument);
    }
}
