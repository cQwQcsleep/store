package androidx.compose.compiler.plugins.kotlin.analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcherCollection;", "", "matchers", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcher;", "<init>", "(Ljava/util/Set;)V", "externalTypesMatched", "", "Lorg/jetbrains/kotlin/name/FqName;", "", "matcherTree", "Landroidx/compose/compiler/plugins/kotlin/analysis/MutableMatcherTree;", "maskForName", "", "name", "(Lorg/jetbrains/kotlin/name/FqName;)Ljava/lang/Integer;", "matches", "superTypes", "", "Lorg/jetbrains/kotlin/ir/types/IrType;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FqNameMatcherCollection {
    private final Map<FqName, Boolean> externalTypesMatched;
    private final MutableMatcherTree matcherTree;
    private final Set<FqNameMatcher> matchers;

    public FqNameMatcherCollection(Set<FqNameMatcher> set) {
        set.getClass();
        this.matchers = set;
        this.externalTypesMatched = new LinkedHashMap();
        MutableMatcherTree mutableMatcherTree = new MutableMatcherTree();
        this.matcherTree = mutableMatcherTree;
        mutableMatcherTree.putAll(set);
    }

    public final Integer maskForName(FqName name) {
        FqNameMatcher fqNameMatcherFindFirstPositiveMatcher;
        if (name == null || (fqNameMatcherFindFirstPositiveMatcher = this.matcherTree.findFirstPositiveMatcher(name)) == null) {
            return null;
        }
        return Integer.valueOf(fqNameMatcherFindFirstPositiveMatcher.getMask());
    }

    public final boolean matches(FqName name, List<? extends IrType> superTypes) {
        superTypes.getClass();
        boolean z = false;
        if (this.matchers.isEmpty() || name == null) {
            return false;
        }
        Boolean bool = this.externalTypesMatched.get(name);
        if (bool != null) {
            return bool.booleanValue();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = superTypes.iterator();
        while (it.hasNext()) {
            FqName classFqName = IrTypesKt.getClassFqName((IrType) it.next());
            if (classFqName != null) {
                arrayList.add(classFqName);
            }
        }
        if (this.matcherTree.findFirstPositiveMatcher(name) != null) {
            z = true;
            break;
        }
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (this.matcherTree.findFirstPositiveMatcher((FqName) it2.next()) != null) {
                    z = true;
                    break;
                }
            }
        }
        this.externalTypesMatched.put(name, Boolean.valueOf(z));
        return z;
    }
}
