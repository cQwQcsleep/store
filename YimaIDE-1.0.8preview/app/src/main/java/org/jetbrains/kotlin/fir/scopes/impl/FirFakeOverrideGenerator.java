package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructedClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirBackingFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyAccessorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculatorKt;
import org.jetbrains.kotlin.fir.scopes.DeferredCallableCopyReturnType;
import org.jetbrains.kotlin.fir.scopes.impl.FirFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001uB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J¢\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0012\b\u0002\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0012\b\u0002\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u001a\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 J\u0098\u0001\u0010\u0004\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002JÎ\u0001\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020$2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\b\u0002\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u00142\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0012\b\u0002\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020\u001aJ\u0096\u0001\u0010.\u001a\u00020/2\u0006\u0010!\u001a\u0002002\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00101\u001a\u00020/2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020$2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010*\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,J\u008a\u0001\u00102\u001a\b\u0012\u0004\u0012\u00020%0\u0014*\u0002032\u0006\u00104\u001a\u00020\u00072\u0006\u0010\t\u001a\u0002052\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010*\u001a\u0004\u0018\u00010\u001c2\n\u00106\u001a\u0006\u0012\u0002\b\u0003072\b\b\u0002\u00108\u001a\u00020\u001aH\u0002Jt\u00109\u001a\u00020:*\u0002032\u0006\u0010\t\u001a\u0002052\n\u0010;\u001a\u0006\u0012\u0002\b\u0003072\u0010\u0010\u0016\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010<\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u000f\u001a\u00020$2\b\b\u0002\u00108\u001a\u00020\u001aH\u0002J@\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020A2\u0006\u0010\u000f\u001a\u00020$2\n\u0010B\u001a\u0006\u0012\u0002\b\u0003072\b\u0010C\u001a\u0004\u0018\u00010,2\b\b\u0002\u00108\u001a\u00020\u001aH\u0002J¤\u0001\u0010D\u001a\u00020E2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0012\b\u0002\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u001cJ\u001a\u0010\u001d\u001a\u00020J2\u0006\u0010\u001e\u001a\u00020E2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 Jâ\u0001\u0010K\u001a\u00020G2\u0006\u0010#\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020$2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0012\b\u0002\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010N2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u001cJl\u0010O\u001a\u0004\u0018\u00010P*\u00020P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u000f\u001a\u00020$2\u0006\u0010S\u001a\u00020A2\u0006\u0010T\u001a\u00020E2\b\u0010U\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010F\u001a\u00020G2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\b\b\u0002\u0010(\u001a\u00020)2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0002Jj\u0010V\u001a\u00020P*\u00020P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010\u000f\u001a\u00020$2\u0006\u0010S\u001a\u00020A2\u0006\u0010T\u001a\u00020E2\b\u0010U\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010F\u001a\u00020G2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\b\b\u0002\u0010(\u001a\u00020)2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0094\u0001\u0010W\u001a\u00020X2\u0006\u0010#\u001a\u00020Y2\u0006\u0010Z\u001a\u00020X2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020$2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0012\b\u0002\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u001cJb\u00102\u001a\b\u0012\u0004\u0012\u00020\u00180\u0014*\u00020[2\u0006\u00104\u001a\u00020\u00072\u0006\u0010F\u001a\u00020G2\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010*\u001a\u0004\u0018\u00010\u001cH\u0002Jb\u0010\\\u001a&\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120^0]2\u0006\u0010_\u001a\u00020`2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010a\u001a\u00020bH\u0002JN\u00109\u001a\u00020:*\u00020c2\u0006\u0010d\u001a\u00020e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010*\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010f\u001a\u00020\u001aH\u0002J<\u0010g\u001a\u00020Y2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020X2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010JN\u0010h\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u0014\u0012\u0004\u0012\u00020b0i2\u0006\u00104\u001a\u00020\u00072\u0006\u0010?\u001a\u00020j2\n\u00106\u001a\u0006\u0012\u0002\b\u00030k2\u0006\u0010a\u001a\u00020b2\u0006\u0010\u000f\u001a\u00020$2\b\b\u0002\u0010l\u001a\u00020\u001aJ\u0010\u0010m\u001a\u00020\u001a2\u0006\u0010n\u001a\u00020`H\u0002J\u0010\u0010o\u001a\u00020:2\u0006\u0010p\u001a\u00020`H\u0002R\u0018\u0010q\u001a\u00020r*\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bs\u0010t¨\u0006v"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator;", Argument.Delimiters.none, "<init>", "()V", "createSubstitutionOverrideFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "symbolForSubstitutionOverride", "baseFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "derivedClassLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "newDispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin$SubstitutionOverride;", "newReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "newContextParameterTypes", Argument.Delimiters.none, "newReturnType", "newParameterTypes", "newTypeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "isExpect", Argument.Delimiters.none, "callableCopySubstitutionForTypeUpdater", "Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "createSymbolForSubstitutionOverride", "baseSymbol", "derivedClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "fakeOverrideSymbol", "createCopyForFirFunction", "newSymbol", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "newModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "newVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "deferredReturnTypeCalculation", "newSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "markAsOverride", "createCopyForFirConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "baseConstructor", "configureAnnotationsTypeParametersAndSignature", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFunctionBuilder;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "symbolForOverride", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "copyDefaultValues", "configureAnnotationsAndSignature", Argument.Delimiters.none, "fakeFunctionSymbol", "deferredTypeCalculation", "buildCopyForValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "original", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "containingDeclarationSymbol", "source", "createSubstitutionOverrideProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "baseProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "explicitBackingFieldNewReturnType", "explicitBackingFieldCopySubstitutionForTypeUpdater", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularPropertySymbol;", "createCopyForFirProperty", "newSetterVisibility", "baseBackingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "buildCopyIfNeeded", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "propertyReturnTypeRef", "propertySymbol", "dispatchReceiverType", "buildCopy", "createCopyForFirField", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "baseField", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirPropertyBuilder;", "substituteReceiverAndReturnType", "Lkotlin/Triple;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe;", "baseCallable", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirVariableBuilder;", "baseVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "updateReceiver", "createSubstitutionOverrideField", "createNewTypeParametersAndSubstitutor", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "forceTypeParametersRecreation", "shouldOverrideSetContainingClass", "baseDeclaration", "checkStatusIsResolved", "member", "resolvePhaseForCopy", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhaseForCopy", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "Maybe", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFakeOverrideGenerator {
    public static final FirFakeOverrideGenerator INSTANCE = new FirFakeOverrideGenerator();

    private FirFakeOverrideGenerator() {
    }

    /* JADX WARN: Code duplicated, block: B:40:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:42:0x01ff  */
    private final FirPropertyAccessor buildCopy(FirPropertyAccessor firPropertyAccessor, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirTypeRef firTypeRef, FirPropertySymbol firPropertySymbol, ConeSimpleKotlinType coneSimpleKotlinType, ConeClassLikeLookupTag coneClassLikeLookupTag, FirProperty firProperty, KtSourceElement ktSourceElement, Visibility visibility, Modality modality) {
        ConeClassLikeLookupTag coneClassLikeLookupTag2;
        FirPropertyAccessor firPropertyAccessor2;
        EffectiveVisibility effectiveVisibility;
        boolean z;
        FirPropertyAccessor firPropertyAccessor3;
        EffectiveVisibility effectiveVisibility2;
        if (!(firPropertyAccessor instanceof FirDefaultPropertyGetter)) {
            if (firPropertyAccessor instanceof FirDefaultPropertySetter) {
                Modality modality2 = firPropertyAccessor.getStatus().getModality();
                FirDeclarationStatus status = firPropertyAccessor.getStatus();
                FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
                if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
                    effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
                }
                FirDefaultPropertySetter firDefaultPropertySetter = (FirDefaultPropertySetter) firPropertyAccessor;
                FirDefaultPropertySetter firDefaultPropertySetter2 = new FirDefaultPropertySetter(ktSourceElement, firModuleData, firDeclarationOrigin, firTypeRef, visibility, firPropertySymbol, modality2, effectiveVisibility, false, true, null, ((FirValueParameter) CollectionsKt.first(firDefaultPropertySetter.getValueParameters())).getSource(), null, getResolvePhaseForCopy(firDeclarationOrigin), firDefaultPropertySetter.getAttributes().copy(), 5376, null);
                firDefaultPropertySetter2.replaceAnnotations(MutableOrEmptyList.m194boximpl(firDefaultPropertySetter.m338getAnnotations5e3fPpI()));
                firPropertyAccessor3 = firDefaultPropertySetter2;
            } else {
                FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
                firPropertyAccessorBuilder.setSource(firPropertyAccessor.getSource());
                firPropertyAccessorBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firPropertyAccessor));
                firPropertyAccessorBuilder.setModuleData(firPropertyAccessor.getModuleData());
                firPropertyAccessorBuilder.setOrigin(firPropertyAccessor.getOrigin());
                firPropertyAccessorBuilder.setAttributes(firPropertyAccessor.getAttributes().copy());
                firPropertyAccessorBuilder.setStatus(firPropertyAccessor.getStatus());
                firPropertyAccessorBuilder.setReturnTypeRef(firPropertyAccessor.getReturnTypeRef());
                firPropertyAccessorBuilder.setDeprecationsProvider(firPropertyAccessor.getDeprecationsProvider());
                firPropertyAccessorBuilder.setDispatchReceiverType(firPropertyAccessor.getDispatchReceiverType());
                firPropertyAccessorBuilder.getValueParameters().addAll(firPropertyAccessor.getValueParameters());
                firPropertyAccessorBuilder.setBody(firPropertyAccessor.getBody());
                firPropertyAccessorBuilder.setContractDescription(firPropertyAccessor.getContractDescription());
                firPropertyAccessorBuilder.setPropertySymbol(firPropertyAccessor.getPropertySymbol());
                firPropertyAccessorBuilder.setGetter(firPropertyAccessor.getIsGetter());
                firPropertyAccessorBuilder.getAnnotations().addAll(firPropertyAccessor.getAnnotations());
                firPropertyAccessorBuilder.setSource(ktSourceElement);
                firPropertyAccessorBuilder.setSymbol(new FirPropertyAccessorSymbol());
                firPropertyAccessorBuilder.setModuleData(firModuleData);
                firPropertyAccessorBuilder.setOrigin(firDeclarationOrigin);
                firPropertyAccessorBuilder.setPropertySymbol(firPropertySymbol);
                firPropertyAccessorBuilder.setDispatchReceiverType(coneSimpleKotlinType);
                coneClassLikeLookupTag2 = null;
                firPropertyAccessorBuilder.setBody(null);
                FirFakeOverrideGenerator firFakeOverrideGenerator = INSTANCE;
                firPropertyAccessorBuilder.setResolvePhase(firFakeOverrideGenerator.getResolvePhaseForCopy(firDeclarationOrigin));
                FirDeclarationStatus status2 = firPropertyAccessorBuilder.getStatus();
                firPropertyAccessorBuilder.setStatus(UtilsKt.copy(status2, (8388575 & 1) != 0 ? status2.getVisibility() : visibility, (8388575 & 2) != 0 ? status2.getModality() : null, (8388575 & 4) != 0 ? status2.isExpect() : false, (8388575 & 8) != 0 ? status2.isActual() : false, (8388575 & 16) != 0 ? status2.isOverride() : false, (8388575 & 32) != 0 ? status2.isOperator() : false, (8388575 & 64) != 0 ? status2.isInfix() : false, (8388575 & 128) != 0 ? status2.isInline() : false, (8388575 & 256) != 0 ? status2.isValue() : false, (8388575 & 512) != 0 ? status2.isTailRec() : false, (8388575 & 1024) != 0 ? status2.isExternal() : false, (8388575 & 2048) != 0 ? status2.isConst() : false, (8388575 & 4096) != 0 ? status2.isLateInit() : false, (8388575 & 8192) != 0 ? status2.isInner() : false, (8388575 & 16384) != 0 ? status2.isCompanion() : false, (8388575 & 32768) != 0 ? status2.isData() : false, (8388575 & 65536) != 0 ? status2.isSuspend() : false, (8388575 & 131072) != 0 ? status2.isStatic() : false, (8388575 & 262144) != 0 ? status2.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status2.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status2.isFun() : false, (8388575 & 2097152) != 0 ? status2.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status2.getReturnValueStatus() : null));
                firPropertyAccessorBuilder.setAttributes(firPropertyAccessor.getAttributes().copy());
                FirPropertyAccessor firPropertyAccessorBuild = firPropertyAccessorBuilder.mo288build();
                if (firPropertyAccessorBuild.isSetter()) {
                    FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.first(firPropertyAccessorBuild.getValueParameters());
                    firPropertyAccessorBuild.replaceValueParameters(CollectionsKt.listOf(buildCopyForValueParameter$default(firFakeOverrideGenerator, firValueParameter, firTypeRef, firDeclarationOrigin, firPropertyAccessorBuild.getSymbol(), firValueParameter.getSource(), false, 32, null)));
                } else {
                    firPropertyAccessorBuild.replaceReturnTypeRef(firTypeRef);
                }
                firPropertyAccessor2 = firPropertyAccessorBuild;
            }
            if (!(firPropertyAccessor2 instanceof FirDefaultPropertyAccessor) || INSTANCE.shouldOverrideSetContainingClass(firProperty)) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                coneClassLikeLookupTag2 = coneClassLikeLookupTag;
            }
            ClassMembersKt.setContainingClassForStaticMemberAttr(firPropertyAccessor2, coneClassLikeLookupTag2);
            return firPropertyAccessor2;
        }
        FirDeclarationStatus status3 = firPropertyAccessor.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus2 = status3 instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status3 : null;
        if (firResolvedDeclarationStatus2 == null || (effectiveVisibility2 = firResolvedDeclarationStatus2.getEffectiveVisibility()) == null) {
            effectiveVisibility2 = EffectiveVisibility.Local.INSTANCE;
        }
        FirDefaultPropertyGetter firDefaultPropertyGetter = (FirDefaultPropertyGetter) firPropertyAccessor;
        FirDefaultPropertyGetter firDefaultPropertyGetter2 = new FirDefaultPropertyGetter(ktSourceElement, firModuleData, firDeclarationOrigin, firTypeRef, visibility, firPropertySymbol, modality, effectiveVisibility2, false, true, null, getResolvePhaseForCopy(firDeclarationOrigin), firDefaultPropertyGetter.getAttributes().copy(), 1280, null);
        firDefaultPropertyGetter2.replaceAnnotations(MutableOrEmptyList.m194boximpl(firDefaultPropertyGetter.m338getAnnotations5e3fPpI()));
        firPropertyAccessor3 = firDefaultPropertyGetter2;
        coneClassLikeLookupTag2 = null;
        firPropertyAccessor2 = firPropertyAccessor3;
        if (firPropertyAccessor2 instanceof FirDefaultPropertyAccessor) {
            z = true;
        } else {
            z = true;
        }
        if (z) {
            coneClassLikeLookupTag2 = coneClassLikeLookupTag;
        }
        ClassMembersKt.setContainingClassForStaticMemberAttr(firPropertyAccessor2, coneClassLikeLookupTag2);
        return firPropertyAccessor2;
    }

    private final FirValueParameter buildCopyForValueParameter(FirValueParameter original, FirTypeRef returnTypeRef, FirDeclarationOrigin origin, FirFunctionSymbol<?> containingDeclarationSymbol, KtSourceElement source, boolean copyDefaultValues) {
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setSource(original.getSource());
        firValueParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(original));
        firValueParameterBuilder.setModuleData(original.getModuleData());
        firValueParameterBuilder.setOrigin(original.getOrigin());
        firValueParameterBuilder.setAttributes(original.getAttributes().copy());
        firValueParameterBuilder.setReturnTypeRef(original.getReturnTypeRef());
        firValueParameterBuilder.setName(original.getName());
        firValueParameterBuilder.getAnnotations().addAll(original.getAnnotations());
        firValueParameterBuilder.setDefaultValue(original.getDefaultValue());
        firValueParameterBuilder.setContainingDeclarationSymbol(original.getContainingDeclarationSymbol());
        firValueParameterBuilder.setCrossinline(original.getIsCrossinline());
        firValueParameterBuilder.setNoinline(original.getIsNoinline());
        firValueParameterBuilder.setVararg(original.getIsVararg());
        firValueParameterBuilder.setValueParameterKind(original.getValueParameterKind());
        firValueParameterBuilder.setOrigin(origin);
        firValueParameterBuilder.setSource(source);
        firValueParameterBuilder.setReturnTypeRef(returnTypeRef);
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
        FirExpression defaultValue = firValueParameterBuilder.getDefaultValue();
        FirExpression firExpressionBuild = null;
        if (defaultValue != null) {
            if (!copyDefaultValues) {
                defaultValue = null;
            }
            if (defaultValue != null) {
                FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
                firExpressionStubBuilder.setConeTypeOrNull(FirTypeUtilsKt.getConeTypeOrNull(returnTypeRef));
                firExpressionBuild = firExpressionStubBuilder.mo288build();
            }
        }
        firValueParameterBuilder.setDefaultValue(firExpressionBuild);
        firValueParameterBuilder.setResolvePhase(INSTANCE.getResolvePhaseForCopy(origin));
        return firValueParameterBuilder.mo288build();
    }

    public static /* synthetic */ FirValueParameter buildCopyForValueParameter$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirValueParameter firValueParameter, FirTypeRef firTypeRef, FirDeclarationOrigin firDeclarationOrigin, FirFunctionSymbol firFunctionSymbol, KtSourceElement ktSourceElement, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = true;
        }
        return firFakeOverrideGenerator.buildCopyForValueParameter(firValueParameter, firTypeRef, firDeclarationOrigin, firFunctionSymbol, ktSourceElement, z);
    }

    private final FirPropertyAccessor buildCopyIfNeeded(FirPropertyAccessor firPropertyAccessor, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirTypeRef firTypeRef, FirPropertySymbol firPropertySymbol, ConeSimpleKotlinType coneSimpleKotlinType, ConeClassLikeLookupTag coneClassLikeLookupTag, FirProperty firProperty, KtSourceElement ktSourceElement, Visibility visibility, Modality modality) {
        if (!firPropertyAccessor.getAnnotations().isEmpty() || !Intrinsics.areEqual(visibility, firProperty.getStatus().getVisibility()) || Intrinsics.areEqual(firDeclarationOrigin, FirDeclarationOrigin.Delegated.INSTANCE) || (firDeclarationOrigin instanceof FirDeclarationOrigin.SubstitutionOverride) || (firDeclarationOrigin instanceof FirDeclarationOrigin.IntersectionOverride)) {
            return buildCopy(firPropertyAccessor, firModuleData, firDeclarationOrigin, firTypeRef, firPropertySymbol, coneSimpleKotlinType, coneClassLikeLookupTag, firProperty, ktSourceElement, visibility, modality);
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final void checkStatusIsResolved(FirCallableDeclaration member) throws KotlinIllegalStateExceptionWithAttachments {
        if (member.getStatus() instanceof FirResolvedDeclarationStatus) {
            return;
        }
        KotlinIllegalStateExceptionWithAttachments kotlinIllegalStateExceptionWithAttachments = new KotlinIllegalStateExceptionWithAttachments("Status should be resolved for a declaration to create its fake override, otherwise the status of the fake override will never be resolved.The status was unresolved for ".concat(member.getClass().getSimpleName()));
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", member);
        exceptionAttachmentBuilder.withEntry("declarationStatus", member.getStatus(), new Function1() { // from class: w75
            public final Object invoke(Object obj) {
                return FirFakeOverrideGenerator.checkStatusIsResolved$lambda$1$0((FirDeclarationStatus) obj);
            }
        });
        kotlinIllegalStateExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalStateExceptionWithAttachments;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String checkStatusIsResolved$lambda$1$0(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        return firDeclarationStatus.toString();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.util.ArrayList] */
    private final void configureAnnotationsAndSignature(FirFunctionBuilder firFunctionBuilder, FirFunction firFunction, FirFunctionSymbol<?> firFunctionSymbol, List<? extends ConeKotlinType> list, ConeKotlinType coneKotlinType, List<? extends ConeKotlinType> list2, ConeKotlinType coneKotlinType2, DeferredCallableCopyReturnType deferredCallableCopyReturnType, FirDeclarationOrigin firDeclarationOrigin, boolean z) throws KotlinIllegalStateExceptionWithAttachments {
        ?? arrayList;
        ?? arrayList2;
        FirReceiverParameter firReceiverParameterBuild;
        checkStatusIsResolved(firFunction);
        CollectionsKt.addAll(firFunctionBuilder.getAnnotations(), firFunction.getAnnotations());
        DeferredCallableCopyReturnType deferredReturnTypeOfSubstitution = deferredCallableCopyReturnType == null ? firFunction.getReturnTypeRef() instanceof FirImplicitTypeRef ? new DeferredReturnTypeOfSubstitution(ConeSubstitutor.Empty.INSTANCE, firFunction.getSymbol()) : null : deferredCallableCopyReturnType;
        if (deferredReturnTypeOfSubstitution != null) {
            firFunctionBuilder.setReturnTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
            CallableCopyTypeCalculatorKt.setDeferredCallableCopyReturnType(firFunctionBuilder.getAttributes(), deferredReturnTypeOfSubstitution);
        } else {
            firFunctionBuilder.setReturnTypeRef(TypeUtilsKt.withReplacedReturnType(firFunction.getReturnTypeRef(), coneKotlinType2));
        }
        if (firFunctionBuilder instanceof FirNamedFunctionBuilder) {
            FirNamedFunctionBuilder firNamedFunctionBuilder = (FirNamedFunctionBuilder) firFunctionBuilder;
            FirReceiverParameter receiverParameter = firFunction.getReceiverParameter();
            if (receiverParameter != null) {
                FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
                firReceiverParameterBuilder.setSource(receiverParameter.getSource());
                firReceiverParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(receiverParameter));
                firReceiverParameterBuilder.setModuleData(receiverParameter.getModuleData());
                firReceiverParameterBuilder.setOrigin(receiverParameter.getOrigin());
                firReceiverParameterBuilder.setAttributes(receiverParameter.getAttributes().copy());
                firReceiverParameterBuilder.setTypeRef(receiverParameter.getTypeRef());
                firReceiverParameterBuilder.setContainingDeclarationSymbol(receiverParameter.getContainingDeclarationSymbol());
                firReceiverParameterBuilder.getAnnotations().addAll(receiverParameter.getAnnotations());
                firReceiverParameterBuilder.setTypeRef(TypeUtilsKt.withReplacedConeType$default(receiverParameter.getTypeRef(), coneKotlinType, null, 2, null));
                firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
                firReceiverParameterBuild = firReceiverParameterBuilder.mo288build();
            } else {
                firReceiverParameterBuild = null;
            }
            firNamedFunctionBuilder.setReceiverParameter(firReceiverParameterBuild);
        }
        List<FirValueParameter> valueParameters = firFunctionBuilder.getValueParameters();
        List<FirValueParameter> valueParameters2 = firFunction.getValueParameters();
        if (list == null) {
            int size = firFunction.getValueParameters().size();
            arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(null);
            }
        } else {
            arrayList = list;
        }
        Iterable iterable = (Iterable) arrayList;
        Iterator it = valueParameters2.iterator();
        Iterator it2 = iterable.iterator();
        ArrayList arrayList3 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(valueParameters2, 10), CollectionsKt.collectionSizeOrDefault(iterable, 10)));
        while (it.hasNext() && it2.hasNext()) {
            Object next = it.next();
            ConeKotlinType coneKotlinType3 = (ConeKotlinType) it2.next();
            FirValueParameter firValueParameter = (FirValueParameter) next;
            FirFakeOverrideGenerator firFakeOverrideGenerator = INSTANCE;
            FirResolvedTypeRef firResolvedTypeRefWithReplacedConeType$default = TypeUtilsKt.withReplacedConeType$default(firValueParameter.getReturnTypeRef(), coneKotlinType3, null, 2, null);
            KtSourceElement source = firFunctionBuilder.getSource();
            if (source == null) {
                source = firValueParameter.getSource();
            }
            arrayList3.add(firFakeOverrideGenerator.buildCopyForValueParameter(firValueParameter, firResolvedTypeRefWithReplacedConeType$default, firDeclarationOrigin, firFunctionSymbol, source, z));
        }
        CollectionsKt.addAll(valueParameters, arrayList3);
        List<FirValueParameter> contextParameters = firFunctionBuilder.getContextParameters();
        List<FirValueParameter> contextParameters2 = firFunction.getContextParameters();
        if (list2 == null) {
            int size2 = firFunction.getContextParameters().size();
            arrayList2 = new ArrayList(size2);
            for (int i2 = 0; i2 < size2; i2++) {
                arrayList2.add(null);
            }
        } else {
            arrayList2 = list2;
        }
        Iterable iterable2 = (Iterable) arrayList2;
        Iterator it3 = contextParameters2.iterator();
        Iterator it4 = iterable2.iterator();
        ArrayList arrayList4 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(contextParameters2, 10), CollectionsKt.collectionSizeOrDefault(iterable2, 10)));
        while (it3.hasNext() && it4.hasNext()) {
            Object next2 = it3.next();
            ConeKotlinType coneKotlinType4 = (ConeKotlinType) it4.next();
            FirValueParameter firValueParameter2 = (FirValueParameter) next2;
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            firValueParameterBuilder.setSource(firValueParameter2.getSource());
            firValueParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firValueParameter2));
            firValueParameterBuilder.setModuleData(firValueParameter2.getModuleData());
            firValueParameterBuilder.setOrigin(firValueParameter2.getOrigin());
            firValueParameterBuilder.setAttributes(firValueParameter2.getAttributes().copy());
            firValueParameterBuilder.setReturnTypeRef(firValueParameter2.getReturnTypeRef());
            firValueParameterBuilder.setName(firValueParameter2.getName());
            firValueParameterBuilder.getAnnotations().addAll(firValueParameter2.getAnnotations());
            firValueParameterBuilder.setDefaultValue(firValueParameter2.getDefaultValue());
            firValueParameterBuilder.setContainingDeclarationSymbol(firValueParameter2.getContainingDeclarationSymbol());
            firValueParameterBuilder.setCrossinline(firValueParameter2.getIsCrossinline());
            firValueParameterBuilder.setNoinline(firValueParameter2.getIsNoinline());
            firValueParameterBuilder.setVararg(firValueParameter2.getIsVararg());
            firValueParameterBuilder.setValueParameterKind(firValueParameter2.getValueParameterKind());
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder.setReturnTypeRef(TypeUtilsKt.withReplacedConeType$default(firValueParameter2.getReturnTypeRef(), coneKotlinType4, null, 2, null));
            arrayList4.add(firValueParameterBuilder.mo288build());
        }
        CollectionsKt.addAll(contextParameters, arrayList4);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public static /* synthetic */ void configureAnnotationsAndSignature$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirVariableBuilder firVariableBuilder, FirVariable firVariable, ConeKotlinType coneKotlinType, List list, ConeKotlinType coneKotlinType2, DeferredCallableCopyReturnType deferredCallableCopyReturnType, boolean z, int i, Object obj) throws KotlinIllegalStateExceptionWithAttachments {
        firFakeOverrideGenerator.configureAnnotationsAndSignature(firVariableBuilder, firVariable, coneKotlinType, list, coneKotlinType2, deferredCallableCopyReturnType, (i & 32) != 0 ? true : z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final List<FirTypeParameterRef> configureAnnotationsTypeParametersAndSignature(FirFunctionBuilder firFunctionBuilder, FirSession firSession, FirFunction firFunction, List<? extends ConeKotlinType> list, List<? extends FirTypeParameterRef> list2, ConeKotlinType coneKotlinType, List<? extends ConeKotlinType> list3, ConeKotlinType coneKotlinType2, DeferredCallableCopyReturnType deferredCallableCopyReturnType, FirFunctionSymbol<?> firFunctionSymbol, boolean z) throws KotlinIllegalStateExceptionWithAttachments {
        if (firFunction.getTypeParameters().isEmpty()) {
            configureAnnotationsAndSignature(firFunctionBuilder, firFunction, firFunctionSymbol, list, coneKotlinType, list3, coneKotlinType2, deferredCallableCopyReturnType, firFunctionBuilder.getOrigin(), z);
            return CollectionsKt.emptyList();
        }
        if (list2 != 0) {
            configureAnnotationsAndSignature(firFunctionBuilder, firFunction, firFunctionSymbol, list, coneKotlinType, list3, coneKotlinType2, deferredCallableCopyReturnType, firFunctionBuilder.getOrigin(), z);
            return list2;
        }
        Pair pairCreateNewTypeParametersAndSubstitutor$default = createNewTypeParametersAndSubstitutor$default(this, firSession, firFunction, firFunctionSymbol, ConeSubstitutor.Empty.INSTANCE, firFunctionBuilder.getOrigin(), false, 32, null);
        List<FirTypeParameterRef> list4 = (List) pairCreateNewTypeParametersAndSubstitutor$default.component1();
        ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pairCreateNewTypeParametersAndSubstitutor$default.component2();
        List<FirValueParameter> valueParameters = firFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(coneSubstitutor.substituteOrNull(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef())));
        }
        FirFunctionSymbol<FirFunction> symbol = firFunction.getSymbol();
        Triple<ConeKotlinType, List<ConeKotlinType>, Maybe<ConeKotlinType>> tripleSubstituteReceiverAndReturnType = substituteReceiverAndReturnType(firFunction, coneKotlinType, list3, coneKotlinType2, coneSubstitutor);
        ConeKotlinType coneKotlinType3 = (ConeKotlinType) tripleSubstituteReceiverAndReturnType.component1();
        List<? extends ConeKotlinType> list5 = (List) tripleSubstituteReceiverAndReturnType.component2();
        Maybe maybe = (Maybe) tripleSubstituteReceiverAndReturnType.component3();
        Pair pair = maybe instanceof Maybe.Value ? TuplesKt.to(((Maybe.Value) maybe).getValue(), null) : TuplesKt.to(null, new DeferredReturnTypeOfSubstitution(coneSubstitutor, symbol));
        configureAnnotationsAndSignature(firFunctionBuilder, firFunction, firFunctionSymbol, arrayList, coneKotlinType3, list5, (ConeKotlinType) pair.component1(), (DeferredReturnTypeOfSubstitution) pair.component2(), firFunctionBuilder.getOrigin(), z);
        return list4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirNamedFunction createCopyForFirFunction$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunction firNamedFunction, ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession, FirDeclarationOrigin firDeclarationOrigin, boolean z, ConeSimpleKotlinType coneSimpleKotlinType, List list, List list2, ConeKotlinType coneKotlinType, List list3, ConeKotlinType coneKotlinType2, Modality modality, Visibility visibility, DeferredCallableCopyReturnType deferredCallableCopyReturnType, KtSourceElement ktSourceElement, boolean z2, int i, Object obj) {
        KtSourceElement ktSourceElement2;
        KtSourceElement source;
        FirClassLikeSymbol<?> symbol;
        boolean zIsExpect = (i & 32) != 0 ? firNamedFunction.getStatus().isExpect() : z;
        List list4 = (i & 128) != 0 ? null : list;
        List list5 = (i & 256) != 0 ? null : list2;
        ConeKotlinType coneKotlinType3 = (i & 512) != 0 ? null : coneKotlinType;
        List list6 = (i & 1024) != 0 ? null : list3;
        ConeKotlinType coneKotlinType4 = (i & 2048) != 0 ? null : coneKotlinType2;
        Modality modality2 = (i & 4096) != 0 ? null : modality;
        Visibility visibility2 = (i & 8192) != 0 ? null : visibility;
        DeferredCallableCopyReturnType deferredCallableCopyReturnType2 = (i & 16384) != 0 ? null : deferredCallableCopyReturnType;
        if ((i & 32768) != 0) {
            if (coneClassLikeLookupTag == null || (symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTag, firSession)) == null || (source = symbol.getSource()) == null) {
                source = firNamedFunction.getSource();
            }
            ktSourceElement2 = source;
        } else {
            ktSourceElement2 = ktSourceElement;
        }
        return firFakeOverrideGenerator.createCopyForFirFunction(firNamedFunctionSymbol, firNamedFunction, coneClassLikeLookupTag, firSession, firDeclarationOrigin, zIsExpect, coneSimpleKotlinType, list4, list5, coneKotlinType3, list6, coneKotlinType4, modality2, visibility2, deferredCallableCopyReturnType2, ktSourceElement2, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirProperty createCopyForFirProperty$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirPropertySymbol firPropertySymbol, FirProperty firProperty, ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession, FirDeclarationOrigin firDeclarationOrigin, boolean z, ConeSimpleKotlinType coneSimpleKotlinType, List list, ConeKotlinType coneKotlinType, List list2, ConeKotlinType coneKotlinType2, Modality modality, Visibility visibility, Visibility visibility2, DeferredCallableCopyReturnType deferredCallableCopyReturnType, KtSourceElement ktSourceElement, FirBackingField firBackingField, ConeKotlinType coneKotlinType3, DeferredCallableCopyReturnType deferredCallableCopyReturnType2, int i, Object obj) {
        KtSourceElement source;
        FirClassLikeSymbol<?> symbol;
        boolean zIsExpect = (i & 32) != 0 ? firProperty.getStatus().isExpect() : z;
        List list3 = (i & 128) != 0 ? null : list;
        ConeKotlinType coneKotlinType4 = (i & 256) != 0 ? null : coneKotlinType;
        List list4 = (i & 512) != 0 ? null : list2;
        ConeKotlinType coneKotlinType5 = (i & 1024) != 0 ? null : coneKotlinType2;
        Modality modality2 = (i & 2048) != 0 ? null : modality;
        Visibility visibility3 = (i & 4096) != 0 ? null : visibility;
        Visibility visibility4 = (i & 8192) != 0 ? null : visibility2;
        DeferredCallableCopyReturnType deferredCallableCopyReturnType3 = (i & 16384) != 0 ? null : deferredCallableCopyReturnType;
        if ((32768 & i) != 0) {
            source = (coneClassLikeLookupTag == null || (symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTag, firSession)) == null) ? null : symbol.getSource();
        } else {
            source = ktSourceElement;
        }
        return firFakeOverrideGenerator.createCopyForFirProperty(firPropertySymbol, firProperty, coneClassLikeLookupTag, firSession, firDeclarationOrigin, zIsExpect, coneSimpleKotlinType, list3, coneKotlinType4, list4, coneKotlinType5, modality2, visibility3, visibility4, deferredCallableCopyReturnType3, source, (65536 & i) != 0 ? null : firBackingField, (131072 & i) != 0 ? null : coneKotlinType3, (i & 262144) != 0 ? null : deferredCallableCopyReturnType2);
    }

    public static /* synthetic */ Pair createNewTypeParametersAndSubstitutor$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirSession firSession, FirTypeParameterRefsOwner firTypeParameterRefsOwner, FirBasedSymbol firBasedSymbol, ConeSubstitutor coneSubstitutor, FirDeclarationOrigin firDeclarationOrigin, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = true;
        }
        return firFakeOverrideGenerator.createNewTypeParametersAndSubstitutor(firSession, firTypeParameterRefsOwner, firBasedSymbol, coneSubstitutor, firDeclarationOrigin, z);
    }

    private static final ConeSubstitutor createNewTypeParametersAndSubstitutor$substitutorFrom(List<? extends Pair<? extends FirTypeParameterRef, FirTypeParameterBuilder>> list, FirSession firSession) {
        List<? extends Pair<? extends FirTypeParameterRef, FirTypeParameterBuilder>> list2 = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            Pair pair2 = new Pair(((FirTypeParameterRef) pair.component1()).getSymbol(), new ConeTypeParameterTypeImpl(((FirTypeParameterBuilder) pair.component2()).getSymbol().getLookupTag(), false, null, 4, null));
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, firSession, false, 4, null);
    }

    public static /* synthetic */ FirFieldSymbol createSubstitutionOverrideField$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirSession firSession, FirField firField, ConeClassLikeLookupTag coneClassLikeLookupTag, ConeKotlinType coneKotlinType, ConeSimpleKotlinType coneSimpleKotlinType, FirDeclarationOrigin.SubstitutionOverride substitutionOverride, int i, Object obj) {
        if ((i & 8) != 0) {
            coneKotlinType = null;
        }
        return firFakeOverrideGenerator.createSubstitutionOverrideField(firSession, firField, coneClassLikeLookupTag, coneKotlinType, coneSimpleKotlinType, substitutionOverride);
    }

    private final FirNamedFunction createSubstitutionOverrideFunction(FirNamedFunctionSymbol fakeOverrideSymbol, FirSession session, FirNamedFunction baseFunction, ConeClassLikeLookupTag derivedClassLookupTag, ConeSimpleKotlinType newDispatchReceiverType, ConeKotlinType newReceiverType, List<? extends ConeKotlinType> newContextParameterTypes, ConeKotlinType newReturnType, List<? extends ConeKotlinType> newParameterTypes, List<? extends FirTypeParameter> newTypeParameters, boolean isExpect, DeferredCallableCopyReturnType callableCopySubstitutionForTypeUpdater, FirDeclarationOrigin.SubstitutionOverride origin) {
        FirNamedFunction firNamedFunctionCreateCopyForFirFunction$default = createCopyForFirFunction$default(this, fakeOverrideSymbol, baseFunction, derivedClassLookupTag, session, origin, isExpect, newDispatchReceiverType, newParameterTypes, newTypeParameters, newReceiverType, newContextParameterTypes, newReturnType, null, null, callableCopySubstitutionForTypeUpdater, null, true, 45056, null);
        ClassMembersKt.setOriginalForSubstitutionOverrideAttr(firNamedFunctionCreateCopyForFirFunction$default, baseFunction);
        return firNamedFunctionCreateCopyForFirFunction$default;
    }

    public static /* synthetic */ FirNamedFunctionSymbol createSymbolForSubstitutionOverride$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirNamedFunctionSymbol firNamedFunctionSymbol, ClassId classId, int i, Object obj) {
        if ((i & 2) != 0) {
            classId = null;
        }
        return firFakeOverrideGenerator.createSymbolForSubstitutionOverride(firNamedFunctionSymbol, classId);
    }

    private final FirResolvePhase getResolvePhaseForCopy(FirDeclarationOrigin firDeclarationOrigin) {
        return FirDeclarationOriginKt.isLazyResolvable(firDeclarationOrigin) ? FirResolvePhase.STATUS : FirResolvePhase.BODY_RESOLVE;
    }

    private final boolean shouldOverrideSetContainingClass(FirCallableDeclaration baseDeclaration) {
        return (baseDeclaration instanceof FirConstructor) || baseDeclaration.getStatus().isStatic() || ClassMembersKt.getContainingClassForStaticMemberAttr(baseDeclaration) != null;
    }

    private final Triple<ConeKotlinType, List<ConeKotlinType>, Maybe<ConeKotlinType>> substituteReceiverAndReturnType(FirCallableDeclaration baseCallable, ConeKotlinType newReceiverType, List<? extends ConeKotlinType> newContextParameterTypes, ConeKotlinType newReturnType, ConeSubstitutor substitutor) {
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        FirTypeRef typeRef;
        ArrayList arrayList;
        ConeKotlinType coneKotlinTypeSubstituteOrNull2;
        if (newReceiverType == null || (coneKotlinTypeSubstituteOrNull = substitutor.substituteOrNull(newReceiverType)) == null) {
            FirReceiverParameter receiverParameter = baseCallable.getReceiverParameter();
            coneKotlinTypeSubstituteOrNull = (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null) ? null : substitutor.substituteOrNull(FirTypeUtilsKt.getConeType(typeRef));
        }
        if (newContextParameterTypes != null) {
            List<? extends ConeKotlinType> list = newContextParameterTypes;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (ConeKotlinType coneKotlinType : list) {
                arrayList.add(coneKotlinType != null ? substitutor.substituteOrNull(coneKotlinType) : null);
            }
        } else {
            List<FirValueParameter> contextParameters = baseCallable.getContextParameters();
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
            Iterator<T> it = contextParameters.iterator();
            while (it.hasNext()) {
                arrayList.add(substitutor.substituteOrNull(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef())));
            }
        }
        if (newReturnType == null || (coneKotlinTypeSubstituteOrNull2 = substitutor.substituteOrNull(newReturnType)) == null) {
            baseCallable.getReturnTypeRef();
            FirResolvedTypeRef returnTypeRef = baseCallable.getReturnTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            ConeKotlinType coneKotlinType2 = coneType != null ? coneType : null;
            if (coneKotlinType2 == null) {
                return new Triple<>(coneKotlinTypeSubstituteOrNull, arrayList, Maybe.Nothing.INSTANCE);
            }
            coneKotlinTypeSubstituteOrNull2 = substitutor.substituteOrNull(coneKotlinType2);
        }
        return new Triple<>(coneKotlinTypeSubstituteOrNull, arrayList, new Maybe.Value(coneKotlinTypeSubstituteOrNull2));
    }

    public final FirConstructor createCopyForFirConstructor(FirConstructorSymbol fakeOverrideSymbol, FirSession session, FirConstructor baseConstructor, ConeClassLikeLookupTag derivedClassLookupTag, FirDeclarationOrigin origin, ConeSimpleKotlinType newDispatchReceiverType, ConeKotlinType newReturnType, List<? extends ConeKotlinType> newParameterTypes, List<? extends ConeKotlinType> newContextParameterTypes, List<? extends FirTypeParameterRef> newTypeParameters, boolean isExpect, DeferredCallableCopyReturnType deferredReturnTypeCalculation, KtSourceElement newSource) {
        KtSourceElement source;
        FirReceiverParameter firReceiverParameterBuild;
        FirClassLikeSymbol<?> symbol;
        ConeClassLikeLookupTag coneClassLikeLookupTag = derivedClassLookupTag;
        fakeOverrideSymbol.getClass();
        session.getClass();
        baseConstructor.getClass();
        origin.getClass();
        FirConstructorBuilder firConstructorBuilder = new FirConstructorBuilder();
        CollectionsKt.addAll(firConstructorBuilder.getAnnotations(), baseConstructor.getAnnotations());
        if (newSource == null) {
            source = (coneClassLikeLookupTag == null || (symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTag, session)) == null) ? null : symbol.getSource();
            if (source == null) {
                source = baseConstructor.getSource();
            }
        } else {
            source = newSource;
        }
        firConstructorBuilder.setSource(source);
        FirModuleData nullableModuleData = FirModuleDataKt.getNullableModuleData(session);
        if (nullableModuleData == null) {
            nullableModuleData = baseConstructor.getModuleData();
        }
        firConstructorBuilder.setModuleData(nullableModuleData);
        firConstructorBuilder.setOrigin(origin);
        FirReceiverParameter receiverParameter = baseConstructor.getReceiverParameter();
        if (receiverParameter != null) {
            FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
            firReceiverParameterBuilder.setSource(receiverParameter.getSource());
            firReceiverParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(receiverParameter));
            firReceiverParameterBuilder.setModuleData(receiverParameter.getModuleData());
            firReceiverParameterBuilder.setOrigin(receiverParameter.getOrigin());
            firReceiverParameterBuilder.setAttributes(receiverParameter.getAttributes().copy());
            firReceiverParameterBuilder.setTypeRef(receiverParameter.getTypeRef());
            firReceiverParameterBuilder.setContainingDeclarationSymbol(receiverParameter.getContainingDeclarationSymbol());
            firReceiverParameterBuilder.getAnnotations().addAll(receiverParameter.getAnnotations());
            firReceiverParameterBuilder.setTypeRef(TypeUtilsKt.withReplacedConeType$default(receiverParameter.getTypeRef(), null, null, 2, null));
            firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
            firReceiverParameterBuild = firReceiverParameterBuilder.mo288build();
        } else {
            firReceiverParameterBuild = null;
        }
        firConstructorBuilder.setReceiverParameter(firReceiverParameterBuild);
        FirDeclarationStatus status = baseConstructor.getStatus();
        firConstructorBuilder.setStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : null, (8388575 & 4) != 0 ? status.isExpect() : isExpect, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
        firConstructorBuilder.setLocal(baseConstructor.getIsLocal());
        firConstructorBuilder.setSymbol(fakeOverrideSymbol);
        List<FirTypeParameterRef> typeParameters = firConstructorBuilder.getTypeParameters();
        FirFakeOverrideGenerator firFakeOverrideGenerator = INSTANCE;
        CollectionsKt.addAll(typeParameters, firFakeOverrideGenerator.configureAnnotationsTypeParametersAndSignature(firConstructorBuilder, session, baseConstructor, newParameterTypes, newTypeParameters, null, newContextParameterTypes, newReturnType, deferredReturnTypeCalculation, fakeOverrideSymbol, true));
        firConstructorBuilder.setDispatchReceiverType(newDispatchReceiverType);
        firConstructorBuilder.setResolvePhase(firFakeOverrideGenerator.getResolvePhaseForCopy(origin));
        firConstructorBuilder.setAttributes(baseConstructor.getAttributes().copy());
        firConstructorBuilder.setDeprecationsProvider(baseConstructor.getDeprecationsProvider());
        FirConstructor firConstructorBuild = firConstructorBuilder.mo288build();
        ClassMembersKt.setOriginalForSubstitutionOverrideAttr(firConstructorBuild, baseConstructor);
        if (!firFakeOverrideGenerator.shouldOverrideSetContainingClass(baseConstructor)) {
            coneClassLikeLookupTag = null;
        }
        ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorBuild, coneClassLikeLookupTag);
        return firConstructorBuild;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final FirField createCopyForFirField(FirFieldSymbol newSymbol, FirField baseField, ConeClassLikeLookupTag derivedClassLookupTag, FirSession session, FirDeclarationOrigin origin, boolean isExpect, ConeSimpleKotlinType newDispatchReceiverType, ConeKotlinType newReceiverType, List<? extends ConeKotlinType> newContextParameterTypes, ConeKotlinType newReturnType, Modality newModality, Visibility newVisibility, DeferredCallableCopyReturnType deferredReturnTypeCalculation) throws KotlinIllegalStateExceptionWithAttachments {
        newSymbol.getClass();
        baseField.getClass();
        session.getClass();
        origin.getClass();
        FirFieldBuilder firFieldBuilder = new FirFieldBuilder();
        firFieldBuilder.setSource(baseField.getSource());
        FirModuleData nullableModuleData = FirModuleDataKt.getNullableModuleData(session);
        if (nullableModuleData == null) {
            nullableModuleData = baseField.getModuleData();
        }
        firFieldBuilder.setModuleData(nullableModuleData);
        firFieldBuilder.setOrigin(origin);
        firFieldBuilder.setName(baseField.getName());
        firFieldBuilder.setVar(baseField.getIsVar());
        firFieldBuilder.setSymbol(newSymbol);
        FirDeclarationStatus status = baseField.getStatus();
        firFieldBuilder.setStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : newVisibility, (8388575 & 2) != 0 ? status.getModality() : newModality, (8388575 & 4) != 0 ? status.isExpect() : isExpect, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
        firFieldBuilder.setLocal(baseField.getIsLocal());
        FirFakeOverrideGenerator firFakeOverrideGenerator = INSTANCE;
        firFieldBuilder.setResolvePhase(firFakeOverrideGenerator.getResolvePhaseForCopy(origin));
        firFieldBuilder.setDispatchReceiverType(newDispatchReceiverType);
        firFieldBuilder.setAttributes(baseField.getAttributes().copy());
        firFakeOverrideGenerator.configureAnnotationsAndSignature(firFieldBuilder, baseField, newReceiverType, newContextParameterTypes, newReturnType, deferredReturnTypeCalculation, false);
        firFieldBuilder.setDeprecationsProvider(baseField.getDeprecationsProvider());
        FirField firFieldBuild = firFieldBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firFieldBuild, firFakeOverrideGenerator.shouldOverrideSetContainingClass(baseField) ? derivedClassLookupTag : null);
        return firFieldBuild;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final FirNamedFunction createCopyForFirFunction(FirNamedFunctionSymbol newSymbol, FirNamedFunction baseFunction, ConeClassLikeLookupTag derivedClassLookupTag, FirSession session, FirDeclarationOrigin origin, boolean isExpect, ConeSimpleKotlinType newDispatchReceiverType, List<? extends ConeKotlinType> newParameterTypes, List<? extends FirTypeParameterRef> newTypeParameters, ConeKotlinType newReceiverType, List<? extends ConeKotlinType> newContextParameterTypes, ConeKotlinType newReturnType, Modality newModality, Visibility newVisibility, DeferredCallableCopyReturnType deferredReturnTypeCalculation, KtSourceElement newSource, boolean markAsOverride) throws KotlinIllegalStateExceptionWithAttachments {
        newSymbol.getClass();
        baseFunction.getClass();
        session.getClass();
        origin.getClass();
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setSource(newSource);
        FirModuleData nullableModuleData = FirModuleDataKt.getNullableModuleData(session);
        if (nullableModuleData == null) {
            nullableModuleData = baseFunction.getModuleData();
        }
        firNamedFunctionBuilder.setModuleData(nullableModuleData);
        firNamedFunctionBuilder.setOrigin(origin);
        firNamedFunctionBuilder.setName(baseFunction.getName());
        FirDeclarationStatus status = baseFunction.getStatus();
        firNamedFunctionBuilder.setStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : newVisibility, (8388575 & 2) != 0 ? status.getModality() : newModality, (8388575 & 4) != 0 ? status.isExpect() : isExpect, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : markAsOverride ? true : baseFunction.getStatus().isOverride(), (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
        firNamedFunctionBuilder.setLocal(baseFunction.getIsLocal());
        firNamedFunctionBuilder.setSymbol(newSymbol);
        FirFakeOverrideGenerator firFakeOverrideGenerator = INSTANCE;
        firNamedFunctionBuilder.setResolvePhase(firFakeOverrideGenerator.getResolvePhaseForCopy(origin));
        firNamedFunctionBuilder.setDispatchReceiverType(newDispatchReceiverType);
        firNamedFunctionBuilder.setAttributes(baseFunction.getAttributes().copy());
        List<FirTypeParameter> typeParameters = firNamedFunctionBuilder.getTypeParameters();
        List<FirTypeParameterRef> listConfigureAnnotationsTypeParametersAndSignature = firFakeOverrideGenerator.configureAnnotationsTypeParametersAndSignature(firNamedFunctionBuilder, session, baseFunction, newParameterTypes, newTypeParameters, newReceiverType, newContextParameterTypes, newReturnType, deferredReturnTypeCalculation, newSymbol, false);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listConfigureAnnotationsTypeParametersAndSignature) {
            if (obj instanceof FirTypeParameter) {
                arrayList.add(obj);
            }
        }
        CollectionsKt.addAll(typeParameters, arrayList);
        firNamedFunctionBuilder.setDeprecationsProvider(baseFunction.getDeprecationsProvider());
        FirNamedFunction firNamedFunctionBuild = firNamedFunctionBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firNamedFunctionBuild, INSTANCE.shouldOverrideSetContainingClass(baseFunction) ? derivedClassLookupTag : null);
        return firNamedFunctionBuild;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final FirProperty createCopyForFirProperty(FirPropertySymbol newSymbol, FirProperty baseProperty, ConeClassLikeLookupTag derivedClassLookupTag, FirSession session, FirDeclarationOrigin origin, boolean isExpect, ConeSimpleKotlinType newDispatchReceiverType, List<? extends FirTypeParameter> newTypeParameters, ConeKotlinType newReceiverType, List<? extends ConeKotlinType> newContextParameterTypes, ConeKotlinType newReturnType, Modality newModality, Visibility newVisibility, Visibility newSetterVisibility, DeferredCallableCopyReturnType deferredReturnTypeCalculation, KtSourceElement newSource, FirBackingField baseBackingField, ConeKotlinType explicitBackingFieldNewReturnType, DeferredCallableCopyReturnType explicitBackingFieldCopySubstitutionForTypeUpdater) throws KotlinIllegalStateExceptionWithAttachments {
        FirFakeOverrideGenerator firFakeOverrideGenerator;
        FirPropertyAccessor firPropertyAccessorBuildCopyIfNeeded;
        FirPropertySymbol firPropertySymbol;
        FirDeclarationOrigin firDeclarationOrigin;
        FirPropertyAccessor firPropertyAccessorBuildCopyIfNeeded2;
        FirBackingField firBackingFieldBuild;
        KtSourceElement source;
        newSymbol.getClass();
        baseProperty.getClass();
        session.getClass();
        origin.getClass();
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        firPropertyBuilder.setSource(newSource == null ? baseProperty.getSource() : newSource);
        FirModuleData nullableModuleData = FirModuleDataKt.getNullableModuleData(session);
        if (nullableModuleData == null) {
            nullableModuleData = baseProperty.getModuleData();
        }
        firPropertyBuilder.setModuleData(nullableModuleData);
        firPropertyBuilder.setOrigin(origin);
        firPropertyBuilder.setName(baseProperty.getName());
        firPropertyBuilder.setVar(baseProperty.getIsVar());
        firPropertyBuilder.setSymbol(newSymbol);
        FirDeclarationStatus status = baseProperty.getStatus();
        firPropertyBuilder.setStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : newVisibility, (8388575 & 2) != 0 ? status.getModality() : newModality, (8388575 & 4) != 0 ? status.isExpect() : isExpect, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : true, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
        firPropertyBuilder.setLocal(baseProperty.getIsLocal());
        FirFakeOverrideGenerator firFakeOverrideGenerator2 = INSTANCE;
        firPropertyBuilder.setResolvePhase(firFakeOverrideGenerator2.getResolvePhaseForCopy(origin));
        firPropertyBuilder.setDispatchReceiverType(newDispatchReceiverType);
        firPropertyBuilder.setAttributes(baseProperty.getAttributes().copy());
        CollectionsKt.addAll(firPropertyBuilder.getTypeParameters(), firFakeOverrideGenerator2.configureAnnotationsTypeParametersAndSignature(firPropertyBuilder, session, baseProperty, newTypeParameters, newReceiverType, newContextParameterTypes, newReturnType, deferredReturnTypeCalculation));
        firPropertyBuilder.setDeprecationsProvider(baseProperty.getDeprecationsProvider());
        FirPropertyAccessor getter = baseProperty.getGetter();
        if (getter != null) {
            FirModuleData nullableModuleData2 = FirModuleDataKt.getNullableModuleData(session);
            if (nullableModuleData2 == null) {
                nullableModuleData2 = baseProperty.getModuleData();
            }
            FirModuleData firModuleData = nullableModuleData2;
            firFakeOverrideGenerator = firFakeOverrideGenerator2;
            firPropertyAccessorBuildCopyIfNeeded = firFakeOverrideGenerator.buildCopyIfNeeded(getter, firModuleData, origin, firPropertyBuilder.getReturnTypeRef(), newSymbol, firPropertyBuilder.getDispatchReceiverType(), derivedClassLookupTag, baseProperty, newSource == null ? getter.getSource() : newSource, newVisibility == null ? getter.getStatus().getVisibility() : newVisibility, newModality == null ? getter.getStatus().getModality() : newModality);
        } else {
            firFakeOverrideGenerator = firFakeOverrideGenerator2;
            firPropertyAccessorBuildCopyIfNeeded = null;
        }
        firPropertyBuilder.setGetter(firPropertyAccessorBuildCopyIfNeeded);
        FirPropertyAccessor setter = baseProperty.getSetter();
        if (setter != null) {
            FirModuleData nullableModuleData3 = FirModuleDataKt.getNullableModuleData(session);
            if (nullableModuleData3 == null) {
                nullableModuleData3 = baseProperty.getModuleData();
            }
            FirTypeRef returnTypeRef = firPropertyBuilder.getReturnTypeRef();
            ConeSimpleKotlinType dispatchReceiverType = firPropertyBuilder.getDispatchReceiverType();
            if (newSource == null) {
                FirPropertyAccessor setter2 = baseProperty.getSetter();
                source = setter2 != null ? setter2.getSource() : null;
            } else {
                source = newSource;
            }
            firPropertySymbol = newSymbol;
            firDeclarationOrigin = origin;
            firPropertyAccessorBuildCopyIfNeeded2 = firFakeOverrideGenerator.buildCopyIfNeeded(setter, nullableModuleData3, firDeclarationOrigin, returnTypeRef, firPropertySymbol, dispatchReceiverType, derivedClassLookupTag, baseProperty, source, newSetterVisibility == null ? setter.getStatus().getVisibility() : newSetterVisibility, newModality == null ? setter.getStatus().getModality() : newModality);
        } else {
            firPropertySymbol = newSymbol;
            firDeclarationOrigin = origin;
            firPropertyAccessorBuildCopyIfNeeded2 = null;
        }
        firPropertyBuilder.setSetter(firPropertyAccessorBuildCopyIfNeeded2);
        FirBackingField explicitBackingField = baseBackingField == null ? DeclarationAttributesKt.getExplicitBackingField(baseProperty) : baseBackingField;
        if (explicitBackingField != null) {
            FirBackingFieldBuilder firBackingFieldBuilder = new FirBackingFieldBuilder();
            firBackingFieldBuilder.setSource(explicitBackingField.getSource());
            firBackingFieldBuilder.setPropertySymbol(firPropertySymbol);
            firBackingFieldBuilder.setSymbol(new FirBackingFieldSymbol());
            firBackingFieldBuilder.setModuleData(explicitBackingField.getModuleData());
            firBackingFieldBuilder.setOrigin(firDeclarationOrigin);
            FirDeclarationStatus status2 = explicitBackingField.getStatus();
            firBackingFieldBuilder.setStatus(UtilsKt.copy(status2, (8388575 & 1) != 0 ? status2.getVisibility() : null, (8388575 & 2) != 0 ? status2.getModality() : null, (8388575 & 4) != 0 ? status2.isExpect() : false, (8388575 & 8) != 0 ? status2.isActual() : false, (8388575 & 16) != 0 ? status2.isOverride() : false, (8388575 & 32) != 0 ? status2.isOperator() : false, (8388575 & 64) != 0 ? status2.isInfix() : false, (8388575 & 128) != 0 ? status2.isInline() : false, (8388575 & 256) != 0 ? status2.isValue() : false, (8388575 & 512) != 0 ? status2.isTailRec() : false, (8388575 & 1024) != 0 ? status2.isExternal() : false, (8388575 & 2048) != 0 ? status2.isConst() : false, (8388575 & 4096) != 0 ? status2.isLateInit() : false, (8388575 & 8192) != 0 ? status2.isInner() : false, (8388575 & 16384) != 0 ? status2.isCompanion() : false, (8388575 & 32768) != 0 ? status2.isData() : false, (8388575 & 65536) != 0 ? status2.isSuspend() : false, (8388575 & 131072) != 0 ? status2.isStatic() : false, (8388575 & 262144) != 0 ? status2.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status2.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status2.isFun() : false, (8388575 & 2097152) != 0 ? status2.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status2.getReturnValueStatus() : null));
            firBackingFieldBuilder.setResolvePhase(firFakeOverrideGenerator.getResolvePhaseForCopy(firDeclarationOrigin));
            firBackingFieldBuilder.setName(explicitBackingField.getName());
            CollectionsKt.addAll(firBackingFieldBuilder.getAnnotations(), explicitBackingField.getAnnotations());
            firBackingFieldBuilder.setAttributes(baseProperty.getAttributes().copy());
            firBackingFieldBuilder.setVar(explicitBackingField.getIsVar());
            firBackingFieldBuilder.setVal(explicitBackingField.getIsVal());
            firFakeOverrideGenerator.configureAnnotationsAndSignature(firBackingFieldBuilder, explicitBackingField, newReceiverType, newContextParameterTypes, explicitBackingFieldNewReturnType, explicitBackingFieldCopySubstitutionForTypeUpdater, false);
            firBackingFieldBuild = firBackingFieldBuilder.mo288build();
        } else {
            firBackingFieldBuild = null;
        }
        r12.setBackingField(firBackingFieldBuild);
        FirProperty firPropertyBuild = firPropertyBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firPropertyBuild, firFakeOverrideGenerator.shouldOverrideSetContainingClass(baseProperty) ? derivedClassLookupTag : null);
        return firPropertyBuild;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Pair<List<FirTypeParameterRef>, ConeSubstitutor> createNewTypeParametersAndSubstitutor(FirSession useSiteSession, FirTypeParameterRefsOwner original, FirBasedSymbol<?> symbolForOverride, ConeSubstitutor substitutor, FirDeclarationOrigin origin, boolean forceTypeParametersRecreation) {
        useSiteSession.getClass();
        original.getClass();
        symbolForOverride.getClass();
        substitutor.getClass();
        origin.getClass();
        if (original.getTypeParameters().isEmpty()) {
            return new Pair<>(original.getTypeParameters(), substitutor);
        }
        List<FirTypeParameterRef> typeParameters = original.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            FirTypeParameter firTypeParameter = (FirTypeParameter) ((FirTypeParameterRef) it.next()).getSymbol().getFir();
            FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
            firTypeParameterBuilder.setSource(firTypeParameter.getSource());
            firTypeParameterBuilder.setModuleData(firTypeParameter.getModuleData());
            firTypeParameterBuilder.setOrigin(origin);
            firTypeParameterBuilder.setResolvePhase(INSTANCE.getResolvePhaseForCopy(origin));
            firTypeParameterBuilder.setName(firTypeParameter.getName());
            firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
            firTypeParameterBuilder.setVariance(firTypeParameter.getVariance());
            firTypeParameterBuilder.setReified(firTypeParameter.getIsReified());
            CollectionsKt.addAll(firTypeParameterBuilder.getAnnotations(), firTypeParameter.getAnnotations());
            firTypeParameterBuilder.setContainingDeclarationSymbol(symbolForOverride);
            arrayList.add(firTypeParameterBuilder);
        }
        List listZip = CollectionsKt.zip(original.getTypeParameters(), arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : listZip) {
            if (((Pair) obj).getFirst() instanceof FirConstructedClassTypeParameterRef) {
                arrayList3.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        List list = (List) pair.component1();
        List list2 = (List) pair.component2();
        ChainedSubstitutor.Companion companion = ChainedSubstitutor.INSTANCE;
        ConeSubstitutor coneSubstitutorInvoke = companion.invoke(createNewTypeParametersAndSubstitutor$substitutorFrom(list, useSiteSession), companion.invoke(substitutor, createNewTypeParametersAndSubstitutor$substitutorFrom(list2, useSiteSession)));
        for (Pair pair2 : CollectionsKt.zip(arrayList, original.getTypeParameters())) {
            FirTypeParameterBuilder firTypeParameterBuilder2 = (FirTypeParameterBuilder) pair2.component1();
            for (FirResolvedTypeRef firResolvedTypeRef : ((FirTypeParameterRef) pair2.component2()).getSymbol().getResolvedBounds()) {
                ConeKotlinType coneType = firResolvedTypeRef.getConeType();
                ConeKotlinType coneKotlinTypeSubstituteOrNull = coneSubstitutorInvoke.substituteOrNull(coneType);
                if (coneKotlinTypeSubstituteOrNull != null) {
                    forceTypeParametersRecreation = true;
                }
                List<FirTypeRef> bounds = firTypeParameterBuilder2.getBounds();
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                firResolvedTypeRefBuilder.setSource(firResolvedTypeRef.getSource());
                if (coneKotlinTypeSubstituteOrNull != null) {
                    coneType = coneKotlinTypeSubstituteOrNull;
                }
                firResolvedTypeRefBuilder.setConeType(coneType);
                bounds.add(firResolvedTypeRefBuilder.build());
            }
        }
        if (!forceTypeParametersRecreation) {
            return new Pair<>(original.getTypeParameters(), substitutor);
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList4.add(((FirTypeParameterBuilder) it2.next()).mo288build());
        }
        return new Pair<>(arrayList4, coneSubstitutorInvoke);
    }

    public final FirFieldSymbol createSubstitutionOverrideField(FirSession session, FirField baseField, ConeClassLikeLookupTag derivedClassLookupTag, ConeKotlinType newReturnType, ConeSimpleKotlinType newDispatchReceiverType, FirDeclarationOrigin.SubstitutionOverride origin) {
        session.getClass();
        baseField.getClass();
        derivedClassLookupTag.getClass();
        origin.getClass();
        FirFieldBuilder firFieldBuilder = new FirFieldBuilder();
        FirFieldSymbol firFieldSymbol = new FirFieldSymbol(new CallableId(derivedClassLookupTag.getClassId(), baseField.getName()));
        FirModuleData nullableModuleData = FirModuleDataKt.getNullableModuleData(session);
        if (nullableModuleData == null) {
            nullableModuleData = baseField.getModuleData();
        }
        firFieldBuilder.setModuleData(nullableModuleData);
        firFieldBuilder.setSymbol(firFieldSymbol);
        firFieldBuilder.setOrigin(origin);
        firFieldBuilder.setReturnTypeRef(TypeUtilsKt.withReplacedConeType$default(baseField.getReturnTypeRef(), newReturnType, null, 2, null));
        firFieldBuilder.setSource(baseField.getSource());
        firFieldBuilder.setName(baseField.getName());
        firFieldBuilder.setVar(baseField.getIsVar());
        firFieldBuilder.setStatus(baseField.getStatus());
        firFieldBuilder.setLocal(baseField.getIsLocal());
        FirFakeOverrideGenerator firFakeOverrideGenerator = INSTANCE;
        firFieldBuilder.setResolvePhase(firFakeOverrideGenerator.getResolvePhaseForCopy(origin));
        CollectionsKt.addAll(firFieldBuilder.getAnnotations(), baseField.getAnnotations());
        firFieldBuilder.setAttributes(baseField.getAttributes().copy());
        firFieldBuilder.setDispatchReceiverType(newDispatchReceiverType);
        FirField firFieldBuild = firFieldBuilder.mo288build();
        ClassMembersKt.setOriginalForSubstitutionOverrideAttr(firFieldBuild, baseField);
        if (!firFakeOverrideGenerator.shouldOverrideSetContainingClass(baseField)) {
            derivedClassLookupTag = null;
        }
        ClassMembersKt.setContainingClassForStaticMemberAttr(firFieldBuild, derivedClassLookupTag);
        return firFieldBuild.getSymbol();
    }

    public final FirPropertySymbol createSubstitutionOverrideProperty(FirSession session, FirPropertySymbol symbolForSubstitutionOverride, FirProperty baseProperty, ConeClassLikeLookupTag derivedClassLookupTag, ConeSimpleKotlinType newDispatchReceiverType, FirDeclarationOrigin.SubstitutionOverride origin, ConeKotlinType newReceiverType, List<? extends ConeKotlinType> newContextParameterTypes, ConeKotlinType newReturnType, List<? extends FirTypeParameter> newTypeParameters, boolean isExpect, DeferredCallableCopyReturnType callableCopySubstitutionForTypeUpdater, ConeKotlinType explicitBackingFieldNewReturnType, DeferredCallableCopyReturnType explicitBackingFieldCopySubstitutionForTypeUpdater) {
        session.getClass();
        symbolForSubstitutionOverride.getClass();
        baseProperty.getClass();
        derivedClassLookupTag.getClass();
        origin.getClass();
        FirProperty firPropertyCreateCopyForFirProperty$default = createCopyForFirProperty$default(this, symbolForSubstitutionOverride, baseProperty, derivedClassLookupTag, session, origin, isExpect, newDispatchReceiverType, newTypeParameters, newReceiverType, newContextParameterTypes, newReturnType, null, null, null, callableCopySubstitutionForTypeUpdater, null, null, explicitBackingFieldNewReturnType, explicitBackingFieldCopySubstitutionForTypeUpdater, 112640, null);
        ClassMembersKt.setOriginalForSubstitutionOverrideAttr(firPropertyCreateCopyForFirProperty$default, baseProperty);
        FirBackingField backingField = firPropertyCreateCopyForFirProperty$default.getBackingField();
        if (backingField != null) {
            ClassMembersKt.setOriginalForSubstitutionOverrideAttr(backingField, baseProperty.getBackingField());
        }
        return symbolForSubstitutionOverride;
    }

    public final FirNamedFunctionSymbol createSymbolForSubstitutionOverride(FirNamedFunctionSymbol baseSymbol, ClassId derivedClassId) {
        baseSymbol.getClass();
        return derivedClassId == null ? new FirNamedFunctionSymbol(baseSymbol.getCallableId()) : new FirNamedFunctionSymbol(new CallableId(derivedClassId, baseSymbol.getCallableId().getCallableName()));
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe;", "A", Argument.Delimiters.none, "<init>", "()V", "Value", "Nothing", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe$Nothing;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe$Value;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class Maybe<A> {

        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe$Nothing;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe;", Argument.Delimiters.none, "<init>", "()V", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Nothing extends Maybe {
            public static final Nothing INSTANCE = new Nothing();

            private Nothing() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe$Value;", "A", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirFakeOverrideGenerator$Maybe;", "value", "<init>", ReifiedTypeInliner.pluginIntrinsicsMarkerSignature, "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Value<A> extends Maybe<A> {
            private final A value;

            public Value(A a) {
                super(null);
                this.value = a;
            }

            public final A getValue() {
                return this.value;
            }
        }

        public /* synthetic */ Maybe(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Maybe() {
        }
    }

    public static /* synthetic */ FirRegularPropertySymbol createSymbolForSubstitutionOverride$default(FirFakeOverrideGenerator firFakeOverrideGenerator, FirPropertySymbol firPropertySymbol, ClassId classId, int i, Object obj) {
        if ((i & 2) != 0) {
            classId = null;
        }
        return firFakeOverrideGenerator.createSymbolForSubstitutionOverride(firPropertySymbol, classId);
    }

    public final FirRegularPropertySymbol createSymbolForSubstitutionOverride(FirPropertySymbol baseSymbol, ClassId derivedClassId) {
        baseSymbol.getClass();
        if (derivedClassId == null) {
            CallableId callableId = baseSymbol.getCallableId();
            callableId.getClass();
            return new FirRegularPropertySymbol(callableId);
        }
        return new FirRegularPropertySymbol(new CallableId(derivedClassId, baseSymbol.getName()));
    }

    public final FirNamedFunctionSymbol createSubstitutionOverrideFunction(FirSession session, FirNamedFunctionSymbol symbolForSubstitutionOverride, FirNamedFunction baseFunction, ConeClassLikeLookupTag derivedClassLookupTag, ConeSimpleKotlinType newDispatchReceiverType, FirDeclarationOrigin.SubstitutionOverride origin, ConeKotlinType newReceiverType, List<? extends ConeKotlinType> newContextParameterTypes, ConeKotlinType newReturnType, List<? extends ConeKotlinType> newParameterTypes, List<? extends FirTypeParameter> newTypeParameters, boolean isExpect, DeferredCallableCopyReturnType callableCopySubstitutionForTypeUpdater) {
        session.getClass();
        symbolForSubstitutionOverride.getClass();
        baseFunction.getClass();
        origin.getClass();
        createSubstitutionOverrideFunction(symbolForSubstitutionOverride, session, baseFunction, derivedClassLookupTag, newDispatchReceiverType, newReceiverType, newContextParameterTypes, newReturnType, newParameterTypes, newTypeParameters, isExpect, callableCopySubstitutionForTypeUpdater, origin);
        return symbolForSubstitutionOverride;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final List<FirTypeParameter> configureAnnotationsTypeParametersAndSignature(FirPropertyBuilder firPropertyBuilder, FirSession firSession, FirProperty firProperty, List<? extends FirTypeParameter> list, ConeKotlinType coneKotlinType, List<? extends ConeKotlinType> list2, ConeKotlinType coneKotlinType2, DeferredCallableCopyReturnType deferredCallableCopyReturnType) throws KotlinIllegalStateExceptionWithAttachments {
        if (firProperty.getTypeParameters().isEmpty()) {
            configureAnnotationsAndSignature$default(this, firPropertyBuilder, firProperty, coneKotlinType, list2, coneKotlinType2, deferredCallableCopyReturnType, false, 32, null);
            return CollectionsKt.emptyList();
        }
        if (list == 0) {
            Pair pairCreateNewTypeParametersAndSubstitutor$default = createNewTypeParametersAndSubstitutor$default(this, firSession, firProperty, firPropertyBuilder.getSymbol(), ConeSubstitutor.Empty.INSTANCE, firPropertyBuilder.getOrigin(), false, 32, null);
            List list3 = (List) pairCreateNewTypeParametersAndSubstitutor$default.component1();
            ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pairCreateNewTypeParametersAndSubstitutor$default.component2();
            Triple<ConeKotlinType, List<ConeKotlinType>, Maybe<ConeKotlinType>> tripleSubstituteReceiverAndReturnType = substituteReceiverAndReturnType(firProperty, coneKotlinType, list2, coneKotlinType2, coneSubstitutor);
            ConeKotlinType coneKotlinType3 = (ConeKotlinType) tripleSubstituteReceiverAndReturnType.component1();
            List list4 = (List) tripleSubstituteReceiverAndReturnType.component2();
            Maybe maybe = (Maybe) tripleSubstituteReceiverAndReturnType.component3();
            Pair pair = maybe instanceof Maybe.Value ? TuplesKt.to(((Maybe.Value) maybe).getValue(), null) : TuplesKt.to(null, new DeferredReturnTypeOfSubstitution(coneSubstitutor, firProperty.getSymbol()));
            configureAnnotationsAndSignature$default(this, firPropertyBuilder, firProperty, coneKotlinType3, list4, (ConeKotlinType) pair.component1(), (DeferredReturnTypeOfSubstitution) pair.component2(), false, 32, null);
            ArrayList arrayList = new ArrayList();
            for (Object obj : list3) {
                if (obj instanceof FirTypeParameter) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }
        configureAnnotationsAndSignature$default(this, firPropertyBuilder, firProperty, coneKotlinType, list2, coneKotlinType2, deferredCallableCopyReturnType, false, 32, null);
        return list;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.util.List<? extends org.jetbrains.kotlin.fir.types.ConeKotlinType>] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.util.ArrayList] */
    private final void configureAnnotationsAndSignature(FirVariableBuilder firVariableBuilder, FirVariable firVariable, ConeKotlinType coneKotlinType, List<? extends ConeKotlinType> list, ConeKotlinType coneKotlinType2, DeferredCallableCopyReturnType deferredCallableCopyReturnType, boolean z) throws KotlinIllegalStateExceptionWithAttachments {
        FirReceiverParameter firReceiverParameterBuild;
        checkStatusIsResolved(firVariable);
        CollectionsKt.addAll(firVariableBuilder.getAnnotations(), firVariable.getAnnotations());
        if (deferredCallableCopyReturnType == null) {
            deferredCallableCopyReturnType = firVariable.getReturnTypeRef() instanceof FirImplicitTypeRef ? new DeferredReturnTypeOfSubstitution(ConeSubstitutor.Empty.INSTANCE, firVariable.getSymbol()) : null;
        }
        if (deferredCallableCopyReturnType != null) {
            firVariableBuilder.setReturnTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
            CallableCopyTypeCalculatorKt.setDeferredCallableCopyReturnType(firVariableBuilder.getAttributes(), deferredCallableCopyReturnType);
        } else {
            firVariableBuilder.setReturnTypeRef(TypeUtilsKt.withReplacedReturnType(firVariable.getReturnTypeRef(), coneKotlinType2));
        }
        if (z) {
            FirReceiverParameter receiverParameter = firVariable.getReceiverParameter();
            if (receiverParameter != null) {
                FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
                firReceiverParameterBuilder.setSource(receiverParameter.getSource());
                firReceiverParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(receiverParameter));
                firReceiverParameterBuilder.setModuleData(receiverParameter.getModuleData());
                firReceiverParameterBuilder.setOrigin(receiverParameter.getOrigin());
                firReceiverParameterBuilder.setAttributes(receiverParameter.getAttributes().copy());
                firReceiverParameterBuilder.setTypeRef(receiverParameter.getTypeRef());
                firReceiverParameterBuilder.setContainingDeclarationSymbol(receiverParameter.getContainingDeclarationSymbol());
                firReceiverParameterBuilder.getAnnotations().addAll(receiverParameter.getAnnotations());
                firReceiverParameterBuilder.setTypeRef(TypeUtilsKt.withReplacedConeType$default(receiverParameter.getTypeRef(), coneKotlinType, null, 2, null));
                firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
                firReceiverParameterBuild = firReceiverParameterBuilder.mo288build();
            } else {
                firReceiverParameterBuild = null;
            }
            firVariableBuilder.setReceiverParameter(firReceiverParameterBuild);
        }
        List<FirValueParameter> contextParameters = firVariableBuilder.getContextParameters();
        List<FirValueParameter> contextParameters2 = firVariable.getContextParameters();
        if (list == 0) {
            int size = firVariable.getContextParameters().size();
            list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(null);
            }
        }
        Iterable iterable = (Iterable) list;
        Iterator it = contextParameters2.iterator();
        Iterator it2 = iterable.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(contextParameters2, 10), CollectionsKt.collectionSizeOrDefault(iterable, 10)));
        while (it.hasNext() && it2.hasNext()) {
            Object next = it.next();
            ConeKotlinType coneKotlinType3 = (ConeKotlinType) it2.next();
            FirValueParameter firValueParameter = (FirValueParameter) next;
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            firValueParameterBuilder.setSource(firValueParameter.getSource());
            firValueParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firValueParameter));
            firValueParameterBuilder.setModuleData(firValueParameter.getModuleData());
            firValueParameterBuilder.setOrigin(firValueParameter.getOrigin());
            firValueParameterBuilder.setAttributes(firValueParameter.getAttributes().copy());
            firValueParameterBuilder.setReturnTypeRef(firValueParameter.getReturnTypeRef());
            firValueParameterBuilder.setName(firValueParameter.getName());
            firValueParameterBuilder.getAnnotations().addAll(firValueParameter.getAnnotations());
            firValueParameterBuilder.setDefaultValue(firValueParameter.getDefaultValue());
            firValueParameterBuilder.setContainingDeclarationSymbol(firValueParameter.getContainingDeclarationSymbol());
            firValueParameterBuilder.setCrossinline(firValueParameter.getIsCrossinline());
            firValueParameterBuilder.setNoinline(firValueParameter.getIsNoinline());
            firValueParameterBuilder.setVararg(firValueParameter.getIsVararg());
            firValueParameterBuilder.setValueParameterKind(firValueParameter.getValueParameterKind());
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder.setReturnTypeRef(TypeUtilsKt.withReplacedConeType$default(firValueParameter.getReturnTypeRef(), coneKotlinType3, null, 2, null));
            arrayList.add(firValueParameterBuilder.mo288build());
        }
        CollectionsKt.addAll(contextParameters, arrayList);
    }
}
