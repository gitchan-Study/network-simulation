package dns.lookup;

import dns.server.RootServer;

// -------------------------
// TCP 기반 DNS 전송
// -------------------------
// - 응답이 너무 커서 UDP로는 조각나거나 잘릴 때 사용
// - 연결을 맺은 뒤 안전하게 요청/응답을 보장
// - 오버헤드가 있지만 안정적
// -------------------------
public class TcpDnsTransport implements DnsTransport {

    private RootServer root;

    public TcpDnsTransport(RootServer root) {
        this.root = root;
    }

    @Override
    public String send(String domain) {
        System.out.println("🌐 [Transport: TCP] DNS Query 전송: " + domain);
        System.out.println("    SourcePort=랜덤(에페메럴), DestPort=53(DNS)");
        System.out.println("    TCP 3-way handshake 후 요청 전송\n");

        // Root → TLD → Authoritative 탐색 실행
        return root.query(domain);
    }
}
