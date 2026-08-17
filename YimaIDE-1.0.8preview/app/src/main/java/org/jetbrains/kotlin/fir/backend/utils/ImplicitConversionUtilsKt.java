package org.jetbrains.kotlin.fir.backend.utils;

import defpackage.f2f;
import java.util.Map;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementContainer;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aG\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\f\u001a6\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u001f\b\u0004\u0010\u0010\u001a\u0019\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0012H\u0082\b\u001a#\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0017\u001a\u001b\u0010\u0018\u001a\u00020\u0001*\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"prepareExpressionForGivenExpectedType", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "valueType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "expectedType", "substitutedExpectedType", "forReceiver", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "applyConversionOnVararg", "Lorg/jetbrains/kotlin/ir/expressions/IrVararg;", "argument", "conversion", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "coerceStatementsToUnit", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrStatementContainer;", "coerceLastExpressionToUnit", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/expressions/IrStatementContainer;Z)V", "coerceToUnitHandlingSpecialBlocks", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitConversionUtilsKt {
    public static final void coerceStatementsToUnit(Fir2IrComponents fir2IrComponents, IrStatementContainer irStatementContainer, boolean z) {
        fir2IrComponents.getClass();
        irStatementContainer.getClass();
        fir2IrComponents.getImplicitCastInserter().coerceStatementsToUnit(irStatementContainer, z);
    }

    public static final IrExpression coerceToUnitHandlingSpecialBlocks(Fir2IrComponents fir2IrComponents, IrExpression irExpression) {
        fir2IrComponents.getClass();
        irExpression.getClass();
        if (irExpression instanceof IrContainerExpression) {
            IrContainerExpression irContainerExpression = (IrContainerExpression) irExpression;
            if (irContainerExpression.getOrigin() == null) {
                IrExpression irExpression2 = (IrStatement) CollectionsKt.lastOrNull(irContainerExpression.getStatements());
                if (irExpression2 instanceof IrExpression) {
                    irContainerExpression.getStatements().set(CollectionsKt.getLastIndex(irContainerExpression.getStatements()), Fir2IrImplicitCastInserter.INSTANCE.coerceToUnitIfNeeded$org_jetbrains_kotlin_fir2ir(fir2IrComponents, irExpression2));
                }
                return irExpression;
            }
        }
        return Fir2IrImplicitCastInserter.INSTANCE.coerceToUnitIfNeeded$org_jetbrains_kotlin_fir2ir(fir2IrComponents, irExpression);
    }

    public static final IrExpression prepareExpressionForGivenExpectedType(final Fir2IrComponents fir2IrComponents, IrExpression irExpression, FirExpression firExpression, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, final ConeKotlinType coneKotlinType3, final boolean z) {
        fir2IrComponents.getClass();
        irExpression.getClass();
        firExpression.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        coneKotlinType3.getClass();
        if (!(irExpression instanceof IrVararg)) {
            Fir2IrImplicitCastInserter implicitCastInserter = fir2IrComponents.getImplicitCastInserter();
            return implicitCastInserter.insertSpecialCast$org_jetbrains_kotlin_fir2ir(z ? implicitCastInserter.insertCastForReceiver$org_jetbrains_kotlin_fir2ir(irExpression, coneKotlinType, coneKotlinType3) : implicitCastInserter.insertCastForIntersectionTypeOrSelf$org_jetbrains_kotlin_fir2ir(irExpression, coneKotlinType, coneKotlinType3), firExpression, coneKotlinType, coneKotlinType2);
        }
        IrVararg irVararg = (IrVararg) irExpression;
        if (firExpression instanceof FirVarargArgumentsExpression) {
            FirVarargArgumentsExpression firVarargArgumentsExpression = (FirVarargArgumentsExpression) firExpression;
            if (firVarargArgumentsExpression.getArguments().size() == irVararg.getElements().size()) {
                final Map map = MapsKt.toMap(CollectionsKt.zip(irVararg.getElements(), firVarargArgumentsExpression.getArguments()));
                irVararg.getElements().replaceAll(new UnaryOperator() { // from class: org.jetbrains.kotlin.fir.backend.utils.ImplicitConversionUtilsKt$prepareExpressionForGivenExpectedType$$inlined$applyConversionOnVararg$1
                    @Override // java.util.function.Function
                    public final IrVarargElement apply(IrVarargElement irVarargElement) {
                        irVarargElement.getClass();
                        if (!(irVarargElement instanceof IrExpression)) {
                            return irVarargElement;
                        }
                        FirExpression firExpression2 = (FirExpression) map.get(irVarargElement);
                        if (firExpression2 != null) {
                            return ImplicitConversionUtilsKt.prepareExpressionForGivenExpectedType$default(fir2IrComponents, (IrExpression) irVarargElement, firExpression2, null, coneKotlinType3, null, z, 20, null);
                        }
                        f2f.a("Can't find the original FirExpression for ", RenderIrElementKt.render$default(irVarargElement, (DumpIrTreeOptions) null, 1, (Object) null));
                        return null;
                    }
                });
            }
        }
        return irVararg;
    }

    public static /* synthetic */ IrExpression prepareExpressionForGivenExpectedType$default(Fir2IrComponents fir2IrComponents, IrExpression irExpression, FirExpression firExpression, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, ConeKotlinType coneKotlinType3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            coneKotlinType = TypeExpansionUtilsKt.fullyExpandedType(fir2IrComponents, FirTypeUtilsKt.getResolvedType(firExpression));
        }
        return prepareExpressionForGivenExpectedType(fir2IrComponents, irExpression, firExpression, coneKotlinType, coneKotlinType2, (i & 16) != 0 ? coneKotlinType2 : coneKotlinType3, z);
    }
}
