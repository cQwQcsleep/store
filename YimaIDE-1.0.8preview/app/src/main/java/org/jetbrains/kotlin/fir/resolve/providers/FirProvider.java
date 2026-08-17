package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousInitializerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDanglingModifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH&J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000eH&J\u0014\u0010\u000f\u001a\u00020\u00102\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0016J\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00102\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0016J\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00102\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0016H&J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0018H&J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u001dH&J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\u0006\u0010\u0011\u001a\u00020 H&J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\u0011\u001a\u00020 H&J\u001a\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00142\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030%H\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getSymbolProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "isPhasedFirAllowed", Argument.Delimiters.none, "()Z", "getFirClassifierByFqName", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getFirClassifierContainerFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "fqName", "getFirClassifierContainerFileIfAny", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getFirCallableContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getFirScriptContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getFirScriptByFilePath", ModuleXmlParser.PATH, Argument.Delimiters.none, "getFirReplSnippetContainerFile", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getFirFilesByPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "getClassNamesInPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getContainingClass", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirProvider implements FirSessionComponent {
    public abstract Set<Name> getClassNamesInPackage(FqName fqName);

    public FirClassLikeSymbol<?> getContainingClass(FirBasedSymbol<?> symbol) {
        FirDanglingModifierSymbol firDanglingModifierSymbol;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        symbol.getClass();
        if (symbol instanceof FirCallableSymbol) {
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) symbol;
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag2 = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol);
            if (coneClassLikeLookupTagContainingClassLookupTag2 != null) {
                return ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagContainingClassLookupTag2, firCallableSymbol.getModuleData().getSession());
            }
            return null;
        }
        if (symbol instanceof FirClassLikeSymbol) {
            FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) symbol;
            ConeClassLikeLookupTag containingClassLookupTag = ClassMembersKt.getContainingClassLookupTag(firClassLikeSymbol);
            if (containingClassLookupTag != null) {
                return ToSymbolUtilsKt.toSymbol(containingClassLookupTag, firClassLikeSymbol.getModuleData().getSession());
            }
            return null;
        }
        if (symbol instanceof FirAnonymousInitializerSymbol) {
            FirBasedSymbol<?> containingDeclarationSymbol = ((FirAnonymousInitializerSymbol) symbol).getContainingDeclarationSymbol();
            if (containingDeclarationSymbol instanceof FirClassLikeSymbol) {
                return (FirClassLikeSymbol) containingDeclarationSymbol;
            }
            return null;
        }
        if (!(symbol instanceof FirDanglingModifierSymbol) || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((firDanglingModifierSymbol = (FirDanglingModifierSymbol) symbol))) == null) {
            return null;
        }
        return ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagContainingClassLookupTag, firDanglingModifierSymbol.getModuleData().getSession());
    }

    public abstract FirFile getFirCallableContainerFile(FirCallableSymbol<?> symbol);

    public abstract FirClassLikeDeclaration getFirClassifierByFqName(ClassId classId);

    public FirFile getFirClassifierContainerFile(FirClassLikeSymbol<?> symbol) {
        symbol.getClass();
        return getFirClassifierContainerFile(symbol.getClassId());
    }

    public abstract FirFile getFirClassifierContainerFile(ClassId fqName);

    public FirFile getFirClassifierContainerFileIfAny(FirClassLikeSymbol<?> symbol) {
        symbol.getClass();
        return getFirClassifierContainerFileIfAny(symbol.getClassId());
    }

    public abstract FirFile getFirClassifierContainerFileIfAny(ClassId fqName);

    public abstract List<FirFile> getFirFilesByPackage(FqName fqName);

    public abstract FirFile getFirReplSnippetContainerFile(FirReplSnippetSymbol symbol);

    public abstract FirScriptSymbol getFirScriptByFilePath(String path);

    public abstract FirFile getFirScriptContainerFile(FirScriptSymbol symbol);

    public abstract FirSymbolProvider getSymbolProvider();

    public boolean isPhasedFirAllowed() {
        return false;
    }
}
