package org.jetbrains.kotlin.fir.builder;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.DestructuringKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u008d\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0013R\b\u0012\u0004\u0012\u0002H\u00020\u0003j\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0014\u001aw\u0010\u0015\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0016\u001a\u0002H\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0014\b\u0002\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0013R\b\u0012\u0004\u0012\u0002H\u00020\u0003j\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"addDestructuringVariables", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;", "c", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "container", "entries", Argument.Delimiters.none, "isNameBased", Argument.Delimiters.none, "isTmpVariable", "forceLocal", "configure", "Lkotlin/Function1;", "(Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Ljava/util/List;ZZZLkotlin/jvm/functions/Function1;)V", "buildDestructuringVariable", "entry", "index", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Ljava/lang/Object;ZZILkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "org.jetbrains.kotlin:raw-fir.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DestructuringKt {
    public static Unit a(FirVariable firVariable) {
        firVariable.getClass();
        return Unit.INSTANCE;
    }

    public static final <T> void addDestructuringVariables(DestructuringContext<T> destructuringContext, AbstractRawFirBuilder<?> abstractRawFirBuilder, List<? super FirVariable> list, FirModuleData firModuleData, FirVariable firVariable, List<? extends T> list2, boolean z, boolean z2, boolean z3, Function1<? super FirVariable, Unit> function1) {
        FirVariable firVariable2;
        destructuringContext.getClass();
        abstractRawFirBuilder.getClass();
        list.getClass();
        firModuleData.getClass();
        firVariable.getClass();
        list2.getClass();
        function1.getClass();
        if (z2) {
            firVariable2 = firVariable;
            list.add(firVariable2);
        } else {
            firVariable2 = firVariable;
        }
        Iterator<T> it = list2.iterator();
        int i = 0;
        while (it.hasNext()) {
            list.add(buildDestructuringVariable(destructuringContext, abstractRawFirBuilder, firModuleData, firVariable2, it.next(), z, z3, i, function1));
            firVariable2 = firVariable;
            i++;
        }
    }

    public static Unit b(FirVariable firVariable) {
        firVariable.getClass();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public static final <T> FirVariable buildDestructuringVariable(final DestructuringContext<T> destructuringContext, AbstractRawFirBuilder<?> abstractRawFirBuilder, FirModuleData firModuleData, final FirVariable firVariable, final T t, final boolean z, boolean z2, final int i, Function1<? super FirVariable, Unit> function1) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
        FirPropertySymbol firRegularPropertySymbol;
        AbstractRawFirBuilder<?> abstractRawFirBuilder2;
        destructuringContext.getClass();
        abstractRawFirBuilder.getClass();
        firModuleData.getClass();
        firVariable.getClass();
        function1.getClass();
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        boolean z3 = z2 || abstractRawFirBuilder.getContext().getInLocalContext();
        if (z3) {
            firRegularPropertySymbol = new FirLocalPropertySymbol();
            abstractRawFirBuilder2 = abstractRawFirBuilder;
        } else {
            abstractRawFirBuilder2 = abstractRawFirBuilder;
            firRegularPropertySymbol = new FirRegularPropertySymbol(abstractRawFirBuilder2.callableIdForName(destructuringContext.getName(t)));
        }
        firPropertyBuilder.setSymbol(firRegularPropertySymbol);
        FirPropertySymbol symbol = firPropertyBuilder.getSymbol();
        if (!z3) {
            abstractRawFirBuilder2.getContext().pushContainerSymbol(symbol);
        }
        try {
            firPropertyBuilder.setModuleData(firModuleData);
            FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
            firPropertyBuilder.setOrigin(source);
            firPropertyBuilder.setReturnTypeRef(destructuringContext.getReturnTypeRef(t));
            firPropertyBuilder.setName(destructuringContext.getName(t));
            firPropertyBuilder.setInitializer(destructuringContext.interceptExpressionBuilding(destructuringContext.getSource(t), new Function0() { // from class: cp3
                public final Object invoke() {
                    return DestructuringKt.buildDestructuringVariable$lambda$1$0$0$0(z, destructuringContext, t, firVariable, i);
                }
            }));
            firPropertyBuilder.setVar(destructuringContext.isVar(t));
            firPropertyBuilder.setSource(destructuringContext.getSource(t));
            Visibility visibility = z3 ? Visibilities.Local.INSTANCE : Visibilities.Public.INSTANCE;
            Modality modality = Modality.FINAL;
            firPropertyBuilder.setStatus(new FirDeclarationStatusImpl(visibility, modality));
            firPropertyBuilder.setLocal(z3);
            FirBasedSymbol<?> containerSymbolIfAny = abstractRawFirBuilder2.getContext().getContainerSymbolIfAny();
            if (containerSymbolIfAny != null) {
                destructuringContext.extractAnnotationsTo(t, firPropertyBuilder, containerSymbolIfAny);
            }
            if (!z3) {
                firPropertyBuilder.setDispatchReceiverType(abstractRawFirBuilder2.currentDispatchReceiverType());
                KtSourceElement source2 = firPropertyBuilder.getSource();
                KtSourceElement ktSourceElementFakeElement$default = source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.DefaultAccessor.INSTANCE, null, 2, null) : null;
                FirTypeRef returnTypeRef = firPropertyBuilder.getReturnTypeRef();
                Visibilities.Public r15 = Visibilities.Public.INSTANCE;
                firPropertyBuilder.setGetter(new FirDefaultPropertyGetter(ktSourceElementFakeElement$default, firModuleData, source, returnTypeRef, r15, firPropertyBuilder.getSymbol(), modality, null, false, false, null, null, null, 8064, null));
                if (destructuringContext.isVar(t)) {
                    KtSourceElement source3 = firPropertyBuilder.getSource();
                    firPropertyBuilder.setSetter(new FirDefaultPropertySetter(source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.DefaultAccessor.INSTANCE, null, 2, null) : null, firModuleData, source, firPropertyBuilder.getReturnTypeRef(), r15, firPropertyBuilder.getSymbol(), modality, null, false, false, null, null, null, null, null, 32640, null));
                }
            }
            Unit unit = Unit.INSTANCE;
            if (!z3) {
                abstractRawFirBuilder2.getContext().popContainerSymbol(symbol);
            }
            FirProperty firPropertyMo288build = firPropertyBuilder.mo288build();
            function1.invoke(firPropertyMo288build);
            return firPropertyMo288build;
        } catch (Throwable th) {
            if (!z3) {
                abstractRawFirBuilder2.getContext().popContainerSymbol(symbol);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirExpression buildDestructuringVariable$lambda$1$0$0$0(boolean z, DestructuringContext destructuringContext, Object obj, FirVariable firVariable, int i) {
        KtSourceElement ktSourceElementFakeElement$default;
        if (!z) {
            return ConversionUtilsKt.toComponentCall(firVariable, destructuringContext.getSource(obj), i);
        }
        KtSourceElement source = destructuringContext.getSource(obj);
        KtFakeSourceElementKind.DesugaredNameBasedDestructuring desugaredNameBasedDestructuring = KtFakeSourceElementKind.DesugaredNameBasedDestructuring.INSTANCE;
        KtSourceElement ktSourceElementFakeElement$default2 = KtSourceElementKt.fakeElement$default(source, desugaredNameBasedDestructuring, null, 2, null);
        KtSourceElement initializerSource = destructuringContext.getInitializerSource(obj);
        if (initializerSource == null || (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(initializerSource, desugaredNameBasedDestructuring, null, 2, null)) == null) {
            ktSourceElementFakeElement$default = ktSourceElementFakeElement$default2;
        }
        if (Intrinsics.areEqual(destructuringContext.getName(obj), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR) && destructuringContext.getInitializerName(obj) == null) {
            return FirExpressionUtilKt.buildErrorExpression$default(ktSourceElementFakeElement$default, new ConeSimpleDiagnostic("Underscore without renaming in destructuring", DiagnosticKind.UnderscoreWithoutRenamingInDestructuring), null, 4, null);
        }
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElementFakeElement$default);
        firPropertyAccessExpressionBuilder.setExplicitReceiver(ConversionUtilsKt.generateResolvedAccessExpression(ktSourceElementFakeElement$default2, firVariable));
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(ktSourceElementFakeElement$default);
        Name initializerName = destructuringContext.getInitializerName(obj);
        if (initializerName == null) {
            initializerName = destructuringContext.getName(obj);
        }
        firSimpleNamedReferenceBuilder.setName(initializerName);
        firPropertyAccessExpressionBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        return firPropertyAccessExpressionBuilder.mo288build();
    }
}
