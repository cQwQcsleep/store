package org.jetbrains.kotlin.fir.java.declarations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.DirectDeclarationsAccess;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtension;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtensionKt;
import org.jetbrains.kotlin.fir.java.JavaTypeConversionKt;
import org.jetbrains.kotlin.fir.java.JavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.MutableJavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaDeclarationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaClassifierType;
import org.jetbrains.kotlin.load.java.structure.JavaPackage;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B³\u0001\b\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u0019\u0012\f\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010#\u001a\u0002\b&¢\u0006\u0004\b$\u0010%J\u000e\u0010[\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\u0016\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\u0010\u0010w\u001a\u00020u2\u0006\u0010x\u001a\u00020fH\u0016J\u0012\u0010y\u001a\u00020u2\b\u0010z\u001a\u0004\u0018\u00010NH\u0016J\u0012\u0010~\u001a\u00020u2\b\u0010\u007f\u001a\u0004\u0018\u00010\u0017H\u0016J?\u0010\u0080\u0001\u001a\u00020u\"\u0005\b\u0000\u0010\u0081\u0001\"\u0005\b\u0001\u0010\u0082\u00012\u0016\u0010\u0083\u0001\u001a\u0011\u0012\u0005\u0012\u0003H\u0081\u0001\u0012\u0005\u0012\u0003H\u0082\u00010\u0084\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u0086\u0001J1\u0010\u0087\u0001\u001a\u00020\u0000\"\u0005\b\u0000\u0010\u0082\u00012\u000f\u0010\u0088\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0082\u00010\u0089\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u008a\u0001J1\u0010\u008b\u0001\u001a\u00020\u0001\"\u0005\b\u0000\u0010\u0082\u00012\u000f\u0010\u0088\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0082\u00010\u0089\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u008c\u0001J1\u0010\u008d\u0001\u001a\u00020\u0001\"\u0005\b\u0000\u0010\u0082\u00012\u000f\u0010\u0088\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0082\u00010\u0089\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u008c\u0001J1\u0010\u008e\u0001\u001a\u00020\u0000\"\u0005\b\u0000\u0010\u0082\u00012\u000f\u0010\u0088\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0082\u00010\u0089\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u008a\u0001J\u0018\u0010\u008f\u0001\u001a\u00020u2\r\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020]0\u0019H\u0016J1\u0010\u0091\u0001\u001a\u00020\u0000\"\u0005\b\u0000\u0010\u0082\u00012\u000f\u0010\u0088\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0082\u00010\u0089\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u008a\u0001J1\u0010\u0092\u0001\u001a\u00020\u0000\"\u0005\b\u0000\u0010\u0082\u00012\u000f\u0010\u0088\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0082\u00010\u0089\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u008a\u0001J1\u0010\u0093\u0001\u001a\u00020\u0001\"\u0005\b\u0000\u0010\u0082\u00012\u000f\u0010\u0088\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0082\u00010\u0089\u00012\b\u0010\u0085\u0001\u001a\u0003H\u0082\u0001H\u0016¢\u0006\u0003\u0010\u008c\u0001J\u0012\u0010\u0094\u0001\u001a\u00020u2\u0007\u0010\u0095\u0001\u001a\u00020pH\u0016J\u0018\u0010\u0096\u0001\u001a\u00020u2\r\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020`0\u0019H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010:R\u001a\u0010\"\u001a\b\u0012\u0002\b\u0003\u0018\u00010#X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u001b\u0010B\u001a\u00020C8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bD\u0010ER\u0011\u0010H\u001a\u00020I¢\u0006\b\n\u0000\u001a\u0004\bH\u0010JR\u0014\u0010K\u001a\u00020I8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010JR\u0016\u0010M\u001a\u0004\u0018\u00010N8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR\u001a\u0010Q\u001a\b\u0012\u0004\u0012\u00020R0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010:R\u0014\u0010T\u001a\u00020UX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010WR!\u0010X\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010G\u001a\u0004\bY\u0010:R\u001a\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b^\u0010:R$\u0010_\u001a\b\u0012\u0004\u0012\u00020`0\u00198VX\u0097\u0004r\u0002\bd¢\u0006\f\u0012\u0004\ba\u0010b\u001a\u0004\bc\u0010:R\u001b\u0010e\u001a\u00020f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bi\u0010G\u001a\u0004\bg\u0010hR\u000e\u0010j\u001a\u00020kX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010l\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00198VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bn\u0010G\u001a\u0004\bm\u0010:R\u001b\u0010o\u001a\u00020p8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bs\u0010G\u001a\u0004\bq\u0010rR\u0016\u0010{\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b|\u00108R\u0014\u0010}\u001a\u00020I8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b}\u0010J¨\u0006\u0098\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "originalStatus", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "declarationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "nonEnhancedSuperTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "nonEnhancedTypeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "javaPackage", "Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;", "classJavaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;", "existingNestedClassifierNames", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/load/java/structure/JavaClass;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;Lorg/jetbrains/kotlin/descriptors/ClassKind;Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;Ljava/util/List;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "getOriginalStatus$org_jetbrains_kotlin_fir_jvm", "()Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "getClassKind", "()Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getDeclarationList", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;", "getScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getNonEnhancedTypeParameters", "()Ljava/util/List;", "getJavaPackage$org_jetbrains_kotlin_fir_jvm", "()Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;", "getClassJavaTypeParameterStack", "()Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;", "getExistingNestedClassifierNames$org_jetbrains_kotlin_fir_jvm", "getContainingClassSymbol$org_jetbrains_kotlin_fir_jvm", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "getJavaTypeParameterStack", "()Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "javaTypeParameterStack$delegate", "Lkotlin/Lazy;", "isRecord", Argument.Delimiters.none, "()Z", "hasLazyNestedClassifiers", "getHasLazyNestedClassifiers", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "superTypeRefs", "getSuperTypeRefs", "superTypeRefs$delegate", "computeSuperTypeRefsByJavaClass", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "declarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarations$annotations", "()V", "getDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/DirectDeclarationsAccess;", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "deprecationsProvider$delegate", "typeParameterBoundsResolveLock", "Ljava/util/concurrent/locks/ReentrantLock;", "typeParameters", "getTypeParameters", "typeParameters$delegate", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "status$delegate", "replaceSuperTypeRefs", Argument.Delimiters.none, "newSuperTypeRefs", "replaceDeprecationsProvider", "newDeprecationsProvider", "replaceControlFlowGraphReference", "newControlFlowGraphReference", "companionObjectSymbol", "getCompanionObjectSymbol", "isLocal", "replaceCompanionObjectSymbol", "newCompanionObjectSymbol", "acceptChildren", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "transformSuperTypeRefs", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "transformContextParameters", "transformStatus", "replaceAnnotations", "newAnnotations", "transformAnnotations", "transformDeclarations", "transformTypeParameters", "replaceStatus", "newStatus", "replaceDeclarations", "newDeclarations", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaClass extends FirRegularClass {
    private final FirJavaAnnotationList annotationList;
    private final FirDeclarationAttributes attributes;
    private final MutableJavaTypeParameterStack classJavaTypeParameterStack;
    private final ClassKind classKind;
    private final FirClassSymbol<?> containingClassSymbol;
    private final FirJavaDeclarationList declarationList;

    /* JADX INFO: renamed from: deprecationsProvider$delegate, reason: from kotlin metadata */
    private final Lazy deprecationsProvider;
    private final List<Name> existingNestedClassifierNames;
    private final boolean isRecord;
    private final JavaClass javaClass;
    private final JavaPackage javaPackage;

    /* JADX INFO: renamed from: javaTypeParameterStack$delegate, reason: from kotlin metadata */
    private final Lazy javaTypeParameterStack;
    private final FirModuleData moduleData;
    private final Name name;
    private final List<FirTypeRef> nonEnhancedSuperTypes;
    private final List<FirTypeParameterRef> nonEnhancedTypeParameters;
    private final FirDeclarationOrigin.Java origin;
    private final FirResolvedDeclarationStatusImpl originalStatus;
    private final FirScopeProvider scopeProvider;
    private final KtSourceElement source;

    /* JADX INFO: renamed from: status$delegate, reason: from kotlin metadata */
    private final Lazy status;

    /* JADX INFO: renamed from: superTypeRefs$delegate, reason: from kotlin metadata */
    private final Lazy superTypeRefs;
    private final FirRegularClassSymbol symbol;
    private final ReentrantLock typeParameterBoundsResolveLock;

    /* JADX INFO: renamed from: typeParameters$delegate, reason: from kotlin metadata */
    private final Lazy typeParameters;

    /* JADX WARN: Multi-variable type inference failed */
    @FirImplementationDetail
    public FirJavaClass(JavaClass javaClass, KtSourceElement ktSourceElement, FirModuleData firModuleData, Name name, FirDeclarationOrigin.Java java, FirJavaAnnotationList firJavaAnnotationList, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl, ClassKind classKind, FirJavaDeclarationList firJavaDeclarationList, FirScopeProvider firScopeProvider, FirRegularClassSymbol firRegularClassSymbol, List<? extends FirTypeRef> list, List<? extends FirTypeParameterRef> list2, JavaPackage javaPackage, MutableJavaTypeParameterStack mutableJavaTypeParameterStack, List<Name> list3, FirClassSymbol<?> firClassSymbol) {
        firModuleData.getClass();
        name.getClass();
        java.getClass();
        firJavaAnnotationList.getClass();
        firResolvedDeclarationStatusImpl.getClass();
        classKind.getClass();
        firJavaDeclarationList.getClass();
        firScopeProvider.getClass();
        firRegularClassSymbol.getClass();
        list.getClass();
        list2.getClass();
        mutableJavaTypeParameterStack.getClass();
        list3.getClass();
        this.javaClass = javaClass;
        this.source = ktSourceElement;
        this.moduleData = firModuleData;
        this.name = name;
        this.origin = java;
        this.annotationList = firJavaAnnotationList;
        this.originalStatus = firResolvedDeclarationStatusImpl;
        this.classKind = classKind;
        this.declarationList = firJavaDeclarationList;
        this.scopeProvider = firScopeProvider;
        this.symbol = firRegularClassSymbol;
        this.nonEnhancedSuperTypes = list;
        this.nonEnhancedTypeParameters = list2;
        this.javaPackage = javaPackage;
        this.classJavaTypeParameterStack = mutableJavaTypeParameterStack;
        this.existingNestedClassifierNames = list3;
        this.containingClassSymbol = firClassSymbol;
        if (javaClass != null && !list.isEmpty()) {
            w01.a("Non-empty super types. They are expected to be computed lazily on demand via `JavaClass`");
            throw null;
        }
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.javaTypeParameterStack = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: s85
            public final Object invoke() {
                return FirJavaClass.f(this.b);
            }
        });
        this.isRecord = javaClass != null ? javaClass.isRecord() : false;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES()));
        this.attributes = new FirDeclarationAttributes();
        this.superTypeRefs = LazyKt.lazy(new Function0() { // from class: t85
            public final Object invoke() {
                return FirJavaClass.d(this.b);
            }
        });
        this.deprecationsProvider = LazyKt.lazy(new Function0() { // from class: u85
            public final Object invoke() {
                return FirJavaClass.a(this.b);
            }
        });
        this.typeParameterBoundsResolveLock = new ReentrantLock();
        this.typeParameters = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: v85
            public final Object invoke() {
                return FirJavaClass.b(this.b);
            }
        });
        this.status = LazyKt.lazy(new Function0() { // from class: w85
            public final Object invoke() {
                return FirJavaClass.c(this.b);
            }
        });
    }

    public static DeprecationsProvider a(FirJavaClass firJavaClass) {
        return DeprecationUtilsKt.getDeprecationsProvider(firJavaClass, firJavaClass.getModuleData().getSession());
    }

    public static List b(FirJavaClass firJavaClass) {
        new FirSignatureEnhancement(firJavaClass, firJavaClass.getModuleData().getSession(), true, new Function1() { // from class: x85
            public final Object invoke(Object obj) {
                return FirJavaClass.typeParameters_delegate$lambda$0$0((FirCallableDeclaration) obj);
            }
        }).enhanceTypeParameterBounds(firJavaClass, firJavaClass.nonEnhancedTypeParameters, new FirJavaClass$typeParameters$2$1(firJavaClass.typeParameterBoundsResolveLock));
        return firJavaClass.nonEnhancedTypeParameters;
    }

    public static FirDeclarationStatus c(FirJavaClass firJavaClass) {
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = firJavaClass.originalStatus;
        List<FirStatusTransformerExtension> statusTransformerExtensions = FirStatusTransformerExtensionKt.getStatusTransformerExtensions(FirExtensionServiceKt.getExtensionService(firJavaClass.getModuleData().getSession()));
        if (!statusTransformerExtensions.isEmpty()) {
            FirDeclarationStatus firDeclarationStatusTransformStatus = firResolvedDeclarationStatusImpl;
            for (FirStatusTransformerExtension firStatusTransformerExtension : statusTransformerExtensions) {
                if (firStatusTransformerExtension.needTransformStatus(firJavaClass)) {
                    firDeclarationStatusTransformStatus = firStatusTransformerExtension.transformStatus(firDeclarationStatusTransformStatus, (FirRegularClass) firJavaClass, (FirClassLikeSymbol<?>) firJavaClass.containingClassSymbol, false);
                }
            }
            firDeclarationStatusTransformStatus.getClass();
            FirDeclarationStatusImpl firDeclarationStatusImpl = (FirDeclarationStatusImpl) firDeclarationStatusTransformStatus;
            if (firDeclarationStatusImpl != firResolvedDeclarationStatusImpl) {
                if (Intrinsics.areEqual(firDeclarationStatusImpl.getVisibility(), firResolvedDeclarationStatusImpl.getVisibility())) {
                    Visibility visibility = firDeclarationStatusImpl.getVisibility();
                    Modality modality = firDeclarationStatusImpl.getModality();
                    if (modality == null) {
                        modality = firResolvedDeclarationStatusImpl.getModality();
                    }
                    return firDeclarationStatusImpl.resolved(visibility, modality, firResolvedDeclarationStatusImpl.getEffectiveVisibility());
                }
                StringBuilder sb = new StringBuilder("Attempt to change visibility of a class-like: ");
                FirClassLikeSymbol<FirClassLikeDeclaration> symbol = firJavaClass.getSymbol();
                sb.append(symbol != null ? symbol.getClassId() : null);
                sb.append(", original visibility: ");
                sb.append(firResolvedDeclarationStatusImpl.getVisibility());
                ywd.a(sb, ", new visibility: ", firDeclarationStatusImpl.getVisibility());
                return null;
            }
        }
        return firResolvedDeclarationStatusImpl;
    }

    private final List<FirTypeRef> computeSuperTypeRefsByJavaClass() {
        Collection supertypes;
        JavaClass javaClass = this.javaClass;
        if (javaClass == null || (supertypes = javaClass.getSupertypes()) == null) {
            return CollectionsKt.emptyList();
        }
        FirSession session = getModuleData().getSession();
        KtSourceElement source = getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
        Collection collection = supertypes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(JavaTypeConversionKt.toFirJavaTypeRef((JavaClassifierType) it.next(), session, ktSourceElementFakeElement$default));
        }
        return arrayList;
    }

    public static List d(FirJavaClass firJavaClass) {
        List<FirTypeRef> listComputeSuperTypeRefsByJavaClass = firJavaClass.nonEnhancedSuperTypes;
        if (listComputeSuperTypeRefsByJavaClass.isEmpty()) {
            listComputeSuperTypeRefsByJavaClass = firJavaClass.computeSuperTypeRefsByJavaClass();
        }
        List<FirTypeRef> listListOf = listComputeSuperTypeRefsByJavaClass;
        if (listListOf.isEmpty()) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setConeType(TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getAny(), null, false, null, 7, null));
            listListOf = CollectionsKt.listOf(firResolvedTypeRefBuilder.build());
        }
        return new FirSignatureEnhancement(firJavaClass, firJavaClass.getModuleData().getSession(), true, new Function1() { // from class: y85
            public final Object invoke(Object obj) {
                return FirJavaClass.superTypeRefs_delegate$lambda$0$2((FirCallableDeclaration) obj);
            }
        }).enhanceSuperTypes(listListOf);
    }

    public static MutableJavaTypeParameterStack f(FirJavaClass firJavaClass) {
        MutableJavaTypeParameterStack mutableJavaTypeParameterStackCopy = firJavaClass.classJavaTypeParameterStack.copy();
        for (FirAnnotationContainer firAnnotationContainer : firJavaClass.getDeclarations()) {
            if (firAnnotationContainer instanceof FirTypeParameterRefsOwner) {
                for (FirTypeParameterRef firTypeParameterRef : ((FirTypeParameterRefsOwner) firAnnotationContainer).getTypeParameters()) {
                    if (firTypeParameterRef instanceof FirJavaTypeParameter) {
                        FirJavaTypeParameter firJavaTypeParameter = (FirJavaTypeParameter) firTypeParameterRef;
                        mutableJavaTypeParameterStackCopy.addParameter(firJavaTypeParameter.getJavaTypeParameter(), firJavaTypeParameter.getSymbol());
                    }
                }
            }
        }
        return mutableJavaTypeParameterStackCopy;
    }

    @DirectDeclarationsAccess
    public static /* synthetic */ void getDeclarations$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List superTypeRefs_delegate$lambda$0$2(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return CollectionsKt.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List typeParameters_delegate$lambda$0$0(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = getDeclarations().iterator();
        while (it.hasNext()) {
            ((FirDeclaration) it.next()).accept(visitor, data);
        }
        Iterator<T> it2 = getAnnotations().iterator();
        while (it2.hasNext()) {
            ((FirAnnotation) it2.next()).accept(visitor, data);
        }
        Iterator<T> it3 = getTypeParameters().iterator();
        while (it3.hasNext()) {
            ((FirTypeParameterRef) it3.next()).accept(visitor, data);
        }
        getStatus().accept(visitor, data);
        Iterator<T> it4 = getSuperTypeRefs().iterator();
        while (it4.hasNext()) {
            ((FirTypeRef) it4.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return this.annotationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    public final MutableJavaTypeParameterStack getClassJavaTypeParameterStack() {
        return this.classJavaTypeParameterStack;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public ClassKind getClassKind() {
        return this.classKind;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass
    public FirRegularClassSymbol getCompanionObjectSymbol() {
        return null;
    }

    public final FirClassSymbol<?> getContainingClassSymbol$org_jetbrains_kotlin_fir_jvm() {
        return this.containingClassSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass
    public List<FirValueParameter> getContextParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public FirControlFlowGraphReference getControlFlowGraphReference() {
        return null;
    }

    public final FirJavaDeclarationList getDeclarationList() {
        return this.declarationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public List<FirDeclaration> getDeclarations() {
        return this.declarationList.getDeclarations();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
    public DeprecationsProvider getDeprecationsProvider() {
        return (DeprecationsProvider) this.deprecationsProvider.getValue();
    }

    public final List<Name> getExistingNestedClassifierNames$org_jetbrains_kotlin_fir_jvm() {
        return this.existingNestedClassifierNames;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass
    public boolean getHasLazyNestedClassifiers() {
        return true;
    }

    /* JADX INFO: renamed from: getJavaPackage$org_jetbrains_kotlin_fir_jvm, reason: from getter */
    public final JavaPackage getJavaPackage() {
        return this.javaPackage;
    }

    public final JavaTypeParameterStack getJavaTypeParameterStack() {
        return (JavaTypeParameterStack) this.javaTypeParameterStack.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass
    public Name getName() {
        return this.name;
    }

    public final List<FirTypeParameterRef> getNonEnhancedTypeParameters() {
        return this.nonEnhancedTypeParameters;
    }

    /* JADX INFO: renamed from: getOriginalStatus$org_jetbrains_kotlin_fir_jvm, reason: from getter */
    public final FirResolvedDeclarationStatusImpl getOriginalStatus() {
        return this.originalStatus;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
    public FirScopeProvider getScopeProvider() {
        return this.scopeProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public FirDeclarationStatus getStatus() {
        return (FirDeclarationStatus) this.status.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public List<FirTypeRef> getSuperTypeRefs() {
        return (List) this.superTypeRefs.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public List<FirTypeParameterRef> getTypeParameters() {
        return (List) this.typeParameters.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    /* JADX INFO: renamed from: isLocal */
    public boolean getIsLocal() {
        return false;
    }

    /* JADX INFO: renamed from: isRecord, reason: from getter */
    public final boolean getIsRecord() {
        return this.isRecord;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) throws KotlinNothingValueException {
        newAnnotations.getClass();
        UtilsKt.shouldNotBeCalled(this, new AnonymousClass1(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass.replaceAnnotations.2
            public Object get() {
                return ((FirJavaClass) ((CallableReference) this).receiver).getAnnotations();
            }
        });
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass
    public void replaceCompanionObjectSymbol(FirRegularClassSymbol newCompanionObjectSymbol) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference) {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public void replaceDeclarations(List<? extends FirDeclaration> newDeclarations) throws KotlinNothingValueException {
        newDeclarations.getClass();
        UtilsKt.shouldNotBeCalled(this, new C00191(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass.replaceDeclarations.2
            public Object get() {
                return ((FirJavaClass) ((CallableReference) this).receiver).getDeclarations();
            }
        });
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration
    public void replaceDeprecationsProvider(DeprecationsProvider newDeprecationsProvider) throws KotlinNothingValueException {
        newDeprecationsProvider.getClass();
        UtilsKt.shouldNotBeCalled(this, new C00211(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass.replaceDeprecationsProvider.2
            public Object get() {
                return ((FirJavaClass) ((CallableReference) this).receiver).getDeprecationsProvider();
            }
        });
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public void replaceStatus(FirDeclarationStatus newStatus) throws KotlinNothingValueException {
        newStatus.getClass();
        UtilsKt.shouldNotBeCalled(this, new C00231(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass.replaceStatus.2
            public Object get() {
                return ((FirJavaClass) ((CallableReference) this).receiver).getStatus();
            }
        });
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public void replaceSuperTypeRefs(List<? extends FirTypeRef> newSuperTypeRefs) throws KotlinNothingValueException {
        newSuperTypeRefs.getClass();
        UtilsKt.shouldNotBeCalled(this, new C00251(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass.replaceSuperTypeRefs.2
            public Object get() {
                return ((FirJavaClass) ((CallableReference) this).receiver).getSuperTypeRefs();
            }
        });
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass
    public <D> FirRegularClass transformContextParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public /* bridge */ /* synthetic */ FirClass transformDeclarations(FirTransformer firTransformer, Object obj) {
        return transformDeclarations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirClass transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public /* bridge */ /* synthetic */ FirClass transformSuperTypeRefs(FirTransformer firTransformer, Object obj) {
        return transformSuperTypeRefs((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirClass transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin.Java getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirJavaClass transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirJavaClass transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public <D> FirJavaClass transformDeclarations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public <D> FirJavaClass transformStatus(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public <D> FirRegularClass transformSuperTypeRefs(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public <D> FirRegularClass transformTypeParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirClass transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass
    public /* bridge */ /* synthetic */ FirRegularClass transformDeclarations(FirTransformer firTransformer, Object obj) {
        return transformDeclarations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirClassLikeDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirClassLikeDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirRegularClassSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirClassLikeDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirRegularClass transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParameterRefsOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass$replaceAnnotations$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<List<? extends FirAnnotation>, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, FirJavaClass.class, "replaceAnnotations", "replaceAnnotations(Ljava/util/List;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(List<? extends FirAnnotation> list) throws KotlinNothingValueException {
            list.getClass();
            ((FirJavaClass) ((CallableReference) this).receiver).replaceAnnotations(list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((List<? extends FirAnnotation>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass$replaceDeclarations$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00191 extends FunctionReferenceImpl implements Function1<List<? extends FirDeclaration>, Unit> {
        public C00191(Object obj) {
            super(1, obj, FirJavaClass.class, "replaceDeclarations", "replaceDeclarations(Ljava/util/List;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(List<? extends FirDeclaration> list) throws KotlinNothingValueException {
            list.getClass();
            ((FirJavaClass) ((CallableReference) this).receiver).replaceDeclarations(list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((List<? extends FirDeclaration>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass$replaceDeprecationsProvider$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00211 extends FunctionReferenceImpl implements Function1<DeprecationsProvider, Unit> {
        public C00211(Object obj) {
            super(1, obj, FirJavaClass.class, "replaceDeprecationsProvider", "replaceDeprecationsProvider(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(DeprecationsProvider deprecationsProvider) throws KotlinNothingValueException {
            deprecationsProvider.getClass();
            ((FirJavaClass) ((CallableReference) this).receiver).replaceDeprecationsProvider(deprecationsProvider);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((DeprecationsProvider) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass$replaceStatus$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00231 extends FunctionReferenceImpl implements Function1<FirDeclarationStatus, Unit> {
        public C00231(Object obj) {
            super(1, obj, FirJavaClass.class, "replaceStatus", "replaceStatus(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(FirDeclarationStatus firDeclarationStatus) throws KotlinNothingValueException {
            firDeclarationStatus.getClass();
            ((FirJavaClass) ((CallableReference) this).receiver).replaceStatus(firDeclarationStatus);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((FirDeclarationStatus) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaClass$replaceSuperTypeRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00251 extends FunctionReferenceImpl implements Function1<List<? extends FirTypeRef>, Unit> {
        public C00251(Object obj) {
            super(1, obj, FirJavaClass.class, "replaceSuperTypeRefs", "replaceSuperTypeRefs(Ljava/util/List;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(List<? extends FirTypeRef> list) throws KotlinNothingValueException {
            list.getClass();
            ((FirJavaClass) ((CallableReference) this).receiver).replaceSuperTypeRefs(list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((List<? extends FirTypeRef>) obj);
            return Unit.INSTANCE;
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirRegularClass transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirRegularClass, org.jetbrains.kotlin.fir.declarations.FirClass, org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
