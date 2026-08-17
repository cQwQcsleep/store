package org.jetbrains.kotlin.fir.backend;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.utils.CodeFragmentConversionData;
import org.jetbrains.kotlin.fir.backend.utils.InjectedValue;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;
import org.jetbrains.kotlin.ir.overrides.IrExternalOverridabilityCondition;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001*J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&J\u0018\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H&J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0014\u0010\u001d\u001a\u00020\u00032\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fH&J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006+À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", Argument.Delimiters.none, "parametersAreAssignable", Argument.Delimiters.none, "getParametersAreAssignable", "()Z", "externalOverridabilityConditions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition;", "getExternalOverridabilityConditions", "()Ljava/util/List;", "findInjectedValue", "Lorg/jetbrains/kotlin/fir/backend/utils/InjectedValue;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "findInjectedInlineLambdaArgument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "parameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "hasBackingField", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "specialBackingFieldVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "firProperty", "shouldGenerateDelegatedMember", "delegateMemberFromBaseType", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "codeFragmentConversionData", "Lorg/jetbrains/kotlin/fir/backend/utils/CodeFragmentConversionData;", "fragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "preserveLocalScope", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "cache", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrScopeCache;", "Default", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface Fir2IrExtensions {
    default CodeFragmentConversionData codeFragmentConversionData(FirCodeFragment fragment) {
        fragment.getClass();
        throw new UnsupportedOperationException();
    }

    FirExpression findInjectedInlineLambdaArgument(FirValueParameterSymbol parameter);

    /* JADX INFO: renamed from: findInjectedValue */
    InjectedValue mo248findInjectedValue(FirReference calleeReference, Fir2IrConversionScope conversionScope);

    List<IrExternalOverridabilityCondition> getExternalOverridabilityConditions();

    boolean getParametersAreAssignable();

    boolean hasBackingField(FirProperty property, FirSession session);

    default void preserveLocalScope(IrSymbol symbol, Fir2IrScopeCache cache) {
        symbol.getClass();
        cache.getClass();
    }

    boolean shouldGenerateDelegatedMember(IrOverridableDeclaration<?> delegateMemberFromBaseType);

    Visibility specialBackingFieldVisibility(FirProperty firProperty, FirSession session);

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u00020\u00052\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0016J\u001a\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions$Default;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "<init>", "()V", "parametersAreAssignable", Argument.Delimiters.none, "getParametersAreAssignable", "()Z", "externalOverridabilityConditions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/overrides/IrExternalOverridabilityCondition;", "getExternalOverridabilityConditions", "()Ljava/util/List;", "findInjectedValue", Argument.Delimiters.none, "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "findInjectedInlineLambdaArgument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "parameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "hasBackingField", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "shouldGenerateDelegatedMember", "delegateMemberFromBaseType", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "specialBackingFieldVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "firProperty", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default implements Fir2IrExtensions {
        public static final Default INSTANCE = new Default();
        private static final List<IrExternalOverridabilityCondition> externalOverridabilityConditions = CollectionsKt.emptyList();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
        public FirExpression findInjectedInlineLambdaArgument(FirValueParameterSymbol parameter) {
            parameter.getClass();
            return null;
        }

        public Void findInjectedValue(FirReference calleeReference, Fir2IrConversionScope conversionScope) {
            calleeReference.getClass();
            conversionScope.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
        public List<IrExternalOverridabilityCondition> getExternalOverridabilityConditions() {
            return externalOverridabilityConditions;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
        public boolean getParametersAreAssignable() {
            return false;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
        public boolean hasBackingField(FirProperty property, FirSession session) {
            property.getClass();
            session.getClass();
            return DeclarationAttributesKt.getHasBackingField(property);
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
        public boolean shouldGenerateDelegatedMember(IrOverridableDeclaration<?> delegateMemberFromBaseType) {
            delegateMemberFromBaseType.getClass();
            return true;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
        public Visibility specialBackingFieldVisibility(FirProperty firProperty, FirSession session) {
            firProperty.getClass();
            session.getClass();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrExtensions
        /* JADX INFO: renamed from: findInjectedValue, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ InjectedValue mo248findInjectedValue(FirReference firReference, Fir2IrConversionScope fir2IrConversionScope) {
            return (InjectedValue) findInjectedValue(firReference, fir2IrConversionScope);
        }
    }
}
