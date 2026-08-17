package com.android.tools.r8;

import com.android.tools.r8.dex.C0011c;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.MethodReference;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ResourceShrinker {

    public static final class Builder extends BaseCommand.Builder<Command, Builder> {
        public Builder() {
            super(com.android.tools.r8.utils.i.b());
        }

        @Override // com.android.tools.r8.BaseCommand.Builder
        public final BaseCommand.Builder c() {
            return this;
        }

        @Override // com.android.tools.r8.BaseCommand.Builder
        public final BaseCommand makeCommand() {
            return new Command(a().a());
        }
    }

    public static final class Command extends BaseCommand {
        public Command(com.android.tools.r8.utils.i iVar) {
            super(iVar);
        }

        @Override // com.android.tools.r8.BaseCommand
        public C2752uB b() {
            return new C2752uB();
        }
    }

    public interface ReferenceChecker {
        default void endClassVisit(ClassReference classReference) {
        }

        default void endMethodVisit(MethodReference methodReference) {
        }

        void referencedInt(int i);

        void referencedMethod(String str, String str2, String str3);

        void referencedStaticField(String str, String str2);

        void referencedString(String str);

        boolean shouldProcess(String str);

        default void startClassVisit(ClassReference classReference) {
        }

        default void startMethodVisit(MethodReference methodReference) {
        }
    }

    public static void run(Command command, ReferenceChecker referenceChecker) throws ExecutionException, IOException {
        runForTesting(command.a(), command.b(), referenceChecker);
    }

    public static void runForTesting(com.android.tools.r8.utils.i iVar, C2752uB c2752uB, ReferenceChecker referenceChecker) throws ExecutionException, IOException {
        Iterator<D2> it = new C0011c(iVar, c2752uB, new Ch0("resource shrinker analyzer", false)).a().d().iterator();
        while (it.hasNext()) {
            new r0(it.next(), referenceChecker).a();
        }
    }
}
