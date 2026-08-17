package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.ParagraphStyle;
import androidx.compose.ui.text.SaversKt;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLinkStyles;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.UrlAnnotation;
import androidx.compose.ui.text.VerbatimTtsAnnotation;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0003\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aI\u0010\u0000\u001a\u00020\u0001\"\u0014\b\u0000\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u0001H\u00042\u0006\u0010\u0007\u001a\u0002H\u00022\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\u0010\n\u001aL\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\u0014\b\u0000\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u0005\"\u0006\b\u0003\u0010\f\u0018\u00012\b\u0010\u0006\u001a\u0004\u0018\u0001H\u00052\u0006\u0010\u0007\u001a\u0002H\u0002H\u0080\b¢\u0006\u0002\u0010\r\u001ay\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u000f\"\u0004\b\u0000\u0010\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00012.\u0010\u0000\u001a*\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u0010¢\u0006\u0002\b\u00132#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u0011H\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u0001H\u00040\u0014H\u0002\u001a\u001f\u0010\u0000\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u00022\b\u0010\u0006\u001a\u0004\u0018\u0001H\u0002H\u0000¢\u0006\u0002\u0010\u0015\u001a\"\u0010\u000b\u001a\u0004\u0018\u0001H\f\"\u0006\b\u0000\u0010\f\u0018\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0080\b¢\u0006\u0002\u0010\u0015\" \u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"(\u0010\u001a\u001a\u001c\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001c0\u001b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"(\u0010\u001d\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001c\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u001f\"\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u001f\"\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010)\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0019\" \u0010,\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0019\" \u0010/\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0019\"$\u00102\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010\u0003*\u0002048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u00106\"\u001a\u00107\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u0003*\u0002098@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010:\"\u001a\u0010;\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00010\u0003*\u00020=8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010>\"\u001a\u0010?\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u00010\u0003*\u00020A8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010B\"\u001a\u0010C\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u00010\u0003*\u00020E8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010F\"\u001a\u0010G\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010\u0003*\u00020I8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010J\"\u001a\u0010K\u001a\u000e\u0012\u0004\u0012\u00020H\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u00010\u0003*\u00020M8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010N\"\u001a\u0010O\u001a\u000e\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020\u00010\u0003*\u00020Q8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010R\"\u001a\u0010S\u001a\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00010\u0003*\u00020U8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010V\"\u001a\u0010W\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\u00010\u0003*\u00020Y8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010Z\"\u001a\u0010[\u001a\u000e\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020\u00010\u0003*\u00020]8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010^\"\u001a\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020\u00010\u0003*\u00020a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010b\" \u0010c\u001a\u000e\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0019\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020e\u0012\u0004\u0012\u00020\u00010\u0003*\u00020f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010g\" \u0010h\u001a\u000e\u0012\u0004\u0012\u00020e\u0012\u0004\u0012\u00020\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bi\u0010\u0019\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020\u00010\u0003*\u00020k8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010l\"\u001a\u0010m\u001a\u000e\u0012\u0004\u0012\u00020j\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020n\u0012\u0004\u0012\u00020\u00010\u0003*\u00020o8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010p\" \u0010q\u001a\u000e\u0012\u0004\u0012\u00020n\u0012\u0004\u0012\u00020\u00010\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010s\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020t\u0012\u0004\u0012\u00020\u00010\u0003*\u00020u8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010v\"\u001a\u0010w\u001a\u000e\u0012\u0004\u0012\u00020t\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u00010\u0003*\u00020y8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010z\"\u001a\u0010{\u001a\u000e\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u00102\u001a\u000e\u0012\u0004\u0012\u00020|\u0012\u0004\u0012\u00020\u00010\u0003*\u00020}8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010~\"\u001a\u0010\u007f\u001a\u000e\u0012\u0004\u0012\u00020|\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u0080\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u0081\u00018@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u0082\u0001\"\u001c\u0010\u0083\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0080\u0001\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u0084\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u0085\u00018BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u0086\u0001\"\u001c\u0010\u0087\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0084\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u0088\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u0089\u00018BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u008a\u0001\"\u001c\u0010\u008b\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u0088\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\"'\u00102\u001a\u000f\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u0004\u0012\u00020\u00010\u0003*\u00030\u008d\u00018BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b5\u0010\u008e\u0001\"\u001c\u0010\u008f\u0001\u001a\u000f\u0012\u0005\u0012\u00030\u008c\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0090\u0001"}, d2 = {"save", "", "T", "Landroidx/compose/runtime/saveable/Saver;", "Original", "Saveable", "value", "saver", "scope", "Landroidx/compose/runtime/saveable/SaverScope;", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Landroidx/compose/runtime/saveable/SaverScope;)Ljava/lang/Object;", "restore", "Result", "(Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;)Ljava/lang/Object;", "NonNullValueClassSaver", "Landroidx/compose/ui/text/NonNullValueClassSaver;", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lkotlin/ExtensionFunctionType;", "Lkotlin/Function1;", "(Ljava/lang/Object;)Ljava/lang/Object;", "AnnotatedStringSaver", "Landroidx/compose/ui/text/AnnotatedString;", "getAnnotatedStringSaver", "()Landroidx/compose/runtime/saveable/Saver;", "AnnotationRangeListSaver", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "AnnotationRangeSaver", "getAnnotationRangeSaver$annotations", "()V", "VerbatimTtsAnnotationSaver", "Landroidx/compose/ui/text/VerbatimTtsAnnotation;", "UrlAnnotationSaver", "Landroidx/compose/ui/text/UrlAnnotation;", "getUrlAnnotationSaver$annotations", "LinkSaver", "Landroidx/compose/ui/text/LinkAnnotation$Url;", "ClickableSaver", "Landroidx/compose/ui/text/LinkAnnotation$Clickable;", "ParagraphStyleSaver", "Landroidx/compose/ui/text/ParagraphStyle;", "getParagraphStyleSaver", "SpanStyleSaver", "Landroidx/compose/ui/text/SpanStyle;", "getSpanStyleSaver", "TextLinkStylesSaver", "Landroidx/compose/ui/text/TextLinkStyles;", "getTextLinkStylesSaver", "Saver", "Landroidx/compose/ui/text/style/TextDecoration;", "Landroidx/compose/ui/text/style/TextDecoration$Companion;", "getSaver", "(Landroidx/compose/ui/text/style/TextDecoration$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextDecorationSaver", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;", "(Landroidx/compose/ui/text/style/TextGeometricTransform$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextGeometricTransformSaver", "Landroidx/compose/ui/text/style/TextIndent;", "Landroidx/compose/ui/text/style/TextIndent$Companion;", "(Landroidx/compose/ui/text/style/TextIndent$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextIndentSaver", "Landroidx/compose/ui/text/font/FontWeight;", "Landroidx/compose/ui/text/font/FontWeight$Companion;", "(Landroidx/compose/ui/text/font/FontWeight$Companion;)Landroidx/compose/runtime/saveable/Saver;", "FontWeightSaver", "Landroidx/compose/ui/text/style/BaselineShift;", "Landroidx/compose/ui/text/style/BaselineShift$Companion;", "(Landroidx/compose/ui/text/style/BaselineShift$Companion;)Landroidx/compose/runtime/saveable/Saver;", "BaselineShiftSaver", "Landroidx/compose/ui/text/TextRange;", "Landroidx/compose/ui/text/TextRange$Companion;", "(Landroidx/compose/ui/text/TextRange$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextRangeSaver", "Landroidx/compose/ui/graphics/Shadow;", "Landroidx/compose/ui/graphics/Shadow$Companion;", "(Landroidx/compose/ui/graphics/Shadow$Companion;)Landroidx/compose/runtime/saveable/Saver;", "ShadowSaver", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/ui/graphics/Color$Companion;", "(Landroidx/compose/ui/graphics/Color$Companion;)Landroidx/compose/runtime/saveable/Saver;", "ColorSaver", "Landroidx/compose/ui/text/style/TextAlign;", "Landroidx/compose/ui/text/style/TextAlign$Companion;", "(Landroidx/compose/ui/text/style/TextAlign$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextAlignSaver", "Landroidx/compose/ui/text/style/TextDirection;", "Landroidx/compose/ui/text/style/TextDirection$Companion;", "(Landroidx/compose/ui/text/style/TextDirection$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextDirectionSaver", "Landroidx/compose/ui/text/style/Hyphens;", "Landroidx/compose/ui/text/style/Hyphens$Companion;", "(Landroidx/compose/ui/text/style/Hyphens$Companion;)Landroidx/compose/runtime/saveable/Saver;", "HyphensSaver", "Landroidx/compose/ui/text/font/FontStyle;", "Landroidx/compose/ui/text/font/FontStyle$Companion;", "(Landroidx/compose/ui/text/font/FontStyle$Companion;)Landroidx/compose/runtime/saveable/Saver;", "FontStyleSaver", "getFontStyleSaver", "Landroidx/compose/ui/text/font/FontSynthesis;", "Landroidx/compose/ui/text/font/FontSynthesis$Companion;", "(Landroidx/compose/ui/text/font/FontSynthesis$Companion;)Landroidx/compose/runtime/saveable/Saver;", "FontSynthesisSaver", "getFontSynthesisSaver", "Landroidx/compose/ui/unit/TextUnit;", "Landroidx/compose/ui/unit/TextUnit$Companion;", "(Landroidx/compose/ui/unit/TextUnit$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextUnitSaver", "Landroidx/compose/ui/unit/TextUnitType;", "Landroidx/compose/ui/unit/TextUnitType$Companion;", "(Landroidx/compose/ui/unit/TextUnitType$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextUnitTypeSaver", "getTextUnitTypeSaver", "()Landroidx/compose/ui/text/NonNullValueClassSaver;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/geometry/Offset$Companion;", "(Landroidx/compose/ui/geometry/Offset$Companion;)Landroidx/compose/runtime/saveable/Saver;", "OffsetSaver", "Landroidx/compose/ui/text/intl/LocaleList;", "Landroidx/compose/ui/text/intl/LocaleList$Companion;", "(Landroidx/compose/ui/text/intl/LocaleList$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LocaleListSaver", "Landroidx/compose/ui/text/intl/Locale;", "Landroidx/compose/ui/text/intl/Locale$Companion;", "(Landroidx/compose/ui/text/intl/Locale$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LocaleSaver", "Landroidx/compose/ui/text/style/LineHeightStyle;", "Landroidx/compose/ui/text/style/LineHeightStyle$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleSaver", "Landroidx/compose/ui/text/style/LineHeightStyle$Alignment;", "Landroidx/compose/ui/text/style/LineHeightStyle$Alignment$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Alignment$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleAlignmentSaver", "Landroidx/compose/ui/text/style/LineHeightStyle$Trim;", "Landroidx/compose/ui/text/style/LineHeightStyle$Trim$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Trim$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleTrimSaver", "Landroidx/compose/ui/text/style/LineHeightStyle$Mode;", "Landroidx/compose/ui/text/style/LineHeightStyle$Mode$Companion;", "(Landroidx/compose/ui/text/style/LineHeightStyle$Mode$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineHeightStyleModeSaver", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SaversKt {
    private static final Saver<AnnotatedString, Object> AnnotatedStringSaver = SaverKt.Saver(new Function2() { // from class: csc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.n((SaverScope) obj, (AnnotatedString) obj2);
        }
    }, new Function1() { // from class: esc
        public final Object invoke(Object obj) {
            return SaversKt.k(obj);
        }
    });
    private static final Saver<List<AnnotatedString.Range<? extends Object>>, Object> AnnotationRangeListSaver = SaverKt.Saver(new Function2() { // from class: qsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.g0((SaverScope) obj, (List) obj2);
        }
    }, new Function1() { // from class: ctc
        public final Object invoke(Object obj) {
            return SaversKt.f0(obj);
        }
    });
    private static final Saver<AnnotatedString.Range<? extends Object>, Object> AnnotationRangeSaver = SaverKt.Saver(new Function2() { // from class: otc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.Q((SaverScope) obj, (AnnotatedString.Range) obj2);
        }
    }, new Function1() { // from class: auc
        public final Object invoke(Object obj) {
            return SaversKt.A(obj);
        }
    });
    private static final Saver<VerbatimTtsAnnotation, Object> VerbatimTtsAnnotationSaver = SaverKt.Saver(new Function2() { // from class: duc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.r((SaverScope) obj, (VerbatimTtsAnnotation) obj2);
        }
    }, new Function1() { // from class: euc
        public final Object invoke(Object obj) {
            return SaversKt.a(obj);
        }
    });
    private static final Saver<UrlAnnotation, Object> UrlAnnotationSaver = SaverKt.Saver(new Function2() { // from class: guc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.H((SaverScope) obj, (UrlAnnotation) obj2);
        }
    }, new Function1() { // from class: huc
        public final Object invoke(Object obj) {
            return SaversKt.y(obj);
        }
    });
    private static final Saver<LinkAnnotation.Url, Object> LinkSaver = SaverKt.Saver(new Function2() { // from class: nsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.Y((SaverScope) obj, (LinkAnnotation.Url) obj2);
        }
    }, new Function1() { // from class: ysc
        public final Object invoke(Object obj) {
            return SaversKt.J(obj);
        }
    });
    private static final Saver<LinkAnnotation.Clickable, Object> ClickableSaver = SaverKt.Saver(new Function2() { // from class: jtc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.t((SaverScope) obj, (LinkAnnotation.Clickable) obj2);
        }
    }, new Function1() { // from class: utc
        public final Object invoke(Object obj) {
            return SaversKt.i0(obj);
        }
    });
    private static final Saver<ParagraphStyle, Object> ParagraphStyleSaver = SaverKt.Saver(new Function2() { // from class: fuc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.e0((SaverScope) obj, (ParagraphStyle) obj2);
        }
    }, new Function1() { // from class: iuc
        public final Object invoke(Object obj) {
            return SaversKt.d0(obj);
        }
    });
    private static final Saver<SpanStyle, Object> SpanStyleSaver = SaverKt.Saver(new Function2() { // from class: juc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.j((SaverScope) obj, (SpanStyle) obj2);
        }
    }, new Function1() { // from class: kuc
        public final Object invoke(Object obj) {
            return SaversKt.U(obj);
        }
    });
    private static final Saver<TextLinkStyles, Object> TextLinkStylesSaver = SaverKt.Saver(new Function2() { // from class: luc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.s((SaverScope) obj, (TextLinkStyles) obj2);
        }
    }, new Function1() { // from class: dsc
        public final Object invoke(Object obj) {
            return SaversKt.x(obj);
        }
    });
    private static final Saver<TextDecoration, Object> TextDecorationSaver = SaverKt.Saver(new Function2() { // from class: fsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.p((SaverScope) obj, (TextDecoration) obj2);
        }
    }, new Function1() { // from class: gsc
        public final Object invoke(Object obj) {
            return SaversKt.G(obj);
        }
    });
    private static final Saver<TextGeometricTransform, Object> TextGeometricTransformSaver = SaverKt.Saver(new Function2() { // from class: hsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.g((SaverScope) obj, (TextGeometricTransform) obj2);
        }
    }, new Function1() { // from class: isc
        public final Object invoke(Object obj) {
            return SaversKt.j0(obj);
        }
    });
    private static final Saver<TextIndent, Object> TextIndentSaver = SaverKt.Saver(new Function2() { // from class: jsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.e((SaverScope) obj, (TextIndent) obj2);
        }
    }, new Function1() { // from class: ksc
        public final Object invoke(Object obj) {
            return SaversKt.D(obj);
        }
    });
    private static final Saver<FontWeight, Object> FontWeightSaver = SaverKt.Saver(new Function2() { // from class: lsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.W((SaverScope) obj, (FontWeight) obj2);
        }
    }, new Function1() { // from class: msc
        public final Object invoke(Object obj) {
            return SaversKt.B(obj);
        }
    });
    private static final Saver<BaselineShift, Object> BaselineShiftSaver = SaverKt.Saver(new Function2() { // from class: osc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.Z((SaverScope) obj, (BaselineShift) obj2);
        }
    }, new Function1() { // from class: psc
        public final Object invoke(Object obj) {
            return SaversKt.c0(obj);
        }
    });
    private static final Saver<TextRange, Object> TextRangeSaver = SaverKt.Saver(new Function2() { // from class: rsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.X((SaverScope) obj, (TextRange) obj2);
        }
    }, new Function1() { // from class: ssc
        public final Object invoke(Object obj) {
            return SaversKt.w(obj);
        }
    });
    private static final Saver<Shadow, Object> ShadowSaver = SaverKt.Saver(new Function2() { // from class: tsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.v((SaverScope) obj, (Shadow) obj2);
        }
    }, new Function1() { // from class: usc
        public final Object invoke(Object obj) {
            return SaversKt.L(obj);
        }
    });
    private static final NonNullValueClassSaver<Color, Object> ColorSaver = NonNullValueClassSaver(new Function2<SaverScope, Color, Object>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$1
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m5408invoke4WTKRHQ((SaverScope) obj, ((Color) obj2).m3144unboximpl());
        }

        /* JADX INFO: renamed from: invoke-4WTKRHQ, reason: not valid java name */
        public final Object m5408invoke4WTKRHQ(SaverScope saverScope, long j) {
            return j == 16 ? Boolean.FALSE : Integer.valueOf(ColorKt.m3188toArgb8_81llA(j));
        }
    }, new Function1<Object, Color>() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$2
        /* JADX INFO: renamed from: invoke-ijrfgN4, reason: not valid java name and merged with bridge method [inline-methods] */
        public final Color invoke(Object obj) {
            if (Intrinsics.areEqual(obj, Boolean.FALSE)) {
                return Color.m3124boximpl(Color.INSTANCE.m3170getUnspecified0d7_KjU());
            }
            obj.getClass();
            return Color.m3124boximpl(ColorKt.Color(((Integer) obj).intValue()));
        }
    });
    private static final NonNullValueClassSaver<TextAlign, Object> TextAlignSaver = NonNullValueClassSaver(new Function2() { // from class: vsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.C((SaverScope) obj, (TextAlign) obj2);
        }
    }, new Function1() { // from class: wsc
        public final Object invoke(Object obj) {
            return SaversKt.h(obj);
        }
    });
    private static final NonNullValueClassSaver<TextDirection, Object> TextDirectionSaver = NonNullValueClassSaver(new Function2() { // from class: xsc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.h0((SaverScope) obj, (TextDirection) obj2);
        }
    }, new Function1() { // from class: zsc
        public final Object invoke(Object obj) {
            return SaversKt.b0(obj);
        }
    });
    private static final NonNullValueClassSaver<Hyphens, Object> HyphensSaver = NonNullValueClassSaver(new Function2() { // from class: atc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.c((SaverScope) obj, (Hyphens) obj2);
        }
    }, new Function1() { // from class: btc
        public final Object invoke(Object obj) {
            return SaversKt.I(obj);
        }
    });
    private static final Saver<FontStyle, Object> FontStyleSaver = SaverKt.Saver(new Function2() { // from class: dtc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.m((SaverScope) obj, (FontStyle) obj2);
        }
    }, new Function1() { // from class: etc
        public final Object invoke(Object obj) {
            return SaversKt.b(obj);
        }
    });
    private static final Saver<FontSynthesis, Object> FontSynthesisSaver = SaverKt.Saver(new Function2() { // from class: ftc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.i((SaverScope) obj, (FontSynthesis) obj2);
        }
    }, new Function1() { // from class: gtc
        public final Object invoke(Object obj) {
            return SaversKt.d(obj);
        }
    });
    private static final NonNullValueClassSaver<TextUnit, Object> TextUnitSaver = NonNullValueClassSaver(new Function2() { // from class: htc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.N((SaverScope) obj, (TextUnit) obj2);
        }
    }, new Function1() { // from class: itc
        public final Object invoke(Object obj) {
            return SaversKt.S(obj);
        }
    });
    private static final NonNullValueClassSaver<TextUnitType, Object> TextUnitTypeSaver = NonNullValueClassSaver(new Function2() { // from class: ktc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.u((SaverScope) obj, (TextUnitType) obj2);
        }
    }, new Function1() { // from class: ltc
        public final Object invoke(Object obj) {
            return SaversKt.T(obj);
        }
    });
    private static final NonNullValueClassSaver<Offset, Object> OffsetSaver = NonNullValueClassSaver(new Function2() { // from class: mtc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.K((SaverScope) obj, (Offset) obj2);
        }
    }, new Function1() { // from class: ntc
        public final Object invoke(Object obj) {
            return SaversKt.q(obj);
        }
    });
    private static final Saver<LocaleList, Object> LocaleListSaver = SaverKt.Saver(new Function2() { // from class: ptc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.M((SaverScope) obj, (LocaleList) obj2);
        }
    }, new Function1() { // from class: qtc
        public final Object invoke(Object obj) {
            return SaversKt.o(obj);
        }
    });
    private static final Saver<Locale, Object> LocaleSaver = SaverKt.Saver(new Function2() { // from class: rtc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.a0((SaverScope) obj, (Locale) obj2);
        }
    }, new Function1() { // from class: stc
        public final Object invoke(Object obj) {
            return SaversKt.f(obj);
        }
    });
    private static final Saver<LineHeightStyle, Object> LineHeightStyleSaver = SaverKt.Saver(new Function2() { // from class: ttc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.l((SaverScope) obj, (LineHeightStyle) obj2);
        }
    }, new Function1() { // from class: vtc
        public final Object invoke(Object obj) {
            return SaversKt.z(obj);
        }
    });
    private static final NonNullValueClassSaver<LineHeightStyle.Alignment, Object> LineHeightStyleAlignmentSaver = NonNullValueClassSaver(new Function2() { // from class: wtc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.O((SaverScope) obj, (LineHeightStyle.Alignment) obj2);
        }
    }, new Function1() { // from class: xtc
        public final Object invoke(Object obj) {
            return SaversKt.F(obj);
        }
    });
    private static final NonNullValueClassSaver<LineHeightStyle.Trim, Object> LineHeightStyleTrimSaver = NonNullValueClassSaver(new Function2() { // from class: ytc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.R((SaverScope) obj, (LineHeightStyle.Trim) obj2);
        }
    }, new Function1() { // from class: ztc
        public final Object invoke(Object obj) {
            return SaversKt.P(obj);
        }
    });
    private static final NonNullValueClassSaver<LineHeightStyle.Mode, Object> LineHeightStyleModeSaver = NonNullValueClassSaver(new Function2() { // from class: buc
        public final Object invoke(Object obj, Object obj2) {
            return SaversKt.V((SaverScope) obj, (LineHeightStyle.Mode) obj2);
        }
    }, new Function1() { // from class: cuc
        public final Object invoke(Object obj) {
            return SaversKt.E(obj);
        }
    });

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotationType.values().length];
            try {
                iArr[AnnotationType.Paragraph.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnnotationType.Span.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnnotationType.VerbatimTts.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AnnotationType.Url.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AnnotationType.Link.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AnnotationType.Clickable.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[AnnotationType.String.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static AnnotatedString.Range A(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        ParagraphStyle paragraphStyleRestore = null;
        clickableRestore = null;
        LinkAnnotation.Clickable clickableRestore = null;
        urlRestore = null;
        LinkAnnotation.Url urlRestore = null;
        urlAnnotationRestore = null;
        UrlAnnotation urlAnnotationRestore = null;
        verbatimTtsAnnotationRestore = null;
        VerbatimTtsAnnotation verbatimTtsAnnotationRestore = null;
        spanStyleRestore = null;
        SpanStyle spanStyleRestore = null;
        paragraphStyleRestore = null;
        AnnotationType annotationType = obj2 != null ? (AnnotationType) obj2 : null;
        annotationType.getClass();
        Object obj3 = list.get(2);
        Integer num = obj3 != null ? (Integer) obj3 : null;
        num.getClass();
        int iIntValue = num.intValue();
        Object obj4 = list.get(3);
        Integer num2 = obj4 != null ? (Integer) obj4 : null;
        num2.getClass();
        int iIntValue2 = num2.intValue();
        Object obj5 = list.get(4);
        String str = obj5 != null ? (String) obj5 : null;
        str.getClass();
        switch (WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()]) {
            case 1:
                Object obj6 = list.get(1);
                Saver<ParagraphStyle, Object> saver = ParagraphStyleSaver;
                if ((!Intrinsics.areEqual(obj6, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj6 != null) {
                    paragraphStyleRestore = saver.restore(obj6);
                }
                paragraphStyleRestore.getClass();
                return new AnnotatedString.Range(paragraphStyleRestore, iIntValue, iIntValue2, str);
            case 2:
                Object obj7 = list.get(1);
                Saver<SpanStyle, Object> saver2 = SpanStyleSaver;
                if ((!Intrinsics.areEqual(obj7, Boolean.FALSE) || (saver2 instanceof NonNullValueClassSaver)) && obj7 != null) {
                    spanStyleRestore = saver2.restore(obj7);
                }
                spanStyleRestore.getClass();
                return new AnnotatedString.Range(spanStyleRestore, iIntValue, iIntValue2, str);
            case 3:
                Object obj8 = list.get(1);
                Saver<VerbatimTtsAnnotation, Object> saver3 = VerbatimTtsAnnotationSaver;
                if ((!Intrinsics.areEqual(obj8, Boolean.FALSE) || (saver3 instanceof NonNullValueClassSaver)) && obj8 != null) {
                    verbatimTtsAnnotationRestore = saver3.restore(obj8);
                }
                verbatimTtsAnnotationRestore.getClass();
                return new AnnotatedString.Range(verbatimTtsAnnotationRestore, iIntValue, iIntValue2, str);
            case 4:
                Object obj9 = list.get(1);
                Saver<UrlAnnotation, Object> saver4 = UrlAnnotationSaver;
                if ((!Intrinsics.areEqual(obj9, Boolean.FALSE) || (saver4 instanceof NonNullValueClassSaver)) && obj9 != null) {
                    urlAnnotationRestore = saver4.restore(obj9);
                }
                urlAnnotationRestore.getClass();
                return new AnnotatedString.Range(urlAnnotationRestore, iIntValue, iIntValue2, str);
            case 5:
                Object obj10 = list.get(1);
                Saver<LinkAnnotation.Url, Object> saver5 = LinkSaver;
                if ((!Intrinsics.areEqual(obj10, Boolean.FALSE) || (saver5 instanceof NonNullValueClassSaver)) && obj10 != null) {
                    urlRestore = saver5.restore(obj10);
                }
                urlRestore.getClass();
                return new AnnotatedString.Range(urlRestore, iIntValue, iIntValue2, str);
            case 6:
                Object obj11 = list.get(1);
                Saver<LinkAnnotation.Clickable, Object> saver6 = ClickableSaver;
                if ((!Intrinsics.areEqual(obj11, Boolean.FALSE) || (saver6 instanceof NonNullValueClassSaver)) && obj11 != null) {
                    clickableRestore = saver6.restore(obj11);
                }
                clickableRestore.getClass();
                return new AnnotatedString.Range(clickableRestore, iIntValue, iIntValue2, str);
            case 7:
                Object obj12 = list.get(1);
                String str2 = obj12 != null ? (String) obj12 : null;
                str2.getClass();
                return new AnnotatedString.Range(StringAnnotation.m5427boximpl(StringAnnotation.m5428constructorimpl(str2)), iIntValue, iIntValue2, str);
            default:
                bu8.a();
                return null;
        }
    }

    public static FontWeight B(Object obj) {
        obj.getClass();
        return new FontWeight(((Integer) obj).intValue());
    }

    public static Object C(SaverScope saverScope, TextAlign textAlign) {
        return Integer.valueOf(textAlign.m5899unboximpl());
    }

    public static TextIndent D(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        TextUnit.Companion companion = TextUnit.INSTANCE;
        Saver<TextUnit, Object> saver = getSaver(companion);
        Boolean bool = Boolean.FALSE;
        TextUnit textUnitRestore = null;
        TextUnit textUnitRestore2 = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
        textUnitRestore2.getClass();
        long packedValue = textUnitRestore2.getPackedValue();
        Object obj3 = list.get(1);
        Saver<TextUnit, Object> saver2 = getSaver(companion);
        if ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) {
            textUnitRestore = saver2.restore(obj3);
        }
        textUnitRestore.getClass();
        return new TextIndent(packedValue, textUnitRestore.getPackedValue(), null);
    }

    public static LineHeightStyle.Mode E(Object obj) {
        obj.getClass();
        return LineHeightStyle.Mode.m5870boximpl(LineHeightStyle.Mode.m5871constructorimpl(((Integer) obj).intValue()));
    }

    public static LineHeightStyle.Alignment F(Object obj) {
        obj.getClass();
        return LineHeightStyle.Alignment.m5859boximpl(LineHeightStyle.Alignment.m5860constructorimpl(((Float) obj).floatValue()));
    }

    public static TextDecoration G(Object obj) {
        obj.getClass();
        return new TextDecoration(((Integer) obj).intValue());
    }

    public static Object H(SaverScope saverScope, UrlAnnotation urlAnnotation) {
        return save(urlAnnotation.getUrl());
    }

    public static Hyphens I(Object obj) {
        obj.getClass();
        return Hyphens.m5785boximpl(Hyphens.m5786constructorimpl(((Integer) obj).intValue()));
    }

    public static LinkAnnotation.Url J(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        TextLinkStyles textLinkStylesRestore = null;
        String str = obj2 != null ? (String) obj2 : null;
        str.getClass();
        Object obj3 = list.get(1);
        Saver<TextLinkStyles, Object> saver = TextLinkStylesSaver;
        if ((!Intrinsics.areEqual(obj3, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj3 != null) {
            textLinkStylesRestore = saver.restore(obj3);
        }
        return new LinkAnnotation.Url(str, textLinkStylesRestore, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object K(SaverScope saverScope, Offset offset) {
        return offset == null ? false : Offset.m2886equalsimpl0(offset.m2899unboximpl(), Offset.INSTANCE.m2904getUnspecifiedF1C5BW0()) ? Boolean.FALSE : CollectionsKt.arrayListOf(new Float[]{save(Float.valueOf(Float.intBitsToFloat((int) (offset.m2899unboximpl() >> 32)))), save(Float.valueOf(Float.intBitsToFloat((int) (offset.m2899unboximpl() & 4294967295L))))});
    }

    public static Shadow L(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Saver<Color, Object> saver = getSaver(Color.INSTANCE);
        Boolean bool = Boolean.FALSE;
        Color colorRestore = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
        colorRestore.getClass();
        long jM3144unboximpl = colorRestore.m3144unboximpl();
        Object obj3 = list.get(1);
        Saver<Offset, Object> saver2 = getSaver(Offset.INSTANCE);
        Offset offsetRestore = ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? saver2.restore(obj3) : null;
        offsetRestore.getClass();
        long jM2899unboximpl = offsetRestore.m2899unboximpl();
        Object obj4 = list.get(2);
        Float f = obj4 != null ? (Float) obj4 : null;
        f.getClass();
        return new Shadow(jM3144unboximpl, jM2899unboximpl, f.floatValue(), null);
    }

    public static Object M(SaverScope saverScope, LocaleList localeList) {
        List<Locale> localeList2 = localeList.getLocaleList();
        ArrayList arrayList = new ArrayList(localeList2.size());
        int size = localeList2.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(save(localeList2.get(i), getSaver(Locale.INSTANCE), saverScope));
        }
        return arrayList;
    }

    public static Object N(SaverScope saverScope, TextUnit textUnit) {
        return textUnit == null ? false : TextUnit.m6213equalsimpl0(textUnit.getPackedValue(), TextUnit.INSTANCE.m6227getUnspecifiedXSAIIZE()) ? Boolean.FALSE : CollectionsKt.arrayListOf(new Object[]{save(Float.valueOf(TextUnit.m6216getValueimpl(textUnit.getPackedValue()))), save(TextUnitType.m6241boximpl(TextUnit.m6215getTypeUIouoOA(textUnit.getPackedValue())), getSaver(TextUnitType.INSTANCE), saverScope)});
    }

    private static final <Original, Saveable> NonNullValueClassSaver<Original, Saveable> NonNullValueClassSaver(final Function2<? super SaverScope, ? super Original, ? extends Saveable> function2, final Function1<? super Saveable, ? extends Original> function1) {
        return new NonNullValueClassSaver<Original, Saveable>() { // from class: androidx.compose.ui.text.SaversKt.NonNullValueClassSaver.1
            @Override // androidx.compose.runtime.saveable.Saver
            public Original restore(Saveable value) {
                return (Original) function1.invoke(value);
            }

            @Override // androidx.compose.runtime.saveable.Saver
            public Saveable save(SaverScope saverScope, Original original) {
                return (Saveable) function2.invoke(saverScope, original);
            }
        };
    }

    public static Object O(SaverScope saverScope, LineHeightStyle.Alignment alignment) {
        return Float.valueOf(alignment.getTopRatio());
    }

    public static LineHeightStyle.Trim P(Object obj) {
        obj.getClass();
        return LineHeightStyle.Trim.m5880boximpl(LineHeightStyle.Trim.m5881constructorimpl(((Integer) obj).intValue()));
    }

    public static Object Q(SaverScope saverScope, AnnotatedString.Range range) {
        AnnotationType annotationType;
        Object objSave;
        Object item = range.getItem();
        if (item instanceof ParagraphStyle) {
            annotationType = AnnotationType.Paragraph;
        } else if (item instanceof SpanStyle) {
            annotationType = AnnotationType.Span;
        } else if (item instanceof VerbatimTtsAnnotation) {
            annotationType = AnnotationType.VerbatimTts;
        } else if (item instanceof UrlAnnotation) {
            annotationType = AnnotationType.Url;
        } else if (item instanceof LinkAnnotation.Url) {
            annotationType = AnnotationType.Link;
        } else if (item instanceof LinkAnnotation.Clickable) {
            annotationType = AnnotationType.Clickable;
        } else {
            if (!(item instanceof StringAnnotation)) {
                a9g.a();
                return null;
            }
            annotationType = AnnotationType.String;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[annotationType.ordinal()]) {
            case 1:
                Object item2 = range.getItem();
                item2.getClass();
                objSave = save((ParagraphStyle) item2, ParagraphStyleSaver, saverScope);
                break;
            case 2:
                Object item3 = range.getItem();
                item3.getClass();
                objSave = save((SpanStyle) item3, SpanStyleSaver, saverScope);
                break;
            case 3:
                Object item4 = range.getItem();
                item4.getClass();
                objSave = save((VerbatimTtsAnnotation) item4, VerbatimTtsAnnotationSaver, saverScope);
                break;
            case 4:
                Object item5 = range.getItem();
                item5.getClass();
                objSave = save((UrlAnnotation) item5, UrlAnnotationSaver, saverScope);
                break;
            case 5:
                Object item6 = range.getItem();
                item6.getClass();
                objSave = save((LinkAnnotation.Url) item6, LinkSaver, saverScope);
                break;
            case 6:
                Object item7 = range.getItem();
                item7.getClass();
                objSave = save((LinkAnnotation.Clickable) item7, ClickableSaver, saverScope);
                break;
            case 7:
                Object item8 = range.getItem();
                item8.getClass();
                objSave = save(((StringAnnotation) item8).m5433unboximpl());
                break;
            default:
                bu8.a();
                return null;
        }
        return CollectionsKt.arrayListOf(new Object[]{save(annotationType), objSave, save(Integer.valueOf(range.getStart())), save(Integer.valueOf(range.getEnd())), save(range.getTag())});
    }

    public static Object R(SaverScope saverScope, LineHeightStyle.Trim trim) {
        return Integer.valueOf(trim.getValue());
    }

    public static TextUnit S(Object obj) {
        Boolean bool = Boolean.FALSE;
        if (Intrinsics.areEqual(obj, bool)) {
            return TextUnit.m6206boximpl(TextUnit.INSTANCE.m6227getUnspecifiedXSAIIZE());
        }
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        TextUnitType textUnitTypeRestore = null;
        Float f = obj2 != null ? (Float) obj2 : null;
        f.getClass();
        float fFloatValue = f.floatValue();
        Object obj3 = list.get(1);
        Saver<TextUnitType, Object> saver = getSaver(TextUnitType.INSTANCE);
        if ((!Intrinsics.areEqual(obj3, bool) || (saver instanceof NonNullValueClassSaver)) && obj3 != null) {
            textUnitTypeRestore = saver.restore(obj3);
        }
        textUnitTypeRestore.getClass();
        return TextUnit.m6206boximpl(TextUnitKt.m6228TextUnitanM5pPY(fFloatValue, textUnitTypeRestore.getType()));
    }

    public static TextUnitType T(Object obj) {
        if (Intrinsics.areEqual(obj, 0)) {
            return TextUnitType.m6241boximpl(TextUnitType.INSTANCE.m6248getEmUIouoOA());
        }
        return Intrinsics.areEqual(obj, 1) ? TextUnitType.m6241boximpl(TextUnitType.INSTANCE.m6249getSpUIouoOA()) : TextUnitType.m6241boximpl(TextUnitType.INSTANCE.m6250getUnspecifiedUIouoOA());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v0 androidx.compose.ui.text.SpanStyle, still in use, count: 2, list:
          (r1v0 androidx.compose.ui.text.SpanStyle) from 0x00fe: MOVE (r16v2 androidx.compose.ui.text.SpanStyle) = (r1v0 androidx.compose.ui.text.SpanStyle)
          (r1v0 androidx.compose.ui.text.SpanStyle) from 0x00f6: MOVE (r16v7 androidx.compose.ui.text.SpanStyle) = (r1v0 androidx.compose.ui.text.SpanStyle)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:59)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    public static androidx.compose.ui.text.SpanStyle U(java.lang.Object r28) {
        /*
            Method dump skipped, instruction units count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.SaversKt.U(java.lang.Object):androidx.compose.ui.text.SpanStyle");
    }

    public static Object V(SaverScope saverScope, LineHeightStyle.Mode mode) {
        return Integer.valueOf(mode.getValue());
    }

    public static Object W(SaverScope saverScope, FontWeight fontWeight) {
        return Integer.valueOf(fontWeight.getWeight());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object X(SaverScope saverScope, TextRange textRange) {
        return CollectionsKt.arrayListOf(new Integer[]{save(Integer.valueOf(TextRange.m5479getStartimpl(textRange.getPackedValue()))), save(Integer.valueOf(TextRange.m5474getEndimpl(textRange.getPackedValue())))});
    }

    public static Object Y(SaverScope saverScope, LinkAnnotation.Url url) {
        return CollectionsKt.arrayListOf(new Object[]{save(url.getUrl()), save(url.getStyles(), TextLinkStylesSaver, saverScope)});
    }

    public static Object Z(SaverScope saverScope, BaselineShift baselineShift) {
        return Float.valueOf(baselineShift.m5768unboximpl());
    }

    public static VerbatimTtsAnnotation a(Object obj) {
        String str = obj != null ? (String) obj : null;
        str.getClass();
        return new VerbatimTtsAnnotation(str);
    }

    public static Object a0(SaverScope saverScope, Locale locale) {
        return locale.toLanguageTag();
    }

    public static FontStyle b(Object obj) {
        obj.getClass();
        return FontStyle.m5588boximpl(FontStyle.m5589constructorimpl(((Integer) obj).intValue()));
    }

    public static TextDirection b0(Object obj) {
        obj.getClass();
        return TextDirection.m5910boximpl(TextDirection.m5911constructorimpl(((Integer) obj).intValue()));
    }

    public static Object c(SaverScope saverScope, Hyphens hyphens) {
        return Integer.valueOf(hyphens.m5791unboximpl());
    }

    public static BaselineShift c0(Object obj) {
        obj.getClass();
        return BaselineShift.m5762boximpl(BaselineShift.m5763constructorimpl(((Float) obj).floatValue()));
    }

    public static FontSynthesis d(Object obj) {
        obj.getClass();
        return FontSynthesis.m5599boximpl(FontSynthesis.m5600constructorimpl(((Integer) obj).intValue()));
    }

    public static ParagraphStyle d0(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Saver<TextAlign, Object> saver = getSaver(TextAlign.INSTANCE);
        Boolean bool = Boolean.FALSE;
        TextMotion textMotionRestore = null;
        TextAlign textAlignRestore = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
        textAlignRestore.getClass();
        int iM5899unboximpl = textAlignRestore.m5899unboximpl();
        Object obj3 = list.get(1);
        Saver<TextDirection, Object> saver2 = getSaver(TextDirection.INSTANCE);
        TextDirection textDirectionRestore = ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? saver2.restore(obj3) : null;
        textDirectionRestore.getClass();
        int iM5916unboximpl = textDirectionRestore.m5916unboximpl();
        Object obj4 = list.get(2);
        Saver<TextUnit, Object> saver3 = getSaver(TextUnit.INSTANCE);
        TextUnit textUnitRestore = ((!Intrinsics.areEqual(obj4, bool) || (saver3 instanceof NonNullValueClassSaver)) && obj4 != null) ? saver3.restore(obj4) : null;
        textUnitRestore.getClass();
        long packedValue = textUnitRestore.getPackedValue();
        Object obj5 = list.get(3);
        Saver<TextIndent, Object> saver4 = getSaver(TextIndent.INSTANCE);
        TextIndent textIndentRestore = ((!Intrinsics.areEqual(obj5, bool) || (saver4 instanceof NonNullValueClassSaver)) && obj5 != null) ? saver4.restore(obj5) : null;
        Object obj6 = list.get(4);
        Saver<PlatformParagraphStyle, Object> saver5 = Savers_androidKt.getSaver(PlatformParagraphStyle.INSTANCE);
        PlatformParagraphStyle platformParagraphStyleRestore = ((!Intrinsics.areEqual(obj6, bool) || (saver5 instanceof NonNullValueClassSaver)) && obj6 != null) ? saver5.restore(obj6) : null;
        Object obj7 = list.get(5);
        Saver<LineHeightStyle, Object> saver6 = getSaver(LineHeightStyle.INSTANCE);
        LineHeightStyle lineHeightStyleRestore = ((!Intrinsics.areEqual(obj7, bool) || (saver6 instanceof NonNullValueClassSaver)) && obj7 != null) ? saver6.restore(obj7) : null;
        Object obj8 = list.get(6);
        Saver<LineBreak, Object> saver7 = Savers_androidKt.getSaver(LineBreak.INSTANCE);
        LineBreak lineBreakRestore = ((!Intrinsics.areEqual(obj8, bool) || (saver7 instanceof NonNullValueClassSaver)) && obj8 != null) ? saver7.restore(obj8) : null;
        lineBreakRestore.getClass();
        int mask = lineBreakRestore.getMask();
        Object obj9 = list.get(7);
        Saver<Hyphens, Object> saver8 = getSaver(Hyphens.INSTANCE);
        Hyphens hyphensRestore = ((!Intrinsics.areEqual(obj9, bool) || (saver8 instanceof NonNullValueClassSaver)) && obj9 != null) ? saver8.restore(obj9) : null;
        hyphensRestore.getClass();
        int iM5791unboximpl = hyphensRestore.m5791unboximpl();
        Object obj10 = list.get(8);
        Saver<TextMotion, Object> saver9 = Savers_androidKt.getSaver(TextMotion.INSTANCE);
        if ((!Intrinsics.areEqual(obj10, bool) || (saver9 instanceof NonNullValueClassSaver)) && obj10 != null) {
            textMotionRestore = saver9.restore(obj10);
        }
        return new ParagraphStyle(iM5899unboximpl, iM5916unboximpl, packedValue, textIndentRestore, platformParagraphStyleRestore, lineHeightStyleRestore, mask, iM5791unboximpl, textMotionRestore, (DefaultConstructorMarker) null);
    }

    public static Object e(SaverScope saverScope, TextIndent textIndent) {
        TextUnit textUnitM6206boximpl = TextUnit.m6206boximpl(textIndent.getFirstLine());
        TextUnit.Companion companion = TextUnit.INSTANCE;
        return CollectionsKt.arrayListOf(new Object[]{save(textUnitM6206boximpl, getSaver(companion), saverScope), save(TextUnit.m6206boximpl(textIndent.getRestLine()), getSaver(companion), saverScope)});
    }

    public static Object e0(SaverScope saverScope, ParagraphStyle paragraphStyle) {
        return CollectionsKt.arrayListOf(new Object[]{save(TextAlign.m5893boximpl(paragraphStyle.getTextAlign()), getSaver(TextAlign.INSTANCE), saverScope), save(TextDirection.m5910boximpl(paragraphStyle.getTextDirection()), getSaver(TextDirection.INSTANCE), saverScope), save(TextUnit.m6206boximpl(paragraphStyle.getLineHeight()), getSaver(TextUnit.INSTANCE), saverScope), save(paragraphStyle.getTextIndent(), getSaver(TextIndent.INSTANCE), saverScope), save(paragraphStyle.getPlatformStyle(), Savers_androidKt.getSaver(PlatformParagraphStyle.INSTANCE), saverScope), save(paragraphStyle.getLineHeightStyle(), getSaver(LineHeightStyle.INSTANCE), saverScope), save(LineBreak.m5798boximpl(paragraphStyle.getLineBreak()), Savers_androidKt.getSaver(LineBreak.INSTANCE), saverScope), save(Hyphens.m5785boximpl(paragraphStyle.getHyphens()), getSaver(Hyphens.INSTANCE), saverScope), save(paragraphStyle.getTextMotion(), Savers_androidKt.getSaver(TextMotion.INSTANCE), saverScope)});
    }

    public static Locale f(Object obj) {
        obj.getClass();
        return new Locale((String) obj);
    }

    public static List f0(Object obj) {
        obj.getClass();
        List list = (List) obj;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj2 = list.get(i);
            Saver<AnnotatedString.Range<? extends Object>, Object> saver = AnnotationRangeSaver;
            AnnotatedString.Range<? extends Object> rangeRestore = null;
            if ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) {
                rangeRestore = saver.restore(obj2);
            }
            rangeRestore.getClass();
            arrayList.add(rangeRestore);
        }
        return arrayList;
    }

    public static Object g(SaverScope saverScope, TextGeometricTransform textGeometricTransform) {
        return CollectionsKt.arrayListOf(new Float[]{Float.valueOf(textGeometricTransform.getScaleX()), Float.valueOf(textGeometricTransform.getSkewX())});
    }

    public static Object g0(SaverScope saverScope, List list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(save((AnnotatedString.Range) list.get(i), AnnotationRangeSaver, saverScope));
        }
        return arrayList;
    }

    public static final Saver<AnnotatedString, Object> getAnnotatedStringSaver() {
        return AnnotatedStringSaver;
    }

    private static /* synthetic */ void getAnnotationRangeSaver$annotations() {
    }

    public static final Saver<FontStyle, Object> getFontStyleSaver() {
        return FontStyleSaver;
    }

    public static final Saver<FontSynthesis, Object> getFontSynthesisSaver() {
        return FontSynthesisSaver;
    }

    public static final Saver<ParagraphStyle, Object> getParagraphStyleSaver() {
        return ParagraphStyleSaver;
    }

    public static final Saver<TextDecoration, Object> getSaver(TextDecoration.Companion companion) {
        return TextDecorationSaver;
    }

    public static final Saver<SpanStyle, Object> getSpanStyleSaver() {
        return SpanStyleSaver;
    }

    public static final Saver<TextLinkStyles, Object> getTextLinkStylesSaver() {
        return TextLinkStylesSaver;
    }

    public static final NonNullValueClassSaver<TextUnitType, Object> getTextUnitTypeSaver() {
        return TextUnitTypeSaver;
    }

    private static /* synthetic */ void getUrlAnnotationSaver$annotations() {
    }

    public static TextAlign h(Object obj) {
        obj.getClass();
        return TextAlign.m5893boximpl(TextAlign.m5894constructorimpl(((Integer) obj).intValue()));
    }

    public static Object h0(SaverScope saverScope, TextDirection textDirection) {
        return Integer.valueOf(textDirection.m5916unboximpl());
    }

    public static Object i(SaverScope saverScope, FontSynthesis fontSynthesis) {
        return Integer.valueOf(fontSynthesis.m5607unboximpl());
    }

    public static LinkAnnotation.Clickable i0(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        String str = obj2 != null ? (String) obj2 : null;
        str.getClass();
        Object obj3 = list.get(1);
        Saver<TextLinkStyles, Object> saver = TextLinkStylesSaver;
        return new LinkAnnotation.Clickable(str, ((!Intrinsics.areEqual(obj3, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj3 != null) ? saver.restore(obj3) : null, null);
    }

    public static Object j(SaverScope saverScope, SpanStyle spanStyle) {
        Color colorM3124boximpl = Color.m3124boximpl(spanStyle.m5420getColor0d7_KjU());
        Color.Companion companion = Color.INSTANCE;
        Object objSave = save(colorM3124boximpl, getSaver(companion), saverScope);
        TextUnit textUnitM6206boximpl = TextUnit.m6206boximpl(spanStyle.getFontSize());
        TextUnit.Companion companion2 = TextUnit.INSTANCE;
        return CollectionsKt.arrayListOf(new Object[]{objSave, save(textUnitM6206boximpl, getSaver(companion2), saverScope), save(spanStyle.getFontWeight(), getSaver(FontWeight.INSTANCE), saverScope), save(spanStyle.getFontStyle(), getSaver(FontStyle.INSTANCE), saverScope), save(spanStyle.getFontSynthesis(), getSaver(FontSynthesis.INSTANCE), saverScope), save(-1), save(spanStyle.getFontFeatureSettings()), save(TextUnit.m6206boximpl(spanStyle.getLetterSpacing()), getSaver(companion2), saverScope), save(spanStyle.getBaselineShift(), getSaver(BaselineShift.INSTANCE), saverScope), save(spanStyle.getTextGeometricTransform(), getSaver(TextGeometricTransform.INSTANCE), saverScope), save(spanStyle.getLocaleList(), getSaver(LocaleList.INSTANCE), saverScope), save(Color.m3124boximpl(spanStyle.getBackground()), getSaver(companion), saverScope), save(spanStyle.getTextDecoration(), getSaver(TextDecoration.INSTANCE), saverScope), save(spanStyle.getShadow(), getSaver(Shadow.INSTANCE), saverScope)});
    }

    public static TextGeometricTransform j0(Object obj) {
        obj.getClass();
        List list = (List) obj;
        return new TextGeometricTransform(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue());
    }

    public static AnnotatedString k(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(1);
        Saver<List<AnnotatedString.Range<? extends Object>>, Object> saver = AnnotationRangeListSaver;
        List<AnnotatedString.Range<? extends Object>> listRestore = ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
        Object obj3 = list.get(0);
        String str = obj3 != null ? (String) obj3 : null;
        str.getClass();
        return new AnnotatedString(listRestore, str);
    }

    public static Object l(SaverScope saverScope, LineHeightStyle lineHeightStyle) {
        return CollectionsKt.arrayListOf(new Object[]{save(LineHeightStyle.Alignment.m5859boximpl(lineHeightStyle.getAlignment()), getSaver(LineHeightStyle.Alignment.INSTANCE), saverScope), save(LineHeightStyle.Trim.m5880boximpl(lineHeightStyle.getTrim()), getSaver(LineHeightStyle.Trim.INSTANCE), saverScope), save(LineHeightStyle.Mode.m5870boximpl(lineHeightStyle.getMode()), getSaver(LineHeightStyle.Mode.INSTANCE), saverScope)});
    }

    public static Object m(SaverScope saverScope, FontStyle fontStyle) {
        return save(Integer.valueOf(fontStyle.m5594unboximpl()));
    }

    public static Object n(SaverScope saverScope, AnnotatedString annotatedString) {
        return CollectionsKt.arrayListOf(new Object[]{save(annotatedString.getText()), save(annotatedString.getAnnotations$ui_text(), AnnotationRangeListSaver, saverScope)});
    }

    public static LocaleList o(Object obj) {
        obj.getClass();
        List list = (List) obj;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj2 = list.get(i);
            Saver<Locale, Object> saver = getSaver(Locale.INSTANCE);
            Locale localeRestore = null;
            if ((!Intrinsics.areEqual(obj2, Boolean.FALSE) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) {
                localeRestore = saver.restore(obj2);
            }
            localeRestore.getClass();
            arrayList.add(localeRestore);
        }
        return new LocaleList(arrayList);
    }

    public static Object p(SaverScope saverScope, TextDecoration textDecoration) {
        return Integer.valueOf(textDecoration.getMask());
    }

    public static Offset q(Object obj) {
        if (Intrinsics.areEqual(obj, Boolean.FALSE)) {
            return Offset.m2878boximpl(Offset.INSTANCE.m2904getUnspecifiedF1C5BW0());
        }
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Float f = obj2 != null ? (Float) obj2 : null;
        f.getClass();
        float fFloatValue = f.floatValue();
        Object obj3 = list.get(1);
        Float f2 = obj3 != null ? (Float) obj3 : null;
        f2.getClass();
        return Offset.m2878boximpl(Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fFloatValue)) << 32) | (((long) Float.floatToRawIntBits(f2.floatValue())) & 4294967295L)));
    }

    public static Object r(SaverScope saverScope, VerbatimTtsAnnotation verbatimTtsAnnotation) {
        return save(verbatimTtsAnnotation.getVerbatim());
    }

    public static final /* synthetic */ <T extends Saver<Original, Saveable>, Original, Saveable, Result> Result restore(Saveable saveable, T t) {
        if ((Intrinsics.areEqual(saveable, Boolean.FALSE) && !(t instanceof NonNullValueClassSaver)) || saveable == null) {
            return null;
        }
        Result result = (Result) t.restore(saveable);
        Intrinsics.reifiedOperationMarker(1, "Result");
        return result;
    }

    public static Object s(SaverScope saverScope, TextLinkStyles textLinkStyles) {
        SpanStyle style = textLinkStyles.getStyle();
        Saver<SpanStyle, Object> saver = SpanStyleSaver;
        return CollectionsKt.arrayListOf(new Object[]{save(style, saver, saverScope), save(textLinkStyles.getFocusedStyle(), saver, saverScope), save(textLinkStyles.getHoveredStyle(), saver, saverScope), save(textLinkStyles.getPressedStyle(), saver, saverScope)});
    }

    public static final <T extends Saver<Original, Saveable>, Original, Saveable> Object save(Original original, T t, SaverScope saverScope) {
        Object objSave;
        return (original == null || (objSave = t.save(saverScope, original)) == null) ? Boolean.FALSE : objSave;
    }

    public static Object t(SaverScope saverScope, LinkAnnotation.Clickable clickable) {
        return CollectionsKt.arrayListOf(new Object[]{save(clickable.getTag()), save(clickable.getStyles(), TextLinkStylesSaver, saverScope)});
    }

    public static Object u(SaverScope saverScope, TextUnitType textUnitType) {
        long type = textUnitType.getType();
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        if (TextUnitType.m6244equalsimpl0(type, companion.m6248getEmUIouoOA())) {
            return 0;
        }
        if (TextUnitType.m6244equalsimpl0(type, companion.m6249getSpUIouoOA())) {
            return 1;
        }
        return Boolean.FALSE;
    }

    public static Object v(SaverScope saverScope, Shadow shadow) {
        return CollectionsKt.arrayListOf(new Object[]{save(Color.m3124boximpl(shadow.getColor()), getSaver(Color.INSTANCE), saverScope), save(Offset.m2878boximpl(shadow.getOffset()), getSaver(Offset.INSTANCE), saverScope), save(Float.valueOf(shadow.getBlurRadius()))});
    }

    public static TextRange w(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Integer num = obj2 != null ? (Integer) obj2 : null;
        num.getClass();
        int iIntValue = num.intValue();
        Object obj3 = list.get(1);
        Integer num2 = obj3 != null ? (Integer) obj3 : null;
        num2.getClass();
        return TextRange.m5467boximpl(TextRangeKt.TextRange(iIntValue, num2.intValue()));
    }

    public static TextLinkStyles x(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Saver<SpanStyle, Object> saver = SpanStyleSaver;
        Boolean bool = Boolean.FALSE;
        SpanStyle spanStyleRestore = null;
        SpanStyle spanStyleRestore2 = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
        Object obj3 = list.get(1);
        SpanStyle spanStyleRestore3 = ((!Intrinsics.areEqual(obj3, bool) || (saver instanceof NonNullValueClassSaver)) && obj3 != null) ? saver.restore(obj3) : null;
        Object obj4 = list.get(2);
        SpanStyle spanStyleRestore4 = ((!Intrinsics.areEqual(obj4, bool) || (saver instanceof NonNullValueClassSaver)) && obj4 != null) ? saver.restore(obj4) : null;
        Object obj5 = list.get(3);
        if ((!Intrinsics.areEqual(obj5, bool) || (saver instanceof NonNullValueClassSaver)) && obj5 != null) {
            spanStyleRestore = saver.restore(obj5);
        }
        return new TextLinkStyles(spanStyleRestore2, spanStyleRestore3, spanStyleRestore4, spanStyleRestore);
    }

    public static UrlAnnotation y(Object obj) {
        String str = obj != null ? (String) obj : null;
        str.getClass();
        return new UrlAnnotation(str);
    }

    public static LineHeightStyle z(Object obj) {
        obj.getClass();
        List list = (List) obj;
        Object obj2 = list.get(0);
        Saver<LineHeightStyle.Alignment, Object> saver = getSaver(LineHeightStyle.Alignment.INSTANCE);
        Boolean bool = Boolean.FALSE;
        LineHeightStyle.Alignment alignmentRestore = ((!Intrinsics.areEqual(obj2, bool) || (saver instanceof NonNullValueClassSaver)) && obj2 != null) ? saver.restore(obj2) : null;
        alignmentRestore.getClass();
        float topRatio = alignmentRestore.getTopRatio();
        Object obj3 = list.get(1);
        Saver<LineHeightStyle.Trim, Object> saver2 = getSaver(LineHeightStyle.Trim.INSTANCE);
        LineHeightStyle.Trim trimRestore = ((!Intrinsics.areEqual(obj3, bool) || (saver2 instanceof NonNullValueClassSaver)) && obj3 != null) ? saver2.restore(obj3) : null;
        trimRestore.getClass();
        int value = trimRestore.getValue();
        Object obj4 = list.get(2);
        Saver<LineHeightStyle.Mode, Object> saver3 = getSaver(LineHeightStyle.Mode.INSTANCE);
        LineHeightStyle.Mode modeRestore = ((!Intrinsics.areEqual(obj4, bool) || (saver3 instanceof NonNullValueClassSaver)) && obj4 != null) ? saver3.restore(obj4) : null;
        modeRestore.getClass();
        return new LineHeightStyle(topRatio, value, modeRestore.getValue(), null);
    }

    public static final Saver<TextGeometricTransform, Object> getSaver(TextGeometricTransform.Companion companion) {
        return TextGeometricTransformSaver;
    }

    public static final Saver<TextIndent, Object> getSaver(TextIndent.Companion companion) {
        return TextIndentSaver;
    }

    public static final Saver<FontWeight, Object> getSaver(FontWeight.Companion companion) {
        return FontWeightSaver;
    }

    public static final Saver<BaselineShift, Object> getSaver(BaselineShift.Companion companion) {
        return BaselineShiftSaver;
    }

    public static final Saver<TextRange, Object> getSaver(TextRange.Companion companion) {
        return TextRangeSaver;
    }

    public static final Saver<Shadow, Object> getSaver(Shadow.Companion companion) {
        return ShadowSaver;
    }

    public static final Saver<Color, Object> getSaver(Color.Companion companion) {
        return ColorSaver;
    }

    public static final Saver<TextAlign, Object> getSaver(TextAlign.Companion companion) {
        return TextAlignSaver;
    }

    public static final Saver<TextDirection, Object> getSaver(TextDirection.Companion companion) {
        return TextDirectionSaver;
    }

    public static final Saver<Hyphens, Object> getSaver(Hyphens.Companion companion) {
        return HyphensSaver;
    }

    public static final Saver<FontStyle, Object> getSaver(FontStyle.Companion companion) {
        return FontStyleSaver;
    }

    public static final <T> T save(T t) {
        return t;
    }

    public static final Saver<FontSynthesis, Object> getSaver(FontSynthesis.Companion companion) {
        return FontSynthesisSaver;
    }

    public static final Saver<TextUnit, Object> getSaver(TextUnit.Companion companion) {
        return TextUnitSaver;
    }

    public static final Saver<TextUnitType, Object> getSaver(TextUnitType.Companion companion) {
        return TextUnitTypeSaver;
    }

    public static final Saver<Offset, Object> getSaver(Offset.Companion companion) {
        return OffsetSaver;
    }

    public static final Saver<LocaleList, Object> getSaver(LocaleList.Companion companion) {
        return LocaleListSaver;
    }

    public static final Saver<Locale, Object> getSaver(Locale.Companion companion) {
        return LocaleSaver;
    }

    public static final Saver<LineHeightStyle, Object> getSaver(LineHeightStyle.Companion companion) {
        return LineHeightStyleSaver;
    }

    private static final Saver<LineHeightStyle.Alignment, Object> getSaver(LineHeightStyle.Alignment.Companion companion) {
        return LineHeightStyleAlignmentSaver;
    }

    private static final Saver<LineHeightStyle.Trim, Object> getSaver(LineHeightStyle.Trim.Companion companion) {
        return LineHeightStyleTrimSaver;
    }

    private static final Saver<LineHeightStyle.Mode, Object> getSaver(LineHeightStyle.Mode.Companion companion) {
        return LineHeightStyleModeSaver;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final /* synthetic */ <Result> Result restore(Object obj) {
        if (obj == 0) {
            return null;
        }
        Intrinsics.reifiedOperationMarker(1, "Result");
        return obj;
    }
}
