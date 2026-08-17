package org.jetbrains.kotlin.fir.types;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeRecursiveTypeParameterDuringErasureError;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.resolve.substitution.SubstitutionUtilitiesKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.NewCommonSuperTypeCalculator;
import org.jetbrains.kotlin.types.AbstractNullabilityChecker;
import org.jetbrains.kotlin.types.AbstractStrictEqualityTypeChecker;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.CaptureStatus;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.MarkerExtensionsKt;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContext;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000º\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u001a\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u001a\u001a\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001\u001a\u001c\u0010\u000b\u001a\u00020\u0007*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002\u001a&\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u001a\u001c\u0010\u0010\u001a\u00020\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u001a&\u0010\u001b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u0007\u001a)\u0010\u001b\u001a\u00020\u001d*\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u0007H\u0086\b\u001a)\u0010\u001e\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u0002H\u001f2\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\"0!¢\u0006\u0002\u0010#\u001a3\u0010\u001e\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u0002H\u001f2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"0%H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010&\u001a!\u0010'\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u0002H\u001f2\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010(\u001a\u0019\u0010)\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u0002H\u001f¢\u0006\u0002\u0010*\u001a!\u0010+\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u0002H\u001f2\u0006\u0010,\u001a\u00020-¢\u0006\u0002\u0010.\u001a)\u0010/\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u0002H\u001f2\u0006\u00100\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\f¢\u0006\u0002\u00101\u001a=\u00102\u001a\u0002H\u001f\"\b\b\u0000\u0010\u001f*\u00020\u0001*\u0002H\u001f2\u0006\u00103\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u0007¢\u0006\u0002\u00104\u001a:\u00105\u001a\u0004\u0018\u00010\u0001*\u0002062\u0006\u0010\u0014\u001a\u00020\f2\b\b\u0002\u00107\u001a\u00020\u00072\u0014\u00108\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010%H\u0086\bø\u0001\u0000\u001a8\u00109\u001a\u00020\u0001*\u0002062\u0006\u0010\u0014\u001a\u00020\f2\b\b\u0002\u00107\u001a\u00020\u00072\u0014\u00108\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u00010%H\u0086\bø\u0001\u0000\u001a&\u0010:\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u00012\u0006\u0010<\u001a\u00020\u00012\u0006\u0010=\u001a\u00020\u0007\u001a\u0012\u0010>\u001a\u000206*\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\f\u001a\u0012\u0010?\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018\u001a\u0012\u0010?\u001a\u00020\u0007*\u00020@2\u0006\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010A\u001a\u00020\u0007*\u00020@\u001a\n\u0010B\u001a\u00020C*\u00020@\u001a\u0014\u0010D\u001a\u00020@*\u00020@2\b\u0010E\u001a\u0004\u0018\u00010\u0001\u001a \u0010F\u001a\u00020C*\u00020@2\b\u0010E\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010G\u001a\u0004\u0018\u00010H\u001a\u001c\u0010I\u001a\u00020C*\u00020C2\b\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010E\u001a\u00020\u0001\u001a\u0018\u0010L\u001a\u00020\u00072\b\u0010M\u001a\u0004\u0018\u00010N2\u0006\u0010O\u001a\u00020\u0007\u001a\u0018\u0010P\u001a\u00020N*\u00020Q2\f\u0010R\u001a\b\u0012\u0002\b\u0003\u0018\u00010S\u001a\u001c\u0010T\u001a\u0004\u0018\u00010\u001d*\u00020\f2\u0006\u0010\r\u001a\u00020\u001d2\u0006\u0010U\u001a\u00020V\u001a'\u0010W\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010!*\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010U\u001a\u00020V¢\u0006\u0002\u0010X\u001a\u0016\u0010Y\u001a\u0004\u0018\u00010\u0001*\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\u0000\u001a\u0016\u0010Z\u001a\u0004\u0018\u00010[*\u00020\f2\u0006\u0010\r\u001a\u00020[H\u0002\u001a\u001c\u0010\\\u001a\n\u0012\u0004\u0012\u00020]\u0018\u00010\u0004*\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a$\u0010^\u001a\u00020\u0007*\u00020\u00012\u0006\u0010_\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010`\u001a\u00020\u0007\u001a$\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\u0006\u00100\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010`\u001a\u00020\u0007\u001a\u001a\u0010^\u001a\u00020\u0007*\u00020a2\u0006\u0010b\u001a\u00020a2\u0006\u0010c\u001a\u00020d\u001a\u0012\u0010e\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018\u001a\u0014\u0010f\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a\u001c\u0010g\u001a\u00020\u0007*\u00020\u00012\u0006\u0010h\u001a\u00020i2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a\u001c\u0010^\u001a\u00020\u0007*\u00020j2\u0006\u0010k\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010j\u001a$\u0010l\u001a\u000e\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u00020\u00010m*\b\u0012\u0004\u0012\u00020i0\u00042\u0006\u0010\u0017\u001a\u00020\u0018\u001a-\u0010n\u001a\b\u0012\u0004\u0012\u00020\u00010!*\b\u0012\u0004\u0012\u00020i0\u00042\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010o\u001a\u0004\u0018\u00010p¢\u0006\u0002\u0010q\u001a\u001a\u0010r\u001a\u00020\u0001*\u00020i2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010s\u001a\u00020\u0007\u001a0\u0010r\u001a\u00020\u0001*\u00020i2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020\u00010u2\u0006\u0010s\u001a\u00020\u0007H\u0002\u001a0\u0010w\u001a\u00020\u0001*\u00020v2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020\u00010u2\u0006\u0010x\u001a\u00020yH\u0002\u001a0\u0010z\u001a\u00020\u0001*\u00020{2\u0006\u0010\u0014\u001a\u00020\u00022\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020\u00010u2\u0006\u0010x\u001a\u00020yH\u0002\u001a0\u0010|\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020\u00010u2\u0006\u0010x\u001a\u00020yH\u0002\u001a\n\u0010}\u001a\u00020\u0007*\u00020\u0001\u001a\n\u0010~\u001a\u00020\u0001*\u00020\u0001\u001a/\u0010\u007f\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\t\b\u0002\u0010\u0080\u0001\u001a\u00020\u00072\u0010\b\u0002\u0010\u0081\u0001\u001a\t\u0012\u0004\u0012\u00020\u00010\u0082\u0001\u001a\u0015\u0010\u0083\u0001\u001a\u00020\u0007*\u00020i2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a\u0014\u0010\u0084\u0001\u001a\u00020\u0007*\u00030\u0085\u00012\u0006\u0010\u0017\u001a\u00020\u0018\u001a\u000b\u0010\u0088\u0001\u001a\u00020\u0007*\u00020\u0001\u001a\f\u0010\u008b\u0001\u001a\u00020\u0007*\u00030\u008c\u0001\u001aC\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u00012\b\u0010\u008f\u0001\u001a\u00030\u008e\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u001f\u0010\u0090\u0001\u001a\u001a\u0012\t\u0012\u0007\u0012\u0002\b\u00030\u0091\u0001\u0012\u000b\u0012\t\u0012\u0002\b\u0003\u0018\u00010\u0091\u00010%H\u0086\bø\u0001\u0000\u001a\u0016\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0093\u0001*\u00020@2\u0006\u0010\u0017\u001a\u00020\u0018\"\u0019\u0010\u0086\u0001\u001a\u00020\u0007*\u00030\u0085\u00018F¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001\"\u0018\u0010\u0089\u0001\u001a\u00020\u0007*\u00020\u00018F¢\u0006\b\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0094\u0001"}, d2 = {"commonSuperTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "types", Argument.Delimiters.none, "intersectTypesOrNull", "equalTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", "a", "b", "makesSenseToBeDefinitelyNotNull", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "avoidComprehensiveCheck", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType;", "Lorg/jetbrains/kotlin/fir/types/ConeDefinitelyNotNullType$Companion;", "original", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeDynamicType;", "Lorg/jetbrains/kotlin/fir/types/ConeDynamicType$Companion;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "attributes", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "makeConeTypeDefinitelyNotNullOrNotNull", "preserveAttributes", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "withArguments", "T", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "replacement", "Lkotlin/Function1;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "withAttributes", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeAttributes;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "removeAnnotations", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "withAbbreviation", "attribute", "Lorg/jetbrains/kotlin/fir/types/AbbreviatedTypeAttribute;", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/AbbreviatedTypeAttribute;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "withNullabilityOf", "otherType", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "withNullability", "nullable", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;ZLorg/jetbrains/kotlin/fir/types/ConeTypeContext;Lorg/jetbrains/kotlin/fir/types/ConeAttributes;Z)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "mapTypesOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "dropIdentity", "f", "mapTypesOrSelf", "coneFlexibleOrSimpleType", "lowerBound", "upperBound", "isTrivial", "toTrivialFlexibleType", "isExtensionFunctionType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "hasEnhancedNullability", "withoutEnhancedNullability", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "withReplacedReturnType", "newType", "withReplacedConeType", "firFakeSourceElementKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "withReplacedSourceAndType", "newSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "shouldApproximateLocalTypesOfNonLocalDeclaration", "containingCallableVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "isInlineFunction", "visibilityForApproximation", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "container", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "captureFromArgumentsInternal", "status", "Lorg/jetbrains/kotlin/types/model/CaptureStatus;", "captureArguments", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/types/model/CaptureStatus;)[Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "captureFromExpressionInternal", "captureCapturedType", "Lorg/jetbrains/kotlin/fir/types/ConeCapturedType;", "captureArgumentsForIntersectionType", "Lorg/jetbrains/kotlin/fir/types/CapturedArguments;", "isSubtypeOf", "superType", "errorTypesEqualToAnything", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "other", "typeCheckerContext", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "canHaveSubtypesAccordingToK1", "hasSubtypesAboveNothingAccordingToK1", "hasSupertypesBelowParameterBoundsAccordingToK1", "typeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "context", "eraseToUpperBoundsAssociated", Argument.Delimiters.none, "getProjectionsForRawType", "nullabilities", Argument.Delimiters.none, "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirSession;[Z)[Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getProjectionForRawType", "makeNullable", "cache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "eraseToUpperBound", "mode", "Lorg/jetbrains/kotlin/fir/types/EraseUpperBoundMode;", "eraseArgumentsDeeply", "Lorg/jetbrains/kotlin/types/model/SimpleTypeMarker;", "eraseAsUpperBound", "isRaw", "convertToNonRawVersion", "canBeNull", "considerTypeVariableBounds", "visited", Argument.Delimiters.none, "allBoundsAreNullableOrUnresolved", "isLeftValidForDefinitelyNotNullable", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "isRightValidForDefinitelyNotNullable", "(Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;)Z", "isKCallableType", "isUnitOrFlexibleUnit", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "isAnonymousClass", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "outerType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "classLikeType", "outerClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "toRegularClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.INVARIANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean allBoundsAreNullableOrUnresolved(FirTypeParameterSymbol firTypeParameterSymbol, FirSession firSession) {
        Iterator<FirTypeRef> it = ((FirTypeParameter) firTypeParameterSymbol.getFir()).getBounds().iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) it.next();
            if (!(firResolvedTypeRef instanceof FirResolvedTypeRef)) {
                return true;
            }
            FirSession firSession2 = firSession;
            if (!canBeNull$default(firResolvedTypeRef.getConeType(), firSession2, false, null, 6, null)) {
                return false;
            }
            firSession = firSession2;
        }
        return true;
    }

    public static final boolean canBeNull(ConeKotlinType coneKotlinType, FirSession firSession, boolean z, Set<ConeKotlinType> set) {
        coneKotlinType.getClass();
        firSession.getClass();
        set.getClass();
        if (!set.add(coneKotlinType)) {
            return false;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return canBeNull(((ConeFlexibleType) coneKotlinType).getUpperBound(), firSession, z, set);
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return false;
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            ConeTypeParameterType coneTypeParameterType = (ConeTypeParameterType) coneKotlinType;
            if (!coneTypeParameterType.getIsMarkedNullable()) {
                List<FirResolvedTypeRef> resolvedBounds = coneTypeParameterType.getLookupTag().getTypeParameterSymbol().getResolvedBounds();
                if (!(resolvedBounds instanceof Collection) || !resolvedBounds.isEmpty()) {
                    Iterator<T> it = resolvedBounds.iterator();
                    while (it.hasNext()) {
                        if (!canBeNull(((FirResolvedTypeRef) it.next()).getConeType(), firSession, z, set)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        if (coneKotlinType instanceof ConeStubType) {
            ConeStubType coneStubType = (ConeStubType) coneKotlinType;
            return coneStubType.getIsMarkedNullable() || canBeNull(coneStubType.getConstructor().getVariable().getDefaultType(), firSession, z, set);
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
                return true;
            }
            Iterator<T> it2 = intersectedTypes.iterator();
            while (it2.hasNext()) {
                if (!canBeNull((ConeKotlinType) it2.next(), firSession, z, set)) {
                    return false;
                }
            }
            return true;
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            ConeCapturedType coneCapturedType = (ConeCapturedType) coneKotlinType;
            if (!coneCapturedType.isMarkedNullable()) {
                List<ConeKotlinType> supertypes = coneCapturedType.getConstructor().getSupertypes();
                if (supertypes != null) {
                    List<ConeKotlinType> list = supertypes;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it3 = list.iterator();
                        while (it3.hasNext()) {
                            if (!canBeNull((ConeKotlinType) it3.next(), firSession, z, set)) {
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }
        if (coneKotlinType instanceof ConeErrorType) {
            return !Intrinsics.areEqual(((ConeErrorType) coneKotlinType).getNullable(), Boolean.FALSE);
        }
        if (coneKotlinType instanceof ConeLookupTagBasedType) {
            return ((ConeLookupTagBasedType) coneKotlinType).getIsMarkedNullable() || ConeTypeUtilsKt.isMarkedNullable(TypeExpansionUtilsKt.fullyExpandedType$default((ConeSimpleKotlinType) coneKotlinType, firSession, (Function1) null, 2, (Object) null));
        }
        if (coneKotlinType instanceof ConeIntegerLiteralType) {
            return ((ConeIntegerLiteralType) coneKotlinType).getIsMarkedNullable();
        }
        if (!(coneKotlinType instanceof ConeTypeVariableType)) {
            bu8.a();
            return false;
        }
        ConeTypeVariableType coneTypeVariableType = (ConeTypeVariableType) coneKotlinType;
        if (!coneTypeVariableType.getIsMarkedNullable()) {
            if (z) {
                TypeParameterMarker originalTypeParameter = coneTypeVariableType.getTypeConstructor().getOriginalTypeParameter();
                ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
                FirTypeParameterSymbol symbol = coneTypeParameterLookupTag != null ? coneTypeParameterLookupTag.getSymbol() : null;
                if (symbol == null || allBoundsAreNullableOrUnresolved(symbol, firSession)) {
                }
            }
            return false;
        }
        return true;
    }

    public static /* synthetic */ boolean canBeNull$default(ConeKotlinType coneKotlinType, FirSession firSession, boolean z, Set set, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            set = new LinkedHashSet();
        }
        return canBeNull(coneKotlinType, firSession, z, set);
    }

    public static final boolean canHaveSubtypesAccordingToK1(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        return hasSubtypesAboveNothingAccordingToK1(coneKotlinType, firSession);
    }

    public static final ConeKotlinType[] captureArguments(ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType, CaptureStatus captureStatus) {
        List<? extends ConeKotlinType> list;
        ConeKotlinType coneCapturedType;
        coneTypeContext.getClass();
        coneKotlinType.getClass();
        captureStatus.getClass();
        int length = coneKotlinType.getTypeArguments().length;
        if (length == 0) {
            return null;
        }
        TypeConstructorMarker typeConstructorMarkerTypeConstructor = coneTypeContext.typeConstructor(coneKotlinType);
        if (length != coneTypeContext.parametersCount(typeConstructorMarkerTypeConstructor)) {
            return null;
        }
        for (ConeTypeProjection coneTypeProjection : coneKotlinType.getTypeArguments()) {
            if (coneTypeProjection.getKind() != ProjectionKind.INVARIANT) {
                ConeKotlinType[] coneKotlinTypeArr = new ConeKotlinType[length];
                for (int i = 0; i < length; i++) {
                    ConeKotlinTypeProjection coneKotlinTypeProjection = coneKotlinType.getTypeArguments()[i];
                    if (coneKotlinTypeProjection.getKind() == ProjectionKind.INVARIANT) {
                        coneCapturedType = ConeTypeProjectionKt.getType(coneKotlinTypeProjection);
                        coneCapturedType.getClass();
                    } else {
                        coneCapturedType = new ConeCapturedType(false, new ConeCapturedTypeConstructor(coneKotlinTypeProjection, coneKotlinTypeProjection.getKind() == ProjectionKind.IN ? coneKotlinTypeProjection.getType() : null, captureStatus, null, coneTypeContext.m675getParameter(typeConstructorMarkerTypeConstructor, i)), null, 5, null);
                    }
                    coneKotlinTypeArr[i] = coneCapturedType;
                }
                IntRange intRangeUntil = RangesKt.until(0, length);
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10)), 16));
                IntIterator it = intRangeUntil.iterator();
                while (it.hasNext()) {
                    int iNextInt = it.nextInt();
                    Pair pair = TuplesKt.to(coneTypeContext.m675getParameter(typeConstructorMarkerTypeConstructor, iNextInt).getSymbol(), coneKotlinTypeArr[iNextInt]);
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                }
                ConeSubstitutor coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, coneTypeContext.getSession(), false, 4, null);
                for (int i2 = 0; i2 < length; i2++) {
                    ConeKotlinTypeProjectionOut coneKotlinTypeProjectionOut = coneKotlinType.getTypeArguments()[i2];
                    ConeKotlinType coneKotlinType2 = coneKotlinTypeArr[i2];
                    if (coneKotlinTypeProjectionOut.getKind() != ProjectionKind.INVARIANT) {
                        ConeTypeParameterLookupTag parameter = coneTypeContext.m675getParameter(typeConstructorMarkerTypeConstructor, i2);
                        FirLazyDeclarationResolverKt.lazyResolveToPhase(parameter.getTypeParameterSymbol(), FirResolvePhase.TYPES);
                        IntRange intRangeUntil2 = RangesKt.until(0, coneTypeContext.upperBoundCount(parameter));
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        IntIterator it2 = intRangeUntil2.iterator();
                        while (it2.hasNext()) {
                            linkedHashSet.add(MarkerExtensionsKt.safeSubstitute(coneSubstitutorSubstitutorByMap$default, (TypeSystemInferenceExtensionContext) coneTypeContext, coneTypeContext.m682getUpperBound((TypeParameterMarker) parameter, it2.nextInt())));
                        }
                        if (coneKotlinTypeProjectionOut instanceof ConeKotlinTypeProjectionOut) {
                            linkedHashSet.add(coneKotlinTypeProjectionOut.getType());
                        }
                        if (!(coneKotlinType2 instanceof ConeCapturedType)) {
                            w01.a("Failed requirement.");
                            return null;
                        }
                        ConeCapturedTypeConstructor constructor = ((ConeCapturedType) coneKotlinType2).getConstructor();
                        if (captureStatus == CaptureStatus.FROM_EXPRESSION) {
                            ConeKotlinType coneKotlinTypeIntersectTypes = coneTypeContext.intersectTypes((Collection<? extends KotlinTypeMarker>) linkedHashSet);
                            list = coneKotlinTypeIntersectTypes instanceof ConeIntersectionType ? CollectionsKt.toList(((ConeIntersectionType) coneKotlinTypeIntersectTypes).getIntersectedTypes()) : CollectionsKt.listOf(coneKotlinTypeIntersectTypes);
                        } else {
                            list = CollectionsKt.toList(linkedHashSet);
                            list.getClass();
                        }
                        constructor.setSupertypes(list);
                    }
                }
                return coneKotlinTypeArr;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.util.ArrayList] */
    private static final List<CapturedArguments> captureArgumentsForIntersectionType(ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType) {
        ?? intersectedTypes;
        CapturedArguments capturedArguments;
        boolean z;
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            List listPlus = CollectionsKt.plus(captureArgumentsForIntersectionType$getTypesToCapture(coneFlexibleType.getLowerBound()), captureArgumentsForIntersectionType$getTypesToCapture(coneFlexibleType.getUpperBound()));
            HashSet hashSet = new HashSet();
            intersectedTypes = new ArrayList();
            for (Object obj : listPlus) {
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) obj;
                TypeConstructorMarker baseBoundFqNameByMutability = ConeFlexibleTypeBoundsChecker.INSTANCE.getBaseBoundFqNameByMutability(coneKotlinType2);
                if (baseBoundFqNameByMutability == null) {
                    baseBoundFqNameByMutability = TypeSystemContextHelpersKt.typeConstructor(coneKotlinType2, coneTypeContext);
                }
                if (hashSet.add(TuplesKt.to(baseBoundFqNameByMutability, coneKotlinType2.getTypeArguments()))) {
                    intersectedTypes.add(obj);
                }
            }
        } else {
            if (!(coneKotlinType instanceof ConeIntersectionType)) {
                k2d.a("Should not be here");
                return null;
            }
            intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (ConeKotlinType coneKotlinType3 : (Iterable) intersectedTypes) {
            ConeKotlinType[] coneKotlinTypeArrCaptureArguments = captureArguments(coneTypeContext, coneKotlinType3, CaptureStatus.FROM_EXPRESSION);
            if (coneKotlinTypeArrCaptureArguments == null) {
                z = z2;
                capturedArguments = null;
            } else {
                capturedArguments = new CapturedArguments(coneKotlinTypeArrCaptureArguments, coneKotlinType3);
                z = true;
            }
            if (capturedArguments != null) {
                arrayList.add(capturedArguments);
            }
            z2 = z;
        }
        if (z2) {
            return arrayList;
        }
        return null;
    }

    private static final Collection<ConeKotlinType> captureArgumentsForIntersectionType$getTypesToCapture(ConeKotlinType coneKotlinType) {
        return coneKotlinType instanceof ConeIntersectionType ? ((ConeIntersectionType) coneKotlinType).getIntersectedTypes() : CollectionsKt.listOf(coneKotlinType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final ConeCapturedType captureCapturedType(ConeTypeContext coneTypeContext, ConeCapturedType coneCapturedType) throws KotlinIllegalArgumentExceptionWithAttachments {
        ArrayList arrayList;
        ConeKotlinType coneKotlinTypeCaptureFromExpressionInternal;
        ConeCapturedTypeConstructor constructor = coneCapturedType.getConstructor();
        ConeKotlinType type = ConeTypeProjectionKt.getType(constructor.getProjection());
        ConeTypeProjection coneTypeProjectionWrapProjection = (type == null || (coneKotlinTypeCaptureFromExpressionInternal = captureFromExpressionInternal(coneTypeContext, type)) == null) ? null : SubstitutionUtilitiesKt.wrapProjection(constructor.getProjection(), coneKotlinTypeCaptureFromExpressionInternal);
        List<ConeKotlinType> supertypes = constructor.getSupertypes();
        if (supertypes != null) {
            List<ConeKotlinType> list = supertypes;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (ConeKotlinType coneKotlinType : list) {
                ConeKotlinType coneKotlinTypeCaptureFromExpressionInternal2 = captureFromExpressionInternal(coneTypeContext, coneKotlinType);
                if (coneKotlinTypeCaptureFromExpressionInternal2 != null) {
                    coneKotlinType = coneKotlinTypeCaptureFromExpressionInternal2;
                }
                arrayList2.add(coneKotlinType);
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        ConeKotlinType lowerType = constructor.getLowerType();
        ConeKotlinType coneKotlinTypeCaptureFromExpressionInternal3 = lowerType != null ? captureFromExpressionInternal(coneTypeContext, lowerType) : null;
        if (coneTypeProjectionWrapProjection == null && coneKotlinTypeCaptureFromExpressionInternal3 == null && Intrinsics.areEqual(arrayList, constructor.getSupertypes())) {
            return null;
        }
        if (coneTypeProjectionWrapProjection == null) {
            coneTypeProjectionWrapProjection = constructor.getProjection();
        }
        ConeTypeProjection coneTypeProjection = coneTypeProjectionWrapProjection;
        if (coneKotlinTypeCaptureFromExpressionInternal3 == null) {
            coneKotlinTypeCaptureFromExpressionInternal3 = constructor.getLowerType();
        }
        return ConeCapturedType.copy$default(coneCapturedType, false, new ConeCapturedTypeConstructor(coneTypeProjection, coneKotlinTypeCaptureFromExpressionInternal3, constructor.getCaptureStatus(), arrayList, constructor.getTypeParameterMarker()), null, 5, null);
    }

    public static final ConeRigidType captureFromArgumentsInternal(ConeTypeContext coneTypeContext, ConeRigidType coneRigidType, CaptureStatus captureStatus) {
        coneTypeContext.getClass();
        coneRigidType.getClass();
        captureStatus.getClass();
        ConeKotlinType[] coneKotlinTypeArrCaptureArguments = captureArguments(coneTypeContext, coneRigidType, captureStatus);
        if (coneKotlinTypeArrCaptureArguments == null) {
            return null;
        }
        return (ConeRigidType) withArguments(coneRigidType, (ConeTypeProjection[]) coneKotlinTypeArrCaptureArguments);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final ConeKotlinType captureFromExpressionInternal(ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneKotlinType2;
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        ConeKotlinType lowerBound2;
        ConeRigidType lowerBound3;
        ConeRigidType upperBound2;
        coneTypeContext.getClass();
        coneKotlinType.getClass();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = CollectionsKt.emptyList();
        if (coneKotlinType instanceof ConeCapturedType) {
            return captureCapturedType(coneTypeContext, (ConeCapturedType) coneKotlinType);
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            ConeKotlinType coneKotlinTypeCaptureFromExpressionInternal = captureFromExpressionInternal(coneTypeContext, ((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
            if (coneKotlinTypeCaptureFromExpressionInternal != null) {
                return makeConeTypeDefinitelyNotNullOrNotNull$default(coneKotlinTypeCaptureFromExpressionInternal, coneTypeContext, false, false, 6, (Object) null);
            }
            return null;
        }
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            if (!(coneKotlinType instanceof ConeIntersectionType)) {
                if (coneKotlinType instanceof ConeSimpleKotlinType) {
                    return captureFromArgumentsInternal(coneTypeContext, (ConeRigidType) coneKotlinType, CaptureStatus.FROM_EXPRESSION);
                }
                bu8.a();
                return null;
            }
            List<CapturedArguments> listCaptureArgumentsForIntersectionType = captureArgumentsForIntersectionType(coneTypeContext, coneKotlinType);
            if (listCaptureArgumentsForIntersectionType == null) {
                return null;
            }
            objectRef.element = listCaptureArgumentsForIntersectionType;
            List<ConeKotlinType> listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents = captureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents(objectRef, coneTypeContext, (ConeRigidType) coneKotlinType);
            if (listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents == null) {
                return null;
            }
            return coneTypeContext.withNullability(coneTypeContext.intersectTypes((Collection<? extends KotlinTypeMarker>) listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents), canBeNull$default(coneKotlinType, coneTypeContext.getSession(), false, null, 6, null));
        }
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinType);
        if (coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeCapturedType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            ConeKotlinType coneKotlinTypeCaptureFromExpressionInternal2 = captureFromExpressionInternal(coneTypeContext, coneFlexibleType.getLowerBound());
            if (!coneFlexibleType.getIsTrivial()) {
                lowerBound2 = coneKotlinTypeCaptureFromExpressionInternal2;
            } else {
                if (coneKotlinTypeCaptureFromExpressionInternal2 == null) {
                    return null;
                }
                if (coneKotlinTypeCaptureFromExpressionInternal2 instanceof ConeRigidType) {
                    return coneFlexibleOrSimpleType(coneTypeContext, coneKotlinTypeCaptureFromExpressionInternal2, withNullability$default(coneKotlinTypeCaptureFromExpressionInternal2, true, coneTypeContext, null, true, 4, null), true);
                }
                lowerBound2 = coneKotlinTypeCaptureFromExpressionInternal2;
                if (!(lowerBound2 instanceof ConeFlexibleType)) {
                    bu8.a();
                    return null;
                }
                if (((ConeFlexibleType) lowerBound2).getAttributes().isEmpty()) {
                    return lowerBound2;
                }
            }
            ConeKotlinType coneKotlinTypeCaptureFromExpressionInternal3 = captureFromExpressionInternal(r2, coneFlexibleType.getUpperBound());
            if (lowerBound2 == null && coneKotlinTypeCaptureFromExpressionInternal3 == null) {
                return null;
            }
            if (!(coneFlexibleType instanceof ConeRawType)) {
                if (lowerBound2 == null) {
                    lowerBound2 = coneFlexibleType.getLowerBound();
                }
                if (coneKotlinTypeCaptureFromExpressionInternal3 == null) {
                    coneKotlinTypeCaptureFromExpressionInternal3 = coneFlexibleType.getUpperBound();
                }
                return coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, coneKotlinTypeCaptureFromExpressionInternal3, false);
            }
            ConeRawType.Companion companion = ConeRawType.INSTANCE;
            if (lowerBound2 == null || (lowerBound3 = ConeTypeUtilsKt.lowerBoundIfFlexible(lowerBound2)) == null) {
                lowerBound3 = coneFlexibleType.getLowerBound();
            }
            if (coneKotlinTypeCaptureFromExpressionInternal3 == null || (upperBound2 = ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinTypeCaptureFromExpressionInternal3)) == null) {
                upperBound2 = coneFlexibleType.getUpperBound();
            }
            return companion.create(lowerBound3, upperBound2);
        }
        if (!(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeLookupTagBasedType) && !(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeIntersectionType)) {
            if (!(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeIntegerLiteralType) && !(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeStubType) && !(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeTypeVariableType)) {
                bu8.a();
            }
            return null;
        }
        List<CapturedArguments> listCaptureArgumentsForIntersectionType2 = captureArgumentsForIntersectionType(coneTypeContext, coneKotlinType);
        if (listCaptureArgumentsForIntersectionType2 == null) {
            return null;
        }
        objectRef.element = listCaptureArgumentsForIntersectionType2;
        ConeFlexibleType coneFlexibleType2 = (ConeFlexibleType) coneKotlinType;
        ConeRigidType lowerBound4 = coneFlexibleType2.getLowerBound();
        List<ConeKotlinType> listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents2 = captureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents(objectRef, coneTypeContext, lowerBound4);
        if (listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents2 == null) {
            return null;
        }
        ConeKotlinType coneKotlinTypeWithNullability$default = withNullability$default(coneTypeContext.intersectTypes((Collection<? extends KotlinTypeMarker>) listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents2), canBeNull$default(lowerBound4, coneTypeContext.getSession(), false, null, 6, null), coneTypeContext, null, false, 12, null);
        if (!coneFlexibleType2.getIsTrivial()) {
            coneKotlinType2 = coneKotlinTypeWithNullability$default;
        } else {
            if (coneKotlinTypeWithNullability$default == null) {
                return null;
            }
            if (coneKotlinTypeWithNullability$default instanceof ConeRigidType) {
                return coneFlexibleOrSimpleType(coneTypeContext, coneKotlinTypeWithNullability$default, withNullability$default(coneKotlinTypeWithNullability$default, true, coneTypeContext, null, true, 4, null), true);
            }
            coneKotlinType2 = coneKotlinTypeWithNullability$default;
            if (!(coneKotlinType2 instanceof ConeFlexibleType)) {
                bu8.a();
                return null;
            }
            if (((ConeFlexibleType) coneKotlinType2).getAttributes().isEmpty()) {
                return coneKotlinType2;
            }
        }
        ConeRigidType upperBound3 = coneFlexibleType2.getUpperBound();
        List<ConeKotlinType> listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents3 = captureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents(objectRef, coneTypeContext, upperBound3);
        if (listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents3 == null) {
            return null;
        }
        ConeKotlinType coneKotlinTypeWithNullability$default2 = withNullability$default(coneTypeContext.intersectTypes((Collection<? extends KotlinTypeMarker>) listCaptureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents3), canBeNull$default(upperBound3, coneTypeContext.getSession(), false, null, 6, null), coneTypeContext, null, false, 12, null);
        if (coneKotlinType2 == null && coneKotlinTypeWithNullability$default2 == null) {
            return null;
        }
        if (!(coneFlexibleType2 instanceof ConeRawType)) {
            ConeKotlinType lowerBound5 = coneKotlinType2 == null ? coneFlexibleType2.getLowerBound() : coneKotlinType2;
            if (coneKotlinTypeWithNullability$default2 == null) {
                coneKotlinTypeWithNullability$default2 = coneFlexibleType2.getUpperBound();
            }
            return coneFlexibleOrSimpleType(coneTypeContext, lowerBound5, coneKotlinTypeWithNullability$default2, false);
        }
        ConeRawType.Companion companion2 = ConeRawType.INSTANCE;
        if (coneKotlinType2 == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType2)) == null) {
            lowerBound = coneFlexibleType2.getLowerBound();
        }
        if (coneKotlinTypeWithNullability$default2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinTypeWithNullability$default2)) == null) {
            upperBound = coneFlexibleType2.getUpperBound();
        }
        return companion2.create(lowerBound, upperBound);
    }

    private static final ConeTypeProjection[] captureFromExpressionInternal$findCorrespondingCapturedArgumentsForType(Ref.ObjectRef<List<CapturedArguments>> objectRef, ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType) {
        Object next;
        Iterator it = ((Iterable) objectRef.element).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((CapturedArguments) next).isSuitableForType(coneKotlinType, coneTypeContext));
        CapturedArguments capturedArguments = (CapturedArguments) next;
        if (capturedArguments != null) {
            return capturedArguments.getCapturedArguments();
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final List<ConeKotlinType> captureFromExpressionInternal$replaceArgumentsWithCapturedArgumentsByIntersectionComponents(Ref.ObjectRef<List<CapturedArguments>> objectRef, ConeTypeContext coneTypeContext, ConeRigidType coneRigidType) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (!(coneRigidType instanceof ConeIntersectionType)) {
            ConeTypeProjection[] coneTypeProjectionArrCaptureFromExpressionInternal$findCorrespondingCapturedArgumentsForType = captureFromExpressionInternal$findCorrespondingCapturedArgumentsForType(objectRef, coneTypeContext, coneRigidType);
            if (coneTypeProjectionArrCaptureFromExpressionInternal$findCorrespondingCapturedArgumentsForType == null) {
                return null;
            }
            return CollectionsKt.listOf(withArguments(coneRigidType, coneTypeProjectionArrCaptureFromExpressionInternal$findCorrespondingCapturedArgumentsForType));
        }
        ConeIntersectionType coneIntersectionType = (ConeIntersectionType) coneRigidType;
        Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
        for (ConeKotlinType coneKotlinTypeWithArguments : intersectedTypes) {
            ConeTypeProjection[] coneTypeProjectionArrCaptureFromExpressionInternal$findCorrespondingCapturedArgumentsForType2 = captureFromExpressionInternal$findCorrespondingCapturedArgumentsForType(objectRef, coneTypeContext, coneKotlinTypeWithArguments);
            if (coneTypeProjectionArrCaptureFromExpressionInternal$findCorrespondingCapturedArgumentsForType2 != null) {
                coneKotlinTypeWithArguments = withArguments(coneKotlinTypeWithArguments, coneTypeProjectionArrCaptureFromExpressionInternal$findCorrespondingCapturedArgumentsForType2);
            }
            arrayList.add(coneKotlinTypeWithArguments);
        }
        if (Intrinsics.areEqual(arrayList, coneIntersectionType.getIntersectedTypes())) {
            return null;
        }
        return arrayList;
    }

    public static final ConeKotlinType commonSuperTypeOrNull(ConeInferenceContext coneInferenceContext, List<? extends ConeKotlinType> list) {
        coneInferenceContext.getClass();
        list.getClass();
        int size = list.size();
        if (size != 0) {
            return size != 1 ? NewCommonSuperTypeCalculator.INSTANCE.commonSuperType(coneInferenceContext, list) : (ConeKotlinType) CollectionsKt.first(list);
        }
        return null;
    }

    public static final ConeKotlinType coneFlexibleOrSimpleType(ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z) {
        coneTypeContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        if (coneKotlinType instanceof ConeFlexibleType) {
            return coneFlexibleOrSimpleType(coneTypeContext, ((ConeFlexibleType) coneKotlinType).getLowerBound(), coneKotlinType2, z);
        }
        if (!(coneKotlinType instanceof ConeRigidType)) {
            bu8.a();
            return null;
        }
        if (coneKotlinType2 instanceof ConeFlexibleType) {
            return coneFlexibleOrSimpleType(coneTypeContext, coneKotlinType, ((ConeFlexibleType) coneKotlinType2).getUpperBound(), z);
        }
        if (coneKotlinType2 instanceof ConeRigidType) {
            return z ? Intrinsics.areEqual(coneKotlinType, coneKotlinType2) : AbstractStrictEqualityTypeChecker.INSTANCE.strictEqualTypes(coneTypeContext, coneKotlinType, coneKotlinType2) ? coneKotlinType : new ConeFlexibleType((ConeRigidType) coneKotlinType, (ConeRigidType) coneKotlinType2, z);
        }
        bu8.a();
        return null;
    }

    public static final ConeKotlinType convertToNonRawVersion(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (!isRaw(coneKotlinType)) {
            return coneKotlinType;
        }
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            return withAttributes(coneKotlinType, coneKotlinType.getAttributes().remove((ConeAttribute<?>) CompilerConeAttributes.RawType.INSTANCE));
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
        return new ConeFlexibleType((ConeRigidType) withAttributes(coneFlexibleType.getLowerBound(), coneFlexibleType.getAttributes().remove((ConeAttribute<?>) CompilerConeAttributes.RawType.INSTANCE)), coneFlexibleType.getUpperBound(), false);
    }

    public static final ConeDefinitelyNotNullType create(ConeDefinitelyNotNullType.Companion companion, ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, boolean z) {
        companion.getClass();
        coneKotlinType.getClass();
        coneTypeContext.getClass();
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return (ConeDefinitelyNotNullType) coneKotlinType;
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return create(companion, ((ConeFlexibleType) coneKotlinType).getLowerBound(), coneTypeContext, z);
        }
        if (!(coneKotlinType instanceof ConeSimpleKotlinType)) {
            bu8.a();
            return null;
        }
        if (makesSenseToBeDefinitelyNotNull(coneTypeContext, (ConeSimpleKotlinType) coneKotlinType, z)) {
            return new ConeDefinitelyNotNullType((ConeSimpleKotlinType) withNullability$default(coneKotlinType, false, coneTypeContext, null, true, 4, null));
        }
        return null;
    }

    public static /* synthetic */ ConeDynamicType create$default(ConeDynamicType.Companion companion, FirSession firSession, ConeAttributes coneAttributes, int i, Object obj) {
        if ((i & 2) != 0) {
            coneAttributes = ConeAttributes.INSTANCE.getEmpty();
        }
        return create(companion, firSession, coneAttributes);
    }

    public static final boolean equalTypes(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, boolean z) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firSession.getClass();
        return AbstractTypeChecker.INSTANCE.equalTypes(TypeCheckerProviderContext.newTypeCheckerState$default(TypeComponentsKt.getTypeContext(firSession), z, false, false, 4, (Object) null), coneKotlinType, coneKotlinType2);
    }

    public static /* synthetic */ boolean equalTypes$default(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return equalTypes(coneKotlinType, coneKotlinType2, firSession, z);
    }

    private static final ConeKotlinType eraseArgumentsDeeply(SimpleTypeMarker simpleTypeMarker, final ConeInferenceContext coneInferenceContext, final Map<FirTypeParameter, ConeKotlinType> map, final EraseUpperBoundMode eraseUpperBoundMode) {
        return (ConeRigidType) coneInferenceContext.replaceArgumentsDeeply(simpleTypeMarker, new Function1() { // from class: org.jetbrains.kotlin.fir.types.a
            public final Object invoke(Object obj) {
                return TypeUtilsKt.eraseArgumentsDeeply$lambda$0$0(coneInferenceContext, map, eraseUpperBoundMode, (TypeArgumentMarker) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final TypeArgumentMarker eraseArgumentsDeeply$lambda$0$0(ConeInferenceContext coneInferenceContext, Map map, EraseUpperBoundMode eraseUpperBoundMode, TypeArgumentMarker typeArgumentMarker) {
        typeArgumentMarker.getClass();
        ConeKotlinType coneKotlinTypeM677getType = coneInferenceContext.m677getType(typeArgumentMarker);
        if (coneKotlinTypeM677getType != null) {
            TypeConstructorMarker typeConstructorMarkerTypeConstructor = coneInferenceContext.typeConstructor(coneKotlinTypeM677getType);
            if (!coneInferenceContext.isTypeParameterTypeConstructor(typeConstructorMarkerTypeConstructor)) {
                typeConstructorMarkerTypeConstructor = null;
            }
            if (typeConstructorMarkerTypeConstructor != null) {
                ConeKotlinType coneKotlinTypeEraseToUpperBound = eraseToUpperBound((FirTypeParameter) ((ConeTypeParameterLookupTag) typeConstructorMarkerTypeConstructor).getTypeParameterSymbol().getFir(), coneInferenceContext.getSession(), map, eraseUpperBoundMode);
                ConeErrorType coneErrorType = coneKotlinTypeEraseToUpperBound instanceof ConeErrorType ? (ConeErrorType) coneKotlinTypeEraseToUpperBound : null;
                if ((coneErrorType != null ? coneErrorType.getDiagnostic() : null) instanceof ConeRecursiveTypeParameterDuringErasureError) {
                    return ConeStarProjection.INSTANCE;
                }
                return eraseUpperBoundMode == EraseUpperBoundMode.FOR_RAW_TYPE_ERASURE ? coneKotlinTypeEraseToUpperBound : ConeTypeUtilsKt.toTypeProjection(coneKotlinTypeEraseToUpperBound, ProjectionKind.OUT);
            }
        }
        return typeArgumentMarker;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeKotlinType eraseAsUpperBound(ConeKotlinType coneKotlinType, FirSession firSession, Map<FirTypeParameter, ConeKotlinType> map, EraseUpperBoundMode eraseUpperBoundMode) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (coneKotlinType instanceof ConeClassLikeType) {
            return eraseArgumentsDeeply((SimpleTypeMarker) coneKotlinType, TypeComponentsKt.getTypeContext(firSession), map, eraseUpperBoundMode);
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            return coneFlexibleOrSimpleType(TypeComponentsKt.getTypeContext(firSession), eraseAsUpperBound(coneFlexibleType.getLowerBound(), firSession, map, eraseUpperBoundMode), eraseAsUpperBound(coneFlexibleType.getUpperBound(), firSession, map, eraseUpperBoundMode), false);
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            ConeTypeParameterType coneTypeParameterType = (ConeTypeParameterType) coneKotlinType;
            ConeKotlinType coneKotlinTypeEraseToUpperBound = eraseToUpperBound((FirTypeParameter) coneTypeParameterType.getLookupTag().getTypeParameterSymbol().getFir(), firSession, map, eraseUpperBoundMode);
            return coneTypeParameterType.getIsMarkedNullable() ? withNullability$default(coneKotlinTypeEraseToUpperBound, true, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null) : coneKotlinTypeEraseToUpperBound;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return makeConeTypeDefinitelyNotNullOrNotNull$default(eraseAsUpperBound(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), firSession, map, eraseUpperBoundMode), (ConeTypeContext) TypeComponentsKt.getTypeContext(firSession), false, false, 6, (Object) null);
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("unexpected Java type parameter upper bound kind: " + Reflection.getOrCreateKotlinClass(coneKotlinType.getClass()), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, ModuleXmlParser.TYPE, coneKotlinType);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    private static final ConeKotlinType eraseToUpperBound(FirTypeParameter firTypeParameter, FirSession firSession, Map<FirTypeParameter, ConeKotlinType> map, EraseUpperBoundMode eraseUpperBoundMode) {
        ConeKotlinType coneKotlinTypeEraseToUpperBound$eraseAsUpperBound;
        ConeKotlinType coneKotlinType = map.get(firTypeParameter);
        if (coneKotlinType == null) {
            map.put(firTypeParameter, new ConeErrorType(new ConeRecursiveTypeParameterDuringErasureError(firTypeParameter.getName()), false, null, null, null, null, null, 126, null));
            if (eraseUpperBoundMode == EraseUpperBoundMode.FOR_EMPTY_INTERSECTION_CHECK) {
                ConeTypeIntersector coneTypeIntersector = ConeTypeIntersector.INSTANCE;
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
                List<FirResolvedTypeRef> resolvedBounds = firTypeParameter.getSymbol().getResolvedBounds();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
                Iterator<T> it = resolvedBounds.iterator();
                while (it.hasNext()) {
                    arrayList.add(eraseToUpperBound$eraseAsUpperBound(firSession, map, eraseUpperBoundMode, (FirResolvedTypeRef) it.next()));
                }
                coneKotlinTypeEraseToUpperBound$eraseAsUpperBound = coneTypeIntersector.intersectTypes(typeContext, arrayList);
            } else {
                FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) CollectionsKt.first(firTypeParameter.getBounds());
                coneKotlinTypeEraseToUpperBound$eraseAsUpperBound = firResolvedTypeRef instanceof FirResolvedTypeRef ? eraseToUpperBound$eraseAsUpperBound(firSession, map, eraseUpperBoundMode, firResolvedTypeRef) : firSession.getBuiltinTypes().getAnyType().getConeType();
            }
            coneKotlinType = coneKotlinTypeEraseToUpperBound$eraseAsUpperBound;
            map.put(firTypeParameter, coneKotlinType);
        }
        return coneKotlinType;
    }

    private static final ConeKotlinType eraseToUpperBound$eraseAsUpperBound(FirSession firSession, Map<FirTypeParameter, ConeKotlinType> map, EraseUpperBoundMode eraseUpperBoundMode, FirResolvedTypeRef firResolvedTypeRef) {
        return eraseAsUpperBound(firResolvedTypeRef.getConeType(), firSession, map, eraseUpperBoundMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Map<FirTypeParameterSymbol, ConeKotlinType> eraseToUpperBoundsAssociated(List<FirTypeParameterSymbol> list, FirSession firSession) {
        list.getClass();
        firSession.getClass();
        HashMap map = new HashMap();
        List<FirTypeParameterSymbol> list2 = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
        for (Object obj : list2) {
            linkedHashMap.put(obj, eraseToUpperBound((FirTypeParameter) ((FirTypeParameterSymbol) obj).getFir(), firSession, map, EraseUpperBoundMode.FOR_EMPTY_INTERSECTION_CHECK));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeKotlinType getProjectionForRawType(FirTypeParameterSymbol firTypeParameterSymbol, FirSession firSession, Map<FirTypeParameter, ConeKotlinType> map, boolean z) {
        ConeKotlinType coneKotlinTypeEraseToUpperBound = eraseToUpperBound((FirTypeParameter) firTypeParameterSymbol.getFir(), firSession, map, EraseUpperBoundMode.FOR_RAW_TYPE_ERASURE);
        return z ? withNullability$default(coneKotlinTypeEraseToUpperBound, true, TypeComponentsKt.getTypeContext(firSession), null, false, 12, null) : coneKotlinTypeEraseToUpperBound;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0023  */
    public static final ConeKotlinType[] getProjectionsForRawType(List<FirTypeParameterSymbol> list, FirSession firSession, boolean[] zArr) {
        boolean z;
        list.getClass();
        firSession.getClass();
        HashMap map = new HashMap();
        int size = list.size();
        ConeKotlinType[] coneKotlinTypeArr = new ConeKotlinType[size];
        for (int i = 0; i < size; i++) {
            FirTypeParameterSymbol firTypeParameterSymbol = list.get(i);
            if (zArr != null) {
                z = true;
                if (!zArr[i]) {
                    z = false;
                }
            } else {
                z = false;
            }
            coneKotlinTypeArr[i] = getProjectionForRawType(firTypeParameterSymbol, firSession, map, z);
        }
        return coneKotlinTypeArr;
    }

    public static final boolean hasEnhancedNullability(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeKotlinType coneKotlinType = coneType != null ? coneType : null;
        return coneKotlinType != null && CompilerConeAttributesKt.getHasEnhancedNullability(coneKotlinType);
    }

    private static final boolean hasSubtypesAboveNothingAccordingToK1(ConeKotlinType coneKotlinType, FirSession firSession) {
        FirClassSymbol<?> classSymbol;
        boolean zHasSubtypesAboveNothingAccordingToK1;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        if (ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType$default) || (classSymbol = ToSymbolUtilsKt.toClassSymbol(coneKotlinTypeFullyExpandedType$default, firSession)) == null || classSymbol.getClassKind() == ClassKind.ENUM_CLASS || classSymbol.getRawStatus().isExpect() || classSymbol.getResolvedStatus().getModality() != Modality.FINAL) {
            return true;
        }
        ConeTypeProjection[] typeArguments = coneKotlinTypeFullyExpandedType$default.getTypeArguments();
        int length = typeArguments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            ConeTypeProjection coneTypeProjection = typeArguments[i];
            int i3 = i2 + 1;
            FirTypeParameterSymbol firTypeParameterSymbol = classSymbol.getTypeParameterSymbols().get(i2);
            if (ConeTypeProjectionKt.isStarProjection(coneTypeProjection)) {
                return true;
            }
            ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
            type.getClass();
            Variance variance = ConeTypeProjectionKt.getVariance(coneTypeProjection);
            int[] iArr = WhenMappings.$EnumSwitchMapping$0;
            int i4 = iArr[variance.ordinal()];
            if (i4 == 1) {
                zHasSubtypesAboveNothingAccordingToK1 = hasSubtypesAboveNothingAccordingToK1(type, firSession);
            } else if (i4 == 2) {
                zHasSubtypesAboveNothingAccordingToK1 = hasSupertypesBelowParameterBoundsAccordingToK1(type, firTypeParameterSymbol, firSession);
            } else {
                if (i4 != 3) {
                    bu8.a();
                    return false;
                }
                int i5 = iArr[firTypeParameterSymbol.getVariance().ordinal()];
                if (i5 == 1) {
                    zHasSubtypesAboveNothingAccordingToK1 = hasSubtypesAboveNothingAccordingToK1(type, firSession);
                } else if (i5 == 2) {
                    zHasSubtypesAboveNothingAccordingToK1 = hasSupertypesBelowParameterBoundsAccordingToK1(type, firTypeParameterSymbol, firSession);
                } else {
                    if (i5 != 3) {
                        bu8.a();
                        return false;
                    }
                    zHasSubtypesAboveNothingAccordingToK1 = hasSubtypesAboveNothingAccordingToK1(type, firSession) || hasSupertypesBelowParameterBoundsAccordingToK1(type, firTypeParameterSymbol, firSession);
                }
            }
            if (zHasSubtypesAboveNothingAccordingToK1) {
                return true;
            }
            i++;
            i2 = i3;
        }
        return false;
    }

    private static final boolean hasSupertypesBelowParameterBoundsAccordingToK1(ConeKotlinType coneKotlinType, FirTypeParameterSymbol firTypeParameterSymbol, FirSession firSession) {
        for (FirResolvedTypeRef firResolvedTypeRef : firTypeParameterSymbol.getResolvedBounds()) {
            if (!Intrinsics.areEqual(coneKotlinType, firResolvedTypeRef.getConeType()) && isSubtypeOf((KotlinTypeMarker) coneKotlinType, (TypeCheckerProviderContext) TypeComponentsKt.getTypeContext(firSession), (KotlinTypeMarker) firResolvedTypeRef.getConeType())) {
                return true;
            }
        }
        return false;
    }

    public static final ConeKotlinType intersectTypesOrNull(ConeInferenceContext coneInferenceContext, List<? extends ConeKotlinType> list) {
        coneInferenceContext.getClass();
        list.getClass();
        int size = list.size();
        if (size != 0) {
            return size != 1 ? ConeTypeIntersector.INSTANCE.intersectTypes(coneInferenceContext, list) : (ConeKotlinType) CollectionsKt.first(list);
        }
        return null;
    }

    public static final boolean isAnonymousClass(ConeClassLikeLookupTag coneClassLikeLookupTag) {
        coneClassLikeLookupTag.getClass();
        return Intrinsics.areEqual(coneClassLikeLookupTag.getName(), SpecialNames.ANONYMOUS);
    }

    public static final boolean isExtensionFunctionType(FirTypeRef firTypeRef, FirSession firSession) {
        firTypeRef.getClass();
        firSession.getClass();
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeKotlinType coneKotlinType = coneType != null ? coneType : null;
        return coneKotlinType != null && isExtensionFunctionType(coneKotlinType, firSession);
    }

    public static final boolean isKCallableType(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), StandardClassIds.INSTANCE.getKCallable());
    }

    public static final boolean isLeftValidForDefinitelyNotNullable(FirIntersectionTypeRef firIntersectionTypeRef, FirSession firSession) {
        firIntersectionTypeRef.getClass();
        firSession.getClass();
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firIntersectionTypeRef.getLeftType());
        return (coneType instanceof ConeTypeParameterType) && canBeNull$default(coneType, firSession, false, null, 6, null) && !((ConeTypeParameterType) coneType).getIsMarkedNullable();
    }

    public static final boolean isRaw(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType).getAttributes().contains((ConeAttribute<?>) CompilerConeAttributes.RawType.INSTANCE);
    }

    public static final boolean isRightValidForDefinitelyNotNullable(FirIntersectionTypeRef firIntersectionTypeRef) {
        firIntersectionTypeRef.getClass();
        return ConeBuiltinTypeUtilsKt.isAny(FirTypeUtilsKt.getConeType(firIntersectionTypeRef.getRightType()));
    }

    public static final boolean isSubtypeOf(FirCallableDeclaration firCallableDeclaration, FirCallableDeclaration firCallableDeclaration2, TypeCheckerState typeCheckerState) {
        firCallableDeclaration.getClass();
        firCallableDeclaration2.getClass();
        typeCheckerState.getClass();
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState, FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef()), FirTypeUtilsKt.getConeType(firCallableDeclaration2.getReturnTypeRef()), false, 8, (Object) null);
    }

    public static /* synthetic */ boolean isSubtypeOf$default(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return isSubtypeOf(coneKotlinType, coneKotlinType2, firSession, z);
    }

    public static final boolean isUnitOrFlexibleUnit(ConeKotlinType coneKotlinType) {
        ClassId classId;
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        if (ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeLowerBoundIfFlexible) || (classId = ConeTypeUtilsKt.getClassId(coneRigidTypeLowerBoundIfFlexible)) == null) {
            return false;
        }
        return Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getUnit());
    }

    public static final ConeKotlinType makeConeTypeDefinitelyNotNullOrNotNull(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, boolean z, boolean z2) {
        coneKotlinType.getClass();
        coneTypeContext.getClass();
        ConeKotlinType coneKotlinType2 = null;
        int i = 2;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, coneTypeContext.getSession(), (Function1) null, 2, (Object) null);
        if (coneKotlinTypeFullyExpandedType$default != coneKotlinType) {
            return makeConeTypeDefinitelyNotNullOrNotNull(coneKotlinTypeFullyExpandedType$default, coneTypeContext, z, z2);
        }
        if (!(coneKotlinType instanceof ConeIntersectionType)) {
            ConeDefinitelyNotNullType coneDefinitelyNotNullTypeCreate = create(ConeDefinitelyNotNullType.INSTANCE, coneKotlinType, coneTypeContext, z);
            return coneDefinitelyNotNullTypeCreate != null ? coneDefinitelyNotNullTypeCreate : withNullability$default(coneKotlinType, false, coneTypeContext, null, z2, 4, null);
        }
        Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
        Iterator<T> it = intersectedTypes.iterator();
        while (it.hasNext()) {
            ConeTypeContext coneTypeContext2 = coneTypeContext;
            arrayList.add(makeConeTypeDefinitelyNotNullOrNotNull$default((ConeKotlinType) it.next(), coneTypeContext2, z, false, 4, (Object) null));
            coneTypeContext = coneTypeContext2;
        }
        return new ConeIntersectionType(arrayList, coneKotlinType2, i, coneKotlinType2);
    }

    public static /* synthetic */ ConeRigidType makeConeTypeDefinitelyNotNullOrNotNull$default(ConeRigidType coneRigidType, ConeTypeContext coneTypeContext, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        coneRigidType.getClass();
        coneTypeContext.getClass();
        ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull = makeConeTypeDefinitelyNotNullOrNotNull((ConeKotlinType) coneRigidType, coneTypeContext, z, z2);
        coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull.getClass();
        return (ConeRigidType) coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull;
    }

    private static final boolean makesSenseToBeDefinitelyNotNull(ConeTypeContext coneTypeContext, ConeSimpleKotlinType coneSimpleKotlinType, boolean z) {
        if (coneSimpleKotlinType instanceof ConeTypeParameterType) {
            return z || coneTypeContext.isNullableType(coneSimpleKotlinType);
        }
        if ((coneSimpleKotlinType instanceof ConeTypeVariableType) || (coneSimpleKotlinType instanceof ConeCapturedType)) {
            return z || !AbstractNullabilityChecker.INSTANCE.isSubtypeOfAny(TypeCheckerProviderContext.newTypeCheckerState$default(coneTypeContext, false, false, false, 4, (Object) null), coneSimpleKotlinType);
        }
        return false;
    }

    public static final ConeKotlinType mapTypesOrNull(ConeFlexibleType coneFlexibleType, ConeTypeContext coneTypeContext, boolean z, Function1<? super ConeRigidType, ? extends ConeKotlinType> function1) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        coneFlexibleType.getClass();
        coneTypeContext.getClass();
        function1.getClass();
        Object objInvoke = function1.invoke(coneFlexibleType.getLowerBound());
        ConeKotlinType coneKotlinType = (ConeKotlinType) objInvoke;
        if (z && coneKotlinType == coneFlexibleType.getLowerBound()) {
            objInvoke = null;
        }
        ConeKotlinType lowerBound2 = (ConeKotlinType) objInvoke;
        if (coneFlexibleType.getIsTrivial()) {
            if (lowerBound2 == null) {
                return null;
            }
            if (lowerBound2 instanceof ConeRigidType) {
                return coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, withNullability$default(lowerBound2, true, coneTypeContext, null, true, 4, null), true);
            }
            if (!(lowerBound2 instanceof ConeFlexibleType)) {
                bu8.a();
                return null;
            }
            if (((ConeFlexibleType) lowerBound2).getAttributes().isEmpty()) {
                return lowerBound2;
            }
        }
        Object objInvoke2 = function1.invoke(coneFlexibleType.getUpperBound());
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) objInvoke2;
        if (z && coneKotlinType2 == coneFlexibleType.getUpperBound()) {
            objInvoke2 = null;
        }
        ConeKotlinType upperBound2 = (ConeKotlinType) objInvoke2;
        if (lowerBound2 == null && upperBound2 == null) {
            return null;
        }
        if (!(coneFlexibleType instanceof ConeRawType)) {
            if (lowerBound2 == null) {
                lowerBound2 = coneFlexibleType.getLowerBound();
            }
            if (upperBound2 == null) {
                upperBound2 = coneFlexibleType.getUpperBound();
            }
            return coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, upperBound2, false);
        }
        ConeRawType.Companion companion = ConeRawType.INSTANCE;
        if (lowerBound2 == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(lowerBound2)) == null) {
            lowerBound = coneFlexibleType.getLowerBound();
        }
        if (upperBound2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(upperBound2)) == null) {
            upperBound = coneFlexibleType.getUpperBound();
        }
        return companion.create(lowerBound, upperBound);
    }

    public static /* synthetic */ ConeKotlinType mapTypesOrNull$default(ConeFlexibleType coneFlexibleType, ConeTypeContext coneTypeContext, boolean z, Function1 function1, int i, Object obj) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        if ((i & 2) != 0) {
            z = false;
        }
        coneFlexibleType.getClass();
        coneTypeContext.getClass();
        function1.getClass();
        Object objInvoke = function1.invoke(coneFlexibleType.getLowerBound());
        ConeKotlinType coneKotlinType = (ConeKotlinType) objInvoke;
        if (z && coneKotlinType == coneFlexibleType.getLowerBound()) {
            objInvoke = null;
        }
        ConeKotlinType lowerBound2 = (ConeKotlinType) objInvoke;
        if (coneFlexibleType.getIsTrivial()) {
            if (lowerBound2 == null) {
                return null;
            }
            if (lowerBound2 instanceof ConeRigidType) {
                return coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, withNullability$default(lowerBound2, true, coneTypeContext, null, true, 4, null), true);
            }
            if (!(lowerBound2 instanceof ConeFlexibleType)) {
                bu8.a();
                return null;
            }
            if (((ConeFlexibleType) lowerBound2).getAttributes().isEmpty()) {
                return lowerBound2;
            }
        }
        Object objInvoke2 = function1.invoke(coneFlexibleType.getUpperBound());
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) objInvoke2;
        if (z && coneKotlinType2 == coneFlexibleType.getUpperBound()) {
            objInvoke2 = null;
        }
        ConeKotlinType upperBound2 = (ConeKotlinType) objInvoke2;
        if (lowerBound2 == null && upperBound2 == null) {
            return null;
        }
        if (!(coneFlexibleType instanceof ConeRawType)) {
            if (lowerBound2 == null) {
                lowerBound2 = coneFlexibleType.getLowerBound();
            }
            if (upperBound2 == null) {
                upperBound2 = coneFlexibleType.getUpperBound();
            }
            return coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, upperBound2, false);
        }
        ConeRawType.Companion companion = ConeRawType.INSTANCE;
        if (lowerBound2 == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(lowerBound2)) == null) {
            lowerBound = coneFlexibleType.getLowerBound();
        }
        if (upperBound2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(upperBound2)) == null) {
            upperBound = coneFlexibleType.getUpperBound();
        }
        return companion.create(lowerBound, upperBound);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00ac A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:52:0x00ad A[RETURN] */
    public static final ConeKotlinType mapTypesOrSelf(ConeFlexibleType coneFlexibleType, ConeTypeContext coneTypeContext, boolean z, Function1<? super ConeRigidType, ? extends ConeKotlinType> function1) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        coneFlexibleType.getClass();
        coneTypeContext.getClass();
        function1.getClass();
        Object objInvoke = function1.invoke(coneFlexibleType.getLowerBound());
        ConeKotlinType coneKotlinType = (ConeKotlinType) objInvoke;
        ConeKotlinType coneKotlinTypeCreate = null;
        if (z && coneKotlinType == coneFlexibleType.getLowerBound()) {
            objInvoke = null;
        }
        ConeKotlinType lowerBound2 = (ConeKotlinType) objInvoke;
        if (coneFlexibleType.getIsTrivial()) {
            if (lowerBound2 != null) {
                if (lowerBound2 instanceof ConeRigidType) {
                    coneKotlinTypeCreate = coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, withNullability$default(lowerBound2, true, coneTypeContext, null, true, 4, null), true);
                } else {
                    if (!(lowerBound2 instanceof ConeFlexibleType)) {
                        bu8.a();
                        return null;
                    }
                    if (((ConeFlexibleType) lowerBound2).getAttributes().isEmpty()) {
                        coneKotlinTypeCreate = lowerBound2;
                    }
                }
            }
            if (coneKotlinTypeCreate == null) {
                return coneFlexibleType;
            }
            return coneKotlinTypeCreate;
        }
        Object objInvoke2 = function1.invoke(coneFlexibleType.getUpperBound());
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) objInvoke2;
        if (z && coneKotlinType2 == coneFlexibleType.getUpperBound()) {
            objInvoke2 = null;
        }
        ConeKotlinType upperBound2 = (ConeKotlinType) objInvoke2;
        if (lowerBound2 != null || upperBound2 != null) {
            if (coneFlexibleType instanceof ConeRawType) {
                ConeRawType.Companion companion = ConeRawType.INSTANCE;
                if (lowerBound2 == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(lowerBound2)) == null) {
                    lowerBound = coneFlexibleType.getLowerBound();
                }
                if (upperBound2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(upperBound2)) == null) {
                    upperBound = coneFlexibleType.getUpperBound();
                }
                coneKotlinTypeCreate = companion.create(lowerBound, upperBound);
            } else {
                if (lowerBound2 == null) {
                    lowerBound2 = coneFlexibleType.getLowerBound();
                }
                if (upperBound2 == null) {
                    upperBound2 = coneFlexibleType.getUpperBound();
                }
                coneKotlinTypeCreate = coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, upperBound2, false);
            }
        }
        if (coneKotlinTypeCreate == null) {
            return coneFlexibleType;
        }
        return coneKotlinTypeCreate;
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00b1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:55:0x00b2 A[RETURN] */
    public static /* synthetic */ ConeKotlinType mapTypesOrSelf$default(ConeFlexibleType coneFlexibleType, ConeTypeContext coneTypeContext, boolean z, Function1 function1, int i, Object obj) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        if ((i & 2) != 0) {
            z = false;
        }
        coneFlexibleType.getClass();
        coneTypeContext.getClass();
        function1.getClass();
        Object objInvoke = function1.invoke(coneFlexibleType.getLowerBound());
        ConeKotlinType coneKotlinType = (ConeKotlinType) objInvoke;
        ConeKotlinType coneKotlinTypeCreate = null;
        if (z && coneKotlinType == coneFlexibleType.getLowerBound()) {
            objInvoke = null;
        }
        ConeKotlinType lowerBound2 = (ConeKotlinType) objInvoke;
        if (coneFlexibleType.getIsTrivial()) {
            if (lowerBound2 != null) {
                if (lowerBound2 instanceof ConeRigidType) {
                    coneKotlinTypeCreate = coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, withNullability$default(lowerBound2, true, coneTypeContext, null, true, 4, null), true);
                } else {
                    if (!(lowerBound2 instanceof ConeFlexibleType)) {
                        bu8.a();
                        return null;
                    }
                    if (((ConeFlexibleType) lowerBound2).getAttributes().isEmpty()) {
                        coneKotlinTypeCreate = lowerBound2;
                    }
                }
            }
            if (coneKotlinTypeCreate == null) {
                return coneFlexibleType;
            }
            return coneKotlinTypeCreate;
        }
        Object objInvoke2 = function1.invoke(coneFlexibleType.getUpperBound());
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) objInvoke2;
        if (z && coneKotlinType2 == coneFlexibleType.getUpperBound()) {
            objInvoke2 = null;
        }
        ConeKotlinType upperBound2 = (ConeKotlinType) objInvoke2;
        if (lowerBound2 != null || upperBound2 != null) {
            if (coneFlexibleType instanceof ConeRawType) {
                ConeRawType.Companion companion = ConeRawType.INSTANCE;
                if (lowerBound2 == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(lowerBound2)) == null) {
                    lowerBound = coneFlexibleType.getLowerBound();
                }
                if (upperBound2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(upperBound2)) == null) {
                    upperBound = coneFlexibleType.getUpperBound();
                }
                coneKotlinTypeCreate = companion.create(lowerBound, upperBound);
            } else {
                if (lowerBound2 == null) {
                    lowerBound2 = coneFlexibleType.getLowerBound();
                }
                if (upperBound2 == null) {
                    upperBound2 = coneFlexibleType.getUpperBound();
                }
                coneKotlinTypeCreate = coneFlexibleOrSimpleType(coneTypeContext, lowerBound2, upperBound2, false);
            }
        }
        if (coneKotlinTypeCreate == null) {
            return coneFlexibleType;
        }
        return coneKotlinTypeCreate;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x007f  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeClassLikeType outerType(ConeClassLikeType coneClassLikeType, FirSession firSession, Function1<? super FirClassLikeSymbol<?>, ? extends FirClassLikeSymbol<?>> function1) {
        FirClassLikeSymbol firClassLikeSymbol;
        int i;
        FirRegularClass firRegularClass;
        List<FirTypeParameterRef> typeParameters;
        coneClassLikeType.getClass();
        firSession.getClass();
        function1.getClass();
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null);
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeTypeFullyExpandedType$default.getLookupTag(), firSession);
        if (symbol == null) {
            return null;
        }
        boolean z = symbol instanceof FirRegularClassSymbol;
        if ((z && !((FirMemberDeclaration) ((FirRegularClassSymbol) symbol).getFir()).getStatus().isInner()) || (firClassLikeSymbol = (FirClassLikeSymbol) function1.invoke(symbol)) == null) {
            return null;
        }
        FirRegularClassSymbol firRegularClassSymbol = z ? (FirRegularClassSymbol) symbol : null;
        if (firRegularClassSymbol == null || (firRegularClass = (FirRegularClass) firRegularClassSymbol.getFir()) == null || (typeParameters = firRegularClass.getTypeParameters()) == null) {
            i = 0;
        } else {
            List<FirTypeParameterRef> list = typeParameters;
            if ((list instanceof Collection) && list.isEmpty()) {
                i = 0;
            } else {
                Iterator<T> it = list.iterator();
                i = 0;
                while (it.hasNext()) {
                    if ((((FirTypeParameterRef) it.next()) instanceof FirTypeParameter) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
        }
        return TypeConstructionUtilsKt.constructType$default(firClassLikeSymbol, (ConeTypeProjection[]) ArraysKt.drop(coneClassLikeTypeFullyExpandedType$default.getTypeArguments(), i).toArray(new ConeTypeProjection[0]), false, (ConeAttributes) null, 6, (Object) null);
    }

    public static final <T extends ConeKotlinType> T removeAnnotations(T t) {
        t.getClass();
        return (T) withAttributes(t, t.getAttributes().remove(Reflection.getOrCreateKotlinClass(CustomAnnotationTypeAttribute.class)));
    }

    public static final boolean shouldApproximateLocalTypesOfNonLocalDeclaration(Visibility visibility, boolean z) {
        if (Intrinsics.areEqual(visibility, Visibilities.Public.INSTANCE) || Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE) || Intrinsics.areEqual(visibility, Visibilities.Internal.INSTANCE)) {
            return true;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE)) {
            return z;
        }
        return false;
    }

    public static final FirRegularClassSymbol toRegularClassSymbol(FirTypeRef firTypeRef, FirSession firSession) {
        firTypeRef.getClass();
        firSession.getClass();
        return ToSymbolUtilsKt.toRegularClassSymbol(FirTypeUtilsKt.getConeType(firTypeRef), firSession);
    }

    public static final ConeFlexibleType toTrivialFlexibleType(ConeRigidType coneRigidType, ConeTypeContext coneTypeContext) {
        coneRigidType.getClass();
        coneTypeContext.getClass();
        return new ConeFlexibleType(coneRigidType, (ConeRigidType) withNullability$default(coneRigidType, true, coneTypeContext, null, false, 12, null), true);
    }

    public static final Visibility visibilityForApproximation(FirDeclaration firDeclaration, FirBasedSymbol<?> firBasedSymbol) {
        Visibility visibility;
        firDeclaration.getClass();
        if (!(firDeclaration instanceof FirMemberDeclaration)) {
            return Visibilities.Local.INSTANCE;
        }
        if (firBasedSymbol == null || (firBasedSymbol instanceof FirFileSymbol) || (firBasedSymbol instanceof FirScriptSymbol) || (firBasedSymbol instanceof FirReplSnippetSymbol)) {
            visibility = Visibilities.Public.INSTANCE;
        } else {
            FirRegularClassSymbol firRegularClassSymbol = firBasedSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) firBasedSymbol : null;
            if (firRegularClassSymbol == null || (visibility = firRegularClassSymbol.getRawStatus().getVisibility()) == null) {
                visibility = Visibilities.Local.INSTANCE;
            }
        }
        Visibilities.Local local = Visibilities.Local.INSTANCE;
        if (Intrinsics.areEqual(visibility, local)) {
            return local;
        }
        FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
        return (Intrinsics.areEqual(firMemberDeclaration.getStatus().getVisibility(), local) && Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(firDeclaration), Boolean.TRUE)) ? Visibilities.Public.INSTANCE : firMemberDeclaration.getStatus().getVisibility();
    }

    public static final <T extends ConeKotlinType> T withAbbreviation(T t, AbbreviatedTypeAttribute abbreviatedTypeAttribute) {
        ConeAttributes attributes;
        t.getClass();
        abbreviatedTypeAttribute.getClass();
        AbbreviatedTypeAttribute abbreviatedType = AbbreviatedTypeAttributeKt.getAbbreviatedType(t.getAttributes());
        if (abbreviatedType == null || (attributes = t.getAttributes().remove(abbreviatedType)) == null) {
            attributes = t.getAttributes();
        }
        return (T) withAttributes(t, attributes.add(abbreviatedTypeAttribute));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final <T extends ConeKotlinType> T withArguments(T t, ConeTypeProjection[] coneTypeProjectionArr) throws KotlinIllegalArgumentExceptionWithAttachments {
        T coneErrorType;
        t.getClass();
        coneTypeProjectionArr.getClass();
        if (t.getTypeArguments() == coneTypeProjectionArr) {
            return t;
        }
        if (t instanceof ConeClassLikeTypeImpl) {
            ConeClassLikeTypeImpl coneClassLikeTypeImpl = (ConeClassLikeTypeImpl) t;
            coneErrorType = new ConeClassLikeTypeImpl(coneClassLikeTypeImpl.getLookupTag(), coneTypeProjectionArr, coneClassLikeTypeImpl.getIsMarkedNullable(), coneClassLikeTypeImpl.getAttributes());
        } else if (t instanceof ConeDefinitelyNotNullType) {
            coneErrorType = new ConeDefinitelyNotNullType((ConeSimpleKotlinType) withArguments(((ConeDefinitelyNotNullType) t).getOriginal(), coneTypeProjectionArr));
        } else if (t instanceof ConeRawType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) t;
            coneErrorType = ConeRawType.INSTANCE.create((ConeRigidType) withArguments(coneFlexibleType.getLowerBound(), coneTypeProjectionArr), (ConeRigidType) withArguments(coneFlexibleType.getUpperBound(), coneTypeProjectionArr));
        } else {
            if (t instanceof ConeDynamicType) {
                withArguments$error(t);
                wq6.a();
                return null;
            }
            if (t instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType2 = (ConeFlexibleType) t;
                coneErrorType = new ConeFlexibleType((ConeRigidType) withArguments(coneFlexibleType2.getLowerBound(), coneTypeProjectionArr), (ConeRigidType) withArguments(coneFlexibleType2.getUpperBound(), coneTypeProjectionArr), coneFlexibleType2.getIsTrivial());
            } else {
                if (!(t instanceof ConeErrorType)) {
                    if (!(t instanceof ConeIntersectionType) && !(t instanceof ConeTypeVariableType) && !(t instanceof ConeStubType) && !(t instanceof ConeIntegerLiteralType) && !(t instanceof ConeCapturedType) && !(t instanceof ConeLookupTagBasedType)) {
                        bu8.a();
                        return null;
                    }
                    withArguments$error(t);
                    wq6.a();
                    return null;
                }
                ConeErrorType coneErrorType2 = (ConeErrorType) t;
                coneErrorType = new ConeErrorType(coneErrorType2.getDiagnostic(), coneErrorType2.getIsUninferredParameter(), null, coneTypeProjectionArr, coneErrorType2.getAttributes(), null, coneErrorType2.getLookupTag(), 36, null);
            }
        }
        coneErrorType.getClass();
        return coneErrorType;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final <T extends ConeKotlinType> Void withArguments$error(T t) throws KotlinIllegalArgumentExceptionWithAttachments {
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Not supported: " + Reflection.getOrCreateKotlinClass(t.getClass()), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, ModuleXmlParser.TYPE, t);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final <T extends ConeKotlinType> T withAttributes(T t, ConeAttributes coneAttributes) throws KotlinIllegalArgumentExceptionWithAttachments {
        T coneTypeVariableType;
        t.getClass();
        coneAttributes.getClass();
        if (Intrinsics.areEqual(t.getAttributes(), coneAttributes)) {
            return t;
        }
        if (t instanceof ConeErrorType) {
            ConeErrorType coneErrorType = (ConeErrorType) t;
            t = new ConeErrorType(coneErrorType.getDiagnostic(), coneErrorType.getIsUninferredParameter(), coneErrorType.getDelegatedType(), coneErrorType.getTypeArguments(), coneAttributes, coneErrorType.getNullable(), coneErrorType.getLookupTag());
        } else {
            if (t instanceof ConeClassLikeTypeImpl) {
                ConeClassLikeTypeImpl coneClassLikeTypeImpl = (ConeClassLikeTypeImpl) t;
                coneTypeVariableType = new ConeClassLikeTypeImpl(coneClassLikeTypeImpl.getLookupTag(), coneClassLikeTypeImpl.getTypeArguments(), coneClassLikeTypeImpl.getIsMarkedNullable(), coneAttributes);
            } else if (t instanceof ConeDefinitelyNotNullType) {
                coneTypeVariableType = new ConeDefinitelyNotNullType((ConeSimpleKotlinType) withAttributes(((ConeDefinitelyNotNullType) t).getOriginal(), coneAttributes));
            } else if (t instanceof ConeTypeParameterTypeImpl) {
                ConeTypeParameterTypeImpl coneTypeParameterTypeImpl = (ConeTypeParameterTypeImpl) t;
                coneTypeVariableType = new ConeTypeParameterTypeImpl(coneTypeParameterTypeImpl.getLookupTag(), coneTypeParameterTypeImpl.getIsMarkedNullable(), coneAttributes);
            } else if (t instanceof ConeRawType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) t;
                t = ConeRawType.INSTANCE.create((ConeRigidType) withAttributes(coneFlexibleType.getLowerBound(), coneAttributes), (ConeRigidType) withAttributes(coneFlexibleType.getUpperBound(), coneAttributes));
            } else if (t instanceof ConeDynamicType) {
                ConeFlexibleType coneFlexibleType2 = (ConeFlexibleType) t;
                coneTypeVariableType = new ConeDynamicType((ConeRigidType) withAttributes(coneFlexibleType2.getLowerBound(), coneAttributes), (ConeRigidType) withAttributes(coneFlexibleType2.getUpperBound(), coneAttributes));
            } else if (t instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType3 = (ConeFlexibleType) t;
                coneTypeVariableType = new ConeFlexibleType((ConeRigidType) withAttributes(coneFlexibleType3.getLowerBound(), coneAttributes), (ConeRigidType) withAttributes(coneFlexibleType3.getUpperBound(), coneAttributes), coneFlexibleType3.getIsTrivial());
            } else if (t instanceof ConeTypeVariableType) {
                ConeTypeVariableType coneTypeVariableType2 = (ConeTypeVariableType) t;
                coneTypeVariableType = new ConeTypeVariableType(coneTypeVariableType2.getIsMarkedNullable(), coneTypeVariableType2.getTypeConstructor(), coneAttributes);
            } else if (t instanceof ConeCapturedType) {
                t = ConeCapturedType.copy$default((ConeCapturedType) t, false, null, coneAttributes, 3, null);
            } else if (!(t instanceof ConeIntersectionType) && !(t instanceof ConeStubType) && !(t instanceof ConeIntegerLiteralType)) {
                if (!(t instanceof ConeLookupTagBasedType)) {
                    bu8.a();
                    return null;
                }
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Not supported: " + Reflection.getOrCreateKotlinClass(t.getClass()), (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, ModuleXmlParser.TYPE, t);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
            t = coneTypeVariableType;
        }
        t.getClass();
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v58 */
    /* JADX WARN: Type inference failed for: r1v10, types: [org.jetbrains.kotlin.fir.types.ConeIntersectionType] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object] */
    public static final <T extends ConeKotlinType> T withNullability(T t, boolean z, ConeTypeContext coneTypeContext, ConeAttributes coneAttributes, boolean z2) {
        boolean z3;
        T t2;
        ConeAttributes coneAttributes2;
        T coneIntegerConstantOperatorTypeImpl;
        ConeAttributes coneAttributesCreate;
        ConeAttributeWithConeType coneAttributeWithConeTypeCopyWith;
        ConeKotlinType coneType;
        t.getClass();
        coneTypeContext.getClass();
        coneAttributes.getClass();
        if (z2) {
            z3 = z;
            t2 = null;
            coneAttributes2 = coneAttributes;
        } else {
            ConeAttributes coneAttributesRemove = coneAttributes.remove((ConeAttribute<?>) CompilerConeAttributes.EnhancedNullability.INSTANCE).remove(Reflection.getOrCreateKotlinClass(ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute.class));
            if (coneAttributesRemove.isEmpty()) {
                z3 = z;
                coneAttributesCreate = null;
                t2 = null;
            } else {
                int i = 0;
                boolean z4 = false;
                List<? extends ConeAttribute<?>> mutableList = null;
                for (ConeAttribute<?> coneAttribute : coneAttributesRemove) {
                    int i2 = i + 1;
                    if (coneAttribute instanceof ConeAttributeWithConeType) {
                        ConeAttributeWithConeType coneAttributeWithConeType = (ConeAttributeWithConeType) coneAttribute;
                        ConeKotlinType coneKotlinTypeWithNullability$default = withNullability$default(coneAttributeWithConeType.getConeType(), z, coneTypeContext, null, false, 12, null);
                        if (coneKotlinTypeWithNullability$default == null) {
                            coneAttributeWithConeTypeCopyWith = null;
                        } else if (!Intrinsics.areEqual(coneKotlinTypeWithNullability$default, coneAttributeWithConeType.getConeType())) {
                            ConeAttributeWithConeType coneAttributeWithConeType2 = (ConeAttributeWithConeType) coneKotlinTypeWithNullability$default.getAttributes().get(coneAttributeWithConeType.getKey());
                            if (coneAttributeWithConeType2 != null && (coneType = coneAttributeWithConeType2.getConeType()) != null) {
                                coneKotlinTypeWithNullability$default = coneType;
                            }
                            coneAttributeWithConeTypeCopyWith = coneAttributeWithConeType.copyWith(coneKotlinTypeWithNullability$default);
                        }
                        if (coneAttributeWithConeTypeCopyWith == null) {
                            coneAttributeWithConeTypeCopyWith = coneAttributeWithConeType;
                        } else {
                            if (mutableList == null) {
                                mutableList = CollectionsKt.toMutableList(coneAttributesRemove);
                            }
                            mutableList.set(i, coneAttributeWithConeTypeCopyWith);
                            z4 = z4 || !Intrinsics.areEqual(coneAttributeWithConeTypeCopyWith, coneAttribute);
                        }
                    }
                    i = i2;
                }
                z3 = z;
                t2 = null;
                coneAttributesCreate = (mutableList == null || z4) ? mutableList != null ? ConeAttributes.INSTANCE.create(mutableList) : null : coneAttributesRemove;
            }
            if (coneAttributesCreate != null) {
                coneAttributesRemove = coneAttributesCreate;
            }
            coneAttributes2 = coneAttributesRemove;
        }
        if (!ConeTypeUtilsKt.getHasFlexibleMarkedNullability(t) && ConeTypeUtilsKt.isMarkedNullable(t) == z3 && Intrinsics.areEqual(t.getAttributes(), coneAttributes2) && !(ConeTypeUtilsKt.lowerBoundIfFlexible(t) instanceof ConeIntersectionType)) {
            return t;
        }
        if (t instanceof ConeErrorType) {
            ConeErrorType coneErrorType = (ConeErrorType) t;
            coneIntegerConstantOperatorTypeImpl = new ConeErrorType(coneErrorType.getDiagnostic(), coneErrorType.getIsUninferredParameter(), coneErrorType.getDelegatedType(), coneErrorType.getTypeArguments(), coneAttributes2, Boolean.valueOf(z3), coneErrorType.getLookupTag());
        } else {
            ?? r1 = t;
            boolean z5 = z3;
            ConeAttributes coneAttributes3 = coneAttributes2;
            if (r1 instanceof ConeClassLikeTypeImpl) {
                ConeClassLikeTypeImpl coneClassLikeTypeImpl = (ConeClassLikeTypeImpl) r1;
                coneIntegerConstantOperatorTypeImpl = new ConeClassLikeTypeImpl(coneClassLikeTypeImpl.getLookupTag(), coneClassLikeTypeImpl.getTypeArguments(), z5, coneAttributes3);
            } else if (r1 instanceof ConeTypeParameterTypeImpl) {
                coneIntegerConstantOperatorTypeImpl = new ConeTypeParameterTypeImpl(((ConeTypeParameterTypeImpl) r1).getLookupTag(), z5, coneAttributes3);
            } else if (r1 instanceof ConeDynamicType) {
                coneIntegerConstantOperatorTypeImpl = (T) r1;
            } else if (r1 instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) r1;
                coneIntegerConstantOperatorTypeImpl = coneFlexibleType.getIsTrivial() ? (T) withNullability$default(coneFlexibleType.getLowerBound(), z, coneTypeContext, null, z2, 4, null) : (T) coneFlexibleOrSimpleType(coneTypeContext, withNullability$default(coneFlexibleType.getLowerBound(), z, coneTypeContext, null, z2, 4, null), withNullability$default(coneFlexibleType.getUpperBound(), z, coneTypeContext, null, z2, 4, null), false);
            } else if (r1 instanceof ConeTypeVariableType) {
                coneIntegerConstantOperatorTypeImpl = new ConeTypeVariableType(z5, ((ConeTypeVariableType) r1).getTypeConstructor(), coneAttributes3);
            } else if (r1 instanceof ConeCapturedType) {
                coneIntegerConstantOperatorTypeImpl = ConeCapturedType.copy$default((ConeCapturedType) r1, z5, null, coneAttributes3, 2, null);
            } else if (r1 instanceof ConeIntersectionType) {
                if (z5) {
                    ConeIntersectionType coneIntersectionType = (ConeIntersectionType) r1;
                    Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
                    Iterator it = intersectedTypes.iterator();
                    while (it.hasNext()) {
                        arrayList.add(withNullability$default((ConeKotlinType) it.next(), true, coneTypeContext, null, z2, 4, null));
                    }
                    ConeKotlinType upperBoundForApproximation = coneIntersectionType.getUpperBoundForApproximation();
                    coneIntegerConstantOperatorTypeImpl = new ConeIntersectionType(arrayList, upperBoundForApproximation != null ? withNullability$default(upperBoundForApproximation, true, coneTypeContext, null, z2, 4, null) : t2);
                } else {
                    if (z5) {
                        bu8.a();
                        return t2;
                    }
                    r1 = (ConeIntersectionType) r1;
                    Collection<ConeKotlinType> intersectedTypes2 = r1.getIntersectedTypes();
                    if (!(intersectedTypes2 instanceof Collection) || !intersectedTypes2.isEmpty()) {
                        Iterator it2 = intersectedTypes2.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (!ConeTypeUtilsKt.isMarkedOrFlexiblyNullable((ConeKotlinType) it2.next())) {
                                    coneIntegerConstantOperatorTypeImpl = (T) r1;
                                }
                            }
                        }
                    }
                    Collection<ConeKotlinType> intersectedTypes3 = r1.getIntersectedTypes();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes3, 10));
                    Iterator it3 = intersectedTypes3.iterator();
                    while (it3.hasNext()) {
                        arrayList2.add(withNullability$default((ConeKotlinType) it3.next(), false, coneTypeContext, null, z2, 4, null));
                    }
                    ConeKotlinType upperBoundForApproximation2 = r1.getUpperBoundForApproximation();
                    coneIntegerConstantOperatorTypeImpl = new ConeIntersectionType(arrayList2, upperBoundForApproximation2 != null ? withNullability$default(upperBoundForApproximation2, false, coneTypeContext, null, z2, 4, null) : t2);
                }
            } else if (r1 instanceof ConeStubTypeForTypeVariableInSubtyping) {
                coneIntegerConstantOperatorTypeImpl = new ConeStubTypeForTypeVariableInSubtyping(((ConeStubType) r1).getConstructor(), z5);
            } else if (r1 instanceof ConeDefinitelyNotNullType) {
                if (!z5) {
                    coneIntegerConstantOperatorTypeImpl = (ConeRigidType) r1;
                } else {
                    if (!z5) {
                        bu8.a();
                        return t2;
                    }
                    coneIntegerConstantOperatorTypeImpl = (ConeRigidType) withNullability$default(((ConeDefinitelyNotNullType) r1).getOriginal(), true, coneTypeContext, null, z2, 4, null);
                }
            } else if (r1 instanceof ConeIntegerLiteralConstantType) {
                long value = ((ConeIntegerLiteralConstantType) r1).getValue();
                ConeIntegerLiteralType coneIntegerLiteralType = (ConeIntegerLiteralType) r1;
                coneIntegerConstantOperatorTypeImpl = new ConeIntegerLiteralConstantTypeImpl(value, coneIntegerLiteralType.getPossibleTypes(), coneIntegerLiteralType.getIsUnsigned(), z5);
            } else {
                if (!(r1 instanceof ConeIntegerConstantOperatorType)) {
                    if (r1 instanceof ConeLookupTagBasedType) {
                        f2f.a("sealed: ", Reflection.getOrCreateKotlinClass(r1.getClass()));
                        return t2;
                    }
                    bu8.a();
                    return t2;
                }
                coneIntegerConstantOperatorTypeImpl = new ConeIntegerConstantOperatorTypeImpl(((ConeIntegerLiteralType) r1).getIsUnsigned(), z5);
            }
        }
        coneIntegerConstantOperatorTypeImpl.getClass();
        return coneIntegerConstantOperatorTypeImpl;
    }

    public static /* synthetic */ ConeKotlinType withNullability$default(ConeKotlinType coneKotlinType, boolean z, ConeTypeContext coneTypeContext, ConeAttributes coneAttributes, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            coneAttributes = coneKotlinType.getAttributes();
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return withNullability(coneKotlinType, z, coneTypeContext, coneAttributes, z2);
    }

    public static final <T extends ConeKotlinType> T withNullabilityOf(T t, ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext) {
        t.getClass();
        coneKotlinType.getClass();
        coneTypeContext.getClass();
        return (ConeTypeUtilsKt.getHasFlexibleMarkedNullability(t) && ConeTypeUtilsKt.getHasFlexibleMarkedNullability(coneKotlinType)) ? t : (T) withNullability$default(t, ConeTypeUtilsKt.isMarkedNullable(coneKotlinType), coneTypeContext, null, false, 12, null);
    }

    public static final FirResolvedTypeRef withReplacedConeType(FirTypeRef firTypeRef, ConeKotlinType coneKotlinType, KtFakeSourceElementKind ktFakeSourceElementKind) {
        firTypeRef.getClass();
        KtSourceElement source = null;
        if (!(firTypeRef instanceof FirResolvedTypeRef)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (coneKotlinType == null) {
            return (FirResolvedTypeRef) firTypeRef;
        }
        if (ktFakeSourceElementKind != null) {
            KtSourceElement source2 = ((FirResolvedTypeRef) firTypeRef).getSource();
            if (source2 != null) {
                source = KtSourceElementKt.fakeElement$default(source2, ktFakeSourceElementKind, null, 2, null);
            }
        } else {
            source = ((FirResolvedTypeRef) firTypeRef).getSource();
        }
        return withReplacedSourceAndType((FirResolvedTypeRef) firTypeRef, source, coneKotlinType);
    }

    public static /* synthetic */ FirResolvedTypeRef withReplacedConeType$default(FirTypeRef firTypeRef, ConeKotlinType coneKotlinType, KtFakeSourceElementKind ktFakeSourceElementKind, int i, Object obj) {
        if ((i & 2) != 0) {
            ktFakeSourceElementKind = null;
        }
        return withReplacedConeType(firTypeRef, coneKotlinType, ktFakeSourceElementKind);
    }

    public static final FirTypeRef withReplacedReturnType(FirTypeRef firTypeRef, ConeKotlinType coneKotlinType) {
        firTypeRef.getClass();
        if ((firTypeRef instanceof FirResolvedTypeRef) || coneKotlinType == null) {
            return coneKotlinType == null ? firTypeRef : CopyUtilsKt.resolvedTypeFromPrototype(firTypeRef, coneKotlinType, null);
        }
        w01.a("Failed requirement.");
        return null;
    }

    public static final FirResolvedTypeRef withReplacedSourceAndType(FirResolvedTypeRef firResolvedTypeRef, KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType) {
        FirTypeRef partiallyResolvedTypeRef;
        firResolvedTypeRef.getClass();
        coneKotlinType.getClass();
        boolean z = firResolvedTypeRef instanceof FirErrorTypeRef;
        FirTypeRef firTypeRefCopyWithNewSource = null;
        FirErrorTypeRef firErrorTypeRef = z ? (FirErrorTypeRef) firResolvedTypeRef : null;
        if (firErrorTypeRef != null && (partiallyResolvedTypeRef = firErrorTypeRef.getPartiallyResolvedTypeRef()) != null) {
            firTypeRefCopyWithNewSource = ktSourceElement != null ? UtilsKt.copyWithNewSource(partiallyResolvedTypeRef, ktSourceElement) : partiallyResolvedTypeRef;
        }
        if (coneKotlinType instanceof ConeErrorType) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setSource(ktSourceElement);
            firErrorTypeRefBuilder.setConeType(coneKotlinType);
            CollectionsKt.addAll(firErrorTypeRefBuilder.getAnnotations(), firResolvedTypeRef.getAnnotations());
            firErrorTypeRefBuilder.setDiagnostic(((ConeErrorType) coneKotlinType).getDiagnostic());
            firErrorTypeRefBuilder.setPartiallyResolvedTypeRef(firTypeRefCopyWithNewSource);
            return firErrorTypeRefBuilder.build();
        }
        if (!z) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setSource(ktSourceElement);
            firResolvedTypeRefBuilder.setConeType(coneKotlinType);
            CollectionsKt.addAll(firResolvedTypeRefBuilder.getAnnotations(), firResolvedTypeRef.getAnnotations());
            firResolvedTypeRefBuilder.setDelegatedTypeRef(firResolvedTypeRef.getDelegatedTypeRef());
            return firResolvedTypeRefBuilder.build();
        }
        FirErrorTypeRefBuilder firErrorTypeRefBuilder2 = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder2.setSource(ktSourceElement);
        firErrorTypeRefBuilder2.setConeType(coneKotlinType);
        FirErrorTypeRef firErrorTypeRef2 = (FirErrorTypeRef) firResolvedTypeRef;
        CollectionsKt.addAll(firErrorTypeRefBuilder2.getAnnotations(), firErrorTypeRef2.getAnnotations());
        firErrorTypeRefBuilder2.setDiagnostic(firErrorTypeRef2.getDiagnostic());
        firErrorTypeRefBuilder2.setDelegatedTypeRef(firErrorTypeRef2.getDelegatedTypeRef());
        firErrorTypeRefBuilder2.setPartiallyResolvedTypeRef(firTypeRefCopyWithNewSource);
        return firErrorTypeRefBuilder2.build();
    }

    public static final FirResolvedTypeRef withoutEnhancedNullability(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        if (!(firTypeRef instanceof FirResolvedTypeRef)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (!hasEnhancedNullability(firTypeRef)) {
            return (FirResolvedTypeRef) firTypeRef;
        }
        FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) firTypeRef;
        KtSourceElement source = firResolvedTypeRef.getSource();
        ConeKotlinType coneType = firResolvedTypeRef.getConeType();
        ConeAttributes.Companion companion = ConeAttributes.INSTANCE;
        ConeAttributes attributes = firResolvedTypeRef.getConeType().getAttributes();
        ArrayList arrayList = new ArrayList();
        for (ConeAttribute<?> coneAttribute : attributes) {
            if (!Intrinsics.areEqual(coneAttribute, CompilerConeAttributes.EnhancedNullability.INSTANCE)) {
                arrayList.add(coneAttribute);
            }
        }
        return withReplacedSourceAndType(firResolvedTypeRef, source, withAttributes(coneType, companion.create(arrayList)));
    }

    public static /* synthetic */ ConeDefinitelyNotNullType create$default(ConeDefinitelyNotNullType.Companion companion, ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return create(companion, coneKotlinType, coneTypeContext, z);
    }

    public static /* synthetic */ ConeKotlinType makeConeTypeDefinitelyNotNullOrNotNull$default(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return makeConeTypeDefinitelyNotNullOrNotNull(coneKotlinType, coneTypeContext, z, z2);
    }

    public static final boolean equalTypes(TypeCheckerProviderContext typeCheckerProviderContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        typeCheckerProviderContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return AbstractTypeChecker.equalTypes$default(AbstractTypeChecker.INSTANCE, typeCheckerProviderContext, coneKotlinType, coneKotlinType2, false, false, 24, (Object) null);
    }

    public static final ConeKotlinType getProjectionForRawType(FirTypeParameterSymbol firTypeParameterSymbol, FirSession firSession, boolean z) {
        firTypeParameterSymbol.getClass();
        firSession.getClass();
        return getProjectionForRawType(firTypeParameterSymbol, firSession, new HashMap(), z);
    }

    public static final boolean isSubtypeOf(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, boolean z) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firSession.getClass();
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeCheckerProviderContext.newTypeCheckerState$default(TypeComponentsKt.getTypeContext(firSession), z, false, false, 4, (Object) null), coneKotlinType, coneKotlinType2, false, 8, (Object) null);
    }

    public static final boolean isExtensionFunctionType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        return CompilerConeAttributesKt.getExtensionFunctionType(TypeExpansionUtilsKt.fullyExpandedType$default(ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinType), firSession, (Function1) null, 2, (Object) null).getAttributes()) != null;
    }

    public static final boolean isSubtypeOf(KotlinTypeMarker kotlinTypeMarker, TypeCheckerProviderContext typeCheckerProviderContext, KotlinTypeMarker kotlinTypeMarker2) {
        kotlinTypeMarker.getClass();
        typeCheckerProviderContext.getClass();
        return kotlinTypeMarker2 != null && AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerProviderContext, kotlinTypeMarker, kotlinTypeMarker2, false, 8, (Object) null);
    }

    public static final ConeDynamicType create(ConeDynamicType.Companion companion, FirSession firSession, ConeAttributes coneAttributes) {
        companion.getClass();
        firSession.getClass();
        coneAttributes.getClass();
        return new ConeDynamicType((ConeRigidType) withAttributes(firSession.getBuiltinTypes().getNothingType().getConeType(), coneAttributes), (ConeRigidType) withAttributes(firSession.getBuiltinTypes().getNullableAnyType().getConeType(), coneAttributes));
    }

    public static final ConeRigidType makeConeTypeDefinitelyNotNullOrNotNull(ConeRigidType coneRigidType, ConeTypeContext coneTypeContext, boolean z, boolean z2) {
        coneRigidType.getClass();
        coneTypeContext.getClass();
        ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull = makeConeTypeDefinitelyNotNullOrNotNull((ConeKotlinType) coneRigidType, coneTypeContext, z, z2);
        coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull.getClass();
        return (ConeRigidType) coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends ConeKotlinType> T withArguments(T t, Function1<? super ConeTypeProjection, ? extends ConeTypeProjection> function1) {
        t.getClass();
        function1.getClass();
        ConeTypeProjection[] typeArguments = t.getTypeArguments();
        if (typeArguments.length == 0) {
            return t;
        }
        int length = typeArguments.length;
        ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
        for (int i = 0; i < length; i++) {
            coneTypeProjectionArr[i] = function1.invoke(typeArguments[i]);
        }
        return (T) withArguments(t, coneTypeProjectionArr);
    }
}
