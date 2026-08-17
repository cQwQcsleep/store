package org.jetbrains.kotlin.fir.backend;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGeneratorKt;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrTypeAlias;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrEnumEntrySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrClassSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrEnumEntrySymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrTypeAliasSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrTypeParameterSymbolImpl;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ê\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ \u0010&\u001a\u00020'2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020'0)H\u0007b\u0002\b+J\u0015\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u000200H\u0000¢\u0006\u0002\b1J'\u00102\u001a\u00020\u00162\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000207H\u0000¢\u0006\u0002\b8J\"\u00109\u001a\u00020\u00162\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000207H\u0002J!\u0010:\u001a\u0004\u0018\u00010\u00162\u0006\u00103\u001a\u00020\u00152\b\b\u0002\u00106\u001a\u000207H\u0000¢\u0006\u0002\b;J\u0016\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u00106\u001a\u000207J\"\u0010@\u001a\u00020\u00102\u0006\u0010A\u001a\u00020\u000b2\u0006\u0010B\u001a\u00020C2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010EJ\b\u0010F\u001a\u00020\fH\u0002J!\u0010G\u001a\u00020'2\u0006\u0010A\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020\u0010H\u0001b\u0002\bJ¢\u0006\u0002\bIJ\u000e\u0010K\u001a\u00020\u00102\u0006\u0010L\u001a\u00020%J\f\u0010M\u001a\u00020-*\u00020\u000bH\u0002J\u0014\u0010N\u001a\u00020O2\u0006\u0010L\u001a\u00020%H\u0007b\u0002\b+J\u0010\u0010P\u001a\u00020O2\u0006\u0010L\u001a\u00020%H\u0002J\u0012\u0010K\u001a\u0004\u0018\u00010\u00102\u0006\u0010Q\u001a\u00020\u000fH\u0002J\u0010\u0010R\u001a\u0004\u0018\u00010\u00102\u0006\u0010S\u001a\u00020%J\u0012\u0010T\u001a\u0004\u0018\u00010\u00102\u0006\u0010S\u001a\u00020%H\u0002J\u0012\u0010U\u001a\u00020\f2\n\u0010V\u001a\u0006\u0012\u0002\b\u00030WJ\u0010\u0010U\u001a\u0004\u0018\u00010\f2\u0006\u0010Q\u001a\u00020\u000fJ\u000e\u0010X\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\u000fJ\u0010\u0010Z\u001a\u00020\u00102\u0006\u0010S\u001a\u00020%H\u0002J\u0006\u0010[\u001a\u00020'J.\u0010\\\u001a\u00020\u00102\u0006\u0010]\u001a\u00020^2\b\b\u0002\u0010_\u001a\u00020`2\b\b\u0002\u0010a\u001a\u00020b2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010CJ \u0010d\u001a\u00020\u00102\u0006\u0010]\u001a\u00020^2\u0006\u0010a\u001a\u00020b2\b\u0010c\u001a\u0004\u0018\u00010\u0010J\u0016\u0010e\u001a\u00020'2\u0006\u0010f\u001a\u00020\u00192\u0006\u0010g\u001a\u00020\u0010J\u000e\u0010h\u001a\u00020\u001a2\u0006\u0010f\u001a\u00020\u0019J\"\u0010i\u001a\u00020j2\u0006\u0010f\u001a\u00020\u00192\u0006\u0010c\u001a\u00020\u00102\n\b\u0002\u0010D\u001a\u0004\u0018\u00010EJ\u0016\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020\u00122\u0006\u0010B\u001a\u00020CJ\u0017\u0010n\u001a\u0004\u0018\u00010l2\u0006\u0010o\u001a\u00020\u0012H\u0000¢\u0006\u0002\bpJ\u000e\u0010q\u001a\u00020\u00132\u0006\u0010r\u001a\u00020sJ\u0010\u0010t\u001a\u0004\u0018\u00010\u00102\u0006\u0010u\u001a\u00020\u001cJ\u0016\u0010v\u001a\u00020\u00102\u0006\u0010u\u001a\u00020\u001c2\u0006\u0010w\u001a\u00020xJ\u0010\u0010y\u001a\u0004\u0018\u00010\u00102\u0006\u0010z\u001a\u00020\u001eJ\u0016\u0010{\u001a\u00020\u00102\u0006\u0010z\u001a\u00020\u001e2\u0006\u0010|\u001a\u00020}R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010~\u001a\u00020\u007fX\u0096\u0005¢\u0006\b\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0016\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0016\u0010\u0086\u0001\u001a\u00030\u0087\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0016\u0010\u008a\u0001\u001a\u00030\u008b\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0016\u0010\u008e\u0001\u001a\u00030\u008f\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0016\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0015\u0010\u0096\u0001\u001a\u00020\u0000X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0016\u0010\u0099\u0001\u001a\u00030\u009a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0016\u0010\u009d\u0001\u001a\u00030\u009e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u0016\u0010¡\u0001\u001a\u00030¢\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001R\u0016\u0010¥\u0001\u001a\u00030¦\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b§\u0001\u0010¨\u0001R\u0016\u0010©\u0001\u001a\u00030ª\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b«\u0001\u0010¬\u0001R\u0016\u0010\u00ad\u0001\u001a\u00030®\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001R\u001f\u0010±\u0001\u001a\f\u0012\u0005\u0012\u00030³\u0001\u0018\u00010²\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0016\u0010¶\u0001\u001a\u00030·\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0016\u0010º\u0001\u001a\u00030»\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u0016\u0010¾\u0001\u001a\u00030¿\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÀ\u0001\u0010Á\u0001R\u001c\u0010Â\u0001\u001a\t\u0012\u0005\u0012\u00030Ã\u00010 X\u0096\u0005¢\u0006\b\u001a\u0006\bÄ\u0001\u0010Å\u0001R\u0016\u0010Æ\u0001\u001a\u00030Ç\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÈ\u0001\u0010É\u0001R\u0016\u0010Ê\u0001\u001a\u00030Ë\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÌ\u0001\u0010Í\u0001R\u0016\u0010Î\u0001\u001a\u00030Ï\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÐ\u0001\u0010Ñ\u0001R\u0016\u0010Ò\u0001\u001a\u00030Ó\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÔ\u0001\u0010Õ\u0001R\u0016\u0010Ö\u0001\u001a\u00030×\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bØ\u0001\u0010Ù\u0001R\u0018\u0010Ú\u0001\u001a\u0005\u0018\u00010Û\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÜ\u0001\u0010Ý\u0001R\u0016\u0010Þ\u0001\u001a\u00030ß\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bà\u0001\u0010á\u0001R\u0016\u0010â\u0001\u001a\u00030ã\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bä\u0001\u0010å\u0001R\u0016\u0010æ\u0001\u001a\u00030ç\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bè\u0001\u0010é\u0001¨\u0006ê\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "commonMemberStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;)V", "classCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "notFoundClassCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "typeAliasCache", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeAliasSymbol;", "typeParameterCache", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "typeParameterCacheForSetter", "enumEntryCache", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/ir/symbols/IrEnumEntrySymbol;", "codeFragmentCache", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "earlierSnippetsCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "fieldsForContextReceivers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "localStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrLocalClassStorage;", "localClassesCreatedOnTheFly", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "forEachCachedDeclarationSymbol", Argument.Delimiters.none, "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "Lorg/jetbrains/kotlin/fir/backend/DelicateDeclarationStorageApi;", "processMembersOfClassesOnTheFlyImmediately", Argument.Delimiters.none, "preCacheTypeParameters", "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "preCacheTypeParameters$org_jetbrains_kotlin_fir2ir", "getIrTypeParameter", "typeParameter", "index", Argument.Delimiters.none, "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "getIrTypeParameter$org_jetbrains_kotlin_fir2ir", "createAndCacheIrTypeParameter", "getCachedIrTypeParameter", "getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir", "getIrTypeParameterSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeParameterSymbol;", "firTypeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "createAndCacheIrClass", "regularClass", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "predefinedOrigin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "createClassSymbol", "cacheIrClass", "irClass", "cacheIrClass$org_jetbrains_kotlin_fir2ir", "Lorg/jetbrains/kotlin/fir/backend/LeakedDeclarationCaches;", "getIrClass", "firClass", "isNonLocalInReplContext", "getFir2IrLazyClass", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyClass;", "createFir2IrLazyClass", "lookupTag", "getCachedIrLocalClass", "klass", "getCachedIrClass", "getIrClassSymbol", "firClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getIrClassForNotFoundClass", "classLikeLookupTag", "createAndCacheLocalIrClassOnTheFly", "processMembersOfClassesCreatedOnTheFly", "createAndCacheAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "irParent", "getIrAnonymousObjectForEnumEntry", "putEnumEntryClassInScope", "enumEntry", "correspondingClass", "getIrEnumEntrySymbol", "createAndCacheIrEnumEntry", "Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;", "createAndCacheIrTypeAlias", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeAlias;", "typeAlias", "getCachedTypeAlias", "firTypeAlias", "getCachedTypeAlias$org_jetbrains_kotlin_fir2ir", "getIrTypeAliasSymbol", "firTypeAliasSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "getCachedIrCodeFragment", "codeFragment", "createAndCacheCodeFragmentClass", "containingFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "getCachedEarlierSnippetClass", "snippetSymbol", "createAndCacheEarlierSnippetClass", "containingPackageFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrPackageFragment;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrClassifierStorage implements Fir2IrComponents {
    private final Fir2IrComponents c;
    private final Map<FirRegularClass, IrClassSymbol> classCache;
    private final Map<FirCodeFragment, IrClass> codeFragmentCache;
    private final Fir2IrConversionScope conversionScope;
    private final Map<FirReplSnippetSymbol, IrClass> earlierSnippetsCache;
    private final Map<FirEnumEntry, IrEnumEntrySymbol> enumEntryCache;
    private final Map<IrClass, List<IrField>> fieldsForContextReceivers;
    private final Map<FirClass, IrClass> localClassesCreatedOnTheFly;
    private final Fir2IrLocalClassStorage localStorage;
    private final ConcurrentHashMap<ConeClassLikeLookupTag, IrClass> notFoundClassCache;
    private boolean processMembersOfClassesOnTheFlyImmediately;
    private final Map<FirTypeAlias, IrTypeAliasSymbol> typeAliasCache;
    private final Map<FirTypeParameter, IrTypeParameter> typeParameterCache;
    private final Map<FirTypeParameter, IrTypeParameter> typeParameterCacheForSetter;

    public Fir2IrClassifierStorage(Fir2IrComponents fir2IrComponents, Fir2IrCommonMemberStorage fir2IrCommonMemberStorage, Fir2IrConversionScope fir2IrConversionScope) {
        fir2IrComponents.getClass();
        fir2IrCommonMemberStorage.getClass();
        fir2IrConversionScope.getClass();
        this.c = fir2IrComponents;
        this.conversionScope = fir2IrConversionScope;
        this.classCache = fir2IrCommonMemberStorage.getClassCache();
        this.notFoundClassCache = fir2IrCommonMemberStorage.getNotFoundClassCache();
        this.typeAliasCache = new LinkedHashMap();
        this.typeParameterCache = fir2IrCommonMemberStorage.getTypeParameterCache();
        this.typeParameterCacheForSetter = new LinkedHashMap();
        this.enumEntryCache = fir2IrCommonMemberStorage.getEnumEntryCache();
        this.codeFragmentCache = new LinkedHashMap();
        this.earlierSnippetsCache = new LinkedHashMap();
        this.fieldsForContextReceivers = new LinkedHashMap();
        this.localStorage = new Fir2IrLocalClassStorage(fir2IrCommonMemberStorage.getLocalClassCache());
        this.localClassesCreatedOnTheFly = new LinkedHashMap();
    }

    public static /* synthetic */ IrClass createAndCacheAnonymousObject$default(Fir2IrClassifierStorage fir2IrClassifierStorage, FirAnonymousObject firAnonymousObject, Visibility visibility, Name name, IrDeclarationParent irDeclarationParent, int i, Object obj) {
        if ((i & 2) != 0) {
            visibility = Visibilities.Local.INSTANCE;
        }
        if ((i & 4) != 0) {
            name = SpecialNames.NO_NAME_PROVIDED;
        }
        if ((i & 8) != 0) {
            irDeclarationParent = null;
        }
        return fir2IrClassifierStorage.createAndCacheAnonymousObject(firAnonymousObject, visibility, name, irDeclarationParent);
    }

    public static /* synthetic */ IrClass createAndCacheIrClass$default(Fir2IrClassifierStorage fir2IrClassifierStorage, FirRegularClass firRegularClass, IrDeclarationParent irDeclarationParent, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            irDeclarationOrigin = null;
        }
        return fir2IrClassifierStorage.createAndCacheIrClass(firRegularClass, irDeclarationParent, irDeclarationOrigin);
    }

    public static /* synthetic */ IrEnumEntry createAndCacheIrEnumEntry$default(Fir2IrClassifierStorage fir2IrClassifierStorage, FirEnumEntry firEnumEntry, IrClass irClass, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            irDeclarationOrigin = null;
        }
        return fir2IrClassifierStorage.createAndCacheIrEnumEntry(firEnumEntry, irClass, irDeclarationOrigin);
    }

    private final IrTypeParameter createAndCacheIrTypeParameter(FirTypeParameter typeParameter, int index, ConversionTypeOrigin typeOrigin) {
        IrTypeParameter irTypeParameterCreateIrTypeParameterWithoutBounds = getClassifiersGenerator().createIrTypeParameterWithoutBounds(typeParameter, index, new IrTypeParameterSymbolImpl((TypeParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null));
        if (typeOrigin.getForSetter()) {
            this.typeParameterCacheForSetter.put(typeParameter, irTypeParameterCreateIrTypeParameterWithoutBounds);
            return irTypeParameterCreateIrTypeParameterWithoutBounds;
        }
        this.typeParameterCache.put(typeParameter, irTypeParameterCreateIrTypeParameterWithoutBounds);
        return irTypeParameterCreateIrTypeParameterWithoutBounds;
    }

    public static /* synthetic */ IrTypeParameter createAndCacheIrTypeParameter$default(Fir2IrClassifierStorage fir2IrClassifierStorage, FirTypeParameter firTypeParameter, int i, ConversionTypeOrigin conversionTypeOrigin, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return fir2IrClassifierStorage.createAndCacheIrTypeParameter(firTypeParameter, i, conversionTypeOrigin);
    }

    private final IrClass createAndCacheLocalIrClassOnTheFly(FirClass klass) {
        Fir2IrClassifiersGenerator.LocalIrClassInfo localIrClassInfoCreateLocalIrClassOnTheFly = getClassifiersGenerator().createLocalIrClassOnTheFly(klass, this.processMembersOfClassesOnTheFlyImmediately);
        IrClass irClass = localIrClassInfoCreateLocalIrClassOnTheFly.getIrClass();
        FirClass firClassOrLocalParent = localIrClassInfoCreateLocalIrClassOnTheFly.getFirClassOrLocalParent();
        IrClass irClassOrLocalParent = localIrClassInfoCreateLocalIrClassOnTheFly.getIrClassOrLocalParent();
        if (!this.processMembersOfClassesOnTheFlyImmediately) {
            this.localClassesCreatedOnTheFly.put(firClassOrLocalParent, irClassOrLocalParent);
        }
        return irClass;
    }

    private final IrClassSymbol createClassSymbol() {
        return new IrClassSymbolImpl((ClassDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
    }

    private final Fir2IrLazyClass createFir2IrLazyClass(FirClass firClass) {
        FirClassLikeSymbol<?> classLikeSymbolByClassId;
        if (!(firClass instanceof FirRegularClass)) {
            w01.a("Failed requirement.");
            return null;
        }
        IrClassSymbol irClassSymbolCreateClassSymbol = createClassSymbol();
        FirRegularClass firRegularClass = (FirRegularClass) firClass;
        ClassId classId = firRegularClass.getSymbol().getClassId();
        ConeClassLikeLookupTag containingClassForLocalAttr = ClassMembersKt.getContainingClassForLocalAttr(firClass);
        if (containingClassForLocalAttr == null) {
            ClassId outerClassId = classId.getOuterClassId();
            containingClassForLocalAttr = (outerClassId == null || (classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(getSession()).getClassLikeSymbolByClassId(outerClassId)) == null) ? null : classLikeSymbolByClassId.getLookupTag();
        }
        IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = getDeclarationStorage().findIrParent$org_jetbrains_kotlin_fir2ir(classId.getPackageFqName(), containingClassForLocalAttr, firRegularClass.getSymbol(), firRegularClass.getOrigin());
        irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir.getClass();
        this.classCache.put(firClass, irClassSymbolCreateClassSymbol);
        if (Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir)) {
            return getLazyDeclarationsGenerator().createIrLazyClass(firRegularClass, irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, irClassSymbolCreateClassSymbol);
        }
        k2d.a("Source classes should be created separately before referencing");
        return null;
    }

    private final IrClass getCachedIrClass(FirClass klass) {
        IrClass cachedIrLocalClass = getCachedIrLocalClass(klass);
        if (cachedIrLocalClass != null) {
            return cachedIrLocalClass;
        }
        IrClassSymbol irClassSymbol = this.classCache.get(klass);
        if (irClassSymbol != null) {
            return irClassSymbol.getOwner();
        }
        return null;
    }

    public static /* synthetic */ IrTypeParameter getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir$default(Fir2IrClassifierStorage fir2IrClassifierStorage, FirTypeParameter firTypeParameter, ConversionTypeOrigin conversionTypeOrigin, int i, Object obj) {
        if ((i & 2) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return fir2IrClassifierStorage.getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir(firTypeParameter, conversionTypeOrigin);
    }

    public static /* synthetic */ IrTypeParameter getIrTypeParameter$org_jetbrains_kotlin_fir2ir$default(Fir2IrClassifierStorage fir2IrClassifierStorage, FirTypeParameter firTypeParameter, int i, ConversionTypeOrigin conversionTypeOrigin, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return fir2IrClassifierStorage.getIrTypeParameter$org_jetbrains_kotlin_fir2ir(firTypeParameter, i, conversionTypeOrigin);
    }

    private static final IrTypeParameterSymbol getIrTypeParameterSymbol$getCachedIrTypeParameterIfAny(Fir2IrClassifierStorage fir2IrClassifierStorage, FirTypeParameter firTypeParameter, ConversionTypeOrigin conversionTypeOrigin) {
        IrTypeParameterSymbol symbol;
        IrTypeParameter cachedIrTypeParameter$org_jetbrains_kotlin_fir2ir = fir2IrClassifierStorage.getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir(firTypeParameter, conversionTypeOrigin);
        if (cachedIrTypeParameter$org_jetbrains_kotlin_fir2ir != null && (symbol = cachedIrTypeParameter$org_jetbrains_kotlin_fir2ir.getSymbol()) != null) {
            return symbol;
        }
        IrTypeParameter irTypeParameter = fir2IrClassifierStorage.typeParameterCache.get(firTypeParameter);
        if (irTypeParameter != null) {
            return irTypeParameter.getSymbol();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isNonLocalInReplContext(FirRegularClass firRegularClass) {
        do {
            if ((firRegularClass != null ? ClassMembersKt.getContainingReplSymbolAttr(firRegularClass) : null) != null) {
                return true;
            }
            FirClassLikeSymbol<?> containingClassSymbol = firRegularClass != null ? ContainingClassUtilsKt.getContainingClassSymbol(firRegularClass) : null;
            FirRegularClassSymbol firRegularClassSymbol = containingClassSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingClassSymbol : null;
            firRegularClass = firRegularClassSymbol != null ? (FirRegularClass) firRegularClassSymbol.getFir() : null;
        } while (firRegularClass != null);
        return false;
    }

    @LeakedDeclarationCaches
    public final void cacheIrClass$org_jetbrains_kotlin_fir2ir(FirRegularClass regularClass, IrClass irClass) {
        regularClass.getClass();
        irClass.getClass();
        if (Intrinsics.areEqual(regularClass.getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
            this.localStorage.set(regularClass, irClass);
        } else {
            this.classCache.put(regularClass, irClass.getSymbol());
        }
    }

    public final IrClass createAndCacheAnonymousObject(FirAnonymousObject anonymousObject, Visibility visibility, Name name, IrDeclarationParent irParent) {
        anonymousObject.getClass();
        visibility.getClass();
        name.getClass();
        IrClass irClassCreateAnonymousObject = getClassifiersGenerator().createAnonymousObject(anonymousObject, visibility, name, irParent);
        this.localStorage.set(anonymousObject, irClassCreateAnonymousObject);
        return irClassCreateAnonymousObject;
    }

    public final IrClass createAndCacheCodeFragmentClass(FirCodeFragment codeFragment, IrFile containingFile) {
        codeFragment.getClass();
        containingFile.getClass();
        IrClass irClassCreateCodeFragmentClass = getClassifiersGenerator().createCodeFragmentClass(codeFragment, containingFile, createClassSymbol());
        this.codeFragmentCache.put(codeFragment, irClassCreateCodeFragmentClass);
        return irClassCreateCodeFragmentClass;
    }

    public final IrClass createAndCacheEarlierSnippetClass(FirReplSnippetSymbol snippetSymbol, IrPackageFragment containingPackageFragment) {
        snippetSymbol.getClass();
        containingPackageFragment.getClass();
        IrClassSymbol irClassSymbolCreateClassSymbol = createClassSymbol();
        IrClass irClassCreateEarlierSnippetClass = getClassifiersGenerator().createEarlierSnippetClass(snippetSymbol.getFir(), containingPackageFragment, irClassSymbolCreateClassSymbol);
        this.earlierSnippetsCache.put(snippetSymbol, irClassCreateEarlierSnippetClass);
        Fir2IrLazyClass fir2IrLazyClass = irClassCreateEarlierSnippetClass instanceof Fir2IrLazyClass ? (Fir2IrLazyClass) irClassCreateEarlierSnippetClass : null;
        if (fir2IrLazyClass != null) {
            this.classCache.put(fir2IrLazyClass.getFir(), irClassSymbolCreateClassSymbol);
        }
        return irClassCreateEarlierSnippetClass;
    }

    public final IrClass createAndCacheIrClass(FirRegularClass regularClass, IrDeclarationParent parent, IrDeclarationOrigin predefinedOrigin) {
        regularClass.getClass();
        parent.getClass();
        IrClass irClassCreateIrClass = getClassifiersGenerator().createIrClass(regularClass, parent, createClassSymbol(), predefinedOrigin);
        cacheIrClass$org_jetbrains_kotlin_fir2ir(regularClass, irClassCreateIrClass);
        return irClassCreateIrClass;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrEnumEntry createAndCacheIrEnumEntry(FirEnumEntry enumEntry, IrClass irParent, IrDeclarationOrigin predefinedOrigin) throws KotlinIllegalArgumentExceptionWithAttachments {
        enumEntry.getClass();
        irParent.getClass();
        IrEnumEntrySymbol irEnumEntrySymbol = getIrEnumEntrySymbol(enumEntry);
        FirFile firCallableContainerFile = getFirProvider().getFirCallableContainerFile(enumEntry.getSymbol());
        if (predefinedOrigin == null) {
            predefinedOrigin = firCallableContainerFile != null ? IrDeclarationOrigin.Companion.getDEFINED() : irParent.getOrigin();
        }
        return getClassifiersGenerator().createIrEnumEntry(enumEntry, irParent, irEnumEntrySymbol, predefinedOrigin);
    }

    public final IrTypeAlias createAndCacheIrTypeAlias(FirTypeAlias typeAlias, IrDeclarationParent parent) {
        typeAlias.getClass();
        parent.getClass();
        IrTypeAliasSymbol irTypeAliasSymbolImpl = new IrTypeAliasSymbolImpl((TypeAliasDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        IrTypeAlias irTypeAliasCreateIrTypeAlias = getClassifiersGenerator().createIrTypeAlias(typeAlias, parent, irTypeAliasSymbolImpl);
        this.typeAliasCache.put(typeAlias, irTypeAliasSymbolImpl);
        return irTypeAliasCreateIrTypeAlias;
    }

    @DelicateDeclarationStorageApi
    public final void forEachCachedDeclarationSymbol(Function1<? super IrSymbol, Unit> block) {
        block.getClass();
        Iterator<T> it = this.classCache.values().iterator();
        while (it.hasNext()) {
            block.invoke((IrClassSymbol) it.next());
        }
        Iterator<T> it2 = this.typeAliasCache.values().iterator();
        while (it2.hasNext()) {
            block.invoke((IrTypeAliasSymbol) it2.next());
        }
        Iterator<T> it3 = this.enumEntryCache.values().iterator();
        while (it3.hasNext()) {
            block.invoke((IrEnumEntrySymbol) it3.next());
        }
        Iterator<T> it4 = this.fieldsForContextReceivers.values().iterator();
        while (it4.hasNext()) {
            Iterator it5 = ((List) it4.next()).iterator();
            while (it5.hasNext()) {
                block.invoke(((IrField) it5.next()).getSymbol());
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    public final IrClass getCachedEarlierSnippetClass(FirReplSnippetSymbol snippetSymbol) {
        snippetSymbol.getClass();
        return this.earlierSnippetsCache.get(snippetSymbol);
    }

    public final IrClass getCachedIrCodeFragment(FirCodeFragment codeFragment) {
        codeFragment.getClass();
        return this.codeFragmentCache.get(codeFragment);
    }

    public final IrClass getCachedIrLocalClass(FirClass klass) {
        klass.getClass();
        if ((klass instanceof FirAnonymousObject) || ((klass instanceof FirRegularClass) && Intrinsics.areEqual(klass.getStatus().getVisibility(), Visibilities.Local.INSTANCE))) {
            return this.localStorage.get(klass);
        }
        return null;
    }

    public final IrTypeParameter getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir(FirTypeParameter typeParameter, ConversionTypeOrigin typeOrigin) {
        typeParameter.getClass();
        typeOrigin.getClass();
        return typeOrigin.getForSetter() ? this.typeParameterCacheForSetter.get(typeParameter) : this.typeParameterCache.get(typeParameter);
    }

    public final IrTypeAlias getCachedTypeAlias$org_jetbrains_kotlin_fir2ir(FirTypeAlias firTypeAlias) {
        firTypeAlias.getClass();
        IrTypeAliasSymbol irTypeAliasSymbol = this.typeAliasCache.get(firTypeAlias);
        if (irTypeAliasSymbol != null) {
            return irTypeAliasSymbol.getOwner();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.c.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.c.getCallablesGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.c.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.c.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.c.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.c.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.c.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.c.getFilesBeingCompiled();
    }

    @DelicateDeclarationStorageApi
    public final Fir2IrLazyClass getFir2IrLazyClass(FirClass firClass) {
        firClass.getClass();
        IrClass cachedIrClass = getCachedIrClass(firClass);
        return cachedIrClass != null ? (Fir2IrLazyClass) cachedIrClass : createFir2IrLazyClass(firClass);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    public final IrClass getIrAnonymousObjectForEnumEntry(FirAnonymousObject anonymousObject, Name name, IrClass irParent) {
        anonymousObject.getClass();
        name.getClass();
        IrClass irClass = this.localStorage.get(anonymousObject);
        if (irClass != null) {
            return irClass;
        }
        IrClass irClassCreateAndCacheAnonymousObject = createAndCacheAnonymousObject(anonymousObject, Visibilities.Private.INSTANCE, name, irParent);
        getClassifiersGenerator().processClassHeader(anonymousObject, irClassCreateAndCacheAnonymousObject);
        return irClassCreateAndCacheAnonymousObject;
    }

    public final IrClass getIrClass(FirClass firClass) {
        firClass.getClass();
        IrClass cachedIrClass = getCachedIrClass(firClass);
        if (cachedIrClass != null) {
            return cachedIrClass;
        }
        if (firClass instanceof FirAnonymousObject) {
            return createAndCacheLocalIrClassOnTheFly(firClass);
        }
        if (firClass instanceof FirRegularClass) {
            return (!Intrinsics.areEqual(firClass.getStatus().getVisibility(), Visibilities.Local.INSTANCE) || isNonLocalInReplContext((FirRegularClass) firClass)) ? createFir2IrLazyClass(firClass) : createAndCacheLocalIrClassOnTheFly(firClass);
        }
        bu8.a();
        return null;
    }

    public final IrClass getIrClassForNotFoundClass(ConeClassLikeLookupTag classLikeLookupTag) {
        classLikeLookupTag.getClass();
        ConcurrentHashMap<ConeClassLikeLookupTag, IrClass> concurrentHashMap = this.notFoundClassCache;
        IrClass irClass = concurrentHashMap.get(classLikeLookupTag);
        if (irClass == null) {
            IrClass irClassCreateIrClassForNotFoundClass = getClassifiersGenerator().createIrClassForNotFoundClass(classLikeLookupTag);
            IrClass irClassPutIfAbsent = concurrentHashMap.putIfAbsent(classLikeLookupTag, irClassCreateIrClassForNotFoundClass);
            irClass = irClassPutIfAbsent == null ? irClassCreateIrClassForNotFoundClass : irClassPutIfAbsent;
        }
        irClass.getClass();
        return irClass;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrClassSymbol getIrClassSymbol(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        return getIrClass((FirClass) firClassSymbol.getFir()).getSymbol();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrEnumEntrySymbol getIrEnumEntrySymbol(FirEnumEntry enumEntry) throws KotlinIllegalArgumentExceptionWithAttachments {
        enumEntry.getClass();
        IrEnumEntrySymbol irEnumEntrySymbol = this.enumEntryCache.get(enumEntry);
        if (irEnumEntrySymbol != null) {
            return irEnumEntrySymbol;
        }
        IrEnumEntrySymbol irEnumEntrySymbolImpl = new IrEnumEntrySymbolImpl((ClassDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        this.enumEntryCache.put(enumEntry, irEnumEntrySymbolImpl);
        IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = getDeclarationStorage().findIrParent$org_jetbrains_kotlin_fir2ir(enumEntry, null);
        irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir.getClass();
        IrClass irClass = (IrClass) irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir;
        if (Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irClass)) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(enumEntry, FirResolvePhase.ANNOTATION_ARGUMENTS);
            getClassifiersGenerator().createIrEnumEntry(enumEntry, irClass, irEnumEntrySymbolImpl, irClass.getOrigin());
        }
        return irEnumEntrySymbolImpl;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrTypeAliasSymbol getIrTypeAliasSymbol(FirTypeAliasSymbol firTypeAliasSymbol) {
        firTypeAliasSymbol.getClass();
        FirTypeAlias firTypeAlias = (FirTypeAlias) firTypeAliasSymbol.getFir();
        IrTypeAlias cachedTypeAlias$org_jetbrains_kotlin_fir2ir = getCachedTypeAlias$org_jetbrains_kotlin_fir2ir(firTypeAlias);
        if (cachedTypeAlias$org_jetbrains_kotlin_fir2ir != null) {
            return cachedTypeAlias$org_jetbrains_kotlin_fir2ir.getSymbol();
        }
        ClassId classId = firTypeAliasSymbol.getClassId();
        ClassId outerClassId = classId.getOuterClassId();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = outerClassId != null ? FirSymbolProviderKt.getSymbolProvider(getSession()).getClassLikeSymbolByClassId(outerClassId) : null;
        IrDeclarationParent irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir = getDeclarationStorage().findIrParent$org_jetbrains_kotlin_fir2ir(classId.getPackageFqName(), classLikeSymbolByClassId != null ? classLikeSymbolByClassId.getLookupTag() : null, firTypeAliasSymbol, firTypeAlias.getOrigin());
        irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir.getClass();
        IrTypeAliasSymbol irTypeAliasSymbolImpl = new IrTypeAliasSymbolImpl((TypeAliasDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
        this.typeAliasCache.put(firTypeAlias, irTypeAliasSymbolImpl);
        getLazyDeclarationsGenerator().createIrLazyTypeAlias(firTypeAlias, irDeclarationParentFindIrParent$org_jetbrains_kotlin_fir2ir, irTypeAliasSymbolImpl);
        return irTypeAliasSymbolImpl;
    }

    public final IrTypeParameter getIrTypeParameter$org_jetbrains_kotlin_fir2ir(FirTypeParameter typeParameter, int index, ConversionTypeOrigin typeOrigin) {
        typeParameter.getClass();
        typeOrigin.getClass();
        IrTypeParameter cachedIrTypeParameter$org_jetbrains_kotlin_fir2ir = getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir(typeParameter, typeOrigin);
        if (cachedIrTypeParameter$org_jetbrains_kotlin_fir2ir != null) {
            return cachedIrTypeParameter$org_jetbrains_kotlin_fir2ir;
        }
        IrTypeParameter irTypeParameterCreateAndCacheIrTypeParameter = createAndCacheIrTypeParameter(typeParameter, index, typeOrigin);
        getClassifiersGenerator().initializeTypeParameterBounds(typeParameter, irTypeParameterCreateAndCacheIrTypeParameter);
        return irTypeParameterCreateAndCacheIrTypeParameter;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final IrTypeParameterSymbol getIrTypeParameterSymbol(FirTypeParameterSymbol firTypeParameterSymbol, ConversionTypeOrigin typeOrigin) throws KotlinIllegalArgumentExceptionWithAttachments {
        firTypeParameterSymbol.getClass();
        typeOrigin.getClass();
        FirTypeParameter firTypeParameter = (FirTypeParameter) firTypeParameterSymbol.getFir();
        IrTypeParameterSymbol irTypeParameterSymbol$getCachedIrTypeParameterIfAny = getIrTypeParameterSymbol$getCachedIrTypeParameterIfAny(this, firTypeParameter, typeOrigin);
        if (irTypeParameterSymbol$getCachedIrTypeParameterIfAny != null) {
            return irTypeParameterSymbol$getCachedIrTypeParameterIfAny;
        }
        if (this.c.getConfiguration().getAllowNonCachedDeclarations()) {
            FirBasedSymbol<?> containingDeclarationSymbol = firTypeParameter.getContainingDeclarationSymbol();
            if (containingDeclarationSymbol instanceof FirRegularClassSymbol) {
                getIrClassSymbol((FirClassSymbol<?>) containingDeclarationSymbol);
            } else if (containingDeclarationSymbol instanceof FirNamedFunctionSymbol) {
                Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), (FirFunctionSymbol) containingDeclarationSymbol, null, false, 6, null);
            } else if (containingDeclarationSymbol instanceof FirPropertySymbol) {
                Fir2IrDeclarationStorage.getIrPropertySymbol$default(getDeclarationStorage(), (FirPropertySymbol) containingDeclarationSymbol, null, 2, null);
            }
            IrTypeParameterSymbol irTypeParameterSymbol$getCachedIrTypeParameterIfAny2 = getIrTypeParameterSymbol$getCachedIrTypeParameterIfAny(this, firTypeParameter, typeOrigin);
            if (irTypeParameterSymbol$getCachedIrTypeParameterIfAny2 != null) {
                return irTypeParameterSymbol$getCachedIrTypeParameterIfAny2;
            }
        }
        StringBuilder sb = new StringBuilder("Cannot find cached type parameter by FIR symbol: ");
        sb.append(firTypeParameterSymbol.getName());
        ej7.a(sb, " of the owner: ", firTypeParameter.getContainingDeclarationSymbol());
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.c.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.c.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.c.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(FirTypeParameterRefsOwner owner) {
        Fir2IrClassifierStorage fir2IrClassifierStorage;
        owner.getClass();
        Iterator<T> it = owner.getTypeParameters().iterator();
        int i = 0;
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return;
            }
            i = i2 + 1;
            FirTypeParameter firTypeParameter = (FirTypeParameter) ((FirTypeParameterRef) it.next()).getSymbol().getFir();
            if (getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir$default(this, firTypeParameter, null, 2, null) == null) {
                fir2IrClassifierStorage = this;
                createAndCacheIrTypeParameter$default(fir2IrClassifierStorage, firTypeParameter, i2, null, 4, null);
            } else {
                fir2IrClassifierStorage = this;
            }
            if ((owner instanceof FirProperty) && ((FirProperty) owner).getIsVar()) {
                ConversionTypeOrigin conversionTypeOrigin = ConversionTypeOrigin.SETTER;
                if (fir2IrClassifierStorage.getCachedIrTypeParameter$org_jetbrains_kotlin_fir2ir(firTypeParameter, conversionTypeOrigin) == null) {
                    fir2IrClassifierStorage.createAndCacheIrTypeParameter(firTypeParameter, i2, conversionTypeOrigin);
                }
            }
            this = fir2IrClassifierStorage;
        }
    }

    public final void processMembersOfClassesCreatedOnTheFly() {
        this.processMembersOfClassesOnTheFlyImmediately = true;
        for (Map.Entry<FirClass, IrClass> entry : this.localClassesCreatedOnTheFly.entrySet()) {
            FirClass key = entry.getKey();
            IrClass value = entry.getValue();
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            fir2IrConversionScope.getContainingFirClassStack().add(key);
            try {
                getClassifiersGenerator().processClassHeader(key, value);
                getConverter().processClassMembers$org_jetbrains_kotlin_fir2ir(key, value);
                fir2IrConversionScope.getContainingFirClassStack().remove(fir2IrConversionScope.getContainingFirClassStack().size() - 1);
            } catch (Throwable th) {
                fir2IrConversionScope.getContainingFirClassStack().remove(fir2IrConversionScope.getContainingFirClassStack().size() - 1);
                throw th;
            }
        }
        this.localClassesCreatedOnTheFly.clear();
    }

    public final void putEnumEntryClassInScope(FirEnumEntry enumEntry, IrClass correspondingClass) {
        enumEntry.getClass();
        correspondingClass.getClass();
        Fir2IrLocalClassStorage fir2IrLocalClassStorage = this.localStorage;
        FirExpression initializer = enumEntry.getInitializer();
        initializer.getClass();
        fir2IrLocalClassStorage.set(((FirAnonymousObjectExpression) initializer).getAnonymousObject(), correspondingClass);
    }

    public final IrClassSymbol getIrClassSymbol(ConeClassLikeLookupTag lookupTag) {
        lookupTag.getClass();
        IrClass irClass = getIrClass(lookupTag);
        if (irClass != null) {
            return irClass.getSymbol();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final IrClass getIrClass(ConeClassLikeLookupTag lookupTag) {
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(this, lookupTag);
        if (classSymbol == null) {
            return null;
        }
        return getIrClass((FirClass) classSymbol.getFir());
    }
}
