package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirPartialModifierRenderer;", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "staticPolicy", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer$StaticPolicy;", "<init>", "(Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer$StaticPolicy;)V", "renderModifiers", Argument.Delimiters.none, "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPartialModifierRenderer extends FirModifierRenderer {
    private final FirModifierRenderer.StaticPolicy staticPolicy;

    public FirPartialModifierRenderer(FirModifierRenderer.StaticPolicy staticPolicy) {
        staticPolicy.getClass();
        this.staticPolicy = staticPolicy;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirModifierRenderer
    public void renderModifiers(FirMemberDeclaration memberDeclaration) {
        memberDeclaration.getClass();
        if (memberDeclaration.getStatus().isExpect()) {
            renderModifier("expect");
        }
        if (memberDeclaration.getStatus().isActual()) {
            renderModifier("actual");
        }
        if (memberDeclaration.getStatus().isStatic()) {
            memberDeclaration.getOrigin();
            renderModifier(this.staticPolicy.renderStatic(memberDeclaration));
        }
        if (memberDeclaration.getStatus().isInner()) {
            renderModifier("inner");
        }
        if (memberDeclaration.getStatus().isCompanion()) {
            renderModifier("companion");
        }
        if (memberDeclaration.getStatus().isData()) {
            renderModifier("data");
        }
        boolean z = (memberDeclaration instanceof FirRegularClass) && ((FirRegularClass) memberDeclaration).getClassKind() == ClassKind.INTERFACE && (memberDeclaration.getOrigin() instanceof FirDeclarationOrigin.Java);
        if (memberDeclaration.getStatus().isFun() && !z) {
            renderModifier("fun");
        }
        if (memberDeclaration.getStatus().isSuspend()) {
            renderModifier("suspend");
        }
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirModifierRenderer
    public void renderModifiers(FirPropertyAccessor propertyAccessor) {
        propertyAccessor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirModifierRenderer
    public void renderModifiers(FirBackingField backingField) {
        backingField.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirModifierRenderer
    public void renderModifiers(FirConstructor constructor) {
        constructor.getClass();
        if (constructor.getStatus().isExpect()) {
            renderModifier("expect");
        }
        if (constructor.getStatus().isActual()) {
            renderModifier("actual");
        }
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirModifierRenderer
    public void renderModifiers(FirAnonymousFunction anonymousFunction) {
        anonymousFunction.getClass();
        if (anonymousFunction.getStatus().isSuspend()) {
            renderModifier("suspend");
        }
    }
}
