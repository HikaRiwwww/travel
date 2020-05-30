package com.throne.travel.web.servlet;

import com.throne.travel.domain.Category;
import com.throne.travel.service.CategoryService;
import com.throne.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryServiceImpl();

    public void getAllCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Category> categories = service.findAllCategories();
        writeJsonToStream(resp, categories);
    }
}
