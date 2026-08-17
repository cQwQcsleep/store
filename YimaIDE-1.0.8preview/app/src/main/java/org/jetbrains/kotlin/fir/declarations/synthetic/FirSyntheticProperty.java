package org.jetbrains.kotlin.fir.declarations.synthetic;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirPropertyBodyResolveState;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.UnresolvedDeprecationProvider;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0001\n\u0000\u0018\u00002\u00020\u0001B_\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u001a\u0002\b\u0015¢\u0006\u0004\b\u0013\u0010\u0014J5\u0010b\u001a\u00020c\"\u0004\b\u0000\u0010d\"\u0004\b\u0001\u0010e2\u0012\u0010f\u001a\u000e\u0012\u0004\u0012\u0002Hd\u0012\u0004\u0012\u0002He0g2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010iJ)\u0010j\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010n\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010o\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010p\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010q\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010r\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010s\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010t\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010u\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010v\u001a\u00020\u0001\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010wJ)\u0010x\u001a\u00020\u0000\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010mJ)\u0010y\u001a\u00020\u0001\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010wJ)\u0010z\u001a\u00020\u0001\"\u0004\b\u0000\u0010e2\f\u0010k\u001a\b\u0012\u0004\u0012\u0002He0l2\u0006\u0010h\u001a\u0002HeH\u0016¢\u0006\u0002\u0010wJ\u0010\u0010{\u001a\u00020c2\u0006\u0010|\u001a\u00020,H\u0016J\u0012\u0010}\u001a\u00020c2\b\u0010~\u001a\u0004\u0018\u00010BH\u0016J\u0011\u0010\u007f\u001a\u00020c2\u0007\u0010\u0080\u0001\u001a\u00020\u0012H\u0016J\u0014\u0010\u0081\u0001\u001a\u00020c2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010SH\u0016J\u0014\u0010\u0083\u0001\u001a\u00020c2\t\u0010\u0084\u0001\u001a\u0004\u0018\u000108H\u0016J\u0014\u0010\u0085\u0001\u001a\u00020c2\t\u0010\u0086\u0001\u001a\u0004\u0018\u000108H\u0016J\u0012\u0010\u0087\u0001\u001a\u00020c2\u0007\u0010\u0088\u0001\u001a\u00020[H\u0016J\u0015\u0010\u0089\u0001\u001a\u00020c2\n\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u008b\u0001H\u0016J\u0015\u0010\u008c\u0001\u001a\u00020c2\n\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008b\u0001H\u0016J\u0018\u0010\u008e\u0001\u001a\u00020c2\r\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020_0GH\u0016J\u0018\u0010\u0090\u0001\u001a\u00020c2\r\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020H0GH\u0016J\u0012\u0010\u0092\u0001\u001a\u00020c2\u0007\u0010\u0093\u0001\u001a\u00020\u000bH\u0016J\n\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u001aR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010'\u001a\u0004\u0018\u00010(8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020,8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0016\u0010/\u001a\u0004\u0018\u0001008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u0002048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0016\u00107\u001a\u0004\u0018\u0001088VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0016\u0010;\u001a\u0004\u0018\u0001088VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010:R\u0016\u0010=\u001a\u0004\u0018\u00010>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0016\u0010A\u001a\u0004\u0018\u00010B8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010\u001aR\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020H0G8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u001a\u0010K\u001a\b\u0012\u0004\u0012\u00020L0G8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bM\u0010JR\u0016\u0010N\u001a\u0004\u0018\u00010O8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0016\u0010R\u001a\u0004\u0018\u00010SX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u0014\u0010V\u001a\u00020WX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0014\u0010Z\u001a\u00020[8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u001a\u0010^\u001a\b\u0012\u0004\u0012\u00020_0G8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010JR\u0014\u0010a\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\ba\u0010\u001a¨\u0006\u0096\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "isVar", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "customStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getter", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticPropertyAccessor;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "setter", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/name/Name;ZLorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticPropertyAccessor;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticPropertyAccessor;Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "()Z", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "getGetter", "()Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticPropertyAccessor;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getSetter", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "status", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "getBackingField", "()Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getInitializer", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "delegate", "getDelegate", "delegateFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "getDelegateFieldSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "isVal", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "getTypeParameters", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "bodyResolveState", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyBodyResolveState;", "getBodyResolveState", "()Lorg/jetbrains/kotlin/fir/declarations/FirPropertyBodyResolveState;", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters", "isLocal", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "transformReturnTypeRef", "transformReceiverParameter", "transformStatus", "transformOtherChildren", "transformInitializer", "transformGetter", "transformSetter", "transformBackingField", "transformDelegate", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "transformAnnotations", "transformTypeParameters", "transformContextParameters", "replaceReturnTypeRef", "newReturnTypeRef", "replaceReceiverParameter", "newReceiverParameter", "replaceDeprecationsProvider", "newDeprecationsProvider", "replaceControlFlowGraphReference", "newControlFlowGraphReference", "replaceInitializer", "newInitializer", "replaceDelegate", "newDelegate", "replaceBodyResolveState", "newBodyResolveState", "replaceGetter", "newGetter", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "replaceSetter", "newSetter", "replaceContextParameters", "newContextParameters", "replaceAnnotations", "newAnnotations", "replaceStatus", "newStatus", "notSupported", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticProperty extends FirProperty {
    private final FirDeclarationAttributes attributes;
    private final FirControlFlowGraphReference controlFlowGraphReference;
    private final FirDeclarationStatus customStatus;
    private final DeprecationsProvider deprecationsProvider;
    private final ConeSimpleKotlinType dispatchReceiverType;
    private final FirSyntheticPropertyAccessor getter;
    private final boolean isVar;
    private final FirModuleData moduleData;
    private final Name name;
    private final FirSyntheticPropertyAccessor setter;
    private final FirSyntheticPropertySymbol symbol;

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @FirImplementationDetail
    public FirSyntheticProperty(FirModuleData firModuleData, Name name, boolean z, FirSyntheticPropertySymbol firSyntheticPropertySymbol, FirDeclarationStatus firDeclarationStatus, FirSyntheticPropertyAccessor firSyntheticPropertyAccessor, ConeSimpleKotlinType coneSimpleKotlinType, FirSyntheticPropertyAccessor firSyntheticPropertyAccessor2, DeprecationsProvider deprecationsProvider) throws KotlinIllegalArgumentExceptionWithAttachments {
        firModuleData.getClass();
        name.getClass();
        firSyntheticPropertySymbol.getClass();
        firSyntheticPropertyAccessor.getClass();
        deprecationsProvider.getClass();
        this.moduleData = firModuleData;
        this.name = name;
        this.isVar = z;
        this.symbol = firSyntheticPropertySymbol;
        this.customStatus = firDeclarationStatus;
        this.getter = firSyntheticPropertyAccessor;
        this.dispatchReceiverType = coneSimpleKotlinType;
        this.setter = firSyntheticPropertyAccessor2;
        this.deprecationsProvider = deprecationsProvider;
        getSymbol().bind(this);
        if (firDeclarationStatus == null || (firDeclarationStatus instanceof FirResolvedDeclarationStatus)) {
            this.attributes = new FirDeclarationAttributes();
            return;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("The status must be resolved as the synthetic property is stateless and hence is not supposed to be transformed later");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "property", this);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    private final Void notSupported() {
        throw new IllegalStateException("Transformation of synthetic property isn't supported");
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getReturnTypeRef().accept(visitor, data);
        getStatus().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirBackingField getBackingField() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty
    public FirPropertyBodyResolveState getBodyResolveState() {
        return FirPropertyBodyResolveState.ALL_BODIES_RESOLVED;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeserializedContainerSource getContainerSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public List<FirValueParameter> getContextParameters() {
        return getGetter().getContextParameters();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public FirControlFlowGraphReference getControlFlowGraphReference() {
        return this.controlFlowGraphReference;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirExpression getDelegate() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty
    public FirDelegateFieldSymbol getDelegateFieldSymbol() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeprecationsProvider getDeprecationsProvider() {
        return this.deprecationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public ConeSimpleKotlinType getDispatchReceiverType() {
        return this.dispatchReceiverType;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirExpression getInitializer() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin getOrigin() {
        return FirDeclarationOrigin.Synthetic.JavaProperty.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirReceiverParameter getReceiverParameter() {
        return getGetter().getReceiverParameter();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirTypeRef getReturnTypeRef() {
        return getGetter().getReturnTypeRef();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public FirDeclarationStatus getStatus() {
        FirDeclarationStatus firDeclarationStatus = this.customStatus;
        return firDeclarationStatus == null ? getGetter().getStatus() : firDeclarationStatus;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public List<FirTypeParameter> getTypeParameters() {
        return getGetter().getTypeParameters();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    /* JADX INFO: renamed from: isLocal */
    public boolean getIsLocal() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public boolean isVal() {
        return !getIsVar();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    /* JADX INFO: renamed from: isVar, reason: from getter */
    public boolean getIsVar() {
        return this.isVar;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) throws KotlinNothingValueException {
        newAnnotations.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty
    public void replaceBodyResolveState(FirPropertyBodyResolveState newBodyResolveState) throws KotlinNothingValueException {
        newBodyResolveState.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceContextParameters(List<? extends FirValueParameter> newContextParameters) throws KotlinNothingValueException {
        newContextParameters.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference) throws KotlinNothingValueException {
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceDelegate(FirExpression newDelegate) throws KotlinNothingValueException {
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceDeprecationsProvider(DeprecationsProvider newDeprecationsProvider) throws KotlinNothingValueException {
        newDeprecationsProvider.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceGetter(FirPropertyAccessor newGetter) throws KotlinNothingValueException {
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceInitializer(FirExpression newInitializer) throws KotlinNothingValueException {
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReceiverParameter(FirReceiverParameter newReceiverParameter) throws KotlinNothingValueException {
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReturnTypeRef(FirTypeRef newReturnTypeRef) throws KotlinNothingValueException {
        newReturnTypeRef.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceSetter(FirPropertyAccessor newSetter) throws KotlinNothingValueException {
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public void replaceStatus(FirDeclarationStatus newStatus) throws KotlinNothingValueException {
        newStatus.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirSyntheticProperty transformAnnotations(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirSyntheticProperty transformBackingField(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirSyntheticProperty transformChildren(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirProperty transformContextParameters(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirProperty transformDelegate(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirSyntheticProperty transformGetter(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirSyntheticProperty transformInitializer(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirSyntheticProperty transformOtherChildren(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirSyntheticProperty transformReceiverParameter(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirSyntheticProperty transformReturnTypeRef(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirSyntheticProperty transformSetter(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public <D> FirSyntheticProperty transformStatus(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public <D> FirProperty transformTypeParameters(FirTransformer<? super D> transformer, D data) throws KotlinNothingValueException {
        transformer.getClass();
        notSupported();
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirSyntheticPropertyAccessor getGetter() {
        return this.getter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirSyntheticPropertyAccessor getSetter() {
        return this.setter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirSyntheticPropertySymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformBackingField(FirTransformer firTransformer, Object obj) {
        return transformBackingField((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformDelegate(FirTransformer firTransformer, Object obj) {
        return transformDelegate((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformGetter(FirTransformer firTransformer, Object obj) {
        return transformGetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformInitializer(FirTransformer firTransformer, Object obj) {
        return transformInitializer((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirProperty transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirProperty transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformSetter(FirTransformer firTransformer, Object obj) {
        return transformSetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirProperty transformBackingField(FirTransformer firTransformer, Object obj) {
        return transformBackingField((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirProperty transformGetter(FirTransformer firTransformer, Object obj) {
        return transformGetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirProperty transformInitializer(FirTransformer firTransformer, Object obj) {
        return transformInitializer((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirProperty transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirProperty transformSetter(FirTransformer firTransformer, Object obj) {
        return transformSetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirProperty transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParameterRefsOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParametersOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirProperty transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirVariable transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirVariable transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirProperty, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirSyntheticProperty(FirModuleData firModuleData, Name name, boolean z, FirSyntheticPropertySymbol firSyntheticPropertySymbol, FirDeclarationStatus firDeclarationStatus, FirSyntheticPropertyAccessor firSyntheticPropertyAccessor, ConeSimpleKotlinType coneSimpleKotlinType, FirSyntheticPropertyAccessor firSyntheticPropertyAccessor2, DeprecationsProvider deprecationsProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firModuleData, name, z, firSyntheticPropertySymbol, firDeclarationStatus, firSyntheticPropertyAccessor, coneSimpleKotlinType, (i & 128) != 0 ? null : firSyntheticPropertyAccessor2, (i & 256) != 0 ? UnresolvedDeprecationProvider.INSTANCE : deprecationsProvider);
    }
}
