package org.jetbrains.kotlin.codegen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.ReificationArgument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeParametersUsages;
import org.jetbrains.kotlin.codegen.intrinsics.TypeIntrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.annotations.AnnotationUtilKt;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.TypeUtils;
import org.jetbrains.kotlin.types.checker.KotlinTypeChecker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000´\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a.\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n\u001a \u0010\f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0002\u001a\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f\u001a\n\u0010\u0012\u001a\u00020\n*\u00020\u0013\u001a\n\u0010\u0014\u001a\u00020\n*\u00020\u0013\u001a \u0010\u0015\u001a\u00020\n*\u00020\u00132\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\n0\u0017H\u0002\u001a\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b\u001a\n\u0010\u001c\u001a\u00020\n*\u00020\u001d\u001a\n\u0010\u001e\u001a\u00020\n*\u00020\u001d\u001a\n\u0010\u001f\u001a\u00020\n*\u00020 \u001a\n\u0010%\u001a\u00020\u000f*\u00020&\u001a\n\u0010'\u001a\u00020\u0007*\u00020&\u001a;\u0010(\u001a\u00020\u0001*\u00020)2\u0006\u0010\r\u001a\u00020\u00072!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00010\u0017H\u0086\bø\u0001\u0000\u001a \u0010.\u001a\u0010\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u000201\u0018\u00010/*\u0002022\u0006\u00103\u001a\u000204\u001a\u0012\u00105\u001a\u000206*\u0002022\u0006\u0010\r\u001a\u000204\u001a\f\u00107\u001a\u000208*\u000208H\u0000\u001a\u0006\u00109\u001a\u00020:\u001a\n\u0010<\u001a\u00020\n*\u00020\u000f\u001a\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000f0>2\u0006\u0010?\u001a\u00020\u000f\u001a\u001c\u0010@\u001a\u00020\u0001*\u00020A2\b\u0010,\u001a\u0004\u0018\u00010\u000f2\u0006\u0010?\u001a\u00020\u000f\u001a\n\u0010B\u001a\u00020\"*\u00020\u000f\"\u0015\u0010!\u001a\u00020\"*\u00020\u00138F¢\u0006\u0006\u001a\u0004\b#\u0010$\"\u000e\u0010;\u001a\u00020\"X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006C"}, d2 = {"generateIsCheck", Argument.Delimiters.none, "v", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "kotlinType", "Lorg/jetbrains/kotlin/types/KotlinType;", "asmType", "Lorg/jetbrains/org/objectweb/asm/Type;", "generateAsCast", "isSafe", Argument.Delimiters.none, "unifiedNullChecks", "generateNullCheckForNonSafeAs", ModuleXmlParser.TYPE, "replaceValueParametersIn", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/SpecialGenericSignatures$SpecialSignatureInfo;", "sourceSignature", "isJvmStaticInObjectOrClassOrInterface", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "isJvmStaticInCompanionObject", "isJvmStaticIn", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Lorg/jetbrains/kotlin/psi/KtExpression;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "isGenericToArray", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "isNonGenericToArray", "isToArrayFromCollection", "Lorg/jetbrains/kotlin/descriptors/MemberDescriptor;", "arity", Argument.Delimiters.none, "getArity", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)I", "topLevelClassInternalName", "Lorg/jetbrains/kotlin/name/FqName;", "topLevelClassAsmType", "useTmpVar", "Lorg/jetbrains/kotlin/codegen/FrameMap;", "block", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "index", "extractReificationArgument", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "initialType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "extractUsedReifiedParameters", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "linkWithLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "linkedLabel", "Lorg/jetbrains/org/objectweb/asm/Label;", "STRING_UTF8_ENCODING_BYTE_LIMIT", "isDefinitelyFitEncodingLimit", "splitStringConstant", Argument.Delimiters.none, "value", "visitWithSplitting", "Lorg/jetbrains/org/objectweb/asm/AnnotationVisitor;", "encodedUTF8Size", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CodegenUtilKt {
    public static final int STRING_UTF8_ENCODING_BYTE_LIMIT = 65535;

    public static boolean a(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return DescriptorUtils.isCompanionObject(declarationDescriptor);
    }

    public static boolean b(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return DescriptorUtils.isNonCompanionObject(declarationDescriptor) || DescriptorUtils.isClassOrEnumClass(declarationDescriptor) || JvmCodegenUtil.isJvmInterface(declarationDescriptor);
    }

    public static final int encodedUTF8Size(String str) {
        str.getClass();
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            i = (1 > cCharAt || cCharAt >= 128) ? cCharAt <= 2047 ? i + 2 : i + 3 : i + 1;
        }
        return i;
    }

    public static final Pair<TypeParameterMarker, ReificationArgument> extractReificationArgument(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        boolean zIsMarkedNullable = typeSystemCommonBackendContext.isMarkedNullable(kotlinTypeMarker);
        int i = 0;
        while (typeSystemCommonBackendContext.isArrayOrNullableArray(kotlinTypeMarker)) {
            i++;
            kotlinTypeMarker = typeSystemCommonBackendContext.getType(typeSystemCommonBackendContext.getArgument(kotlinTypeMarker, 0));
            if (kotlinTypeMarker == null) {
                return null;
            }
        }
        TypeParameterMarker typeParameterClassifier = typeSystemCommonBackendContext.getTypeParameterClassifier(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker));
        if (typeParameterClassifier == null || !typeSystemCommonBackendContext.isReified(typeParameterClassifier)) {
            return null;
        }
        String strAsString = typeSystemCommonBackendContext.getName(typeParameterClassifier).asString();
        strAsString.getClass();
        return new Pair<>(typeParameterClassifier, new ReificationArgument(strAsString, zIsMarkedNullable, i));
    }

    public static final ReifiedTypeParametersUsages extractUsedReifiedParameters(TypeSystemCommonBackendContext typeSystemCommonBackendContext, KotlinTypeMarker kotlinTypeMarker) {
        typeSystemCommonBackendContext.getClass();
        kotlinTypeMarker.getClass();
        ReifiedTypeParametersUsages reifiedTypeParametersUsages = new ReifiedTypeParametersUsages();
        extractUsedReifiedParameters$lambda$0$visit(kotlinTypeMarker, typeSystemCommonBackendContext, reifiedTypeParametersUsages);
        return reifiedTypeParametersUsages;
    }

    private static final void extractUsedReifiedParameters$lambda$0$visit(KotlinTypeMarker kotlinTypeMarker, TypeSystemCommonBackendContext typeSystemCommonBackendContext, ReifiedTypeParametersUsages reifiedTypeParametersUsages) {
        TypeParameterMarker typeParameterClassifier = typeSystemCommonBackendContext.getTypeParameterClassifier(typeSystemCommonBackendContext.typeConstructor(kotlinTypeMarker));
        if (typeParameterClassifier == null) {
            Iterator it = typeSystemCommonBackendContext.getArguments(kotlinTypeMarker).iterator();
            while (it.hasNext()) {
                KotlinTypeMarker type = typeSystemCommonBackendContext.getType((TypeArgumentMarker) it.next());
                if (type != null) {
                    extractUsedReifiedParameters$lambda$0$visit(type, typeSystemCommonBackendContext, reifiedTypeParametersUsages);
                }
            }
            return;
        }
        if (typeSystemCommonBackendContext.isReified(typeParameterClassifier)) {
            String strAsString = typeSystemCommonBackendContext.getName(typeParameterClassifier).asString();
            strAsString.getClass();
            reifiedTypeParametersUsages.addUsedReifiedParameter(strAsString);
        }
    }

    public static final void generateAsCast(InstructionAdapter instructionAdapter, KotlinType kotlinType, Type type, boolean z, boolean z2) {
        instructionAdapter.getClass();
        kotlinType.getClass();
        type.getClass();
        if (z) {
            instructionAdapter.dup();
            TypeIntrinsics.instanceOf(instructionAdapter, kotlinType, type);
            Label label = new Label();
            instructionAdapter.ifne(label);
            instructionAdapter.pop();
            instructionAdapter.aconst((Object) null);
            instructionAdapter.mark(label);
        } else if (!TypeUtils.isNullableType(kotlinType)) {
            generateNullCheckForNonSafeAs(instructionAdapter, kotlinType, z2);
        }
        TypeIntrinsics.checkcast(instructionAdapter, kotlinType, type, z);
    }

    public static final void generateIsCheck(InstructionAdapter instructionAdapter, KotlinType kotlinType, Type type) {
        instructionAdapter.getClass();
        kotlinType.getClass();
        type.getClass();
        if (!TypeUtils.isNullableType(kotlinType)) {
            TypeIntrinsics.instanceOf(instructionAdapter, kotlinType, type);
            return;
        }
        Label label = new Label();
        Label label2 = new Label();
        instructionAdapter.dup();
        instructionAdapter.ifnull(label);
        TypeIntrinsics.instanceOf(instructionAdapter, kotlinType, type);
        instructionAdapter.goTo(label2);
        instructionAdapter.mark(label);
        instructionAdapter.pop();
        instructionAdapter.iconst(1);
        instructionAdapter.mark(label2);
    }

    private static final void generateNullCheckForNonSafeAs(InstructionAdapter instructionAdapter, KotlinType kotlinType, boolean z) {
        instructionAdapter.dup();
        Label label = new Label();
        instructionAdapter.ifnonnull(label);
        AsmUtil.genThrow(instructionAdapter, z ? "java/lang/NullPointerException" : "kotlin/TypeCastException", "null cannot be cast to non-null type " + DescriptorRenderer.FQ_NAMES_IN_TYPES.renderType(kotlinType));
        instructionAdapter.mark(label);
    }

    public static final int getArity(CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return callableDescriptor.getValueParameters().size() + (callableDescriptor.getExtensionReceiverParameter() != null ? 1 : 0) + (callableDescriptor.getDispatchReceiverParameter() != null ? 1 : 0);
    }

    public static final boolean isDefinitelyFitEncodingLimit(String str) {
        str.getClass();
        return str.length() <= 21845;
    }

    public static final boolean isGenericToArray(FunctionDescriptor functionDescriptor) {
        functionDescriptor.getClass();
        if (!Intrinsics.areEqual(functionDescriptor.getName().asString(), "toArray") || functionDescriptor.getValueParameters().size() != 1 || functionDescriptor.getTypeParameters().size() != 1) {
            return false;
        }
        KotlinType returnType = functionDescriptor.getReturnType();
        if (returnType == null) {
            throw new AssertionError(functionDescriptor.toString());
        }
        KotlinType type = functionDescriptor.getValueParameters().get(0).getType();
        type.getClass();
        if (KotlinBuiltIns.isArray(returnType) && KotlinBuiltIns.isArray(type)) {
            SimpleType defaultType = functionDescriptor.getTypeParameters().get(0).getDefaultType();
            defaultType.getClass();
            KotlinTypeChecker kotlinTypeChecker = KotlinTypeChecker.DEFAULT;
            if (kotlinTypeChecker.equalTypes(defaultType, DescriptorUtilsKt.getBuiltIns(functionDescriptor).getArrayElementType(returnType)) && kotlinTypeChecker.equalTypes(defaultType, DescriptorUtilsKt.getBuiltIns(functionDescriptor).getArrayElementType(type))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean isJvmStaticIn(CallableDescriptor callableDescriptor, Function1<? super DeclarationDescriptor, Boolean> function1) {
        if (!(callableDescriptor instanceof PropertyAccessorDescriptor)) {
            DeclarationDescriptor containingDeclaration = callableDescriptor.getContainingDeclaration();
            containingDeclaration.getClass();
            return ((Boolean) function1.invoke(containingDeclaration)).booleanValue() && AnnotationUtilKt.hasJvmStaticAnnotation(callableDescriptor);
        }
        PropertyDescriptor correspondingProperty = ((PropertyAccessorDescriptor) callableDescriptor).getCorrespondingProperty();
        correspondingProperty.getClass();
        DeclarationDescriptor containingDeclaration2 = correspondingProperty.getContainingDeclaration();
        containingDeclaration2.getClass();
        return ((Boolean) function1.invoke(containingDeclaration2)).booleanValue() && (AnnotationUtilKt.hasJvmStaticAnnotation(callableDescriptor) || AnnotationUtilKt.hasJvmStaticAnnotation(correspondingProperty));
    }

    public static final boolean isJvmStaticInCompanionObject(CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return isJvmStaticIn(callableDescriptor, new Function1() { // from class: v32
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CodegenUtilKt.a((DeclarationDescriptor) obj));
            }
        });
    }

    public static final boolean isJvmStaticInObjectOrClassOrInterface(CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return isJvmStaticIn(callableDescriptor, new Function1() { // from class: u32
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CodegenUtilKt.b((DeclarationDescriptor) obj));
            }
        });
    }

    public static final boolean isNonGenericToArray(FunctionDescriptor functionDescriptor) {
        KotlinType returnType;
        functionDescriptor.getClass();
        if (!Intrinsics.areEqual(functionDescriptor.getName().asString(), "toArray")) {
            return false;
        }
        List<ValueParameterDescriptor> valueParameters = functionDescriptor.getValueParameters();
        valueParameters.getClass();
        if (valueParameters.isEmpty()) {
            List<TypeParameterDescriptor> typeParameters = functionDescriptor.getTypeParameters();
            typeParameters.getClass();
            if (typeParameters.isEmpty() && (returnType = functionDescriptor.getReturnType()) != null && KotlinBuiltIns.isArray(returnType)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isToArrayFromCollection(MemberDescriptor memberDescriptor) {
        memberDescriptor.getClass();
        if (!(memberDescriptor instanceof FunctionDescriptor)) {
            return false;
        }
        FunctionDescriptor functionDescriptor = (FunctionDescriptor) memberDescriptor;
        ClassDescriptor containingDeclaration = functionDescriptor.getContainingDeclaration();
        ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? containingDeclaration : null;
        if (classDescriptor == null || Intrinsics.areEqual(classDescriptor.getSource(), SourceElement.NO_SOURCE)) {
            return false;
        }
        ClassDescriptor collection = DescriptorUtilsKt.getBuiltIns(memberDescriptor).getCollection();
        collection.getClass();
        if (DescriptorUtils.isSubclass(classDescriptor, collection)) {
            return isGenericToArray(functionDescriptor) || isNonGenericToArray(functionDescriptor);
        }
        return false;
    }

    public static final KotlinType kotlinType(KtExpression ktExpression, BindingContext bindingContext) {
        bindingContext.getClass();
        if (ktExpression != null) {
            return bindingContext.getType(ktExpression);
        }
        return null;
    }

    public static final LabelNode linkWithLabel(LabelNode labelNode) {
        labelNode.getClass();
        if (labelNode.getLabel().info == null) {
            labelNode.getLabel().info = labelNode;
        }
        return labelNode;
    }

    public static final Label linkedLabel() {
        Label label = linkWithLabel(new LabelNode()).getLabel();
        label.getClass();
        return label;
    }

    public static final String replaceValueParametersIn(SpecialGenericSignatures.SpecialSignatureInfo specialSignatureInfo, String str) {
        specialSignatureInfo.getClass();
        String valueParametersSignature = specialSignatureInfo.getValueParametersSignature();
        if (valueParametersSignature == null || str == null) {
            return null;
        }
        return new Regex("^\\(.*\\)").replace(str, "(" + valueParametersSignature + ')');
    }

    public static final List<String> splitStringConstant(String str) {
        str.getClass();
        if (isDefinitelyFitEncodingLimit(str)) {
            return CollectionsKt.listOf(str);
        }
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            int i4 = 1;
            if (1 > cCharAt || cCharAt >= 128) {
                i4 = cCharAt <= 2047 ? 2 : 3;
            }
            if (i3 + i4 > 65535) {
                arrayList.add(str.substring(i2, i));
                i3 = 0;
                i2 = i;
            }
            i3 += i4;
            i++;
        }
        arrayList.add(str.substring(i2, i));
        return arrayList;
    }

    public static final Type topLevelClassAsmType(FqName fqName) {
        fqName.getClass();
        Type objectType = Type.getObjectType(topLevelClassInternalName(fqName));
        objectType.getClass();
        return objectType;
    }

    public static final String topLevelClassInternalName(FqName fqName) {
        fqName.getClass();
        String strInternalNameByClassId = JvmClassName.internalNameByClassId(new ClassId(fqName.parent(), fqName.shortName()));
        strInternalNameByClassId.getClass();
        return strInternalNameByClassId;
    }

    public static final void useTmpVar(FrameMap frameMap, Type type, Function1<? super Integer, Unit> function1) {
        frameMap.getClass();
        type.getClass();
        function1.getClass();
        function1.invoke(Integer.valueOf(frameMap.enterTemp(type)));
        frameMap.leaveTemp(type);
    }

    public static final void visitWithSplitting(AnnotationVisitor annotationVisitor, String str, String str2) {
        annotationVisitor.getClass();
        str2.getClass();
        AnnotationVisitor annotationVisitorVisitArray = annotationVisitor.visitArray(str);
        Iterator<String> it = splitStringConstant(str2).iterator();
        while (it.hasNext()) {
            annotationVisitorVisitArray.visit((String) null, it.next());
        }
        annotationVisitorVisitArray.visitEnd();
    }
}
