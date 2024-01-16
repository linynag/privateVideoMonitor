package org.yzh;

import io.github.yezhihao.protostar.util.Explain;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.yzh.protocol.JT808Beans;
import org.yzh.protocol.basics.JTMessage;
import org.yzh.protocol.codec.JTMessageAdapter;

/**
 * 解码分析
 * @author yezhihao
 * https://gitee.com/yezhihao/jt808-server
 */
public class Elucidator extends JT808Beans {

    public static final JTMessageAdapter coder = new JTMessageAdapter("org.yzh.protocol");

    public static void main(String[] args) {
        String hex = "7e020041340112345678901234567890ffff000004000000080006eeb6ad02633df70138000300632007071923596447000000400101010a0a01010a0a00640001b2070003640e20070719235900013039383736353433323130393837363534333231000000000000000000002007071923590101000165470000004102020214000000141400c8000516150006c81c200707192359000230393837363534333231303938373635343332310000000000000000000020070719235902020002664100000042031e012c00087a23000a2c2a200707192359000330393837363534333231303938373635343332310000000000000000000020070719235903030003006741000000430404280190000bde31000d90382007071923590004303938373635343332313039383736353433323100000000000000000000200707192359040400041f7e";
        JTMessage msg = H2019(T0200JSATL12());

        msg = decode(hex);
        // hex = encode(msg);
    }

    private static String encode(JTMessage message) {
        System.out.println("====================================================================================\n");
        Explain explain = new Explain();
        ByteBuf buf = null;
        try {
            buf = coder.encode(message, explain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String hex = ByteBufUtil.hexDump(buf);
        System.out.println(message);
        System.out.println(hex);
        explain.println();
        return hex;
    }

    private static JTMessage decode(String hex) {
        System.out.println("====================================================================================\n");
        Explain explain = new Explain();
        JTMessage message = null;
        try {
            message = coder.decode(Unpooled.wrappedBuffer(ByteBufUtil.decodeHexDump(hex)), explain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(message);
        System.out.println(hex);
        explain.println();
        return message;
    }
}