package org.jetbrains.kotlin.config;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.utils.DescriptionAware;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'TypeAliases' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0003\b¸\u0003\b\u0086\u0081\u0002\u0018\u0000 Å\u00032\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0004Ä\u0003Å\u0003BY\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010B\u001b\b\u0012\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0011B#\b\u0012\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0012R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010¾\u0003\u001a\u00020\u00078F¢\u0006\u0007\u001a\u0005\b¿\u0003\u0010\u0018R\u0013\u0010À\u0003\u001a\u00020\u00078F¢\u0006\u0007\u001a\u0005\bÁ\u0003\u0010\u0018R\u0013\u0010Â\u0003\u001a\u00020\t8F¢\u0006\u0007\u001a\u0005\bÃ\u0003\u0010\u001aj\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bgj\u0002\bhj\u0002\bij\u0002\bjj\u0002\bkj\u0002\blj\u0002\bmj\u0002\bnj\u0002\boj\u0002\bpj\u0002\bqj\u0002\brj\u0002\bsj\u0002\btj\u0002\buj\u0002\bvj\u0002\bwj\u0002\bxj\u0002\byj\u0002\bzj\u0002\b{j\u0002\b|j\u0002\b}j\u0002\b~j\u0002\b\u007fj\u0003\b\u0080\u0001j\u0003\b\u0081\u0001j\u0003\b\u0082\u0001j\u0003\b\u0083\u0001j\u0003\b\u0084\u0001j\u0003\b\u0085\u0001j\u0003\b\u0086\u0001j\u0003\b\u0087\u0001j\u0003\b\u0088\u0001j\u0003\b\u0089\u0001j\u0003\b\u008a\u0001j\u0003\b\u008b\u0001j\u0003\b\u008c\u0001j\u0003\b\u008d\u0001j\u0003\b\u008e\u0001j\u0003\b\u008f\u0001j\u0003\b\u0090\u0001j\u0003\b\u0091\u0001j\u0003\b\u0092\u0001j\u0003\b\u0093\u0001j\u0003\b\u0094\u0001j\u0003\b\u0095\u0001j\u0003\b\u0096\u0001j\u0003\b\u0097\u0001j\u0003\b\u0098\u0001j\u0003\b\u0099\u0001j\u0003\b\u009a\u0001j\u0003\b\u009b\u0001j\u0003\b\u009c\u0001j\u0003\b\u009d\u0001j\u0003\b\u009e\u0001j\u0003\b\u009f\u0001j\u0003\b \u0001j\u0003\b¡\u0001j\u0003\b¢\u0001j\u0003\b£\u0001j\u0003\b¤\u0001j\u0003\b¥\u0001j\u0003\b¦\u0001j\u0003\b§\u0001j\u0003\b¨\u0001j\u0003\b©\u0001j\u0003\bª\u0001j\u0003\b«\u0001j\u0003\b¬\u0001j\u0003\b\u00ad\u0001j\u0003\b®\u0001j\u0003\b¯\u0001j\u0003\b°\u0001j\u0003\b±\u0001j\u0003\b²\u0001j\u0003\b³\u0001j\u0003\b´\u0001j\u0003\bµ\u0001j\u0003\b¶\u0001j\u0003\b·\u0001j\u0003\b¸\u0001j\u0003\b¹\u0001j\u0003\bº\u0001j\u0003\b»\u0001j\u0003\b¼\u0001j\u0003\b½\u0001j\u0003\b¾\u0001j\u0003\b¿\u0001j\u0003\bÀ\u0001j\u0003\bÁ\u0001j\u0003\bÂ\u0001j\u0003\bÃ\u0001j\u0003\bÄ\u0001j\u0003\bÅ\u0001j\u0003\bÆ\u0001j\u0003\bÇ\u0001j\u0003\bÈ\u0001j\u0003\bÉ\u0001j\u0003\bÊ\u0001j\u0003\bË\u0001j\u0003\bÌ\u0001j\u0003\bÍ\u0001j\u0003\bÎ\u0001j\u0003\bÏ\u0001j\u0003\bÐ\u0001j\u0003\bÑ\u0001j\u0003\bÒ\u0001j\u0003\bÓ\u0001j\u0003\bÔ\u0001j\u0003\bÕ\u0001j\u0003\bÖ\u0001j\u0003\b×\u0001j\u0003\bØ\u0001j\u0003\bÙ\u0001j\u0003\bÚ\u0001j\u0003\bÛ\u0001j\u0003\bÜ\u0001j\u0003\bÝ\u0001j\u0003\bÞ\u0001j\u0003\bß\u0001j\u0003\bà\u0001j\u0003\bá\u0001j\u0003\bâ\u0001j\u0003\bã\u0001j\u0003\bä\u0001j\u0003\bå\u0001j\u0003\bæ\u0001j\u0003\bç\u0001j\u0003\bè\u0001j\u0003\bé\u0001j\u0003\bê\u0001j\u0003\bë\u0001j\u0003\bì\u0001j\u0003\bí\u0001j\u0003\bî\u0001j\u0003\bï\u0001j\u0003\bð\u0001j\u0003\bñ\u0001j\u0003\bò\u0001j\u0003\bó\u0001j\u0003\bô\u0001j\u0003\bõ\u0001j\u0003\bö\u0001j\u0003\b÷\u0001j\u0003\bø\u0001j\u0003\bù\u0001j\u0003\bú\u0001j\u0003\bû\u0001j\u0003\bü\u0001j\u0003\bý\u0001j\u0003\bþ\u0001j\u0003\bÿ\u0001j\u0003\b\u0080\u0002j\u0003\b\u0081\u0002j\u0003\b\u0082\u0002j\u0003\b\u0083\u0002j\u0003\b\u0084\u0002j\u0003\b\u0085\u0002j\u0003\b\u0086\u0002j\u0003\b\u0087\u0002j\u0003\b\u0088\u0002j\u0003\b\u0089\u0002j\u0003\b\u008a\u0002j\u0003\b\u008b\u0002j\u0003\b\u008c\u0002j\u0003\b\u008d\u0002j\u0003\b\u008e\u0002j\u0003\b\u008f\u0002j\u0003\b\u0090\u0002j\u0003\b\u0091\u0002j\u0003\b\u0092\u0002j\u0003\b\u0093\u0002j\u0003\b\u0094\u0002j\u0003\b\u0095\u0002j\u0003\b\u0096\u0002j\u0003\b\u0097\u0002j\u0003\b\u0098\u0002j\u0003\b\u0099\u0002j\u0003\b\u009a\u0002j\u0003\b\u009b\u0002j\u0003\b\u009c\u0002j\u0003\b\u009d\u0002j\u0003\b\u009e\u0002j\u0003\b\u009f\u0002j\u0003\b \u0002j\u0003\b¡\u0002j\u0003\b¢\u0002j\u0003\b£\u0002j\u0003\b¤\u0002j\u0003\b¥\u0002j\u0003\b¦\u0002j\u0003\b§\u0002j\u0003\b¨\u0002j\u0003\b©\u0002j\u0003\bª\u0002j\u0003\b«\u0002j\u0003\b¬\u0002j\u0003\b\u00ad\u0002j\u0003\b®\u0002j\u0003\b¯\u0002j\u0003\b°\u0002j\u0003\b±\u0002j\u0003\b²\u0002j\u0003\b³\u0002j\u0003\b´\u0002j\u0003\bµ\u0002j\u0003\b¶\u0002j\u0003\b·\u0002j\u0003\b¸\u0002j\u0003\b¹\u0002j\u0003\bº\u0002j\u0003\b»\u0002j\u0003\b¼\u0002j\u0003\b½\u0002j\u0003\b¾\u0002j\u0003\b¿\u0002j\u0003\bÀ\u0002j\u0003\bÁ\u0002j\u0003\bÂ\u0002j\u0003\bÃ\u0002j\u0003\bÄ\u0002j\u0003\bÅ\u0002j\u0003\bÆ\u0002j\u0003\bÇ\u0002j\u0003\bÈ\u0002j\u0003\bÉ\u0002j\u0003\bÊ\u0002j\u0003\bË\u0002j\u0003\bÌ\u0002j\u0003\bÍ\u0002j\u0003\bÎ\u0002j\u0003\bÏ\u0002j\u0003\bÐ\u0002j\u0003\bÑ\u0002j\u0003\bÒ\u0002j\u0003\bÓ\u0002j\u0003\bÔ\u0002j\u0003\bÕ\u0002j\u0003\bÖ\u0002j\u0003\b×\u0002j\u0003\bØ\u0002j\u0003\bÙ\u0002j\u0003\bÚ\u0002j\u0003\bÛ\u0002j\u0003\bÜ\u0002j\u0003\bÝ\u0002j\u0003\bÞ\u0002j\u0003\bß\u0002j\u0003\bà\u0002j\u0003\bá\u0002j\u0003\bâ\u0002j\u0003\bã\u0002j\u0003\bä\u0002j\u0003\bå\u0002j\u0003\bæ\u0002j\u0003\bç\u0002j\u0003\bè\u0002j\u0003\bé\u0002j\u0003\bê\u0002j\u0003\bë\u0002j\u0003\bì\u0002j\u0003\bí\u0002j\u0003\bî\u0002j\u0003\bï\u0002j\u0003\bð\u0002j\u0003\bñ\u0002j\u0003\bò\u0002j\u0003\bó\u0002j\u0003\bô\u0002j\u0003\bõ\u0002j\u0003\bö\u0002j\u0003\b÷\u0002j\u0003\bø\u0002j\u0003\bù\u0002j\u0003\bú\u0002j\u0003\bû\u0002j\u0003\bü\u0002j\u0003\bý\u0002j\u0003\bþ\u0002j\u0003\bÿ\u0002j\u0003\b\u0080\u0003j\u0003\b\u0081\u0003j\u0003\b\u0082\u0003j\u0003\b\u0083\u0003j\u0003\b\u0084\u0003j\u0003\b\u0085\u0003j\u0003\b\u0086\u0003j\u0003\b\u0087\u0003j\u0003\b\u0088\u0003j\u0003\b\u0089\u0003j\u0003\b\u008a\u0003j\u0003\b\u008b\u0003j\u0003\b\u008c\u0003j\u0003\b\u008d\u0003j\u0003\b\u008e\u0003j\u0003\b\u008f\u0003j\u0003\b\u0090\u0003j\u0003\b\u0091\u0003j\u0003\b\u0092\u0003j\u0003\b\u0093\u0003j\u0003\b\u0094\u0003j\u0003\b\u0095\u0003j\u0003\b\u0096\u0003j\u0003\b\u0097\u0003j\u0003\b\u0098\u0003j\u0003\b\u0099\u0003j\u0003\b\u009a\u0003j\u0003\b\u009b\u0003j\u0003\b\u009c\u0003j\u0003\b\u009d\u0003j\u0003\b\u009e\u0003j\u0003\b\u009f\u0003j\u0003\b \u0003j\u0003\b¡\u0003j\u0003\b¢\u0003j\u0003\b£\u0003j\u0003\b¤\u0003j\u0003\b¥\u0003j\u0003\b¦\u0003j\u0003\b§\u0003j\u0003\b¨\u0003j\u0003\b©\u0003j\u0003\bª\u0003j\u0003\b«\u0003j\u0003\b¬\u0003j\u0003\b\u00ad\u0003j\u0003\b®\u0003j\u0003\b¯\u0003j\u0003\b°\u0003j\u0003\b±\u0003j\u0003\b²\u0003j\u0003\b³\u0003j\u0003\b´\u0003j\u0003\bµ\u0003j\u0003\b¶\u0003j\u0003\b·\u0003j\u0003\b¸\u0003j\u0003\b¹\u0003j\u0003\bº\u0003j\u0003\b»\u0003j\u0003\b¼\u0003j\u0003\b½\u0003¨\u0006Æ\u0003"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageFeature;", Argument.Delimiters.none, "sinceVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "sinceApiVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "issue", Argument.Delimiters.none, "enabledInProgressiveMode", Argument.Delimiters.none, "forcesPreReleaseBinaries", "testOnly", "hintUrl", "behaviorAfterSinceVersion", "Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion;", "<init>", "(Ljava/lang/String;ILorg/jetbrains/kotlin/config/LanguageVersion;Lorg/jetbrains/kotlin/config/ApiVersion;Ljava/lang/String;ZZZLjava/lang/String;Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion;)V", "(Ljava/lang/String;ILorg/jetbrains/kotlin/config/LanguageVersion;Ljava/lang/String;)V", "(Ljava/lang/String;ILorg/jetbrains/kotlin/config/LanguageVersion;ZLjava/lang/String;)V", "getSinceVersion", "()Lorg/jetbrains/kotlin/config/LanguageVersion;", "getSinceApiVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "getIssue", "()Ljava/lang/String;", "getForcesPreReleaseBinaries", "()Z", "getTestOnly", "getHintUrl", "getBehaviorAfterSinceVersion", "()Lorg/jetbrains/kotlin/config/LanguageFeatureBehaviorAfterSinceVersion;", "TypeAliases", "BoundCallableReferences", "LocalDelegatedProperties", "TopLevelSealedInheritance", "AdditionalBuiltInsMembers", "DataClassInheritance", "InlineProperties", "DestructuringLambdaParameters", "SingleUnderscoreForParameterName", "DslMarkersSupport", "UnderscoresInNumericLiterals", "DivisionByZeroInConstantExpressions", "InlineConstVals", "OperatorProvideDelegate", "ShortSyntaxForPropertyGetters", "RefinedSamAdaptersPriority", "SafeCallBoundSmartCasts", "TypeInferenceOnGenericsForCallableReferences", "NoDelegationToJavaDefaultInterfaceMembers", "Coroutines", "InlineDefaultFunctionalParameters", "SoundSmartCastsAfterTry", "NullabilityAssertionOnExtensionReceiver", "SafeCastCheckBoundSmartCasts", "CapturedInClosureSmartCasts", "LateinitTopLevelProperties", "LateinitLocalVariables", "InnerClassInEnumEntryClass", "CallableReferencesToClassMembersWithEmptyLHS", "JvmPackageName", "AssigningArraysToVarargsInNamedFormInAnnotations", "ExpectedTypeFromCast", "RestrictionOfValReassignmentViaBackingField", "NestedClassesInEnumEntryShouldBeInner", "ProhibitDataClassesOverridingCopy", "RestrictionOfWrongAnnotationsWithUseSiteTargetsOnTypes", "ProhibitInnerClassesOfGenericClassExtendingThrowable", "ProperForInArrayLoopRangeVariableAssignmentSemantic", "NestedClassesInAnnotations", "JvmStaticInInterface", "JvmFieldInInterface", "ProhibitVisibilityOfNestedClassifiersFromSupertypesOfCompanion", "ProhibitNonConstValuesAsVarargsInAnnotations", "ReleaseCoroutines", "ReadDeserializedContracts", "UseReturnsEffect", "UseCallsInPlaceEffect", "AllowContractsForCustomFunctions", "VariableDeclarationInWhenSubject", "ProhibitLocalAnnotations", "ProhibitSmartcastsOnLocalDelegatedProperty", "ProhibitAssigningSingleElementsToVarargsInNamedForm", "FunctionTypesWithBigArity", "RestrictRetentionForExpressionAnnotations", "StrictJavaNullabilityAssertions", "SoundSmartcastForEnumEntries", "ProhibitErroneousExpressionsInAnnotationsWithUseSiteTargets", "NewCapturedReceiverFieldNamingConvention", "ExtendedMainConvention", "ExperimentalBuilderInference", "InlineClasses", "DslMarkerOnFunctionTypeReceiver", "RestrictReturnStatementTarget", "WarningOnMainUnusedParameter", "PolymorphicSignature", "ProhibitConcurrentHashMapContains", "ProhibitTypeParametersForLocalVariables", "ProhibitJvmOverloadsOnConstructorsOfAnnotationClasses", "ProhibitTypeParametersInAnonymousObjects", "ProhibitRepeatedUseSiteTargetAnnotations", "ProhibitUseSiteTargetAnnotationsOnSuperTypes", "ProhibitTypeParametersInClassLiteralsInAnnotationArguments", "ProhibitComparisonOfIncompatibleEnums", "BareArrayClassLiteral", "ProhibitGenericArrayClassLiteral", "NonParenthesizedAnnotationsOnFunctionalTypes", "UseGetterNameForPropertyAnnotationsMethodOnJvm", "AllowBreakAndContinueInsideWhen", "MixedNamedArgumentsInTheirOwnPosition", "ProhibitTailrecOnVirtualMember", "ProperComputationOrderOfTailrecDefaultParameters", "TrailingCommas", "ProhibitProtectedCallFromInline", "ProperFinally", "AllowAssigningArrayElementsToVarargsInNamedFormForFunctions", "AllowNullOperatorsForResult", "PreferJavaFieldOverload", "AllowContractsForNonOverridableMembers", "AllowReifiedGenericsInContracts", "ProperVisibilityForCompanionObjectInstanceField", "DoNotGenerateThrowsForDelegatedKotlinMembers", "ProperIeee754Comparisons", "FunctionalInterfaceConversion", "GenerateJvmOverloadsAsFinal", "MangleClassMembersReturningInlineClasses", "ImproveReportingDiagnosticsOnProtectedMembersOfBaseClass", "NewInference", "SamConversionForKotlinFunctions", "SamConversionPerArgument", "FunctionReferenceWithDefaultValueAsOtherType", "OverloadResolutionByLambdaReturnType", "ContractsOnCallsWithImplicitReceiver", "ProhibitSpreadOnSignaturePolymorphicCall", "ProhibitInvisibleAbstractMethodsInSuperclasses", "ProhibitNonReifiedArraysAsReifiedTypeArguments", "ProhibitVarargAsArrayAfterSamArgument", "CorrectSourceMappingSyntax", "RequiredPrimaryConstructorDelegationCallInEnums", "ApproximateAnonymousReturnTypesInPrivateInlineFunctions", "ForbidReferencingToUnderscoreNamedParameterOfCatchBlock", "UseCorrectExecutionOrderForVarargArguments", "JvmRecordSupport", "AllowNullOperatorsForResultAndResultReturnTypeByDefault", "AllowSealedInheritorsInDifferentFilesOfSamePackage", "SealedInterfaces", "JvmInlineValueClasses", "SuspendFunctionsInFunInterfaces", "SamWrapperClassesAreSynthetic", "StrictOnlyInputTypesChecks", "ProhibitJvmFieldOnOverrideFromInterfaceInPrimaryConstructor", "PrivateInFileEffectiveVisibility", "ProhibitSelfCallsInNestedObjects", "ProperCheckAnnotationsTargetInTypeUsePositions", "SuspendFunctionAsSupertype", "UnrestrictedBuilderInference", "ClassTypeParameterAnnotations", "WarnAboutNonExhaustiveWhenOnAlgebraicTypes", "InstantiationOfAnnotationClasses", "OptInContagiousSignatures", "RepeatableAnnotations", "RepeatableAnnotationContainerConstraints", "UseBuilderInferenceOnlyIfNeeded", "SuspendConversion", "ProhibitSuperCallsFromPublicInline", "ProhibitProtectedConstructorCallFromPublicInline", "TypeEnhancementImprovementsInStrictMode", "OptInRelease", "ProhibitNonExhaustiveWhenOnAlgebraicTypes", "UseBuilderInferenceWithoutAnnotation", "ProhibitSmartcastsOnPropertyFromAlienBaseClass", "ProhibitInvalidCharsInNativeIdentifiers", "DefinitelyNonNullableTypes", "ProhibitSimplificationOfNonTrivialConstBooleanExpressions", "SafeCallsAreAlwaysNullable", "JvmPermittedSubclassesAttributeForSealed", "ProperTypeInferenceConstraintsProcessing", "ForbidExposingTypesInPrimaryConstructorProperties", "PartiallySpecifiedTypeArguments", "EliminateAmbiguitiesWithExternalTypeParameters", "EliminateAmbiguitiesOnInheritedSamInterfaces", "ProperInternalVisibilityCheckInImportingScope", "InlineClassImplementationByDelegation", "QualifiedSupertypeMayBeExtendedByOtherSupertype", "YieldIsNoMoreReserved", "NoDeprecationOnDeprecatedEnumEntries", "ProhibitQualifiedAccessToUninitializedEnumEntry", "ForbidRecursiveDelegateExpressions", "KotlinFunInterfaceConstructorReference", "SuspendOnlySamConversions", "DontLoseDiagnosticsDuringOverloadResolutionByReturnType", "ProhibitConfusingSyntaxInWhenBranches", "UseConsistentRulesForPrivateConstructorsOfSealedClasses", "ProgressionsChangingResolve", "AbstractClassMemberNotImplementedWithIntermediateAbstractClass", "ForbidSuperDelegationToAbstractAnyMethod", "ProperEqualityChecksInBuilderInferenceCalls", "ProhibitNonExhaustiveIfInRhsOfElvis", "ReportMissingUpperBoundsViolatedErrorOnAbbreviationAtSupertypes", "ForbidUsingExtensionPropertyTypeParameterInDelegate", "SynchronizedSuspendError", "ReportNonVarargSpreadOnGenericCalls", "RangeUntilOperator", "GenericInlineClassParameter", "ProhibitIllegalValueParameterUsageInDefaultArguments", "ProhibitConstructorCallOnFunctionalSupertype", "ProhibitArrayLiteralsInCompanionOfAnnotation", "ProhibitCyclesInAnnotations", "ForbidExtensionFunctionTypeOnNonFunctionTypes", "ProhibitEnumDeclaringClass", "StopPropagatingDeprecationThroughOverrides", "ReportTypeVarianceConflictOnQualifierArguments", "ReportErrorsOnRecursiveTypeInsidePlusAssignment", "ForbidExtensionCallsOnInlineFunctionalParameters", "SkipStandaloneScriptsInSourceRoots", "ModifierNonBuiltinSuspendFunError", "EnumEntries", "ForbidSuperDelegationToAbstractFakeOverride", "DataObjects", "ProhibitAccessToEnumCompanionMembersInEnumConstructorCall", "RefineTypeCheckingOnAssignmentsToJavaFields", "ValueClassesSecondaryConstructorWithBody", "NativeJsProhibitLateinitIsInitializedIntrinsicWithoutPrivateAccess", "TakeIntoAccountEffectivelyFinalInMustBeInitializedCheck", "ProhibitUsingNullableTypeParameterAgainstNotNullAnnotated", "NoSourceCodeInNotNullAssertionExceptions", "MultiplatformRestrictions", "EnhanceNullabilityOfPrimitiveArrays", "AllowEmptyIntersectionsInResultTypeResolver", "ProhibitSmartcastsOnPropertyFromAlienBaseClassInheritedInInvisibleClass", "ForbidInferringPostponedTypeVariableIntoDeclaredUpperBound", "ProhibitUseSiteGetTargetAnnotations", "KeepNullabilityWhenApproximatingLocalType", "ProhibitAccessToInvisibleSetterFromDerivedClass", "ProhibitOpenValDeferredInitialization", "SupportEffectivelyFinalInExpectActualVisibilityCheck", "ProhibitMissedMustBeInitializedWhenThereIsNoPrimaryConstructor", "MangleCallsToJavaMethodsWithValueClasses", "ProhibitDefaultArgumentsInExpectActualizedByFakeOverride", "DisableCompatibilityModeForNewInference", "DfaBooleanVariables", "LightweightLambdas", "ObjCSignatureOverrideAnnotation", "JsAllowValueClassesInExternals", "ProhibitImplementingVarByInheritedVal", "PrioritizedEnumEntries", "ProhibitInlineModifierOnPrimaryConstructorParameters", "ProhibitSingleNamedFunctionAsExpression", "ForbidLambdaParameterWithMissingDependencyType", "JsAllowInvalidCharsIdentifiersEscaping", "SupportJavaErrorEnhancementOfArgumentsOfWarningLevelEnhanced", "ProhibitPrivateOperatorCallInInline", "ProhibitTypealiasAsCallableQualifierInImport", "JsExternalPropertyParameters", "CorrectSpecificityCheckForSignedAndUnsigned", "AllowAccessToProtectedFieldFromSuperCompanion", "CheckLambdaAgainstTypeVariableContradictionInResolution", "ProperUninitializedEnumEntryAccessAnalysis", "ImprovedCapturedTypeApproximationInInference", "ImprovedVarianceInCst", "InferMoreImplicationsFromBooleanExpressions", "ImprovedExhaustivenessChecksIn21", "ProhibitSynchronizationByValueClassesAndPrimitives", "AllowSuperCallToJavaInterface", "ProhibitJavaClassInheritingPrivateKotlinClass", "ProhibitReturningIncorrectNullabilityValuesFromSamConstructorLambdaOfJdkInterfaces", "ProhibitNothingAsCatchParameter", "NullableNothingInReifiedPosition", "ElvisInferenceImprovementsIn21", "ConsiderForkPointsWhenCheckingContradictions", "AvoidApproximationOfRecursiveCapturedTypesWithNoReason", "PCLAEnhancementsIn21", "InferenceEnhancementsIn21", "StricterConstraintIncorporationRecursionDetector", "ForkIsNotSuccessfulWhenNoBranchIsSuccessful", "BreakContinueInInlineLambdas", "ForbidUsingExpressionTypesWithInaccessibleContent", "ReportExposedTypeForMoreCasesOfTypeParameterBounds", "ForbidReifiedTypeParametersOnTypeAliases", "ForbidProjectionsInAnnotationProperties", "ForbidJvmAnnotationsOnAnnotationParameters", "ForbidFieldAnnotationsOnAnnotationParameters", "ProhibitConstructorAndSupertypeOnTypealiasWithTypeProjection", "CallableReferenceOverloadResolutionInLambda", "ProhibitGenericQualifiersOnConstructorCalls", "AvoidWrongOptimizationOfTypeOperatorsOnValueClasses", "ForbidSyntheticPropertiesWithoutBaseJavaGetter", "AnnotationDefaultTargetMigrationWarning", "AllowDnnTypeOverridingFlexibleType", "ForbidEnumEntryNamedEntries", "WhenGuards", "MultiDollarInterpolation", "JvmDefaultEnableByDefault", "ForbidExposureOfPrivateTypesInNonPrivateInlineFunctionsInKlibs", "FixationEnhancementsIn22", "ForbidCrossFileIrFieldAccessInKlibs", "AllowExpectDeclarationsInJsExport", "DoNotRunSuspendConversionForLambdaReturnStatements", "JvmNullOutSpilledCoroutineLocals", "CapturedTypeApproximationReworked", "ForbidCompanionInLocalInnerClass", "ForbidImplementationByDelegationWithDifferentGenericSignature", "ForbidJvmSerializableLambdaOnInlinedFunctionLiterals", "ReportExposedTypeForInternalTypeParameterBounds", "EnableDfaWarningsInK2", "ResolveTopLevelLambdasAsSyntheticCallArgument", "DataFlowBasedExhaustiveness", "UnstableSmartcastOnDelegatedProperties", "ForbidAnnotationsWithUseSiteTargetOnExpressions", "ProhibitNullableTypeThroughTypealias", "ForbidObjectDelegationToItself", "JvmIndyAllowLambdasWithAnnotations", "NestedTypeAliases", "ProhibitIntersectionReifiedTypeParameter", "AllowCheckForErasedTypesInContracts", "AllowContractsOnSomeOperators", "AllowContractsOnPropertyAccessors", "ConditionImpliesReturnsContracts", "HoldsInContracts", "InferenceEnhancementsIn23", "AllowReturnInExpressionBodyWithExplicitType", "ParseLambdaWithSuspendModifier", "JsAllowLongInExportedDeclarations", "JsStaticInInterface", "IrRichCallableReferencesInKlibs", "AllowCallingJavaOpenSealedClassConstructor", "ImprovedExhaustivenessChecksIn23", "EqualityConstraintForOperatorsUnderAssignments", "AnnotationsInMetadata", "ForbidExposingLessVisibleTypesInInline", "ForbidCaptureInlinableLambdasInJsCode", "ForbidInitializationBeforeDeclarationInAnonymous", "ForbidPrivateToThisUnboundCallableReferences", "AllowReifiedTypeInCatchClause", "ApproximateLocalTypesInPublicDeclarations", "LocalVariableTargetedAnnotationOnDestructuring", "ForbidGetSetValueWithTooManyParameters", "ForbidInlineEnumEntries", "TurnTypeCheckWarningsIntoErrors", "ProhibitExtendingAnnotationClasses", "RefinedVarargConversionRulesForCallableReferences", "CheckOptInOnPureEnumEntries", "ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables", "ReportUpperBoundViolatedInCallArgumentInteractions", "CheckPackageInfoNullnessAnnotations", "ForbidTypeAliasWithMissingDependencyType", "ForbidImplicitTypeAnnotationWithMissingDependency", "ProperExhaustivenessCheckForJavaOpenSealedClass", "DisableMaxTypeDepthFromInitialConstraints", "JsAllowExportingSuspendFunctions", "ImprovedResolutionInSecondaryConstructors", "CacheLocalVariableScopes", "ContextParameters", "PropertyParamAnnotationDefaultTargetMode", "AnnotationAllUseSiteTarget", "OverloadResolutionSpecificityForEnhancedJvmPrimitiveWrappers", "ForbidTypeAliasToCompilerRequiredAnnotation", "ForbidArrayLiteralsInNonAnnotationContexts", "LexicographicVariableReadinessCalculation", "ForbidClassLiteralWithPotentiallyNullableReifiedLhs", "ForbidOverriddenDefaultParametersInInline", "ForbidRootIdePackageInCli", "ReportOptInUsageOnCompanionObjectAccesses", "ChangedIntersectionWithRecursiveCapturedType", "FixedUninitializedEnumCompanionCheck", "SkipHiddenObjectsInResolution", "NoDeprecationOnImportStatements", "JvmEnhancedBridges", "DiscriminateNothingAsNullabilityConstraintInInference", "ResolveEqualsRhsInDependentContextWithCompletion", "IrIntraModuleInlinerBeforeKlibSerialization", "ImprovedExhaustivenessCheckForSubjectVariable24", "DontMakeExplicitNullableJavaTypeArgumentsFlexible", "ExplicitBackingFields", "ProhibitFunctionCallsInDefaultParametersOfInline", "AllowNamedCompanionForJsExport", "AllowInterfaceNestedClassesInJsExport", "NativeTestProcessorBeforeSerialization", "JsAllowExportingValueClasses", "DontCreateSyntheticPropertiesWithoutBaseJavaGetter", "ErrorAboutDataClassCopyVisibilityChange", "KlibAnnotationsInMetadata", "ForbidReturnInExpressionBodyWithoutExplicitTypeEdgeCases", "ForbidExternalEnumEntriesAndPrimaryConstructorProperties", "ReportTypeVarianceConflictsInDnnAndFlexible", "ProperSupportOfInnerClassesInCallableReferenceLHS", "DontIgnoreUpperBoundViolatedOnImplicitArguments", "ForbidUpperBoundsViolationOnTypeOperatorAndParameterBounds", "ForbidUselessTypeArgumentsIn25", "WrapContinuationForTailCallFunctions", "EagerLambdaAnalysis", "AllowReturnsResultOfContract", "ExpectActualClasses", "DataClassCopyRespectsConstructorVisibility", "ForbidParenthesizedLhsInAssignments", "DirectJavaActualization", "IgnoreNullabilityForErasedValueParameters", "NoBuilderInferenceWithoutAnnotationRestriction", "ReportErrorsForComparisonOperators", "NoAdditionalErrorsInK1DiagnosticReporter", "ProhibitScriptTopLevelInnerClasses", "DisableSimplificationOfFlexibleUpperConstraintWithDnnLowerBound", "PreciseSimplificationToFlexibleLowerConstraint", "DiscriminateSuspendInOverloadResolution", "ExpectRefinement", "JsEnableExtensionFunctionInExternals", "PackagePrivateFileClassesWithAllPrivateMembers", "MultiPlatformProjects", "ProhibitComparisonOfIncompatibleClasses", "ProhibitAllMultipleDefaultsInheritedFromSupertypes", "FunctionalTypeWithExtensionAsSupertype", "ContextReceivers", "ExplicitContextArguments", "JvmInlineMultiFieldValueClasses", "JavaSamConversionEqualsHashCode", "AllowAnyAsAnActualTypeForExpectInterface", "CompanionBlocksAndExtensions", "NameBasedDestructuring", "DeprecateNameMismatchInShortDestructuringWithParentheses", "EnableNameBasedDestructuringShortForm", "LocalTypeAliases", "JsExposedNotExportedSuperInterfaceApiByExportedOne", "JsExportInterfacesInImplementableWay", "UnitConversionsOnArbitraryExpressions", "JsAllowImplementingFunctionInterface", "CustomEqualsInValueClasses", "ContractSyntaxV2", "ReferencesToSyntheticJavaProperties", "ImplicitSignedToUnsignedIntegerConversion", "ForbidInferringTypeVariablesIntoEmptyIntersection", "IntrinsicConstEvaluation", "DisableCheckingChangedProgressionsResolve", "CollectionLiterals", "ProperFieldAccessGenerationForFieldAccessShadowedByKotlinProperty", "IrCrossModuleInlinerBeforeKlibSerialization", "AllowEagerSupertypeAccessibilityChecks", "UnnamedLocalVariables", "ContextSensitiveResolutionUsingExpectedType", "DisableWarningsForValueBasedJavaClasses", "DisableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives", "ExportKlibToOlderAbiVersion", "ForbidInferOfInvisibleTypeAsReifiedVarargOrReturnType", "ForbidExposingPackagePrivateInInternal", "JvmLoadAnnotationsOnAnnotationProperties", "TreatProvideDelegateAsConventionName", "ExportKDocDocumentationToKlib", "presentableName", "getPresentableName", "presentableText", "getPresentableText", "actuallyEnabledInProgressiveMode", "getActuallyEnabledInProgressiveMode", "State", "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageFeature {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LanguageFeature[] $VALUES;
    public static final LanguageFeature AbstractClassMemberNotImplementedWithIntermediateAbstractClass;
    public static final LanguageFeature AdditionalBuiltInsMembers;
    public static final LanguageFeature AllowAccessToProtectedFieldFromSuperCompanion;
    public static final LanguageFeature AllowAnyAsAnActualTypeForExpectInterface;
    public static final LanguageFeature AllowAssigningArrayElementsToVarargsInNamedFormForFunctions;
    public static final LanguageFeature AllowBreakAndContinueInsideWhen;
    public static final LanguageFeature AllowCallingJavaOpenSealedClassConstructor;
    public static final LanguageFeature AllowCheckForErasedTypesInContracts;
    public static final LanguageFeature AllowContractsForCustomFunctions;
    public static final LanguageFeature AllowContractsForNonOverridableMembers;
    public static final LanguageFeature AllowContractsOnPropertyAccessors;
    public static final LanguageFeature AllowContractsOnSomeOperators;
    public static final LanguageFeature AllowDnnTypeOverridingFlexibleType;
    public static final LanguageFeature AllowEagerSupertypeAccessibilityChecks;
    public static final LanguageFeature AllowEmptyIntersectionsInResultTypeResolver;
    public static final LanguageFeature AllowExpectDeclarationsInJsExport;
    public static final LanguageFeature AllowInterfaceNestedClassesInJsExport;
    public static final LanguageFeature AllowNamedCompanionForJsExport;
    public static final LanguageFeature AllowNullOperatorsForResult;
    public static final LanguageFeature AllowNullOperatorsForResultAndResultReturnTypeByDefault;
    public static final LanguageFeature AllowReifiedGenericsInContracts;
    public static final LanguageFeature AllowReifiedTypeInCatchClause;
    public static final LanguageFeature AllowReturnInExpressionBodyWithExplicitType;
    public static final LanguageFeature AllowReturnsResultOfContract;
    public static final LanguageFeature AllowSealedInheritorsInDifferentFilesOfSamePackage;
    public static final LanguageFeature AllowSuperCallToJavaInterface;
    public static final LanguageFeature AnnotationAllUseSiteTarget;
    public static final LanguageFeature AnnotationDefaultTargetMigrationWarning;
    public static final LanguageFeature AnnotationsInMetadata;
    public static final LanguageFeature ApproximateAnonymousReturnTypesInPrivateInlineFunctions;
    public static final LanguageFeature ApproximateLocalTypesInPublicDeclarations;
    public static final LanguageFeature AssigningArraysToVarargsInNamedFormInAnnotations;
    public static final LanguageFeature AvoidApproximationOfRecursiveCapturedTypesWithNoReason;
    public static final LanguageFeature AvoidWrongOptimizationOfTypeOperatorsOnValueClasses;
    public static final LanguageFeature BareArrayClassLiteral;
    public static final LanguageFeature BoundCallableReferences;
    public static final LanguageFeature BreakContinueInInlineLambdas;
    public static final LanguageFeature CacheLocalVariableScopes;
    public static final LanguageFeature CallableReferenceOverloadResolutionInLambda;
    public static final LanguageFeature CallableReferencesToClassMembersWithEmptyLHS;
    public static final LanguageFeature CapturedInClosureSmartCasts;
    public static final LanguageFeature CapturedTypeApproximationReworked;
    public static final LanguageFeature ChangedIntersectionWithRecursiveCapturedType;
    public static final LanguageFeature CheckLambdaAgainstTypeVariableContradictionInResolution;
    public static final LanguageFeature CheckOptInOnPureEnumEntries;
    public static final LanguageFeature CheckPackageInfoNullnessAnnotations;
    public static final LanguageFeature ClassTypeParameterAnnotations;
    public static final LanguageFeature CollectionLiterals;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final LanguageFeature CompanionBlocksAndExtensions;
    public static final LanguageFeature ConditionImpliesReturnsContracts;
    public static final LanguageFeature ConsiderForkPointsWhenCheckingContradictions;
    public static final LanguageFeature ContextParameters;
    public static final LanguageFeature ContextReceivers;
    public static final LanguageFeature ContextSensitiveResolutionUsingExpectedType;
    public static final LanguageFeature ContractSyntaxV2;
    public static final LanguageFeature ContractsOnCallsWithImplicitReceiver;
    public static final LanguageFeature Coroutines;
    public static final LanguageFeature CorrectSourceMappingSyntax;
    public static final LanguageFeature CorrectSpecificityCheckForSignedAndUnsigned;
    public static final LanguageFeature CustomEqualsInValueClasses;
    public static final LanguageFeature DataClassCopyRespectsConstructorVisibility;
    public static final LanguageFeature DataClassInheritance;
    public static final LanguageFeature DataFlowBasedExhaustiveness;
    public static final LanguageFeature DataObjects;
    public static final LanguageFeature DefinitelyNonNullableTypes;
    public static final LanguageFeature DeprecateNameMismatchInShortDestructuringWithParentheses;
    public static final LanguageFeature DestructuringLambdaParameters;
    public static final LanguageFeature DfaBooleanVariables;
    public static final LanguageFeature DirectJavaActualization;
    public static final LanguageFeature DisableCheckingChangedProgressionsResolve;
    public static final LanguageFeature DisableCompatibilityModeForNewInference;
    public static final LanguageFeature DisableMaxTypeDepthFromInitialConstraints;
    public static final LanguageFeature DisableSimplificationOfFlexibleUpperConstraintWithDnnLowerBound;
    public static final LanguageFeature DisableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives;
    public static final LanguageFeature DisableWarningsForValueBasedJavaClasses;
    public static final LanguageFeature DiscriminateNothingAsNullabilityConstraintInInference;
    public static final LanguageFeature DiscriminateSuspendInOverloadResolution;
    public static final LanguageFeature DivisionByZeroInConstantExpressions;
    public static final LanguageFeature DoNotGenerateThrowsForDelegatedKotlinMembers;
    public static final LanguageFeature DoNotRunSuspendConversionForLambdaReturnStatements;
    public static final LanguageFeature DontCreateSyntheticPropertiesWithoutBaseJavaGetter;
    public static final LanguageFeature DontIgnoreUpperBoundViolatedOnImplicitArguments;
    public static final LanguageFeature DontLoseDiagnosticsDuringOverloadResolutionByReturnType;
    public static final LanguageFeature DontMakeExplicitNullableJavaTypeArgumentsFlexible;
    public static final LanguageFeature DslMarkerOnFunctionTypeReceiver;
    public static final LanguageFeature DslMarkersSupport;
    public static final LanguageFeature EagerLambdaAnalysis;
    public static final LanguageFeature EliminateAmbiguitiesOnInheritedSamInterfaces;
    public static final LanguageFeature EliminateAmbiguitiesWithExternalTypeParameters;
    public static final LanguageFeature ElvisInferenceImprovementsIn21;
    public static final LanguageFeature EnableDfaWarningsInK2;
    public static final LanguageFeature EnableNameBasedDestructuringShortForm;
    public static final LanguageFeature EnhanceNullabilityOfPrimitiveArrays;
    public static final LanguageFeature EnumEntries;
    public static final LanguageFeature EqualityConstraintForOperatorsUnderAssignments;
    public static final LanguageFeature ErrorAboutDataClassCopyVisibilityChange;
    public static final LanguageFeature ExpectActualClasses;
    public static final LanguageFeature ExpectRefinement;
    public static final LanguageFeature ExpectedTypeFromCast;
    public static final LanguageFeature ExperimentalBuilderInference;
    public static final LanguageFeature ExplicitBackingFields;
    public static final LanguageFeature ExplicitContextArguments;
    public static final LanguageFeature ExportKDocDocumentationToKlib;
    public static final LanguageFeature ExportKlibToOlderAbiVersion;
    public static final LanguageFeature ExtendedMainConvention;
    public static final LanguageFeature FixationEnhancementsIn22;
    public static final LanguageFeature FixedUninitializedEnumCompanionCheck;
    public static final LanguageFeature ForbidAnnotationsWithUseSiteTargetOnExpressions;
    public static final LanguageFeature ForbidArrayLiteralsInNonAnnotationContexts;
    public static final LanguageFeature ForbidCaptureInlinableLambdasInJsCode;
    public static final LanguageFeature ForbidClassLiteralWithPotentiallyNullableReifiedLhs;
    public static final LanguageFeature ForbidCompanionInLocalInnerClass;
    public static final LanguageFeature ForbidCrossFileIrFieldAccessInKlibs;
    public static final LanguageFeature ForbidEnumEntryNamedEntries;
    public static final LanguageFeature ForbidExposingLessVisibleTypesInInline;
    public static final LanguageFeature ForbidExposingPackagePrivateInInternal;
    public static final LanguageFeature ForbidExposingTypesInPrimaryConstructorProperties;
    public static final LanguageFeature ForbidExposureOfPrivateTypesInNonPrivateInlineFunctionsInKlibs;
    public static final LanguageFeature ForbidExtensionCallsOnInlineFunctionalParameters;
    public static final LanguageFeature ForbidExtensionFunctionTypeOnNonFunctionTypes;
    public static final LanguageFeature ForbidExternalEnumEntriesAndPrimaryConstructorProperties;
    public static final LanguageFeature ForbidFieldAnnotationsOnAnnotationParameters;
    public static final LanguageFeature ForbidGetSetValueWithTooManyParameters;
    public static final LanguageFeature ForbidImplementationByDelegationWithDifferentGenericSignature;
    public static final LanguageFeature ForbidImplicitTypeAnnotationWithMissingDependency;
    public static final LanguageFeature ForbidInferOfInvisibleTypeAsReifiedVarargOrReturnType;
    public static final LanguageFeature ForbidInferringPostponedTypeVariableIntoDeclaredUpperBound;
    public static final LanguageFeature ForbidInferringTypeVariablesIntoEmptyIntersection;
    public static final LanguageFeature ForbidInitializationBeforeDeclarationInAnonymous;
    public static final LanguageFeature ForbidInlineEnumEntries;
    public static final LanguageFeature ForbidJvmAnnotationsOnAnnotationParameters;
    public static final LanguageFeature ForbidJvmSerializableLambdaOnInlinedFunctionLiterals;
    public static final LanguageFeature ForbidLambdaParameterWithMissingDependencyType;
    public static final LanguageFeature ForbidObjectDelegationToItself;
    public static final LanguageFeature ForbidOverriddenDefaultParametersInInline;
    public static final LanguageFeature ForbidParenthesizedLhsInAssignments;
    public static final LanguageFeature ForbidPrivateToThisUnboundCallableReferences;
    public static final LanguageFeature ForbidProjectionsInAnnotationProperties;
    public static final LanguageFeature ForbidRecursiveDelegateExpressions;
    public static final LanguageFeature ForbidReferencingToUnderscoreNamedParameterOfCatchBlock;
    public static final LanguageFeature ForbidReifiedTypeParametersOnTypeAliases;
    public static final LanguageFeature ForbidReturnInExpressionBodyWithoutExplicitTypeEdgeCases;
    public static final LanguageFeature ForbidRootIdePackageInCli;
    public static final LanguageFeature ForbidSuperDelegationToAbstractAnyMethod;
    public static final LanguageFeature ForbidSuperDelegationToAbstractFakeOverride;
    public static final LanguageFeature ForbidSyntheticPropertiesWithoutBaseJavaGetter;
    public static final LanguageFeature ForbidTypeAliasToCompilerRequiredAnnotation;
    public static final LanguageFeature ForbidTypeAliasWithMissingDependencyType;
    public static final LanguageFeature ForbidUpperBoundsViolationOnTypeOperatorAndParameterBounds;
    public static final LanguageFeature ForbidUselessTypeArgumentsIn25;
    public static final LanguageFeature ForbidUsingExpressionTypesWithInaccessibleContent;
    public static final LanguageFeature ForbidUsingExtensionPropertyTypeParameterInDelegate;
    public static final LanguageFeature ForkIsNotSuccessfulWhenNoBranchIsSuccessful;
    public static final LanguageFeature FunctionReferenceWithDefaultValueAsOtherType;
    public static final LanguageFeature FunctionTypesWithBigArity;
    public static final LanguageFeature FunctionalInterfaceConversion;
    public static final LanguageFeature FunctionalTypeWithExtensionAsSupertype;
    public static final LanguageFeature GenerateJvmOverloadsAsFinal;
    public static final LanguageFeature GenericInlineClassParameter;
    public static final LanguageFeature HoldsInContracts;
    public static final LanguageFeature IgnoreNullabilityForErasedValueParameters;
    public static final LanguageFeature ImplicitSignedToUnsignedIntegerConversion;
    public static final LanguageFeature ImproveReportingDiagnosticsOnProtectedMembersOfBaseClass;
    public static final LanguageFeature ImprovedCapturedTypeApproximationInInference;
    public static final LanguageFeature ImprovedExhaustivenessCheckForSubjectVariable24;
    public static final LanguageFeature ImprovedExhaustivenessChecksIn21;
    public static final LanguageFeature ImprovedExhaustivenessChecksIn23;
    public static final LanguageFeature ImprovedResolutionInSecondaryConstructors;
    public static final LanguageFeature ImprovedVarianceInCst;
    public static final LanguageFeature InferMoreImplicationsFromBooleanExpressions;
    public static final LanguageFeature InferenceEnhancementsIn21;
    public static final LanguageFeature InferenceEnhancementsIn23;
    public static final LanguageFeature InlineClassImplementationByDelegation;
    public static final LanguageFeature InlineClasses;
    public static final LanguageFeature InlineConstVals;
    public static final LanguageFeature InlineDefaultFunctionalParameters;
    public static final LanguageFeature InlineProperties;
    public static final LanguageFeature InnerClassInEnumEntryClass;
    public static final LanguageFeature InstantiationOfAnnotationClasses;
    public static final LanguageFeature IntrinsicConstEvaluation;
    public static final LanguageFeature IrCrossModuleInlinerBeforeKlibSerialization;
    public static final LanguageFeature IrIntraModuleInlinerBeforeKlibSerialization;
    public static final LanguageFeature IrRichCallableReferencesInKlibs;
    public static final LanguageFeature JavaSamConversionEqualsHashCode;
    public static final LanguageFeature JsAllowExportingSuspendFunctions;
    public static final LanguageFeature JsAllowExportingValueClasses;
    public static final LanguageFeature JsAllowImplementingFunctionInterface;
    public static final LanguageFeature JsAllowInvalidCharsIdentifiersEscaping;
    public static final LanguageFeature JsAllowLongInExportedDeclarations;
    public static final LanguageFeature JsAllowValueClassesInExternals;
    public static final LanguageFeature JsEnableExtensionFunctionInExternals;
    public static final LanguageFeature JsExportInterfacesInImplementableWay;
    public static final LanguageFeature JsExposedNotExportedSuperInterfaceApiByExportedOne;
    public static final LanguageFeature JsExternalPropertyParameters;
    public static final LanguageFeature JsStaticInInterface;
    public static final LanguageFeature JvmDefaultEnableByDefault;
    public static final LanguageFeature JvmEnhancedBridges;
    public static final LanguageFeature JvmFieldInInterface;
    public static final LanguageFeature JvmIndyAllowLambdasWithAnnotations;
    public static final LanguageFeature JvmInlineMultiFieldValueClasses;
    public static final LanguageFeature JvmInlineValueClasses;
    public static final LanguageFeature JvmLoadAnnotationsOnAnnotationProperties;
    public static final LanguageFeature JvmNullOutSpilledCoroutineLocals;
    public static final LanguageFeature JvmPackageName;
    public static final LanguageFeature JvmPermittedSubclassesAttributeForSealed;
    public static final LanguageFeature JvmRecordSupport;
    public static final LanguageFeature JvmStaticInInterface;
    public static final LanguageFeature KeepNullabilityWhenApproximatingLocalType;
    public static final LanguageFeature KlibAnnotationsInMetadata;
    public static final LanguageFeature KotlinFunInterfaceConstructorReference;
    public static final LanguageFeature LateinitLocalVariables;
    public static final LanguageFeature LateinitTopLevelProperties;
    public static final LanguageFeature LexicographicVariableReadinessCalculation;
    public static final LanguageFeature LightweightLambdas;
    public static final LanguageFeature LocalDelegatedProperties;
    public static final LanguageFeature LocalTypeAliases;
    public static final LanguageFeature LocalVariableTargetedAnnotationOnDestructuring;
    public static final LanguageFeature MangleCallsToJavaMethodsWithValueClasses;
    public static final LanguageFeature MangleClassMembersReturningInlineClasses;
    public static final LanguageFeature MixedNamedArgumentsInTheirOwnPosition;
    public static final LanguageFeature ModifierNonBuiltinSuspendFunError;
    public static final LanguageFeature MultiDollarInterpolation;
    public static final LanguageFeature MultiPlatformProjects;
    public static final LanguageFeature MultiplatformRestrictions;
    public static final LanguageFeature NameBasedDestructuring;
    public static final LanguageFeature NativeJsProhibitLateinitIsInitializedIntrinsicWithoutPrivateAccess;
    public static final LanguageFeature NativeTestProcessorBeforeSerialization;
    public static final LanguageFeature NestedClassesInAnnotations;
    public static final LanguageFeature NestedClassesInEnumEntryShouldBeInner;
    public static final LanguageFeature NestedTypeAliases;
    public static final LanguageFeature NewCapturedReceiverFieldNamingConvention;
    public static final LanguageFeature NewInference;
    public static final LanguageFeature NoAdditionalErrorsInK1DiagnosticReporter;
    public static final LanguageFeature NoBuilderInferenceWithoutAnnotationRestriction;
    public static final LanguageFeature NoDelegationToJavaDefaultInterfaceMembers;
    public static final LanguageFeature NoDeprecationOnDeprecatedEnumEntries;
    public static final LanguageFeature NoDeprecationOnImportStatements;
    public static final LanguageFeature NoSourceCodeInNotNullAssertionExceptions;
    public static final LanguageFeature NonParenthesizedAnnotationsOnFunctionalTypes;
    public static final LanguageFeature NullabilityAssertionOnExtensionReceiver;
    public static final LanguageFeature NullableNothingInReifiedPosition;
    public static final LanguageFeature ObjCSignatureOverrideAnnotation;
    public static final LanguageFeature OperatorProvideDelegate;
    public static final LanguageFeature OptInContagiousSignatures;
    public static final LanguageFeature OptInRelease;
    public static final LanguageFeature OverloadResolutionByLambdaReturnType;
    public static final LanguageFeature OverloadResolutionSpecificityForEnhancedJvmPrimitiveWrappers;
    public static final LanguageFeature PCLAEnhancementsIn21;
    public static final LanguageFeature PackagePrivateFileClassesWithAllPrivateMembers;
    public static final LanguageFeature ParseLambdaWithSuspendModifier;
    public static final LanguageFeature PartiallySpecifiedTypeArguments;
    public static final LanguageFeature PolymorphicSignature;
    public static final LanguageFeature PreciseSimplificationToFlexibleLowerConstraint;
    public static final LanguageFeature PreferJavaFieldOverload;
    public static final LanguageFeature PrioritizedEnumEntries;
    public static final LanguageFeature PrivateInFileEffectiveVisibility;
    public static final LanguageFeature ProgressionsChangingResolve;
    public static final LanguageFeature ProhibitAccessToEnumCompanionMembersInEnumConstructorCall;
    public static final LanguageFeature ProhibitAccessToInvisibleSetterFromDerivedClass;
    public static final LanguageFeature ProhibitAllMultipleDefaultsInheritedFromSupertypes;
    public static final LanguageFeature ProhibitArrayLiteralsInCompanionOfAnnotation;
    public static final LanguageFeature ProhibitAssigningSingleElementsToVarargsInNamedForm;
    public static final LanguageFeature ProhibitComparisonOfIncompatibleClasses;
    public static final LanguageFeature ProhibitComparisonOfIncompatibleEnums;
    public static final LanguageFeature ProhibitConcurrentHashMapContains;
    public static final LanguageFeature ProhibitConfusingSyntaxInWhenBranches;
    public static final LanguageFeature ProhibitConstructorAndSupertypeOnTypealiasWithTypeProjection;
    public static final LanguageFeature ProhibitConstructorCallOnFunctionalSupertype;
    public static final LanguageFeature ProhibitCyclesInAnnotations;
    public static final LanguageFeature ProhibitDataClassesOverridingCopy;
    public static final LanguageFeature ProhibitDefaultArgumentsInExpectActualizedByFakeOverride;
    public static final LanguageFeature ProhibitEnumDeclaringClass;
    public static final LanguageFeature ProhibitErroneousExpressionsInAnnotationsWithUseSiteTargets;
    public static final LanguageFeature ProhibitExtendingAnnotationClasses;
    public static final LanguageFeature ProhibitFunctionCallsInDefaultParametersOfInline;
    public static final LanguageFeature ProhibitGenericArrayClassLiteral;
    public static final LanguageFeature ProhibitGenericQualifiersOnConstructorCalls;
    public static final LanguageFeature ProhibitIllegalValueParameterUsageInDefaultArguments;
    public static final LanguageFeature ProhibitImplementingVarByInheritedVal;
    public static final LanguageFeature ProhibitInlineModifierOnPrimaryConstructorParameters;
    public static final LanguageFeature ProhibitInnerClassesOfGenericClassExtendingThrowable;
    public static final LanguageFeature ProhibitIntersectionReifiedTypeParameter;
    public static final LanguageFeature ProhibitInvalidCharsInNativeIdentifiers;
    public static final LanguageFeature ProhibitInvisibleAbstractMethodsInSuperclasses;
    public static final LanguageFeature ProhibitJavaClassInheritingPrivateKotlinClass;
    public static final LanguageFeature ProhibitJvmFieldOnOverrideFromInterfaceInPrimaryConstructor;
    public static final LanguageFeature ProhibitJvmOverloadsOnConstructorsOfAnnotationClasses;
    public static final LanguageFeature ProhibitLocalAnnotations;
    public static final LanguageFeature ProhibitMissedMustBeInitializedWhenThereIsNoPrimaryConstructor;
    public static final LanguageFeature ProhibitNonConstValuesAsVarargsInAnnotations;
    public static final LanguageFeature ProhibitNonExhaustiveIfInRhsOfElvis;
    public static final LanguageFeature ProhibitNonExhaustiveWhenOnAlgebraicTypes;
    public static final LanguageFeature ProhibitNonReifiedArraysAsReifiedTypeArguments;
    public static final LanguageFeature ProhibitNothingAsCatchParameter;
    public static final LanguageFeature ProhibitNullableTypeThroughTypealias;
    public static final LanguageFeature ProhibitOpenValDeferredInitialization;
    public static final LanguageFeature ProhibitPrivateOperatorCallInInline;
    public static final LanguageFeature ProhibitProtectedCallFromInline;
    public static final LanguageFeature ProhibitProtectedConstructorCallFromPublicInline;
    public static final LanguageFeature ProhibitQualifiedAccessToUninitializedEnumEntry;
    public static final LanguageFeature ProhibitRepeatedUseSiteTargetAnnotations;
    public static final LanguageFeature ProhibitReturningIncorrectNullabilityValuesFromSamConstructorLambdaOfJdkInterfaces;
    public static final LanguageFeature ProhibitScriptTopLevelInnerClasses;
    public static final LanguageFeature ProhibitSelfCallsInNestedObjects;
    public static final LanguageFeature ProhibitSimplificationOfNonTrivialConstBooleanExpressions;
    public static final LanguageFeature ProhibitSingleNamedFunctionAsExpression;
    public static final LanguageFeature ProhibitSmartcastsOnLocalDelegatedProperty;
    public static final LanguageFeature ProhibitSmartcastsOnPropertyFromAlienBaseClass;
    public static final LanguageFeature ProhibitSmartcastsOnPropertyFromAlienBaseClassInheritedInInvisibleClass;
    public static final LanguageFeature ProhibitSpreadOnSignaturePolymorphicCall;
    public static final LanguageFeature ProhibitSuperCallsFromPublicInline;
    public static final LanguageFeature ProhibitSynchronizationByValueClassesAndPrimitives;
    public static final LanguageFeature ProhibitTailrecOnVirtualMember;
    public static final LanguageFeature ProhibitTypeParametersForLocalVariables;
    public static final LanguageFeature ProhibitTypeParametersInAnonymousObjects;
    public static final LanguageFeature ProhibitTypeParametersInClassLiteralsInAnnotationArguments;
    public static final LanguageFeature ProhibitTypealiasAsCallableQualifierInImport;
    public static final LanguageFeature ProhibitUseSiteGetTargetAnnotations;
    public static final LanguageFeature ProhibitUseSiteTargetAnnotationsOnSuperTypes;
    public static final LanguageFeature ProhibitUsingNullableTypeParameterAgainstNotNullAnnotated;
    public static final LanguageFeature ProhibitVarargAsArrayAfterSamArgument;
    public static final LanguageFeature ProhibitVisibilityOfNestedClassifiersFromSupertypesOfCompanion;
    public static final LanguageFeature ProperCheckAnnotationsTargetInTypeUsePositions;
    public static final LanguageFeature ProperComputationOrderOfTailrecDefaultParameters;
    public static final LanguageFeature ProperEqualityChecksInBuilderInferenceCalls;
    public static final LanguageFeature ProperExhaustivenessCheckForJavaOpenSealedClass;
    public static final LanguageFeature ProperFieldAccessGenerationForFieldAccessShadowedByKotlinProperty;
    public static final LanguageFeature ProperFinally;
    public static final LanguageFeature ProperForInArrayLoopRangeVariableAssignmentSemantic;
    public static final LanguageFeature ProperIeee754Comparisons;
    public static final LanguageFeature ProperInternalVisibilityCheckInImportingScope;
    public static final LanguageFeature ProperSupportOfInnerClassesInCallableReferenceLHS;
    public static final LanguageFeature ProperTypeInferenceConstraintsProcessing;
    public static final LanguageFeature ProperUninitializedEnumEntryAccessAnalysis;
    public static final LanguageFeature ProperVisibilityForCompanionObjectInstanceField;
    public static final LanguageFeature ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables;
    public static final LanguageFeature PropertyParamAnnotationDefaultTargetMode;
    public static final LanguageFeature QualifiedSupertypeMayBeExtendedByOtherSupertype;
    public static final LanguageFeature RangeUntilOperator;
    public static final LanguageFeature ReadDeserializedContracts;
    public static final LanguageFeature ReferencesToSyntheticJavaProperties;
    public static final LanguageFeature RefineTypeCheckingOnAssignmentsToJavaFields;
    public static final LanguageFeature RefinedSamAdaptersPriority;
    public static final LanguageFeature RefinedVarargConversionRulesForCallableReferences;
    public static final LanguageFeature ReleaseCoroutines;
    public static final LanguageFeature RepeatableAnnotationContainerConstraints;
    public static final LanguageFeature RepeatableAnnotations;
    public static final LanguageFeature ReportErrorsForComparisonOperators;
    public static final LanguageFeature ReportErrorsOnRecursiveTypeInsidePlusAssignment;
    public static final LanguageFeature ReportExposedTypeForInternalTypeParameterBounds;
    public static final LanguageFeature ReportExposedTypeForMoreCasesOfTypeParameterBounds;
    public static final LanguageFeature ReportMissingUpperBoundsViolatedErrorOnAbbreviationAtSupertypes;
    public static final LanguageFeature ReportNonVarargSpreadOnGenericCalls;
    public static final LanguageFeature ReportOptInUsageOnCompanionObjectAccesses;
    public static final LanguageFeature ReportTypeVarianceConflictOnQualifierArguments;
    public static final LanguageFeature ReportTypeVarianceConflictsInDnnAndFlexible;
    public static final LanguageFeature ReportUpperBoundViolatedInCallArgumentInteractions;
    public static final LanguageFeature RequiredPrimaryConstructorDelegationCallInEnums;
    public static final LanguageFeature ResolveEqualsRhsInDependentContextWithCompletion;
    public static final LanguageFeature ResolveTopLevelLambdasAsSyntheticCallArgument;
    public static final LanguageFeature RestrictRetentionForExpressionAnnotations;
    public static final LanguageFeature RestrictReturnStatementTarget;
    public static final LanguageFeature RestrictionOfValReassignmentViaBackingField;
    public static final LanguageFeature RestrictionOfWrongAnnotationsWithUseSiteTargetsOnTypes;
    public static final LanguageFeature SafeCallBoundSmartCasts;
    public static final LanguageFeature SafeCallsAreAlwaysNullable;
    public static final LanguageFeature SafeCastCheckBoundSmartCasts;
    public static final LanguageFeature SamConversionForKotlinFunctions;
    public static final LanguageFeature SamConversionPerArgument;
    public static final LanguageFeature SamWrapperClassesAreSynthetic;
    public static final LanguageFeature SealedInterfaces;
    public static final LanguageFeature ShortSyntaxForPropertyGetters;
    public static final LanguageFeature SingleUnderscoreForParameterName;
    public static final LanguageFeature SkipHiddenObjectsInResolution;
    public static final LanguageFeature SkipStandaloneScriptsInSourceRoots;
    public static final LanguageFeature SoundSmartCastsAfterTry;
    public static final LanguageFeature SoundSmartcastForEnumEntries;
    public static final LanguageFeature StopPropagatingDeprecationThroughOverrides;
    public static final LanguageFeature StrictJavaNullabilityAssertions;
    public static final LanguageFeature StrictOnlyInputTypesChecks;
    public static final LanguageFeature StricterConstraintIncorporationRecursionDetector;
    public static final LanguageFeature SupportEffectivelyFinalInExpectActualVisibilityCheck;
    public static final LanguageFeature SupportJavaErrorEnhancementOfArgumentsOfWarningLevelEnhanced;
    public static final LanguageFeature SuspendConversion;
    public static final LanguageFeature SuspendFunctionAsSupertype;
    public static final LanguageFeature SuspendFunctionsInFunInterfaces;
    public static final LanguageFeature SuspendOnlySamConversions;
    public static final LanguageFeature SynchronizedSuspendError;
    public static final LanguageFeature TakeIntoAccountEffectivelyFinalInMustBeInitializedCheck;
    public static final LanguageFeature TopLevelSealedInheritance;
    public static final LanguageFeature TrailingCommas;
    public static final LanguageFeature TreatProvideDelegateAsConventionName;
    public static final LanguageFeature TurnTypeCheckWarningsIntoErrors;
    public static final LanguageFeature TypeAliases;
    public static final LanguageFeature TypeEnhancementImprovementsInStrictMode;
    public static final LanguageFeature TypeInferenceOnGenericsForCallableReferences;
    public static final LanguageFeature UnderscoresInNumericLiterals;
    public static final LanguageFeature UnitConversionsOnArbitraryExpressions;
    public static final LanguageFeature UnnamedLocalVariables;
    public static final LanguageFeature UnrestrictedBuilderInference;
    public static final LanguageFeature UnstableSmartcastOnDelegatedProperties;
    public static final LanguageFeature UseBuilderInferenceOnlyIfNeeded;
    public static final LanguageFeature UseBuilderInferenceWithoutAnnotation;
    public static final LanguageFeature UseCallsInPlaceEffect;
    public static final LanguageFeature UseConsistentRulesForPrivateConstructorsOfSealedClasses;
    public static final LanguageFeature UseCorrectExecutionOrderForVarargArguments;
    public static final LanguageFeature UseGetterNameForPropertyAnnotationsMethodOnJvm;
    public static final LanguageFeature UseReturnsEffect;
    public static final LanguageFeature ValueClassesSecondaryConstructorWithBody;
    public static final LanguageFeature VariableDeclarationInWhenSubject;
    public static final LanguageFeature WarnAboutNonExhaustiveWhenOnAlgebraicTypes;
    public static final LanguageFeature WarningOnMainUnusedParameter;
    public static final LanguageFeature WhenGuards;
    public static final LanguageFeature WrapContinuationForTailCallFunctions;
    public static final LanguageFeature YieldIsNoMoreReserved;
    private final LanguageFeatureBehaviorAfterSinceVersion behaviorAfterSinceVersion;
    private final boolean enabledInProgressiveMode;
    private final boolean forcesPreReleaseBinaries;
    private final String hintUrl;
    private final String issue;
    private final ApiVersion sinceApiVersion;
    private final LanguageVersion sinceVersion;
    private final boolean testOnly;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "Lorg/jetbrains/kotlin/utils/DescriptionAware;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "ENABLED", "DISABLED", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum State implements DescriptionAware {
        ENABLED("Enabled"),
        DISABLED("Disabled");

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final String description;

        State(String str) {
            this.description = str;
        }

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }

        public String getDescription() {
            return this.description;
        }
    }

    private static final /* synthetic */ LanguageFeature[] $values() {
        return new LanguageFeature[]{TypeAliases, BoundCallableReferences, LocalDelegatedProperties, TopLevelSealedInheritance, AdditionalBuiltInsMembers, DataClassInheritance, InlineProperties, DestructuringLambdaParameters, SingleUnderscoreForParameterName, DslMarkersSupport, UnderscoresInNumericLiterals, DivisionByZeroInConstantExpressions, InlineConstVals, OperatorProvideDelegate, ShortSyntaxForPropertyGetters, RefinedSamAdaptersPriority, SafeCallBoundSmartCasts, TypeInferenceOnGenericsForCallableReferences, NoDelegationToJavaDefaultInterfaceMembers, Coroutines, InlineDefaultFunctionalParameters, SoundSmartCastsAfterTry, NullabilityAssertionOnExtensionReceiver, SafeCastCheckBoundSmartCasts, CapturedInClosureSmartCasts, LateinitTopLevelProperties, LateinitLocalVariables, InnerClassInEnumEntryClass, CallableReferencesToClassMembersWithEmptyLHS, JvmPackageName, AssigningArraysToVarargsInNamedFormInAnnotations, ExpectedTypeFromCast, RestrictionOfValReassignmentViaBackingField, NestedClassesInEnumEntryShouldBeInner, ProhibitDataClassesOverridingCopy, RestrictionOfWrongAnnotationsWithUseSiteTargetsOnTypes, ProhibitInnerClassesOfGenericClassExtendingThrowable, ProperForInArrayLoopRangeVariableAssignmentSemantic, NestedClassesInAnnotations, JvmStaticInInterface, JvmFieldInInterface, ProhibitVisibilityOfNestedClassifiersFromSupertypesOfCompanion, ProhibitNonConstValuesAsVarargsInAnnotations, ReleaseCoroutines, ReadDeserializedContracts, UseReturnsEffect, UseCallsInPlaceEffect, AllowContractsForCustomFunctions, VariableDeclarationInWhenSubject, ProhibitLocalAnnotations, ProhibitSmartcastsOnLocalDelegatedProperty, ProhibitAssigningSingleElementsToVarargsInNamedForm, FunctionTypesWithBigArity, RestrictRetentionForExpressionAnnotations, StrictJavaNullabilityAssertions, SoundSmartcastForEnumEntries, ProhibitErroneousExpressionsInAnnotationsWithUseSiteTargets, NewCapturedReceiverFieldNamingConvention, ExtendedMainConvention, ExperimentalBuilderInference, InlineClasses, DslMarkerOnFunctionTypeReceiver, RestrictReturnStatementTarget, WarningOnMainUnusedParameter, PolymorphicSignature, ProhibitConcurrentHashMapContains, ProhibitTypeParametersForLocalVariables, ProhibitJvmOverloadsOnConstructorsOfAnnotationClasses, ProhibitTypeParametersInAnonymousObjects, ProhibitRepeatedUseSiteTargetAnnotations, ProhibitUseSiteTargetAnnotationsOnSuperTypes, ProhibitTypeParametersInClassLiteralsInAnnotationArguments, ProhibitComparisonOfIncompatibleEnums, BareArrayClassLiteral, ProhibitGenericArrayClassLiteral, NonParenthesizedAnnotationsOnFunctionalTypes, UseGetterNameForPropertyAnnotationsMethodOnJvm, AllowBreakAndContinueInsideWhen, MixedNamedArgumentsInTheirOwnPosition, ProhibitTailrecOnVirtualMember, ProperComputationOrderOfTailrecDefaultParameters, TrailingCommas, ProhibitProtectedCallFromInline, ProperFinally, AllowAssigningArrayElementsToVarargsInNamedFormForFunctions, AllowNullOperatorsForResult, PreferJavaFieldOverload, AllowContractsForNonOverridableMembers, AllowReifiedGenericsInContracts, ProperVisibilityForCompanionObjectInstanceField, DoNotGenerateThrowsForDelegatedKotlinMembers, ProperIeee754Comparisons, FunctionalInterfaceConversion, GenerateJvmOverloadsAsFinal, MangleClassMembersReturningInlineClasses, ImproveReportingDiagnosticsOnProtectedMembersOfBaseClass, NewInference, SamConversionForKotlinFunctions, SamConversionPerArgument, FunctionReferenceWithDefaultValueAsOtherType, OverloadResolutionByLambdaReturnType, ContractsOnCallsWithImplicitReceiver, ProhibitSpreadOnSignaturePolymorphicCall, ProhibitInvisibleAbstractMethodsInSuperclasses, ProhibitNonReifiedArraysAsReifiedTypeArguments, ProhibitVarargAsArrayAfterSamArgument, CorrectSourceMappingSyntax, RequiredPrimaryConstructorDelegationCallInEnums, ApproximateAnonymousReturnTypesInPrivateInlineFunctions, ForbidReferencingToUnderscoreNamedParameterOfCatchBlock, UseCorrectExecutionOrderForVarargArguments, JvmRecordSupport, AllowNullOperatorsForResultAndResultReturnTypeByDefault, AllowSealedInheritorsInDifferentFilesOfSamePackage, SealedInterfaces, JvmInlineValueClasses, SuspendFunctionsInFunInterfaces, SamWrapperClassesAreSynthetic, StrictOnlyInputTypesChecks, ProhibitJvmFieldOnOverrideFromInterfaceInPrimaryConstructor, PrivateInFileEffectiveVisibility, ProhibitSelfCallsInNestedObjects, ProperCheckAnnotationsTargetInTypeUsePositions, SuspendFunctionAsSupertype, UnrestrictedBuilderInference, ClassTypeParameterAnnotations, WarnAboutNonExhaustiveWhenOnAlgebraicTypes, InstantiationOfAnnotationClasses, OptInContagiousSignatures, RepeatableAnnotations, RepeatableAnnotationContainerConstraints, UseBuilderInferenceOnlyIfNeeded, SuspendConversion, ProhibitSuperCallsFromPublicInline, ProhibitProtectedConstructorCallFromPublicInline, TypeEnhancementImprovementsInStrictMode, OptInRelease, ProhibitNonExhaustiveWhenOnAlgebraicTypes, UseBuilderInferenceWithoutAnnotation, ProhibitSmartcastsOnPropertyFromAlienBaseClass, ProhibitInvalidCharsInNativeIdentifiers, DefinitelyNonNullableTypes, ProhibitSimplificationOfNonTrivialConstBooleanExpressions, SafeCallsAreAlwaysNullable, JvmPermittedSubclassesAttributeForSealed, ProperTypeInferenceConstraintsProcessing, ForbidExposingTypesInPrimaryConstructorProperties, PartiallySpecifiedTypeArguments, EliminateAmbiguitiesWithExternalTypeParameters, EliminateAmbiguitiesOnInheritedSamInterfaces, ProperInternalVisibilityCheckInImportingScope, InlineClassImplementationByDelegation, QualifiedSupertypeMayBeExtendedByOtherSupertype, YieldIsNoMoreReserved, NoDeprecationOnDeprecatedEnumEntries, ProhibitQualifiedAccessToUninitializedEnumEntry, ForbidRecursiveDelegateExpressions, KotlinFunInterfaceConstructorReference, SuspendOnlySamConversions, DontLoseDiagnosticsDuringOverloadResolutionByReturnType, ProhibitConfusingSyntaxInWhenBranches, UseConsistentRulesForPrivateConstructorsOfSealedClasses, ProgressionsChangingResolve, AbstractClassMemberNotImplementedWithIntermediateAbstractClass, ForbidSuperDelegationToAbstractAnyMethod, ProperEqualityChecksInBuilderInferenceCalls, ProhibitNonExhaustiveIfInRhsOfElvis, ReportMissingUpperBoundsViolatedErrorOnAbbreviationAtSupertypes, ForbidUsingExtensionPropertyTypeParameterInDelegate, SynchronizedSuspendError, ReportNonVarargSpreadOnGenericCalls, RangeUntilOperator, GenericInlineClassParameter, ProhibitIllegalValueParameterUsageInDefaultArguments, ProhibitConstructorCallOnFunctionalSupertype, ProhibitArrayLiteralsInCompanionOfAnnotation, ProhibitCyclesInAnnotations, ForbidExtensionFunctionTypeOnNonFunctionTypes, ProhibitEnumDeclaringClass, StopPropagatingDeprecationThroughOverrides, ReportTypeVarianceConflictOnQualifierArguments, ReportErrorsOnRecursiveTypeInsidePlusAssignment, ForbidExtensionCallsOnInlineFunctionalParameters, SkipStandaloneScriptsInSourceRoots, ModifierNonBuiltinSuspendFunError, EnumEntries, ForbidSuperDelegationToAbstractFakeOverride, DataObjects, ProhibitAccessToEnumCompanionMembersInEnumConstructorCall, RefineTypeCheckingOnAssignmentsToJavaFields, ValueClassesSecondaryConstructorWithBody, NativeJsProhibitLateinitIsInitializedIntrinsicWithoutPrivateAccess, TakeIntoAccountEffectivelyFinalInMustBeInitializedCheck, ProhibitUsingNullableTypeParameterAgainstNotNullAnnotated, NoSourceCodeInNotNullAssertionExceptions, MultiplatformRestrictions, EnhanceNullabilityOfPrimitiveArrays, AllowEmptyIntersectionsInResultTypeResolver, ProhibitSmartcastsOnPropertyFromAlienBaseClassInheritedInInvisibleClass, ForbidInferringPostponedTypeVariableIntoDeclaredUpperBound, ProhibitUseSiteGetTargetAnnotations, KeepNullabilityWhenApproximatingLocalType, ProhibitAccessToInvisibleSetterFromDerivedClass, ProhibitOpenValDeferredInitialization, SupportEffectivelyFinalInExpectActualVisibilityCheck, ProhibitMissedMustBeInitializedWhenThereIsNoPrimaryConstructor, MangleCallsToJavaMethodsWithValueClasses, ProhibitDefaultArgumentsInExpectActualizedByFakeOverride, DisableCompatibilityModeForNewInference, DfaBooleanVariables, LightweightLambdas, ObjCSignatureOverrideAnnotation, JsAllowValueClassesInExternals, ProhibitImplementingVarByInheritedVal, PrioritizedEnumEntries, ProhibitInlineModifierOnPrimaryConstructorParameters, ProhibitSingleNamedFunctionAsExpression, ForbidLambdaParameterWithMissingDependencyType, JsAllowInvalidCharsIdentifiersEscaping, SupportJavaErrorEnhancementOfArgumentsOfWarningLevelEnhanced, ProhibitPrivateOperatorCallInInline, ProhibitTypealiasAsCallableQualifierInImport, JsExternalPropertyParameters, CorrectSpecificityCheckForSignedAndUnsigned, AllowAccessToProtectedFieldFromSuperCompanion, CheckLambdaAgainstTypeVariableContradictionInResolution, ProperUninitializedEnumEntryAccessAnalysis, ImprovedCapturedTypeApproximationInInference, ImprovedVarianceInCst, InferMoreImplicationsFromBooleanExpressions, ImprovedExhaustivenessChecksIn21, ProhibitSynchronizationByValueClassesAndPrimitives, AllowSuperCallToJavaInterface, ProhibitJavaClassInheritingPrivateKotlinClass, ProhibitReturningIncorrectNullabilityValuesFromSamConstructorLambdaOfJdkInterfaces, ProhibitNothingAsCatchParameter, NullableNothingInReifiedPosition, ElvisInferenceImprovementsIn21, ConsiderForkPointsWhenCheckingContradictions, AvoidApproximationOfRecursiveCapturedTypesWithNoReason, PCLAEnhancementsIn21, InferenceEnhancementsIn21, StricterConstraintIncorporationRecursionDetector, ForkIsNotSuccessfulWhenNoBranchIsSuccessful, BreakContinueInInlineLambdas, ForbidUsingExpressionTypesWithInaccessibleContent, ReportExposedTypeForMoreCasesOfTypeParameterBounds, ForbidReifiedTypeParametersOnTypeAliases, ForbidProjectionsInAnnotationProperties, ForbidJvmAnnotationsOnAnnotationParameters, ForbidFieldAnnotationsOnAnnotationParameters, ProhibitConstructorAndSupertypeOnTypealiasWithTypeProjection, CallableReferenceOverloadResolutionInLambda, ProhibitGenericQualifiersOnConstructorCalls, AvoidWrongOptimizationOfTypeOperatorsOnValueClasses, ForbidSyntheticPropertiesWithoutBaseJavaGetter, AnnotationDefaultTargetMigrationWarning, AllowDnnTypeOverridingFlexibleType, ForbidEnumEntryNamedEntries, WhenGuards, MultiDollarInterpolation, JvmDefaultEnableByDefault, ForbidExposureOfPrivateTypesInNonPrivateInlineFunctionsInKlibs, FixationEnhancementsIn22, ForbidCrossFileIrFieldAccessInKlibs, AllowExpectDeclarationsInJsExport, DoNotRunSuspendConversionForLambdaReturnStatements, JvmNullOutSpilledCoroutineLocals, CapturedTypeApproximationReworked, ForbidCompanionInLocalInnerClass, ForbidImplementationByDelegationWithDifferentGenericSignature, ForbidJvmSerializableLambdaOnInlinedFunctionLiterals, ReportExposedTypeForInternalTypeParameterBounds, EnableDfaWarningsInK2, ResolveTopLevelLambdasAsSyntheticCallArgument, DataFlowBasedExhaustiveness, UnstableSmartcastOnDelegatedProperties, ForbidAnnotationsWithUseSiteTargetOnExpressions, ProhibitNullableTypeThroughTypealias, ForbidObjectDelegationToItself, JvmIndyAllowLambdasWithAnnotations, NestedTypeAliases, ProhibitIntersectionReifiedTypeParameter, AllowCheckForErasedTypesInContracts, AllowContractsOnSomeOperators, AllowContractsOnPropertyAccessors, ConditionImpliesReturnsContracts, HoldsInContracts, InferenceEnhancementsIn23, AllowReturnInExpressionBodyWithExplicitType, ParseLambdaWithSuspendModifier, JsAllowLongInExportedDeclarations, JsStaticInInterface, IrRichCallableReferencesInKlibs, AllowCallingJavaOpenSealedClassConstructor, ImprovedExhaustivenessChecksIn23, EqualityConstraintForOperatorsUnderAssignments, AnnotationsInMetadata, ForbidExposingLessVisibleTypesInInline, ForbidCaptureInlinableLambdasInJsCode, ForbidInitializationBeforeDeclarationInAnonymous, ForbidPrivateToThisUnboundCallableReferences, AllowReifiedTypeInCatchClause, ApproximateLocalTypesInPublicDeclarations, LocalVariableTargetedAnnotationOnDestructuring, ForbidGetSetValueWithTooManyParameters, ForbidInlineEnumEntries, TurnTypeCheckWarningsIntoErrors, ProhibitExtendingAnnotationClasses, RefinedVarargConversionRulesForCallableReferences, CheckOptInOnPureEnumEntries, ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables, ReportUpperBoundViolatedInCallArgumentInteractions, CheckPackageInfoNullnessAnnotations, ForbidTypeAliasWithMissingDependencyType, ForbidImplicitTypeAnnotationWithMissingDependency, ProperExhaustivenessCheckForJavaOpenSealedClass, DisableMaxTypeDepthFromInitialConstraints, JsAllowExportingSuspendFunctions, ImprovedResolutionInSecondaryConstructors, CacheLocalVariableScopes, ContextParameters, PropertyParamAnnotationDefaultTargetMode, AnnotationAllUseSiteTarget, OverloadResolutionSpecificityForEnhancedJvmPrimitiveWrappers, ForbidTypeAliasToCompilerRequiredAnnotation, ForbidArrayLiteralsInNonAnnotationContexts, LexicographicVariableReadinessCalculation, ForbidClassLiteralWithPotentiallyNullableReifiedLhs, ForbidOverriddenDefaultParametersInInline, ForbidRootIdePackageInCli, ReportOptInUsageOnCompanionObjectAccesses, ChangedIntersectionWithRecursiveCapturedType, FixedUninitializedEnumCompanionCheck, SkipHiddenObjectsInResolution, NoDeprecationOnImportStatements, JvmEnhancedBridges, DiscriminateNothingAsNullabilityConstraintInInference, ResolveEqualsRhsInDependentContextWithCompletion, IrIntraModuleInlinerBeforeKlibSerialization, ImprovedExhaustivenessCheckForSubjectVariable24, DontMakeExplicitNullableJavaTypeArgumentsFlexible, ExplicitBackingFields, ProhibitFunctionCallsInDefaultParametersOfInline, AllowNamedCompanionForJsExport, AllowInterfaceNestedClassesInJsExport, NativeTestProcessorBeforeSerialization, JsAllowExportingValueClasses, DontCreateSyntheticPropertiesWithoutBaseJavaGetter, ErrorAboutDataClassCopyVisibilityChange, KlibAnnotationsInMetadata, ForbidReturnInExpressionBodyWithoutExplicitTypeEdgeCases, ForbidExternalEnumEntriesAndPrimaryConstructorProperties, ReportTypeVarianceConflictsInDnnAndFlexible, ProperSupportOfInnerClassesInCallableReferenceLHS, DontIgnoreUpperBoundViolatedOnImplicitArguments, ForbidUpperBoundsViolationOnTypeOperatorAndParameterBounds, ForbidUselessTypeArgumentsIn25, WrapContinuationForTailCallFunctions, EagerLambdaAnalysis, AllowReturnsResultOfContract, ExpectActualClasses, DataClassCopyRespectsConstructorVisibility, ForbidParenthesizedLhsInAssignments, DirectJavaActualization, IgnoreNullabilityForErasedValueParameters, NoBuilderInferenceWithoutAnnotationRestriction, ReportErrorsForComparisonOperators, NoAdditionalErrorsInK1DiagnosticReporter, ProhibitScriptTopLevelInnerClasses, DisableSimplificationOfFlexibleUpperConstraintWithDnnLowerBound, PreciseSimplificationToFlexibleLowerConstraint, DiscriminateSuspendInOverloadResolution, ExpectRefinement, JsEnableExtensionFunctionInExternals, PackagePrivateFileClassesWithAllPrivateMembers, MultiPlatformProjects, ProhibitComparisonOfIncompatibleClasses, ProhibitAllMultipleDefaultsInheritedFromSupertypes, FunctionalTypeWithExtensionAsSupertype, ContextReceivers, ExplicitContextArguments, JvmInlineMultiFieldValueClasses, JavaSamConversionEqualsHashCode, AllowAnyAsAnActualTypeForExpectInterface, CompanionBlocksAndExtensions, NameBasedDestructuring, DeprecateNameMismatchInShortDestructuringWithParentheses, EnableNameBasedDestructuringShortForm, LocalTypeAliases, JsExposedNotExportedSuperInterfaceApiByExportedOne, JsExportInterfacesInImplementableWay, UnitConversionsOnArbitraryExpressions, JsAllowImplementingFunctionInterface, CustomEqualsInValueClasses, ContractSyntaxV2, ReferencesToSyntheticJavaProperties, ImplicitSignedToUnsignedIntegerConversion, ForbidInferringTypeVariablesIntoEmptyIntersection, IntrinsicConstEvaluation, DisableCheckingChangedProgressionsResolve, CollectionLiterals, ProperFieldAccessGenerationForFieldAccessShadowedByKotlinProperty, IrCrossModuleInlinerBeforeKlibSerialization, AllowEagerSupertypeAccessibilityChecks, UnnamedLocalVariables, ContextSensitiveResolutionUsingExpectedType, DisableWarningsForValueBasedJavaClasses, DisableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives, ExportKlibToOlderAbiVersion, ForbidInferOfInvisibleTypeAsReifiedVarargOrReturnType, ForbidExposingPackagePrivateInInternal, JvmLoadAnnotationsOnAnnotationProperties, TreatProvideDelegateAsConventionName, ExportKDocDocumentationToKlib};
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        LanguageVersion languageVersion = LanguageVersion.KOTLIN_1_1;
        TypeAliases = new LanguageFeature("TypeAliases", 0, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ApiVersion apiVersion = ApiVersion.KOTLIN_1_1;
        int i = 248;
        DefaultConstructorMarker defaultConstructorMarker = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str = null;
        LanguageFeatureBehaviorAfterSinceVersion languageFeatureBehaviorAfterSinceVersion = null;
        BoundCallableReferences = new LanguageFeature("BoundCallableReferences", 1, languageVersion, apiVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z, z2, z3, str, languageFeatureBehaviorAfterSinceVersion, i, defaultConstructorMarker);
        LocalDelegatedProperties = new LanguageFeature("LocalDelegatedProperties", 2, languageVersion, apiVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z, z2, z3, str, languageFeatureBehaviorAfterSinceVersion, i, defaultConstructorMarker);
        TopLevelSealedInheritance = new LanguageFeature("TopLevelSealedInheritance", 3, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AdditionalBuiltInsMembers = new LanguageFeature("AdditionalBuiltInsMembers", 4, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        DataClassInheritance = new LanguageFeature("DataClassInheritance", 5, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        InlineProperties = new LanguageFeature("InlineProperties", 6, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        DestructuringLambdaParameters = new LanguageFeature("DestructuringLambdaParameters", 7, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SingleUnderscoreForParameterName = new LanguageFeature("SingleUnderscoreForParameterName", 8, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        DslMarkersSupport = new LanguageFeature("DslMarkersSupport", 9, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UnderscoresInNumericLiterals = new LanguageFeature("UnderscoresInNumericLiterals", 10, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        DivisionByZeroInConstantExpressions = new LanguageFeature("DivisionByZeroInConstantExpressions", 11, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        InlineConstVals = new LanguageFeature("InlineConstVals", 12, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        OperatorProvideDelegate = new LanguageFeature("OperatorProvideDelegate", 13, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ShortSyntaxForPropertyGetters = new LanguageFeature("ShortSyntaxForPropertyGetters", 14, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        RefinedSamAdaptersPriority = new LanguageFeature("RefinedSamAdaptersPriority", 15, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SafeCallBoundSmartCasts = new LanguageFeature("SafeCallBoundSmartCasts", 16, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        TypeInferenceOnGenericsForCallableReferences = new LanguageFeature("TypeInferenceOnGenericsForCallableReferences", 17, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NoDelegationToJavaDefaultInterfaceMembers = new LanguageFeature("NoDelegationToJavaDefaultInterfaceMembers", 18, languageVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        Coroutines = new LanguageFeature("Coroutines", 19, languageVersion, apiVersion, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z, z2, z3, str, languageFeatureBehaviorAfterSinceVersion, i, defaultConstructorMarker);
        LanguageVersion languageVersion2 = LanguageVersion.KOTLIN_1_2;
        InlineDefaultFunctionalParameters = new LanguageFeature("InlineDefaultFunctionalParameters", 20, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SoundSmartCastsAfterTry = new LanguageFeature("SoundSmartCastsAfterTry", 21, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NullabilityAssertionOnExtensionReceiver = new LanguageFeature("NullabilityAssertionOnExtensionReceiver", 22, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SafeCastCheckBoundSmartCasts = new LanguageFeature("SafeCastCheckBoundSmartCasts", 23, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        CapturedInClosureSmartCasts = new LanguageFeature("CapturedInClosureSmartCasts", 24, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LateinitTopLevelProperties = new LanguageFeature("LateinitTopLevelProperties", 25, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LateinitLocalVariables = new LanguageFeature("LateinitLocalVariables", 26, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        InnerClassInEnumEntryClass = new LanguageFeature("InnerClassInEnumEntryClass", 27, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        CallableReferencesToClassMembersWithEmptyLHS = new LanguageFeature("CallableReferencesToClassMembersWithEmptyLHS", 28, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        JvmPackageName = new LanguageFeature("JvmPackageName", 29, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AssigningArraysToVarargsInNamedFormInAnnotations = new LanguageFeature("AssigningArraysToVarargsInNamedFormInAnnotations", 30, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ExpectedTypeFromCast = new LanguageFeature("ExpectedTypeFromCast", 31, languageVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LanguageVersion languageVersion3 = LanguageVersion.KOTLIN_1_3;
        RestrictionOfValReassignmentViaBackingField = new LanguageFeature("RestrictionOfValReassignmentViaBackingField", 32, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NestedClassesInEnumEntryShouldBeInner = new LanguageFeature("NestedClassesInEnumEntryShouldBeInner", 33, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitDataClassesOverridingCopy = new LanguageFeature("ProhibitDataClassesOverridingCopy", 34, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        RestrictionOfWrongAnnotationsWithUseSiteTargetsOnTypes = new LanguageFeature("RestrictionOfWrongAnnotationsWithUseSiteTargetsOnTypes", 35, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitInnerClassesOfGenericClassExtendingThrowable = new LanguageFeature("ProhibitInnerClassesOfGenericClassExtendingThrowable", 36, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperForInArrayLoopRangeVariableAssignmentSemantic = new LanguageFeature("ProperForInArrayLoopRangeVariableAssignmentSemantic", 37, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NestedClassesInAnnotations = new LanguageFeature("NestedClassesInAnnotations", 38, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        int i2 = 234;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        ApiVersion apiVersion2 = null;
        boolean z4 = false;
        boolean z5 = true;
        boolean z6 = false;
        String str2 = null;
        LanguageFeatureBehaviorAfterSinceVersion languageFeatureBehaviorAfterSinceVersion2 = null;
        JvmStaticInInterface = new LanguageFeature("JvmStaticInInterface", 39, languageVersion3, apiVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z4, z5, z6, str2, languageFeatureBehaviorAfterSinceVersion2, i2, defaultConstructorMarker2);
        JvmFieldInInterface = new LanguageFeature("JvmFieldInInterface", 40, languageVersion3, apiVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z4, z5, z6, str2, languageFeatureBehaviorAfterSinceVersion2, i2, defaultConstructorMarker2);
        ProhibitVisibilityOfNestedClassifiersFromSupertypesOfCompanion = new LanguageFeature("ProhibitVisibilityOfNestedClassifiersFromSupertypesOfCompanion", 41, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitNonConstValuesAsVarargsInAnnotations = new LanguageFeature("ProhibitNonConstValuesAsVarargsInAnnotations", 42, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ReleaseCoroutines = new LanguageFeature("ReleaseCoroutines", 43, languageVersion3, apiVersion2, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z4, z5, z6, str2, languageFeatureBehaviorAfterSinceVersion2, i2, defaultConstructorMarker2);
        ReadDeserializedContracts = new LanguageFeature("ReadDeserializedContracts", 44, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UseReturnsEffect = new LanguageFeature("UseReturnsEffect", 45, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UseCallsInPlaceEffect = new LanguageFeature("UseCallsInPlaceEffect", 46, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowContractsForCustomFunctions = new LanguageFeature("AllowContractsForCustomFunctions", 47, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        VariableDeclarationInWhenSubject = new LanguageFeature("VariableDeclarationInWhenSubject", 48, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitLocalAnnotations = new LanguageFeature("ProhibitLocalAnnotations", 49, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitSmartcastsOnLocalDelegatedProperty = new LanguageFeature("ProhibitSmartcastsOnLocalDelegatedProperty", 50, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitAssigningSingleElementsToVarargsInNamedForm = new LanguageFeature("ProhibitAssigningSingleElementsToVarargsInNamedForm", 51, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        FunctionTypesWithBigArity = new LanguageFeature("FunctionTypesWithBigArity", 52, languageVersion3, ApiVersion.KOTLIN_1_3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z4, false, z6, str2, languageFeatureBehaviorAfterSinceVersion2, 248, defaultConstructorMarker2);
        RestrictRetentionForExpressionAnnotations = new LanguageFeature("RestrictRetentionForExpressionAnnotations", 53, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        StrictJavaNullabilityAssertions = new LanguageFeature("StrictJavaNullabilityAssertions", 54, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SoundSmartcastForEnumEntries = new LanguageFeature("SoundSmartcastForEnumEntries", 55, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitErroneousExpressionsInAnnotationsWithUseSiteTargets = new LanguageFeature("ProhibitErroneousExpressionsInAnnotationsWithUseSiteTargets", 56, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NewCapturedReceiverFieldNamingConvention = new LanguageFeature("NewCapturedReceiverFieldNamingConvention", 57, languageVersion3, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ExtendedMainConvention = new LanguageFeature("ExtendedMainConvention", 58, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ExperimentalBuilderInference = new LanguageFeature("ExperimentalBuilderInference", 59, languageVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        int i3 = 234;
        ApiVersion apiVersion3 = null;
        boolean z7 = true;
        InlineClasses = new LanguageFeature("InlineClasses", 60, languageVersion3, apiVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z4, z7, z6, str2, languageFeatureBehaviorAfterSinceVersion2, i3, defaultConstructorMarker2);
        LanguageVersion languageVersion4 = LanguageVersion.KOTLIN_1_4;
        DslMarkerOnFunctionTypeReceiver = new LanguageFeature("DslMarkerOnFunctionTypeReceiver", 61, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        RestrictReturnStatementTarget = new LanguageFeature("RestrictReturnStatementTarget", 62, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        WarningOnMainUnusedParameter = new LanguageFeature("WarningOnMainUnusedParameter", 63, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        PolymorphicSignature = new LanguageFeature("PolymorphicSignature", 64, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitConcurrentHashMapContains = new LanguageFeature("ProhibitConcurrentHashMapContains", 65, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitTypeParametersForLocalVariables = new LanguageFeature("ProhibitTypeParametersForLocalVariables", 66, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitJvmOverloadsOnConstructorsOfAnnotationClasses = new LanguageFeature("ProhibitJvmOverloadsOnConstructorsOfAnnotationClasses", 67, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitTypeParametersInAnonymousObjects = new LanguageFeature("ProhibitTypeParametersInAnonymousObjects", 68, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitRepeatedUseSiteTargetAnnotations = new LanguageFeature("ProhibitRepeatedUseSiteTargetAnnotations", 69, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitUseSiteTargetAnnotationsOnSuperTypes = new LanguageFeature("ProhibitUseSiteTargetAnnotationsOnSuperTypes", 70, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitTypeParametersInClassLiteralsInAnnotationArguments = new LanguageFeature("ProhibitTypeParametersInClassLiteralsInAnnotationArguments", 71, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitComparisonOfIncompatibleEnums = new LanguageFeature("ProhibitComparisonOfIncompatibleEnums", 72, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        BareArrayClassLiteral = new LanguageFeature("BareArrayClassLiteral", 73, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitGenericArrayClassLiteral = new LanguageFeature("ProhibitGenericArrayClassLiteral", 74, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NonParenthesizedAnnotationsOnFunctionalTypes = new LanguageFeature("NonParenthesizedAnnotationsOnFunctionalTypes", 75, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UseGetterNameForPropertyAnnotationsMethodOnJvm = new LanguageFeature("UseGetterNameForPropertyAnnotationsMethodOnJvm", 76, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowBreakAndContinueInsideWhen = new LanguageFeature("AllowBreakAndContinueInsideWhen", 77, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        MixedNamedArgumentsInTheirOwnPosition = new LanguageFeature("MixedNamedArgumentsInTheirOwnPosition", 78, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitTailrecOnVirtualMember = new LanguageFeature("ProhibitTailrecOnVirtualMember", 79, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperComputationOrderOfTailrecDefaultParameters = new LanguageFeature("ProperComputationOrderOfTailrecDefaultParameters", 80, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        TrailingCommas = new LanguageFeature("TrailingCommas", 81, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitProtectedCallFromInline = new LanguageFeature("ProhibitProtectedCallFromInline", 82, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperFinally = new LanguageFeature("ProperFinally", 83, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowAssigningArrayElementsToVarargsInNamedFormForFunctions = new LanguageFeature("AllowAssigningArrayElementsToVarargsInNamedFormForFunctions", 84, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowNullOperatorsForResult = new LanguageFeature("AllowNullOperatorsForResult", 85, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        PreferJavaFieldOverload = new LanguageFeature("PreferJavaFieldOverload", 86, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowContractsForNonOverridableMembers = new LanguageFeature("AllowContractsForNonOverridableMembers", 87, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowReifiedGenericsInContracts = new LanguageFeature("AllowReifiedGenericsInContracts", 88, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperVisibilityForCompanionObjectInstanceField = new LanguageFeature("ProperVisibilityForCompanionObjectInstanceField", 89, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        DoNotGenerateThrowsForDelegatedKotlinMembers = new LanguageFeature("DoNotGenerateThrowsForDelegatedKotlinMembers", 90, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperIeee754Comparisons = new LanguageFeature("ProperIeee754Comparisons", 91, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        FunctionalInterfaceConversion = new LanguageFeature("FunctionalInterfaceConversion", 92, languageVersion4, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, false, true, false, null, languageFeatureBehaviorAfterSinceVersion, 234, defaultConstructorMarker);
        GenerateJvmOverloadsAsFinal = new LanguageFeature("GenerateJvmOverloadsAsFinal", 93, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        MangleClassMembersReturningInlineClasses = new LanguageFeature("MangleClassMembersReturningInlineClasses", 94, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ImproveReportingDiagnosticsOnProtectedMembersOfBaseClass = new LanguageFeature("ImproveReportingDiagnosticsOnProtectedMembersOfBaseClass", 95, languageVersion4, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NewInference = new LanguageFeature("NewInference", 96, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SamConversionForKotlinFunctions = new LanguageFeature("SamConversionForKotlinFunctions", 97, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SamConversionPerArgument = new LanguageFeature("SamConversionPerArgument", 98, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        FunctionReferenceWithDefaultValueAsOtherType = new LanguageFeature("FunctionReferenceWithDefaultValueAsOtherType", 99, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        OverloadResolutionByLambdaReturnType = new LanguageFeature("OverloadResolutionByLambdaReturnType", 100, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ContractsOnCallsWithImplicitReceiver = new LanguageFeature("ContractsOnCallsWithImplicitReceiver", 101, languageVersion4, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LanguageVersion languageVersion5 = LanguageVersion.KOTLIN_1_5;
        ProhibitSpreadOnSignaturePolymorphicCall = new LanguageFeature("ProhibitSpreadOnSignaturePolymorphicCall", 102, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitInvisibleAbstractMethodsInSuperclasses = new LanguageFeature("ProhibitInvisibleAbstractMethodsInSuperclasses", 103, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitNonReifiedArraysAsReifiedTypeArguments = new LanguageFeature("ProhibitNonReifiedArraysAsReifiedTypeArguments", 104, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitVarargAsArrayAfterSamArgument = new LanguageFeature("ProhibitVarargAsArrayAfterSamArgument", 105, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        CorrectSourceMappingSyntax = new LanguageFeature("CorrectSourceMappingSyntax", 106, languageVersion5, apiVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z4, z7, z6, str2, languageFeatureBehaviorAfterSinceVersion2, i3, defaultConstructorMarker2);
        RequiredPrimaryConstructorDelegationCallInEnums = new LanguageFeature("RequiredPrimaryConstructorDelegationCallInEnums", 107, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ApproximateAnonymousReturnTypesInPrivateInlineFunctions = new LanguageFeature("ApproximateAnonymousReturnTypesInPrivateInlineFunctions", 108, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ForbidReferencingToUnderscoreNamedParameterOfCatchBlock = new LanguageFeature("ForbidReferencingToUnderscoreNamedParameterOfCatchBlock", 109, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UseCorrectExecutionOrderForVarargArguments = new LanguageFeature("UseCorrectExecutionOrderForVarargArguments", 110, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        JvmRecordSupport = new LanguageFeature("JvmRecordSupport", 111, languageVersion5, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowNullOperatorsForResultAndResultReturnTypeByDefault = new LanguageFeature("AllowNullOperatorsForResultAndResultReturnTypeByDefault", 112, languageVersion5, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        AllowSealedInheritorsInDifferentFilesOfSamePackage = new LanguageFeature("AllowSealedInheritorsInDifferentFilesOfSamePackage", 113, languageVersion5, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SealedInterfaces = new LanguageFeature("SealedInterfaces", 114, languageVersion5, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        JvmInlineValueClasses = new LanguageFeature("JvmInlineValueClasses", 115, languageVersion5, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SuspendFunctionsInFunInterfaces = new LanguageFeature("SuspendFunctionsInFunInterfaces", 116, languageVersion5, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SamWrapperClassesAreSynthetic = new LanguageFeature("SamWrapperClassesAreSynthetic", 117, languageVersion5, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        StrictOnlyInputTypesChecks = new LanguageFeature("StrictOnlyInputTypesChecks", 118, languageVersion5, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LanguageVersion languageVersion6 = LanguageVersion.KOTLIN_1_6;
        ProhibitJvmFieldOnOverrideFromInterfaceInPrimaryConstructor = new LanguageFeature("ProhibitJvmFieldOnOverrideFromInterfaceInPrimaryConstructor", 119, languageVersion6, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        PrivateInFileEffectiveVisibility = new LanguageFeature("PrivateInFileEffectiveVisibility", 120, languageVersion6, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitSelfCallsInNestedObjects = new LanguageFeature("ProhibitSelfCallsInNestedObjects", 121, languageVersion6, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperCheckAnnotationsTargetInTypeUsePositions = new LanguageFeature("ProperCheckAnnotationsTargetInTypeUsePositions", 122, languageVersion6, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SuspendFunctionAsSupertype = new LanguageFeature("SuspendFunctionAsSupertype", 123, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UnrestrictedBuilderInference = new LanguageFeature("UnrestrictedBuilderInference", 124, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ClassTypeParameterAnnotations = new LanguageFeature("ClassTypeParameterAnnotations", 125, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        WarnAboutNonExhaustiveWhenOnAlgebraicTypes = new LanguageFeature("WarnAboutNonExhaustiveWhenOnAlgebraicTypes", 126, languageVersion6, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        InstantiationOfAnnotationClasses = new LanguageFeature("InstantiationOfAnnotationClasses", 127, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        OptInContagiousSignatures = new LanguageFeature("OptInContagiousSignatures", 128, languageVersion6, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        RepeatableAnnotations = new LanguageFeature("RepeatableAnnotations", 129, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        RepeatableAnnotationContainerConstraints = new LanguageFeature("RepeatableAnnotationContainerConstraints", 130, languageVersion6, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UseBuilderInferenceOnlyIfNeeded = new LanguageFeature("UseBuilderInferenceOnlyIfNeeded", 131, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SuspendConversion = new LanguageFeature("SuspendConversion", 132, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitSuperCallsFromPublicInline = new LanguageFeature("ProhibitSuperCallsFromPublicInline", 133, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitProtectedConstructorCallFromPublicInline = new LanguageFeature("ProhibitProtectedConstructorCallFromPublicInline", 134, languageVersion6, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LanguageVersion languageVersion7 = LanguageVersion.KOTLIN_1_7;
        boolean z8 = false;
        TypeEnhancementImprovementsInStrictMode = new LanguageFeature("TypeEnhancementImprovementsInStrictMode", 135, languageVersion7, apiVersion3, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z4, z8, z6, str2, new LanguageFeatureBehaviorAfterSinceVersion.CanStillBeDisabledForNow("KT-76100"), 122, defaultConstructorMarker2);
        OptInRelease = new LanguageFeature("OptInRelease", 136, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitNonExhaustiveWhenOnAlgebraicTypes = new LanguageFeature("ProhibitNonExhaustiveWhenOnAlgebraicTypes", 137, languageVersion7, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        UseBuilderInferenceWithoutAnnotation = new LanguageFeature("UseBuilderInferenceWithoutAnnotation", 138, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitSmartcastsOnPropertyFromAlienBaseClass = new LanguageFeature("ProhibitSmartcastsOnPropertyFromAlienBaseClass", 139, languageVersion7, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitInvalidCharsInNativeIdentifiers = new LanguageFeature("ProhibitInvalidCharsInNativeIdentifiers", 140, languageVersion7, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        DefinitelyNonNullableTypes = new LanguageFeature("DefinitelyNonNullableTypes", 141, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitSimplificationOfNonTrivialConstBooleanExpressions = new LanguageFeature("ProhibitSimplificationOfNonTrivialConstBooleanExpressions", 142, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SafeCallsAreAlwaysNullable = new LanguageFeature("SafeCallsAreAlwaysNullable", 143, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        JvmPermittedSubclassesAttributeForSealed = new LanguageFeature("JvmPermittedSubclassesAttributeForSealed", 144, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperTypeInferenceConstraintsProcessing = new LanguageFeature("ProperTypeInferenceConstraintsProcessing", 145, languageVersion7, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ForbidExposingTypesInPrimaryConstructorProperties = new LanguageFeature("ForbidExposingTypesInPrimaryConstructorProperties", 146, languageVersion7, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        PartiallySpecifiedTypeArguments = new LanguageFeature("PartiallySpecifiedTypeArguments", 147, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        EliminateAmbiguitiesWithExternalTypeParameters = new LanguageFeature("EliminateAmbiguitiesWithExternalTypeParameters", 148, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        EliminateAmbiguitiesOnInheritedSamInterfaces = new LanguageFeature("EliminateAmbiguitiesOnInheritedSamInterfaces", 149, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProperInternalVisibilityCheckInImportingScope = new LanguageFeature("ProperInternalVisibilityCheckInImportingScope", 150, languageVersion7, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        InlineClassImplementationByDelegation = new LanguageFeature("InlineClassImplementationByDelegation", 151, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        QualifiedSupertypeMayBeExtendedByOtherSupertype = new LanguageFeature("QualifiedSupertypeMayBeExtendedByOtherSupertype", 152, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        YieldIsNoMoreReserved = new LanguageFeature("YieldIsNoMoreReserved", 153, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NoDeprecationOnDeprecatedEnumEntries = new LanguageFeature("NoDeprecationOnDeprecatedEnumEntries", 154, languageVersion7, "KT-37975");
        ProhibitQualifiedAccessToUninitializedEnumEntry = new LanguageFeature("ProhibitQualifiedAccessToUninitializedEnumEntry", 155, languageVersion7, true, "KT-41124");
        ForbidRecursiveDelegateExpressions = new LanguageFeature("ForbidRecursiveDelegateExpressions", 156, languageVersion7, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        KotlinFunInterfaceConstructorReference = new LanguageFeature("KotlinFunInterfaceConstructorReference", 157, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        SuspendOnlySamConversions = new LanguageFeature("SuspendOnlySamConversions", 158, languageVersion7, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LanguageVersion languageVersion8 = LanguageVersion.KOTLIN_1_8;
        DontLoseDiagnosticsDuringOverloadResolutionByReturnType = new LanguageFeature("DontLoseDiagnosticsDuringOverloadResolutionByReturnType", 159, languageVersion8, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitConfusingSyntaxInWhenBranches = new LanguageFeature("ProhibitConfusingSyntaxInWhenBranches", 160, languageVersion8, true, "KT-48385");
        UseConsistentRulesForPrivateConstructorsOfSealedClasses = new LanguageFeature("UseConsistentRulesForPrivateConstructorsOfSealedClasses", 161, languageVersion8, true, "KT-44866");
        ProgressionsChangingResolve = new LanguageFeature("ProgressionsChangingResolve", 162, languageVersion8, "KT-49276");
        AbstractClassMemberNotImplementedWithIntermediateAbstractClass = new LanguageFeature("AbstractClassMemberNotImplementedWithIntermediateAbstractClass", 163, languageVersion8, true, "KT-45508");
        ForbidSuperDelegationToAbstractAnyMethod = new LanguageFeature("ForbidSuperDelegationToAbstractAnyMethod", 164, languageVersion8, true, "KT-38078");
        ProperEqualityChecksInBuilderInferenceCalls = new LanguageFeature("ProperEqualityChecksInBuilderInferenceCalls", 165, languageVersion8, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitNonExhaustiveIfInRhsOfElvis = new LanguageFeature("ProhibitNonExhaustiveIfInRhsOfElvis", 166, languageVersion8, true, "KT-44705");
        ReportMissingUpperBoundsViolatedErrorOnAbbreviationAtSupertypes = new LanguageFeature("ReportMissingUpperBoundsViolatedErrorOnAbbreviationAtSupertypes", 167, languageVersion8, true, "KT-29168");
        ForbidUsingExtensionPropertyTypeParameterInDelegate = new LanguageFeature("ForbidUsingExtensionPropertyTypeParameterInDelegate", 168, languageVersion8, true, "KT-24643");
        SynchronizedSuspendError = new LanguageFeature("SynchronizedSuspendError", 169, languageVersion8, true, "KT-48516");
        ReportNonVarargSpreadOnGenericCalls = new LanguageFeature("ReportNonVarargSpreadOnGenericCalls", 170, languageVersion8, true, "KT-48162");
        RangeUntilOperator = new LanguageFeature("RangeUntilOperator", 171, languageVersion8, "KT-15613");
        DefaultConstructorMarker defaultConstructorMarker3 = null;
        GenericInlineClassParameter = new LanguageFeature("GenericInlineClassParameter", 172, languageVersion8, null, "KT-32162", false, true, false, null, null, 234, defaultConstructorMarker3);
        LanguageVersion languageVersion9 = LanguageVersion.KOTLIN_1_9;
        ProhibitIllegalValueParameterUsageInDefaultArguments = new LanguageFeature("ProhibitIllegalValueParameterUsageInDefaultArguments", 173, languageVersion9, true, "KT-25694");
        ProhibitConstructorCallOnFunctionalSupertype = new LanguageFeature("ProhibitConstructorCallOnFunctionalSupertype", 174, languageVersion9, true, "KT-46344");
        ProhibitArrayLiteralsInCompanionOfAnnotation = new LanguageFeature("ProhibitArrayLiteralsInCompanionOfAnnotation", 175, languageVersion9, true, "KT-39041");
        ProhibitCyclesInAnnotations = new LanguageFeature("ProhibitCyclesInAnnotations", 176, languageVersion9, true, "KT-47932");
        ForbidExtensionFunctionTypeOnNonFunctionTypes = new LanguageFeature("ForbidExtensionFunctionTypeOnNonFunctionTypes", 177, languageVersion9, true, "KT-43527");
        ProhibitEnumDeclaringClass = new LanguageFeature("ProhibitEnumDeclaringClass", 178, languageVersion9, true, "KT-49653");
        StopPropagatingDeprecationThroughOverrides = new LanguageFeature("StopPropagatingDeprecationThroughOverrides", 179, languageVersion9, true, "KT-47902");
        ReportTypeVarianceConflictOnQualifierArguments = new LanguageFeature("ReportTypeVarianceConflictOnQualifierArguments", 180, languageVersion9, true, "KT-50947");
        ReportErrorsOnRecursiveTypeInsidePlusAssignment = new LanguageFeature("ReportErrorsOnRecursiveTypeInsidePlusAssignment", 181, languageVersion9, true, "KT-48546");
        ForbidExtensionCallsOnInlineFunctionalParameters = new LanguageFeature("ForbidExtensionCallsOnInlineFunctionalParameters", 182, languageVersion9, true, "KT-52502");
        SkipStandaloneScriptsInSourceRoots = new LanguageFeature("SkipStandaloneScriptsInSourceRoots", 183, languageVersion9, "KT-52525");
        ModifierNonBuiltinSuspendFunError = new LanguageFeature("ModifierNonBuiltinSuspendFunError", 184, languageVersion9, true, "KT-49264");
        DefaultConstructorMarker defaultConstructorMarker4 = null;
        boolean z9 = true;
        boolean z10 = false;
        String str3 = null;
        LanguageFeatureBehaviorAfterSinceVersion languageFeatureBehaviorAfterSinceVersion3 = null;
        EnumEntries = new LanguageFeature("EnumEntries", 185, languageVersion9, ApiVersion.KOTLIN_1_8, "KT-48872", z8, z9, z10, str3, languageFeatureBehaviorAfterSinceVersion3, 232, defaultConstructorMarker4);
        ForbidSuperDelegationToAbstractFakeOverride = new LanguageFeature("ForbidSuperDelegationToAbstractFakeOverride", 186, languageVersion9, true, "KT-49017");
        DataObjects = new LanguageFeature("DataObjects", 187, languageVersion9, "KT-4107");
        ProhibitAccessToEnumCompanionMembersInEnumConstructorCall = new LanguageFeature("ProhibitAccessToEnumCompanionMembersInEnumConstructorCall", 188, languageVersion9, true, "KT-49110");
        RefineTypeCheckingOnAssignmentsToJavaFields = new LanguageFeature("RefineTypeCheckingOnAssignmentsToJavaFields", 189, languageVersion9, true, "KT-46727");
        ValueClassesSecondaryConstructorWithBody = new LanguageFeature("ValueClassesSecondaryConstructorWithBody", 190, languageVersion9, null, "KT-55333", z8, z9, z10, str3, languageFeatureBehaviorAfterSinceVersion3, 234, defaultConstructorMarker4);
        NativeJsProhibitLateinitIsInitializedIntrinsicWithoutPrivateAccess = new LanguageFeature("NativeJsProhibitLateinitIsInitializedIntrinsicWithoutPrivateAccess", 191, languageVersion9, true, "KT-27002");
        TakeIntoAccountEffectivelyFinalInMustBeInitializedCheck = new LanguageFeature("TakeIntoAccountEffectivelyFinalInMustBeInitializedCheck", 192, languageVersion9, "KT-58587");
        ProhibitUsingNullableTypeParameterAgainstNotNullAnnotated = new LanguageFeature("ProhibitUsingNullableTypeParameterAgainstNotNullAnnotated", 193, languageVersion9, "KT-36770");
        int i4 = 248;
        boolean z11 = false;
        NoSourceCodeInNotNullAssertionExceptions = new LanguageFeature("NoSourceCodeInNotNullAssertionExceptions", 194, languageVersion9, ApiVersion.KOTLIN_1_4, "KT-57570", z8, z11, z10, str3, languageFeatureBehaviorAfterSinceVersion3, i4, defaultConstructorMarker4);
        MultiplatformRestrictions = new LanguageFeature("MultiplatformRestrictions", 195, languageVersion9, true, "KT-61668");
        LanguageVersion languageVersion10 = LanguageVersion.KOTLIN_2_0;
        EnhanceNullabilityOfPrimitiveArrays = new LanguageFeature("EnhanceNullabilityOfPrimitiveArrays", 196, languageVersion10, true, "KT-54521");
        AllowEmptyIntersectionsInResultTypeResolver = new LanguageFeature("AllowEmptyIntersectionsInResultTypeResolver", 197, languageVersion10, "KT-51221");
        ProhibitSmartcastsOnPropertyFromAlienBaseClassInheritedInInvisibleClass = new LanguageFeature("ProhibitSmartcastsOnPropertyFromAlienBaseClassInheritedInInvisibleClass", 198, languageVersion10, true, "KT-57290");
        ForbidInferringPostponedTypeVariableIntoDeclaredUpperBound = new LanguageFeature("ForbidInferringPostponedTypeVariableIntoDeclaredUpperBound", 199, languageVersion10, true, "KT-47986");
        ProhibitUseSiteGetTargetAnnotations = new LanguageFeature("ProhibitUseSiteGetTargetAnnotations", 200, languageVersion10, true, "KT-15470");
        KeepNullabilityWhenApproximatingLocalType = new LanguageFeature("KeepNullabilityWhenApproximatingLocalType", 201, languageVersion10, true, "KT-53982");
        ProhibitAccessToInvisibleSetterFromDerivedClass = new LanguageFeature("ProhibitAccessToInvisibleSetterFromDerivedClass", 202, languageVersion10, true, "KT-56662");
        ProhibitOpenValDeferredInitialization = new LanguageFeature("ProhibitOpenValDeferredInitialization", 203, languageVersion10, true, "KT-57553");
        SupportEffectivelyFinalInExpectActualVisibilityCheck = new LanguageFeature("SupportEffectivelyFinalInExpectActualVisibilityCheck", 204, languageVersion10, true, "KT-61955");
        ProhibitMissedMustBeInitializedWhenThereIsNoPrimaryConstructor = new LanguageFeature("ProhibitMissedMustBeInitializedWhenThereIsNoPrimaryConstructor", 205, languageVersion10, true, "KT-58472");
        MangleCallsToJavaMethodsWithValueClasses = new LanguageFeature("MangleCallsToJavaMethodsWithValueClasses", 206, languageVersion10, "KT-55945");
        ProhibitDefaultArgumentsInExpectActualizedByFakeOverride = new LanguageFeature("ProhibitDefaultArgumentsInExpectActualizedByFakeOverride", 207, languageVersion10, true, "KT-62036");
        DisableCompatibilityModeForNewInference = new LanguageFeature("DisableCompatibilityModeForNewInference", 208, languageVersion10, "KT-63558");
        DfaBooleanVariables = new LanguageFeature("DfaBooleanVariables", 209, languageVersion10, "KT-25747");
        LightweightLambdas = new LanguageFeature("LightweightLambdas", 210, languageVersion10, "KT-45375");
        ObjCSignatureOverrideAnnotation = new LanguageFeature("ObjCSignatureOverrideAnnotation", 211, languageVersion10, ApiVersion.KOTLIN_2_0, "KT-61323", z8, z11, z10, str3, languageFeatureBehaviorAfterSinceVersion3, i4, defaultConstructorMarker4);
        JsAllowValueClassesInExternals = new LanguageFeature("JsAllowValueClassesInExternals", 212, languageVersion10, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        LanguageVersion languageVersion11 = LanguageVersion.KOTLIN_2_1;
        ProhibitImplementingVarByInheritedVal = new LanguageFeature("ProhibitImplementingVarByInheritedVal", 213, languageVersion11, true, "KT-56779");
        int i5 = 234;
        DefaultConstructorMarker defaultConstructorMarker5 = null;
        boolean z12 = false;
        boolean z13 = true;
        boolean z14 = false;
        String str4 = null;
        PrioritizedEnumEntries = new LanguageFeature("PrioritizedEnumEntries", 214, languageVersion11, null, "KT-58920", z12, z13, z14, str4, null, i5, defaultConstructorMarker5);
        ProhibitInlineModifierOnPrimaryConstructorParameters = new LanguageFeature("ProhibitInlineModifierOnPrimaryConstructorParameters", 215, languageVersion11, true, "KT-59664");
        ProhibitSingleNamedFunctionAsExpression = new LanguageFeature("ProhibitSingleNamedFunctionAsExpression", 216, languageVersion11, true, "KT-62573");
        ForbidLambdaParameterWithMissingDependencyType = new LanguageFeature("ForbidLambdaParameterWithMissingDependencyType", 217, languageVersion11, true, "KT-64474");
        JsAllowInvalidCharsIdentifiersEscaping = new LanguageFeature("JsAllowInvalidCharsIdentifiersEscaping", 218, languageVersion11, "KT-31799");
        SupportJavaErrorEnhancementOfArgumentsOfWarningLevelEnhanced = new LanguageFeature("SupportJavaErrorEnhancementOfArgumentsOfWarningLevelEnhanced", 219, languageVersion11, true, "KT-63209");
        ProhibitPrivateOperatorCallInInline = new LanguageFeature("ProhibitPrivateOperatorCallInInline", 220, languageVersion11, true, "KT-65494");
        ProhibitTypealiasAsCallableQualifierInImport = new LanguageFeature("ProhibitTypealiasAsCallableQualifierInImport", 221, languageVersion11, true, "KT-64350");
        JsExternalPropertyParameters = new LanguageFeature("JsExternalPropertyParameters", 222, languageVersion11, "KT-65965");
        CorrectSpecificityCheckForSignedAndUnsigned = new LanguageFeature("CorrectSpecificityCheckForSignedAndUnsigned", 223, languageVersion11, "KT-35305");
        AllowAccessToProtectedFieldFromSuperCompanion = new LanguageFeature("AllowAccessToProtectedFieldFromSuperCompanion", 224, languageVersion11, "KT-39868");
        CheckLambdaAgainstTypeVariableContradictionInResolution = new LanguageFeature("CheckLambdaAgainstTypeVariableContradictionInResolution", 225, languageVersion11, "KT-58310");
        ProperUninitializedEnumEntryAccessAnalysis = new LanguageFeature("ProperUninitializedEnumEntryAccessAnalysis", 226, languageVersion11, true, "KT-68451");
        ImprovedCapturedTypeApproximationInInference = new LanguageFeature("ImprovedCapturedTypeApproximationInInference", 227, languageVersion11, "KT-64515");
        ImprovedVarianceInCst = new LanguageFeature("ImprovedVarianceInCst", 228, languageVersion11, "KT-68970");
        InferMoreImplicationsFromBooleanExpressions = new LanguageFeature("InferMoreImplicationsFromBooleanExpressions", 229, languageVersion11, "KT-64193");
        ImprovedExhaustivenessChecksIn21 = new LanguageFeature("ImprovedExhaustivenessChecksIn21", 230, languageVersion11, "KT-21908");
        ProhibitSynchronizationByValueClassesAndPrimitives = new LanguageFeature("ProhibitSynchronizationByValueClassesAndPrimitives", 231, languageVersion11, true, "KT-67791");
        AllowSuperCallToJavaInterface = new LanguageFeature("AllowSuperCallToJavaInterface", 232, languageVersion11, "KT-69729");
        ProhibitJavaClassInheritingPrivateKotlinClass = new LanguageFeature("ProhibitJavaClassInheritingPrivateKotlinClass", 233, languageVersion11, true, "KT-66328");
        ProhibitReturningIncorrectNullabilityValuesFromSamConstructorLambdaOfJdkInterfaces = new LanguageFeature("ProhibitReturningIncorrectNullabilityValuesFromSamConstructorLambdaOfJdkInterfaces", 234, languageVersion11, true, "KT-57014");
        ProhibitNothingAsCatchParameter = new LanguageFeature("ProhibitNothingAsCatchParameter", 235, languageVersion11, true, "KT-8322");
        NullableNothingInReifiedPosition = new LanguageFeature("NullableNothingInReifiedPosition", 236, languageVersion11, null, "KT-54227", z12, z13, z14, str4, 0 == true ? 1 : 0, i5, defaultConstructorMarker5);
        ElvisInferenceImprovementsIn21 = new LanguageFeature("ElvisInferenceImprovementsIn21", 237, languageVersion11, "KT-71751");
        ConsiderForkPointsWhenCheckingContradictions = new LanguageFeature("ConsiderForkPointsWhenCheckingContradictions", 238, languageVersion11, "KT-68768");
        AvoidApproximationOfRecursiveCapturedTypesWithNoReason = new LanguageFeature("AvoidApproximationOfRecursiveCapturedTypesWithNoReason", 239, languageVersion11, "KT-69995");
        PCLAEnhancementsIn21 = new LanguageFeature("PCLAEnhancementsIn21", 240, languageVersion11, "KT-69170");
        InferenceEnhancementsIn21 = new LanguageFeature("InferenceEnhancementsIn21", 241, languageVersion11, "KT-61227");
        StricterConstraintIncorporationRecursionDetector = new LanguageFeature("StricterConstraintIncorporationRecursionDetector", 242, languageVersion11, "KT-73434");
        ForkIsNotSuccessfulWhenNoBranchIsSuccessful = new LanguageFeature("ForkIsNotSuccessfulWhenNoBranchIsSuccessful", 243, languageVersion11, "KT-75444");
        LanguageVersion languageVersion12 = LanguageVersion.KOTLIN_2_2;
        BreakContinueInInlineLambdas = new LanguageFeature("BreakContinueInInlineLambdas", 244, languageVersion12, "KT-1436");
        ForbidUsingExpressionTypesWithInaccessibleContent = new LanguageFeature("ForbidUsingExpressionTypesWithInaccessibleContent", 245, languageVersion12, true, "KT-66691");
        ReportExposedTypeForMoreCasesOfTypeParameterBounds = new LanguageFeature("ReportExposedTypeForMoreCasesOfTypeParameterBounds", 246, languageVersion12, true, "KT-69653");
        ForbidReifiedTypeParametersOnTypeAliases = new LanguageFeature("ForbidReifiedTypeParametersOnTypeAliases", 247, languageVersion12, true, "KT-70163");
        ForbidProjectionsInAnnotationProperties = new LanguageFeature("ForbidProjectionsInAnnotationProperties", 248, languageVersion12, true, "KT-70002");
        ForbidJvmAnnotationsOnAnnotationParameters = new LanguageFeature("ForbidJvmAnnotationsOnAnnotationParameters", 249, languageVersion12, true, "KT-25861");
        ForbidFieldAnnotationsOnAnnotationParameters = new LanguageFeature("ForbidFieldAnnotationsOnAnnotationParameters", 250, languageVersion12, true, "KT-70233");
        ProhibitConstructorAndSupertypeOnTypealiasWithTypeProjection = new LanguageFeature("ProhibitConstructorAndSupertypeOnTypealiasWithTypeProjection", 251, languageVersion12, true, "KT-60305");
        CallableReferenceOverloadResolutionInLambda = new LanguageFeature("CallableReferenceOverloadResolutionInLambda", 252, languageVersion12, "KT-73011");
        ProhibitGenericQualifiersOnConstructorCalls = new LanguageFeature("ProhibitGenericQualifiersOnConstructorCalls", 253, languageVersion12, true, "KT-73527");
        AvoidWrongOptimizationOfTypeOperatorsOnValueClasses = new LanguageFeature("AvoidWrongOptimizationOfTypeOperatorsOnValueClasses", 254, languageVersion12, "KT-67517");
        ForbidSyntheticPropertiesWithoutBaseJavaGetter = new LanguageFeature("ForbidSyntheticPropertiesWithoutBaseJavaGetter", 255, languageVersion12, true, "KT-72305");
        AnnotationDefaultTargetMigrationWarning = new LanguageFeature("AnnotationDefaultTargetMigrationWarning", 256, languageVersion12, true, "KT-73255");
        AllowDnnTypeOverridingFlexibleType = new LanguageFeature("AllowDnnTypeOverridingFlexibleType", 257, languageVersion12, "KT-74049");
        ForbidEnumEntryNamedEntries = new LanguageFeature("ForbidEnumEntryNamedEntries", 258, languageVersion12, true, "KT-72829");
        WhenGuards = new LanguageFeature("WhenGuards", 259, languageVersion12, "KT-13626");
        MultiDollarInterpolation = new LanguageFeature("MultiDollarInterpolation", 260, languageVersion12, "KT-2425");
        JvmDefaultEnableByDefault = new LanguageFeature("JvmDefaultEnableByDefault", 261, languageVersion12, "KT-71768");
        ForbidExposureOfPrivateTypesInNonPrivateInlineFunctionsInKlibs = new LanguageFeature("ForbidExposureOfPrivateTypesInNonPrivateInlineFunctionsInKlibs", 262, languageVersion12, true, "KT-70916");
        FixationEnhancementsIn22 = new LanguageFeature("FixationEnhancementsIn22", 263, languageVersion12, "KT-76345");
        ForbidCrossFileIrFieldAccessInKlibs = new LanguageFeature("ForbidCrossFileIrFieldAccessInKlibs", 264, languageVersion12, true, "KT-71138");
        AllowExpectDeclarationsInJsExport = new LanguageFeature("AllowExpectDeclarationsInJsExport", 265, languageVersion12, "KT-64951");
        DoNotRunSuspendConversionForLambdaReturnStatements = new LanguageFeature("DoNotRunSuspendConversionForLambdaReturnStatements", 266, languageVersion12, true, "KT-74932");
        int i6 = 248;
        JvmNullOutSpilledCoroutineLocals = new LanguageFeature("JvmNullOutSpilledCoroutineLocals", 267, languageVersion12, ApiVersion.KOTLIN_2_2, "KT-63720", false, z14, false, 0 == true ? 1 : 0, null, i6, defaultConstructorMarker3);
        CapturedTypeApproximationReworked = new LanguageFeature("CapturedTypeApproximationReworked", 268, languageVersion12, "KT-79451");
        LanguageVersion languageVersion13 = LanguageVersion.KOTLIN_2_3;
        ForbidCompanionInLocalInnerClass = new LanguageFeature("ForbidCompanionInLocalInnerClass", 269, languageVersion13, true, "KT-47289");
        ForbidImplementationByDelegationWithDifferentGenericSignature = new LanguageFeature("ForbidImplementationByDelegationWithDifferentGenericSignature", 270, languageVersion13, true, "KTLC-267");
        ForbidJvmSerializableLambdaOnInlinedFunctionLiterals = new LanguageFeature("ForbidJvmSerializableLambdaOnInlinedFunctionLiterals", 271, languageVersion13, true, "KTLC-9");
        ReportExposedTypeForInternalTypeParameterBounds = new LanguageFeature("ReportExposedTypeForInternalTypeParameterBounds", 272, languageVersion13, true, "KTLC-275");
        EnableDfaWarningsInK2 = new LanguageFeature("EnableDfaWarningsInK2", 273, languageVersion13, "KT-50965");
        ResolveTopLevelLambdasAsSyntheticCallArgument = new LanguageFeature("ResolveTopLevelLambdasAsSyntheticCallArgument", 274, languageVersion13, "KT-67869");
        DataFlowBasedExhaustiveness = new LanguageFeature("DataFlowBasedExhaustiveness", 275, languageVersion13, "KT-76635");
        UnstableSmartcastOnDelegatedProperties = new LanguageFeature("UnstableSmartcastOnDelegatedProperties", 276, languageVersion13, true, "KTLC-273");
        ForbidAnnotationsWithUseSiteTargetOnExpressions = new LanguageFeature("ForbidAnnotationsWithUseSiteTargetOnExpressions", 277, languageVersion13, true, "KT-75242");
        ProhibitNullableTypeThroughTypealias = new LanguageFeature("ProhibitNullableTypeThroughTypealias", 278, languageVersion13, true, "KTLC-279");
        ForbidObjectDelegationToItself = new LanguageFeature("ForbidObjectDelegationToItself", 279, languageVersion13, true, "KT-17417");
        JvmIndyAllowLambdasWithAnnotations = new LanguageFeature("JvmIndyAllowLambdasWithAnnotations", 280, languageVersion13, "KTLC-278");
        NestedTypeAliases = new LanguageFeature("NestedTypeAliases", 281, languageVersion13, null, "KT-45285", z8, true, z10, str3, languageFeatureBehaviorAfterSinceVersion3, 234, defaultConstructorMarker4);
        ProhibitIntersectionReifiedTypeParameter = new LanguageFeature("ProhibitIntersectionReifiedTypeParameter", 282, languageVersion13, true, "KTLC-13");
        AllowCheckForErasedTypesInContracts = new LanguageFeature("AllowCheckForErasedTypesInContracts", 283, languageVersion13, "KT-45683");
        AllowContractsOnSomeOperators = new LanguageFeature("AllowContractsOnSomeOperators", 284, languageVersion13, "KT-32313");
        AllowContractsOnPropertyAccessors = new LanguageFeature("AllowContractsOnPropertyAccessors", 285, languageVersion13, "KT-27090");
        ConditionImpliesReturnsContracts = new LanguageFeature("ConditionImpliesReturnsContracts", 286, languageVersion13, "KT-8889");
        HoldsInContracts = new LanguageFeature("HoldsInContracts", 287, languageVersion13, "KT-32993");
        InferenceEnhancementsIn23 = new LanguageFeature("InferenceEnhancementsIn23", 288, languageVersion13, "KT-76826");
        AllowReturnInExpressionBodyWithExplicitType = new LanguageFeature("AllowReturnInExpressionBodyWithExplicitType", 289, languageVersion13, "KT-76926");
        ParseLambdaWithSuspendModifier = new LanguageFeature("ParseLambdaWithSuspendModifier", 290, languageVersion13, "KT-22765");
        JsAllowLongInExportedDeclarations = new LanguageFeature("JsAllowLongInExportedDeclarations", 291, languageVersion13, "KT-79222");
        JsStaticInInterface = new LanguageFeature("JsStaticInInterface", 292, languageVersion13, "KT-80168");
        IrRichCallableReferencesInKlibs = new LanguageFeature("IrRichCallableReferencesInKlibs", 293, languageVersion13, "KT-72734");
        AllowCallingJavaOpenSealedClassConstructor = new LanguageFeature("AllowCallingJavaOpenSealedClassConstructor", 294, languageVersion13, "KT-78879");
        ImprovedExhaustivenessChecksIn23 = new LanguageFeature("ImprovedExhaustivenessChecksIn23", 295, languageVersion13, "KT-80602");
        EqualityConstraintForOperatorsUnderAssignments = new LanguageFeature("EqualityConstraintForOperatorsUnderAssignments", 296, languageVersion13, "KT-77008");
        LanguageVersion languageVersion14 = LanguageVersion.KOTLIN_2_4;
        AnnotationsInMetadata = new LanguageFeature("AnnotationsInMetadata", 297, languageVersion14, "KT-75736");
        ForbidExposingLessVisibleTypesInInline = new LanguageFeature("ForbidExposingLessVisibleTypesInInline", 298, languageVersion14, true, "KTLC-283");
        ForbidCaptureInlinableLambdasInJsCode = new LanguageFeature("ForbidCaptureInlinableLambdasInJsCode", 299, languageVersion14, true, "KT-69297");
        ForbidInitializationBeforeDeclarationInAnonymous = new LanguageFeature("ForbidInitializationBeforeDeclarationInAnonymous", 300, languageVersion14, true, "KTLC-290");
        ForbidPrivateToThisUnboundCallableReferences = new LanguageFeature("ForbidPrivateToThisUnboundCallableReferences", 301, languageVersion14, true, "KTLC-383");
        AllowReifiedTypeInCatchClause = new LanguageFeature("AllowReifiedTypeInCatchClause", 302, languageVersion14, "KT-54363");
        ApproximateLocalTypesInPublicDeclarations = new LanguageFeature("ApproximateLocalTypesInPublicDeclarations", 303, languageVersion14, "KT-82454");
        LocalVariableTargetedAnnotationOnDestructuring = new LanguageFeature("LocalVariableTargetedAnnotationOnDestructuring", 304, languageVersion14, "KT-81408");
        ForbidGetSetValueWithTooManyParameters = new LanguageFeature("ForbidGetSetValueWithTooManyParameters", 305, languageVersion14, true, "KTLC-289");
        ForbidInlineEnumEntries = new LanguageFeature("ForbidInlineEnumEntries", 306, languageVersion14, true, "KTLC-361");
        TurnTypeCheckWarningsIntoErrors = new LanguageFeature("TurnTypeCheckWarningsIntoErrors", 307, languageVersion14, true, "KTLC-365");
        ProhibitExtendingAnnotationClasses = new LanguageFeature("ProhibitExtendingAnnotationClasses", 308, languageVersion14, true, "KTLC-374");
        RefinedVarargConversionRulesForCallableReferences = new LanguageFeature("RefinedVarargConversionRulesForCallableReferences", 309, languageVersion14, false, "KT-39697");
        CheckOptInOnPureEnumEntries = new LanguageFeature("CheckOptInOnPureEnumEntries", 310, languageVersion14, true, "KTLC-359");
        ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables = new LanguageFeature("ProperlyCheckUpperBoundsViolationsWhenCreatingFreshVariables", 311, languageVersion14, true, "KT-82318");
        ReportUpperBoundViolatedInCallArgumentInteractions = new LanguageFeature("ReportUpperBoundViolatedInCallArgumentInteractions", 312, languageVersion14, true, "KTLC-373");
        CheckPackageInfoNullnessAnnotations = new LanguageFeature("CheckPackageInfoNullnessAnnotations", 313, languageVersion14, true, "KT-77729");
        ForbidTypeAliasWithMissingDependencyType = new LanguageFeature("ForbidTypeAliasWithMissingDependencyType", 314, languageVersion14, true, "KT-79781");
        ForbidImplicitTypeAnnotationWithMissingDependency = new LanguageFeature("ForbidImplicitTypeAnnotationWithMissingDependency", 315, languageVersion14, true, "KT-80247");
        ProperExhaustivenessCheckForJavaOpenSealedClass = new LanguageFeature("ProperExhaustivenessCheckForJavaOpenSealedClass", 316, languageVersion14, true, "KTLC-366");
        DisableMaxTypeDepthFromInitialConstraints = new LanguageFeature("DisableMaxTypeDepthFromInitialConstraints", 317, languageVersion14, true, "KTLC-372");
        JsAllowExportingSuspendFunctions = new LanguageFeature("JsAllowExportingSuspendFunctions", 318, languageVersion14, "KT-56281");
        ImprovedResolutionInSecondaryConstructors = new LanguageFeature("ImprovedResolutionInSecondaryConstructors", 319, languageVersion14, "KT-77275");
        CacheLocalVariableScopes = new LanguageFeature("CacheLocalVariableScopes", 320, languageVersion14, "KT-68606");
        ContextParameters = new LanguageFeature("ContextParameters", 321, languageVersion14, "KT-72222");
        PropertyParamAnnotationDefaultTargetMode = new LanguageFeature("PropertyParamAnnotationDefaultTargetMode", 322, languageVersion14, "KT-73255");
        AnnotationAllUseSiteTarget = new LanguageFeature("AnnotationAllUseSiteTarget", 323, languageVersion14, "KT-73256");
        OverloadResolutionSpecificityForEnhancedJvmPrimitiveWrappers = new LanguageFeature("OverloadResolutionSpecificityForEnhancedJvmPrimitiveWrappers", 324, languageVersion14, "KT-9182");
        ForbidTypeAliasToCompilerRequiredAnnotation = new LanguageFeature("ForbidTypeAliasToCompilerRequiredAnnotation", 325, languageVersion14, true, "KT-79369");
        ForbidArrayLiteralsInNonAnnotationContexts = new LanguageFeature("ForbidArrayLiteralsInNonAnnotationContexts", 326, languageVersion14, true, "KTLC-369");
        LexicographicVariableReadinessCalculation = new LanguageFeature("LexicographicVariableReadinessCalculation", 327, languageVersion14, false, "KT-77939");
        ForbidClassLiteralWithPotentiallyNullableReifiedLhs = new LanguageFeature("ForbidClassLiteralWithPotentiallyNullableReifiedLhs", 328, languageVersion14, true, "KTLC-370");
        ForbidOverriddenDefaultParametersInInline = new LanguageFeature("ForbidOverriddenDefaultParametersInInline", 329, languageVersion14, true, "KT-49722");
        ForbidRootIdePackageInCli = new LanguageFeature("ForbidRootIdePackageInCli", 330, languageVersion14, true, "KT-81357");
        ReportOptInUsageOnCompanionObjectAccesses = new LanguageFeature("ReportOptInUsageOnCompanionObjectAccesses", 331, languageVersion14, true, "KT-82524");
        ChangedIntersectionWithRecursiveCapturedType = new LanguageFeature("ChangedIntersectionWithRecursiveCapturedType", 332, languageVersion14, "KT-65059");
        FixedUninitializedEnumCompanionCheck = new LanguageFeature("FixedUninitializedEnumCompanionCheck", 333, languageVersion14, "KT-84860");
        SkipHiddenObjectsInResolution = new LanguageFeature("SkipHiddenObjectsInResolution", 334, languageVersion14, "KT-82555");
        NoDeprecationOnImportStatements = new LanguageFeature("NoDeprecationOnImportStatements", 335, languageVersion14, "KT-52673");
        JvmEnhancedBridges = new LanguageFeature("JvmEnhancedBridges", 336, languageVersion14, "KT-82900");
        DiscriminateNothingAsNullabilityConstraintInInference = new LanguageFeature("DiscriminateNothingAsNullabilityConstraintInInference", 337, languageVersion14, "KT-81948");
        ResolveEqualsRhsInDependentContextWithCompletion = new LanguageFeature("ResolveEqualsRhsInDependentContextWithCompletion", 338, languageVersion14, "KT-81763");
        ApiVersion apiVersion4 = ApiVersion.KOTLIN_2_3;
        IrIntraModuleInlinerBeforeKlibSerialization = new LanguageFeature("IrIntraModuleInlinerBeforeKlibSerialization", 339, languageVersion14, apiVersion4, "KT-79717", false, false, false, null, null, i6, defaultConstructorMarker3);
        ImprovedExhaustivenessCheckForSubjectVariable24 = new LanguageFeature("ImprovedExhaustivenessCheckForSubjectVariable24", 340, languageVersion14, "KT-83903");
        DontMakeExplicitNullableJavaTypeArgumentsFlexible = new LanguageFeature("DontMakeExplicitNullableJavaTypeArgumentsFlexible", 341, languageVersion14, "KTLC-284");
        ExplicitBackingFields = new LanguageFeature("ExplicitBackingFields", 342, languageVersion14, "KT-14663");
        ProhibitFunctionCallsInDefaultParametersOfInline = new LanguageFeature("ProhibitFunctionCallsInDefaultParametersOfInline", 343, languageVersion14, true, "KT-83829");
        AllowNamedCompanionForJsExport = new LanguageFeature("AllowNamedCompanionForJsExport", 344, languageVersion14, "KT-82128");
        AllowInterfaceNestedClassesInJsExport = new LanguageFeature("AllowInterfaceNestedClassesInJsExport", 345, languageVersion14, "KT-84332");
        NativeTestProcessorBeforeSerialization = new LanguageFeature("NativeTestProcessorBeforeSerialization", 346, languageVersion14, "KT-83807");
        JsAllowExportingValueClasses = new LanguageFeature("JsAllowExportingValueClasses", 347, languageVersion14, "KT-72198");
        DontCreateSyntheticPropertiesWithoutBaseJavaGetter = new LanguageFeature("DontCreateSyntheticPropertiesWithoutBaseJavaGetter", 348, languageVersion14, "KT-64358");
        LanguageVersion languageVersion15 = LanguageVersion.KOTLIN_2_5;
        ErrorAboutDataClassCopyVisibilityChange = new LanguageFeature("ErrorAboutDataClassCopyVisibilityChange", 349, languageVersion15, true, "KT-11914");
        KlibAnnotationsInMetadata = new LanguageFeature("KlibAnnotationsInMetadata", 350, languageVersion15, "KT-81466");
        ForbidReturnInExpressionBodyWithoutExplicitTypeEdgeCases = new LanguageFeature("ForbidReturnInExpressionBodyWithoutExplicitTypeEdgeCases", 351, languageVersion15, "KTLC-288");
        ForbidExternalEnumEntriesAndPrimaryConstructorProperties = new LanguageFeature("ForbidExternalEnumEntriesAndPrimaryConstructorProperties", 352, languageVersion15, true, "KTLC-389");
        ReportTypeVarianceConflictsInDnnAndFlexible = new LanguageFeature("ReportTypeVarianceConflictsInDnnAndFlexible", 353, languageVersion15, true, "KTLC-392");
        ProperSupportOfInnerClassesInCallableReferenceLHS = new LanguageFeature("ProperSupportOfInnerClassesInCallableReferenceLHS", 354, languageVersion15, "KTLC-388");
        DontIgnoreUpperBoundViolatedOnImplicitArguments = new LanguageFeature("DontIgnoreUpperBoundViolatedOnImplicitArguments", 355, languageVersion15, "KTLC-287");
        ForbidUpperBoundsViolationOnTypeOperatorAndParameterBounds = new LanguageFeature("ForbidUpperBoundsViolationOnTypeOperatorAndParameterBounds", 356, languageVersion15, true, "KTLC-358");
        ForbidUselessTypeArgumentsIn25 = new LanguageFeature("ForbidUselessTypeArgumentsIn25", 357, languageVersion15, true, "KTLC-390");
        DefaultConstructorMarker defaultConstructorMarker6 = null;
        boolean z15 = false;
        boolean z16 = false;
        String str5 = null;
        WrapContinuationForTailCallFunctions = new LanguageFeature("WrapContinuationForTailCallFunctions", 358, languageVersion15, ApiVersion.KOTLIN_2_5, "KT-74051", z15, false, z16, str5, null, 248, defaultConstructorMarker6);
        EagerLambdaAnalysis = new LanguageFeature("EagerLambdaAnalysis", 359, languageVersion15, "KT-51107");
        boolean z17 = true;
        AllowReturnsResultOfContract = new LanguageFeature("AllowReturnsResultOfContract", 360, languageVersion15, ApiVersion.KOTLIN_2_4, "KT-85948", z15, z17, z16, str5, 0 == true ? 1 : 0, 232, defaultConstructorMarker6);
        ExpectActualClasses = new LanguageFeature("ExpectActualClasses", 361, null, "KT-62885");
        DataClassCopyRespectsConstructorVisibility = new LanguageFeature("DataClassCopyRespectsConstructorVisibility", 362, null, "KT-11914");
        ForbidParenthesizedLhsInAssignments = new LanguageFeature("ForbidParenthesizedLhsInAssignments", 363, null, true, "KT-70507");
        DirectJavaActualization = new LanguageFeature("DirectJavaActualization", 364, null, "KT-67202");
        IgnoreNullabilityForErasedValueParameters = new LanguageFeature("IgnoreNullabilityForErasedValueParameters", 365, null, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NoBuilderInferenceWithoutAnnotationRestriction = new LanguageFeature("NoBuilderInferenceWithoutAnnotationRestriction", 366, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ReportErrorsForComparisonOperators = new LanguageFeature("ReportErrorsForComparisonOperators", 367, null, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        NoAdditionalErrorsInK1DiagnosticReporter = new LanguageFeature("NoAdditionalErrorsInK1DiagnosticReporter", 368, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitScriptTopLevelInnerClasses = new LanguageFeature("ProhibitScriptTopLevelInnerClasses", 369, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        DisableSimplificationOfFlexibleUpperConstraintWithDnnLowerBound = new LanguageFeature("DisableSimplificationOfFlexibleUpperConstraintWithDnnLowerBound", 370, null, "KT-52283");
        PreciseSimplificationToFlexibleLowerConstraint = new LanguageFeature("PreciseSimplificationToFlexibleLowerConstraint", 371, null, "KT-78621");
        DiscriminateSuspendInOverloadResolution = new LanguageFeature("DiscriminateSuspendInOverloadResolution", 372, null, "KT-23610");
        ExpectRefinement = new LanguageFeature("ExpectRefinement", 373, null, "KT-73557");
        JsEnableExtensionFunctionInExternals = new LanguageFeature("JsEnableExtensionFunctionInExternals", 374, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        PackagePrivateFileClassesWithAllPrivateMembers = new LanguageFeature("PackagePrivateFileClassesWithAllPrivateMembers", 375, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        MultiPlatformProjects = new LanguageFeature("MultiPlatformProjects", 376, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitComparisonOfIncompatibleClasses = new LanguageFeature("ProhibitComparisonOfIncompatibleClasses", 377, null, true, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ProhibitAllMultipleDefaultsInheritedFromSupertypes = new LanguageFeature("ProhibitAllMultipleDefaultsInheritedFromSupertypes", 378, null, false, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        FunctionalTypeWithExtensionAsSupertype = new LanguageFeature("FunctionalTypeWithExtensionAsSupertype", 379, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ContextReceivers = new LanguageFeature("ContextReceivers", 380, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        ExplicitContextArguments = new LanguageFeature("ExplicitContextArguments", 381, null, "KT-81684");
        JvmInlineMultiFieldValueClasses = new LanguageFeature("JvmInlineMultiFieldValueClasses", 382, null, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, z15, z17, z16, str5, 0 == true ? 1 : 0, 234, defaultConstructorMarker6);
        DefaultConstructorMarker defaultConstructorMarker7 = null;
        Object[] objArr = 0 == true ? 1 : 0;
        Object[] objArr2 = 0 == true ? 1 : 0;
        JavaSamConversionEqualsHashCode = new LanguageFeature("JavaSamConversionEqualsHashCode", 383, objArr2, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED, false, true, false, objArr, null, 234, defaultConstructorMarker7);
        AllowAnyAsAnActualTypeForExpectInterface = new LanguageFeature("AllowAnyAsAnActualTypeForExpectInterface", 384, null, "KT-79308");
        int i7 = 234;
        DefaultConstructorMarker defaultConstructorMarker8 = null;
        LanguageVersion languageVersion16 = null;
        boolean z18 = false;
        boolean z19 = true;
        boolean z20 = false;
        String str6 = null;
        Object[] objArr3 = 0 == true ? 1 : 0;
        CompanionBlocksAndExtensions = new LanguageFeature("CompanionBlocksAndExtensions", 385, languageVersion16, 0 == true ? 1 : 0, "KT-11968", z18, z19, z20, str6, objArr3, i7, defaultConstructorMarker8);
        NameBasedDestructuring = new LanguageFeature("NameBasedDestructuring", 386, null, "KT-19627");
        DeprecateNameMismatchInShortDestructuringWithParentheses = new LanguageFeature("DeprecateNameMismatchInShortDestructuringWithParentheses", 387, null, "KT-19627");
        EnableNameBasedDestructuringShortForm = new LanguageFeature("EnableNameBasedDestructuringShortForm", 388, null, "KT-19627");
        Object[] objArr4 = 0 == true ? 1 : 0;
        LocalTypeAliases = new LanguageFeature("LocalTypeAliases", 389, languageVersion16, 0 == true ? 1 : 0, "KT-81404", z18, z19, z20, str6, objArr4, i7, defaultConstructorMarker8);
        JsExposedNotExportedSuperInterfaceApiByExportedOne = new LanguageFeature("JsExposedNotExportedSuperInterfaceApiByExportedOne", 390, null, "KT-83009");
        JsExportInterfacesInImplementableWay = new LanguageFeature("JsExportInterfacesInImplementableWay", 391, null, "KT-65802");
        UnitConversionsOnArbitraryExpressions = new LanguageFeature("UnitConversionsOnArbitraryExpressions", 392, null, "KT-84393");
        JsAllowImplementingFunctionInterface = new LanguageFeature("JsAllowImplementingFunctionInterface", 393, null, LanguageVersionSettingsKt.NO_ISSUE_SPECIFIED);
        CustomEqualsInValueClasses = new LanguageFeature("CustomEqualsInValueClasses", 394, null, "KT-24874");
        Object[] objArr5 = 0 == true ? 1 : 0;
        ContractSyntaxV2 = new LanguageFeature("ContractSyntaxV2", 395, languageVersion16, 0 == true ? 1 : 0, "KT-56127", z18, z19, z20, str6, objArr5, i7, defaultConstructorMarker8);
        Object[] objArr6 = 0 == true ? 1 : 0;
        Object[] objArr7 = 0 == true ? 1 : 0;
        ReferencesToSyntheticJavaProperties = new LanguageFeature("ReferencesToSyntheticJavaProperties", 396, objArr7, null, "KT-8575", false, z20, true, objArr6, null, 218, defaultConstructorMarker7);
        Object[] objArr8 = 0 == true ? 1 : 0;
        Object[] objArr9 = 0 == true ? 1 : 0;
        ImplicitSignedToUnsignedIntegerConversion = new LanguageFeature("ImplicitSignedToUnsignedIntegerConversion", 397, objArr8, null, "KT-56583", z20, false, true, objArr9, null, 218, null);
        ForbidInferringTypeVariablesIntoEmptyIntersection = new LanguageFeature("ForbidInferringTypeVariablesIntoEmptyIntersection", 398, null, true, "KT-51221");
        IntrinsicConstEvaluation = new LanguageFeature("IntrinsicConstEvaluation", 399, null, "KT-49303");
        DisableCheckingChangedProgressionsResolve = new LanguageFeature("DisableCheckingChangedProgressionsResolve", 400, null, "KT-49276");
        CollectionLiterals = new LanguageFeature("CollectionLiterals", 401, null, "KT-80489");
        ProperFieldAccessGenerationForFieldAccessShadowedByKotlinProperty = new LanguageFeature("ProperFieldAccessGenerationForFieldAccessShadowedByKotlinProperty", 402, null, "KT-56386");
        IrCrossModuleInlinerBeforeKlibSerialization = new LanguageFeature("IrCrossModuleInlinerBeforeKlibSerialization", 403, null, apiVersion4, "KT-79717", false, true, false, null, 0 == true ? 1 : 0, 232, null);
        AllowEagerSupertypeAccessibilityChecks = new LanguageFeature("AllowEagerSupertypeAccessibilityChecks", 404, null, true, "KT-73611");
        boolean z21 = false;
        UnnamedLocalVariables = new LanguageFeature("UnnamedLocalVariables", 405, null, null, "KT-74809", false, z21, false, null, null, 234, null);
        ContextSensitiveResolutionUsingExpectedType = new LanguageFeature("ContextSensitiveResolutionUsingExpectedType", 406, null, "KT-16768");
        DisableWarningsForValueBasedJavaClasses = new LanguageFeature("DisableWarningsForValueBasedJavaClasses", 407, null, "KT-70722");
        DisableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives = new LanguageFeature("DisableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives", 408, null, "KT-70722");
        Object[] objArr10 = 0 == true ? 1 : 0;
        ExportKlibToOlderAbiVersion = new LanguageFeature("ExportKlibToOlderAbiVersion", 409, null, objArr10, "KT-76131", false, true, z21, null, 0 == true ? 1 : 0, 234, null);
        ForbidInferOfInvisibleTypeAsReifiedVarargOrReturnType = new LanguageFeature("ForbidInferOfInvisibleTypeAsReifiedVarargOrReturnType", 410, null, true, "KTLC-14");
        ForbidExposingPackagePrivateInInternal = new LanguageFeature("ForbidExposingPackagePrivateInInternal", 411, null, true, "KTLC-271");
        JvmLoadAnnotationsOnAnnotationProperties = new LanguageFeature("JvmLoadAnnotationsOnAnnotationProperties", 412, null, "KT-22463");
        TreatProvideDelegateAsConventionName = new LanguageFeature("TreatProvideDelegateAsConventionName", 413, null, "KT-83538");
        ExportKDocDocumentationToKlib = new LanguageFeature("ExportKDocDocumentationToKlib", 414, null, "KT-83921");
        LanguageFeature[] languageFeatureArr$values = $values();
        $VALUES = languageFeatureArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(languageFeatureArr$values);
        INSTANCE = new Companion(null);
    }

    public /* synthetic */ LanguageFeature(String str, int i, LanguageVersion languageVersion, ApiVersion apiVersion, String str2, boolean z, boolean z2, boolean z3, String str3, LanguageFeatureBehaviorAfterSinceVersion languageFeatureBehaviorAfterSinceVersion, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, languageVersion, (i2 & 2) != 0 ? ApiVersion.KOTLIN_1_0 : apiVersion, str2, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2, (i2 & 32) != 0 ? false : z3, (i2 & 64) != 0 ? null : str3, (i2 & 128) != 0 ? LanguageFeatureBehaviorAfterSinceVersion.CannotBeDisabled.INSTANCE : languageFeatureBehaviorAfterSinceVersion);
    }

    @JvmStatic
    public static final LanguageFeature fromString(String str) {
        return INSTANCE.fromString(str);
    }

    public static EnumEntries<LanguageFeature> getEntries() {
        return $ENTRIES;
    }

    public static LanguageFeature valueOf(String str) {
        return (LanguageFeature) Enum.valueOf(LanguageFeature.class, str);
    }

    public static LanguageFeature[] values() {
        return (LanguageFeature[]) $VALUES.clone();
    }

    public final boolean getActuallyEnabledInProgressiveMode() {
        return this.enabledInProgressiveMode && this.sinceVersion != null;
    }

    public final LanguageFeatureBehaviorAfterSinceVersion getBehaviorAfterSinceVersion() {
        return this.behaviorAfterSinceVersion;
    }

    public final boolean getForcesPreReleaseBinaries() {
        return this.forcesPreReleaseBinaries;
    }

    public final String getHintUrl() {
        return this.hintUrl;
    }

    public final String getIssue() {
        return this.issue;
    }

    public final String getPresentableName() {
        return CollectionsKt.joinToString$default(new Regex("(?<!^)(?=[A-Z])").split(name(), 0), Argument.Delimiters.space, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, LanguageFeature$presentableName$1.INSTANCE, 30, (Object) null);
    }

    public final String getPresentableText() {
        if (this.hintUrl == null) {
            return getPresentableName();
        }
        return getPresentableName() + " (See: " + this.hintUrl + ')';
    }

    public final ApiVersion getSinceApiVersion() {
        return this.sinceApiVersion;
    }

    public final LanguageVersion getSinceVersion() {
        return this.sinceVersion;
    }

    public final boolean getTestOnly() {
        return this.testOnly;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageFeature$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromString", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "str", Argument.Delimiters.none, "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final LanguageFeature fromString(String str) {
            Object next;
            str.getClass();
            Iterator it = LanguageFeature.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(((LanguageFeature) next).name(), str)) {
                    return (LanguageFeature) next;
                }
            }
            next = null;
            return (LanguageFeature) next;
        }

        private Companion() {
        }
    }

    private LanguageFeature(String str, int i, LanguageVersion languageVersion, ApiVersion apiVersion, String str2, boolean z, boolean z2, boolean z3, String str3, LanguageFeatureBehaviorAfterSinceVersion languageFeatureBehaviorAfterSinceVersion) {
        super(str, i);
        this.sinceVersion = languageVersion;
        this.sinceApiVersion = apiVersion;
        this.issue = str2;
        this.enabledInProgressiveMode = z;
        this.forcesPreReleaseBinaries = z2;
        this.testOnly = z3;
        this.hintUrl = str3;
        this.behaviorAfterSinceVersion = languageFeatureBehaviorAfterSinceVersion;
        if (!z3 || languageVersion == null) {
            return;
        }
        throw new IllegalStateException((this + ": should be enabled by default since version " + languageVersion + " but is test only").toString());
    }

    private LanguageFeature(String str, int i, LanguageVersion languageVersion, String str2) {
        this(str, i, languageVersion, ApiVersion.KOTLIN_1_0, str2, false, false, false, null, null, 248, null);
    }

    private LanguageFeature(String str, int i, LanguageVersion languageVersion, boolean z, String str2) {
        this(str, i, languageVersion, ApiVersion.KOTLIN_1_0, str2, z, false, false, null, null, 240, null);
    }
}
