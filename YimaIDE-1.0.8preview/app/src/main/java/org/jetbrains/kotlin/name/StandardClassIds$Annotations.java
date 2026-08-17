package org.jetbrains.kotlin.name;

import java.util.HashSet;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bc\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001sB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0011\u00106\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007R\u0011\u00108\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0007R\u0011\u0010:\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0007R\u0011\u0010<\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0011\u0010>\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0007R\u0011\u0010@\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0007R\u0011\u0010B\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0007R\u0011\u0010D\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0007R\u0011\u0010F\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u0007R\u0011\u0010H\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0007R\u0011\u0010J\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\u0007R\u0011\u0010L\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\u0007R\u0011\u0010N\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\u0007R\u0011\u0010P\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\u0007R\u0011\u0010R\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bS\u0010\u0007R\u0011\u0010T\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\u0007R\u0011\u0010V\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bW\u0010\u0007R\u0011\u0010X\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bY\u0010\u0007R\u0011\u0010Z\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u0007R\u0011\u0010\\\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b]\u0010\u0007R\u0011\u0010^\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b_\u0010\u0007R\u0011\u0010`\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\ba\u0010\u0007R\u0011\u0010b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bc\u0010\u0007R\u0011\u0010d\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\be\u0010\u0007R\u0011\u0010f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bg\u0010\u0007R!\u0010h\u001a\u0012\u0012\u0004\u0012\u00020\u00050ij\b\u0012\u0004\u0012\u00020\u0005`j¢\u0006\b\n\u0000\u001a\u0004\bk\u0010lR\u0011\u0010m\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0007R\u0011\u0010o\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0007R\u0011\u0010q\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0007¨\u0006t"}, d2 = {"Lorg/jetbrains/kotlin/name/StandardClassIds$Annotations;", Argument.Delimiters.none, "<init>", "()V", "Suppress", "Lorg/jetbrains/kotlin/name/ClassId;", "getSuppress", "()Lorg/jetbrains/kotlin/name/ClassId;", "PublishedApi", "getPublishedApi", "SinceKotlin", "getSinceKotlin", "ExtensionFunctionType", "getExtensionFunctionType", "ContextFunctionTypeParams", "getContextFunctionTypeParams", "Deprecated", "getDeprecated", "DeprecatedSinceKotlin", "getDeprecatedSinceKotlin", "RequireKotlin", "getRequireKotlin", "DslMarker", "getDslMarker", "IntroducedAt", "getIntroducedAt", "ConsistentCopyVisibility", "getConsistentCopyVisibility", "ExposedCopyVisibility", "getExposedCopyVisibility", "HidesMembers", "getHidesMembers", "DynamicExtension", "getDynamicExtension", "IntrinsicConstEvaluation", "getIntrinsicConstEvaluation", "Retention", "getRetention", "Target", "getTarget", "Repeatable", "getRepeatable", "MustBeDocumented", "getMustBeDocumented", "ExpectRefinement", "getExpectRefinement", "Volatile", "getVolatile", "Test", "getTest", "RawTypeAnnotation", "getRawTypeAnnotation", "FlexibleNullability", "getFlexibleNullability", "FlexibleMutability", "getFlexibleMutability", "FlexibleArrayElementVariance", "getFlexibleArrayElementVariance", "EnhancedNullability", "getEnhancedNullability", "NoInfer", "getNoInfer", "FunctionN", "getFunctionN", "InlineOnly", "getInlineOnly", "OnlyInputTypes", "getOnlyInputTypes", "RestrictsSuspension", "getRestrictsSuspension", "WasExperimental", "getWasExperimental", "MustUseReturnValues", "getMustUseReturnValues", "IgnorableReturnValue", "getIgnorableReturnValue", "AccessibleLateinitPropertyLiteral", "getAccessibleLateinitPropertyLiteral", "OptionalExpectation", "getOptionalExpectation", "ImplicitlyActualizedByJvmDeclaration", "getImplicitlyActualizedByJvmDeclaration", "KotlinActual", "getKotlinActual", "jvmStatic", "getJvmStatic", "jvmName", "getJvmName", "Transient", "getTransient", "jsExport", "getJsExport", "jsExportIgnore", "getJsExportIgnore", "jsExportDefault", "getJsExportDefault", "jsNoDispatchReceiver", "getJsNoDispatchReceiver", "jsNoRuntime", "getJsNoRuntime", "AssociatedObjectKey", "getAssociatedObjectKey", "ExperimentalAssociatedObjects", "getExperimentalAssociatedObjects", "associatedObjectAnnotations", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getAssociatedObjectAnnotations", "()Ljava/util/HashSet;", "JvmBuiltin", "getJvmBuiltin", "SuppressBytecodeGeneration", "getSuppressBytecodeGeneration", "UsedFromCompilerGeneratedCode", "getUsedFromCompilerGeneratedCode", "ParameterNames", "org.jetbrains.kotlin:names"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StandardClassIds$Annotations {
    private static final ClassId AssociatedObjectKey;
    private static final ClassId ExperimentalAssociatedObjects;
    private static final ClassId JvmBuiltin;
    private static final ClassId SuppressBytecodeGeneration;
    private static final ClassId UsedFromCompilerGeneratedCode;
    private static final HashSet<ClassId> associatedObjectAnnotations;
    private static final ClassId jsExport;
    private static final ClassId jsExportDefault;
    private static final ClassId jsExportIgnore;
    private static final ClassId jsNoDispatchReceiver;
    private static final ClassId jsNoRuntime;
    public static final StandardClassIds$Annotations INSTANCE = new StandardClassIds$Annotations();
    private static final ClassId Suppress = StandardClassIdsKt.access$baseId("Suppress");
    private static final ClassId PublishedApi = StandardClassIdsKt.access$baseId("PublishedApi");
    private static final ClassId SinceKotlin = StandardClassIdsKt.access$baseId("SinceKotlin");
    private static final ClassId ExtensionFunctionType = StandardClassIdsKt.access$baseId("ExtensionFunctionType");
    private static final ClassId ContextFunctionTypeParams = StandardClassIdsKt.access$baseId("ContextFunctionTypeParams");
    private static final ClassId Deprecated = StandardClassIdsKt.access$baseId("Deprecated");
    private static final ClassId DeprecatedSinceKotlin = StandardClassIdsKt.access$baseId("DeprecatedSinceKotlin");
    private static final ClassId RequireKotlin = StandardClassIdsKt.access$internalId("RequireKotlin");
    private static final ClassId DslMarker = StandardClassIdsKt.access$baseId("DslMarker");
    private static final ClassId IntroducedAt = StandardClassIdsKt.access$baseId("IntroducedAt");
    private static final ClassId ConsistentCopyVisibility = StandardClassIdsKt.access$baseId("ConsistentCopyVisibility");
    private static final ClassId ExposedCopyVisibility = StandardClassIdsKt.access$baseId("ExposedCopyVisibility");
    private static final ClassId HidesMembers = StandardClassIdsKt.access$internalId("HidesMembers");
    private static final ClassId DynamicExtension = StandardClassIdsKt.access$internalId("DynamicExtension");
    private static final ClassId IntrinsicConstEvaluation = StandardClassIdsKt.access$internalId("IntrinsicConstEvaluation");
    private static final ClassId Retention = StandardClassIdsKt.access$annotationId("Retention");
    private static final ClassId Target = StandardClassIdsKt.access$annotationId("Target");
    private static final ClassId Repeatable = StandardClassIdsKt.access$annotationId("Repeatable");
    private static final ClassId MustBeDocumented = StandardClassIdsKt.access$annotationId("MustBeDocumented");
    private static final ClassId ExpectRefinement = StandardClassIdsKt.access$experimentalId("ExpectRefinement");
    private static final ClassId Volatile = StandardClassIdsKt.access$concurrentId("Volatile");
    private static final ClassId Test = StandardClassIdsKt.access$testId("Test");
    private static final ClassId RawTypeAnnotation = StandardClassIdsKt.access$internalIrId("RawType");
    private static final ClassId FlexibleNullability = StandardClassIdsKt.access$internalIrId("FlexibleNullability");
    private static final ClassId FlexibleMutability = StandardClassIdsKt.access$internalIrId("FlexibleMutability");
    private static final ClassId FlexibleArrayElementVariance = StandardClassIdsKt.access$internalIrId("FlexibleArrayElementVariance");
    private static final ClassId EnhancedNullability = StandardClassIdsKt.access$jvmInternalId("EnhancedNullability");
    private static final ClassId NoInfer = StandardClassIdsKt.access$internalId("NoInfer");
    private static final ClassId FunctionN = StandardClassIdsKt.access$jvmFunctionsId("FunctionN");
    private static final ClassId InlineOnly = StandardClassIdsKt.access$internalId("InlineOnly");
    private static final ClassId OnlyInputTypes = StandardClassIdsKt.access$internalId("OnlyInputTypes");
    private static final ClassId RestrictsSuspension = StandardClassIdsKt.access$coroutinesId("RestrictsSuspension");
    private static final ClassId WasExperimental = StandardClassIdsKt.access$baseId("WasExperimental");
    private static final ClassId MustUseReturnValues = StandardClassIdsKt.access$baseId("MustUseReturnValues");
    private static final ClassId IgnorableReturnValue = StandardClassIdsKt.access$baseId("IgnorableReturnValue");
    private static final ClassId AccessibleLateinitPropertyLiteral = StandardClassIdsKt.access$internalId("AccessibleLateinitPropertyLiteral");
    private static final ClassId OptionalExpectation = StandardClassIdsKt.access$baseId("OptionalExpectation");
    private static final ClassId ImplicitlyActualizedByJvmDeclaration = StandardClassIdsKt.access$jvmId("ImplicitlyActualizedByJvmDeclaration");
    private static final ClassId KotlinActual = StandardClassIdsKt.access$annotationsJvmId("KotlinActual");
    private static final ClassId jvmStatic = StandardClassIdsKt.access$jvmId("JvmStatic");
    private static final ClassId jvmName = StandardClassIdsKt.access$jvmId("JvmName");
    private static final ClassId Transient = StandardClassIdsKt.access$jvmId("Transient");

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/name/StandardClassIds$Annotations$ParameterNames;", Argument.Delimiters.none, "<init>", "()V", "value", "Lorg/jetbrains/kotlin/name/Name;", "getValue", "()Lorg/jetbrains/kotlin/name/Name;", "retentionValue", "getRetentionValue", "targetAllowedTargets", "getTargetAllowedTargets", "sinceKotlinVersion", "getSinceKotlinVersion", "deprecatedMessage", "getDeprecatedMessage", "deprecatedLevel", "getDeprecatedLevel", "deprecatedSinceKotlinWarningSince", "getDeprecatedSinceKotlinWarningSince", "deprecatedSinceKotlinErrorSince", "getDeprecatedSinceKotlinErrorSince", "deprecatedSinceKotlinHiddenSince", "getDeprecatedSinceKotlinHiddenSince", "suppressNames", "getSuppressNames", "parameterNameName", "getParameterNameName", "org.jetbrains.kotlin:names"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ParameterNames {
        public static final ParameterNames INSTANCE = new ParameterNames();
        private static final Name deprecatedLevel;
        private static final Name deprecatedMessage;
        private static final Name deprecatedSinceKotlinErrorSince;
        private static final Name deprecatedSinceKotlinHiddenSince;
        private static final Name deprecatedSinceKotlinWarningSince;
        private static final Name parameterNameName;
        private static final Name retentionValue;
        private static final Name sinceKotlinVersion;
        private static final Name suppressNames;
        private static final Name targetAllowedTargets;
        private static final Name value;

        static {
            Name nameIdentifier = Name.identifier("value");
            nameIdentifier.getClass();
            value = nameIdentifier;
            retentionValue = nameIdentifier;
            Name nameIdentifier2 = Name.identifier("allowedTargets");
            nameIdentifier2.getClass();
            targetAllowedTargets = nameIdentifier2;
            Name nameIdentifier3 = Name.identifier("version");
            nameIdentifier3.getClass();
            sinceKotlinVersion = nameIdentifier3;
            Name nameIdentifier4 = Name.identifier("message");
            nameIdentifier4.getClass();
            deprecatedMessage = nameIdentifier4;
            Name nameIdentifier5 = Name.identifier("level");
            nameIdentifier5.getClass();
            deprecatedLevel = nameIdentifier5;
            Name nameIdentifier6 = Name.identifier("warningSince");
            nameIdentifier6.getClass();
            deprecatedSinceKotlinWarningSince = nameIdentifier6;
            Name nameIdentifier7 = Name.identifier("errorSince");
            nameIdentifier7.getClass();
            deprecatedSinceKotlinErrorSince = nameIdentifier7;
            Name nameIdentifier8 = Name.identifier("hiddenSince");
            nameIdentifier8.getClass();
            deprecatedSinceKotlinHiddenSince = nameIdentifier8;
            Name nameIdentifier9 = Name.identifier("names");
            nameIdentifier9.getClass();
            suppressNames = nameIdentifier9;
            Name nameIdentifier10 = Name.identifier(ModuleXmlParser.NAME);
            nameIdentifier10.getClass();
            parameterNameName = nameIdentifier10;
        }

        private ParameterNames() {
        }

        public final Name getDeprecatedLevel() {
            return deprecatedLevel;
        }

        public final Name getDeprecatedMessage() {
            return deprecatedMessage;
        }

        public final Name getDeprecatedSinceKotlinErrorSince() {
            return deprecatedSinceKotlinErrorSince;
        }

        public final Name getDeprecatedSinceKotlinHiddenSince() {
            return deprecatedSinceKotlinHiddenSince;
        }

        public final Name getDeprecatedSinceKotlinWarningSince() {
            return deprecatedSinceKotlinWarningSince;
        }

        public final Name getParameterNameName() {
            return parameterNameName;
        }

        public final Name getRetentionValue() {
            return retentionValue;
        }

        public final Name getSinceKotlinVersion() {
            return sinceKotlinVersion;
        }

        public final Name getSuppressNames() {
            return suppressNames;
        }

        public final Name getTargetAllowedTargets() {
            return targetAllowedTargets;
        }

        public final Name getValue() {
            return value;
        }
    }

    static {
        ClassId classIdAccess$jsId = StandardClassIdsKt.access$jsId("JsExport");
        jsExport = classIdAccess$jsId;
        Name nameIdentifier = Name.identifier("Ignore");
        nameIdentifier.getClass();
        jsExportIgnore = classIdAccess$jsId.createNestedClassId(nameIdentifier);
        Name nameIdentifier2 = Name.identifier("Default");
        nameIdentifier2.getClass();
        jsExportDefault = classIdAccess$jsId.createNestedClassId(nameIdentifier2);
        jsNoDispatchReceiver = StandardClassIdsKt.access$jsId("JsNoDispatchReceiver");
        jsNoRuntime = StandardClassIdsKt.access$jsId("JsNoRuntime");
        ClassId classIdAccess$reflectId = StandardClassIdsKt.access$reflectId("AssociatedObjectKey");
        AssociatedObjectKey = classIdAccess$reflectId;
        ClassId classIdAccess$reflectId2 = StandardClassIdsKt.access$reflectId("ExperimentalAssociatedObjects");
        ExperimentalAssociatedObjects = classIdAccess$reflectId2;
        associatedObjectAnnotations = SetsKt.hashSetOf(new ClassId[]{classIdAccess$reflectId, classIdAccess$reflectId2});
        JvmBuiltin = StandardClassIdsKt.access$internalId("JvmBuiltin");
        SuppressBytecodeGeneration = StandardClassIdsKt.access$internalId("SuppressBytecodeGeneration");
        UsedFromCompilerGeneratedCode = StandardClassIdsKt.access$internalId("UsedFromCompilerGeneratedCode");
    }

    private StandardClassIds$Annotations() {
    }

    public final ClassId getAccessibleLateinitPropertyLiteral() {
        return AccessibleLateinitPropertyLiteral;
    }

    public final HashSet<ClassId> getAssociatedObjectAnnotations() {
        return associatedObjectAnnotations;
    }

    public final ClassId getAssociatedObjectKey() {
        return AssociatedObjectKey;
    }

    public final ClassId getConsistentCopyVisibility() {
        return ConsistentCopyVisibility;
    }

    public final ClassId getContextFunctionTypeParams() {
        return ContextFunctionTypeParams;
    }

    public final ClassId getDeprecated() {
        return Deprecated;
    }

    public final ClassId getDeprecatedSinceKotlin() {
        return DeprecatedSinceKotlin;
    }

    public final ClassId getDslMarker() {
        return DslMarker;
    }

    public final ClassId getDynamicExtension() {
        return DynamicExtension;
    }

    public final ClassId getEnhancedNullability() {
        return EnhancedNullability;
    }

    public final ClassId getExpectRefinement() {
        return ExpectRefinement;
    }

    public final ClassId getExperimentalAssociatedObjects() {
        return ExperimentalAssociatedObjects;
    }

    public final ClassId getExposedCopyVisibility() {
        return ExposedCopyVisibility;
    }

    public final ClassId getExtensionFunctionType() {
        return ExtensionFunctionType;
    }

    public final ClassId getFlexibleArrayElementVariance() {
        return FlexibleArrayElementVariance;
    }

    public final ClassId getFlexibleMutability() {
        return FlexibleMutability;
    }

    public final ClassId getFlexibleNullability() {
        return FlexibleNullability;
    }

    public final ClassId getFunctionN() {
        return FunctionN;
    }

    public final ClassId getHidesMembers() {
        return HidesMembers;
    }

    public final ClassId getIgnorableReturnValue() {
        return IgnorableReturnValue;
    }

    public final ClassId getImplicitlyActualizedByJvmDeclaration() {
        return ImplicitlyActualizedByJvmDeclaration;
    }

    public final ClassId getInlineOnly() {
        return InlineOnly;
    }

    public final ClassId getIntrinsicConstEvaluation() {
        return IntrinsicConstEvaluation;
    }

    public final ClassId getIntroducedAt() {
        return IntroducedAt;
    }

    public final ClassId getJsExport() {
        return jsExport;
    }

    public final ClassId getJsExportDefault() {
        return jsExportDefault;
    }

    public final ClassId getJsExportIgnore() {
        return jsExportIgnore;
    }

    public final ClassId getJsNoDispatchReceiver() {
        return jsNoDispatchReceiver;
    }

    public final ClassId getJsNoRuntime() {
        return jsNoRuntime;
    }

    public final ClassId getJvmBuiltin() {
        return JvmBuiltin;
    }

    public final ClassId getJvmName() {
        return jvmName;
    }

    public final ClassId getJvmStatic() {
        return jvmStatic;
    }

    public final ClassId getKotlinActual() {
        return KotlinActual;
    }

    public final ClassId getMustBeDocumented() {
        return MustBeDocumented;
    }

    public final ClassId getMustUseReturnValues() {
        return MustUseReturnValues;
    }

    public final ClassId getNoInfer() {
        return NoInfer;
    }

    public final ClassId getOnlyInputTypes() {
        return OnlyInputTypes;
    }

    public final ClassId getOptionalExpectation() {
        return OptionalExpectation;
    }

    public final ClassId getPublishedApi() {
        return PublishedApi;
    }

    public final ClassId getRawTypeAnnotation() {
        return RawTypeAnnotation;
    }

    public final ClassId getRepeatable() {
        return Repeatable;
    }

    public final ClassId getRequireKotlin() {
        return RequireKotlin;
    }

    public final ClassId getRestrictsSuspension() {
        return RestrictsSuspension;
    }

    public final ClassId getRetention() {
        return Retention;
    }

    public final ClassId getSinceKotlin() {
        return SinceKotlin;
    }

    public final ClassId getSuppress() {
        return Suppress;
    }

    public final ClassId getSuppressBytecodeGeneration() {
        return SuppressBytecodeGeneration;
    }

    public final ClassId getTarget() {
        return Target;
    }

    public final ClassId getTest() {
        return Test;
    }

    public final ClassId getTransient() {
        return Transient;
    }

    public final ClassId getUsedFromCompilerGeneratedCode() {
        return UsedFromCompilerGeneratedCode;
    }

    public final ClassId getVolatile() {
        return Volatile;
    }

    public final ClassId getWasExperimental() {
        return WasExperimental;
    }
}
