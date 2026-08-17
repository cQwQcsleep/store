package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirNameConflictsTracker;
import org.jetbrains.kotlin.fir.FirNameConflictsTrackerKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirProviderImpl;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNamesUtilKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0004/012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0011J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#H\u0002J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00110%2\u0006\u0010\u001c\u001a\u00020&H\u0016J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u001dH\u0016J\u0014\u0010*\u001a\u00020 2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110%J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u0010\u001c\u001a\u00020&H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getKotlinScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getFirCallableContainerFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getFirScriptContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getFirScriptByFilePath", ModuleXmlParser.PATH, Argument.Delimiters.none, "getFirReplSnippetContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getFirClassifierContainerFile", "fqName", "Lorg/jetbrains/kotlin/name/ClassId;", "getFirClassifierContainerFileIfAny", "recordFile", Argument.Delimiters.none, "file", "state", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$State;", "getFirFilesByPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "getFirClassifierByFqName", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "classId", "ensureConsistent", "files", "getClassNamesInPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "SymbolProvider", "FirRecorderData", "FirRecorder", "State", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirProviderImpl extends FirProvider {
    private final FirKotlinScopeProvider kotlinScopeProvider;
    private final FirSession session;
    private final State state;
    private final FirSymbolProvider symbolProvider;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$FirRecorderData;", Argument.Delimiters.none, "state", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$State;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "nameConflictsTracker", "Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$State;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker;)V", "getState", "()Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$State;", "getFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getNameConflictsTracker", "()Lorg/jetbrains/kotlin/fir/FirNameConflictsTracker;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirRecorderData {
        private final FirFile file;
        private final FirNameConflictsTracker nameConflictsTracker;
        private final State state;

        public FirRecorderData(State state, FirFile firFile, FirNameConflictsTracker firNameConflictsTracker) {
            state.getClass();
            firFile.getClass();
            this.state = state;
            this.file = firFile;
            this.nameConflictsTracker = firNameConflictsTracker;
        }

        public final FirFile getFile() {
            return this.file;
        }

        public final FirNameConflictsTracker getNameConflictsTracker() {
            return this.nameConflictsTracker;
        }

        public final State getState() {
            return this.state;
        }
    }

    @Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u0000R#\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR-\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R-\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b0\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R9\u0010\u0018\u001a*\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\f0\u0010j\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\f`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R9\u0010\u001b\u001a*\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\f0\u0010j\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\f`\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R#\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\nR#\u0010!\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\nR9\u0010$\u001a*\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00070\u0010j\u0014\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u0007`\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0015R5\u0010'\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0004\u0012\u00020\b0\u0010j\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030(\u0012\u0004\u0012\u00020\b`\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0015R-\u0010*\u001a\u001e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\b0\u0010j\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\b`\u0013¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0015R-\u0010-\u001a\u001e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020+0\u0010j\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020+`\u0013¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0015R-\u00100\u001a\u001e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\b0\u0010j\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\b`\u0013¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0015¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$State;", Argument.Delimiters.none, "<init>", "()V", "fileMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFileMap", "()Ljava/util/Map;", "allSubPackages", Argument.Delimiters.none, "getAllSubPackages", "()Ljava/util/Set;", "classifierMap", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lkotlin/collections/HashMap;", "getClassifierMap", "()Ljava/util/HashMap;", "classifierContainerFileMap", "getClassifierContainerFileMap", "classifierInPackage", "Lorg/jetbrains/kotlin/name/Name;", "getClassifierInPackage", "classesInPackage", "getClassesInPackage", "functionMap", "Lorg/jetbrains/kotlin/name/CallableId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getFunctionMap", "propertyMap", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getPropertyMap", "constructorMap", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getConstructorMap", "callableContainerMap", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getCallableContainerMap", "scriptContainerMap", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getScriptContainerMap", "scriptByFilePathMap", Argument.Delimiters.none, "getScriptByFilePathMap", "snippetContainerMap", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getSnippetContainerMap", "setFrom", Argument.Delimiters.none, "other", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class State {
        private final Map<FqName, List<FirFile>> fileMap = new HashMap();
        private final Set<FqName> allSubPackages = new LinkedHashSet();
        private final HashMap<ClassId, FirClassLikeDeclaration> classifierMap = new HashMap<>();
        private final HashMap<ClassId, FirFile> classifierContainerFileMap = new HashMap<>();
        private final HashMap<FqName, Set<Name>> classifierInPackage = new HashMap<>();
        private final HashMap<FqName, Set<Name>> classesInPackage = new HashMap<>();
        private final Map<CallableId, List<FirNamedFunctionSymbol>> functionMap = new LinkedHashMap();
        private final Map<CallableId, List<FirPropertySymbol>> propertyMap = new LinkedHashMap();
        private final HashMap<CallableId, List<FirConstructorSymbol>> constructorMap = new HashMap<>();
        private final HashMap<FirCallableSymbol<?>, FirFile> callableContainerMap = new HashMap<>();
        private final HashMap<FirScriptSymbol, FirFile> scriptContainerMap = new HashMap<>();
        private final HashMap<String, FirScriptSymbol> scriptByFilePathMap = new HashMap<>();
        private final HashMap<FirReplSnippetSymbol, FirFile> snippetContainerMap = new HashMap<>();

        public final Set<FqName> getAllSubPackages() {
            return this.allSubPackages;
        }

        public final HashMap<FirCallableSymbol<?>, FirFile> getCallableContainerMap() {
            return this.callableContainerMap;
        }

        public final HashMap<FqName, Set<Name>> getClassesInPackage() {
            return this.classesInPackage;
        }

        public final HashMap<ClassId, FirFile> getClassifierContainerFileMap() {
            return this.classifierContainerFileMap;
        }

        public final HashMap<FqName, Set<Name>> getClassifierInPackage() {
            return this.classifierInPackage;
        }

        public final HashMap<ClassId, FirClassLikeDeclaration> getClassifierMap() {
            return this.classifierMap;
        }

        public final HashMap<CallableId, List<FirConstructorSymbol>> getConstructorMap() {
            return this.constructorMap;
        }

        public final Map<FqName, List<FirFile>> getFileMap() {
            return this.fileMap;
        }

        public final Map<CallableId, List<FirNamedFunctionSymbol>> getFunctionMap() {
            return this.functionMap;
        }

        public final Map<CallableId, List<FirPropertySymbol>> getPropertyMap() {
            return this.propertyMap;
        }

        public final HashMap<String, FirScriptSymbol> getScriptByFilePathMap() {
            return this.scriptByFilePathMap;
        }

        public final HashMap<FirScriptSymbol, FirFile> getScriptContainerMap() {
            return this.scriptContainerMap;
        }

        public final HashMap<FirReplSnippetSymbol, FirFile> getSnippetContainerMap() {
            return this.snippetContainerMap;
        }

        public final void setFrom(State other) {
            other.getClass();
            this.fileMap.clear();
            this.allSubPackages.clear();
            this.classifierMap.clear();
            this.classifierContainerFileMap.clear();
            this.functionMap.clear();
            this.propertyMap.clear();
            this.constructorMap.clear();
            this.callableContainerMap.clear();
            this.scriptContainerMap.clear();
            this.scriptByFilePathMap.clear();
            this.snippetContainerMap.clear();
            this.fileMap.putAll(other.fileMap);
            this.allSubPackages.addAll(other.allSubPackages);
            this.classifierMap.putAll(other.classifierMap);
            this.classifierContainerFileMap.putAll(other.classifierContainerFileMap);
            this.functionMap.putAll(other.functionMap);
            this.propertyMap.putAll(other.propertyMap);
            this.constructorMap.putAll(other.constructorMap);
            this.callableContainerMap.putAll(other.callableContainerMap);
            this.scriptContainerMap.putAll(other.scriptContainerMap);
            this.scriptByFilePathMap.putAll(other.scriptByFilePathMap);
            this.snippetContainerMap.putAll(other.snippetContainerMap);
            this.classesInPackage.putAll(other.classesInPackage);
            this.classifierInPackage.putAll(other.classifierInPackage);
        }
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J.\u0010\b\u001a\u00020\t2\u0010\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0017b\u0002\b\u0011J*\u0010\u0012\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0017b\u0002\b\u0011J*\u0010\u0014\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0017b\u0002\b\u0011J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000eH\u0016R\u0014\u0010\u0019\u001a\u00020\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$SymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl;)V", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "hasPackage", Argument.Delimiters.none, "fqName", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class SymbolProvider extends FirSymbolProvider {
        private final FirSymbolNamesProvider symbolNamesProvider;

        public SymbolProvider() {
            super(FirProviderImpl.this.getSession());
            this.symbolNamesProvider = new FirSymbolNamesProvider() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirProviderImpl$SymbolProvider$symbolNamesProvider$1
                @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
                public boolean getHasSpecificCallablePackageNamesComputation() {
                    return false;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
                public boolean getHasSpecificClassifierPackageNamesComputation() {
                    return false;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
                public Set<String> getPackageNames() {
                    Set<FqName> allSubPackages = firProviderImpl.state.getAllSubPackages();
                    if (allSubPackages.isEmpty()) {
                        return SetsKt.emptySet();
                    }
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    Iterator<T> it = allSubPackages.iterator();
                    while (it.hasNext()) {
                        linkedHashSet.add(((FqName) it.next()).asString());
                    }
                    return linkedHashSet;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
                public Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName) {
                    packageFqName.getClass();
                    FirProviderImpl firProviderImpl = firProviderImpl;
                    Set setCreateSetBuilder = SetsKt.createSetBuilder();
                    for (CallableId callableId : firProviderImpl.state.getFunctionMap().keySet()) {
                        if (Intrinsics.areEqual(callableId.getPackageName(), packageFqName)) {
                            setCreateSetBuilder.add(callableId.getCallableName());
                        }
                    }
                    for (CallableId callableId2 : firProviderImpl.state.getPropertyMap().keySet()) {
                        if (Intrinsics.areEqual(callableId2.getPackageName(), packageFqName)) {
                            setCreateSetBuilder.add(callableId2.getCallableName());
                        }
                    }
                    return SetsKt.build(setCreateSetBuilder);
                }

                @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
                public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                    packageFqName.getClass();
                    Set<Name> set = firProviderImpl.state.getClassifierInPackage().get(packageFqName);
                    return set == null ? SetsKt.emptySet() : set;
                }
            };
        }

        @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
        public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
            classId.getClass();
            FirClassLikeDeclaration firClassifierByFqName = FirProviderImpl.this.getFirClassifierByFqName(classId);
            if (firClassifierByFqName != null) {
                return firClassifierByFqName.getSymbol();
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
        public FirSymbolNamesProvider getSymbolNamesProvider() {
            return this.symbolNamesProvider;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
        @FirSymbolProviderInternals
        public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
            destination.getClass();
            packageFqName.getClass();
            name.getClass();
            List<FirCallableSymbol<?>> list = destination;
            List<FirNamedFunctionSymbol> listEmptyList = FirProviderImpl.this.state.getFunctionMap().get(new CallableId(packageFqName, name));
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(list, listEmptyList);
            List<FirPropertySymbol> listEmptyList2 = FirProviderImpl.this.state.getPropertyMap().get(new CallableId(packageFqName, name));
            if (listEmptyList2 == null) {
                listEmptyList2 = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(list, listEmptyList2);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
        @FirSymbolProviderInternals
        public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
            destination.getClass();
            packageFqName.getClass();
            name.getClass();
            List<FirNamedFunctionSymbol> list = destination;
            List<FirNamedFunctionSymbol> listEmptyList = FirProviderImpl.this.state.getFunctionMap().get(new CallableId(packageFqName, name));
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(list, listEmptyList);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
        @FirSymbolProviderInternals
        public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
            destination.getClass();
            packageFqName.getClass();
            name.getClass();
            List<FirPropertySymbol> list = destination;
            List<FirPropertySymbol> listEmptyList = FirProviderImpl.this.state.getPropertyMap().get(new CallableId(packageFqName, name));
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(list, listEmptyList);
        }

        @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
        public boolean hasPackage(FqName fqName) {
            fqName.getClass();
            return FirProviderImpl.this.state.getAllSubPackages().contains(fqName);
        }
    }

    public FirProviderImpl(FirSession firSession, FirKotlinScopeProvider firKotlinScopeProvider) {
        firSession.getClass();
        firKotlinScopeProvider.getClass();
        this.session = firSession;
        this.kotlinScopeProvider = firKotlinScopeProvider;
        this.symbolProvider = new SymbolProvider();
        this.state = new State();
    }

    public static List a(List list, List list2) {
        list.getClass();
        list2.getClass();
        return CollectionsKt.plus(list, list2);
    }

    public static List b(Function2 function2, Object obj, Object obj2) {
        return (List) function2.invoke(obj, obj2);
    }

    public static boolean c(Object obj, Object obj2) {
        return obj == obj2;
    }

    public static FqName d(FqName fqName) {
        fqName.getClass();
        return FqNamesUtilKt.parentOrNull(fqName);
    }

    private static final <K, V> void ensureConsistent$checkMMapDiff(List<String> list, String str, Map<K, ? extends List<? extends V>> map, Map<K, ? extends List<? extends V>> map2) {
        Set setPlus = SetsKt.plus(map.keySet(), map2.keySet());
        ArrayList<Triple> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setPlus, 10));
        for (Object obj : setPlus) {
            arrayList.add(new Triple(obj, map.get(obj), map2.get(obj)));
        }
        boolean z = false;
        for (Triple triple : arrayList) {
            Object objComponent1 = triple.component1();
            List list2 = (List) triple.component2();
            List list3 = (List) triple.component3();
            if (list2 == null || list3 == null) {
                if (!z) {
                    list.add(str);
                    z = true;
                }
                list.add("diff at key = '" + objComponent1 + "': was: " + list2 + ", become: " + list3);
            } else {
                Set set = CollectionsKt.toSet(list2);
                Set set2 = CollectionsKt.toSet(list3);
                Set setMinus = SetsKt.minus(set, set2);
                Set setMinus2 = SetsKt.minus(set2, set);
                if (!setMinus.isEmpty() || !setMinus2.isEmpty()) {
                    List<String> list4 = list;
                    list4.add("diff at key = '" + objComponent1 + "':");
                    list4.add("    Lost:");
                    Iterator it = setMinus.iterator();
                    while (it.hasNext()) {
                        list4.add("     " + it.next());
                    }
                    list4.add("    New:");
                    Iterator it2 = setMinus2.iterator();
                    while (it2.hasNext()) {
                        list4.add("     " + it2.next());
                    }
                }
            }
        }
    }

    private static final <K, V> void ensureConsistent$checkMapDiff(List<String> list, String str, Map<K, ? extends V> map, Map<K, ? extends V> map2, Function2<? super V, ? super V, Boolean> function2) {
        Set setPlus = SetsKt.plus(map.keySet(), map2.keySet());
        ArrayList<Triple> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setPlus, 10));
        for (Object obj : setPlus) {
            arrayList.add(new Triple(obj, map.get(obj), map2.get(obj)));
        }
        boolean z = false;
        for (Triple triple : arrayList) {
            Object objComponent1 = triple.component1();
            Object objComponent2 = triple.component2();
            Object objComponent3 = triple.component3();
            if (!((Boolean) function2.invoke(objComponent2, objComponent3)).booleanValue()) {
                if (!z) {
                    list.add(str);
                    z = true;
                }
                list.add("diff at key = '" + objComponent1 + "': was: '" + objComponent2 + "', become: '" + objComponent3 + '\'');
            }
        }
    }

    public static /* synthetic */ void ensureConsistent$checkMapDiff$default(List list, String str, Map map, Map map2, Function2 function2, int i, Object obj) {
        if ((i & 16) != 0) {
            function2 = new Function2() { // from class: bc5
                public final Object invoke(Object obj2, Object obj3) {
                    return Boolean.valueOf(FirProviderImpl.c(obj2, obj3));
                }
            };
        }
        ensureConsistent$checkMapDiff(list, str, map, map2, function2);
    }

    private final void recordFile(FirFile file, State state) {
        FqName packageFqName = UtilsKt.getPackageFqName(file);
        Map<FqName, List<FirFile>> fileMap = state.getFileMap();
        List<FirFile> listListOf = CollectionsKt.listOf(file);
        final Function2 function2 = new Function2() { // from class: cc5
            public final Object invoke(Object obj, Object obj2) {
                return FirProviderImpl.a((List) obj, (List) obj2);
            }
        };
        fileMap.merge(packageFqName, listListOf, new BiFunction() { // from class: dc5
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return FirProviderImpl.b(function2, obj, obj2);
            }
        });
        Sequence sequenceGenerateSequence = SequencesKt.generateSequence(packageFqName, new Function1() { // from class: ec5
            public final Object invoke(Object obj) {
                return FirProviderImpl.d((FqName) obj);
            }
        });
        Set<FqName> allSubPackages = state.getAllSubPackages();
        Iterator it = sequenceGenerateSequence.iterator();
        while (it.hasNext()) {
            allSubPackages.add((FqName) it.next());
        }
        file.acceptChildren(FirRecorder.INSTANCE, new FirRecorderData(state, file, FirNameConflictsTrackerKt.getNameConflictsTracker(this.session)));
    }

    public final void ensureConsistent(List<? extends FirFile> files) {
        files.getClass();
        State state = new State();
        Iterator<T> it = files.iterator();
        while (it.hasNext()) {
            recordFile((FirFile) it.next(), state);
        }
        ArrayList arrayList = new ArrayList();
        ensureConsistent$checkMMapDiff(arrayList, "fileMap", this.state.getFileMap(), state.getFileMap());
        ensureConsistent$checkMapDiff$default(arrayList, "classifierMap", this.state.getClassifierMap(), state.getClassifierMap(), null, 16, null);
        ensureConsistent$checkMapDiff$default(arrayList, "classifierContainerFileMap", this.state.getClassifierContainerFileMap(), state.getClassifierContainerFileMap(), null, 16, null);
        ensureConsistent$checkMMapDiff(arrayList, "callableMap", this.state.getFunctionMap(), state.getFunctionMap());
        ensureConsistent$checkMMapDiff(arrayList, "callableMap", this.state.getPropertyMap(), state.getPropertyMap());
        ensureConsistent$checkMMapDiff(arrayList, "callableMap", this.state.getConstructorMap(), state.getConstructorMap());
        ensureConsistent$checkMapDiff$default(arrayList, "callableContainerMap", this.state.getCallableContainerMap(), state.getCallableContainerMap(), null, 16, null);
        this.state.setFrom(state);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public Set<Name> getClassNamesInPackage(FqName fqName) {
        fqName.getClass();
        Set<Name> set = this.state.getClassesInPackage().get(fqName);
        return set == null ? SetsKt.emptySet() : set;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirCallableContainerFile(FirCallableSymbol<?> symbol) {
        symbol.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) symbol.getFir();
        FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
        if (originalForSubstitutionOverrideAttr == null) {
            originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol2 = originalForSubstitutionOverrideAttr != null ? originalForSubstitutionOverrideAttr.getSymbol() : null;
        if (symbol2 != null) {
            return FirProviderKt.getFirProvider(symbol2.getModuleData().getSession()).getFirCallableContainerFile(symbol2);
        }
        if (symbol instanceof FirBackingFieldSymbol) {
            return getFirCallableContainerFile(((FirBackingField) ((FirBackingFieldSymbol) symbol).getFir()).getPropertySymbol());
        }
        if (symbol instanceof FirSyntheticPropertySymbol) {
            FirProperty firProperty = (FirProperty) ((FirSyntheticPropertySymbol) symbol).getFir();
            if (firProperty instanceof FirSyntheticProperty) {
                return getFirCallableContainerFile(((FirSyntheticProperty) firProperty).getGetter().getDelegate().getSymbol());
            }
        }
        return this.state.getCallableContainerMap().get(symbol);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirClassLikeDeclaration getFirClassifierByFqName(ClassId classId) {
        classId.getClass();
        if (!classId.isLocal()) {
            return this.state.getClassifierMap().get(classId);
        }
        wec.a("Local ", classId, " should never be used to find its corresponding classifier");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirClassifierContainerFile(ClassId fqName) {
        fqName.getClass();
        FirFile firFile = this.state.getClassifierContainerFileMap().get(fqName);
        if (firFile != null) {
            return firFile;
        }
        w04.a("Couldn't find container for ", fqName);
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirClassifierContainerFileIfAny(ClassId fqName) {
        fqName.getClass();
        return this.state.getClassifierContainerFileMap().get(fqName);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public List<FirFile> getFirFilesByPackage(FqName fqName) {
        fqName.getClass();
        List<FirFile> list = this.state.getFileMap().get(fqName);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirReplSnippetContainerFile(FirReplSnippetSymbol symbol) {
        symbol.getClass();
        return this.state.getSnippetContainerMap().get(symbol);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirScriptSymbol getFirScriptByFilePath(String path) {
        path.getClass();
        return this.state.getScriptByFilePathMap().get(FirProviderImplKt.toSystemIndependentScriptPath(path));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirScriptContainerFile(FirScriptSymbol symbol) {
        symbol.getClass();
        return this.state.getScriptContainerMap().get(symbol);
    }

    public final FirKotlinScopeProvider getKotlinScopeProvider() {
        return this.kotlinScopeProvider;
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirSymbolProvider getSymbolProvider() {
        return this.symbolProvider;
    }

    @Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0003H\u0002J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\u0003H\u0016JT\u0010\u0016\u001a\u00020\u0002\"\n\b\u0000\u0010\u0017\u0018\u0001*\u00020\u0018\"\u000e\b\u0001\u0010\u0019*\b\u0012\u0004\u0012\u0002H\u00170\u001a2\u0006\u0010\u001b\u001a\u0002H\u00192\u0006\u0010\t\u001a\u00020\u00032\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u001f0\u001dH\u0082\b¢\u0006\u0002\u0010 J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020&2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020)2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010*\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020/2\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u00100\u001a\u00020\u00022\u0006\u00101\u001a\u0002022\u0006\u0010\t\u001a\u00020\u0003H\u0016¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$FirRecorder;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$FirRecorderData;", "<init>", "()V", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "visitClassifier", "classLike", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "visitPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "registerCallable", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "symbol", "map", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/CallableId;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl$FirRecorderData;Ljava/util/Map;)V", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "visitScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "visitReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirRecorder extends FirDefaultVisitor<Unit, FirRecorderData> {
        public static final FirRecorder INSTANCE = new FirRecorder();

        private FirRecorder() {
        }

        private final void visitClassifier(FirClassLikeDeclaration classLike, FirRecorderData data) {
            ClassId classId = classLike.getSymbol().getClassId();
            if (!data.getState().getClassifierMap().containsKey(classId)) {
                data.getState().getClassifierMap().put(classId, classLike);
                data.getState().getClassifierContainerFileMap().put(classId, data.getFile());
            } else {
                FirNameConflictsTracker nameConflictsTracker = data.getNameConflictsTracker();
                if (nameConflictsTracker != null) {
                    nameConflictsTracker.registerClassifierRedeclaration(classId, classLike.getSymbol(), data.getFile(), ((FirClassLikeDeclaration) MapsKt.getValue(data.getState().getClassifierMap(), classId)).getSymbol(), (FirFile) MapsKt.getValue(data.getState().getClassifierContainerFileMap(), classId));
                }
            }
        }

        public void visitConstructor(FirConstructor constructor, FirRecorderData data) {
            constructor.getClass();
            data.getClass();
            FirConstructorSymbol symbol = constructor.getSymbol();
            HashMap<CallableId, List<FirConstructorSymbol>> constructorMap = data.getState().getConstructorMap();
            CallableId callableId = symbol.getCallableId();
            if (callableId == null) {
                return;
            }
            constructorMap.merge(callableId, CollectionsKt.listOf(symbol), new FirProviderImplKt$sam$java_util_function_BiFunction$0(FirProviderImpl$FirRecorder$registerCallable$1.INSTANCE));
            data.getState().getCallableContainerMap().put(symbol, data.getFile());
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
            visitElement(firElement, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        public void visitEnumEntry(FirEnumEntry enumEntry, FirRecorderData data) {
            enumEntry.getClass();
            data.getClass();
            data.getState().getCallableContainerMap().put(enumEntry.getSymbol(), data.getFile());
        }

        public void visitNamedFunction(FirNamedFunction namedFunction, FirRecorderData data) {
            namedFunction.getClass();
            data.getClass();
            FirNamedFunctionSymbol symbol = namedFunction.getSymbol();
            Map<CallableId, List<FirNamedFunctionSymbol>> functionMap = data.getState().getFunctionMap();
            CallableId callableId = symbol.getCallableId();
            if (callableId == null) {
                return;
            }
            functionMap.merge(callableId, CollectionsKt.listOf(symbol), new FirProviderImplKt$sam$java_util_function_BiFunction$0(FirProviderImpl$FirRecorder$registerCallable$1.INSTANCE));
            data.getState().getCallableContainerMap().put(symbol, data.getFile());
        }

        public void visitProperty(FirProperty property, FirRecorderData data) {
            property.getClass();
            data.getClass();
            FirPropertySymbol symbol = property.getSymbol();
            Map<CallableId, List<FirPropertySymbol>> propertyMap = data.getState().getPropertyMap();
            CallableId callableId = symbol.getCallableId();
            if (callableId != null) {
                propertyMap.merge(callableId, CollectionsKt.listOf(symbol), new FirProviderImplKt$sam$java_util_function_BiFunction$0(FirProviderImpl$FirRecorder$registerCallable$1.INSTANCE));
                data.getState().getCallableContainerMap().put(symbol, data.getFile());
            }
            FirPropertyAccessor getter = property.getGetter();
            if (getter != null) {
                INSTANCE.visitPropertyAccessor(getter, data);
            }
            FirPropertyAccessor setter = property.getSetter();
            if (setter != null) {
                INSTANCE.visitPropertyAccessor(setter, data);
            }
        }

        public void visitPropertyAccessor(FirPropertyAccessor propertyAccessor, FirRecorderData data) {
            propertyAccessor.getClass();
            data.getClass();
            data.getState().getCallableContainerMap().put(propertyAccessor.getSymbol(), data.getFile());
        }

        public void visitRegularClass(FirRegularClass regularClass, FirRecorderData data) {
            regularClass.getClass();
            data.getClass();
            visitClassifier(regularClass, data);
            ClassId classId = regularClass.getSymbol().getClassId();
            if (!classId.isNestedClass() && !regularClass.getIsLocal()) {
                HashMap<FqName, Set<Name>> classesInPackage = data.getState().getClassesInPackage();
                FqName packageFqName = classId.getPackageFqName();
                Set<Name> linkedHashSet = classesInPackage.get(packageFqName);
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet<>();
                    classesInPackage.put(packageFqName, linkedHashSet);
                }
                linkedHashSet.add(classId.getShortClassName());
                HashMap<FqName, Set<Name>> classifierInPackage = data.getState().getClassifierInPackage();
                FqName packageFqName2 = classId.getPackageFqName();
                Set<Name> linkedHashSet2 = classifierInPackage.get(packageFqName2);
                if (linkedHashSet2 == null) {
                    linkedHashSet2 = new LinkedHashSet<>();
                    classifierInPackage.put(packageFqName2, linkedHashSet2);
                }
                linkedHashSet2.add(classId.getShortClassName());
            }
            regularClass.acceptChildren(this, data);
        }

        public void visitReplSnippet(FirReplSnippet replSnippet, FirRecorderData data) {
            replSnippet.getClass();
            data.getClass();
            data.getState().getSnippetContainerMap().put(replSnippet.getSymbol(), data.getFile());
            visitRegularClass(replSnippet.getSnippetClass(), data);
            super.visitReplSnippet(replSnippet, data);
        }

        public void visitScript(FirScript script, FirRecorderData data) {
            String path;
            script.getClass();
            data.getClass();
            FirScriptSymbol symbol = script.getSymbol();
            data.getState().getScriptContainerMap().put(symbol, data.getFile());
            KtSourceFile sourceFile = data.getFile().getSourceFile();
            if (sourceFile != null && (path = sourceFile.getPath()) != null) {
                data.getState().getScriptByFilePathMap().put(FirProviderImplKt.toSystemIndependentScriptPath(path), symbol);
            }
            script.acceptChildren(this, data);
        }

        public void visitTypeAlias(FirTypeAlias typeAlias, FirRecorderData data) {
            typeAlias.getClass();
            data.getClass();
            visitClassifier(typeAlias, data);
            ClassId classId = typeAlias.getSymbol().getClassId();
            HashMap<FqName, Set<Name>> classifierInPackage = data.getState().getClassifierInPackage();
            FqName packageFqName = classId.getPackageFqName();
            Set<Name> linkedHashSet = classifierInPackage.get(packageFqName);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet<>();
                classifierInPackage.put(packageFqName, linkedHashSet);
            }
            linkedHashSet.add(classId.getShortClassName());
        }

        public void visitElement(FirElement element, FirRecorderData data) {
            element.getClass();
            data.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitEnumEntry(FirEnumEntry firEnumEntry, Object obj) {
            visitEnumEntry(firEnumEntry, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitPropertyAccessor(FirPropertyAccessor firPropertyAccessor, Object obj) {
            visitPropertyAccessor(firPropertyAccessor, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitReplSnippet(FirReplSnippet firReplSnippet, Object obj) {
            visitReplSnippet(firReplSnippet, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitTypeAlias(FirTypeAlias firTypeAlias, Object obj) {
            visitTypeAlias(firTypeAlias, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitConstructor(FirConstructor firConstructor, Object obj) {
            visitConstructor(firConstructor, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitNamedFunction(FirNamedFunction firNamedFunction, Object obj) {
            visitNamedFunction(firNamedFunction, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitScript(FirScript firScript, Object obj) {
            visitScript(firScript, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitProperty(FirProperty firProperty, Object obj) {
            visitProperty(firProperty, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitRegularClass(FirRegularClass firRegularClass, Object obj) {
            visitRegularClass(firRegularClass, (FirRecorderData) obj);
            return Unit.INSTANCE;
        }
    }

    public final void recordFile(FirFile file) {
        file.getClass();
        recordFile(file, this.state);
    }
}
