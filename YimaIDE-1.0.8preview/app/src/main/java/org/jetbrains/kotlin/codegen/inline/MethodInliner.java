package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AssertCodegenUtilKt;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.IrExpressionLambda;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.MethodInliner;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformer;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformerKt;
import org.jetbrains.kotlin.codegen.optimization.ApiVersionCallsPreprocessingMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.FixStackWithLabelNormalizationMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FastStackAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackInterpreter;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackValue;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.nullCheck.RedundantNullCheckMethodTransformerKt;
import org.jetbrains.kotlin.codegen.optimization.temporaryVals.TemporaryVariablesEliminationTransformer;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.commons.LocalVariablesSorter;
import org.jetbrains.org.objectweb.asm.commons.MethodRemapper;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u0000 `2\u00020\u0001:\u0003^_`Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J4\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000b2\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010.0-J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020 H\u0002J>\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000b2\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010.0-2\u0006\u00102\u001a\u00020\u0016H\u0002J\u0010\u0010&\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u00103\u001a\u0002002\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u0003H\u0002J\u0018\u00106\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00102\u001a\u00020\u0016H\u0002J.\u00107\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010.0-2\u0006\u00102\u001a\u00020\u0016H\u0002J3\u00108\u001a\b\u0012\u0004\u0012\u00020:092\u0006\u0010;\u001a\u00020<2\u0016\u0010=\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020@\u0018\u00010?0>H\u0002¢\u0006\u0002\u0010AJ&\u0010B\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010.0-H\u0002J\u0010\u0010C\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0012\u0010D\u001a\u00020\u000b2\b\u0010E\u001a\u0004\u0018\u00010FH\u0002J<\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u000e2\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020L0-2\u0006\u0010M\u001a\u00020\u000b2\u0006\u0010N\u001a\u00020\u000bH\u0002J\u0010\u0010O\u001a\u00020\u000b2\u0006\u0010P\u001a\u00020\u000eH\u0002J\u0017\u0010Q\u001a\u0004\u0018\u00010L2\u0006\u0010R\u001a\u00020SH\u0000¢\u0006\u0002\bTJ\u0017\u0010Q\u001a\u0004\u0018\u00010L2\u0006\u0010U\u001a\u00020\u0016H\u0000¢\u0006\u0002\bTJ\u0010\u0010V\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J$\u0010W\u001a\u00060Xj\u0002`Y2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\\\u001a\u00020\u000eH\u0002J\b\u0010]\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MethodInliner;", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "parameters", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "nodeRemapper", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "isSameModule", Argument.Delimiters.none, "errorPrefixSupplier", "Lkotlin/Function0;", Argument.Delimiters.none, "sourceMapper", "Lorg/jetbrains/kotlin/codegen/inline/SourceMapCopier;", "inlineCallSiteInfo", "Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", "isInlineOnlyMethod", "shouldPreprocessApiVersionCalls", "defaultMaskStart", Argument.Delimiters.none, "defaultMaskEnd", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/kotlin/codegen/inline/Parameters;Lorg/jetbrains/kotlin/codegen/inline/InliningContext;Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;ZLkotlin/jvm/functions/Function0;Lorg/jetbrains/kotlin/codegen/inline/SourceMapCopier;Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;ZZII)V", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "invokeCalls", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/codegen/inline/InvokeCall;", "transformations", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "currentTypeMapping", "Ljava/util/HashMap;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "lambdasFinallyBlocks", "doInline", "adapter", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "remapper", "Lorg/jetbrains/kotlin/codegen/inline/LocalVarRemapper;", "remapReturn", "returnLabels", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Label;", "recordTransformation", Argument.Delimiters.none, "info", "finallyDeepShift", "updateCallSiteLineNumbers", "resultNode", "inlinedNode", "prepareNode", "markPlacesForInlineAndRemoveInlinable", "markObsoleteInstruction", "Lorg/jetbrains/kotlin/utils/SmartSet;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", ModuleXmlParser.SOURCES, Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "(Lorg/jetbrains/org/objectweb/asm/tree/InsnList;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)Lorg/jetbrains/kotlin/utils/SmartSet;", "preprocessNodeBeforeInline", "removeFakeVariablesInitializationIfPresent", "isAnonymousClassThatMustBeRegenerated", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "buildConstructorInvocation", "Lorg/jetbrains/kotlin/codegen/inline/AnonymousObjectTransformationInfo;", "anonymousType", "desc", "lambdaMapping", "Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "needReification", "capturesAnonymousObjectThatMustBeRegenerated", "isAlreadyRegenerated", "owner", "getFunctionalArgumentIfExists", "insnNode", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "getFunctionalArgumentIfExists$org_jetbrains_kotlin_backend", "varIndex", "transformCaptured", "wrapException", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "originalException", Argument.Delimiters.none, "errorSuffix", "isRegeneratingAnonymousObject", "LocalReturnsNormalizer", "PointForExternalFinallyBlocks", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MethodInliner {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HashMap<String, String> currentTypeMapping;
    private final int defaultMaskEnd;
    private final int defaultMaskStart;
    private final Function0<String> errorPrefixSupplier;
    private final InlineCallSiteInfo inlineCallSiteInfo;
    private final InliningContext inliningContext;
    private final ArrayList<InvokeCall> invokeCalls;
    private final boolean isInlineOnlyMethod;
    private final boolean isSameModule;
    private int lambdasFinallyBlocks;
    private final LanguageVersionSettings languageVersionSettings;
    private final MethodNode node;
    private final FieldRemapper nodeRemapper;
    private final Parameters parameters;
    private final InlineResult result;
    private final boolean shouldPreprocessApiVersionCalls;
    private final SourceMapCopier sourceMapper;
    private final ArrayList<TransformationInfo> transformations;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MethodInliner$LocalReturnsNormalizer;", Argument.Delimiters.none, "<init>", "()V", "localReturns", "Lorg/jetbrains/kotlin/utils/SmartList;", "Lorg/jetbrains/kotlin/codegen/inline/MethodInliner$LocalReturnsNormalizer$LocalReturn;", "returnVariableSize", Argument.Delimiters.none, "returnOpcode", "addLocalReturnToTransform", Argument.Delimiters.none, "returnInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "insertBeforeInsn", "sourceValueFrame", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "transform", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "LocalReturn", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class LocalReturnsNormalizer {
        private final SmartList<LocalReturn> localReturns = new SmartList<>();
        private int returnOpcode = -1;
        private int returnVariableSize;

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MethodInliner$LocalReturnsNormalizer$LocalReturn;", Argument.Delimiters.none, "returnInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "insertBeforeInsn", "frame", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "transform", Argument.Delimiters.none, "insnList", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "returnVariableIndex", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class LocalReturn {
            private final Frame<FixStackValue> frame;
            private final AbstractInsnNode insertBeforeInsn;
            private final AbstractInsnNode returnInsn;

            public LocalReturn(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2, Frame<FixStackValue> frame) {
                abstractInsnNode.getClass();
                abstractInsnNode2.getClass();
                frame.getClass();
                this.returnInsn = abstractInsnNode;
                this.insertBeforeInsn = abstractInsnNode2;
                this.frame = frame;
            }

            public final void transform(InsnList insnList, int returnVariableIndex) {
                insnList.getClass();
                int i = this.returnInsn.getOpcode() != 177 ? 1 : 0;
                int stackSize = this.frame.getStackSize();
                if (i == stackSize) {
                    return;
                }
                FixStackValue fixStackValue = (FixStackValue) this.frame.getStack(stackSize - 1);
                if (i != 0) {
                    insnList.insertBefore(this.insertBeforeInsn, new VarInsnNode(fixStackValue.getStoreOpcode(), returnVariableIndex));
                    stackSize--;
                }
                while (stackSize > 0) {
                    insnList.insertBefore(this.insertBeforeInsn, new InsnNode(((FixStackValue) this.frame.getStack(stackSize - 1)).getPopOpcode()));
                    stackSize--;
                }
                if (i != 0) {
                    insnList.insertBefore(this.insertBeforeInsn, new VarInsnNode(fixStackValue.getLoadOpcode(), returnVariableIndex));
                }
            }
        }

        public final void addLocalReturnToTransform(AbstractInsnNode returnInsn, AbstractInsnNode insertBeforeInsn, Frame<FixStackValue> sourceValueFrame) {
            returnInsn.getClass();
            insertBeforeInsn.getClass();
            sourceValueFrame.getClass();
            InlineCodegenUtilsKt.isReturnOpcode(returnInsn.getOpcode());
            if (this.returnOpcode >= 0) {
                returnInsn.getOpcode();
            }
            this.returnOpcode = returnInsn.getOpcode();
            this.localReturns.add(new LocalReturn(returnInsn, insertBeforeInsn, sourceValueFrame));
            if (returnInsn.getOpcode() != 177) {
                this.returnVariableSize = (returnInsn.getOpcode() == 173 || returnInsn.getOpcode() == 175) ? 2 : 1;
            }
        }

        public final void transform(MethodNode methodNode) {
            int i;
            methodNode.getClass();
            int i2 = this.returnVariableSize;
            if (i2 > 0) {
                i = methodNode.maxLocals;
                methodNode.maxLocals = i2 + i;
            } else {
                i = -1;
            }
            Iterator it = this.localReturns.iterator();
            it.getClass();
            while (it.hasNext()) {
                LocalReturn localReturn = (LocalReturn) it.next();
                InsnList insnList = methodNode.instructions;
                insnList.getClass();
                localReturn.transform(insnList, i);
            }
        }
    }

    public MethodInliner(MethodNode methodNode, Parameters parameters, InliningContext inliningContext, FieldRemapper fieldRemapper, boolean z, Function0<String> function0, SourceMapCopier sourceMapCopier, InlineCallSiteInfo inlineCallSiteInfo, boolean z2, boolean z3, int i, int i2) {
        methodNode.getClass();
        parameters.getClass();
        inliningContext.getClass();
        fieldRemapper.getClass();
        function0.getClass();
        sourceMapCopier.getClass();
        inlineCallSiteInfo.getClass();
        this.node = methodNode;
        this.parameters = parameters;
        this.inliningContext = inliningContext;
        this.nodeRemapper = fieldRemapper;
        this.isSameModule = z;
        this.errorPrefixSupplier = function0;
        this.sourceMapper = sourceMapCopier;
        this.inlineCallSiteInfo = inlineCallSiteInfo;
        this.isInlineOnlyMethod = z2;
        this.shouldPreprocessApiVersionCalls = z3;
        this.defaultMaskStart = i;
        this.defaultMaskEnd = i2;
        this.languageVersionSettings = inliningContext.getState().getConfig().getLanguageVersionSettings();
        this.invokeCalls = new ArrayList<>();
        this.transformations = new ArrayList<>();
        this.currentTypeMapping = new HashMap<>();
        this.result = InlineResult.INSTANCE.create();
    }

    public static Frame a(int i, int i2) {
        return new Frame(i, i2);
    }

    public static boolean b(TryCatchBlockNode tryCatchBlockNode) {
        tryCatchBlockNode.getClass();
        return CoveringTryCatchNodeProcessorKt.isMeaningless(tryCatchBlockNode);
    }

    private final AnonymousObjectTransformationInfo buildConstructorInvocation(String anonymousType, String desc, Map<Integer, ? extends FunctionalArgument> lambdaMapping, boolean needReification, boolean capturesAnonymousObjectThatMustBeRegenerated) {
        return new AnonymousObjectTransformationInfo(anonymousType, needReification && !(this.inliningContext.isInliningLambda() && !(this.inliningContext.getLambdaInfo() instanceof DefaultLambda)), lambdaMapping, this.inliningContext.getClassRegeneration(), isAlreadyRegenerated(anonymousType), desc, false, this.inliningContext.getNameGenerator(), capturesAnonymousObjectThatMustBeRegenerated);
    }

    public static boolean c(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static int d(MethodInliner methodInliner, int i) {
        return methodInliner.sourceMapper.mapLineNumber(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public final InlineResult doInline(MethodVisitor adapter, LocalVarRemapper remapper, boolean remapReturn, Map<String, ? extends Label> returnLabels, int finallyDeepShift) throws AnalyzerException {
        MethodNode methodNodeMarkPlacesForInlineAndRemoveInlinable = markPlacesForInlineAndRemoveInlinable(this.node, returnLabels, finallyDeepShift);
        Label labelLinkedLabel = CodegenUtilKt.linkedLabel();
        boolean z = this.nodeRemapper instanceof RegeneratedLambdaFieldRemapper;
        MethodNode methodNodeDoInline = doInline(methodNodeMarkPlacesForInlineAndRemoveInlinable);
        if (!z) {
            INSTANCE.removeClosureAssertions(methodNodeDoInline);
        }
        methodNodeDoInline.instructions.resetLabels();
        int i = methodNodeDoInline.access;
        String str = methodNodeDoInline.name;
        String str2 = methodNodeDoInline.desc;
        String str3 = methodNodeDoInline.signature;
        List list = methodNodeDoInline.exceptions;
        MethodNode methodNode = new MethodNode(589824, i, str, str2, str3, list != null ? (String[]) list.toArray(new String[0]) : null);
        MethodVisitor remapVisitor = new RemapVisitor(methodNode, remapper, this.nodeRemapper, this.inliningContext.getTypeMapper());
        if (!z) {
            try {
                remapVisitor = new MethodBodyVisitor(remapVisitor);
            } catch (Throwable th) {
                throw wrapException(th, methodNodeDoInline, "couldn't inline method call");
            }
        }
        methodNodeDoInline.accept(remapVisitor);
        methodNode.visitLabel(labelLinkedLabel);
        if (this.inliningContext.isRoot()) {
            StackValue stackValue = remapper.remap(this.parameters.getArgsSizeOnStack() + 1).value;
            int i2 = this.lambdasFinallyBlocks;
            stackValue.getClass();
            InternalFinallyBlockInliner.processInlineFunFinallyBlocks(methodNode, i2, ((StackValue.Local) stackValue).index, this.languageVersionSettings.supportsFeature(LanguageFeature.ProperFinally));
        }
        if (remapReturn) {
            INSTANCE.processReturns(methodNode, returnLabels, labelLinkedLabel);
        }
        methodNode.accept(new SkipMaxAndEndVisitor(adapter));
        return this.result;
    }

    @JvmStatic
    public static final CapturedParamInfo findCapturedField(FieldInsnNode fieldInsnNode, FieldRemapper fieldRemapper) {
        return INSTANCE.findCapturedField(fieldInsnNode, fieldRemapper);
    }

    private final boolean isAlreadyRegenerated(String owner) {
        return this.inliningContext.getTypeRemapper().hasNoAdditionalMapping(owner);
    }

    private final boolean isAnonymousClassThatMustBeRegenerated(Type type) {
        if (type == null || type.getSort() != 10) {
            return false;
        }
        InliningContext inliningContext = this.inliningContext;
        String internalName = type.getInternalName();
        internalName.getClass();
        return inliningContext.isRegeneratedAnonymousObject(internalName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRegeneratingAnonymousObject() {
        return this.inliningContext.getParent() instanceof RegeneratedClassContext;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0068  */
    /* JADX WARN: Code duplicated, block: B:32:0x006c  */
    /* JADX WARN: Code duplicated, block: B:34:0x007a  */
    /* JADX WARN: Code duplicated, block: B:36:0x007e  */
    private final SmartSet<AbstractInsnNode> markObsoleteInstruction(InsnList instructions, Frame<BasicValue>[] sources) {
        boolean z;
        SmartSet<AbstractInsnNode> smartSetCreate = SmartSet.Companion.create();
        int i = 0;
        for (Object obj : instructions) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) obj;
            abstractInsnNode.getClass();
            if (!MethodInlinerUtilKt.isAloadBeforeCheckParameterIsNotNull(abstractInsnNode)) {
                int opcode = abstractInsnNode.getOpcode();
                if (opcode == 25) {
                    Frame<BasicValue> frame = sources[i2];
                    z = MethodInlinerUtilKt.getFunctionalArgument(frame != null ? (BasicValue) StackTransformationUtilsKt.top(frame) : null) instanceof LambdaInfo;
                } else if (opcode == 58) {
                    Frame<BasicValue> frame2 = sources[i];
                    z = MethodInlinerUtilKt.getFunctionalArgument(frame2 != null ? (BasicValue) StackTransformationUtilsKt.top(frame2) : null) instanceof LambdaInfo;
                } else {
                    if (opcode != 95) {
                        switch (opcode) {
                            case 178:
                            case 180:
                                Frame<BasicValue> frame3 = sources[i2];
                                z = MethodInlinerUtilKt.getFunctionalArgument(frame3 != null ? (BasicValue) StackTransformationUtilsKt.top(frame3) : null) instanceof LambdaInfo;
                                break;
                            case 179:
                            case 181:
                                Frame<BasicValue> frame4 = sources[i];
                                z = MethodInlinerUtilKt.getFunctionalArgument(frame4 != null ? (BasicValue) StackTransformationUtilsKt.top(frame4) : null) instanceof LambdaInfo;
                                break;
                        }
                    } else {
                        Frame<BasicValue> frame5 = sources[i];
                        z = true;
                        if (!(MethodInlinerUtilKt.getFunctionalArgument(frame5 != null ? (BasicValue) StackTransformationUtilsKt.peek(frame5, 0) : null) instanceof LambdaInfo)) {
                            Frame<BasicValue> frame6 = sources[i];
                            if (!(MethodInlinerUtilKt.getFunctionalArgument(frame6 != null ? (BasicValue) StackTransformationUtilsKt.peek(frame6, 1) : null) instanceof LambdaInfo)) {
                            }
                        }
                    }
                    z = false;
                }
                if (z) {
                    smartSetCreate.add(obj);
                }
            }
            i = i2;
        }
        return smartSetCreate;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v26, types: [org.jetbrains.kotlin.codegen.inline.InliningContext] */
    /* JADX WARN: Type inference failed for: r1v54 */
    private final MethodNode markPlacesForInlineAndRemoveInlinable(MethodNode node, Map<String, ? extends Label> returnLabels, int finallyDeepShift) throws AnalyzerException {
        MethodInliner methodInliner;
        ?? r1;
        InliningContext parent;
        int i;
        MethodInliner methodInliner2 = this;
        MethodNode methodNodePrepareNode = methodInliner2.prepareNode(node, finallyDeepShift);
        methodInliner2.preprocessNodeBeforeInline(methodNodePrepareNode, returnLabels);
        Frame<BasicValue>[] frameArrAnalyzeMethodNodeWithInterpreter = MethodInlinerUtilKt.analyzeMethodNodeWithInterpreter(methodNodePrepareNode, new FunctionalArgumentInterpreter(methodInliner2));
        InsnList insnList = methodNodePrepareNode.instructions;
        insnList.getClass();
        SmartSet<AbstractInsnNode> smartSetMarkObsoleteInstruction = methodInliner2.markObsoleteInstruction(insnList, frameArrAnalyzeMethodNodeWithInterpreter);
        boolean z = false;
        int constant = 0;
        for (MethodInsnNode methodInsnNode : new InsnSequence(insnList)) {
            Frame<BasicValue> frame = frameArrAnalyzeMethodNodeWithInterpreter[insnList.indexOf(methodInsnNode)];
            if (frame != null) {
                ReifiedTypeInliner.Companion companion = ReifiedTypeInliner.INSTANCE;
                if (companion.isNeedClassReificationMarker(methodInsnNode)) {
                    methodInliner = methodInliner2;
                    z = true;
                } else if (methodInsnNode instanceof MethodInsnNode) {
                    if (InlineCodegenUtilsKt.isFinallyStart(methodInsnNode)) {
                        AbstractInsnNode previous = methodInsnNode.getPrevious();
                        previous.getClass();
                        constant = InlineCodegenUtilsKt.getConstant(previous);
                    }
                    MethodInsnNode methodInsnNode2 = methodInsnNode;
                    String str = methodInsnNode2.owner;
                    String str2 = methodInsnNode2.name;
                    Type[] argumentTypes = Type.getArgumentTypes(methodInsnNode2.desc);
                    int length = argumentTypes.length + 1;
                    boolean z2 = z;
                    int stackSize = frame.getStackSize() - length;
                    str.getClass();
                    str2.getClass();
                    if (InlineCodegenUtilsKt.isInvokeOnLambda(str, str2)) {
                        methodInliner2.invokeCalls.add(new InvokeCall(MethodInlinerUtilKt.getFunctionalArgument(frame.getStack(stackSize)), constant));
                    } else if (InlineCodegenUtilsKt.isSamWrapperConstructorCall(str, str2)) {
                        methodInliner2.recordTransformation(new SamWrapperTransformationInfo(str, methodInliner2.inliningContext, methodInliner2.isAlreadyRegenerated(str)));
                    } else if (InlineCodegenUtilsKt.isAnonymousConstructorCall(str, str2)) {
                        HashMap map = new HashMap();
                        boolean z3 = false;
                        int i2 = 0;
                        int size = 0;
                        while (i2 < length) {
                            String str3 = str;
                            FunctionalArgument functionalArgument = MethodInlinerUtilKt.getFunctionalArgument(frame.getStack(stackSize + i2));
                            if (functionalArgument != null) {
                                i = stackSize;
                                map.put(Integer.valueOf(size), functionalArgument);
                            } else {
                                i = stackSize;
                                if (i2 < argumentTypes.length && methodInliner2.isAnonymousClassThatMustBeRegenerated(argumentTypes[i2])) {
                                    z3 = true;
                                }
                            }
                            size += i2 == 0 ? 1 : argumentTypes[i2 - 1].getSize();
                            i2++;
                            str = str3;
                            stackSize = i;
                        }
                        String str4 = methodInsnNode2.desc;
                        str4.getClass();
                        methodInliner = methodInliner2;
                        methodInliner.recordTransformation(methodInliner2.buildConstructorInvocation(str, str4, map, z2, z3));
                        z = false;
                    } else {
                        z = z2;
                        methodInliner = methodInliner2;
                        if (methodInliner.inliningContext.isInliningLambda() && companion.isOperationReifiedMarker(methodInsnNode)) {
                            ReificationArgument reificationArgument = ReifiedTypeInlinerKt.getReificationArgument(methodInsnNode2);
                            reificationArgument.getClass();
                            methodInliner.result.getReifiedTypeParametersUsages().addUsedReifiedParameter(reificationArgument.getParameterName());
                        }
                    }
                    z = z2;
                    methodInliner = methodInliner2;
                } else {
                    methodInliner = methodInliner2;
                    Object functionalArgument2 = null;
                    if (methodInsnNode.getOpcode() == 178) {
                        FieldInsnNode fieldInsnNode = (FieldInsnNode) methodInsnNode;
                        String str5 = fieldInsnNode.owner;
                        str5.getClass();
                        String str6 = fieldInsnNode.name;
                        str6.getClass();
                        if (InlineCodegenUtilsKt.isAnonymousSingletonLoad(str5, str6)) {
                            methodInliner.recordTransformation(new AnonymousObjectTransformationInfo(str5, z, methodInliner.isAlreadyRegenerated(str5), true, methodInliner.inliningContext.getNameGenerator()));
                            z = false;
                        } else {
                            String str7 = fieldInsnNode.name;
                            str7.getClass();
                            if (InlineCodegenUtilsKt.isWhenMappingAccess(str5, str7)) {
                                methodInliner.recordTransformation(new WhenMappingTransformationInfo(str5, methodInliner.inliningContext.getNameGenerator(), methodInliner.isAlreadyRegenerated(str5), fieldInsnNode));
                            } else if (AssertCodegenUtilKt.isCheckAssertionsStatus(fieldInsnNode)) {
                                fieldInsnNode.owner = methodInliner.inlineCallSiteInfo.getOwnerClassName();
                                InliningContext parent2 = methodInliner.inliningContext.getParent();
                                InliningContext inliningContext = methodInliner.inliningContext;
                                if (parent2 == null) {
                                    parent = inliningContext;
                                    r1 = parent;
                                } else {
                                    boolean z4 = inliningContext.getParent() instanceof RegeneratedClassContext;
                                    InliningContext inliningContext2 = methodInliner.inliningContext;
                                    if (z4) {
                                        parent = inliningContext;
                                        parent = inliningContext2.getParent();
                                    } else {
                                        InliningContext parent3 = inliningContext2.getParent().getParent();
                                        InliningContext inliningContext3 = methodInliner.inliningContext;
                                        if (parent3 == null) {
                                            parent = inliningContext;
                                            parent = inliningContext3.getParent();
                                        } else {
                                            Object parent4 = inliningContext3.getParent().getParent();
                                            if (parent4 instanceof RegeneratedClassContext) {
                                                parent = inliningContext;
                                                functionalArgument2 = (RegeneratedClassContext) parent4;
                                            }
                                            if (functionalArgument2 == null) {
                                                throw new AssertionError("couldn't find class for $assertionsDisabled (context = " + methodInliner.inliningContext + ')');
                                            }
                                            r1 = functionalArgument2;
                                        }
                                    }
                                    parent = inliningContext;
                                    r1 = parent;
                                }
                                r1.setGenerateAssertField(true);
                            } else {
                                continue;
                            }
                        }
                    } else if (methodInsnNode.getOpcode() == 87) {
                        if (MethodInlinerUtilKt.getFunctionalArgument(StackTransformationUtilsKt.top(frame)) instanceof LambdaInfo) {
                            smartSetMarkObsoleteInstruction.add(methodInsnNode);
                        }
                    } else if (methodInsnNode.getOpcode() == 181) {
                        FieldInsnNode fieldInsnNode2 = (FieldInsnNode) methodInsnNode;
                        String str8 = fieldInsnNode2.name;
                        str8.getClass();
                        if (InlineCodegenUtilsKt.isCapturedFieldName(str8)) {
                            FieldRemapper fieldRemapper = methodInliner.nodeRemapper;
                            if ((fieldRemapper instanceof InlinedLambdaRemapper) && Intrinsics.areEqual(fieldRemapper.getOriginalLambdaInternalName(), fieldInsnNode2.owner)) {
                                LinkedHashSet linkedHashSet = new LinkedHashSet();
                                BasicValue basicValuePeek = StackTransformationUtilsKt.peek(frame, 1);
                                functionalArgument2 = basicValuePeek != null ? MethodInlinerUtilKt.getFunctionalArgument(basicValuePeek) : null;
                                if (functionalArgument2 instanceof LambdaInfo) {
                                    if (linkedHashSet.isEmpty()) {
                                        Intrinsics.areEqual(((LambdaInfo) functionalArgument2).getLambdaClassType().getInternalName(), methodInliner.nodeRemapper.getOriginalLambdaInternalName());
                                        FieldRemapper.Companion companion2 = FieldRemapper.INSTANCE;
                                        String str9 = fieldInsnNode2.name;
                                        str9.getClass();
                                        fieldInsnNode2.name = companion2.foldName(str9);
                                        fieldInsnNode2.setOpcode(179);
                                        smartSetMarkObsoleteInstruction.addAll(linkedHashSet);
                                        break;
                                    }
                                    Iterator it = linkedHashSet.iterator();
                                    do {
                                        if (!it.hasNext()) {
                                            Intrinsics.areEqual(((LambdaInfo) functionalArgument2).getLambdaClassType().getInternalName(), methodInliner.nodeRemapper.getOriginalLambdaInternalName());
                                            FieldRemapper.Companion companion3 = FieldRemapper.INSTANCE;
                                            String str10 = fieldInsnNode2.name;
                                            str10.getClass();
                                            fieldInsnNode2.name = companion3.foldName(str10);
                                            fieldInsnNode2.setOpcode(179);
                                            smartSetMarkObsoleteInstruction.addAll(linkedHashSet);
                                            break;
                                            break;
                                        }
                                    } while (((AbstractInsnNode) it.next()) instanceof VarInsnNode);
                                }
                            }
                        }
                    }
                }
            } else {
                methodInliner = methodInliner2;
                if (UtilKt.getNodeType(methodInsnNode) != 8) {
                    smartSetMarkObsoleteInstruction.add(methodInsnNode);
                }
            }
            methodInliner2 = methodInliner;
        }
        MethodInlinerUtilKt.remove(methodNodePrepareNode, (Collection<? extends AbstractInsnNode>) smartSetMarkObsoleteInstruction);
        List list = methodNodePrepareNode.tryCatchBlocks;
        final Function1 function1 = new Function1() { // from class: k1a
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MethodInliner.b((TryCatchBlockNode) obj));
            }
        };
        list.removeIf(new Predicate() { // from class: l1a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MethodInliner.c(function1, obj);
            }
        });
        return methodNodePrepareNode;
    }

    private final MethodNode prepareNode(MethodNode node, int finallyDeepShift) {
        List listListOf;
        InliningContext parent;
        node.instructions.resetLabels();
        final int capturedParametersSizeOnStack = this.parameters.getCapturedParametersSizeOnStack();
        final int realParametersSizeOnStack = this.parameters.getRealParametersSizeOnStack();
        boolean z = this.inliningContext.isInliningLambda() && (parent = this.inliningContext.getParent()) != null && !parent.isInliningLambda() && (this.inliningContext.getLambdaInfo() instanceof IrExpressionLambda);
        Type[] argumentTypes = z ? Type.getArgumentTypes(this.inliningContext.getLambdaInfo().getInvokeMethod().getDescriptor()) : Type.getArgumentTypes(node.desc);
        argumentTypes.getClass();
        Integer numValueOf = 0;
        if (argumentTypes.length == 0) {
            listListOf = CollectionsKt.listOf(numValueOf);
        } else {
            ArrayList arrayList = new ArrayList(argumentTypes.length + 1);
            arrayList.add(numValueOf);
            for (Type type : argumentTypes) {
                numValueOf = Integer.valueOf(numValueOf.intValue() + type.getSize());
                arrayList.add(numValueOf);
            }
            listListOf = arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        int length = argumentTypes.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Type type2 = argumentTypes[i];
            int i3 = i2 + 1;
            int i4 = this.defaultMaskStart;
            int i5 = this.defaultMaskEnd;
            int iIntValue = ((Number) listListOf.get(i2)).intValue();
            if (!(i4 <= iIntValue && iIntValue <= i5)) {
                arrayList2.add(type2);
            }
            i++;
            i2 = i3;
        }
        Type[] typeArr = (Type[]) ArraysKt.plus(arrayList2.toArray(new Type[0]), this.parameters.getCapturedTypes());
        int i6 = node.access;
        String str = node.name;
        String methodDescriptor = Type.getMethodDescriptor(Type.getReturnType(node.desc), (Type[]) Arrays.copyOf(typeArr, typeArr.length));
        String str2 = node.signature;
        List list = node.exceptions;
        final MethodNode methodNode = new MethodNode(589824, i6, str, methodDescriptor, str2, list != null ? (String[]) list.toArray(new String[0]) : null);
        InlineScopesGenerator inlineScopesGenerator = this.inliningContext.getInlineScopesGenerator();
        if (inlineScopesGenerator != null) {
            inlineScopesGenerator.addInlineScopesInfo(node, isRegeneratingAnonymousObject());
        }
        final boolean z2 = z;
        node.accept(new InlineMethodInstructionAdapter(methodNode) { // from class: org.jetbrains.kotlin.codegen.inline.MethodInliner$prepareNode$transformationVisitor$1
            private final boolean GENERATE_DEBUG_INFO;
            private final boolean isInliningLambda;

            {
                this.GENERATE_DEBUG_INFO = !this.this$0.isInlineOnlyMethod;
                this.isInliningLambda = this.this$0.nodeRemapper.getIsInsideInliningLambda();
            }

            private final String calculateNewNameUsingScopeNumbers(String name) {
                if (!StringsKt.startsWith$default(name, "this", false, 2, (Object) null)) {
                    return name;
                }
                InlineScopeInfo inlineScopeInfo = InlineScopeUtilsKt.getInlineScopeInfo(name);
                if (inlineScopeInfo == null) {
                    return "this_";
                }
                return "this_\\" + inlineScopeInfo.getScopeNumber();
            }

            private final String calculateNewNameUsingTheOldScheme(String name) {
                if (Intrinsics.areEqual(name, "this")) {
                    name = "this_";
                }
                return name + InlineCodegenUtilsKt.INLINE_FUN_VAR_SUFFIX;
            }

            /* JADX WARN: Multi-variable type inference failed */
            private final int getNewIndex(int var) {
                LambdaInfo lambdaInfo = this.this$0.inliningContext.getLambdaInfo();
                int size = 0;
                if (!z2) {
                    return var + (var >= realParametersSizeOnStack ? capturedParametersSizeOnStack : 0);
                }
                Type[] argumentTypes2 = lambdaInfo.getInvokeMethod().getArgumentTypes();
                argumentTypes2.getClass();
                Iterator it = ArraysKt.slice(argumentTypes2, RangesKt.until(0, ((IrExpressionLambda) lambdaInfo).getNonRegularParametersCount())).iterator();
                while (it.hasNext()) {
                    size += ((Type) it.next()).getSize();
                }
                if (var >= this.this$0.parameters.getArgsSizeOnStack()) {
                    return var;
                }
                int i7 = capturedParametersSizeOnStack;
                if (var >= size + i7) {
                    return var - i7;
                }
                return var >= size ? (var + realParametersSizeOnStack) - size : var;
            }

            public void visitIincInsn(int var, int increment) {
                super.visitIincInsn(getNewIndex(var), increment);
            }

            public void visitLineNumber(int line, Label start) {
                start.getClass();
                if (this.isInliningLambda || this.GENERATE_DEBUG_INFO) {
                    super/*org.jetbrains.org.objectweb.asm.MethodVisitor*/.visitLineNumber(line, start);
                }
            }

            public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                name.getClass();
                desc.getClass();
                start.getClass();
                end.getClass();
                if (this.isInliningLambda || this.GENERATE_DEBUG_INFO) {
                    boolean zStartsWith$default = StringsKt.startsWith$default(name, "$i$f$", false, 2, (Object) null);
                    if (this.this$0.inliningContext.isRoot() && !zStartsWith$default) {
                        name = this.this$0.inliningContext.getInlineScopesGenerator() != null ? calculateNewNameUsingScopeNumbers(name) : calculateNewNameUsingTheOldScheme(name);
                    }
                    super/*org.jetbrains.org.objectweb.asm.MethodVisitor*/.visitLocalVariable(name, desc, signature, start, end, getNewIndex(index));
                }
            }

            public void visitMaxs(int maxStack, int maxLocals) {
                super/*org.jetbrains.org.objectweb.asm.MethodVisitor*/.visitMaxs(maxStack, maxLocals + capturedParametersSizeOnStack);
            }

            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                owner.getClass();
                name.getClass();
                desc.getClass();
                if (!Intrinsics.areEqual(InlineCodegenUtilsKt.DEFAULT_LAMBDA_FAKE_CALL, owner)) {
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                    return;
                }
                FunctionalArgument functionalArgumentIfExists$org_jetbrains_kotlin_backend = this.this$0.getFunctionalArgumentIfExists$org_jetbrains_kotlin_backend(Integer.parseInt(StringsKt.substringAfter$default(name, InlineCodegenUtilsKt.DEFAULT_LAMBDA_FAKE_CALL, (String) null, 2, (Object) null)));
                functionalArgumentIfExists$org_jetbrains_kotlin_backend.getClass();
                DefaultLambda defaultLambda = (DefaultLambda) functionalArgumentIfExists$org_jetbrains_kotlin_backend;
                for (CapturedParamDesc capturedParamDesc : CollectionsKt.asReversed(defaultLambda.getCapturedVars())) {
                    Type originalBoundReceiverType = defaultLambda.getOriginalBoundReceiverType();
                    if (originalBoundReceiverType != null) {
                        StackValue.coerce(originalBoundReceiverType, capturedParamDesc.getType(), new InstructionAdapter(this));
                    }
                    super.visitFieldInsn(179, capturedParamDesc.getContainingLambdaName(), InlineCodegenUtilsKt.CAPTURED_FIELD_FOLD_PREFIX + capturedParamDesc.getFieldName(), capturedParamDesc.getType().getDescriptor());
                }
            }

            public void visitVarInsn(int opcode, int var) {
                super.visitVarInsn(opcode, getNewIndex(var));
            }
        });
        transformCaptured(methodNode);
        INSTANCE.transformFinallyDeepIndex(methodNode, finallyDeepShift);
        return methodNode;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Multi-variable type inference failed */
    private final void preprocessNodeBeforeInline(MethodNode node, Map<String, ? extends Label> returnLabels) throws AnalyzerException {
        try {
            new InplaceArgumentsMethodTransformer().transform("fake", node);
            new FixStackWithLabelNormalizationMethodTransformer().transform("fake", node);
            new TemporaryVariablesEliminationTransformer().transform("fake", node);
            if (this.shouldPreprocessApiVersionCalls) {
                new ApiVersionCallsPreprocessingMethodTransformer(this.inliningContext.getState().getConfig().getLanguageVersionSettings().getApiVersion()).transform("fake", node);
            }
            removeFakeVariablesInitializationIfPresent(node);
            Frame[] frameArrAnalyze = new FastStackAnalyzer("<fake>", node, new FixStackInterpreter(), new Function2() { // from class: j1a
                public final Object invoke(Object obj, Object obj2) {
                    return MethodInliner.a(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }).analyze();
            LocalReturnsNormalizer localReturnsNormalizer = new LocalReturnsNormalizer();
            AbstractInsnNode[] array = node.instructions.toArray();
            array.getClass();
            int length = array.length;
            for (int i = 0; i < length; i++) {
                AbstractInsnNode abstractInsnNode = array[i];
                Frame frame = frameArrAnalyze[i];
                if (frame != null && InlineCodegenUtilsKt.isReturnOpcode(abstractInsnNode.getOpcode())) {
                    String markedReturnLabelOrNull = InlineCodegenUtilsKt.getMarkedReturnLabelOrNull(abstractInsnNode);
                    if (markedReturnLabelOrNull == null) {
                        localReturnsNormalizer.addLocalReturnToTransform(abstractInsnNode, abstractInsnNode, frame);
                    } else if (returnLabels.containsKey(markedReturnLabelOrNull)) {
                        AbstractInsnNode previous = abstractInsnNode.getPrevious();
                        previous.getClass();
                        localReturnsNormalizer.addLocalReturnToTransform(abstractInsnNode, previous, frame);
                    }
                }
            }
            localReturnsNormalizer.transform(node);
        } catch (Throwable th) {
            throw wrapException(th, node, "couldn't inline method call");
        }
    }

    @JvmStatic
    public static final List<PointForExternalFinallyBlocks> processReturns(MethodNode methodNode, Map<String, ? extends Label> map, Label label) {
        return INSTANCE.processReturns(methodNode, map, label);
    }

    private final void recordTransformation(TransformationInfo info) {
        if (!this.inliningContext.isInliningLambda()) {
            this.inliningContext.getRoot().getState().getGlobalInlineContext().recordTypeFromInlineFunction(info.getOldClassName());
        }
        if (info.shouldRegenerate(this.isSameModule)) {
            this.inliningContext.recordRegeneratedAnonymousObject(info.getOldClassName());
        }
        this.transformations.add(info);
    }

    private final void removeFakeVariablesInitializationIfPresent(MethodNode node) {
        VarInsnNode[] array = node.instructions.toArray();
        boolean[] zArr = new boolean[node.maxLocals];
        array.getClass();
        for (VarInsnNode varInsnNode : array) {
            varInsnNode.getClass();
            if (UtilKt.getNodeType(varInsnNode) == 2 && varInsnNode.getOpcode() == 21) {
                zArr[varInsnNode.var] = true;
            } else if (UtilKt.getNodeType(varInsnNode) == 10) {
                zArr[((IincInsnNode) varInsnNode).var] = true;
            }
        }
        for (LocalVariableNode localVariableNode : node.localVariables) {
            char cCharAt = localVariableNode.desc.charAt(0);
            if (cCharAt == 'B' || cCharAt == 'C' || cCharAt == 'I' || cCharAt == 'S' || cCharAt == 'Z') {
                zArr[localVariableNode.index] = true;
            }
        }
        boolean z = false;
        for (VarInsnNode varInsnNode2 : array) {
            if (varInsnNode2.getOpcode() == 3) {
                VarInsnNode next = varInsnNode2.getNext();
                if (next == null) {
                    break;
                }
                if (next.getOpcode() != 54) {
                    continue;
                } else {
                    AbstractInsnNode next2 = next.getNext();
                    if (next2 == null) {
                        break;
                    }
                    if (UtilKt.getNodeType(next2) == 8 && !zArr[next.var]) {
                        node.instructions.remove(varInsnNode2);
                        node.instructions.remove(next);
                        z = true;
                    }
                }
            }
        }
        if (z) {
            UtilKt.removeEmptyCatchBlocks(node);
        }
    }

    private final void transformCaptured(MethodNode node) {
        VarInsnNode varInsnNode;
        int opcode;
        if (this.nodeRemapper.getIsRoot()) {
            return;
        }
        if (this.inliningContext.isInliningLambda() && (this.inliningContext.getLambdaInfo() instanceof IrExpressionLambda)) {
            InliningContext parent = this.inliningContext.getParent();
            parent.getClass();
            if (!parent.isInliningLambda()) {
                List<CapturedParamDesc> capturedVars = this.inliningContext.getLambdaInfo().getCapturedVars();
                int realParametersSizeOnStack = this.parameters.getRealParametersSizeOnStack();
                List<CapturedParamDesc> list = capturedVars;
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
                for (CapturedParamDesc capturedParamDesc : list) {
                    Integer numValueOf = Integer.valueOf(realParametersSizeOnStack);
                    realParametersSizeOnStack += capturedParamDesc.getType().getSize();
                    Pair pair = TuplesKt.to(numValueOf, capturedParamDesc);
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                }
                AbstractInsnNode first = node.instructions.getFirst();
                while (first != null) {
                    if ((first instanceof VarInsnNode) && 21 <= (opcode = (varInsnNode = (VarInsnNode) first).getOpcode()) && opcode < 26 && linkedHashMap.containsKey(Integer.valueOf(varInsnNode.var))) {
                        Object obj = linkedHashMap.get(Integer.valueOf(varInsnNode.var));
                        obj.getClass();
                        CapturedParamDesc capturedParamDesc2 = (CapturedParamDesc) obj;
                        AbstractInsnNode fieldInsnNode = new FieldInsnNode(178, capturedParamDesc2.getContainingLambdaName(), FieldRemapper.INSTANCE.foldName(capturedParamDesc2.getFieldName()), capturedParamDesc2.getType().getDescriptor());
                        node.instructions.insertBefore(first, fieldInsnNode);
                        node.instructions.remove(first);
                        first = fieldInsnNode;
                    }
                    first = first.getNext();
                }
            }
        }
        AbstractInsnNode first2 = node.instructions.getFirst();
        while (first2 != null) {
            if (first2 instanceof VarInsnNode) {
                VarInsnNode varInsnNode2 = (VarInsnNode) first2;
                if (varInsnNode2.getOpcode() == 25 && (varInsnNode2.var == 0 || this.nodeRemapper.getIsConstructor())) {
                    AbstractInsnNode abstractInsnNodeFoldFieldAccessChainIfNeeded = this.nodeRemapper.foldFieldAccessChainIfNeeded(INSTANCE.getCapturedFieldAccessChain(varInsnNode2), node);
                    if (abstractInsnNodeFoldFieldAccessChainIfNeeded != null) {
                        first2 = abstractInsnNodeFoldFieldAccessChainIfNeeded;
                    }
                }
            }
            first2 = first2.getNext();
        }
    }

    private final void updateCallSiteLineNumbers(MethodNode resultNode, MethodNode inlinedNode) {
        List<LocalVariableNode> list;
        List list2 = inlinedNode.localVariables;
        if (list2 == null || (list = resultNode.localVariables) == null || list2.isEmpty() || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            String str = ((LocalVariableNode) obj).name;
            str.getClass();
            if (JvmAbi.isFakeLocalVariableForInline(str)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((LocalVariableNode) it.next()).name);
        }
        Set mutableSet = CollectionsKt.toMutableSet(arrayList2);
        if (!isRegeneratingAnonymousObject()) {
            final Map<Label, Integer> labelToIndexMap = InlineScopesGeneratorKt.getLabelToIndexMap(inlinedNode);
            mutableSet.remove(((LocalVariableNode) CollectionsKt.first(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.codegen.inline.MethodInliner$updateCallSiteLineNumbers$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) labelToIndexMap.get(((LocalVariableNode) t).start.getLabel()), (Integer) labelToIndexMap.get(((LocalVariableNode) t2).start.getLabel()));
                }
            }))).name);
        }
        for (LocalVariableNode localVariableNode : list) {
            String str2 = localVariableNode.name;
            str2.getClass();
            if (JvmAbi.isFakeLocalVariableForInline(str2) && mutableSet.contains(str2)) {
                localVariableNode.name = InlineScopesGeneratorKt.updateCallSiteLineNumber(str2, (Function1<? super Integer, Integer>) new Function1() { // from class: m1a
                    public final Object invoke(Object obj2) {
                        return Integer.valueOf(MethodInliner.d(this.b, ((Integer) obj2).intValue()));
                    }
                });
            }
        }
    }

    private final RuntimeException wrapException(Throwable originalException, MethodNode node, String errorSuffix) {
        boolean z = originalException instanceof InlineException;
        Function0<String> function0 = this.errorPrefixSupplier;
        if (z) {
            return new InlineException(((String) function0.invoke()) + ": " + errorSuffix, originalException);
        }
        return new InlineException(((String) function0.invoke()) + ": " + errorSuffix + "\nCause: " + InlineCodegenUtilsKt.getNodeText(node), originalException);
    }

    public final FunctionalArgument getFunctionalArgumentIfExists$org_jetbrains_kotlin_backend(FieldInsnNode insnNode) {
        insnNode.getClass();
        String str = insnNode.name;
        str.getClass();
        if (StringsKt.startsWith$default(str, InlineCodegenUtilsKt.CAPTURED_FIELD_FOLD_PREFIX, false, 2, (Object) null)) {
            return INSTANCE.findCapturedField(insnNode, this.nodeRemapper).getFunctionalArgument();
        }
        SourceCompilerForInline sourceCompilerForInline = this.inliningContext.getRoot().getSourceCompilerForInline();
        String str2 = insnNode.name;
        str2.getClass();
        if (sourceCompilerForInline.isSuspendLambdaCapturedByOuterObjectOrLambda(str2)) {
            return NonInlineArgumentForInlineSuspendParameter.INLINE_LAMBDA_AS_VARIABLE;
        }
        return null;
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007b\u0002\b\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J:\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00122\u0006\u0010\u0006\u001a\u00020\r2\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0007b\u0002\b\n¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MethodInliner$Companion;", Argument.Delimiters.none, "<init>", "()V", "findCapturedField", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "fieldRemapper", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "Lkotlin/jvm/JvmStatic;", "removeClosureAssertions", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "transformFinallyDeepIndex", "finallyDeepShift", Argument.Delimiters.none, "getCapturedFieldAccessChain", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "aload0", "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "processReturns", "Lorg/jetbrains/kotlin/codegen/inline/MethodInliner$PointForExternalFinallyBlocks;", "returnLabels", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Label;", "endLabel", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public final List<AbstractInsnNode> getCapturedFieldAccessChain(VarInsnNode aload0) {
            List<AbstractInsnNode> listMutableListOf = CollectionsKt.mutableListOf(new AbstractInsnNode[]{aload0});
            AbstractInsnNode next = aload0.getNext();
            next.getClass();
            listMutableListOf.addAll(SequencesKt.toList(SequencesKt.takeWhile(SequencesKt.filter(new InsnSequence(next, null), new Function1() { // from class: n1a
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(MethodInliner.Companion.getCapturedFieldAccessChain$lambda$0$0((AbstractInsnNode) obj));
                }
            }), new Function1() { // from class: o1a
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(MethodInliner.Companion.getCapturedFieldAccessChain$lambda$0$1((AbstractInsnNode) obj));
                }
            })));
            AbstractInsnNode nextMeaningful = MethodInlinerUtilKt.getNextMeaningful((AbstractInsnNode) CollectionsKt.last(listMutableListOf));
            AbstractInsnNode abstractInsnNode = nextMeaningful instanceof FieldInsnNode ? nextMeaningful : null;
            if (abstractInsnNode != null) {
                listMutableListOf.add(abstractInsnNode);
            }
            return listMutableListOf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean getCapturedFieldAccessChain$lambda$0$0(AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            return UtilKt.isMeaningful(abstractInsnNode);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean getCapturedFieldAccessChain$lambda$0$1(AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            return (abstractInsnNode instanceof FieldInsnNode) && Intrinsics.areEqual("this$0", ((FieldInsnNode) abstractInsnNode).name);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeClosureAssertions(MethodNode node) {
            ArrayList arrayList = new ArrayList();
            InsnList insnList = node.instructions;
            insnList.getClass();
            Sequence<MethodInsnNode> sequenceFilter = SequencesKt.filter(new InsnSequence(insnList), new Function1<Object, Boolean>() { // from class: org.jetbrains.kotlin.codegen.inline.MethodInliner$Companion$removeClosureAssertions$$inlined$filterIsInstance$1
                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Boolean m65invoke(Object obj) {
                    return Boolean.valueOf(obj instanceof MethodInsnNode);
                }
            });
            sequenceFilter.getClass();
            for (MethodInsnNode methodInsnNode : sequenceFilter) {
                if (RedundantNullCheckMethodTransformerKt.isCheckParameterIsNotNull(methodInsnNode)) {
                    AbstractInsnNode previous = methodInsnNode.getPrevious();
                    if (previous != null) {
                        previous.getOpcode();
                    }
                    AbstractInsnNode previous2 = methodInsnNode.getPrevious().getPrevious();
                    if (previous2 != null) {
                        previous2.getOpcode();
                    }
                    arrayList.add(previous2);
                    arrayList.add(previous);
                    arrayList.add(methodInsnNode);
                }
            }
            MethodInlinerUtilKt.remove(node, arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void transformFinallyDeepIndex(MethodNode node, int finallyDeepShift) {
            if (finallyDeepShift == 0) {
                return;
            }
            for (MethodInsnNode first = node.instructions.getFirst(); first != null; first = first.getNext()) {
                if ((first instanceof MethodInsnNode) && InlineCodegenUtilsKt.isFinallyMarker(first)) {
                    AbstractInsnNode previous = first.getPrevious();
                    previous.getClass();
                    node.instructions.insert(previous, new LdcInsnNode(Integer.valueOf(InlineCodegenUtilsKt.getConstant(previous) + finallyDeepShift)));
                    node.instructions.remove(previous);
                }
            }
        }

        @JvmStatic
        public final CapturedParamInfo findCapturedField(FieldInsnNode node, FieldRemapper fieldRemapper) {
            node.getClass();
            fieldRemapper.getClass();
            String str = node.name;
            str.getClass();
            StringsKt.startsWith$default(str, InlineCodegenUtilsKt.CAPTURED_FIELD_FOLD_PREFIX, false, 2, (Object) null);
            int opcode = node.getOpcode();
            String str2 = node.owner;
            String str3 = node.name;
            str3.getClass();
            CapturedParamInfo capturedParamInfoFindField$default = FieldRemapper.findField$default(fieldRemapper, new FieldInsnNode(opcode, str2, str3.substring(3), node.desc), null, 2, null);
            if (capturedParamInfoFindField$default != null) {
                return capturedParamInfoFindField$default;
            }
            throw new IllegalStateException("Couldn't find captured field " + node.owner + '.' + node.name + " in " + fieldRemapper.getOriginalLambdaInternalName());
        }

        @JvmStatic
        public final List<PointForExternalFinallyBlocks> processReturns(MethodNode node, Map<String, ? extends Label> returnLabels, Label endLabel) {
            node.getClass();
            returnLabels.getClass();
            ArrayList arrayList = new ArrayList();
            InsnList insnList = node.instructions;
            AbstractInsnNode first = insnList.getFirst();
            while (first != null) {
                if (InlineCodegenUtilsKt.isReturnOpcode(first.getOpcode())) {
                    String markedReturnLabelOrNull = InlineCodegenUtilsKt.getMarkedReturnLabelOrNull(first);
                    Type returnType = InlineCodegenUtilsKt.getReturnType(first.getOpcode());
                    boolean z = markedReturnLabelOrNull == null || returnLabels.containsKey(markedReturnLabelOrNull);
                    Label label = returnLabels.get(markedReturnLabelOrNull);
                    if (label == null) {
                        label = endLabel;
                    }
                    if (z && markedReturnLabelOrNull != null) {
                        insnList.remove(first.getPrevious());
                    }
                    if (z && label != null) {
                        Object obj = label.info;
                        obj.getClass();
                        AbstractInsnNode jumpInsnNode = new JumpInsnNode(167, (LabelNode) obj);
                        insnList.insertBefore(first, new InsnNode(0));
                        if (!Intrinsics.areEqual(label, endLabel)) {
                            insnList.insertBefore(first, PseudoInsn.FIX_STACK_BEFORE_JUMP.createInsnNode());
                        }
                        insnList.insertBefore(first, jumpInsnNode);
                        insnList.remove(first);
                        first = jumpInsnNode;
                    }
                    LabelNode labelNode = new LabelNode();
                    insnList.insert(first, labelNode);
                    AbstractInsnNode previous = (z && Intrinsics.areEqual(label, endLabel)) ? first : first.getPrevious();
                    previous.getClass();
                    arrayList.add(new PointForExternalFinallyBlocks(previous, returnType, labelNode, label));
                }
                first = first.getNext();
            }
            return arrayList;
        }

        private Companion() {
        }
    }

    public final FunctionalArgument getFunctionalArgumentIfExists$org_jetbrains_kotlin_backend(int varIndex) {
        if (varIndex < this.parameters.getArgsSizeOnStack()) {
            return this.parameters.getParameterByDeclarationSlot(varIndex).getFunctionalArgument();
        }
        return null;
    }

    public /* synthetic */ MethodInliner(MethodNode methodNode, Parameters parameters, InliningContext inliningContext, FieldRemapper fieldRemapper, boolean z, Function0 function0, SourceMapCopier sourceMapCopier, InlineCallSiteInfo inlineCallSiteInfo, boolean z2, boolean z3, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(methodNode, parameters, inliningContext, fieldRemapper, z, function0, sourceMapCopier, inlineCallSiteInfo, (i3 & 256) != 0 ? false : z2, (i3 & 512) != 0 ? false : z3, (i3 & 1024) != 0 ? -1 : i, (i3 & 2048) != 0 ? -1 : i2);
    }

    public final InlineResult doInline(MethodVisitor adapter, LocalVarRemapper remapper, boolean remapReturn, Map<String, ? extends Label> returnLabels) {
        adapter.getClass();
        remapper.getClass();
        returnLabels.getClass();
        return doInline(adapter, remapper, remapReturn, returnLabels, 0);
    }

    private final MethodNode doInline(MethodNode node) {
        int line;
        LinkedList linkedList = new LinkedList(this.invokeCalls);
        MethodNode methodNode = new MethodNode(node.access, node.name, node.desc, node.signature, (String[]) null);
        Iterator<TransformationInfo> it = this.transformations.iterator();
        it.getClass();
        TypeRemapper typeRemapperCreateFrom = TypeRemapper.INSTANCE.createFrom(this.currentTypeMapping);
        LocalVariablesSorter localVariablesSorter = new LocalVariablesSorter(methodNode.access, methodNode.desc, InlineCodegenUtilsKt.wrapWithMaxLocalCalc(methodNode));
        MethodRemapper methodRemapper = new MethodRemapper(localVariablesSorter, new AsmTypeRemapper(typeRemapperCreateFrom, this.result));
        String strFindFakeContinuationConstructorClassName = CoroutineTransformer.INSTANCE.findFakeContinuationConstructorClassName(node);
        int iCalcMarkerShift = InlineCodegenUtilsKt.calcMarkerShift(this.parameters, node);
        Ref.IntRef intRef = new Ref.IntRef();
        if (this.isInlineOnlyMethod) {
            SourcePosition callSite = this.sourceMapper.getCallSite();
            callSite.getClass();
            line = callSite.getLine();
        } else {
            line = -1;
        }
        intRef.element = line;
        node.accept(new MethodInliner$doInline$lambdaInliner$1(methodRemapper, this, intRef, it, typeRemapperCreateFrom, strFindFakeContinuationConstructorClassName, linkedList, iCalcMarkerShift, node, localVariablesSorter, methodNode, this.parameters.getArgsSizeOnStack(), this.sourceMapper));
        CoroutineTransformerKt.surroundInvokesWithSuspendMarkersIfNeeded(methodNode);
        if (this.inliningContext.getInlineScopesGenerator() != null) {
            updateCallSiteLineNumbers(methodNode, node);
        }
        return methodNode;
    }
}
