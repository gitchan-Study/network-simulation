package dns.server;

// -------------------------
// TLD DNS Server (.com, .net, .kr 등)
// -------------------------
// - 특정 최상위 도메인(TLD)에 대한 권한 서버 주소를 알려줌
// - 예: "gitchan-coding.com은 어떤 권한 서버가 관리하나요?"
// -------------------------
public class TldServer extends DnsServer {

    private DnsServer authoritative;

    public TldServer(String name, DnsServer authoritative) {
        super(name);
        this.authoritative = authoritative;
    }

    @Override
    public String query(String domain) {
        System.out.println("🌐 [" + name + "] " + domain + " 담당 권한 서버 주소 알려줌");
        return authoritative.query(domain);
    }
}
