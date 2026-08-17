package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.builtins.UnsignedType;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.SymbolConversionUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeEquivalentCallConflictResolver;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.SimpleTypeNullability;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bc\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0001b\u0002\b\u0017¢\u0006\u0002\b\u0016J'\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0001b\u0002\b\u0017¢\u0006\u0002\b\u001aJ\u0014\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0013\u0010\u009d\u0001\u001a\u00020\u001c2\b\u0010\u009e\u0001\u001a\u00030\u0093\u0001H\u0002J\u0013\u0010·\u0001\u001a\u00020\u001c2\b\u0010¸\u0001\u001a\u00030¹\u0001H\u0002J.\u0010í\u0001\u001a\u0010\u0012\u0005\u0012\u00030Þ\u0001\u0012\u0004\u0012\u00020\n0\u0092\u00012\u0015\u0010î\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0093\u0001\u0012\u0004\u0012\u00020\n0\u0092\u0001H\u0002JH\u0010ï\u0001\u001a\u001d\u0012\u0005\u0012\u00030Þ\u0001\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n0ð\u00010\u0092\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0016\u0010ñ\u0001\u001a\f\u0012\u0007\b\u0001\u0012\u00030ó\u00010ò\u0001\"\u00030ó\u0001¢\u0006\u0003\u0010ô\u0001J\u001b\u0010õ\u0001\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u0013H\u0001b\u0002\b\u0017¢\u0006\u0003\bö\u0001J\u001d\u0010÷\u0001\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0012\u001a\u00020\u0013H\u0001b\u0002\b\u0017¢\u0006\u0003\bø\u0001J#\u0010ù\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\u00102\b\u0010ú\u0001\u001a\u00030û\u0001H\u0001b\u0002\b\u0017¢\u0006\u0003\bü\u0001J$\u0010ý\u0001\u001a\t\u0012\u0005\u0012\u00030þ\u00010\u00102\b\u0010ú\u0001\u001a\u00030û\u0001H\u0001b\u0002\b\u0017¢\u0006\u0003\bÿ\u0001J+\u0010\u0080\u0002\u001a\t\u0012\u0005\u0012\u0003H\u0081\u00020\u0010\"\u000e\b\u0000\u0010\u0081\u0002*\u0007\u0012\u0002\b\u00030\u0082\u0002*\t\u0012\u0005\u0012\u0003H\u0081\u00020\u0010H\u0002J\u0092\u0001\u0010\u0083\u0002\u001a\u0011\u0012\u0005\u0012\u0003H\u0084\u0002\u0012\u0005\u0012\u0003H\u0081\u00020\u0092\u0001\"\n\b\u0000\u0010\u0084\u0002*\u00030\u0085\u0002\"\u0005\b\u0001\u0010\u0081\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0016\u0010ñ\u0001\u001a\f\u0012\u0007\b\u0001\u0012\u00030ó\u00010ò\u0001\"\u00030ó\u00012\u0017\u0010\u0086\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0011\u0012\u0007\u0012\u0005\u0018\u0001H\u0084\u00020\u0087\u00022\u001b\u0010\u0088\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n\u0012\u0005\u0012\u0003H\u0081\u00020\u0089\u0002H\u0081\bb\u0002\b\u0017ø\u0001\u0000¢\u0006\u0006\b\u008a\u0002\u0010\u008b\u0002J\u001c\u0010\u008c\u0002\u001a\u00020\n2\u0007\u0010\u008d\u0002\u001a\u00020\u0011H\u0001b\u0002\b\u0017¢\u0006\u0003\b\u008e\u0002J,\u0010ý\u0001\u001a\t\u0012\u0005\u0012\u00030þ\u00010\u00102\b\u0010\u008f\u0002\u001a\u00030\u0090\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0001b\u0002\b\u0017¢\u0006\u0003\bÿ\u0001J\u001d\u0010\u0091\u0002\u001a\u00030þ\u00012\u0007\u0010\u0092\u0002\u001a\u00020\u0019H\u0001b\u0002\b\u0017¢\u0006\u0003\b\u0093\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u001b\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u000e\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010 \u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001b\u0010$\u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u000e\u001a\u0004\b%\u0010#R\u001b\u0010'\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u000e\u001a\u0004\b(\u0010\u001eR\u001b\u0010*\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u000e\u001a\u0004\b+\u0010\u001eR\u0011\u0010-\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b.\u0010#R\u001b\u0010/\u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u000e\u001a\u0004\b0\u0010#R\u001b\u00102\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\u000e\u001a\u0004\b3\u0010\u001eR\u0011\u00105\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b6\u0010#R\u001b\u00107\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u000e\u001a\u0004\b8\u0010\u001eR\u0011\u0010:\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b;\u0010#R\u001b\u0010<\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\u000e\u001a\u0004\b=\u0010\u001eR\u0011\u0010?\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b@\u0010#R\u001b\u0010A\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bC\u0010\u000e\u001a\u0004\bB\u0010\u001eR\u0011\u0010D\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bE\u0010#R\u001b\u0010F\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\u000e\u001a\u0004\bG\u0010\u001eR\u0011\u0010I\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bJ\u0010#R\u001b\u0010K\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bM\u0010\u000e\u001a\u0004\bL\u0010\u001eR\u0011\u0010N\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bO\u0010#R\u001b\u0010P\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bR\u0010\u000e\u001a\u0004\bQ\u0010\u001eR\u0011\u0010S\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bT\u0010#R\u001d\u0010U\u001a\u0004\u0018\u00010\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bW\u0010\u000e\u001a\u0004\bV\u0010\u001eR\u001b\u0010X\u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010\u000e\u001a\u0004\bY\u0010#R\u001d\u0010[\u001a\u0004\u0018\u00010\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b]\u0010\u000e\u001a\u0004\b\\\u0010\u001eR\u001b\u0010^\u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b`\u0010\u000e\u001a\u0004\b_\u0010#R\u001d\u0010a\u001a\u0004\u0018\u00010\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bc\u0010\u000e\u001a\u0004\bb\u0010\u001eR\u001b\u0010d\u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bf\u0010\u000e\u001a\u0004\be\u0010#R\u001d\u0010g\u001a\u0004\u0018\u00010\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bi\u0010\u000e\u001a\u0004\bh\u0010\u001eR\u001b\u0010j\u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bl\u0010\u000e\u001a\u0004\bk\u0010#R\u001b\u0010m\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bo\u0010\u000e\u001a\u0004\bn\u0010\u001eR\u0011\u0010p\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bq\u0010#R\u001b\u0010r\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bt\u0010\u000e\u001a\u0004\bs\u0010\u001eR\u0011\u0010u\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bv\u0010#R\u001b\u0010w\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\by\u0010\u000e\u001a\u0004\bx\u0010\u001eR\u001b\u0010z\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b|\u0010\u000e\u001a\u0004\b{\u0010\u001eR\u0011\u0010}\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b~\u0010#R\u001d\u0010\u007f\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010\u000e\u001a\u0005\b\u0080\u0001\u0010\u001eR\u0013\u0010\u0082\u0001\u001a\u00020!8F¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u0010#R\"\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u00018FX\u0086\u0084\u0002¢\u0006\u000f\n\u0005\b\u0088\u0001\u0010\u000e\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\"\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0085\u00018FX\u0086\u0084\u0002¢\u0006\u000f\n\u0005\b\u008b\u0001\u0010\u000e\u001a\u0006\b\u008a\u0001\u0010\u0087\u0001R\u001e\u0010\u008e\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u0090\u0001\u0010\u000e\u001a\u0005\b\u008f\u0001\u0010\u001eR9\u0010\u0091\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0005\u0012\u00030\u0093\u00010\u0092\u00018@X\u0081\u0084\u0002r\u0002\b\u0017¢\u0006\u0017\n\u0005\b\u0098\u0001\u0010\u000e\u0012\u0006\b\u0094\u0001\u0010\u0095\u0001\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R9\u0010\u0099\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0093\u0001\u0012\u0004\u0012\u00020!0\u0092\u00018@X\u0081\u0084\u0002r\u0002\b\u0017¢\u0006\u0017\n\u0005\b\u009c\u0001\u0010\u000e\u0012\u0006\b\u009a\u0001\u0010\u0095\u0001\u001a\u0006\b\u009b\u0001\u0010\u0097\u0001R\u001e\u0010\u009f\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¡\u0001\u0010\u000e\u001a\u0005\b \u0001\u0010\u001eR\u001e\u0010¢\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¤\u0001\u0010\u000e\u001a\u0005\b£\u0001\u0010\u001eR\u001e\u0010¥\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b§\u0001\u0010\u000e\u001a\u0005\b¦\u0001\u0010\u001eR\u001e\u0010¨\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bª\u0001\u0010\u000e\u001a\u0005\b©\u0001\u0010\u001eR\u001e\u0010«\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b\u00ad\u0001\u0010\u000e\u001a\u0005\b¬\u0001\u0010\u001eR\u001e\u0010®\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b°\u0001\u0010\u000e\u001a\u0005\b¯\u0001\u0010\u001eR\u001e\u0010±\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b³\u0001\u0010\u000e\u001a\u0005\b²\u0001\u0010\u001eR\u001e\u0010´\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¶\u0001\u0010\u000e\u001a\u0005\bµ\u0001\u0010\u001eR\u001e\u0010º\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¼\u0001\u0010\u000e\u001a\u0005\b»\u0001\u0010\u001eR\u001e\u0010½\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\b¿\u0001\u0010\u000e\u001a\u0005\b¾\u0001\u0010\u001eR\u001e\u0010À\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÂ\u0001\u0010\u000e\u001a\u0005\bÁ\u0001\u0010\u001eR\u001e\u0010Ã\u0001\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\u000e\n\u0005\bÅ\u0001\u0010\u000e\u001a\u0005\bÄ\u0001\u0010\u001eR9\u0010Æ\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0005\u0012\u00030\u0093\u00010\u0092\u00018@X\u0081\u0084\u0002r\u0002\b\u0017¢\u0006\u0017\n\u0005\bÉ\u0001\u0010\u000e\u0012\u0006\bÇ\u0001\u0010\u0095\u0001\u001a\u0006\bÈ\u0001\u0010\u0097\u0001R#\u0010Ê\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010!0\u0092\u00018F¢\u0006\b\u001a\u0006\bË\u0001\u0010\u0097\u0001R#\u0010Ì\u0001\u001a\u0011\u0012\u0006\u0012\u0004\u0018\u00010!\u0012\u0004\u0012\u00020\u001c0\u0092\u00018F¢\u0006\b\u001a\u0006\bÍ\u0001\u0010\u0097\u0001R-\u0010Î\u0001\u001a\u0010\u0012\u0005\u0012\u00030¹\u0001\u0012\u0004\u0012\u00020\u001c0\u0092\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bÐ\u0001\u0010\u000e\u001a\u0006\bÏ\u0001\u0010\u0097\u0001R6\u0010Ñ\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010!0\u0092\u00018FX\u0086\u0084\u0002¢\u0006\u0017\n\u0005\bÔ\u0001\u0010\u000e\u0012\u0006\bÒ\u0001\u0010\u0095\u0001\u001a\u0006\bÓ\u0001\u0010\u0097\u0001R\u0013\u0010Õ\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÖ\u0001\u0010\fR\u0013\u0010×\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bØ\u0001\u0010\fR\u0013\u0010Ù\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÚ\u0001\u0010\fR\u0013\u0010Û\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÜ\u0001\u0010\fR-\u0010Ý\u0001\u001a\u0010\u0012\u0005\u0012\u00030Þ\u0001\u0012\u0004\u0012\u00020\n0\u0092\u00018FX\u0086\u0084\u0002¢\u0006\u000f\n\u0005\bà\u0001\u0010\u000e\u001a\u0006\bß\u0001\u0010\u0097\u0001R-\u0010á\u0001\u001a\u0010\u0012\u0005\u0012\u00030Þ\u0001\u0012\u0004\u0012\u00020\n0\u0092\u00018FX\u0086\u0084\u0002¢\u0006\u000f\n\u0005\bã\u0001\u0010\u000e\u001a\u0006\bâ\u0001\u0010\u0097\u0001R-\u0010ä\u0001\u001a\u0010\u0012\u0005\u0012\u00030Þ\u0001\u0012\u0004\u0012\u00020\n0\u0092\u00018FX\u0086\u0084\u0002¢\u0006\u000f\n\u0005\bæ\u0001\u0010\u000e\u001a\u0006\bå\u0001\u0010\u0097\u0001R-\u0010ç\u0001\u001a\u0010\u0012\u0005\u0012\u00030Þ\u0001\u0012\u0004\u0012\u00020\n0\u0092\u00018FX\u0086\u0084\u0002¢\u0006\u000f\n\u0005\bé\u0001\u0010\u000e\u001a\u0006\bè\u0001\u0010\u0097\u0001R-\u0010ê\u0001\u001a\u0010\u0012\u0005\u0012\u00030Þ\u0001\u0012\u0004\u0012\u00020\n0\u0092\u00018FX\u0086\u0084\u0002¢\u0006\u000f\n\u0005\bì\u0001\u0010\u000e\u001a\u0006\bë\u0001\u0010\u0097\u0001R\u0016\u0010\u0094\u0002\u001a\u00030\u0095\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0096\u0002\u0010\u0097\u0002R\u0016\u0010\u0098\u0002\u001a\u00030\u0099\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009a\u0002\u0010\u009b\u0002R\u0016\u0010\u009c\u0002\u001a\u00030\u009d\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009e\u0002\u0010\u009f\u0002R\u0015\u0010 \u0002\u001a\u00020\u0000X\u0096\u0005¢\u0006\b\u001a\u0006\b¡\u0002\u0010¢\u0002R\u0016\u0010£\u0002\u001a\u00030¤\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b¥\u0002\u0010¦\u0002R\u0016\u0010§\u0002\u001a\u00030¨\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b©\u0002\u0010ª\u0002R\u0016\u0010«\u0002\u001a\u00030¬\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b\u00ad\u0002\u0010®\u0002R\u0016\u0010¯\u0002\u001a\u00030°\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b±\u0002\u0010²\u0002R\u0016\u0010³\u0002\u001a\u00030´\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bµ\u0002\u0010¶\u0002R\u0016\u0010·\u0002\u001a\u00030¸\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b¹\u0002\u0010º\u0002R\u0016\u0010»\u0002\u001a\u00030¼\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b½\u0002\u0010¾\u0002R\u0016\u0010¿\u0002\u001a\u00030À\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÁ\u0002\u0010Â\u0002R\u0016\u0010Ã\u0002\u001a\u00030Ä\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÅ\u0002\u0010Æ\u0002R\u001f\u0010Ç\u0002\u001a\f\u0012\u0005\u0012\u00030É\u0002\u0018\u00010È\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÊ\u0002\u0010Ë\u0002R\u0016\u0010Ì\u0002\u001a\u00030Í\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÎ\u0002\u0010Ï\u0002R\u0016\u0010Ð\u0002\u001a\u00030Ñ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÒ\u0002\u0010Ó\u0002R\u0016\u0010Ô\u0002\u001a\u00030Õ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÖ\u0002\u0010×\u0002R\u001c\u0010Ø\u0002\u001a\t\u0012\u0005\u0012\u00030Ù\u00020\u0010X\u0096\u0005¢\u0006\b\u001a\u0006\bÚ\u0002\u0010Û\u0002R\u0016\u0010Ü\u0002\u001a\u00030Ý\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÞ\u0002\u0010ß\u0002R\u0016\u0010à\u0002\u001a\u00030á\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bâ\u0002\u0010ã\u0002R\u0016\u0010ä\u0002\u001a\u00030å\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bæ\u0002\u0010ç\u0002R\u0016\u0010è\u0002\u001a\u00030é\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bê\u0002\u0010ë\u0002R\u0016\u0010ì\u0002\u001a\u00030í\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bî\u0002\u0010ï\u0002R\u0018\u0010ð\u0002\u001a\u0005\u0018\u00010ñ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bò\u0002\u0010ó\u0002R\u0016\u0010ô\u0002\u001a\u00030õ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bö\u0002\u0010÷\u0002R\u0016\u0010ø\u0002\u001a\u00030ù\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bú\u0002\u0010û\u0002R\u0016\u0010ü\u0002\u001a\u00030ý\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bþ\u0002\u0010ÿ\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0080\u0003"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "syntheticSymbolsContainer", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;)V", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "booleanNotSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getBooleanNotSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "booleanNotSymbol$delegate", "Lkotlin/Lazy;", "findFirMemberFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "findFirMemberFunctions$org_jetbrains_kotlin_fir2ir", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltInsInternals;", "findFirMemberProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "findFirMemberProperties$org_jetbrains_kotlin_fir2ir", "anyClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getAnyClass", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "anyClass$delegate", "anyType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "getAnyType", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "anyNType", "getAnyNType", "anyNType$delegate", "numberClass", "getNumberClass", "numberClass$delegate", "nothingClass", "getNothingClass", "nothingClass$delegate", "nothingType", "getNothingType", "nothingNType", "getNothingNType", "nothingNType$delegate", "unitClass", "getUnitClass", "unitClass$delegate", "unitType", "getUnitType", "booleanClass", "getBooleanClass", "booleanClass$delegate", "booleanType", "getBooleanType", "charClass", "getCharClass", "charClass$delegate", "charType", "getCharType", "byteClass", "getByteClass", "byteClass$delegate", "byteType", "getByteType", "shortClass", "getShortClass", "shortClass$delegate", "shortType", "getShortType", "intClass", "getIntClass", "intClass$delegate", "intType", "getIntType", "longClass", "getLongClass", "longClass$delegate", "longType", "getLongType", "ubyteClass", "getUbyteClass", "ubyteClass$delegate", "ubyteType", "getUbyteType", "ubyteType$delegate", "ushortClass", "getUshortClass", "ushortClass$delegate", "ushortType", "getUshortType", "ushortType$delegate", "uintClass", "getUintClass", "uintClass$delegate", "uintType", "getUintType", "uintType$delegate", "ulongClass", "getUlongClass", "ulongClass$delegate", "ulongType", "getUlongType", "ulongType$delegate", "floatClass", "getFloatClass", "floatClass$delegate", "floatType", "getFloatType", "doubleClass", "getDoubleClass", "doubleClass$delegate", "doubleType", "getDoubleType", "charSequenceClass", "getCharSequenceClass", "charSequenceClass$delegate", "stringClass", "getStringClass", "stringClass$delegate", "stringType", "getStringType", "throwableClass", "getThrowableClass", "throwableClass$delegate", "throwableType", "getThrowableType", "extensionFunctionTypeAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "getExtensionFunctionTypeAnnotation", "()Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "extensionFunctionTypeAnnotation$delegate", "noInferAnnotation", "getNoInferAnnotation", "noInferAnnotation$delegate", "generateAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrAnnotationImpl;", "arrayClass", "getArrayClass", "arrayClass$delegate", "primitiveSymbolToPrimitiveType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "getPrimitiveSymbolToPrimitiveType$org_jetbrains_kotlin_fir2ir$annotations", "()V", "getPrimitiveSymbolToPrimitiveType$org_jetbrains_kotlin_fir2ir", "()Ljava/util/Map;", "primitiveSymbolToPrimitiveType$delegate", "primitiveTypeToIrType", "getPrimitiveTypeToIrType$org_jetbrains_kotlin_fir2ir$annotations", "getPrimitiveTypeToIrType$org_jetbrains_kotlin_fir2ir", "primitiveTypeToIrType$delegate", "loadPrimitiveArray", "primitiveType", "booleanArray", "getBooleanArray", "booleanArray$delegate", "charArray", "getCharArray", "charArray$delegate", "byteArray", "getByteArray", "byteArray$delegate", "shortArray", "getShortArray", "shortArray$delegate", "intArray", "getIntArray", "intArray$delegate", "longArray", "getLongArray", "longArray$delegate", "floatArray", "getFloatArray", "floatArray$delegate", "doubleArray", "getDoubleArray", "doubleArray$delegate", "loadUnsignedArray", "unsignedType", "Lorg/jetbrains/kotlin/builtins/UnsignedType;", "ubyteArray", "getUbyteArray", "ubyteArray$delegate", "ushortArray", "getUshortArray", "ushortArray$delegate", "uintArray", "getUintArray", "uintArray$delegate", "ulongArray", "getUlongArray", "ulongArray$delegate", "primitiveArraysToPrimitiveTypes", "getPrimitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir$annotations", "getPrimitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir", "primitiveArraysToPrimitiveTypes$delegate", "primitiveArrayElementTypes", "getPrimitiveArrayElementTypes", "primitiveArrayForType", "getPrimitiveArrayForType", "unsignedTypesToUnsignedArrays", "getUnsignedTypesToUnsignedArrays", "unsignedTypesToUnsignedArrays$delegate", "unsignedArraysElementTypes", "getUnsignedArraysElementTypes$annotations", "getUnsignedArraysElementTypes", "unsignedArraysElementTypes$delegate", "eqeqeqSymbol", "getEqeqeqSymbol", "eqeqSymbol", "getEqeqSymbol", "noWhenBranchMatchedExceptionSymbol", "getNoWhenBranchMatchedExceptionSymbol", "checkNotNullSymbol", "getCheckNotNullSymbol", "lessFunByOperandType", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "getLessFunByOperandType", "lessFunByOperandType$delegate", "lessOrEqualFunByOperandType", "getLessOrEqualFunByOperandType", "lessOrEqualFunByOperandType$delegate", "greaterOrEqualFunByOperandType", "getGreaterOrEqualFunByOperandType", "greaterOrEqualFunByOperandType$delegate", "greaterFunByOperandType", "getGreaterFunByOperandType", "greaterFunByOperandType$delegate", "ieee754equalsFunByOperandType", "getIeee754equalsFunByOperandType", "ieee754equalsFunByOperandType$delegate", "operatorMap", "syntheticMap", "getNonBuiltInFunctionsWithFirCounterpartByExtensionReceiver", "Lkotlin/Pair;", "packageNameSegments", Argument.Delimiters.none, Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/name/Name;[Ljava/lang/String;)Ljava/util/Map;", "loadClass", "loadClass$org_jetbrains_kotlin_fir2ir", "loadClassSafe", "loadClassSafe$org_jetbrains_kotlin_fir2ir", "findFunctions", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "findFunctions$org_jetbrains_kotlin_fir2ir", "findProperties", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "findProperties$org_jetbrains_kotlin_fir2ir", "filterEquivalentSymbols", "T", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getFunctionsByKey", "K", Argument.Delimiters.none, "mapKey", "Lkotlin/Function1;", "mapValue", "Lkotlin/Function2;", "getFunctionsByKey$org_jetbrains_kotlin_fir2ir", "(Lorg/jetbrains/kotlin/name/Name;[Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "findFunction", "functionSymbol", "findFunction$org_jetbrains_kotlin_fir2ir", "packageName", "Lorg/jetbrains/kotlin/name/FqName;", "findProperty", "propertySymbol", "findProperty$org_jetbrains_kotlin_fir2ir", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrBuiltinSymbolsContainer implements Fir2IrComponents {
    private final /* synthetic */ Fir2IrComponents $$delegate_0;

    /* JADX INFO: renamed from: anyClass$delegate, reason: from kotlin metadata */
    private final Lazy anyClass;

    /* JADX INFO: renamed from: anyNType$delegate, reason: from kotlin metadata */
    private final Lazy anyNType;

    /* JADX INFO: renamed from: arrayClass$delegate, reason: from kotlin metadata */
    private final Lazy arrayClass;

    /* JADX INFO: renamed from: booleanArray$delegate, reason: from kotlin metadata */
    private final Lazy booleanArray;

    /* JADX INFO: renamed from: booleanClass$delegate, reason: from kotlin metadata */
    private final Lazy booleanClass;

    /* JADX INFO: renamed from: booleanNotSymbol$delegate, reason: from kotlin metadata */
    private final Lazy booleanNotSymbol;

    /* JADX INFO: renamed from: byteArray$delegate, reason: from kotlin metadata */
    private final Lazy byteArray;

    /* JADX INFO: renamed from: byteClass$delegate, reason: from kotlin metadata */
    private final Lazy byteClass;

    /* JADX INFO: renamed from: charArray$delegate, reason: from kotlin metadata */
    private final Lazy charArray;

    /* JADX INFO: renamed from: charClass$delegate, reason: from kotlin metadata */
    private final Lazy charClass;

    /* JADX INFO: renamed from: charSequenceClass$delegate, reason: from kotlin metadata */
    private final Lazy charSequenceClass;

    /* JADX INFO: renamed from: doubleArray$delegate, reason: from kotlin metadata */
    private final Lazy doubleArray;

    /* JADX INFO: renamed from: doubleClass$delegate, reason: from kotlin metadata */
    private final Lazy doubleClass;

    /* JADX INFO: renamed from: extensionFunctionTypeAnnotation$delegate, reason: from kotlin metadata */
    private final Lazy extensionFunctionTypeAnnotation;

    /* JADX INFO: renamed from: floatArray$delegate, reason: from kotlin metadata */
    private final Lazy floatArray;

    /* JADX INFO: renamed from: floatClass$delegate, reason: from kotlin metadata */
    private final Lazy floatClass;

    /* JADX INFO: renamed from: greaterFunByOperandType$delegate, reason: from kotlin metadata */
    private final Lazy greaterFunByOperandType;

    /* JADX INFO: renamed from: greaterOrEqualFunByOperandType$delegate, reason: from kotlin metadata */
    private final Lazy greaterOrEqualFunByOperandType;

    /* JADX INFO: renamed from: ieee754equalsFunByOperandType$delegate, reason: from kotlin metadata */
    private final Lazy ieee754equalsFunByOperandType;

    /* JADX INFO: renamed from: intArray$delegate, reason: from kotlin metadata */
    private final Lazy intArray;

    /* JADX INFO: renamed from: intClass$delegate, reason: from kotlin metadata */
    private final Lazy intClass;

    /* JADX INFO: renamed from: lessFunByOperandType$delegate, reason: from kotlin metadata */
    private final Lazy lessFunByOperandType;

    /* JADX INFO: renamed from: lessOrEqualFunByOperandType$delegate, reason: from kotlin metadata */
    private final Lazy lessOrEqualFunByOperandType;

    /* JADX INFO: renamed from: longArray$delegate, reason: from kotlin metadata */
    private final Lazy longArray;

    /* JADX INFO: renamed from: longClass$delegate, reason: from kotlin metadata */
    private final Lazy longClass;

    /* JADX INFO: renamed from: noInferAnnotation$delegate, reason: from kotlin metadata */
    private final Lazy noInferAnnotation;

    /* JADX INFO: renamed from: nothingClass$delegate, reason: from kotlin metadata */
    private final Lazy nothingClass;

    /* JADX INFO: renamed from: nothingNType$delegate, reason: from kotlin metadata */
    private final Lazy nothingNType;

    /* JADX INFO: renamed from: numberClass$delegate, reason: from kotlin metadata */
    private final Lazy numberClass;

    /* JADX INFO: renamed from: primitiveArraysToPrimitiveTypes$delegate, reason: from kotlin metadata */
    private final Lazy primitiveArraysToPrimitiveTypes;

    /* JADX INFO: renamed from: primitiveSymbolToPrimitiveType$delegate, reason: from kotlin metadata */
    private final Lazy primitiveSymbolToPrimitiveType;

    /* JADX INFO: renamed from: primitiveTypeToIrType$delegate, reason: from kotlin metadata */
    private final Lazy primitiveTypeToIrType;

    /* JADX INFO: renamed from: shortArray$delegate, reason: from kotlin metadata */
    private final Lazy shortArray;

    /* JADX INFO: renamed from: shortClass$delegate, reason: from kotlin metadata */
    private final Lazy shortClass;

    /* JADX INFO: renamed from: stringClass$delegate, reason: from kotlin metadata */
    private final Lazy stringClass;
    private final FirSymbolProvider symbolProvider;
    private final Fir2IrSyntheticIrBuiltinsSymbolsContainer syntheticSymbolsContainer;

    /* JADX INFO: renamed from: throwableClass$delegate, reason: from kotlin metadata */
    private final Lazy throwableClass;

    /* JADX INFO: renamed from: ubyteArray$delegate, reason: from kotlin metadata */
    private final Lazy ubyteArray;

    /* JADX INFO: renamed from: ubyteClass$delegate, reason: from kotlin metadata */
    private final Lazy ubyteClass;

    /* JADX INFO: renamed from: ubyteType$delegate, reason: from kotlin metadata */
    private final Lazy ubyteType;

    /* JADX INFO: renamed from: uintArray$delegate, reason: from kotlin metadata */
    private final Lazy uintArray;

    /* JADX INFO: renamed from: uintClass$delegate, reason: from kotlin metadata */
    private final Lazy uintClass;

    /* JADX INFO: renamed from: uintType$delegate, reason: from kotlin metadata */
    private final Lazy uintType;

    /* JADX INFO: renamed from: ulongArray$delegate, reason: from kotlin metadata */
    private final Lazy ulongArray;

    /* JADX INFO: renamed from: ulongClass$delegate, reason: from kotlin metadata */
    private final Lazy ulongClass;

    /* JADX INFO: renamed from: ulongType$delegate, reason: from kotlin metadata */
    private final Lazy ulongType;

    /* JADX INFO: renamed from: unitClass$delegate, reason: from kotlin metadata */
    private final Lazy unitClass;

    /* JADX INFO: renamed from: unsignedArraysElementTypes$delegate, reason: from kotlin metadata */
    private final Lazy unsignedArraysElementTypes;

    /* JADX INFO: renamed from: unsignedTypesToUnsignedArrays$delegate, reason: from kotlin metadata */
    private final Lazy unsignedTypesToUnsignedArrays;

    /* JADX INFO: renamed from: ushortArray$delegate, reason: from kotlin metadata */
    private final Lazy ushortArray;

    /* JADX INFO: renamed from: ushortClass$delegate, reason: from kotlin metadata */
    private final Lazy ushortClass;

    /* JADX INFO: renamed from: ushortType$delegate, reason: from kotlin metadata */
    private final Lazy ushortType;

    public Fir2IrBuiltinSymbolsContainer(Fir2IrComponents fir2IrComponents, Fir2IrSyntheticIrBuiltinsSymbolsContainer fir2IrSyntheticIrBuiltinsSymbolsContainer) {
        fir2IrComponents.getClass();
        fir2IrSyntheticIrBuiltinsSymbolsContainer.getClass();
        this.$$delegate_0 = fir2IrComponents;
        this.syntheticSymbolsContainer = fir2IrSyntheticIrBuiltinsSymbolsContainer;
        this.symbolProvider = FirSymbolProviderKt.getSymbolProvider(getSession());
        this.booleanNotSymbol = LazyKt.lazy(new Function0() { // from class: zs4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.m(this.b);
            }
        });
        this.anyClass = LazyKt.lazy(new Function0() { // from class: bt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.e(this.b);
            }
        });
        this.anyNType = LazyKt.lazy(new Function0() { // from class: nt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.w(this.b);
            }
        });
        this.numberClass = LazyKt.lazy(new Function0() { // from class: zt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.d(this.b);
            }
        });
        this.nothingClass = LazyKt.lazy(new Function0() { // from class: lu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.A(this.b);
            }
        });
        this.nothingNType = LazyKt.lazy(new Function0() { // from class: nu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.i(this.b);
            }
        });
        this.unitClass = LazyKt.lazy(new Function0() { // from class: ou4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.G(this.b);
            }
        });
        this.booleanClass = LazyKt.lazy(new Function0() { // from class: pu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.M(this.b);
            }
        });
        this.charClass = LazyKt.lazy(new Function0() { // from class: qu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.W(this.b);
            }
        });
        this.byteClass = LazyKt.lazy(new Function0() { // from class: su4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.N(this.b);
            }
        });
        this.shortClass = LazyKt.lazy(new Function0() { // from class: kt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.k(this.b);
            }
        });
        this.intClass = LazyKt.lazy(new Function0() { // from class: vt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.o(this.b);
            }
        });
        this.longClass = LazyKt.lazy(new Function0() { // from class: gu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.Q(this.b);
            }
        });
        this.ubyteClass = LazyKt.lazy(new Function0() { // from class: ru4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.g(this.b);
            }
        });
        this.ubyteType = LazyKt.lazy(new Function0() { // from class: tu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.F(this.b);
            }
        });
        this.ushortClass = LazyKt.lazy(new Function0() { // from class: uu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.S(this.b);
            }
        });
        this.ushortType = LazyKt.lazy(new Function0() { // from class: vu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.P(this.b);
            }
        });
        this.uintClass = LazyKt.lazy(new Function0() { // from class: wu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.c(this.b);
            }
        });
        this.uintType = LazyKt.lazy(new Function0() { // from class: xu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.l(this.b);
            }
        });
        this.ulongClass = LazyKt.lazy(new Function0() { // from class: at4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.u(this.b);
            }
        });
        this.ulongType = LazyKt.lazy(new Function0() { // from class: ct4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.r(this.b);
            }
        });
        this.floatClass = LazyKt.lazy(new Function0() { // from class: dt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.h(this.b);
            }
        });
        this.doubleClass = LazyKt.lazy(new Function0() { // from class: et4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.Y(this.b);
            }
        });
        this.charSequenceClass = LazyKt.lazy(new Function0() { // from class: ft4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.H(this.b);
            }
        });
        this.stringClass = LazyKt.lazy(new Function0() { // from class: gt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.U(this.b);
            }
        });
        this.throwableClass = LazyKt.lazy(new Function0() { // from class: ht4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.n(this.b);
            }
        });
        this.extensionFunctionTypeAnnotation = LazyKt.lazy(new Function0() { // from class: it4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.q(this.b);
            }
        });
        this.noInferAnnotation = LazyKt.lazy(new Function0() { // from class: jt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.f(this.b);
            }
        });
        this.arrayClass = LazyKt.lazy(new Function0() { // from class: lt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.p(this.b);
            }
        });
        this.primitiveSymbolToPrimitiveType = LazyKt.lazy(new Function0() { // from class: mt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.E(this.b);
            }
        });
        this.primitiveTypeToIrType = LazyKt.lazy(new Function0() { // from class: ot4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.V(this.b);
            }
        });
        this.booleanArray = LazyKt.lazy(new Function0() { // from class: pt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.I(this.b);
            }
        });
        this.charArray = LazyKt.lazy(new Function0() { // from class: qt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.X(this.b);
            }
        });
        this.byteArray = LazyKt.lazy(new Function0() { // from class: rt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.L(this.b);
            }
        });
        this.shortArray = LazyKt.lazy(new Function0() { // from class: st4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.j(this.b);
            }
        });
        this.intArray = LazyKt.lazy(new Function0() { // from class: tt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.v(this.b);
            }
        });
        this.longArray = LazyKt.lazy(new Function0() { // from class: ut4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.O(this.b);
            }
        });
        this.floatArray = LazyKt.lazy(new Function0() { // from class: wt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.D(this.b);
            }
        });
        this.doubleArray = LazyKt.lazy(new Function0() { // from class: xt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.t(this.b);
            }
        });
        this.ubyteArray = LazyKt.lazy(new Function0() { // from class: yt4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.B(this.b);
            }
        });
        this.ushortArray = LazyKt.lazy(new Function0() { // from class: au4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.T(this.b);
            }
        });
        this.uintArray = LazyKt.lazy(new Function0() { // from class: bu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.Z(this.b);
            }
        });
        this.ulongArray = LazyKt.lazy(new Function0() { // from class: cu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.C(this.b);
            }
        });
        this.primitiveArraysToPrimitiveTypes = LazyKt.lazy(new Function0() { // from class: du4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.b(this.b);
            }
        });
        this.unsignedTypesToUnsignedArrays = LazyKt.lazy(new Function0() { // from class: eu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.z(this.b);
            }
        });
        this.unsignedArraysElementTypes = LazyKt.lazy(new Function0() { // from class: fu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.K(this.b);
            }
        });
        this.lessFunByOperandType = LazyKt.lazy(new Function0() { // from class: hu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.J(this.b);
            }
        });
        this.lessOrEqualFunByOperandType = LazyKt.lazy(new Function0() { // from class: iu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.x(this.b);
            }
        });
        this.greaterOrEqualFunByOperandType = LazyKt.lazy(new Function0() { // from class: ju4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.y(this.b);
            }
        });
        this.greaterFunByOperandType = LazyKt.lazy(new Function0() { // from class: ku4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.R(this.b);
            }
        });
        this.ieee754equalsFunByOperandType = LazyKt.lazy(new Function0() { // from class: mu4
            public final Object invoke() {
                return Fir2IrBuiltinSymbolsContainer.s(this.b);
            }
        });
    }

    public static IrClassSymbol A(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getNothing());
    }

    public static IrClassSymbol B(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadUnsignedArray(UnsignedType.UBYTE);
    }

    public static IrClassSymbol C(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadUnsignedArray(UnsignedType.ULONG);
    }

    public static IrClassSymbol D(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.FLOAT);
    }

    public static Map E(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return MapsKt.mapOf(new Pair[]{TuplesKt.to(fir2IrBuiltinSymbolsContainer.getCharClass(), PrimitiveType.CHAR), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getByteClass(), PrimitiveType.BYTE), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getShortClass(), PrimitiveType.SHORT), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getIntClass(), PrimitiveType.INT), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getLongClass(), PrimitiveType.LONG), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getFloatClass(), PrimitiveType.FLOAT), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getDoubleClass(), PrimitiveType.DOUBLE)});
    }

    public static IrSimpleType F(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        IrClassSymbol ubyteClass = fir2IrBuiltinSymbolsContainer.getUbyteClass();
        ubyteClass.getClass();
        return VariousUtilsKt.getDefaultTypeWithoutArguments(ubyteClass);
    }

    public static IrClassSymbol G(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getUnit());
    }

    public static IrClassSymbol H(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getCharSequence());
    }

    public static IrClassSymbol I(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.BOOLEAN);
    }

    public static Map J(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.operatorMap(fir2IrBuiltinSymbolsContainer.syntheticSymbolsContainer.getLessFunByOperandType());
    }

    public static Map K(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        Map<UnsignedType, IrClassSymbol> unsignedTypesToUnsignedArrays = fir2IrBuiltinSymbolsContainer.getUnsignedTypesToUnsignedArrays();
        ArrayList arrayList = new ArrayList(unsignedTypesToUnsignedArrays.size());
        for (Map.Entry<UnsignedType, IrClassSymbol> entry : unsignedTypesToUnsignedArrays.entrySet()) {
            arrayList.add(TuplesKt.to(entry.getValue(), IrUtilsKt.getDefaultType(fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(entry.getKey().getClassId()).getOwner())));
        }
        return MapsKt.toMap(arrayList);
    }

    public static IrClassSymbol L(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.BYTE);
    }

    public static IrClassSymbol M(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getBoolean());
    }

    public static IrClassSymbol N(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getByte());
    }

    public static IrClassSymbol O(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.LONG);
    }

    public static IrSimpleType P(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        IrClassSymbol ushortClass = fir2IrBuiltinSymbolsContainer.getUshortClass();
        ushortClass.getClass();
        return VariousUtilsKt.getDefaultTypeWithoutArguments(ushortClass);
    }

    public static IrClassSymbol Q(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getLong());
    }

    public static Map R(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.operatorMap(fir2IrBuiltinSymbolsContainer.syntheticSymbolsContainer.getGreaterFunByOperandType());
    }

    public static IrClassSymbol S(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClassSafe$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getUShort());
    }

    public static IrClassSymbol T(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadUnsignedArray(UnsignedType.USHORT);
    }

    public static IrClassSymbol U(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getString());
    }

    public static Map V(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return MapsKt.mapOf(new Pair[]{TuplesKt.to(PrimitiveType.BOOLEAN, fir2IrBuiltinSymbolsContainer.getBooleanType()), TuplesKt.to(PrimitiveType.CHAR, fir2IrBuiltinSymbolsContainer.getCharType()), TuplesKt.to(PrimitiveType.BYTE, fir2IrBuiltinSymbolsContainer.getByteType()), TuplesKt.to(PrimitiveType.SHORT, fir2IrBuiltinSymbolsContainer.getShortType()), TuplesKt.to(PrimitiveType.INT, fir2IrBuiltinSymbolsContainer.getIntType()), TuplesKt.to(PrimitiveType.LONG, fir2IrBuiltinSymbolsContainer.getLongType()), TuplesKt.to(PrimitiveType.FLOAT, fir2IrBuiltinSymbolsContainer.getFloatType()), TuplesKt.to(PrimitiveType.DOUBLE, fir2IrBuiltinSymbolsContainer.getDoubleType())});
    }

    public static IrClassSymbol W(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getChar());
    }

    public static IrClassSymbol X(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.CHAR);
    }

    public static IrClassSymbol Y(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getDouble());
    }

    public static IrClassSymbol Z(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadUnsignedArray(UnsignedType.UINT);
    }

    public static Map b(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return MapsKt.mapOf(new Pair[]{TuplesKt.to(fir2IrBuiltinSymbolsContainer.getBooleanArray(), PrimitiveType.BOOLEAN), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getCharArray(), PrimitiveType.CHAR), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getByteArray(), PrimitiveType.BYTE), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getShortArray(), PrimitiveType.SHORT), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getIntArray(), PrimitiveType.INT), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getLongArray(), PrimitiveType.LONG), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getFloatArray(), PrimitiveType.FLOAT), TuplesKt.to(fir2IrBuiltinSymbolsContainer.getDoubleArray(), PrimitiveType.DOUBLE)});
    }

    public static IrClassSymbol c(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClassSafe$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getUInt());
    }

    public static IrClassSymbol d(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getNumber());
    }

    public static IrClassSymbol e(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getAny());
    }

    public static IrAnnotationImpl f(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.generateAnnotation(StandardClassIds$Annotations.INSTANCE.getNoInfer());
    }

    private final <T extends FirCallableSymbol<?>> List<T> filterEquivalentSymbols(List<? extends T> list) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        for (T t : list) {
            List list2 = listCreateListBuilder;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it = list2.iterator();
                do {
                    if (it.hasNext()) {
                    }
                } while (!filterEquivalentSymbols$isEquivalentTo(t, this, (FirCallableSymbol) it.next()));
            }
            listCreateListBuilder.add(t);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private static final <T extends FirCallableSymbol<?>> boolean filterEquivalentSymbols$isEquivalentTo(T t, Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer, T t2) {
        return ConeEquivalentCallConflictResolver.INSTANCE.areEquivalentTopLevelCallables((FirCallableDeclaration) t.getFir(), (FirCallableDeclaration) t2.getFir(), fir2IrBuiltinSymbolsContainer.getSession(), null);
    }

    public static IrClassSymbol g(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClassSafe$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getUByte());
    }

    private final IrAnnotationImpl generateAnnotation(ClassId classId) {
        FirConstructorSymbol firConstructorSymbol;
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(getSession()).getClassLikeSymbolByClassId(classId);
        FirRegularClassSymbol firRegularClassSymbol = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
        if (firRegularClassSymbol == null) {
            return null;
        }
        IrClassifierSymbol irSymbol$default = SymbolConversionUtilsKt.toIrSymbol$default(this, firRegularClassSymbol, ConversionTypeOrigin.DEFAULT, null, 4, null);
        IrClassifierSymbol irClassifierSymbol = irSymbol$default instanceof IrClassSymbol ? (IrClassSymbol) irSymbol$default : null;
        if (irClassifierSymbol == null || (firConstructorSymbol = (FirConstructorSymbol) CollectionsKt.singleOrNull(FirScopeKt.getDeclaredConstructors(ScopeUtilsKt.unsubstitutedScope(this, firRegularClassSymbol)))) == null) {
            return null;
        }
        return BuildersKt.IrAnnotationImplWithShape$default(-1, -1, IrSimpleTypeImplKt.IrSimpleTypeImpl$default(irClassifierSymbol, SimpleTypeNullability.DEFINITELY_NOT_NULL, CollectionsKt.emptyList(), CollectionsKt.emptyList(), (KotlinType) null, 16, (Object) null), Fir2IrDeclarationStorage.getIrConstructorSymbol$default(getDeclarationStorage(), firConstructorSymbol, false, 2, null), 0, 0, 0, 0, false, false, (IrStatementOrigin) null, (SourceElement) null, 3072, (Object) null);
    }

    @Fir2IrBuiltInsInternals
    public static /* synthetic */ void getPrimitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir$annotations() {
    }

    @Fir2IrBuiltInsInternals
    public static /* synthetic */ void getPrimitiveSymbolToPrimitiveType$org_jetbrains_kotlin_fir2ir$annotations() {
    }

    @Fir2IrBuiltInsInternals
    public static /* synthetic */ void getPrimitiveTypeToIrType$org_jetbrains_kotlin_fir2ir$annotations() {
    }

    public static /* synthetic */ void getUnsignedArraysElementTypes$annotations() {
    }

    private final Map<UnsignedType, IrClassSymbol> getUnsignedTypesToUnsignedArrays() {
        return (Map) this.unsignedTypesToUnsignedArrays.getValue();
    }

    public static IrClassSymbol h(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getFloat());
    }

    public static IrType i(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return IrTypesKt.makeNullable(fir2IrBuiltinSymbolsContainer.getNothingType());
    }

    public static IrClassSymbol j(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.SHORT);
    }

    public static IrClassSymbol k(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getShort());
    }

    public static IrSimpleType l(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        IrClassSymbol uintClass = fir2IrBuiltinSymbolsContainer.getUintClass();
        uintClass.getClass();
        return VariousUtilsKt.getDefaultTypeWithoutArguments(uintClass);
    }

    private final IrClassSymbol loadPrimitiveArray(PrimitiveType primitiveType) {
        FqName base_kotlin_package = StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier = Name.identifier(primitiveType.getTypeName() + "Array");
        nameIdentifier.getClass();
        return loadClass$org_jetbrains_kotlin_fir2ir(new ClassId(base_kotlin_package, nameIdentifier));
    }

    private final IrClassSymbol loadUnsignedArray(UnsignedType unsignedType) {
        FqName base_kotlin_package = StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier = Name.identifier(unsignedType.getTypeName() + "Array");
        nameIdentifier.getClass();
        return loadClass$org_jetbrains_kotlin_fir2ir(new ClassId(base_kotlin_package, nameIdentifier));
    }

    public static IrSimpleFunctionSymbol m(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        for (FirNamedFunctionSymbol firNamedFunctionSymbol : fir2IrBuiltinSymbolsContainer.findFirMemberFunctions$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getBoolean(), OperatorNameConventions.NOT)) {
            if (ConeBuiltinTypeUtilsKt.isBoolean(firNamedFunctionSymbol.getResolvedReturnType())) {
                return fir2IrBuiltinSymbolsContainer.findFunction$org_jetbrains_kotlin_fir2ir(firNamedFunctionSymbol);
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    public static IrClassSymbol n(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getThrowable());
    }

    public static IrClassSymbol o(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getInt());
    }

    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> operatorMap(Map<PrimitiveType, ? extends IrSimpleFunctionSymbol> syntheticMap) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (Map.Entry<IrClassSymbol, PrimitiveType> entry : getPrimitiveSymbolToPrimitiveType$org_jetbrains_kotlin_fir2ir().entrySet()) {
            IrClassSymbol key = entry.getKey();
            IrSimpleFunctionSymbol irSimpleFunctionSymbol = syntheticMap.get(entry.getValue());
            if (irSimpleFunctionSymbol != null) {
                mapCreateMapBuilder.put(key, irSimpleFunctionSymbol);
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    public static IrClassSymbol p(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClass$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getArray());
    }

    public static IrAnnotationImpl q(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.generateAnnotation(StandardClassIds$Annotations.INSTANCE.getExtensionFunctionType());
    }

    public static IrSimpleType r(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        IrClassSymbol ulongClass = fir2IrBuiltinSymbolsContainer.getUlongClass();
        ulongClass.getClass();
        return VariousUtilsKt.getDefaultTypeWithoutArguments(ulongClass);
    }

    public static Map s(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.operatorMap(fir2IrBuiltinSymbolsContainer.syntheticSymbolsContainer.getIeee754equalsFunByOperandType());
    }

    public static IrClassSymbol t(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.DOUBLE);
    }

    public static IrClassSymbol u(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadClassSafe$org_jetbrains_kotlin_fir2ir(StandardClassIds.INSTANCE.getULong());
    }

    public static IrClassSymbol v(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.loadPrimitiveArray(PrimitiveType.INT);
    }

    public static IrType w(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return IrTypesKt.makeNullable(fir2IrBuiltinSymbolsContainer.getAnyType());
    }

    public static Map x(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.operatorMap(fir2IrBuiltinSymbolsContainer.syntheticSymbolsContainer.getLessOrEqualFunByOperandType());
    }

    public static Map y(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        return fir2IrBuiltinSymbolsContainer.operatorMap(fir2IrBuiltinSymbolsContainer.syntheticSymbolsContainer.getGreaterOrEqualFunByOperandType());
    }

    public static Map z(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        EnumEntries<UnsignedType> entries = UnsignedType.getEntries();
        ArrayList arrayList = new ArrayList();
        for (UnsignedType unsignedType : entries) {
            IrClassSymbol irClassSymbolLoadClassSafe$org_jetbrains_kotlin_fir2ir = fir2IrBuiltinSymbolsContainer.loadClassSafe$org_jetbrains_kotlin_fir2ir(unsignedType.getArrayClassId());
            Pair pair = irClassSymbolLoadClassSafe$org_jetbrains_kotlin_fir2ir == null ? null : TuplesKt.to(unsignedType, irClassSymbolLoadClassSafe$org_jetbrains_kotlin_fir2ir);
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return MapsKt.toMap(arrayList);
    }

    @Fir2IrBuiltInsInternals
    public final List<FirNamedFunctionSymbol> findFirMemberFunctions$org_jetbrains_kotlin_fir2ir(ClassId classId, Name name) {
        classId.getClass();
        name.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = this.symbolProvider.getClassLikeSymbolByClassId(classId);
        classLikeSymbolByClassId.getClass();
        return FirScopeKt.getFunctions(ScopeUtilsKt.unsubstitutedScope(this, (FirRegularClassSymbol) classLikeSymbolByClassId), name);
    }

    @Fir2IrBuiltInsInternals
    public final List<FirPropertySymbol> findFirMemberProperties$org_jetbrains_kotlin_fir2ir(ClassId classId, Name name) {
        classId.getClass();
        name.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = this.symbolProvider.getClassLikeSymbolByClassId(classId);
        classLikeSymbolByClassId.getClass();
        List<FirVariableSymbol<?>> properties = FirScopeKt.getProperties(ScopeUtilsKt.unsubstitutedScope(this, (FirRegularClassSymbol) classLikeSymbolByClassId), name);
        ArrayList arrayList = new ArrayList();
        for (Object obj : properties) {
            if (obj instanceof FirPropertySymbol) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Fir2IrBuiltInsInternals
    public final IrSimpleFunctionSymbol findFunction$org_jetbrains_kotlin_fir2ir(FirNamedFunctionSymbol functionSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        functionSymbol.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(functionSymbol, FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE);
        IrSimpleFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), functionSymbol, null, false, 6, null);
        irFunctionSymbol$default.getClass();
        return irFunctionSymbol$default;
    }

    @Fir2IrBuiltInsInternals
    public final List<IrSimpleFunctionSymbol> findFunctions$org_jetbrains_kotlin_fir2ir(CallableId callableId) {
        callableId.getClass();
        if (callableId.isLocal()) {
            w01.a("Failed requirement.");
            return null;
        }
        ClassId classId = callableId.getClassId();
        List<FirNamedFunctionSymbol> topLevelFunctionSymbols = classId == null ? this.symbolProvider.getTopLevelFunctionSymbols(callableId.getPackageName(), callableId.getCallableName()) : findFirMemberFunctions$org_jetbrains_kotlin_fir2ir(classId, callableId.getCallableName());
        ArrayList arrayList = new ArrayList();
        for (Object obj : topLevelFunctionSymbols) {
            if (!((FirNamedFunctionSymbol) obj).getRawStatus().isExpect()) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            topLevelFunctionSymbols = arrayList;
        }
        List listFilterEquivalentSymbols = filterEquivalentSymbols(topLevelFunctionSymbols);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFilterEquivalentSymbols, 10));
        Iterator it = listFilterEquivalentSymbols.iterator();
        while (it.hasNext()) {
            arrayList2.add(findFunction$org_jetbrains_kotlin_fir2ir((FirNamedFunctionSymbol) it.next()));
        }
        return arrayList2;
    }

    @Fir2IrBuiltInsInternals
    public final List<IrPropertySymbol> findProperties$org_jetbrains_kotlin_fir2ir(CallableId callableId) {
        callableId.getClass();
        if (callableId.isLocal()) {
            w01.a("Failed requirement.");
            return null;
        }
        ClassId classId = callableId.getClassId();
        List<FirPropertySymbol> topLevelPropertySymbols = classId == null ? this.symbolProvider.getTopLevelPropertySymbols(callableId.getPackageName(), callableId.getCallableName()) : findFirMemberProperties$org_jetbrains_kotlin_fir2ir(classId, callableId.getCallableName());
        ArrayList arrayList = new ArrayList();
        for (Object obj : topLevelPropertySymbols) {
            if (!((FirPropertySymbol) obj).getRawStatus().isExpect()) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            topLevelPropertySymbols = arrayList;
        }
        List listFilterEquivalentSymbols = filterEquivalentSymbols(topLevelPropertySymbols);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFilterEquivalentSymbols, 10));
        Iterator it = listFilterEquivalentSymbols.iterator();
        while (it.hasNext()) {
            arrayList2.add(findProperty$org_jetbrains_kotlin_fir2ir((FirPropertySymbol) it.next()));
        }
        return arrayList2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Fir2IrBuiltInsInternals
    public final IrPropertySymbol findProperty$org_jetbrains_kotlin_fir2ir(FirPropertySymbol propertySymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        propertySymbol.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(propertySymbol, FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE);
        IrPropertySymbol irPropertySymbol$default = Fir2IrDeclarationStorage.getIrPropertySymbol$default(getDeclarationStorage(), propertySymbol, null, 2, null);
        irPropertySymbol$default.getClass();
        return irPropertySymbol$default;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.$$delegate_0.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.$$delegate_0.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.$$delegate_0.getAnnotationsFromPluginRegistrar();
    }

    public final IrClassSymbol getAnyClass() {
        return (IrClassSymbol) this.anyClass.getValue();
    }

    public final IrType getAnyNType() {
        return (IrType) this.anyNType.getValue();
    }

    public final IrType getAnyType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getAnyClass());
    }

    public final IrClassSymbol getArrayClass() {
        return (IrClassSymbol) this.arrayClass.getValue();
    }

    public final IrClassSymbol getBooleanArray() {
        return (IrClassSymbol) this.booleanArray.getValue();
    }

    public final IrClassSymbol getBooleanClass() {
        return (IrClassSymbol) this.booleanClass.getValue();
    }

    public final IrSimpleFunctionSymbol getBooleanNotSymbol() {
        return (IrSimpleFunctionSymbol) this.booleanNotSymbol.getValue();
    }

    public final IrType getBooleanType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getBooleanClass());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.$$delegate_0.getBuiltins();
    }

    public final IrClassSymbol getByteArray() {
        return (IrClassSymbol) this.byteArray.getValue();
    }

    public final IrClassSymbol getByteClass() {
        return (IrClassSymbol) this.byteClass.getValue();
    }

    public final IrType getByteType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getByteClass());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.$$delegate_0.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.$$delegate_0.getCallablesGenerator();
    }

    public final IrClassSymbol getCharArray() {
        return (IrClassSymbol) this.charArray.getValue();
    }

    public final IrClassSymbol getCharClass() {
        return (IrClassSymbol) this.charClass.getValue();
    }

    public final IrClassSymbol getCharSequenceClass() {
        return (IrClassSymbol) this.charSequenceClass.getValue();
    }

    public final IrType getCharType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getCharClass());
    }

    public final IrSimpleFunctionSymbol getCheckNotNullSymbol() {
        return this.syntheticSymbolsContainer.getCheckNotNullSymbol();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.$$delegate_0.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.$$delegate_0.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.$$delegate_0.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.$$delegate_0.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.$$delegate_0.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.$$delegate_0.getDeclarationStorage();
    }

    public final IrClassSymbol getDoubleArray() {
        return (IrClassSymbol) this.doubleArray.getValue();
    }

    public final IrClassSymbol getDoubleClass() {
        return (IrClassSymbol) this.doubleClass.getValue();
    }

    public final IrType getDoubleType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getDoubleClass());
    }

    public final IrSimpleFunctionSymbol getEqeqSymbol() {
        return this.syntheticSymbolsContainer.getEqeqSymbol();
    }

    public final IrSimpleFunctionSymbol getEqeqeqSymbol() {
        return this.syntheticSymbolsContainer.getEqeqeqSymbol();
    }

    public final IrAnnotation getExtensionFunctionTypeAnnotation() {
        return (IrAnnotation) this.extensionFunctionTypeAnnotation.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.$$delegate_0.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.$$delegate_0.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.$$delegate_0.getFirProvider();
    }

    public final IrClassSymbol getFloatArray() {
        return (IrClassSymbol) this.floatArray.getValue();
    }

    public final IrClassSymbol getFloatClass() {
        return (IrClassSymbol) this.floatClass.getValue();
    }

    public final IrType getFloatType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getFloatClass());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Fir2IrBuiltInsInternals
    public final <K, T> Map<K, T> getFunctionsByKey$org_jetbrains_kotlin_fir2ir(Name name, String[] packageNameSegments, Function1<? super FirNamedFunctionSymbol, ? extends K> mapKey, Function2<? super FirNamedFunctionSymbol, ? super IrSimpleFunctionSymbol, ? extends T> mapValue) {
        name.getClass();
        packageNameSegments.getClass();
        mapKey.getClass();
        mapValue.getClass();
        FqName fqNameFromSegments = FqName.Companion.fromSegments(ArraysKt.asList(packageNameSegments));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirNamedFunctionSymbol firNamedFunctionSymbol : this.symbolProvider.getTopLevelFunctionSymbols(fqNameFromSegments, name)) {
            Object objInvoke = mapKey.invoke(firNamedFunctionSymbol);
            if (objInvoke != null) {
                linkedHashMap.put(objInvoke, mapValue.invoke(firNamedFunctionSymbol, findFunction$org_jetbrains_kotlin_fir2ir(firNamedFunctionSymbol)));
            }
        }
        return linkedHashMap;
    }

    public final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getGreaterFunByOperandType() {
        return (Map) this.greaterFunByOperandType.getValue();
    }

    public final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getGreaterOrEqualFunByOperandType() {
        return (Map) this.greaterOrEqualFunByOperandType.getValue();
    }

    public final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getIeee754equalsFunByOperandType() {
        return (Map) this.ieee754equalsFunByOperandType.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.$$delegate_0.getImplicitCastInserter();
    }

    public final IrClassSymbol getIntArray() {
        return (IrClassSymbol) this.intArray.getValue();
    }

    public final IrClassSymbol getIntClass() {
        return (IrClassSymbol) this.intClass.getValue();
    }

    public final IrType getIntType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getIntClass());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.$$delegate_0.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.$$delegate_0.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.$$delegate_0.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.$$delegate_0.getLazyFakeOverrideGenerator();
    }

    public final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getLessFunByOperandType() {
        return (Map) this.lessFunByOperandType.getValue();
    }

    public final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getLessOrEqualFunByOperandType() {
        return (Map) this.lessOrEqualFunByOperandType.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.$$delegate_0.getLock();
    }

    public final IrClassSymbol getLongArray() {
        return (IrClassSymbol) this.longArray.getValue();
    }

    public final IrClassSymbol getLongClass() {
        return (IrClassSymbol) this.longClass.getValue();
    }

    public final IrType getLongType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getLongClass());
    }

    public final IrAnnotation getNoInferAnnotation() {
        return (IrAnnotation) this.noInferAnnotation.getValue();
    }

    public final IrSimpleFunctionSymbol getNoWhenBranchMatchedExceptionSymbol() {
        return this.syntheticSymbolsContainer.getNoWhenBranchMatchedExceptionSymbol();
    }

    public final Map<IrClassifierSymbol, Pair<FirNamedFunctionSymbol, IrSimpleFunctionSymbol>> getNonBuiltInFunctionsWithFirCounterpartByExtensionReceiver(Name name, String... packageNameSegments) {
        IrType irType$default;
        name.getClass();
        packageNameSegments.getClass();
        FqName fqNameFromSegments = FqName.Companion.fromSegments(ArraysKt.asList((String[]) Arrays.copyOf(packageNameSegments, packageNameSegments.length)));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirNamedFunctionSymbol firNamedFunctionSymbol : this.symbolProvider.getTopLevelFunctionSymbols(fqNameFromSegments, name)) {
            FirResolvedTypeRef resolvedReceiverTypeRef = firNamedFunctionSymbol.getResolvedReceiverTypeRef();
            IrClassifierSymbol classifierOrNull = null;
            if (resolvedReceiverTypeRef != null && (irType$default = Fir2IrTypeConverterKt.toIrType$default((Fir2IrComponents) this, (FirTypeRef) resolvedReceiverTypeRef, (ConversionTypeOrigin) null, 2, (Object) null)) != null) {
                classifierOrNull = IrTypesKt.getClassifierOrNull(irType$default);
            }
            if (classifierOrNull != null) {
                linkedHashMap.put(classifierOrNull, TuplesKt.to(firNamedFunctionSymbol, findFunction$org_jetbrains_kotlin_fir2ir(firNamedFunctionSymbol)));
            }
        }
        return linkedHashMap;
    }

    public final IrClassSymbol getNothingClass() {
        return (IrClassSymbol) this.nothingClass.getValue();
    }

    public final IrType getNothingNType() {
        return (IrType) this.nothingNType.getValue();
    }

    public final IrType getNothingType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getNothingClass());
    }

    public final IrClassSymbol getNumberClass() {
        return (IrClassSymbol) this.numberClass.getValue();
    }

    public final Map<IrClassSymbol, IrType> getPrimitiveArrayElementTypes() {
        Map<IrClassSymbol, PrimitiveType> primitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir = getPrimitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir();
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(primitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir.size()));
        Iterator<T> it = primitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), getPrimitiveTypeToIrType$org_jetbrains_kotlin_fir2ir().get(entry.getValue()));
        }
        return linkedHashMap;
    }

    public final Map<IrType, IrClassSymbol> getPrimitiveArrayForType() {
        Sequence<Map.Entry> sequenceAsSequence = MapsKt.asSequence(getPrimitiveArrayElementTypes());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : sequenceAsSequence) {
            Pair pair = TuplesKt.to(entry.getValue(), entry.getKey());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    public final Map<IrClassSymbol, PrimitiveType> getPrimitiveArraysToPrimitiveTypes$org_jetbrains_kotlin_fir2ir() {
        return (Map) this.primitiveArraysToPrimitiveTypes.getValue();
    }

    public final Map<IrClassSymbol, PrimitiveType> getPrimitiveSymbolToPrimitiveType$org_jetbrains_kotlin_fir2ir() {
        return (Map) this.primitiveSymbolToPrimitiveType.getValue();
    }

    public final Map<PrimitiveType, IrType> getPrimitiveTypeToIrType$org_jetbrains_kotlin_fir2ir() {
        return (Map) this.primitiveTypeToIrType.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.$$delegate_0.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.$$delegate_0.getSession();
    }

    public final IrClassSymbol getShortArray() {
        return (IrClassSymbol) this.shortArray.getValue();
    }

    public final IrClassSymbol getShortClass() {
        return (IrClassSymbol) this.shortClass.getValue();
    }

    public final IrType getShortType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getShortClass());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.$$delegate_0.getSpecialAnnotationsProvider();
    }

    public final IrClassSymbol getStringClass() {
        return (IrClassSymbol) this.stringClass.getValue();
    }

    public final IrType getStringType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getStringClass());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.$$delegate_0.getSymbolsMappingForLazyClasses();
    }

    public final IrClassSymbol getThrowableClass() {
        return (IrClassSymbol) this.throwableClass.getValue();
    }

    public final IrType getThrowableType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getThrowableClass());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.$$delegate_0.getTypeConverter();
    }

    public final IrClassSymbol getUbyteArray() {
        return (IrClassSymbol) this.ubyteArray.getValue();
    }

    public final IrClassSymbol getUbyteClass() {
        return (IrClassSymbol) this.ubyteClass.getValue();
    }

    public final IrType getUbyteType() {
        return (IrType) this.ubyteType.getValue();
    }

    public final IrClassSymbol getUintArray() {
        return (IrClassSymbol) this.uintArray.getValue();
    }

    public final IrClassSymbol getUintClass() {
        return (IrClassSymbol) this.uintClass.getValue();
    }

    public final IrType getUintType() {
        return (IrType) this.uintType.getValue();
    }

    public final IrClassSymbol getUlongArray() {
        return (IrClassSymbol) this.ulongArray.getValue();
    }

    public final IrClassSymbol getUlongClass() {
        return (IrClassSymbol) this.ulongClass.getValue();
    }

    public final IrType getUlongType() {
        return (IrType) this.ulongType.getValue();
    }

    public final IrClassSymbol getUnitClass() {
        return (IrClassSymbol) this.unitClass.getValue();
    }

    public final IrType getUnitType() {
        return VariousUtilsKt.getDefaultTypeWithoutArguments(getUnitClass());
    }

    public final Map<IrClassSymbol, IrType> getUnsignedArraysElementTypes() {
        return (Map) this.unsignedArraysElementTypes.getValue();
    }

    public final IrClassSymbol getUshortArray() {
        return (IrClassSymbol) this.ushortArray.getValue();
    }

    public final IrClassSymbol getUshortClass() {
        return (IrClassSymbol) this.ushortClass.getValue();
    }

    public final IrType getUshortType() {
        return (IrType) this.ushortType.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.$$delegate_0.getVisibilityConverter();
    }

    @Fir2IrBuiltInsInternals
    public final IrClassSymbol loadClass$org_jetbrains_kotlin_fir2ir(ClassId classId) {
        classId.getClass();
        IrClassSymbol irClassSymbolLoadClassSafe$org_jetbrains_kotlin_fir2ir = loadClassSafe$org_jetbrains_kotlin_fir2ir(classId);
        if (irClassSymbolLoadClassSafe$org_jetbrains_kotlin_fir2ir != null) {
            return irClassSymbolLoadClassSafe$org_jetbrains_kotlin_fir2ir;
        }
        w04.a("Class not found: ", classId);
        return null;
    }

    @Fir2IrBuiltInsInternals
    public final IrClassSymbol loadClassSafe$org_jetbrains_kotlin_fir2ir(ClassId classId) {
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = this.symbolProvider.getClassLikeSymbolByClassId(classId);
        FirRegularClassSymbol firRegularClassSymbol = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
        if (firRegularClassSymbol == null) {
            return null;
        }
        return getClassifierStorage().getIrClassSymbol(firRegularClassSymbol);
    }

    @Fir2IrBuiltInsInternals
    public final List<IrPropertySymbol> findProperties$org_jetbrains_kotlin_fir2ir(FqName packageName, Name name) {
        packageName.getClass();
        name.getClass();
        List<FirPropertySymbol> topLevelPropertySymbols = this.symbolProvider.getTopLevelPropertySymbols(packageName, name);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(topLevelPropertySymbols, 10));
        Iterator<T> it = topLevelPropertySymbols.iterator();
        while (it.hasNext()) {
            arrayList.add(findProperty$org_jetbrains_kotlin_fir2ir((FirPropertySymbol) it.next()));
        }
        return arrayList;
    }
}
