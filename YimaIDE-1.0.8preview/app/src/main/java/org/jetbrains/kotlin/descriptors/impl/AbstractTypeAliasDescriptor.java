package org.jetbrains.kotlin.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.AbstractTypeAliasDescriptor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.storage.NotNullLazyValue;
import org.jetbrains.kotlin.storage.StorageKt;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.KotlinTypeKt;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.TypeConstructor;
import org.jetbrains.kotlin.types.TypeUtils;
import org.jetbrains.kotlin.types.UnwrappedType;
import org.jetbrains.kotlin.types.checker.KotlinTypeRefiner;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0085\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003*\u0001:\b&\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0014\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ5\u0010 \u001a\u0002H!\"\u0004\b\u0000\u0010!\"\u0004\b\u0001\u0010\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\"0$2\u0006\u0010%\u001a\u0002H\"H\u0016¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020(H\u0016J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u000eH\u0016J\b\u0010.\u001a\u00020(H\u0016J\b\u0010/\u001a\u00020(H\u0016J\b\u00100\u001a\u00020(H\u0016J\b\u00101\u001a\u000202H\u0016J\n\u00103\u001a\u000204H\u0096\u0080\u0004J\b\u00105\u001a\u00020\u0002H\u0016J\u000e\u00106\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH$J\b\u00107\u001a\u000208H\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u00020:X\u0082\u0004¢\u0006\u0004\n\u0002\u0010;¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/AbstractTypeAliasDescriptor;", "Lorg/jetbrains/kotlin/descriptors/impl/DeclarationDescriptorNonRootImpl;", "Lorg/jetbrains/kotlin/descriptors/TypeAliasDescriptor;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "sourceElement", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "visibilityImpl", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "<init>", "(Lorg/jetbrains/kotlin/storage/StorageManager;Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/descriptors/SourceElement;Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)V", "getStorageManager", "()Lorg/jetbrains/kotlin/storage/StorageManager;", "constructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;", "getConstructors", "()Ljava/util/Collection;", "constructors$delegate", "Lorg/jetbrains/kotlin/storage/NotNullLazyValue;", "declaredTypeParametersImpl", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "initialize", Argument.Delimiters.none, "declaredTypeParameters", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "isInner", Argument.Delimiters.none, "getTypeAliasConstructors", "getDeclaredTypeParameters", "getModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getVisibility", "isExpect", "isActual", "isExternal", "getTypeConstructor", "Lorg/jetbrains/kotlin/types/TypeConstructor;", "toString", Argument.Delimiters.none, "getOriginal", "getTypeConstructorTypeParameters", "computeDefaultType", "Lorg/jetbrains/kotlin/types/SimpleType;", "typeConstructor", "org/jetbrains/kotlin/descriptors/impl/AbstractTypeAliasDescriptor$typeConstructor$1", "Lorg/jetbrains/kotlin/descriptors/impl/AbstractTypeAliasDescriptor$typeConstructor$1;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractTypeAliasDescriptor extends DeclarationDescriptorNonRootImpl implements TypeAliasDescriptor {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(AbstractTypeAliasDescriptor.class, "constructors", "getConstructors()Ljava/util/Collection;", 0)};

    /* JADX INFO: renamed from: constructors$delegate, reason: from kotlin metadata */
    private final NotNullLazyValue constructors;
    private List<? extends TypeParameterDescriptor> declaredTypeParametersImpl;
    private final StorageManager storageManager;
    private final AbstractTypeAliasDescriptor$typeConstructor$1 typeConstructor;
    private final DescriptorVisibility visibilityImpl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v2, types: [org.jetbrains.kotlin.descriptors.impl.AbstractTypeAliasDescriptor$typeConstructor$1] */
    public AbstractTypeAliasDescriptor(StorageManager storageManager, DeclarationDescriptor declarationDescriptor, Annotations annotations, Name name, SourceElement sourceElement, DescriptorVisibility descriptorVisibility) {
        super(declarationDescriptor, annotations, name, sourceElement);
        storageManager.getClass();
        declarationDescriptor.getClass();
        annotations.getClass();
        name.getClass();
        sourceElement.getClass();
        descriptorVisibility.getClass();
        this.storageManager = storageManager;
        this.visibilityImpl = descriptorVisibility;
        this.constructors = storageManager.createLazyValue(new Function0() { // from class: xr
            public final Object invoke() {
                return AbstractTypeAliasDescriptor.a(this.b);
            }
        });
        this.typeConstructor = new TypeConstructor() { // from class: org.jetbrains.kotlin.descriptors.impl.AbstractTypeAliasDescriptor$typeConstructor$1
            public KotlinBuiltIns getBuiltIns() {
                return DescriptorUtilsKt.getBuiltIns(getDeclarationDescriptor());
            }

            public List<TypeParameterDescriptor> getParameters() {
                return this.this$0.getTypeConstructorTypeParameters();
            }

            public Collection<KotlinType> getSupertypes() {
                Collection<KotlinType> supertypes = getDeclarationDescriptor().getUnderlyingType().getConstructor().getSupertypes();
                supertypes.getClass();
                return supertypes;
            }

            public boolean isDenotable() {
                return true;
            }

            public boolean isFinal() {
                return getDeclarationDescriptor().getUnderlyingType().getConstructor().isFinal();
            }

            public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner) {
                kotlinTypeRefiner.getClass();
                return this;
            }

            public String toString() {
                return "[typealias " + getDeclarationDescriptor().getName().asString() + ']';
            }

            public TypeAliasDescriptor getDeclarationDescriptor() {
                return this.this$0;
            }
        };
    }

    public static Collection a(AbstractTypeAliasDescriptor abstractTypeAliasDescriptor) {
        return abstractTypeAliasDescriptor.getTypeAliasConstructors();
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0023  */
    public static Boolean b(AbstractTypeAliasDescriptor abstractTypeAliasDescriptor, UnwrappedType unwrappedType) {
        boolean z;
        unwrappedType.getClass();
        if (KotlinTypeKt.isError(unwrappedType)) {
            z = false;
        } else {
            ClassifierDescriptor declarationDescriptor = unwrappedType.getConstructor().getDeclarationDescriptor();
            if (!(declarationDescriptor instanceof TypeParameterDescriptor) || Intrinsics.areEqual(((TypeParameterDescriptor) declarationDescriptor).getDeclarationDescriptor(), abstractTypeAliasDescriptor)) {
                z = false;
            } else {
                z = true;
            }
        }
        return Boolean.valueOf(z);
    }

    public static SimpleType c(AbstractTypeAliasDescriptor abstractTypeAliasDescriptor, KotlinTypeRefiner kotlinTypeRefiner) {
        ClassifierDescriptor classifierDescriptorRefineDescriptor = kotlinTypeRefiner.refineDescriptor(abstractTypeAliasDescriptor);
        if (classifierDescriptorRefineDescriptor != null) {
            return classifierDescriptorRefineDescriptor.getDefaultType();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        visitor.getClass();
        return (R) visitor.visitTypeAliasDescriptor(this, data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final SimpleType computeDefaultType() {
        MemberScope unsubstitutedMemberScope;
        ClassDescriptor classDescriptor = getClassDescriptor();
        if (classDescriptor == null || (unsubstitutedMemberScope = classDescriptor.getUnsubstitutedMemberScope()) == null) {
            unsubstitutedMemberScope = MemberScope.Empty.INSTANCE;
        }
        SimpleType simpleTypeMakeUnsubstitutedType = TypeUtils.makeUnsubstitutedType(this, unsubstitutedMemberScope, new Function1() { // from class: yr
            public final Object invoke(Object obj) {
                return AbstractTypeAliasDescriptor.c(this.b, (KotlinTypeRefiner) obj);
            }
        });
        simpleTypeMakeUnsubstitutedType.getClass();
        return simpleTypeMakeUnsubstitutedType;
    }

    public Collection<TypeAliasConstructorDescriptor> getConstructors() {
        return (Collection) StorageKt.getValue(this.constructors, this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public List<TypeParameterDescriptor> getDeclaredTypeParameters() throws UninitializedPropertyAccessException {
        List list = this.declaredTypeParametersImpl;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("declaredTypeParametersImpl");
        return null;
    }

    public Modality getModality() {
        return Modality.FINAL;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorNonRootImpl
    /* JADX INFO: renamed from: getOriginal, reason: collision with other method in class and merged with bridge method [inline-methods] */
    public TypeAliasDescriptor mo102getOriginal() {
        TypeAliasDescriptor typeAliasDescriptorMo102getOriginal = super.mo102getOriginal();
        typeAliasDescriptorMo102getOriginal.getClass();
        return typeAliasDescriptorMo102getOriginal;
    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    public final Collection<TypeAliasConstructorDescriptor> getTypeAliasConstructors() {
        ClassDescriptor classDescriptor = getClassDescriptor();
        if (classDescriptor == null) {
            return CollectionsKt.emptyList();
        }
        Collection<ClassConstructorDescriptor> constructors = classDescriptor.getConstructors();
        constructors.getClass();
        ArrayList arrayList = new ArrayList();
        for (ClassConstructorDescriptor classConstructorDescriptor : constructors) {
            TypeAliasConstructorDescriptorImpl.Companion companion = TypeAliasConstructorDescriptorImpl.Companion;
            StorageManager storageManager = this.storageManager;
            classConstructorDescriptor.getClass();
            TypeAliasConstructorDescriptor typeAliasConstructorDescriptorCreateIfAvailable = companion.createIfAvailable(storageManager, this, classConstructorDescriptor);
            if (typeAliasConstructorDescriptorCreateIfAvailable != null) {
                arrayList.add(typeAliasConstructorDescriptorCreateIfAvailable);
            }
        }
        return arrayList;
    }

    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    public abstract List<TypeParameterDescriptor> getTypeConstructorTypeParameters();

    /* JADX INFO: renamed from: getVisibility, reason: from getter */
    public DescriptorVisibility getVisibilityImpl() {
        return this.visibilityImpl;
    }

    public final void initialize(List<? extends TypeParameterDescriptor> declaredTypeParameters) {
        declaredTypeParameters.getClass();
        this.declaredTypeParametersImpl = declaredTypeParameters;
    }

    public boolean isActual() {
        return false;
    }

    public boolean isExpect() {
        return false;
    }

    public boolean isExternal() {
        return false;
    }

    public boolean isInner() {
        return TypeUtils.contains(getUnderlyingType(), new Function1() { // from class: wr
            public final Object invoke(Object obj) {
                return AbstractTypeAliasDescriptor.b(this.b, (UnwrappedType) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        return "typealias " + getName().asString();
    }
}
