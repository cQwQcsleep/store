package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.InaccessibleReceiverKind;
import org.jetbrains.kotlin.fir.expressions.builder.FirInaccessibleReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThisReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.references.builder.FirImplicitThisReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002\u001a\u000e\u0010\r\u001a\u00020\u000e*\u0006\u0012\u0002\b\u00030\n\"\u001d\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"receiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "inaccessibleReceiverKind", "Lorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;", "referencedMemberSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "getReferencedMemberSymbol", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;)Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "producesInapplicableCandidate", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReceiversKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[InaccessibleReceiverKind.values().length];
    }

    public static final FirBasedSymbol<?> getReferencedMemberSymbol(ImplicitReceiverValue<?> implicitReceiverValue) {
        implicitReceiverValue.getClass();
        FirThisOwnerSymbol boundSymbol = implicitReceiverValue.getBoundSymbol();
        if (boundSymbol instanceof FirReceiverParameterSymbol) {
            return ((FirReceiverParameterSymbol) boundSymbol).getContainingDeclarationSymbol();
        }
        boundSymbol.getClass();
        return boundSymbol;
    }

    public static final boolean producesInapplicableCandidate(ImplicitReceiverValue<?> implicitReceiverValue) {
        implicitReceiverValue.getClass();
        return (implicitReceiverValue instanceof InaccessibleImplicitReceiverValue) && !((InaccessibleImplicitReceiverValue) implicitReceiverValue).getKind().getProducesApplicableCandidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirExpression receiverExpression(FirThisOwnerSymbol<?> firThisOwnerSymbol, ConeKotlinType coneKotlinType, InaccessibleReceiverKind inaccessibleReceiverKind) {
        FirImplicitThisReferenceBuilder firImplicitThisReferenceBuilder = new FirImplicitThisReferenceBuilder();
        firImplicitThisReferenceBuilder.setBoundSymbol(firThisOwnerSymbol);
        FirThisReference firThisReferenceBuild = firImplicitThisReferenceBuilder.build();
        KtSourceElement source = firThisOwnerSymbol.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE, null, 2, null) : null;
        if ((inaccessibleReceiverKind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[inaccessibleReceiverKind.ordinal()]) == -1) {
            FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
            firThisReceiverExpressionBuilder.setSource(ktSourceElementFakeElement$default);
            firThisReceiverExpressionBuilder.setCalleeReference(firThisReferenceBuild);
            firThisReceiverExpressionBuilder.setConeTypeOrNull(coneKotlinType);
            firThisReceiverExpressionBuilder.setImplicit(true);
            return firThisReceiverExpressionBuilder.mo288build();
        }
        FirInaccessibleReceiverExpressionBuilder firInaccessibleReceiverExpressionBuilder = new FirInaccessibleReceiverExpressionBuilder();
        firInaccessibleReceiverExpressionBuilder.setSource(ktSourceElementFakeElement$default);
        firInaccessibleReceiverExpressionBuilder.setCalleeReference(firThisReferenceBuild);
        firInaccessibleReceiverExpressionBuilder.setConeTypeOrNull(coneKotlinType);
        firInaccessibleReceiverExpressionBuilder.setKind(inaccessibleReceiverKind);
        return firInaccessibleReceiverExpressionBuilder.mo288build();
    }
}
