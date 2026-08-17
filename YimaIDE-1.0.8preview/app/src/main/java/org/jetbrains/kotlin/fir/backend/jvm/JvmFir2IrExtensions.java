package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.jvm.CachedFieldsForObjectInstances;
import org.jetbrains.kotlin.backend.jvm.JvmGeneratorExtensions;
import org.jetbrains.kotlin.backend.jvm.JvmSymbols;
import org.jetbrains.kotlin.backend.jvm.overrides.IrJavaIncompatibilityRulesOverridabilityCondition;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrConversionScope;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.utils.InjectedValue;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.java.JavaUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.scopes.jvm.FirJvmDelegatedMembersFilter;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.impl.IrExternalPackageFragmentImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.overrides.IrExternalOverridabilityCondition;
import org.jetbrains.kotlin.ir.symbols.impl.DescriptorlessExternalPackageFragmentSymbol;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrFakeOverrideUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u001a\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0016J\u001a\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0016J\u0014\u00105\u001a\u00020\b2\n\u00106\u001a\u0006\u0012\u0002\b\u000307H\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/JvmFir2IrExtensions;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "Lorg/jetbrains/kotlin/backend/jvm/JvmGeneratorExtensions;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "parametersAreAssignable", Argument.Delimiters.none, "getParametersAreAssignable", "()Z", "externalOverridabilityConditions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition;", "getExternalOverridabilityConditions", "()Ljava/util/List;", "cachedFields", "Lorg/jetbrains/kotlin/backend/jvm/CachedFieldsForObjectInstances;", "getCachedFields", "()Lorg/jetbrains/kotlin/backend/jvm/CachedFieldsForObjectInstances;", "kotlinIrInternalPackage", "Lorg/jetbrains/kotlin/ir/declarations/impl/IrExternalPackageFragmentImpl;", "kotlinJvmInternalPackage", "specialAnnotationConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "rawTypeAnnotationClassConstructor", "generateRawTypeAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "createSpecialAnnotationClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "fqn", "Lorg/jetbrains/kotlin/name/FqName;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrPackageFragment;", "findInjectedValue", "Lorg/jetbrains/kotlin/fir/backend/utils/InjectedValue;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "findInjectedInlineLambdaArgument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "parameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "hasBackingField", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "specialBackingFieldVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "firProperty", "shouldGenerateDelegatedMember", "delegateMemberFromBaseType", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmFir2IrExtensions implements JvmGeneratorExtensions, Fir2IrExtensions {
    private final CachedFieldsForObjectInstances cachedFields;
    private final IrExternalPackageFragmentImpl kotlinIrInternalPackage;
    private final IrExternalPackageFragmentImpl kotlinJvmInternalPackage;
    private final IrConstructor rawTypeAnnotationClassConstructor;
    private final List<IrConstructor> specialAnnotationConstructors;

    public JvmFir2IrExtensions(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        this.cachedFields = new CachedFieldsForObjectInstances(IrFactoryImpl.INSTANCE, CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration));
        IrExternalPackageFragmentImpl irExternalPackageFragmentImpl = new IrExternalPackageFragmentImpl(new DescriptorlessExternalPackageFragmentSymbol(), IrBuiltIns.Companion.getKOTLIN_INTERNAL_IR_FQN());
        this.kotlinIrInternalPackage = irExternalPackageFragmentImpl;
        DescriptorlessExternalPackageFragmentSymbol descriptorlessExternalPackageFragmentSymbol = new DescriptorlessExternalPackageFragmentSymbol();
        FqName fqName = JvmAnnotationNames.KOTLIN_JVM_INTERNAL;
        fqName.getClass();
        IrExternalPackageFragmentImpl irExternalPackageFragmentImpl2 = new IrExternalPackageFragmentImpl(descriptorlessExternalPackageFragmentSymbol, fqName);
        this.kotlinJvmInternalPackage = irExternalPackageFragmentImpl2;
        this.specialAnnotationConstructors = new ArrayList();
        JvmSymbols.Companion companion = JvmSymbols.Companion;
        this.rawTypeAnnotationClassConstructor = (IrConstructor) SequencesKt.single(IrUtilsKt.getConstructors(createSpecialAnnotationClass(companion.getRAW_TYPE_ANNOTATION_FQ_NAME(), irExternalPackageFragmentImpl)));
        FqName fqName2 = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
        fqName2.getClass();
        createSpecialAnnotationClass(fqName2, irExternalPackageFragmentImpl2);
        createSpecialAnnotationClass(companion.getFLEXIBLE_NULLABILITY_ANNOTATION_FQ_NAME(), irExternalPackageFragmentImpl);
        createSpecialAnnotationClass(companion.getFLEXIBLE_MUTABILITY_ANNOTATION_FQ_NAME(), irExternalPackageFragmentImpl);
    }

    private final IrClass createSpecialAnnotationClass(FqName fqn, IrPackageFragment parent) {
        IrClass irClassCreateSpecialAnnotationClass = IrUtilsKt.createSpecialAnnotationClass(IrFactoryImpl.INSTANCE, fqn, parent);
        this.specialAnnotationConstructors.add((IrConstructor) SequencesKt.single(IrUtilsKt.getConstructors(irClassCreateSpecialAnnotationClass)));
        return irClassCreateSpecialAnnotationClass;
    }

    private static final boolean shouldGenerateDelegatedMember$hasJvmDefaultAnnotation(IrOverridableDeclaration<?> irOverridableDeclaration) {
        return AdditionalIrUtilsKt.hasAnnotation(irOverridableDeclaration.getAnnotations(), JvmStandardClassIds.INSTANCE.getJVM_DEFAULT_CLASS_ID());
    }

    private static final boolean shouldGenerateDelegatedMember$isBuiltInMemberMappedToJavaDefault(IrOverridableDeclaration<?> irOverridableDeclaration) {
        return irOverridableDeclaration.getModality() != Modality.ABSTRACT && AdditionalIrUtilsKt.hasAnnotation(irOverridableDeclaration.getAnnotations(), FirJvmDelegatedMembersFilter.INSTANCE.getPLATFORM_DEPENDENT_ANNOTATION_CLASS_ID());
    }

    private static final boolean shouldGenerateDelegatedMember$isNonAbstractJavaMethod(IrOverridableDeclaration<?> irOverridableDeclaration) {
        return Intrinsics.areEqual(irOverridableDeclaration.getOrigin(), IrDeclarationOrigin.Companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB()) && irOverridableDeclaration.getModality() != Modality.ABSTRACT;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
    public FirExpression findInjectedInlineLambdaArgument(FirValueParameterSymbol parameter) {
        parameter.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
    /* JADX INFO: renamed from: findInjectedValue */
    public InjectedValue mo248findInjectedValue(FirReference calleeReference, Fir2IrConversionScope conversionScope) {
        calleeReference.getClass();
        conversionScope.getClass();
        return null;
    }

    public IrAnnotation generateRawTypeAnnotation() {
        IrConstructor irConstructor = this.rawTypeAnnotationClassConstructor;
        return BuildersKt.fromSymbolOwner$default(IrAnnotationImpl.Companion, -1, -1, IrUtilsKt.getConstructedClassType(irConstructor), irConstructor.getSymbol(), (IrStatementOrigin) null, 16, (Object) null);
    }

    public CachedFieldsForObjectInstances getCachedFields() {
        return this.cachedFields;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
    public List<IrExternalOverridabilityCondition> getExternalOverridabilityConditions() {
        return CollectionsKt.listOf(new IrJavaIncompatibilityRulesOverridabilityCondition());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
    public boolean getParametersAreAssignable() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
    public boolean hasBackingField(FirProperty property, FirSession session) {
        property.getClass();
        session.getClass();
        return (property.getOrigin() instanceof FirDeclarationOrigin.Java) || Intrinsics.areEqual(DeclarationAttributesKt.isDeserializedPropertyFromAnnotation(property), Boolean.TRUE) || Fir2IrExtensions.Default.INSTANCE.hasBackingField(property, session);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
    public boolean shouldGenerateDelegatedMember(IrOverridableDeclaration<?> delegateMemberFromBaseType) {
        delegateMemberFromBaseType.getClass();
        IrOverridableDeclaration irOverridableDeclarationResolveFakeOverride$default = IrFakeOverrideUtilsKt.resolveFakeOverride$default(delegateMemberFromBaseType, (Function1) null, 1, (Object) null);
        if (irOverridableDeclarationResolveFakeOverride$default == null) {
            return true;
        }
        return !(shouldGenerateDelegatedMember$isNonAbstractJavaMethod(irOverridableDeclarationResolveFakeOverride$default) || shouldGenerateDelegatedMember$hasJvmDefaultAnnotation(irOverridableDeclarationResolveFakeOverride$default) || shouldGenerateDelegatedMember$isBuiltInMemberMappedToJavaDefault(irOverridableDeclarationResolveFakeOverride$default));
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
    public Visibility specialBackingFieldVisibility(FirProperty firProperty, FirSession session) {
        firProperty.getClass();
        session.getClass();
        if (JavaUtilsKt.hasJvmFieldAnnotation(firProperty, session)) {
            return firProperty.getStatus().getVisibility();
        }
        return null;
    }
}
