package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r*\u0006\u0012\u0002\b\u00030\u000eH\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H$J8\u0010\u0016\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00192\u0016\u0010\u001a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c\u0012\u0004\u0012\u00020\u00170\u001bH\u0004J\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0014\u001a\u00020\u0015H'b\u0002\b\u001fJ&\u0010 \u001a\u0004\u0018\u00010\u001e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0019H\u0005b\u0002\b\u001fJÃ\u0001\u0010!\u001a\u00020\u0017\"\b\b\u0000\u0010\"*\u00020#\"\u000e\b\u0001\u0010$*\b\u0012\u0004\u0012\u0002H\"0%2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00192\u0014\b\u0004\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u00170\u001b2\u001f\b\u0004\u0010&\u001a\u0019\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002H$0'¢\u0006\u0002\b(2/\u0010)\u001a+\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u00170\u001b\u0012\u0004\u0012\u00020\u00170*¢\u0006\u0002\b(2\u001e\u0010+\u001a\u001a\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\u00190'H\u0082\bJ4\u0010-\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00170\u001bH\u0004J8\u0010/\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00192\u0016\u0010\u001a\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u000300\u0012\u0004\u0012\u00020\u00170\u001bH\u0004J\u001c\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u0005H'b\u0002\b4R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractImportingScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractProviderBasedScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "lookupInFir", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Z)V", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getStaticsScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "isExcluded", "import", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processClassifiersFromImportsByName", Argument.Delimiters.none, "imports", Argument.Delimiters.none, "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "findEnumEntryWithoutResolution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "doFindEnumEntryWithoutResolution", "processCallablesFromImportsByName", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "buildImportedCopy", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "processCallablesByName", "Lkotlin/Function3;", "getTopLevelCallableSymbols", "Lorg/jetbrains/kotlin/name/FqName;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractImportingScope extends FirAbstractProviderBasedScope {
    private final ScopeSession scopeSession;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractImportingScope(FirSession firSession, ScopeSession scopeSession, boolean z) {
        super(firSession, z);
        firSession.getClass();
        scopeSession.getClass();
        this.scopeSession = scopeSession;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirContainingNamesAwareScope getStaticsScope(FirClassSymbol<?> firClassSymbol) {
        return ((FirClass) firClassSymbol.getFir()).getClassKind() == ClassKind.OBJECT ? FirKotlinScopeProviderKt.unsubstitutedScope(firClassSymbol, getSession(), this.scopeSession, false, FirResolvePhase.STATUS) : ((FirClass) firClassSymbol.getFir()).getScopeProvider().getStaticScope((FirClass) firClassSymbol.getFir(), getSession(), this.scopeSession);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0079  */
    /* JADX WARN: Multi-variable type inference failed */
    @FirImplementationDetail
    public final FirEnumEntrySymbol doFindEnumEntryWithoutResolution(Name name, List<? extends FirResolvedImport> imports) {
        Name importedName;
        ClassId resolvedParentClassId;
        FirEnumEntry firEnumEntry;
        imports.getClass();
        Iterator<? extends FirResolvedImport> it = imports.iterator();
        while (true) {
            FirEnumEntry firEnumEntry2 = null;
            if (!it.hasNext()) {
                return null;
            }
            FirResolvedImport next = it.next();
            if (name == null) {
                importedName = next.getImportedName();
                if (importedName == null) {
                    continue;
                }
            } else {
                importedName = name;
            }
            if (!isExcluded(next, importedName) && (resolvedParentClassId = next.getResolvedParentClassId()) != null) {
                FirClassLikeSymbol<?> classLikeSymbolByClassId = getProvider().getClassLikeSymbolByClassId(resolvedParentClassId);
                FirRegularClassSymbol firRegularClassSymbol = classLikeSymbolByClassId instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) classLikeSymbolByClassId : null;
                if (firRegularClassSymbol != null && ((FirRegularClass) firRegularClassSymbol.getFir()).getClassKind() == ClassKind.ENUM_CLASS) {
                    for (FirDeclaration firDeclaration : ((FirRegularClass) firRegularClassSymbol.getFir()).getDeclarations()) {
                        if (firDeclaration instanceof FirEnumEntry) {
                            firEnumEntry = (FirEnumEntry) firDeclaration;
                            if (!Intrinsics.areEqual(firEnumEntry.getName(), importedName)) {
                                firEnumEntry = null;
                            }
                        } else {
                            firEnumEntry = null;
                        }
                        if (firEnumEntry != null) {
                            firEnumEntry2 = firEnumEntry;
                            break;
                        }
                    }
                    if (firEnumEntry2 != null) {
                        return firEnumEntry2.getSymbol();
                    }
                }
            }
        }
    }

    @FirImplementationDetail
    public abstract FirEnumEntrySymbol findEnumEntryWithoutResolution(Name name);

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public abstract boolean isExcluded(FirResolvedImport firResolvedImport, Name name);

    public final void processClassifiersFromImportsByName(Name name, List<? extends FirResolvedImport> imports, Function1<? super FirClassLikeSymbol<?>, Unit> processor) {
        Name importedName;
        ClassId classIdCreateNestedClassId;
        imports.getClass();
        processor.getClass();
        for (FirResolvedImport firResolvedImport : imports) {
            if (name == null) {
                importedName = firResolvedImport.getImportedName();
                if (importedName == null) {
                }
            } else {
                importedName = name;
            }
            if (!isExcluded(firResolvedImport, importedName)) {
                ClassId resolvedParentClassId = firResolvedImport.getResolvedParentClassId();
                if (resolvedParentClassId == null || (classIdCreateNestedClassId = resolvedParentClassId.createNestedClassId(importedName)) == null) {
                    classIdCreateNestedClassId = ClassId.Companion.topLevel(firResolvedImport.getPackageFqName().child(importedName));
                }
                FirClassLikeSymbol<?> classLikeSymbolByClassId = getProvider().getClassLikeSymbolByClassId(classIdCreateNestedClassId);
                if (classLikeSymbolByClassId != null) {
                    processor.invoke(classLikeSymbolByClassId);
                }
            }
        }
    }

    public final void processFunctionsByName(Name name, List<? extends FirResolvedImport> imports, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        Name importedName;
        imports.getClass();
        processor.getClass();
        FirSymbolProvider provider = getProvider();
        for (FirResolvedImport firResolvedImport : imports) {
            if (name == null) {
                importedName = firResolvedImport.getImportedName();
                if (importedName == null) {
                }
            } else {
                importedName = name;
            }
            if (!isExcluded(firResolvedImport, importedName)) {
                ClassId resolvedParentClassId = firResolvedImport.getResolvedParentClassId();
                if (resolvedParentClassId != null) {
                    FirClassLikeSymbol<?> classLikeSymbolByClassId = getProvider().getClassLikeSymbolByClassId(resolvedParentClassId);
                    final FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = classLikeSymbolByClassId != null ? DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId, getSession()) : null;
                    FirContainingNamesAwareScope staticsScope = firRegularClassSymbolFullyExpandedClass != null ? getStaticsScope(firRegularClassSymbolFullyExpandedClass) : null;
                    if (staticsScope != null) {
                        staticsScope.processFunctionsByName(importedName, new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope$processFunctionsByName$$inlined$processCallablesFromImportsByName$1
                            /* JADX WARN: Multi-variable type inference failed */
                            public final void invoke(FirNamedFunctionSymbol firNamedFunctionSymbol) {
                                firNamedFunctionSymbol.getClass();
                                if (!firNamedFunctionSymbol.getRawStatus().isStatic() && firRegularClassSymbolFullyExpandedClass.getClassKind() != ClassKind.OBJECT) {
                                    processor.invoke(firNamedFunctionSymbol);
                                    return;
                                }
                                processor.invoke(FirAbstractImportingScopeKt.buildImportedVersion((FirNamedFunction) firNamedFunctionSymbol.getFir(), firRegularClassSymbolFullyExpandedClass.getClassId()).getSymbol());
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((FirNamedFunctionSymbol) obj);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                if (!importedName.isSpecial()) {
                    String identifier = importedName.getIdentifier();
                    identifier.getClass();
                    if (identifier.length() > 0) {
                    }
                }
                Iterator<FirNamedFunctionSymbol> it = provider.getTopLevelFunctionSymbols(firResolvedImport.getPackageFqName(), importedName).iterator();
                while (it.hasNext()) {
                    processor.invoke(it.next());
                }
            }
        }
    }

    public final void processPropertiesByName(Name name, List<? extends FirResolvedImport> imports, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        Name importedName;
        imports.getClass();
        processor.getClass();
        FirSymbolProvider provider = getProvider();
        for (FirResolvedImport firResolvedImport : imports) {
            if (name == null) {
                importedName = firResolvedImport.getImportedName();
                if (importedName == null) {
                }
            } else {
                importedName = name;
            }
            if (!isExcluded(firResolvedImport, importedName)) {
                ClassId resolvedParentClassId = firResolvedImport.getResolvedParentClassId();
                if (resolvedParentClassId != null) {
                    FirClassLikeSymbol<?> classLikeSymbolByClassId = getProvider().getClassLikeSymbolByClassId(resolvedParentClassId);
                    final FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = classLikeSymbolByClassId != null ? DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId, getSession()) : null;
                    FirContainingNamesAwareScope staticsScope = firRegularClassSymbolFullyExpandedClass != null ? getStaticsScope(firRegularClassSymbolFullyExpandedClass) : null;
                    if (staticsScope != null) {
                        staticsScope.processPropertiesByName(importedName, new Function1<FirVariableSymbol<?>, Unit>() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope$processPropertiesByName$$inlined$processCallablesFromImportsByName$1
                            /* JADX WARN: Multi-variable type inference failed */
                            public final void invoke(FirVariableSymbol<?> firVariableSymbol) {
                                firVariableSymbol.getClass();
                                if (!firVariableSymbol.getRawStatus().isStatic() && firRegularClassSymbolFullyExpandedClass.getClassKind() != ClassKind.OBJECT) {
                                    processor.invoke(firVariableSymbol);
                                    return;
                                }
                                processor.invoke(FirAbstractImportingScopeKt.buildImportedVersion((FirVariable) firVariableSymbol.getFir(), firRegularClassSymbolFullyExpandedClass.getClassId()).getSymbol());
                            }

                            /* JADX WARN: Multi-variable type inference failed */
                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((FirVariableSymbol<?>) obj);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                if (!importedName.isSpecial()) {
                    String identifier = importedName.getIdentifier();
                    identifier.getClass();
                    if (identifier.length() > 0) {
                    }
                }
                Iterator<FirPropertySymbol> it = provider.getTopLevelPropertySymbols(firResolvedImport.getPackageFqName(), importedName).iterator();
                while (it.hasNext()) {
                    processor.invoke(it.next());
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirAbstractProviderBasedScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public abstract FirAbstractImportingScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);

    public final FirContainingNamesAwareScope getStaticsScope(ClassId classId) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = getProvider().getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId, getSession())) == null) {
            return null;
        }
        return getStaticsScope(firRegularClassSymbolFullyExpandedClass);
    }
}
