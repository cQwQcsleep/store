package org.jetbrains.kotlin.fir.builder;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.KtFakePsiSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.builder.PsiConversionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirEqualityOperatorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirTypeOperatorCallBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtAnnotated;
import org.jetbrains.kotlin.psi.KtDestructuringDeclaration;
import org.jetbrains.kotlin.psi.KtDestructuringDeclarationEntry;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtOperationReferenceExpression;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.KtWhenCondition;
import org.jetbrains.kotlin.psi.KtWhenConditionInRange;
import org.jetbrains.kotlin.psi.KtWhenConditionIsPattern;
import org.jetbrains.kotlin.psi.KtWhenConditionWithExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0004\u001ag\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u000424\u0010\u0005\u001a0\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\r2\u0019\u0010\u000e\u001a\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\rH\u0000\u001ar\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u000424\u0010\u0005\u001a0\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\r2\u0019\u0010\u000e\u001a\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\rH\u0000¢\u0006\u0002\u0010\u0013\u001a_\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u000b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001d\u0010\u001e\u001a\u0019\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0\u001f¢\u0006\u0002\b\rH\u0000\u001aS\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010#\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00012\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001d\u0010\u001e\u001a\u0019\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0\u001f¢\u0006\u0002\b\rH\u0000\u001aM\u0010$\u001a\u00020%*\u0006\u0012\u0002\b\u00030)2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.H\u0000R\b\u0012\u0004\u0012\u00020'0&j\f\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&¢\u0006\u0002\u0010/\u001a{\u00100\u001a\u00020\"*\u0006\u0012\u0002\b\u00030)2\u000e\u00101\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u0004022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.2\u0006\u00103\u001a\u00020.2\u0014\b\u0002\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0\u000fH\u0000R\b\u0012\u0004\u0012\u00020'0&j\f\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&¢\u0006\u0002\u00105¨\u00066"}, d2 = {"toFirWhenCondition", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/psi/KtWhenCondition;", "subjectVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "convert", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/psi/KtExpression;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtElement;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "fallbackSource", "Lkotlin/ExtensionFunctionType;", "toFirOrErrorTypeRef", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/psi/KtTypeReference;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", Argument.Delimiters.none, "([Lorg/jetbrains/kotlin/psi/KtWhenCondition;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "generateTemporaryVariable", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "Lorg/jetbrains/kotlin/name/Name;", "initializer", "typeRef", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "extractAnnotationsTo", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/psi/KtAnnotated;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", Argument.Delimiters.none, "specialName", "generateDestructuringBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;", "Lorg/jetbrains/kotlin/psi/KtDestructuringDeclarationEntry;", "c", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "multiDeclaration", "Lorg/jetbrains/kotlin/psi/KtDestructuringDeclaration;", "container", "tmpVariable", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/psi/KtDestructuringDeclaration;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Z)Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "addDestructuringVariables", "destination", Argument.Delimiters.none, "forceLocal", "configure", "(Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/psi/KtDestructuringDeclaration;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;ZZLkotlin/jvm/functions/Function1;)V", "org.jetbrains.kotlin:psi2fir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PsiConversionUtilsKt {
    public static Unit a(FirVariable firVariable) {
        firVariable.getClass();
        return Unit.INSTANCE;
    }

    public static final void addDestructuringVariables(DestructuringContext<KtDestructuringDeclarationEntry> destructuringContext, AbstractRawFirBuilder<?> abstractRawFirBuilder, List<? super FirVariable> list, FirModuleData firModuleData, KtDestructuringDeclaration ktDestructuringDeclaration, FirVariable firVariable, boolean z, boolean z2, Function1<? super FirVariable, Unit> function1) {
        destructuringContext.getClass();
        abstractRawFirBuilder.getClass();
        list.getClass();
        firModuleData.getClass();
        ktDestructuringDeclaration.getClass();
        firVariable.getClass();
        function1.getClass();
        List entries = ktDestructuringDeclaration.getEntries();
        entries.getClass();
        DestructuringKt.addDestructuringVariables(destructuringContext, abstractRawFirBuilder, list, firModuleData, firVariable, entries, !ktDestructuringDeclaration.hasSquareBrackets() && (ktDestructuringDeclaration.isFullForm() || abstractRawFirBuilder.getNameBasedDestructuringShortForm()), z, z2, function1);
    }

    public static /* synthetic */ void addDestructuringVariables$default(DestructuringContext destructuringContext, AbstractRawFirBuilder abstractRawFirBuilder, List list, FirModuleData firModuleData, KtDestructuringDeclaration ktDestructuringDeclaration, FirVariable firVariable, boolean z, boolean z2, Function1 function1, int i, Object obj) {
        addDestructuringVariables(destructuringContext, abstractRawFirBuilder, list, firModuleData, ktDestructuringDeclaration, firVariable, z, z2, (i & 128) != 0 ? new Function1() { // from class: vjb
            public final Object invoke(Object obj2) {
                return PsiConversionUtilsKt.a((FirVariable) obj2);
            }
        } : function1);
    }

    public static final FirBlock generateDestructuringBlock(DestructuringContext<KtDestructuringDeclarationEntry> destructuringContext, AbstractRawFirBuilder<?> abstractRawFirBuilder, FirModuleData firModuleData, KtDestructuringDeclaration ktDestructuringDeclaration, FirVariable firVariable, boolean z) {
        destructuringContext.getClass();
        abstractRawFirBuilder.getClass();
        firModuleData.getClass();
        ktDestructuringDeclaration.getClass();
        firVariable.getClass();
        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
        KtFakeSourceElementKind.DestructuringBlock destructuringBlock = KtFakeSourceElementKind.DestructuringBlock.INSTANCE;
        if (destructuringBlock == null) {
            bu8.a();
            return null;
        }
        firBlockBuilder.setSource(new KtFakePsiSourceElement(ktDestructuringDeclaration, destructuringBlock));
        addDestructuringVariables$default(destructuringContext, abstractRawFirBuilder, firBlockBuilder.getStatements(), firModuleData, ktDestructuringDeclaration, firVariable, z, false, null, 128, null);
        return firBlockBuilder.mo288build();
    }

    public static final FirVariable generateTemporaryVariable(FirModuleData firModuleData, KtSourceElement ktSourceElement, Name name, FirExpression firExpression, FirTypeRef firTypeRef, FirDeclarationOrigin firDeclarationOrigin, Function2<? super KtAnnotated, ? super FirAnnotationContainerBuilder, Unit> function2) {
        firModuleData.getClass();
        name.getClass();
        firExpression.getClass();
        firDeclarationOrigin.getClass();
        function2.getClass();
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        firPropertyBuilder.setSource(ktSourceElement);
        firPropertyBuilder.setModuleData(firModuleData);
        firPropertyBuilder.setOrigin(firDeclarationOrigin);
        if (firTypeRef == null) {
            firTypeRef = FirImplicitTypeRefImplWithoutSource.INSTANCE;
        }
        firPropertyBuilder.setReturnTypeRef(firTypeRef);
        firPropertyBuilder.setName(name);
        firPropertyBuilder.setInitializer(firExpression);
        firPropertyBuilder.setSymbol(new FirLocalPropertySymbol());
        firPropertyBuilder.setVar(false);
        firPropertyBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL));
        firPropertyBuilder.setLocal(true);
        KtAnnotated psi = KtSourceElementKt.getPsi(ktSourceElement);
        KtAnnotated ktAnnotated = psi instanceof KtAnnotated ? psi : null;
        if (ktAnnotated != null) {
            function2.invoke(ktAnnotated, firPropertyBuilder);
        }
        return firPropertyBuilder.mo288build();
    }

    public static /* synthetic */ FirVariable generateTemporaryVariable$default(FirModuleData firModuleData, KtSourceElement ktSourceElement, Name name, FirExpression firExpression, FirTypeRef firTypeRef, FirDeclarationOrigin firDeclarationOrigin, Function2 function2, int i, Object obj) {
        if ((i & 16) != 0) {
            firTypeRef = null;
        }
        FirTypeRef firTypeRef2 = firTypeRef;
        if ((i & 32) != 0) {
            firDeclarationOrigin = FirDeclarationOrigin.Source.INSTANCE;
        }
        return generateTemporaryVariable(firModuleData, ktSourceElement, name, firExpression, firTypeRef2, firDeclarationOrigin, function2);
    }

    public static final FirExpression toFirWhenCondition(KtWhenCondition ktWhenCondition, FirVariable firVariable, Function3<? super KtExpression, ? super String, ? super KtElement, ? extends FirExpression> function3, Function1<? super KtTypeReference, ? extends FirTypeRef> function1) {
        ktWhenCondition.getClass();
        function3.getClass();
        function1.getClass();
        KtFakeSourceElementKind.WhenGeneratedSubject whenGeneratedSubject = KtFakeSourceElementKind.WhenGeneratedSubject.INSTANCE;
        KtFakePsiSourceElement ktFakePsiSourceElement = null;
        if (whenGeneratedSubject == null) {
            bu8.a();
            return null;
        }
        KtFakePsiSourceElement ktFakePsiSourceElement2 = new KtFakePsiSourceElement(ktWhenCondition, whenGeneratedSubject);
        FirPropertyAccessExpression firPropertyAccessExpressionBuildWhenSubjectAccess = UtilsKt.buildWhenSubjectAccess(ktFakePsiSourceElement2, firVariable);
        if (ktWhenCondition instanceof KtWhenConditionWithExpression) {
            FirEqualityOperatorCallBuilder firEqualityOperatorCallBuilder = new FirEqualityOperatorCallBuilder();
            KtWhenConditionWithExpression ktWhenConditionWithExpression = (KtWhenConditionWithExpression) ktWhenCondition;
            PsiElement expression = ktWhenConditionWithExpression.getExpression();
            if (expression == null) {
                expression = ktWhenConditionWithExpression.getFirstChild();
            }
            if (expression != null) {
                KtFakeSourceElementKind.WhenCondition whenCondition = KtFakeSourceElementKind.WhenCondition.INSTANCE;
                if (whenCondition == null) {
                    bu8.a();
                    return null;
                }
                ktFakePsiSourceElement = new KtFakePsiSourceElement(expression, whenCondition);
            }
            firEqualityOperatorCallBuilder.setSource(ktFakePsiSourceElement);
            firEqualityOperatorCallBuilder.setOperation(FirOperation.EQ);
            firEqualityOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildBinaryArgumentList(firPropertyAccessExpressionBuildWhenSubjectAccess, (FirExpression) function3.invoke(ktWhenConditionWithExpression.getExpression(), "No expression in condition with expression", ktWhenCondition)));
            return firEqualityOperatorCallBuilder.mo288build();
        }
        if (ktWhenCondition instanceof KtWhenConditionInRange) {
            KtWhenConditionInRange ktWhenConditionInRange = (KtWhenConditionInRange) ktWhenCondition;
            FirExpression firExpression = (FirExpression) function3.invoke(ktWhenConditionInRange.getRangeExpression(), "No range in condition with range", ktWhenCondition);
            boolean zIsNegated = ktWhenConditionInRange.isNegated();
            KtRealSourceElementKind ktRealSourceElementKind = KtRealSourceElementKind.INSTANCE;
            if (ktRealSourceElementKind == null) {
                bu8.a();
                return null;
            }
            KtRealPsiSourceElement ktRealPsiSourceElement = new KtRealPsiSourceElement(ktWhenCondition);
            KtOperationReferenceExpression operationReference = ktWhenConditionInRange.getOperationReference();
            operationReference.getClass();
            if (ktRealSourceElementKind != null) {
                return ConversionUtilsKt.generateContainsOperation(firExpression, firPropertyAccessExpressionBuildWhenSubjectAccess, zIsNegated, ktRealPsiSourceElement, new KtRealPsiSourceElement(operationReference));
            }
            bu8.a();
            return null;
        }
        if (!(ktWhenCondition instanceof KtWhenConditionIsPattern)) {
            return FirExpressionUtilKt.buildErrorExpression$default(ktFakePsiSourceElement2, new ConeSyntaxDiagnostic("Unsupported when condition: " + ktWhenCondition.getClass()), null, 4, null);
        }
        FirTypeOperatorCallBuilder firTypeOperatorCallBuilder = new FirTypeOperatorCallBuilder();
        if (KtRealSourceElementKind.INSTANCE == null) {
            bu8.a();
            return null;
        }
        firTypeOperatorCallBuilder.setSource(new KtRealPsiSourceElement(ktWhenCondition));
        KtWhenConditionIsPattern ktWhenConditionIsPattern = (KtWhenConditionIsPattern) ktWhenCondition;
        firTypeOperatorCallBuilder.setOperation(ktWhenConditionIsPattern.isNegated() ? FirOperation.NOT_IS : FirOperation.IS);
        firTypeOperatorCallBuilder.setConversionTypeRef((FirTypeRef) function1.invoke(ktWhenConditionIsPattern.getTypeReference()));
        firTypeOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firPropertyAccessExpressionBuildWhenSubjectAccess));
        return firTypeOperatorCallBuilder.mo288build();
    }

    public static /* synthetic */ FirVariable generateTemporaryVariable$default(FirModuleData firModuleData, KtSourceElement ktSourceElement, String str, FirExpression firExpression, FirDeclarationOrigin firDeclarationOrigin, Function2 function2, int i, Object obj) {
        if ((i & 16) != 0) {
            firDeclarationOrigin = FirDeclarationOrigin.Source.INSTANCE;
        }
        return generateTemporaryVariable(firModuleData, ktSourceElement, str, firExpression, firDeclarationOrigin, function2);
    }

    public static final FirVariable generateTemporaryVariable(FirModuleData firModuleData, KtSourceElement ktSourceElement, String str, FirExpression firExpression, FirDeclarationOrigin firDeclarationOrigin, Function2<? super KtAnnotated, ? super FirAnnotationContainerBuilder, Unit> function2) {
        firModuleData.getClass();
        str.getClass();
        firExpression.getClass();
        firDeclarationOrigin.getClass();
        function2.getClass();
        Name nameSpecial = Name.special("<" + str + '>');
        nameSpecial.getClass();
        return generateTemporaryVariable(firModuleData, ktSourceElement, nameSpecial, firExpression, null, firDeclarationOrigin, function2);
    }

    public static final FirExpression toFirWhenCondition(KtWhenCondition[] ktWhenConditionArr, FirVariable firVariable, Function3<? super KtExpression, ? super String, ? super KtElement, ? extends FirExpression> function3, Function1<? super KtTypeReference, ? extends FirTypeRef> function1) {
        ktWhenConditionArr.getClass();
        function3.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList(ktWhenConditionArr.length);
        for (KtWhenCondition ktWhenCondition : ktWhenConditionArr) {
            arrayList.add(toFirWhenCondition(ktWhenCondition, firVariable, function3, function1));
        }
        if (!arrayList.isEmpty()) {
            return ConversionUtilsKt.buildBalancedOrExpressionTree$default(arrayList, 0, 0, 6, null);
        }
        w01.a("Failed requirement.");
        return null;
    }
}
