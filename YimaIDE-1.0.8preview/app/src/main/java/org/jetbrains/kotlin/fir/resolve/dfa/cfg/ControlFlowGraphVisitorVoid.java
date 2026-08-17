package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008a\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH&J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u000200H\u0016J\u0010\u00101\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u000206H\u0016J\u0010\u00107\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u000208H\u0016J\u0010\u00109\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020>H\u0016J\u0010\u0010?\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020JH\u0016J\u0010\u0010K\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020LH\u0016J\u0010\u0010M\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020NH\u0016J\u0010\u0010O\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020PH\u0016J\u0010\u0010Q\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020RH\u0016J\u0010\u0010S\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020TH\u0016J\u0010\u0010U\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020VH\u0016J\u0010\u0010W\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020XH\u0016J\u0010\u0010Y\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020ZH\u0016J\u0010\u0010[\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\\H\u0016J\u0010\u0010]\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020^H\u0016J\u0010\u0010_\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020`H\u0016J\u0010\u0010a\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020bH\u0016J\u0010\u0010c\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020dH\u0016J\u0010\u0010e\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020fH\u0016J\u0010\u0010g\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020hH\u0016J\u0010\u0010i\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020jH\u0016J\u0010\u0010k\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020lH\u0016J\u0010\u0010m\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020nH\u0016J\u0010\u0010o\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020pH\u0016J\u0010\u0010q\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020rH\u0016J\u0010\u0010s\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020tH\u0016J\u0010\u0010u\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020vH\u0016J\u0010\u0010w\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020xH\u0016J\u0010\u0010y\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020zH\u0016J\u0010\u0010{\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020|H\u0016J\u0010\u0010}\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020~H\u0016J\u0011\u0010\u007f\u001a\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0080\u0001H\u0016J\u0012\u0010\u0081\u0001\u001a\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0082\u0001H\u0016J\u001d\u0010\u0006\u001a\u00020\u00022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\n2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\f2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\r\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u000e2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00102\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00122\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00142\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00162\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00182\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u001a2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u001c2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u001e2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020 2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010!\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\"2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010#\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020$2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010%\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020&2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010'\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020(2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010)\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020*2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010+\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020,2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010-\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020.2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010/\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u0002002\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u00101\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u0002022\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u00103\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u0002042\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u00105\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u0002062\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u00107\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u0002082\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u00109\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020:2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010;\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020<2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010=\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020>2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010?\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020@2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010A\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020B2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010C\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020D2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010E\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020F2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010G\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020H2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010I\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020J2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010K\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020L2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010M\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020N2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010O\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020P2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010Q\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020R2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010S\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020T2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010U\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020V2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010W\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020X2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010Y\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020Z2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010]\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020^2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010[\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\\2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010_\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020`2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010a\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020b2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010c\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020d2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010e\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020f2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010g\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020h2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010i\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020j2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010k\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020l2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010m\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020n2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010o\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020p2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010q\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020r2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010s\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020t2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010u\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020v2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010w\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020x2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010y\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020z2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010{\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020|2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u0019\u0010}\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020~2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u001a\u0010\u007f\u001a\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0080\u00012\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003J\u001b\u0010\u0081\u0001\u001a\u00020\u00022\u0007\u0010\u0007\u001a\u00030\u0082\u00012\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003¨\u0006\u0084\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitorVoid;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "()V", "visitNode", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitFunctionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;", "visitFunctionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;", "visitLocalFunctionDeclarationNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;", "visitSplitPostponedLambdasNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;", "visitPostponedLambdaExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;", "visitMergePostponedLambdaExitsNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/MergePostponedLambdaExitsNode;", "visitAnonymousFunctionCaptureNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;", "visitAnonymousFunctionExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;", "visitPropertyInitializerEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;", "visitPropertyInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;", "visitDelegateExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;", "visitInitBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;", "visitInitBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;", "visitBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;", "visitBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockExitNode;", "visitWhenEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;", "visitWhenExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;", "visitWhenBranchConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;", "visitWhenBranchConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;", "visitWhenBranchResultEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;", "visitWhenBranchResultExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;", "visitWhenSyntheticElseBranchNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;", "visitLoopEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;", "visitLoopBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;", "visitLoopBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;", "visitLoopConditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;", "visitLoopConditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;", "visitLoopExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;", "visitTryExpressionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;", "visitTryMainBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;", "visitTryMainBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;", "visitCatchClauseEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;", "visitCatchClauseExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;", "visitFinallyBlockEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;", "visitFinallyBlockExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;", "visitTryExpressionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;", "visitBooleanOperatorEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterNode;", "visitBooleanOperatorExitLeftOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitLeftOperandNode;", "visitBooleanOperatorEnterRightOperandNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterRightOperandNode;", "visitBooleanOperatorExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "visitTypeOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;", "visitComparisonExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;", "visitEqualityOperatorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;", "visitJumpNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;", "visitLiteralExpressionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;", "visitCheckNotNullCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;", "visitQualifiedAccessNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "visitResolvedQualifierNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;", "visitFunctionCallArgumentsEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;", "visitFunctionCallArgumentsExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;", "visitFunctionCallEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;", "visitFunctionCallExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "visitDelegatedConstructorCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;", "visitStringConcatenationCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;", "visitThrowExceptionNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;", "visitStubNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;", "visitVariableDeclarationEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "visitVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "visitEnterSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;", "visitExitSafeCallNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;", "data", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ControlFlowGraphVisitorVoid extends ControlFlowGraphVisitor {
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousFunctionCaptureNode(AnonymousFunctionCaptureNode anonymousFunctionCaptureNode, Object obj) {
        visitAnonymousFunctionCaptureNode(anonymousFunctionCaptureNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousFunctionExpressionNode(AnonymousFunctionExpressionNode anonymousFunctionExpressionNode, Object obj) {
        visitAnonymousFunctionExpressionNode(anonymousFunctionExpressionNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitBlockEnterNode(BlockEnterNode blockEnterNode, Object obj) {
        visitBlockEnterNode(blockEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitBlockExitNode(BlockExitNode blockExitNode, Object obj) {
        visitBlockExitNode(blockExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitBooleanOperatorEnterNode(BooleanOperatorEnterNode booleanOperatorEnterNode, Object obj) {
        visitBooleanOperatorEnterNode(booleanOperatorEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitBooleanOperatorEnterRightOperandNode(BooleanOperatorEnterRightOperandNode booleanOperatorEnterRightOperandNode, Object obj) {
        visitBooleanOperatorEnterRightOperandNode(booleanOperatorEnterRightOperandNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitBooleanOperatorExitLeftOperandNode(BooleanOperatorExitLeftOperandNode booleanOperatorExitLeftOperandNode, Object obj) {
        visitBooleanOperatorExitLeftOperandNode(booleanOperatorExitLeftOperandNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitBooleanOperatorExitNode(BooleanOperatorExitNode booleanOperatorExitNode, Object obj) {
        visitBooleanOperatorExitNode(booleanOperatorExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitCatchClauseEnterNode(CatchClauseEnterNode catchClauseEnterNode, Object obj) {
        visitCatchClauseEnterNode(catchClauseEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitCatchClauseExitNode(CatchClauseExitNode catchClauseExitNode, Object obj) {
        visitCatchClauseExitNode(catchClauseExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitCheckNotNullCallNode(CheckNotNullCallNode checkNotNullCallNode, Object obj) {
        visitCheckNotNullCallNode(checkNotNullCallNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitComparisonExpressionNode(ComparisonExpressionNode comparisonExpressionNode, Object obj) {
        visitComparisonExpressionNode(comparisonExpressionNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitDelegateExpressionExitNode(DelegateExpressionExitNode delegateExpressionExitNode, Object obj) {
        visitDelegateExpressionExitNode(delegateExpressionExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitDelegatedConstructorCallNode(DelegatedConstructorCallNode delegatedConstructorCallNode, Object obj) {
        visitDelegatedConstructorCallNode(delegatedConstructorCallNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitEnterSafeCallNode(EnterSafeCallNode enterSafeCallNode, Object obj) {
        visitEnterSafeCallNode(enterSafeCallNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitEqualityOperatorCallNode(EqualityOperatorCallNode equalityOperatorCallNode, Object obj) {
        visitEqualityOperatorCallNode(equalityOperatorCallNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitExitSafeCallNode(ExitSafeCallNode exitSafeCallNode, Object obj) {
        visitExitSafeCallNode(exitSafeCallNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFinallyBlockEnterNode(FinallyBlockEnterNode finallyBlockEnterNode, Object obj) {
        visitFinallyBlockEnterNode(finallyBlockEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFinallyBlockExitNode(FinallyBlockExitNode finallyBlockExitNode, Object obj) {
        visitFinallyBlockExitNode(finallyBlockExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFunctionCallArgumentsEnterNode(FunctionCallArgumentsEnterNode functionCallArgumentsEnterNode, Object obj) {
        visitFunctionCallArgumentsEnterNode(functionCallArgumentsEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFunctionCallArgumentsExitNode(FunctionCallArgumentsExitNode functionCallArgumentsExitNode, Object obj) {
        visitFunctionCallArgumentsExitNode(functionCallArgumentsExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFunctionCallEnterNode(FunctionCallEnterNode functionCallEnterNode, Object obj) {
        visitFunctionCallEnterNode(functionCallEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFunctionCallExitNode(FunctionCallExitNode functionCallExitNode, Object obj) {
        visitFunctionCallExitNode(functionCallExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFunctionEnterNode(FunctionEnterNode functionEnterNode, Object obj) {
        visitFunctionEnterNode(functionEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitFunctionExitNode(FunctionExitNode functionExitNode, Object obj) {
        visitFunctionExitNode(functionExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitInitBlockEnterNode(InitBlockEnterNode initBlockEnterNode, Object obj) {
        visitInitBlockEnterNode(initBlockEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitInitBlockExitNode(InitBlockExitNode initBlockExitNode, Object obj) {
        visitInitBlockExitNode(initBlockExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitJumpNode(JumpNode jumpNode, Object obj) {
        visitJumpNode(jumpNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLiteralExpressionNode(LiteralExpressionNode literalExpressionNode, Object obj) {
        visitLiteralExpressionNode(literalExpressionNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLocalFunctionDeclarationNode(LocalFunctionDeclarationNode localFunctionDeclarationNode, Object obj) {
        visitLocalFunctionDeclarationNode(localFunctionDeclarationNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLoopBlockEnterNode(LoopBlockEnterNode loopBlockEnterNode, Object obj) {
        visitLoopBlockEnterNode(loopBlockEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLoopBlockExitNode(LoopBlockExitNode loopBlockExitNode, Object obj) {
        visitLoopBlockExitNode(loopBlockExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLoopConditionEnterNode(LoopConditionEnterNode loopConditionEnterNode, Object obj) {
        visitLoopConditionEnterNode(loopConditionEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLoopConditionExitNode(LoopConditionExitNode loopConditionExitNode, Object obj) {
        visitLoopConditionExitNode(loopConditionExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLoopEnterNode(LoopEnterNode loopEnterNode, Object obj) {
        visitLoopEnterNode(loopEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitLoopExitNode(LoopExitNode loopExitNode, Object obj) {
        visitLoopExitNode(loopExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitMergePostponedLambdaExitsNode(MergePostponedLambdaExitsNode mergePostponedLambdaExitsNode, Object obj) {
        visitMergePostponedLambdaExitsNode(mergePostponedLambdaExitsNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitNode(CFGNode cFGNode, Object obj) {
        visitNode((CFGNode<?>) cFGNode, (Void) obj);
        return Unit.INSTANCE;
    }

    public abstract void visitNode(CFGNode<?> node);

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitPostponedLambdaExitNode(PostponedLambdaExitNode postponedLambdaExitNode, Object obj) {
        visitPostponedLambdaExitNode(postponedLambdaExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitPropertyInitializerEnterNode(PropertyInitializerEnterNode propertyInitializerEnterNode, Object obj) {
        visitPropertyInitializerEnterNode(propertyInitializerEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitPropertyInitializerExitNode(PropertyInitializerExitNode propertyInitializerExitNode, Object obj) {
        visitPropertyInitializerExitNode(propertyInitializerExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitQualifiedAccessNode(QualifiedAccessNode qualifiedAccessNode, Object obj) {
        visitQualifiedAccessNode(qualifiedAccessNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitResolvedQualifierNode(ResolvedQualifierNode resolvedQualifierNode, Object obj) {
        visitResolvedQualifierNode(resolvedQualifierNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitSplitPostponedLambdasNode(SplitPostponedLambdasNode splitPostponedLambdasNode, Object obj) {
        visitSplitPostponedLambdasNode(splitPostponedLambdasNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitStringConcatenationCallNode(StringConcatenationCallNode stringConcatenationCallNode, Object obj) {
        visitStringConcatenationCallNode(stringConcatenationCallNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitStubNode(StubNode stubNode, Object obj) {
        visitStubNode(stubNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitThrowExceptionNode(ThrowExceptionNode throwExceptionNode, Object obj) {
        visitThrowExceptionNode(throwExceptionNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitTryExpressionEnterNode(TryExpressionEnterNode tryExpressionEnterNode, Object obj) {
        visitTryExpressionEnterNode(tryExpressionEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitTryExpressionExitNode(TryExpressionExitNode tryExpressionExitNode, Object obj) {
        visitTryExpressionExitNode(tryExpressionExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitTryMainBlockEnterNode(TryMainBlockEnterNode tryMainBlockEnterNode, Object obj) {
        visitTryMainBlockEnterNode(tryMainBlockEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitTryMainBlockExitNode(TryMainBlockExitNode tryMainBlockExitNode, Object obj) {
        visitTryMainBlockExitNode(tryMainBlockExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitTypeOperatorCallNode(TypeOperatorCallNode typeOperatorCallNode, Object obj) {
        visitTypeOperatorCallNode(typeOperatorCallNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitVariableAssignmentNode(VariableAssignmentNode variableAssignmentNode, Object obj) {
        visitVariableAssignmentNode(variableAssignmentNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitVariableDeclarationEnterNode(VariableDeclarationEnterNode variableDeclarationEnterNode, Object obj) {
        visitVariableDeclarationEnterNode(variableDeclarationEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitVariableDeclarationExitNode(VariableDeclarationExitNode variableDeclarationExitNode, Object obj) {
        visitVariableDeclarationExitNode(variableDeclarationExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitWhenBranchConditionEnterNode(WhenBranchConditionEnterNode whenBranchConditionEnterNode, Object obj) {
        visitWhenBranchConditionEnterNode(whenBranchConditionEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitWhenBranchConditionExitNode(WhenBranchConditionExitNode whenBranchConditionExitNode, Object obj) {
        visitWhenBranchConditionExitNode(whenBranchConditionExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitWhenBranchResultEnterNode(WhenBranchResultEnterNode whenBranchResultEnterNode, Object obj) {
        visitWhenBranchResultEnterNode(whenBranchResultEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitWhenBranchResultExitNode(WhenBranchResultExitNode whenBranchResultExitNode, Object obj) {
        visitWhenBranchResultExitNode(whenBranchResultExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitWhenEnterNode(WhenEnterNode whenEnterNode, Object obj) {
        visitWhenEnterNode(whenEnterNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitWhenExitNode(WhenExitNode whenExitNode, Object obj) {
        visitWhenExitNode(whenExitNode, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitWhenSyntheticElseBranchNode(WhenSyntheticElseBranchNode whenSyntheticElseBranchNode, Object obj) {
        visitWhenSyntheticElseBranchNode(whenSyntheticElseBranchNode, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitAnonymousFunctionCaptureNode(AnonymousFunctionCaptureNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitAnonymousFunctionExpressionNode(AnonymousFunctionExpressionNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitBlockEnterNode(BlockEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitBlockExitNode(BlockExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitBooleanOperatorEnterNode(BooleanOperatorEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitBooleanOperatorEnterRightOperandNode(BooleanOperatorEnterRightOperandNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitBooleanOperatorExitLeftOperandNode(BooleanOperatorExitLeftOperandNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitBooleanOperatorExitNode(BooleanOperatorExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitCatchClauseEnterNode(CatchClauseEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitCatchClauseExitNode(CatchClauseExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitCheckNotNullCallNode(CheckNotNullCallNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitComparisonExpressionNode(ComparisonExpressionNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitDelegateExpressionExitNode(DelegateExpressionExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitDelegatedConstructorCallNode(DelegatedConstructorCallNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitEnterSafeCallNode(EnterSafeCallNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitEqualityOperatorCallNode(EqualityOperatorCallNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitExitSafeCallNode(ExitSafeCallNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFinallyBlockEnterNode(FinallyBlockEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFinallyBlockExitNode(FinallyBlockExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFunctionCallArgumentsEnterNode(FunctionCallArgumentsEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFunctionCallArgumentsExitNode(FunctionCallArgumentsExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFunctionCallEnterNode(FunctionCallEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFunctionCallExitNode(FunctionCallExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFunctionEnterNode(FunctionEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitFunctionExitNode(FunctionExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitInitBlockEnterNode(InitBlockEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitInitBlockExitNode(InitBlockExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitJumpNode(JumpNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLiteralExpressionNode(LiteralExpressionNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLocalFunctionDeclarationNode(LocalFunctionDeclarationNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLoopBlockEnterNode(LoopBlockEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLoopBlockExitNode(LoopBlockExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLoopConditionEnterNode(LoopConditionEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLoopConditionExitNode(LoopConditionExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLoopEnterNode(LoopEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitLoopExitNode(LoopExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitMergePostponedLambdaExitsNode(MergePostponedLambdaExitsNode node) {
        node.getClass();
        visitNode(node);
    }

    public final void visitNode(CFGNode<?> node, Void data) {
        node.getClass();
        visitNode(node);
    }

    public void visitPostponedLambdaExitNode(PostponedLambdaExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitPropertyInitializerEnterNode(PropertyInitializerEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitPropertyInitializerExitNode(PropertyInitializerExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitQualifiedAccessNode(QualifiedAccessNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitResolvedQualifierNode(ResolvedQualifierNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitSplitPostponedLambdasNode(SplitPostponedLambdasNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitStringConcatenationCallNode(StringConcatenationCallNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitStubNode(StubNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitThrowExceptionNode(ThrowExceptionNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitTryExpressionEnterNode(TryExpressionEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitTryExpressionExitNode(TryExpressionExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitTryMainBlockEnterNode(TryMainBlockEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitTryMainBlockExitNode(TryMainBlockExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitTypeOperatorCallNode(TypeOperatorCallNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitVariableAssignmentNode(VariableAssignmentNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitVariableDeclarationEnterNode(VariableDeclarationEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitVariableDeclarationExitNode(VariableDeclarationExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitWhenBranchConditionEnterNode(WhenBranchConditionEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitWhenBranchConditionExitNode(WhenBranchConditionExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitWhenBranchResultEnterNode(WhenBranchResultEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitWhenBranchResultExitNode(WhenBranchResultExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitWhenEnterNode(WhenEnterNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitWhenExitNode(WhenExitNode node) {
        node.getClass();
        visitNode(node);
    }

    public void visitWhenSyntheticElseBranchNode(WhenSyntheticElseBranchNode node) {
        node.getClass();
        visitNode(node);
    }

    public final void visitAnonymousFunctionCaptureNode(AnonymousFunctionCaptureNode node, Void data) {
        node.getClass();
        visitAnonymousFunctionCaptureNode(node);
    }

    public final void visitAnonymousFunctionExpressionNode(AnonymousFunctionExpressionNode node, Void data) {
        node.getClass();
        visitAnonymousFunctionExpressionNode(node);
    }

    public final void visitBlockEnterNode(BlockEnterNode node, Void data) {
        node.getClass();
        visitBlockEnterNode(node);
    }

    public final void visitBlockExitNode(BlockExitNode node, Void data) {
        node.getClass();
        visitBlockExitNode(node);
    }

    public final void visitBooleanOperatorEnterNode(BooleanOperatorEnterNode node, Void data) {
        node.getClass();
        visitBooleanOperatorEnterNode(node);
    }

    public final void visitBooleanOperatorEnterRightOperandNode(BooleanOperatorEnterRightOperandNode node, Void data) {
        node.getClass();
        visitBooleanOperatorEnterRightOperandNode(node);
    }

    public final void visitBooleanOperatorExitLeftOperandNode(BooleanOperatorExitLeftOperandNode node, Void data) {
        node.getClass();
        visitBooleanOperatorExitLeftOperandNode(node);
    }

    public final void visitBooleanOperatorExitNode(BooleanOperatorExitNode node, Void data) {
        node.getClass();
        visitBooleanOperatorExitNode(node);
    }

    public final void visitCatchClauseEnterNode(CatchClauseEnterNode node, Void data) {
        node.getClass();
        visitCatchClauseEnterNode(node);
    }

    public final void visitCatchClauseExitNode(CatchClauseExitNode node, Void data) {
        node.getClass();
        visitCatchClauseExitNode(node);
    }

    public final void visitCheckNotNullCallNode(CheckNotNullCallNode node, Void data) {
        node.getClass();
        visitCheckNotNullCallNode(node);
    }

    public final void visitComparisonExpressionNode(ComparisonExpressionNode node, Void data) {
        node.getClass();
        visitComparisonExpressionNode(node);
    }

    public final void visitDelegateExpressionExitNode(DelegateExpressionExitNode node, Void data) {
        node.getClass();
        visitDelegateExpressionExitNode(node);
    }

    public final void visitDelegatedConstructorCallNode(DelegatedConstructorCallNode node, Void data) {
        node.getClass();
        visitDelegatedConstructorCallNode(node);
    }

    public final void visitEnterSafeCallNode(EnterSafeCallNode node, Void data) {
        node.getClass();
        visitEnterSafeCallNode(node);
    }

    public final void visitEqualityOperatorCallNode(EqualityOperatorCallNode node, Void data) {
        node.getClass();
        visitEqualityOperatorCallNode(node);
    }

    public final void visitExitSafeCallNode(ExitSafeCallNode node, Void data) {
        node.getClass();
        visitExitSafeCallNode(node);
    }

    public final void visitFinallyBlockEnterNode(FinallyBlockEnterNode node, Void data) {
        node.getClass();
        visitFinallyBlockEnterNode(node);
    }

    public final void visitFinallyBlockExitNode(FinallyBlockExitNode node, Void data) {
        node.getClass();
        visitFinallyBlockExitNode(node);
    }

    public final void visitFunctionCallArgumentsEnterNode(FunctionCallArgumentsEnterNode node, Void data) {
        node.getClass();
        visitFunctionCallArgumentsEnterNode(node);
    }

    public final void visitFunctionCallArgumentsExitNode(FunctionCallArgumentsExitNode node, Void data) {
        node.getClass();
        visitFunctionCallArgumentsExitNode(node);
    }

    public final void visitFunctionCallEnterNode(FunctionCallEnterNode node, Void data) {
        node.getClass();
        visitFunctionCallEnterNode(node);
    }

    public final void visitFunctionCallExitNode(FunctionCallExitNode node, Void data) {
        node.getClass();
        visitFunctionCallExitNode(node);
    }

    public final void visitFunctionEnterNode(FunctionEnterNode node, Void data) {
        node.getClass();
        visitFunctionEnterNode(node);
    }

    public final void visitFunctionExitNode(FunctionExitNode node, Void data) {
        node.getClass();
        visitFunctionExitNode(node);
    }

    public final void visitInitBlockEnterNode(InitBlockEnterNode node, Void data) {
        node.getClass();
        visitInitBlockEnterNode(node);
    }

    public final void visitInitBlockExitNode(InitBlockExitNode node, Void data) {
        node.getClass();
        visitInitBlockExitNode(node);
    }

    public final void visitJumpNode(JumpNode node, Void data) {
        node.getClass();
        visitJumpNode(node);
    }

    public final void visitLiteralExpressionNode(LiteralExpressionNode node, Void data) {
        node.getClass();
        visitLiteralExpressionNode(node);
    }

    public final void visitLocalFunctionDeclarationNode(LocalFunctionDeclarationNode node, Void data) {
        node.getClass();
        visitLocalFunctionDeclarationNode(node);
    }

    public final void visitLoopBlockEnterNode(LoopBlockEnterNode node, Void data) {
        node.getClass();
        visitLoopBlockEnterNode(node);
    }

    public final void visitLoopBlockExitNode(LoopBlockExitNode node, Void data) {
        node.getClass();
        visitLoopBlockExitNode(node);
    }

    public final void visitLoopConditionEnterNode(LoopConditionEnterNode node, Void data) {
        node.getClass();
        visitLoopConditionEnterNode(node);
    }

    public final void visitLoopConditionExitNode(LoopConditionExitNode node, Void data) {
        node.getClass();
        visitLoopConditionExitNode(node);
    }

    public final void visitLoopEnterNode(LoopEnterNode node, Void data) {
        node.getClass();
        visitLoopEnterNode(node);
    }

    public final void visitLoopExitNode(LoopExitNode node, Void data) {
        node.getClass();
        visitLoopExitNode(node);
    }

    public final void visitMergePostponedLambdaExitsNode(MergePostponedLambdaExitsNode node, Void data) {
        node.getClass();
        visitMergePostponedLambdaExitsNode(node);
    }

    public final void visitPostponedLambdaExitNode(PostponedLambdaExitNode node, Void data) {
        node.getClass();
        visitPostponedLambdaExitNode(node);
    }

    public final void visitPropertyInitializerEnterNode(PropertyInitializerEnterNode node, Void data) {
        node.getClass();
        visitPropertyInitializerEnterNode(node);
    }

    public final void visitPropertyInitializerExitNode(PropertyInitializerExitNode node, Void data) {
        node.getClass();
        visitPropertyInitializerExitNode(node);
    }

    public final void visitQualifiedAccessNode(QualifiedAccessNode node, Void data) {
        node.getClass();
        visitQualifiedAccessNode(node);
    }

    public final void visitResolvedQualifierNode(ResolvedQualifierNode node, Void data) {
        node.getClass();
        visitResolvedQualifierNode(node);
    }

    public final void visitSplitPostponedLambdasNode(SplitPostponedLambdasNode node, Void data) {
        node.getClass();
        visitSplitPostponedLambdasNode(node);
    }

    public final void visitStringConcatenationCallNode(StringConcatenationCallNode node, Void data) {
        node.getClass();
        visitStringConcatenationCallNode(node);
    }

    public final void visitStubNode(StubNode node, Void data) {
        node.getClass();
        visitStubNode(node);
    }

    public final void visitThrowExceptionNode(ThrowExceptionNode node, Void data) {
        node.getClass();
        visitThrowExceptionNode(node);
    }

    public final void visitTryExpressionEnterNode(TryExpressionEnterNode node, Void data) {
        node.getClass();
        visitTryExpressionEnterNode(node);
    }

    public final void visitTryExpressionExitNode(TryExpressionExitNode node, Void data) {
        node.getClass();
        visitTryExpressionExitNode(node);
    }

    public final void visitTryMainBlockEnterNode(TryMainBlockEnterNode node, Void data) {
        node.getClass();
        visitTryMainBlockEnterNode(node);
    }

    public final void visitTryMainBlockExitNode(TryMainBlockExitNode node, Void data) {
        node.getClass();
        visitTryMainBlockExitNode(node);
    }

    public final void visitTypeOperatorCallNode(TypeOperatorCallNode node, Void data) {
        node.getClass();
        visitTypeOperatorCallNode(node);
    }

    public final void visitVariableAssignmentNode(VariableAssignmentNode node, Void data) {
        node.getClass();
        visitVariableAssignmentNode(node);
    }

    public final void visitVariableDeclarationEnterNode(VariableDeclarationEnterNode node, Void data) {
        node.getClass();
        visitVariableDeclarationEnterNode(node);
    }

    public final void visitVariableDeclarationExitNode(VariableDeclarationExitNode node, Void data) {
        node.getClass();
        visitVariableDeclarationExitNode(node);
    }

    public final void visitWhenBranchConditionEnterNode(WhenBranchConditionEnterNode node, Void data) {
        node.getClass();
        visitWhenBranchConditionEnterNode(node);
    }

    public final void visitWhenBranchConditionExitNode(WhenBranchConditionExitNode node, Void data) {
        node.getClass();
        visitWhenBranchConditionExitNode(node);
    }

    public final void visitWhenBranchResultEnterNode(WhenBranchResultEnterNode node, Void data) {
        node.getClass();
        visitWhenBranchResultEnterNode(node);
    }

    public final void visitWhenBranchResultExitNode(WhenBranchResultExitNode node, Void data) {
        node.getClass();
        visitWhenBranchResultExitNode(node);
    }

    public final void visitWhenEnterNode(WhenEnterNode node, Void data) {
        node.getClass();
        visitWhenEnterNode(node);
    }

    public final void visitWhenExitNode(WhenExitNode node, Void data) {
        node.getClass();
        visitWhenExitNode(node);
    }

    public final void visitWhenSyntheticElseBranchNode(WhenSyntheticElseBranchNode node, Void data) {
        node.getClass();
        visitWhenSyntheticElseBranchNode(node);
    }
}
