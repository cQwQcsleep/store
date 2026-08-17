package org.jetbrains.kotlin.fir.declarations.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bF\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0019\b\u0000\u0018\u00002\u00020\u0001Bï\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001e\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\b\u0010!\u001a\u0004\u0018\u00010\"\u0012\u0006\u0010#\u001a\u00020$\u0012\b\u0010%\u001a\u0004\u0018\u00010&\u0012\b\u0010'\u001a\u0004\u0018\u00010(\u0012\u0006\u0010)\u001a\u00020*\u0012\u0006\u0010+\u001a\u00020,\u0012\u0006\u0010-\u001a\u00020,\u0012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u001e\u0012\u0006\u00100\u001a\u00020\u0012¢\u0006\u0004\b1\u00102J6\u0010y\u001a\u00020z\"\u0004\b\u0000\u0010{\"\u0004\b\u0001\u0010|2\u0012\u0010}\u001a\u000e\u0012\u0004\u0012\u0002H{\u0012\u0004\u0012\u0002H|0~2\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0080\u0001J-\u0010\u0081\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u0085\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u0086\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u0087\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u0088\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u0089\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u008a\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u008b\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u008c\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J-\u0010\u008d\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010|2\u000e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u0002H|0\u0083\u00012\u0006\u0010\u007f\u001a\u0002H|H\u0016¢\u0006\u0003\u0010\u0084\u0001J\u0019\u0010\u008e\u0001\u001a\u00020z2\u000e\u0010\u008f\u0001\u001a\t\u0012\u0004\u0012\u00020\b0\u0090\u0001H\u0016J\u0012\u0010\u0091\u0001\u001a\u00020z2\u0007\u0010\u0092\u0001\u001a\u00020\u0010H\u0016J\u0012\u0010\u0093\u0001\u001a\u00020z2\u0007\u0010\u0094\u0001\u001a\u00020\u0012H\u0016J\u0014\u0010\u0095\u0001\u001a\u00020z2\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0097\u0001\u001a\u00020z2\u0007\u0010\u0098\u0001\u001a\u00020\u0016H\u0016J\u0019\u0010\u0099\u0001\u001a\u00020z2\u000e\u0010\u009a\u0001\u001a\t\u0012\u0004\u0012\u00020\u001a0\u0090\u0001H\u0016J\u0014\u0010\u009b\u0001\u001a\u00020z2\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u001cH\u0016J\u0019\u0010\u009d\u0001\u001a\u00020z2\u000e\u0010\u009e\u0001\u001a\t\u0012\u0004\u0012\u00020\u001a0\u0090\u0001H\u0016J\u0014\u0010\u009f\u0001\u001a\u00020z2\t\u0010 \u0001\u001a\u0004\u0018\u00010 H\u0016J\u0014\u0010¡\u0001\u001a\u00020z2\t\u0010¢\u0001\u001a\u0004\u0018\u00010\"H\u0016J\u0014\u0010£\u0001\u001a\u00020z2\t\u0010¤\u0001\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010¥\u0001\u001a\u00020z2\u0007\u0010¦\u0001\u001a\u00020*H\u0016J\u0012\u0010§\u0001\u001a\u00020z2\u0007\u0010¨\u0001\u001a\u00020\u0012H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001a\u0010\u0011\u001a\u00020\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001a\u0010\u0015\u001a\u00020\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\bR\u00106\"\u0004\bS\u00108R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u00106R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u0014\u0010#\u001a\u00020$X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\ba\u0010bR\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\u001a\u0010)\u001a\u00020*X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\u0014\u0010+\u001a\u00020,X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010oR\u0014\u0010-\u001a\u00020,X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010oR\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bq\u00106R\u001a\u00100\u001a\u00020\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010E\"\u0004\bs\u0010GR\u0014\u0010t\u001a\u00020,8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bt\u0010oR\u0016\u0010u\u001a\u0004\u0018\u00010v8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bw\u0010x¨\u0006©\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirAnonymousFunctionImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "valueParameters", Argument.Delimiters.none, "body", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "invocationKind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "inlineStatus", "Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;", "isLambda", Argument.Delimiters.none, "hasExplicitParameterList", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "typeRef", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;Lorg/jetbrains/kotlin/fir/FirLabel;Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;ZZLjava/util/List;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "setReceiverParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)V", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getContextParameters-5e3fPpI", "setContextParameters-GqUYU-s", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "setControlFlowGraphReference", "(Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;)V", "getValueParameters", "getBody", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBody", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "getContractDescription", "()Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "setContractDescription", "(Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "getLabel", "()Lorg/jetbrains/kotlin/fir/FirLabel;", "setLabel", "(Lorg/jetbrains/kotlin/fir/FirLabel;)V", "getInvocationKind", "()Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "setInvocationKind", "(Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;)V", "getInlineStatus", "()Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;", "setInlineStatus", "(Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;)V", "()Z", "getHasExplicitParameterList", "getTypeParameters", "getTypeRef", "setTypeRef", "isLocal", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirAnonymousFunctionImpl;", "transformAnnotations", "transformStatus", "transformReturnTypeRef", "transformReceiverParameter", "transformContextParameters", "transformValueParameters", "transformBody", "transformContractDescription", "transformTypeParameters", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceStatus", "newStatus", "replaceReturnTypeRef", "newReturnTypeRef", "replaceReceiverParameter", "newReceiverParameter", "replaceDeprecationsProvider", "newDeprecationsProvider", "replaceContextParameters", "newContextParameters", "replaceControlFlowGraphReference", "newControlFlowGraphReference", "replaceValueParameters", "newValueParameters", "replaceBody", "newBody", "replaceContractDescription", "newContractDescription", "replaceInvocationKind", "newInvocationKind", "replaceInlineStatus", "newInlineStatus", "replaceTypeRef", "newTypeRef", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousFunctionImpl extends FirAnonymousFunction {
    private List<FirAnnotation> annotations;
    private final FirDeclarationAttributes attributes;
    private FirBlock body;
    private List<FirValueParameter> contextParameters;
    private FirContractDescription contractDescription;
    private FirControlFlowGraphReference controlFlowGraphReference;
    private DeprecationsProvider deprecationsProvider;
    private final ConeSimpleKotlinType dispatchReceiverType;
    private final boolean hasExplicitParameterList;
    private InlineStatus inlineStatus;
    private EventOccurrencesRange invocationKind;
    private final boolean isLambda;
    private FirLabel label;
    private final FirModuleData moduleData;
    private final FirDeclarationOrigin origin;
    private FirReceiverParameter receiverParameter;
    private FirTypeRef returnTypeRef;
    private final KtSourceElement source;
    private FirDeclarationStatus status;
    private final FirAnonymousFunctionSymbol symbol;
    private final List<FirTypeParameter> typeParameters;
    private FirTypeRef typeRef;
    private final List<FirValueParameter> valueParameters;

    private FirAnonymousFunctionImpl(KtSourceElement ktSourceElement, FirResolvePhase firResolvePhase, List<FirAnnotation> list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, FirDeclarationStatus firDeclarationStatus, FirTypeRef firTypeRef, FirReceiverParameter firReceiverParameter, DeprecationsProvider deprecationsProvider, ConeSimpleKotlinType coneSimpleKotlinType, List<FirValueParameter> list2, FirControlFlowGraphReference firControlFlowGraphReference, List<FirValueParameter> list3, FirBlock firBlock, FirContractDescription firContractDescription, FirAnonymousFunctionSymbol firAnonymousFunctionSymbol, FirLabel firLabel, EventOccurrencesRange eventOccurrencesRange, InlineStatus inlineStatus, boolean z, boolean z2, List<FirTypeParameter> list4, FirTypeRef firTypeRef2) {
        firResolvePhase.getClass();
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firDeclarationAttributes.getClass();
        firDeclarationStatus.getClass();
        firTypeRef.getClass();
        deprecationsProvider.getClass();
        list3.getClass();
        firAnonymousFunctionSymbol.getClass();
        inlineStatus.getClass();
        list4.getClass();
        firTypeRef2.getClass();
        this.source = ktSourceElement;
        this.annotations = list;
        this.moduleData = firModuleData;
        this.origin = firDeclarationOrigin;
        this.attributes = firDeclarationAttributes;
        this.status = firDeclarationStatus;
        this.returnTypeRef = firTypeRef;
        this.receiverParameter = firReceiverParameter;
        this.deprecationsProvider = deprecationsProvider;
        this.dispatchReceiverType = coneSimpleKotlinType;
        this.contextParameters = list2;
        this.controlFlowGraphReference = firControlFlowGraphReference;
        this.valueParameters = list3;
        this.body = firBlock;
        this.contractDescription = firContractDescription;
        this.symbol = firAnonymousFunctionSymbol;
        this.label = firLabel;
        this.invocationKind = eventOccurrencesRange;
        this.inlineStatus = inlineStatus;
        this.isLambda = z;
        this.hasExplicitParameterList = z2;
        this.typeParameters = list4;
        this.typeRef = firTypeRef2;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(firResolvePhase));
        if (getSource() == null && Intrinsics.areEqual(getOrigin(), FirDeclarationOrigin.Source.INSTANCE)) {
            z1f.a(Reflection.getOrCreateKotlinClass(FirAnonymousFunctionImpl.class).getSimpleName(), " with Source origin was instantiated without a source element.");
            throw null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m290getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getStatus().accept(visitor, data);
        getReturnTypeRef().accept(visitor, data);
        FirReceiverParameter receiverParameter = getReceiverParameter();
        if (receiverParameter != null) {
            receiverParameter.accept(visitor, data);
        }
        Iterator<T> it2 = MutableOrEmptyList.m194boximpl(m291getContextParameters5e3fPpI()).iterator();
        while (it2.hasNext()) {
            ((FirValueParameter) it2.next()).accept(visitor, data);
        }
        FirControlFlowGraphReference controlFlowGraphReference = getControlFlowGraphReference();
        if (controlFlowGraphReference != null) {
            controlFlowGraphReference.accept(visitor, data);
        }
        Iterator<T> it3 = getValueParameters().iterator();
        while (it3.hasNext()) {
            ((FirValueParameter) it3.next()).accept(visitor, data);
        }
        FirBlock body = getBody();
        if (body != null) {
            body.accept(visitor, data);
        }
        FirContractDescription contractDescription = getContractDescription();
        if (contractDescription != null) {
            contractDescription.accept(visitor, data);
        }
        FirLabel label = getLabel();
        if (label != null) {
            label.accept(visitor, data);
        }
        Iterator<T> it4 = getTypeParameters().iterator();
        while (it4.hasNext()) {
            ((FirTypeParameter) it4.next()).accept(visitor, data);
        }
        getTypeRef().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m290getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m290getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner
    public FirBlock getBody() {
        return this.body;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeserializedContainerSource getContainerSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ List getContextParameters() {
        return MutableOrEmptyList.m194boximpl(m291getContextParameters5e3fPpI());
    }

    /* JADX INFO: renamed from: getContextParameters-5e3fPpI, reason: not valid java name */
    public List<FirValueParameter> m291getContextParameters5e3fPpI() {
        return this.contextParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner
    public FirContractDescription getContractDescription() {
        return this.contractDescription;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public FirControlFlowGraphReference getControlFlowGraphReference() {
        return this.controlFlowGraphReference;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeprecationsProvider getDeprecationsProvider() {
        return this.deprecationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public ConeSimpleKotlinType getDispatchReceiverType() {
        return this.dispatchReceiverType;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public boolean getHasExplicitParameterList() {
        return this.hasExplicitParameterList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public InlineStatus getInlineStatus() {
        return this.inlineStatus;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public EventOccurrencesRange getInvocationKind() {
        return this.invocationKind;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public FirLabel getLabel() {
        return this.label;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirReceiverParameter getReceiverParameter() {
        return this.receiverParameter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirTypeRef getReturnTypeRef() {
        return this.returnTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public FirDeclarationStatus getStatus() {
        return this.status;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public List<FirTypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public FirTypeRef getTypeRef() {
        return this.typeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner
    public List<FirValueParameter> getValueParameters() {
        return this.valueParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    /* JADX INFO: renamed from: isLambda, reason: from getter */
    public boolean getIsLambda() {
        return this.isLambda;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    /* JADX INFO: renamed from: isLocal */
    public boolean getIsLocal() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m292setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public void replaceBody(FirBlock newBody) {
        setBody(newBody);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceContextParameters(List<? extends FirValueParameter> newContextParameters) {
        newContextParameters.getClass();
        m293setContextParametersGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newContextParameters));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner
    public void replaceContractDescription(FirContractDescription newContractDescription) {
        setContractDescription(newContractDescription);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference) {
        setControlFlowGraphReference(newControlFlowGraphReference);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceDeprecationsProvider(DeprecationsProvider newDeprecationsProvider) {
        newDeprecationsProvider.getClass();
        setDeprecationsProvider(newDeprecationsProvider);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public void replaceInlineStatus(InlineStatus newInlineStatus) {
        newInlineStatus.getClass();
        setInlineStatus(newInlineStatus);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public void replaceInvocationKind(EventOccurrencesRange newInvocationKind) {
        setInvocationKind(newInvocationKind);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReceiverParameter(FirReceiverParameter newReceiverParameter) {
        setReceiverParameter(newReceiverParameter);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReturnTypeRef(FirTypeRef newReturnTypeRef) {
        newReturnTypeRef.getClass();
        setReturnTypeRef(newReturnTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public void replaceStatus(FirDeclarationStatus newStatus) {
        newStatus.getClass();
        setStatus(newStatus);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
    public void replaceTypeRef(FirTypeRef newTypeRef) {
        newTypeRef.getClass();
        setTypeRef(newTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public void replaceValueParameters(List<? extends FirValueParameter> newValueParameters) {
        newValueParameters.getClass();
        if (getValueParameters() == newValueParameters) {
            return;
        }
        getValueParameters().clear();
        getValueParameters().addAll(newValueParameters);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m292setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setBody(FirBlock firBlock) {
        this.body = firBlock;
    }

    /* JADX INFO: renamed from: setContextParameters-GqUYU-s, reason: not valid java name */
    public void m293setContextParametersGqUYUs(List<FirValueParameter> list) {
        this.contextParameters = list;
    }

    public void setContractDescription(FirContractDescription firContractDescription) {
        this.contractDescription = firContractDescription;
    }

    public void setControlFlowGraphReference(FirControlFlowGraphReference firControlFlowGraphReference) {
        this.controlFlowGraphReference = firControlFlowGraphReference;
    }

    public void setDeprecationsProvider(DeprecationsProvider deprecationsProvider) {
        deprecationsProvider.getClass();
        this.deprecationsProvider = deprecationsProvider;
    }

    public void setInlineStatus(InlineStatus inlineStatus) {
        inlineStatus.getClass();
        this.inlineStatus = inlineStatus;
    }

    public void setInvocationKind(EventOccurrencesRange eventOccurrencesRange) {
        this.invocationKind = eventOccurrencesRange;
    }

    public void setLabel(FirLabel firLabel) {
        this.label = firLabel;
    }

    public void setReceiverParameter(FirReceiverParameter firReceiverParameter) {
        this.receiverParameter = firReceiverParameter;
    }

    public void setReturnTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.returnTypeRef = firTypeRef;
    }

    public void setStatus(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        this.status = firDeclarationStatus;
    }

    public void setTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.typeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirAnonymousFunctionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m290getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public <D> FirAnonymousFunctionImpl transformBody(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirBlock body = getBody();
        setBody(body != null ? (FirBlock) body.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirAnonymousFunctionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformStatus((FirTransformer) transformer, (Object) data);
        transformReturnTypeRef((FirTransformer) transformer, (Object) data);
        transformReceiverParameter((FirTransformer) transformer, (Object) data);
        transformContextParameters((FirTransformer) transformer, (Object) data);
        FirControlFlowGraphReference controlFlowGraphReference = getControlFlowGraphReference();
        setControlFlowGraphReference(controlFlowGraphReference != null ? (FirControlFlowGraphReference) controlFlowGraphReference.transform(transformer, data) : null);
        transformValueParameters((FirTransformer) transformer, (Object) data);
        transformBody((FirTransformer) transformer, (Object) data);
        transformContractDescription((FirTransformer) transformer, (Object) data);
        FirLabel label = getLabel();
        setLabel(label != null ? (FirLabel) label.transform(transformer, data) : null);
        transformTypeParameters((FirTransformer) transformer, (Object) data);
        setTypeRef((FirTypeRef) getTypeRef().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirAnonymousFunctionImpl transformContextParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m291getContextParameters5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner
    public <D> FirAnonymousFunctionImpl transformContractDescription(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirContractDescription contractDescription = getContractDescription();
        setContractDescription(contractDescription != null ? (FirContractDescription) contractDescription.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirAnonymousFunctionImpl transformReceiverParameter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirReceiverParameter receiverParameter = getReceiverParameter();
        setReceiverParameter(receiverParameter != null ? (FirReceiverParameter) receiverParameter.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirAnonymousFunctionImpl transformReturnTypeRef(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setReturnTypeRef((FirTypeRef) getReturnTypeRef().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public <D> FirAnonymousFunctionImpl transformStatus(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setStatus((FirDeclarationStatus) getStatus().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public <D> FirAnonymousFunctionImpl transformTypeParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getTypeParameters(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public <D> FirAnonymousFunctionImpl transformValueParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getValueParameters(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirAnonymousFunctionSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public /* bridge */ /* synthetic */ FirFunction transformValueParameters(FirTransformer firTransformer, Object obj) {
        return transformValueParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirFunction transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirFunction transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformValueParameters(FirTransformer firTransformer, Object obj) {
        return transformValueParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirFunction transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParameterRefsOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParametersOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirFunction transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirFunction transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public /* bridge */ /* synthetic */ FirFunction transformBody(FirTransformer firTransformer, Object obj) {
        return transformBody((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner
    public /* bridge */ /* synthetic */ FirContractDescriptionOwner transformContractDescription(FirTransformer firTransformer, Object obj) {
        return transformContractDescription((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformBody(FirTransformer firTransformer, Object obj) {
        return transformBody((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformContractDescription(FirTransformer firTransformer, Object obj) {
        return transformContractDescription((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirFunction transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction, org.jetbrains.kotlin.fir.declarations.FirFunction, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirAnonymousFunction transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirAnonymousFunctionImpl(KtSourceElement ktSourceElement, FirResolvePhase firResolvePhase, List list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, FirDeclarationStatus firDeclarationStatus, FirTypeRef firTypeRef, FirReceiverParameter firReceiverParameter, DeprecationsProvider deprecationsProvider, ConeSimpleKotlinType coneSimpleKotlinType, List list2, FirControlFlowGraphReference firControlFlowGraphReference, List list3, FirBlock firBlock, FirContractDescription firContractDescription, FirAnonymousFunctionSymbol firAnonymousFunctionSymbol, FirLabel firLabel, EventOccurrencesRange eventOccurrencesRange, InlineStatus inlineStatus, boolean z, boolean z2, List list4, FirTypeRef firTypeRef2, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, firResolvePhase, list, firModuleData, firDeclarationOrigin, firDeclarationAttributes, firDeclarationStatus, firTypeRef, firReceiverParameter, deprecationsProvider, coneSimpleKotlinType, list2, firControlFlowGraphReference, list3, firBlock, firContractDescription, firAnonymousFunctionSymbol, firLabel, eventOccurrencesRange, inlineStatus, z, z2, list4, firTypeRef2);
    }
}
