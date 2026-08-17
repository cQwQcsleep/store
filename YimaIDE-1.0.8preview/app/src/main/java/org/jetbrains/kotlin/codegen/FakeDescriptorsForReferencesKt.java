package org.jetbrains.kotlin.codegen;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor$CopyBuilder;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.LocalVariableAccessorDescriptor;
import org.jetbrains.kotlin.descriptors.impl.LocalVariableDescriptor;
import org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl;
import org.jetbrains.kotlin.descriptors.impl.PropertyGetterDescriptorImpl;
import org.jetbrains.kotlin.descriptors.impl.PropertySetterDescriptorImpl;
import org.jetbrains.kotlin.descriptors.impl.ValueParameterDescriptorImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeApproximator;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.TypeProjection;
import org.jetbrains.kotlin.types.TypeSubstitution;
import org.jetbrains.kotlin.types.TypeSubstitutor;
import org.jetbrains.kotlin.types.UnwrappedType;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a'\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\u0002\u001a\u0002H\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\b\u001a \u0010\t\u001a\u00020\n*\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00072\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\u001e\u0010\t\u001a\u0004\u0018\u00010\u0010*\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0002\u001a\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0017"}, d2 = {"createFreeFakeLambdaDescriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "descriptor", "typeApproximator", "Lorg/jetbrains/kotlin/types/TypeApproximator;", "createFreeDescriptor", "D", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;Lorg/jetbrains/kotlin/types/TypeApproximator;)Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "approximate", Argument.Delimiters.none, "builder", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$CopyBuilder;", "substituteTopLevelType", "Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "newType", "Lorg/jetbrains/kotlin/types/KotlinType;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/UnwrappedType;", "toSuper", "createFreeFakeLocalPropertyDescriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lorg/jetbrains/kotlin/descriptors/impl/LocalVariableDescriptor;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FakeDescriptorsForReferencesKt {
    /* JADX WARN: Type inference failed for: r5v1, types: [org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor, org.jetbrains.kotlin.descriptors.ValueDescriptor] */
    private static final boolean approximate(TypeApproximator typeApproximator, CallableMemberDescriptor callableMemberDescriptor, CallableMemberDescriptor.CopyBuilder<?> copyBuilder) {
        boolean z;
        KotlinType kotlinTypeApproximate;
        KotlinType kotlinTypeApproximate2;
        KotlinType returnType = callableMemberDescriptor.getReturnType();
        if (returnType == null || (kotlinTypeApproximate2 = approximate(typeApproximator, returnType.unwrap(), true)) == null) {
            z = false;
        } else {
            copyBuilder.setReturnType(kotlinTypeApproximate2);
            z = true;
        }
        if (!(copyBuilder instanceof FunctionDescriptor$CopyBuilder)) {
            return z;
        }
        ?? extensionReceiverParameter = callableMemberDescriptor.getExtensionReceiverParameter();
        if (extensionReceiverParameter != 0 && (kotlinTypeApproximate = approximate(typeApproximator, extensionReceiverParameter.getType().unwrap(), false)) != null) {
            ((FunctionDescriptor$CopyBuilder) copyBuilder).setExtensionReceiverParameter(substituteTopLevelType(extensionReceiverParameter, kotlinTypeApproximate));
            z = true;
        }
        List<ValueParameterDescriptor> valueParameters = callableMemberDescriptor.getValueParameters();
        valueParameters.getClass();
        List<ValueParameterDescriptor> list = valueParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        boolean z2 = false;
        for (ValueParameterDescriptor valueParameterDescriptorImpl : list) {
            KotlinType kotlinTypeApproximate3 = approximate(typeApproximator, valueParameterDescriptorImpl.getType().unwrap(), false);
            if (kotlinTypeApproximate3 != null) {
                CallableDescriptor containingDeclaration = valueParameterDescriptorImpl.getContainingDeclaration();
                ValueParameterDescriptor original = valueParameterDescriptorImpl.getOriginal();
                int index = valueParameterDescriptorImpl.getIndex();
                Annotations annotations = valueParameterDescriptorImpl.getAnnotations();
                Name name = valueParameterDescriptorImpl.getName();
                name.getClass();
                boolean zDeclaresDefaultValue = valueParameterDescriptorImpl.declaresDefaultValue();
                boolean zIsCrossinline = valueParameterDescriptorImpl.isCrossinline();
                boolean zIsNoinline = valueParameterDescriptorImpl.isNoinline();
                KotlinType varargElementType = valueParameterDescriptorImpl.getVarargElementType();
                SourceElement source = valueParameterDescriptorImpl.getSource();
                source.getClass();
                z2 = true;
                valueParameterDescriptorImpl = new ValueParameterDescriptorImpl(containingDeclaration, original, index, annotations, name, kotlinTypeApproximate3, zDeclaresDefaultValue, zIsCrossinline, zIsNoinline, varargElementType, source);
            }
            arrayList.add(valueParameterDescriptorImpl);
        }
        if (!z2) {
            return z;
        }
        ((FunctionDescriptor$CopyBuilder) copyBuilder).setValueParameters(arrayList);
        return true;
    }

    private static final <D extends CallableMemberDescriptor> D createFreeDescriptor(D d, TypeApproximator typeApproximator) {
        CallableMemberDescriptor.CopyBuilder copyBuilderNewCopyBuilder = d.newCopyBuilder();
        copyBuilderNewCopyBuilder.getClass();
        ArrayList arrayList = new ArrayList(0);
        copyBuilderNewCopyBuilder.setTypeParameters(arrayList);
        for (ClassDescriptor declarationDescriptor = d.getDeclarationDescriptor(); declarationDescriptor != null; declarationDescriptor = declarationDescriptor.getContainingDeclaration()) {
            if (declarationDescriptor instanceof ClassDescriptor) {
                arrayList.addAll(declarationDescriptor.getDeclaredTypeParameters());
            } else if ((declarationDescriptor instanceof CallableDescriptor) && !(declarationDescriptor instanceof ConstructorDescriptor)) {
                arrayList.addAll(((CallableDescriptor) declarationDescriptor).getTypeParameters());
            }
        }
        boolean zApproximate = approximate(typeApproximator, d, (CallableMemberDescriptor.CopyBuilder<?>) copyBuilderNewCopyBuilder);
        if (arrayList.isEmpty() && !zApproximate) {
            return d;
        }
        D d2 = (D) copyBuilderNewCopyBuilder.build();
        d2.getClass();
        return d2;
    }

    public static final FunctionDescriptor createFreeFakeLambdaDescriptor(FunctionDescriptor functionDescriptor, TypeApproximator typeApproximator) {
        functionDescriptor.getClass();
        typeApproximator.getClass();
        return createFreeDescriptor(functionDescriptor, typeApproximator);
    }

    public static final PropertyDescriptor createFreeFakeLocalPropertyDescriptor(LocalVariableDescriptor localVariableDescriptor, TypeApproximator typeApproximator) {
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        localVariableDescriptor.getClass();
        typeApproximator.getClass();
        PropertyDescriptorImpl propertyDescriptorImplCreate = PropertyDescriptorImpl.create(localVariableDescriptor.getContainingDeclaration(), localVariableDescriptor.getAnnotations(), Modality.FINAL, localVariableDescriptor.getVisibility(), localVariableDescriptor.isVar(), localVariableDescriptor.getName(), CallableMemberDescriptor.Kind.DECLARATION, localVariableDescriptor.getSource(), false, localVariableDescriptor.isConst(), false, false, false, localVariableDescriptor.isDelegated());
        propertyDescriptorImplCreate.getClass();
        propertyDescriptorImplCreate.setType(localVariableDescriptor.getType(), localVariableDescriptor.getTypeParameters(), localVariableDescriptor.mo160getDispatchReceiverParameter(), localVariableDescriptor.getExtensionReceiverParameter(), localVariableDescriptor.getContextReceiverParameters());
        LocalVariableAccessorDescriptor getter = localVariableDescriptor.getGetter();
        PropertySetterDescriptorImpl propertySetterDescriptorImpl = null;
        if (getter != null) {
            PropertyGetterDescriptorImpl propertyGetterDescriptorImpl2 = new PropertyGetterDescriptorImpl(propertyDescriptorImplCreate, getter.getAnnotations(), getter.getModality(), getter.getVisibility(), true, getter.isExternal(), getter.isInline(), getter.getKind(), null, getter.getSource());
            propertyGetterDescriptorImpl2.initialize(getter.getReturnType());
            propertyGetterDescriptorImpl = propertyGetterDescriptorImpl2;
        } else {
            propertyGetterDescriptorImpl = null;
        }
        LocalVariableAccessorDescriptor setter = localVariableDescriptor.getSetter();
        if (setter != null) {
            PropertySetterDescriptorImpl propertySetterDescriptorImpl2 = new PropertySetterDescriptorImpl(propertyDescriptorImplCreate, setter.getAnnotations(), setter.getModality(), setter.getVisibility(), true, setter.isExternal(), setter.isInline(), setter.getKind(), null, setter.getSource());
            List<ValueParameterDescriptor> valueParameters = setter.getValueParameters();
            valueParameters.getClass();
            propertySetterDescriptorImpl2.initialize((ValueParameterDescriptor) CollectionsKt.single(valueParameters));
            propertySetterDescriptorImpl = propertySetterDescriptorImpl2;
        }
        propertyDescriptorImplCreate.initialize(propertyGetterDescriptorImpl, propertySetterDescriptorImpl);
        return createFreeDescriptor(propertyDescriptorImplCreate, typeApproximator);
    }

    private static final ReceiverParameterDescriptor substituteTopLevelType(ReceiverParameterDescriptor receiverParameterDescriptor, final KotlinType kotlinType) {
        return receiverParameterDescriptor.substitute(TypeSubstitutor.create(new TypeSubstitution() { // from class: org.jetbrains.kotlin.codegen.FakeDescriptorsForReferencesKt$substituteTopLevelType$wrappedSubstitution$1
            public TypeProjection get(KotlinType key) {
                key.getClass();
                return null;
            }

            public KotlinType prepareTopLevelType(KotlinType topLevelType, Variance position) {
                topLevelType.getClass();
                position.getClass();
                return kotlinType;
            }
        }));
    }

    private static final KotlinType approximate(TypeApproximator typeApproximator, UnwrappedType unwrappedType, boolean z) {
        if (unwrappedType.getArguments().isEmpty() && unwrappedType.getConstructor().isDenotable()) {
            return null;
        }
        if (z) {
            return typeApproximator.approximateToSuperType(unwrappedType, TypeApproximatorConfiguration.PublicDeclaration.SaveAnonymousTypes.INSTANCE);
        }
        return typeApproximator.approximateToSubType(unwrappedType, TypeApproximatorConfiguration.PublicDeclaration.SaveAnonymousTypes.INSTANCE);
    }
}
