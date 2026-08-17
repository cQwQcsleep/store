package org.jetbrains.kotlin.cli.common.arguments;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BË\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012 \b\u0002\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000e0\r0\u0003\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u001a\b\u0002\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\r0\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0015\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\bHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J!\u0010+\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000e0\r0\u0003HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u001b\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\r0\u0003HÆ\u0003JÍ\u0001\u0010.\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032 \b\u0002\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000e0\r0\u00032\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u001a\b\u0002\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\r0\u0003HÆ\u0001J\u0014\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00102\u001a\u000203HÖ\u0081\u0004J\n\u00104\u001a\u00020\u0004HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u001cR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0015\"\u0004\b\u001e\u0010\u001cR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u001cR)\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000e0\r0\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015R#\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\r0\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentParseErrors;", Argument.Delimiters.none, "unknownArgs", Argument.Delimiters.none, Argument.Delimiters.none, "unknownExtraFlags", "extraArgumentsPassedInObsoleteForm", "deprecatedArguments", Argument.Delimiters.none, "argumentsWithoutValue", "booleanArgumentsWithIncorrectValue", "booleanLangFeatureArgumentsWithValue", "stringLangFeatureArgumentsWithIncorrectValue", "Lkotlin/Pair;", Argument.Delimiters.none, "argfileErrors", "internalArgumentsParsingProblems", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getUnknownArgs", "()Ljava/util/List;", "getUnknownExtraFlags", "getExtraArgumentsPassedInObsoleteForm", "getDeprecatedArguments", "()Ljava/util/Map;", "getArgumentsWithoutValue", "setArgumentsWithoutValue", "(Ljava/util/List;)V", "getBooleanArgumentsWithIncorrectValue", "setBooleanArgumentsWithIncorrectValue", "getBooleanLangFeatureArgumentsWithValue", "setBooleanLangFeatureArgumentsWithValue", "getStringLangFeatureArgumentsWithIncorrectValue", "getArgfileErrors", "getInternalArgumentsParsingProblems", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ArgumentParseErrors {
    private final List<String> argfileErrors;
    private List<String> argumentsWithoutValue;
    private List<String> booleanArgumentsWithIncorrectValue;
    private List<String> booleanLangFeatureArgumentsWithValue;
    private final Map<String, String> deprecatedArguments;
    private final List<String> extraArgumentsPassedInObsoleteForm;
    private final List<Pair<CompilerMessageSeverity, String>> internalArgumentsParsingProblems;
    private final List<Pair<String, Set<String>>> stringLangFeatureArgumentsWithIncorrectValue;
    private final List<String> unknownArgs;
    private final List<String> unknownExtraFlags;

    public /* synthetic */ ArgumentParseErrors(List list, List list2, List list3, Map map, List list4, List list5, List list6, List list7, List list8, List list9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new SmartList() : list, (i & 2) != 0 ? new SmartList() : list2, (i & 4) != 0 ? new SmartList() : list3, (i & 8) != 0 ? new LinkedHashMap() : map, (i & 16) != 0 ? new SmartList() : list4, (i & 32) != 0 ? new SmartList() : list5, (i & 64) != 0 ? new SmartList() : list6, (i & 128) != 0 ? new SmartList() : list7, (i & 256) != 0 ? new SmartList() : list8, (i & 512) != 0 ? new SmartList() : list9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArgumentParseErrors copy$default(ArgumentParseErrors argumentParseErrors, List list, List list2, List list3, Map map, List list4, List list5, List list6, List list7, List list8, List list9, int i, Object obj) {
        if ((i & 1) != 0) {
            list = argumentParseErrors.unknownArgs;
        }
        if ((i & 2) != 0) {
            list2 = argumentParseErrors.unknownExtraFlags;
        }
        if ((i & 4) != 0) {
            list3 = argumentParseErrors.extraArgumentsPassedInObsoleteForm;
        }
        if ((i & 8) != 0) {
            map = argumentParseErrors.deprecatedArguments;
        }
        if ((i & 16) != 0) {
            list4 = argumentParseErrors.argumentsWithoutValue;
        }
        if ((i & 32) != 0) {
            list5 = argumentParseErrors.booleanArgumentsWithIncorrectValue;
        }
        if ((i & 64) != 0) {
            list6 = argumentParseErrors.booleanLangFeatureArgumentsWithValue;
        }
        if ((i & 128) != 0) {
            list7 = argumentParseErrors.stringLangFeatureArgumentsWithIncorrectValue;
        }
        if ((i & 256) != 0) {
            list8 = argumentParseErrors.argfileErrors;
        }
        if ((i & 512) != 0) {
            list9 = argumentParseErrors.internalArgumentsParsingProblems;
        }
        List list10 = list8;
        List list11 = list9;
        List list12 = list6;
        List list13 = list7;
        List list14 = list4;
        List list15 = list5;
        return argumentParseErrors.copy(list, list2, list3, map, list14, list15, list12, list13, list10, list11);
    }

    public final List<String> component1() {
        return this.unknownArgs;
    }

    public final List<Pair<CompilerMessageSeverity, String>> component10() {
        return this.internalArgumentsParsingProblems;
    }

    public final List<String> component2() {
        return this.unknownExtraFlags;
    }

    public final List<String> component3() {
        return this.extraArgumentsPassedInObsoleteForm;
    }

    public final Map<String, String> component4() {
        return this.deprecatedArguments;
    }

    public final List<String> component5() {
        return this.argumentsWithoutValue;
    }

    public final List<String> component6() {
        return this.booleanArgumentsWithIncorrectValue;
    }

    public final List<String> component7() {
        return this.booleanLangFeatureArgumentsWithValue;
    }

    public final List<Pair<String, Set<String>>> component8() {
        return this.stringLangFeatureArgumentsWithIncorrectValue;
    }

    public final List<String> component9() {
        return this.argfileErrors;
    }

    public final ArgumentParseErrors copy(List<String> unknownArgs, List<String> unknownExtraFlags, List<String> extraArgumentsPassedInObsoleteForm, Map<String, String> deprecatedArguments, List<String> argumentsWithoutValue, List<String> booleanArgumentsWithIncorrectValue, List<String> booleanLangFeatureArgumentsWithValue, List<Pair<String, Set<String>>> stringLangFeatureArgumentsWithIncorrectValue, List<String> argfileErrors, List<Pair<CompilerMessageSeverity, String>> internalArgumentsParsingProblems) {
        unknownArgs.getClass();
        unknownExtraFlags.getClass();
        extraArgumentsPassedInObsoleteForm.getClass();
        deprecatedArguments.getClass();
        argumentsWithoutValue.getClass();
        booleanArgumentsWithIncorrectValue.getClass();
        booleanLangFeatureArgumentsWithValue.getClass();
        stringLangFeatureArgumentsWithIncorrectValue.getClass();
        argfileErrors.getClass();
        internalArgumentsParsingProblems.getClass();
        return new ArgumentParseErrors(unknownArgs, unknownExtraFlags, extraArgumentsPassedInObsoleteForm, deprecatedArguments, argumentsWithoutValue, booleanArgumentsWithIncorrectValue, booleanLangFeatureArgumentsWithValue, stringLangFeatureArgumentsWithIncorrectValue, argfileErrors, internalArgumentsParsingProblems);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArgumentParseErrors)) {
            return false;
        }
        ArgumentParseErrors argumentParseErrors = (ArgumentParseErrors) other;
        return Intrinsics.areEqual(this.unknownArgs, argumentParseErrors.unknownArgs) && Intrinsics.areEqual(this.unknownExtraFlags, argumentParseErrors.unknownExtraFlags) && Intrinsics.areEqual(this.extraArgumentsPassedInObsoleteForm, argumentParseErrors.extraArgumentsPassedInObsoleteForm) && Intrinsics.areEqual(this.deprecatedArguments, argumentParseErrors.deprecatedArguments) && Intrinsics.areEqual(this.argumentsWithoutValue, argumentParseErrors.argumentsWithoutValue) && Intrinsics.areEqual(this.booleanArgumentsWithIncorrectValue, argumentParseErrors.booleanArgumentsWithIncorrectValue) && Intrinsics.areEqual(this.booleanLangFeatureArgumentsWithValue, argumentParseErrors.booleanLangFeatureArgumentsWithValue) && Intrinsics.areEqual(this.stringLangFeatureArgumentsWithIncorrectValue, argumentParseErrors.stringLangFeatureArgumentsWithIncorrectValue) && Intrinsics.areEqual(this.argfileErrors, argumentParseErrors.argfileErrors) && Intrinsics.areEqual(this.internalArgumentsParsingProblems, argumentParseErrors.internalArgumentsParsingProblems);
    }

    public final List<String> getArgfileErrors() {
        return this.argfileErrors;
    }

    public final List<String> getArgumentsWithoutValue() {
        return this.argumentsWithoutValue;
    }

    public final List<String> getBooleanArgumentsWithIncorrectValue() {
        return this.booleanArgumentsWithIncorrectValue;
    }

    public final List<String> getBooleanLangFeatureArgumentsWithValue() {
        return this.booleanLangFeatureArgumentsWithValue;
    }

    public final Map<String, String> getDeprecatedArguments() {
        return this.deprecatedArguments;
    }

    public final List<String> getExtraArgumentsPassedInObsoleteForm() {
        return this.extraArgumentsPassedInObsoleteForm;
    }

    public final List<Pair<CompilerMessageSeverity, String>> getInternalArgumentsParsingProblems() {
        return this.internalArgumentsParsingProblems;
    }

    public final List<Pair<String, Set<String>>> getStringLangFeatureArgumentsWithIncorrectValue() {
        return this.stringLangFeatureArgumentsWithIncorrectValue;
    }

    public final List<String> getUnknownArgs() {
        return this.unknownArgs;
    }

    public final List<String> getUnknownExtraFlags() {
        return this.unknownExtraFlags;
    }

    public int hashCode() {
        return (((((((((((((((((this.unknownArgs.hashCode() * 31) + this.unknownExtraFlags.hashCode()) * 31) + this.extraArgumentsPassedInObsoleteForm.hashCode()) * 31) + this.deprecatedArguments.hashCode()) * 31) + this.argumentsWithoutValue.hashCode()) * 31) + this.booleanArgumentsWithIncorrectValue.hashCode()) * 31) + this.booleanLangFeatureArgumentsWithValue.hashCode()) * 31) + this.stringLangFeatureArgumentsWithIncorrectValue.hashCode()) * 31) + this.argfileErrors.hashCode()) * 31) + this.internalArgumentsParsingProblems.hashCode();
    }

    public final void setArgumentsWithoutValue(List<String> list) {
        list.getClass();
        this.argumentsWithoutValue = list;
    }

    public final void setBooleanArgumentsWithIncorrectValue(List<String> list) {
        list.getClass();
        this.booleanArgumentsWithIncorrectValue = list;
    }

    public final void setBooleanLangFeatureArgumentsWithValue(List<String> list) {
        list.getClass();
        this.booleanLangFeatureArgumentsWithValue = list;
    }

    public String toString() {
        return "ArgumentParseErrors(unknownArgs=" + this.unknownArgs + ", unknownExtraFlags=" + this.unknownExtraFlags + ", extraArgumentsPassedInObsoleteForm=" + this.extraArgumentsPassedInObsoleteForm + ", deprecatedArguments=" + this.deprecatedArguments + ", argumentsWithoutValue=" + this.argumentsWithoutValue + ", booleanArgumentsWithIncorrectValue=" + this.booleanArgumentsWithIncorrectValue + ", booleanLangFeatureArgumentsWithValue=" + this.booleanLangFeatureArgumentsWithValue + ", stringLangFeatureArgumentsWithIncorrectValue=" + this.stringLangFeatureArgumentsWithIncorrectValue + ", argfileErrors=" + this.argfileErrors + ", internalArgumentsParsingProblems=" + this.internalArgumentsParsingProblems + ')';
    }

    public ArgumentParseErrors(List<String> list, List<String> list2, List<String> list3, Map<String, String> map, List<String> list4, List<String> list5, List<String> list6, List<Pair<String, Set<String>>> list7, List<String> list8, List<Pair<CompilerMessageSeverity, String>> list9) {
        list.getClass();
        list2.getClass();
        list3.getClass();
        map.getClass();
        list4.getClass();
        list5.getClass();
        list6.getClass();
        list7.getClass();
        list8.getClass();
        list9.getClass();
        this.unknownArgs = list;
        this.unknownExtraFlags = list2;
        this.extraArgumentsPassedInObsoleteForm = list3;
        this.deprecatedArguments = map;
        this.argumentsWithoutValue = list4;
        this.booleanArgumentsWithIncorrectValue = list5;
        this.booleanLangFeatureArgumentsWithValue = list6;
        this.stringLangFeatureArgumentsWithIncorrectValue = list7;
        this.argfileErrors = list8;
        this.internalArgumentsParsingProblems = list9;
    }

    public ArgumentParseErrors() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }
}
