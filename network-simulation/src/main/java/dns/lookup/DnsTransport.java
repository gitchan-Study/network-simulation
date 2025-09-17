package dns.lookup;

// -------------------------
// DnsTransport 인터페이스
// -------------------------
// - DNS 요청이 어떤 전송 방식(UDP, TCP)으로 나가는지 추상화
// - 실제 네트워크에서는 대부분 UDP(빠르고 단순) 사용
// - 응답이 크거나 특별한 상황에서는 TCP 사용
// -------------------------
interface DnsTransport {

    String send(String query);
}
