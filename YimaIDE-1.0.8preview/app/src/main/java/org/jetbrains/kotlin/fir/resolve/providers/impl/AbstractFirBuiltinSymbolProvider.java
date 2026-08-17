package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.caches.FirLazyValue;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.deserialization.ClassDeserializationKt;
import org.jetbrains.kotlin.fir.deserialization.FirBuiltinAnnotationDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirConstDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext;
import org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirMemberDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirNestedTypeAliasDeserializationContext;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.resolve.providers.impl.AbstractFirBuiltinSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.NameResolverImpl;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.ClassData;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.ProtoBasedClassDataFinder;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u000256B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u0012J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0012H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$H\u0016J.\u0010)\u001a\u00020*2\u0010\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030-0,2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0018H\u0017b\u0002\b/J*\u00100\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002010,2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0018H\u0017b\u0002\b/J&\u00102\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002010,2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0018H\u0002J*\u00103\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002040,2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0018H\u0017b\u0002\b/R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R'\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001b0\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001c\u0010\u0015R\u0014\u0010%\u001a\u00020&X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "isFallback", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Z)V", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getKotlinScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "builtInsPackageFragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;", "getBuiltInsPackageFragments", "()Ljava/util/Map;", "getTopLevelClassifierNamesInPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "packageFqName", "allPackageFragments", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragmentWrapper;", "getAllPackageFragments", "allPackageFragments$delegate", "Lkotlin/Lazy;", "hasPackage", "fqName", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelFunctionSymbolsToByPackageFragments", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "BuiltInsPackageFragment", "BuiltInsPackageFragmentWrapper", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirBuiltinSymbolProvider extends FirSymbolProvider {

    /* JADX INFO: renamed from: allPackageFragments$delegate, reason: from kotlin metadata */
    private final Lazy allPackageFragments;
    private final boolean isFallback;
    private final FirKotlinScopeProvider kotlinScopeProvider;
    private final FirModuleData moduleData;
    private final FirSymbolNamesProvider symbolNamesProvider;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;", Argument.Delimiters.none, "stream", "Ljava/io/InputStream;", "<init>", "(Ljava/io/InputStream;)V", "binaryVersionAndPackageFragment", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/BinaryVersionAndPackageFragment;", "version", "Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "getVersion", "()Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "packageProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "getPackageProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "getNameResolver", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "classDataFinder", "Lorg/jetbrains/kotlin/serialization/deserialization/ProtoBasedClassDataFinder;", "getClassDataFinder", "()Lorg/jetbrains/kotlin/serialization/deserialization/ProtoBasedClassDataFinder;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BuiltInsPackageFragment {
        private final BinaryVersionAndPackageFragment binaryVersionAndPackageFragment;
        private final ProtoBasedClassDataFinder classDataFinder;
        private final NameResolver nameResolver;

        public BuiltInsPackageFragment(InputStream inputStream) {
            inputStream.getClass();
            this.binaryVersionAndPackageFragment = BinaryVersionAndPackageFragment.Companion.createFromStream(inputStream);
            ProtoBuf.StringTable strings = getPackageProto().getStrings();
            strings.getClass();
            ProtoBuf.QualifiedNameTable qualifiedNames = getPackageProto().getQualifiedNames();
            qualifiedNames.getClass();
            NameResolverImpl nameResolverImpl = new NameResolverImpl(strings, qualifiedNames);
            this.nameResolver = nameResolverImpl;
            this.classDataFinder = new ProtoBasedClassDataFinder(getPackageProto(), nameResolverImpl, getVersion(), new Function1() { // from class: lm
                public final Object invoke(Object obj) {
                    return AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment.a((ClassId) obj);
                }
            });
        }

        public static SourceElement a(ClassId classId) {
            classId.getClass();
            SourceElement sourceElement = SourceElement.NO_SOURCE;
            sourceElement.getClass();
            return sourceElement;
        }

        public final ProtoBasedClassDataFinder getClassDataFinder() {
            return this.classDataFinder;
        }

        public final NameResolver getNameResolver() {
            return this.nameResolver;
        }

        public final ProtoBuf.PackageFragment getPackageProto() {
            return this.binaryVersionAndPackageFragment.getPackageFragment();
        }

        public final BuiltInsBinaryVersion getVersion() {
            return this.binaryVersionAndPackageFragment.getVersion();
        }
    }

    @Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0010\u00102\u001a\u0004\u0018\u00010)2\u0006\u00103\u001a\u00020(J\u001e\u00104\u001a\u0004\u0018\u00010)2\u0006\u00103\u001a\u00020(2\n\b\u0002\u00105\u001a\u0004\u0018\u00010*H\u0002J\u0018\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003070-2\u0006\u00108\u001a\u00020,J\f\u00109\u001a\b\u0012\u0004\u0012\u00020,0:J\u0014\u0010;\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u00108\u001a\u00020,R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\"\u0010&\u001a\u0016\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)\u0012\u0006\u0012\u0004\u0018\u00010*0'X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010+\u001a\u001c\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0-\u0012\u0006\u0012\u0004\u0018\u00010/0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0-01X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragmentWrapper;", Argument.Delimiters.none, "builtInsPackageFragment", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "originateFromFallbackBuiltIns", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Z)V", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getKotlinScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "packageProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "getPackageProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "getNameResolver", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "classDataFinder", "Lorg/jetbrains/kotlin/serialization/deserialization/ProtoBasedClassDataFinder;", "getClassDataFinder", "()Lorg/jetbrains/kotlin/serialization/deserialization/ProtoBasedClassDataFinder;", "memberDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;", "getMemberDeserializer", "()Lorg/jetbrains/kotlin/fir/deserialization/FirMemberDeserializer;", "memberDeserializer$delegate", "Lkotlin/Lazy;", "classCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "functionCache", "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", Argument.Delimiters.none, "functionsNameCache", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "getClassLikeSymbolByClassId", "classId", "findAndDeserializeClass", "parentContext", "getTopLevelCallableSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", ModuleXmlParser.NAME, "getTopLevelCallableNames", Argument.Delimiters.none, "getTopLevelFunctionSymbols", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BuiltInsPackageFragmentWrapper {
        private final BuiltInsPackageFragment builtInsPackageFragment;
        private final FirCache<ClassId, FirRegularClassSymbol, FirDeserializationContext> classCache;
        private final FqName fqName;
        private final FirCache functionCache;
        private final FirLazyValue<List<Name>> functionsNameCache;
        private final FirKotlinScopeProvider kotlinScopeProvider;

        /* JADX INFO: renamed from: memberDeserializer$delegate, reason: from kotlin metadata */
        private final Lazy memberDeserializer;
        private final FirModuleData moduleData;
        private final boolean originateFromFallbackBuiltIns;

        public BuiltInsPackageFragmentWrapper(BuiltInsPackageFragment builtInsPackageFragment, FqName fqName, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, boolean z) {
            builtInsPackageFragment.getClass();
            fqName.getClass();
            firModuleData.getClass();
            firKotlinScopeProvider.getClass();
            this.builtInsPackageFragment = builtInsPackageFragment;
            this.fqName = fqName;
            this.moduleData = firModuleData;
            this.kotlinScopeProvider = firKotlinScopeProvider;
            this.originateFromFallbackBuiltIns = z;
            this.memberDeserializer = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.a
                public final Object invoke() {
                    return AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragmentWrapper.e(this.b);
                }
            });
            this.classCache = FirCachesFactoryKt.getFirCachesFactory(firModuleData.getSession()).createCacheWithPostCompute(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.b
                public final Object invoke(Object obj, Object obj2) {
                    return AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragmentWrapper.a((ClassId) obj, (FirDeserializationContext) obj2);
                }
            }, new Function3() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.c
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragmentWrapper.b(this.b, (ClassId) obj, (FirRegularClassSymbol) obj2, (FirDeserializationContext) obj3);
                }
            });
            this.functionCache = FirCachesFactoryKt.getFirCachesFactory(firModuleData.getSession()).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragmentWrapper$special$$inlined$createCache$1
                public final List<? extends FirNamedFunctionSymbol> invoke(Name name, Void r10) {
                    name.getClass();
                    Name name2 = name;
                    List functionList = this.this$0.getPackageProto().getPackage().getFunctionList();
                    functionList.getClass();
                    ArrayList<ProtoBuf.Function> arrayList = new ArrayList();
                    for (Object obj : functionList) {
                        if (Intrinsics.areEqual(NameResolverUtilKt.getName(this.this$0.getNameResolver(), ((ProtoBuf.Function) obj).getName()), name2)) {
                            arrayList.add(obj);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                    for (ProtoBuf.Function function : arrayList) {
                        FirMemberDeserializer memberDeserializer = this.this$0.getMemberDeserializer();
                        function.getClass();
                        arrayList2.add(FirMemberDeserializer.loadFunction$default(memberDeserializer, function, null, null, null, 14, null).getSymbol());
                    }
                    return arrayList2;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((Name) obj, (Void) obj2);
                }
            });
            this.functionsNameCache = FirCachesFactoryKt.getFirCachesFactory(firModuleData.getSession()).createLazyValue(new Function0() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.d
                public final Object invoke() {
                    return AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragmentWrapper.d(this.b);
                }
            });
        }

        public static Pair a(ClassId classId, FirDeserializationContext firDeserializationContext) {
            classId.getClass();
            return TuplesKt.to(new FirRegularClassSymbol(classId), firDeserializationContext);
        }

        public static Unit b(final BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper, ClassId classId, FirRegularClassSymbol firRegularClassSymbol, FirDeserializationContext firDeserializationContext) {
            classId.getClass();
            firRegularClassSymbol.getClass();
            ClassData classDataFindClassData = builtInsPackageFragmentWrapper.getClassDataFinder().findClassData(classId);
            classDataFindClassData.getClass();
            ClassDeserializationKt.deserializeClassToSymbol(classId, classDataFindClassData.getClassProto(), firRegularClassSymbol, builtInsPackageFragmentWrapper.getNameResolver(), builtInsPackageFragmentWrapper.moduleData.getSession(), builtInsPackageFragmentWrapper.moduleData, null, FirKDocDeserializer.Empty.INSTANCE, FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE, builtInsPackageFragmentWrapper.kotlinScopeProvider, BuiltInSerializerProtocol.INSTANCE, firDeserializationContext, null, builtInsPackageFragmentWrapper.originateFromFallbackBuiltIns ? FirDeclarationOrigin.BuiltInsFallback.INSTANCE : FirDeclarationOrigin.BuiltIns.INSTANCE, new AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragmentWrapper$classCache$2$1(builtInsPackageFragmentWrapper), new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.e
                public final Object invoke(Object obj, Object obj2) {
                    return AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragmentWrapper.classCache$lambda$1$0(this.b, (ClassId) obj, (FirNestedTypeAliasDeserializationContext) obj2);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FirTypeAliasSymbol classCache$lambda$1$0(BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper, ClassId classId, FirNestedTypeAliasDeserializationContext firNestedTypeAliasDeserializationContext) {
            classId.getClass();
            firNestedTypeAliasDeserializationContext.getClass();
            return FirMemberDeserializer.loadTypeAlias$default(builtInsPackageFragmentWrapper.getMemberDeserializer(), firNestedTypeAliasDeserializationContext.getProto(), classId, firNestedTypeAliasDeserializationContext.getScopeProvider(), null, 8, null).getSymbol();
        }

        public static List d(BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper) {
            List functionList = builtInsPackageFragmentWrapper.getPackageProto().getPackage().getFunctionList();
            functionList.getClass();
            List list = functionList;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(NameResolverUtilKt.getName(builtInsPackageFragmentWrapper.getNameResolver(), ((ProtoBuf.Function) it.next()).getName()));
            }
            return arrayList;
        }

        public static FirMemberDeserializer e(BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper) {
            FirDeserializationContext.Companion companion = FirDeserializationContext.INSTANCE;
            FqName fqName = builtInsPackageFragmentWrapper.fqName;
            ProtoBuf.Package r2 = builtInsPackageFragmentWrapper.getPackageProto().getPackage();
            r2.getClass();
            NameResolver nameResolver = builtInsPackageFragmentWrapper.getNameResolver();
            FirModuleData firModuleData = builtInsPackageFragmentWrapper.moduleData;
            return companion.createForPackage(fqName, r2, nameResolver, firModuleData, new FirBuiltinAnnotationDeserializer(firModuleData.getSession()), FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE, new FirConstDeserializer(BuiltInSerializerProtocol.INSTANCE), FirKDocDeserializer.Empty.INSTANCE, null).getMemberDeserializer();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FirRegularClassSymbol findAndDeserializeClass(ClassId classId, FirDeserializationContext parentContext) {
            if (getClassDataFinder().getAllClassIds().contains(classId)) {
                return this.classCache.getValue(classId, parentContext);
            }
            return null;
        }

        public static /* synthetic */ FirRegularClassSymbol findAndDeserializeClass$default(BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper, ClassId classId, FirDeserializationContext firDeserializationContext, int i, Object obj) {
            if ((i & 2) != 0) {
                firDeserializationContext = null;
            }
            return builtInsPackageFragmentWrapper.findAndDeserializeClass(classId, firDeserializationContext);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FirMemberDeserializer getMemberDeserializer() {
            return (FirMemberDeserializer) this.memberDeserializer.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final NameResolver getNameResolver() {
            return this.builtInsPackageFragment.getNameResolver();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ProtoBuf.PackageFragment getPackageProto() {
            return this.builtInsPackageFragment.getPackageProto();
        }

        public final ProtoBasedClassDataFinder getClassDataFinder() {
            return this.builtInsPackageFragment.getClassDataFinder();
        }

        public final FirRegularClassSymbol getClassLikeSymbolByClassId(ClassId classId) {
            classId.getClass();
            return findAndDeserializeClass$default(this, classId, null, 2, null);
        }

        public final FqName getFqName() {
            return this.fqName;
        }

        public final FirKotlinScopeProvider getKotlinScopeProvider() {
            return this.kotlinScopeProvider;
        }

        public final FirModuleData getModuleData() {
            return this.moduleData;
        }

        public final Collection<Name> getTopLevelCallableNames() {
            return this.functionsNameCache.getValue();
        }

        public final List<FirCallableSymbol<?>> getTopLevelCallableSymbols(Name name) {
            name.getClass();
            return getTopLevelFunctionSymbols(name);
        }

        public final List<FirNamedFunctionSymbol> getTopLevelFunctionSymbols(Name name) {
            name.getClass();
            return (List) this.functionCache.getValue(name, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractFirBuiltinSymbolProvider(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, boolean z) {
        super(firSession);
        firSession.getClass();
        firModuleData.getClass();
        firKotlinScopeProvider.getClass();
        this.moduleData = firModuleData;
        this.kotlinScopeProvider = firKotlinScopeProvider;
        this.isFallback = z;
        this.allPackageFragments = LazyKt.lazy(new Function0() { // from class: km
            public final Object invoke() {
                return AbstractFirBuiltinSymbolProvider.a(this.b);
            }
        });
        this.symbolNamesProvider = new FirSymbolNamesProvider() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.AbstractFirBuiltinSymbolProvider$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificCallablePackageNamesComputation() {
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getMayHaveSyntheticFunctionTypes() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNames() {
                Set setKeySet = this.this$0.getAllPackageFragments().keySet();
                if (setKeySet.isEmpty()) {
                    return SetsKt.emptySet();
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Iterator it = setKeySet.iterator();
                while (it.hasNext()) {
                    linkedHashSet.add(((FqName) it.next()).asString());
                }
                return linkedHashSet;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName) {
                Collection<Name> topLevelCallableNames;
                packageFqName.getClass();
                AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper = (AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragmentWrapper) this.this$0.getAllPackageFragments().get(packageFqName);
                Set<Name> set = (builtInsPackageFragmentWrapper == null || (topLevelCallableNames = builtInsPackageFragmentWrapper.getTopLevelCallableNames()) == null) ? null : CollectionsKt.toSet(topLevelCallableNames);
                return set == null ? SetsKt.emptySet() : set;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                return FirBuiltinSymbolProvidersKt.getTopLevelClassifierNamesInPackage(this.this$0.getBuiltInsPackageFragments(), packageFqName);
            }
        };
    }

    public static Map a(AbstractFirBuiltinSymbolProvider abstractFirBuiltinSymbolProvider) {
        Map<FqName, BuiltInsPackageFragment> builtInsPackageFragments = abstractFirBuiltinSymbolProvider.getBuiltInsPackageFragments();
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(builtInsPackageFragments.size()));
        Iterator<T> it = builtInsPackageFragments.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), new BuiltInsPackageFragmentWrapper((BuiltInsPackageFragment) entry.getValue(), (FqName) entry.getKey(), abstractFirBuiltinSymbolProvider.moduleData, abstractFirBuiltinSymbolProvider.kotlinScopeProvider, abstractFirBuiltinSymbolProvider.isFallback));
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<FqName, BuiltInsPackageFragmentWrapper> getAllPackageFragments() {
        return (Map) this.allPackageFragments.getValue();
    }

    private final void getTopLevelFunctionSymbolsToByPackageFragments(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        List<FirNamedFunctionSymbol> topLevelFunctionSymbols;
        BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper = getAllPackageFragments().get(packageFqName);
        if (builtInsPackageFragmentWrapper == null || (topLevelFunctionSymbols = builtInsPackageFragmentWrapper.getTopLevelFunctionSymbols(name)) == null) {
            return;
        }
        CollectionsKt.addAll(destination, topLevelFunctionSymbols);
    }

    public abstract Map<FqName, BuiltInsPackageFragment> getBuiltInsPackageFragments();

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirRegularClassSymbol getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper = getAllPackageFragments().get(classId.getPackageFqName());
        if (builtInsPackageFragmentWrapper != null) {
            return builtInsPackageFragmentWrapper.getClassLikeSymbolByClassId(classId);
        }
        return null;
    }

    public final FirKotlinScopeProvider getKotlinScopeProvider() {
        return this.kotlinScopeProvider;
    }

    public final FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        List<FirCallableSymbol<?>> topLevelCallableSymbols;
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        BuiltInsPackageFragmentWrapper builtInsPackageFragmentWrapper = getAllPackageFragments().get(packageFqName);
        if (builtInsPackageFragmentWrapper == null || (topLevelCallableSymbols = builtInsPackageFragmentWrapper.getTopLevelCallableSymbols(name)) == null) {
            return;
        }
        CollectionsKt.addAll(destination, topLevelCallableSymbols);
    }

    public final Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        return FirBuiltinSymbolProvidersKt.getTopLevelClassifierNamesInPackage(getBuiltInsPackageFragments(), packageFqName);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        getTopLevelFunctionSymbolsToByPackageFragments(destination, packageFqName, name);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return getAllPackageFragments().containsKey(fqName);
    }
}
