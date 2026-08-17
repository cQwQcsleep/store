package androidx.compose.compiler.plugins.kotlin.analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\nJ\u0010\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/MutableMatcherTree;", "", "<init>", "()V", "root", "Landroidx/compose/compiler/plugins/kotlin/analysis/MutableMatcherTree$Node;", "putAll", "", "matchers", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcher;", "put", "matcher", "findFirstPositiveMatcher", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "Node", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class MutableMatcherTree {
    private final Node root = new Node();

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/analysis/MutableMatcherTree$Node;", "", "<init>", "()V", "children", "", "", "getChildren", "()Ljava/util/Map;", "values", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/FqNameMatcher;", "getValues", "()Ljava/util/List;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Node {
        private final Map<Character, Node> children = new LinkedHashMap();
        private final List<FqNameMatcher> values = new ArrayList();

        public final Map<Character, Node> getChildren() {
            return this.children;
        }

        public final List<FqNameMatcher> getValues() {
            return this.values;
        }
    }

    public final FqNameMatcher findFirstPositiveMatcher(FqName fqName) {
        fqName.getClass();
        String strAsString = fqName.asString();
        Node node = this.root;
        int i = 0;
        while (node != null) {
            Character orNull = StringsKt.getOrNull(strAsString, i);
            int size = node.getValues().size();
            for (int i2 = 0; i2 < size; i2++) {
                FqNameMatcher fqNameMatcher = node.getValues().get(i2);
                if (fqNameMatcher.matches(fqName)) {
                    return fqNameMatcher;
                }
            }
            if (orNull != null) {
                node = node.getChildren().get(orNull);
                i++;
            } else {
                node = null;
            }
        }
        return null;
    }

    public final void put(FqNameMatcher matcher) {
        matcher.getClass();
        Node node = this.root;
        String key = matcher.getKey();
        int length = key.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = key.charAt(i);
            Map<Character, Node> children = node.getChildren();
            Character chValueOf = Character.valueOf(cCharAt);
            Node node2 = children.get(chValueOf);
            if (node2 == null) {
                node2 = new Node();
                children.put(chValueOf, node2);
            }
            node = node2;
        }
        node.getValues().add(matcher);
    }

    public final void putAll(Iterable<FqNameMatcher> matchers) {
        matchers.getClass();
        Iterator<FqNameMatcher> it = matchers.iterator();
        while (it.hasNext()) {
            put(it.next());
        }
    }
}
