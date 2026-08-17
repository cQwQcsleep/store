package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.config.LanguageVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB)\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/FragmentContext;", "", "fileToFragment", "", "", "leafFragments", "", "<init>", "(Ljava/util/Map;Ljava/util/Set;)V", "dirtySetTouchesNonLeafFragments", "", "dirtySet", "", "Ljava/io/File;", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FragmentContext {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, String> fileToFragment;
    private final Set<String> leafFragments;

    public FragmentContext(Map<String, String> map, Set<String> set) {
        map.getClass();
        set.getClass();
        this.fileToFragment = map;
        this.leafFragments = set;
    }

    public final boolean dirtySetTouchesNonLeafFragments(Iterable<? extends File> dirtySet) {
        dirtySet.getClass();
        if ((dirtySet instanceof Collection) && ((Collection) dirtySet).isEmpty()) {
            return false;
        }
        Iterator<? extends File> it = dirtySet.iterator();
        while (it.hasNext()) {
            if (!CollectionsKt.contains(this.leafFragments, this.fileToFragment.get(it.next().getAbsolutePath()))) {
                return true;
            }
        }
        return false;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/FragmentContext$Companion;", "", "<init>", "()V", "canCreateFragmentContext", "", "args", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "fromCompilerArguments", "Lorg/jetbrains/kotlin/incremental/FragmentContext;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final boolean canCreateFragmentContext(CommonCompilerArguments args) {
            boolean z;
            LanguageVersion languageVersionFromVersionString = LanguageVersion.Companion.fromVersionString(args.getLanguageVersion());
            if (languageVersionFromVersionString == null) {
                languageVersionFromVersionString = LanguageVersion.LATEST_STABLE;
            }
            boolean usesK2 = languageVersionFromVersionString.getUsesK2();
            List listListOf = CollectionsKt.listOf(new String[][]{args.getFragments(), args.getFragmentRefines(), args.getFragmentSources()});
            if ((listListOf instanceof Collection) && listListOf.isEmpty()) {
                z = true;
            } else {
                Iterator it = listListOf.iterator();
                while (true) {
                    if (it.hasNext()) {
                        String[] strArr = (String[]) it.next();
                        if (strArr == null || strArr.length == 0) {
                            z = false;
                        }
                    } else {
                        z = true;
                    }
                }
            }
            return usesK2 && z;
        }

        public final FragmentContext fromCompilerArguments(CommonCompilerArguments args) {
            args.getClass();
            if (!canCreateFragmentContext(args)) {
                return null;
            }
            String[] fragmentSources = args.getFragmentSources();
            fragmentSources.getClass();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(fragmentSources.length), 16));
            for (String str : fragmentSources) {
                List listSplit$default = StringsKt.split$default(str, new String[]{":"}, false, 2, 2, (Object) null);
                Pair pair = new Pair(CollectionsKt.last(listSplit$default), CollectionsKt.first(listSplit$default));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            String[] fragmentRefines = args.getFragmentRefines();
            fragmentRefines.getClass();
            ArrayList arrayList = new ArrayList(fragmentRefines.length);
            for (String str2 : fragmentRefines) {
                arrayList.add((String) CollectionsKt.last(StringsKt.split$default(str2, new String[]{":"}, false, 2, 2, (Object) null)));
            }
            Set set = CollectionsKt.toSet(arrayList);
            String[] fragments = args.getFragments();
            fragments.getClass();
            return new FragmentContext(linkedHashMap, SetsKt.minus(ArraysKt.toSet(fragments), set));
        }

        private Companion() {
        }
    }
}
