package com.reandroid.archive;

import com.reandroid.archive.PathTree;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.MergingIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class PathTree<T> implements Comparable<PathTree<?>>, Iterable<PathTree<T>>, JSONConvert<JSONObject> {
    public static final String NAME_elements = "elements";
    public static final String NAME_path = "path";
    private final LinkedHashMap<String, PathTree<T>> elementsMap;
    private T item;
    private String name;
    private PathTree<T> parent;

    public PathTree(T t, String str) {
        this.item = t;
        this.name = str;
        this.elementsMap = new LinkedHashMap<>();
    }

    public static /* synthetic */ Iterator a(PathTree pathTree) {
        return pathTree.isFile() ? SingleIterator.of(pathTree) : pathTree.getFiles();
    }

    private static String getName(String str) {
        int length = str.length();
        if (length < 2) {
            return str;
        }
        int i = length - 1;
        boolean z = false;
        if (str.charAt(i) == '/') {
            str = str.substring(0, i);
            z = true;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf >= 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        return z ? str.concat("/") : str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PathTree<?>[] getParentElements() {
        int depth = getDepth();
        PathTree<?>[] pathTreeArr = new PathTree[depth];
        int i = depth - 1;
        while (this != null && !this.isRoot()) {
            pathTreeArr[i] = this;
            i--;
            this = this.getParent();
        }
        return pathTreeArr;
    }

    public static <T1> PathTree<T1> newRoot() {
        return new PathTree<>("/");
    }

    private void setItemNonNull(T t) {
        if (t != null) {
            setItem(t);
        }
    }

    public static Iterator<String> sortPaths(Iterator<String> it) {
        PathTree pathTreeNewRoot = newRoot();
        while (it.hasNext()) {
            String next = it.next();
            pathTreeNewRoot.add(next, next);
        }
        pathTreeNewRoot.sort();
        return pathTreeNewRoot.getFileItems();
    }

    public PathTree<T> add(String str, T t) {
        int iIndexOf = str.indexOf(47);
        if (iIndexOf < 0) {
            if (str.length() <= 0) {
                return this;
            }
            PathTree<T> orCreate = getOrCreate(str);
            orCreate.setItem(t);
            return orCreate;
        }
        int i = iIndexOf + 1;
        String strSubstring = str.substring(0, i);
        String strSubstring2 = str.substring(i);
        PathTree<T> orCreate2 = getOrCreate(strSubstring);
        if (strSubstring2.length() != 0) {
            return orCreate2.add(strSubstring2, t);
        }
        orCreate2.setItemNonNull(t);
        return orCreate2;
    }

    @Override // java.lang.Comparable
    public int compareTo(PathTree<?> pathTree) {
        boolean zIsDirectory = isDirectory();
        if (zIsDirectory != pathTree.isDirectory()) {
            return zIsDirectory ? -1 : 1;
        }
        String name = getName();
        String name2 = pathTree.getName();
        if (zIsDirectory) {
            name = name.substring(0, name.length() - 1);
            name2 = name2.substring(0, name2.length() - 1);
        }
        return StringsUtil.toLowercase(name).compareTo(StringsUtil.toLowercase(name2));
    }

    public boolean contains(String str) {
        return this.elementsMap.containsKey(str);
    }

    public PathTree<T> copy(String str) {
        PathTree<T> pathTree = new PathTree<>(getItem(), str);
        pathTree.setItem(getItem());
        Iterator<PathTree<T>> it = iterator();
        while (it.hasNext()) {
            pathTree.add(it.next().copy());
        }
        return pathTree;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PathTree) {
            return Objects.equals(getName(), ((PathTree) obj).getName());
        }
        return false;
    }

    public PathTree<T> find(String str) {
        if (str.equals(getName())) {
            return this;
        }
        int iIndexOf = str.indexOf(47);
        if (iIndexOf < 0) {
            if (str.length() > 0) {
                return get(str);
            }
            return null;
        }
        int i = iIndexOf + 1;
        String strSubstring = str.substring(0, i);
        String strSubstring2 = str.substring(i);
        PathTree<T> pathTree = get(strSubstring);
        return (pathTree == null || strSubstring2.length() == 0) ? pathTree : pathTree.find(strSubstring2);
    }

    public void fromJson(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(NAME_elements);
        if (jSONArrayOptJSONArray == null) {
            return;
        }
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
            getOrCreate(getName(jSONObject2.optString(NAME_path, ""))).fromJson(jSONObject2);
        }
    }

    public PathTree<T> get(String str) {
        return this.elementsMap.get(str);
    }

    public int getDepth() {
        int i = 0;
        while (this != null && !this.isRoot()) {
            i++;
            this = this.getParent();
        }
        return i;
    }

    public Iterator<T> getFileItems() {
        return ComputeIterator.of(getFiles(), new Function() { // from class: uza
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((PathTree) obj).getItem();
            }
        });
    }

    public Iterator<PathTree<T>> getFiles() {
        return new MergingIterator(ComputeIterator.of(iterator(), new Function() { // from class: tza
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PathTree.a((PathTree) obj);
            }
        }));
    }

    public T getItem() {
        return this.item;
    }

    public PathTree<T> getOrCreate(String str) {
        PathTree<T> pathTree = get(str);
        if (pathTree != null) {
            return pathTree;
        }
        PathTree<T> pathTree2 = new PathTree<>(str);
        add(pathTree2);
        return pathTree2;
    }

    public PathTree<T> getParent() {
        return this.parent;
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        for (PathTree<?> pathTree : getParentElements()) {
            sb.append(pathTree.getName());
        }
        return sb.toString();
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    public boolean isFile() {
        return !isDirectory();
    }

    public boolean isRoot() {
        return isDirectory() && getParent() == null;
    }

    @Override // java.lang.Iterable
    public Iterator<PathTree<T>> iterator() {
        return this.elementsMap.values().iterator();
    }

    public PathTree<T> remove(String str) {
        return this.elementsMap.remove(str);
    }

    public void setItem(T t) {
        this.item = t;
    }

    public void setName(String str) {
        String str2 = this.name;
        if (str2.equals(str)) {
            return;
        }
        this.name = str;
        PathTree<T> pathTree = this.parent;
        if (pathTree != null) {
            pathTree.elementsMap.remove(str2);
            pathTree.elementsMap.put(str, this);
        }
    }

    public void setParent(PathTree<T> pathTree) {
        if (pathTree != this) {
            this.parent = pathTree;
        }
    }

    public int size() {
        return this.elementsMap.size();
    }

    public void sort(Comparator<? super PathTree<?>> comparator, boolean z) {
        LinkedHashMap<String, PathTree<T>> linkedHashMap = this.elementsMap;
        if (linkedHashMap.size() == 0) {
            return;
        }
        PathTree<T>[] pathTreeArr = (PathTree[]) linkedHashMap.values().toArray(new PathTree[0]);
        Arrays.sort(pathTreeArr, comparator);
        linkedHashMap.clear();
        for (PathTree<T> pathTree : pathTreeArr) {
            linkedHashMap.put(pathTree.getName(), pathTree);
        }
        if (z) {
            for (PathTree<T> pathTree2 : pathTreeArr) {
                pathTree2.sort(comparator, true);
            }
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NAME_path, getPath());
        JSONArray jSONArray = new JSONArray();
        Iterator<PathTree<T>> it = iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJson());
        }
        if (!jSONArray.isEmpty()) {
            jSONObject.put(NAME_elements, jSONArray);
        }
        return jSONObject;
    }

    public List<PathTree<T>> toList() {
        return ArrayCollection.of(this.elementsMap.values());
    }

    public String toString() {
        return getPath();
    }

    public PathTree(String str) {
        this(null, str);
    }

    public PathTree<T> copy() {
        return copy(getName());
    }

    public String getName() {
        return this.name;
    }

    public boolean add(PathTree<T> pathTree) {
        if (pathTree == null) {
            return false;
        }
        pathTree.setParent(this);
        this.elementsMap.put(pathTree.getName(), pathTree);
        return true;
    }

    public void sort(boolean z) {
        sort(CompareUtil.getComparableComparator(), z);
    }

    public void sort() {
        sort(true);
    }
}
