package ru.itis.servlets;

import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/files")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private FileService fileService;

    // было бы лучше fileDto = FileDto.builder.build();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        fileService.saveFileToStorage(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());

        resp.sendRedirect("/files");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/fileUpload.html").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.fileService = (FileService) config.getServletContext().getAttribute("fileService");
    }
}
