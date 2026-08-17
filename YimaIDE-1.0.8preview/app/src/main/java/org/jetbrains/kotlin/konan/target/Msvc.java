package org.jetbrains.kotlin.konan.target;

import java.nio.file.Path;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Msvc;", "", "()V", "compilerFlags", "", "", "CustomPath", "DefaultPath", "Lorg/jetbrains/kotlin/konan/target/Msvc$CustomPath;", "Lorg/jetbrains/kotlin/konan/target/Msvc$DefaultPath;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Msvc {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Msvc$CustomPath;", "Lorg/jetbrains/kotlin/konan/target/Msvc;", "msvcParts", "Ljava/nio/file/Path;", "(Ljava/nio/file/Path;)V", "compilerFlags", "", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class CustomPath extends Msvc {
        private final Path msvcParts;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CustomPath(Path path) {
            super(null);
            path.getClass();
            this.msvcParts = path;
        }

        @Override // org.jetbrains.kotlin.konan.target.Msvc
        public List<String> compilerFlags() {
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            String string = this.msvcParts.toAbsolutePath().toString();
            listCreateListBuilder.addAll(CollectionsKt.listOf(new String[]{"-Xmicrosoft-visualc-tools-root", string}));
            listCreateListBuilder.add("-Wl,-vctoolsdir:" + string);
            return CollectionsKt.build(listCreateListBuilder);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/Msvc$DefaultPath;", "Lorg/jetbrains/kotlin/konan/target/Msvc;", "()V", "compilerFlags", "", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultPath extends Msvc {
        public static final DefaultPath INSTANCE = new DefaultPath();

        private DefaultPath() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.konan.target.Msvc
        public List<String> compilerFlags() {
            return CollectionsKt.emptyList();
        }
    }

    public /* synthetic */ Msvc(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract List<String> compilerFlags();

    private Msvc() {
    }
}
