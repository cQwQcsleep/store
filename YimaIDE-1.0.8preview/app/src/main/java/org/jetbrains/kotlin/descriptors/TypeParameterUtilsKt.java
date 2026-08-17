package org.jetbrains.kotlin.descriptors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterUtilsKt;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeConstructor;
import org.jetbrains.kotlin.types.error.ErrorUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\f\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\f\u001a \u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\tH\u0002¨\u0006\u000f"}, d2 = {"computeConstructorTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptorWithTypeParameters;", "capturedCopyForInnerDeclaration", "Lorg/jetbrains/kotlin/descriptors/CapturedTypeParameterDescriptor;", "declarationDescriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "declaredTypeParametersCount", Argument.Delimiters.none, "buildPossiblyInnerType", "Lorg/jetbrains/kotlin/descriptors/PossiblyInnerType;", "Lorg/jetbrains/kotlin/types/KotlinType;", "classifierDescriptor", "index", "org.jetbrains.kotlin:descriptors"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeParameterUtilsKt {
    public static boolean a(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return declarationDescriptor instanceof CallableDescriptor;
    }

    public static boolean b(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return !(declarationDescriptor instanceof ConstructorDescriptor);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, int i) {
        if (classifierDescriptorWithTypeParameters == null || ErrorUtils.isError(classifierDescriptorWithTypeParameters)) {
            return null;
        }
        int size = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters().size() + i;
        if (classifierDescriptorWithTypeParameters.isInner()) {
            List listSubList = kotlinType.getArguments().subList(i, size);
            DeclarationDescriptor declarationDescriptor = classifierDescriptorWithTypeParameters.getDeclarationDescriptor();
            return new PossiblyInnerType(classifierDescriptorWithTypeParameters, listSubList, buildPossiblyInnerType(kotlinType, declarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) declarationDescriptor : null, size));
        }
        if (size != kotlinType.getArguments().size()) {
            DescriptorUtils.isLocal(classifierDescriptorWithTypeParameters);
        }
        return new PossiblyInnerType(classifierDescriptorWithTypeParameters, kotlinType.getArguments().subList(i, kotlinType.getArguments().size()), (PossiblyInnerType) null);
    }

    public static Sequence c(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        List<TypeParameterDescriptor> typeParameters = ((CallableDescriptor) declarationDescriptor).getTypeParameters();
        typeParameters.getClass();
        return CollectionsKt.asSequence(typeParameters);
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(TypeParameterDescriptor typeParameterDescriptor, DeclarationDescriptor declarationDescriptor, int i) {
        return new CapturedTypeParameterDescriptor(typeParameterDescriptor, declarationDescriptor, i);
    }

    public static final List<TypeParameterDescriptor> computeConstructorTypeParameters(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters) {
        List listEmptyList;
        Object next;
        TypeConstructor typeConstructor;
        classifierDescriptorWithTypeParameters.getClass();
        List<TypeParameterDescriptor> declaredTypeParameters = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
        declaredTypeParameters.getClass();
        if (!classifierDescriptorWithTypeParameters.isInner() && !(classifierDescriptorWithTypeParameters.getDeclarationDescriptor() instanceof CallableDescriptor)) {
            return declaredTypeParameters;
        }
        List list = SequencesKt.toList(SequencesKt.flatMap(SequencesKt.filter(SequencesKt.takeWhile(DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters), new Function1() { // from class: ive
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TypeParameterUtilsKt.a((DeclarationDescriptor) obj));
            }
        }), new Function1() { // from class: jve
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TypeParameterUtilsKt.b((DeclarationDescriptor) obj));
            }
        }), new Function1() { // from class: kve
            public final Object invoke(Object obj) {
                return TypeParameterUtilsKt.c((DeclarationDescriptor) obj);
            }
        }));
        Iterator it = DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters).iterator();
        do {
            listEmptyList = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(next instanceof ClassDescriptor));
        ClassDescriptor classDescriptor = (ClassDescriptor) next;
        if (classDescriptor != null && (typeConstructor = classDescriptor.getTypeConstructor()) != null) {
            listEmptyList = typeConstructor.getParameters();
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        if (list.isEmpty() && listEmptyList.isEmpty()) {
            List<TypeParameterDescriptor> declaredTypeParameters2 = classifierDescriptorWithTypeParameters.getDeclaredTypeParameters();
            declaredTypeParameters2.getClass();
            return declaredTypeParameters2;
        }
        List<TypeParameterDescriptor> listPlus = CollectionsKt.plus(list, listEmptyList);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
        for (TypeParameterDescriptor typeParameterDescriptor : listPlus) {
            typeParameterDescriptor.getClass();
            arrayList.add(capturedCopyForInnerDeclaration(typeParameterDescriptor, classifierDescriptorWithTypeParameters, declaredTypeParameters.size()));
        }
        return CollectionsKt.plus(declaredTypeParameters, arrayList);
    }

    public static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType) {
        kotlinType.getClass();
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        return buildPossiblyInnerType(kotlinType, declarationDescriptor instanceof ClassifierDescriptorWithTypeParameters ? (ClassifierDescriptorWithTypeParameters) declarationDescriptor : null, 0);
    }
}
