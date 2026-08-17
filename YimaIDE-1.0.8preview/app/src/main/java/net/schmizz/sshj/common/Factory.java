package net.schmizz.sshj.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Factory<T> {

    public interface Named<T> extends Factory<T> {

        public static class Util {
            public static <T> T create(List<Named<T>> list, String str) {
                if (list == null) {
                    return null;
                }
                for (Named<T> named : list) {
                    if (named.getName().equals(str)) {
                        return named.create();
                    }
                }
                return null;
            }

            public static <T> Named<T> get(List<Named<T>> list, String str) {
                for (Named<T> named : list) {
                    if (named.getName().equals(str)) {
                        return named;
                    }
                }
                return null;
            }

            public static <T> List<String> getNames(List<Named<T>> list) {
                LinkedList linkedList = new LinkedList();
                Iterator<Named<T>> it = list.iterator();
                while (it.hasNext()) {
                    linkedList.add(it.next().getName());
                }
                return linkedList;
            }
        }

        String getName();
    }

    T create();
}
