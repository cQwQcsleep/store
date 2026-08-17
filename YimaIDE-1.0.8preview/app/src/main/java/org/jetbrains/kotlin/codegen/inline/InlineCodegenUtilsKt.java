package org.jetbrains.kotlin.codegen.inline;

import com.intellij.openapi.vfs.VirtualFile;
import defpackage.yp6;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.backend.common.output.OutputFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AssertCodegenUtilKt;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinder;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.commons.Method;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FrameNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.IntInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.util.Printer;
import org.jetbrains.org.objectweb.asm.util.Textifier;
import org.jetbrains.org.objectweb.asm.util.TraceMethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\t\u001a4\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0014\b\u0004\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00010)H\u0080\bø\u0001\u0000\u001a\"\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010+\u001a\u00020*H\u0000\u001a\u0016\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u0001\u001a\u001a\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0000\u001a\u001a\u00105\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u0002022\u0006\u00106\u001a\u00020\u0003H\u0000\u001a%\u00107\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u0002042\u0006\u00101\u001a\u0002022\u0006\u0010+\u001a\u00020*¢\u0006\u0002\u00108\u001a\u0018\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00032\u0006\u0010;\u001a\u00020\u0003H\u0000\u001a\f\u0010<\u001a\u00020\u0001*\u00020\u0003H\u0000\u001a\u0018\u0010=\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u0003H\u0000\u001a\u0010\u0010@\u001a\u00020\u00012\u0006\u0010?\u001a\u00020\u0003H\u0002\u001a\u0018\u0010A\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u00032\u0006\u0010B\u001a\u00020\u0003H\u0000\u001a\u0018\u0010C\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u00032\u0006\u0010B\u001a\u00020\u0003H\u0000\u001a\u0010\u0010D\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u0003H\u0002\u001a\u0010\u0010E\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u0003H\u0000\u001a\u0018\u0010F\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u0003H\u0000\u001a\u0010\u0010G\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u0003H\u0000\u001a\u000e\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K\u001a \u0010L\u001a\u00020K2\u0012\u0010M\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020O0)H\u0086\bø\u0001\u0000\u001a\u0016\u0010P\u001a\u00020\u0001*\u00020\u00032\b\b\u0002\u0010Q\u001a\u00020\u0015H\u0002\u001a\u0010\u0010R\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u0003H\u0000\u001a\u0010\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u0015H\u0000\u001a\u0010\u0010U\u001a\u00020\u00012\u0006\u0010V\u001a\u00020WH\u0000\u001a\u0012\u0010X\u001a\u0004\u0018\u00010\u00032\u0006\u0010Y\u001a\u00020WH\u0000\u001a\u0016\u0010Z\u001a\u00020O2\u0006\u0010[\u001a\u00020N2\u0006\u0010\\\u001a\u00020\u0003\u001a\u0010\u0010]\u001a\u00020'2\u0006\u0010T\u001a\u00020\u0015H\u0000\u001a\u001e\u0010^\u001a\u00020O2\u0006\u0010_\u001a\u00020K2\u0006\u0010`\u001a\u00020K2\u0006\u0010a\u001a\u00020W\u001a\u0017\u0010b\u001a\u0004\u0018\u00010\u00152\b\u0010c\u001a\u0004\u0018\u00010W¢\u0006\u0002\u0010d\u001a\u001a\u0010e\u001a\u0004\u0018\u00010W2\u0006\u0010f\u001a\u00020W2\u0006\u0010g\u001a\u00020WH\u0002\u001a\u001d\u0010h\u001a\u0004\u0018\u00010\u00152\u0006\u0010f\u001a\u00020W2\u0006\u0010g\u001a\u00020W¢\u0006\u0002\u0010i\u001a\u0006\u0010j\u001a\u00020K\u001a\u0010\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020lH\u0000\u001a\u0018\u0010n\u001a\u00020\u00012\u0006\u0010o\u001a\u00020l2\u0006\u0010p\u001a\u00020lH\u0000\u001a\u0014\u0010t\u001a\u00020\u0003*\u0004\u0018\u00010W2\u0006\u0010w\u001a\u00020x\u001a\n\u0010y\u001a\u00020\u0003*\u00020K\u001a\u0014\u0010|\u001a\u00020\u0003*\u00020}2\u0006\u0010~\u001a\u00020xH\u0000\u001a\u0018\u0010\u007f\u001a\u00020%2\u0006\u00101\u001a\u0002022\u0006\u0010>\u001a\u00020\u0003H\u0000\u001a\"\u0010\u0080\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N2\u0007\u0010\u0082\u0001\u001a\u00020\u00152\u0007\u0010\u0083\u0001\u001a\u00020\u0001\u001a\u000f\u0010\u0084\u0001\u001a\u00020\u00012\u0006\u0010m\u001a\u00020W\u001a\u000f\u0010\u0085\u0001\u001a\u00020\u00012\u0006\u0010m\u001a\u00020W\u001a\u0011\u0010\u0086\u0001\u001a\u00020\u00012\b\u0010m\u001a\u0004\u0018\u00010W\u001a\u0019\u0010\u0086\u0001\u001a\u00020\u00012\u0006\u0010m\u001a\u00020W2\u0006\u0010;\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0087\u0001\u001a\u00020\u00152\u0007\u0010\u0088\u0001\u001a\u00020W\u001a\u0010\u0010\u0089\u0001\u001a\u00020O2\u0007\u0010\u008a\u0001\u001a\u00020K\u001a\u0019\u0010\u008b\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N2\u0007\u0010\u008c\u0001\u001a\u00020\u0001\u001a$\u0010\u008d\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001\u001a\u0012\u0010\u0092\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020NH\u0002\u001a\u0012\u0010\u0093\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020NH\u0002\u001a\u0010\u0010\u0094\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N\u001a\u000f\u0010\u0095\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020W\u001a$\u0010\u0096\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N2\u0007\u0010\u008c\u0001\u001a\u00020\u00012\t\b\u0002\u0010\u0097\u0001\u001a\u00020\u0001\u001a\u0019\u0010\u0098\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N2\u0007\u0010\u008c\u0001\u001a\u00020\u0001\u001a\u0010\u0010\u0099\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N\u001a\u0010\u0010\u009a\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N\u001a\u0010\u0010\u009b\u0001\u001a\u00020O2\u0007\u0010\u0081\u0001\u001a\u00020N\u001a\u0016\u0010\u009c\u0001\u001a\u00020O*\u00020N2\u0007\u0010\u009d\u0001\u001a\u00020\u0015H\u0002\u001a\u000f\u0010\u009e\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020W\u001a\u0011\u0010\u009f\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u000f\u0010 \u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020W\u001a\u0011\u0010¡\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010¢\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010£\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u000f\u0010¤\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020W\u001a\u0011\u0010¥\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010¦\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010§\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010¨\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010©\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010ª\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u001a\u0010«\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020W2\u0007\u0010\u009d\u0001\u001a\u00020\u0015H\u0002\u001a\u0011\u0010¬\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u001b\u0010¬\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020W2\b\u0010;\u001a\u0004\u0018\u00010\u0003H\u0000\u001a\u0011\u0010\u00ad\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010®\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020WH\u0000\u001a\u0011\u0010¯\u0001\u001a\u00020\u00152\u0006\u0010T\u001a\u00020\u0015H\u0000\u001a\u0011\u0010°\u0001\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u0015H\u0000\u001a\u001b\u0010±\u0001\u001a\u00020\u00152\b\u0010²\u0001\u001a\u00030³\u00012\u0006\u0010m\u001a\u00020KH\u0000\u001a\u0011\u0010´\u0001\u001a\u00020\u00152\u0006\u0010m\u001a\u00020KH\u0002\u001a\u0011\u0010µ\u0001\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u0003H\u0000\u001a\u001f\u0010¶\u0001\u001a\u00020O*\u00020K2\u0007\u0010·\u0001\u001a\u00020\u00012\t\b\u0002\u0010¸\u0001\u001a\u00020\u0001\u001a\u000f\u0010¹\u0001\u001a\u00020K2\u0006\u0010J\u001a\u00020K\u001a\u000f\u0010º\u0001\u001a\u00020\u00012\u0006\u0010c\u001a\u00020W\u001a\u0011\u0010»\u0001\u001a\u0004\u0018\u00010W2\u0006\u0010c\u001a\u00020W\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u0017\u0010q\u001a\u00020\u0003*\u0004\u0018\u00010K8F¢\u0006\u0006\u001a\u0004\br\u0010s\"\u001a\u0010t\u001a\u00020\u0003*\u0004\u0018\u00010W8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bu\u0010v\"\u001a\u0010z\u001a\u00020\u0003*\u0004\u0018\u00010W8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b{\u0010v\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¼\u0001"}, d2 = {"GENERATE_SMAP", Argument.Delimiters.none, "NUMBERED_FUNCTION_PREFIX", Argument.Delimiters.none, "INLINE_FUN_VAR_SUFFIX", "FIRST_FUN_LABEL", "SPECIAL_TRANSFORMATION_NAME", "INLINE_TRANSFORMATION_SUFFIX", "INLINE_CALL_TRANSFORMATION_SUFFIX", "INLINE_FUN_THIS_0_SUFFIX", "DEFAULT_LAMBDA_FAKE_CALL", "CAPTURED_FIELD_FOLD_PREFIX", "NON_LOCAL_RETURN", "CAPTURED_FIELD_PREFIX", "NON_CAPTURED_FIELD_PREFIX", "INLINE_MARKER_CLASS_NAME", "INLINE_MARKER_BEFORE_METHOD_NAME", "INLINE_MARKER_AFTER_METHOD_NAME", "INLINE_MARKER_FINALLY_START", "INLINE_MARKER_FINALLY_END", "INLINE_MARKER_BEFORE_SUSPEND_ID", Argument.Delimiters.none, "INLINE_MARKER_AFTER_SUSPEND_ID", "INLINE_MARKER_RETURNS_UNIT", "INLINE_MARKER_FAKE_CONTINUATION", "INLINE_MARKER_BEFORE_FAKE_CONTINUATION_CONSTRUCTOR_CALL", "INLINE_MARKER_AFTER_FAKE_CONTINUATION_CONSTRUCTOR_CALL", "INLINE_MARKER_BEFORE_INLINE_SUSPEND_ID", "INLINE_MARKER_AFTER_INLINE_SUSPEND_ID", "INLINE_MARKER_BEFORE_UNBOX_INLINE_CLASS", "INLINE_MARKER_AFTER_UNBOX_INLINE_CLASS", "INLINE_MARKER_SUSPEND_LAMBDA_PARAMETER", "INLINE_MARKER_BEFORE_SUSPEND_UNIT_CALL", "INLINE_MARKER_BEFORE_SUSPEND_GENERIC_CALL", "getMethodNode", "Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", "classData", Argument.Delimiters.none, "classType", "Lorg/jetbrains/org/objectweb/asm/Type;", "match", "Lkotlin/Function1;", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "method", "argumentsSize", "descriptor", "isStatic", "findVirtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findVirtualFileImprecise", "internalClassName", "classFileContainsMethod", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/codegen/state/GenerationState;Lorg/jetbrains/org/objectweb/asm/commons/Method;)Ljava/lang/Boolean;", "isInvokeOnLambda", "owner", ModuleXmlParser.NAME, "isNumberedFunctionInternalName", "isAnonymousConstructorCall", "internalName", "methodName", "isConstructor", "isWhenMappingAccess", "fieldName", "isAnonymousSingletonLoad", "isOldSamWrapper", "isSamWrapper", "isSamWrapperConstructorCall", "isAnonymousClass", "wrapWithMaxLocalCalc", "Lorg/jetbrains/kotlin/codegen/inline/MaxStackFrameSizeAndLocalsCalculator;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "newMethodNodeWithCorrectStackSize", "block", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", Argument.Delimiters.none, "isInteger", "radix", "isCapturedFieldName", "isReturnOpcode", "opcode", "isMarkedReturn", "returnIns", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getMarkedReturnLabelOrNull", "returnInsn", "generateGlobalReturnFlag", "iv", "labelName", "getReturnType", "insertNodeBefore", "from", "to", "beforeNode", "getLineNumberOrNull", "insn", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Ljava/lang/Integer;", "getFirstFinallyOperationInstructionOrNull", "startIns", "endInsExclusive", "getFirstFinallyOperationLineNumberOrNull", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Ljava/lang/Integer;", "createEmptyMethodNode", "firstLabelInChain", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "node", "areLabelsBeforeSameInsn", "first", "second", "nodeText", "getNodeText", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)Ljava/lang/String;", "insnText", "getInsnText", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Ljava/lang/String;", "insnList", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "dumpBody", "insnOpcodeText", "getInsnOpcodeText", "text", "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "insns", "loadClassBytesByInternalName", "generateFinallyMarker", "v", "depth", "start", "isFinallyEnd", "isFinallyStart", "isFinallyMarker", "getConstant", "ins", "removeFinallyMarkers", "intoNode", "addInlineMarker", "isStartNotEnd", "generateResumePathUnboxing", "inlineClass", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "addBeforeUnboxInlineClassMarker", "addAfterUnboxInlineClassMarker", "addSuspendLambdaParameterMarker", "isSuspendLambdaParameterMarker", "addSuspendMarker", "inlinable", "addFakeContinuationConstructorCallMarker", "addFakeContinuationMarker", "addBeforeSuspendUnitCallMarker", "addBeforeSuspendGenericCallMarker", "emitInlineMarker", "id", "isBeforeSuspendMarker", "isAfterSuspendMarker", "isBeforeInlineSuspendMarker", "isAfterInlineSuspendMarker", "isReturnsUnitMarker", "isBeforeSuspendUnitCallMarker", "isBeforeSuspendGenericCallMarker", "isFakeContinuationMarker", "isBeforeUnboxInlineClassMarker", "isAfterUnboxInlineClassMarker", "isBeforeFakeContinuationConstructorCallMarker", "isAfterFakeContinuationConstructorCallMarker", "isSuspendInlineMarker", "isSuspendMarker", "isInlineMarker", "isBeforeInlineMarker", "isAfterInlineMarker", "getLoadStoreArgSize", "isStoreInstruction", "calcMarkerShift", "parameters", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "getIndexAfterLastMarker", "isThis0", "preprocessSuspendMarkers", "forInline", "keepFakeContinuation", "cloneMethodNode", "isCatchStoreInstruction", "resolveCatchStoreInstruction", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineCodegenUtilsKt {
    public static final String CAPTURED_FIELD_FOLD_PREFIX = "$$$";
    public static final String CAPTURED_FIELD_PREFIX = "$";
    public static final String DEFAULT_LAMBDA_FAKE_CALL = "$$$DEFAULT_LAMBDA_FAKE_CALL$$$";
    public static final String FIRST_FUN_LABEL = "$$$$$ROOT$$$$$";
    public static final boolean GENERATE_SMAP = true;
    public static final String INLINE_CALL_TRANSFORMATION_SUFFIX = "$$inlined";
    public static final String INLINE_FUN_THIS_0_SUFFIX = "$inline_fun";
    public static final String INLINE_FUN_VAR_SUFFIX = "$iv";
    public static final String INLINE_MARKER_CLASS_NAME = "kotlin/jvm/internal/InlineMarker";
    public static final String INLINE_TRANSFORMATION_SUFFIX = "$inlined";
    public static final String NUMBERED_FUNCTION_PREFIX = "kotlin/jvm/functions/Function";
    public static final String SPECIAL_TRANSFORMATION_NAME = "$special";

    public static CharSequence a(InsnList insnList, Pair pair) {
        pair.getClass();
        Integer num = (Integer) pair.component1();
        LabelNode labelNode = (LabelNode) pair.component2();
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.append(':');
        labelNode.getClass();
        sb.append(insnText$labelText(labelNode, insnList));
        return sb.toString();
    }

    private static final void addAfterUnboxInlineClassMarker(InstructionAdapter instructionAdapter) {
        emitInlineMarker(instructionAdapter, 9);
    }

    public static final void addBeforeSuspendGenericCallMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        emitInlineMarker(instructionAdapter, 12);
    }

    public static final void addBeforeSuspendUnitCallMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        emitInlineMarker(instructionAdapter, 11);
    }

    private static final void addBeforeUnboxInlineClassMarker(InstructionAdapter instructionAdapter) {
        emitInlineMarker(instructionAdapter, 8);
    }

    public static final void addFakeContinuationConstructorCallMarker(InstructionAdapter instructionAdapter, boolean z) {
        instructionAdapter.getClass();
        emitInlineMarker(instructionAdapter, z ? 4 : 5);
    }

    public static final void addFakeContinuationMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        emitInlineMarker(instructionAdapter, 3);
        instructionAdapter.aconst((Object) null);
    }

    public static final void addInlineMarker(InstructionAdapter instructionAdapter, boolean z) {
        instructionAdapter.getClass();
        instructionAdapter.visitMethodInsn(184, INLINE_MARKER_CLASS_NAME, z ? "beforeInlineCall" : "afterInlineCall", "()V", false);
    }

    public static final void addSuspendLambdaParameterMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        emitInlineMarker(instructionAdapter, 10);
    }

    public static final void addSuspendMarker(InstructionAdapter instructionAdapter, boolean z, boolean z2) {
        int i;
        instructionAdapter.getClass();
        if (z2 && z) {
            i = 6;
        } else if (z2) {
            i = 7;
        } else {
            i = z ? 0 : 1;
        }
        emitInlineMarker(instructionAdapter, i);
    }

    public static /* synthetic */ void addSuspendMarker$default(InstructionAdapter instructionAdapter, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        addSuspendMarker(instructionAdapter, z, z2);
    }

    public static final boolean areLabelsBeforeSameInsn(LabelNode labelNode, LabelNode labelNode2) {
        labelNode.getClass();
        labelNode2.getClass();
        return Intrinsics.areEqual(firstLabelInChain(labelNode), firstLabelInChain(labelNode2));
    }

    public static final int argumentsSize(String str, boolean z) {
        str.getClass();
        return (Type.getArgumentsAndReturnSizes(str) >> 2) - (z ? 1 : 0);
    }

    public static CharSequence b(InsnList insnList, Pair pair) {
        pair.getClass();
        int iIntValue = ((Number) pair.component1()).intValue();
        LabelNode labelNode = (LabelNode) pair.component2();
        StringBuilder sb = new StringBuilder();
        sb.append(iIntValue);
        sb.append(':');
        labelNode.getClass();
        sb.append(insnText$labelText(labelNode, insnList));
        return sb.toString();
    }

    public static boolean c(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isBeforeInlineSuspendMarker(abstractInsnNode) || isAfterInlineSuspendMarker(abstractInsnNode);
    }

    public static final int calcMarkerShift(Parameters parameters, MethodNode methodNode) {
        parameters.getClass();
        methodNode.getClass();
        return (getIndexAfterLastMarker(methodNode) - parameters.getRealParametersSizeOnStack()) + parameters.getArgsSizeOnStack();
    }

    public static final Boolean classFileContainsMethod(ClassId classId, GenerationState generationState, final Method method) {
        byte[] bArrContentsToByteArray;
        classId.getClass();
        generationState.getClass();
        method.getClass();
        VirtualFile virtualFileFindVirtualFile = findVirtualFile(generationState, classId);
        if (virtualFileFindVirtualFile == null || (bArrContentsToByteArray = virtualFileFindVirtualFile.contentsToByteArray()) == null) {
            return null;
        }
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        new ClassReader(bArrContentsToByteArray).accept(new ClassVisitor() { // from class: org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt.classFileContainsMethod.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(589824);
            }

            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                if (Intrinsics.areEqual(name, method.getName()) && Intrinsics.areEqual(descriptor, method.getDescriptor())) {
                    booleanRef.element = true;
                }
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        }, 4);
        return Boolean.valueOf(booleanRef.element);
    }

    public static final MethodNode cloneMethodNode(MethodNode methodNode) {
        MethodNode methodNode2;
        methodNode.getClass();
        synchronized (methodNode) {
            methodNode.instructions.resetLabels();
            int i = methodNode.access;
            String str = methodNode.name;
            String str2 = methodNode.desc;
            String str3 = methodNode.signature;
            List list = methodNode.exceptions;
            list.getClass();
            methodNode2 = new MethodNode(589824, i, str, str2, str3, (String[]) list.toArray(new String[0]));
            methodNode.accept(methodNode2);
        }
        return methodNode2;
    }

    public static final MethodNode createEmptyMethodNode() {
        return new MethodNode(589824, 0, "fake", "()V", (String) null, (String[]) null);
    }

    public static final String dumpBody(MethodNode methodNode) {
        String str;
        methodNode.getClass();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println(methodNode.name + ' ' + methodNode.desc);
        for (TryCatchBlockNode tryCatchBlockNode : methodNode.tryCatchBlocks) {
            StringBuilder sb = new StringBuilder("  TRYCATCHBLOCK start:");
            LabelNode labelNode = tryCatchBlockNode.start;
            labelNode.getClass();
            sb.append(dumpBody$labelRef(labelNode, methodNode));
            sb.append(" end:");
            LabelNode labelNode2 = tryCatchBlockNode.end;
            labelNode2.getClass();
            sb.append(dumpBody$labelRef(labelNode2, methodNode));
            sb.append(" handler:");
            LabelNode labelNode3 = tryCatchBlockNode.handler;
            labelNode3.getClass();
            sb.append(dumpBody$labelRef(labelNode3, methodNode));
            printWriter.println(sb.toString());
        }
        LineNumberNode[] array = methodNode.instructions.toArray();
        array.getClass();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            LineNumberNode lineNumberNode = array[i];
            lineNumberNode.getClass();
            switch (UtilKt.getNodeType(lineNumberNode)) {
                case MavenComparableVersion.Item.INTEGER_ITEM /* 0 */:
                    printWriter.println(i + '\t' + Printer.OPCODES[lineNumberNode.getOpcode()]);
                    break;
                case 1:
                    printWriter.println(i + '\t' + Printer.OPCODES[lineNumberNode.getOpcode()] + ' ' + ((IntInsnNode) lineNumberNode).operand);
                    break;
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                    printWriter.println(i + '\t' + Printer.OPCODES[lineNumberNode.getOpcode()] + ' ' + ((VarInsnNode) lineNumberNode).var);
                    break;
                case 3:
                    printWriter.println(i + '\t' + Printer.OPCODES[lineNumberNode.getOpcode()] + ' ' + ((TypeInsnNode) lineNumberNode).desc);
                    break;
                case 4:
                    FieldInsnNode fieldInsnNode = (FieldInsnNode) lineNumberNode;
                    printWriter.println(i + '\t' + Printer.OPCODES[fieldInsnNode.getOpcode()] + ' ' + fieldInsnNode.owner + '#' + fieldInsnNode.name + ' ' + fieldInsnNode.desc);
                    break;
                case 5:
                    MethodInsnNode methodInsnNode = (MethodInsnNode) lineNumberNode;
                    printWriter.println(i + '\t' + Printer.OPCODES[methodInsnNode.getOpcode()] + ' ' + methodInsnNode.owner + '#' + methodInsnNode.name + ' ' + methodInsnNode.desc);
                    break;
                case 6:
                    InvokeDynamicInsnNode invokeDynamicInsnNode = (InvokeDynamicInsnNode) lineNumberNode;
                    printWriter.println(i + '\t' + Printer.OPCODES[invokeDynamicInsnNode.getOpcode()] + ' ' + invokeDynamicInsnNode.name + ' ' + invokeDynamicInsnNode.desc);
                    int tag = invokeDynamicInsnNode.bsm.getTag();
                    switch (tag) {
                        case 1:
                            str = "H_GETFIELD";
                            break;
                        case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                            str = "H_GETSTATIC";
                            break;
                        case 3:
                            str = "H_PUTFIELD";
                            break;
                        case 4:
                            str = "H_PUTSTATIC";
                            break;
                        case 5:
                            str = "H_INVOKEVIRTUAL";
                            break;
                        case 6:
                            str = "H_INVOKESTATIC";
                            break;
                        case 7:
                            str = "H_INVOKESPECIAL";
                            break;
                        case 8:
                            str = "H_NEWINVOKESPECIAL";
                            break;
                        case 9:
                            str = "H_INVOKEINTERFACE";
                            break;
                        default:
                            str = "<" + tag + '>';
                            break;
                    }
                    printWriter.println("\t" + str + ' ' + invokeDynamicInsnNode.bsm.getOwner() + '#' + invokeDynamicInsnNode.bsm.getName() + ' ' + invokeDynamicInsnNode.bsm.getDesc() + " [");
                    Object[] objArr = invokeDynamicInsnNode.bsmArgs;
                    objArr.getClass();
                    for (Object obj : objArr) {
                        printWriter.println("\t" + obj);
                    }
                    printWriter.println("\t]");
                    break;
                case 7:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i);
                    sb2.append('\t');
                    sb2.append(Printer.OPCODES[lineNumberNode.getOpcode()]);
                    sb2.append(' ');
                    LabelNode labelNode4 = ((JumpInsnNode) lineNumberNode).label;
                    labelNode4.getClass();
                    sb2.append(dumpBody$labelRef(labelNode4, methodNode));
                    printWriter.println(sb2.toString());
                    break;
                case 8:
                    printWriter.println(i + "\tL#" + i);
                    break;
                case 9:
                    printWriter.println(i + "\tLDC " + ((LdcInsnNode) lineNumberNode).cst);
                    break;
                case 10:
                    IincInsnNode iincInsnNode = (IincInsnNode) lineNumberNode;
                    printWriter.println(i + "\tIINC " + iincInsnNode.var + " incr:" + iincInsnNode.incr);
                    break;
                case 11:
                    TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode) lineNumberNode;
                    printWriter.println(i + "\tTABLESWITCH min:" + tableSwitchInsnNode.min + " max:" + tableSwitchInsnNode.max + '{');
                    int size = tableSwitchInsnNode.labels.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        StringBuilder sb3 = new StringBuilder("\t");
                        sb3.append(tableSwitchInsnNode.min + i2);
                        sb3.append(": ");
                        Object obj2 = tableSwitchInsnNode.labels.get(i2);
                        obj2.getClass();
                        sb3.append(dumpBody$labelRef((LabelNode) obj2, methodNode));
                        printWriter.println(sb3.toString());
                    }
                    StringBuilder sb4 = new StringBuilder("\tdefault: ");
                    LabelNode labelNode5 = tableSwitchInsnNode.dflt;
                    labelNode5.getClass();
                    sb4.append(dumpBody$labelRef(labelNode5, methodNode));
                    printWriter.println(sb4.toString());
                    printWriter.println("\t}");
                    break;
                case 12:
                    LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode) lineNumberNode;
                    printWriter.println(i + "\tLOOKUPSWITCH {");
                    int size2 = lookupSwitchInsnNode.labels.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        Integer num = (Integer) lookupSwitchInsnNode.keys.get(i3);
                        LabelNode labelNode6 = (LabelNode) lookupSwitchInsnNode.labels.get(i3);
                        StringBuilder sb5 = new StringBuilder("\t");
                        sb5.append(num);
                        sb5.append(": ");
                        labelNode6.getClass();
                        sb5.append(dumpBody$labelRef(labelNode6, methodNode));
                        printWriter.println(sb5.toString());
                    }
                    printWriter.println("\t}");
                    break;
                case 13:
                    MultiANewArrayInsnNode multiANewArrayInsnNode = (MultiANewArrayInsnNode) lineNumberNode;
                    printWriter.println(i + '\t' + Printer.OPCODES[multiANewArrayInsnNode.getOpcode()] + ' ' + multiANewArrayInsnNode.desc + " dims:" + multiANewArrayInsnNode.dims + ' ');
                    break;
                case 14:
                    printWriter.println(i + "\tFRAME {...}");
                    break;
                case 15:
                    LineNumberNode lineNumberNode2 = lineNumberNode;
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(i);
                    sb6.append("\tLINENUMBER ");
                    sb6.append(lineNumberNode2.line);
                    sb6.append(' ');
                    LabelNode labelNode7 = lineNumberNode2.start;
                    labelNode7.getClass();
                    sb6.append(dumpBody$labelRef(labelNode7, methodNode));
                    printWriter.println(sb6.toString());
                    break;
                default:
                    printWriter.println(i + "\t??? " + lineNumberNode);
                    break;
            }
        }
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            StringBuilder sb7 = new StringBuilder("  LOCALVARIABLE ");
            sb7.append(localVariableNode.index);
            sb7.append(' ');
            sb7.append(localVariableNode.name);
            sb7.append(' ');
            sb7.append(localVariableNode.desc);
            sb7.append(' ');
            LabelNode labelNode8 = localVariableNode.start;
            labelNode8.getClass();
            sb7.append(dumpBody$labelRef(labelNode8, methodNode));
            sb7.append(' ');
            LabelNode labelNode9 = localVariableNode.end;
            labelNode9.getClass();
            sb7.append(dumpBody$labelRef(labelNode9, methodNode));
            printWriter.println(sb7.toString());
            if (localVariableNode.signature != null) {
                printWriter.println("    // signature: " + localVariableNode.signature);
            }
        }
        printWriter.flush();
        String string = stringWriter.toString();
        string.getClass();
        return string;
    }

    private static final String dumpBody$labelRef(LabelNode labelNode, MethodNode methodNode) {
        return "L#" + methodNode.instructions.indexOf(labelNode);
    }

    private static final void emitInlineMarker(InstructionAdapter instructionAdapter, int i) {
        instructionAdapter.iconst(i);
        instructionAdapter.invokestatic(INLINE_MARKER_CLASS_NAME, "mark", "(I)V", false);
    }

    public static final VirtualFile findVirtualFile(GenerationState generationState, ClassId classId) {
        generationState.getClass();
        classId.getClass();
        return VirtualFileFinder.SERVICE.getInstance(generationState.getProject(), (ModuleInfo) generationState.getModule().getCapability(ModuleInfo.Companion.getCapability())).findVirtualFileWithHeader(classId);
    }

    public static final VirtualFile findVirtualFileImprecise(GenerationState generationState, String str) {
        generationState.getClass();
        str.getClass();
        FqName packageFqName = JvmClassName.byInternalName(str).getPackageFqName();
        packageFqName.getClass();
        Name nameIdentifier = Name.identifier(StringsKt.substringAfterLast(str, "/", str));
        nameIdentifier.getClass();
        return findVirtualFile(generationState, new ClassId(packageFqName, nameIdentifier));
    }

    public static final LabelNode firstLabelInChain(LabelNode labelNode) {
        labelNode.getClass();
        while (labelNode.getPrevious() instanceof LabelNode) {
            AbstractInsnNode previous = labelNode.getPrevious();
            previous.getClass();
            labelNode = (LabelNode) previous;
        }
        return labelNode;
    }

    public static final void generateFinallyMarker(InstructionAdapter instructionAdapter, int i, boolean z) {
        instructionAdapter.getClass();
        instructionAdapter.iconst(i);
        instructionAdapter.invokestatic(INLINE_MARKER_CLASS_NAME, z ? "finallyStart" : "finallyEnd", "(I)V", false);
    }

    public static final void generateGlobalReturnFlag(InstructionAdapter instructionAdapter, String str) {
        instructionAdapter.getClass();
        str.getClass();
        instructionAdapter.invokestatic("$$$$$NON_LOCAL_RETURN$$$$$", str, "()V", false);
    }

    public static final void generateResumePathUnboxing(InstructionAdapter instructionAdapter, KotlinTypeMarker kotlinTypeMarker, KotlinTypeMapperBase kotlinTypeMapperBase) {
        instructionAdapter.getClass();
        kotlinTypeMarker.getClass();
        kotlinTypeMapperBase.getClass();
        addBeforeUnboxInlineClassMarker(instructionAdapter);
        Type type = AsmTypes.OBJECT_TYPE;
        StackValue.unboxInlineClass(type, kotlinTypeMarker, instructionAdapter, kotlinTypeMapperBase);
        instructionAdapter.checkcast(type);
        addAfterUnboxInlineClassMarker(instructionAdapter);
    }

    public static final int getConstant(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int opcode = abstractInsnNode.getOpcode();
        if (3 <= opcode && opcode < 9) {
            return opcode - 3;
        }
        if (opcode == 16 || opcode == 17) {
            return ((IntInsnNode) abstractInsnNode).operand;
        }
        Object obj = ((LdcInsnNode) abstractInsnNode).cst;
        obj.getClass();
        return ((Integer) obj).intValue();
    }

    private static final AbstractInsnNode getFirstFinallyOperationInstructionOrNull(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        while (abstractInsnNode != null && !Intrinsics.areEqual(abstractInsnNode, abstractInsnNode2)) {
            if (!UtilKt.isMeaningful(abstractInsnNode)) {
                abstractInsnNode = abstractInsnNode.getNext();
            } else {
                if (!isFinallyMarker(abstractInsnNode.getNext())) {
                    return abstractInsnNode;
                }
                abstractInsnNode = abstractInsnNode.getNext().getNext();
            }
        }
        return null;
    }

    public static final Integer getFirstFinallyOperationLineNumberOrNull(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        abstractInsnNode.getClass();
        abstractInsnNode2.getClass();
        return getLineNumberOrNull(getFirstFinallyOperationInstructionOrNull(abstractInsnNode, abstractInsnNode2));
    }

    private static final int getIndexAfterLastMarker(MethodNode methodNode) {
        int iMax = -1;
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            String str = localVariableNode.name;
            str.getClass();
            if (JvmAbi.isFakeLocalVariableForInline(str)) {
                iMax = Math.max(iMax, localVariableNode.index + 1);
            }
        }
        return iMax;
    }

    public static final String getInsnOpcodeText(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            return "null";
        }
        if (abstractInsnNode instanceof LabelNode) {
            return "LABEL";
        }
        if (abstractInsnNode instanceof LineNumberNode) {
            return "LINENUMBER";
        }
        if (abstractInsnNode instanceof FrameNode) {
            return "FRAME";
        }
        String str = Printer.OPCODES[abstractInsnNode.getOpcode()];
        str.getClass();
        return str;
    }

    public static final String getInsnText(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            return "<null>";
        }
        Textifier textifier = new Textifier();
        abstractInsnNode.accept(new TraceMethodVisitor(textifier));
        StringWriter stringWriter = new StringWriter();
        textifier.print(new PrintWriter(stringWriter));
        stringWriter.flush();
        String string = stringWriter.toString();
        string.getClass();
        return StringsKt.trim(string).toString();
    }

    public static final Integer getLineNumberOrNull(AbstractInsnNode abstractInsnNode) {
        while (abstractInsnNode != null) {
            if (abstractInsnNode instanceof LineNumberNode) {
                return Integer.valueOf(((LineNumberNode) abstractInsnNode).line);
            }
            abstractInsnNode = abstractInsnNode.getPrevious();
        }
        return null;
    }

    public static final int getLoadStoreArgSize(int i) {
        return (i == 22 || i == 24 || i == 55 || i == 57) ? 2 : 1;
    }

    public static final String getMarkedReturnLabelOrNull(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (!isReturnOpcode(abstractInsnNode.getOpcode())) {
            return null;
        }
        MethodInsnNode previous = abstractInsnNode.getPrevious();
        if (previous instanceof MethodInsnNode) {
            MethodInsnNode methodInsnNode = previous;
            if (Intrinsics.areEqual("$$$$$NON_LOCAL_RETURN$$$$$", methodInsnNode.owner)) {
                return methodInsnNode.name;
            }
        }
        return null;
    }

    public static final SMAPAndMethodNode getMethodNode(byte[] bArr, Type type, final Function1<? super Method, Boolean> function1) {
        SMAP smapIdentityMapping;
        bArr.getClass();
        type.getClass();
        function1.getClass();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        new ClassReader(bArr).accept(new ClassVisitor() { // from class: org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt.getMethodNode.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(589824);
            }

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                name.getClass();
                desc.getClass();
                if (!((Boolean) function1.invoke(new Method(name, desc))).booleanValue()) {
                    return null;
                }
                Ref.ObjectRef<MethodNode> objectRef4 = objectRef;
                MethodNode methodNode = (MethodNode) objectRef4.element;
                if (methodNode == null) {
                    objectRef4.element = new MethodNode(589824, access, name, desc, signature, exceptions);
                    return (MethodVisitor) objectRef.element;
                }
                yp6.a(name, methodNode.name, methodNode.desc, desc);
                return null;
            }

            public void visitSource(String source, String debug) {
                objectRef2.element = source;
                objectRef3.element = debug;
            }
        }, 4);
        MethodNode methodNode = (MethodNode) objectRef.element;
        if (methodNode == null) {
            return null;
        }
        String str = (String) objectRef3.element;
        if (str == null || (smapIdentityMapping = SMAPParser.INSTANCE.parseOrNull(str)) == null) {
            SMAP.Companion companion = SMAP.INSTANCE;
            String str2 = (String) objectRef2.element;
            String internalName = type.getInternalName();
            internalName.getClass();
            smapIdentityMapping = companion.identityMapping(str2, internalName, CollectionsKt.listOfNotNull(methodNode));
        }
        return new SMAPAndMethodNode(methodNode, smapIdentityMapping);
    }

    public static final String getNodeText(MethodNode methodNode) {
        if (methodNode == null) {
            return "Not generated";
        }
        Textifier textifier = new Textifier();
        methodNode.accept(new TraceMethodVisitor(textifier));
        StringWriter stringWriter = new StringWriter();
        textifier.print(new PrintWriter(stringWriter));
        stringWriter.flush();
        return methodNode.name + ' ' + methodNode.desc + ":\n" + ((Object) stringWriter.getBuffer());
    }

    public static final Type getReturnType(int i) {
        switch (i) {
            case 172:
                Type type = Type.INT_TYPE;
                type.getClass();
                return type;
            case 173:
                Type type2 = Type.LONG_TYPE;
                type2.getClass();
                return type2;
            case 174:
                Type type3 = Type.FLOAT_TYPE;
                type3.getClass();
                return type3;
            case 175:
                Type type4 = Type.DOUBLE_TYPE;
                type4.getClass();
                return type4;
            case 176:
            default:
                Type type5 = AsmTypes.OBJECT_TYPE;
                type5.getClass();
                return type5;
            case 177:
                Type type6 = Type.VOID_TYPE;
                type6.getClass();
                return type6;
        }
    }

    public static final void insertNodeBefore(MethodNode methodNode, MethodNode methodNode2, AbstractInsnNode abstractInsnNode) {
        methodNode.getClass();
        methodNode2.getClass();
        abstractInsnNode.getClass();
        ListIterator it = methodNode.instructions.iterator();
        it.getClass();
        while (it.hasNext()) {
            methodNode2.instructions.insertBefore(abstractInsnNode, (AbstractInsnNode) it.next());
        }
    }

    public static final String insnText(AbstractInsnNode abstractInsnNode, final InsnList insnList) {
        insnList.getClass();
        if (abstractInsnNode == null) {
            return "<null>";
        }
        if (abstractInsnNode instanceof LabelNode) {
            return insnText$labelText((LabelNode) abstractInsnNode, insnList);
        }
        if (abstractInsnNode instanceof JumpInsnNode) {
            StringBuilder sb = new StringBuilder();
            sb.append(getInsnOpcodeText(abstractInsnNode));
            sb.append(' ');
            LabelNode labelNode = ((JumpInsnNode) abstractInsnNode).label;
            labelNode.getClass();
            sb.append(insnText$labelText(labelNode, insnList));
            return sb.toString();
        }
        if (abstractInsnNode instanceof LookupSwitchInsnNode) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getInsnOpcodeText(abstractInsnNode));
            sb2.append(' ');
            LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode) abstractInsnNode;
            List list = lookupSwitchInsnNode.keys;
            list.getClass();
            List list2 = lookupSwitchInsnNode.labels;
            list2.getClass();
            sb2.append(CollectionsKt.joinToString$default(CollectionsKt.zip(list, list2), (CharSequence) null, "[", "]", 0, (CharSequence) null, new Function1() { // from class: wp6
                public final Object invoke(Object obj) {
                    return InlineCodegenUtilsKt.a(insnList, (Pair) obj);
                }
            }, 25, (Object) null));
            return sb2.toString();
        }
        if (!(abstractInsnNode instanceof TableSwitchInsnNode)) {
            return getInsnText(abstractInsnNode);
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getInsnOpcodeText(abstractInsnNode));
        sb3.append(' ');
        TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode) abstractInsnNode;
        IntRange intRange = new IntRange(tableSwitchInsnNode.min, tableSwitchInsnNode.max);
        List list3 = tableSwitchInsnNode.labels;
        list3.getClass();
        sb3.append(CollectionsKt.joinToString$default(CollectionsKt.zip(intRange, list3), (CharSequence) null, "[", "]", 0, (CharSequence) null, new Function1() { // from class: xp6
            public final Object invoke(Object obj) {
                return InlineCodegenUtilsKt.b(insnList, (Pair) obj);
            }
        }, 25, (Object) null));
        return sb3.toString();
    }

    private static final int insnText$indexOf(AbstractInsnNode abstractInsnNode, InsnList insnList) {
        return insnList.indexOf(abstractInsnNode);
    }

    private static final String insnText$labelText(LabelNode labelNode, InsnList insnList) {
        return "L#" + insnText$indexOf(labelNode, insnList);
    }

    public static final boolean isAfterFakeContinuationConstructorCallMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 5);
    }

    public static final boolean isAfterInlineMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isInlineMarker(abstractInsnNode, "afterInlineCall");
    }

    public static final boolean isAfterInlineSuspendMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 7);
    }

    public static final boolean isAfterSuspendMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 1);
    }

    public static final boolean isAfterUnboxInlineClassMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 9);
    }

    public static final boolean isAnonymousClass(String str) {
        str.getClass();
        return !StringsKt.contains$default(str, "$sam$", false, 2, (Object) null) && isInteger$default(StringsKt.substringAfterLast(StringsKt.substringAfterLast$default(str, '/', (String) null, 2, (Object) null), CAPTURED_FIELD_PREFIX, Argument.Delimiters.none), 0, 1, null);
    }

    public static final boolean isAnonymousConstructorCall(String str, String str2) {
        str.getClass();
        str2.getClass();
        return isConstructor(str2) && isAnonymousClass(str);
    }

    public static final boolean isAnonymousSingletonLoad(String str, String str2) {
        str.getClass();
        str2.getClass();
        return Intrinsics.areEqual("INSTANCE", str2) && isAnonymousClass(str);
    }

    public static final boolean isBeforeFakeContinuationConstructorCallMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 4);
    }

    public static final boolean isBeforeInlineMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isInlineMarker(abstractInsnNode, "beforeInlineCall");
    }

    public static final boolean isBeforeInlineSuspendMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 6);
    }

    public static final boolean isBeforeSuspendGenericCallMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 12);
    }

    public static final boolean isBeforeSuspendMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 0);
    }

    public static final boolean isBeforeSuspendUnitCallMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 11);
    }

    public static final boolean isBeforeUnboxInlineClassMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 8);
    }

    public static final boolean isCapturedFieldName(String str) {
        str.getClass();
        return !(!StringsKt.startsWith$default(str, CAPTURED_FIELD_PREFIX, false, 2, (Object) null) || StringsKt.startsWith$default(str, "$$", false, 2, (Object) null) || Intrinsics.areEqual(str, AssertCodegenUtilKt.ASSERTIONS_DISABLED_FIELD_NAME)) || Intrinsics.areEqual("this$0", str) || Intrinsics.areEqual("receiver$0", str);
    }

    public static final boolean isCatchStoreInstruction(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return resolveCatchStoreInstruction(abstractInsnNode) != null;
    }

    private static final boolean isConstructor(String str) {
        return Intrinsics.areEqual("<init>", str);
    }

    public static final boolean isFakeContinuationMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getPrevious() == null) {
            return false;
        }
        AbstractInsnNode previous = abstractInsnNode.getPrevious();
        previous.getClass();
        return isSuspendMarker(previous, 3) && abstractInsnNode.getOpcode() == 1;
    }

    public static final boolean isFinallyEnd(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isFinallyMarker(abstractInsnNode, "finallyEnd");
    }

    private static final boolean isFinallyMarker(AbstractInsnNode abstractInsnNode, String str) {
        if (!(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(INLINE_MARKER_CLASS_NAME, methodInsnNode.owner) && Intrinsics.areEqual(str, methodInsnNode.name);
    }

    public static final boolean isFinallyStart(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isFinallyMarker(abstractInsnNode, "finallyStart");
    }

    public static final boolean isInlineMarker(AbstractInsnNode abstractInsnNode, String str) {
        boolean zAreEqual;
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 184) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        if (Intrinsics.areEqual(methodInsnNode.owner, INLINE_MARKER_CLASS_NAME)) {
            String str2 = methodInsnNode.name;
            if (str != null) {
                zAreEqual = Intrinsics.areEqual(str2, str);
            } else {
                zAreEqual = Intrinsics.areEqual(str2, "beforeInlineCall") || Intrinsics.areEqual(methodInsnNode.name, "afterInlineCall");
            }
            if (zAreEqual) {
                return true;
            }
        }
        return false;
    }

    private static final boolean isInteger(String str, int i) {
        return StringsKt.toIntOrNull(str, i) != null;
    }

    public static /* synthetic */ boolean isInteger$default(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        return isInteger(str, i);
    }

    public static final boolean isInvokeOnLambda(String str, String str2) {
        str.getClass();
        str2.getClass();
        return Intrinsics.areEqual(OperatorNameConventions.INVOKE.asString(), str2) && isNumberedFunctionInternalName(str);
    }

    public static final boolean isMarkedReturn(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return getMarkedReturnLabelOrNull(abstractInsnNode) != null;
    }

    public static final boolean isNumberedFunctionInternalName(String str) {
        str.getClass();
        return StringsKt.startsWith$default(str, NUMBERED_FUNCTION_PREFIX, false, 2, (Object) null) && isInteger$default(str.substring(29), 0, 1, null);
    }

    private static final boolean isOldSamWrapper(String str) {
        if (StringsKt.contains$default(str, "$sam$", false, 2, (Object) null)) {
            String strSubstringAfter = StringsKt.substringAfter(str, "$i$", Argument.Delimiters.none);
            if (strSubstringAfter.length() == 8 && StringsKt.toLongOrNull(strSubstringAfter, 16) != null) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isReturnOpcode(int i) {
        return i >= 172 && i <= 177;
    }

    public static final boolean isReturnsUnitMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 2);
    }

    public static final boolean isSamWrapper(String str) {
        str.getClass();
        return (StringsKt.endsWith$default(str, "$0", false, 2, (Object) null) && StringsKt.contains$default(str, "$sam$i$", false, 2, (Object) null)) || isOldSamWrapper(str);
    }

    public static final boolean isSamWrapperConstructorCall(String str, String str2) {
        str.getClass();
        str2.getClass();
        return isConstructor(str2) && isSamWrapper(str);
    }

    public static final boolean isStoreInstruction(int i) {
        return i >= 54 && i <= 58;
    }

    public static final boolean isSuspendInlineMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isInlineMarker(abstractInsnNode, "mark");
    }

    public static final boolean isSuspendLambdaParameterMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isSuspendMarker(abstractInsnNode, 10);
    }

    private static final boolean isSuspendMarker(AbstractInsnNode abstractInsnNode, int i) {
        if (!isInlineMarker(abstractInsnNode, "mark")) {
            return false;
        }
        AbstractInsnNode previous = abstractInsnNode.getPrevious();
        previous.getClass();
        Integer intConstant = UtilKt.getIntConstant(previous);
        return intConstant != null && intConstant.intValue() == i;
    }

    public static final boolean isThis0(String str) {
        str.getClass();
        return Intrinsics.areEqual("this$0", str);
    }

    public static final boolean isWhenMappingAccess(String str, String str2) {
        str.getClass();
        str2.getClass();
        return StringsKt.startsWith$default(str2, "$EnumSwitchMapping$", false, 2, (Object) null) && StringsKt.endsWith$default(str, "$WhenMappings", false, 2, (Object) null);
    }

    public static final byte[] loadClassBytesByInternalName(GenerationState generationState, String str) {
        generationState.getClass();
        str.getClass();
        OutputFile outputFile = generationState.getFactory().get(str + ".class");
        if (outputFile != null) {
            return outputFile.asByteArray();
        }
        byte[] classBytes = generationState.getInlineCache().getClassBytes(str);
        if (classBytes != null) {
            return classBytes;
        }
        VirtualFile virtualFileFindVirtualFileImprecise = findVirtualFileImprecise(generationState, str);
        if (virtualFileFindVirtualFileImprecise == null) {
            y04.a("Couldn't find virtual file for ", str);
            return null;
        }
        byte[] bArrContentsToByteArray = virtualFileFindVirtualFileImprecise.contentsToByteArray();
        bArrContentsToByteArray.getClass();
        return bArrContentsToByteArray;
    }

    public static final MethodNode newMethodNodeWithCorrectStackSize(Function1<? super InstructionAdapter, Unit> function1) {
        function1.getClass();
        MethodNode methodNode = new MethodNode(589824, "fake", "()V", (String) null, (String[]) null);
        MaxStackFrameSizeAndLocalsCalculator maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc = wrapWithMaxLocalCalc(methodNode);
        function1.invoke(new InstructionAdapter(maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc));
        maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc.visitInsn(177);
        maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc.visitMaxs(-1, -1);
        InsnList insnList = methodNode.instructions;
        insnList.remove(insnList.getLast());
        return methodNode;
    }

    public static final void preprocessSuspendMarkers(MethodNode methodNode, boolean z, boolean z2) {
        AbstractInsnNode next;
        AbstractInsnNode next2;
        AbstractInsnNode next3;
        Object next4;
        Object next5;
        AbstractInsnNode next6;
        methodNode.getClass();
        if (methodNode.instructions.getFirst() == null) {
            return;
        }
        if (!z2) {
            InsnList insnList = methodNode.instructions;
            insnList.getClass();
            Sequence<AbstractInsnNode> sequenceAsSequence = InsnSequenceKt.asSequence(insnList);
            Iterator it = sequenceAsSequence.iterator();
            do {
                next3 = null;
                if (!it.hasNext()) {
                    next4 = null;
                    break;
                }
                next4 = it.next();
            } while (!isBeforeFakeContinuationConstructorCallMarker((AbstractInsnNode) next4));
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) next4;
            Iterator it2 = sequenceAsSequence.iterator();
            do {
                if (!it2.hasNext()) {
                    next5 = null;
                    break;
                }
                next5 = it2.next();
            } while (!isAfterFakeContinuationConstructorCallMarker((AbstractInsnNode) next5));
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) next5;
            if (abstractInsnNode != null) {
                AbstractInsnNode previous = abstractInsnNode.getPrevious();
                previous.getClass();
                if (abstractInsnNode2 != null && (next6 = abstractInsnNode2.getNext()) != null) {
                    next3 = next6.getNext();
                }
                InsnSequence insnSequence = new InsnSequence(previous, next3);
                InsnList insnList2 = methodNode.instructions;
                insnList2.getClass();
                Iterator it3 = insnSequence.iterator();
                while (it3.hasNext()) {
                    insnList2.remove((AbstractInsnNode) it3.next());
                }
            }
        }
        InsnList insnList3 = methodNode.instructions;
        insnList3.getClass();
        for (AbstractInsnNode abstractInsnNode3 : SequencesKt.filter(InsnSequenceKt.asSequence(insnList3), new Function1() { // from class: vp6
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InlineCodegenUtilsKt.c((AbstractInsnNode) obj));
            }
        })) {
            if (z || z2) {
                AbstractInsnNode previous2 = abstractInsnNode3.getPrevious().getPrevious();
                previous2.getClass();
                if (isReturnsUnitMarker(previous2)) {
                    methodNode.instructions.remove(previous2.getPrevious());
                    methodNode.instructions.remove(previous2);
                }
                if (isBeforeInlineSuspendMarker(abstractInsnNode3) && (next = abstractInsnNode3.getNext()) != null && (next2 = next.getNext()) != null && (isBeforeSuspendUnitCallMarker(next2) || isBeforeSuspendGenericCallMarker(next2))) {
                    methodNode.instructions.remove(abstractInsnNode3.getNext().getNext());
                    methodNode.instructions.remove(abstractInsnNode3.getNext());
                }
                methodNode.instructions.remove(abstractInsnNode3.getPrevious());
                methodNode.instructions.remove(abstractInsnNode3);
            } else {
                methodNode.instructions.set(abstractInsnNode3.getPrevious(), new InsnNode((!isBeforeInlineSuspendMarker(abstractInsnNode3) ? 1 : 0) + 3));
            }
        }
    }

    public static /* synthetic */ void preprocessSuspendMarkers$default(MethodNode methodNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        preprocessSuspendMarkers(methodNode, z, z2);
    }

    public static final void removeFinallyMarkers(MethodNode methodNode) {
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        AbstractInsnNode first = insnList.getFirst();
        while (first != null) {
            if (isFinallyMarker(first)) {
                AbstractInsnNode previous = first.getPrevious();
                previous.getClass();
                getConstant(previous);
                AbstractInsnNode next = first.getNext();
                insnList.remove(first.getPrevious());
                insnList.remove(first);
                first = next;
            } else {
                first = first.getNext();
            }
        }
    }

    public static final AbstractInsnNode resolveCatchStoreInstruction(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode next;
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() == 58) {
            return abstractInsnNode;
        }
        AbstractInsnNode next2 = abstractInsnNode.getNext();
        if (next2 == null || (next = next2.getNext()) == null || !ReifiedTypeInliner.INSTANCE.isOperationReifiedMarker(next)) {
            return null;
        }
        AbstractInsnNode next3 = next.getNext();
        if (next3.getOpcode() == 58) {
            return next3;
        }
        return null;
    }

    public static final String text(TryCatchBlockNode tryCatchBlockNode, InsnList insnList) {
        tryCatchBlockNode.getClass();
        insnList.getClass();
        return "[" + insnList.indexOf(tryCatchBlockNode.start) + " .. " + insnList.indexOf(tryCatchBlockNode.end) + " -> " + insnList.indexOf(tryCatchBlockNode.handler) + ']';
    }

    public static final MaxStackFrameSizeAndLocalsCalculator wrapWithMaxLocalCalc(MethodNode methodNode) {
        methodNode.getClass();
        return new MaxStackFrameSizeAndLocalsCalculator(589824, methodNode.access, methodNode.desc, methodNode);
    }

    public static final boolean isFinallyMarker(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode != null) {
            return isFinallyStart(abstractInsnNode) || isFinallyEnd(abstractInsnNode);
        }
        return false;
    }

    public static final boolean isInlineMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return isInlineMarker(abstractInsnNode, null);
    }

    public static final SMAPAndMethodNode getMethodNode(byte[] bArr, Type type, final Method method) {
        SMAP smapIdentityMapping;
        bArr.getClass();
        type.getClass();
        method.getClass();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        new ClassReader(bArr).accept(new ClassVisitor() { // from class: org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt$getMethodNode$$inlined$getMethodNode$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(589824);
            }

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                name.getClass();
                desc.getClass();
                if (!Intrinsics.areEqual(new Method(name, desc), method)) {
                    return null;
                }
                Ref.ObjectRef objectRef4 = objectRef;
                MethodNode methodNode = (MethodNode) objectRef4.element;
                if (methodNode == null) {
                    objectRef4.element = new MethodNode(589824, access, name, desc, signature, exceptions);
                    return (MethodVisitor) objectRef.element;
                }
                yp6.a(name, methodNode.name, methodNode.desc, desc);
                return null;
            }

            public void visitSource(String source, String debug) {
                objectRef2.element = source;
                objectRef3.element = debug;
            }
        }, 4);
        MethodNode methodNode = (MethodNode) objectRef.element;
        if (methodNode == null) {
            return null;
        }
        String str = (String) objectRef3.element;
        if (str == null || (smapIdentityMapping = SMAPParser.INSTANCE.parseOrNull(str)) == null) {
            SMAP.Companion companion = SMAP.INSTANCE;
            String str2 = (String) objectRef2.element;
            String internalName = type.getInternalName();
            internalName.getClass();
            smapIdentityMapping = companion.identityMapping(str2, internalName, CollectionsKt.listOfNotNull(methodNode));
        }
        return new SMAPAndMethodNode(methodNode, smapIdentityMapping);
    }
}
