package kotlin.io.path;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0086\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010&\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a \u0010'\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a$\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020\u0002H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u001a$\u0010*\u001a\u00020\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020\u0002H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u001a&\u0010+\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010)\u001a\u00020\u0002H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u001a6\u0010,\u001a\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\u00022\b\b\u0002\u0010.\u001a\u00020/H\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0\u001aE\u0010,\u001a\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020302\"\u000203H\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0¢\u0006\u0002\u00104\u001a9\u00105\u001a\u00020/*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u00107\u001a9\u00108\u001a\u00020/*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u00107\u001a9\u00109\u001a\u00020/*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u00107\u001a9\u0010:\u001a\u00020/*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u00107\u001a \u0010;\u001a\u00020/*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a \u0010<\u001a\u00020/*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a \u0010=\u001a\u00020/*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a \u0010>\u001a\u00020/*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a \u0010?\u001a\u00020/*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a(\u0010@\u001a\u00020/*\u00020\u00022\u0006\u0010A\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a,\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00020C*\u00020\u00022\b\b\u0002\u0010D\u001a\u00020\u0001H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u001aV\u0010E\u001a\u0002HF\"\u0004\b\u0000\u0010F*\u00020\u00022\b\b\u0002\u0010D\u001a\u00020\u00012\u0018\u0010G\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020I\u0012\u0004\u0012\u0002HF0HH\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0ø\u0001\u0000¢\u0006\u0002\u0010J\u001aA\u0010K\u001a\u00020L*\u00020\u00022\b\b\u0002\u0010D\u001a\u00020\u00012\u0012\u0010M\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020L0HH\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013ø\u0001\u0000\u001a \u0010N\u001a\u00020O*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a \u0010P\u001a\u00020L*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a$\u0010Q\u001a\u00020/*\u00020\u0002H\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0\u001aE\u0010R\u001a\u00020\u0002*\u00020\u00022\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0¢\u0006\u0002\u0010U\u001aE\u0010V\u001a\u00020\u0002*\u00020\u00022\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0¢\u0006\u0002\u0010U\u001aA\u0010W\u001a\u00020\u0002*\u00020\u00022\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0080\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(Xb\u0002\b0¢\u0006\u0002\u0010U\u001aE\u0010Y\u001a\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020302\"\u000203H\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0¢\u0006\u0002\u00104\u001a6\u0010Y\u001a\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\u00022\b\b\u0002\u0010.\u001a\u00020/H\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0\u001a \u0010Z\u001a\u00020[*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001aC\u0010\\\u001a\u0004\u0018\u00010]*\u00020\u00022\u0006\u0010^\u001a\u00020\u00012\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010_\u001aO\u0010`\u001a\u00020\u0002*\u00020\u00022\u0006\u0010^\u001a\u00020\u00012\b\u0010a\u001a\u0004\u0018\u00010]2\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0¢\u0006\u0002\u0010b\u001aG\u0010c\u001a\u0004\u0018\u0001Hd\"\n\b\u0000\u0010d\u0018\u0001*\u00020e*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010f\u001aE\u0010g\u001a\u0002Hd\"\n\b\u0000\u0010d\u0018\u0001*\u00020e*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010f\u001a\"\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00022\n\u0010k\u001a\u0006\u0012\u0002\b\u00030lH\u0081\u0080\u0004b\u0002\bm\u001aE\u0010n\u001a\u0002Ho\"\n\b\u0000\u0010o\u0018\u0001*\u00020p*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010q\u001aO\u0010n\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010]0r*\u00020\u00022\u0006\u0010S\u001a\u00020\u00012\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010s\u001a9\u0010t\u001a\u00020u*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010v\u001a(\u0010w\u001a\u00020\u0002*\u00020\u00022\u0006\u0010a\u001a\u00020uH\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a;\u0010x\u001a\u0004\u0018\u00010y*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010z\u001a,\u0010{\u001a\u00020\u0002*\u00020\u00022\u0006\u0010a\u001a\u00020yH\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0\u001a?\u0010|\u001a\b\u0012\u0004\u0012\u00020~0}*\u00020\u00022\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020602\"\u000206H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0002\u0010\u007f\u001a/\u0010\u0080\u0001\u001a\u00020\u0002*\u00020\u00022\f\u0010a\u001a\b\u0012\u0004\u0012\u00020~0}H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a-\u0010\u0081\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\u0002H\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0\u001aO\u0010\u0082\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\u00022\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0¢\u0006\u0003\u0010\u0083\u0001\u001a!\u0010\u0084\u0001\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001aF\u0010\u0085\u0001\u001a\u00020\u0002*\u00020\u00022\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0088\bb\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013b\u0002\b0¢\u0006\u0002\u0010U\u001aY\u0010\u0086\u0001\u001a\u00020\u00022\u000b\b\u0002\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00012\u000b\b\u0002\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00012\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0003\u0010\u0089\u0001\u001a`\u0010\u0086\u0001\u001a\u00020\u00022\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00022\u000b\b\u0002\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00012\u000b\b\u0002\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00012\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\u0003\u0010\u008b\u0001\u001aL\u0010\u008c\u0001\u001a\u00020\u00022\u000b\b\u0002\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00012\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0003\u0010\u008d\u0001\u001aS\u0010\u008c\u0001\u001a\u00020\u00022\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00022\u000b\b\u0002\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00012\u001a\u0010S\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030T02\"\u0006\u0012\u0002\b\u00030TH\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\u0003\u0010\u008e\u0001\u001a)\u0010\u008f\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010A\u001a\u00020\u0002H\u0087\u008a\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a)\u0010\u008f\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010A\u001a\u00020\u0001H\u0087\u008a\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a%\u0010\u0090\u0001\u001a\u00020\u00022\u0006\u0010j\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001a@\u0010\u0090\u0001\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00012\u0013\u0010\u0091\u0001\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000102\"\u00020\u0001H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013¢\u0006\u0003\u0010\u0092\u0001\u001a\"\u0010\u0093\u0001\u001a\u00020\u0002*\u00030\u0094\u0001H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\u0013\u001aT\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020\u00020I*\u00020\u00022\u0014\u00101\u001a\u000b\u0012\u0007\b\u0001\u0012\u00030\u0096\u000102\"\u00030\u0096\u0001H\u0087\u0080\u0004b\u0012\b\u0098\u0001\u0012\r\b\u0099\u0001\u0012\b\b\fJ\u0004\b\t0\u001bb\r\b\u0007\u0012\t\b\b\u0012\u0005\b\b(\u009a\u0001¢\u0006\u0003\u0010\u0097\u0001\u001aY\u0010\u009b\u0001\u001a\u00020L*\u00020\u00022\u000e\u0010\u009c\u0001\u001a\t\u0012\u0004\u0012\u00020\u00020\u009d\u00012\n\b\u0002\u0010\u009e\u0001\u001a\u00030\u009f\u00012\t\b\u0002\u0010 \u0001\u001a\u00020/H\u0087\u0080\u0004b\u0012\b\u0098\u0001\u0012\r\b\u0099\u0001\u0012\b\b\fJ\u0004\b\t0\u001bb\r\b\u0007\u0012\t\b\b\u0012\u0005\b\b(\u009a\u0001\u001ar\u0010\u009b\u0001\u001a\u00020L*\u00020\u00022\n\b\u0002\u0010\u009e\u0001\u001a\u00030\u009f\u00012\t\b\u0002\u0010 \u0001\u001a\u00020/2\u001a\u0010¡\u0001\u001a\u0015\u0012\u0005\u0012\u00030¢\u0001\u0012\u0004\u0012\u00020L0H¢\u0006\u0003\b£\u0001H\u0087\u0080\u0004b\u0012\b\u0098\u0001\u0012\r\b\u0099\u0001\u0012\b\b\fJ\u0004\b\t0\u001bb\r\b\u0007\u0012\t\b\b\u0012\u0005\b\b(\u009a\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001\u001a^\u0010¤\u0001\u001a\t\u0012\u0004\u0012\u00020\u00020\u009d\u00012\u001a\u0010¡\u0001\u001a\u0015\u0012\u0005\u0012\u00030¢\u0001\u0012\u0004\u0012\u00020L0H¢\u0006\u0003\b£\u0001H\u0087\u0080\u0004b\u0012\b\u0098\u0001\u0012\r\b\u0099\u0001\u0012\b\b\fJ\u0004\b\t0\u001bb\r\b\u0007\u0012\t\b\b\u0012\u0005\b\b(\u009a\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\"-\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0084\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"-\u0010\n\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0084\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006\"-\u0010\r\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0084\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\f\u0012\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006\"2\u0010\u0010\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0084\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tr\u0002\b\u0013¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\u0006\"-\u0010\u0014\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0084\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\f\u0012\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0006\"n\u0010\u0017\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0084\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001ar\u0002\b\u001br6\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u001c\b\u001f\u0012\u0018\b\u000bB\u0014\b \u0012\b\b!\u0012\u0004\b\b(\u0014\u0012\u0006\b\"\u0012\u0002\b\f\u0012\n\b#\u0012\u0006\b\n0$8%r\u0002\b\u0013¢\u0006\f\u0012\u0004\b\u0018\u0010\u0004\u001a\u0004\b\u0019\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¥\u0001"}, d2 = {"name", "", "Ljava/nio/file/Path;", "getName$annotations", "(Ljava/nio/file/Path;)V", "getName", "(Ljava/nio/file/Path;)Ljava/lang/String;", "Lkotlin/SinceKotlin;", "version", "1.5", "nameWithoutExtension", "getNameWithoutExtension$annotations", "getNameWithoutExtension", "extension", "getExtension$annotations", "getExtension", "pathString", "getPathString$annotations", "getPathString", "Lkotlin/internal/InlineOnly;", "invariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "getInvariantSeparatorsPathString", "invariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "getInvariantSeparatorsPath", "1.4", "Lkotlin/io/path/ExperimentalPathApi;", "Lkotlin/Deprecated;", "message", "Use invariantSeparatorsPathString property instead.", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "imports", "level", "Lkotlin/DeprecationLevel;", "ERROR", "absolute", "absolutePathString", "relativeTo", "base", "relativeToOrSelf", "relativeToOrNull", "copyTo", "target", "overwrite", "", "Lkotlin/IgnorableReturnValue;", "options", "", "Ljava/nio/file/CopyOption;", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/CopyOption;)Ljava/nio/file/Path;", "exists", "Ljava/nio/file/LinkOption;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "notExists", "isRegularFile", "isDirectory", "isSymbolicLink", "isExecutable", "isHidden", "isReadable", "isWritable", "isSameFileAs", "other", "listDirectoryEntries", "", "glob", "useDirectoryEntries", "T", "block", "Lkotlin/Function1;", "Lkotlin/sequences/Sequence;", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "forEachDirectoryEntry", "", "action", "fileSize", "", "deleteExisting", "deleteIfExists", "createDirectory", "attributes", "Ljava/nio/file/attribute/FileAttribute;", "(Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createDirectories", "createParentDirectories", "1.9", "moveTo", "fileStore", "Ljava/nio/file/FileStore;", "getAttribute", "", "attribute", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/lang/Object;", "setAttribute", "value", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Ljava/nio/file/LinkOption;)Ljava/nio/file/Path;", "fileAttributesViewOrNull", "V", "Ljava/nio/file/attribute/FileAttributeView;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileAttributeView;", "fileAttributesView", "fileAttributeViewNotAvailable", "", "path", "attributeViewClass", "Ljava/lang/Class;", "Lkotlin/PublishedApi;", "readAttributes", "A", "Ljava/nio/file/attribute/BasicFileAttributes;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/BasicFileAttributes;", "", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/LinkOption;)Ljava/util/Map;", "getLastModifiedTime", "Ljava/nio/file/attribute/FileTime;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/FileTime;", "setLastModifiedTime", "getOwner", "Ljava/nio/file/attribute/UserPrincipal;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/nio/file/attribute/UserPrincipal;", "setOwner", "getPosixFilePermissions", "", "Ljava/nio/file/attribute/PosixFilePermission;", "(Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Ljava/util/Set;", "setPosixFilePermissions", "createLinkPointingTo", "createSymbolicLinkPointingTo", "(Ljava/nio/file/Path;Ljava/nio/file/Path;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "readSymbolicLink", "createFile", "createTempFile", "prefix", "suffix", "(Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "directory", "(Ljava/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "createTempDirectory", "(Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Ljava/lang/String;[Ljava/nio/file/attribute/FileAttribute;)Ljava/nio/file/Path;", "div", "Path", "subpaths", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;", "toPath", "Ljava/net/URI;", "walk", "Lkotlin/io/path/PathWalkOption;", "(Ljava/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)Lkotlin/sequences/Sequence;", "Lkotlin/WasExperimental;", "markerClass", "2.1", "visitFileTree", "visitor", "Ljava/nio/file/FileVisitor;", "maxDepth", "", "followLinks", "builderAction", "Lkotlin/io/path/FileVisitorBuilder;", "Lkotlin/ExtensionFunctionType;", "fileVisitor", "kotlin-stdlib-jdk7"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/io/path/PathsKt")
class PathsKt__PathUtilsKt extends PathsKt__PathRecursiveFunctionsKt {
    private static final Path Path(String str, String... strArr) {
        str.getClass();
        strArr.getClass();
        Path path = Paths.get(str, (String[]) Arrays.copyOf(strArr, strArr.length));
        path.getClass();
        return path;
    }

    private static final Path absolute(Path path) {
        path.getClass();
        Path absolutePath = path.toAbsolutePath();
        absolutePath.getClass();
        return absolutePath;
    }

    private static final String absolutePathString(Path path) {
        path.getClass();
        return path.toAbsolutePath().toString();
    }

    @IgnorableReturnValue
    private static final Path copyTo(Path path, Path path2, boolean z) throws IOException {
        path.getClass();
        path2.getClass();
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathCopy = Files.copy(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        pathCopy.getClass();
        return pathCopy;
    }

    public static /* synthetic */ Path copyTo$default(Path path, Path path2, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        path.getClass();
        path2.getClass();
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathCopy = Files.copy(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        pathCopy.getClass();
        return pathCopy;
    }

    @IgnorableReturnValue
    private static final Path createDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        path.getClass();
        fileAttributeArr.getClass();
        Path pathCreateDirectories = Files.createDirectories(path, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateDirectories.getClass();
        return pathCreateDirectories;
    }

    @IgnorableReturnValue
    private static final Path createDirectory(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        path.getClass();
        fileAttributeArr.getClass();
        Path pathCreateDirectory = Files.createDirectory(path, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateDirectory.getClass();
        return pathCreateDirectory;
    }

    @IgnorableReturnValue
    private static final Path createFile(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        path.getClass();
        fileAttributeArr.getClass();
        Path pathCreateFile = Files.createFile(path, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateFile.getClass();
        return pathCreateFile;
    }

    @IgnorableReturnValue
    private static final Path createLinkPointingTo(Path path, Path path2) throws IOException {
        path.getClass();
        path2.getClass();
        Path pathCreateLink = Files.createLink(path, path2);
        pathCreateLink.getClass();
        return pathCreateLink;
    }

    @IgnorableReturnValue
    public static final Path createParentDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        path.getClass();
        fileAttributeArr.getClass();
        Path parent = path.getParent();
        if (parent != null && !Files.isDirectory(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
            try {
                FileAttribute[] fileAttributeArr2 = (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length);
                Files.createDirectories(parent, (FileAttribute[]) Arrays.copyOf(fileAttributeArr2, fileAttributeArr2.length)).getClass();
                return path;
            } catch (FileAlreadyExistsException e) {
                if (!Files.isDirectory(parent, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                    throw e;
                }
            }
        }
        return path;
    }

    @IgnorableReturnValue
    private static final Path createSymbolicLinkPointingTo(Path path, Path path2, FileAttribute<?>... fileAttributeArr) throws IOException {
        path.getClass();
        path2.getClass();
        fileAttributeArr.getClass();
        Path pathCreateSymbolicLink = Files.createSymbolicLink(path, path2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateSymbolicLink.getClass();
        return pathCreateSymbolicLink;
    }

    public static final Path createTempDirectory(Path path, String str, FileAttribute<?>... fileAttributeArr) throws IOException {
        fileAttributeArr.getClass();
        if (path != null) {
            Path pathCreateTempDirectory = Files.createTempDirectory(path, str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
            pathCreateTempDirectory.getClass();
            return pathCreateTempDirectory;
        }
        Path pathCreateTempDirectory2 = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateTempDirectory2.getClass();
        return pathCreateTempDirectory2;
    }

    public static /* synthetic */ Path createTempDirectory$default(String str, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        fileAttributeArr.getClass();
        Path pathCreateTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateTempDirectory.getClass();
        return pathCreateTempDirectory;
    }

    public static final Path createTempFile(Path path, String str, String str2, FileAttribute<?>... fileAttributeArr) throws IOException {
        fileAttributeArr.getClass();
        if (path != null) {
            Path pathCreateTempFile = Files.createTempFile(path, str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
            pathCreateTempFile.getClass();
            return pathCreateTempFile;
        }
        Path pathCreateTempFile2 = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateTempFile2.getClass();
        return pathCreateTempFile2;
    }

    public static /* synthetic */ Path createTempFile$default(String str, String str2, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        fileAttributeArr.getClass();
        Path pathCreateTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateTempFile.getClass();
        return pathCreateTempFile;
    }

    private static final void deleteExisting(Path path) throws IOException {
        path.getClass();
        Files.delete(path);
    }

    @IgnorableReturnValue
    private static final boolean deleteIfExists(Path path) throws IOException {
        path.getClass();
        return Files.deleteIfExists(path);
    }

    private static final Path div(Path path, Path path2) {
        path.getClass();
        path2.getClass();
        Path pathResolve = path.resolve(path2);
        pathResolve.getClass();
        return pathResolve;
    }

    private static final boolean exists(Path path, LinkOption... linkOptionArr) {
        path.getClass();
        linkOptionArr.getClass();
        return Files.exists(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    public static final Void fileAttributeViewNotAvailable(Path path, Class<?> cls) {
        path.getClass();
        cls.getClass();
        throw new UnsupportedOperationException("The desired attribute view type " + cls + " is not available for the file " + path + '.');
    }

    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesView(Path path, LinkOption... linkOptionArr) {
        path.getClass();
        linkOptionArr.getClass();
        Intrinsics.reifiedOperationMarker(4, "V");
        V v = (V) Files.getFileAttributeView(path, FileAttributeView.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        if (v != null) {
            return v;
        }
        Intrinsics.reifiedOperationMarker(4, "V");
        fileAttributeViewNotAvailable(path, FileAttributeView.class);
        wq6.a();
        return null;
    }

    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesViewOrNull(Path path, LinkOption... linkOptionArr) {
        path.getClass();
        linkOptionArr.getClass();
        Intrinsics.reifiedOperationMarker(4, "V");
        return (V) Files.getFileAttributeView(path, FileAttributeView.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final long fileSize(Path path) throws IOException {
        path.getClass();
        return Files.size(path);
    }

    private static final FileStore fileStore(Path path) throws IOException {
        path.getClass();
        FileStore fileStore = Files.getFileStore(path);
        fileStore.getClass();
        return fileStore;
    }

    public static final FileVisitor<Path> fileVisitor(Function1<? super FileVisitorBuilder, Unit> function1) {
        function1.getClass();
        FileVisitorBuilderImpl fileVisitorBuilderImpl = new FileVisitorBuilderImpl();
        function1.invoke(fileVisitorBuilderImpl);
        return fileVisitorBuilderImpl.build();
    }

    private static final void forEachDirectoryEntry(Path path, String str, Function1<? super Path, Unit> function1) throws IOException {
        path.getClass();
        str.getClass();
        function1.getClass();
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, str);
        try {
            directoryStreamNewDirectoryStream.getClass();
            Iterator<Path> it2 = directoryStreamNewDirectoryStream.iterator();
            while (it2.hasNext()) {
                function1.invoke(it2.next());
            }
            Unit unit = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, (Throwable) null);
            InlineMarker.finallyEnd(1);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public static /* synthetic */ void forEachDirectoryEntry$default(Path path, String str, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = "*";
        }
        path.getClass();
        str.getClass();
        function1.getClass();
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, str);
        try {
            directoryStreamNewDirectoryStream.getClass();
            Iterator<Path> it2 = directoryStreamNewDirectoryStream.iterator();
            while (it2.hasNext()) {
                function1.invoke(it2.next());
            }
            Unit unit = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, (Throwable) null);
            InlineMarker.finallyEnd(1);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    private static final Object getAttribute(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        path.getClass();
        str.getClass();
        linkOptionArr.getClass();
        return Files.getAttribute(path, str, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    public static final String getExtension(Path path) {
        String string;
        String strSubstringAfterLast;
        path.getClass();
        Path fileName = path.getFileName();
        return (fileName == null || (string = fileName.toString()) == null || (strSubstringAfterLast = StringsKt.substringAfterLast(string, '.', "")) == null) ? "" : strSubstringAfterLast;
    }

    public static /* synthetic */ void getExtension$annotations(Path path) {
    }

    private static final String getInvariantSeparatorsPath(Path path) {
        path.getClass();
        return getInvariantSeparatorsPathString(path);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use invariantSeparatorsPathString property instead.", replaceWith = @ReplaceWith(expression = "invariantSeparatorsPathString", imports = {}))
    public static /* synthetic */ void getInvariantSeparatorsPath$annotations(Path path) {
    }

    public static String getInvariantSeparatorsPathString(Path path) {
        path.getClass();
        String separator = path.getFileSystem().getSeparator();
        if (Intrinsics.areEqual(separator, "/")) {
            return path.toString();
        }
        String string = path.toString();
        separator.getClass();
        return StringsKt.replace$default(string, separator, "/", false, 4, (Object) null);
    }

    public static /* synthetic */ void getInvariantSeparatorsPathString$annotations(Path path) {
    }

    private static final FileTime getLastModifiedTime(Path path, LinkOption... linkOptionArr) throws IOException {
        path.getClass();
        linkOptionArr.getClass();
        FileTime lastModifiedTime = Files.getLastModifiedTime(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        lastModifiedTime.getClass();
        return lastModifiedTime;
    }

    public static final String getName(Path path) {
        path.getClass();
        Path fileName = path.getFileName();
        String string = fileName != null ? fileName.toString() : null;
        return string == null ? "" : string;
    }

    public static /* synthetic */ void getName$annotations(Path path) {
    }

    public static final String getNameWithoutExtension(Path path) {
        String string;
        String strSubstringBeforeLast$default;
        path.getClass();
        Path fileName = path.getFileName();
        return (fileName == null || (string = fileName.toString()) == null || (strSubstringBeforeLast$default = StringsKt.substringBeforeLast$default(string, ".", (String) null, 2, (Object) null)) == null) ? "" : strSubstringBeforeLast$default;
    }

    public static /* synthetic */ void getNameWithoutExtension$annotations(Path path) {
    }

    private static final UserPrincipal getOwner(Path path, LinkOption... linkOptionArr) throws IOException {
        path.getClass();
        linkOptionArr.getClass();
        return Files.getOwner(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final String getPathString(Path path) {
        path.getClass();
        return path.toString();
    }

    public static /* synthetic */ void getPathString$annotations(Path path) {
    }

    private static final Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... linkOptionArr) throws IOException {
        path.getClass();
        linkOptionArr.getClass();
        Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        posixFilePermissions.getClass();
        return posixFilePermissions;
    }

    private static final boolean isDirectory(Path path, LinkOption... linkOptionArr) {
        path.getClass();
        linkOptionArr.getClass();
        return Files.isDirectory(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final boolean isExecutable(Path path) {
        path.getClass();
        return Files.isExecutable(path);
    }

    private static final boolean isHidden(Path path) throws IOException {
        path.getClass();
        return Files.isHidden(path);
    }

    private static final boolean isReadable(Path path) {
        path.getClass();
        return Files.isReadable(path);
    }

    private static final boolean isRegularFile(Path path, LinkOption... linkOptionArr) {
        path.getClass();
        linkOptionArr.getClass();
        return Files.isRegularFile(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final boolean isSameFileAs(Path path, Path path2) throws IOException {
        path.getClass();
        path2.getClass();
        return Files.isSameFile(path, path2);
    }

    private static final boolean isSymbolicLink(Path path) {
        path.getClass();
        return Files.isSymbolicLink(path);
    }

    private static final boolean isWritable(Path path) {
        path.getClass();
        return Files.isWritable(path);
    }

    public static final List<Path> listDirectoryEntries(Path path, String str) throws IOException {
        path.getClass();
        str.getClass();
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, str);
        try {
            directoryStreamNewDirectoryStream.getClass();
            List<Path> list = CollectionsKt.toList(directoryStreamNewDirectoryStream);
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, (Throwable) null);
            return list;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ List listDirectoryEntries$default(Path path, String str, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = "*";
        }
        return listDirectoryEntries(path, str);
    }

    @IgnorableReturnValue
    private static final Path moveTo(Path path, Path path2, boolean z) throws IOException {
        path.getClass();
        path2.getClass();
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathMove = Files.move(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        pathMove.getClass();
        return pathMove;
    }

    public static /* synthetic */ Path moveTo$default(Path path, Path path2, boolean z, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            z = false;
        }
        path.getClass();
        path2.getClass();
        CopyOption[] copyOptionArr = z ? new CopyOption[]{StandardCopyOption.REPLACE_EXISTING} : new CopyOption[0];
        Path pathMove = Files.move(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        pathMove.getClass();
        return pathMove;
    }

    private static final boolean notExists(Path path, LinkOption... linkOptionArr) {
        path.getClass();
        linkOptionArr.getClass();
        return Files.notExists(path, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
    }

    private static final /* synthetic */ <A extends BasicFileAttributes> A readAttributes(Path path, LinkOption... linkOptionArr) throws IOException {
        path.getClass();
        linkOptionArr.getClass();
        Intrinsics.reifiedOperationMarker(4, "A");
        A a = (A) Files.readAttributes(path, BasicFileAttributes.class, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        a.getClass();
        return a;
    }

    private static final Path readSymbolicLink(Path path) throws IOException {
        path.getClass();
        Path symbolicLink = Files.readSymbolicLink(path);
        symbolicLink.getClass();
        return symbolicLink;
    }

    public static Path relativeTo(Path path, Path path2) {
        path.getClass();
        path2.getClass();
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, path2);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + "\nthis path: " + path + "\nbase path: " + path2, e);
        }
    }

    public static final Path relativeToOrNull(Path path, Path path2) {
        path.getClass();
        path2.getClass();
        try {
            return PathRelativizer.INSTANCE.tryRelativeTo(path, path2);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static final Path relativeToOrSelf(Path path, Path path2) {
        path.getClass();
        path2.getClass();
        Path pathRelativeToOrNull = relativeToOrNull(path, path2);
        return pathRelativeToOrNull == null ? path : pathRelativeToOrNull;
    }

    @IgnorableReturnValue
    private static final Path setAttribute(Path path, String str, Object obj, LinkOption... linkOptionArr) throws IOException {
        path.getClass();
        str.getClass();
        linkOptionArr.getClass();
        Path attribute = Files.setAttribute(path, str, obj, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        attribute.getClass();
        return attribute;
    }

    private static final Path setLastModifiedTime(Path path, FileTime fileTime) throws IOException {
        path.getClass();
        fileTime.getClass();
        Path lastModifiedTime = Files.setLastModifiedTime(path, fileTime);
        lastModifiedTime.getClass();
        return lastModifiedTime;
    }

    @IgnorableReturnValue
    private static final Path setOwner(Path path, UserPrincipal userPrincipal) throws IOException {
        path.getClass();
        userPrincipal.getClass();
        Path owner = Files.setOwner(path, userPrincipal);
        owner.getClass();
        return owner;
    }

    private static final Path setPosixFilePermissions(Path path, Set<? extends PosixFilePermission> set) throws IOException {
        path.getClass();
        set.getClass();
        Path posixFilePermissions = Files.setPosixFilePermissions(path, set);
        posixFilePermissions.getClass();
        return posixFilePermissions;
    }

    private static final Path toPath(URI uri) {
        uri.getClass();
        Path path = Paths.get(uri);
        path.getClass();
        return path;
    }

    @IgnorableReturnValue
    private static final <T> T useDirectoryEntries(Path path, String str, Function1<? super Sequence<? extends Path>, ? extends T> function1) throws IOException {
        path.getClass();
        str.getClass();
        function1.getClass();
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, str);
        try {
            directoryStreamNewDirectoryStream.getClass();
            T tInvoke = function1.invoke(CollectionsKt.asSequence(directoryStreamNewDirectoryStream));
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, (Throwable) null);
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public static /* synthetic */ Object useDirectoryEntries$default(Path path, String str, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            str = "*";
        }
        path.getClass();
        str.getClass();
        function1.getClass();
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, str);
        try {
            directoryStreamNewDirectoryStream.getClass();
            Object objInvoke = function1.invoke(CollectionsKt.asSequence(directoryStreamNewDirectoryStream));
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(directoryStreamNewDirectoryStream, (Throwable) null);
            InlineMarker.finallyEnd(1);
            return objInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public static final void visitFileTree(Path path, FileVisitor<Path> fileVisitor, int i, boolean z) throws IOException {
        path.getClass();
        fileVisitor.getClass();
        Files.walkFileTree(path, z ? SetsKt.setOf(FileVisitOption.FOLLOW_LINKS) : SetsKt.emptySet(), i, fileVisitor);
    }

    public static /* synthetic */ void visitFileTree$default(Path path, FileVisitor fileVisitor, int i, boolean z, int i2, Object obj) throws IOException {
        if ((i2 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        visitFileTree(path, (FileVisitor<Path>) fileVisitor, i, z);
    }

    public static final Sequence<Path> walk(Path path, PathWalkOption... pathWalkOptionArr) {
        path.getClass();
        pathWalkOptionArr.getClass();
        return new PathTreeWalk(path, pathWalkOptionArr);
    }

    private static final Path div(Path path, String str) {
        path.getClass();
        str.getClass();
        Path pathResolve = path.resolve(str);
        pathResolve.getClass();
        return pathResolve;
    }

    public static /* synthetic */ void visitFileTree$default(Path path, int i, boolean z, Function1 function1, int i2, Object obj) throws IOException {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        visitFileTree(path, i, z, (Function1<? super FileVisitorBuilder, Unit>) function1);
    }

    private static final Path Path(String str) {
        str.getClass();
        Path path = Paths.get(str, new String[0]);
        path.getClass();
        return path;
    }

    public static /* synthetic */ Path createTempDirectory$default(Path path, String str, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        return createTempDirectory(path, str, fileAttributeArr);
    }

    public static final void visitFileTree(Path path, int i, boolean z, Function1<? super FileVisitorBuilder, Unit> function1) throws IOException {
        path.getClass();
        function1.getClass();
        visitFileTree(path, fileVisitor(function1), i, z);
    }

    public static /* synthetic */ Path createTempFile$default(Path path, String str, String str2, FileAttribute[] fileAttributeArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        return createTempFile(path, str, str2, fileAttributeArr);
    }

    private static final Map<String, Object> readAttributes(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        path.getClass();
        str.getClass();
        linkOptionArr.getClass();
        Map<String, Object> attributes = Files.readAttributes(path, str, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length));
        attributes.getClass();
        return attributes;
    }

    @IgnorableReturnValue
    private static final Path copyTo(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        path.getClass();
        path2.getClass();
        copyOptionArr.getClass();
        Path pathCopy = Files.copy(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        pathCopy.getClass();
        return pathCopy;
    }

    @IgnorableReturnValue
    private static final Path moveTo(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        path.getClass();
        path2.getClass();
        copyOptionArr.getClass();
        Path pathMove = Files.move(path, path2, (CopyOption[]) Arrays.copyOf(copyOptionArr, copyOptionArr.length));
        pathMove.getClass();
        return pathMove;
    }

    private static final Path createTempDirectory(String str, FileAttribute<?>... fileAttributeArr) throws IOException {
        fileAttributeArr.getClass();
        Path pathCreateTempDirectory = Files.createTempDirectory(str, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateTempDirectory.getClass();
        return pathCreateTempDirectory;
    }

    private static final Path createTempFile(String str, String str2, FileAttribute<?>... fileAttributeArr) throws IOException {
        fileAttributeArr.getClass();
        Path pathCreateTempFile = Files.createTempFile(str, str2, (FileAttribute[]) Arrays.copyOf(fileAttributeArr, fileAttributeArr.length));
        pathCreateTempFile.getClass();
        return pathCreateTempFile;
    }
}
