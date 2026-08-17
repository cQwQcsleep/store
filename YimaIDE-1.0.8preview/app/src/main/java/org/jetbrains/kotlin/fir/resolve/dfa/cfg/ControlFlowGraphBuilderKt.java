package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0010\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0004\"\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0004\"\u001e\u0010\b\u001a\u00020\u0001*\u00020\t8BX\u0082\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"memberShouldHaveGraph", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;", "getMemberShouldHaveGraph", "(Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;)Z", "isUsedInControlFlowGraphBuilderForClass", "isUsedInControlFlowGraphBuilderForFile", "isUsedInControlFlowGraphBuilderForScript", "hasNothingType", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getHasNothingType$annotations", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getHasNothingType", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "lastStatement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "booleanLiteralValue", "getBooleanLiteralValue", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraphBuilderKt {
    public static final Boolean getBooleanLiteralValue(FirExpression firExpression) {
        firExpression.getClass();
        FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getHasNothingType(FirExpression firExpression) {
        ConeKotlinType coneTypeOrNull = firExpression.getConeTypeOrNull();
        return coneTypeOrNull != null && ConeBuiltinTypeUtilsKt.isNothing(coneTypeOrNull);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getMemberShouldHaveGraph(FirControlFlowGraphOwner firControlFlowGraphOwner) {
        if (!(firControlFlowGraphOwner instanceof FirProperty)) {
            return ((firControlFlowGraphOwner instanceof FirField) && ((FirField) firControlFlowGraphOwner).getInitializer() == null) ? false : true;
        }
        FirProperty firProperty = (FirProperty) firControlFlowGraphOwner;
        return (firProperty.getInitializer() == null && firProperty.getDelegate() == null && !DeclarationAttributesKt.getHasExplicitBackingField(firProperty)) ? false : true;
    }

    public static final boolean isUsedInControlFlowGraphBuilderForClass(FirControlFlowGraphOwner firControlFlowGraphOwner) {
        firControlFlowGraphOwner.getClass();
        if ((firControlFlowGraphOwner instanceof FirProperty) || (firControlFlowGraphOwner instanceof FirField)) {
            return getMemberShouldHaveGraph(firControlFlowGraphOwner);
        }
        if ((firControlFlowGraphOwner instanceof FirConstructor) || (firControlFlowGraphOwner instanceof FirAnonymousInitializer)) {
            return true;
        }
        return ((firControlFlowGraphOwner instanceof FirFunction) || (firControlFlowGraphOwner instanceof FirClass)) ? false : true;
    }

    public static final boolean isUsedInControlFlowGraphBuilderForFile(FirControlFlowGraphOwner firControlFlowGraphOwner) {
        firControlFlowGraphOwner.getClass();
        if (firControlFlowGraphOwner instanceof FirProperty) {
            return getMemberShouldHaveGraph(firControlFlowGraphOwner);
        }
        return false;
    }

    public static final boolean isUsedInControlFlowGraphBuilderForScript(FirControlFlowGraphOwner firControlFlowGraphOwner) {
        firControlFlowGraphOwner.getClass();
        if ((firControlFlowGraphOwner instanceof FirProperty) || (firControlFlowGraphOwner instanceof FirField) || (firControlFlowGraphOwner instanceof FirAnonymousInitializer)) {
            return getMemberShouldHaveGraph(firControlFlowGraphOwner);
        }
        return false;
    }

    public static final FirStatement lastStatement(FirAnonymousFunction firAnonymousFunction) {
        List<FirStatement> statements;
        FirStatement firStatement;
        firAnonymousFunction.getClass();
        FirBlock body = firAnonymousFunction.getBody();
        if (body == null || (statements = body.getStatements()) == null || (firStatement = (FirStatement) CollectionsKt.lastOrNull(statements)) == null) {
            return null;
        }
        return lastStatement$unwrapBlocks(firStatement);
    }

    private static final FirStatement lastStatement$unwrapBlocks(FirStatement firStatement) {
        FirStatement firStatement2;
        FirStatement firStatementLastStatement$unwrapBlocks;
        return (!(firStatement instanceof FirBlock) || (firStatement2 = (FirStatement) CollectionsKt.lastOrNull(((FirBlock) firStatement).getStatements())) == null || (firStatementLastStatement$unwrapBlocks = lastStatement$unwrapBlocks(firStatement2)) == null) ? firStatement : firStatementLastStatement$unwrapBlocks;
    }
}
