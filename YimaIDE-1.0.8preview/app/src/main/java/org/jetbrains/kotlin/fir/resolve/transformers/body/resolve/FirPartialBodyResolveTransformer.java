package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010 \u001a\u0002H!\"\b\b\u0000\u0010!*\u00020\"2\u0006\u0010#\u001a\u0002H!2\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0002\u0010&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\t8Æ\u0002¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000f8Æ\u0002¢\u0006\f\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u00148Æ\u0002¢\u0006\f\u0012\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0016\u0010\u0017R)\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198V@QX\u0096\u000e\u0082\u0001\u0002\b\u001f¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirPartialBodyResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer;", "transformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;)V", "getTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "context", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "getContext$annotations", "()V", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "getComponents$annotations", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "getResolutionContext$annotations", "getResolutionContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "value", Argument.Delimiters.none, "implicitTypeOnly", "getImplicitTypeOnly", "()Z", "setImplicitTypeOnly$org_jetbrains_kotlin_resolve", "(Z)V", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPartialBodyResolveTransformer extends FirAbstractBodyResolveTransformer {
    private final FirAbstractBodyResolveTransformerDispatcher transformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirPartialBodyResolveTransformer(FirAbstractBodyResolveTransformerDispatcher firAbstractBodyResolveTransformerDispatcher) {
        super(firAbstractBodyResolveTransformerDispatcher.getTransformerPhase());
        firAbstractBodyResolveTransformerDispatcher.getClass();
        this.transformer = firAbstractBodyResolveTransformerDispatcher;
    }

    public static /* synthetic */ void getComponents$annotations() {
    }

    public static /* synthetic */ void getContext$annotations() {
    }

    public static /* synthetic */ void getResolutionContext$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents getComponents() {
        return getTransformer().getComponents();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public final BodyResolveContext getContext() {
        return getTransformer().getContext();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public boolean getImplicitTypeOnly() {
        return this.transformer.getImplicitTypeOnly();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public final ResolutionContext getResolutionContext() {
        return getTransformer().getResolutionContext();
    }

    public final FirAbstractBodyResolveTransformerDispatcher getTransformer() {
        return this.transformer;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    @PrivateForInline
    public void setImplicitTypeOnly$org_jetbrains_kotlin_resolve(boolean z) {
        this.transformer.setImplicitTypeOnly$org_jetbrains_kotlin_resolve(z);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, ResolutionMode data) {
        element.getClass();
        data.getClass();
        return (E) element.transform(this.transformer, data);
    }
}
