package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.incremental.LookupSymbol;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\r\u001a\u00020\tJ\u0011\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0086\u0002R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/LookupSymbolSet;", "", "lookupSymbols", "", "Lorg/jetbrains/kotlin/incremental/LookupSymbol;", "<init>", "(Ljava/lang/Iterable;)V", "scopeToLookupNames", "", "Lorg/jetbrains/kotlin/name/FqName;", "", "", "getLookupNamesInScope", "scope", "contains", "", "lookupSymbol", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LookupSymbolSet {
    private final Map<FqName, Set<String>> scopeToLookupNames;

    public LookupSymbolSet(Iterable<LookupSymbol> iterable) {
        iterable.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (LookupSymbol lookupSymbol : iterable) {
            FqName fqName = new FqName(lookupSymbol.getScope());
            Object linkedHashSet = linkedHashMap.get(fqName);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
                linkedHashMap.put(fqName, linkedHashSet);
            }
            ((Set) linkedHashSet).add(lookupSymbol.getName());
        }
        this.scopeToLookupNames = linkedHashMap;
    }

    public final boolean contains(LookupSymbol lookupSymbol) {
        lookupSymbol.getClass();
        Set<String> set = this.scopeToLookupNames.get(new FqName(lookupSymbol.getScope()));
        if (set != null) {
            return set.contains(lookupSymbol.getName());
        }
        return false;
    }

    public final Set<String> getLookupNamesInScope(FqName scope) {
        scope.getClass();
        Set<String> set = this.scopeToLookupNames.get(scope);
        return set == null ? SetsKt.emptySet() : set;
    }
}
