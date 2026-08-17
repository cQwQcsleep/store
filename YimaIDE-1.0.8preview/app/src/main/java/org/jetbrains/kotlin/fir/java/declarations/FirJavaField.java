package org.jetbrains.kotlin.fir.java.declarations;

import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtension;
import org.jetbrains.kotlin.fir.extensions.FirStatusTransformerExtensionKt;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaField;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u0093\u0001\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u000e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d\u001a\u0002\b ¢\u0006\u0004\b\u001e\u0010\u001fJ)\u0010g\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ)\u0010m\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ)\u0010n\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ)\u0010o\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ)\u0010p\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ)\u0010q\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ)\u0010r\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ5\u0010s\u001a\u00020t\"\u0004\b\u0000\u0010u\"\u0004\b\u0001\u0010h2\u0012\u0010v\u001a\u000e\u0012\u0004\u0012\u0002Hu\u0012\u0004\u0012\u0002Hh0w2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010xJ)\u0010y\u001a\u00020\u0000\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010zJ)\u0010{\u001a\u00020\u0000\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010zJ\u0010\u0010|\u001a\u00020t2\u0006\u0010}\u001a\u00020\rH\u0016J\u0016\u0010~\u001a\u00020t2\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020M0LH\u0016J*\u0010\u0080\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010zJ\u0014\u0010\u0081\u0001\u001a\u00020t2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0016H\u0016J*\u0010\u0083\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ\u0014\u0010\u0084\u0001\u001a\u00020t2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0016H\u0016J*\u0010\u008e\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ\u0014\u0010\u008f\u0001\u001a\u00020t2\t\u0010\u0090\u0001\u001a\u0004\u0018\u000109H\u0016J\u0012\u0010\u0091\u0001\u001a\u00020t2\u0007\u0010\u0092\u0001\u001a\u00020VH\u0016J*\u0010\u0093\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010h2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010k\u001a\u0002HhH\u0016¢\u0006\u0002\u0010lJ\u0014\u0010\u0094\u0001\u001a\u00020t2\t\u0010\u0095\u0001\u001a\u0004\u0018\u00010>H\u0016J\u0014\u0010\u0096\u0001\u001a\u00020t2\t\u0010\u0097\u0001\u001a\u0004\u0018\u00010>H\u0016J\u0014\u0010\u0098\u0001\u001a\u00020t2\t\u0010\u0099\u0001\u001a\u0004\u0018\u00010HH\u0016J\u0018\u0010\u009a\u0001\u001a\u00020t2\r\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020d0LH\u0016J\u0012\u0010\u009c\u0001\u001a\u00020t2\u0007\u0010\u009d\u0001\u001a\u00020\\H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010/R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0012\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00152\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R*\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u00152\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u00106R\u0016\u00108\u001a\u0004\u0018\u0001098VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010/R\u0016\u0010=\u001a\u0004\u0018\u00010>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0016\u0010A\u001a\u0004\u0018\u00010>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010@R\u0016\u0010C\u001a\u0004\u0018\u00010DX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0016\u0010G\u001a\u0004\u0018\u00010H8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u001a\u0010K\u001a\b\u0012\u0004\u0012\u00020M0L8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0016\u0010P\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bT\u0010/R\u001b\u0010U\u001a\u00020V8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bY\u0010Z\u001a\u0004\bW\u0010XR\u001b\u0010[\u001a\u00020\\8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b_\u0010Z\u001a\u0004\b]\u0010^R\u001a\u0010`\u001a\b\u0012\u0004\u0012\u00020a0L8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bb\u0010OR\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020d0L8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\be\u0010OR\u0014\u0010f\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bf\u0010/R\u0018\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010RR\"\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u0001X\u0096\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001¨\u0006\u009e\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaField;", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "originalStatus", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;", "isVar", Argument.Delimiters.none, "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "lazyInitializer", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "lazyHasConstantInitializer", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/declarations/impl/FirResolvedDeclarationStatusImpl;ZLorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;Lkotlin/Lazy;Lkotlin/Lazy;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "()Z", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "value", "getLazyInitializer", "()Lkotlin/Lazy;", "getLazyHasConstantInitializer", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "isVal", "getter", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "getGetter", "()Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "setter", "getSetter", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "getBackingField", "()Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "initializer", "getInitializer", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "hasConstantInitializer", "getHasConstantInitializer", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "deprecationsProvider$delegate", "Lkotlin/Lazy;", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "status$delegate", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "getTypeParameters", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters", "isLocal", "transformReturnTypeRef", "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirField;", "transformGetter", "transformSetter", "transformBackingField", "transformOtherChildren", "transformReceiverParameter", "transformContextParameters", "acceptChildren", Argument.Delimiters.none, "R", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaField;", "transformStatus", "replaceReturnTypeRef", "newReturnTypeRef", "replaceAnnotations", "newAnnotations", "transformAnnotations", "replaceInitializer", "newInitializer", "transformTypeParameters", "replaceDelegate", "newDelegate", "delegate", "getDelegate", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "setContainerSource", "(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;)V", "transformInitializer", "replaceReceiverParameter", "newReceiverParameter", "replaceDeprecationsProvider", "newDeprecationsProvider", "transformDelegate", "replaceGetter", "newGetter", "replaceSetter", "newSetter", "replaceControlFlowGraphReference", "newControlFlowGraphReference", "replaceContextParameters", "newContextParameters", "replaceStatus", "newStatus", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaField extends FirField {
    private final FirJavaAnnotationList annotationList;
    private final FirDeclarationAttributes attributes;
    private final FirBackingField backingField;
    private DeserializedContainerSource containerSource;
    private final FirClassSymbol<?> containingClassSymbol;

    /* JADX INFO: renamed from: deprecationsProvider$delegate, reason: from kotlin metadata */
    private final Lazy deprecationsProvider;
    private final ConeSimpleKotlinType dispatchReceiverType;
    private final boolean isVar;
    private Lazy<Boolean> lazyHasConstantInitializer;
    private Lazy<? extends FirExpression> lazyInitializer;
    private final FirModuleData moduleData;
    private final Name name;
    private final FirDeclarationOrigin.Java origin;
    private final FirResolvedDeclarationStatusImpl originalStatus;
    private FirTypeRef returnTypeRef;
    private final KtSourceElement source;

    /* JADX INFO: renamed from: status$delegate, reason: from kotlin metadata */
    private final Lazy status;
    private final FirFieldSymbol symbol;

    @FirImplementationDetail
    public FirJavaField(KtSourceElement ktSourceElement, FirModuleData firModuleData, FirDeclarationOrigin.Java java, FirFieldSymbol firFieldSymbol, Name name, FirTypeRef firTypeRef, FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl, boolean z, FirJavaAnnotationList firJavaAnnotationList, Lazy<? extends FirExpression> lazy, Lazy<Boolean> lazy2, ConeSimpleKotlinType coneSimpleKotlinType, FirDeclarationAttributes firDeclarationAttributes, FirClassSymbol<?> firClassSymbol) {
        firModuleData.getClass();
        java.getClass();
        firFieldSymbol.getClass();
        name.getClass();
        firTypeRef.getClass();
        firResolvedDeclarationStatusImpl.getClass();
        firJavaAnnotationList.getClass();
        lazy.getClass();
        lazy2.getClass();
        firDeclarationAttributes.getClass();
        firClassSymbol.getClass();
        this.source = ktSourceElement;
        this.moduleData = firModuleData;
        this.origin = java;
        this.symbol = firFieldSymbol;
        this.name = name;
        this.returnTypeRef = firTypeRef;
        this.originalStatus = firResolvedDeclarationStatusImpl;
        this.isVar = z;
        this.annotationList = firJavaAnnotationList;
        this.dispatchReceiverType = coneSimpleKotlinType;
        this.attributes = firDeclarationAttributes;
        this.containingClassSymbol = firClassSymbol;
        this.lazyInitializer = lazy;
        this.lazyHasConstantInitializer = lazy2;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES()));
        this.deprecationsProvider = LazyKt.lazy(new Function0() { // from class: i95
            public final Object invoke() {
                return FirJavaField.a(this.b);
            }
        });
        this.status = LazyKt.lazy(new Function0() { // from class: j95
            public final Object invoke() {
                return FirJavaField.b(this.b);
            }
        });
    }

    public static DeprecationsProvider a(FirJavaField firJavaField) {
        return DeprecationUtilsKt.getDeprecationsProviderFromAnnotations$default(firJavaField.getAnnotations(), firJavaField.getModuleData().getSession(), true, null, 4, null);
    }

    public static FirDeclarationStatus b(FirJavaField firJavaField) {
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = firJavaField.originalStatus;
        List<FirStatusTransformerExtension> statusTransformerExtensions = FirStatusTransformerExtensionKt.getStatusTransformerExtensions(FirExtensionServiceKt.getExtensionService(firJavaField.getModuleData().getSession()));
        if (!statusTransformerExtensions.isEmpty()) {
            FirDeclarationStatus firDeclarationStatusTransformStatus = firResolvedDeclarationStatusImpl;
            for (FirStatusTransformerExtension firStatusTransformerExtension : statusTransformerExtensions) {
                if (firStatusTransformerExtension.needTransformStatus(firJavaField)) {
                    firDeclarationStatusTransformStatus = firStatusTransformerExtension.transformStatus(firDeclarationStatusTransformStatus, (FirField) firJavaField, (FirClassLikeSymbol<?>) firJavaField.containingClassSymbol, false);
                }
            }
            firDeclarationStatusTransformStatus.getClass();
            FirDeclarationStatusImpl firDeclarationStatusImpl = (FirDeclarationStatusImpl) firDeclarationStatusTransformStatus;
            if (firDeclarationStatusImpl != firResolvedDeclarationStatusImpl) {
                Intrinsics.areEqual(firDeclarationStatusImpl.getVisibility(), firResolvedDeclarationStatusImpl.getVisibility());
                Visibility visibility = firDeclarationStatusImpl.getVisibility();
                Modality modality = firDeclarationStatusImpl.getModality();
                if (modality == null) {
                    modality = firResolvedDeclarationStatusImpl.getModality();
                }
                return firDeclarationStatusImpl.resolved(visibility, modality, firResolvedDeclarationStatusImpl.getEffectiveVisibility());
            }
        }
        return firResolvedDeclarationStatusImpl;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getReturnTypeRef().accept(visitor, data);
        Iterator<T> it = getAnnotations().iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        Iterator<T> it2 = getTypeParameters().iterator();
        while (it2.hasNext()) {
            ((FirTypeParameterRef) it2.next()).accept(visitor, data);
        }
        getStatus().accept(visitor, data);
        FirExpression initializer = getInitializer();
        if (initializer != null) {
            initializer.accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return this.annotationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirBackingField getBackingField() {
        return this.backingField;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public List<FirValueParameter> getContextParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public FirControlFlowGraphReference getControlFlowGraphReference() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirExpression getDelegate() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeprecationsProvider getDeprecationsProvider() {
        return (DeprecationsProvider) this.deprecationsProvider.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public ConeSimpleKotlinType getDispatchReceiverType() {
        return this.dispatchReceiverType;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirPropertyAccessor getGetter() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField
    public boolean getHasConstantInitializer() {
        return ((Boolean) this.lazyHasConstantInitializer.getValue()).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirExpression getInitializer() {
        return (FirExpression) this.lazyInitializer.getValue();
    }

    public final Lazy<Boolean> getLazyHasConstantInitializer() {
        return this.lazyHasConstantInitializer;
    }

    public final Lazy<FirExpression> getLazyInitializer() {
        return this.lazyInitializer;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirReceiverParameter getReceiverParameter() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirTypeRef getReturnTypeRef() {
        return this.returnTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirPropertyAccessor getSetter() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public FirDeclarationStatus getStatus() {
        return (FirDeclarationStatus) this.status.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public List<FirTypeParameterRef> getTypeParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    /* JADX INFO: renamed from: isLocal */
    public boolean getIsLocal() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    /* JADX INFO: renamed from: isVal */
    public boolean getIsVal() {
        return !getIsVar();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    /* JADX INFO: renamed from: isVar, reason: from getter */
    public boolean getIsVar() {
        return this.isVar;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) throws KotlinNothingValueException {
        newAnnotations.getClass();
        UtilsKt.shouldNotBeCalled(this, new AnonymousClass1(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaField.replaceAnnotations.2
            public Object get() {
                return ((FirJavaField) ((CallableReference) this).receiver).getAnnotations();
            }
        });
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceContextParameters(List<? extends FirValueParameter> newContextParameters) {
        newContextParameters.getClass();
        throw new IllegalStateException("Body cannot be replaced for FirJavaField");
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceDelegate(FirExpression newDelegate) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceDeprecationsProvider(DeprecationsProvider newDeprecationsProvider) {
        newDeprecationsProvider.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceGetter(FirPropertyAccessor newGetter) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceInitializer(FirExpression newInitializer) {
        this.lazyInitializer = LazyKt.lazyOf(newInitializer);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReceiverParameter(FirReceiverParameter newReceiverParameter) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReturnTypeRef(FirTypeRef newReturnTypeRef) {
        newReturnTypeRef.getClass();
        setReturnTypeRef(newReturnTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceSetter(FirPropertyAccessor newSetter) {
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public void replaceStatus(FirDeclarationStatus newStatus) throws KotlinNothingValueException {
        newStatus.getClass();
        UtilsKt.shouldNotBeCalled(this, new C00291(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaField.replaceStatus.2
            public Object get() {
                return ((FirJavaField) ((CallableReference) this).receiver).getStatus();
            }
        });
        throw new KotlinNothingValueException();
    }

    public void setContainerSource(DeserializedContainerSource deserializedContainerSource) {
        this.containerSource = deserializedContainerSource;
    }

    public void setReturnTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.returnTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformBackingField(FirTransformer firTransformer, Object obj) {
        return transformBackingField((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirJavaField transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformReturnTypeRef((FirTransformer) transformer, (Object) data);
        transformTypeParameters((FirTransformer) transformer, (Object) data);
        transformOtherChildren((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformDelegate(FirTransformer firTransformer, Object obj) {
        return transformDelegate((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformGetter(FirTransformer firTransformer, Object obj) {
        return transformGetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirField transformInitializer(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirExpression initializer = getInitializer();
        replaceInitializer(initializer != null ? (FirExpression) FirTransformerUtilKt.transformSingle(initializer, transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirField transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformInitializer((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirField transformReturnTypeRef(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setReturnTypeRef((FirTypeRef) FirTransformerUtilKt.transformSingle(getReturnTypeRef(), transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformSetter(FirTransformer firTransformer, Object obj) {
        return transformSetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin.Java getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirJavaField transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirField transformBackingField(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirField transformContextParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirField transformDelegate(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirField transformGetter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirField transformReceiverParameter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirField transformSetter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public <D> FirJavaField transformStatus(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public <D> FirField transformTypeParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirField transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirFieldSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParameterRefsOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirField transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirVariable transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaField$replaceAnnotations$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<List<? extends FirAnnotation>, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, FirJavaField.class, "replaceAnnotations", "replaceAnnotations(Ljava/util/List;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(List<? extends FirAnnotation> list) throws KotlinNothingValueException {
            list.getClass();
            ((FirJavaField) ((CallableReference) this).receiver).replaceAnnotations(list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((List<? extends FirAnnotation>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaField$replaceStatus$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00291 extends FunctionReferenceImpl implements Function1<FirDeclarationStatus, Unit> {
        public C00291(Object obj) {
            super(1, obj, FirJavaField.class, "replaceStatus", "replaceStatus(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(FirDeclarationStatus firDeclarationStatus) throws KotlinNothingValueException {
            firDeclarationStatus.getClass();
            ((FirJavaField) ((CallableReference) this).receiver).replaceStatus(firDeclarationStatus);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((FirDeclarationStatus) obj);
            return Unit.INSTANCE;
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirVariable transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirField, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformInitializer(FirTransformer firTransformer, Object obj) {
        return transformInitializer((FirTransformer<? super Object>) firTransformer, obj);
    }
}
