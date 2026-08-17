package com.android.tools.r8;

import com.android.tools.r8.ExtractMarkerCommand;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import defpackage.n33;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ExtractMarkerCommand {
    static final String g = String.join("\n", AbstractC0551Hu.a("Usage: extractmarker [options] <input-files>", " where <input-files> are D8 supported input/output files and options are:", "  --help                  # Print this message."));
    private final boolean a;
    private final DiagnosticsHandler b;
    private final MarkerInfoConsumer c;
    private final List d;
    private final List e;
    private final List f;

    private ExtractMarkerCommand(DiagnosticsHandler diagnosticsHandler, MarkerInfoConsumer markerInfoConsumer, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3) {
        this.a = false;
        this.b = diagnosticsHandler;
        this.c = markerInfoConsumer;
        this.d = arrayList;
        this.e = arrayList2;
        this.f = arrayList3;
    }

    private static void a(String[] strArr, Builder builder) {
        for (String str : strArr) {
            String strTrim = str.trim();
            if (strTrim.equals("--help")) {
                builder.setPrintHelp(true);
            } else {
                if (strTrim.startsWith("--")) {
                    n33.a("Unknown option: ".concat(strTrim));
                    return;
                }
                builder.addProgramFiles(Paths.get(strTrim, new String[0]));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(BiConsumer biConsumer, C1405eW c1405eW) {
        biConsumer.accept((byte[]) c1405eW.b(), (Origin) c1405eW.a());
    }

    public static Builder builder() {
        return builder(new G());
    }

    public static Builder parse(String[] strArr) {
        Builder builder = builder();
        a(strArr, builder);
        return builder;
    }

    public void forEachEntry(final BiConsumer<Path, Origin> biConsumer, final BiConsumer<byte[], Origin> biConsumer2, final BiConsumer<byte[], Origin> biConsumer3) {
        this.d.forEach(new Consumer() { // from class: lk4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ExtractMarkerCommand.a(biConsumer, (Path) obj);
            }
        });
        this.e.forEach(new Consumer() { // from class: mk4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ExtractMarkerCommand.a(biConsumer2, (C1405eW) obj);
            }
        });
        this.f.forEach(new Consumer() { // from class: nk4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ExtractMarkerCommand.b(biConsumer3, (C1405eW) obj);
            }
        });
    }

    public DiagnosticsHandler getDiagnosticsHandler() {
        return this.b;
    }

    public MarkerInfoConsumer getMarkerInfoConsumer() {
        return this.c;
    }

    public boolean isPrintHelp() {
        return this.a;
    }

    public static class Builder {
        private boolean a = false;
        private final ArrayList b = new ArrayList();
        private final ArrayList c = new ArrayList();
        private final ArrayList d = new ArrayList();
        private MarkerInfoConsumer e;
        private final DiagnosticsHandler f;

        public Builder(DiagnosticsHandler diagnosticsHandler) {
            this.f = diagnosticsHandler;
        }

        public Builder addClassProgramData(byte[] bArr, Origin origin) {
            this.d.add(new C1405eW(origin, bArr));
            return this;
        }

        public Builder addDexProgramData(byte[] bArr, Origin origin) {
            this.c.add(new C1405eW(origin, bArr));
            return this;
        }

        public Builder addProgramFiles(Path... pathArr) {
            return addProgramFiles(Arrays.asList(pathArr));
        }

        public ExtractMarkerCommand build() {
            return isPrintHelp() ? new ExtractMarkerCommand(isPrintHelp()) : new ExtractMarkerCommand(this.f, this.e, this.b, this.c, this.d);
        }

        public boolean isPrintHelp() {
            return this.a;
        }

        public Builder setMarkerInfoConsumer(MarkerInfoConsumer markerInfoConsumer) {
            this.e = markerInfoConsumer;
            return this;
        }

        public Builder setPrintHelp(boolean z) {
            this.a = z;
            return this;
        }

        public Builder addProgramFiles(Collection<Path> collection) {
            this.b.addAll(collection);
            return this;
        }
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }

    private ExtractMarkerCommand(boolean z) {
        this.a = z;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(BiConsumer biConsumer, Path path) {
        biConsumer.accept(path, new PathOrigin(path));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(BiConsumer biConsumer, C1405eW c1405eW) {
        biConsumer.accept((byte[]) c1405eW.b(), (Origin) c1405eW.a());
    }
}
