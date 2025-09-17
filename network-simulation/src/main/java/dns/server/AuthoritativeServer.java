package dns.server;

import java.util.HashMap;
import java.util.Map;

// -------------------------
// Authoritative DNS Server
// -------------------------
// - 최종적으로 도메인의 실제 IP 주소를 응답
// - gitchan-coding.com → 203.0.113.50
// -------------------------
public class AuthoritativeServer extends DnsServer {

    private static final String GITCHAN_CODING_DOMAIN = "gitchan-coding.com";
    private static final String GITCHAN_CODING_IP = "203.0.113.50";

    private Map<String, String> ipMap = new HashMap<>();

    public AuthoritativeServer(String name) {
        super(name);

        // 기본 등록 (dummy 데이터)
        ipMap.put(GITCHAN_CODING_DOMAIN, GITCHAN_CODING_IP);
        ipMap.put("example.com", "203.0.113.51");
    }

    @Override
    public String query(String domain) {
        String ip = ipMap.getOrDefault(domain, null);
        if (ip != null) {
            System.out.println("🌐 [" + name + "] 최종 응답: " + domain + " → " + ip);
        } else {
            System.out.println("🌐 [" + name + "] 도메인 미등록 ❌: " + domain);
        }
        return ip;
    }

    public void addRecord(String domain, String ip) {
        ipMap.put(domain, ip);
    }
}
