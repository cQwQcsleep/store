package org.eclipse.tm4e.core.internal.matcher;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface NameMatcher<T> {
    public static final NameMatcher<List<String>> DEFAULT = new AnonymousClass1();

    boolean matches(Collection<String> collection, T t);

    /* JADX INFO: renamed from: org.eclipse.tm4e.core.internal.matcher.NameMatcher$1, reason: invalid class name */
    public class AnonymousClass1 implements NameMatcher<List<String>> {
        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$matches$0(int[] iArr, List list, String str) {
            for (int i = iArr[0]; i < list.size(); i++) {
                if (scopesAreMatching((String) list.get(i), str)) {
                    iArr[0] = iArr[0] + 1;
                    return true;
                }
            }
            return false;
        }

        private boolean scopesAreMatching(String str, String str2) {
            if (str == null) {
                return false;
            }
            if (str.equals(str2)) {
                return true;
            }
            int length = str2.length();
            return str.length() > length && str.substring(0, length).equals(str2) && str.charAt(length) == '.';
        }

        /* JADX INFO: renamed from: matches, reason: avoid collision after fix types in other method */
        public boolean matches2(Collection<String> collection, final List<String> list) {
            if (list.size() < collection.size()) {
                return false;
            }
            final int[] iArr = {0};
            return collection.stream().allMatch(new Predicate() { // from class: org.eclipse.tm4e.core.internal.matcher.e
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.lambda$matches$0(iArr, list, (String) obj);
                }
            });
        }

        @Override // org.eclipse.tm4e.core.internal.matcher.NameMatcher
        public /* bridge */ /* synthetic */ boolean matches(Collection collection, List<String> list) {
            return matches2((Collection<String>) collection, list);
        }
    }
}
