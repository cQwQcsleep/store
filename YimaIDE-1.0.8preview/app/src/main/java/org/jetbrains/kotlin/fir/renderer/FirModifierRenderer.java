package org.jetbrains.kotlin.fir.renderer;

import java.util.Set;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 )2\u00020\u0001:\u0002()B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u000f2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0018\u0010#\u001a\u00020\"*\u00020$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&H\u0014J\u000e\u0010'\u001a\u0004\u0018\u00010\"*\u00020\u0011H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", Argument.Delimiters.none, "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "renderModifiers", Argument.Delimiters.none, "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "functionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "renderModifier", "modifier", Argument.Delimiters.none, "asString", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "effectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "modalityAsString", "StaticPolicy", "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirModifierRenderer {
    private static final Set<Visibility> visibilitiesToRenderEffectiveSet = SetsKt.setOf(new Visibility[]{Visibilities.Private.INSTANCE, Visibilities.PrivateToThis.INSTANCE, Visibilities.Internal.INSTANCE, Visibilities.Protected.INSTANCE, Visibilities.Public.INSTANCE, Visibilities.Local.INSTANCE});
    public FirRendererComponents components;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer$StaticPolicy;", Argument.Delimiters.none, "renderStatic", Argument.Delimiters.none, "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Default", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface StaticPolicy {

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer$StaticPolicy$Default;", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer$StaticPolicy;", "<init>", "()V", "renderStatic", Argument.Delimiters.none, "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Default implements StaticPolicy {
            public static final Default INSTANCE = new Default();

            private Default() {
            }

            @Override // org.jetbrains.kotlin.fir.renderer.FirModifierRenderer.StaticPolicy
            public String renderStatic(FirDeclaration memberDeclaration) {
                memberDeclaration.getClass();
                return "static";
            }
        }

        String renderStatic(FirDeclaration memberDeclaration);
    }

    public static /* synthetic */ String asString$default(FirModifierRenderer firModifierRenderer, Visibility visibility, EffectiveVisibility effectiveVisibility, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: asString");
            return null;
        }
        if ((i & 1) != 0) {
            effectiveVisibility = null;
        }
        return firModifierRenderer.asString(visibility, effectiveVisibility);
    }

    public String asString(Visibility visibility, EffectiveVisibility effectiveVisibility) {
        visibility.getClass();
        if (Intrinsics.areEqual(visibility, Visibilities.Unknown.INSTANCE)) {
            return "public?";
        }
        String string = visibility.toString();
        if (effectiveVisibility != null) {
            Visibility visibility2 = effectiveVisibility.toVisibility();
            if (!Intrinsics.areEqual(visibility2, visibility) && ((!Intrinsics.areEqual(visibility2, Visibilities.Private.INSTANCE) || !Intrinsics.areEqual(visibility, Visibilities.PrivateToThis.INSTANCE)) && visibilitiesToRenderEffectiveSet.contains(visibility))) {
                return string + '[' + effectiveVisibility.getName() + ']';
            }
        }
        return string;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirRendererComponents getComponents$org_jetbrains_kotlin_tree() throws UninitializedPropertyAccessException {
        FirRendererComponents firRendererComponents = this.components;
        if (firRendererComponents != null) {
            return firRendererComponents;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    public final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    public String modalityAsString(FirMemberDeclaration firMemberDeclaration) {
        String strName;
        String lowerCaseAsciiOnly;
        firMemberDeclaration.getClass();
        if (firMemberDeclaration instanceof FirField) {
            if (((FirField) firMemberDeclaration).getIsVal()) {
                return "final";
            }
            return null;
        }
        Modality modality = firMemberDeclaration.getStatus().getModality();
        if (modality == null || (strName = modality.name()) == null || (lowerCaseAsciiOnly = CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(strName)) == null) {
            return ((firMemberDeclaration instanceof FirCallableDeclaration) && firMemberDeclaration.getStatus().isOverride()) ? "open?" : "final?";
        }
        return lowerCaseAsciiOnly;
    }

    public void renderModifier(String modifier) {
        if (modifier == null) {
            return;
        }
        getPrinter().print(modifier.concat(Argument.Delimiters.space));
    }

    public abstract void renderModifiers(FirAnonymousFunction anonymousFunction);

    public abstract void renderModifiers(FirBackingField backingField);

    public abstract void renderModifiers(FirConstructor constructor);

    public abstract void renderModifiers(FirMemberDeclaration memberDeclaration);

    public abstract void renderModifiers(FirPropertyAccessor propertyAccessor);

    public void renderModifiers(FirValueParameter valueParameter) {
        valueParameter.getClass();
        if (valueParameter.getIsCrossinline()) {
            renderModifier("crossinline");
        }
        if (valueParameter.getIsNoinline()) {
            renderModifier("noinline");
        }
        if (valueParameter.getIsVararg()) {
            renderModifier("vararg");
        }
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }

    public void renderModifiers(FirTypeParameter typeParameter) {
        typeParameter.getClass();
        if (typeParameter.getIsReified()) {
            renderModifier("reified");
        }
    }

    public void renderModifiers(FirFunctionTypeRef functionTypeRef) {
        functionTypeRef.getClass();
        if (functionTypeRef.isSuspend()) {
            renderModifier("suspend");
        }
    }
}
