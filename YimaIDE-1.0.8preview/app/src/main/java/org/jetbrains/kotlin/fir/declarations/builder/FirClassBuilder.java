package org.jetbrains.kotlin.fir.declarations.builder;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bw\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010L\u001a\u00020MH&R\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u00020\nX¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u00020\u001bX¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0018\u0010 \u001a\u00020!X¦\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0013R\u0018\u0010)\u001a\u00020*X¦\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0018\u0010/\u001a\u000200X¦\u000e¢\u0006\f\u001a\u0004\b/\u00101\"\u0004\b2\u00103R\u0018\u00104\u001a\u000205X¦\u000e¢\u0006\f\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0018\u0010:\u001a\u00020;X¦\u000e¢\u0006\f\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0018\u0010@\u001a\u00020AX¦\u000e¢\u0006\f\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u0018\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\u0013R\u0018\u0010I\u001a\b\u0012\u0004\u0012\u00020J0\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u0013\u0082\u0001\u0002NOÊ\u0001\u0002\bQø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006PÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirDeclarationBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "getTypeParameters", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "isLocal", Argument.Delimiters.none, "()Z", "setLocal", "(Z)V", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "getScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "setScopeProvider", "(Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;)V", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getClassKind", "()Lorg/jetbrains/kotlin/descriptors/ClassKind;", "setClassKind", "(Lorg/jetbrains/kotlin/descriptors/ClassKind;)V", "superTypeRefs", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSuperTypeRefs", "declarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarations", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirAnonymousObjectBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirClassBuilder extends FirAnnotationContainerBuilder, FirDeclarationBuilder {
    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    FirClass build();

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    List<FirAnnotation> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    FirDeclarationAttributes getAttributes();

    ClassKind getClassKind();

    List<FirDeclaration> getDeclarations();

    DeprecationsProvider getDeprecationsProvider();

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    FirModuleData getModuleData();

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    FirDeclarationOrigin getOrigin();

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    FirResolvePhase getResolvePhase();

    FirScopeProvider getScopeProvider();

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    KtSourceElement getSource();

    FirDeclarationStatus getStatus();

    List<FirTypeRef> getSuperTypeRefs();

    List<FirTypeParameterRef> getTypeParameters();

    boolean isLocal();

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    void setAttributes(FirDeclarationAttributes firDeclarationAttributes);

    void setClassKind(ClassKind classKind);

    void setDeprecationsProvider(DeprecationsProvider deprecationsProvider);

    void setLocal(boolean z);

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    void setModuleData(FirModuleData firModuleData);

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    void setOrigin(FirDeclarationOrigin firDeclarationOrigin);

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    void setResolvePhase(FirResolvePhase firResolvePhase);

    void setScopeProvider(FirScopeProvider firScopeProvider);

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    void setSource(KtSourceElement ktSourceElement);

    void setStatus(FirDeclarationStatus firDeclarationStatus);
}
