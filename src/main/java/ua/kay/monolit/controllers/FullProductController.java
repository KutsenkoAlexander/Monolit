package ua.kay.monolit.controllers;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kay.monolit.models.FullProduct;
import ua.kay.monolit.repositories.FullProductRepository;

import java.util.List;

@RestController
@RequestMapping("/full_product")
public class FullProductController {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    FullProductRepository imgProductRepository;


}
