package org.jetbrains.kotlin.fir.scopes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR(\u0010\n\u001a\u001c\u0012\t\u0012\u00070\f¢\u0006\u0002\b\r0\u000bj\r\u0012\t\u0012\u00070\f¢\u0006\u0002\b\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirPlatformDeclarationFilter;", Argument.Delimiters.none, "<init>", "()V", "isNotPlatformDependent", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "namesToCheck", "Ljava/util/HashSet;", "Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlin/collections/HashSet;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPlatformDeclarationFilter {
    public static final FirPlatformDeclarationFilter INSTANCE = new FirPlatformDeclarationFilter();
    private static final HashSet<Name> namesToCheck;

    static {
        List listListOf = CollectionsKt.listOf(new String[]{"getOrDefault", "remove", "first", "last"});
        HashSet<Name> hashSet = new HashSet<>();
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            hashSet.add(Name.identifier((String) it.next()));
        }
        namesToCheck = hashSet;
    }

    private FirPlatformDeclarationFilter() {
    }

    public final boolean isNotPlatformDependent(FirNamedFunction function, FirSession session) {
        function.getClass();
        session.getClass();
        return (namesToCheck.contains(function.getName()) && FirAnnotationUtilsKt.hasAnnotation(function.getSymbol(), StandardNames.FqNames.platformDependentClassId, session)) ? false : true;
    }
}
