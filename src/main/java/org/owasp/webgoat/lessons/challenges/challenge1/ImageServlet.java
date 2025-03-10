package org.owasp.webgoat.lessons.challenges.challenge1;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.Random;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageServlet {

  private static final Random random = new Random();  
  public static final int PINCODE = random.nextInt(10000);

  @RequestMapping(
      method = {GET, POST},
      value = "/challenge/logo",
      produces = MediaType.IMAGE_PNG_VALUE)
  @ResponseBody
  public byte[] logo() throws IOException {
    byte[] in =
        new ClassPathResource("lessons/challenges/images/webgoat2.png")
            .getInputStream()
            .readAllBytes();

    String pincode = String.format("%04d", PINCODE);

    in[81216] = (byte) pincode.charAt(0);
    in[81217] = (byte) pincode.charAt(1);
    in[81218] = (byte) pincode.charAt(2);
    in[81219] = (byte) pincode.charAt(3);

    return in;
  }
}
