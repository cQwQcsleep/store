package org.jetbrains.kotlin.codegen.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.checkers.CoroutineCallCheckerKt;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\n\u001a\u00020\u0007\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\u0001\u001a\u0014\u0010\r\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b\u001a\u0006\u0010\u001c\u001a\u00020\u0019\u001a\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0002\u001a\n\u0010 \u001a\u00020\u001e*\u00020\u001f\u001a\n\u0010!\u001a\u00020\u001e*\u00020\u001f\u001a\n\u0010%\u001a\u00020\u001e*\u00020\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u0015\u0010#\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b$¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"COROUTINE_LABEL_FIELD_NAME", Argument.Delimiters.none, "SUSPEND_FUNCTION_CREATE_METHOD_NAME", "INVOKE_SUSPEND_METHOD_NAME", "CONTINUATION_RESULT_FIELD_NAME", "GET_CONTEXT_METHOD_NAME", "DEBUG_METADATA_ANNOTATION_ASM_TYPE", "Lorg/jetbrains/org/objectweb/asm/Type;", "getDEBUG_METADATA_ANNOTATION_ASM_TYPE", "()Lorg/jetbrains/org/objectweb/asm/Type;", "coroutineContextAsmType", "isCoroutineSuperClass", Argument.Delimiters.none, "identifiedChild", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "coroutinesIntrinsicsFileFacadeInternalName", "CONTINUATION_PARAMETER_NAME", "Lorg/jetbrains/kotlin/name/Name;", "getCONTINUATION_PARAMETER_NAME", "()Lorg/jetbrains/kotlin/name/Name;", "CONTINUATION_VARIABLE_NAME", "DEBUG_PROBES_INTERNAL_NAME", "SPILLING_INTERNAL_NAME", "createMethodNodeForCoroutineContext", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "functionDescriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "createMethodNodeForSuspendCoroutineUninterceptedOrReturn", "invokeGetContext", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "loadCoroutineSuspendedMarker", "generateCoroutineSuspendedCheck", "SUSPEND_IMPL_NAME_SUFFIX", "CONTINUATION_ASM_TYPE", "Lkotlin/jvm/JvmField;", "invokeNullOutSpilledVariable", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoroutineCodegenUtilKt {
    public static final Type CONTINUATION_ASM_TYPE;
    private static final Name CONTINUATION_PARAMETER_NAME;
    public static final String CONTINUATION_RESULT_FIELD_NAME = "result";
    public static final String CONTINUATION_VARIABLE_NAME = "$continuation";
    public static final String COROUTINE_LABEL_FIELD_NAME = "label";
    private static final Type DEBUG_METADATA_ANNOTATION_ASM_TYPE;
    private static final String DEBUG_PROBES_INTERNAL_NAME;
    public static final String INVOKE_SUSPEND_METHOD_NAME = "invokeSuspend";
    private static final String SPILLING_INTERNAL_NAME;
    public static final String SUSPEND_FUNCTION_CREATE_METHOD_NAME = "create";
    public static final String SUSPEND_IMPL_NAME_SUFFIX = "$suspendImpl";
    private static final Type coroutinesIntrinsicsFileFacadeInternalName;

    static {
        FqName fqName = StandardNames.COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME;
        Name nameIdentifier = Name.identifier("DebugMetadata");
        nameIdentifier.getClass();
        DEBUG_METADATA_ANNOTATION_ASM_TYPE = CodegenUtilKt.topLevelClassAsmType(fqName.child(nameIdentifier));
        FqName fqName2 = StandardNames.COROUTINES_INTRINSICS_PACKAGE_FQ_NAME;
        Name nameIdentifier2 = Name.identifier("IntrinsicsKt");
        nameIdentifier2.getClass();
        coroutinesIntrinsicsFileFacadeInternalName = CodegenUtilKt.topLevelClassAsmType(fqName2.child(nameIdentifier2));
        Name nameIdentifier3 = Name.identifier("continuation");
        nameIdentifier3.getClass();
        CONTINUATION_PARAMETER_NAME = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("DebugProbesKt");
        nameIdentifier4.getClass();
        DEBUG_PROBES_INTERNAL_NAME = CodegenUtilKt.topLevelClassInternalName(fqName.child(nameIdentifier4));
        Name nameIdentifier5 = Name.identifier("SpillingKt");
        nameIdentifier5.getClass();
        SPILLING_INTERNAL_NAME = CodegenUtilKt.topLevelClassInternalName(fqName.child(nameIdentifier5));
        CONTINUATION_ASM_TYPE = CodegenUtilKt.topLevelClassAsmType(StandardNames.CONTINUATION_INTERFACE_FQ_NAME);
    }

    public static final Type coroutineContextAsmType() {
        FqName fqName = StandardNames.COROUTINES_PACKAGE_FQ_NAME;
        Name nameIdentifier = Name.identifier("CoroutineContext");
        nameIdentifier.getClass();
        return CodegenUtilKt.topLevelClassAsmType(fqName.child(nameIdentifier));
    }

    public static final MethodNode createMethodNodeForCoroutineContext(FunctionDescriptor functionDescriptor) {
        functionDescriptor.getClass();
        CoroutineCallCheckerKt.isBuiltInCoroutineContext(functionDescriptor);
        MethodNode methodNode = new MethodNode(589824, 8, "fake", Type.getMethodDescriptor(coroutineContextAsmType(), new Type[0]), (String) null, (String[]) null);
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
        InlineCodegenUtilsKt.addFakeContinuationMarker(instructionAdapter);
        invokeGetContext(instructionAdapter);
        methodNode.visitMaxs(1, 1);
        return methodNode;
    }

    public static final MethodNode createMethodNodeForSuspendCoroutineUninterceptedOrReturn() {
        Type type = AsmTypes.OBJECT_TYPE;
        Type type2 = AsmTypes.FUNCTION1;
        Type type3 = CONTINUATION_ASM_TYPE;
        MethodNode methodNode = new MethodNode(589824, 8, "fake", Type.getMethodDescriptor(type, new Type[]{type2, type3}), (String) null, (String[]) null);
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
        instructionAdapter.load(0, type);
        instructionAdapter.load(1, type);
        instructionAdapter.invokeinterface(type2.getInternalName(), OperatorNameConventions.INVOKE.getIdentifier(), "(" + type + ')' + type);
        Label label = new Label();
        instructionAdapter.dup();
        loadCoroutineSuspendedMarker(instructionAdapter);
        instructionAdapter.ifacmpne(label);
        instructionAdapter.load(1, type);
        instructionAdapter.checkcast(type3);
        instructionAdapter.invokestatic(DEBUG_PROBES_INTERNAL_NAME, "probeCoroutineSuspended", "(" + type3 + ")V", false);
        instructionAdapter.mark(label);
        methodNode.visitInsn(176);
        methodNode.visitMaxs(3, 2);
        return methodNode;
    }

    public static final void generateCoroutineSuspendedCheck(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        instructionAdapter.dup();
        loadCoroutineSuspendedMarker(instructionAdapter);
        Label label = new Label();
        instructionAdapter.ifacmpne(label);
        instructionAdapter.areturn(AsmTypes.OBJECT_TYPE);
        instructionAdapter.mark(label);
    }

    public static final Name getCONTINUATION_PARAMETER_NAME() {
        return CONTINUATION_PARAMETER_NAME;
    }

    public static final Type getDEBUG_METADATA_ANNOTATION_ASM_TYPE() {
        return DEBUG_METADATA_ANNOTATION_ASM_TYPE;
    }

    private static final String identifiedChild(FqName fqName, String str) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return CodegenUtilKt.topLevelClassInternalName(fqName.child(nameIdentifier));
    }

    private static final void invokeGetContext(InstructionAdapter instructionAdapter) {
        instructionAdapter.invokeinterface(CONTINUATION_ASM_TYPE.getInternalName(), "getContext", Type.getMethodDescriptor(coroutineContextAsmType(), new Type[0]));
        instructionAdapter.areturn(coroutineContextAsmType());
    }

    public static final void invokeNullOutSpilledVariable(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        String str = SPILLING_INTERNAL_NAME;
        StringBuilder sb = new StringBuilder("(");
        Type type = AsmTypes.OBJECT_TYPE;
        sb.append(type);
        sb.append(')');
        sb.append(type);
        instructionAdapter.invokestatic(str, "nullOutSpilledVariable", sb.toString(), false);
    }

    public static final boolean isCoroutineSuperClass(String str) {
        str.getClass();
        FqName fqName = StandardNames.COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME;
        return Intrinsics.areEqual(identifiedChild(fqName, "ContinuationImpl"), str) || Intrinsics.areEqual(identifiedChild(fqName, "RestrictedContinuationImpl"), str) || Intrinsics.areEqual(identifiedChild(fqName, "SuspendLambda"), str) || Intrinsics.areEqual(identifiedChild(fqName, "RestrictedSuspendLambda"), str);
    }

    public static final void loadCoroutineSuspendedMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        instructionAdapter.invokestatic(coroutinesIntrinsicsFileFacadeInternalName.getInternalName(), "get" + StandardNames.COROUTINE_SUSPENDED_NAME, Type.getMethodDescriptor(AsmTypes.OBJECT_TYPE, new Type[0]), false);
    }
}
