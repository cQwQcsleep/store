package com.shadow.okio.internal;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.collections.ArrayDeque;
import com.shadow.kotlin.collections.EmptyList;
import com.shadow.kotlin.coroutines.intrinsics.CoroutineSingletons;
import com.shadow.kotlin.coroutines.jvm.internal.ContinuationImpl;
import com.shadow.kotlin.coroutines.jvm.internal.DebugMetadata;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;
import com.shadow.okio.BufferedSink;
import com.shadow.okio.FileMetadata;
import com.shadow.okio.Okio;
import com.shadow.okio.Path;
import com.shadow.okio.Source;
import core.pro.android.notify.h;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;

/* renamed from: com.shadow.okio.internal.-FileSystem, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class FileSystem {

    @DebugMetadata(c = "com.shadow.okio.internal.-FileSystem", f = "FileSystem.kt", l = {116, 135, 145}, m = "collectRecursively")
    /* renamed from: com.shadow.okio.internal.-FileSystem$collectRecursively$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // com.shadow.kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FileSystem.collectRecursively(null, null, null, null, false, false, this);
        }
    }

    @DebugMetadata(c = "com.shadow.okio.internal.-FileSystem$commonListRecursively$1", f = "FileSystem.kt", l = {96}, m = "invokeSuspend")
    /* renamed from: com.shadow.okio.internal.-FileSystem$commonListRecursively$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00601 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Path $dir;
        final /* synthetic */ boolean $followSymlinks;
        final /* synthetic */ com.shadow.okio.FileSystem $this_commonListRecursively;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00601(Path path, com.shadow.okio.FileSystem fileSystem, boolean z, Continuation<? super C00601> continuation) {
            super(2, continuation);
            this.$dir = path;
            this.$this_commonListRecursively = fileSystem;
            this.$followSymlinks = z;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C00601 c00601 = new C00601(this.$dir, this.$this_commonListRecursively, this.$followSymlinks, continuation);
            c00601.L$0 = obj;
            return c00601;
        }

        public final Object invokeSuspend(Object obj) throws Throwable {
            com.shadow.kotlin.sequences.SequenceScope sequenceScope;
            ArrayDeque arrayDeque;
            Iterator<Path> it;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                LazyKt.throwOnFailure(obj);
                com.shadow.kotlin.sequences.SequenceScope sequenceScope2 = (com.shadow.kotlin.sequences.SequenceScope) this.L$0;
                ArrayDeque arrayDeque2 = new ArrayDeque();
                arrayDeque2.addLast(this.$dir);
                sequenceScope = sequenceScope2;
                arrayDeque = arrayDeque2;
                it = this.$this_commonListRecursively.list(this.$dir).iterator();
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (Iterator) this.L$2;
                ArrayDeque arrayDeque3 = (ArrayDeque) this.L$1;
                com.shadow.kotlin.sequences.SequenceScope sequenceScope3 = (com.shadow.kotlin.sequences.SequenceScope) this.L$0;
                LazyKt.throwOnFailure(obj);
                arrayDeque = arrayDeque3;
                sequenceScope = sequenceScope3;
            }
            while (it.hasNext()) {
                Path next = it.next();
                com.shadow.okio.FileSystem fileSystem = this.$this_commonListRecursively;
                boolean z = this.$followSymlinks;
                this.L$0 = sequenceScope;
                this.L$1 = arrayDeque;
                this.L$2 = it;
                this.label = 1;
                if (FileSystem.collectRecursively(sequenceScope, fileSystem, arrayDeque, next, z, false, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            return com.shadow.kotlin.Unit.INSTANCE;
        }

        public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(com.shadow.kotlin.Unit.INSTANCE);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d1, code lost:
    
        if (r0 != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d3, code lost:
    
        if (r12 != 0) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d5, code lost:
    
        r6.addLast(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00dc, code lost:
    
        r12 = r11;
        r11 = r3;
        r15 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r8.iterator();
        r8 = r6;
        r6 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x011c, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x011d, code lost:
    
        r8 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object collectRecursively(SequenceScope<? super Path> sequenceScope, com.shadow.okio.FileSystem fileSystem, kotlin.collections.ArrayDeque<Path> arrayDeque, Path path, boolean z, boolean z2, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        com.shadow.okio.FileSystem fileSystem2;
        kotlin.collections.ArrayDeque<Path> arrayDeque2;
        boolean z3;
        SequenceScope<? super Path> sequenceScope2;
        boolean z4;
        Path path2 = path;
        boolean z5 = z2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i2 = anonymousClass1.label;
        com.shadow.kotlin.Unit unit = com.shadow.kotlin.Unit.INSTANCE;
        if (i2 == 0) {
            LazyKt.throwOnFailure(obj);
            if (z5) {
                fileSystem2 = fileSystem;
                arrayDeque2 = arrayDeque;
                z3 = z;
            } else {
                anonymousClass1.L$0 = sequenceScope;
                fileSystem2 = fileSystem;
                anonymousClass1.L$1 = fileSystem2;
                arrayDeque2 = arrayDeque;
                anonymousClass1.L$2 = arrayDeque2;
                anonymousClass1.L$3 = path2;
                z3 = z;
                anonymousClass1.Z$0 = z3;
                anonymousClass1.Z$1 = z5;
                anonymousClass1.label = 1;
                if (sequenceScope.yield(path2, anonymousClass1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            boolean z6 = z3;
            sequenceScope2 = sequenceScope;
            z4 = z6;
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    LazyKt.throwOnFailure(obj);
                    return unit;
                }
                boolean z7 = anonymousClass1.Z$1;
                boolean z8 = anonymousClass1.Z$0;
                Iterator<Path> it = (Iterator) anonymousClass1.L$4;
                Path path3 = (Path) anonymousClass1.L$3;
                kotlin.collections.ArrayDeque<Path> arrayDeque3 = (ArrayDeque) anonymousClass1.L$2;
                com.shadow.okio.FileSystem fileSystem3 = (com.shadow.okio.FileSystem) anonymousClass1.L$1;
                SequenceScope<? super Path> sequenceScope3 = (com.shadow.kotlin.sequences.SequenceScope) anonymousClass1.L$0;
                try {
                    LazyKt.throwOnFailure(obj);
                    while (it.hasNext()) {
                        Path next = it.next();
                        anonymousClass1.L$0 = sequenceScope3;
                        anonymousClass1.L$1 = fileSystem3;
                        anonymousClass1.L$2 = arrayDeque3;
                        anonymousClass1.L$3 = path3;
                        anonymousClass1.L$4 = it;
                        anonymousClass1.Z$0 = z8;
                        anonymousClass1.Z$1 = z7;
                        anonymousClass1.label = 2;
                        if (collectRecursively(sequenceScope3, fileSystem3, arrayDeque3, next, z8, z7, anonymousClass1) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    }
                    arrayDeque3.removeLast();
                    z5 = z7;
                    path2 = path3;
                    sequenceScope2 = sequenceScope3;
                    if (z5) {
                        anonymousClass1.L$0 = null;
                        anonymousClass1.L$1 = null;
                        anonymousClass1.L$2 = null;
                        anonymousClass1.L$3 = null;
                        anonymousClass1.L$4 = null;
                        anonymousClass1.label = 3;
                        if (sequenceScope2.yield(path2, anonymousClass1) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    }
                    return unit;
                } catch (Throwable th) {
                    th = th;
                    arrayDeque3.removeLast();
                    throw th;
                }
            }
            boolean z9 = anonymousClass1.Z$1;
            boolean z10 = anonymousClass1.Z$0;
            Path path4 = (Path) anonymousClass1.L$3;
            arrayDeque2 = (ArrayDeque) anonymousClass1.L$2;
            com.shadow.okio.FileSystem fileSystem4 = (com.shadow.okio.FileSystem) anonymousClass1.L$1;
            sequenceScope2 = (com.shadow.kotlin.sequences.SequenceScope) anonymousClass1.L$0;
            LazyKt.throwOnFailure(obj);
            fileSystem2 = fileSystem4;
            z5 = z9;
            z4 = z10;
            path2 = path4;
        }
        List<Path> listListOrNull = fileSystem2.listOrNull(path2);
        if (listListOrNull == null) {
            listListOrNull = EmptyList.INSTANCE;
        }
        if (!listListOrNull.isEmpty()) {
            int i3 = 0;
            Path path5 = path2;
            while (true) {
                if (z4 && arrayDeque2.contains(path5)) {
                    throw new IOException(h.b(path2, "symlink cycle at "));
                }
                Path pathSymlinkTarget = symlinkTarget(fileSystem2, path5);
                if (pathSymlinkTarget == null) {
                    break;
                }
                i3++;
                path5 = pathSymlinkTarget;
            }
        }
        if (z5) {
        }
        return unit;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0040 A[Catch: all -> 0x003a, TRY_LEAVE, TryCatch #4 {all -> 0x003a, blocks: (B:3:0x0013, B:22:0x0040, B:28:0x004b, B:17:0x0036, B:14:0x0031, B:5:0x001c), top: B:46:0x0013, inners: #1, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b A[Catch: all -> 0x003a, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x003a, blocks: (B:3:0x0013, B:22:0x0040, B:28:0x004b, B:17:0x0036, B:14:0x0031, B:5:0x001c), top: B:46:0x0013, inners: #1, #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void commonCopy(com.shadow.okio.FileSystem fileSystem, Path path, Path path2) throws IOException {
        Throwable th;
        Throwable th2;
        Long lValueOf;
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        Source source = fileSystem.source(path);
        try {
            BufferedSink bufferedSinkBuffer = Okio.buffer(fileSystem.sink(path2));
            th = null;
            try {
                lValueOf = Long.valueOf(bufferedSinkBuffer.writeAll(source));
            } catch (Throwable th3) {
                if (bufferedSinkBuffer != null) {
                    try {
                        bufferedSinkBuffer.close();
                    } catch (Throwable th4) {
                        LazyKt.a(th3, th4);
                    }
                }
                th2 = th3;
                lValueOf = null;
            }
            if (bufferedSinkBuffer != null) {
                try {
                    bufferedSinkBuffer.close();
                    th2 = null;
                } catch (Throwable th5) {
                    th2 = th5;
                }
                if (th2 == null) {
                    throw th2;
                }
                lValueOf.longValue();
                if (source != null) {
                    try {
                        source.close();
                    } catch (Throwable th6) {
                        th = th6;
                    }
                }
            } else {
                th2 = null;
                if (th2 == null) {
                }
            }
        } catch (Throwable th7) {
            if (source != null) {
                try {
                    source.close();
                } catch (Throwable th8) {
                    LazyKt.a(th7, th8);
                }
            }
            th = th7;
        }
        if (th != null) {
            throw th;
        }
    }

    public static final void commonCreateDirectories(com.shadow.okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "dir");
        ArrayDeque arrayDeque = new ArrayDeque();
        for (Path pathParent = path; pathParent != null && !fileSystem.exists(pathParent); pathParent = pathParent.parent()) {
            arrayDeque.addFirst(pathParent);
        }
        if (z && arrayDeque.isEmpty()) {
            throw new IOException(path + " already exists.");
        }
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            fileSystem.createDirectory((Path) it.next());
        }
    }

    public static final void commonDeleteRecursively(com.shadow.okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "fileOrDirectory");
        Iterator<Object> it = new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new FileSystem$commonDeleteRecursively$sequence$1(fileSystem, path, null)).iterator();
        while (it.hasNext()) {
            fileSystem.delete((Path) it.next(), z && !it.hasNext());
        }
    }

    public static final boolean commonExists(com.shadow.okio.FileSystem fileSystem, Path path) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "path");
        return fileSystem.metadataOrNull(path) != null;
    }

    public static final Sequence<Path> commonListRecursively(com.shadow.okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "dir");
        return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(new C00601(path, fileSystem, z, null));
    }

    public static final FileMetadata commonMetadata(com.shadow.okio.FileSystem fileSystem, Path path) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "path");
        FileMetadata fileMetadataMetadataOrNull = fileSystem.metadataOrNull(path);
        if (fileMetadataMetadataOrNull != null) {
            return fileMetadataMetadataOrNull;
        }
        throw new FileNotFoundException(h.b(path, "no such file: "));
    }

    public static final Path symlinkTarget(com.shadow.okio.FileSystem fileSystem, Path path) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "path");
        Path symlinkTarget = fileSystem.metadata(path).getSymlinkTarget();
        if (symlinkTarget == null) {
            return null;
        }
        Path pathParent = path.parent();
        CloseableKt.checkNotNull(pathParent);
        return pathParent.resolve(symlinkTarget);
    }
}
