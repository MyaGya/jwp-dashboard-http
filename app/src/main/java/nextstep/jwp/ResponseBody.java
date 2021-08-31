package nextstep.jwp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class ResponseBody {
    private final String responseBody;

    private ResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public static ResponseBody createByPath(String path) throws IOException {
        if (path.equals("")) {
            return new ResponseBody("");
        }
        URL resource = ResponseBody.class.getClassLoader().getResource("static" + path);
        return new ResponseBody(new String(Files.readAllBytes(new File(resource.getFile()).toPath())));
    }

    public static ResponseBody createByString(String responseBody) {
        return new ResponseBody(responseBody);
    }

    public String getResponseBody() {
        return responseBody;
    }
}
