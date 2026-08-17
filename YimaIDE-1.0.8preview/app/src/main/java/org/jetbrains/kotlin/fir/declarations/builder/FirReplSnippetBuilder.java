package org.jetbrains.kotlin.fir.declarations.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirReplSnippetImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010B\u001a\u00020CH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020.X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0017\u00103\u001a\b\u0012\u0004\u0012\u0002040\u000b¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u000eR\u001a\u00106\u001a\u000207X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020=X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AÊ\u0001\u0002\bE¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirReplSnippetBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;)V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "receivers", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "getReceivers", "snippetClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getSnippetClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "setSnippetClass", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "evalFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getEvalFunctionSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "setEvalFunctionSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReplSnippetBuilder implements FirAnnotationContainerBuilder {
    public FirNamedFunctionSymbol evalFunctionSymbol;
    public FirModuleData moduleData;
    public Name name;
    public FirDeclarationOrigin origin;
    public FirRegularClass snippetClass;
    public KtSourceElement source;
    public FirReplSnippetSymbol symbol;
    private FirResolvePhase resolvePhase = FirResolvePhase.RAW_FIR;
    private final List<FirAnnotation> annotations = new ArrayList();
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();
    private final List<FirScriptReceiverParameter> receivers = new ArrayList();

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirReplSnippet build() {
        return new FirReplSnippetImpl(this.resolvePhase, FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getModuleData(), getOrigin(), this.attributes, getName(), getSymbol(), getSource(), FirBuilderDslKt.toMutableOrEmpty(this.receivers), getSnippetClass(), getEvalFunctionSymbol(), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirNamedFunctionSymbol getEvalFunctionSymbol() throws UninitializedPropertyAccessException {
        FirNamedFunctionSymbol firNamedFunctionSymbol = this.evalFunctionSymbol;
        if (firNamedFunctionSymbol != null) {
            return firNamedFunctionSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("evalFunctionSymbol");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirModuleData getModuleData() throws UninitializedPropertyAccessException {
        FirModuleData firModuleData = this.moduleData;
        if (firModuleData != null) {
            return firModuleData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("moduleData");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Name getName() throws UninitializedPropertyAccessException {
        Name name = this.name;
        if (name != null) {
            return name;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ModuleXmlParser.NAME);
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirDeclarationOrigin getOrigin() throws UninitializedPropertyAccessException {
        FirDeclarationOrigin firDeclarationOrigin = this.origin;
        if (firDeclarationOrigin != null) {
            return firDeclarationOrigin;
        }
        Intrinsics.throwUninitializedPropertyAccessException("origin");
        return null;
    }

    public final List<FirScriptReceiverParameter> getReceivers() {
        return this.receivers;
    }

    public final FirResolvePhase getResolvePhase() {
        return this.resolvePhase;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirRegularClass getSnippetClass() throws UninitializedPropertyAccessException {
        FirRegularClass firRegularClass = this.snippetClass;
        if (firRegularClass != null) {
            return firRegularClass;
        }
        Intrinsics.throwUninitializedPropertyAccessException("snippetClass");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final KtSourceElement getSource() throws UninitializedPropertyAccessException {
        KtSourceElement ktSourceElement = this.source;
        if (ktSourceElement != null) {
            return ktSourceElement;
        }
        Intrinsics.throwUninitializedPropertyAccessException("source");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirReplSnippetSymbol getSymbol() throws UninitializedPropertyAccessException {
        FirReplSnippetSymbol firReplSnippetSymbol = this.symbol;
        if (firReplSnippetSymbol != null) {
            return firReplSnippetSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    public final void setAttributes(FirDeclarationAttributes firDeclarationAttributes) {
        firDeclarationAttributes.getClass();
        this.attributes = firDeclarationAttributes;
    }

    public final void setEvalFunctionSymbol(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        this.evalFunctionSymbol = firNamedFunctionSymbol;
    }

    public final void setModuleData(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    public final void setName(Name name) {
        name.getClass();
        this.name = name;
    }

    public final void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        this.origin = firDeclarationOrigin;
    }

    public final void setResolvePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        this.resolvePhase = firResolvePhase;
    }

    public final void setSnippetClass(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        this.snippetClass = firRegularClass;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        this.source = ktSourceElement;
    }

    public final void setSymbol(FirReplSnippetSymbol firReplSnippetSymbol) {
        firReplSnippetSymbol.getClass();
        this.symbol = firReplSnippetSymbol;
    }
}
