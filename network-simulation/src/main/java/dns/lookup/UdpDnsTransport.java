package dns.lookup;

import dns.server.RootServer;

// -------------------------
// UDP 기반 DNS Transport
// -------------------------
// - 현실에서 DNS는 대부분 UDP(53번 포트)로 동작
// - 여기서는 RootServer를 품고 있고,
//   send() 호출 시 실제 query() 탐색까지 실행
// -------------------------
public class UdpDnsTransport implements DnsTransport {

    private RootServer root;

    public UdpDnsTransport(RootServer root) {
        this.root = root;
    }

    @Override
    public String send(String domain) {
        System.out.println("🌐 [Transport: UDP] DNS Query 전송: " + domain);
        System.out.println("    SourcePort=랜덤(에페메럴), DestPort=53\n");
        return root.query(domain);
    }
}
