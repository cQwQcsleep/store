package org.jetbrains.kotlin.codegen.coroutines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.TransformationMethodVisitor;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineTransformerMethodVisitor;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.inline.InplaceArgumentsMethodTransformer;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.codegen.optimization.common.ControlFlowGraph;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.common.VariableLivenessFrame;
import org.jetbrains.kotlin.codegen.optimization.common.VariableLivenessKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackMethodTransformer;
import org.jetbrains.kotlin.codegen.state.JvmBackendConfig;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0002\u008e\u0001B¹\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0010\u0010\n\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0007\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0007\u0012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00050\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0014J\u001a\u0010,\u001a\u00020\u0014*\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H\u0002J2\u00100\u001a\u00020\u0014*\u0002012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\f\u00102\u001a\b\u0012\u0004\u0012\u0002030.H\u0002J\u001e\u00104\u001a\u00020\u00142\u0006\u00105\u001a\u00020+2\f\u00106\u001a\b\u0012\u0004\u0012\u00020/0.H\u0002J>\u00107\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020\u00052\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0.H\u0002J\u0010\u0010=\u001a\u00020\u00142\u0006\u00105\u001a\u00020+H\u0002J\u001e\u0010>\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0.H\u0002J\u0010\u0010?\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0002J\u0012\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010B\u001a\u00020/H\u0002J\u0012\u0010C\u001a\u0004\u0018\u00010A2\u0006\u0010B\u001a\u00020/H\u0002J\u001e\u0010D\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H\u0002JD\u0010E\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u000e\u0010F\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010A0.2\u000e\u0010G\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010A0.2\u0012\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0.0.H\u0002J \u0010J\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020LH\u0002J\u0010\u0010N\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0002J\f\u0010O\u001a\u00020\u0014*\u000201H\u0002J\f\u0010P\u001a\u00020\u0014*\u000201H\u0002J\u0010\u0010Q\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0002J\u0016\u0010R\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010S\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0002J\u001e\u0010T\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H\u0002J\u0010\u0010U\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H\u0002J*\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0.0.2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u0010*\u001a\u00020+H\u0002J.\u0010W\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u0006\u0010X\u001a\u00020/2\u0006\u0010Y\u001a\u00020Z2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00050.H\u0002J \u0010\\\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u0006\u0010X\u001a\u00020/2\u0006\u0010]\u001a\u00020ZH\u0002J\u0014\u0010^\u001a\u00020\u0014*\u0002012\u0006\u0010Y\u001a\u00020ZH\u0002J*\u0010_\u001a\u0004\u0018\u0001032\u0006\u0010*\u001a\u00020+2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010X\u001a\u00020/2\u0006\u0010`\u001a\u00020<H\u0002J*\u0010a\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u0006\u0010X\u001a\u00020/2\b\u0010b\u001a\u0004\u0018\u0001032\u0006\u0010c\u001a\u00020<H\u0002J \u0010d\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u0006\u0010X\u001a\u00020/2\u0006\u0010e\u001a\u00020\u0005H\u0002J]\u0010f\u001a\b\u0012\u0004\u0012\u00020Z0g2\u0006\u0010*\u001a\u00020+2\u0016\u0010h\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020j\u0018\u00010i0\u000b2\f\u0010k\u001a\b\u0012\u0004\u0012\u00020l0.2\u0006\u0010m\u001a\u00020\u00052\u0012\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00050oH\u0002¢\u0006\u0002\u0010pJ\u001c\u0010q\u001a\u00020\u0011*\u00020+2\u0006\u0010r\u001a\u00020\u00052\u0006\u0010m\u001a\u00020\u0005H\u0002J \u0010s\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+2\u0006\u0010r\u001a\u00020\u00052\u0006\u0010m\u001a\u00020\u0005H\u0002JR\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0.0g2\u0006\u0010*\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0012\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Z0.0g2\u0012\u0010v\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Z0.0gH\u0002JL\u0010w\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050x0g2\u0006\u0010*\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0012\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Z0.0.2\u0006\u0010y\u001a\u00020\u0005H\u0002J0\u0010z\u001a\u0014\u0012\u0004\u0012\u00020/\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.0\u001c2\u0006\u0010*\u001a\u00020+2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H\u0002J;\u0010{\u001a\b\u0012\u0004\u0012\u00020/0.2\u0012\u0010|\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020/0\u001c2\u0006\u0010}\u001a\u00020~2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\u0006\u0010X\u001a\u00020/H\u0002J&\u0010\u0081\u0001\u001a\u00020\u00052\u0012\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Z0.0.2\u0007\u0010\u0082\u0001\u001a\u00020\u0005H\u0002J#\u0010\u0083\u0001\u001a\u0004\u0018\u00010I2\u0006\u0010*\u001a\u00020+2\u0006\u0010X\u001a\u00020/2\u0006\u0010Y\u001a\u00020ZH\u0002J%\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00072\u0006\u0010*\u001a\u00020+2\u0007\u0010\u0082\u0001\u001a\u00020\u00052\u0007\u0010\u0085\u0001\u001a\u00020\u0005H\u0002J5\u0010\u0088\u0001\u001a\u00020<2\u0007\u0010\u0089\u0001\u001a\u00020\u00052\u0006\u0010X\u001a\u00020/2\u0006\u0010*\u001a\u00020+2\u0006\u0010:\u001a\u00020\u00052\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010AH\u0002J\u0013\u0010\u008b\u0001\u001a\u0004\u0018\u00010A2\u0006\u0010X\u001a\u00020/H\u0002J\u0019\u0010\u008c\u0001\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+2\u0006\u0010B\u001a\u00020/H\u0002J\u0015\u0010\u008d\u0001\u001a\u00020\u0014*\u0002012\u0006\u0010&\u001a\u00020\u0005H\u0002R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00050\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u000e\u0010%\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010`\u001a\u00020<*\u00020/8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001¨\u0006\u008f\u0001"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/CoroutineTransformerMethodVisitor;", "Lorg/jetbrains/kotlin/codegen/TransformationMethodVisitor;", "delegate", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "access", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "desc", "signature", "exceptions", Argument.Delimiters.none, "containingClassInternalName", "obtainClassBuilderForCoroutineState", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "isForNamedFunction", Argument.Delimiters.none, "reportSuspensionPointInsideMonitor", "Lkotlin/Function1;", Argument.Delimiters.none, "lineNumber", "sourceFile", "config", "Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;", "needDispatchReceiver", "internalNameForDispatchReceiver", "initialVarsCountByType", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/MethodVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function1;ILjava/lang/String;Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;ZLjava/lang/String;Ljava/util/Map;)V", "classBuilderForCoroutineState", "getClassBuilderForCoroutineState", "()Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "classBuilderForCoroutineState$delegate", "Lkotlin/Lazy;", "continuationIndex", "dataIndex", "generatedCodeMarkers", "Lorg/jetbrains/kotlin/codegen/coroutines/GeneratedCodeMarkers;", "performTransformations", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "insertAsyncStackTraceEntriesForTailCallFunction", "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "callWrapContinuation", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "visibleLocals", "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "addLineNumberForSuspensionPointsAtTheSameLine", "node", "points", "generateStateMachinesTableswitch", "actualCoroutineStart", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "suspendMarkerVarIndex", "stateLabels", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "markFakeLineNumberForLambdaArgumentUnspilling", "initializeFakeInlinerVariables", "replaceReturnsUnitMarkersWithPushingUnitOnStack", "findSuspensionPointLineNumber", "Lorg/jetbrains/org/objectweb/asm/tree/LineNumberNode;", "suspensionPoint", "findSuspensionPointNextLineNumber", "checkForSuspensionPointInsideMonitor", "writeDebugMetadata", "suspensionPointLineNumbers", "suspensionPointNextLineNumbers", "spilledToLocalMapping", "Lorg/jetbrains/kotlin/codegen/coroutines/CoroutineTransformerMethodVisitor$SpilledVariableAndField;", "addContinuationAndResultToLvt", "startLabel", "Lorg/jetbrains/org/objectweb/asm/Label;", "resultStartLabel", "removeFakeContinuationConstructorCall", "getLabel", "setLabel", "prepareMethodNodePreludeForNamedFunction", "collectSuspensionPoints", "dropSuspensionMarkers", "dropUnboxInlineClassMarkers", "dropSuspendLambdaParameterMarkers", "spillVariables", "generateSpillAndUnspill", "suspension", "spillableVariable", "Lorg/jetbrains/kotlin/codegen/coroutines/SpillableVariable;", "suspendLambdaParameters", "generateFakeUnspill", "variable", "putOnStack", "findLocalCorrespondingToSpillableVariable", "tryCatchBlockEndLabelAfterSuspensionCall", "splitLvtRecord", "local", "localRestart", "cleanUpField", "fieldIndex", "calculateVariablesToSpill", Argument.Delimiters.none, "frames", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "livenessFrames", "Lorg/jetbrains/kotlin/codegen/optimization/common/VariableLivenessFrame;", "suspensionCallBeginIndex", "varsCountByType", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;Ljava/util/List;ILjava/util/Map;)Ljava/util/List;", "isFakeInlinerVariable", "slot", "checkWhetherVariableWillBeVisible", "mapFieldNameToVariable", "referencesToSpillBySuspensionPointIndex", "primitivesToSpillBySuspensionPointIndex", "calculateVariablesToCleanup", "Lkotlin/Pair;", "initialSpilledVariablesCount", "calculateSuspensionPointPredecessorsMapping", "findSuspensionPointPredecessors", "suspensionPointEnds", "cfg", "Lorg/jetbrains/kotlin/codegen/optimization/common/ControlFlowGraph;", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "countVariablesToSpill", "index", "calculateSpilledVariableAndField", "localVariableName", "suspensionCallIndex", "getTryCatchBlockEndLabelAfterSuspensionCall", "(Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;)Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "transformCallAndReturnStateLabel", "id", "suspendPointLineNumber", "nextDefinitelyHitLineNumber", "splitTryCatchBlocksContainingSuspensionPoint", "generateResumeWithExceptionCheck", "SpilledVariableAndField", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoroutineTransformerMethodVisitor extends TransformationMethodVisitor {

    /* JADX INFO: renamed from: classBuilderForCoroutineState$delegate, reason: from kotlin metadata */
    private final Lazy classBuilderForCoroutineState;
    private final JvmBackendConfig config;
    private final String containingClassInternalName;
    private int continuationIndex;
    private int dataIndex;
    private GeneratedCodeMarkers generatedCodeMarkers;
    private final Map<Type, Integer> initialVarsCountByType;
    private final String internalNameForDispatchReceiver;
    private final boolean isForNamedFunction;
    private final int lineNumber;
    private final boolean needDispatchReceiver;
    private final Function1<String, Unit> reportSuspensionPointInsideMonitor;
    private final String sourceFile;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/CoroutineTransformerMethodVisitor$SpilledVariableAndField;", Argument.Delimiters.none, "fieldName", Argument.Delimiters.none, "variableName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getFieldName", "()Ljava/lang/String;", "getVariableName", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SpilledVariableAndField {
        private final String fieldName;
        private final String variableName;

        public SpilledVariableAndField(String str, String str2) {
            str.getClass();
            str2.getClass();
            this.fieldName = str;
            this.variableName = str2;
        }

        public static /* synthetic */ SpilledVariableAndField copy$default(SpilledVariableAndField spilledVariableAndField, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = spilledVariableAndField.fieldName;
            }
            if ((i & 2) != 0) {
                str2 = spilledVariableAndField.variableName;
            }
            return spilledVariableAndField.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFieldName() {
            return this.fieldName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVariableName() {
            return this.variableName;
        }

        public final SpilledVariableAndField copy(String fieldName, String variableName) {
            fieldName.getClass();
            variableName.getClass();
            return new SpilledVariableAndField(fieldName, variableName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SpilledVariableAndField)) {
                return false;
            }
            SpilledVariableAndField spilledVariableAndField = (SpilledVariableAndField) other;
            return Intrinsics.areEqual(this.fieldName, spilledVariableAndField.fieldName) && Intrinsics.areEqual(this.variableName, spilledVariableAndField.variableName);
        }

        public final String getFieldName() {
            return this.fieldName;
        }

        public final String getVariableName() {
            return this.variableName;
        }

        public int hashCode() {
            return (this.fieldName.hashCode() * 31) + this.variableName.hashCode();
        }

        public String toString() {
            return "SpilledVariableAndField(fieldName=" + this.fieldName + ", variableName=" + this.variableName + ')';
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.coroutines.CoroutineTransformerMethodVisitor$replaceReturnsUnitMarkersWithPushingUnitOnStack$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<AbstractInsnNode, Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, InlineCodegenUtilsKt.class, "isReturnsUnitMarker", "isReturnsUnitMarker(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Z", 1);
        }

        public final Boolean invoke(AbstractInsnNode abstractInsnNode) {
            abstractInsnNode.getClass();
            return Boolean.valueOf(InlineCodegenUtilsKt.isReturnsUnitMarker(abstractInsnNode));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CoroutineTransformerMethodVisitor(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr, String str4, Function0<? extends ClassBuilder> function0, boolean z, Function1<? super String, Unit> function1, int i2, String str5, JvmBackendConfig jvmBackendConfig, boolean z2, String str6, Map<Type, Integer> map) {
        super(methodVisitor, i, str, str2, str3, strArr, 0, 64, null);
        methodVisitor.getClass();
        str.getClass();
        str2.getClass();
        str4.getClass();
        function0.getClass();
        function1.getClass();
        str5.getClass();
        jvmBackendConfig.getClass();
        map.getClass();
        this.containingClassInternalName = str4;
        this.isForNamedFunction = z;
        this.reportSuspensionPointInsideMonitor = function1;
        this.lineNumber = i2;
        this.sourceFile = str5;
        this.config = jvmBackendConfig;
        this.needDispatchReceiver = z2;
        this.internalNameForDispatchReceiver = str6;
        this.initialVarsCountByType = map;
        this.classBuilderForCoroutineState = LazyKt.lazy(function0);
        this.continuationIndex = z ? -1 : 0;
        this.dataIndex = z ? -1 : 1;
    }

    public static SuspensionPoint a(ControlFlowGraph controlFlowGraph, MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        Object next;
        abstractInsnNode.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (!collectSuspensionPoints$collectSuspensionPointEnds(controlFlowGraph, methodNode, abstractInsnNode, new LinkedHashSet(), linkedHashSet)) {
            Iterator it = linkedHashSet.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!InlineCodegenUtilsKt.isAfterSuspendMarker((AbstractInsnNode) next));
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) next;
            if (abstractInsnNode2 != null) {
                AbstractInsnNode previous = abstractInsnNode.getPrevious();
                previous.getClass();
                return new SuspensionPoint(previous, abstractInsnNode2);
            }
        }
        return null;
    }

    private final void addContinuationAndResultToLvt(MethodNode methodNode, Label startLabel, Label resultStartLabel) {
        Label label = new Label();
        InsnList insnList = methodNode.instructions;
        MethodNode methodNode2 = new MethodNode();
        new InstructionAdapter(methodNode2).mark(label);
        InsnList insnList2 = methodNode2.instructions;
        insnList2.getClass();
        insnList.add(insnList2);
        methodNode.visitLocalVariable("$continuation", CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE.getDescriptor(), (String) null, startLabel, label, this.continuationIndex);
        methodNode.visitLocalVariable(CoroutineConstantsKt.SUSPEND_CALL_RESULT_NAME, AsmTypes.OBJECT_TYPE.getDescriptor(), (String) null, resultStartLabel, label, this.dataIndex);
    }

    private final void addLineNumberForSuspensionPointsAtTheSameLine(MethodNode node, List<SuspensionPoint> points) {
        int size = CollectionsKt.dropLast(points, 1).size();
        int i = 0;
        while (i < size) {
            AbstractInsnNode next = points.get(i).getSuspensionCallEnd().getNext();
            next.getClass();
            int i2 = i + 1;
            Iterator it = new InsnSequence(next, points.get(i2).getSuspensionCallBegin()).iterator();
            do {
                if (!it.hasNext()) {
                    LineNumberNode lineNumberNodeFindSuspensionPointLineNumber = findSuspensionPointLineNumber(points.get(i));
                    if (lineNumberNodeFindSuspensionPointLineNumber == null) {
                        break;
                    }
                    InsnList insnList = node.instructions;
                    AbstractInsnNode suspensionCallBegin = points.get(i2).getSuspensionCallBegin();
                    MethodNode methodNode = new MethodNode();
                    InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
                    Label label = new Label();
                    instructionAdapter.mark(label);
                    instructionAdapter.visitLineNumber(lineNumberNodeFindSuspensionPointLineNumber.line, label);
                    Unit unit = Unit.INSTANCE;
                    InsnList insnList2 = methodNode.instructions;
                    insnList2.getClass();
                    insnList.insertBefore(suspensionCallBegin, insnList2);
                    break;
                }
            } while (!(((AbstractInsnNode) it.next()) instanceof LineNumberNode));
            i = i2;
        }
    }

    public static boolean b(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return dropSuspensionMarkers$isSuspensionMarkerToRemove(abstractInsnNode);
    }

    public static boolean c(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isBeforeUnboxInlineClassMarker(abstractInsnNode);
    }

    private final SpilledVariableAndField calculateSpilledVariableAndField(MethodNode methodNode, SuspensionPoint suspension, SpillableVariable spillableVariable) {
        String strLocalVariableName;
        if (spillableVariable.isNull() || (strLocalVariableName = localVariableName(methodNode, spillableVariable.getSlot(), methodNode.instructions.indexOf(suspension.getSuspensionCallBegin()))) == null) {
            return null;
        }
        String fieldName = spillableVariable.getFieldName();
        fieldName.getClass();
        return new SpilledVariableAndField(fieldName, strLocalVariableName);
    }

    private final Map<SuspensionPoint, List<SuspensionPoint>> calculateSuspensionPointPredecessorsMapping(MethodNode methodNode, List<SuspensionPoint> suspensionPoints) {
        ControlFlowGraph controlFlowGraphBuild$default = ControlFlowGraph.Companion.build$default(ControlFlowGraph.INSTANCE, methodNode, false, 2, null);
        List<SuspensionPoint> list = suspensionPoints;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((SuspensionPoint) obj).getSuspensionCallEnd(), obj);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj2 : list) {
            InsnList insnList = methodNode.instructions;
            insnList.getClass();
            linkedHashMap2.put(obj2, findSuspensionPointPredecessors(linkedHashMap, controlFlowGraphBuild$default, insnList, (SuspensionPoint) obj2));
        }
        return linkedHashMap2;
    }

    private final List<Pair<Integer, Integer>> calculateVariablesToCleanup(MethodNode methodNode, List<SuspensionPoint> suspensionPoints, List<? extends List<SpillableVariable>> referencesToSpillBySuspensionPointIndex, int initialSpilledVariablesCount) {
        int iCountVariablesToSpill;
        Map<SuspensionPoint, List<SuspensionPoint>> mapCalculateSuspensionPointPredecessorsMapping = calculateSuspensionPointPredecessorsMapping(methodNode, suspensionPoints);
        ArrayList arrayList = new ArrayList();
        int size = suspensionPoints.size();
        for (int i = 0; i < size; i++) {
            SuspensionPoint suspensionPoint = suspensionPoints.get(i);
            int iCountVariablesToSpill2 = countVariablesToSpill(referencesToSpillBySuspensionPointIndex, i);
            List<SuspensionPoint> list = mapCalculateSuspensionPointPredecessorsMapping.get(suspensionPoint);
            List<SuspensionPoint> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                iCountVariablesToSpill = initialSpilledVariablesCount;
            } else {
                Iterator<T> it = list.iterator();
                if (!it.hasNext()) {
                    z0e.a();
                    return null;
                }
                iCountVariablesToSpill = countVariablesToSpill(referencesToSpillBySuspensionPointIndex, suspensionPoints.indexOf((SuspensionPoint) it.next()));
                while (it.hasNext()) {
                    int iCountVariablesToSpill3 = countVariablesToSpill(referencesToSpillBySuspensionPointIndex, suspensionPoints.indexOf((SuspensionPoint) it.next()));
                    if (iCountVariablesToSpill < iCountVariablesToSpill3) {
                        iCountVariablesToSpill = iCountVariablesToSpill3;
                    }
                }
            }
            arrayList.add(TuplesKt.to(Integer.valueOf(iCountVariablesToSpill2), Integer.valueOf(iCountVariablesToSpill)));
        }
        return arrayList;
    }

    private final List<SpillableVariable> calculateVariablesToSpill(MethodNode methodNode, Frame<BasicValue>[] frames, List<VariableLivenessFrame> livenessFrames, int suspensionCallBeginIndex, Map<Type, Integer> varsCountByType) {
        boolean z;
        Frame<BasicValue> frame = frames[suspensionCallBeginIndex];
        if (frame == null) {
            x01.a("Suspension points containing in dead code must be removed");
            return null;
        }
        int locals = frame.getLocals();
        ArrayList arrayList = new ArrayList();
        VariableLivenessFrame variableLivenessFrame = livenessFrames.get(suspensionCallBeginIndex);
        String str = methodNode.desc;
        str.getClass();
        int lastParameterIndex = CoroutineTransformerMethodVisitorKt.getLastParameterIndex(str, methodNode.access);
        for (int i = 0; i < locals; i++) {
            if ((CoroutineTransformerMethodVisitorKt.isStatic(methodNode.access) || i != 0) && i != this.continuationIndex && i != this.dataIndex && ((!this.isForNamedFunction || i != lastParameterIndex) && !isFakeInlinerVariable(methodNode, i, suspensionCallBeginIndex))) {
                BasicValue local = frame.getLocal(i);
                if (local.getType() != null) {
                    List list = methodNode.localVariables;
                    list.getClass();
                    List list2 = list;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator it = list2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                            LocalVariableNode localVariableNode = (LocalVariableNode) it.next();
                            if (localVariableNode.index == i && methodNode.instructions.indexOf(localVariableNode.start) < suspensionCallBeginIndex && suspensionCallBeginIndex < methodNode.instructions.indexOf(localVariableNode.end)) {
                                z = true;
                                break;
                            }
                        }
                    } else {
                        z = false;
                        break;
                    }
                    boolean z2 = (variableLivenessFrame.isAlive(i) || z || !checkWhetherVariableWillBeVisible(methodNode, i, suspensionCallBeginIndex)) ? false : true;
                    if (variableLivenessFrame.isAlive(i) || ((this.config.getNullOutSpilledCoroutineLocalsUsingStdlibFunction() || this.config.getEnableDebugMode()) && (z || z2))) {
                        if (local == StrictBasicValue.NULL_VALUE) {
                            Type type = AsmTypes.OBJECT_TYPE;
                            type.getClass();
                            type.getClass();
                            arrayList.add(new SpillableVariable(local, type, type, null, i, z, variableLivenessFrame.isAlive(i)));
                        } else {
                            Type type2 = local.getType();
                            type2.getClass();
                            Type typeNormalize = CoroutineTransformerMethodVisitorKt.normalize(type2);
                            Integer num = varsCountByType.get(typeNormalize);
                            int iIntValue = num != null ? num.intValue() + 1 : 0;
                            varsCountByType.put(typeNormalize, Integer.valueOf(iIntValue));
                            arrayList.add(new SpillableVariable(local, type2, typeNormalize, CoroutineTransformerMethodVisitorKt.fieldNameForVar(typeNormalize, iIntValue), i, z, variableLivenessFrame.isAlive(i)));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private final void callWrapContinuation(InstructionAdapter instructionAdapter, String str, int i, int i2, List<? extends LocalVariableNode> list) {
        instructionAdapter.aconst(Type.getObjectType(this.containingClassInternalName).getClassName());
        instructionAdapter.aconst(str);
        instructionAdapter.aconst(this.sourceFile);
        instructionAdapter.iconst(i);
        instructionAdapter.iconst(list.size() * 2);
        instructionAdapter.newarray(AsmTypes.OBJECT_TYPE);
        int i3 = 0;
        for (LocalVariableNode localVariableNode : list) {
            int i4 = i3 + 1;
            instructionAdapter.dup();
            int i5 = i3 * 2;
            instructionAdapter.iconst(i5);
            instructionAdapter.aconst(localVariableNode.name);
            Type type = AsmTypes.OBJECT_TYPE;
            instructionAdapter.astore(type);
            instructionAdapter.dup();
            instructionAdapter.iconst(i5 + 1);
            Type type2 = Type.getType(localVariableNode.desc);
            instructionAdapter.load(localVariableNode.index, type2);
            StackValue.coerce(type2, type, instructionAdapter);
            instructionAdapter.astore(type);
            i3 = i4;
        }
        instructionAdapter.load(i2, CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE);
        instructionAdapter.invokestatic("kotlin/coroutines/jvm/internal/TailCallAsyncStackTraceEntryKt", "wrapContinuation", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", false);
    }

    private final void checkForSuspensionPointInsideMonitor(MethodNode methodNode, List<SuspensionPoint> suspensionPoints) {
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Iterator it = InsnSequenceKt.asSequence(insnList).iterator();
        while (it.hasNext()) {
            if (((AbstractInsnNode) it.next()).getOpcode() == 194) {
                ControlFlowGraph controlFlowGraphBuild$default = ControlFlowGraph.Companion.build$default(ControlFlowGraph.INSTANCE, methodNode, false, 2, null);
                HashMap map = new HashMap();
                checkForSuspensionPointInsideMonitor$addMonitorDepthToSuccs(methodNode, map, controlFlowGraphBuild$default, 0, 0);
                for (SuspensionPoint suspensionPoint : suspensionPoints) {
                    Integer num = (Integer) map.get(suspensionPoint.getSuspensionCallBegin());
                    if (num != null && num.intValue() > 0) {
                        String str = this.containingClassInternalName;
                        String str2 = methodNode.name;
                        String str3 = this.sourceFile;
                        LineNumberNode lineNumberNodeFindSuspensionPointLineNumber = findSuspensionPointLineNumber(suspensionPoint);
                        this.reportSuspensionPointInsideMonitor.invoke(String.valueOf(new StackTraceElement(str, str2, str3, lineNumberNodeFindSuspensionPointLineNumber != null ? lineNumberNodeFindSuspensionPointLineNumber.line : -1)));
                        return;
                    }
                }
                return;
            }
        }
    }

    private static final void checkForSuspensionPointInsideMonitor$addMonitorDepthToSuccs(MethodNode methodNode, HashMap<AbstractInsnNode, Integer> map, ControlFlowGraph controlFlowGraph, int i, int i2) {
        AbstractInsnNode abstractInsnNode = methodNode.instructions.get(i);
        map.put(abstractInsnNode, Integer.valueOf(i2));
        int opcode = abstractInsnNode.getOpcode();
        if (opcode == 194) {
            i2++;
        } else if (opcode == 195) {
            i2--;
        }
        Iterator<Integer> it = controlFlowGraph.getSuccessorsIndices(i).iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (map.get(methodNode.instructions.get(iIntValue)) == null) {
                checkForSuspensionPointInsideMonitor$addMonitorDepthToSuccs(methodNode, map, controlFlowGraph, iIntValue, i2);
            }
        }
    }

    private final boolean checkWhetherVariableWillBeVisible(MethodNode methodNode, int slot, int suspensionCallBeginIndex) {
        Object obj;
        List list = methodNode.localVariables;
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            LocalVariableNode localVariableNode = (LocalVariableNode) obj2;
            if (localVariableNode.index == slot && suspensionCallBeginIndex < methodNode.instructions.indexOf(localVariableNode.start)) {
                arrayList.add(obj2);
            }
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                int iIndexOf = methodNode.instructions.indexOf(((LocalVariableNode) next).start);
                do {
                    Object next2 = it.next();
                    int iIndexOf2 = methodNode.instructions.indexOf(((LocalVariableNode) next2).start);
                    if (iIndexOf > iIndexOf2) {
                        next = next2;
                        iIndexOf = iIndexOf2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        LocalVariableNode localVariableNode2 = (LocalVariableNode) obj;
        if (localVariableNode2 == null) {
            return false;
        }
        for (VarInsnNode next3 = methodNode.instructions.get(suspensionCallBeginIndex); next3 != null && !Intrinsics.areEqual(next3, localVariableNode2.start); next3 = next3.getNext()) {
            if (UtilKt.isStoreOperation(next3) && next3.var == slot) {
                return false;
            }
        }
        for (AbstractInsnNode next4 = localVariableNode2.start; next4 != null && !Intrinsics.areEqual(next4, localVariableNode2.end); next4 = next4.getNext()) {
            if (UtilKt.isMeaningful(next4)) {
                return true;
            }
        }
        return false;
    }

    private final void cleanUpField(MethodNode methodNode, SuspensionPoint suspension, int fieldIndex) {
        InsnList insnList = methodNode.instructions;
        AbstractInsnNode suspensionCallBegin = suspension.getSuspensionCallBegin();
        MethodNode methodNode2 = new MethodNode();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
        int i = this.continuationIndex;
        Type type = AsmTypes.OBJECT_TYPE;
        instructionAdapter.load(i, type);
        instructionAdapter.aconst((Object) null);
        instructionAdapter.putfield(getClassBuilderForCoroutineState().getThisName(), "L$" + fieldIndex, type.getDescriptor());
        Unit unit = Unit.INSTANCE;
        InsnList insnList2 = methodNode2.instructions;
        insnList2.getClass();
        insnList.insertBefore(suspensionCallBegin, insnList2);
    }

    private final List<SuspensionPoint> collectSuspensionPoints(final MethodNode methodNode) {
        final ControlFlowGraph controlFlowGraphBuild = ControlFlowGraph.INSTANCE.build(methodNode, false);
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        return SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.filter(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: r13
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoroutineTransformerMethodVisitor.f((AbstractInsnNode) obj));
            }
        }), new Function1() { // from class: s13
            public final Object invoke(Object obj) {
                return CoroutineTransformerMethodVisitor.a(controlFlowGraphBuild, methodNode, (AbstractInsnNode) obj);
            }
        }));
    }

    private static final boolean collectSuspensionPoints$collectSuspensionPointEnds(ControlFlowGraph controlFlowGraph, MethodNode methodNode, AbstractInsnNode abstractInsnNode, Set<AbstractInsnNode> set, Set<AbstractInsnNode> set2) {
        if (!set.add(abstractInsnNode)) {
            return false;
        }
        if (abstractInsnNode.getOpcode() == 176 || abstractInsnNode.getOpcode() == 191 || InlineCodegenUtilsKt.isAfterSuspendMarker(abstractInsnNode)) {
            set2.add(abstractInsnNode);
        } else {
            Iterator<Integer> it = controlFlowGraph.getSuccessorsIndices(abstractInsnNode).iterator();
            while (it.hasNext()) {
                AbstractInsnNode abstractInsnNode2 = methodNode.instructions.get(it.next().intValue());
                abstractInsnNode2.getClass();
                if (InlineCodegenUtilsKt.isBeforeSuspendMarker(abstractInsnNode2) || collectSuspensionPoints$collectSuspensionPointEnds(controlFlowGraph, methodNode, abstractInsnNode2, set, set2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final int countVariablesToSpill(List<? extends List<SpillableVariable>> referencesToSpillBySuspensionPointIndex, int index) {
        List<SpillableVariable> list = referencesToSpillBySuspensionPointIndex.get(index);
        int i = 0;
        if ((list instanceof Collection) && list.isEmpty()) {
            return 0;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((SpillableVariable) it.next()).isNull() && (i = i + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return i;
    }

    public static boolean d(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isSuspendLambdaParameterMarker(abstractInsnNode);
    }

    private final void dropSuspendLambdaParameterMarkers(MethodNode methodNode) {
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (AbstractInsnNode abstractInsnNode : SequencesKt.toList(SequencesKt.filter(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: p13
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoroutineTransformerMethodVisitor.d((AbstractInsnNode) obj));
            }
        }))) {
            InsnList insnList2 = methodNode.instructions;
            insnList2.getClass();
            UtilKt.removeAll(insnList2, CollectionsKt.listOf(new AbstractInsnNode[]{abstractInsnNode.getPrevious(), abstractInsnNode}));
        }
    }

    private final void dropSuspensionMarkers(MethodNode methodNode) {
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (AbstractInsnNode abstractInsnNode : SequencesKt.toList(SequencesKt.filter(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: q13
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoroutineTransformerMethodVisitor.b((AbstractInsnNode) obj));
            }
        }))) {
            InsnList insnList2 = methodNode.instructions;
            insnList2.getClass();
            UtilKt.removeAll(insnList2, CollectionsKt.listOf(new AbstractInsnNode[]{abstractInsnNode.getPrevious(), abstractInsnNode}));
        }
    }

    private static final boolean dropSuspensionMarkers$isSuspensionMarkerToRemove(AbstractInsnNode abstractInsnNode) {
        return InlineCodegenUtilsKt.isBeforeSuspendMarker(abstractInsnNode) || InlineCodegenUtilsKt.isAfterSuspendMarker(abstractInsnNode) || InlineCodegenUtilsKt.isBeforeSuspendUnitCallMarker(abstractInsnNode) || InlineCodegenUtilsKt.isBeforeSuspendGenericCallMarker(abstractInsnNode);
    }

    private final void dropUnboxInlineClassMarkers(MethodNode methodNode, List<SuspensionPoint> suspensionPoints) {
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (AbstractInsnNode abstractInsnNode : SequencesKt.toList(SequencesKt.filter(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: n13
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoroutineTransformerMethodVisitor.c((AbstractInsnNode) obj));
            }
        }))) {
            InsnList insnList2 = methodNode.instructions;
            insnList2.getClass();
            UtilKt.removeAll(insnList2, CollectionsKt.listOf(new AbstractInsnNode[]{abstractInsnNode.getPrevious(), abstractInsnNode}));
        }
        InsnList insnList3 = methodNode.instructions;
        insnList3.getClass();
        for (AbstractInsnNode abstractInsnNode2 : SequencesKt.toList(SequencesKt.filter(InsnSequenceKt.asSequence(insnList3), new Function1() { // from class: o13
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CoroutineTransformerMethodVisitor.e((AbstractInsnNode) obj));
            }
        }))) {
            InsnList insnList4 = methodNode.instructions;
            insnList4.getClass();
            UtilKt.removeAll(insnList4, CollectionsKt.listOf(new AbstractInsnNode[]{abstractInsnNode2.getPrevious().getPrevious(), abstractInsnNode2.getPrevious(), abstractInsnNode2}));
        }
        for (SuspensionPoint suspensionPoint : suspensionPoints) {
            InsnList insnList5 = methodNode.instructions;
            insnList5.getClass();
            UtilKt.removeAll(insnList5, suspensionPoint.getUnboxInlineClassInstructions());
        }
    }

    public static boolean e(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isAfterUnboxInlineClassMarker(abstractInsnNode);
    }

    public static boolean f(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isBeforeSuspendMarker(abstractInsnNode);
    }

    private final LocalVariableNode findLocalCorrespondingToSpillableVariable(MethodNode methodNode, SpillableVariable spillableVariable, SuspensionPoint suspension, LabelNode tryCatchBlockEndLabelAfterSuspensionCall) {
        ListIterator listIterator = methodNode.localVariables.listIterator();
        while (listIterator.hasNext()) {
            LocalVariableNode localVariableNode = (LocalVariableNode) listIterator.next();
            if (localVariableNode.index == spillableVariable.getSlot() && methodNode.instructions.indexOf(localVariableNode.start) <= methodNode.instructions.indexOf(suspension.getSuspensionCallBegin()) && methodNode.instructions.indexOf(localVariableNode.end) > methodNode.instructions.indexOf(tryCatchBlockEndLabelAfterSuspensionCall)) {
                return localVariableNode;
            }
        }
        return null;
    }

    private final LineNumberNode findSuspensionPointLineNumber(SuspensionPoint suspensionPoint) {
        AbstractInsnNode previous = suspensionPoint.getSuspensionCallBegin().getPrevious();
        while (previous != null && !(previous instanceof LineNumberNode)) {
            previous = previous.getPrevious();
        }
        return (LineNumberNode) previous;
    }

    private final LineNumberNode findSuspensionPointNextLineNumber(SuspensionPoint suspensionPoint) {
        AbstractInsnNode next = suspensionPoint.getSuspensionCallEnd().getNext();
        while (next != null && !(next instanceof LineNumberNode)) {
            next = next.getNext();
        }
        return (LineNumberNode) next;
    }

    private final List<SuspensionPoint> findSuspensionPointPredecessors(Map<AbstractInsnNode, SuspensionPoint> suspensionPointEnds, ControlFlowGraph cfg, InsnList instructions, SuspensionPoint suspension) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List listMutableListOf = CollectionsKt.mutableListOf(new AbstractInsnNode[]{suspension.getSuspensionCallBegin()});
        ArrayList arrayList = new ArrayList();
        while (!listMutableListOf.isEmpty()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) AddToStdlibKt.popLast(listMutableListOf);
            if (linkedHashSet.add(abstractInsnNode)) {
                SuspensionPoint suspensionPoint = suspensionPointEnds.get(abstractInsnNode);
                if (suspensionPoint != null) {
                    arrayList.add(suspensionPoint);
                } else {
                    List<Integer> predecessorsIndices = cfg.getPredecessorsIndices(abstractInsnNode);
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(predecessorsIndices, 10));
                    Iterator<T> it = predecessorsIndices.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(instructions.get(((Number) it.next()).intValue()));
                    }
                    listMutableListOf.addAll(arrayList2);
                }
            }
        }
        return arrayList;
    }

    private final void generateFakeUnspill(MethodNode methodNode, SuspensionPoint suspension, SpillableVariable variable) {
        InsnList insnList = methodNode.instructions;
        LabelNode tryCatchBlockEndLabelAfterSuspensionCall = getTryCatchBlockEndLabelAfterSuspensionCall(suspension);
        MethodNode methodNode2 = new MethodNode();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
        switch (variable.getNormalizedType().getSort()) {
            case 1:
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 4:
            case 5:
                instructionAdapter.iconst(0);
                break;
            case 6:
                instructionAdapter.fconst(0.0f);
                break;
            case 7:
                instructionAdapter.lconst(0L);
                break;
            case 8:
                instructionAdapter.dconst(0.0d);
                break;
            default:
                instructionAdapter.aconst((Object) null);
                break;
        }
        instructionAdapter.store(variable.getSlot(), variable.getNormalizedType());
        Unit unit = Unit.INSTANCE;
        InsnList insnList2 = methodNode2.instructions;
        insnList2.getClass();
        insnList.insert(tryCatchBlockEndLabelAfterSuspensionCall, insnList2);
    }

    private final void generateResumeWithExceptionCheck(InstructionAdapter instructionAdapter, int i) {
        GeneratedCodeMarkers.Companion companion = GeneratedCodeMarkers.INSTANCE;
        GeneratedCodeMarkers generatedCodeMarkers = this.generatedCodeMarkers;
        companion.markFakeLineNumber(instructionAdapter, generatedCodeMarkers != null ? Integer.valueOf(generatedCodeMarkers.getCheckResult()) : null);
        instructionAdapter.load(i, AsmTypes.OBJECT_TYPE);
        instructionAdapter.invokestatic("kotlin/ResultKt", "throwOnFailure", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, false);
    }

    private final void generateSpillAndUnspill(MethodNode methodNode, SuspensionPoint suspension, SpillableVariable spillableVariable, List<Integer> suspendLambdaParameters) {
        LocalVariableNode localVariableNodeFindLocalCorrespondingToSpillableVariable = findLocalCorrespondingToSpillableVariable(methodNode, spillableVariable, suspension, getTryCatchBlockEndLabelAfterSuspensionCall(suspension));
        LabelNode labelNodeLinkWithLabel = CodegenUtilKt.linkWithLabel(new LabelNode());
        if (spillableVariable.isNull()) {
            InsnList insnList = methodNode.instructions;
            LabelNode tryCatchBlockEndLabelAfterSuspensionCall = getTryCatchBlockEndLabelAfterSuspensionCall(suspension);
            MethodNode methodNode2 = new MethodNode();
            InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
            instructionAdapter.aconst((Object) null);
            instructionAdapter.store(spillableVariable.getSlot(), AsmTypes.OBJECT_TYPE);
            if (localVariableNodeFindLocalCorrespondingToSpillableVariable != null) {
                instructionAdapter.visitLabel(labelNodeLinkWithLabel.getLabel());
            }
            Unit unit = Unit.INSTANCE;
            InsnList insnList2 = methodNode2.instructions;
            insnList2.getClass();
            insnList.insert(tryCatchBlockEndLabelAfterSuspensionCall, insnList2);
            splitLvtRecord(methodNode, suspension, localVariableNodeFindLocalCorrespondingToSpillableVariable, labelNodeLinkWithLabel);
            return;
        }
        InsnList insnList3 = methodNode.instructions;
        boolean z = !CoroutineTransformerMethodVisitorKt.isStatic(methodNode.access) && spillableVariable.getSlot() == 0;
        if (!z) {
            AbstractInsnNode suspensionCallBegin = suspension.getSuspensionCallBegin();
            MethodNode methodNode3 = new MethodNode();
            InstructionAdapter instructionAdapter2 = new InstructionAdapter(methodNode3);
            instructionAdapter2.load(this.continuationIndex, AsmTypes.OBJECT_TYPE);
            if (this.config.getEnableDebugMode() || !spillableVariable.getShouldSpillNull()) {
                putOnStack(instructionAdapter2, spillableVariable);
            } else if (this.config.getNullOutSpilledCoroutineLocalsUsingStdlibFunction()) {
                putOnStack(instructionAdapter2, spillableVariable);
                CoroutineCodegenUtilKt.invokeNullOutSpilledVariable(instructionAdapter2);
            } else {
                instructionAdapter2.aconst((Object) null);
            }
            instructionAdapter2.putfield(getClassBuilderForCoroutineState().getThisName(), spillableVariable.getFieldName(), spillableVariable.getNormalizedType().getDescriptor());
            Unit unit2 = Unit.INSTANCE;
            InsnList insnList4 = methodNode3.instructions;
            insnList4.getClass();
            insnList3.insertBefore(suspensionCallBegin, insnList4);
        }
        if (suspendLambdaParameters.contains(Integer.valueOf(spillableVariable.getSlot())) || z) {
            return;
        }
        LabelNode tryCatchBlockEndLabelAfterSuspensionCall2 = getTryCatchBlockEndLabelAfterSuspensionCall(suspension);
        MethodNode methodNode4 = new MethodNode();
        InstructionAdapter instructionAdapter3 = new InstructionAdapter(methodNode4);
        instructionAdapter3.load(this.continuationIndex, AsmTypes.OBJECT_TYPE);
        instructionAdapter3.getfield(getClassBuilderForCoroutineState().getThisName(), spillableVariable.getFieldName(), spillableVariable.getNormalizedType().getDescriptor());
        StackValue.coerce(spillableVariable.getNormalizedType(), spillableVariable.getType(), instructionAdapter3);
        instructionAdapter3.store(spillableVariable.getSlot(), spillableVariable.getType());
        if (localVariableNodeFindLocalCorrespondingToSpillableVariable != null) {
            instructionAdapter3.visitLabel(labelNodeLinkWithLabel.getLabel());
        }
        Unit unit3 = Unit.INSTANCE;
        InsnList insnList5 = methodNode4.instructions;
        insnList5.getClass();
        insnList3.insert(tryCatchBlockEndLabelAfterSuspensionCall2, insnList5);
        splitLvtRecord(methodNode, suspension, localVariableNodeFindLocalCorrespondingToSpillableVariable, labelNodeLinkWithLabel);
    }

    private final void generateStateMachinesTableswitch(MethodNode methodNode, AbstractInsnNode actualCoroutineStart, int suspendMarkerVarIndex, List<SuspensionPoint> suspensionPoints, List<? extends LabelNode> stateLabels) {
        InsnList insnList = methodNode.instructions;
        LabelNode labelNode = new LabelNode();
        LabelNode labelNode2 = new LabelNode();
        LabelNode labelNode3 = new LabelNode();
        MethodNode methodNode2 = new MethodNode();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
        GeneratedCodeMarkers.Companion companion = GeneratedCodeMarkers.INSTANCE;
        GeneratedCodeMarkers generatedCodeMarkers = this.generatedCodeMarkers;
        companion.markFakeLineNumber(instructionAdapter, generatedCodeMarkers != null ? Integer.valueOf(generatedCodeMarkers.getTableswitch()) : null);
        Unit unit = Unit.INSTANCE;
        InsnList insnList2 = methodNode2.instructions;
        insnList2.getClass();
        insnList.insertBefore(actualCoroutineStart, insnList2);
        SpreadBuilder spreadBuilder = new SpreadBuilder(8);
        MethodNode methodNode3 = new MethodNode();
        CoroutineCodegenUtilKt.loadCoroutineSuspendedMarker(new InstructionAdapter(methodNode3));
        InsnList insnList3 = methodNode3.instructions;
        insnList3.getClass();
        spreadBuilder.addSpread(insnList3.toArray());
        spreadBuilder.add(labelNode);
        spreadBuilder.add(new LineNumberNode(this.lineNumber, labelNode));
        spreadBuilder.add(new VarInsnNode(58, suspendMarkerVarIndex));
        spreadBuilder.add(new VarInsnNode(25, this.continuationIndex));
        MethodNode methodNode4 = new MethodNode();
        getLabel(new InstructionAdapter(methodNode4));
        InsnList insnList4 = methodNode4.instructions;
        insnList4.getClass();
        spreadBuilder.addSpread(insnList4.toArray());
        int size = suspensionPoints.size();
        SpreadBuilder spreadBuilder2 = new SpreadBuilder(2);
        spreadBuilder2.add(labelNode2);
        spreadBuilder2.addSpread(stateLabels.toArray(new LabelNode[0]));
        spreadBuilder.add(new TableSwitchInsnNode(0, size, labelNode3, (LabelNode[]) spreadBuilder2.toArray(new LabelNode[spreadBuilder2.size()])));
        spreadBuilder.add(labelNode2);
        insnList.insertBefore(actualCoroutineStart, UtilKt.insnListOf((AbstractInsnNode[]) spreadBuilder.toArray(new AbstractInsnNode[spreadBuilder.size()])));
        MethodNode methodNode5 = new MethodNode();
        generateResumeWithExceptionCheck(new InstructionAdapter(methodNode5), this.dataIndex);
        InsnList insnList5 = methodNode5.instructions;
        insnList5.getClass();
        insnList.insert(labelNode2, insnList5);
        insnList.insert(insnList.getLast(), labelNode3);
        insnList.insert(insnList.getLast(), new LineNumberNode(this.lineNumber, labelNode3));
        AbstractInsnNode last = insnList.getLast();
        MethodNode methodNode6 = new MethodNode();
        InstructionAdapter instructionAdapter2 = new InstructionAdapter(methodNode6);
        GeneratedCodeMarkers generatedCodeMarkers2 = this.generatedCodeMarkers;
        companion.markFakeLineNumber(instructionAdapter2, generatedCodeMarkers2 != null ? Integer.valueOf(generatedCodeMarkers2.getUnreachable()) : null);
        InsnList insnList6 = methodNode6.instructions;
        insnList6.getClass();
        insnList.insert(last, insnList6);
        AbstractInsnNode last2 = insnList.getLast();
        MethodNode methodNode7 = new MethodNode();
        InstructionAdapter instructionAdapter3 = new InstructionAdapter(methodNode7);
        AsmUtil.genThrow(instructionAdapter3, "java/lang/IllegalStateException", CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
        instructionAdapter3.areturn(Type.VOID_TYPE);
        InsnList insnList7 = methodNode7.instructions;
        insnList7.getClass();
        insnList.insert(last2, insnList7);
    }

    private final ClassBuilder getClassBuilderForCoroutineState() {
        return (ClassBuilder) this.classBuilderForCoroutineState.getValue();
    }

    private final void getLabel(InstructionAdapter instructionAdapter) {
        instructionAdapter.getfield(Type.getObjectType(getClassBuilderForCoroutineState().getThisName()).getInternalName(), CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, Type.INT_TYPE.getDescriptor());
    }

    private final LabelNode getTryCatchBlockEndLabelAfterSuspensionCall(SuspensionPoint suspensionPoint) {
        suspensionPoint.getSuspensionCallEnd().getNext();
        LabelNode next = suspensionPoint.getSuspensionCallEnd().getNext();
        next.getClass();
        return next;
    }

    private final void initializeFakeInlinerVariables(MethodNode methodNode, List<? extends LabelNode> stateLabels) {
        for (LabelNode labelNode : stateLabels) {
            ArrayList arrayList = new ArrayList();
            for (LocalVariableNode localVariableNode : methodNode.localVariables) {
                String str = localVariableNode.name;
                str.getClass();
                if (JvmAbi.isFakeLocalVariableForInline(str) && methodNode.instructions.indexOf(localVariableNode.start) < methodNode.instructions.indexOf(labelNode) && methodNode.instructions.indexOf(labelNode) < methodNode.instructions.indexOf(localVariableNode.end)) {
                    LabelNode labelNode2 = localVariableNode.end;
                    LabelNode labelNode3 = new LabelNode();
                    localVariableNode.end = labelNode;
                    InsnList insnList = methodNode.instructions;
                    MethodNode methodNode2 = new MethodNode();
                    InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
                    instructionAdapter.iconst(0);
                    instructionAdapter.store(localVariableNode.index, Type.INT_TYPE);
                    Unit unit = Unit.INSTANCE;
                    InsnList insnList2 = methodNode2.instructions;
                    insnList2.getClass();
                    insnList2.add(labelNode3);
                    insnList.insert(labelNode, insnList2);
                    arrayList.add(new LocalVariableNode(localVariableNode.name, localVariableNode.desc, localVariableNode.signature, labelNode3, labelNode2, localVariableNode.index));
                }
            }
            methodNode.localVariables.addAll(arrayList);
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x015c  */
    /* JADX WARN: Code duplicated, block: B:47:0x0167  */
    /* JADX WARN: Code duplicated, block: B:51:0x0174  */
    /* JADX WARN: Code duplicated, block: B:54:0x01a3  */
    private final void insertAsyncStackTraceEntriesForTailCallFunction(MethodNode methodNode, List<SuspensionPoint> list) {
        CoroutineTransformerMethodVisitor coroutineTransformerMethodVisitor;
        AbstractInsnNode previous;
        if (this.config.getWrapContinuationForTailCallFunctions()) {
            String str = methodNode.desc;
            str.getClass();
            int lastParameterIndex = CoroutineTransformerMethodVisitorKt.getLastParameterIndex(str, methodNode.access);
            for (SuspensionPoint suspensionPoint : list) {
                LineNumberNode lineNumberNodeFindSuspensionPointLineNumber = this.findSuspensionPointLineNumber(suspensionPoint);
                int i = lineNumberNodeFindSuspensionPointLineNumber != null ? lineNumberNodeFindSuspensionPointLineNumber.line : 0;
                List list2 = methodNode.localVariables;
                list2.getClass();
                ArrayList arrayList = new ArrayList();
                for (Object obj : list2) {
                    LocalVariableNode localVariableNode = (LocalVariableNode) obj;
                    if (!Intrinsics.areEqual(localVariableNode.name, CoroutineConstantsKt.SUSPEND_FUNCTION_COMPLETION_PARAMETER_NAME) && methodNode.instructions.indexOf(localVariableNode.start) < methodNode.instructions.indexOf(suspensionPoint.getSuspensionCallBegin()) && methodNode.instructions.indexOf(suspensionPoint.getSuspensionCallBegin()) < methodNode.instructions.indexOf(localVariableNode.end)) {
                        arrayList.add(obj);
                    }
                }
                MethodInsnNode next = suspensionPoint.getSuspensionCallBegin().getNext().getNext();
                if (next instanceof MethodInsnNode) {
                    MethodInsnNode methodInsnNode = next;
                    String str2 = methodInsnNode.name;
                    str2.getClass();
                    if (StringsKt.endsWith$default(str2, "$default", false, 2, (Object) null)) {
                        Type methodType = Type.getMethodType(methodInsnNode.desc);
                        AbstractInsnNode previous2 = suspensionPoint.getSuspensionCallBegin().getPrevious();
                        Type[] argumentTypes = methodType.getArgumentTypes();
                        argumentTypes.getClass();
                        List listReversed = ArraysKt.reversed(argumentTypes);
                        int size = listReversed.size();
                        for (int i2 = 0; i2 < size && !Intrinsics.areEqual((Type) listReversed.get(i2), CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE); i2++) {
                            if (previous2 != null) {
                                previous2 = previous2.getPrevious();
                                while (previous2 != null && !UtilKt.isMeaningful(previous2)) {
                                    previous2 = previous2.getPrevious();
                                }
                            } else {
                                previous2 = null;
                            }
                        }
                        if (previous2 == null) {
                            coroutineTransformerMethodVisitor = this;
                            previous = suspensionPoint.getSuspensionCallBegin().getPrevious();
                            while (previous != null && !UtilKt.isMeaningful(previous)) {
                                previous = previous.getPrevious();
                            }
                            if (previous == null && previous.getOpcode() == 25) {
                                AbstractInsnNode previous3 = previous.getPrevious();
                                methodNode.instructions.remove(previous);
                                InsnList insnList = methodNode.instructions;
                                MethodNode methodNode2 = new MethodNode();
                                InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
                                String str3 = methodNode.name;
                                str3.getClass();
                                coroutineTransformerMethodVisitor.callWrapContinuation(instructionAdapter, str3, i, lastParameterIndex, arrayList);
                                Unit unit = Unit.INSTANCE;
                                InsnList insnList2 = methodNode2.instructions;
                                insnList2.getClass();
                                insnList.insert(previous3, insnList2);
                            } else {
                                InsnList insnList3 = methodNode.instructions;
                                AbstractInsnNode suspensionCallBegin = suspensionPoint.getSuspensionCallBegin();
                                MethodNode methodNode3 = new MethodNode();
                                InstructionAdapter instructionAdapter2 = new InstructionAdapter(methodNode3);
                                String str4 = methodNode.name;
                                str4.getClass();
                                coroutineTransformerMethodVisitor.callWrapContinuation(instructionAdapter2, str4, i, lastParameterIndex, arrayList);
                                instructionAdapter2.store(lastParameterIndex, CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE);
                                Unit unit2 = Unit.INSTANCE;
                                InsnList insnList4 = methodNode3.instructions;
                                insnList4.getClass();
                                insnList3.insertBefore(suspensionCallBegin, insnList4);
                            }
                        } else {
                            if (previous2.getOpcode() != 25) {
                                CoroutineTransformerMethodVisitor coroutineTransformerMethodVisitor2 = this;
                                throw new IllegalArgumentException(("Expected ALOAD opcode for continuation load before " + InlineCodegenUtilsKt.getInsnText(next) + " in method " + coroutineTransformerMethodVisitor2.containingClassInternalName + '.' + methodNode.name + ", found " + InlineCodegenUtilsKt.getInsnText(previous2)).toString());
                            }
                            AbstractInsnNode previous4 = previous2.getPrevious();
                            methodNode.instructions.remove(previous2);
                            InsnList insnList5 = methodNode.instructions;
                            MethodNode methodNode4 = new MethodNode();
                            InstructionAdapter instructionAdapter3 = new InstructionAdapter(methodNode4);
                            String str5 = methodNode.name;
                            str5.getClass();
                            coroutineTransformerMethodVisitor = this;
                            coroutineTransformerMethodVisitor.callWrapContinuation(instructionAdapter3, str5, i, lastParameterIndex, arrayList);
                            Unit unit3 = Unit.INSTANCE;
                            InsnList insnList6 = methodNode4.instructions;
                            insnList6.getClass();
                            insnList5.insert(previous4, insnList6);
                        }
                    } else {
                        coroutineTransformerMethodVisitor = this;
                        previous = suspensionPoint.getSuspensionCallBegin().getPrevious();
                        while (previous != null) {
                            previous = previous.getPrevious();
                        }
                        if (previous == null) {
                            InsnList insnList7 = methodNode.instructions;
                            AbstractInsnNode suspensionCallBegin2 = suspensionPoint.getSuspensionCallBegin();
                            MethodNode methodNode5 = new MethodNode();
                            InstructionAdapter instructionAdapter4 = new InstructionAdapter(methodNode5);
                            String str6 = methodNode.name;
                            str6.getClass();
                            coroutineTransformerMethodVisitor.callWrapContinuation(instructionAdapter4, str6, i, lastParameterIndex, arrayList);
                            instructionAdapter4.store(lastParameterIndex, CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE);
                            Unit unit4 = Unit.INSTANCE;
                            InsnList insnList8 = methodNode5.instructions;
                            insnList8.getClass();
                            insnList7.insertBefore(suspensionCallBegin2, insnList8);
                        } else {
                            InsnList insnList9 = methodNode.instructions;
                            AbstractInsnNode suspensionCallBegin3 = suspensionPoint.getSuspensionCallBegin();
                            MethodNode methodNode6 = new MethodNode();
                            InstructionAdapter instructionAdapter5 = new InstructionAdapter(methodNode6);
                            String str7 = methodNode.name;
                            str7.getClass();
                            coroutineTransformerMethodVisitor.callWrapContinuation(instructionAdapter5, str7, i, lastParameterIndex, arrayList);
                            instructionAdapter5.store(lastParameterIndex, CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE);
                            Unit unit5 = Unit.INSTANCE;
                            InsnList insnList10 = methodNode6.instructions;
                            insnList10.getClass();
                            insnList9.insertBefore(suspensionCallBegin3, insnList10);
                        }
                    }
                } else {
                    coroutineTransformerMethodVisitor = this;
                    previous = suspensionPoint.getSuspensionCallBegin().getPrevious();
                    while (previous != null) {
                        previous = previous.getPrevious();
                    }
                    if (previous == null) {
                        InsnList insnList11 = methodNode.instructions;
                        AbstractInsnNode suspensionCallBegin4 = suspensionPoint.getSuspensionCallBegin();
                        MethodNode methodNode7 = new MethodNode();
                        InstructionAdapter instructionAdapter6 = new InstructionAdapter(methodNode7);
                        String str8 = methodNode.name;
                        str8.getClass();
                        coroutineTransformerMethodVisitor.callWrapContinuation(instructionAdapter6, str8, i, lastParameterIndex, arrayList);
                        instructionAdapter6.store(lastParameterIndex, CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE);
                        Unit unit6 = Unit.INSTANCE;
                        InsnList insnList12 = methodNode7.instructions;
                        insnList12.getClass();
                        insnList11.insertBefore(suspensionCallBegin4, insnList12);
                    } else {
                        InsnList insnList13 = methodNode.instructions;
                        AbstractInsnNode suspensionCallBegin5 = suspensionPoint.getSuspensionCallBegin();
                        MethodNode methodNode8 = new MethodNode();
                        InstructionAdapter instructionAdapter7 = new InstructionAdapter(methodNode8);
                        String str9 = methodNode.name;
                        str9.getClass();
                        coroutineTransformerMethodVisitor.callWrapContinuation(instructionAdapter7, str9, i, lastParameterIndex, arrayList);
                        instructionAdapter7.store(lastParameterIndex, CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE);
                        Unit unit7 = Unit.INSTANCE;
                        InsnList insnList14 = methodNode8.instructions;
                        insnList14.getClass();
                        insnList13.insertBefore(suspensionCallBegin5, insnList14);
                    }
                }
                this = coroutineTransformerMethodVisitor;
            }
        }
    }

    private final boolean isFakeInlinerVariable(MethodNode methodNode, int i, int i2) {
        List list = methodNode.localVariables;
        list.getClass();
        ArrayList<LocalVariableNode> arrayList = new ArrayList();
        for (Object obj : list) {
            if (((LocalVariableNode) obj).index == i) {
                arrayList.add(obj);
            }
        }
        for (LocalVariableNode localVariableNode : arrayList) {
            if (methodNode.instructions.indexOf(localVariableNode.start) <= i2 && i2 < methodNode.instructions.indexOf(localVariableNode.end)) {
                String str = localVariableNode.name;
                str.getClass();
                return JvmAbi.isFakeLocalVariableForInline(str);
            }
        }
        return false;
    }

    private final String localVariableName(MethodNode methodNode, int index, int suspensionCallIndex) {
        Object next;
        List list = methodNode.localVariables;
        list.getClass();
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            LocalVariableNode localVariableNode = (LocalVariableNode) next;
            if (index == localVariableNode.index && methodNode.instructions.indexOf(localVariableNode.start) <= suspensionCallIndex && suspensionCallIndex < methodNode.instructions.indexOf(localVariableNode.end)) {
                break;
            }
        }
        LocalVariableNode localVariableNode2 = (LocalVariableNode) next;
        if (localVariableNode2 != null) {
            return localVariableNode2.name;
        }
        return null;
    }

    private final List<List<SpilledVariableAndField>> mapFieldNameToVariable(MethodNode methodNode, List<SuspensionPoint> suspensionPoints, List<List<SpillableVariable>> referencesToSpillBySuspensionPointIndex, List<List<SpillableVariable>> primitivesToSpillBySuspensionPointIndex) {
        ArrayList arrayList = new ArrayList();
        int size = suspensionPoints.size();
        for (int i = 0; i < size; i++) {
            SuspensionPoint suspensionPoint = suspensionPoints.get(i);
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = referencesToSpillBySuspensionPointIndex.get(i).iterator();
            while (it.hasNext()) {
                SpilledVariableAndField spilledVariableAndFieldCalculateSpilledVariableAndField = calculateSpilledVariableAndField(methodNode, suspensionPoint, (SpillableVariable) it.next());
                if (spilledVariableAndFieldCalculateSpilledVariableAndField != null) {
                    arrayList2.add(spilledVariableAndFieldCalculateSpilledVariableAndField);
                }
            }
            Iterator<T> it2 = primitivesToSpillBySuspensionPointIndex.get(i).iterator();
            while (it2.hasNext()) {
                SpilledVariableAndField spilledVariableAndFieldCalculateSpilledVariableAndField2 = calculateSpilledVariableAndField(methodNode, suspensionPoint, (SpillableVariable) it2.next());
                if (spilledVariableAndFieldCalculateSpilledVariableAndField2 != null) {
                    arrayList2.add(spilledVariableAndFieldCalculateSpilledVariableAndField2);
                }
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private final void markFakeLineNumberForLambdaArgumentUnspilling(MethodNode node) {
        Object next;
        AbstractInsnNode previous;
        AbstractInsnNode abstractInsnNode;
        InsnList insnList = node.instructions;
        insnList.getClass();
        Iterator it = insnList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                abstractInsnNode = (AbstractInsnNode) next;
                abstractInsnNode.getClass();
            }
        } while (!InlineCodegenUtilsKt.isSuspendLambdaParameterMarker(abstractInsnNode));
        AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) next;
        if (abstractInsnNode2 != null) {
            previous = abstractInsnNode2.getPrevious();
            while (previous != null && !(previous instanceof LabelNode)) {
                previous = previous.getPrevious();
            }
        } else {
            previous = null;
        }
        if (previous == null) {
            return;
        }
        InsnList insnList2 = node.instructions;
        MethodNode methodNode = new MethodNode();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
        GeneratedCodeMarkers.Companion companion = GeneratedCodeMarkers.INSTANCE;
        GeneratedCodeMarkers generatedCodeMarkers = this.generatedCodeMarkers;
        companion.markFakeLineNumber(instructionAdapter, generatedCodeMarkers != null ? Integer.valueOf(generatedCodeMarkers.getLambdaArgumentsUnspilling()) : null);
        Unit unit = Unit.INSTANCE;
        InsnList insnList3 = methodNode.instructions;
        insnList3.getClass();
        insnList2.insert(previous, insnList3);
    }

    private final LineNumberNode nextDefinitelyHitLineNumber(SuspensionPoint suspension) {
        for (AbstractInsnNode next = suspension.getSuspensionCallEnd().getNext(); next != null && !UtilKt.isBranchOrCall(next); next = next.getNext()) {
            if (next instanceof LineNumberNode) {
                return (LineNumberNode) next;
            }
        }
        return null;
    }

    private final void prepareMethodNodePreludeForNamedFunction(MethodNode methodNode) {
        Type objectType = Type.getObjectType(getClassBuilderForCoroutineState().getThisName());
        String str = methodNode.desc;
        str.getClass();
        int lastParameterIndex = CoroutineTransformerMethodVisitorKt.getLastParameterIndex(str, methodNode.access);
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Sequence<VarInsnNode> sequenceFilter = SequencesKt.filter(InsnSequenceKt.asSequence(insnList), new Function1<Object, Boolean>() { // from class: org.jetbrains.kotlin.codegen.coroutines.CoroutineTransformerMethodVisitor$prepareMethodNodePreludeForNamedFunction$$inlined$filterIsInstance$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m52invoke(Object obj) {
                return Boolean.valueOf(obj instanceof VarInsnNode);
            }
        });
        sequenceFilter.getClass();
        for (VarInsnNode varInsnNode : sequenceFilter) {
            if (varInsnNode.var == lastParameterIndex) {
                varInsnNode.getOpcode();
                varInsnNode.var = this.continuationIndex;
            }
        }
        InsnList insnList2 = methodNode.instructions;
        MethodNode methodNode2 = new MethodNode();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
        Label label = new Label();
        Label label2 = new Label();
        GeneratedCodeMarkers.Companion companion = GeneratedCodeMarkers.INSTANCE;
        GeneratedCodeMarkers generatedCodeMarkers = this.generatedCodeMarkers;
        companion.markFakeLineNumber(instructionAdapter, generatedCodeMarkers != null ? Integer.valueOf(generatedCodeMarkers.getCheckContinuation()) : null);
        instructionAdapter.visitVarInsn(25, lastParameterIndex);
        instructionAdapter.instanceOf(objectType);
        instructionAdapter.ifeq(label);
        instructionAdapter.visitVarInsn(25, lastParameterIndex);
        instructionAdapter.checkcast(objectType);
        instructionAdapter.visitVarInsn(58, this.continuationIndex);
        instructionAdapter.visitVarInsn(25, this.continuationIndex);
        getLabel(instructionAdapter);
        instructionAdapter.iconst(Integer.MIN_VALUE);
        Type type = Type.INT_TYPE;
        instructionAdapter.and(type);
        instructionAdapter.ifeq(label);
        instructionAdapter.visitVarInsn(25, this.continuationIndex);
        instructionAdapter.dup();
        getLabel(instructionAdapter);
        instructionAdapter.iconst(Integer.MIN_VALUE);
        instructionAdapter.sub(type);
        setLabel(instructionAdapter);
        instructionAdapter.goTo(label2);
        instructionAdapter.visitLabel(label);
        CoroutineTransformerMethodVisitorKt.generateContinuationConstructorCall(instructionAdapter, objectType, methodNode, this.needDispatchReceiver, this.internalNameForDispatchReceiver, this.containingClassInternalName, getClassBuilderForCoroutineState());
        instructionAdapter.visitVarInsn(58, this.continuationIndex);
        instructionAdapter.visitLabel(label2);
        instructionAdapter.visitVarInsn(25, this.continuationIndex);
        instructionAdapter.getfield(getClassBuilderForCoroutineState().getThisName(), CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, AsmTypes.OBJECT_TYPE.getDescriptor());
        instructionAdapter.visitVarInsn(58, this.dataIndex);
        Label label3 = new Label();
        instructionAdapter.visitLabel(label3);
        addContinuationAndResultToLvt(methodNode, label2, label3);
        InsnList insnList3 = methodNode2.instructions;
        insnList3.getClass();
        insnList2.insert(insnList3);
    }

    private final void putOnStack(InstructionAdapter instructionAdapter, SpillableVariable spillableVariable) {
        instructionAdapter.load(spillableVariable.getSlot(), spillableVariable.getType());
        StackValue.coerce(spillableVariable.getType(), spillableVariable.getNormalizedType(), instructionAdapter);
    }

    private final void removeFakeContinuationConstructorCall(MethodNode methodNode) {
        Object obj;
        Object next;
        AbstractInsnNode previous;
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Sequence<AbstractInsnNode> sequenceAsSequence = InsnSequenceKt.asSequence(insnList);
        Iterator it = sequenceAsSequence.iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!InlineCodegenUtilsKt.isBeforeFakeContinuationConstructorCallMarker((AbstractInsnNode) next));
        AbstractInsnNode abstractInsnNode = (AbstractInsnNode) next;
        if (abstractInsnNode == null || (previous = abstractInsnNode.getPrevious()) == null) {
            return;
        }
        for (Object obj2 : sequenceAsSequence) {
            if (InlineCodegenUtilsKt.isAfterFakeContinuationConstructorCallMarker((AbstractInsnNode) obj2)) {
                obj = obj2;
                break;
            }
        }
        if (obj == null) {
            x01.a("BeforeFakeContinuationConstructorCallMarker without AfterFakeContinuationConstructorCallMarker");
            return;
        }
        AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) obj;
        List list = SequencesKt.toList(new InsnSequence(previous, abstractInsnNode2));
        InsnList insnList2 = methodNode.instructions;
        insnList2.getClass();
        UtilKt.removeAll(insnList2, list);
        methodNode.instructions.set(abstractInsnNode2, new InsnNode(1));
    }

    private final void replaceReturnsUnitMarkersWithPushingUnitOnStack(MethodNode methodNode) {
        AbstractInsnNode next;
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (AbstractInsnNode abstractInsnNode : SequencesKt.toList(SequencesKt.filter(InsnSequenceKt.asSequence(insnList), AnonymousClass1.INSTANCE))) {
            AbstractInsnNode next2 = abstractInsnNode.getNext();
            if (next2 != null && (next = next2.getNext()) != null) {
                InlineCodegenUtilsKt.isAfterSuspendMarker(next);
            }
            InsnList insnList2 = methodNode.instructions;
            AbstractInsnNode next3 = abstractInsnNode.getNext().getNext();
            MethodNode methodNode2 = new MethodNode();
            InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
            instructionAdapter.pop();
            instructionAdapter.getstatic("kotlin/Unit", "INSTANCE", "Lkotlin/Unit;");
            Unit unit = Unit.INSTANCE;
            InsnList insnList3 = methodNode2.instructions;
            insnList3.getClass();
            insnList2.insert(next3, insnList3);
            InsnList insnList4 = methodNode.instructions;
            insnList4.getClass();
            UtilKt.removeAll(insnList4, CollectionsKt.listOf(new AbstractInsnNode[]{abstractInsnNode.getPrevious(), abstractInsnNode}));
        }
    }

    private final void setLabel(InstructionAdapter instructionAdapter) {
        instructionAdapter.putfield(Type.getObjectType(getClassBuilderForCoroutineState().getThisName()).getInternalName(), CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, Type.INT_TYPE.getDescriptor());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final List<List<SpilledVariableAndField>> spillVariables(List<SuspensionPoint> suspensionPoints, MethodNode methodNode) throws AnalyzerException {
        if (suspensionPoints.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        Frame<BasicValue>[] frameArrPerformSpilledVariableFieldTypesAnalysis = SpilledVariableFieldTypesAnalysisKt.performSpilledVariableFieldTypesAnalysis(methodNode, this.containingClassInternalName);
        List<Integer> listCollectSuspendLambdaParameterSlots = this.config.getNullOutSpilledCoroutineLocalsUsingStdlibFunction() ? CoroutineTransformerMethodVisitorKt.collectSuspendLambdaParameterSlots(methodNode) : CollectionsKt.emptyList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (Map.Entry<Type, Integer> entry : this.initialVarsCountByType.entrySet()) {
            Type key = entry.getKey();
            int iIntValue = entry.getValue().intValue();
            if (Intrinsics.areEqual(key, AsmTypes.OBJECT_TYPE)) {
                i = iIntValue;
            }
            linkedHashMap.put(key, Integer.valueOf(iIntValue));
        }
        List<VariableLivenessFrame> listAnalyzeLiveness = VariableLivenessKt.analyzeLiveness(methodNode);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (SuspensionPoint suspensionPoint : suspensionPoints) {
            int iIndexOf = methodNode.instructions.indexOf(suspensionPoint.getSuspensionCallBegin());
            Frame<BasicValue> frame = frameArrPerformSpilledVariableFieldTypesAnalysis[methodNode.instructions.indexOf(suspensionPoint.getSuspensionCallEnd().getNext())];
            if (frame == null || frame.getStackSize() != 1) {
                w01.a("Stack should be spilled before suspension call");
                return null;
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            List<SpillableVariable> listCalculateVariablesToSpill = calculateVariablesToSpill(methodNode, frameArrPerformSpilledVariableFieldTypesAnalysis, listAnalyzeLiveness, iIndexOf, linkedHashMap2);
            ArrayList arrayList4 = new ArrayList();
            Frame<BasicValue>[] frameArr = frameArrPerformSpilledVariableFieldTypesAnalysis;
            ArrayList arrayList5 = new ArrayList();
            Iterator it = listCalculateVariablesToSpill.iterator();
            while (it.hasNext()) {
                List<VariableLivenessFrame> list = listAnalyzeLiveness;
                Object next = it.next();
                LinkedHashMap linkedHashMap3 = linkedHashMap2;
                Iterator it2 = it;
                if (Intrinsics.areEqual(((SpillableVariable) next).getNormalizedType(), AsmTypes.OBJECT_TYPE)) {
                    arrayList4.add(next);
                } else {
                    arrayList5.add(next);
                }
                listAnalyzeLiveness = list;
                it = it2;
                linkedHashMap2 = linkedHashMap3;
            }
            List<VariableLivenessFrame> list2 = listAnalyzeLiveness;
            Pair pair = new Pair(arrayList4, arrayList5);
            List list3 = (List) pair.component1();
            List<SpillableVariable> list4 = (List) pair.component2();
            arrayList.add(list3);
            arrayList2.add(list4);
            arrayList3.add(listCalculateVariablesToSpill);
            for (Map.Entry<Type, Integer> entry2 : linkedHashMap2.entrySet()) {
                Type key2 = entry2.getKey();
                int iIntValue2 = entry2.getValue().intValue();
                Integer num = (Integer) linkedHashMap.get(key2);
                linkedHashMap.put(key2, Integer.valueOf(Math.max(num != null ? num.intValue() : 0, iIntValue2)));
            }
            frameArrPerformSpilledVariableFieldTypesAnalysis = frameArr;
            listAnalyzeLiveness = list2;
        }
        List<Pair<Integer, Integer>> listCalculateVariablesToCleanup = calculateVariablesToCleanup(methodNode, suspensionPoints, arrayList, i);
        List<List<SpilledVariableAndField>> listMapFieldNameToVariable = mapFieldNameToVariable(methodNode, suspensionPoints, arrayList, arrayList2);
        List<SpillableVariable>[] listArrCalculateVariablesToReinitializeBySuspensionPoint = ResumePointDependentAnalysisKt.calculateVariablesToReinitializeBySuspensionPoint(suspensionPoints, methodNode, this.containingClassInternalName, arrayList3);
        int size = suspensionPoints.size();
        for (int i2 = 0; i2 < size; i2++) {
            SuspensionPoint suspensionPoint2 = suspensionPoints.get(i2);
            Iterator it3 = ((List) arrayList.get(i2)).iterator();
            while (it3.hasNext()) {
                generateSpillAndUnspill(methodNode, suspensionPoint2, (SpillableVariable) it3.next(), listCollectSuspendLambdaParameterSlots);
            }
            Pair<Integer, Integer> pair2 = listCalculateVariablesToCleanup.get(i2);
            int iIntValue3 = ((Number) pair2.component1()).intValue();
            int iIntValue4 = ((Number) pair2.component2()).intValue();
            if (iIntValue4 > iIntValue3) {
                while (iIntValue3 < iIntValue4) {
                    cleanUpField(methodNode, suspensionPoint2, iIntValue3);
                    iIntValue3++;
                }
            }
            Iterator<SpillableVariable> it4 = arrayList2.get(i2).iterator();
            while (it4.hasNext()) {
                generateSpillAndUnspill(methodNode, suspensionPoint2, it4.next(), listCollectSuspendLambdaParameterSlots);
            }
            Iterator<SpillableVariable> it5 = listArrCalculateVariablesToReinitializeBySuspensionPoint[i2].iterator();
            while (it5.hasNext()) {
                generateFakeUnspill(methodNode, suspensionPoint2, it5.next());
            }
        }
        for (Map.Entry entry3 : linkedHashMap.entrySet()) {
            Type type = (Type) entry3.getKey();
            int iIntValue5 = ((Number) entry3.getValue()).intValue();
            Integer num2 = this.initialVarsCountByType.get(type);
            int iIntValue6 = num2 != null ? num2.intValue() + 1 : 0;
            if (iIntValue6 <= iIntValue5) {
                while (true) {
                    getClassBuilderForCoroutineState().newField(JvmDeclarationOrigin.NO_ORIGIN, 0, CoroutineTransformerMethodVisitorKt.fieldNameForVar(type, iIntValue6), type.getDescriptor(), null, null);
                    if (iIntValue6 != iIntValue5) {
                        iIntValue6++;
                    }
                }
            }
        }
        return listMapFieldNameToVariable;
    }

    private final void splitLvtRecord(MethodNode methodNode, SuspensionPoint suspension, LocalVariableNode local, LabelNode localRestart) {
        if (local != null) {
            LabelNode labelNode = local.end;
            local.end = suspension.getStateLabel();
            methodNode.localVariables.add(new LocalVariableNode(local.name, local.desc, local.signature, localRestart, labelNode, local.index));
        }
    }

    private final void splitTryCatchBlocksContainingSuspensionPoint(MethodNode methodNode, SuspensionPoint suspensionPoint) {
        List listListOf;
        InsnList insnList = methodNode.instructions;
        int iIndexOf = insnList.indexOf(suspensionPoint.getSuspensionCallBegin());
        int iIndexOf2 = insnList.indexOf(suspensionPoint.getSuspensionCallEnd());
        LabelNode labelNode = new LabelNode();
        LabelNode labelNode2 = new LabelNode();
        insnList.insert(suspensionPoint.getSuspensionCallEnd(), labelNode);
        insnList.insert(labelNode, new InsnNode(0));
        insnList.insert(labelNode.getNext(), labelNode2);
        List<TryCatchBlockNode> list = methodNode.tryCatchBlocks;
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (TryCatchBlockNode tryCatchBlockNode : list) {
            if (insnList.indexOf(tryCatchBlockNode.start) >= iIndexOf || iIndexOf >= insnList.indexOf(tryCatchBlockNode.end)) {
                listListOf = CollectionsKt.listOf(tryCatchBlockNode);
            } else {
                if (insnList.indexOf(tryCatchBlockNode.start) < iIndexOf2) {
                    insnList.indexOf(tryCatchBlockNode.end);
                }
                listListOf = CollectionsKt.listOf(new TryCatchBlockNode[]{new TryCatchBlockNode(tryCatchBlockNode.start, labelNode, tryCatchBlockNode.handler, tryCatchBlockNode.type), new TryCatchBlockNode(labelNode2, tryCatchBlockNode.end, tryCatchBlockNode.handler, tryCatchBlockNode.type)});
            }
            CollectionsKt.addAll(arrayList, listListOf);
        }
        methodNode.tryCatchBlocks = arrayList;
        suspensionPoint.setTryCatchBlocksContinuationLabel(labelNode2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final LabelNode transformCallAndReturnStateLabel(int id, SuspensionPoint suspension, MethodNode methodNode, int suspendMarkerVarIndex, LineNumberNode suspendPointLineNumber) throws UninitializedPropertyAccessException {
        int opcode;
        LabelNode labelNode = new LabelNode();
        AbstractInsnNode abstractInsnNodeNextDefinitelyHitLineNumber = nextDefinitelyHitLineNumber(suspension);
        InsnList insnList = methodNode.instructions;
        AbstractInsnNode suspensionCallBegin = suspension.getSuspensionCallBegin();
        MethodNode methodNode2 = new MethodNode();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
        instructionAdapter.visitVarInsn(25, this.continuationIndex);
        instructionAdapter.iconst(id);
        setLabel(instructionAdapter);
        Unit unit = Unit.INSTANCE;
        InsnList insnList2 = methodNode2.instructions;
        insnList2.getClass();
        insnList.insertBefore(suspensionCallBegin, insnList2);
        LabelNode tryCatchBlockEndLabelAfterSuspensionCall = getTryCatchBlockEndLabelAfterSuspensionCall(suspension);
        MethodNode methodNode3 = new MethodNode();
        InstructionAdapter instructionAdapter2 = new InstructionAdapter(methodNode3);
        GeneratedCodeMarkers.Companion companion = GeneratedCodeMarkers.INSTANCE;
        GeneratedCodeMarkers generatedCodeMarkers = this.generatedCodeMarkers;
        companion.markFakeLineNumber(instructionAdapter2, generatedCodeMarkers != null ? Integer.valueOf(generatedCodeMarkers.getCheckCOROUTINE_SUSPENDED()) : null);
        instructionAdapter2.dup();
        Type type = AsmTypes.OBJECT_TYPE;
        instructionAdapter2.load(suspendMarkerVarIndex, type);
        instructionAdapter2.ifacmpne(labelNode.getLabel());
        LabelNode labelNode2 = new LabelNode();
        instructionAdapter2.visitLabel(labelNode2.getLabel());
        instructionAdapter2.visitLineNumber(this.lineNumber, labelNode2.getLabel());
        instructionAdapter2.load(suspendMarkerVarIndex, type);
        instructionAdapter2.areturn(type);
        instructionAdapter2.visitLabel(suspension.getStateLabel().getLabel());
        InsnList insnList3 = methodNode3.instructions;
        insnList3.getClass();
        insnList.insert(tryCatchBlockEndLabelAfterSuspensionCall, insnList3);
        LabelNode tryCatchBlocksContinuationLabel = suspension.getTryCatchBlocksContinuationLabel();
        tryCatchBlocksContinuationLabel.getPrevious().getOpcode();
        insnList.remove(tryCatchBlocksContinuationLabel.getPrevious());
        MethodNode methodNode4 = new MethodNode();
        InstructionAdapter instructionAdapter3 = new InstructionAdapter(methodNode4);
        instructionAdapter3.nop();
        generateResumeWithExceptionCheck(instructionAdapter3, this.dataIndex);
        instructionAdapter3.load(this.dataIndex, type);
        Iterator<AbstractInsnNode> it = suspension.getUnboxInlineClassInstructions().iterator();
        while (it.hasNext()) {
            it.next().accept(instructionAdapter3);
        }
        instructionAdapter3.visitLabel(labelNode.getLabel());
        if (!this.config.getEnhancedCoroutinesDebugging()) {
            if (abstractInsnNodeNextDefinitelyHitLineNumber != null) {
                AbstractInsnNode next = tryCatchBlocksContinuationLabel.getNext();
                if (next == null || (opcode = next.getOpcode()) == 58 || opcode == 192 || opcode == 184 || opcode == 182 || opcode == 185) {
                    abstractInsnNodeNextDefinitelyHitLineNumber = null;
                } else {
                    instructionAdapter3.visitLineNumber(((LineNumberNode) abstractInsnNodeNextDefinitelyHitLineNumber).line, labelNode.getLabel());
                }
            } else if (suspendPointLineNumber != null) {
                instructionAdapter3.visitLineNumber(suspendPointLineNumber.line, labelNode.getLabel());
            }
        }
        Unit unit2 = Unit.INSTANCE;
        InsnList insnList4 = methodNode4.instructions;
        insnList4.getClass();
        insnList.insert(tryCatchBlocksContinuationLabel, insnList4);
        if (!this.config.getEnhancedCoroutinesDebugging() && abstractInsnNodeNextDefinitelyHitLineNumber != null) {
            insnList.remove(abstractInsnNodeNextDefinitelyHitLineNumber);
        }
        return suspension.getStateLabel();
    }

    private final void writeDebugMetadata(MethodNode methodNode, List<? extends LineNumberNode> suspensionPointLineNumbers, List<? extends LineNumberNode> suspensionPointNextLineNumbers, List<? extends List<SpilledVariableAndField>> spilledToLocalMapping) {
        List<? extends LineNumberNode> list = suspensionPointLineNumbers;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (true) {
            int i = -1;
            if (!it.hasNext()) {
                break;
            }
            LineNumberNode lineNumberNode = (LineNumberNode) it.next();
            if (lineNumberNode != null) {
                i = lineNumberNode.line;
            }
            arrayList.add(Integer.valueOf(i));
        }
        AnnotationVisitor annotationVisitorNewAnnotation = getClassBuilderForCoroutineState().newAnnotation(CoroutineCodegenUtilKt.getDEBUG_METADATA_ANNOTATION_ASM_TYPE().getDescriptor(), true);
        annotationVisitorNewAnnotation.getClass();
        annotationVisitorNewAnnotation.visit("f", this.sourceFile);
        annotationVisitorNewAnnotation.visit("l", CollectionsKt.toIntArray(arrayList));
        if (this.config.getGenerateDebugMetadataV2()) {
            List<? extends LineNumberNode> list2 = suspensionPointNextLineNumbers;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (LineNumberNode lineNumberNode2 : list2) {
                arrayList2.add(Integer.valueOf(lineNumberNode2 != null ? lineNumberNode2.line : -1));
            }
            annotationVisitorNewAnnotation.visit("nl", CollectionsKt.toIntArray(arrayList2));
        }
        List<? extends List<SpilledVariableAndField>> list3 = spilledToLocalMapping;
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(list3);
        ArrayList arrayList3 = new ArrayList();
        for (IndexedValue indexedValue : iterableWithIndex) {
            int index = indexedValue.getIndex();
            List<SpilledVariableAndField> list4 = (List) indexedValue.component2();
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            for (SpilledVariableAndField spilledVariableAndField : list4) {
                arrayList4.add(Integer.valueOf(index));
            }
            CollectionsKt.addAll(arrayList3, arrayList4);
        }
        List listFlatten = CollectionsKt.flatten(list3);
        annotationVisitorNewAnnotation.visit("i", CollectionsKt.toIntArray(arrayList3));
        AnnotationVisitor annotationVisitorVisitArray = annotationVisitorNewAnnotation.visitArray("s");
        List list5 = listFlatten;
        Iterator it2 = list5.iterator();
        while (it2.hasNext()) {
            annotationVisitorVisitArray.visit((String) null, ((SpilledVariableAndField) it2.next()).getFieldName());
        }
        annotationVisitorVisitArray.visitEnd();
        AnnotationVisitor annotationVisitorVisitArray2 = annotationVisitorNewAnnotation.visitArray("n");
        Iterator it3 = list5.iterator();
        while (it3.hasNext()) {
            annotationVisitorVisitArray2.visit((String) null, ((SpilledVariableAndField) it3.next()).getVariableName());
        }
        annotationVisitorVisitArray2.visitEnd();
        annotationVisitorNewAnnotation.visit("m", methodNode.name);
        annotationVisitorNewAnnotation.visit("c", Type.getObjectType(this.containingClassInternalName).getClassName());
        annotationVisitorNewAnnotation.visit("v", Integer.valueOf(this.config.getGenerateDebugMetadataV2() ? 2 : 1));
        annotationVisitorNewAnnotation.visitEnd();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.TransformationMethodVisitor
    public void performTransformations(MethodNode methodNode) throws AnalyzerException {
        int lastParameterIndex;
        AbstractInsnNode next;
        methodNode.getClass();
        if (this.config.getEnhancedCoroutinesDebugging()) {
            this.generatedCodeMarkers = GeneratedCodeMarkers.INSTANCE.fillOutMarkersAndCleanUpMethodNode(methodNode);
        }
        removeFakeContinuationConstructorCall(methodNode);
        replaceReturnsUnitMarkersWithPushingUnitOnStack(methodNode);
        if (this.isForNamedFunction) {
            String str = methodNode.desc;
            str.getClass();
            lastParameterIndex = CoroutineTransformerMethodVisitorKt.getLastParameterIndex(str, methodNode.access);
        } else {
            lastParameterIndex = 0;
        }
        CoroutineTransformerMethodVisitorKt.replaceFakeContinuationsWithRealOnes(methodNode, lastParameterIndex);
        new InplaceArgumentsMethodTransformer().transform(this.containingClassInternalName, methodNode);
        new FixStackMethodTransformer().transform(this.containingClassInternalName, methodNode);
        List<SuspensionPoint> listCollectSuspensionPoints = collectSuspensionPoints(methodNode);
        new RedundantLocalsEliminationMethodTransformer(listCollectSuspensionPoints).transform(this.containingClassInternalName, methodNode);
        ChangeBoxingMethodTransformer.INSTANCE.transform(this.containingClassInternalName, methodNode);
        UtilKt.updateMaxStack(methodNode);
        checkForSuspensionPointInsideMonitor(methodNode, listCollectSuspensionPoints);
        addLineNumberForSuspensionPointsAtTheSameLine(methodNode, listCollectSuspensionPoints);
        AbstractInsnNode first = methodNode.instructions.getFirst();
        if (this.isForNamedFunction) {
            if (TailCallOptimizationKt.allSuspensionPointsAreTailCalls(methodNode, listCollectSuspensionPoints)) {
                TailCallOptimizationKt.addCoroutineSuspendedChecks(methodNode, listCollectSuspensionPoints);
                insertAsyncStackTraceEntriesForTailCallFunction(methodNode, listCollectSuspensionPoints);
                dropSuspensionMarkers(methodNode);
                dropUnboxInlineClassMarkers(methodNode, listCollectSuspensionPoints);
                return;
            }
            int i = methodNode.maxLocals;
            this.dataIndex = i;
            methodNode.maxLocals = i + 2;
            this.continuationIndex = i + 1;
            prepareMethodNodePreludeForNamedFunction(methodNode);
        } else if (this.config.getNullOutSpilledCoroutineLocalsUsingStdlibFunction()) {
            InsnList insnList = methodNode.instructions;
            insnList.getClass();
            Object obj = null;
            for (Object obj2 : insnList) {
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode) obj2;
                abstractInsnNode.getClass();
                if (InlineCodegenUtilsKt.isSuspendLambdaParameterMarker(abstractInsnNode)) {
                    obj = obj2;
                }
            }
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) obj;
            if (abstractInsnNode2 != null && (next = abstractInsnNode2.getNext()) != null) {
                first = next;
            }
        }
        AbstractInsnNode abstractInsnNode3 = first;
        if (!this.isForNamedFunction) {
            markFakeLineNumberForLambdaArgumentUnspilling(methodNode);
        }
        Iterator<SuspensionPoint> it = listCollectSuspensionPoints.iterator();
        while (it.hasNext()) {
            splitTryCatchBlocksContainingSuspensionPoint(methodNode, it.next());
        }
        UtilKt.updateMaxStack(methodNode);
        new UninitializedStoresProcessor(methodNode).run();
        List<List<SpilledVariableAndField>> listSpillVariables = spillVariables(listCollectSuspensionPoints, methodNode);
        int i2 = methodNode.maxLocals;
        methodNode.maxLocals = i2 + 1;
        List<SuspensionPoint> list = listCollectSuspensionPoints;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(findSuspensionPointLineNumber((SuspensionPoint) it2.next()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it3 = list.iterator();
        while (it3.hasNext()) {
            arrayList2.add(findSuspensionPointNextLineNumber((SuspensionPoint) it3.next()));
        }
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(list);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10));
        for (IndexedValue indexedValue : iterableWithIndex) {
            int i3 = i2;
            i2 = i3;
            arrayList3.add(transformCallAndReturnStateLabel(indexedValue.getIndex() + 1, (SuspensionPoint) indexedValue.getValue(), methodNode, i3, arrayList.get(indexedValue.getIndex())));
        }
        generateStateMachinesTableswitch(methodNode, abstractInsnNode3, i2, listCollectSuspensionPoints, arrayList3);
        initializeFakeInlinerVariables(methodNode, arrayList3);
        dropSuspensionMarkers(methodNode);
        dropUnboxInlineClassMarkers(methodNode, listCollectSuspensionPoints);
        UtilKt.removeEmptyCatchBlocks(methodNode);
        if (this.config.getNullOutSpilledCoroutineLocalsUsingStdlibFunction()) {
            CoroutineTransformerMethodVisitorKt.extendParameterRanges(methodNode);
            CoroutineTransformerMethodVisitorKt.extendSuspendLambdaParameterRanges(methodNode);
        }
        dropSuspendLambdaParameterMarkers(methodNode);
        if (!this.config.getNullOutSpilledCoroutineLocalsUsingStdlibFunction() && !this.config.getEnableDebugMode()) {
            CoroutineTransformerMethodVisitorKt.updateLvtAccordingToLiveness(methodNode, this.isForNamedFunction, arrayList3);
        }
        GeneratedCodeMarkers generatedCodeMarkers = this.generatedCodeMarkers;
        if (generatedCodeMarkers != null) {
            generatedCodeMarkers.addFakeVariablesToLVTAndInitializeThem(methodNode, this.isForNamedFunction);
        }
        writeDebugMetadata(methodNode, arrayList, arrayList2, listSpillVariables);
    }

    public /* synthetic */ CoroutineTransformerMethodVisitor(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr, String str4, Function0 function0, boolean z, Function1 function1, int i2, String str5, JvmBackendConfig jvmBackendConfig, boolean z2, String str6, Map map, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(methodVisitor, i, str, str2, str3, strArr, str4, function0, z, function1, i2, str5, jvmBackendConfig, (i3 & 8192) != 0 ? false : z2, (i3 & 16384) != 0 ? null : str6, (i3 & 32768) != 0 ? MapsKt.emptyMap() : map);
    }
}
