package org.jetbrains.kotlin.codegen.inline.coroutines;

import com.intellij.util.ArrayUtil;
import defpackage.f2f;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineTransformerMethodVisitor;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineTransformerMethodVisitorKt;
import org.jetbrains.kotlin.codegen.coroutines.SuspendForInlineCopyingMethodVisitor;
import org.jetbrains.kotlin.codegen.inline.DeferredMethodVisitor;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.inline.InliningContext;
import org.jetbrains.kotlin.codegen.inline.RegeneratedClassContext;
import org.jetbrains.kotlin.codegen.inline.SourceCompilerForInline;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformer;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 )2\u00020\u0001:\u0001)B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bJ\b\u0010\u0015\u001a\u00020\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\bJ\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\bH\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010 \u001a\u00020\nH\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\bJ\u000e\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\nJ\u0012\u0010%\u001a\u0004\u0018\u00010\u00052\u0006\u0010$\u001a\u00020\nH\u0002J\u000e\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\bJ\u0010\u0010(\u001a\u0004\u0018\u00010\n2\u0006\u0010'\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/coroutines/CoroutineTransformer;", Argument.Delimiters.none, "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "classBuilder", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "methods", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "superClassName", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/InliningContext;Lorg/jetbrains/kotlin/codegen/ClassBuilder;Ljava/util/List;Ljava/lang/String;)V", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "generateForInline", Argument.Delimiters.none, "shouldSkip", "node", "shouldGenerateStateMachine", "suspendLambdaWithGeneratedStateMachine", "isContinuationNotLambda", "isStateMachine", "isSuspendLambda", "newMethod", "Lorg/jetbrains/kotlin/codegen/inline/DeferredMethodVisitor;", "isInvokeSuspend", "isSuspendFunctionWithFakeConstructorCall", "newStateMachineForLambda", "newStateMachineForNamedFunction", "createNewMethodFrom", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", ModuleXmlParser.NAME, "replaceFakesWithReals", Argument.Delimiters.none, "registerClassBuilder", "continuationClassName", "unregisterClassBuilder", "safeToRemoveContinuationClass", "method", "oldContinuationFrom", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoroutineTransformer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ClassBuilder classBuilder;
    private final boolean generateForInline;
    private final InliningContext inliningContext;
    private final List<MethodNode> methods;
    private final GenerationState state;
    private final String superClassName;

    public CoroutineTransformer(InliningContext inliningContext, ClassBuilder classBuilder, List<? extends MethodNode> list, String str) {
        inliningContext.getClass();
        classBuilder.getClass();
        list.getClass();
        str.getClass();
        this.inliningContext = inliningContext;
        this.classBuilder = classBuilder;
        this.methods = list;
        this.superClassName = str;
        this.state = inliningContext.getState();
        this.generateForInline = inliningContext.getCallSiteInfo().isInlineOrInsideInline();
    }

    private final MethodVisitor createNewMethodFrom(MethodNode node, String name) {
        MethodVisitor methodVisitorNewMethod = this.classBuilder.newMethod(JvmDeclarationOrigin.NO_ORIGIN, node.access, name, node.desc, node.signature, ArrayUtil.toStringArray(node.exceptions));
        methodVisitorNewMethod.getClass();
        return methodVisitorNewMethod;
    }

    public static MethodVisitor d(final CoroutineTransformer coroutineTransformer, MethodNode methodNode, String str) {
        String name;
        final SourceCompilerForInline sourceCompilerForInline = coroutineTransformer.inliningContext.getRoot().getSourceCompilerForInline();
        MethodVisitor methodVisitorCreateNewMethodFrom = coroutineTransformer.createNewMethodFrom(methodNode, str);
        int i = methodNode.access;
        String str2 = methodNode.desc;
        str2.getClass();
        String thisName = coroutineTransformer.classBuilder.getThisName();
        thisName.getClass();
        Function0 function0 = new Function0() { // from class: h13
            public final Object invoke() {
                return this.b.classBuilder;
            }
        };
        Function1 function1 = new Function1() { // from class: i13
            public final Object invoke(Object obj) {
                return CoroutineTransformer.newStateMachineForLambda$lambda$0$1(sourceCompilerForInline, (String) obj);
            }
        };
        int lineNumber = coroutineTransformer.inliningContext.getCallSiteInfo().getLineNumber();
        File file = coroutineTransformer.inliningContext.getCallSiteInfo().getFile();
        if (file == null || (name = file.getName()) == null) {
            name = Argument.Delimiters.none;
        }
        CoroutineTransformerMethodVisitor coroutineTransformerMethodVisitor = new CoroutineTransformerMethodVisitor(methodVisitorCreateNewMethodFrom, i, str, str2, null, null, thisName, function0, false, function1, lineNumber, name, coroutineTransformer.state.getConfig(), false, null, null, 57344, null);
        if (!coroutineTransformer.generateForInline) {
            return coroutineTransformerMethodVisitor;
        }
        int i2 = methodNode.access;
        String str3 = methodNode.desc;
        str3.getClass();
        return new SuspendForInlineCopyingMethodVisitor(coroutineTransformerMethodVisitor, i2, str, str3, new CoroutineTransformer$newStateMachineForLambda$1$1(coroutineTransformer.classBuilder), false, 32, null);
    }

    public static MethodVisitor e(final CoroutineTransformer coroutineTransformer, MethodNode methodNode, String str, final String str2) {
        String name;
        final SourceCompilerForInline sourceCompilerForInline = coroutineTransformer.inliningContext.getRoot().getSourceCompilerForInline();
        MethodVisitor methodVisitorCreateNewMethodFrom = coroutineTransformer.createNewMethodFrom(methodNode, str);
        int i = methodNode.access;
        String str3 = methodNode.desc;
        str3.getClass();
        String thisName = coroutineTransformer.classBuilder.getThisName();
        thisName.getClass();
        Function0 function0 = new Function0() { // from class: k13
            public final Object invoke() {
                return CoroutineTransformer.newStateMachineForNamedFunction$lambda$0$0(this.b, str2);
            }
        };
        Function1 function1 = new Function1() { // from class: l13
            public final Object invoke(Object obj) {
                return CoroutineTransformer.newStateMachineForNamedFunction$lambda$0$1(sourceCompilerForInline, (String) obj);
            }
        };
        int lineNumber = coroutineTransformer.inliningContext.getCallSiteInfo().getLineNumber();
        File file = coroutineTransformer.inliningContext.getCallSiteInfo().getFile();
        if (file == null || (name = file.getName()) == null) {
            name = Argument.Delimiters.none;
        }
        CoroutineTransformerMethodVisitor coroutineTransformerMethodVisitor = new CoroutineTransformerMethodVisitor(methodVisitorCreateNewMethodFrom, i, str, str3, null, null, thisName, function0, true, function1, lineNumber, name, coroutineTransformer.state.getConfig(), true, coroutineTransformer.classBuilder.getThisName(), null, 32768, null);
        if (!coroutineTransformer.generateForInline) {
            return coroutineTransformerMethodVisitor;
        }
        int i2 = methodNode.access;
        String str4 = methodNode.desc;
        str4.getClass();
        return new SuspendForInlineCopyingMethodVisitor(coroutineTransformerMethodVisitor, i2, str, str4, new CoroutineTransformer$newStateMachineForNamedFunction$1$1(coroutineTransformer.classBuilder), false, 32, null);
    }

    private final boolean isContinuationNotLambda() {
        return this.inliningContext.getIsContinuation() && StringsKt.endsWith$default(this.superClassName, "ContinuationImpl", false, 2, (Object) null);
    }

    private final boolean isInvokeSuspend(MethodNode node) {
        String str = node.name;
        str.getClass();
        return Intrinsics.areEqual(StringsKt.removeSuffix(str, CoroutineTransformerKt.FOR_INLINE_SUFFIX), CoroutineCodegenUtilKt.INVOKE_SUSPEND_METHOD_NAME) && this.inliningContext.getIsContinuation();
    }

    private final boolean isStateMachine(MethodNode node) {
        InsnList insnList = node.instructions;
        insnList.getClass();
        Iterator it = InsnSequenceKt.asSequence(insnList).iterator();
        while (it.hasNext()) {
            LdcInsnNode ldcInsnNode = (AbstractInsnNode) it.next();
            if ((ldcInsnNode instanceof LdcInsnNode) && Intrinsics.areEqual(ldcInsnNode.cst, CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isSuspendFunctionWithFakeConstructorCall(MethodNode node) {
        return INSTANCE.findFakeContinuationConstructorClassName(node) != null;
    }

    private final boolean isSuspendLambda(MethodNode node) {
        return isInvokeSuspend(node);
    }

    private final DeferredMethodVisitor newStateMachineForLambda(final MethodNode node) {
        String str = node.name;
        str.getClass();
        final String strRemoveSuffix = StringsKt.removeSuffix(str, CoroutineTransformerKt.FOR_INLINE_SUFFIX);
        return new DeferredMethodVisitor(new MethodNode(node.access, strRemoveSuffix, node.desc, node.signature, ArrayUtil.toStringArray(node.exceptions)), new Function0() { // from class: g13
            public final Object invoke() {
                return CoroutineTransformer.d(this.b, node, strRemoveSuffix);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit newStateMachineForLambda$lambda$0$1(SourceCompilerForInline sourceCompilerForInline, String str) {
        str.getClass();
        sourceCompilerForInline.reportSuspensionPointInsideMonitor(str);
        return Unit.INSTANCE;
    }

    private final DeferredMethodVisitor newStateMachineForNamedFunction(final MethodNode node) {
        String str = node.name;
        str.getClass();
        final String strRemoveSuffix = StringsKt.removeSuffix(str, CoroutineTransformerKt.FOR_INLINE_SUFFIX);
        final String strFindFakeContinuationConstructorClassName = INSTANCE.findFakeContinuationConstructorClassName(node);
        return new DeferredMethodVisitor(new MethodNode(node.access, strRemoveSuffix, node.desc, node.signature, ArrayUtil.toStringArray(node.exceptions)), new Function0() { // from class: j13
            public final Object invoke() {
                return CoroutineTransformer.e(this.b, node, strRemoveSuffix, strFindFakeContinuationConstructorClassName);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClassBuilder newStateMachineForNamedFunction$lambda$0$0(CoroutineTransformer coroutineTransformer, String str) {
        InliningContext inliningContext = coroutineTransformer.inliningContext;
        inliningContext.getClass();
        ClassBuilder classBuilder = ((RegeneratedClassContext) inliningContext).getContinuationBuilders().get(str);
        classBuilder.getClass();
        return classBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit newStateMachineForNamedFunction$lambda$0$1(SourceCompilerForInline sourceCompilerForInline, String str) {
        str.getClass();
        sourceCompilerForInline.reportSuspensionPointInsideMonitor(str);
        return Unit.INSTANCE;
    }

    private final ClassBuilder unregisterClassBuilder(String continuationClassName) {
        InliningContext inliningContext = this.inliningContext;
        inliningContext.getClass();
        return ((RegeneratedClassContext) inliningContext).getContinuationBuilders().remove(continuationClassName);
    }

    public final DeferredMethodVisitor newMethod(MethodNode node) {
        node.getClass();
        if (isInvokeSuspend(node)) {
            isStateMachine(node);
            return newStateMachineForLambda(node);
        }
        if (isSuspendFunctionWithFakeConstructorCall(node)) {
            return newStateMachineForNamedFunction(node);
        }
        f2f.a("no need to generate state machine for ", node.name);
        return null;
    }

    public final String oldContinuationFrom(MethodNode method) {
        Object next;
        method.getClass();
        Iterator<T> it = this.methods.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            MethodNode methodNode = (MethodNode) next;
            if (Intrinsics.areEqual(methodNode.name, method.name + CoroutineTransformerKt.FOR_INLINE_SUFFIX) && Intrinsics.areEqual(methodNode.desc, method.desc)) {
                break;
            }
        }
        MethodNode methodNode2 = (MethodNode) next;
        if (methodNode2 != null) {
            return INSTANCE.findFakeContinuationConstructorClassName(methodNode2);
        }
        return null;
    }

    public final void registerClassBuilder(String continuationClassName) {
        continuationClassName.getClass();
        InliningContext parent = this.inliningContext.getParent();
        InliningContext parent2 = parent != null ? parent.getParent() : null;
        RegeneratedClassContext regeneratedClassContext = parent2 instanceof RegeneratedClassContext ? (RegeneratedClassContext) parent2 : null;
        if (regeneratedClassContext != null) {
            regeneratedClassContext.getContinuationBuilders().put(continuationClassName, this.classBuilder);
        } else {
            k2d.a("incorrect context");
        }
    }

    public final void replaceFakesWithReals(MethodNode node) {
        int lastParameterIndex;
        ClassBuilder classBuilderUnregisterClassBuilder;
        node.getClass();
        String strFindFakeContinuationConstructorClassName = INSTANCE.findFakeContinuationConstructorClassName(node);
        if (strFindFakeContinuationConstructorClassName != null && (classBuilderUnregisterClassBuilder = unregisterClassBuilder(strFindFakeContinuationConstructorClassName)) != null) {
            classBuilderUnregisterClassBuilder.done(this.state.getConfig().getGenerateSmapCopyToAnnotation());
        }
        if (this.inliningContext.getIsContinuation()) {
            lastParameterIndex = 0;
        } else {
            String str = node.desc;
            str.getClass();
            lastParameterIndex = CoroutineTransformerMethodVisitorKt.getLastParameterIndex(str, node.access);
        }
        CoroutineTransformerMethodVisitorKt.replaceFakeContinuationsWithRealOnes(node, lastParameterIndex);
    }

    public final boolean safeToRemoveContinuationClass(MethodNode method) {
        method.getClass();
        return (this.generateForInline || isStateMachine(method)) ? false : true;
    }

    public final boolean shouldGenerateStateMachine(MethodNode node) {
        node.getClass();
        if (isContinuationNotLambda()) {
            return false;
        }
        if (isSuspendFunctionWithFakeConstructorCall(node)) {
            return true;
        }
        return isSuspendLambda(node) && !isStateMachine(node);
    }

    public final boolean shouldSkip(MethodNode node) {
        node.getClass();
        List<MethodNode> list = this.methods;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (MethodNode methodNode : list) {
            if (Intrinsics.areEqual(methodNode.name, node.name + CoroutineTransformerKt.FOR_INLINE_SUFFIX) && Intrinsics.areEqual(methodNode.desc, node.desc)) {
                return true;
            }
        }
        return false;
    }

    public final boolean suspendLambdaWithGeneratedStateMachine(MethodNode node) {
        node.getClass();
        return !isContinuationNotLambda() && isSuspendLambda(node) && isStateMachine(node);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/coroutines/CoroutineTransformer$Companion;", Argument.Delimiters.none, "<init>", "()V", "findFakeContinuationConstructorClassName", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String findFakeContinuationConstructorClassName(MethodNode node) {
            Object next;
            node.getClass();
            InsnList insnList = node.instructions;
            insnList.getClass();
            Iterator it = InsnSequenceKt.asSequence(insnList).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!InlineCodegenUtilsKt.isBeforeFakeContinuationConstructorCallMarker((AbstractInsnNode) next));
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) next;
            if (abstractInsnNode == null) {
                return null;
            }
            TypeInsnNode next2 = abstractInsnNode.getNext();
            if (next2 != null) {
                next2.getOpcode();
            }
            next2.getClass();
            return next2.desc;
        }

        private Companion() {
        }
    }
}
