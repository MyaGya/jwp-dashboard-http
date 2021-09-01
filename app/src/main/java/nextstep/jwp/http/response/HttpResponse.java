package nextstep.jwp.http.response;

public class HttpResponse {
    private final ResponseLine responseLine;
    private final ResponseHeader responseHeader;
    private final ResponseBody responseBody;

    public HttpResponse(ResponseLine responseLine, ResponseHeader responseHeader, ResponseBody responseBody) {
        this.responseLine = responseLine;
        this.responseHeader = responseHeader;
        this.responseBody = responseBody;
    }


    public String getResponseToString() {
        return String.join("\r\n",
                responseLine.getResponseLine(),
                responseHeader.getResponseHeader(),
                responseBody.getResponseBody());
    }
}
