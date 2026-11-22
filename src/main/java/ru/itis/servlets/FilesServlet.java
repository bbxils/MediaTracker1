package ru.itis.servlets;

import ru.itis.models.FileInfo;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/uploaded/files")
public class FilesServlet extends HttpServlet {
    private FileService fileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.fileService = (FileService) config.getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileId = req.getParameter("id");
        // получили инфу о загруженном файле
        FileInfo fileInfo = fileService.getFileInfo(Long.parseLong(fileId));
        // в ответ указали тип данных
        resp.setContentType(fileInfo.getType());
        // в ответ указали размер данных
        resp.setContentLength(fileInfo.getSize().intValue());
        // в ответ укзаали оригинальное название файла
        resp.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");
        // записали в ответ сайм файл (данные файла)
        fileService.writeFileFromStorage(Long.parseLong(fileId), resp.getOutputStream());
        // очистка буфера
        resp.flushBuffer();
    }
}
