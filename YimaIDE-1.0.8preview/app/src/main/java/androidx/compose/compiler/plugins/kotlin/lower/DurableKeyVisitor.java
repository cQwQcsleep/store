package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.lower.DurableKeyVisitor;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J'\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000f¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000f¢\u0006\u0002\u0010\u0012J'\u0010\u0011\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000f¢\u0006\u0002\u0010\u0010J/\u0010\u0013\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000f¢\u0006\u0002\u0010\u0014J.\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u0004R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/DurableKeyVisitor;", "", "keys", "", "", "<init>", "(Ljava/util/Set;)V", "current", "Landroidx/compose/compiler/plugins/kotlin/lower/PathPartInfo;", "parent", "sibling", "enter", "T", "part", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "siblings", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "root", "(Ljava/util/Set;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "buildPath", "Lkotlin/Pair;", "", "prefix", "pathSeparator", "siblingSeparator", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DurableKeyVisitor {
    private PathPartInfo current;
    private Set<String> keys;
    private PathPartInfo parent;
    private PathPartInfo sibling;

    public DurableKeyVisitor(Set<String> set) {
        set.getClass();
        this.keys = set;
        this.current = PathPartInfo.INSTANCE.getROOT();
    }

    public static Object a(DurableKeyVisitor durableKeyVisitor, Function0 function0) {
        return durableKeyVisitor.siblings(function0);
    }

    public static /* synthetic */ Pair buildPath$default(DurableKeyVisitor durableKeyVisitor, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "/";
        }
        if ((i & 4) != 0) {
            str3 = ":";
        }
        return durableKeyVisitor.buildPath(str, str2, str3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object root$default(DurableKeyVisitor durableKeyVisitor, Set set, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            set = new LinkedHashSet();
        }
        return durableKeyVisitor.root(set, function0);
    }

    public final Pair<String, Boolean> buildPath(String prefix, String pathSeparator, String siblingSeparator) {
        prefix.getClass();
        pathSeparator.getClass();
        siblingSeparator.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        this.current.print(sb, pathSeparator, siblingSeparator);
        String string = sb.toString();
        return TuplesKt.to(string, Boolean.valueOf(this.keys.add(string)));
    }

    public final <T> T enter(String part, Function0<? extends T> block) {
        part.getClass();
        block.getClass();
        PathPartInfo pathPartInfo = this.current;
        PathPartInfo pathPartInfo2 = this.sibling;
        PathPartInfo pathPartInfo3 = this.parent;
        PathPartInfo pathPartInfo4 = new PathPartInfo(part);
        try {
            if (pathPartInfo3 != null && pathPartInfo2 == null) {
                pathPartInfo4.setParent(pathPartInfo3);
                this.sibling = pathPartInfo4;
                this.parent = null;
            } else if (pathPartInfo3 == null || pathPartInfo2 == null) {
                pathPartInfo4.setParent(pathPartInfo);
                this.parent = null;
            } else {
                pathPartInfo4.setPrev(pathPartInfo2);
                this.sibling = pathPartInfo4;
                this.parent = null;
            }
            this.current = pathPartInfo4;
            T t = (T) block.invoke();
            this.current = pathPartInfo;
            this.parent = pathPartInfo3;
            return t;
        } catch (Throwable th) {
            this.current = pathPartInfo;
            this.parent = pathPartInfo3;
            throw th;
        }
    }

    public final <T> T root(Set<String> keys, Function0<? extends T> block) {
        keys.getClass();
        block.getClass();
        Set<String> set = this.keys;
        PathPartInfo pathPartInfo = this.current;
        PathPartInfo pathPartInfo2 = this.parent;
        PathPartInfo pathPartInfo3 = this.sibling;
        try {
            this.keys = keys;
            this.current = PathPartInfo.INSTANCE.getROOT();
            this.parent = null;
            this.sibling = null;
            return (T) siblings(block);
        } finally {
            this.keys = set;
            this.current = pathPartInfo;
            this.parent = pathPartInfo2;
            this.sibling = pathPartInfo3;
        }
    }

    public final <T> T siblings(Function0<? extends T> block) {
        block.getClass();
        PathPartInfo pathPartInfo = this.parent;
        if (pathPartInfo != null) {
            return (T) block.invoke();
        }
        PathPartInfo pathPartInfo2 = this.sibling;
        PathPartInfo pathPartInfo3 = this.current;
        try {
            this.parent = pathPartInfo3;
            this.sibling = null;
            return (T) block.invoke();
        } finally {
            this.parent = pathPartInfo;
            this.sibling = pathPartInfo2;
            this.current = pathPartInfo3;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DurableKeyVisitor() {
        Set set = null;
        this(set, 1, set);
    }

    public /* synthetic */ DurableKeyVisitor(Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashSet() : set);
    }

    public final <T> T siblings(String part, final Function0<? extends T> block) {
        part.getClass();
        block.getClass();
        return (T) enter(part, new Function0() { // from class: v04
            public final Object invoke() {
                return DurableKeyVisitor.a(this.b, block);
            }
        });
    }
}
