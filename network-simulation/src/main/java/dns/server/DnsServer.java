package dns.server;

// -------------------------
// DNS Server 추상 클래스
// -------------------------
// - Root / TLD / Authoritative 서버의 공통 역할 정의
// - query()를 통해 다음 단계로 위임
// -------------------------
public abstract class DnsServer {

    String name;

    public DnsServer(String name) {
        this.name = name;
    }

    public abstract String query(String domain);
}
