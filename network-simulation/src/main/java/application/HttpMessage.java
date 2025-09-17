package application;

// ======================================================
// HTTP 메시지 (Application Layer)
// ======================================================
// - TCP 세그먼트의 데이터(payload)에 실리는 실제 응용 계층 데이터
// - 요청(Request) 또는 응답(Response) 형태로 존재
// - 여기서는 단순히 문자열 기반으로 구현
// ======================================================
public class HttpMessage {

    public String startLine; // ex) "GET /index.html HTTP/1.1" or "HTTP/1.1 200 OK"
    String headers;   // ex) "Host: example.com\r\nUser-Agent: ..."
    String body;      // ex) "<html>...</html>"

    @Override
    public String toString() {
        return "HTTP(\n  " + startLine + "\n  " +
                headers + "\n  " +
                (body != null ? body : "") + "\n)";
    }
}
