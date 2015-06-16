package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by voskart on 16.06.15.
 */
@Controller
@RequestMapping("/download")
public class DownloadUserController {

    @Autowired
    private ServletContext servletContext;
    private static final Logger logger = Logger.getLogger(String.valueOf(RegistrationController.class));
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public FileSystemResource getFile() {

        File file = null;
        try {
            ServletContextResource resource = new ServletContextResource(servletContext,"/WEB-INF/content/users.xml");
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileSystemResource(file);
    }
}
