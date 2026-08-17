package org.jetbrains.kotlin.backend.common.serialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleConstant;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithVisibility;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProviderKt;
import org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyGetterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertySetterDescriptor;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.incremental.components.NoLookupLocation;
import org.jetbrains.kotlin.ir.util.DescriptorByIdSignatureFinder;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.resolve.scopes.ResolutionScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001(B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0017*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\"H\u0002J'\u0010#\u001a\u0004\u0018\u00010\u000b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00172\b\u0010%\u001a\u0004\u0018\u00010&H\u0002¢\u0006\u0002\u0010'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/DescriptorByIdSignatureFinderImpl;", "Lorg/jetbrains/kotlin/ir/util/DescriptorByIdSignatureFinder;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "mangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$DescriptorMangler;", "lookupMode", "Lorg/jetbrains/kotlin/backend/common/serialization/DescriptorByIdSignatureFinderImpl$LookupMode;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Lorg/jetbrains/kotlin/ir/util/KotlinMangler$DescriptorMangler;Lorg/jetbrains/kotlin/backend/common/serialization/DescriptorByIdSignatureFinderImpl$LookupMode;)V", "findDescriptorBySignature", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "signature", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "resolveCompositeSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CompositeSignature;", "resolveAccessorSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature$AccessorSignature;", "isConstructorName", "", "n", "Lorg/jetbrains/kotlin/name/Name;", "loadDescriptors", "", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "name", "", "isLeaf", "lookupTopLevelDescriptors", "nameSegments", "", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "resolveCommonSignature", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CommonSignature;", "findDescriptorByHash", "candidates", "id", "", "(Ljava/util/Collection;Ljava/lang/Long;)Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "LookupMode", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DescriptorByIdSignatureFinderImpl implements DescriptorByIdSignatureFinder {
    private final LookupMode lookupMode;
    private final KotlinMangler.DescriptorMangler mangler;
    private final ModuleDescriptor moduleDescriptor;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/DescriptorByIdSignatureFinderImpl$LookupMode;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "MODULE_WITH_DEPENDENCIES", "MODULE_ONLY", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum LookupMode {
        MODULE_WITH_DEPENDENCIES,
        MODULE_ONLY;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<LookupMode> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LookupMode.values().length];
            try {
                iArr[LookupMode.MODULE_WITH_DEPENDENCIES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LookupMode.MODULE_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DescriptorByIdSignatureFinderImpl(ModuleDescriptor moduleDescriptor, KotlinMangler.DescriptorMangler descriptorMangler, LookupMode lookupMode) {
        moduleDescriptor.getClass();
        descriptorMangler.getClass();
        lookupMode.getClass();
        this.moduleDescriptor = moduleDescriptor;
        this.mangler = descriptorMangler;
        this.lookupMode = lookupMode;
        LookupMode lookupMode2 = LookupMode.MODULE_WITH_DEPENDENCIES;
    }

    private final DeclarationDescriptor findDescriptorByHash(Collection<? extends DeclarationDescriptor> candidates, Long id) {
        Object next;
        Iterator<T> it = candidates.iterator();
        while (it.hasNext()) {
            next = it.next();
            DeclarationDescriptor declarationDescriptor = (DeclarationDescriptor) next;
            if (id == null) {
                if ((declarationDescriptor instanceof ClassDescriptor) || (declarationDescriptor instanceof TypeAliasDescriptor)) {
                    return (DeclarationDescriptor) next;
                }
            } else if (this.mangler.signatureMangle(declarationDescriptor, false) == id.longValue()) {
                return (DeclarationDescriptor) next;
            }
        }
        next = null;
        return (DeclarationDescriptor) next;
    }

    private final boolean isConstructorName(Name n) {
        return n.isSpecial() && Intrinsics.areEqual(n.asString(), CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME);
    }

    private final Collection<DeclarationDescriptor> loadDescriptors(MemberScope memberScope, String str, boolean z) {
        Name nameGuessByFirstCharacter = Name.guessByFirstCharacter(str);
        nameGuessByFirstCharacter.getClass();
        NoLookupLocation noLookupLocation = NoLookupLocation.FROM_BACKEND;
        ClassifierDescriptor contributedClassifier = memberScope.getContributedClassifier(nameGuessByFirstCharacter, noLookupLocation);
        if (!z) {
            return CollectionsKt.listOfNotNull(contributedClassifier);
        }
        ArrayList arrayList = new ArrayList();
        if (contributedClassifier != null) {
            arrayList.add(contributedClassifier);
        }
        arrayList.addAll(memberScope.getContributedFunctions(nameGuessByFirstCharacter, noLookupLocation));
        arrayList.addAll(memberScope.getContributedVariables(nameGuessByFirstCharacter, noLookupLocation));
        return arrayList;
    }

    private final Collection<DeclarationDescriptor> lookupTopLevelDescriptors(List<String> nameSegments, FqName packageFqName) {
        String str = nameSegments.get(0);
        boolean z = nameSegments.size() == 1;
        int i = WhenMappings.$EnumSwitchMapping$0[this.lookupMode.ordinal()];
        if (i == 1) {
            return loadDescriptors(this.moduleDescriptor.getPackage(packageFqName).getMemberScope(), str, z);
        }
        if (i != 2) {
            bu8.a();
            return null;
        }
        ModuleDescriptorImpl moduleDescriptorImpl = this.moduleDescriptor;
        moduleDescriptorImpl.getClass();
        List listPackageFragments = PackageFragmentProviderKt.packageFragments(moduleDescriptorImpl.getPackageFragmentProviderForModuleContentWithoutDependencies(), packageFqName);
        ArrayList arrayList = new ArrayList();
        Iterator it = listPackageFragments.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, loadDescriptors(((PackageFragmentDescriptor) it.next()).getMemberScope(), str, z));
        }
        return arrayList;
    }

    private final DeclarationDescriptor resolveAccessorSignature(IdSignature.AccessorSignature signature) {
        Name name;
        Name name2;
        PropertyDescriptor propertyDescriptorFindDescriptorBySignature = findDescriptorBySignature(signature.getPropertySignature());
        PropertyDescriptor propertyDescriptor = propertyDescriptorFindDescriptorBySignature instanceof PropertyDescriptor ? propertyDescriptorFindDescriptorBySignature : null;
        if (propertyDescriptor == null) {
            return null;
        }
        String shortName = signature.getAccessorSignature().getShortName();
        PropertyGetterDescriptor getter = propertyDescriptor.getGetter();
        if (Intrinsics.areEqual(shortName, (getter == null || (name2 = getter.getName()) == null) ? null : name2.asString())) {
            return propertyDescriptor.getGetter();
        }
        PropertySetterDescriptor setter = propertyDescriptor.getSetter();
        if (Intrinsics.areEqual(shortName, (setter == null || (name = setter.getName()) == null) ? null : name.asString())) {
            return propertyDescriptor.getSetter();
        }
        return null;
    }

    private final DeclarationDescriptor resolveCommonSignature(IdSignature.CommonSignature signature) {
        List listEmptyList;
        List<String> nameSegments = signature.getNameSegments();
        Collection<DeclarationDescriptor> collectionLookupTopLevelDescriptors = lookupTopLevelDescriptors(nameSegments, signature.packageFqName());
        if (collectionLookupTopLevelDescriptors.isEmpty()) {
            return null;
        }
        int lastIndex = CollectionsKt.getLastIndex(nameSegments);
        int size = nameSegments.size();
        int i = 1;
        while (i < size) {
            Name nameGuessByFirstCharacter = Name.guessByFirstCharacter(nameSegments.get(i));
            nameGuessByFirstCharacter.getClass();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = collectionLookupTopLevelDescriptors.iterator();
            while (it.hasNext()) {
                ClassDescriptor classDescriptor = (DeclarationDescriptor) it.next();
                ClassDescriptor classDescriptor2 = classDescriptor instanceof ClassDescriptor ? classDescriptor : null;
                if (classDescriptor2 == null) {
                    listEmptyList = CollectionsKt.emptyList();
                } else {
                    boolean z = i == lastIndex;
                    MemberScope unsubstitutedMemberScope = classDescriptor2.getUnsubstitutedMemberScope();
                    unsubstitutedMemberScope.getClass();
                    NoLookupLocation noLookupLocation = NoLookupLocation.FROM_BACKEND;
                    ClassifierDescriptor contributedClassifier = unsubstitutedMemberScope.getContributedClassifier(nameGuessByFirstCharacter, noLookupLocation);
                    if (z) {
                        ArrayList arrayList2 = new ArrayList();
                        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(arrayList2, contributedClassifier);
                        if (signature.getId() != null) {
                            if (isConstructorName(nameGuessByFirstCharacter)) {
                                Collection constructors = classDescriptor2.getConstructors();
                                constructors.getClass();
                                arrayList2.addAll(constructors);
                            }
                            arrayList2.addAll(unsubstitutedMemberScope.getContributedFunctions(nameGuessByFirstCharacter, noLookupLocation));
                            arrayList2.addAll(unsubstitutedMemberScope.getContributedVariables(nameGuessByFirstCharacter, noLookupLocation));
                        }
                        MemberScope staticScope = classDescriptor2.getStaticScope();
                        staticScope.getClass();
                        Collection contributedDescriptors$default = ResolutionScope.DefaultImpls.getContributedDescriptors$default(staticScope, (DescriptorKindFilter) null, (Function1) null, 3, (Object) null);
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj : contributedDescriptors$default) {
                            if (Intrinsics.areEqual(((DeclarationDescriptor) obj).getName(), nameGuessByFirstCharacter)) {
                                arrayList3.add(obj);
                            }
                        }
                        arrayList2.addAll(arrayList3);
                        listEmptyList = arrayList2;
                    } else if (contributedClassifier == null || (listEmptyList = CollectionsKt.listOf(contributedClassifier)) == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                }
                CollectionsKt.addAll(arrayList, listEmptyList);
            }
            i++;
            collectionLookupTopLevelDescriptors = arrayList;
        }
        return findDescriptorByHash(collectionLookupTopLevelDescriptors, signature.getId());
    }

    private final DeclarationDescriptor resolveCompositeSignature(IdSignature.CompositeSignature signature) {
        CallableDescriptor callableDescriptorFindDescriptorBySignature = findDescriptorBySignature(signature.nearestPublicSig());
        if (callableDescriptorFindDescriptorBySignature == null) {
            return null;
        }
        IdSignature inner = signature.getInner();
        IdSignature.LocalSignature localSignature = inner instanceof IdSignature.LocalSignature ? (IdSignature.LocalSignature) inner : null;
        if (localSignature == null || !resolveCompositeSignature$lambda$0$isTypeParameterSig(localSignature.getLocalFqn())) {
            return callableDescriptorFindDescriptorBySignature;
        }
        int iIndex = localSignature.index();
        if (callableDescriptorFindDescriptorBySignature instanceof CallableDescriptor) {
            return (DeclarationDescriptor) callableDescriptorFindDescriptorBySignature.getTypeParameters().get(iIndex);
        }
        return callableDescriptorFindDescriptorBySignature instanceof ClassifierDescriptorWithTypeParameters ? (DeclarationDescriptor) ((ClassifierDescriptorWithTypeParameters) callableDescriptorFindDescriptorBySignature).getDeclaredTypeParameters().get(iIndex) : callableDescriptorFindDescriptorBySignature;
    }

    private static final boolean resolveCompositeSignature$lambda$0$isTypeParameterSig(String str) {
        return Intrinsics.areEqual(str, MangleConstant.TYPE_PARAMETER_MARKER_NAME) || Intrinsics.areEqual(str, MangleConstant.TYPE_PARAMETER_MARKER_NAME_SETTER);
    }

    public DeclarationDescriptor findDescriptorBySignature(IdSignature signature) {
        DeclarationDescriptor declarationDescriptorResolveCompositeSignature;
        signature.getClass();
        if (signature instanceof IdSignature.AccessorSignature) {
            declarationDescriptorResolveCompositeSignature = resolveAccessorSignature((IdSignature.AccessorSignature) signature);
        } else if (signature instanceof IdSignature.CommonSignature) {
            declarationDescriptorResolveCompositeSignature = resolveCommonSignature((IdSignature.CommonSignature) signature);
        } else {
            if (!(signature instanceof IdSignature.CompositeSignature)) {
                w04.a("Unexpected signature kind: ", signature);
                return null;
            }
            declarationDescriptorResolveCompositeSignature = resolveCompositeSignature((IdSignature.CompositeSignature) signature);
        }
        if (declarationDescriptorResolveCompositeSignature == null) {
            return null;
        }
        if (!(declarationDescriptorResolveCompositeSignature instanceof PropertyAccessorDescriptor)) {
            if ((declarationDescriptorResolveCompositeSignature instanceof DeclarationDescriptorWithVisibility) && Intrinsics.areEqual(((DeclarationDescriptorWithVisibility) declarationDescriptorResolveCompositeSignature).getVisibility(), DescriptorVisibilities.INVISIBLE_FAKE)) {
                return null;
            }
            return declarationDescriptorResolveCompositeSignature;
        }
        PropertyAccessorDescriptor propertyAccessorDescriptor = (PropertyAccessorDescriptor) declarationDescriptorResolveCompositeSignature;
        DescriptorVisibility visibility = propertyAccessorDescriptor.getVisibility();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.INVISIBLE_FAKE;
        if (Intrinsics.areEqual(visibility, descriptorVisibility) || Intrinsics.areEqual(propertyAccessorDescriptor.getCorrespondingProperty().getVisibility(), descriptorVisibility)) {
            return null;
        }
        return declarationDescriptorResolveCompositeSignature;
    }

    public /* synthetic */ DescriptorByIdSignatureFinderImpl(ModuleDescriptor moduleDescriptor, KotlinMangler.DescriptorMangler descriptorMangler, LookupMode lookupMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(moduleDescriptor, descriptorMangler, (i & 4) != 0 ? LookupMode.MODULE_WITH_DEPENDENCIES : lookupMode);
    }
}
