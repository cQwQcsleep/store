package org.jetbrains.kotlin.fir.java.declarations;

import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.PropertyReference0Impl;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.EmptyDeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b&\u0018\u00002\u00020\u0001Bs\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013\u0012\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u001a\u0002\b\u001b¢\u0006\u0004\b\u0019\u0010\u001aJ5\u0010t\u001a\u00020u\"\u0004\b\u0000\u0010v\"\u0004\b\u0001\u0010w2\u0012\u0010x\u001a\u000e\u0012\u0004\u0012\u0002Hv\u0012\u0004\u0012\u0002Hw0y2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010{J)\u0010|\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0080\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0081\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0082\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0083\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0084\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0085\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0086\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u0087\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ\u0018\u0010\u0088\u0001\u001a\u00020u2\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020E0DH\u0016J*\u0010\u008a\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u008b\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u008c\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ*\u0010\u008d\u0001\u001a\u00020\u0001\"\u0004\b\u0000\u0010w2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002Hw0~2\u0006\u0010z\u001a\u0002HwH\u0016¢\u0006\u0002\u0010\u007fJ\u0012\u0010\u008e\u0001\u001a\u00020u2\u0007\u0010\u008f\u0001\u001a\u00020\u000bH\u0016J\u0014\u0010\u0090\u0001\u001a\u00020u2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010IH\u0016J\u0012\u0010\u0092\u0001\u001a\u00020u2\u0007\u0010\u0093\u0001\u001a\u00020MH\u0016J\u0014\u0010\u0094\u0001\u001a\u00020u2\t\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\u0014\u0010\u0096\u0001\u001a\u00020u2\t\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\u0014\u0010\u0098\u0001\u001a\u00020u2\t\u0010\u0099\u0001\u001a\u0004\u0018\u00010_H\u0016J\u0014\u0010\u009a\u0001\u001a\u00020u2\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u0014H\u0016J\u0014\u0010\u009c\u0001\u001a\u00020u2\t\u0010\u009d\u0001\u001a\u0004\u0018\u00010UH\u0016J\u0014\u0010\u009e\u0001\u001a\u00020u2\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010UH\u0016J\u0018\u0010 \u0001\u001a\u00020u2\r\u0010¡\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010DH\u0016J\u0012\u0010¢\u0001\u001a\u00020u2\u0007\u0010£\u0001\u001a\u00020fH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010\n\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0018\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u00104R(\u00106\u001a\u0004\u0018\u00010\u00142\b\u00105\u001a\u0004\u0018\u00010\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u00104R\u0014\u0010<\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u00104R\u0014\u0010=\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u00104R\u0014\u0010B\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u00104R\u001a\u0010C\u001a\b\u0012\u0004\u0012\u00020E0D8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0016\u0010H\u001a\u0004\u0018\u00010I8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020M8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0016\u0010P\u001a\u0004\u0018\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bQ\u00108R\u0016\u0010R\u001a\u0004\u0018\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u00108R\u0016\u0010T\u001a\u0004\u0018\u00010U8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0016\u0010X\u001a\u0004\u0018\u00010U8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bY\u0010WR\u0016\u0010Z\u001a\u0004\u0018\u00010[8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0016\u0010^\u001a\u0004\u0018\u00010_8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u001a\u0010b\u001a\b\u0012\u0004\u0012\u00020c0D8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010GR\u0014\u0010e\u001a\u00020f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0016\u0010i\u001a\u0004\u0018\u00010j8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bk\u0010lR\u0016\u0010m\u001a\u0004\u0018\u00010n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bo\u0010pR\u001a\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00010D8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\br\u0010GR\u0014\u0010s\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bs\u00104¨\u0006¤\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaValueParameter;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "lazyDefaultValue", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "isVararg", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;Lkotlin/Lazy;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;Z)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$Java;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "getAnnotationList", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "getLazyDefaultValue", "()Lkotlin/Lazy;", "setLazyDefaultValue", "(Lkotlin/Lazy;)V", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "()Z", "value", "defaultValue", "getDefaultValue", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setDefaultValue", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "isCrossinline", "isNoinline", "valueParameterKind", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameterKind;", "getValueParameterKind", "()Lorg/jetbrains/kotlin/fir/declarations/FirValueParameterKind;", "isVal", "isVar", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "initializer", "getInitializer", "delegate", "getDelegate", "getter", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "getGetter", "()Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "setter", "getSetter", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "getBackingField", "()Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "getTypeParameters", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "contextParameters", "getContextParameters", "isLocal", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "transformReturnTypeRef", "transformReceiverParameter", "transformContextParameters", "transformInitializer", "transformDelegate", "transformGetter", "transformSetter", "transformBackingField", "replaceAnnotations", "newAnnotations", "transformAnnotations", "transformOtherChildren", "transformTypeParameters", "transformStatus", "replaceReturnTypeRef", "newReturnTypeRef", "replaceReceiverParameter", "newReceiverParameter", "replaceDeprecationsProvider", "newDeprecationsProvider", "replaceInitializer", "newInitializer", "replaceDelegate", "newDelegate", "replaceControlFlowGraphReference", "newControlFlowGraphReference", "replaceDefaultValue", "newDefaultValue", "replaceGetter", "newGetter", "replaceSetter", "newSetter", "replaceContextParameters", "newContextParameters", "replaceStatus", "newStatus", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaValueParameter extends FirValueParameter {
    private final FirJavaAnnotationList annotationList;
    private final FirDeclarationAttributes attributes;
    private final FirFunctionSymbol<?> containingDeclarationSymbol;
    private final boolean isVararg;
    private Lazy<? extends FirExpression> lazyDefaultValue;
    private final FirModuleData moduleData;
    private final Name name;
    private final FirDeclarationOrigin.Java origin;
    private FirTypeRef returnTypeRef;
    private final KtSourceElement source;
    private final FirValueParameterSymbol symbol;

    @FirImplementationDetail
    public FirJavaValueParameter(KtSourceElement ktSourceElement, FirModuleData firModuleData, FirDeclarationOrigin.Java java, FirDeclarationAttributes firDeclarationAttributes, FirTypeRef firTypeRef, Name name, FirValueParameterSymbol firValueParameterSymbol, FirJavaAnnotationList firJavaAnnotationList, Lazy<? extends FirExpression> lazy, FirFunctionSymbol<?> firFunctionSymbol, boolean z) {
        firModuleData.getClass();
        java.getClass();
        firDeclarationAttributes.getClass();
        firTypeRef.getClass();
        name.getClass();
        firValueParameterSymbol.getClass();
        firJavaAnnotationList.getClass();
        firFunctionSymbol.getClass();
        this.source = ktSourceElement;
        this.moduleData = firModuleData;
        this.origin = java;
        this.attributes = firDeclarationAttributes;
        this.returnTypeRef = firTypeRef;
        this.name = name;
        this.symbol = firValueParameterSymbol;
        this.annotationList = firJavaAnnotationList;
        this.lazyDefaultValue = lazy;
        this.containingDeclarationSymbol = firFunctionSymbol;
        this.isVararg = z;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES()));
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getReturnTypeRef().accept(visitor, data);
        Iterator<T> it = getAnnotations().iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        FirExpression defaultValue = getDefaultValue();
        if (defaultValue != null) {
            defaultValue.accept(visitor, data);
        }
    }

    public final FirJavaAnnotationList getAnnotationList() {
        return this.annotationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return this.annotationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirBackingField getBackingField() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeserializedContainerSource getContainerSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public List<FirValueParameter> getContextParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public FirControlFlowGraphReference getControlFlowGraphReference() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter
    public FirExpression getDefaultValue() {
        Lazy<? extends FirExpression> lazy = this.lazyDefaultValue;
        if (lazy != null) {
            return (FirExpression) lazy.getValue();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirExpression getDelegate() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public DeprecationsProvider getDeprecationsProvider() {
        return EmptyDeprecationsProvider.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public ConeSimpleKotlinType getDispatchReceiverType() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirPropertyAccessor getGetter() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirExpression getInitializer() {
        return null;
    }

    public final Lazy<FirExpression> getLazyDefaultValue() {
        return this.lazyDefaultValue;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirReceiverParameter getReceiverParameter() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public FirTypeRef getReturnTypeRef() {
        return this.returnTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public FirPropertyAccessor getSetter() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public FirDeclarationStatus getStatus() {
        return FirResolvedDeclarationStatusImpl.INSTANCE.getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public List<FirTypeParameterRef> getTypeParameters() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter
    public FirValueParameterKind getValueParameterKind() {
        return FirValueParameterKind.Regular;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter
    public boolean isCrossinline() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public boolean isLocal() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter
    public boolean isNoinline() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public boolean isVal() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    /* JADX INFO: renamed from: isVar */
    public boolean getIsVar() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter
    /* JADX INFO: renamed from: isVararg, reason: from getter */
    public boolean getIsVararg() {
        return this.isVararg;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) throws KotlinNothingValueException {
        newAnnotations.getClass();
        UtilsKt.shouldNotBeCalled(this, new AnonymousClass1(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameter.replaceAnnotations.2
            public Object get() {
                return ((FirJavaValueParameter) ((CallableReference) this).receiver).getAnnotations();
            }
        });
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceContextParameters(List<? extends FirValueParameter> newContextParameters) {
        newContextParameters.getClass();
        throw new IllegalStateException("Body cannot be replaced for FirJavaValueParameter");
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter
    public void replaceDefaultValue(FirExpression newDefaultValue) {
        throw new IllegalStateException("Java value parameter cannot has default value");
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceDelegate(FirExpression newDelegate) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceDeprecationsProvider(DeprecationsProvider newDeprecationsProvider) {
        newDeprecationsProvider.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceGetter(FirPropertyAccessor newGetter) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceInitializer(FirExpression newInitializer) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReceiverParameter(FirReceiverParameter newReceiverParameter) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public void replaceReturnTypeRef(FirTypeRef newReturnTypeRef) {
        newReturnTypeRef.getClass();
        setReturnTypeRef(newReturnTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public void replaceSetter(FirPropertyAccessor newSetter) {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public void replaceStatus(FirDeclarationStatus newStatus) {
        newStatus.getClass();
        throw new IllegalStateException("Status cannot be replaced for FirJavaValueParameter");
    }

    public void setDefaultValue(FirExpression firExpression) {
        this.lazyDefaultValue = firExpression != null ? LazyKt.lazyOf(firExpression) : null;
    }

    public final void setLazyDefaultValue(Lazy<? extends FirExpression> lazy) {
        this.lazyDefaultValue = lazy;
    }

    public void setReturnTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.returnTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformBackingField(FirTransformer firTransformer, Object obj) {
        return transformBackingField((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirValueParameter transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformReturnTypeRef((FirTransformer) transformer, (Object) data);
        transformOtherChildren((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformDelegate(FirTransformer firTransformer, Object obj) {
        return transformDelegate((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformGetter(FirTransformer firTransformer, Object obj) {
        return transformGetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformInitializer(FirTransformer firTransformer, Object obj) {
        return transformInitializer((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirValueParameter transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirExpression defaultValue = getDefaultValue();
        setDefaultValue(defaultValue != null ? (FirExpression) FirTransformerUtilKt.transformSingle(defaultValue, transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirValueParameter transformReturnTypeRef(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setReturnTypeRef((FirTypeRef) FirTransformerUtilKt.transformSingle(getReturnTypeRef(), transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformSetter(FirTransformer firTransformer, Object obj) {
        return transformSetter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter
    public FirFunctionSymbol<?> getContainingDeclarationSymbol() {
        return this.containingDeclarationSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin.Java getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirValueParameter transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirValueParameter transformBackingField(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirValueParameter transformContextParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirValueParameter transformDelegate(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirValueParameter transformGetter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirValueParameter transformInitializer(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public <D> FirValueParameter transformReceiverParameter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public <D> FirValueParameter transformSetter(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public <D> FirValueParameter transformStatus(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public <D> FirValueParameter transformTypeParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformContextParameters(FirTransformer firTransformer, Object obj) {
        return transformContextParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformReceiverParameter(FirTransformer firTransformer, Object obj) {
        return transformReceiverParameter((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirValueParameterSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformStatus(FirTransformer firTransformer, Object obj) {
        return transformStatus((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirTypeParameterRefsOwner transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirMemberDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner, org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner
    public /* bridge */ /* synthetic */ FirVariable transformTypeParameters(FirTransformer firTransformer, Object obj) {
        return transformTypeParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirVariable transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameter$replaceAnnotations$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<List<? extends FirAnnotation>, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, FirJavaValueParameter.class, "replaceAnnotations", "replaceAnnotations(Ljava/util/List;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(List<? extends FirAnnotation> list) throws KotlinNothingValueException {
            list.getClass();
            ((FirJavaValueParameter) ((CallableReference) this).receiver).replaceAnnotations(list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((List<? extends FirAnnotation>) obj);
            return Unit.INSTANCE;
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration, org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirVariable transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable, org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    public /* bridge */ /* synthetic */ FirCallableDeclaration transformReturnTypeRef(FirTransformer firTransformer, Object obj) {
        return transformReturnTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirValueParameter, org.jetbrains.kotlin.fir.declarations.FirVariable
    public /* bridge */ /* synthetic */ FirVariable transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
