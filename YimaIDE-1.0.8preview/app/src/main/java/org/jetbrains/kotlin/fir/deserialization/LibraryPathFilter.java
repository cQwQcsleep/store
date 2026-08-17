package org.jetbrains.kotlin.fir.deserialization;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.deserialization.LibraryPathFilter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0002\b\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/LibraryPathFilter;", Argument.Delimiters.none, "<init>", "()V", "accepts", Argument.Delimiters.none, ModuleXmlParser.PATH, "Ljava/nio/file/Path;", "TakeAll", "LibraryList", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class LibraryPathFilter {

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f²\u0006\u0012\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\u00040\u0004X\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/LibraryPathFilter$LibraryList;", "Lorg/jetbrains/kotlin/fir/deserialization/LibraryPathFilter;", "libs", Argument.Delimiters.none, "Ljava/nio/file/Path;", "<init>", "(Ljava/util/Set;)V", "getLibs", "()Ljava/util/Set;", "accepts", Argument.Delimiters.none, ModuleXmlParser.PATH, "org.jetbrains.kotlin:fir-deserialization", "absolutePath", "kotlin.jvm.PlatformType"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class LibraryList extends LibraryPathFilter {
        private final Set<Path> libs;

        public LibraryList(Set<? extends Path> set) {
            set.getClass();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((Path) it.next()).normalize());
            }
            this.libs = linkedHashSet;
        }

        public static Path a(Path path) {
            return path.toAbsolutePath().normalize();
        }

        private static final Path accepts$lambda$1(Lazy<? extends Path> lazy) {
            return (Path) lazy.getValue();
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.LibraryPathFilter
        public boolean accepts(final Path path) {
            if (path == null) {
                return false;
            }
            boolean zIsAbsolute = path.isAbsolute();
            Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: az8
                public final Object invoke() {
                    return LibraryPathFilter.LibraryList.a(path);
                }
            });
            Set<Path> set = this.libs;
            if ((set instanceof Collection) && set.isEmpty()) {
                return false;
            }
            for (Path path2 : set) {
                if ((!path2.isAbsolute() || zIsAbsolute) ? (path2.isAbsolute() || !zIsAbsolute) ? path.startsWith(path2) : accepts$lambda$1(lazy).startsWith(path2.toAbsolutePath().normalize()) : accepts$lambda$1(lazy).startsWith(path2.normalize())) {
                    return true;
                }
            }
            return false;
        }

        public final Set<Path> getLibs() {
            return this.libs;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/LibraryPathFilter$TakeAll;", "Lorg/jetbrains/kotlin/fir/deserialization/LibraryPathFilter;", "<init>", "()V", "accepts", Argument.Delimiters.none, ModuleXmlParser.PATH, "Ljava/nio/file/Path;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TakeAll extends LibraryPathFilter {
        public static final TakeAll INSTANCE = new TakeAll();

        private TakeAll() {
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.LibraryPathFilter
        public boolean accepts(Path path) {
            return true;
        }
    }

    public abstract boolean accepts(Path path);
}
