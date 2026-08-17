package org.jetbrains.kotlin.codegen.inline;

import com.intellij.util.ArrayUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.AssertCodegenUtilKt;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.codegen.WriteKotlinMetadataKt;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.AnonymousObjectTransformer;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformer;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformerKt;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.load.kotlin.FileBasedKotlinClass;
import org.jetbrains.kotlin.load.kotlin.header.KotlinClassHeader;
import org.jetbrains.kotlin.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmNameResolver;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;
import org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.kotlin.util.MetadataHelpersKt;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;
import org.jetbrains.org.objectweb.asm.tree.AnnotationNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InnerClassNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001e\u0010$\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0018\u00010%2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0002J0\u0010)\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0007H\u0002J0\u00100\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+2\u0006\u00101\u001a\u00020\u00142\u0006\u00102\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0007H\u0002J \u00103\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"2\u0006\u00104\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u00105\u001a\u0002062\u0006\u00102\u001a\u00020.2\u0006\u00101\u001a\u00020\u0014H\u0002J\u0018\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\"2\u0006\u0010:\u001a\u00020\u0014H\u0002J0\u0010;\u001a\u00020\u001e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010<\u001a\u00020.2\u0006\u0010=\u001a\u00020.2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u001cH\u0002J\u0010\u0010?\u001a\u00020\t2\u0006\u0010@\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u000f\u001a*\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00110\u0010j\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0011`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/AnonymousObjectTransformer;", "Lorg/jetbrains/kotlin/codegen/inline/ObjectTransformer;", "Lorg/jetbrains/kotlin/codegen/inline/AnonymousObjectTransformationInfo;", "transformationInfo", "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "isSameModule", Argument.Delimiters.none, "continuationClassName", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/AnonymousObjectTransformationInfo;Lorg/jetbrains/kotlin/codegen/inline/InliningContext;ZLjava/lang/String;)V", "oldObjectType", "Lorg/jetbrains/org/objectweb/asm/Type;", "kotlin.jvm.PlatformType", "fieldNames", "Ljava/util/HashMap;", Argument.Delimiters.none, "Lkotlin/collections/HashMap;", "constructor", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "sourceMap", "Lorg/jetbrains/kotlin/codegen/inline/SMAP;", "sourceMapper", "Lorg/jetbrains/kotlin/codegen/inline/SourceMapper;", "doTransform", "Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "parentRemapper", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "writeTransformedMetadata", Argument.Delimiters.none, "header", "Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader;", "classBuilder", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "isPublicAbi", "transformMetadata", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "Lorg/jetbrains/kotlin/metadata/jvm/serialization/JvmStringTable;", "writeOuterInfo", "inlineMethodAndUpdateGlobalResult", "deferringVisitor", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "next", "allCapturedParamBuilder", "Lorg/jetbrains/kotlin/codegen/inline/ParametersBuilder;", "isConstructor", "inlineMethod", "sourceNode", "capturedBuilder", "generateConstructorAndFields", "constructorInlineBuilder", "getMethodParametersWithCaptured", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "newMethod", "Lorg/jetbrains/kotlin/codegen/inline/DeferredMethodVisitor;", "builder", "original", "extractParametersMappingAndPatchConstructor", "capturedParamBuilder", "constructorParamBuilder", "parentFieldRemapper", "addUniqueField", ModuleXmlParser.NAME, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnonymousObjectTransformer extends ObjectTransformer<AnonymousObjectTransformationInfo> {
    private MethodNode constructor;
    private final String continuationClassName;
    private final HashMap<String, List<String>> fieldNames;
    private final InliningContext inliningContext;
    private final boolean isSameModule;
    private final Type oldObjectType;
    private SMAP sourceMap;
    private SourceMapper sourceMapper;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KotlinClassHeader.Kind.values().length];
            try {
                iArr[KotlinClassHeader.Kind.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KotlinClassHeader.Kind.SYNTHETIC_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnonymousObjectTransformer(AnonymousObjectTransformationInfo anonymousObjectTransformationInfo, InliningContext inliningContext, boolean z, String str) {
        super(anonymousObjectTransformationInfo, inliningContext.getState());
        anonymousObjectTransformationInfo.getClass();
        inliningContext.getClass();
        this.inliningContext = inliningContext;
        this.isSameModule = z;
        this.continuationClassName = str;
        this.oldObjectType = Type.getObjectType(anonymousObjectTransformationInfo.getOldClassName());
        this.fieldNames = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String addUniqueField(String name) {
        String str;
        HashMap<String, List<String>> map = this.fieldNames;
        List<String> linkedList = map.get(name);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            map.put(name, linkedList);
        }
        List<String> list = linkedList;
        if (list.isEmpty()) {
            str = Argument.Delimiters.none;
        } else {
            str = InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX + list.size();
        }
        String str2 = name + str;
        list.add(str2);
        return str2;
    }

    public static String c(AnonymousObjectTransformer anonymousObjectTransformer) {
        return "Transformer for " + ((AnonymousObjectTransformationInfo) anonymousObjectTransformer.transformationInfo).getOldClassName();
    }

    public static MethodVisitor d(ClassBuilder classBuilder, MethodNode methodNode) {
        MethodVisitor methodVisitorNewMethod = classBuilder.newMethod(JvmDeclarationOrigin.NO_ORIGIN, methodNode.access, methodNode.name, methodNode.desc, methodNode.signature, ArrayUtil.toStringArray(methodNode.exceptions));
        methodVisitorNewMethod.getClass();
        return methodVisitorNewMethod;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean doTransform$lambda$2$0(String str, InnerClassNode innerClassNode) {
        innerClassNode.getClass();
        return Intrinsics.areEqual(innerClassNode.name, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean doTransform$lambda$2$1(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static Unit e(AnonymousObjectTransformer anonymousObjectTransformer, KotlinClassHeader kotlinClassHeader, AnnotationVisitor annotationVisitor) {
        annotationVisitor.getClass();
        Pair<MessageLite, JvmStringTable> pairTransformMetadata = anonymousObjectTransformer.transformMetadata(kotlinClassHeader);
        if (pairTransformMetadata != null) {
            MessageLite messageLite = (MessageLite) pairTransformMetadata.component1();
            JvmStringTable jvmStringTable = (JvmStringTable) pairTransformMetadata.component2();
            AsmUtil.writeAnnotationData(annotationVisitor, JvmProtoBufUtil.writeData(messageLite, jvmStringTable), ArrayUtil.toStringArray(jvmStringTable.getStrings()));
            return Unit.INSTANCE;
        }
        String[] data = kotlinClassHeader.getData();
        String[] strings = kotlinClassHeader.getStrings();
        if (data != null && strings != null) {
            AsmUtil.writeAnnotationData(annotationVisitor, data, strings);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00d1 A[EDGE_INSN: B:36:0x00d1->B:37:0x00d2 BREAK  A[LOOP:1: B:21:0x007b->B:104:0x007b]] */
    /* JADX WARN: Code duplicated, block: B:73:0x0219  */
    /* JADX WARN: Code duplicated, block: B:7:0x0026  */
    private final void extractParametersMappingAndPatchConstructor(MethodNode constructor, ParametersBuilder capturedParamBuilder, ParametersBuilder constructorParamBuilder, AnonymousObjectTransformationInfo transformationInfo, FieldRemapper parentFieldRemapper) {
        boolean z;
        Type[] argumentTypes;
        boolean z2;
        String strAddUniqueField;
        AnonymousObjectTransformer anonymousObjectTransformer = this;
        ParametersBuilder parametersBuilder = constructorParamBuilder;
        HashMap map = new HashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Map<Integer, FunctionalArgument> functionalArguments = transformationInfo.getFunctionalArguments();
        if (parentFieldRemapper instanceof InlinedLambdaRemapper) {
            FieldRemapper fieldRemapper = parentFieldRemapper.parent;
            fieldRemapper.getClass();
            if (fieldRemapper.getIsRoot()) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        ArrayList arrayList = new ArrayList();
        for (FieldInsnNode fieldInsnNode : MethodInlinerUtilKt.findCapturedFieldAssignmentInstructions(constructor)) {
            String str = fieldInsnNode.name;
            VarInsnNode previous = fieldInsnNode.getPrevious();
            previous.getClass();
            VarInsnNode varInsnNode = previous;
            int i = varInsnNode.var;
            FunctionalArgument functionalArgument = functionalArguments.get(Integer.valueOf(i));
            if (z) {
                strAddUniqueField = str;
                break;
            }
            str.getClass();
            if (!InlineCodegenUtilsKt.isThis0(str)) {
                strAddUniqueField = str;
                break;
            }
            Collection<FunctionalArgument> collectionValues = functionalArguments.values();
            if ((collectionValues instanceof Collection) && collectionValues.isEmpty()) {
                strAddUniqueField = str;
                break;
            }
            Iterator<T> it = collectionValues.iterator();
            while (true) {
                if (!it.hasNext()) {
                    strAddUniqueField = str;
                    break;
                }
                FunctionalArgument functionalArgument2 = (FunctionalArgument) it.next();
                if (functionalArgument2 instanceof LambdaInfo) {
                    List<CapturedParamDesc> capturedVars = ((LambdaInfo) functionalArgument2).getCapturedVars();
                    if (!(capturedVars instanceof Collection) || !capturedVars.isEmpty()) {
                        Iterator<T> it2 = capturedVars.iterator();
                        while (it2.hasNext()) {
                            if (Intrinsics.areEqual(((CapturedParamDesc) it2.next()).getFieldName(), str)) {
                                strAddUniqueField = anonymousObjectTransformer.addUniqueField(str + InlineCodegenUtilsKt.INLINE_FUN_THIS_0_SUFFIX);
                                break;
                            }
                        }
                    }
                }
            }
            Type objectType = Type.getObjectType(transformationInfo.getOldClassName());
            objectType.getClass();
            str.getClass();
            strAddUniqueField.getClass();
            Type type = Type.getType(fieldInsnNode.desc);
            type.getClass();
            ArrayList arrayList2 = arrayList;
            boolean z3 = functionalArgument instanceof LambdaInfo;
            CapturedParamInfo capturedParamInfoAddCapturedParam = capturedParamBuilder.addCapturedParam(objectType, str, strAddUniqueField, type, z3, null);
            capturedParamInfoAddCapturedParam.setFunctionalArgument(functionalArgument);
            if (z3) {
                linkedHashSet.add(functionalArgument);
            }
            map.put(Integer.valueOf(i), capturedParamInfoAddCapturedParam);
            arrayList2.add(varInsnNode.getPrevious());
            arrayList2.add(varInsnNode);
            arrayList2.add(fieldInsnNode);
            arrayList = arrayList2;
        }
        MethodInlinerUtilKt.remove(constructor, arrayList);
        Type type2 = anonymousObjectTransformer.oldObjectType;
        type2.getClass();
        parametersBuilder.addThis(type2, false);
        String constructorDesc = transformationInfo.getConstructorDesc();
        if (constructorDesc == null || (argumentTypes = Type.getArgumentTypes(constructorDesc)) == null) {
            argumentTypes = new Type[0];
        }
        Type[] typeArr = argumentTypes;
        int length = typeArr.length;
        int i2 = 0;
        while (i2 < length) {
            Type type3 = typeArr[i2];
            FunctionalArgument functionalArgument3 = functionalArguments.get(Integer.valueOf(parametersBuilder.getNextParameterOffset()));
            CapturedParamInfo capturedParamInfo = (CapturedParamInfo) map.get(Integer.valueOf(parametersBuilder.getNextParameterOffset()));
            type3.getClass();
            boolean z4 = functionalArgument3 instanceof LambdaInfo;
            ParametersBuilder parametersBuilder2 = parametersBuilder;
            Type[] typeArr2 = typeArr;
            ParameterInfo parameterInfoAddNextParameter$default = ParametersBuilder.addNextParameter$default(parametersBuilder2, type3, z4, null, 4, null);
            parameterInfoAddNextParameter$default.setFunctionalArgument(functionalArgument3);
            parameterInfoAddNextParameter$default.setFieldEquivalent(capturedParamInfo);
            if (z4 && parameterInfoAddNextParameter$default.getFieldEquivalent() == null) {
                linkedHashSet.add(functionalArgument3);
            }
            i2++;
            parametersBuilder = parametersBuilder2;
            typeArr = typeArr2;
        }
        ParametersBuilder parametersBuilder3 = parametersBuilder;
        ArrayList arrayList3 = new ArrayList();
        if (!z) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Iterator it3 = linkedHashSet.iterator();
            it3.getClass();
            while (it3.hasNext()) {
                Object next = it3.next();
                next.getClass();
                for (CapturedParamDesc capturedParamDesc : ((LambdaInfo) next).getCapturedVars()) {
                    String fieldName = InlineCodegenUtilsKt.isThis0(capturedParamDesc.getFieldName()) ? capturedParamDesc.getFieldName() : anonymousObjectTransformer.addUniqueField(capturedParamDesc.getFieldName() + InlineCodegenUtilsKt.INLINE_TRANSFORMATION_SUFFIX);
                    if (InlineCodegenUtilsKt.isThis0(capturedParamDesc.getFieldName())) {
                        String className = capturedParamDesc.getType().getClassName();
                        className.getClass();
                        if (linkedHashSet2.add(className)) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                    } else {
                        z2 = false;
                    }
                    CapturedParamInfo capturedParamInfoAddCapturedParam2 = parametersBuilder3.addCapturedParam(capturedParamDesc, fieldName, z2);
                    if (capturedParamDesc.getIsSuspend()) {
                        capturedParamInfoAddCapturedParam2.setFunctionalArgument(NonInlineArgumentForInlineSuspendParameter.INLINE_LAMBDA_AS_VARIABLE);
                    }
                    capturedParamBuilder.addCapturedParam(capturedParamInfoAddCapturedParam2, capturedParamInfoAddCapturedParam2.getNewFieldName()).setRemapValue(new StackValue.Field(capturedParamDesc.getType(), anonymousObjectTransformer.oldObjectType, capturedParamInfoAddCapturedParam2.getNewFieldName(), new StackValue.Local(0, AsmTypes.OBJECT_TYPE, null)));
                    arrayList3.add(capturedParamDesc);
                    anonymousObjectTransformer = this;
                    linkedHashSet2 = linkedHashSet2;
                }
                anonymousObjectTransformer = this;
            }
        } else if (!linkedHashSet.isEmpty()) {
            FieldRemapper fieldRemapper2 = parentFieldRemapper.parent;
            RegeneratedLambdaFieldRemapper regeneratedLambdaFieldRemapper = fieldRemapper2 instanceof RegeneratedLambdaFieldRemapper ? (RegeneratedLambdaFieldRemapper) fieldRemapper2 : null;
            if (regeneratedLambdaFieldRemapper == null) {
                pe1.a("Expecting RegeneratedLambdaFieldRemapper, but ", parentFieldRemapper.parent);
                return;
            }
            Type objectType2 = Type.getObjectType(regeneratedLambdaFieldRemapper.getOriginalLambdaInternalName());
            objectType2.getClass();
            CapturedParamDesc capturedParamDesc2 = new CapturedParamDesc(objectType2, "this", objectType2, false, 8, null);
            CapturedParamInfo capturedParamInfoAddCapturedParam3 = parametersBuilder3.addCapturedParam(capturedParamDesc2, "this$0", false);
            capturedParamBuilder.addCapturedParam(capturedParamInfoAddCapturedParam3, capturedParamInfoAddCapturedParam3.getNewFieldName()).setRemapValue(new StackValue.Local(0, AsmTypes.OBJECT_TYPE, null));
            arrayList3.add(capturedParamDesc2);
        }
        transformationInfo.setAllRecapturedParameters(arrayList3);
        Map<String, ? extends LambdaInfo> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(linkedHashSet, 10)), 16));
        for (Object obj : linkedHashSet) {
            String internalName = ((LambdaInfo) obj).getLambdaClassType().getInternalName();
            internalName.getClass();
            linkedHashMap.put(internalName, obj);
        }
        transformationInfo.setCapturedLambdasToInline(linkedHashMap);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final void generateConstructorAndFields(ClassBuilder classBuilder, ParametersBuilder constructorInlineBuilder, FieldRemapper parentRemapper) throws UninitializedPropertyAccessException {
        int size;
        ParameterInfo parameterInfo;
        Parameters parametersBuildParameters = constructorInlineBuilder.buildParameters();
        ArrayList arrayList = new ArrayList();
        for (ParameterInfo parameterInfo2 : parametersBuildParameters) {
            if (!parameterInfo2.getIsSkipped()) {
                arrayList.add(parameterInfo2);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ParameterInfo) it.next()).getType());
        }
        List listDrop = CollectionsKt.drop(arrayList2, 1);
        Type type = Type.VOID_TYPE;
        Type[] typeArr = (Type[]) listDrop.toArray(new Type[0]);
        String methodDescriptor = Type.getMethodDescriptor(type, (Type[]) Arrays.copyOf(typeArr, typeArr.length));
        AnonymousObjectTransformationInfo anonymousObjectTransformationInfo = (AnonymousObjectTransformationInfo) this.transformationInfo;
        methodDescriptor.getClass();
        anonymousObjectTransformationInfo.setNewConstructorDescriptor(methodDescriptor);
        JvmDeclarationOrigin jvmDeclarationOrigin = JvmDeclarationOrigin.NO_ORIGIN;
        MethodNode methodNode = this.constructor;
        methodNode.getClass();
        MethodVisitor methodVisitorNewMethod = classBuilder.newMethod(jvmDeclarationOrigin, methodNode.access, "<init>", methodDescriptor, null, ArrayUtil.EMPTY_STRING_ARRAY);
        methodVisitorNewMethod.getClass();
        final Label label = new Label();
        methodVisitorNewMethod.visitLabel(label);
        Iterator<ParameterInfo> it2 = parametersBuildParameters.iterator();
        int i = 0;
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            ParameterInfo next = it2.next();
            if (next.getIsSkipped()) {
                size = i;
                i = -1;
            } else {
                size = next.getType().getSize() + i;
            }
            CapturedParamInfo fieldEquivalent = next.getFieldEquivalent();
            if (fieldEquivalent != null) {
                constructorInlineBuilder.addCapturedParam(fieldEquivalent, fieldEquivalent.getNewFieldName()).setRemapValue(i != -1 ? new StackValue.Local(i, next.getType(), null) : null);
                parameterInfo = fieldEquivalent;
            } else {
                parameterInfo = next;
            }
            if (!next.getIsSkipped() && (parameterInfo instanceof CapturedParamInfo)) {
                CapturedParamInfo capturedParamInfo = (CapturedParamInfo) parameterInfo;
                if (!capturedParamInfo.getIsSkipInConstructor()) {
                    String descriptor = parameterInfo.getType().getDescriptor();
                    classBuilder.newField(JvmDeclarationOrigin.NO_ORIGIN, 4112, capturedParamInfo.getNewFieldName(), descriptor, null, null);
                    methodVisitorNewMethod.visitVarInsn(25, 0);
                    methodVisitorNewMethod.visitVarInsn(parameterInfo.getType().getOpcode(21), i);
                    methodVisitorNewMethod.visitFieldInsn(181, ((AnonymousObjectTransformationInfo) this.transformationInfo).getNewClassName(), capturedParamInfo.getNewFieldName(), descriptor);
                }
            }
            i = size;
        }
        MethodNode methodNode2 = this.constructor;
        methodNode2.getClass();
        MethodNode methodNode3 = new MethodNode(methodNode2.access, "<init>", methodDescriptor, (String) null, ArrayUtil.EMPTY_STRING_ARRAY);
        MethodNode methodNode4 = this.constructor;
        methodNode4.getClass();
        inlineMethodAndUpdateGlobalResult(parentRemapper, methodNode3, methodNode4, constructorInlineBuilder, true);
        InlineCodegenUtilsKt.removeFinallyMarkers(methodNode3);
        LabelNode first = methodNode3.instructions.getFirst();
        LabelNode labelNode = first instanceof LabelNode ? first : null;
        final StackValue.Local label2 = labelNode != null ? labelNode.getLabel() : null;
        methodNode3.accept(new MethodBodyVisitor(methodVisitorNewMethod) { // from class: org.jetbrains.kotlin.codegen.inline.AnonymousObjectTransformer.generateConstructorAndFields.1
            public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                name.getClass();
                desc.getClass();
                start.getClass();
                end.getClass();
                if (label2 == start) {
                    start = label;
                }
                super/*org.jetbrains.org.objectweb.asm.MethodVisitor*/.visitLocalVariable(name, desc, signature, start, end, index);
            }
        });
        methodVisitorNewMethod.visitEnd();
    }

    private final Parameters getMethodParametersWithCaptured(ParametersBuilder capturedBuilder, MethodNode sourceNode) {
        ParametersBuilder parametersBuilderNewBuilder = ParametersBuilder.INSTANCE.newBuilder();
        if ((sourceNode.access & 8) == 0) {
            Type type = this.oldObjectType;
            type.getClass();
            parametersBuilderNewBuilder.addThis(type, false);
        }
        Type[] argumentTypes = Type.getArgumentTypes(sourceNode.desc);
        argumentTypes.getClass();
        for (Type type2 : argumentTypes) {
            type2.getClass();
            ParametersBuilder.addNextParameter$default(parametersBuilderNewBuilder, type2, false, null, 4, null);
        }
        Iterator<CapturedParamInfo> it = capturedBuilder.listCaptured().iterator();
        while (it.hasNext()) {
            parametersBuilderNewBuilder.addCapturedParamCopy(it.next());
        }
        return parametersBuilderNewBuilder.buildParameters();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final InlineResult inlineMethod(FieldRemapper parentRemapper, MethodVisitor deferringVisitor, MethodNode sourceNode, ParametersBuilder capturedBuilder, boolean isConstructor) throws UninitializedPropertyAccessException {
        Parameters parametersBuildParameters = isConstructor ? capturedBuilder.buildParameters() : getMethodParametersWithCaptured(capturedBuilder, sourceNode);
        String internalName = this.oldObjectType.getInternalName();
        internalName.getClass();
        RegeneratedLambdaFieldRemapper regeneratedLambdaFieldRemapper = new RegeneratedLambdaFieldRemapper(internalName, ((AnonymousObjectTransformationInfo) this.transformationInfo).getNewClassName(), parametersBuildParameters, ((AnonymousObjectTransformationInfo) this.transformationInfo).getCapturedLambdasToInline(), parentRemapper, isConstructor);
        ReifiedTypeParametersUsages reifiedTypeParametersUsagesReifyInstructions = this.inliningContext.getShouldReifyTypeParametersInObjects() ? this.inliningContext.getRoot().getInlineMethodReifier().reifyInstructions(sourceNode) : null;
        InliningContext inliningContextSubInline$default = InliningContext.subInline$default(this.inliningContext, ((AnonymousObjectTransformationInfo) this.transformationInfo).getNameGenerator(), null, null, false, getState().getConfiguration().getBoolean(JVMConfigurationKeys.USE_INLINE_SCOPES_NUMBERS) ? new InlineScopesGenerator() : null, 14, null);
        boolean z = this.isSameModule;
        Function0 function0 = new Function0() { // from class: pa0
            public final Object invoke() {
                return AnonymousObjectTransformer.c(this.b);
            }
        };
        SourceMapper sourceMapper = this.sourceMapper;
        if (sourceMapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sourceMapper");
            sourceMapper = null;
        }
        SMAP smap = this.sourceMap;
        if (smap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sourceMap");
            smap = null;
        }
        InlineResult inlineResultDoInline = new MethodInliner(sourceNode, parametersBuildParameters, inliningContextSubInline$default, regeneratedLambdaFieldRemapper, z, function0, new SourceMapCopier(sourceMapper, smap, null, 4, null), new InlineCallSiteInfo(((AnonymousObjectTransformationInfo) this.transformationInfo).getOldClassName(), new Method(sourceNode.name, isConstructor ? ((AnonymousObjectTransformationInfo) this.transformationInfo).getNewConstructorDescriptor() : sourceNode.desc), this.inliningContext.getCallSiteInfo().getSuppressNonPublicApiObjectInliningError(), this.inliningContext.getCallSiteInfo().getInlineScopeVisibility(), this.inliningContext.getCallSiteInfo().getFile(), this.inliningContext.getCallSiteInfo().getLineNumber()), false, false, 0, 0, 3584, null).doInline(deferringVisitor, new LocalVarRemapper(parametersBuildParameters, 0), false, MapsKt.emptyMap());
        if (reifiedTypeParametersUsagesReifyInstructions != null) {
            inlineResultDoInline.getReifiedTypeParametersUsages().mergeAll(reifiedTypeParametersUsagesReifyInstructions);
        }
        deferringVisitor.visitMaxs(-1, -1);
        return inlineResultDoInline;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final InlineResult inlineMethodAndUpdateGlobalResult(FieldRemapper parentRemapper, MethodVisitor deferringVisitor, MethodNode next, ParametersBuilder allCapturedParamBuilder, boolean isConstructor) throws UninitializedPropertyAccessException {
        InlineResult inlineResultInlineMethod = inlineMethod(parentRemapper, deferringVisitor, next, allCapturedParamBuilder, isConstructor);
        this.transformationResult.merge(inlineResultInlineMethod);
        this.transformationResult.getReifiedTypeParametersUsages().mergeAll(inlineResultInlineMethod.getReifiedTypeParametersUsages());
        return inlineResultInlineMethod;
    }

    private final boolean isPublicAbi(KotlinClassHeader header) {
        return (header.getMetadataVersion().isAtLeast(1, 6, 0) && (header.getExtraInt() & 128) == 0) ? false : true;
    }

    private final DeferredMethodVisitor newMethod(final ClassBuilder builder, final MethodNode original) {
        return new DeferredMethodVisitor(new MethodNode(original.access, original.name, original.desc, original.signature, ArrayUtil.toStringArray(original.exceptions)), new Function0() { // from class: qa0
            public final Object invoke() {
                return AnonymousObjectTransformer.d(builder, original);
            }
        });
    }

    private final Pair<MessageLite, JvmStringTable> transformMetadata(KotlinClassHeader header) {
        String[] strings;
        String[] data = header.getData();
        if (data == null || (strings = header.getStrings()) == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[header.getKind().ordinal()];
        if (i == 1) {
            Pair classDataFrom = JvmProtoBufUtil.readClassDataFrom(data, strings);
            JvmNameResolver jvmNameResolver = (JvmNameResolver) classDataFrom.component1();
            ProtoBuf.Class r5 = (ProtoBuf.Class) classDataFrom.component2();
            JvmStringTable jvmStringTable = new JvmStringTable(jvmNameResolver);
            ProtoBuf.Class.Builder builder = r5.toBuilder();
            GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.anonymousObjectOriginName;
            String internalName = this.oldObjectType.getInternalName();
            internalName.getClass();
            builder.setExtension(generatedExtension, Integer.valueOf(jvmStringTable.getStringIndex(internalName)));
            return TuplesKt.to(builder.build(), jvmStringTable);
        }
        if (i != 2) {
            return null;
        }
        Pair functionDataFrom = JvmProtoBufUtil.readFunctionDataFrom(data, strings);
        JvmNameResolver jvmNameResolver2 = (JvmNameResolver) functionDataFrom.component1();
        ProtoBuf.Function function = (ProtoBuf.Function) functionDataFrom.component2();
        JvmStringTable jvmStringTable2 = new JvmStringTable(jvmNameResolver2);
        ProtoBuf.Function.Builder builder2 = function.toBuilder();
        GeneratedMessageLite.GeneratedExtension generatedExtension2 = JvmProtoBuf.lambdaClassOriginName;
        String internalName2 = this.oldObjectType.getInternalName();
        internalName2.getClass();
        builder2.setExtension(generatedExtension2, Integer.valueOf(jvmStringTable2.getStringIndex(internalName2)));
        return TuplesKt.to(builder2.build(), jvmStringTable2);
    }

    private final void writeOuterInfo(ClassBuilder classBuilder) {
        InlineCallSiteInfo callSiteInfo = this.inliningContext.getCallSiteInfo();
        String ownerClassName = callSiteInfo.getOwnerClassName();
        String name = callSiteInfo.getMethod().getName();
        name.getClass();
        classBuilder.visitOuterClass(ownerClassName, StringsKt.removeSuffix(name, CoroutineTransformerKt.FOR_INLINE_SUFFIX), callSiteInfo.getMethod().getDescriptor());
    }

    private final void writeTransformedMetadata(final KotlinClassHeader header, ClassBuilder classBuilder) {
        WriteKotlinMetadataKt.writeKotlinMetadata(classBuilder, getState().getConfig(), header.getKind(), this.inliningContext.getCallSiteInfo().isInPublicInlineScope(), header.getExtraInt() & (-129), new Function1() { // from class: oa0
            public final Object invoke(Object obj) {
                return AnonymousObjectTransformer.e(this.b, header, (AnnotationVisitor) obj);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.codegen.inline.ObjectTransformer
    public InlineResult doTransform(FieldRemapper parentRemapper) throws UninitializedPropertyAccessException {
        SMAP smapIdentityMapping;
        String str;
        ClassBuilder classBuilder;
        Object obj;
        DeferredMethodVisitor deferredMethodVisitorNewMethod;
        String internalName;
        String str2;
        parentRemapper.getClass();
        final ArrayList<InnerClassNode> arrayList = new ArrayList();
        final ClassBuilder classBuilderCreateRemappingClassBuilderViaFactory = createRemappingClassBuilderViaFactory(this.inliningContext);
        final ArrayList arrayList2 = new ArrayList();
        final ReadKotlinClassHeaderAnnotationVisitor readKotlinClassHeaderAnnotationVisitor = new ReadKotlinClassHeaderAnnotationVisitor();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
        createClassReader().accept(new ClassVisitor(classBuilderCreateRemappingClassBuilderViaFactory.getVisitor()) { // from class: org.jetbrains.kotlin.codegen.inline.AnonymousObjectTransformer.doTransform.1
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                name.getClass();
                superName.getClass();
                interfaces.getClass();
                classBuilderCreateRemappingClassBuilderViaFactory.defineClass(null, Math.max(version, this.getState().getConfig().getClassFileVersion()), access, name, signature, superName, interfaces);
                if (CoroutineCodegenUtilKt.isCoroutineSuperClass(superName)) {
                    this.inliningContext.setContinuation(true);
                }
                objectRef.element = superName;
            }

            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                desc.getClass();
                if (Intrinsics.areEqual(desc, JvmAnnotationNames.METADATA_DESC)) {
                    return FileBasedKotlinClass.convertAnnotationVisitor(readKotlinClassHeaderAnnotationVisitor, desc, new FileBasedKotlinClass.InnerClassesInfo());
                }
                if (Intrinsics.areEqual(desc, CoroutineCodegenUtilKt.getDEBUG_METADATA_ANNOTATION_ASM_TYPE().getDescriptor())) {
                    objectRef4.element = new AnnotationNode(desc);
                    return (AnnotationVisitor) objectRef4.element;
                }
                if (Intrinsics.areEqual(desc, "Lkotlin/jvm/internal/SourceDebugExtension;")) {
                    return null;
                }
                return classBuilderCreateRemappingClassBuilderViaFactory.newAnnotation(desc, visible);
            }

            public void visitEnd() {
            }

            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                name.getClass();
                desc.getClass();
                this.addUniqueField(name);
                if (InlineCodegenUtilsKt.isCapturedFieldName(name)) {
                    return null;
                }
                return classBuilderCreateRemappingClassBuilderViaFactory.newField(JvmDeclarationOrigin.NO_ORIGIN, access, name, desc, signature, value);
            }

            public void visitInnerClass(String name, String outerName, String innerName, int access) {
                name.getClass();
                arrayList.add(new InnerClassNode(name, outerName, innerName, access));
            }

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                name.getClass();
                desc.getClass();
                MethodNode methodNode = new MethodNode(access, name, desc, signature, exceptions);
                if (!Intrinsics.areEqual(name, "<init>")) {
                    arrayList2.add(methodNode);
                    return methodNode;
                }
                MethodNode methodNode2 = this.constructor;
                AnonymousObjectTransformer anonymousObjectTransformer = this;
                if (methodNode2 == null) {
                    anonymousObjectTransformer.constructor = methodNode;
                    return methodNode;
                }
                r0g.a("Lambda, SAM or anonymous object should have only one constructor.\nFirst:\n", InlineCodegenUtilsKt.getNodeText(anonymousObjectTransformer.constructor), "\n\nSecond:\n", InlineCodegenUtilsKt.getNodeText(methodNode), 10);
                return null;
            }

            public void visitSource(String source, String debug) {
                source.getClass();
                objectRef2.element = source;
                objectRef3.element = debug;
            }
        }, 4);
        KotlinClassHeader kotlinClassHeaderCreateHeader = readKotlinClassHeaderAnnotationVisitor.createHeader(MetadataHelpersKt.toJvmMetadataVersion(this.inliningContext.getState().getConfig().getLanguageVersionSettings().getLanguageVersion()));
        if (!this.isSameModule && (kotlinClassHeaderCreateHeader == null || !isPublicAbi(kotlinClassHeaderCreateHeader))) {
            this.inliningContext.getCallSiteInfo().getSuppressNonPublicApiObjectInliningError();
        }
        Object obj2 = objectRef3.element;
        if (this.inliningContext.isInliningLambda()) {
            obj2 = null;
        }
        String str3 = (String) obj2;
        if (str3 == null || (smapIdentityMapping = SMAPParser.INSTANCE.parseOrNull(str3)) == null) {
            SMAP.Companion companion = SMAP.INSTANCE;
            String str4 = (String) objectRef2.element;
            String internalName2 = this.oldObjectType.getInternalName();
            internalName2.getClass();
            smapIdentityMapping = companion.identityMapping(str4, internalName2, CollectionsKt.plus(arrayList2, CollectionsKt.listOfNotNull(this.constructor)));
        }
        this.sourceMap = smapIdentityMapping;
        String str5 = (String) objectRef2.element;
        SMAP smap = this.sourceMap;
        if (smap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sourceMap");
            smap = null;
        }
        this.sourceMapper = new SourceMapper(str5, smap);
        ParametersBuilder.Companion companion2 = ParametersBuilder.INSTANCE;
        ParametersBuilder parametersBuilderNewBuilder = companion2.newBuilder();
        ParametersBuilder parametersBuilderNewBuilder2 = companion2.newBuilder();
        MethodNode methodNode = this.constructor;
        methodNode.getClass();
        extractParametersMappingAndPatchConstructor(methodNode, parametersBuilderNewBuilder, parametersBuilderNewBuilder2, (AnonymousObjectTransformationInfo) this.transformationInfo, parentRemapper);
        ArrayList<DeferredMethodVisitor> arrayList3 = new ArrayList();
        generateConstructorAndFields(classBuilderCreateRemappingClassBuilderViaFactory, parametersBuilderNewBuilder2, parentRemapper);
        InliningContext inliningContext = this.inliningContext;
        Object obj3 = objectRef.element;
        if (obj3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("superClassName");
            str = null;
        } else {
            str = (String) obj3;
        }
        CoroutineTransformer coroutineTransformer = new CoroutineTransformer(inliningContext, classBuilderCreateRemappingClassBuilderViaFactory, arrayList2, str);
        Iterator it = arrayList2.iterator();
        it.getClass();
        boolean z = false;
        while (it.hasNext()) {
            Object next = it.next();
            next.getClass();
            MethodNode methodNode2 = (MethodNode) next;
            if (!coroutineTransformer.shouldSkip(methodNode2)) {
                if (coroutineTransformer.shouldGenerateStateMachine(methodNode2)) {
                    deferredMethodVisitorNewMethod = coroutineTransformer.newMethod(methodNode2);
                } else {
                    if (coroutineTransformer.suspendLambdaWithGeneratedStateMachine(methodNode2)) {
                        z = true;
                    }
                    deferredMethodVisitorNewMethod = newMethod(classBuilderCreateRemappingClassBuilderViaFactory, methodNode2);
                }
                boolean z2 = z;
                ParametersBuilder parametersBuilder = parametersBuilderNewBuilder;
                DeferredMethodVisitor deferredMethodVisitor = deferredMethodVisitorNewMethod;
                if (Intrinsics.areEqual(methodNode2.name, "<clinit>")) {
                    AssertCodegenUtilKt.rewriteAssertionsDisabledFieldInitialization(methodNode2, this.inliningContext.getRoot().getCallSiteInfo().getOwnerClassName());
                }
                CoroutineTransformer coroutineTransformer2 = coroutineTransformer;
                InlineResult inlineResultInlineMethodAndUpdateGlobalResult = inlineMethodAndUpdateGlobalResult(parentRemapper, deferredMethodVisitor, methodNode2, parametersBuilder, false);
                parametersBuilderNewBuilder = parametersBuilder;
                Type returnType = Type.getReturnType(methodNode2.desc);
                if (!AsmUtil.isPrimitive(returnType) && (str2 = inlineResultInlineMethodAndUpdateGlobalResult.getChangedTypes().get((internalName = returnType.getInternalName()))) != null) {
                    TypeRemapper typeRemapper = this.inliningContext.getTypeRemapper();
                    internalName.getClass();
                    typeRemapper.addAdditionalMappings(internalName, str2);
                }
                arrayList3.add(deferredMethodVisitor);
                z = z2;
                coroutineTransformer = coroutineTransformer2;
            }
        }
        CoroutineTransformer coroutineTransformer3 = coroutineTransformer;
        for (DeferredMethodVisitor deferredMethodVisitor2 : arrayList3) {
            String strFindFakeContinuationConstructorClassName = CoroutineTransformer.INSTANCE.findFakeContinuationConstructorClassName(deferredMethodVisitor2.getIntermediate());
            final String strOldContinuationFrom = coroutineTransformer3.oldContinuationFrom(deferredMethodVisitor2.getIntermediate());
            coroutineTransformer3.replaceFakesWithReals(deferredMethodVisitor2.getIntermediate());
            InlineCodegenUtilsKt.removeFinallyMarkers(deferredMethodVisitor2.getIntermediate());
            deferredMethodVisitor2.visitEnd();
            if (strFindFakeContinuationConstructorClassName != null && coroutineTransformer3.safeToRemoveContinuationClass(deferredMethodVisitor2.getIntermediate())) {
                this.transformationResult.addClassToRemove(strFindFakeContinuationConstructorClassName);
                final Function1 function1 = new Function1() { // from class: ma0
                    public final Object invoke(Object obj4) {
                        return Boolean.valueOf(AnonymousObjectTransformer.doTransform$lambda$2$0(strOldContinuationFrom, (InnerClassNode) obj4));
                    }
                };
                arrayList.removeIf(new Predicate() { // from class: na0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj4) {
                        return AnonymousObjectTransformer.doTransform$lambda$2$1(function1, obj4);
                    }
                });
            }
        }
        if (this.inliningContext.isInliningLambda()) {
            Object obj4 = objectRef2.element;
            if (obj4 != null) {
                classBuilderCreateRemappingClassBuilderViaFactory.visitSource((String) obj4, (String) objectRef3.element);
            }
        } else {
            SourceMapper sourceMapper = this.sourceMapper;
            if (sourceMapper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sourceMapper");
                sourceMapper = null;
            }
            classBuilderCreateRemappingClassBuilderViaFactory.visitSMAP(sourceMapper, !getState().getConfig().getLanguageVersionSettings().supportsFeature(LanguageFeature.CorrectSourceMappingSyntax), true);
        }
        for (InnerClassNode innerClassNode : arrayList) {
            classBuilderCreateRemappingClassBuilderViaFactory.visitInnerClass(innerClassNode.name, innerClassNode.outerName, innerClassNode.innerName, innerClassNode.access);
        }
        if (kotlinClassHeaderCreateHeader != null) {
            writeTransformedMetadata(kotlinClassHeaderCreateHeader, classBuilderCreateRemappingClassBuilderViaFactory);
        }
        if (z && (obj = objectRef4.element) != null) {
            ((AnnotationNode) objectRef4.element).accept(classBuilderCreateRemappingClassBuilderViaFactory.newAnnotation(((AnnotationNode) obj).desc, true));
        }
        writeOuterInfo(classBuilderCreateRemappingClassBuilderViaFactory);
        if (this.inliningContext.getGenerateAssertField()) {
            HashMap<String, List<String>> map = this.fieldNames;
            if (!map.isEmpty()) {
                Iterator<Map.Entry<String, List<String>>> it2 = map.entrySet().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (Intrinsics.areEqual(it2.next().getKey(), AssertCodegenUtilKt.ASSERTIONS_DISABLED_FIELD_NAME)) {
                            classBuilder = classBuilderCreateRemappingClassBuilderViaFactory;
                        }
                    }
                }
            }
            classBuilder = classBuilderCreateRemappingClassBuilderViaFactory;
            MethodVisitor methodVisitorNewMethod = classBuilder.newMethod(JvmDeclarationOrigin.NO_ORIGIN, 8, "<clinit>", "()V", null, null);
            methodVisitorNewMethod.getClass();
            AssertCodegenUtilKt.generateAssertionsDisabledFieldInitialization(classBuilder, methodVisitorNewMethod, this.inliningContext.getRoot().getCallSiteInfo().getOwnerClassName());
            methodVisitorNewMethod.visitInsn(177);
            methodVisitorNewMethod.visitEnd();
        } else {
            classBuilder = classBuilderCreateRemappingClassBuilderViaFactory;
        }
        if (Intrinsics.areEqual(this.continuationClassName, ((AnonymousObjectTransformationInfo) this.transformationInfo).getOldClassName())) {
            coroutineTransformer3.registerClassBuilder(this.continuationClassName);
        } else {
            classBuilder.done(getState().getConfig().getGenerateSmapCopyToAnnotation());
        }
        return this.transformationResult;
    }
}
