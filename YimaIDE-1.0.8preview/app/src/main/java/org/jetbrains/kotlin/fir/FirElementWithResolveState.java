package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirResolveState;
import org.jetbrains.kotlin.fir.declarations.ResolveStateAccess;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u0016\"\u0004\b\u0001\u0010\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u00170\u00192\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010\u001bJ3\u0010\u001c\u001a\u0002H\u001d\"\b\b\u0000\u0010\u001d*\u00020\u0002\"\u0004\b\u0001\u0010\u00172\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00170\u001f2\u0006\u0010\u001a\u001a\u0002H\u0017H\u0016¢\u0006\u0002\u0010 R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR(\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.r\u0002\b\u0010¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;", "Lorg/jetbrains/kotlin/fir/FirPureAbstractElement;", "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "resolveState", "Lorg/jetbrains/kotlin/fir/declarations/FirResolveState;", "getResolveState$annotations", "getResolveState", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolveState;", "setResolveState", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolveState;)V", "Lorg/jetbrains/kotlin/fir/declarations/ResolveStateAccess;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirElementWithResolveState extends FirPureAbstractElement implements FirElement {
    public volatile FirResolveState resolveState;

    @ResolveStateAccess
    public static /* synthetic */ void getResolveState$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitElementWithResolveState(this, data);
    }

    public abstract FirModuleData getModuleData();

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirResolveState getResolveState() throws UninitializedPropertyAccessException {
        FirResolveState firResolveState = this.resolveState;
        if (firResolveState != null) {
            return firResolveState;
        }
        Intrinsics.throwUninitializedPropertyAccessException("resolveState");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    public final void setResolveState(FirResolveState firResolveState) {
        firResolveState.getClass();
        this.resolveState = firResolveState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirElementWithResolveState firElementWithResolveStateTransformElementWithResolveState = transformer.transformElementWithResolveState(this, data);
        firElementWithResolveStateTransformElementWithResolveState.getClass();
        return firElementWithResolveStateTransformElementWithResolveState;
    }
}
