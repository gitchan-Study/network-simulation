package dns.server;

// -------------------------
// DnsTopology
// -------------------------
// - Root / TLD / Authoritative 서버 계층을 초기화하는 유틸
// - 현실에서는 이미 전 세계적으로 존재하는 DNS 서버 집합
// - 코드에서는 학습용으로 Root 아래에 .com / .org TLD, 그리고 각각의 Authoritative 서버들을 구성
// -------------------------
public class DnsTopology {

    public static RootServer build() {
        // Authoritative 서버 (실제 IP 매핑 보관)
        AuthoritativeServer auth_com = new AuthoritativeServer("Authoritative .com Server");
        auth_com.addRecord("gitchan-coding.com", "203.0.113.50");
        auth_com.addRecord("example.com", "203.0.113.51");

        AuthoritativeServer auth_org = new AuthoritativeServer("Authoritative .org Server");
        auth_org.addRecord("opensource.org", "203.0.113.60");

        // TLD 서버
        TldServer tldCom = new TldServer("TLD .com DNS");
        tldCom.addAuthoritativeServer("gitchan-coding.com", auth_com);
        tldCom.addAuthoritativeServer("example.com", auth_com);

        TldServer tldOrg = new TldServer("TLD .org DNS");
        tldOrg.addAuthoritativeServer("opensource.org", auth_org);

        // Root 서버
        RootServer root = new RootServer("Root DNS");
        root.addTldServer("com", tldCom);
        root.addTldServer("org", tldOrg);

        return root;
    }
}
