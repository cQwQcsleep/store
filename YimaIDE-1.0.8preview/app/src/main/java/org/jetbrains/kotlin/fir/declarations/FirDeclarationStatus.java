package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J5\u0010-\u001a\u0002H.\"\u0004\b\u0000\u0010.\"\u0004\b\u0001\u0010/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u0002H.\u0012\u0004\u0012\u0002H/012\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00103J3\u00104\u001a\u0002H5\"\b\b\u0000\u00105*\u00020\u0001\"\u0004\b\u0001\u0010/2\f\u00106\u001a\b\u0012\u0004\u0012\u0002H/072\u0006\u00102\u001a\u0002H/H\u0016¢\u0006\u0002\u00108R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0012\u0010\u0012\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0012\u0010\u0013\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0012\u0010\u0014\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0012\u0010\u0015\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010R\u0012\u0010\u0016\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0012\u0010\u0017\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0010R\u0012\u0010\u0018\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0010R\u0012\u0010\u0019\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0010R\u0012\u0010\u001a\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0010R\u0012\u0010\u001b\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0010R\u0012\u0010\u001c\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0010R\u0012\u0010\u001d\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0010R\u0012\u0010\u001e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0010R\u0012\u0010\u001f\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0010R\u0012\u0010 \u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0010R\u0012\u0010!\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0010R\u0012\u0010\"\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0010R\u0012\u0010#\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0010R\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\tR\u0012\u0010+\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00069À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "Lorg/jetbrains/kotlin/fir/FirElement;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getModality", "()Lorg/jetbrains/kotlin/descriptors/Modality;", "isExpect", Argument.Delimiters.none, "()Z", "isActual", "isOverride", "isOperator", "isInfix", "isInline", "isValue", "isTailRec", "isExternal", "isConst", "isLateInit", "isInner", "isCompanion", "isData", "isSuspend", "isStatic", "isFromSealedClass", "isFromEnumClass", "isFun", "hasStableParameterNames", "getHasStableParameterNames", "returnValueStatus", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "getReturnValueStatus", "()Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "defaultVisibility", "getDefaultVisibility", "defaultModality", "getDefaultModality", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirDeclarationStatus extends FirElement {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.FirElement
    default <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitDeclarationStatus(this, data);
    }

    Modality getDefaultModality();

    Visibility getDefaultVisibility();

    boolean getHasStableParameterNames();

    Modality getModality();

    ReturnValueStatus getReturnValueStatus();

    @Override // org.jetbrains.kotlin.fir.FirElement
    KtSourceElement getSource();

    Visibility getVisibility();

    boolean isActual();

    boolean isCompanion();

    boolean isConst();

    boolean isData();

    boolean isExpect();

    boolean isExternal();

    boolean isFromEnumClass();

    boolean isFromSealedClass();

    boolean isFun();

    boolean isInfix();

    boolean isInline();

    boolean isInner();

    boolean isLateInit();

    boolean isOperator();

    boolean isOverride();

    boolean isStatic();

    boolean isSuspend();

    boolean isTailRec();

    boolean isValue();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.FirElement
    default <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirDeclarationStatus firDeclarationStatusTransformDeclarationStatus = transformer.transformDeclarationStatus(this, data);
        firDeclarationStatusTransformDeclarationStatus.getClass();
        return firDeclarationStatusTransformDeclarationStatus;
    }
}
