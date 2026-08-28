package com.shadow.okio.internal;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.collections.ArrayDeque;
import com.shadow.kotlin.coroutines.intrinsics.CoroutineSingletons;
import com.shadow.kotlin.coroutines.jvm.internal.DebugMetadata;
import com.shadow.okio.FileSystem;
import com.shadow.okio.Path;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@DebugMetadata(c = "com.shadow.okio.internal.-FileSystem$commonDeleteRecursively$sequence$1", f = "FileSystem.kt", l = {75}, m = "invokeSuspend")
/* renamed from: com.shadow.okio.internal.-FileSystem$commonDeleteRecursively$sequence$1, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class FileSystem$commonDeleteRecursively$sequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Path $fileOrDirectory;
    final /* synthetic */ FileSystem $this_commonDeleteRecursively;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileSystem$commonDeleteRecursively$sequence$1(FileSystem fileSystem, Path path, Continuation<? super FileSystem$commonDeleteRecursively$sequence$1> continuation) {
        super(2, continuation);
        this.$this_commonDeleteRecursively = fileSystem;
        this.$fileOrDirectory = path;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FileSystem$commonDeleteRecursively$sequence$1 fileSystem$commonDeleteRecursively$sequence$1 = new FileSystem$commonDeleteRecursively$sequence$1(this.$this_commonDeleteRecursively, this.$fileOrDirectory, continuation);
        fileSystem$commonDeleteRecursively$sequence$1.L$0 = obj;
        return fileSystem$commonDeleteRecursively$sequence$1;
    }

    public final Object invokeSuspend(Object obj) throws Throwable {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            LazyKt.throwOnFailure(obj);
            com.shadow.kotlin.sequences.SequenceScope sequenceScope = (com.shadow.kotlin.sequences.SequenceScope) this.L$0;
            FileSystem fileSystem = this.$this_commonDeleteRecursively;
            ArrayDeque arrayDeque = new ArrayDeque();
            Path path = this.$fileOrDirectory;
            this.label = 1;
            if (FileSystem.collectRecursively(sequenceScope, fileSystem, arrayDeque, path, false, true, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            LazyKt.throwOnFailure(obj);
        }
        return com.shadow.kotlin.Unit.INSTANCE;
    }

    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return create(sequenceScope, continuation).invokeSuspend(com.shadow.kotlin.Unit.INSTANCE);
    }
}
