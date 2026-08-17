package org.jetbrains.kotlin.cli.common.arguments;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"configureCommonLanguageFeatures", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConfigureCommonLanguageFeaturesKt {
    public static final void configureCommonLanguageFeatures(Map<LanguageFeature, LanguageFeature.State> map, CommonCompilerArguments commonCompilerArguments) {
        map.getClass();
        commonCompilerArguments.getClass();
        if (commonCompilerArguments.getAllowAnyScriptsInSourceRoots()) {
            map.put(LanguageFeature.SkipStandaloneScriptsInSourceRoots, LanguageFeature.State.DISABLED);
        }
        if (commonCompilerArguments.getAllowConditionImpliesReturnsContracts()) {
            map.put(LanguageFeature.ConditionImpliesReturnsContracts, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getAllowContractsOnMoreFunctions()) {
            LanguageFeature languageFeature = LanguageFeature.AllowCheckForErasedTypesInContracts;
            LanguageFeature.State state = LanguageFeature.State.ENABLED;
            map.put(languageFeature, state);
            map.put(LanguageFeature.AllowContractsOnSomeOperators, state);
            map.put(LanguageFeature.AllowContractsOnPropertyAccessors, state);
        }
        if (commonCompilerArguments.getAllowHoldsinContract()) {
            map.put(LanguageFeature.HoldsInContracts, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getAllowReifiedTypeInCatch()) {
            map.put(LanguageFeature.AllowReifiedTypeInCatchClause, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getAllowReturnsResultOf()) {
            map.put(LanguageFeature.AllowReturnsResultOfContract, LanguageFeature.State.ENABLED);
        }
        if (Intrinsics.areEqual(commonCompilerArguments.getAnnotationDefaultTarget(), "first-only-warn")) {
            map.put(LanguageFeature.AnnotationDefaultTargetMigrationWarning, LanguageFeature.State.ENABLED);
            map.put(LanguageFeature.PropertyParamAnnotationDefaultTargetMode, LanguageFeature.State.DISABLED);
        }
        if (Intrinsics.areEqual(commonCompilerArguments.getAnnotationDefaultTarget(), "param-property")) {
            map.put(LanguageFeature.PropertyParamAnnotationDefaultTargetMode, LanguageFeature.State.ENABLED);
        }
        if (Intrinsics.areEqual(commonCompilerArguments.getAnnotationDefaultTarget(), "first-only")) {
            LanguageFeature languageFeature2 = LanguageFeature.AnnotationDefaultTargetMigrationWarning;
            LanguageFeature.State state2 = LanguageFeature.State.DISABLED;
            map.put(languageFeature2, state2);
            map.put(LanguageFeature.PropertyParamAnnotationDefaultTargetMode, state2);
        }
        if (commonCompilerArguments.getAnnotationTargetAll()) {
            map.put(LanguageFeature.AnnotationAllUseSiteTarget, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getCollectionLiterals()) {
            map.put(LanguageFeature.CollectionLiterals, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getConsistentDataClassCopyVisibility()) {
            map.put(LanguageFeature.DataClassCopyRespectsConstructorVisibility, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getContextParameters()) {
            map.put(LanguageFeature.ContextParameters, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getContextReceivers()) {
            map.put(LanguageFeature.ContextReceivers, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getContextSensitiveResolution()) {
            map.put(LanguageFeature.ContextSensitiveResolutionUsingExpectedType, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getDataFlowBasedExhaustiveness()) {
            map.put(LanguageFeature.DataFlowBasedExhaustiveness, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getDirectJavaActualization()) {
            map.put(LanguageFeature.DirectJavaActualization, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getExplicitBackingFields()) {
            map.put(LanguageFeature.ExplicitBackingFields, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getExplicitContextArguments()) {
            map.put(LanguageFeature.ExplicitContextArguments, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getInlineClasses()) {
            map.put(LanguageFeature.InlineClasses, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getIntrinsicConstEvaluation()) {
            map.put(LanguageFeature.IntrinsicConstEvaluation, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getLocalTypeAliases()) {
            map.put(LanguageFeature.LocalTypeAliases, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getMultiDollarInterpolation()) {
            map.put(LanguageFeature.MultiDollarInterpolation, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getMultiPlatform()) {
            map.put(LanguageFeature.MultiPlatformProjects, LanguageFeature.State.ENABLED);
        }
        if (Intrinsics.areEqual(commonCompilerArguments.getNameBasedDestructuring(), "only-syntax")) {
            map.put(LanguageFeature.NameBasedDestructuring, LanguageFeature.State.ENABLED);
        }
        if (Intrinsics.areEqual(commonCompilerArguments.getNameBasedDestructuring(), "name-mismatch")) {
            LanguageFeature languageFeature3 = LanguageFeature.NameBasedDestructuring;
            LanguageFeature.State state3 = LanguageFeature.State.ENABLED;
            map.put(languageFeature3, state3);
            map.put(LanguageFeature.DeprecateNameMismatchInShortDestructuringWithParentheses, state3);
        }
        if (Intrinsics.areEqual(commonCompilerArguments.getNameBasedDestructuring(), "complete")) {
            LanguageFeature languageFeature4 = LanguageFeature.NameBasedDestructuring;
            LanguageFeature.State state4 = LanguageFeature.State.ENABLED;
            map.put(languageFeature4, state4);
            map.put(LanguageFeature.DeprecateNameMismatchInShortDestructuringWithParentheses, state4);
            map.put(LanguageFeature.EnableNameBasedDestructuringShortForm, state4);
        }
        if (commonCompilerArguments.getNestedTypeAliases()) {
            map.put(LanguageFeature.NestedTypeAliases, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getNewInference()) {
            LanguageFeature languageFeature5 = LanguageFeature.NewInference;
            LanguageFeature.State state5 = LanguageFeature.State.ENABLED;
            map.put(languageFeature5, state5);
            map.put(LanguageFeature.SamConversionPerArgument, state5);
            map.put(LanguageFeature.FunctionReferenceWithDefaultValueAsOtherType, state5);
            map.put(LanguageFeature.DisableCompatibilityModeForNewInference, state5);
        }
        if (commonCompilerArguments.getNonLocalBreakContinue()) {
            map.put(LanguageFeature.BreakContinueInInlineLambdas, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getUnrestrictedBuilderInference()) {
            map.put(LanguageFeature.UnrestrictedBuilderInference, LanguageFeature.State.ENABLED);
        }
        if (commonCompilerArguments.getWhenGuards()) {
            map.put(LanguageFeature.WhenGuards, LanguageFeature.State.ENABLED);
        }
    }
}
