package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\u000bJ\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitContextParameterValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "boundSymbol", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "originalType", "mutable", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)V", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getBoundSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "computeOriginalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "createSnapshot", "keepMutable", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitContextParameterValue extends ImplicitValue<FirValueParameterSymbol> {
    private final FirValueParameterSymbol boundSymbol;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ImplicitContextParameterValue(FirValueParameterSymbol firValueParameterSymbol, ConeKotlinType coneKotlinType) {
        this(firValueParameterSymbol, coneKotlinType, coneKotlinType, true);
        firValueParameterSymbol.getClass();
        coneKotlinType.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public FirExpression computeOriginalExpression() {
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        KtSourceElement source = getBoundSymbol().getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE, null, 2, null) : null;
        firPropertyAccessExpressionBuilder.setSource(ktSourceElementFakeElement$default);
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setSource(ktSourceElementFakeElement$default);
        firResolvedNamedReferenceBuilder.setName(getBoundSymbol().getName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(getBoundSymbol());
        firPropertyAccessExpressionBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
        firPropertyAccessExpressionBuilder.setConeTypeOrNull(getOriginalType());
        return firPropertyAccessExpressionBuilder.mo289build();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public ImplicitContextParameterValue createSnapshot(boolean keepMutable) {
        return new ImplicitContextParameterValue(getBoundSymbol(), getType(), getOriginalType(), keepMutable);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public FirValueParameterSymbol getBoundSymbol() {
        return this.boundSymbol;
    }

    private ImplicitContextParameterValue(FirValueParameterSymbol firValueParameterSymbol, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z) {
        super(coneKotlinType, coneKotlinType2, z, null);
        this.boundSymbol = firValueParameterSymbol;
    }
}
