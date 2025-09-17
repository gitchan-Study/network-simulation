package dns.lookup;

// -------------------------
// TCP 기반 DNS 전송
// -------------------------
// - 응답이 너무 커서 UDP로는 조각나거나 잘릴 때 사용
// - 연결을 맺은 뒤 안전하게 요청/응답을 보장
// - 오버헤드가 있지만 안정적
// -------------------------
public class TcpDnsTransport implements DnsTransport {

    @Override
    public void send(String query) {
        System.out.println("🌐 [Transport: TCP] DNS Query 전송");
        System.out.println("    Payload: \"" + query + "\"");
        System.out.println("    Source Port = random(에페메럴), Dest Port = 53(DNS)");
        System.out.println("    TCP 3-way handshake 후 요청\n");
    }
}
