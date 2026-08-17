package org.jetbrains.kotlin.fir.backend.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.util.MultimapKt;
import org.jetbrains.kotlin.fir.util.SetMultimap;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ä\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J/\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0002\b\rJ/\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\t0\u00070\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\rJ/\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\u00070\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\rJ\u0096\u0001\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u00020\t0\u00070\u0006\"\u000e\b\u0000\u0010\u0013\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u0002H\u00132#\u0010\u0016\u001a\u001f\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u00060\u0017¢\u0006\u0002\b\u00192/\u0010\u001a\u001a+\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u0013\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u001d0\u001b¢\u0006\u0002\b\u0019H\u0082\b¢\u0006\u0002\u0010\u001eJa\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0006\"\u000e\b\u0000\u0010\u0013\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010 \u001a\u0002H\u00132#\u0010\u0016\u001a\u001f\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u00060\u0017¢\u0006\u0002\b\u00192\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\tH\u0082\b¢\u0006\u0002\u0010$J*\u0010%\u001a\u0002H\u0013\"\u000e\b\u0000\u0010\u0013\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0014*\u0002H\u00132\u0006\u0010#\u001a\u00020\tH\u0082\b¢\u0006\u0002\u0010&J\"\u0010'\u001a\u0002H\u0013\"\u000e\b\u0000\u0010\u0013\u0018\u0001*\u0006\u0012\u0002\b\u00030\u0014*\u0002H\u0013H\u0082\b¢\u0006\u0002\u0010(Je\u0010)\u001a\u0002H\u0013\"\u000e\b\u0000\u0010\u0013\u0018\u0001*\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010*\u001a\u00020\t2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H\u00130,2/\u0010\u001a\u001a+\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u0002H\u0013\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u001d0\u001b¢\u0006\u0002\b\u0019H\u0082\b¢\u0006\u0002\u0010-J\u001f\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\f\u001a\u00020/2\u0006\u00100\u001a\u00020\tH\u0000¢\u0006\u0002\b1J\u001f\u00102\u001a\u0004\u0018\u0001032\u0006\u0010\u000f\u001a\u0002032\u0006\u00100\u001a\u00020\tH\u0000¢\u0006\u0002\b4J\u001f\u00105\u001a\u0004\u0018\u0001062\u0006\u0010\u0011\u001a\u0002062\u0006\u00100\u001a\u00020\tH\u0000¢\u0006\u0002\b7J_\u00108\u001a\u0004\u0018\u0001H9\"\b\b\u0000\u00109*\u00020:\"\u0010\b\u0001\u0010\u0013\u0018\u0001*\b\u0012\u0004\u0012\u0002H90\u00142\u0006\u00100\u001a\u00020\t2\u0006\u0010\u0015\u001a\u0002H\u00132!\u0010;\u001a\u001d\u0012\u0013\u0012\u0011H9¢\u0006\f\b<\u0012\b\b=\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002H\u00130\u001cH\u0082\b¢\u0006\u0002\u0010?R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010@\u001a\u00020AX\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0012\u0010D\u001a\u00020EX\u0096\u0005¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0012\u0010H\u001a\u00020IX\u0096\u0005¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0012\u0010L\u001a\u00020MX\u0096\u0005¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0012\u0010P\u001a\u00020QX\u0096\u0005¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0012\u0010T\u001a\u00020UX\u0096\u0005¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0012\u0010X\u001a\u00020YX\u0096\u0005¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0012\u0010\\\u001a\u00020]X\u0096\u0005¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0012\u0010`\u001a\u00020aX\u0096\u0005¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0012\u0010d\u001a\u00020eX\u0096\u0005¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0012\u0010h\u001a\u00020iX\u0096\u0005¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0012\u0010l\u001a\u00020mX\u0096\u0005¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0012\u0010p\u001a\u00020qX\u0096\u0005¢\u0006\u0006\u001a\u0004\br\u0010sR\u001a\u0010t\u001a\n\u0012\u0004\u0012\u00020v\u0018\u00010uX\u0096\u0005¢\u0006\u0006\u001a\u0004\bw\u0010xR\u0012\u0010y\u001a\u00020zX\u0096\u0005¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0013\u0010}\u001a\u00020~X\u0096\u0005¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0016\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u001c\u0010\u0085\u0001\u001a\t\u0012\u0005\u0012\u00030\u0086\u00010\u0006X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0016\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0015\u0010\u008d\u0001\u001a\u00020\u0000X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0016\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0016\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0018\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0016\u0010 \u0001\u001a\u00030¡\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¢\u0001\u0010£\u0001R\u0016\u0010¤\u0001\u001a\u00030¥\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¦\u0001\u0010§\u0001R\u0016\u0010¨\u0001\u001a\u00030©\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001¨\u0006¬\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)V", "computeFakeOverrideKeys", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "originalFunction", "computeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "originalProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "originalField", "computeFakeOverrideKeysImpl", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "originalSymbol", "directOverridden", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lkotlin/ExtensionFunctionType;", "processOverridden", "Lkotlin/Function3;", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/List;", "computeBaseSymbols", "symbol", "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "containingClass", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function2;Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Ljava/util/List;", "unwrapSubstitutionOverride", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "unwrapRenamedForOverride", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "chooseMostSpecificOverridden", "containingClassLookupTag", "overridden", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Ljava/util/Collection;Lkotlin/jvm/functions/Function3;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "createFirFunctionFakeOverrideIfNeeded", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "dispatchReceiverLookupTag", "createFirFunctionFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir", "createFirPropertyFakeOverrideIfNeeded", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "createFirPropertyFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir", "createFirFieldFakeOverrideIfNeeded", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "createFirFieldFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir", "createFirFakeOverrideIfNeeded", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "createFakeOverrideSymbol", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "firDeclaration", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyFakeOverrideGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;

    public Fir2IrLazyFakeOverrideGenerator(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        this.c = fir2IrComponents;
    }

    public static Unit b(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static Unit c(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<Pair<FirFieldSymbol, ConeClassLikeLookupTag>> computeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir(FirClass klass, FirFieldSymbol originalField) {
        List<FirCallableSymbol> listEmptyList;
        List<ConeClassLikeType> superConeTypes;
        FirCallableSymbol firCallableSymbol;
        Object next;
        klass.getClass();
        originalField.getClass();
        FirTypeScope firTypeScopeUnsubstitutedScope = ScopeUtilsKt.unsubstitutedScope(this, klass);
        ConeClassLikeLookupTag lookupTag = klass.getSymbol().getLookupTag();
        if (((FirCallableDeclaration) originalField.getFir()).getOrigin() instanceof FirDeclarationOrigin.SubstitutionOverride) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) originalField.getFir();
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            FirFieldSymbol firFieldSymbol = (FirFieldSymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
            firFieldSymbol.getClass();
            listEmptyList = CollectionsKt.listOf(firFieldSymbol);
        } else if (firTypeScopeUnsubstitutedScope == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) originalField.getFir();
            FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
            if (originalForSubstitutionOverrideAttr2 == null) {
                originalForSubstitutionOverrideAttr2 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
            }
            List<FirCallableSymbol> listListOfNotNull = CollectionsKt.listOfNotNull((FirFieldSymbol) (originalForSubstitutionOverrideAttr2 != null ? originalForSubstitutionOverrideAttr2.getSymbol() : null));
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listListOfNotNull, 10));
            for (FirCallableSymbol firCallableSymbol2 : listListOfNotNull) {
                if (Intrinsics.areEqual(firCallableSymbol2.getOrigin(), FirDeclarationOrigin.RenamedForOverride.INSTANCE)) {
                    ClassMembersKt.getInitialSignatureAttr((FirCallableDeclaration) firCallableSymbol2.getFir());
                }
                if (CallableIdUtilsKt.isRealOwnerOf(lookupTag, firCallableSymbol2) && ClassMembersKt.isSubstitutionOverride((FirCallableDeclaration) firCallableSymbol2.getFir())) {
                    FirCallableDeclaration firCallableDeclaration3 = (FirCallableDeclaration) firCallableSymbol2.getFir();
                    FirCallableDeclaration originalForSubstitutionOverrideAttr3 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration3) || (firCallableDeclaration3.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration3) : null;
                    firCallableSymbol2 = (FirFieldSymbol) (originalForSubstitutionOverrideAttr3 != null ? originalForSubstitutionOverrideAttr3.getSymbol() : null);
                    firCallableSymbol2.getClass();
                }
                arrayList.add(firCallableSymbol2);
            }
            listEmptyList = arrayList;
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        SetMultimap<Map.Entry> multimapOf = MultimapKt.setMultimapOf();
        if (getConfiguration().getSkipBodies()) {
            List<ConeClassLikeType> superConeTypes2 = FirDeclarationUtilKt.getSuperConeTypes(klass);
            superConeTypes = new ArrayList();
            for (Object obj : superConeTypes2) {
                if (ToSymbolUtilsKt.toClassSymbol(this, ((ConeClassLikeType) obj).getLookupTag()) != null) {
                    superConeTypes.add(obj);
                }
            }
        } else {
            superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(klass);
        }
        for (FirCallableSymbol firCallableSymbol3 : listEmptyList) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol3);
            if (coneClassLikeLookupTagContainingClassLookupTag != null) {
                for (ConeClassLikeType coneClassLikeType : superConeTypes) {
                    if (typeContext.anySuperTypeConstructor(coneClassLikeType, new Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeysImpl$1$compatibleType$1(typeContext, coneClassLikeLookupTagContainingClassLookupTag))) {
                        multimapOf.put(coneClassLikeType.getLookupTag(), firCallableSymbol3);
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(multimapOf, 10));
        for (Map.Entry entry : multimapOf) {
            ConeClassLikeLookupTag coneClassLikeLookupTag = (ConeClassLikeLookupTag) entry.getKey();
            Set set = (Set) entry.getValue();
            int size = set.size();
            if (size == 0) {
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return null;
            }
            if (size != 1) {
                Set set2 = set;
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, coneClassLikeLookupTag);
                if (regularClassSymbol == null || ScopeUtilsKt.unsubstitutedScope(this, regularClassSymbol) == null) {
                    firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set2);
                } else {
                    Set<FirCallableSymbol> set3 = set2;
                    Iterator it = set3.iterator();
                    if (it.hasNext()) {
                        next = it.next();
                        FirCallableSymbol firCallableSymbol4 = (FirCallableSymbol) next;
                        if (!(set3 instanceof Collection) || !set3.isEmpty()) {
                            for (final FirCallableSymbol firCallableSymbol5 : set3) {
                                if (!Intrinsics.areEqual(firCallableSymbol4, firCallableSymbol5)) {
                                    final Function1<FirFieldSymbol, Boolean> function1 = new Function1<FirFieldSymbol, Boolean>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeys$$inlined$computeFakeOverrideKeysImpl$5
                                        public final Boolean invoke(FirFieldSymbol firFieldSymbol2) {
                                            firFieldSymbol2.getClass();
                                            return Boolean.valueOf(Intrinsics.areEqual(firFieldSymbol2, firCallableSymbol5));
                                        }
                                    };
                                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                                    new Function1<FirFieldSymbol, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeys$$inlined$computeFakeOverrideKeysImpl$6
                                        public final ProcessorAction invoke(FirFieldSymbol firFieldSymbol2) {
                                            firFieldSymbol2.getClass();
                                            if (!((Boolean) function1.invoke(firFieldSymbol2)).booleanValue()) {
                                                return ProcessorAction.NEXT;
                                            }
                                            booleanRef.element = true;
                                            return ProcessorAction.STOP;
                                        }
                                    };
                                    AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                                    wq6.a();
                                    return null;
                                }
                            }
                        }
                    } else {
                        next = null;
                    }
                    firCallableSymbol = (FirCallableSymbol) next;
                    if (firCallableSymbol == null) {
                        firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set3);
                    }
                }
            } else {
                firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set);
            }
            arrayList2.add(TuplesKt.to(firCallableSymbol, coneClassLikeLookupTag));
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirField createFirFieldFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir(FirField originalField, ConeClassLikeLookupTag dispatchReceiverLookupTag) {
        originalField.getClass();
        dispatchReceiverLookupTag.getClass();
        FirFieldSymbol symbol = originalField.getSymbol();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) symbol.getFir();
        FirCallableDeclaration firCallableDeclaration2 = null;
        if (!Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag(symbol), dispatchReceiverLookupTag) && !Intrinsics.areEqual(firCallableDeclaration.getStatus().getVisibility(), Visibilities.InvisibleFake.INSTANCE)) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, dispatchReceiverLookupTag);
            regularClassSymbol.getClass();
            firCallableDeclaration2 = (FirCallableDeclaration) FirFakeOverrideGenerator.createSubstitutionOverrideField$default(FirFakeOverrideGenerator.INSTANCE, getSession(), (FirField) firCallableDeclaration, dispatchReceiverLookupTag, null, org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt.defaultType(regularClassSymbol), FirDeclarationOrigin.SubstitutionOverride.DeclarationSite.INSTANCE, 8, null).getFir();
        }
        return (FirField) firCallableDeclaration2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunction createFirFunctionFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir(FirNamedFunction originalFunction, ConeClassLikeLookupTag dispatchReceiverLookupTag) {
        originalFunction.getClass();
        dispatchReceiverLookupTag.getClass();
        FirNamedFunctionSymbol symbol = originalFunction.getSymbol();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) symbol.getFir();
        FirCallableDeclaration firCallableDeclaration2 = null;
        if (!Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag(symbol), dispatchReceiverLookupTag) && !Intrinsics.areEqual(firCallableDeclaration.getStatus().getVisibility(), Visibilities.InvisibleFake.INSTANCE)) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firCallableDeclaration;
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, dispatchReceiverLookupTag);
            regularClassSymbol.getClass();
            firCallableDeclaration2 = (FirCallableDeclaration) FirFakeOverrideGenerator.INSTANCE.createSubstitutionOverrideFunction(getSession(), new FirNamedFunctionSymbol(new CallableId(regularClassSymbol.getClassId(), symbol.getCallableId().getCallableName())), firNamedFunction, dispatchReceiverLookupTag, org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt.defaultType(regularClassSymbol), FirDeclarationOrigin.SubstitutionOverride.DeclarationSite.INSTANCE, (6080 & 64) != 0 ? null : null, (List<? extends ConeKotlinType>) ((6080 & 128) != 0 ? null : null), (6080 & 256) != 0 ? null : null, (List<? extends ConeKotlinType>) ((6080 & 512) != 0 ? null : null), (List<? extends FirTypeParameter>) ((6080 & 1024) != 0 ? null : null), (6080 & 2048) != 0 ? firNamedFunction.getStatus().isExpect() : regularClassSymbol.getRawStatus().isExpect() || firNamedFunction.getStatus().isExpect(), (6080 & 4096) != 0 ? null : null).getFir();
        }
        return (FirNamedFunction) firCallableDeclaration2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirProperty createFirPropertyFakeOverrideIfNeeded$org_jetbrains_kotlin_fir2ir(FirProperty originalProperty, ConeClassLikeLookupTag dispatchReceiverLookupTag) {
        originalProperty.getClass();
        dispatchReceiverLookupTag.getClass();
        FirPropertySymbol symbol = originalProperty.getSymbol();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) symbol.getFir();
        FirCallableDeclaration firCallableDeclaration2 = null;
        if (!Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag(symbol), dispatchReceiverLookupTag) && !Intrinsics.areEqual(firCallableDeclaration.getStatus().getVisibility(), Visibilities.InvisibleFake.INSTANCE)) {
            FirProperty firProperty = (FirProperty) firCallableDeclaration;
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, dispatchReceiverLookupTag);
            regularClassSymbol.getClass();
            firCallableDeclaration2 = (FirCallableDeclaration) FirFakeOverrideGenerator.INSTANCE.createSubstitutionOverrideProperty(getSession(), new FirRegularPropertySymbol(new CallableId(regularClassSymbol.getClassId(), symbol.getName())), firProperty, dispatchReceiverLookupTag, org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt.defaultType(regularClassSymbol), FirDeclarationOrigin.SubstitutionOverride.DeclarationSite.INSTANCE, (15296 & 64) != 0 ? null : null, (15296 & 128) != 0 ? null : null, (15296 & 256) != 0 ? null : null, (15296 & 512) != 0 ? null : null, (15296 & 1024) != 0 ? firProperty.getStatus().isExpect() : regularClassSymbol.getRawStatus().isExpect() || firProperty.getStatus().isExpect(), (15296 & 2048) != 0 ? null : null, (15296 & 4096) != 0 ? null : null, (15296 & 8192) != 0 ? null : null).getFir();
        }
        return (FirProperty) firCallableDeclaration2;
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

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
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
    public final List<Pair<FirPropertySymbol, ConeClassLikeLookupTag>> computeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir(FirClass klass, FirPropertySymbol originalProperty) {
        List<FirCallableSymbol> listEmptyList;
        List<ConeClassLikeType> superConeTypes;
        FirCallableSymbol firCallableSymbol;
        FirTypeScope firTypeScopeUnsubstitutedScope;
        Object next;
        boolean z;
        klass.getClass();
        originalProperty.getClass();
        if (this.c.getConfiguration().getAllowNonCachedDeclarations()) {
            ScopeUtilsKt.unsubstitutedScope(this, klass).processPropertiesByName(originalProperty.getName(), new Function1() { // from class: bw4
                public final Object invoke(Object obj) {
                    return Fir2IrLazyFakeOverrideGenerator.c((FirVariableSymbol) obj);
                }
            });
        }
        FirTypeScope firTypeScopeUnsubstitutedScope2 = ScopeUtilsKt.unsubstitutedScope(this, klass);
        ConeClassLikeLookupTag lookupTag = klass.getSymbol().getLookupTag();
        if (((FirCallableDeclaration) originalProperty.getFir()).getOrigin() instanceof FirDeclarationOrigin.SubstitutionOverride) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) originalProperty.getFir();
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
            firPropertySymbol.getClass();
            listEmptyList = CollectionsKt.listOf(firPropertySymbol);
        } else if (firTypeScopeUnsubstitutedScope2 == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            List<FirCallableSymbol> directOverriddenProperties$default = FirTypeScopeKt.getDirectOverriddenProperties$default(firTypeScopeUnsubstitutedScope2, originalProperty, false, 2, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(directOverriddenProperties$default, 10));
            for (FirCallableSymbol firCallableSymbol2 : directOverriddenProperties$default) {
                if (Intrinsics.areEqual(firCallableSymbol2.getOrigin(), FirDeclarationOrigin.RenamedForOverride.INSTANCE)) {
                    ClassMembersKt.getInitialSignatureAttr((FirCallableDeclaration) firCallableSymbol2.getFir());
                }
                if (CallableIdUtilsKt.isRealOwnerOf(lookupTag, firCallableSymbol2) && ClassMembersKt.isSubstitutionOverride((FirCallableDeclaration) firCallableSymbol2.getFir())) {
                    FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) firCallableSymbol2.getFir();
                    FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                    firCallableSymbol2 = (FirPropertySymbol) (originalForSubstitutionOverrideAttr2 != null ? originalForSubstitutionOverrideAttr2.getSymbol() : null);
                    firCallableSymbol2.getClass();
                }
                arrayList.add(firCallableSymbol2);
            }
            listEmptyList = arrayList;
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        SetMultimap<Map.Entry> multimapOf = MultimapKt.setMultimapOf();
        if (getConfiguration().getSkipBodies()) {
            List<ConeClassLikeType> superConeTypes2 = FirDeclarationUtilKt.getSuperConeTypes(klass);
            superConeTypes = new ArrayList();
            for (Object obj : superConeTypes2) {
                if (ToSymbolUtilsKt.toClassSymbol(this, ((ConeClassLikeType) obj).getLookupTag()) != null) {
                    superConeTypes.add(obj);
                }
            }
        } else {
            superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(klass);
        }
        for (FirCallableSymbol firCallableSymbol3 : listEmptyList) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol3);
            if (coneClassLikeLookupTagContainingClassLookupTag != null) {
                for (ConeClassLikeType coneClassLikeType : superConeTypes) {
                    if (typeContext.anySuperTypeConstructor(coneClassLikeType, new Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeysImpl$1$compatibleType$1(typeContext, coneClassLikeLookupTagContainingClassLookupTag))) {
                        multimapOf.put(coneClassLikeType.getLookupTag(), firCallableSymbol3);
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(multimapOf, 10));
        for (Map.Entry entry : multimapOf) {
            ConeClassLikeLookupTag coneClassLikeLookupTag = (ConeClassLikeLookupTag) entry.getKey();
            Set set = (Set) entry.getValue();
            int size = set.size();
            if (size != 0) {
                if (size != 1) {
                    Set set2 = set;
                    FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, coneClassLikeLookupTag);
                    if (regularClassSymbol != null && (firTypeScopeUnsubstitutedScope = ScopeUtilsKt.unsubstitutedScope(this, regularClassSymbol)) != null) {
                        Set set3 = set2;
                        Iterator it = set3.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                            FirCallableSymbol firCallableSymbol4 = (FirCallableSymbol) next;
                            if ((set3 instanceof Collection) && set3.isEmpty()) {
                                break;
                            }
                            Iterator it2 = set3.iterator();
                            do {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                final FirCallableSymbol firCallableSymbol5 = (FirCallableSymbol) it2.next();
                                if (Intrinsics.areEqual(firCallableSymbol4, firCallableSymbol5)) {
                                    z = true;
                                } else {
                                    final Function1<FirPropertySymbol, Boolean> function1 = new Function1<FirPropertySymbol, Boolean>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeys$$inlined$computeFakeOverrideKeysImpl$3
                                        public final Boolean invoke(FirPropertySymbol firPropertySymbol2) {
                                            firPropertySymbol2.getClass();
                                            return Boolean.valueOf(Intrinsics.areEqual(firPropertySymbol2, firCallableSymbol5));
                                        }
                                    };
                                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                                    FirTypeScopeKt.processOverriddenProperties(firTypeScopeUnsubstitutedScope, (FirPropertySymbol) firCallableSymbol4, new Function1<FirPropertySymbol, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeys$$inlined$computeFakeOverrideKeysImpl$4
                                        public final ProcessorAction invoke(FirPropertySymbol firPropertySymbol2) {
                                            firPropertySymbol2.getClass();
                                            if (!((Boolean) function1.invoke(firPropertySymbol2)).booleanValue()) {
                                                return ProcessorAction.NEXT;
                                            }
                                            booleanRef.element = true;
                                            return ProcessorAction.STOP;
                                        }
                                    });
                                    z = booleanRef.element;
                                }
                            } while (z);
                        }
                        firCallableSymbol = (FirCallableSymbol) next;
                        if (firCallableSymbol == null) {
                            firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set3);
                        }
                    } else {
                        firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set2);
                    }
                } else {
                    firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set);
                }
                arrayList2.add(TuplesKt.to(firCallableSymbol, coneClassLikeLookupTag));
            } else {
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return null;
            }
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<Pair<FirNamedFunctionSymbol, ConeClassLikeLookupTag>> computeFakeOverrideKeys$org_jetbrains_kotlin_fir2ir(FirClass klass, FirNamedFunctionSymbol originalFunction) {
        List<FirCallableSymbol> listEmptyList;
        List<ConeClassLikeType> superConeTypes;
        FirCallableSymbol firCallableSymbol;
        FirTypeScope firTypeScopeUnsubstitutedScope;
        Object next;
        boolean z;
        klass.getClass();
        originalFunction.getClass();
        if (this.c.getConfiguration().getAllowNonCachedDeclarations()) {
            ScopeUtilsKt.unsubstitutedScope(this, klass).processFunctionsByName(originalFunction.getName(), new Function1() { // from class: aw4
                public final Object invoke(Object obj) {
                    return Fir2IrLazyFakeOverrideGenerator.b((FirNamedFunctionSymbol) obj);
                }
            });
        }
        FirTypeScope firTypeScopeUnsubstitutedScope2 = ScopeUtilsKt.unsubstitutedScope(this, klass);
        ConeClassLikeLookupTag lookupTag = klass.getSymbol().getLookupTag();
        if (((FirCallableDeclaration) originalFunction.getFir()).getOrigin() instanceof FirDeclarationOrigin.SubstitutionOverride) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) originalFunction.getFir();
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) (originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null);
            firNamedFunctionSymbol.getClass();
            listEmptyList = CollectionsKt.listOf(firNamedFunctionSymbol);
        } else if (firTypeScopeUnsubstitutedScope2 == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            List<FirCallableSymbol> directOverriddenFunctions$default = FirTypeScopeKt.getDirectOverriddenFunctions$default(firTypeScopeUnsubstitutedScope2, originalFunction, false, 2, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(directOverriddenFunctions$default, 10));
            for (FirCallableSymbol firCallableSymbol2 : directOverriddenFunctions$default) {
                if (Intrinsics.areEqual(firCallableSymbol2.getOrigin(), FirDeclarationOrigin.RenamedForOverride.INSTANCE)) {
                    FirNamedFunctionSymbol initialSignatureAttr = ClassMembersKt.getInitialSignatureAttr((FirCallableDeclaration) firCallableSymbol2.getFir());
                    if (initialSignatureAttr == null) {
                        initialSignatureAttr = null;
                    }
                    if (initialSignatureAttr != null) {
                        firCallableSymbol2 = initialSignatureAttr;
                    }
                }
                if (CallableIdUtilsKt.isRealOwnerOf(lookupTag, firCallableSymbol2) && ClassMembersKt.isSubstitutionOverride((FirCallableDeclaration) firCallableSymbol2.getFir())) {
                    FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) firCallableSymbol2.getFir();
                    FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                    firCallableSymbol2 = (FirNamedFunctionSymbol) (originalForSubstitutionOverrideAttr2 != null ? originalForSubstitutionOverrideAttr2.getSymbol() : null);
                    firCallableSymbol2.getClass();
                }
                arrayList.add(firCallableSymbol2);
            }
            listEmptyList = arrayList;
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(getSession());
        SetMultimap<Map.Entry> multimapOf = MultimapKt.setMultimapOf();
        if (getConfiguration().getSkipBodies()) {
            List<ConeClassLikeType> superConeTypes2 = FirDeclarationUtilKt.getSuperConeTypes(klass);
            superConeTypes = new ArrayList();
            for (Object obj : superConeTypes2) {
                if (ToSymbolUtilsKt.toClassSymbol(this, ((ConeClassLikeType) obj).getLookupTag()) != null) {
                    superConeTypes.add(obj);
                }
            }
        } else {
            superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(klass);
        }
        for (FirCallableSymbol firCallableSymbol3 : listEmptyList) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol3);
            if (coneClassLikeLookupTagContainingClassLookupTag != null) {
                for (ConeClassLikeType coneClassLikeType : superConeTypes) {
                    if (typeContext.anySuperTypeConstructor(coneClassLikeType, new Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeysImpl$1$compatibleType$1(typeContext, coneClassLikeLookupTagContainingClassLookupTag))) {
                        multimapOf.put(coneClassLikeType.getLookupTag(), firCallableSymbol3);
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(multimapOf, 10));
        for (Map.Entry entry : multimapOf) {
            ConeClassLikeLookupTag coneClassLikeLookupTag = (ConeClassLikeLookupTag) entry.getKey();
            Set set = (Set) entry.getValue();
            int size = set.size();
            if (size != 0) {
                if (size != 1) {
                    Set set2 = set;
                    FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, coneClassLikeLookupTag);
                    if (regularClassSymbol != null && (firTypeScopeUnsubstitutedScope = ScopeUtilsKt.unsubstitutedScope(this, regularClassSymbol)) != null) {
                        Set set3 = set2;
                        Iterator it = set3.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                            FirCallableSymbol firCallableSymbol4 = (FirCallableSymbol) next;
                            if ((set3 instanceof Collection) && set3.isEmpty()) {
                                break;
                            }
                            Iterator it2 = set3.iterator();
                            do {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                final FirCallableSymbol firCallableSymbol5 = (FirCallableSymbol) it2.next();
                                if (Intrinsics.areEqual(firCallableSymbol4, firCallableSymbol5)) {
                                    z = true;
                                } else {
                                    final Function1<FirNamedFunctionSymbol, Boolean> function1 = new Function1<FirNamedFunctionSymbol, Boolean>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeys$$inlined$computeFakeOverrideKeysImpl$1
                                        public final Boolean invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                                            firNamedFunctionSymbol2.getClass();
                                            return Boolean.valueOf(Intrinsics.areEqual(firNamedFunctionSymbol2, firCallableSymbol5));
                                        }
                                    };
                                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                                    FirTypeScopeKt.processOverriddenFunctions(firTypeScopeUnsubstitutedScope, (FirNamedFunctionSymbol) firCallableSymbol4, new Function1<FirNamedFunctionSymbol, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeys$$inlined$computeFakeOverrideKeysImpl$2
                                        public final ProcessorAction invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                                            firNamedFunctionSymbol2.getClass();
                                            if (!((Boolean) function1.invoke(firNamedFunctionSymbol2)).booleanValue()) {
                                                return ProcessorAction.NEXT;
                                            }
                                            booleanRef.element = true;
                                            return ProcessorAction.STOP;
                                        }
                                    });
                                    z = booleanRef.element;
                                }
                            } while (z);
                        }
                        firCallableSymbol = (FirCallableSymbol) next;
                        if (firCallableSymbol == null) {
                            firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set3);
                        }
                    } else {
                        firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set2);
                    }
                } else {
                    firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(set);
                }
                arrayList2.add(TuplesKt.to(firCallableSymbol, coneClassLikeLookupTag));
            } else {
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return null;
            }
        }
        return arrayList2;
    }
}
