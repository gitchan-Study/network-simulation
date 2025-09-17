package dns.server;

// -------------------------
// Authoritative DNS Server
// -------------------------
// - 최종적으로 도메인의 실제 IP 주소를 응답
// - gitchan-coding.com → 203.0.113.50
// -------------------------
public class AuthoritativeServer extends DnsServer {
    String ip;

    public AuthoritativeServer(String name, String ip) {
        super(name);
        this.ip = ip;
    }

    @Override
    public String query(String domain) {
        System.out.println("🌐 [" + name + "] 최종 응답: " + domain + " → " + ip);
        return ip;
    }
}
