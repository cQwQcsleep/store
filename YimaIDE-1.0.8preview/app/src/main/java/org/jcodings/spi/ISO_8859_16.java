package org.jcodings.spi;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import org.eclipse.jdt.internal.compiler.codegen.Opcodes;
import org.eclipse.jdt.internal.compiler.lookup.Binding;
import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ISO_8859_16 extends Charset {
    public static final ISO_8859_16 INSTANCE = new ISO_8859_16();

    public static class Decoder extends CharsetDecoder {
        private static final char[] TABLE = new char[256];

        static {
            for (int i = 0; i < 256; i++) {
                TABLE[i] = (char) i;
            }
            char[] cArr = TABLE;
            cArr[161] = 260;
            cArr[162] = 261;
            cArr[163] = 321;
            cArr[164] = 8364;
            cArr[165] = 8222;
            cArr[166] = 352;
            cArr[168] = 353;
            cArr[170] = 536;
            cArr[172] = 377;
            cArr[174] = 378;
            cArr[175] = 379;
            cArr[178] = 268;
            cArr[179] = 322;
            cArr[180] = 381;
            cArr[181] = 8221;
            cArr[184] = 382;
            cArr[185] = 269;
            cArr[186] = 537;
            cArr[188] = 338;
            cArr[189] = 339;
            cArr[190] = 376;
            cArr[191] = 380;
            cArr[195] = 258;
            cArr[197] = 262;
            cArr[209] = 272;
            cArr[210] = 323;
            cArr[213] = 336;
            cArr[215] = 346;
            cArr[216] = 368;
            cArr[221] = 280;
            cArr[222] = 538;
            cArr[227] = 259;
            cArr[229] = 263;
        }

        public Decoder(Charset charset) {
            super(charset, 1.0f, 1.0f);
        }

        @Override // java.nio.charset.CharsetDecoder
        public CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
            while (byteBuffer.hasRemaining()) {
                if (!charBuffer.hasRemaining()) {
                    return CoderResult.OVERFLOW;
                }
                charBuffer.put(TABLE[Byte.toUnsignedInt(byteBuffer.get())]);
            }
            return CoderResult.UNDERFLOW;
        }
    }

    public static class Encoder extends CharsetEncoder {
        public Encoder(Charset charset) {
            super(charset, 1.0f, 1.0f, new byte[]{Opcodes.OPC_lstore_0});
        }

        @Override // java.nio.charset.CharsetEncoder
        public CoderResult encodeLoop(CharBuffer charBuffer, ByteBuffer byteBuffer) {
            byte b;
            while (charBuffer.hasRemaining()) {
                if (!byteBuffer.hasRemaining()) {
                    return CoderResult.OVERFLOW;
                }
                char c = charBuffer.get();
                byte[] bArrReplacement = null;
                if (c == 268) {
                    b = Opcodes.OPC_getstatic;
                } else if (c == 269) {
                    b = Opcodes.OPC_invokeinterface;
                } else if (c == 272) {
                    b = -47;
                } else if (c == 280) {
                    b = -35;
                } else if (c == 336) {
                    b = -43;
                } else if (c == 346) {
                    b = -41;
                } else if (c == 368) {
                    b = -40;
                } else if (c == 8364) {
                    b = Opcodes.OPC_if_icmple;
                } else if (c == 338) {
                    b = Opcodes.OPC_newarray;
                } else if (c == 339) {
                    b = Opcodes.OPC_anewarray;
                } else if (c == 352) {
                    b = Opcodes.OPC_if_acmpne;
                } else if (c == 353) {
                    b = Opcodes.OPC_jsr;
                } else if (c == 8221) {
                    b = Opcodes.OPC_putfield;
                } else if (c != 8222) {
                    switch (c) {
                        case 258:
                            b = Opcodes.OPC_monitorexit;
                            break;
                        case 259:
                            b = -29;
                            break;
                        case Binding.PARAMETERIZED_TYPE /* 260 */:
                            b = Opcodes.OPC_if_icmplt;
                            break;
                        case 261:
                            b = Opcodes.OPC_if_icmpge;
                            break;
                        case 262:
                            b = Opcodes.OPC_multianewarray;
                            break;
                        case 263:
                            b = -27;
                            break;
                        default:
                            switch (c) {
                                case ParserBasicInformation.SCOPE_UBOUND /* 321 */:
                                    b = Opcodes.OPC_if_icmpgt;
                                    break;
                                case ParserBasicInformation.SCOPE_SIZE /* 322 */:
                                    b = Opcodes.OPC_putstatic;
                                    break;
                                case 323:
                                    b = -46;
                                    break;
                                default:
                                    switch (c) {
                                        case 376:
                                            b = Opcodes.OPC_arraylength;
                                            break;
                                        case 377:
                                            b = Opcodes.OPC_ireturn;
                                            break;
                                        case 378:
                                            b = Opcodes.OPC_freturn;
                                            break;
                                        case 379:
                                            b = Opcodes.OPC_dreturn;
                                            break;
                                        case 380:
                                            b = Opcodes.OPC_athrow;
                                            break;
                                        case 381:
                                            b = Opcodes.OPC_getfield;
                                            break;
                                        case 382:
                                            b = Opcodes.OPC_invokestatic;
                                            break;
                                        default:
                                            switch (c) {
                                                case 536:
                                                    b = Opcodes.OPC_tableswitch;
                                                    break;
                                                case 537:
                                                    b = Opcodes.OPC_invokedynamic;
                                                    break;
                                                case 538:
                                                    b = -34;
                                                    break;
                                                default:
                                                    if (c >= 256) {
                                                        bArrReplacement = replacement();
                                                        b = 0;
                                                    } else {
                                                        b = (byte) c;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                } else {
                    b = Opcodes.OPC_if_acmpeq;
                }
                if (bArrReplacement == null) {
                    byteBuffer.put(b);
                } else {
                    if (byteBuffer.remaining() < bArrReplacement.length) {
                        charBuffer.position(charBuffer.position() - 1);
                        return CoderResult.OVERFLOW;
                    }
                    byteBuffer.put(bArrReplacement);
                }
            }
            return CoderResult.UNDERFLOW;
        }
    }

    public ISO_8859_16() {
        super("ISO-8859-16", new String[]{"iso-ir-226", "ISO_8859-16:2001", "ISO_8859-16", "latin10", "l10", "csISO885916", "ISO8859_16", "ISO_8859_16", "8859_16", "ISO8859-16"});
    }

    @Override // java.nio.charset.Charset
    public boolean contains(Charset charset) {
        return charset.name().equals("US-ASCII") || (charset instanceof ISO_8859_16);
    }

    @Override // java.nio.charset.Charset
    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    @Override // java.nio.charset.Charset
    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }
}
