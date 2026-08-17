package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.inline.RangeMapping;
import org.jetbrains.kotlin.codegen.inline.SMAP;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SMAP;", Argument.Delimiters.none, "fileMappings", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/FileMapping;", "<init>", "(Ljava/util/List;)V", "getFileMappings", "()Ljava/util/List;", "intervals", "Lorg/jetbrains/kotlin/codegen/inline/RangeMapping;", "findRange", "lineNumber", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SMAP {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String END = "*E";
    public static final String FILE_SECTION = "*F";
    public static final String LINE_SECTION = "*L";
    public static final String STRATA_SECTION = "*S";
    private final List<FileMapping> fileMappings;
    private final List<RangeMapping> intervals;

    public SMAP(List<FileMapping> list) {
        list.getClass();
        this.fileMappings = list;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((FileMapping) it.next()).getLineMappings());
        }
        this.intervals = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.codegen.inline.SMAP$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((RangeMapping) t).getDest()), Integer.valueOf(((RangeMapping) t2).getDest()));
            }
        });
    }

    public static int a(int i, RangeMapping rangeMapping) {
        rangeMapping.getClass();
        if (rangeMapping.contains(i)) {
            return 0;
        }
        return rangeMapping.getDest() - i;
    }

    public final RangeMapping findRange(final int lineNumber) {
        int iBinarySearch$default = CollectionsKt.binarySearch$default(this.intervals, 0, 0, new Function1() { // from class: xnc
            public final Object invoke(Object obj) {
                return Integer.valueOf(SMAP.a(lineNumber, (RangeMapping) obj));
            }
        }, 3, (Object) null);
        if (iBinarySearch$default < 0) {
            return null;
        }
        return this.intervals.get(iBinarySearch$default);
    }

    public final List<FileMapping> getFileMappings() {
        return this.fileMappings;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SMAP$Companion;", Argument.Delimiters.none, "<init>", "()V", "FILE_SECTION", Argument.Delimiters.none, "LINE_SECTION", "STRATA_SECTION", "END", "identityMapping", "Lorg/jetbrains/kotlin/codegen/inline/SMAP;", ModuleXmlParser.NAME, ModuleXmlParser.PATH, "methods", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SMAP identityMapping(String name, String path, Collection<? extends MethodNode> methods) {
            path.getClass();
            methods.getClass();
            if (name == null || name.length() == 0) {
                return new SMAP(CollectionsKt.emptyList());
            }
            Iterator<? extends MethodNode> it = methods.iterator();
            int iMax = 0;
            int iMin = 0;
            while (it.hasNext()) {
                InsnList insnList = it.next().instructions;
                insnList.getClass();
                Iterator it2 = InsnSequenceKt.asSequence(insnList).iterator();
                while (it2.hasNext()) {
                    LineNumberNode lineNumberNode = (AbstractInsnNode) it2.next();
                    if (lineNumberNode instanceof LineNumberNode) {
                        LineNumberNode lineNumberNode2 = lineNumberNode;
                        iMin = Math.min(iMin, lineNumberNode2.line);
                        iMax = Math.max(iMax, lineNumberNode2.line + 1);
                    }
                }
            }
            if (iMin >= iMax) {
                return new SMAP(CollectionsKt.emptyList());
            }
            FileMapping fileMapping = new FileMapping(name, path);
            FileMapping.mapNewInterval$default(fileMapping, iMin, iMin, iMax - iMin, null, 8, null);
            return new SMAP(CollectionsKt.listOf(fileMapping));
        }

        private Companion() {
        }
    }
}
