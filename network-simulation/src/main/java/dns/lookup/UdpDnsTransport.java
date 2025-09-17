package dns.lookup;

// -------------------------
// UDP 기반 DNS 전송
// -------------------------
// - 가장 흔히 쓰이는 방식
// - 연결 과정 없이 요청 → 응답만 빠르게 주고받음
// - 단순하고 가볍지만, 신뢰성(재전송 등)은 없음
// -------------------------
public class UdpDnsTransport implements DnsTransport {

    @Override
    public void send(String query) {
        System.out.println("🌐 [Transport: UDP] DNS Query 전송");
        System.out.println("    Payload: \"" + query + "\"");
        System.out.println("    Source Port = random(에페메럴), Dest Port = 53(DNS)\n");
    }
}
