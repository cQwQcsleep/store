package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u000256B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJA\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u0002H\u0002J\u001f\u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0018J%\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001cJ%\u0010\u001d\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001cJ9\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u00022\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u00172\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\"H\u0002J\u0014\u0010#\u001a\u00020\u001a*\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J \u0010'\u001a\u00020\u001a2\b\u0010(\u001a\u0004\u0018\u00010)2\f\u0010*\u001a\b\u0012\u0002\b\u0003\u0018\u00010+H\u0002J \u0010,\u001a\u00020-*\u00020\u00022\u0006\u0010%\u001a\u00020&2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002J,\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\f\u00101\u001a\b\u0012\u0004\u0012\u00020)0/2\u000e\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0/H\u0002J9\u00103\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00104¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkSuspendModifierForm", "reference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "formOfSuspendModifierForLambdaOrFun", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/SuspendCallArgumentKind;", "findEnclosingSuspendFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "isInScopeForDefaultParameterValues", Argument.Delimiters.none, "enclosingSuspendFunction", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)Z", "checkNonLocalReturnUsage", "checkRestrictsSuspension", "calledDeclarationSymbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "isCaseMissedByK1", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "isRestrictSuspensionReceiver", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "sameInstanceOfReceiver", "useSiteReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "declarationSiteReceiverOwnerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "computeReceiversInfo", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker$ReceiversInfo;", "zipReceiverInfo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker$ReceiverInfo;", "expressions", "types", "checkCallableReference", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "ReceiversInfo", "ReceiverInfo", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSuspendCallChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirSuspendCallChecker INSTANCE = new FirSuspendCallChecker();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker$ReceiverInfo;", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ReceiverInfo {
        private final FirExpression expression;
        private final ConeKotlinType type;

        public ReceiverInfo(FirExpression firExpression, ConeKotlinType coneKotlinType) {
            this.expression = firExpression;
            this.type = coneKotlinType;
        }

        public static /* synthetic */ ReceiverInfo copy$default(ReceiverInfo receiverInfo, FirExpression firExpression, ConeKotlinType coneKotlinType, int i, Object obj) {
            if ((i & 1) != 0) {
                firExpression = receiverInfo.expression;
            }
            if ((i & 2) != 0) {
                coneKotlinType = receiverInfo.type;
            }
            return receiverInfo.copy(firExpression, coneKotlinType);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirExpression getExpression() {
            return this.expression;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeKotlinType getType() {
            return this.type;
        }

        public final ReceiverInfo copy(FirExpression expression, ConeKotlinType type) {
            return new ReceiverInfo(expression, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReceiverInfo)) {
                return false;
            }
            ReceiverInfo receiverInfo = (ReceiverInfo) other;
            return Intrinsics.areEqual(this.expression, receiverInfo.expression) && Intrinsics.areEqual(this.type, receiverInfo.type);
        }

        public final FirExpression getExpression() {
            return this.expression;
        }

        public final ConeKotlinType getType() {
            return this.type;
        }

        public int hashCode() {
            FirExpression firExpression = this.expression;
            int iHashCode = (firExpression == null ? 0 : firExpression.hashCode()) * 31;
            ConeKotlinType coneKotlinType = this.type;
            return iHashCode + (coneKotlinType != null ? coneKotlinType.hashCode() : 0);
        }

        public String toString() {
            return "ReceiverInfo(expression=" + this.expression + ", type=" + this.type + ')';
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J/\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker$ReceiversInfo;", Argument.Delimiters.none, "dispatchReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "extensionReceiver", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker$ReceiverInfo;", "contextParameters", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker$ReceiverInfo;Ljava/util/List;)V", "getDispatchReceiverExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getExtensionReceiver", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuspendCallChecker$ReceiverInfo;", "getContextParameters", "()Ljava/util/List;", "expressions", "getExpressions", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ReceiversInfo {
        private final List<ReceiverInfo> contextParameters;
        private final FirExpression dispatchReceiverExpression;
        private final List<FirExpression> expressions;
        private final ReceiverInfo extensionReceiver;

        public ReceiversInfo(FirExpression firExpression, ReceiverInfo receiverInfo, List<ReceiverInfo> list) {
            receiverInfo.getClass();
            list.getClass();
            this.dispatchReceiverExpression = firExpression;
            this.extensionReceiver = receiverInfo;
            this.contextParameters = list;
            List listListOfNotNull = CollectionsKt.listOfNotNull(new FirExpression[]{firExpression, receiverInfo.getExpression()});
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                FirExpression expression = ((ReceiverInfo) it.next()).getExpression();
                if (expression != null) {
                    arrayList.add(expression);
                }
            }
            this.expressions = CollectionsKt.plus(listListOfNotNull, arrayList);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ReceiversInfo copy$default(ReceiversInfo receiversInfo, FirExpression firExpression, ReceiverInfo receiverInfo, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                firExpression = receiversInfo.dispatchReceiverExpression;
            }
            if ((i & 2) != 0) {
                receiverInfo = receiversInfo.extensionReceiver;
            }
            if ((i & 4) != 0) {
                list = receiversInfo.contextParameters;
            }
            return receiversInfo.copy(firExpression, receiverInfo, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirExpression getDispatchReceiverExpression() {
            return this.dispatchReceiverExpression;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ReceiverInfo getExtensionReceiver() {
            return this.extensionReceiver;
        }

        public final List<ReceiverInfo> component3() {
            return this.contextParameters;
        }

        public final ReceiversInfo copy(FirExpression dispatchReceiverExpression, ReceiverInfo extensionReceiver, List<ReceiverInfo> contextParameters) {
            extensionReceiver.getClass();
            contextParameters.getClass();
            return new ReceiversInfo(dispatchReceiverExpression, extensionReceiver, contextParameters);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReceiversInfo)) {
                return false;
            }
            ReceiversInfo receiversInfo = (ReceiversInfo) other;
            return Intrinsics.areEqual(this.dispatchReceiverExpression, receiversInfo.dispatchReceiverExpression) && Intrinsics.areEqual(this.extensionReceiver, receiversInfo.extensionReceiver) && Intrinsics.areEqual(this.contextParameters, receiversInfo.contextParameters);
        }

        public final List<ReceiverInfo> getContextParameters() {
            return this.contextParameters;
        }

        public final FirExpression getDispatchReceiverExpression() {
            return this.dispatchReceiverExpression;
        }

        public final List<FirExpression> getExpressions() {
            return this.expressions;
        }

        public final ReceiverInfo getExtensionReceiver() {
            return this.extensionReceiver;
        }

        public int hashCode() {
            FirExpression firExpression = this.dispatchReceiverExpression;
            return ((((firExpression == null ? 0 : firExpression.hashCode()) * 31) + this.extensionReceiver.hashCode()) * 31) + this.contextParameters.hashCode();
        }

        public String toString() {
            return "ReceiversInfo(dispatchReceiverExpression=" + this.dispatchReceiverExpression + ", extensionReceiver=" + this.extensionReceiver + ", contextParameters=" + this.contextParameters + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SuspendCallArgumentKind.values().length];
            try {
                iArr[SuspendCallArgumentKind.FUN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SuspendCallArgumentKind.LAMBDA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirSuspendCallChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkCallableReference(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirQualifiedAccessExpression firQualifiedAccessExpression, FirCallableSymbol<?> firCallableSymbol) {
        if (Intrinsics.areEqual(firCallableSymbol.getCallableId(), StandardClassIds.Callables.INSTANCE.getCoroutineContext())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getCalleeReference().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Callable reference to suspend property is unsupported.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final boolean checkNonLocalReturnUsage(CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol) {
        for (FirBasedSymbol firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if (Intrinsics.areEqual(firBasedSymbol, firFunctionSymbol)) {
                return true;
            }
            if (!(firBasedSymbol instanceof FirPropertySymbol) || !(firBasedSymbol instanceof FirLocalPropertySymbol)) {
                if (!(firBasedSymbol instanceof FirAnonymousFunctionSymbol) || !((FirAnonymousFunctionSymbol) firBasedSymbol).getInlineStatus().getReturnAllowed()) {
                    if (!(firBasedSymbol instanceof FirValueParameterSymbol)) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean checkRestrictsSuspension(CheckerContext checkerContext, FirQualifiedAccessExpression firQualifiedAccessExpression, FirFunctionSymbol<?> firFunctionSymbol, FirCallableSymbol<?> firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType type;
        ConeClassLikeLookupTag classLikeLookupTagIfAny;
        if ((firQualifiedAccessExpression instanceof FirFunctionCall) && isCaseMissedByK1((FirFunctionCall) firQualifiedAccessExpression)) {
            return true;
        }
        FirSession session = checkerContext.getSession();
        ConeSimpleKotlinType dispatchReceiverType = firFunctionSymbol.getDispatchReceiverType();
        FirReceiverParameterSymbol firReceiverParameterSymbol = null;
        FirBasedSymbol<?> regularClassSymbol = (dispatchReceiverType == null || (classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(dispatchReceiverType)) == null) ? null : ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, classLikeLookupTagIfAny);
        FirReceiverParameterSymbol receiverParameterSymbol = firFunctionSymbol.getReceiverParameterSymbol();
        List<FirValueParameterSymbol> contextParameterSymbols = firFunctionSymbol.getContextParameterSymbols();
        ReceiversInfo receiversInfoComputeReceiversInfo = computeReceiversInfo(firQualifiedAccessExpression, session, firCallableSymbol);
        for (FirExpression firExpression : receiversInfoComputeReceiversInfo.getExpressions()) {
            if (isRestrictSuspensionReceiver(FirTypeUtilsKt.getResolvedType(firExpression), session) && !sameInstanceOfReceiver(firExpression, regularClassSymbol) && !sameInstanceOfReceiver(firExpression, receiverParameterSymbol)) {
                List<FirValueParameterSymbol> list = contextParameterSymbols;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        if (INSTANCE.sameInstanceOfReceiver(firExpression, (FirValueParameterSymbol) it.next())) {
                        }
                    }
                }
                return false;
            }
        }
        if (receiverParameterSymbol != null && INSTANCE.isRestrictSuspensionReceiver(receiverParameterSymbol.getResolvedType(), session)) {
            firReceiverParameterSymbol = receiverParameterSymbol;
        }
        List listListOfNotNull = CollectionsKt.listOfNotNull(firReceiverParameterSymbol);
        ArrayList arrayList = new ArrayList();
        for (Object obj : contextParameterSymbols) {
            if (INSTANCE.isRestrictSuspensionReceiver(((FirValueParameterSymbol) obj).getResolvedReturnType(), session)) {
                arrayList.add(obj);
            }
        }
        List listPlus = CollectionsKt.plus(listListOfNotNull, arrayList);
        int size = listPlus.size();
        if (size == 0) {
            return true;
        }
        if (size != 1) {
            return false;
        }
        FirBasedSymbol<?> firBasedSymbol = (FirBasedSymbol) CollectionsKt.single(listPlus);
        if (sameInstanceOfReceiver(receiversInfoComputeReceiversInfo.getDispatchReceiverExpression(), firBasedSymbol)) {
            return true;
        }
        for (ReceiverInfo receiverInfo : CollectionsKt.plus(CollectionsKt.listOf(receiversInfoComputeReceiversInfo.getExtensionReceiver()), receiversInfoComputeReceiversInfo.getContextParameters())) {
            if (sameInstanceOfReceiver(receiverInfo.getExpression(), firBasedSymbol) && (type = receiverInfo.getType()) != null && isRestrictSuspensionReceiver(type, session)) {
                return true;
            }
        }
        return false;
    }

    private final void checkSuspendModifierForm(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression, FirResolvedNamedReference firResolvedNamedReference, FirCallableSymbol<?> firCallableSymbol) {
        CallableId callableId = firCallableSymbol.getCallableId();
        StandardClassIds.Callables callables = StandardClassIds.Callables.INSTANCE;
        if (Intrinsics.areEqual(callableId, callables.getSuspend())) {
            if (Intrinsics.areEqual(firResolvedNamedReference.getName(), callables.getSuspend().getCallableName()) && firQualifiedAccessExpression.getExplicitReceiver() == null && formOfSuspendModifierForLambdaOrFun(firQualifiedAccessExpression) != null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirErrors.INSTANCE.getNON_MODIFIER_FORM_FOR_BUILT_IN_SUSPEND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (Intrinsics.areEqual(firResolvedNamedReference.getName(), callables.getSuspend().getCallableName())) {
            SuspendCallArgumentKind suspendCallArgumentKindFormOfSuspendModifierForLambdaOrFun = formOfSuspendModifierForLambdaOrFun(firQualifiedAccessExpression);
            int i = suspendCallArgumentKindFormOfSuspendModifierForLambdaOrFun == null ? -1 : WhenMappings.$EnumSwitchMapping$0[suspendCallArgumentKindFormOfSuspendModifierForLambdaOrFun.ordinal()];
            if (i != -1) {
                if (i == 1) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirErrors.INSTANCE.getMODIFIER_FORM_FOR_NON_BUILT_IN_SUSPEND_FUN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (i == 2) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirErrors.INSTANCE.getMODIFIER_FORM_FOR_NON_BUILT_IN_SUSPEND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else {
                    bu8.a();
                }
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final ReceiversInfo computeReceiversInfo(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSession firSession, FirCallableSymbol<?> firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        if (!(firQualifiedAccessExpression instanceof FirImplicitInvokeCall) || dispatchReceiver == null || !FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType(FirTypeUtilsKt.getResolvedType(dispatchReceiver), firSession)) {
            ReceiverInfo receiverInfo = new ReceiverInfo(firQualifiedAccessExpression.getExtensionReceiver(), firCallableSymbol.getResolvedReceiverType());
            List<FirExpression> contextArguments = firQualifiedAccessExpression.getContextArguments();
            List<FirValueParameterSymbol> contextParameterSymbols = firCallableSymbol.getContextParameterSymbols();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameterSymbols, 10));
            Iterator<T> it = contextParameterSymbols.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirValueParameterSymbol) it.next()).getResolvedReturnType());
            }
            return new ReceiversInfo(dispatchReceiver, receiverInfo, zipReceiverInfo(contextArguments, arrayList));
        }
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver);
        if (!CompilerConeAttributesKt.getHasContextParameters(resolvedType) && !CompilerConeAttributesKt.isExtensionFunctionType(resolvedType)) {
            return new ReceiversInfo(null, new ReceiverInfo(null, null), CollectionsKt.emptyList());
        }
        int contextParameterNumberForFunctionType = CompilerConeAttributesKt.getContextParameterNumberForFunctionType(resolvedType);
        FirImplicitInvokeCall firImplicitInvokeCall = (FirImplicitInvokeCall) firQualifiedAccessExpression;
        List<? extends FirExpression> listTake = CollectionsKt.take(firImplicitInvokeCall.getArgumentList().getArguments(), contextParameterNumberForFunctionType);
        List<ConeKotlinType> listTake2 = ArraysKt.take(resolvedType.getTypeArguments(), contextParameterNumberForFunctionType);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listTake2, 10));
        for (ConeKotlinType coneKotlinType : listTake2) {
            arrayList2.add(coneKotlinType instanceof ConeKotlinType ? coneKotlinType : null);
        }
        List<ReceiverInfo> listZipReceiverInfo = zipReceiverInfo(listTake, arrayList2);
        FirExpression firExpression = (FirExpression) CollectionsKt.getOrNull(firImplicitInvokeCall.getArgumentList().getArguments(), contextParameterNumberForFunctionType);
        Object orNull = ArraysKt.getOrNull(resolvedType.getTypeArguments(), contextParameterNumberForFunctionType);
        return new ReceiversInfo(null, new ReceiverInfo(firExpression, orNull instanceof ConeKotlinType ? (ConeKotlinType) orNull : null), listZipReceiverInfo);
    }

    private final FirFunctionSymbol<?> findEnclosingSuspendFunction(CheckerContext checkerContext) {
        FirBasedSymbol<?> firBasedSymbolPrevious;
        boolean zIsSuspend;
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        ListIterator<FirBasedSymbol<?>> listIterator = containingDeclarations.listIterator(containingDeclarations.size());
        do {
            if (!listIterator.hasPrevious()) {
                firBasedSymbolPrevious = null;
                break;
            }
            firBasedSymbolPrevious = listIterator.previous();
            FirBasedSymbol<?> firBasedSymbol = firBasedSymbolPrevious;
            if (firBasedSymbol instanceof FirAnonymousFunctionSymbol) {
                FirAnonymousFunctionSymbol firAnonymousFunctionSymbol = (FirAnonymousFunctionSymbol) firBasedSymbol;
                zIsSuspend = firAnonymousFunctionSymbol.isLambda() ? FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType(firAnonymousFunctionSymbol.getResolvedTypeRef().getConeType(), checkerContext.getSession()) : ((FirCallableSymbol) firBasedSymbol).getRawStatus().isSuspend();
            } else {
                zIsSuspend = firBasedSymbol instanceof FirNamedFunctionSymbol ? ((FirCallableSymbol) firBasedSymbol).getRawStatus().isSuspend() : false;
            }
        } while (!zIsSuspend);
        if (firBasedSymbolPrevious instanceof FirFunctionSymbol) {
            return (FirFunctionSymbol) firBasedSymbolPrevious;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x007e  */
    /* JADX WARN: Code duplicated, block: B:33:0x008e  */
    /* JADX WARN: Code duplicated, block: B:35:0x0091  */
    /* JADX WARN: Multi-variable type inference failed */
    private final SuspendCallArgumentKind formOfSuspendModifierForLambdaOrFun(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        KtSourceElement child$default;
        LighterASTNode lighterASTNode;
        KtSourceElement ktSourceElement;
        KtSourceElement child$default2;
        KtSourceElement child$default3;
        IElementType tokenType = null;
        if (!(firQualifiedAccessExpression instanceof FirFunctionCall)) {
            return null;
        }
        FirFunctionCall firFunctionCall = (FirFunctionCall) firQualifiedAccessExpression;
        if (firFunctionCall.getCalleeReference() instanceof FirResolvedCallableReference) {
            return null;
        }
        List<FirTypeProjection> typeArguments = firFunctionCall.getTypeArguments();
        if (!(typeArguments instanceof Collection) || !typeArguments.isEmpty()) {
            Iterator<T> it = typeArguments.iterator();
            while (it.hasNext()) {
                if (FirHelpersKt.isExplicit((FirTypeProjection) it.next())) {
                    return null;
                }
            }
        }
        FirCall firCall = (FirCall) firQualifiedAccessExpression;
        FirExpression firExpression = (FirExpression) CollectionsKt.singleOrNull(firCall.getArgumentList().getArguments());
        if ((firExpression instanceof FirAnonymousFunctionExpression) && ((FirAnonymousFunctionExpression) firExpression).getIsTrailingLambda()) {
            if (firFunctionCall.getExplicitReceiver() == null) {
                child$default2 = firFunctionCall.getSource();
            } else {
                KtSourceElement source = firFunctionCall.getSource();
                if (source != null) {
                    IElementType iElementType = KtNodeTypes.CALL_EXPRESSION;
                    iElementType.getClass();
                    child$default2 = FirSourceUtilsKt.getChild$default(source, iElementType, 1, 1, false, 8, (Object) null);
                } else {
                    ktSourceElement = null;
                }
                if (ktSourceElement != null) {
                    IElementType iElementType2 = KtNodeTypes.VALUE_ARGUMENT_LIST;
                    iElementType2.getClass();
                    child$default3 = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType2, 0, 1, false, 10, (Object) null);
                } else {
                    child$default3 = null;
                }
                if (child$default3 == null) {
                    return SuspendCallArgumentKind.LAMBDA;
                }
            }
            ktSourceElement = child$default2;
            if (ktSourceElement != null) {
                IElementType iElementType3 = KtNodeTypes.VALUE_ARGUMENT_LIST;
                iElementType3.getClass();
                child$default3 = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType3, 0, 1, false, 10, (Object) null);
            } else {
                child$default3 = null;
            }
            if (child$default3 == null) {
                return SuspendCallArgumentKind.LAMBDA;
            }
        }
        if (firFunctionCall.getOrigin() == FirFunctionCallOrigin.Infix) {
            FirExpression firExpression2 = (FirExpression) CollectionsKt.lastOrNull(firCall.getArgumentList().getArguments());
            if (firExpression2 instanceof FirAnonymousFunctionExpression) {
                KtSourceElement source2 = firFunctionCall.getSource();
                if (source2 != null) {
                    IElementType iElementType4 = KtNodeTypes.PARENTHESIZED;
                    iElementType4.getClass();
                    child$default = FirSourceUtilsKt.getChild$default(source2, iElementType4, 0, 1, false, 10, (Object) null);
                } else {
                    child$default = null;
                }
                if (child$default == null) {
                    KtSourceElement source3 = ((FirAnonymousFunctionExpression) firExpression2).getSource();
                    if (source3 != null && (lighterASTNode = source3.getLighterASTNode()) != null) {
                        tokenType = lighterASTNode.getTokenType();
                    }
                    return Intrinsics.areEqual(tokenType, KtStubElementTypes.FUNCTION) ? SuspendCallArgumentKind.FUN : SuspendCallArgumentKind.LAMBDA;
                }
            }
        }
        return null;
    }

    private final boolean isCaseMissedByK1(FirFunctionCall expression) {
        FirExpression explicitReceiver;
        ConeKotlinType resolvedType;
        KtSourceElement source;
        LighterASTNode lighterASTNode;
        List children;
        if (!(expression instanceof FirImplicitInvokeCall) || (explicitReceiver = ((FirImplicitInvokeCall) expression).getExplicitReceiver()) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(explicitReceiver)) == null || !CompilerConeAttributesKt.isExtensionFunctionType(resolvedType) || (source = ((FirImplicitInvokeCall) expression).getSource()) == null) {
            return false;
        }
        IElementType iElementType = KtNodeTypes.VALUE_ARGUMENT_LIST;
        iElementType.getClass();
        KtSourceElement child$default = FirSourceUtilsKt.getChild$default(source, iElementType, 0, 1, false, 10, (Object) null);
        if (child$default != null && (lighterASTNode = child$default.getLighterASTNode()) != null && (children = LightTreeUtilsKt.getChildren(lighterASTNode, source.getTreeStructure())) != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : children) {
                if (Intrinsics.areEqual(((LighterASTNode) obj).getTokenType(), KtNodeTypes.VALUE_ARGUMENT)) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.size() != expression.getArgumentList().getArguments().size() - 1) {
                return true;
            }
        }
        return false;
    }

    private final boolean isInScopeForDefaultParameterValues(CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol) {
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols();
        for (FirBasedSymbol firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if ((firBasedSymbol instanceof FirValueParameterSymbol) && valueParameterSymbols.contains(firBasedSymbol) && ((FirValueParameterSymbol) firBasedSymbol).getHasDefaultValue()) {
                return true;
            }
            if (!(firBasedSymbol instanceof FirAnonymousFunctionSymbol) || ((FirAnonymousFunctionSymbol) firBasedSymbol).getInlineStatus() != InlineStatus.Inline) {
                if ((firBasedSymbol instanceof FirFunctionSymbol) && !((FirCallableSymbol) firBasedSymbol).getRawStatus().isInline()) {
                    break;
                }
            }
        }
        return false;
    }

    private final boolean isRestrictSuspensionReceiver(ConeKotlinType coneKotlinType, FirSession firSession) {
        if (!(coneKotlinType instanceof ConeClassLikeType)) {
            if (coneKotlinType instanceof ConeTypeParameterType) {
                List<FirResolvedTypeRef> resolvedBounds = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getResolvedBounds();
                if ((resolvedBounds instanceof Collection) && resolvedBounds.isEmpty()) {
                    return false;
                }
                Iterator<T> it = resolvedBounds.iterator();
                while (it.hasNext()) {
                    if (INSTANCE.isRestrictSuspensionReceiver(((FirResolvedTypeRef) it.next()).getConeType(), firSession)) {
                        return true;
                    }
                }
            }
            return false;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) coneKotlinType, firSession, (Function1) null, 2, (Object) null).getLookupTag(), firSession);
        if (regularClassSymbol == null) {
            return false;
        }
        if (FirAnnotationUtilsKt.hasAnnotationWithClassId(regularClassSymbol, StandardClassIds$Annotations.INSTANCE.getRestrictsSuspension(), firSession)) {
            return true;
        }
        List<ConeKotlinType> resolvedSuperTypes = regularClassSymbol.getResolvedSuperTypes();
        if ((resolvedSuperTypes instanceof Collection) && resolvedSuperTypes.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = resolvedSuperTypes.iterator();
        while (it2.hasNext()) {
            if (INSTANCE.isRestrictSuspensionReceiver((ConeKotlinType) it2.next(), firSession)) {
                return true;
            }
        }
        return false;
    }

    private final boolean sameInstanceOfReceiver(FirExpression useSiteReceiverExpression, FirBasedSymbol<?> declarationSiteReceiverOwnerSymbol) {
        FirAnnotationContainer firAnnotationContainerUnwrapSmartcastExpression = useSiteReceiverExpression != null ? FirExpressionUtilKt.unwrapSmartcastExpression(useSiteReceiverExpression) : null;
        if (firAnnotationContainerUnwrapSmartcastExpression == null || declarationSiteReceiverOwnerSymbol == null) {
            return false;
        }
        if (firAnnotationContainerUnwrapSmartcastExpression instanceof FirThisReceiverExpression) {
            return Intrinsics.areEqual(((FirThisReceiverExpression) firAnnotationContainerUnwrapSmartcastExpression).getCalleeReference().getBoundSymbol(), declarationSiteReceiverOwnerSymbol);
        }
        return (firAnnotationContainerUnwrapSmartcastExpression instanceof FirPropertyAccessExpression) && (declarationSiteReceiverOwnerSymbol instanceof FirValueParameterSymbol) && DeclarationUtilsKt.isContextParameter(declarationSiteReceiverOwnerSymbol) && Intrinsics.areEqual(org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol((FirResolvable) firAnnotationContainerUnwrapSmartcastExpression), declarationSiteReceiverOwnerSymbol);
    }

    private final List<ReceiverInfo> zipReceiverInfo(List<? extends FirExpression> expressions, List<? extends ConeKotlinType> types) {
        List<? extends FirExpression> list = expressions;
        List<? extends ConeKotlinType> list2 = types;
        Iterator<T> it = list.iterator();
        Iterator<T> it2 = list2.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(list, 10), CollectionsKt.collectionSizeOrDefault(list2, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(new ReceiverInfo((FirExpression) it.next(), (ConeKotlinType) it2.next()));
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firQualifiedAccessExpression.getCalleeReference());
        if (resolved == null) {
            return;
        }
        FirBasedSymbol<?> resolvedSymbol = resolved.getResolvedSymbol();
        FirCallableSymbol<?> firCallableSymbol = resolvedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) resolvedSymbol : null;
        if (firCallableSymbol == null) {
            return;
        }
        Name name = resolved.getName();
        StandardClassIds.Callables callables = StandardClassIds.Callables.INSTANCE;
        if (Intrinsics.areEqual(name, callables.getSuspend().getCallableName()) || ((firCallableSymbol instanceof FirNamedFunctionSymbol) && Intrinsics.areEqual(((FirNamedFunctionSymbol) firCallableSymbol).getName(), callables.getSuspend().getCallableName()))) {
            checkSuspendModifierForm(checkerContext, diagnosticReporter, firQualifiedAccessExpression, resolved, firCallableSymbol);
        }
        if (resolved instanceof FirResolvedCallableReference) {
            checkCallableReference(diagnosticReporter, checkerContext, firQualifiedAccessExpression, firCallableSymbol);
            return;
        }
        boolean z = firCallableSymbol instanceof FirNamedFunctionSymbol;
        if (z) {
            if (!firCallableSymbol.getRawStatus().isSuspend()) {
                return;
            }
        } else if (!(firCallableSymbol instanceof FirPropertySymbol) || !Intrinsics.areEqual(((FirPropertySymbol) firCallableSymbol).getCallableId(), callables.getCoroutineContext())) {
            return;
        }
        FirFunctionSymbol<?> firFunctionSymbolFindEnclosingSuspendFunction = findEnclosingSuspendFunction(checkerContext);
        if (firFunctionSymbolFindEnclosingSuspendFunction == null) {
            if (z) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_SUSPEND_FUNCTION_CALL(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            } else {
                if (firCallableSymbol instanceof FirPropertySymbol) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_SUSPEND_PROPERTY_ACCESS(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
                return;
            }
        }
        if (!checkNonLocalReturnUsage(checkerContext, firFunctionSymbolFindEnclosingSuspendFunction)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirErrors.INSTANCE.getNON_LOCAL_SUSPENSION_POINT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (isInScopeForDefaultParameterValues(checkerContext, firFunctionSymbolFindEnclosingSuspendFunction)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Suspend function call in default parameter value is unsupported.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (checkRestrictsSuspension(checkerContext, firQualifiedAccessExpression, firFunctionSymbolFindEnclosingSuspendFunction, firCallableSymbol)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirErrors.INSTANCE.getILLEGAL_RESTRICTED_SUSPENDING_FUNCTION_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
