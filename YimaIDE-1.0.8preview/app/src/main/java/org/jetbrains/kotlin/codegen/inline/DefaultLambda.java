package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.DefaultLambda;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 &2\u00020\u0001:\u0001&B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0014\u0010\u001e\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/DefaultLambda;", "Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "info", "Lorg/jetbrains/kotlin/codegen/inline/ExtractedDefaultLambda;", "sourceCompiler", "Lorg/jetbrains/kotlin/codegen/inline/SourceCompilerForInline;", "functionName", Argument.Delimiters.none, "nullableAnyType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/ExtractedDefaultLambda;Lorg/jetbrains/kotlin/codegen/inline/SourceCompilerForInline;Ljava/lang/String;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)V", "isBoundCallableReference", Argument.Delimiters.none, "()Z", "lambdaClassType", "Lorg/jetbrains/org/objectweb/asm/Type;", "getLambdaClassType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "capturedVars", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", "getCapturedVars", "()Ljava/util/List;", "invokeMethod", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "getInvokeMethod", "()Lorg/jetbrains/org/objectweb/asm/commons/Method;", "invokeMethodParameters", "getInvokeMethodParameters", "invokeMethodReturnType", "getInvokeMethodReturnType", "()Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "originalBoundReceiverType", "getOriginalBoundReceiverType", "createNodeWithFakeVariables", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "originNode", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DefaultLambda extends LambdaInfo {
    private static final Companion Companion = new Companion(null);
    private static final HashSet<String> PROPERTY_REFERENCE_SUPER_CLASSES;
    private final List<CapturedParamDesc> capturedVars;
    private final String functionName;
    private final boolean isBoundCallableReference;
    private final Type lambdaClassType;
    private final KotlinTypeMarker nullableAnyType;
    private final Type originalBoundReceiverType;

    static {
        List listListOf = CollectionsKt.listOf(new Type[]{AsmTypes.PROPERTY_REFERENCE0, AsmTypes.PROPERTY_REFERENCE1, AsmTypes.PROPERTY_REFERENCE2, AsmTypes.MUTABLE_PROPERTY_REFERENCE0, AsmTypes.MUTABLE_PROPERTY_REFERENCE1, AsmTypes.MUTABLE_PROPERTY_REFERENCE2});
        List list = AsmTypes.OPTIMIZED_PROPERTY_REFERENCE_SUPERTYPES;
        list.getClass();
        List listPlus = CollectionsKt.plus(listListOf, list);
        HashSet<String> hashSet = new HashSet<>();
        Iterator it = listPlus.iterator();
        while (it.hasNext()) {
            hashSet.add(((Type) it.next()).getInternalName());
        }
        PROPERTY_REFERENCE_SUPER_CLASSES = hashSet;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00ca  */
    public DefaultLambda(ExtractedDefaultLambda extractedDefaultLambda, final SourceCompilerForInline sourceCompilerForInline, String str, KotlinTypeMarker kotlinTypeMarker) {
        List<CapturedParamDesc> listEmptyList;
        Sequence<FieldInsnNode> sequenceFindCapturedFieldAssignmentInstructions;
        Sequence map;
        extractedDefaultLambda.getClass();
        sourceCompilerForInline.getClass();
        str.getClass();
        kotlinTypeMarker.getClass();
        this.functionName = str;
        this.nullableAnyType = kotlinTypeMarker;
        this.lambdaClassType = extractedDefaultLambda.getType();
        InlineCache inlineCache = sourceCompilerForInline.getState().getInlineCache();
        String internalName = getLambdaClassType().getInternalName();
        internalName.getClass();
        byte[] bArrComputeClassBytes = inlineCache.computeClassBytes(internalName, new Function0() { // from class: lh3
            public final Object invoke() {
                return DefaultLambda.a(sourceCompilerForInline, this);
            }
        });
        String superName = new ClassReader(bArrComputeClassBytes).getSuperName();
        superName.getClass();
        Type type = null;
        if (CoroutineCodegenUtilKt.isCoroutineSuperClass(superName)) {
            wec.a("suspend default lambda ", getLambdaClassType().getInternalName(), " cannot be inlined; use a function reference instead");
            throw null;
        }
        SMAPAndMethodNode methodNode = InlineCodegenUtilsKt.getMethodNode(bArrComputeClassBytes, getLambdaClassType(), new Method("<init>", Type.VOID_TYPE, extractedDefaultLambda.getCapturedArgs()));
        MethodNode node = methodNode != null ? methodNode.getNode() : null;
        if (node == null) {
            int length = extractedDefaultLambda.getCapturedArgs().length;
        }
        boolean zContains = PROPERTY_REFERENCE_SUPER_CLASSES.contains(superName);
        boolean z = zContains || Intrinsics.areEqual(superName, AsmTypes.FUNCTION_REFERENCE.getInternalName()) || Intrinsics.areEqual(superName, AsmTypes.FUNCTION_REFERENCE_IMPL.getInternalName());
        Type type2 = (Type) ArraysKt.singleOrNull(extractedDefaultLambda.getCapturedArgs());
        if (type2 != null && z && AsmUtil.isPrimitive(type2)) {
            type = type2;
        }
        this.originalBoundReceiverType = type;
        if (z) {
            if (((Type) ArraysKt.singleOrNull(extractedDefaultLambda.getCapturedArgs())) != null) {
                LambdaInfo.Companion companion = LambdaInfo.INSTANCE;
                Type type3 = AsmTypes.OBJECT_TYPE;
                type3.getClass();
                listEmptyList = CollectionsKt.listOf(companion.capturedParamDesc(this, "$receiver", type3, false));
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
        } else if (node == null || (sequenceFindCapturedFieldAssignmentInstructions = MethodInlinerUtilKt.findCapturedFieldAssignmentInstructions(node)) == null || (map = SequencesKt.map(sequenceFindCapturedFieldAssignmentInstructions, new Function1() { // from class: mh3
            public final Object invoke(Object obj) {
                return DefaultLambda.b(this.b, (FieldInsnNode) obj);
            }
        })) == null || (listEmptyList = SequencesKt.toList(map)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        this.capturedVars = listEmptyList;
        this.isBoundCallableReference = z && !getCapturedVars().isEmpty();
        SMAPAndMethodNode sMAPAndMethodNodeLoadDefaultLambdaBody = DefaultMethodUtilKt.loadDefaultLambdaBody(bArrComputeClassBytes, getLambdaClassType(), zContains);
        setNode(new SMAPAndMethodNode(createNodeWithFakeVariables(sMAPAndMethodNodeLoadDefaultLambdaBody.getNode()), sMAPAndMethodNodeLoadDefaultLambdaBody.getClassSMAP()));
    }

    public static byte[] a(SourceCompilerForInline sourceCompilerForInline, DefaultLambda defaultLambda) {
        GenerationState state = sourceCompilerForInline.getState();
        String internalName = defaultLambda.getLambdaClassType().getInternalName();
        internalName.getClass();
        return InlineCodegenUtilsKt.loadClassBytesByInternalName(state, internalName);
    }

    public static CapturedParamDesc b(DefaultLambda defaultLambda, FieldInsnNode fieldInsnNode) {
        fieldInsnNode.getClass();
        LambdaInfo.Companion companion = LambdaInfo.INSTANCE;
        String str = fieldInsnNode.name;
        str.getClass();
        Type type = Type.getType(fieldInsnNode.desc);
        type.getClass();
        return companion.capturedParamDesc(defaultLambda, str, type, false);
    }

    private final MethodNode createNodeWithFakeVariables(MethodNode originNode) {
        int i = originNode.access;
        String str = originNode.name;
        String str2 = originNode.desc;
        String str3 = originNode.signature;
        List list = originNode.exceptions;
        MethodNode methodNode = new MethodNode(i, str, str2, str3, list != null ? (String[]) list.toArray(new String[0]) : null);
        int i2 = originNode.maxLocals;
        methodNode.instructions.add(new LdcInsnNode(0));
        methodNode.instructions.add(new VarInsnNode(54, i2));
        LabelNode labelNode = new LabelNode();
        methodNode.instructions.add(labelNode);
        originNode.accept(methodNode);
        LabelNode last = methodNode.instructions.getLast();
        LabelNode labelNode2 = last instanceof LabelNode ? last : null;
        if (labelNode2 == null) {
            labelNode2 = new LabelNode();
            methodNode.instructions.add(labelNode2);
        }
        LabelNode labelNode3 = labelNode2;
        List list2 = methodNode.localVariables;
        StringBuilder sb = new StringBuilder("$i$a$-");
        sb.append(this.functionName);
        sb.append('-');
        String internalName = getLambdaClassType().getInternalName();
        internalName.getClass();
        sb.append(StringsKt.substringAfterLast$default(internalName, '/', (String) null, 2, (Object) null));
        list2.add(new LocalVariableNode(sb.toString(), Type.INT_TYPE.getDescriptor(), null, labelNode, labelNode3, i2));
        methodNode.maxLocals++;
        return methodNode;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.LambdaInfo
    public List<CapturedParamDesc> getCapturedVars() {
        return this.capturedVars;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.LambdaInfo
    public Method getInvokeMethod() {
        return new Method(getNode().getNode().name, getNode().getNode().desc);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.LambdaInfo
    public List<KotlinTypeMarker> getInvokeMethodParameters() {
        int length = getInvokeMethod().getArgumentTypes().length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(this.nullableAnyType);
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.LambdaInfo
    /* JADX INFO: renamed from: getInvokeMethodReturnType, reason: from getter */
    public KotlinTypeMarker getNullableAnyType() {
        return this.nullableAnyType;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.LambdaInfo
    public Type getLambdaClassType() {
        return this.lambdaClassType;
    }

    public final Type getOriginalBoundReceiverType() {
        return this.originalBoundReceiverType;
    }

    /* JADX INFO: renamed from: isBoundCallableReference, reason: from getter */
    public final boolean getIsBoundCallableReference() {
        return this.isBoundCallableReference;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R1\u0010\u0004\u001a\"\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u00060\u0005j\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/DefaultLambda$Companion;", Argument.Delimiters.none, "<init>", "()V", "PROPERTY_REFERENCE_SUPER_CLASSES", "Ljava/util/HashSet;", Argument.Delimiters.none, "kotlin.jvm.PlatformType", "Lkotlin/collections/HashSet;", "getPROPERTY_REFERENCE_SUPER_CLASSES", "()Ljava/util/HashSet;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HashSet<String> getPROPERTY_REFERENCE_SUPER_CLASSES() {
            return DefaultLambda.PROPERTY_REFERENCE_SUPER_CLASSES;
        }

        private Companion() {
        }
    }
}
