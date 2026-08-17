package org.jetbrains.kotlin.ir.backend.js.ic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.konan.library.NativeLibraryConstantsKt;
import org.jetbrains.kotlin.protobuf.CodedInputStream;
import org.jetbrains.kotlin.protobuf.CodedOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\n\u0010\u0010\u001a\u00020\u0005H\u0096\u0080\u0004J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/KotlinSourceFile;", "", "path", "", "id", "", "<init>", "(Ljava/lang/String;I)V", "getPath", "()Ljava/lang/String;", "getId", "()I", "toProtoStream", "", "out", "Lorg/jetbrains/kotlin/protobuf/CodedOutputStream;", "hashCode", "equals", "", "other", "toString", "Companion", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KotlinSourceFile {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int id;
    private final String path;

    private KotlinSourceFile(String str, int i) {
        this.path = str;
        this.id = i;
    }

    public boolean equals(Object other) {
        if (!(other instanceof KotlinSourceFile)) {
            return false;
        }
        KotlinSourceFile kotlinSourceFile = (KotlinSourceFile) other;
        return Intrinsics.areEqual(kotlinSourceFile.path, this.path) && kotlinSourceFile.id == this.id;
    }

    public final int getId() {
        return this.id;
    }

    public final String getPath() {
        return this.path;
    }

    public int hashCode() {
        return this.id ^ this.path.hashCode();
    }

    public final void toProtoStream(CodedOutputStream out) throws IOException {
        out.getClass();
        out.writeStringNoTag(this.path);
        out.writeInt32NoTag(this.id);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(new File(this.path).getName());
        if (this.id != 0) {
            str = "." + this.id;
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/KotlinSourceFile$Companion;", "", "<init>", "()V", "fromProtoStream", "Lorg/jetbrains/kotlin/ir/backend/js/ic/KotlinSourceFile;", "input", "Lorg/jetbrains/kotlin/protobuf/CodedInputStream;", "fromSources", "", NativeLibraryConstantsKt.KONAN_DISTRIBUTION_SOURCES_DIR, "", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KotlinSourceFile fromProtoStream(CodedInputStream input) throws IOException {
            input.getClass();
            String string = input.readString();
            int int32 = input.readInt32();
            string.getClass();
            return new KotlinSourceFile(string, int32, null);
        }

        public final List<KotlinSourceFile> fromSources(List<String> sources) {
            sources.getClass();
            HashMap map = new HashMap();
            List<String> list = sources;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (String str : list) {
                Integer num = (Integer) map.get(str);
                int iIntValue = num != null ? num.intValue() : 0;
                map.put(str, Integer.valueOf(iIntValue + 1));
                arrayList.add(new KotlinSourceFile(str, iIntValue, null));
            }
            return arrayList;
        }

        private Companion() {
        }
    }

    public /* synthetic */ KotlinSourceFile(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i);
    }
}
