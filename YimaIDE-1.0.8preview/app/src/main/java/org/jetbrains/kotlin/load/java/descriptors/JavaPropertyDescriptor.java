package org.jetbrains.kotlin.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ConstUtil;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl;
import org.jetbrains.kotlin.descriptors.impl.PropertyGetterDescriptorImpl;
import org.jetbrains.kotlin.descriptors.impl.PropertySetterDescriptorImpl;
import org.jetbrains.kotlin.load.java.typeEnhancement.TypeEnhancementKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.DescriptorFactory;
import org.jetbrains.kotlin.resolve.constants.ConstantValue;
import org.jetbrains.kotlin.storage.NullableLazyValue;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class JavaPropertyDescriptor extends PropertyDescriptorImpl implements JavaCallableMemberDescriptor {
    private KotlinType inType;
    private final boolean isStaticFinal;
    private final Pair<CallableDescriptor.UserDataKey<?>, ?> singleUserData;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 21 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 21 ? 3 : 2];
        switch (i) {
            case 1:
            case 8:
                objArr[0] = "annotations";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 9:
                objArr[0] = "modality";
                break;
            case 3:
            case 10:
                objArr[0] = "visibility";
                break;
            case 4:
            case 11:
                objArr[0] = ModuleXmlParser.NAME;
                break;
            case 5:
            case 12:
            case 18:
                objArr[0] = "source";
                break;
            case 6:
            case 16:
                objArr[0] = "kind";
                break;
            case 7:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 13:
                objArr[0] = "newOwner";
                break;
            case 14:
                objArr[0] = "newModality";
                break;
            case 15:
                objArr[0] = "newVisibility";
                break;
            case 17:
                objArr[0] = "newName";
                break;
            case 19:
                objArr[0] = "enhancedValueParameterTypes";
                break;
            case 20:
                objArr[0] = "enhancedReturnType";
                break;
            case 21:
                objArr[0] = "org/jetbrains/kotlin/load/java/descriptors/JavaPropertyDescriptor";
                break;
            case 22:
                objArr[0] = "inType";
                break;
        }
        if (i != 21) {
            objArr[1] = "org/jetbrains/kotlin/load/java/descriptors/JavaPropertyDescriptor";
        } else {
            objArr[1] = "enhance";
        }
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                objArr[2] = CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME;
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 19:
            case 20:
                objArr[2] = "enhance";
                break;
            case 21:
                break;
            case 22:
                objArr[2] = "setInType";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 21) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaPropertyDescriptor(DeclarationDescriptor declarationDescriptor, Annotations annotations, Modality modality, DescriptorVisibility descriptorVisibility, boolean z, Name name, SourceElement sourceElement, PropertyDescriptor propertyDescriptor, CallableMemberDescriptor.Kind kind, boolean z2, Pair<CallableDescriptor.UserDataKey<?>, ?> pair) {
        super(declarationDescriptor, propertyDescriptor, annotations, modality, descriptorVisibility, z, name, kind, sourceElement, false, false, false, false, false, false);
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (modality == null) {
            $$$reportNull$$$0(2);
        }
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(3);
        }
        if (name == null) {
            $$$reportNull$$$0(4);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(5);
        }
        if (kind == null) {
            $$$reportNull$$$0(6);
        }
        this.inType = null;
        this.isStaticFinal = z2;
        this.singleUserData = pair;
    }

    public static JavaPropertyDescriptor create(DeclarationDescriptor declarationDescriptor, Annotations annotations, Modality modality, DescriptorVisibility descriptorVisibility, boolean z, Name name, SourceElement sourceElement, boolean z2) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(7);
        }
        if (annotations == null) {
            $$$reportNull$$$0(8);
        }
        if (modality == null) {
            $$$reportNull$$$0(9);
        }
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(10);
        }
        if (name == null) {
            $$$reportNull$$$0(11);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(12);
        }
        return new JavaPropertyDescriptor(declarationDescriptor, annotations, modality, descriptorVisibility, z, name, sourceElement, null, CallableMemberDescriptor.Kind.DECLARATION, z2, null);
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl
    public PropertyDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor, Modality modality, DescriptorVisibility descriptorVisibility, PropertyDescriptor propertyDescriptor, CallableMemberDescriptor.Kind kind, Name name, SourceElement sourceElement) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(13);
        }
        if (modality == null) {
            $$$reportNull$$$0(14);
        }
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(15);
        }
        if (kind == null) {
            $$$reportNull$$$0(16);
        }
        if (name == null) {
            $$$reportNull$$$0(17);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(18);
        }
        return new JavaPropertyDescriptor(declarationDescriptor, getAnnotations(), modality, descriptorVisibility, isVar(), name, sourceElement, propertyDescriptor, kind, this.isStaticFinal, this.singleUserData);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [org.jetbrains.kotlin.descriptors.CallableDescriptor, org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithSource, org.jetbrains.kotlin.descriptors.FunctionDescriptor, org.jetbrains.kotlin.descriptors.MemberDescriptor, org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor, org.jetbrains.kotlin.descriptors.PropertySetterDescriptor, org.jetbrains.kotlin.descriptors.annotations.Annotated] */
    public JavaCallableMemberDescriptor enhance(KotlinType kotlinType, List<KotlinType> list, KotlinType kotlinType2, Pair<CallableDescriptor.UserDataKey<?>, ?> pair) {
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl;
        PropertySetterDescriptorImpl propertySetterDescriptorImpl;
        if (list == null) {
            $$$reportNull$$$0(19);
        }
        if (kotlinType2 == null) {
            $$$reportNull$$$0(20);
        }
        PropertyDescriptor propertyDescriptorM164getOriginal = m164getOriginal() == this ? null : m164getOriginal();
        JavaPropertyDescriptor javaPropertyDescriptor = new JavaPropertyDescriptor(getContainingDeclaration(), getAnnotations(), getModality(), getVisibility(), isVar(), getName(), getSource(), propertyDescriptorM164getOriginal, getKind(), this.isStaticFinal, pair);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImplM142getGetter = m142getGetter();
        if (propertyGetterDescriptorImplM142getGetter != null) {
            PropertyGetterDescriptorImpl propertyGetterDescriptorImpl2 = new PropertyGetterDescriptorImpl(javaPropertyDescriptor, propertyGetterDescriptorImplM142getGetter.getAnnotations(), propertyGetterDescriptorImplM142getGetter.getModality(), propertyGetterDescriptorImplM142getGetter.getVisibility(), propertyGetterDescriptorImplM142getGetter.isDefault(), propertyGetterDescriptorImplM142getGetter.isExternal(), propertyGetterDescriptorImplM142getGetter.isInline(), getKind(), propertyDescriptorM164getOriginal == null ? null : propertyDescriptorM164getOriginal.getGetter(), propertyGetterDescriptorImplM142getGetter.getSource());
            propertyGetterDescriptorImpl2.setInitialSignatureDescriptor(propertyGetterDescriptorImplM142getGetter.getInitialSignatureDescriptor());
            propertyGetterDescriptorImpl2.initialize(kotlinType2);
            propertyGetterDescriptorImpl = propertyGetterDescriptorImpl2;
        } else {
            propertyGetterDescriptorImpl = null;
        }
        ?? M146getSetter = m146getSetter();
        if (M146getSetter != 0) {
            propertySetterDescriptorImpl = new PropertySetterDescriptorImpl(javaPropertyDescriptor, M146getSetter.getAnnotations(), M146getSetter.getModality(), M146getSetter.getVisibility(), M146getSetter.isDefault(), M146getSetter.isExternal(), M146getSetter.isInline(), getKind(), propertyDescriptorM164getOriginal == null ? null : propertyDescriptorM164getOriginal.getSetter(), M146getSetter.getSource());
            propertySetterDescriptorImpl.setInitialSignatureDescriptor(propertySetterDescriptorImpl.getInitialSignatureDescriptor());
            propertySetterDescriptorImpl.initialize(M146getSetter.getValueParameters().get(0));
        } else {
            propertySetterDescriptorImpl = null;
        }
        javaPropertyDescriptor.initialize(propertyGetterDescriptorImpl, propertySetterDescriptorImpl, getBackingField(), getDelegateField());
        javaPropertyDescriptor.setSetterProjectedOut(isSetterProjectedOut());
        Function0<NullableLazyValue<ConstantValue<?>>> function0 = this.compileTimeInitializerFactory;
        if (function0 != null) {
            javaPropertyDescriptor.setCompileTimeInitializer(this.compileTimeInitializer, function0);
        }
        javaPropertyDescriptor.setOverriddenDescriptors(getOverriddenDescriptors());
        javaPropertyDescriptor.setType(kotlinType2, getTypeParameters(), mo160getDispatchReceiverParameter(), kotlinType != null ? DescriptorFactory.createExtensionReceiverParameterForCallable(this, kotlinType, Annotations.INSTANCE.getEMPTY()) : null, CollectionsKt.emptyList());
        return javaPropertyDescriptor;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl
    public KotlinType getInType() {
        FunctionDescriptor functionDescriptorM146getSetter = m146getSetter();
        KotlinType type = functionDescriptorM146getSetter != null ? functionDescriptorM146getSetter.getValueParameters().get(0).getType() : null;
        return type != null ? type : this.inType;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl, org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl
    public <V> V getUserData(CallableDescriptor.UserDataKey<V> userDataKey) {
        Pair<CallableDescriptor.UserDataKey<?>, ?> pair = this.singleUserData;
        if (pair == null || !((CallableDescriptor.UserDataKey) pair.getFirst()).equals(userDataKey)) {
            return null;
        }
        return (V) this.singleUserData.getSecond();
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl
    public boolean hasSynthesizedParameterNames() {
        return false;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl, org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl
    public boolean isConst() {
        KotlinType type = getType();
        if (this.isStaticFinal && ConstUtil.canBeUsedForConstVal(type)) {
            return !TypeEnhancementKt.hasEnhancedNullability(type) || KotlinBuiltIns.isString(type);
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.PropertyDescriptorImpl
    public void setInType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(22);
        }
        this.inType = kotlinType;
    }
}
