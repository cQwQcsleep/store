package org.jetbrains.kotlin.descriptors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.SimpleType;
import org.jetbrains.kotlin.types.TypeConstructor;
import org.jetbrains.kotlin.types.TypeProjection;
import org.jetbrains.kotlin.types.checker.SimpleClassicTypeSystemContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001c\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a\u001e\u0010\b\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u0002*\u00020\u000f2\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a\"\u0010\u0003\u001a\u00020\u0002*\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0012\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\u0011H\u0002\u001a(\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u000b*\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\u0002H\u0002\u001a\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u000b*\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a\u0018\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0002¨\u0006\u001b"}, d2 = {"toDescriptorVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "effectiveVisibility", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "checkPublishedApi", Argument.Delimiters.none, "forVisibility", "dependentDescriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/DescriptorWithRelation;", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptor;", "ownRelation", "Lorg/jetbrains/kotlin/descriptors/RelationToType;", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "classes", "Lorg/jetbrains/kotlin/types/KotlinType;", "types", "leastPermissive", "base", "leastPermissiveDescriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorWithVisibility;", "visibility", "lowerBound", "first", "second", "org.jetbrains.kotlin:descriptors"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EffectiveVisibilityUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EffectiveVisibility.Permissiveness.values().length];
            try {
                iArr[EffectiveVisibility.Permissiveness.LESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EffectiveVisibility.Permissiveness.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final Set<DescriptorWithRelation> dependentDescriptors(KotlinType kotlinType, Set<? extends KotlinType> set, RelationToType relationToType) {
        Set<DescriptorWithRelation> setEmptySet;
        if (set.contains(kotlinType)) {
            return SetsKt.emptySet();
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null || (setEmptySet = dependentDescriptors(declarationDescriptor, relationToType)) == null) {
            setEmptySet = SetsKt.emptySet();
        }
        List arguments = kotlinType.getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        Iterator it = arguments.iterator();
        while (it.hasNext()) {
            KotlinType type = ((TypeProjection) it.next()).getType();
            type.getClass();
            arrayList.add(dependentDescriptors(type, SetsKt.plus(set, kotlinType), RelationToType.ARGUMENT));
        }
        return SetsKt.plus(setEmptySet, CollectionsKt.flatten(arrayList));
    }

    private static final EffectiveVisibility effectiveVisibility(ClassDescriptor classDescriptor, Set<? extends ClassDescriptor> set, boolean z) {
        EffectiveVisibility effectiveVisibility;
        if (set.contains(classDescriptor)) {
            return EffectiveVisibility.Public.INSTANCE;
        }
        ClassDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
        ClassDescriptor classDescriptor2 = containingDeclaration instanceof ClassDescriptor ? containingDeclaration : null;
        DescriptorVisibility visibility = classDescriptor.getVisibility();
        visibility.getClass();
        EffectiveVisibility effectiveVisibility2 = effectiveVisibility(visibility, (DeclarationDescriptor) classDescriptor, z);
        if (classDescriptor2 == null || (effectiveVisibility = effectiveVisibility(classDescriptor2, (Set<? extends ClassDescriptor>) SetsKt.plus(set, classDescriptor), z)) == null) {
            effectiveVisibility = EffectiveVisibility.Public.INSTANCE;
        }
        return lowerBound(effectiveVisibility2, effectiveVisibility);
    }

    public static /* synthetic */ EffectiveVisibility effectiveVisibility$default(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DescriptorVisibility descriptorVisibility, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            descriptorVisibility = declarationDescriptorWithVisibility.getVisibility();
            descriptorVisibility.getClass();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return effectiveVisibility(declarationDescriptorWithVisibility, descriptorVisibility, z);
    }

    private static final EffectiveVisibility forVisibility(DescriptorVisibility descriptorVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
        SimpleType defaultType;
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PRIVATE_TO_THIS) || Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.INVISIBLE_FAKE)) {
            return EffectiveVisibility.PrivateInClass.INSTANCE;
        }
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PRIVATE)) {
            return ((declarationDescriptor instanceof ClassDescriptor) && (((ClassDescriptor) declarationDescriptor).getContainingDeclaration() instanceof PackageFragmentDescriptor)) ? EffectiveVisibility.PrivateInFile.INSTANCE : EffectiveVisibility.PrivateInClass.INSTANCE;
        }
        TypeConstructor constructor = null;
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PROTECTED)) {
            ClassDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
            ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? containingDeclaration : null;
            if (classDescriptor != null && (defaultType = classDescriptor.getDefaultType()) != null) {
                constructor = defaultType.getConstructor();
            }
            return new EffectiveVisibility.Protected(constructor);
        }
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.INTERNAL)) {
            return (z && DescriptorUtilsKt.isPublishedApi(declarationDescriptor)) ? EffectiveVisibility.Public.INSTANCE : EffectiveVisibility.Internal.INSTANCE;
        }
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.PUBLIC)) {
            return EffectiveVisibility.Public.INSTANCE;
        }
        if (Intrinsics.areEqual(descriptorVisibility, DescriptorVisibilities.LOCAL)) {
            return EffectiveVisibility.Local.INSTANCE;
        }
        hih.a("Visibility ", descriptorVisibility.getName(), " is not allowed in forVisibility");
        return null;
    }

    private static final DescriptorWithRelation leastPermissive(Set<DescriptorWithRelation> set, EffectiveVisibility effectiveVisibility) {
        for (DescriptorWithRelation descriptorWithRelation : set) {
            EffectiveVisibility effectiveVisibility2 = descriptorWithRelation.effectiveVisibility();
            if (!(effectiveVisibility2 instanceof EffectiveVisibility.InternalOrPackage) || !(effectiveVisibility instanceof EffectiveVisibility.InternalOrPackage)) {
                int i = WhenMappings.$EnumSwitchMapping$0[effectiveVisibility2.relation(effectiveVisibility, SimpleClassicTypeSystemContext.INSTANCE).ordinal()];
                if (i == 1 || i == 2) {
                    return descriptorWithRelation;
                }
            }
        }
        return null;
    }

    public static final DescriptorWithRelation leastPermissiveDescriptor(KotlinType kotlinType, EffectiveVisibility effectiveVisibility) {
        kotlinType.getClass();
        effectiveVisibility.getClass();
        return leastPermissive(dependentDescriptors(kotlinType), effectiveVisibility);
    }

    private static final EffectiveVisibility lowerBound(EffectiveVisibility effectiveVisibility, EffectiveVisibility effectiveVisibility2) {
        return effectiveVisibility.lowerBound(effectiveVisibility2, SimpleClassicTypeSystemContext.INSTANCE);
    }

    public static final DescriptorVisibility toDescriptorVisibility(EffectiveVisibility effectiveVisibility) {
        effectiveVisibility.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.toDescriptorVisibility(effectiveVisibility.toVisibility());
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }

    public static /* synthetic */ EffectiveVisibility effectiveVisibility$default(ClassDescriptor classDescriptor, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return effectiveVisibility(classDescriptor, z);
    }

    public static /* synthetic */ EffectiveVisibility effectiveVisibility$default(DescriptorVisibility descriptorVisibility, DeclarationDescriptor declarationDescriptor, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return effectiveVisibility(descriptorVisibility, declarationDescriptor, z);
    }

    public static final EffectiveVisibility effectiveVisibility(ClassDescriptor classDescriptor, boolean z) {
        classDescriptor.getClass();
        return effectiveVisibility(classDescriptor, (Set<? extends ClassDescriptor>) SetsKt.emptySet(), z);
    }

    public static final EffectiveVisibility effectiveVisibility(DescriptorVisibility descriptorVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
        descriptorVisibility.getClass();
        declarationDescriptor.getClass();
        EffectiveVisibility effectiveVisibilityCustomEffectiveVisibility = descriptorVisibility.customEffectiveVisibility();
        return effectiveVisibilityCustomEffectiveVisibility == null ? forVisibility(descriptorVisibility.normalize(), declarationDescriptor, z) : effectiveVisibilityCustomEffectiveVisibility;
    }

    public static final EffectiveVisibility effectiveVisibility(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DescriptorVisibility descriptorVisibility, boolean z) {
        EffectiveVisibility effectiveVisibility;
        declarationDescriptorWithVisibility.getClass();
        descriptorVisibility.getClass();
        EffectiveVisibility effectiveVisibility2 = effectiveVisibility(descriptorVisibility, declarationDescriptorWithVisibility, z);
        ClassDescriptor containingDeclaration = declarationDescriptorWithVisibility.getContainingDeclaration();
        ClassDescriptor classDescriptor = containingDeclaration instanceof ClassDescriptor ? containingDeclaration : null;
        if (classDescriptor == null || (effectiveVisibility = effectiveVisibility(classDescriptor, z)) == null) {
            effectiveVisibility = EffectiveVisibility.Public.INSTANCE;
        }
        return lowerBound(effectiveVisibility2, effectiveVisibility);
    }

    private static final Set<DescriptorWithRelation> dependentDescriptors(KotlinType kotlinType) {
        return dependentDescriptors(kotlinType, SetsKt.emptySet(), RelationToType.CONSTRUCTOR);
    }

    private static final Set<DescriptorWithRelation> dependentDescriptors(ClassifierDescriptor classifierDescriptor, RelationToType relationToType) {
        Set<DescriptorWithRelation> setEmptySet;
        Set of = SetsKt.setOf(new DescriptorWithRelation(classifierDescriptor, relationToType));
        DeclarationDescriptor declarationDescriptor = classifierDescriptor.getDeclarationDescriptor();
        ClassifierDescriptor classifierDescriptor2 = declarationDescriptor instanceof ClassifierDescriptor ? (ClassifierDescriptor) declarationDescriptor : null;
        if (classifierDescriptor2 == null || (setEmptySet = dependentDescriptors(classifierDescriptor2, relationToType.containerRelation())) == null) {
            setEmptySet = SetsKt.emptySet();
        }
        return SetsKt.plus(of, setEmptySet);
    }
}
