package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirProviderImpl;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00000\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00182\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010#\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020$H\u0016J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00180\u000e2\u0006\u0010\u0019\u001a\u00020&H\u0016J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010\u0019\u001a\u00020&H\u0016J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "previousProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirModuleData;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/Map;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "generatedFilesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirProviderImpl;", "providers", Argument.Delimiters.none, "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getFirClassifierByFqName", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getFirClassifierContainerFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "fqName", "getFirClassifierContainerFileIfAny", "getFirCallableContainerFile", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getFirScriptContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getFirScriptByFilePath", ModuleXmlParser.PATH, Argument.Delimiters.none, "getFirReplSnippetContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getFirFilesByPackage", "Lorg/jetbrains/kotlin/name/FqName;", "getClassNamesInPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "recordFile", Argument.Delimiters.none, "file", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirProviderWithGeneratedFiles extends FirProvider {
    private final FirProviderImpl generatedFilesProvider;
    private final List<FirProvider> providers;
    private final FirSession session;

    public FirProviderWithGeneratedFiles(FirSession firSession, Map<FirModuleData, FirProviderWithGeneratedFiles> map) {
        firSession.getClass();
        map.getClass();
        this.session = firSession;
        FirProviderImpl firProviderImpl = new FirProviderImpl(firSession, FirKotlinScopeProviderKt.getKotlinScopeProvider(firSession));
        this.generatedFilesProvider = firProviderImpl;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(FirProviderKt.getFirProvider(firSession));
        listCreateListBuilder.add(firProviderImpl);
        List list = listCreateListBuilder;
        Iterator<T> it = FirModuleDataKt.getModuleData(firSession).getDependsOnDependencies().iterator();
        while (it.hasNext()) {
            FirProviderWithGeneratedFiles firProviderWithGeneratedFiles = map.get((FirModuleData) it.next());
            if (firProviderWithGeneratedFiles != null) {
                list.add(firProviderWithGeneratedFiles);
            }
        }
        this.providers = CollectionsKt.build(listCreateListBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public Set<Name> getClassNamesInPackage(FqName fqName) {
        fqName.getClass();
        List<FirProvider> list = this.providers;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(linkedHashSet, ((FirProvider) it.next()).getClassNamesInPackage(fqName));
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirCallableContainerFile(FirCallableSymbol<?> symbol) {
        symbol.getClass();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            FirFile firCallableContainerFile = ((FirProvider) it.next()).getFirCallableContainerFile(symbol);
            if (firCallableContainerFile != null) {
                return firCallableContainerFile;
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirClassLikeDeclaration getFirClassifierByFqName(ClassId classId) {
        classId.getClass();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            FirClassLikeDeclaration firClassifierByFqName = ((FirProvider) it.next()).getFirClassifierByFqName(classId);
            if (firClassifierByFqName != null) {
                return firClassifierByFqName;
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirClassifierContainerFile(ClassId fqName) {
        fqName.getClass();
        FirFile firClassifierContainerFileIfAny = getFirClassifierContainerFileIfAny(fqName);
        if (firClassifierContainerFileIfAny != null) {
            return firClassifierContainerFileIfAny;
        }
        w04.a("Couldn't find container for ", fqName);
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirClassifierContainerFileIfAny(ClassId fqName) {
        fqName.getClass();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            FirFile firClassifierContainerFileIfAny = ((FirProvider) it.next()).getFirClassifierContainerFileIfAny(fqName);
            if (firClassifierContainerFileIfAny != null) {
                return firClassifierContainerFileIfAny;
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public List<FirFile> getFirFilesByPackage(FqName fqName) {
        fqName.getClass();
        List<FirProvider> list = this.providers;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((FirProvider) it.next()).getFirFilesByPackage(fqName));
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirReplSnippetContainerFile(FirReplSnippetSymbol symbol) {
        symbol.getClass();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            FirFile firReplSnippetContainerFile = ((FirProvider) it.next()).getFirReplSnippetContainerFile(symbol);
            if (firReplSnippetContainerFile != null) {
                return firReplSnippetContainerFile;
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirScriptSymbol getFirScriptByFilePath(String path) {
        path.getClass();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            FirScriptSymbol firScriptByFilePath = ((FirProvider) it.next()).getFirScriptByFilePath(path);
            if (firScriptByFilePath != null) {
                return firScriptByFilePath;
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirScriptContainerFile(FirScriptSymbol symbol) {
        symbol.getClass();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            FirFile firScriptContainerFile = ((FirProvider) it.next()).getFirScriptContainerFile(symbol);
            if (firScriptContainerFile != null) {
                return firScriptContainerFile;
            }
        }
        return null;
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirSymbolProvider getSymbolProvider() {
        return ((FirProvider) CollectionsKt.first(this.providers)).getSymbolProvider();
    }

    public final void recordFile(FirFile file) {
        file.getClass();
        this.generatedFilesProvider.recordFile(file);
    }
}
