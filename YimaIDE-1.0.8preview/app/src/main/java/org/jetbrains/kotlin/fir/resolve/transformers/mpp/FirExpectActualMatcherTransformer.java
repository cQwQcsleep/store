package org.jetbrains.kotlin.fir.resolve.transformers.mpp;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContext;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContextKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractTreeTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016J)\u0010'\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020)2\u0006\u0010*\u001a\u0002H(2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010+J\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualMatcherTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractTreeTransformer;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "actualScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "expectActualMatchingContext", "Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;", "transformTypeAlias", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "data", "transformRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "transformEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "transformProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "transformConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "transformErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "transformNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Void;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformMemberDeclaration", Argument.Delimiters.none, "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirExpectActualMatcherTransformer extends FirAbstractTreeTransformer {
    private final FirExpectActualMatchingContext expectActualMatchingContext;
    private final FirSession session;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirExpectActualMatcherTransformer(FirSession firSession, ScopeSession scopeSession) {
        super(FirResolvePhase.EXPECT_ACTUAL_MATCHING);
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.expectActualMatchingContext = FirExpectActualMatchingContextKt.getExpectActualMatchingContextFactory(firSession).create(firSession, scopeSession, true);
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, Void data) {
        errorPrimaryConstructor.getClass();
        return transformConstructor((FirConstructor) errorPrimaryConstructor, data);
    }

    public final void transformMemberDeclaration(FirMemberDeclaration memberDeclaration) {
        memberDeclaration.getClass();
        ExpectActualAttributesKt.setExpectForActual(memberDeclaration, FirExpectActualResolver.INSTANCE.findExpectForActual(memberDeclaration.getSymbol(), this.session, this.expectActualMatchingContext));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformRegularClass(FirRegularClass regularClass, Void data) {
        regularClass.getClass();
        transformMemberDeclaration(regularClass);
        regularClass.transformDeclarations((FirTransformer<? super Void>) this, data);
        return regularClass;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformConstructor(FirConstructor constructor, Void data) {
        constructor.getClass();
        transformMemberDeclaration(constructor);
        return constructor;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractTreeTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Void data) {
        element.getClass();
        return element;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformEnumEntry(FirEnumEntry enumEntry, Void data) {
        enumEntry.getClass();
        transformMemberDeclaration(enumEntry);
        return enumEntry;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Void data) {
        file.getClass();
        file.transformDeclarations(this, data);
        return file;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformNamedFunction(FirNamedFunction namedFunction, Void data) {
        namedFunction.getClass();
        transformMemberDeclaration(namedFunction);
        return namedFunction;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformProperty(FirProperty property, Void data) {
        property.getClass();
        transformMemberDeclaration(property);
        return property;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTypeAlias(FirTypeAlias typeAlias, Void data) {
        typeAlias.getClass();
        transformMemberDeclaration(typeAlias);
        return typeAlias;
    }
}
