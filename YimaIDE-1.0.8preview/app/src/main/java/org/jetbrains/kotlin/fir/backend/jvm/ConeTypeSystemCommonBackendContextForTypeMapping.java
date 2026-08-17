package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.builtins.functions.AllowedToUsedOnlyInK1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedSymbolError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedTypeQualifierError;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeErrorLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContextForTypeMapping;
import org.jetbrains.kotlin.types.model.AnnotationMarker;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.CapturedTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.CapturedTypeMarker;
import org.jetbrains.kotlin.types.model.DefinitelyNotNullTypeMarker;
import org.jetbrains.kotlin.types.model.DynamicTypeMarker;
import org.jetbrains.kotlin.types.model.FlexibleTypeMarker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.ObsoleteTypeKind;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentListMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeVariance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ð\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0016J\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0014H\u0016J\f\u0010\u0017\u001a\u00020\u0018*\u00020\u0014H\u0016J\f\u0010\u0019\u001a\u00020\u0013*\u00020\u0014H\u0016J\f\u0010\u001a\u001a\u00020\u0013*\u00020\u001bH\u0016J\f\u0010\u001c\u001a\u00020\u0013*\u00020\u001bH\u0016J\f\u0010\u001d\u001a\u00020\u0013*\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u0018*\u00020\u00142\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001e0!H\u0016J\f\u0010\"\u001a\u00020#*\u00020\u0016H\u0016J\b\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+H\u0002J\u000e\u0010,\u001a\u0004\u0018\u00010\u0007*\u00020\u001eH\u0016J\u0019\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\u0014H\u0096\u0001J\r\u00100\u001a\u00020(*\u00020\u001eH\u0096\u0001J\u0011\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u001eH\u0096\u0001J\r\u00104\u001a\u000205*\u00020\u001bH\u0096\u0001J\u000f\u00106\u001a\u0004\u0018\u000107*\u000202H\u0096\u0001J'\u00106\u001a\u000207*\u000207H\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000f\u0010>\u001a\u0004\u0018\u000107*\u00020\u001bH\u0096\u0001J'\u0010>\u001a\u000207*\u000207H\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000f\u0010?\u001a\u0004\u0018\u00010@*\u00020\u001bH\u0096\u0001J'\u0010?\u001a\u00020@*\u00020@H\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000f\u0010A\u001a\u0004\u0018\u00010B*\u00020CH\u0096\u0001J'\u0010A\u001a\u00020B*\u00020BH\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000f\u0010D\u001a\u0004\u0018\u00010C*\u00020\u001eH\u0096\u0001J'\u0010D\u001a\u00020C*\u00020CH\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000f\u0010E\u001a\u0004\u0018\u00010\u001b*\u00020\u001eH\u0096\u0001J'\u0010E\u001a\u00020\u001b*\u00020\u001bH\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\r\u0010F\u001a\u00020G*\u00020\u001eH\u0096\u0001J\u001b\u0010H\u001a\u0004\u0018\u00010\u001b2\u0006\u0010I\u001a\u00020\u001b2\u0006\u0010J\u001a\u00020KH\u0096\u0001J\u0013\u0010L\u001a\u0004\u0018\u00010\u001e2\u0006\u0010I\u001a\u00020\u001eH\u0096\u0001J\r\u0010M\u001a\u00020K*\u000207H\u0096\u0001J\t\u0010N\u001a\u00020OH\u0096\u0001J\u001d\u0010P\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010!*\u00020\u001b2\u0006\u0010Q\u001a\u00020\u0014H\u0096\u0001J\u0015\u0010R\u001a\u00020G*\u0002052\u0006\u0010S\u001a\u00020(H\u0096\u0003J\u0017\u0010T\u001a\u0004\u0018\u00010U*\u00020\u001e2\u0006\u0010V\u001a\u00020WH\u0096\u0001J\u0015\u0010X\u001a\u00020G*\u00020\u001e2\u0006\u0010S\u001a\u00020(H\u0096\u0001J\u0017\u0010Y\u001a\u0004\u0018\u00010G*\u00020\u001b2\u0006\u0010S\u001a\u00020(H\u0096\u0001J\u0013\u0010Z\u001a\b\u0012\u0004\u0012\u00020G0!*\u00020\u001eH\u0096\u0001J\u0013\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0!*\u00020\u001eH\u0096\u0001J\u000f\u0010]\u001a\u0004\u0018\u00010^*\u00020\u0014H\u0096\u0001J\r\u0010_\u001a\u00020`*\u00020\u0016H\u0096\u0001J\u0015\u0010a\u001a\u00020\u0016*\u00020\u00142\u0006\u0010S\u001a\u00020(H\u0096\u0001J\u0013\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00160!*\u00020\u0014H\u0096\u0001J\u000f\u0010c\u001a\u0004\u0018\u00010d*\u00020\u0014H\u0096\u0001J\u000f\u0010e\u001a\u0004\u0018\u00010d*\u00020\u0014H\u0096\u0001J\r\u0010f\u001a\u00020\u001e*\u00020\u0016H\u0096\u0001J\u000f\u0010g\u001a\u0004\u0018\u00010\u001e*\u00020GH\u0096\u0001J\r\u0010h\u001a\u00020\u0014*\u00020\u0016H\u0096\u0001J\u000f\u0010i\u001a\u0004\u0018\u00010\u0016*\u00020\u0014H\u0096\u0001J\u000f\u0010j\u001a\u0004\u0018\u00010\u001e*\u00020\u001eH\u0096\u0001J\u0015\u0010k\u001a\u00020\u001e*\u00020\u00162\u0006\u0010S\u001a\u00020(H\u0096\u0001J\u0013\u0010l\u001a\b\u0012\u0004\u0012\u00020\u001e0!*\u00020\u0016H\u0096\u0001J!\u0010m\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020\u001b0n\u0018\u00010!*\u00020\u0014H\u0096\u0001J\r\u0010o\u001a\u00020p*\u00020GH\u0096\u0001J\r\u0010o\u001a\u00020p*\u00020\u0016H\u0096\u0001J\u0015\u0010q\u001a\u00020\u0013*\u00020\u001e2\u0006\u0010V\u001a\u00020WH\u0096\u0001J\r\u0010r\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u0017\u0010s\u001a\u00020\u0013*\u00020\u00162\b\u0010t\u001a\u0004\u0018\u00010\u0014H\u0096\u0001J\u0019\u0010u\u001a\u00020\u00132\u0006\u0010v\u001a\u00020\u001b2\u0006\u0010w\u001a\u00020\u001bH\u0096\u0001J\u0017\u0010x\u001a\u00020\u001e2\f\u0010y\u001a\b\u0012\u0004\u0012\u00020\u001e0zH\u0096\u0001J\u0017\u0010x\u001a\u0002022\f\u0010y\u001a\b\u0012\u0004\u0012\u0002020zH\u0096\u0001J\r\u0010{\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\r\u0010|\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\r\u0010}\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\r\u0010~\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\r\u0010\u007f\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u0080\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u0081\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010\u0082\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0083\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0084\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u0084\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010\u0085\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0086\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u0087\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u0087\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0088\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0089\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u008a\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u008b\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u008c\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u008d\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u008e\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u008f\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0090\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010\u0091\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0092\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0093\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u0094\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0095\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u0096\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u0097\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u0013\u0010\u0098\u0001\u001a\u00020\u0013*\u00020\u001eH\u0097\u0001b\u0003\b\u0099\u0001J\u000e\u0010\u009a\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u009b\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010\u009c\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u009d\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u009e\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u0017\u0010\u009e\u0001\u001a\u00020\u0013*\u00020\u001e2\u0007\u0010\u009f\u0001\u001a\u00020\u0013H\u0096\u0001J\u000e\u0010 \u0001\u001a\u00020\u0013*\u000207H\u0096\u0001J\u000e\u0010¡\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010¡\u0001\u001a\u00020\u0013*\u000202H\u0096\u0001J\u0013\u0010¢\u0001\u001a\u00020\u0013*\u000207H\u0097\u0001b\u0003\b£\u0001J\u000e\u0010¤\u0001\u001a\u00020\u0013*\u00020\u0016H\u0096\u0001J\u000e\u0010¥\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010¦\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010§\u0001\u001a\u00020\u0013*\u00020GH\u0096\u0001J\u000e\u0010¨\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010©\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010ª\u0001\u001a\u00020\u0013*\u00020\u001bH\u0096\u0001J\u000e\u0010«\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010¬\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u000e\u0010\u00ad\u0001\u001a\u00020\u0013*\u00020\u0014H\u0096\u0001J\u000e\u0010®\u0001\u001a\u00020\u0013*\u00020\u001eH\u0096\u0001J\u0015\u0010¯\u0001\u001a\t\u0012\u0004\u0012\u00020G0°\u0001*\u000205H\u0096\u0003J\u000e\u0010±\u0001\u001a\u00020\u001b*\u00020CH\u0096\u0001J(\u0010²\u0001\u001a\u00020\u001b*\u00020\u001bH\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000e\u0010²\u0001\u001a\u00020\u001b*\u00020\u001eH\u0096\u0001J\u0010\u0010³\u0001\u001a\u0004\u0018\u00010\u001e*\u000207H\u0096\u0001J(\u0010´\u0001\u001a\u00020@*\u00020@H\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000e\u0010´\u0001\u001a\u00020\u001e*\u00020\u001eH\u0096\u0001J\u0017\u0010´\u0001\u001a\u00020\u001e*\u00020\u001e2\u0007\u0010µ\u0001\u001a\u00020\u0013H\u0096\u0001J\u000e\u0010´\u0001\u001a\u00020\u001b*\u00020\u001bH\u0096\u0001J\u000e\u0010¶\u0001\u001a\u00020\u001e*\u00020\u001eH\u0096\u0001J\n\u0010·\u0001\u001a\u000202H\u0096\u0001J\u000e\u0010¸\u0001\u001a\u000202*\u00020@H\u0096\u0001J(\u0010¹\u0001\u001a\u000202*\u000202H\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000e\u0010¹\u0001\u001a\u000202*\u00020\u001bH\u0096\u0001J\u000e\u0010º\u0001\u001a\u00020(*\u00020\u0014H\u0096\u0001J\u0014\u0010»\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0z*\u00020\u001bH\u0096\u0001J\u000f\u0010¼\u0001\u001a\u00020G*\u00030½\u0001H\u0096\u0001J\u0017\u0010¾\u0001\u001a\u00020G*\u00020G2\u0007\u0010¿\u0001\u001a\u00020\u001eH\u0096\u0001J\u0016\u0010À\u0001\u001a\u00020\u001e*\u00020O2\u0006\u0010I\u001a\u00020\u001eH\u0096\u0001J\u000e\u0010Á\u0001\u001a\u00020(*\u000205H\u0096\u0001J\u0013\u0010Â\u0001\u001a\u00030Ã\u00012\u0006\u0010I\u001a\u00020\u001bH\u0096\u0001J\u0014\u0010Ä\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0z*\u00020\u0014H\u0096\u0001J\u000f\u0010Å\u0001\u001a\u00030½\u0001*\u000207H\u0096\u0001J\u000e\u0010Å\u0001\u001a\u00020\u0014*\u00020\u001bH\u0096\u0001J\u000e\u0010Å\u0001\u001a\u00020\u0014*\u00020\u001eH\u0096\u0001J \u0010Æ\u0001\u001a\u00020O2\u0014\u0010Ç\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u001e0È\u0001H\u0096\u0001J \u0010É\u0001\u001a\u00020O2\u0014\u0010Ç\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u001e0È\u0001H\u0096\u0001J\u000e\u0010Ê\u0001\u001a\u00020\u0014*\u00020\u0014H\u0096\u0001J\u000e\u0010Ë\u0001\u001a\u00020\u001b*\u00020CH\u0096\u0001J\u000e\u0010Ì\u0001\u001a\u00020(*\u00020\u0016H\u0096\u0001J(\u0010Í\u0001\u001a\u00020\u001b*\u00020\u001bH\u0097\u0001b\u0018\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\n\b;\u0012\u0006\b\n0<8=J\u000e\u0010Í\u0001\u001a\u00020\u001b*\u00020\u001eH\u0096\u0001J \u0010Î\u0001\u001a\u00020\u001e*\u00020\u001e2\u0007\u0010Ï\u0001\u001a\u00020U2\u0007\u0010¿\u0001\u001a\u00020\u001eH\u0096\u0001J\u0017\u0010Ð\u0001\u001a\u00020\u001e*\u00020\u001e2\u0007\u0010Ñ\u0001\u001a\u00020\u0013H\u0096\u0001J\u0017\u0010Ð\u0001\u001a\u00020\u001b*\u00020\u001b2\u0007\u0010Ñ\u0001\u001a\u00020\u0013H\u0096\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000RY\u0010Ò\u0001\u001aD\u0012\u0016\u0012\u00140\u001e¢\u0006\u000f\bÔ\u0001\u0012\n\bÕ\u0001\u0012\u0005\b\b(Ö\u0001\u0012\u0016\u0012\u00140\u001e¢\u0006\u000f\bÔ\u0001\u0012\n\bÕ\u0001\u0012\u0005\b\b(×\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010Ó\u0001j\u0005\u0018\u0001`Ø\u00018VX\u0096\u0005¢\u0006\b\u001a\u0006\bÙ\u0001\u0010Ú\u0001R\u001c\u0010Û\u0001\u001a\u0004\u0018\u00010\u0016*\u00030Ü\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÝ\u0001\u0010Þ\u0001¨\u0006ß\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/ConeTypeSystemCommonBackendContextForTypeMapping;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContextForTypeMapping;", "context", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "unresolvedQualifierRemapper", "Lkotlin/Function1;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;Lkotlin/jvm/functions/Function1;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "getUnresolvedQualifierRemapper", "()Lkotlin/jvm/functions/Function1;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "isTypeParameter", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "asTypeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "defaultType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "isScript", "isSuspendFunction", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "isKClass", "isRawType", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "typeWithArguments", "arguments", Argument.Delimiters.none, "representativeUpperBound", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "continuationTypeConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "functionNTypeConstructor", "n", Argument.Delimiters.none, "possiblyErrorTypeConstructorByClassId", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getNameForErrorType", "areEqualTypeConstructors", "c1", "c2", "argumentsCount", "arrayType", "Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "componentType", "asArgumentList", "Lorg/jetbrains/kotlin/types/model/TypeArgumentListMarker;", "asCapturedType", "Lorg/jetbrains/kotlin/types/model/CapturedTypeMarker;", "Lkotlin/Deprecated;", "message", "This call does effectively nothing, please drop it", "level", "Lkotlin/DeprecationLevel;", "ERROR", "asCapturedTypeUnwrappingDnn", "asDefinitelyNotNullType", "Lorg/jetbrains/kotlin/types/model/DefinitelyNotNullTypeMarker;", "asDynamicType", "Lorg/jetbrains/kotlin/types/model/DynamicTypeMarker;", "Lorg/jetbrains/kotlin/types/model/FlexibleTypeMarker;", "asFlexibleType", "asRigidType", "asTypeArgument", "Lorg/jetbrains/kotlin/types/model/TypeArgumentMarker;", "captureFromArguments", ModuleXmlParser.TYPE, "status", "Lorg/jetbrains/kotlin/types/model/CaptureStatus;", "captureFromExpression", "captureStatus", "createEmptySubstitutor", "Lorg/jetbrains/kotlin/types/model/TypeSubstitutorMarker;", "fastCorrespondingSupertypes", "constructor", "get", "index", "getAnnotationFirstArgumentValue", Argument.Delimiters.none, "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getArgument", "getArgumentOrNull", "getArguments", "getAttributes", "Lorg/jetbrains/kotlin/types/model/AnnotationMarker;", "getClassFqNameUnsafe", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "getName", "Lorg/jetbrains/kotlin/name/Name;", "getParameter", "getParameters", "getPrimitiveArrayType", "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "getPrimitiveType", "getRepresentativeUpperBound", "getType", "getTypeConstructor", "getTypeParameterClassifier", "getUnsubstitutedUnderlyingType", "getUpperBound", "getUpperBounds", "getValueClassProperties", "Lkotlin/Pair;", "getVariance", "Lorg/jetbrains/kotlin/types/model/TypeVariance;", "hasAnnotation", "hasFlexibleNullability", "hasRecursiveBounds", "selfConstructor", "identicalArguments", "a", "b", "intersectTypes", "types", Argument.Delimiters.none, "isAnonymous", "isAnyConstructor", "isArrayConstructor", "isArrayOrNullableArray", "isCapturedDynamic", "isCapturedType", "isClassType", "isClassTypeConstructor", "isCommonFinalClassConstructor", "isDefinitelyNotNullType", "isDenotable", "isDynamic", "isError", "isFinalClassOrEnumEntryOrAnnotationClassConstructor", "isFlexible", "isFlexibleNothing", "isFlexibleWithDifferentTypeConstructors", "isInlineClass", "isInnerClass", "isIntegerConstantOperatorTypeConstructor", "isIntegerLiteralConstantTypeConstructor", "isIntegerLiteralType", "isIntegerLiteralTypeConstructor", "isInterface", "isInterfaceOrAnnotationClass", "isIntersection", "isLocalType", "isMarkedNullable", "isMultiFieldValueClass", "isNotNullTypeParameter", "Lorg/jetbrains/kotlin/types/model/ObsoleteTypeKind;", "isNothing", "isNothingConstructor", "isNullableAny", "isNullableNothing", "isNullableType", "considerTypeVariableBounds", "isOldCapturedType", "isPrimitiveType", "isProjectionNotNull", "Lorg/jetbrains/kotlin/builtins/functions/AllowedToUsedOnlyInK1;", "isReified", "isRigidType", "isSingleClassifierType", "isStarProjection", "isStubType", "isStubTypeForBuilderInference", "isStubTypeForVariableInSubtyping", "isTypeParameterTypeConstructor", "isTypeVariableType", "isUnderKotlinPackage", "isUninferredParameter", "iterator", Argument.Delimiters.none, "lowerBound", "lowerBoundIfFlexible", "lowerType", "makeDefinitelyNotNullOrNotNull", "preserveAttributes", "makeNullable", "nullableAnyType", "original", "originalIfDefinitelyNotNullable", "parametersCount", "possibleIntegerTypes", "projection", "Lorg/jetbrains/kotlin/types/model/CapturedTypeConstructorMarker;", "replaceType", "newType", "safeSubstitute", "size", "substitutionSupertypePolicy", "Lorg/jetbrains/kotlin/types/TypeCheckerState$SupertypesPolicy;", "supertypes", "typeConstructor", "typeSubstitutorByTypeConstructor", "map", Argument.Delimiters.none, "typeSubstitutorForUnderlyingType", "unwrapStubTypeVariableConstructor", "upperBound", "upperBoundCount", "upperBoundIfFlexible", "withNewTypeSince", "languageFeature", "withNullability", "nullable", "customSubtypingCallback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "subType", "superType", "Lorg/jetbrains/kotlin/types/model/CustomSubtypingCallback;", "getCustomSubtypingCallback", "()Lkotlin/jvm/functions/Function2;", "typeParameter", "Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;", "getTypeParameter", "(Lorg/jetbrains/kotlin/types/model/TypeVariableTypeConstructorMarker;)Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeSystemCommonBackendContextForTypeMapping implements TypeSystemCommonBackendContext, TypeSystemCommonBackendContextForTypeMapping {
    private final ConeTypeContext context;
    private final FirSession session;
    private final FirSymbolProvider symbolProvider;
    private final Function1<String, String> unresolvedQualifierRemapper;

    /* JADX WARN: Multi-variable type inference failed */
    public ConeTypeSystemCommonBackendContextForTypeMapping(ConeTypeContext coneTypeContext, Function1<? super String, String> function1) {
        coneTypeContext.getClass();
        function1.getClass();
        this.context = coneTypeContext;
        this.unresolvedQualifierRemapper = function1;
        FirSession session = coneTypeContext.getSession();
        this.session = session;
        this.symbolProvider = FirSymbolProviderKt.getSymbolProvider(session);
    }

    private final ConeClassLikeLookupTag possiblyErrorTypeConstructorByClassId(ClassId classId) {
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> classLikeSymbolByClassId = this.symbolProvider.getClassLikeSymbolByClassId(classId);
        return (classLikeSymbolByClassId == null || (lookupTag = classLikeSymbolByClassId.getLookupTag()) == null) ? new ConeClassLikeErrorLookupTag(classId, new ConeUnresolvedSymbolError(classId), null, 4, null) : lookupTag;
    }

    public boolean areEqualTypeConstructors(TypeConstructorMarker c1, TypeConstructorMarker c2) {
        c1.getClass();
        c2.getClass();
        return this.context.areEqualTypeConstructors(c1, c2);
    }

    public int argumentsCount(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.argumentsCount(kotlinTypeMarker);
    }

    public SimpleTypeMarker arrayType(KotlinTypeMarker componentType) {
        componentType.getClass();
        return this.context.m664arrayType(componentType);
    }

    public TypeArgumentListMarker asArgumentList(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.m665asArgumentList(rigidTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public CapturedTypeMarker asCapturedType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.context.asCapturedType(capturedTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public CapturedTypeMarker asCapturedTypeUnwrappingDnn(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.context.asCapturedTypeUnwrappingDnn(capturedTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        definitelyNotNullTypeMarker.getClass();
        return this.context.asDefinitelyNotNullType(definitelyNotNullTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public DynamicTypeMarker asDynamicType(DynamicTypeMarker dynamicTypeMarker) {
        dynamicTypeMarker.getClass();
        return this.context.asDynamicType(dynamicTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public FlexibleTypeMarker asFlexibleType(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.context.asFlexibleType(flexibleTypeMarker);
    }

    public RigidTypeMarker asRigidType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.m670asRigidType(kotlinTypeMarker);
    }

    public TypeArgumentMarker asTypeArgument(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.m671asTypeArgument(kotlinTypeMarker);
    }

    public TypeParameterMarker asTypeParameter(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (isTypeParameter(typeConstructorMarker)) {
            return (ConeTypeParameterLookupTag) typeConstructorMarker;
        }
        w01.a("Failed requirement.");
        return null;
    }

    public RigidTypeMarker captureFromArguments(RigidTypeMarker type, CaptureStatus status) {
        type.getClass();
        status.getClass();
        return this.context.m672captureFromArguments(type, status);
    }

    public KotlinTypeMarker captureFromExpression(KotlinTypeMarker type) {
        type.getClass();
        return this.context.m673captureFromExpression(type);
    }

    public CaptureStatus captureStatus(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.context.captureStatus(capturedTypeMarker);
    }

    /* JADX INFO: renamed from: continuationTypeConstructor, reason: merged with bridge method [inline-methods] */
    public ConeClassLikeLookupTag m251continuationTypeConstructor() {
        return possiblyErrorTypeConstructorByClassId(StandardClassIds.INSTANCE.getContinuation());
    }

    public TypeSubstitutorMarker createEmptySubstitutor() {
        return this.context.createEmptySubstitutor();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: defaultType, reason: merged with bridge method [inline-methods] */
    public ConeSimpleKotlinType m252defaultType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        if (!(typeConstructorMarker instanceof ConeClassifierLookupTag)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeClassifierLookupTag coneClassifierLookupTag = (ConeClassifierLookupTag) typeConstructorMarker;
        if (coneClassifierLookupTag instanceof ConeTypeParameterLookupTag) {
            return new ConeTypeParameterTypeImpl((ConeTypeParameterLookupTag) typeConstructorMarker, false, null, 4, null);
        }
        if (!(coneClassifierLookupTag instanceof ConeClassLikeLookupTag)) {
            w04.a("Unsupported type constructor: ", typeConstructorMarker);
            return null;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTag = (ConeClassLikeLookupTag) typeConstructorMarker;
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(coneClassLikeLookupTag, this.session);
        return classSymbol == null ? new ConeErrorType(new ConeUnresolvedSymbolError(coneClassLikeLookupTag.getClassId()), false, null, null, null, null, null, 126, null) : ScopeUtilsKt.defaultType((FirClassLikeDeclaration) classSymbol.getFir());
    }

    public List<SimpleTypeMarker> fastCorrespondingSupertypes(RigidTypeMarker rigidTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        rigidTypeMarker.getClass();
        typeConstructorMarker.getClass();
        return this.context.fastCorrespondingSupertypes(rigidTypeMarker, typeConstructorMarker);
    }

    public TypeConstructorMarker functionNTypeConstructor(int n) {
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> classLikeSymbolByClassId = this.symbolProvider.getClassLikeSymbolByClassId(StandardClassIds.INSTANCE.FunctionN(n));
        if (classLikeSymbolByClassId != null && (lookupTag = classLikeSymbolByClassId.getLookupTag()) != null) {
            return lookupTag;
        }
        l0i.a("Function", n, " class not found");
        return null;
    }

    public TypeArgumentMarker get(TypeArgumentListMarker typeArgumentListMarker, int i) {
        typeArgumentListMarker.getClass();
        return this.context.get(typeArgumentListMarker, i);
    }

    public Object getAnnotationFirstArgumentValue(KotlinTypeMarker kotlinTypeMarker, FqName fqName) {
        kotlinTypeMarker.getClass();
        fqName.getClass();
        return this.context.getAnnotationFirstArgumentValue(kotlinTypeMarker, fqName);
    }

    public TypeArgumentMarker getArgument(KotlinTypeMarker kotlinTypeMarker, int i) {
        kotlinTypeMarker.getClass();
        return this.context.m674getArgument(kotlinTypeMarker, i);
    }

    public TypeArgumentMarker getArgumentOrNull(RigidTypeMarker rigidTypeMarker, int i) {
        rigidTypeMarker.getClass();
        return this.context.getArgumentOrNull(rigidTypeMarker, i);
    }

    public List<TypeArgumentMarker> getArguments(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.getArguments(kotlinTypeMarker);
    }

    public List<AnnotationMarker> getAttributes(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.getAttributes(kotlinTypeMarker);
    }

    public FqNameUnsafe getClassFqNameUnsafe(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.getClassFqNameUnsafe(typeConstructorMarker);
    }

    public final ConeTypeContext getContext() {
        return this.context;
    }

    public Function2<KotlinTypeMarker, KotlinTypeMarker, Boolean> getCustomSubtypingCallback() {
        return this.context.getCustomSubtypingCallback();
    }

    public Name getName(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.context.getName(typeParameterMarker);
    }

    public String getNameForErrorType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (!(kotlinTypeMarker instanceof ConeErrorType)) {
            w01.a("Failed requirement.");
            return null;
        }
        ConeDiagnostic diagnostic = ((ConeErrorType) kotlinTypeMarker).getDiagnostic();
        String qualifier = diagnostic instanceof ConeUnresolvedTypeQualifierError ? ((ConeUnresolvedTypeQualifierError) diagnostic).getQualifier() : null;
        if (qualifier == null) {
            return null;
        }
        String str = (String) this.unresolvedQualifierRemapper.invoke(qualifier);
        return str == null ? qualifier : str;
    }

    public TypeParameterMarker getParameter(TypeConstructorMarker typeConstructorMarker, int i) {
        typeConstructorMarker.getClass();
        return this.context.m675getParameter(typeConstructorMarker, i);
    }

    public List<TypeParameterMarker> getParameters(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.getParameters(typeConstructorMarker);
    }

    public PrimitiveType getPrimitiveArrayType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.getPrimitiveArrayType(typeConstructorMarker);
    }

    public PrimitiveType getPrimitiveType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.getPrimitiveType(typeConstructorMarker);
    }

    public KotlinTypeMarker getRepresentativeUpperBound(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.context.m676getRepresentativeUpperBound(typeParameterMarker);
    }

    public KotlinTypeMarker getType(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return this.context.m677getType(typeArgumentMarker);
    }

    public TypeConstructorMarker getTypeConstructor(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.context.m678getTypeConstructor(typeParameterMarker);
    }

    public TypeParameterMarker getTypeParameter(TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker) {
        typeVariableTypeConstructorMarker.getClass();
        return this.context.m679getTypeParameter(typeVariableTypeConstructorMarker);
    }

    public TypeParameterMarker getTypeParameterClassifier(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.m680getTypeParameterClassifier(typeConstructorMarker);
    }

    public final Function1<String, String> getUnresolvedQualifierRemapper() {
        return this.unresolvedQualifierRemapper;
    }

    public KotlinTypeMarker getUnsubstitutedUnderlyingType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.m681getUnsubstitutedUnderlyingType(kotlinTypeMarker);
    }

    public KotlinTypeMarker getUpperBound(TypeParameterMarker typeParameterMarker, int i) {
        typeParameterMarker.getClass();
        return this.context.m682getUpperBound(typeParameterMarker, i);
    }

    public List<KotlinTypeMarker> getUpperBounds(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.context.getUpperBounds(typeParameterMarker);
    }

    public List<Pair<Name, RigidTypeMarker>> getValueClassProperties(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.getValueClassProperties(typeConstructorMarker);
    }

    public TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return this.context.getVariance(typeArgumentMarker);
    }

    public boolean hasAnnotation(KotlinTypeMarker kotlinTypeMarker, FqName fqName) {
        kotlinTypeMarker.getClass();
        fqName.getClass();
        return this.context.hasAnnotation(kotlinTypeMarker, fqName);
    }

    public boolean hasFlexibleNullability(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.hasFlexibleNullability(kotlinTypeMarker);
    }

    public boolean hasRecursiveBounds(TypeParameterMarker typeParameterMarker, TypeConstructorMarker typeConstructorMarker) {
        typeParameterMarker.getClass();
        return this.context.hasRecursiveBounds(typeParameterMarker, typeConstructorMarker);
    }

    public boolean identicalArguments(RigidTypeMarker a, RigidTypeMarker b) {
        a.getClass();
        b.getClass();
        return this.context.identicalArguments(a, b);
    }

    public KotlinTypeMarker intersectTypes(Collection<? extends KotlinTypeMarker> types) {
        types.getClass();
        return this.context.intersectTypes(types);
    }

    public boolean isAnonymous(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isAnonymous(typeConstructorMarker);
    }

    public boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isAnyConstructor(typeConstructorMarker);
    }

    public boolean isArrayConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isArrayConstructor(typeConstructorMarker);
    }

    public boolean isArrayOrNullableArray(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isArrayOrNullableArray(kotlinTypeMarker);
    }

    public boolean isCapturedDynamic(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isCapturedDynamic(kotlinTypeMarker);
    }

    public boolean isCapturedType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isCapturedType(kotlinTypeMarker);
    }

    public boolean isClassType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isClassType(rigidTypeMarker);
    }

    public boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isClassTypeConstructor(typeConstructorMarker);
    }

    public boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isCommonFinalClassConstructor(typeConstructorMarker);
    }

    public boolean isDefinitelyNotNullType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isDefinitelyNotNullType(kotlinTypeMarker);
    }

    public boolean isDenotable(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isDenotable(typeConstructorMarker);
    }

    public boolean isDynamic(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isDynamic(kotlinTypeMarker);
    }

    public boolean isError(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isError(kotlinTypeMarker);
    }

    public boolean isFinalClassOrEnumEntryOrAnnotationClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isFinalClassOrEnumEntryOrAnnotationClassConstructor(typeConstructorMarker);
    }

    public boolean isFlexible(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isFlexible(kotlinTypeMarker);
    }

    public boolean isFlexibleNothing(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isFlexibleNothing(kotlinTypeMarker);
    }

    public boolean isFlexibleWithDifferentTypeConstructors(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isFlexibleWithDifferentTypeConstructors(kotlinTypeMarker);
    }

    public boolean isInlineClass(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isInlineClass(typeConstructorMarker);
    }

    public boolean isInnerClass(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isInnerClass(typeConstructorMarker);
    }

    public boolean isIntegerConstantOperatorTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isIntegerConstantOperatorTypeConstructor(typeConstructorMarker);
    }

    public boolean isIntegerLiteralConstantTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isIntegerLiteralConstantTypeConstructor(typeConstructorMarker);
    }

    public boolean isIntegerLiteralType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isIntegerLiteralType(rigidTypeMarker);
    }

    public boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isIntegerLiteralTypeConstructor(typeConstructorMarker);
    }

    public boolean isInterface(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isInterface(typeConstructorMarker);
    }

    public boolean isInterfaceOrAnnotationClass(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isInterfaceOrAnnotationClass(kotlinTypeMarker);
    }

    public boolean isIntersection(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isIntersection(typeConstructorMarker);
    }

    public boolean isKClass(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeRigidType) {
            return InferenceUtilsKt.isKClassType((ConeKotlinType) rigidTypeMarker);
        }
        w01.a("Failed requirement.");
        return false;
    }

    public boolean isLocalType(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isLocalType(typeConstructorMarker);
    }

    public boolean isMarkedNullable(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isMarkedNullable(kotlinTypeMarker);
    }

    public boolean isMultiFieldValueClass(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isMultiFieldValueClass(typeConstructorMarker);
    }

    @ObsoleteTypeKind
    public boolean isNotNullTypeParameter(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isNotNullTypeParameter(kotlinTypeMarker);
    }

    public boolean isNothing(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isNothing(kotlinTypeMarker);
    }

    public boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isNothingConstructor(typeConstructorMarker);
    }

    public boolean isNullableAny(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isNullableAny(kotlinTypeMarker);
    }

    public boolean isNullableNothing(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isNullableNothing(kotlinTypeMarker);
    }

    public boolean isNullableType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isNullableType(kotlinTypeMarker);
    }

    public boolean isOldCapturedType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.context.isOldCapturedType(capturedTypeMarker);
    }

    public boolean isPrimitiveType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isPrimitiveType(rigidTypeMarker);
    }

    @AllowedToUsedOnlyInK1
    public boolean isProjectionNotNull(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.context.isProjectionNotNull(capturedTypeMarker);
    }

    public boolean isRawType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return kotlinTypeMarker instanceof ConeRawType;
    }

    public boolean isReified(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.context.isReified(typeParameterMarker);
    }

    public boolean isRigidType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isRigidType(kotlinTypeMarker);
    }

    public boolean isScript(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return false;
    }

    public boolean isSingleClassifierType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isSingleClassifierType(rigidTypeMarker);
    }

    public boolean isStarProjection(TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        return this.context.isStarProjection(typeArgumentMarker);
    }

    public boolean isStubType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isStubType(rigidTypeMarker);
    }

    public boolean isStubTypeForBuilderInference(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isStubTypeForBuilderInference(rigidTypeMarker);
    }

    public boolean isStubTypeForVariableInSubtyping(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isStubTypeForVariableInSubtyping(rigidTypeMarker);
    }

    public boolean isSuspendFunction(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        if (rigidTypeMarker instanceof ConeRigidType) {
            return FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType((ConeKotlinType) rigidTypeMarker, this.session);
        }
        w01.a("Failed requirement.");
        return false;
    }

    public boolean isTypeParameter(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return typeConstructorMarker instanceof ConeTypeParameterLookupTag;
    }

    public boolean isTypeParameterTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isTypeParameterTypeConstructor(typeConstructorMarker);
    }

    public boolean isTypeVariableType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isTypeVariableType(kotlinTypeMarker);
    }

    public boolean isUnderKotlinPackage(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isUnderKotlinPackage(typeConstructorMarker);
    }

    public boolean isUninferredParameter(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.isUninferredParameter(kotlinTypeMarker);
    }

    public Iterator<TypeArgumentMarker> iterator(TypeArgumentListMarker typeArgumentListMarker) {
        typeArgumentListMarker.getClass();
        return this.context.iterator(typeArgumentListMarker);
    }

    public RigidTypeMarker lowerBound(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.context.m686lowerBound(flexibleTypeMarker);
    }

    public RigidTypeMarker lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.lowerBoundIfFlexible(kotlinTypeMarker);
    }

    public KotlinTypeMarker lowerType(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.context.m687lowerType(capturedTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public DefinitelyNotNullTypeMarker makeDefinitelyNotNullOrNotNull(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        definitelyNotNullTypeMarker.getClass();
        return this.context.makeDefinitelyNotNullOrNotNull(definitelyNotNullTypeMarker);
    }

    public KotlinTypeMarker makeNullable(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.makeNullable(kotlinTypeMarker);
    }

    public SimpleTypeMarker nullableAnyType() {
        return this.context.mo649nullableAnyType();
    }

    public SimpleTypeMarker original(DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker) {
        definitelyNotNullTypeMarker.getClass();
        return this.context.original(definitelyNotNullTypeMarker);
    }

    public SimpleTypeMarker originalIfDefinitelyNotNullable(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.originalIfDefinitelyNotNullable(rigidTypeMarker);
    }

    public int parametersCount(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.parametersCount(typeConstructorMarker);
    }

    public Collection<KotlinTypeMarker> possibleIntegerTypes(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.possibleIntegerTypes(rigidTypeMarker);
    }

    public TypeArgumentMarker projection(CapturedTypeConstructorMarker capturedTypeConstructorMarker) {
        capturedTypeConstructorMarker.getClass();
        return this.context.m688projection(capturedTypeConstructorMarker);
    }

    public TypeArgumentMarker replaceType(TypeArgumentMarker typeArgumentMarker, KotlinTypeMarker kotlinTypeMarker) {
        typeArgumentMarker.getClass();
        kotlinTypeMarker.getClass();
        return this.context.m689replaceType(typeArgumentMarker, kotlinTypeMarker);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: representativeUpperBound, reason: merged with bridge method [inline-methods] */
    public ConeKotlinType m254representativeUpperBound(TypeParameterMarker typeParameterMarker) {
        ClassKind classKind;
        typeParameterMarker.getClass();
        Object obj = null;
        if (!(typeParameterMarker instanceof ConeTypeParameterLookupTag)) {
            w01.a("Failed requirement.");
            return null;
        }
        List<FirResolvedTypeRef> resolvedBounds = ((ConeTypeParameterLookupTag) typeParameterMarker).getTypeParameterSymbol().getResolvedBounds();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
        }
        for (Object obj2 : arrayList) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((ConeKotlinType) obj2, this.session);
            if (regularClassSymbol != null && (classKind = ((FirRegularClass) regularClassSymbol.getFir()).getClassKind()) != ClassKind.INTERFACE && classKind != ClassKind.ANNOTATION_CLASS) {
                obj = obj2;
                break;
            }
        }
        ConeKotlinType coneKotlinType = (ConeKotlinType) obj;
        return coneKotlinType == null ? (ConeKotlinType) CollectionsKt.first(arrayList) : coneKotlinType;
    }

    public KotlinTypeMarker safeSubstitute(TypeSubstitutorMarker typeSubstitutorMarker, KotlinTypeMarker kotlinTypeMarker) {
        typeSubstitutorMarker.getClass();
        kotlinTypeMarker.getClass();
        return this.context.safeSubstitute(typeSubstitutorMarker, kotlinTypeMarker);
    }

    public int size(TypeArgumentListMarker typeArgumentListMarker) {
        typeArgumentListMarker.getClass();
        return this.context.size(typeArgumentListMarker);
    }

    public TypeCheckerState.SupertypesPolicy substitutionSupertypePolicy(RigidTypeMarker type) {
        type.getClass();
        return this.context.substitutionSupertypePolicy(type);
    }

    public Collection<KotlinTypeMarker> supertypes(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.supertypes(typeConstructorMarker);
    }

    public CapturedTypeConstructorMarker typeConstructor(CapturedTypeMarker capturedTypeMarker) {
        capturedTypeMarker.getClass();
        return this.context.m690typeConstructor(capturedTypeMarker);
    }

    public TypeSubstitutorMarker typeSubstitutorByTypeConstructor(Map<TypeConstructorMarker, ? extends KotlinTypeMarker> map) {
        map.getClass();
        return this.context.typeSubstitutorByTypeConstructor(map);
    }

    public TypeSubstitutorMarker typeSubstitutorForUnderlyingType(Map<TypeConstructorMarker, ? extends KotlinTypeMarker> map) {
        map.getClass();
        return this.context.typeSubstitutorForUnderlyingType(map);
    }

    public ConeSimpleKotlinType typeWithArguments(TypeConstructorMarker typeConstructorMarker, List<? extends KotlinTypeMarker> list) {
        typeConstructorMarker.getClass();
        list.getClass();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!(((KotlinTypeMarker) it.next()) instanceof ConeKotlinType)) {
                w01.a("Failed requirement.");
                return null;
            }
        }
        return (ConeSimpleKotlinType) TypeUtilsKt.withArguments(m252defaultType(typeConstructorMarker), (ConeTypeProjection[]) list.toArray(new ConeKotlinType[0]));
    }

    public TypeConstructorMarker unwrapStubTypeVariableConstructor(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.m692unwrapStubTypeVariableConstructor(typeConstructorMarker);
    }

    public RigidTypeMarker upperBound(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.context.m693upperBound(flexibleTypeMarker);
    }

    public int upperBoundCount(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.context.upperBoundCount(typeParameterMarker);
    }

    public RigidTypeMarker upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.upperBoundIfFlexible(kotlinTypeMarker);
    }

    public KotlinTypeMarker withNewTypeSince(KotlinTypeMarker kotlinTypeMarker, Object obj, KotlinTypeMarker kotlinTypeMarker2) {
        kotlinTypeMarker.getClass();
        obj.getClass();
        kotlinTypeMarker2.getClass();
        return this.context.m694withNewTypeSince(kotlinTypeMarker, obj, kotlinTypeMarker2);
    }

    public KotlinTypeMarker withNullability(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        return this.context.withNullability(kotlinTypeMarker, z);
    }

    public CapturedTypeMarker asCapturedType(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        return this.context.m666asCapturedType(simpleTypeMarker);
    }

    public CapturedTypeMarker asCapturedTypeUnwrappingDnn(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.asCapturedTypeUnwrappingDnn(rigidTypeMarker);
    }

    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.m667asDefinitelyNotNullType(rigidTypeMarker);
    }

    public DynamicTypeMarker asDynamicType(FlexibleTypeMarker flexibleTypeMarker) {
        flexibleTypeMarker.getClass();
        return this.context.m668asDynamicType(flexibleTypeMarker);
    }

    public FlexibleTypeMarker asFlexibleType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.m669asFlexibleType(kotlinTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public RigidTypeMarker asRigidType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.asRigidType(rigidTypeMarker);
    }

    public TypeVariance getVariance(TypeParameterMarker typeParameterMarker) {
        typeParameterMarker.getClass();
        return this.context.getVariance(typeParameterMarker);
    }

    /* JADX INFO: renamed from: intersectTypes, reason: collision with other method in class */
    public SimpleTypeMarker m253intersectTypes(Collection<? extends SimpleTypeMarker> types) {
        types.getClass();
        return this.context.m683intersectTypes(types);
    }

    public boolean isDefinitelyNotNullType(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.isDefinitelyNotNullType(rigidTypeMarker);
    }

    public boolean isError(TypeConstructorMarker typeConstructorMarker) {
        typeConstructorMarker.getClass();
        return this.context.isError(typeConstructorMarker);
    }

    public boolean isNullableType(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        return this.context.isNullableType(kotlinTypeMarker, z);
    }

    public boolean isPrimitiveType(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        return this.context.isPrimitiveType(simpleTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public RigidTypeMarker lowerBoundIfFlexible(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.lowerBoundIfFlexible(rigidTypeMarker);
    }

    public KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.makeDefinitelyNotNullOrNotNull(kotlinTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public SimpleTypeMarker originalIfDefinitelyNotNullable(SimpleTypeMarker simpleTypeMarker) {
        simpleTypeMarker.getClass();
        return this.context.originalIfDefinitelyNotNullable(simpleTypeMarker);
    }

    public TypeConstructorMarker typeConstructor(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return this.context.typeConstructor(kotlinTypeMarker);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This call does effectively nothing, please drop it")
    public RigidTypeMarker upperBoundIfFlexible(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.upperBoundIfFlexible(rigidTypeMarker);
    }

    public RigidTypeMarker withNullability(RigidTypeMarker rigidTypeMarker, boolean z) {
        rigidTypeMarker.getClass();
        return this.context.m695withNullability(rigidTypeMarker, z);
    }

    public KotlinTypeMarker makeDefinitelyNotNullOrNotNull(KotlinTypeMarker kotlinTypeMarker, boolean z) {
        kotlinTypeMarker.getClass();
        return this.context.makeDefinitelyNotNullOrNotNull(kotlinTypeMarker, z);
    }

    public TypeConstructorMarker typeConstructor(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.m691typeConstructor(rigidTypeMarker);
    }

    public RigidTypeMarker makeDefinitelyNotNullOrNotNull(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return this.context.makeDefinitelyNotNullOrNotNull(rigidTypeMarker);
    }

    /* JADX INFO: renamed from: typeWithArguments, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ SimpleTypeMarker m255typeWithArguments(TypeConstructorMarker typeConstructorMarker, List list) {
        return typeWithArguments(typeConstructorMarker, (List<? extends KotlinTypeMarker>) list);
    }
}
