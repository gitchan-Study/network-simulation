package dns.lookup;

import java.util.HashMap;
import java.util.Map;

// -------------------------
// ClientDns (내 PC의 DNS 확인 단계)
// -------------------------
// - 1. 브라우저 캐시 확인
// - 2. OS 캐시 확인
// - 3. hosts 파일 확인
// - 4. 다 없으면 로컬 리졸버에게 질의
// -------------------------
public class ClientDns {

    private Map<String, String> browserCache = new HashMap<>();
    private Map<String, String> osCache = new HashMap<>();
    private Map<String, String> hostsFile = new HashMap<>();
    private DnsResolver resolver;

    public ClientDns(DnsResolver resolver) {
        this.resolver = resolver;
        // hosts 파일에 들어있다고 가정하는 값 (모의)
        hostsFile.put("localhost", "127.0.0.1");
        hostsFile.put("myserver", "192.168.0.100");
    }

    public String resolve(String domain) {
        System.out.println("=== Client-side DNS Resolution Start ===");
        System.out.println("[1] 요청 도메인: " + domain);

        // 1. 브라우저 캐시 확인
        if (browserCache.containsKey(domain)) {
            String ip = browserCache.get(domain);
            System.out.println("[2] 브라우저 캐시 hit ✅ → " + domain + " → " + ip);
            return ip;
        }
        System.out.println("[2] 브라우저 캐시 miss ❌");

        // 2. OS 캐시 확인
        if (osCache.containsKey(domain)) {
            String ip = osCache.get(domain);
            System.out.println("[3] OS 캐시 hit ✅ → " + domain + " → " + ip);
            // 브라우저 캐시에 적재
            browserCache.put(domain, ip);
            return ip;
        }
        System.out.println("[3] OS 캐시 miss ❌");

        // 3. hosts 파일 확인
        if (hostsFile.containsKey(domain)) {
            String ip = hostsFile.get(domain);
            System.out.println("[4] hosts 파일 hit ✅ → " + domain + " → " + ip);
            // OS / 브라우저 캐시에 적재
            osCache.put(domain, ip);
            browserCache.put(domain, ip);
            return ip;
        }
        System.out.println("[4] hosts 파일 miss ❌");

        // 4. 로컬 리졸버에게 요청
        System.out.println("[5] 로컬 리졸버에 질의 시작...");
        String ip = resolver.resolve(domain);

        // 응답을 OS / 브라우저 캐시에 저장
        osCache.put(domain, ip);
        browserCache.put(domain, ip);

        System.out.println("[6] 응답을 캐시에 저장 완료 → " + domain + " → " + ip);
        System.out.println("=== Client-side DNS Resolution End ===\n");

        return ip;
    }
}
