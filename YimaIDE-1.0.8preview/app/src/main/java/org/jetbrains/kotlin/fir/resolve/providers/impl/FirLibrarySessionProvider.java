package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0016\u0010\u0010\u001a\u0004\u0018\u00010\r2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0016J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u0019H\u0016J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u001b2\u0006\u0010\u000e\u001a\u00020\u001cH\u0016J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u000e\u001a\u00020\u001cH\u0016J\b\u0010 \u001a\u00020!H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirLibrarySessionProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;)V", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getFirClassifierByFqName", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getFirClassifierContainerFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "fqName", "getFirClassifierContainerFileIfAny", "getFirCallableContainerFile", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getFirScriptContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getFirScriptByFilePath", ModuleXmlParser.PATH, Argument.Delimiters.none, "getFirReplSnippetContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getFirFilesByPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "getClassNamesInPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "shouldNotBeCalled", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLibrarySessionProvider extends FirProvider {
    private final FirSymbolProvider symbolProvider;

    public FirLibrarySessionProvider(FirSymbolProvider firSymbolProvider) {
        firSymbolProvider.getClass();
        this.symbolProvider = firSymbolProvider;
    }

    private final Void shouldNotBeCalled() {
        throw new IllegalStateException("Should not be called for FirLibrarySessionProvider");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public Set<Name> getClassNamesInPackage(FqName fqName) throws KotlinNothingValueException {
        fqName.getClass();
        shouldNotBeCalled();
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirCallableContainerFile(FirCallableSymbol<?> symbol) {
        symbol.getClass();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirClassLikeDeclaration getFirClassifierByFqName(ClassId classId) {
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = getSymbolProvider().getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId != null) {
            return (FirClassLikeDeclaration) classLikeSymbolByClassId.getFir();
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirClassifierContainerFile(ClassId fqName) throws KotlinNothingValueException {
        fqName.getClass();
        shouldNotBeCalled();
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirClassifierContainerFileIfAny(ClassId fqName) {
        fqName.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public List<FirFile> getFirFilesByPackage(FqName fqName) {
        fqName.getClass();
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirReplSnippetContainerFile(FirReplSnippetSymbol symbol) {
        symbol.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirScriptSymbol getFirScriptByFilePath(String path) {
        path.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirFile getFirScriptContainerFile(FirScriptSymbol symbol) {
        symbol.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirProvider
    public FirSymbolProvider getSymbolProvider() {
        return this.symbolProvider;
    }
}
