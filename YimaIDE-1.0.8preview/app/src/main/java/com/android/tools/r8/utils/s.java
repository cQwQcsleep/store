package com.android.tools.r8.utils;

import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.MO;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.startup.StartupClassBuilder;
import com.android.tools.r8.startup.StartupMethodBuilder;
import com.android.tools.r8.startup.StartupProfileBuilder;
import com.android.tools.r8.startup.StartupProfileProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class s implements StartupProfileProvider {
    public static final /* synthetic */ boolean b = true;
    public final /* synthetic */ Path a;

    public s(Path path) {
        this.a = path;
    }

    @Override // com.android.tools.r8.Resource
    public final Origin getOrigin() {
        return new PathOrigin(this.a);
    }

    @Override // com.android.tools.r8.startup.StartupProfileProvider
    public final void getStartupProfile(StartupProfileBuilder startupProfileBuilder) {
        try {
            BufferedReader bufferedReaderNewBufferedReader = Files.newBufferedReader(this.a);
            while (bufferedReaderNewBufferedReader.ready()) {
                try {
                    final String line = bufferedReaderNewBufferedReader.readLine();
                    Comparator comparator = MO.a;
                    int iIndexOf = line.indexOf("->");
                    final MethodReference methodReferenceA = iIndexOf >= 0 ? MO.a(iIndexOf, line) : null;
                    if (methodReferenceA != null) {
                        startupProfileBuilder.addStartupMethod(new Consumer() { // from class: z8i
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((StartupMethodBuilder) obj).setMethodReference(methodReferenceA);
                            }
                        });
                    } else {
                        if (!b && !C0929Wj.z(line)) {
                            throw new AssertionError();
                        }
                        startupProfileBuilder.addStartupClass(new Consumer() { // from class: b9i
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                ((StartupClassBuilder) obj).setClassReference(Reference.classFromDescriptor(line));
                            }
                        });
                    }
                } catch (Throwable th) {
                    if (bufferedReaderNewBufferedReader != null) {
                        try {
                            bufferedReaderNewBufferedReader.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            bufferedReaderNewBufferedReader.close();
        } catch (IOException e) {
            u8i.a(e);
        }
    }
}
