package org.jetbrains.kotlin.descriptors.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptorImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.storage.NullableLazyValue;
import org.jetbrains.kotlin.storage.StorageKt;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 52\u00020\u00012\u00020\u0002:\u00015BC\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0006H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\u0001H\u0016J\u0012\u0010&\u001a\u0004\u0018\u00010\u00012\u0006\u0010'\u001a\u00020(H\u0016J0\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u0010\f\u001a\u00020\r2\u0006\u00100\u001a\u00020\u001fH\u0016J<\u00101\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\b\u0010\t\u001a\u0004\u0018\u0001022\u0006\u0010\f\u001a\u00020\r2\b\u00103\u001a\u0004\u0018\u0001042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00018VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;", "Lorg/jetbrains/kotlin/descriptors/impl/FunctionDescriptorImpl;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "typeAliasDescriptor", "Lorg/jetbrains/kotlin/descriptors/TypeAliasDescriptor;", "underlyingConstructorDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "original", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", "kind", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "<init>", "(Lorg/jetbrains/kotlin/storage/StorageManager;Lorg/jetbrains/kotlin/descriptors/TypeAliasDescriptor;Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;Lorg/jetbrains/kotlin/descriptors/SourceElement;)V", "getStorageManager", "()Lorg/jetbrains/kotlin/storage/StorageManager;", "getTypeAliasDescriptor", "()Lorg/jetbrains/kotlin/descriptors/TypeAliasDescriptor;", "withDispatchReceiver", "getWithDispatchReceiver", "()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;", "withDispatchReceiver$delegate", "Lorg/jetbrains/kotlin/storage/NullableLazyValue;", "value", "getUnderlyingConstructorDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "isPrimary", Argument.Delimiters.none, "getContainingDeclaration", "getConstructedClass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getReturnType", "Lorg/jetbrains/kotlin/types/KotlinType;", "getOriginal", "substitute", "substitutor", "Lorg/jetbrains/kotlin/types/TypeSubstitutor;", "copy", "newOwner", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "copyOverrides", "createSubstitutedCopy", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "newName", "Lorg/jetbrains/kotlin/name/Name;", "Companion", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeAliasConstructorDescriptorImpl extends FunctionDescriptorImpl implements TypeAliasConstructorDescriptor {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(TypeAliasConstructorDescriptorImpl.class, "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;", 0)};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final StorageManager storageManager;
    private final TypeAliasDescriptor typeAliasDescriptor;
    private ClassConstructorDescriptor underlyingConstructorDescriptor;

    /* JADX INFO: renamed from: withDispatchReceiver$delegate, reason: from kotlin metadata */
    private final NullableLazyValue withDispatchReceiver;

    private TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, final ClassConstructorDescriptor classConstructorDescriptor, TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement sourceElement) {
        super(typeAliasDescriptor, typeAliasConstructorDescriptor, annotations, SpecialNames.INIT, kind, sourceElement);
        this.storageManager = storageManager;
        this.typeAliasDescriptor = typeAliasDescriptor;
        setActual(getTypeAliasDescriptor().isActual());
        this.withDispatchReceiver = storageManager.createNullableLazyValue(new Function0() { // from class: qse
            public final Object invoke() {
                return TypeAliasConstructorDescriptorImpl.a(this.b, classConstructorDescriptor);
            }
        });
        this.underlyingConstructorDescriptor = classConstructorDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static TypeAliasConstructorDescriptorImpl a(TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl, ClassConstructorDescriptor classConstructorDescriptor) {
        StorageManager storageManager = typeAliasConstructorDescriptorImpl.storageManager;
        TypeAliasDescriptor typeAliasDescriptor = typeAliasConstructorDescriptorImpl.getTypeAliasDescriptor();
        Annotations annotations = classConstructorDescriptor.getAnnotations();
        CallableMemberDescriptor.Kind kind = classConstructorDescriptor.getKind();
        kind.getClass();
        SourceElement source = typeAliasConstructorDescriptorImpl.getTypeAliasDescriptor().getSource();
        source.getClass();
        TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl2 = new TypeAliasConstructorDescriptorImpl(storageManager, typeAliasDescriptor, classConstructorDescriptor, typeAliasConstructorDescriptorImpl, annotations, kind, source);
        TypeSubstitutor typeSubstitutorAccess$getTypeSubstitutorForUnderlyingClass = Companion.access$getTypeSubstitutorForUnderlyingClass(Companion, typeAliasConstructorDescriptorImpl.getTypeAliasDescriptor());
        if (typeSubstitutorAccess$getTypeSubstitutorForUnderlyingClass == null) {
            return null;
        }
        ReceiverParameterDescriptor dispatchReceiverParameter = classConstructorDescriptor.getDispatchReceiverParameter();
        ReceiverParameterDescriptor receiverParameterDescriptorSubstitute = dispatchReceiverParameter != null ? dispatchReceiverParameter.substitute(typeSubstitutorAccess$getTypeSubstitutorForUnderlyingClass) : null;
        List<ReceiverParameterDescriptor> contextReceiverParameters = classConstructorDescriptor.getContextReceiverParameters();
        contextReceiverParameters.getClass();
        List<ReceiverParameterDescriptor> list = contextReceiverParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ReceiverParameterDescriptor) it.next()).substitute(typeSubstitutorAccess$getTypeSubstitutorForUnderlyingClass));
        }
        typeAliasConstructorDescriptorImpl2.initialize(null, receiverParameterDescriptorSubstitute, arrayList, typeAliasConstructorDescriptorImpl.getTypeAliasDescriptor().getDeclaredTypeParameters(), typeAliasConstructorDescriptorImpl.getValueParameters(), typeAliasConstructorDescriptorImpl.getReturnType(), Modality.FINAL, typeAliasConstructorDescriptorImpl.getTypeAliasDescriptor().getVisibility());
        return typeAliasConstructorDescriptorImpl2;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.FunctionDescriptorImpl
    public TypeAliasConstructorDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        newOwner.getClass();
        modality.getClass();
        visibility.getClass();
        kind.getClass();
        TypeAliasConstructorDescriptor typeAliasConstructorDescriptorBuild = newCopyBuilder().setOwner(newOwner).setModality(modality).setVisibility(visibility).setKind(kind).setCopyOverrides(copyOverrides).build();
        typeAliasConstructorDescriptorBuild.getClass();
        return typeAliasConstructorDescriptorBuild;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.FunctionDescriptorImpl
    public TypeAliasConstructorDescriptorImpl createSubstitutedCopy(DeclarationDescriptor newOwner, FunctionDescriptor original, CallableMemberDescriptor.Kind kind, Name newName, Annotations annotations, SourceElement source) {
        newOwner.getClass();
        kind.getClass();
        annotations.getClass();
        source.getClass();
        CallableMemberDescriptor.Kind kind2 = CallableMemberDescriptor.Kind.DECLARATION;
        if (kind != kind2) {
            CallableMemberDescriptor.Kind kind3 = CallableMemberDescriptor.Kind.SYNTHESIZED;
        }
        return new TypeAliasConstructorDescriptorImpl(this.storageManager, getTypeAliasDescriptor(), getUnderlyingConstructorDescriptor(), this, annotations, kind2, source);
    }

    public ClassDescriptor getConstructedClass() {
        ClassDescriptor constructedClass = getUnderlyingConstructorDescriptor().getConstructedClass();
        constructedClass.getClass();
        return constructedClass;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.FunctionDescriptorImpl
    public TypeAliasConstructorDescriptor mo102getOriginal() {
        TypeAliasConstructorDescriptor typeAliasConstructorDescriptorMo102getOriginal = super.mo102getOriginal();
        typeAliasConstructorDescriptorMo102getOriginal.getClass();
        return typeAliasConstructorDescriptorMo102getOriginal;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.FunctionDescriptorImpl, org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor
    public KotlinType getReturnType() {
        KotlinType returnType = super.getReturnType();
        returnType.getClass();
        return returnType;
    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.DescriptorDerivedFromTypeAlias
    public TypeAliasDescriptor getTypeAliasDescriptor() {
        return this.typeAliasDescriptor;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor
    public ClassConstructorDescriptor getUnderlyingConstructorDescriptor() {
        return this.underlyingConstructorDescriptor;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor
    public TypeAliasConstructorDescriptor getWithDispatchReceiver() {
        return (TypeAliasConstructorDescriptor) StorageKt.getValue(this.withDispatchReceiver, this, $$delegatedProperties[0]);
    }

    public boolean isPrimary() {
        return getUnderlyingConstructorDescriptor().isPrimary();
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.FunctionDescriptorImpl
    /* JADX INFO: renamed from: substitute, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TypeAliasConstructorDescriptor mo166substitute(TypeSubstitutor substitutor) {
        substitutor.getClass();
        FunctionDescriptor functionDescriptorMo166substitute = super.mo166substitute(substitutor);
        functionDescriptorMo166substitute.getClass();
        TypeAliasConstructorDescriptorImpl typeAliasConstructorDescriptorImpl = (TypeAliasConstructorDescriptorImpl) functionDescriptorMo166substitute;
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(typeAliasConstructorDescriptorImpl.getReturnType());
        typeSubstitutorCreate.getClass();
        ClassConstructorDescriptor classConstructorDescriptorM16substitute = getUnderlyingConstructorDescriptor().m12getOriginal().m16substitute(typeSubstitutorCreate);
        if (classConstructorDescriptorM16substitute == null) {
            return null;
        }
        typeAliasConstructorDescriptorImpl.underlyingConstructorDescriptor = classConstructorDescriptorM16substitute;
        return typeAliasConstructorDescriptorImpl;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorNonRootImpl, org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public TypeAliasDescriptor getContainingDeclaration() {
        return getTypeAliasDescriptor();
    }

    public /* synthetic */ TypeAliasConstructorDescriptorImpl(StorageManager storageManager, TypeAliasDescriptor typeAliasDescriptor, ClassConstructorDescriptor classConstructorDescriptor, TypeAliasConstructorDescriptor typeAliasConstructorDescriptor, Annotations annotations, CallableMemberDescriptor.Kind kind, SourceElement sourceElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(storageManager, typeAliasDescriptor, classConstructorDescriptor, typeAliasConstructorDescriptor, annotations, kind, sourceElement);
    }
}
