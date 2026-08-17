package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.name.NameRenderingUtils;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0015\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001fJ\f\u0010 \u001a\u00020\u001b*\u00020\u001dH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", Argument.Delimiters.none, "localVariablePrefix", Argument.Delimiters.none, "renderVerboseAccessors", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Z)V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "resolvePhaseRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvePhaseRenderer;", "getResolvePhaseRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirResolvePhaseRenderer;", "typeRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "getTypeRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "render", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "renderPhaseAndAttributes", "renderPhaseAndAttributes$org_jetbrains_kotlin_tree", "renderDeclarationAttributes", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirDeclarationRenderer {
    public FirRendererComponents components;
    private final String localVariablePrefix;
    private final boolean renderVerboseAccessors;

    public /* synthetic */ FirDeclarationRenderer(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "l" : str, (i & 2) != 0 ? false : z);
    }

    private final FirResolvePhaseRenderer getResolvePhaseRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getResolvePhaseRenderer();
    }

    private final ConeTypeRenderer getTypeRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getTypeRenderer();
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

    public final void render(FirDeclaration declaration) {
        String string;
        declaration.getClass();
        renderPhaseAndAttributes$org_jetbrains_kotlin_tree(declaration);
        if (declaration instanceof FirConstructor) {
            ConeSimpleKotlinType dispatchReceiverType = ((FirConstructor) declaration).getDispatchReceiverType();
            if (dispatchReceiverType != null) {
                ConeTypeRenderer.render$default(getTypeRenderer(), dispatchReceiverType, null, 2, null);
                getPrinter().print(".");
            }
            if (declaration instanceof FirErrorPrimaryConstructor) {
                getPrinter().print("error_");
            }
            getPrinter().print("constructor");
            return;
        }
        FirPrinter printer = getPrinter();
        if (declaration instanceof FirRegularClass) {
            string = StringsKt.replace$default(CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(((FirRegularClass) declaration).getClassKind().name()), InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, Argument.Delimiters.space, false, 4, (Object) null);
        } else if (declaration instanceof FirTypeAlias) {
            string = "typealias";
        } else {
            boolean z = declaration instanceof FirAnonymousFunction;
            String str = Argument.Delimiters.none;
            if (z) {
                FirLabel label = ((FirAnonymousFunction) declaration).getLabel();
                if (label != null) {
                    str = label.getName() + '@';
                }
                string = str.concat("fun");
            } else if (declaration instanceof FirNamedFunction) {
                string = "fun";
            } else if (declaration instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) declaration;
                if (Intrinsics.areEqual(ClassMembersKt.isCatchParameter(firProperty), Boolean.TRUE)) {
                    string = Argument.Delimiters.none;
                } else {
                    if ((firProperty.getSymbol() instanceof FirLocalPropertySymbol) || Intrinsics.areEqual(((FirMemberDeclaration) declaration).getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                        str = this.localVariablePrefix;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(firProperty.getIsVal() ? "val" : "var");
                    string = sb.toString();
                }
            } else if (declaration instanceof FirPropertyAccessor) {
                FirPropertyAccessor firPropertyAccessor = (FirPropertyAccessor) declaration;
                String str2 = firPropertyAccessor.getIsGetter() ? "get" : "set";
                if (this.renderVerboseAccessors) {
                    string = "fun `<" + str2 + '-' + NameRenderingUtils.render$default(firPropertyAccessor.getPropertySymbol().getName(), false, 1, (Object) null) + ">`";
                } else {
                    string = str2;
                }
            } else if (declaration instanceof FirField) {
                string = "field";
            } else if (declaration instanceof FirEnumEntry) {
                string = "enum entry";
            } else {
                string = declaration instanceof FirBackingField ? "backing field" : "unknown";
            }
        }
        printer.print(string);
    }

    public void renderDeclarationAttributes(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
    }

    public final void renderPhaseAndAttributes$org_jetbrains_kotlin_tree(FirDeclaration declaration) {
        declaration.getClass();
        FirResolvePhaseRenderer resolvePhaseRenderer = getResolvePhaseRenderer();
        if (resolvePhaseRenderer != null) {
            resolvePhaseRenderer.render(declaration);
        }
        renderDeclarationAttributes(declaration);
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }

    public FirDeclarationRenderer(String str, boolean z) {
        str.getClass();
        this.localVariablePrefix = str;
        this.renderVerboseAccessors = z;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FirDeclarationRenderer() {
        String str = null;
        this(str, false, 3, str);
    }
}
