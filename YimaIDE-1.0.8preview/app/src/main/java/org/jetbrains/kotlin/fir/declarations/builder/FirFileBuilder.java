package org.jetbrains.kotlin.fir.declarations.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMapping;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirPackageDirective;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.impl.FirFileImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010K\u001a\u00020LH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u0011¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0014R\u0017\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0011¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0014R\u001a\u00103\u001a\u000204X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u00109\u001a\u0004\u0018\u00010:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001c\u0010?\u001a\u0004\u0018\u00010@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001a\u0010E\u001a\u00020FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JÊ\u0001\u0002\bN¨\u0006M"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirFileBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "packageDirective", "Lorg/jetbrains/kotlin/fir/FirPackageDirective;", "getPackageDirective", "()Lorg/jetbrains/kotlin/fir/FirPackageDirective;", "setPackageDirective", "(Lorg/jetbrains/kotlin/fir/FirPackageDirective;)V", "imports", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "getImports", "declarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarations", ModuleXmlParser.NAME, Argument.Delimiters.none, "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "getSourceFile", "()Lorg/jetbrains/kotlin/KtSourceFile;", "setSourceFile", "(Lorg/jetbrains/kotlin/KtSourceFile;)V", "sourceFileLinesMapping", "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "getSourceFileLinesMapping", "()Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "setSourceFileLinesMapping", "(Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFileBuilder implements FirAnnotationContainerBuilder {
    public FirModuleData moduleData;
    public String name;
    public FirDeclarationOrigin origin;
    public FirPackageDirective packageDirective;
    private KtSourceElement source;
    private KtSourceFile sourceFile;
    private KtSourceFileLinesMapping sourceFileLinesMapping;
    private FirResolvePhase resolvePhase = FirResolvePhase.RAW_FIR;
    private final List<FirAnnotation> annotations = new ArrayList();
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();
    private final List<FirImport> imports = new ArrayList();
    private final List<FirDeclaration> declarations = new ArrayList();
    private FirFileSymbol symbol = new FirFileSymbol();

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirFile build() {
        return new FirFileImpl(this.source, this.resolvePhase, FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getModuleData(), getOrigin(), this.attributes, getPackageDirective(), this.imports, this.declarations, getName(), this.sourceFile, this.sourceFileLinesMapping, this.symbol, null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    public final List<FirDeclaration> getDeclarations() {
        return this.declarations;
    }

    public final List<FirImport> getImports() {
        return this.imports;
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
    public final String getName() throws UninitializedPropertyAccessException {
        String str = this.name;
        if (str != null) {
            return str;
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirPackageDirective getPackageDirective() throws UninitializedPropertyAccessException {
        FirPackageDirective firPackageDirective = this.packageDirective;
        if (firPackageDirective != null) {
            return firPackageDirective;
        }
        Intrinsics.throwUninitializedPropertyAccessException("packageDirective");
        return null;
    }

    public final FirResolvePhase getResolvePhase() {
        return this.resolvePhase;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final KtSourceFile getSourceFile() {
        return this.sourceFile;
    }

    public final KtSourceFileLinesMapping getSourceFileLinesMapping() {
        return this.sourceFileLinesMapping;
    }

    public final FirFileSymbol getSymbol() {
        return this.symbol;
    }

    public final void setAttributes(FirDeclarationAttributes firDeclarationAttributes) {
        firDeclarationAttributes.getClass();
        this.attributes = firDeclarationAttributes;
    }

    public final void setModuleData(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    public final void setName(String str) {
        str.getClass();
        this.name = str;
    }

    public final void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        this.origin = firDeclarationOrigin;
    }

    public final void setPackageDirective(FirPackageDirective firPackageDirective) {
        firPackageDirective.getClass();
        this.packageDirective = firPackageDirective;
    }

    public final void setResolvePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        this.resolvePhase = firResolvePhase;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setSourceFile(KtSourceFile ktSourceFile) {
        this.sourceFile = ktSourceFile;
    }

    public final void setSourceFileLinesMapping(KtSourceFileLinesMapping ktSourceFileLinesMapping) {
        this.sourceFileLinesMapping = ktSourceFileLinesMapping;
    }

    public final void setSymbol(FirFileSymbol firFileSymbol) {
        firFileSymbol.getClass();
        this.symbol = firFileSymbol;
    }
}
