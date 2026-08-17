package com.android.tools.r8;

import com.android.tools.r8.internal.C3043xe0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ProgramResource extends Resource {

    public static class ByteResource implements ProgramResource {
        static final /* synthetic */ boolean e = true;
        private final Origin a;
        private final Kind b;
        private final byte[] c;
        private final Set d;

        private ByteResource(Origin origin, Kind kind, byte[] bArr, Set set) {
            if (!e && bArr == null) {
                x1f.a();
                throw null;
            }
            this.a = origin;
            this.b = kind;
            this.c = bArr;
            this.d = set;
        }

        @Override // com.android.tools.r8.ProgramResource
        public InputStream getByteStream() throws ResourceException {
            return new ByteArrayInputStream(this.c);
        }

        @Override // com.android.tools.r8.ProgramResource
        public byte[] getBytes() throws ResourceException {
            return this.c;
        }

        @Override // com.android.tools.r8.ProgramResource
        public Set<String> getClassDescriptors() {
            return this.d;
        }

        @Override // com.android.tools.r8.ProgramResource
        public Kind getKind() {
            return this.b;
        }

        @Override // com.android.tools.r8.Resource
        public Origin getOrigin() {
            return this.a;
        }
    }

    public static class FileResource implements ProgramResource {
        private final PathOrigin a;
        private final Kind b;
        private final Path c;
        private final Set d;

        private FileResource(Kind kind, Path path) {
            this.a = new PathOrigin(path);
            this.b = kind;
            this.c = path;
            this.d = null;
        }

        @Override // com.android.tools.r8.ProgramResource
        public InputStream getByteStream() throws ResourceException {
            try {
                return Files.newInputStream(this.c, new OpenOption[0]);
            } catch (IOException e) {
                throw new ResourceException(getOrigin(), e);
            }
        }

        @Override // com.android.tools.r8.ProgramResource
        public byte[] getBytes() throws ResourceException {
            try {
                return Files.readAllBytes(this.c);
            } catch (IOException e) {
                throw new ResourceException(getOrigin(), e);
            }
        }

        @Override // com.android.tools.r8.ProgramResource
        public Set<String> getClassDescriptors() {
            return this.d;
        }

        @Override // com.android.tools.r8.ProgramResource
        public Kind getKind() {
            return this.b;
        }

        @Override // com.android.tools.r8.Resource
        public Origin getOrigin() {
            return this.a;
        }
    }

    public enum Kind {
        CF,
        DEX;

        Kind() {
        }
    }

    static ProgramResource fromBytes(Origin origin, Kind kind, byte[] bArr, Set<String> set) {
        return new ByteResource(origin, kind, bArr, set);
    }

    static ProgramResource fromFile(Kind kind, Path path) {
        return new FileResource(kind, path);
    }

    InputStream getByteStream() throws ResourceException;

    default byte[] getBytes() throws ResourceException {
        try {
            return C3043xe0.a(getByteStream());
        } catch (IOException e) {
            throw new ResourceException(getOrigin(), e);
        }
    }

    Set<String> getClassDescriptors();

    Kind getKind();
}
