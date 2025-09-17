package dns.server;

import java.util.HashMap;
import java.util.Map;

// -------------------------
// TLD DNS Server (.com, .org, .net 등)
// -------------------------
// - 특정 TLD 하위의 도메인에 맞는 Authoritative 서버로 위임
// -------------------------
public class TldServer extends DnsServer {

    private Map<String, AuthoritativeServer> authoritativeServers = new HashMap<>();

    public TldServer(String name) {
        super(name);
    }

    // Authoritative 서버 등록
    public void addAuthoritativeServer(String domain, AuthoritativeServer server) {
        authoritativeServers.put(domain, server);
    }

    @Override
    public String query(String domain) {
        AuthoritativeServer auth = authoritativeServers.get(domain);
        if (auth == null) {
            System.out.println("🌐 [" + name + "] ❌ 권한 서버 없음: " + domain);
            return null;
        }

        System.out.println("🌐 [" + name + "] " + domain + " 담당 권한 서버 주소 알려줌");
        return auth.query(domain);
    }
}
