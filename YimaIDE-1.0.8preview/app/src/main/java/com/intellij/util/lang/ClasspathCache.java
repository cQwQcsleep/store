package com.intellij.util.lang;

import com.intellij.util.lang.ClasspathCache;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ClasspathCache {
    private static final IntFunction<Loader[][]> ARRAY_FACTORY = new IntFunction() { // from class: oy1
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            return ClasspathCache.b(i);
        }
    };
    private static final LongFunction<Loader[]> NULL = new LongFunction() { // from class: py1
        @Override // java.util.function.LongFunction
        public final Object apply(long j) {
            return ClasspathCache.a(j);
        }
    };
    private StrippedLongToObjectMap<Loader[]> classPackageCache;
    private volatile LongFunction<Loader[]> classPackageCacheGetter;
    private StrippedLongToObjectMap<Loader[]> resourcePackageCache;
    private volatile LongFunction<Loader[]> resourcePackageCacheGetter;

    public interface IndexRegistrar {
        int classPackageCount();

        long[] classPackages();

        default LongPredicate getKeyFilter(boolean z) {
            return null;
        }

        default Predicate<String> getNameFilter() {
            return null;
        }

        int resourcePackageCount();

        long[] resourcePackages();
    }

    public static final class LoaderDataBuilder implements IndexRegistrar {
        final StrippedLongSet classPackageHashes = new StrippedLongSet();
        final StrippedLongSet resourcePackageHashes = new StrippedLongSet();

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            objArr[0] = "path";
            objArr[1] = "com/intellij/util/lang/ClasspathCache$LoaderDataBuilder";
            if (i == 1) {
                objArr[2] = "addPackageFromName";
            } else if (i != 2) {
                objArr[2] = "addResourcePackage";
            } else {
                objArr[2] = "addClassPackage";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public void addClassPackage(String str) {
            if (str == null) {
                $$$reportNull$$$0(2);
            }
            this.classPackageHashes.add(ClasspathCache.getPackageNameHash(str, str.length()));
        }

        public void addResourcePackage(String str) {
            if (str == null) {
                $$$reportNull$$$0(0);
            }
            this.resourcePackageHashes.add(ClasspathCache.getPackageNameHash(str, str.length()));
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public int classPackageCount() {
            return this.classPackageHashes.size();
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public long[] classPackages() {
            return this.classPackageHashes.keys;
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public LongPredicate getKeyFilter(boolean z) {
            return new LongPredicate(z) { // from class: com.intellij.util.lang.ClasspathCache.LoaderDataBuilder.1
                boolean addZero;
                final /* synthetic */ boolean val$forClasses;

                {
                    this.val$forClasses = z;
                    this.addZero = (z ? LoaderDataBuilder.this.classPackageHashes : LoaderDataBuilder.this.resourcePackageHashes).hasNull();
                }

                @Override // java.util.function.LongPredicate
                public boolean test(long j) {
                    if (j != 0) {
                        return true;
                    }
                    if (!this.addZero) {
                        return false;
                    }
                    this.addZero = false;
                    return true;
                }
            };
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public int resourcePackageCount() {
            return this.resourcePackageHashes.size();
        }

        @Override // com.intellij.util.lang.ClasspathCache.IndexRegistrar
        public long[] resourcePackages() {
            return this.resourcePackageHashes.keys;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "loader";
        } else if (i == 2) {
            objArr[0] = "path";
        } else if (i == 3 || i == 4) {
            objArr[0] = "resourcePath";
        } else {
            objArr[0] = "registrar";
        }
        objArr[1] = "com/intellij/util/lang/ClasspathCache";
        if (i == 2) {
            objArr[2] = "getLoadersByName";
        } else if (i == 3) {
            objArr[2] = "getLoadersByResourcePackageDir";
        } else if (i != 4) {
            objArr[2] = "applyLoaderData";
        } else {
            objArr[2] = "getPackageNameHash";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static /* synthetic */ Loader[] a(long j) {
        return null;
    }

    private static void addPackages(long[] jArr, StrippedLongToObjectMap<Loader[]> strippedLongToObjectMap, Loader loader, LongPredicate longPredicate) {
        Loader[] loaderArr = null;
        for (long j : jArr) {
            if (longPredicate == null || longPredicate.test(j)) {
                int iIndex = strippedLongToObjectMap.index(j);
                if (iIndex < 0) {
                    if (loaderArr == null) {
                        loaderArr = new Loader[]{loader};
                    }
                    strippedLongToObjectMap.addByIndex(iIndex, j, loaderArr);
                } else {
                    Loader[] byIndex = strippedLongToObjectMap.getByIndex(iIndex);
                    Loader[] loaderArr2 = new Loader[byIndex.length + 1];
                    System.arraycopy(byIndex, 0, loaderArr2, 0, byIndex.length);
                    loaderArr2[byIndex.length] = loader;
                    strippedLongToObjectMap.replaceByIndex(iIndex, j, loaderArr2);
                }
            }
        }
    }

    public static /* synthetic */ Loader[][] b(int i) {
        return new Loader[i][];
    }

    public static long getPackageNameHash(String str, int i) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        if (i <= 0) {
            return 0L;
        }
        return Xxh3Impl.hash(str, CharSequenceAccess.INSTANCE, 0, i * 2, 0L);
    }

    public void applyLoaderData(IndexRegistrar indexRegistrar, Loader loader) {
        if (indexRegistrar == null) {
            $$$reportNull$$$0(0);
        }
        if (loader == null) {
            $$$reportNull$$$0(1);
        }
        if (indexRegistrar.classPackageCount() != 0) {
            StrippedLongToObjectMap<Loader[]> strippedLongToObjectMap = this.classPackageCache;
            StrippedLongToObjectMap<Loader[]> strippedLongToObjectMap2 = strippedLongToObjectMap == null ? new StrippedLongToObjectMap<>(ARRAY_FACTORY, indexRegistrar.classPackageCount()) : new StrippedLongToObjectMap<>(strippedLongToObjectMap);
            addPackages(indexRegistrar.classPackages(), strippedLongToObjectMap2, loader, indexRegistrar.getKeyFilter(true));
            this.classPackageCache = strippedLongToObjectMap2;
            this.classPackageCacheGetter = strippedLongToObjectMap2;
        }
        if (indexRegistrar.resourcePackageCount() != 0) {
            StrippedLongToObjectMap<Loader[]> strippedLongToObjectMap3 = this.resourcePackageCache;
            StrippedLongToObjectMap<Loader[]> strippedLongToObjectMap4 = strippedLongToObjectMap3 == null ? new StrippedLongToObjectMap<>(ARRAY_FACTORY, indexRegistrar.resourcePackageCount()) : new StrippedLongToObjectMap<>(strippedLongToObjectMap3);
            addPackages(indexRegistrar.resourcePackages(), strippedLongToObjectMap4, loader, indexRegistrar.getKeyFilter(false));
            this.resourcePackageCache = strippedLongToObjectMap4;
            this.resourcePackageCacheGetter = strippedLongToObjectMap4;
        }
    }

    public Loader[] getClassLoadersByPackageNameHash(long j) {
        return this.classPackageCacheGetter.apply(j);
    }

    public Loader[] getLoadersByName(String str) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        return (str.endsWith(".class") ? this.classPackageCacheGetter : this.resourcePackageCacheGetter).apply(getPackageNameHash(str, str.lastIndexOf(47)));
    }
}
