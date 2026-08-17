package org.jetbrains.kotlin.backend.konan.serialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u000b\f\r\u000eB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H&\u0082\u0001\u0004\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "contains", "", "filePath", "", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "fileName", "Nothing", "WholeModule", "SingleFile", "MultipleFiles", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$MultipleFiles;", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$Nothing;", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$SingleFile;", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$WholeModule;", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class CacheDeserializationStrategy {
    public /* synthetic */ CacheDeserializationStrategy(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean contains(String filePath);

    public abstract boolean contains(FqName fqName, String fileName);

    private CacheDeserializationStrategy() {
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$Nothing;", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "contains", "", "filePath", "", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "fileName", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Nothing extends CacheDeserializationStrategy {
        public static final Nothing INSTANCE = new Nothing();

        private Nothing() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(FqName fqName, String fileName) {
            fqName.getClass();
            fileName.getClass();
            return false;
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(String filePath) {
            filePath.getClass();
            return false;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$WholeModule;", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "contains", "", "filePath", "", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "fileName", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class WholeModule extends CacheDeserializationStrategy {
        public static final WholeModule INSTANCE = new WholeModule();

        private WholeModule() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(FqName fqName, String fileName) {
            fqName.getClass();
            fileName.getClass();
            return true;
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(String filePath) {
            filePath.getClass();
            return true;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$MultipleFiles;", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;", "filePaths", "", "", "fqNames", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;Ljava/util/List;)V", "", "fqNamesWithNames", "Lkotlin/Pair;", "contains", "", "filePath", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "fileName", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class MultipleFiles extends CacheDeserializationStrategy {
        private final Set<String> filePaths;
        private final Set<Pair<String, String>> fqNamesWithNames;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MultipleFiles(List<String> list, List<String> list2) {
            super(null);
            list.getClass();
            list2.getClass();
            this.filePaths = CollectionsKt.toSet(list);
            List<String> list3 = list2;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            int i = 0;
            for (Object obj : list3) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(new Pair((String) obj, new File(list.get(i)).getName()));
                i = i2;
            }
            this.fqNamesWithNames = CollectionsKt.toSet(arrayList);
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(FqName fqName, String fileName) {
            fqName.getClass();
            fileName.getClass();
            return this.fqNamesWithNames.contains(new Pair(fqName.asString(), fileName));
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(String filePath) {
            filePath.getClass();
            return this.filePaths.contains(filePath);
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy$SingleFile;", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;", "filePath", "", "fqName", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;)V", "getFilePath", "()Ljava/lang/String;", "getFqName", "contains", "", "Lorg/jetbrains/kotlin/name/FqName;", "fileName", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SingleFile extends CacheDeserializationStrategy {
        private final String filePath;
        private final String fqName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SingleFile(String str, String str2) {
            super(null);
            str.getClass();
            str2.getClass();
            this.filePath = str;
            this.fqName = str2;
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(FqName fqName, String fileName) {
            fqName.getClass();
            fileName.getClass();
            return Intrinsics.areEqual(fqName.asString(), this.fqName) && Intrinsics.areEqual(new File(this.filePath).getName(), fileName);
        }

        public final String getFilePath() {
            return this.filePath;
        }

        public final String getFqName() {
            return this.fqName;
        }

        @Override // org.jetbrains.kotlin.backend.konan.serialization.CacheDeserializationStrategy
        public boolean contains(String filePath) {
            filePath.getClass();
            return Intrinsics.areEqual(filePath, this.filePath);
        }
    }
}
